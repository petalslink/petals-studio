/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.jbi.editor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.FileEditorInput;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.su.SuPersonality;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * The Petals form editor for JBI descriptors.
 * <p>
 * Let in *.services for historical reasons, but may be moved in *.common.
 * </p>
 */
public class JbiFormEditor extends FormEditor implements ISelectionProvider, IPartListener2, IEditingDomainProvider {

	private static final String JBI_FORM_EDITOR_ID = "com.ebmwebsourcing.petals.services.formeditor"; //$NON-NLS-1$

	private Jbi model;
	private IResourceChangeListener workspaceListener;
	private IJbiEditorPersonality personality;

	private final Set<ISelectionChangedListener> selectionListeners = new HashSet<ISelectionChangedListener> ();

	protected final Map<String,AbstractJBIFormPage> pages = new HashMap<String,AbstractJBIFormPage> ();
	protected IFile editedFile;

	private ResourceSet resourceSet;
	private TransactionalEditingDomain editDomain;
	protected Collection<Resource> savedResources = new ArrayList<Resource>();
	private DataBindingContext dbc;
	
	private ISelection selection;

	/**
	 * Constructor.
	 */
	public JbiFormEditor() {
		super();
		dbc = new DataBindingContext();
	}


	/**
	 * @return the editor personality
	 */
	private IJbiEditorPersonality getPersonality() {

		if( this.personality == null && this.editedFile != null ) {
			IJbiEditorPersonality[] personalities = new IJbiEditorPersonality[] {
						new SuPersonality(),
						//new SaPersonality()
			};

			for( IJbiEditorPersonality pers : personalities ) {
				if( pers.matchesPersonality(model, this.editedFile )) {
					this.personality = pers;
					break;
				}
			}
		}

		return this.personality;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor
	 * #addPages()
	 */
	@Override
	protected void addPages() {

		try {
			// Find the personality
			IJbiEditorPersonality pers = getPersonality();

			// Add the "General" page
			if( pers != null ) {
				AbstractJBIFormPage page = pers.getGeneralMasterPage( this );
				addPage( page );
				this.pages.put( page.getId(), page );
			}
			
			// Always add the page with the source editor
			/*this.srcEditor = new StructuredTextEditor();
			int index = addPage( this.srcEditor, getEditorInput());
			setPageText( index, "Source" );*/

		} catch( PartInitException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		updateEditor();
	}


	/**
	 * Embeds the XML Editor as source page into the Form Editor.
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createSite(org.eclipse.ui.IEditorPart)
	 */
	/*@Override
	protected IEditorSite createSite( IEditorPart page ) {

		IEditorSite site = null;
		if( page == this.srcEditor ) {
			site = new MultiPageEditorSite( this, page );
		} else {
			site = super.createSite( page );
		}

		return site;
	}*/


	/**
	 * Loads the DOM model.
	 * <p>
	 * If this model was already loaded, we reuse it.
	 * Otherwise, we make it loaded.
	 * </p>
	 */
	protected void loadSharedModel() throws IOException {

		try {
			// Get the edited file
			if( getEditorInput() instanceof IFileEditorInput ) {
				this.editedFile = ((IFileEditorInput) getEditorInput()).getFile();
				if( ! this.editedFile.exists()) {
					throw new FileNotFoundException( this.editedFile.getLocation() + " does not exist." );
				}

				if( ! this.editedFile.isSynchronized( IResource.DEPTH_ONE )) {
					this.editedFile.refreshLocal( IResource.DEPTH_ONE, null );
				}

			}

			// Otherwise, do no go further: only the source page will be displayed
			else {
				return;
			}


			resourceSet = new ResourceSetImpl();
			this.editDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
			Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(editedFile.getFullPath().toString(), true), true);
			resource.load(resourceSet.getLoadOptions());
			model = ((DocumentRoot)resource.getContents().get(0)).getJbi();
			
			this.editDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
				 public void commandStackChanged(final EventObject event) {
					 getContainer().getDisplay().asyncExec(new Runnable() {
							  public void run() {
								  firePropertyChange(IEditorPart.PROP_DIRTY);
							  }
					 });
				 }
			});

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Updates the editor UI.
	 */
	protected void updateEditor() {
		// Nothing at the moment
		/*for( AbstractJBIFormPage page : this.pages.values()) {
			if( page.getPartControl() != null && ! page.getPartControl().isDisposed()) {
				page.updatePage();
			}
		}*/
	}


	/***************************************************************************
	 * Add an outline view
	 **************************************************************************/

	// @Override
	// public Object getAdapter( Class required ) {
	// if( IContentOutlinePage.class.equals( required )) {
	// if( outlinePage == null ) {
	// outlinePage = new ScaCompositeWizardEditorOutline ();
	// TODO: add missing line - add input
	// }
	// return outlinePage;
	// }
	// return super.getAdapter( required );
	// }

	/***************************************************************************
	 * Adding EMF Code (from SCA EMF Editor)
	 **************************************************************************/


	/**
	 * This is called during startup.
	 */
	@Override
	public void init( IEditorSite site, IEditorInput editorInput ) {
		setSite( site );
		setInputWithNotify( editorInput );
		if (editorInput instanceof FileEditorInput) {
			setPartName( ((FileEditorInput)editorInput).getFile().getProject().getName() );
		} else {
			setPartName( editorInput.getName());
		}
		site.setSelectionProvider( this );

		try {
			loadSharedModel();
		} catch (Exception ex) {
			PetalsServicesPlugin.log(ex, IStatus.ERROR);
		}
		site.getWorkbenchWindow().getPartService().addPartListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave( IProgressMonitor monitor ) {

		try {
			// Save only resources that have actually changed.
			//
			final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
			saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

			// Do the work within an operation because this is a long running activity that modifies the workbench.
			//
			WorkspaceModifyOperation operation =
				new WorkspaceModifyOperation() {
					// This is the method that gets invoked when the operation runs.
					//
					@Override
					public void execute(IProgressMonitor monitor) {
						// Save the resources to the file system.
						//
						for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
							try {
								long timeStamp = resource.getTimeStamp();
								resource.save(saveOptions);
								if (resource.getTimeStamp() != timeStamp) {
									savedResources.add(resource);
								}
							}
							catch (Exception exception) {
								PetalsServicesPlugin.log(exception, IStatus.ERROR);
							}
						}
					}
				};

			try {
				new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

				((BasicCommandStack)getEditingDomain().getCommandStack()).saveIsDone();
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
			catch (Exception exception) {
				PetalsServicesPlugin.log(exception, IStatus.ERROR);
			}

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// Not supported
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
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
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		this.selectionListeners.add( listener );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #getSelection()
	 */
	public ISelection getSelection() {
		return this.selection;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener( ISelectionChangedListener listener) {
		this.selectionListeners.remove( listener );
	}
	
		/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection( ISelection selection ) {

		// Update the selection
		this.selection = selection;
		for( ISelectionChangedListener listener : this.selectionListeners ) {
			listener.selectionChanged( new SelectionChangedEvent( this, selection ));
		}

		// Update the status line
		try {
			IStatusLineManager manager = getEditorSite().getActionBars().getStatusLineManager();
			if( selection instanceof IStructuredSelection ) {

				IStructuredSelection sse = (IStructuredSelection) selection;
				switch( sse.size()) {
				case 0:
					manager.setMessage( "" );
					break;
				case 1:
					ILabelProvider lp = getStatusLineLabelProvider();
					if( lp != null ) {
						String msg = lp.getText( sse.getFirstElement());
						Image img = lp.getImage( sse.getFirstElement());
						manager.setMessage( img, msg );
					}
					break;
				default:
					manager.setMessage( sse.size() + " selected elements" );
					break;
				}
			}

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)	/**
	 * @return the editedFile
	 */
	public IFile getEditedFile() {
		return this.editedFile;
	}


	/**
	 * @return a label provider for the status line manager
	 */
	public ILabelProvider getStatusLineLabelProvider() {

		ILabelProvider result = null;
		IJbiEditorPersonality pers = getPersonality();
		if( pers != null ) {
			result = pers.getStatusLineLabelProvider();
		}

		return result;
	}


	// IPartListener methods

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partActivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partActivated( IWorkbenchPartReference partRef ) {
		getActionBarContributor().setActiveEditor(this);
	}
	
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partBroughtToTop( IWorkbenchPartReference partRef ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partClosed(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partClosed( IWorkbenchPartReference partRef ) {
		if (dbc != null) {
			dbc.dispose();
		}
		if (editDomain != null) {
			editDomain.dispose();
		}
		// Remove the listeners
		if( partRef instanceof IEditorReference ) {
			try {
				boolean eq = ((IEditorReference) partRef).getEditorInput().equals( getEditorInput());
				if( eq && ((IEditorReference) partRef).getId().equals( JBI_FORM_EDITOR_ID )) {

					// Same editor and same input
					getSite().getWorkbenchWindow().getPartService().removePartListener( this );

					// No model or workspace changes are listened to
					// if the edited file is not in the workspace
					if( this.editedFile != null ) {
						ResourcesPlugin.getWorkspace().removeResourceChangeListener( this.workspaceListener );
					}
				}

			} catch( PartInitException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partDeactivated( IWorkbenchPartReference partRef ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partHidden(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partHidden( IWorkbenchPartReference partRef ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partInputChanged( IWorkbenchPartReference partRef ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partOpened(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partOpened( IWorkbenchPartReference partRef ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partVisible(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partVisible( IWorkbenchPartReference partRef ) {
		// nothing
	}
	
	@Override
	public boolean isDirty() {
		CommandStack commandStack = getEditingDomain().getCommandStack();
		if (commandStack != null) {
			return ((BasicCommandStack)commandStack).isSaveNeeded();
		} else {
			return false;
		}
	}


	@Override
	public EditingDomain getEditingDomain() {
		return editDomain;
	}
	
	public Jbi getJbiModel() {
		return this.model;
	}
	
	public DataBindingContext getDataBindingContext() {
		return this.dbc;
	}
}
