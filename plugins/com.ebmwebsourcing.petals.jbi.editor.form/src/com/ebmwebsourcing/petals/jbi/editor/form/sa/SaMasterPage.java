/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.jbi.editor.form.sa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.jbi.editor.form.IServiceMasterPage;
import com.ebmwebsourcing.petals.jbi.editor.form.ServicesLabelProvider;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.ServiceUnit;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaMasterPage
extends MasterDetailsBlock
implements IServiceMasterPage {

	private final Map<Element,List<IMarker>> elementToMarkers = new HashMap<Element,List<IMarker>> ();
	final AbstractServicesFormPage saFormPage;
	private final SaDetailsPageProvider pageProvider;

	private TreeViewer viewer;
	private ServicesLabelProvider labelProvider;
	private DetailsPart detailsPart;
	private IManagedForm managedForm;


	/**
	 * Constructor.
	 * @param saFormPage
	 */
	public SaMasterPage( SaPage saFormPage ) {

		this.saFormPage = saFormPage;
		this.pageProvider = new SaDetailsPageProvider( this.saFormPage );
	}


	/**
	 * Disposes the resources.
	 */
	public void dispose() {

		if( this.labelProvider != null )
			this.labelProvider.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.MasterDetailsBlock
	 * #applyLayout(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void applyLayout( Composite parent ) {

		GridLayout layout = new GridLayout();
		layout.marginWidth = 7;
		layout.marginHeight = 15;
		parent.setLayout( layout );
	}


	/**
	 * Updates the viewer.
	 */
	public void update() {
		updateViewerInput();
		this.viewer.refresh( true );
		this.viewer.expandAll();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.MasterDetailsBlock
	 * #createMasterPart(org.eclipse.ui.forms.IManagedForm, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createMasterPart( final IManagedForm managedForm, Composite parent ) {

		this.managedForm = managedForm;

		FormToolkit toolkit = managedForm.getToolkit();
		Composite bigContainer = toolkit.createComposite( parent );
		GridLayout tLayout = new GridLayout();
		tLayout.marginHeight = 0;
		tLayout.marginWidth = 0;
		tLayout.marginRight = 5;
		tLayout.verticalSpacing = 20;
		bigContainer.setLayout( tLayout );
		bigContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Containing section
		Section section = toolkit.createSection( bigContainer,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Service Assembly" );
		section.setDescription( "Edit the properties of this service assembly." );

		Composite container = toolkit.createComposite( section );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// Add the tree
		Tree tree = toolkit.createTree( container, SWT.BORDER | SWT.MULTI );
		this.viewer = new TreeViewer( tree );
		this.viewer.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Add the content provider
		this.viewer.setContentProvider( new SATreeContentProvider(this));


		// Add the label provider
		this.labelProvider = new ServicesLabelProvider();
		this.viewer.setLabelProvider( this.labelProvider );

		// Add the buttons
		Composite buttonsComposite = toolkit.createComposite( container );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		SectionPart sectionPart = new SectionPart( section );
		managedForm.addPart( sectionPart );
		addSuButtons( sectionPart, buttonsComposite );

		update();
		this.viewer.getTree().setFocus();
	}


	/**
	 * Creates the buttons for the provides viewer.
	 * @param buttonsComposite
	 */
	private void addSuButtons( final SectionPart sectionPart, Composite buttonsComposite ) {

		FormToolkit toolkit = sectionPart.getManagedForm().getToolkit();
		final List<Button> selectionSensitiveButtons = new ArrayList<Button> ();

		// Buttons
		Button addProvidesButton = toolkit.createButton( buttonsComposite, "Add...", SWT.PUSH );
		addProvidesButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button removeButton = toolkit.createButton( buttonsComposite, "&Remove", SWT.PUSH );
		removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		selectionSensitiveButtons.add( removeButton );

		// Listeners
		addProvidesButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				addServiceUnit();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				addServiceUnit();
			}
		});

		Listener removeListener = new Listener() {
			public void handleEvent( Event event ) {

				// Keyboard event? Check the key...
				if( event.type == SWT.KeyDown
							&& event.character != SWT.DEL )
					return;

				// Remove the selection
				ISelection s = SaMasterPage.this.viewer.getSelection();
				if( s.isEmpty() || ! ( s instanceof IStructuredSelection ))
					return;

				Iterator<?> it = ((IStructuredSelection) SaMasterPage.this.viewer.getSelection()).iterator();
				List<EObject> toRemove = new ArrayList<EObject>();
				while( it.hasNext()) {
					Object o = it.next();
					if( o instanceof EObject) {
						toRemove.add((EObject)o);
					}
				}
				DeleteCommand deleteCommand = new DeleteCommand(saFormPage.getEditDomain(), toRemove);
				saFormPage.getEditDomain().getCommandStack().execute(deleteCommand);
				updateViewerInput();
				SaMasterPage.this.viewer.refresh();
				SaMasterPage.this.viewer.expandAll();
			}
		};

		removeButton.addListener( SWT.Selection, removeListener );
		this.viewer.getTree().addListener( SWT.KeyDown, removeListener );

		// Up and Down
		final Button upButton = toolkit.createButton( buttonsComposite, "&Up", SWT.PUSH );
		GridData upButtonLayoutData = new GridData( GridData.FILL_HORIZONTAL );
		selectionSensitiveButtons.add( upButton );
		upButtonLayoutData.verticalIndent = 20;
		upButton.setLayoutData( upButtonLayoutData );

		final Button downButton = toolkit.createButton( buttonsComposite, "&Down", SWT.PUSH );
		downButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		selectionSensitiveButtons.add( downButton );


		// Add the listeners
		upButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( true );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( true );
			}
		});

		downButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( false );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( false );
			}
		});


		// React to selection changes and focus events
		for( Button button : selectionSensitiveButtons )
			button.setEnabled( false );

		this.viewer.getTree().addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				boolean enableButtons = ! SaMasterPage.this.viewer.getSelection().isEmpty();
				for( Button button : selectionSensitiveButtons )
					button.setEnabled( enableButtons );
			}

			@Override
			public void focusLost( FocusEvent e ) {
				if( SaMasterPage.this.viewer.getSelection().isEmpty()) {
					for( Button button : selectionSensitiveButtons )
						button.setEnabled( false );
				}
			}
		});

		this.viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				SaMasterPage.this.saFormPage.getSite().getSelectionProvider().setSelection( event.getSelection());
				sectionPart.getManagedForm().fireSelectionChanged(
							sectionPart,
							event.getSelection());

				int size = ((IStructuredSelection) SaMasterPage.this.viewer.getSelection()).size();
				boolean isSa = false;
				if( size == 1 ) {
					Object o = ((IStructuredSelection) SaMasterPage.this.viewer.getSelection()).getFirstElement();
					isSa = o instanceof Element
					&& "service-assembly".equalsIgnoreCase( DomUtils.getNodeName((Element) o));
				}

				for( Button button : selectionSensitiveButtons ) {
					if( button == downButton || button == upButton )
						button.setEnabled( size == 1 && ! isSa );
					else
						button.setEnabled( size != 0 && ! isSa );
				}
			}
		});
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.MasterDetailsBlock
	 * #createToolBarActions(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createToolBarActions( IManagedForm managedForm ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.MasterDetailsBlock
	 * #registerPages(org.eclipse.ui.forms.DetailsPart)
	 */
	@Override
	protected void registerPages( DetailsPart detailsPart ) {

		this.detailsPart = detailsPart;
		detailsPart.setPageProvider( this.pageProvider );
	}


	/**
	 * @param provides
	 */
	private void addServiceUnit() {

		List<IProject> suProjects = ServiceProjectRelationUtils.getAllSuProjects();
		ListDialog dlg = new ListDialog( new Shell());
		dlg.setAddCancelButton( true );
		dlg.setContentProvider( new ArrayContentProvider());
		dlg.setLabelProvider( new WorkbenchLabelProvider());
		dlg.setInput( suProjects );
		dlg.setTitle( "Service Unit Selection" );
		dlg.setMessage( "Select a Service Unit project." );

		if( dlg.open() == Window.OK ) {
			IProject selectedProject = (IProject) dlg.getResult()[ 0 ];
			String expr = "/*[local-name()='jbi']/*[local-name()='service-assembly']";
			ServiceAssembly sa = saFormPage.getJbi().getServiceAssembly();

			ServiceUnit newElt = JbiFactory.eINSTANCE.createServiceUnit();
			
			// TODO populate service unit
//				Element identificationElement = DomUtils.getChildElement( newElt, "identification" );
//				Element editedElement = DomUtils.getChildElement( identificationElement, "name" );
//				if( editedElement != null )
//					StructuredModelHelper.setElementSimpleValue( editedElement, selectedProject.getName());
//
//				editedElement = DomUtils.getChildElement( identificationElement, "description" );
//				if( editedElement != null )
//					StructuredModelHelper.setElementSimpleValue( editedElement, "" );
//
//				Element targetElement = DomUtils.getChildElement( newElt, "target" );
//				editedElement = DomUtils.getChildElement( targetElement, "artifacts-zip" );
//				if( editedElement != null )
//					StructuredModelHelper.setElementSimpleValue( editedElement, selectedProject.getName() + ".zip" );
//
//				Properties properties = PetalsSPPropertiesManager.getProperties( selectedProject );
//				String componentName = properties.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, "" );
//				properties = null;
//
//				editedElement = DomUtils.getChildElement( targetElement, "component-name" );
//				if( editedElement != null )
//					StructuredModelHelper.setElementSimpleValue( editedElement, componentName );

				AddCommand addCommand = new AddCommand(saFormPage.getEditDomain(), sa.getServiceUnit(), newElt);
				saFormPage.getEditDomain().getCommandStack().execute(addCommand);

				// Update the viewers
				updateViewerInput();
				this.viewer.refresh();
				this.viewer.expandAll();
				this.viewer.setSelection( new StructuredSelection( newElt ));
				this.viewer.getTree().setFocus();
		}
	}


	/**
	 * @param up
	 * @param provides
	 */
	private void moveSelection( boolean up ) {

		ISelection s = this.viewer.getSelection();
		if( s.isEmpty() || ! ( s instanceof IStructuredSelection ))
			return;

		Object o = ((IStructuredSelection) this.viewer.getSelection()).getFirstElement();
		if( o instanceof Element ) {
			Element selection = (Element) o;
			// TODO implement it with EMF
			// What is allowed?
			if( up ) {
				Element beforeElt = DomUtils.getElement( selection, true );
				if( beforeElt == null || ! "service-unit".equals( DomUtils.getNodeName( beforeElt )))
					return;
			}
			else {
				Element afterElt = DomUtils.getElement( selection, false );
				if( afterElt == null )
					return;
			}

			DomUtils.moveElementOrder( selection, up );
			updateViewerInput();

			this.viewer.setSelection( new StructuredSelection( selection ));
			this.viewer.refresh();
		}
	}


	/**
	 * @param providesViewer
	 */
	private void updateViewerInput() {
		this.viewer.setInput( this.saFormPage.getJbi().getServiceAssembly());
	}


	/**
	 * Updates the markers in the viewers.
	 * @param markerToNode
	 */
	public void updateMarkers( Map<IMarker,Node> markerToNode ) {

		// Build a new map associating provides / consumes and markers
		this.elementToMarkers.clear();
		for( Map.Entry<IMarker,Node> entry : markerToNode.entrySet()) {

			// Prepare the decoration of the viewers
			Node n = entry.getValue();
			Element elt = getSuElement( n );
			if( elt != null ) {

				List<IMarker> markers = this.elementToMarkers.get( elt );
				if( markers == null )
					markers = new ArrayList<IMarker> ();

				markers.add( entry.getKey());
				this.elementToMarkers.put( elt, markers );
			}

			// Update the header
			String msg = entry.getKey().getAttribute( IMarker.MESSAGE, "" );
			String xpath = entry.getKey().getAttribute( PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE, "" );
			int severity = entry.getKey().getAttribute( IMarker.SEVERITY, -1 );
			int type = MarkerUtils.getMessageSeverityFromMarkerSeverity( severity );

			this.managedForm.getMessageManager().addMessage( xpath, msg, entry.getKey(), type );
		}

		// Update the viewers
		this.labelProvider.setElementToMarkers( this.elementToMarkers );
		if( ! this.viewer.getTree().isDisposed())
			this.viewer.refresh();

		// Update the details pages
		for( Map.Entry<Element,List<IMarker>> entry : this.elementToMarkers.entrySet()) {
			SaDetailsPage page = this.pageProvider.getCreatedPage( entry.getKey());
			if( page != null )
				page.setPagesMarkers( entry.getValue());
		}

		if( this.detailsPart != null
					&& this.detailsPart.getCurrentPage() != null )
			this.detailsPart.getCurrentPage().refresh();
		else
			this.managedForm.getMessageManager().update();
	}


	/**
	 * Gets the markers for the details page associated with this element.
	 * @param elt
	 * @return a list of markers (possibly null)
	 */
	public List<IMarker> getMarkersForDetails( Element elt ) {
		return this.elementToMarkers.get( elt );
	}


	/**
	 * Shows and selects the part which is wrong and associated with this marker.
	 * @param node (not null)
	 * @param xPathLocation
	 * @return true if a matching widget could be found, false otherwise
	 */
	public boolean gotoMarker( Node node, String xPathLocation ) {

		boolean result = false;
		Element elt = getSuElement( node );

		if( elt != null ) {
			result = true;

			// Reveal the element in the viewer
			this.viewer.setSelection( new StructuredSelection( elt ), true );

			// Reveal the element in the details
			SaDetailsPage details = this.pageProvider.getCreatedPage( elt );
			if( details != null )
				details.gotoMarker( xPathLocation );
		}

		return result;
	}


	/**
	 * Gets the service-unit element associated with this node.
	 * @param node a node (attribute or element) being a child of service-unit
	 * @return the service-unit element, or null if it was not found
	 */
	private Element getSuElement( Node node ) {

		Node n;
		if( node instanceof Attr )
			n = ((Attr) node).getOwnerElement();
		else
			n = node;

		while( n != null ) {
			String name = DomUtils.getNodeName( n );
			if( "service-unit".equals( name ))
				return (Element) n;

			n = n.getParentNode();
		}

		return null;
	}
}
