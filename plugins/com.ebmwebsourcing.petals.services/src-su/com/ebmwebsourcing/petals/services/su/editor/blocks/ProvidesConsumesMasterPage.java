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

package com.ebmwebsourcing.petals.services.su.editor.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathConstants;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NodeListContentProvider;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.editor.IServiceMasterPage;
import com.ebmwebsourcing.petals.services.su.editor.pages.SuDetailsPageProvider;
import com.ebmwebsourcing.petals.services.su.utils.DomEditorHelper;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProvidesConsumesMasterPage
extends MasterDetailsBlock
implements IServiceMasterPage {

	private final Map<Element,List<IMarker>> elementToMarkers = new HashMap<Element,List<IMarker>> ();
	private final AbstractServicesFormPage suFormPage;
	private final boolean displayEveryButton;
	private final SuDetailsPageProvider detailsPageProvider;

	private TableViewer providesViewer, consumesViewer;
	private PCStyledLabelProvider providesLabeler, consumesLabeler;
	private DetailsPart detailsPart;
	private IManagedForm managedForm;



	/**
	 * Constructor.
	 * @param suFormPage
	 * @param displayEveryButton
	 * @param detailsPageProvider
	 */
	public ProvidesConsumesMasterPage(
				AbstractServicesFormPage suFormPage,
				boolean displayEveryButton,
				SuDetailsPageProvider detailsPageProvider ) {

		this.suFormPage = suFormPage;
		this.displayEveryButton = displayEveryButton;
		this.detailsPageProvider = detailsPageProvider;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #dispose()
	 */
	public void dispose() {

		if( this.providesLabeler != null )
			this.providesLabeler.dispose();

		if( this.consumesLabeler != null )
			this.consumesLabeler.dispose();
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


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #update()
	 */
	public void update() {

		updateConsumesViewerInput();
		this.consumesViewer.refresh( true );

		updateProvidesViewerInput();
		this.providesViewer.refresh( true );

		if( this.detailsPart != null
					&& this.detailsPart.getCurrentPage() != null ) {
			this.detailsPart.getCurrentPage().refresh();
		}
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


		// PROVIDES
		// Containing section
		Section section = toolkit.createSection( bigContainer,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Provides" );
		section.setDescription( "Edit the general properties of provides blocks." );

		Composite container = toolkit.createComposite( section );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// Add the list
		Table table = toolkit.createTable( container, SWT.BORDER | SWT.SINGLE );
		this.providesViewer = new TableViewer( table );
		this.providesViewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.providesViewer.setContentProvider( new NodeListContentProvider ());

		// Add the label provider (with style)
		this.providesLabeler = new PCStyledLabelProvider( table );
		this.providesViewer.setLabelProvider( new DelegatingStyledCellLabelProvider( this.providesLabeler ));

		// Add the buttons
		Composite buttonsComposite = toolkit.createComposite( container );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		SectionPart sectionPart = new SectionPart( section );
		managedForm.addPart( sectionPart );
		addProvidesButtons( sectionPart, buttonsComposite );


		// CONSUMES
		// Containing section
		section = toolkit.createSection( bigContainer,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Consumes" );
		section.setDescription( "Edit the general properties of consumes blocks." );

		container = toolkit.createComposite( section );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// Add the list
		table = toolkit.createTable( container, SWT.BORDER | SWT.SINGLE );
		this.consumesViewer = new TableViewer( table );
		this.consumesViewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.consumesViewer.setContentProvider( new NodeListContentProvider ());

		// Add the label provider (with style)
		this.consumesLabeler = new PCStyledLabelProvider( table );
		this.consumesViewer.setLabelProvider( new DelegatingStyledCellLabelProvider( this.consumesLabeler ));

		// Add the buttons
		buttonsComposite = toolkit.createComposite( container );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));
		addConsumesButtons( sectionPart, buttonsComposite );

		update();
	}


	/**
	 * Creates the buttons for the provides viewer.
	 * @param buttonsComposite
	 */
	private void addProvidesButtons( final SectionPart sectionPart, Composite buttonsComposite ) {

		FormToolkit toolkit = sectionPart.getManagedForm().getToolkit();
		final List<Button> selectionSensitiveButtons = new ArrayList<Button> ();

		// Add and remove are not displayed on every page
		if( this.displayEveryButton ) {

			// Buttons
			Button addProvidesButton = toolkit.createButton( buttonsComposite, "New &Provides", SWT.PUSH );
			addProvidesButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

			Button removeButton = toolkit.createButton( buttonsComposite, "&Remove", SWT.PUSH );
			removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			selectionSensitiveButtons.add( removeButton );

			// Listeners
			addProvidesButton.addSelectionListener( new SelectionListener() {
				public void widgetSelected( SelectionEvent e ) {
					addProvidesOrConsumes( true );
				}

				public void widgetDefaultSelected( SelectionEvent e ) {
					addProvidesOrConsumes( true );
				}
			});

			Listener removeListener = new Listener() {
				public void handleEvent( Event event ) {

					// Keyboard event? Check the key...
					if( event.type == SWT.KeyDown
								&& event.character != SWT.DEL )
						return;

					// Remove the selection
					ISelection s = ProvidesConsumesMasterPage.this.providesViewer.getSelection();
					if( s.isEmpty() || ! ( s instanceof IStructuredSelection ))
						return;

					Object o = ((IStructuredSelection) ProvidesConsumesMasterPage.this.providesViewer.getSelection()).getFirstElement();
					if( o instanceof Element ) {

						ProvidesConsumesMasterPage.this.suFormPage.startTransaction();
						DomUtils.removeElement((Element) o);
						ProvidesConsumesMasterPage.this.suFormPage.stopTransaction();

						updateProvidesViewerInput();
						ProvidesConsumesMasterPage.this.providesViewer.refresh();
					}
				}
			};

			removeButton.addListener( SWT.Selection, removeListener );
			this.providesViewer.getTable().addListener( SWT.KeyDown, removeListener );
		}

		// Up and Down
		Button upButton = toolkit.createButton( buttonsComposite, "&Up", SWT.PUSH );
		GridData upButtonLayoutData = new GridData( GridData.FILL_HORIZONTAL );
		selectionSensitiveButtons.add( upButton );
		upButtonLayoutData.verticalIndent = 20;
		upButton.setLayoutData( upButtonLayoutData );

		Button downButton = toolkit.createButton( buttonsComposite, "&Down", SWT.PUSH );
		downButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		selectionSensitiveButtons.add( downButton );


		// Add the listeners
		upButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( true, true );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( true, true );
			}
		});

		downButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( false, true );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( false, true );
			}
		});


		// React to selection changes and focus events
		for( Button button : selectionSensitiveButtons )
			button.setEnabled( false );

		this.providesViewer.getTable().addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				boolean enableButtons = ! ProvidesConsumesMasterPage.this.providesViewer.getSelection().isEmpty();
				for( Button button : selectionSensitiveButtons )
					button.setEnabled( enableButtons );
			}

			@Override
			public void focusLost( FocusEvent e ) {
				if( ProvidesConsumesMasterPage.this.providesViewer.getSelection().isEmpty()) {
					for( Button button : selectionSensitiveButtons )
						button.setEnabled( false );
				}
			}
		});

		this.providesViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				ProvidesConsumesMasterPage.this.suFormPage.getSite().getSelectionProvider().setSelection( event.getSelection());
				sectionPart.getManagedForm().fireSelectionChanged(
							sectionPart,
							event.getSelection());

				boolean enableButtons = ! ProvidesConsumesMasterPage.this.providesViewer.getSelection().isEmpty();
				for( Button button : selectionSensitiveButtons )
					button.setEnabled( enableButtons );
			}
		});
	}


	/**
	 * Creates the buttons for the consumes viewer.
	 * @param buttonsComposite
	 */
	private void addConsumesButtons( final SectionPart sectionPart, Composite buttonsComposite ) {

		FormToolkit toolkit = sectionPart.getManagedForm().getToolkit();
		final List<Button> selectionSensitiveButtons = new ArrayList<Button> ();

		// Add and remove are not displayed on every page
		if( this.displayEveryButton ) {

			// Buttons
			Button addConsumesButton = toolkit.createButton( buttonsComposite, "New &Consumes", SWT.PUSH );
			addConsumesButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

			Button removeButton = toolkit.createButton( buttonsComposite, "&Remove", SWT.PUSH );
			removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			selectionSensitiveButtons.add( removeButton );

			// Listeners
			addConsumesButton.addSelectionListener( new SelectionListener() {
				public void widgetSelected( SelectionEvent e ) {
					addProvidesOrConsumes( false );
				}

				public void widgetDefaultSelected( SelectionEvent e ) {
					addProvidesOrConsumes( false );
				}
			});

			Listener removeListener = new Listener() {
				public void handleEvent( Event event ) {

					// Keyboard event? Check the key...
					if( event.type == SWT.KeyDown
								&& event.character != SWT.DEL )
						return;

					// Remove the selection
					ISelection s = ProvidesConsumesMasterPage.this.consumesViewer.getSelection();
					if( s.isEmpty() || ! ( s instanceof IStructuredSelection ))
						return;

					Object o = ((IStructuredSelection) ProvidesConsumesMasterPage.this.consumesViewer.getSelection()).getFirstElement();
					if( o instanceof Element ) {

						ProvidesConsumesMasterPage.this.suFormPage.startTransaction();
						DomUtils.removeElement((Element) o);
						ProvidesConsumesMasterPage.this.suFormPage.stopTransaction();

						updateConsumesViewerInput();
						ProvidesConsumesMasterPage.this.consumesViewer.refresh();
					}
				}
			};

			removeButton.addListener( SWT.Selection, removeListener );
			this.consumesViewer.getTable().addListener( SWT.KeyDown, removeListener );
		}

		// Up and Down
		Button upButton = toolkit.createButton( buttonsComposite, "&Up", SWT.PUSH );
		GridData upButtonLayoutData = new GridData( GridData.FILL_HORIZONTAL );
		selectionSensitiveButtons.add( upButton );
		upButtonLayoutData.verticalIndent = 20;
		upButton.setLayoutData( upButtonLayoutData );

		Button downButton = toolkit.createButton( buttonsComposite, "&Down", SWT.PUSH );
		downButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		selectionSensitiveButtons.add( downButton );


		// Add the listeners
		upButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( true, false );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( true, false );
			}
		});

		downButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelection( false, false );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelection( false, false );
			}
		});


		// React to selection changes and focus events
		for( Button button : selectionSensitiveButtons )
			button.setEnabled( false );

		this.consumesViewer.getTable().addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				boolean enableButtons = ! ProvidesConsumesMasterPage.this.consumesViewer.getSelection().isEmpty();
				for( Button button : selectionSensitiveButtons )
					button.setEnabled( enableButtons );
			}

			@Override
			public void focusLost( FocusEvent e ) {
				if( ProvidesConsumesMasterPage.this.consumesViewer.getSelection().isEmpty()) {
					for( Button button : selectionSensitiveButtons )
						button.setEnabled( false );
				}
			}
		});

		this.consumesViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				ProvidesConsumesMasterPage.this.suFormPage.getSite().getSelectionProvider().setSelection( event.getSelection());
				sectionPart.getManagedForm().fireSelectionChanged(
							sectionPart,
							event.getSelection());

				boolean enableButtons = ! ProvidesConsumesMasterPage.this.consumesViewer.getSelection().isEmpty();
				for( Button button : selectionSensitiveButtons )
					button.setEnabled( enableButtons );
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
		detailsPart.setPageProvider( this.detailsPageProvider );

		// Initialize the selection, when possible
		if( this.providesViewer.getTable().getItemCount() > 0 ) {
			Object o = this.providesViewer.getTable().getItem( 0 ).getData();
			if( o != null )
				this.providesViewer.setSelection( new StructuredSelection( o ));
		}
		else if( this.consumesViewer.getTable().getItemCount() > 0 ) {
			Object o = this.consumesViewer.getTable().getItem( 0 ).getData();
			if( o != null )
				this.consumesViewer.setSelection( new StructuredSelection( o ));
		}
	}


	/**
	 * @param provides
	 */
	private void addProvidesOrConsumes( boolean provides ) {

		String expr = "/*[local-name()='jbi']/*[local-name()='services']";
		Node servicesNode = (Node) XPathUtils.evaluateXPathExpression(
					expr, this.suFormPage.getDocument(), XPathConstants.NODE );

		String elementName = provides ? "provides" : "consumes";
		if( servicesNode == null || servicesNode.getNodeType() != Node.ELEMENT_NODE )
			return;

		this.suFormPage.startTransaction();
		Element servicesElt = (Element) servicesNode;
		int insertionIndex = getLastOnePosition( servicesElt, provides ) + 1;
		Element newElt = StructuredModelHelper.getChildElementToInsert(
					servicesElt,
					elementName,
					insertionIndex ).getElement();

		if( newElt != null ) {
			DomUtils.addOrSetAttribute( newElt, "interface-name", "itfNs:itfName" );
			DomUtils.addOrSetAttribute( newElt, "service-name", "srvNs:srvName" );
			DomUtils.addOrSetAttribute( newElt, "xmlns:srvNs", "http://" );
			DomUtils.addOrSetAttribute( newElt, "xmlns:itfNs", "http://" );
			DomUtils.insertChildElement( servicesElt, newElt, insertionIndex );

			// FIXME: generating empty mark-ups automatically is not possible
			// due to restrictions on group inclusion in the choices (the first one is used)

			// Add an empty WSDL value
			if( provides )
				DomEditorHelper.setWsdl( newElt, null );
			this.suFormPage.stopTransaction();

			// Update the viewers
			if( provides )
				updateProvidesViewerInput();
			else
				updateConsumesViewerInput();

			TableViewer viewer = provides ? this.providesViewer : this.consumesViewer;
			viewer.refresh();
			viewer.setSelection( new StructuredSelection( newElt ));
			viewer.getTable().setFocus();
		}
	}


	/**
	 * @param up
	 * @param provides
	 */
	private void moveSelection( boolean up, boolean provides ) {

		TableViewer viewer = provides ? this.providesViewer : this.consumesViewer;
		ISelection s = viewer.getSelection();
		if( s.isEmpty() || ! ( s instanceof IStructuredSelection ))
			return;

		Object o = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
		if( o instanceof Element ) {
			Element selection = (Element) o;

			// A provides cannot be moved down if the element below is a consumes
			if( provides && ! up ) {
				Element eltAfter = DomUtils.getElement( selection, false );
				if( eltAfter == null || ! "provides".equals( DomUtils.getNodeName( eltAfter )))
					return;
			}

			// A consumes cannot be moved up if the element before is a provides
			else if( ! provides && up ) {
				Element eltBefore = DomUtils.getElement( selection, true );
				if( eltBefore == null || ! "consumes".equals( DomUtils.getNodeName( eltBefore )))
					return;
			}

			DomUtils.moveElementOrder( selection, up );
			if( provides )
				updateProvidesViewerInput();
			else
				updateConsumesViewerInput();

			viewer.setSelection( new StructuredSelection( selection ));
			viewer.refresh();
		}
	}


	/**
	 * @param providesViewer
	 */
	private void updateProvidesViewerInput() {

		String expr = "/*[local-name()='jbi']/*[local-name()='services']/*[local-name()='provides']";
		NodeList pc = (NodeList) XPathUtils.evaluateXPathExpression( expr, this.suFormPage.getDocument(), XPathConstants.NODESET );
		this.providesViewer.setInput( pc );
	}


	/**
	 * @param providesViewer
	 */
	private void updateConsumesViewerInput() {

		String expr = "/*[local-name()='jbi']/*[local-name()='services']/*[local-name()='consumes']";
		NodeList pc = (NodeList) XPathUtils.evaluateXPathExpression( expr, this.suFormPage.getDocument(), XPathConstants.NODESET );
		this.consumesViewer.setInput( pc );
	}


	/**
	 * Gets the position of the last provides or consumes among the children of 'services'.
	 * @param servicesElement
	 * @param provides
	 * @return
	 */
	private int getLastOnePosition( Element servicesElement, boolean provides ) {

		// We assume provides and consumes are in the right order:
		// all the provides first, all the consumes next, anything then
		NodeList children = servicesElement.getChildNodes();
		int lastOnePosition = -1;
		for( int i=0; i<children.getLength(); i++ ) {
			Node child = children.item( i );
			if( child.getNodeType() != Node.ELEMENT_NODE )
				continue;

			if( provides ) {
				if( "provides".equals( DomUtils.getNodeName( child )))
					lastOnePosition  = i;
				else
					break;
			}
			else {
				String name = DomUtils.getNodeName( child );
				if( "provides".equals( name ) || "consumes".equals( name ))
					lastOnePosition  = i;
				else
					break;
			}
		}

		return lastOnePosition;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #updateMarkers(java.util.Map)
	 */
	public void updateMarkers( Map<IMarker,Node> markerToNode ) {

		// Build a new map associating provides / consumes and markers
		this.elementToMarkers.clear();
		for( Map.Entry<IMarker,Node> entry : markerToNode.entrySet()) {

			// Prepare the decoration of the viewers
			Node n = entry.getValue();
			Element elt = getProvidesOrConsumes( n );
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
		this.providesLabeler.setElementToMarkers( this.elementToMarkers );
		this.consumesLabeler.setElementToMarkers( this.elementToMarkers );

		if( ! this.providesViewer.getTable().isDisposed())
			this.providesViewer.refresh();
		if( ! this.consumesViewer.getTable().isDisposed())
			this.consumesViewer.refresh();

		// Update the details pages
		for( Map.Entry<Element,List<IMarker>> entry : this.elementToMarkers.entrySet()) {
			GeneralAbstractDetails page = this.detailsPageProvider.getCreatedPage( entry.getKey());
			if( page != null )
				page.setPagesMarkers( entry.getValue());
		}

		try {
			if( this.detailsPart != null
						&& this.detailsPart.getCurrentPage() != null )
				this.detailsPart.getCurrentPage().refresh();
			else
				this.managedForm.getMessageManager().update();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Gets the provides or the consumes element associated with this node.
	 * @param node a node (attribute or element) being a child of a provides / consumes
	 * @return the provides or the consumes element, or null if it was not found
	 */
	private Element getProvidesOrConsumes( Node node ) {

		Node n;
		if( node instanceof Attr )
			n = ((Attr) node).getOwnerElement();
		else
			n = node;

		while( n != null ) {
			String name = DomUtils.getNodeName( n );
			if( "provides".equals( name ) || "consumes".equals( name ))
				return (Element) n;

			n = n.getParentNode();
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #getMarkersForDetails(org.w3c.dom.Element)
	 */
	public List<IMarker> getMarkersForDetails( Element elt ) {
		return this.elementToMarkers.get( elt );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #gotoMarker(org.w3c.dom.Node, java.lang.String)
	 */
	public boolean gotoMarker( Node node, String xPathLocation ) {

		boolean result = false;
		Element elt = getProvidesOrConsumes( node );

		if( elt != null ) {
			result = true;

			// Reveal the element in the viewer
			String name = DomUtils.getNodeName( elt );
			if( "provides".equals( name ))
				this.providesViewer.setSelection( new StructuredSelection( elt ), true );
			else
				this.consumesViewer.setSelection( new StructuredSelection( elt ), true );

			// Reveal the element in the details
			GeneralAbstractDetails details = this.detailsPageProvider.getCreatedPage( elt );
			if( details != null )
				details.gotoMarker( xPathLocation );
		}

		return result;
	}
}
