/****************************************************************************
 *
 * Copyright (c) 2009-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.formeditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * The Petals form editor for JBI descriptors.
 */
public class JbiFormEditor extends EditorPart
implements IEditorPart, ISelectionProvider, ISharedEdition {

	private static final String EXTENSION_POINT = "com.ebmwebsourcing.petals.common.formeditor";

	private DataBindingContext dbc;
	private Jbi jbiModel;
	private TransactionalEditingDomain editDomain;
	private FormToolkit toolkit;
	private IFile editedFile;

	private IResourceChangeListener workspaceListener;
	private IJbiEditorPersonality personality;
	private Image editorImage;
	private ScrolledForm mainForm;

	private final Set<ISelectionChangedListener> selectionListeners = new HashSet<ISelectionChangedListener>();
	private ISelection selection;



	/**
	 * @return the editor personality
	 */
	private IJbiEditorPersonality getPersonality() {

		if( this.personality == null && this.editedFile != null) {

			// Look into the extensions
			IExtensionRegistry reg = Platform.getExtensionRegistry();
			List<IJbiEditorPersonality> personalities = new ArrayList<IJbiEditorPersonality> ();
			IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_POINT );
			for( IConfigurationElement elt : extensions ) {

					String className = elt.getAttribute( "class" );
					if( StringUtils.isEmpty( className )) {
						PetalsCommonPlugin.log( "No personality was found for " + elt.getContributor().getName(), IStatus.ERROR );
						continue;
					}

					try {
						IJbiEditorPersonality pers = (IJbiEditorPersonality) elt.createExecutableExtension( "class" );
						personalities.add( pers );

					} catch( CoreException e ) {
						PetalsCommonPlugin.log( "A JBI personality could not be instantiated - " + className, IStatus.ERROR );
					}
			}

			for( IJbiEditorPersonality pers : personalities ) {
				if( pers.matchesPersonality( this.jbiModel, this.editedFile )) {
					this.personality = pers;
					break;
				}
			}

			if( this.personality == null )
				this.personality = new JbiDefaultPersonality();
		}

		return this.personality;
	}


	/**
	 * Loads the DOM model.
	 * <p>
	 * If this model was already loaded, we reuse it. Otherwise, we make it loaded.
	 * </p>
	 */
	private void loadSharedModel() throws IOException {

		try {
			// Get the edited file
			if (getEditorInput() instanceof IFileEditorInput) {
				this.editedFile = ((IFileEditorInput) getEditorInput()).getFile();
				if( !this.editedFile.exists())
					throw new FileNotFoundException( this.editedFile.getLocation() + " does not exist.");

				if( ! this.editedFile.isSynchronized( IResource.DEPTH_ONE))
					this.editedFile.refreshLocal( IResource.DEPTH_ONE, null );

			} else {
				// Don't load anything
				return;
			}

			ResourceSet resourceSet = new ResourceSetImpl();
			this.editDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain( resourceSet );
			URI resourceUri = URI.createPlatformResourceURI(this.editedFile.getFullPath().toString(), true);
			Resource resource = resourceSet.getResource(resourceUri, true);
			resource.load( resourceSet.getLoadOptions());
			this.jbiModel = ((DocumentRoot) resource.getContents().get(0)).getJbi();

			this.editDomain.getCommandStack().addCommandStackListener( new CommandStackListener() {
				@Override
				public void commandStackChanged( final EventObject event ) {
					Display.getDefault().asyncExec( new Runnable() {
						@Override
						public void run() {
							firePropertyChange( IEditorPart.PROP_DIRTY );
						}
					});
				}
			});

		} catch( CoreException e ) {
			PetalsCommonPlugin.log(e, IStatus.WARNING);
		}
	}


	/**
	 * Initializes the resource listener to check markers.
	 */
	private void initializeResourceListener() {

		// No need to monitor changes if the file is not in the workspace
		if( this.editedFile == null )
			return;

		// The resource delta visitor
		final IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
			@Override
			public boolean visit( final IResourceDelta delta ) throws CoreException {

				// Check for changes.
				if( delta.getResource().equals( JbiFormEditor.this.editedFile )) {

					// Deleted or renamed
					if( delta.getKind() == IResourceDelta.REMOVED ) {
						if( delta.getMovedToPath() == null ) {
							// Deleted
							getSite().getShell().getDisplay().asyncExec( new Runnable() {
								@Override
								public void run() {
									getSite().getPage().closeEditor( JbiFormEditor.this, false );
								}
							});

						} else {
							// Renamed?
							if( delta.getMovedToPath() != null ) {
								Display.getDefault().asyncExec( new Runnable() {
									@Override
									public void run() {
										setPartName( delta.getMovedToPath().lastSegment());
									}
								});
							}
						}
					}

					// Marker changes
					else if((delta.getFlags() & IResourceDelta.MARKERS) != 0 )
						refreshMarkers();

					return false;
				}

				// Keep on checking on, only if the resource is an ancestor of the edited file.
				return delta.getResource().getFullPath().isPrefixOf( JbiFormEditor.this.editedFile.getFullPath());
			}
		};

		// The resource change listener
		this.workspaceListener = new IResourceChangeListener() {
			@Override
			public void resourceChanged( IResourceChangeEvent event ) {
				try {
					event.getDelta().accept( visitor );

				} catch( CoreException e ) {
					PetalsCommonPlugin.log( e, IStatus.ERROR );
				}
			}
		};

		// Register it
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
					this.workspaceListener,
					IResourceChangeEvent.POST_BUILD );
	}


	/**
	 * Refreshes the markers
	 * @param markerDeltas
	 */
	private void refreshMarkers() {

		if( this.editedFile == null || ! this.editedFile.exists())
			return;

		try {
			// Prepare the messages
			final Set<String> warningMessages = new HashSet<String> ();
			final Set<String> errorMessages = new HashSet<String> ();
			IMarker[] markers = this.editedFile.findMarkers( PetalsConstants.MARKER_ID_JBI_XML, true, IResource.DEPTH_ZERO );
			if( markers != null ) {
				for( IMarker marker : markers ) {
					int severity =  marker.getAttribute( IMarker.SEVERITY, -1 );
					if( severity == IMarker.SEVERITY_ERROR )
						errorMessages.add( marker.getAttribute( IMarker.MESSAGE, "" ));
					else if( severity == IMarker.SEVERITY_WARNING )
						warningMessages.add( marker.getAttribute( IMarker.MESSAGE, "" ));
				}
			}


			// Update the message manager
			Display.getDefault().asyncExec( new Runnable() {
				@Override
				public void run() {

					JbiFormEditor.this.mainForm.getMessageManager().removeAllMessages();
					int i = 0;
					for( String msg : errorMessages )
						JbiFormEditor.this.mainForm.getMessageManager().addMessage( "error" + i++, msg, null, IMessageProvider.ERROR );

					for( String msg : warningMessages )
						JbiFormEditor.this.mainForm.getMessageManager().addMessage( "warning" + i++, msg, null, IMessageProvider.WARNING );
				}
			});

		} catch( CoreException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * This is called during startup.
	 */
	@Override
	public void init( IEditorSite site, IEditorInput editorInput ) {

		setSite( site );
		setInputWithNotify( editorInput );
		if (editorInput instanceof FileEditorInput)
			setPartName(((FileEditorInput) editorInput).getFile().getProject().getName());
		else
			setPartName( editorInput.getName());

		site.setSelectionProvider( this );
		try {
			loadSharedModel();
			initializeResourceListener();
			this.dbc = new DataBindingContext();

		} catch( Exception ex ) {
			PetalsCommonPlugin.log( ex, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave( IProgressMonitor monitor ) {

		// Save only resources that have actually changed.
		final Map<Object,Object> saveOptions = JbiXmlUtils.getJbiXmlSaveOptions();
		saveOptions.put( Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER );

		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			public void execute( IProgressMonitor monitor ) {

				for( Resource resource : getEditingDomain().getResourceSet().getResources()) {
					try {
						resource.save( saveOptions );

					} catch( Exception exception ) {
						PetalsCommonPlugin.log( exception, IStatus.ERROR );
					}
				}
			}
		};

		try {
			new ProgressMonitorDialog( getSite().getShell()).run( true, false, operation );
			((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
			firePropertyChange( IEditorPart.PROP_DIRTY );

		} catch( Exception exception ) {
			PetalsCommonPlugin.log( exception, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// Not supported
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		this.selectionListeners.add(listener);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #getSelection()
	 */
	@Override
	public ISelection getSelection() {
		return this.selection;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void removeSelectionChangedListener( ISelectionChangedListener listener ) {
		this.selectionListeners.remove(listener);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setSelection(ISelection selection) {

		// Update the selection
		this.selection = selection;
		for (ISelectionChangedListener listener : this.selectionListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}

		// Update the status line
		try {
			IStatusLineManager manager = getEditorSite().getActionBars().getStatusLineManager();
			if (selection instanceof IStructuredSelection) {

				IStructuredSelection sse = (IStructuredSelection) selection;
				switch (sse.size()) {
				case 0:
					manager.setMessage("");
					break;

				case 1:
					ILabelProvider lp = getStatusLineLabelProvider();
					if (lp != null) {
						String msg = lp.getText(sse.getFirstElement());
						Image img = lp.getImage(sse.getFirstElement());
						manager.setMessage(img, msg);
					}
					break;

				default:
					manager.setMessage(sse.size() + " selected elements");
					break;
				}
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log(e, IStatus.WARNING);
		}
	}


	/**
	 * @return a label provider for the status line manager
	 */
	public ILabelProvider getStatusLineLabelProvider() {
		IJbiEditorPersonality pers = getPersonality();
		return pers != null ? pers.getStatusLineLabelProvider() : null;
	}


	/**
	 * @return
	 */
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.editDomain != null )
			this.editDomain.dispose();

		if( this.dbc != null )
			this.dbc.dispose();

		if( this.toolkit != null )
			this.toolkit.dispose();

		if( this.editorImage != null && ! this.editorImage.isDisposed())
			this.editorImage.dispose();

		ResourcesPlugin.getWorkspace().removeResourceChangeListener( this.workspaceListener );
		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #isDirty()
	 */
	@Override
	public boolean isDirty() {
		CommandStack commandStack = getEditingDomain().getCommandStack();
		return commandStack != null ?  ((BasicCommandStack) commandStack).isSaveNeeded() : false;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart
	 * #createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl( Composite parent ) {

		IJbiEditorPersonality pers = getPersonality();
		this.toolkit = new FormToolkit( getSite().getShell().getDisplay());
		this.mainForm = this.toolkit.createScrolledForm( parent );
		this.mainForm.setText( pers != null ? pers.getTitle() : "Title" );
		this.editorImage = pers != null ? pers.getTitleImage() : null;
		this.mainForm.setImage( this.editorImage );

		this.toolkit.decorateFormHeading( this.mainForm.getForm());
		this.toolkit.paintBordersFor( this.mainForm.getBody());

		GridLayout layout = new GridLayout();
		layout.marginBottom = 4;
		this.mainForm.getBody().setLayout( layout );
		this.mainForm.setLayoutData ( new GridData( GridData.FILL_BOTH ));

		if( pers != null )
			pers.createControl( this.mainForm.getBody(), this );

		refreshMarkers();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart
	 * #setFocus()
	 */
	@Override
	public void setFocus() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.domain.IEditingDomainProvider
	 * #getEditingDomain()
	 */
	@Override
	public EditingDomain getEditingDomain() {
		return this.editDomain;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.ISharedEdition
	 * #getFormToolkit()
	 */
	@Override
	public FormToolkit getFormToolkit() {
		return this.toolkit;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.ISharedEdition
	 * #getDataBindingContext()
	 */
	@Override
	public DataBindingContext getDataBindingContext() {
		return this.dbc;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.ISharedEdition
	 * #getJbiModel()
	 */
	@Override
	public Jbi getJbiModel() {
		return this.jbiModel;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.ISharedEdition
	 * #getEditedFile()
	 */
	@Override
	public IFile getEditedFile() {
		return this.editedFile;
	}
}
