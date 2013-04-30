/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ContainerGenerator;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.actions.ArrangeAllAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.ConnectionToAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.CopyNodeAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.ExportDiagramAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.PasteNodeAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.ShowPropertiesAction;
import com.ebmwebsourcing.petals.services.eip.designer.actions.SwitchNodesAction;
import com.ebmwebsourcing.petals.services.eip.designer.edit.ConnectionCreationFactory;
import com.ebmwebsourcing.petals.services.eip.designer.edit.EipNodeCreationFactory;
import com.ebmwebsourcing.petals.services.eip.designer.edit.EndpointCreationFactory;
import com.ebmwebsourcing.petals.services.eip.designer.edit.PetalsEndpointDropTargetListener;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipEditPartFactory;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.EipChainTransactionalValidator;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;
import com.ebmwebsourcing.petals.services.eip.designer.outline.EipDiagramOutlinePage;
import com.ebmwebsourcing.petals.services.eip.designer.palette.EipPalettePreferences;
import com.ebmwebsourcing.petals.services.eip.designer.palette.EipPaletteViewer;
import com.ebmwebsourcing.petals.services.eip.designer.zoom.ZoomInToolEntry;
import com.ebmwebsourcing.petals.services.eip.designer.zoom.ZoomOutToolEntry;

/**
 * A SWT widget that contains an EIP diagram.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChainDiagramEditor extends EditorPart
implements IResourceChangeListener, ITabbedPropertySheetPageContributor, CommandStackListener, ISelectionListener {

	/**
	 * The ID of this editor.
	 */
	public final static String EDITOR_ID = "com.ebmwebsourcing.petals.services.EipDesigner";
	private static final String EIP_MARKER_ID = "com.ebmwebsourcing.petals.services.eip.marker";

	private EipChain eipChain;
	private IFile editedFile;
	private EditDomain editingDomain;

	private GraphicalViewer graphicalViewer;
	private SelectionSynchronizer selectionSynchronizer;
	private ActionRegistry actionRegistry;

	private final List<String> selectionActions = new ArrayList<String> ();
	private final List<String> stackActions = new ArrayList<String> ();


	/**
	 * Constructor.
	 */
	public EipChainDiagramEditor() {
		super();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.core.runtime.IAdaptable
	 * #getAdapter(java.lang.Class)
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	public Object getAdapter( Class type ) {

		if( type.equals( EipChain.class ))
			return this.eipChain;

		if( type.equals( EipChainDiagramEditor.class ))
			return this;

		if( type == IPropertySheetPage.class )
			return new TabbedPropertySheetPage( this );

		if( type == GraphicalViewer.class )
			return this.graphicalViewer;

		if( type == IContentOutlinePage.class )
			return new EipDiagramOutlinePage( this );

		if( type == CommandStack.class )
			return this.editingDomain.getCommandStack();

		if( type == EditPart.class && this.graphicalViewer != null )
			return this.graphicalViewer.getRootEditPart();

		if( type == IFigure.class && this.graphicalViewer != null )
			return ((GraphicalEditPart) this.graphicalViewer.getRootEditPart()).getFigure();

		if( type == ZoomManager.class )
			return getZoomManager();

		if( type == ActionRegistry.class )
			return this.actionRegistry;

		return null;
	}


	/**
	 * @return the eipChain
	 */
	public EipChain getEipChain() {
		return this.eipChain;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave( IProgressMonitor monitor ) {

		try {
			EipDesignerSerializer.INSTANCE.write( this.editedFile.getLocation().toFile(), this.eipChain );
			this.editedFile.refreshLocal( IResource.DEPTH_ZERO, monitor );
			this.editingDomain.getCommandStack().markSaveLocation();
			firePropertyChange( PROP_DIRTY );

		} catch( IOException e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
			MessageDialog.openError( getSite().getShell(), "Save Error", "The file could not be saved. Check the logs for more details." );

		} catch( CoreException e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
			MessageDialog.openError( getSite().getShell(), "Save Error", "The file could not be saved. Check the logs for more details." );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #doSaveAs()
	 */
	@Override
	public void doSaveAs() {

		SaveAsDialog dlg = new SaveAsDialog( getSite().getShell());
		dlg.setTitle( "Save as..." );
		dlg.setOriginalFile( this.editedFile );

		if( dlg.open() == Window.OK) {
			try {
				this.editedFile = ResourcesPlugin.getWorkspace().getRoot().getFile( dlg.getResult());
				if( ! this.editedFile.exists()) {
					new ContainerGenerator( this.editedFile.getParent().getFullPath()).generateContainer( null );
					this.editedFile.create( new ByteArrayInputStream( new byte[ 0 ]), true, null );
				}

				EipDesignerSerializer.INSTANCE.write( this.editedFile.getLocation().toFile(), this.eipChain );
				this.editedFile.refreshLocal( IResource.DEPTH_ZERO, null );
				setInput( new FileEditorInput( this.editedFile ));
				setPartName( this.editedFile.getName());

				this.editingDomain.getCommandStack().markSaveLocation();
				firePropertyChange( PROP_DIRTY );
				ResourceUtils.selectResourceInPetalsExplorer( true, this.editedFile );

			} catch( CoreException e ) {
				PetalsEipPlugin.log( e, IStatus.ERROR );
				MessageDialog.openError( getSite().getShell(), "Save Error", "The file could not be saved. Check the logs for more details." );

			} catch( IOException e ) {
				PetalsEipPlugin.log( e, IStatus.ERROR );
				MessageDialog.openError( getSite().getShell(), "Save Error", "The file could not be saved. Check the logs for more details." );
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart
	 * #init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init( IEditorSite site, IEditorInput editorInput )
	throws PartInitException {

		try {
			// Editor properties
			setSite( site );
			setInputWithNotify( editorInput );
			setPartName( editorInput.getName());

			// Get the editor input
			if( getEditorInput() instanceof IFileEditorInput )
				this.editedFile = ((IFileEditorInput) getEditorInput()).getFile();
			else
				throw new PartInitException( "Unsupported input" );

			// Refresh it if needed
			if( ! this.editedFile.isSynchronized( IResource.DEPTH_ZERO )) {
				try {
					this.editedFile.refreshLocal( IResource.DEPTH_ZERO, null );

				} catch( CoreException e ) {
					PetalsEipPlugin.log( e, IStatus.WARNING );
				}
			}

			// Load the model
			this.eipChain = EipDesignerSerializer.INSTANCE.read( this.editedFile.getLocation().toFile());
			this.editingDomain = new DefaultEditDomain( this );
			this.selectionSynchronizer = new SelectionSynchronizer();

			// Start listening
			ResourcesPlugin.getWorkspace().addResourceChangeListener( this );
			this.editingDomain.getCommandStack().addCommandStackListener( this );
			getSite().getWorkbenchWindow().getSelectionService().addSelectionListener( this );

		} catch( IOException e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
			throw new PartInitException( "Invalid file content" );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart
	 * #isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return this.editingDomain != null && this.editingDomain.getCommandStack().isDirty();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor
	 * #getContributorId()
	 */
	public String getContributorId() {
		return getSite().getId();
	}


	/**
	 * @return the editingDomain
	 */
	public EditDomain getEditingDomain() {
		return this.editingDomain;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.CommandStackListener
	 * #commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged( EventObject event ) {
		updateActions( this.stackActions );
		firePropertyChange( PROP_DIRTY );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		this.graphicalViewer.getControl().setFocus();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener
	 * #resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged( final IResourceChangeEvent event ) {

		// Closed project => close the editor
		if( event.getType() == IResourceChangeEvent.PRE_CLOSE ){
			Display.getDefault().asyncExec( new Runnable() {

				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for( IWorkbenchPage page : pages ) {

						if( EipChainDiagramEditor.this.editedFile.equals( event.getResource())) {
							IEditorPart editorPart = page.findEditor( getEditorInput());
							page.closeEditor( editorPart, true );
						}
					}
				}
			});
		}

		// Otherwise, any modification => validation
		else if( event.getType() == IResourceChangeEvent.POST_CHANGE ) {
			try {
				event.getDelta().accept( new IResourceDeltaVisitor() {
					public boolean visit( IResourceDelta delta ) throws CoreException {

						// Only validate the file if it changed
						if( EipChainDiagramEditor.this.editedFile.equals( delta.getResource())
									&& delta.getKind() == IResourceDelta.CHANGED ) {

							// Do not react to marker changes
							if(( delta.getFlags() & IResourceDelta.MARKERS ) == 0 )
								validate();
						}

						return delta.getResource() instanceof IContainer;
					}
				});

			} catch( CoreException e ) {
				PetalsEipPlugin.log( e, IStatus.ERROR );
			}
		}
	}


	/**
	 * @return the graphicalViewer
	 */
	public GraphicalViewer getGraphicalViewer() {
		return this.graphicalViewer;
	}


	/**
	 * @return the selectionSynchronizer
	 */
	public SelectionSynchronizer getSelectionSynchronizer() {
		return this.selectionSynchronizer;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.part.WorkbenchPart
	 * #createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );

		// Create this composite
		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout( layout );
		container.setBackground( container.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		// Create the palette
		PaletteViewerProvider paletteProvider = new PaletteViewerProvider( this.editingDomain ) {
			@Override
			public PaletteViewer createPaletteViewer( Composite parent ) {

				final PaletteViewer result = new EipPaletteViewer();
				result.createControl( parent );
				configurePaletteViewer( result );
				hookPaletteViewer( result );

				return result;
			}
		};

		FlyoutPaletteComposite paletteComposite = new FlyoutPaletteComposite(
					container,
					SWT.NONE,
					getSite().getPage(),
					paletteProvider,
					new EipPalettePreferences());
		paletteComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));
		createPaletteRoot();

		// Create the viewer
		this.graphicalViewer = new ScrollingGraphicalViewer();
		this.graphicalViewer.setEditPartFactory( new EipEditPartFactory());

		List<String> zoomLevels = new ArrayList<String>( 3 );
		zoomLevels.add( ZoomManager.FIT_ALL );
		zoomLevels.add( ZoomManager.FIT_WIDTH );
		zoomLevels.add( ZoomManager.FIT_HEIGHT );

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
		root.getZoomManager().setZoomLevelContributions( zoomLevels );
		this.graphicalViewer.setRootEditPart( root );
		this.editingDomain.addViewer( this.graphicalViewer );

		Control viewerControl = this.graphicalViewer.createControl( paletteComposite );
		paletteComposite.setGraphicalControl( viewerControl );

		// Handle selections
		this.selectionSynchronizer.addViewer( this.graphicalViewer );
		getSite().setSelectionProvider( this.graphicalViewer );

		// Show a grid on the viewer
		this.graphicalViewer.setProperty( SnapToGrid.PROPERTY_GRID_ENABLED, true );
		this.graphicalViewer.setProperty( SnapToGrid.PROPERTY_GRID_VISIBLE, false );
		this.graphicalViewer.setProperty( SnapToGrid.PROPERTY_GRID_SPACING, new Dimension( 110, 110 ));

		// Handle mouse scroll for ZOOM
		this.graphicalViewer.setProperty(
					MouseWheelHandler.KeyGenerator.getKey( SWT.MOD1 ),
					MouseWheelZoomHandler.SINGLETON );

		// Support for DnD - from the palette
		this.graphicalViewer.addDropTargetListener( new TemplateTransferDropTargetListener( this.graphicalViewer ) {
			@Override
			protected CreationFactory getFactory( Object template ) {

				if( template instanceof EIPtype )
					return new EipNodeCreationFactory((EIPtype) template);
				else if( template instanceof Class<?> )
					return new EndpointCreationFactory();

				return null;
			}
		});

		// Support for DnD - from the Petals services view
		this.graphicalViewer.addDropTargetListener( new PetalsEndpointDropTargetListener( this.graphicalViewer ));

		// Initialize the viewer
		this.graphicalViewer.setContents( this.eipChain );

		// Add a contextual menu
		final MenuManager menuMgr = new MenuManager( "com.ebmwebsourcing.petals.services.eip.contextmenu" );
		this.graphicalViewer.setContextMenu( menuMgr );
		menuMgr.addMenuListener( new IMenuListener() {

			public void menuAboutToShow( IMenuManager manager ) {

				manager.removeAll();
				Action action = new SwitchNodesAction( EipChainDiagramEditor.this );
				if( action.isEnabled()) {
					manager.add( action );
				}

				action = new ConnectionToAction( EipChainDiagramEditor.this );
				if( action.isEnabled()) {
					manager.add( action );
				}

				menuMgr.add( new ShowPropertiesAction());
				manager.add( new Separator());
				menuMgr.add( new ArrangeAllAction( EipChainDiagramEditor.this ));
				menuMgr.add( new ExportDiagramAction(
							EipChainDiagramEditor.this.graphicalViewer,
							EipChainDiagramEditor.this.eipChain ));
			}
		});

		// Create the actions
		createActions();

		// Refresh markers
		validate();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart
	 * #dispose()
	 */
	@Override
	public void dispose() {

		// Stop listening
		ResourcesPlugin.getWorkspace().removeResourceChangeListener( this );
		this.editingDomain.getCommandStack().removeCommandStackListener( this );
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener( this );

		// Delete the action registry
		this.actionRegistry.dispose();

		super.dispose();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.ISelectionListener
	 * #selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged( IWorkbenchPart part, ISelection selection ) {

		if( this.equals( getSite().getPage().getActiveEditor()))
			updateActions( this.selectionActions );
	}


	/**
	 * Validates the model and updates the markers on the file.
	 */
	private void validate() {

		// Validate the entire chain
		final EipChainTransactionalValidator val = new EipChainTransactionalValidator();
		val.validateAll( this.eipChain );

		// Create a workspace job to avoid modification conflicts on the resource tree
		Job job = new WorkspaceJob( "Updating EIP markers" ) {

			@Override
			public IStatus runInWorkspace( IProgressMonitor monitor )
			throws CoreException {

				IStatus result = Status.OK_STATUS;
				try {
					// Remove old markers
					EipChainDiagramEditor.this.editedFile.deleteMarkers( EIP_MARKER_ID, false, IResource.DEPTH_ZERO );

					// Add markers
					for( List<String> errors : val.getNodeToErrorMessage().values()) {
						for( String msg : errors ) {
							IMarker marker = EipChainDiagramEditor.this.editedFile.createMarker( EIP_MARKER_ID );
							marker.setAttribute( IMarker.PRIORITY, IMarker.PRIORITY_NORMAL );
							marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_ERROR );
							marker.setAttribute( IMarker.MESSAGE, msg );
						}
					}

					for( List<String> errors : val.getConnectionToErrorMessage().values()) {
						for( String msg : errors ) {
							IMarker marker = EipChainDiagramEditor.this.editedFile.createMarker( EIP_MARKER_ID );
							marker.setAttribute( IMarker.PRIORITY, IMarker.PRIORITY_NORMAL );
							marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_ERROR );
							marker.setAttribute( IMarker.MESSAGE, msg );
						}
					}
					
					for( List<String> warnings : val.getConnectionToWarningMessage().values()) {
						for( String msg : warnings ) {
							IMarker marker = EipChainDiagramEditor.this.editedFile.createMarker( EIP_MARKER_ID );
							marker.setAttribute( IMarker.PRIORITY, IMarker.PRIORITY_NORMAL );
							marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_WARNING );
							marker.setAttribute( IMarker.MESSAGE, msg );
						}
					}

				} catch( CoreException e ) {
					PetalsEipPlugin.log( e, IStatus.ERROR );
					result = new Status( IStatus.ERROR, PetalsEipPlugin.PLUGIN_ID, "Error while refreshing EIP markers.", e );
				}

				return result;
			}
		};

		// And schedule it
		job.schedule();
	}


	/**
	 * Creates the palette root and adds it to the editing domain.
	 */
	private void createPaletteRoot() {

		PaletteRoot root = new PaletteRoot();
		PaletteGroup mainGroup = new PaletteGroup( "Basic Features" );
		root.add( mainGroup );

		// Basic features
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		mainGroup.add( selectionToolEntry );
		mainGroup.add( new MarqueeToolEntry ());
		mainGroup.add( new ZoomInToolEntry());
		mainGroup.add( new ZoomOutToolEntry());
		root.add( new PaletteSeparator());

		// Connections and end-point tools...
		PaletteGroup edptGroup = new PaletteGroup( "Petals" );
		root.add( edptGroup );
		edptGroup.add( new ConnectionCreationToolEntry( "Connection", "Create a connection between two nodes",
					new ConnectionCreationFactory(),
					null,
					PetalsEipPlugin.getImageDescriptor( "icons/obj32/e_arrow.png" )));

		edptGroup.add( new CombinedTemplateCreationEntry(
					"Petals service", "Invoke a Petals service in the chain",
					Endpoint.class,
					new EndpointCreationFactory(),
					null,
					PetalsEipPlugin.getImageDescriptor( "icons/obj32/d_endpoint_32x32.png" )));

		// EIP tools
		root.add( new PaletteSeparator());
		PaletteGroup eipGroup = new PaletteGroup( "Patterns" );
		root.add( eipGroup );

		for( EIPtype type : EIPtype.values()) {
			String name = type.getPrettyName();
			eipGroup.add( new CombinedTemplateCreationEntry(
						name, "Create a new " + name + " pattern",
						type,
						new EipNodeCreationFactory( type ),
						null,
						EipDesignerImageStore.INSTANCE.getDefaultEipImageDescriptor( type )));
		}

		// Details
		root.add( new PaletteSeparator());
		root.setDefaultEntry( selectionToolEntry );
		this.editingDomain.setPaletteRoot( root );
	}


	/**
	 * Creates actions for this editor. Subclasses should override this method
	 * to create and register actions with the {@link ActionRegistry}.
	 */
	private void createActions() {

		this.actionRegistry = new ActionRegistry();
		IAction action;


		// Stack actions - related to commands
		action = new UndoAction( this );
		this.actionRegistry.registerAction( action );
		this.stackActions.add( action.getId());

		action = new RedoAction( this );
		this.actionRegistry.registerAction( action );
		this.stackActions.add( action.getId());


		// Selection actions - reacting to selection changes
		action = new DeleteAction((IWorkbenchPart)  this );
		this.actionRegistry.registerAction( action );
		this.selectionActions.add( action.getId());

		action = new CopyNodeAction( this );
		this.actionRegistry.registerAction( action );
		this.selectionActions.add( action.getId());

		action = new PasteNodeAction( this );
		this.actionRegistry.registerAction(action);
		this.selectionActions.add( action.getId());


		// Other actions
		action = new SelectAllAction( this );
		this.actionRegistry.registerAction( action );

		action = new SaveAction( this );
		this.actionRegistry.registerAction( action );

		this.actionRegistry.registerAction( new PrintAction( this ));
		this.actionRegistry.registerAction( new ZoomInAction( getZoomManager()));
		this.actionRegistry.registerAction( new ZoomOutAction( getZoomManager()));
	}


	/**
	 * Updates a collection of actions.
	 * @param actionIds a list of action IDs
	 */
	private void updateActions( List<String> actionIds ) {

		for( String id : actionIds ) {
			IAction action = this.actionRegistry.getAction( id );
			if( action instanceof UpdateAction )
				((UpdateAction) action).update();
		}
	}


	/**
	 * @return the zoom manager
	 */
	private ZoomManager getZoomManager() {
		return ((ScalableFreeformRootEditPart) this.graphicalViewer.getRootEditPart()).getZoomManager();
	}
}
