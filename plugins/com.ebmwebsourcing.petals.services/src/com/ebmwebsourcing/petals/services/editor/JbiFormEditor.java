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

package com.ebmwebsourcing.petals.services.editor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPathConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelStateListener;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.common.internal.provisional.builder.JbiXmlBuilder;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.sse.PetalsProcessorXML;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.sa.editor.SaPersonality;
import com.ebmwebsourcing.petals.services.su.editor.SuPersonality;

/**
 * The Petals form editor for JBI descriptors.
 * <p>
 * Let in *.services for historical reasons, but may be moved in *.common.
 * </p>
 */
@SuppressWarnings( "restriction" )
public class JbiFormEditor
extends FormEditor
implements ISelectionProvider, IGotoMarker, IPartListener2 {

	private static final String JBI_TEXT_EDITOR_ID = "com.ebmwebsourcing.petals.common.xmleditor"; //$NON-NLS-1$
	private static final String JBI_FORM_EDITOR_ID = "com.ebmwebsourcing.petals.services.formeditor"; //$NON-NLS-1$

	private IDOMModel model;
	private IResourceChangeListener workspaceListener;
	private IModelStateListener modelListener;
	private ISelection selection;
	private IJbiEditorPersonality personality;

	private final PetalsProcessorXML formatter = new PetalsProcessorXML();
	private final Set<ISelectionChangedListener> selectionListeners = new HashSet<ISelectionChangedListener> ();

	protected final Map<String,AbstractServicesFormPage> pages = new HashMap<String,AbstractServicesFormPage> ();
	protected StructuredTextEditor editor;
	protected IFile editedFile;


	/**
	 * Constructor.
	 */
	public JbiFormEditor() {
		super();
	}


	/**
	 * @return the editor personality
	 */
	private IJbiEditorPersonality getPersonality() {

		if( this.personality == null && this.editedFile != null ) {
			IJbiEditorPersonality[] personalities = new IJbiEditorPersonality[] {
						new SuPersonality(),
						new SaPersonality()
			};

			for( IJbiEditorPersonality pers : personalities ) {
				if( pers.matchesPersonality( this.editedFile )) {
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
				AbstractServicesFormPage page = pers.getGeneralMasterPage( this );
				addPage( page );
				this.pages.put( page.getId(), page );
			}

			// Always add the page with the source editor
			this.editor = new StructuredTextEditor();
			int index = addPage( this.editor, getEditorInput());
			setPageText( index, "Source" );

		} catch( PartInitException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		updateEditor();
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
			public boolean visit( final IResourceDelta delta ) throws CoreException {

				// Check for changes.
				if( delta.getResource().equals( JbiFormEditor.this.editedFile )) {

					// Deleted? Close the editor.
					boolean deleted = false;
					if( delta.getKind() == IResourceDelta.REMOVED ) {
						deleted = delta.getMovedToPath() == null;
						if( deleted ) {
							getSite().getShell().getDisplay().asyncExec( new Runnable() {
								public void run() {

									// The part listener should handle it then...
									getSite().getPage().closeEditor( JbiFormEditor.this, false );
								}
							});
						}
					}

					if( ! deleted ) {

						// Renamed?
						if( delta.getMovedToPath() != null ) {
							Display.getDefault().asyncExec( new Runnable() {
								public void run() {
									setPartName( delta.getMovedToPath().lastSegment());
								}
							});
						}

						// Update markers
						updateMarkers();
					}

					return false;
				}

				// Keep on checking on, only if the resource is an ancestor of the edited file.
				return delta.getResource().getFullPath().isPrefixOf(
							JbiFormEditor.this.editedFile.getFullPath());
			}
		};

		// The resource change listener
		this.workspaceListener = new IResourceChangeListener() {
			public void resourceChanged( IResourceChangeEvent event ) {

				try {
					event.getDelta().accept( visitor );
				} catch( CoreException e ) {
					e.printStackTrace();
				}
			}
		};

		// Register it
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
					this.workspaceListener,
					IResourceChangeEvent.POST_BUILD );
	}


	/**
	 * Updates the markers in the editor from the edited file.
	 */
	private void updateMarkers() {

		// No need to monitor changes if the file is not in the workspace
		if( this.editedFile == null )
			return;

		// Get the markers
		IMarker[] markers;
		try {
			markers = JbiFormEditor.this.editedFile.findMarkers(
						PetalsConstants.MARKER_ID_JBI_XML, true, IResource.DEPTH_ONE );
		} catch( CoreException e ) {
			markers = new IMarker[ 0 ];
		}

		// Associate the markers with a node
		final Map<IMarker,Node> markerToNode = new HashMap<IMarker, Node> ();
		for( IMarker marker : markers ) {
			String xpathLocation =
				marker.getAttribute( PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE, null );

			if( xpathLocation == null || xpathLocation.trim().length() == 0 )
				continue;

			// Resolve the element
			Node node = (Node) XPathUtils.evaluateXPathExpression(
						xpathLocation, this.model.getDocument(), XPathConstants.NODE );

			if( node != null )
				markerToNode.put( marker, node );
		}

		getSite().getShell().getDisplay().asyncExec( new Runnable() {
			public void run() {
				for( AbstractServicesFormPage page : JbiFormEditor.this.pages.values())
					page.updateMarkers( markerToNode );
			}
		});
	}


	/**
	 * Embeds the XML Editor as source page into the Form Editor.
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createSite(org.eclipse.ui.IEditorPart)
	 */
	@Override
	protected IEditorSite createSite( IEditorPart page ) {

		IEditorSite site = null;
		if( page == this.editor ) {
			site = new MultiPageEditorSite( this, page ) {
				@Override
				public String getId() {
					// return the ID of our customized text editor
					return JBI_TEXT_EDITOR_ID;
				}
			};
		} else {
			site = super.createSite( page );
		}

		return site;
	}


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


			// Get the underlying model
			IStructuredModel model = StructuredModelManager.getModelManager().getExistingModelForEdit( this.editedFile );
			if( model == null )
				model = StructuredModelManager.getModelManager().getModelForEdit( this.editedFile );

			this.model = (IDOMModel) model;
			this.modelListener = new IModelStateListener () {

				public void modelAboutToBeChanged( IStructuredModel model ) {}
				public void modelAboutToBeReinitialized( IStructuredModel structuredModel ) {}

				public void modelChanged( IStructuredModel model ) {
					updateEditor();
				}

				public void modelDirtyStateChanged( IStructuredModel model, boolean isDirty ) {
					// nothing
				}

				public void modelReinitialized( IStructuredModel structuredModel ) {
					updateEditor();
				}

				public void modelResourceDeleted( IStructuredModel model ) {
					// TODO: close the editor
				}
				public void modelResourceMoved( IStructuredModel oldModel, IStructuredModel newModel ) {
					// TODO: update editor
				}
			};

			this.model.addModelStateListener( this.modelListener );


			// Update markers
			JbiXmlBuilder.resolveLineNumbers( this.editedFile );

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Updates the editor UI.
	 */
	protected void updateEditor() {

		for( AbstractServicesFormPage page : this.pages.values()) {
			page.setDocument( this.model.getDocument());
			if( page.getPartControl() != null && ! page.getPartControl().isDisposed())
				page.updatePage();
		}
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

		try {
			setSite( site );
			setInputWithNotify( editorInput );
			if (editorInput instanceof FileEditorInput) {
				setPartName( ((FileEditorInput)editorInput).getFile().getProject().getName() );
			} else {
				setPartName( editorInput.getName());
			}
			site.setSelectionProvider( this );

			loadSharedModel();

			site.getWorkbenchWindow().getPartService().addPartListener( this );
			initializeResourceListener();
			updateMarkers();

		} catch( IOException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			close( false );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave( IProgressMonitor monitor ) {

		try {
			if( this.model != null )
				this.model.save();
			else
				this.editor.doSave( monitor );

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
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
		for( ISelectionChangedListener listener : this.selectionListeners )
			listener.selectionChanged( new SelectionChangedEvent( this, selection ));

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
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ide.IGotoMarker
	 * #gotoMarker(org.eclipse.core.resources.IMarker)
	 */
	public void gotoMarker( IMarker marker ) {

		// Find the element
		Node node = null;
		String xPathLocation =
			marker.getAttribute( PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE, null );

		if( xPathLocation != null ) {
			node = (Node) XPathUtils.evaluateXPathExpression(
						xPathLocation, this.model.getDocument(), XPathConstants.NODE );
		}

		if( node != null ) {

			// Reveal it in the form pages
			boolean found = false;
			for( Map.Entry<String,AbstractServicesFormPage> entry : this.pages.entrySet()) {

				if( entry.getValue().gotoMarker( node, xPathLocation )) {
					found = true;
					setActivePage( entry.getKey());
					break;
				}
			}

			// Try to select the element in the text viewer if it was not found
			if( ! found ) {

				if( node instanceof IDOMElement ) {
					int startOffset = ((IDOMElement) node).getStartOffset();
					int length = ((IDOMElement) node).getLength();
					this.editor.selectAndReveal( startOffset, length );
				}
				else if( node instanceof IDOMAttr ) {
					int startOffset = ((IDOMAttr) node).getStartOffset();
					int length = ((IDOMAttr) node).getLength();
					this.editor.selectAndReveal( startOffset, length );
				}
			}
		}

		// Give the focus to the editor
		setFocus();
	}


	/**
	 * @return the editedFile
	 */
	public IFile getEditedFile() {
		return this.editedFile;
	}


	/**
	 * Indicates the model is about to be changed.
	 * <p>
	 * Useful when the model is shared by other editors.
	 * </p>
	 */
	public void aboutToChangeModel () {
		this.model.aboutToChangeModel();
	}


	/**
	 * Indicates the model has changed.
	 * <p>
	 * Useful when the model is shared by other editors.
	 * </p>
	 */
	public void changedModel () {
		this.model.changedModel();

		// Format after a change
		try {
			if( PreferencesManager.formatJbiXmlAutomatically())
				this.formatter.formatDocument( this.model.getStructuredDocument());

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR, "Document formatting failed." );
		}
	}


	/**
	 * @return a label provider for the status line manager
	 */
	public ILabelProvider getStatusLineLabelProvider() {

		ILabelProvider result = null;
		IJbiEditorPersonality pers = getPersonality();
		if( pers != null )
			result = pers.getStatusLineLabelProvider();

		return result;
	}


	// IPartListener methods

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2
	 * #partActivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public void partActivated( IWorkbenchPartReference partRef ) {
		// nothing
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
						this.model.removeModelStateListener( this.modelListener );
						this.model.releaseFromEdit();
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
}
