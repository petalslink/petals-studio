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

package com.ebmwebsourcing.petals.jbi.editor.form.su;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.MoveCommand;
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
import org.eclipse.swt.layout.FillLayout;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.jbi.editor.form.IServiceMasterPage;
import com.ebmwebsourcing.petals.jbi.editor.form.Messages;
import com.ebmwebsourcing.petals.jbi.editor.form.common.emf.EMFFeatureContentProvider;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Services;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProvidesConsumesMasterPage extends MasterDetailsBlock implements IServiceMasterPage {

	private final AbstractServicesFormPage suFormPage;
	private final boolean displayEveryButton;
	private final SuDetailsPageProvider detailsPageProvider;

	private TableViewer providesViewer, consumesViewer;
	private EMFPCStyledLabelProvider providesLabeler, consumesLabeler;
	private DetailsPart detailsPart;
	private Services services;



	/**
	 * Constructor.
	 * @param suFormPage
	 * @param displayEveryButton
	 * @param detailsPageProvider
	 * @param services 
	 */
	public ProvidesConsumesMasterPage(
				AbstractServicesFormPage suFormPage,
				boolean displayEveryButton,
				SuDetailsPageProvider detailsPageProvider,
				Services services ) {

		this.suFormPage = suFormPage;
		this.displayEveryButton = displayEveryButton;
		this.detailsPageProvider = detailsPageProvider;
		this.services = services;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServiceMasterPage
	 * #dispose()
	 */
	public void dispose() {

		if( this.providesLabeler != null ) {
			this.providesLabeler.dispose();
		}

		if( this.consumesLabeler != null ) {
			this.consumesLabeler.dispose();
		}
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
		this.consumesViewer.setInput(services);
		this.providesViewer.setInput(services);

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

		FormToolkit toolkit = managedForm.getToolkit();
		Section servicesSection = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR
				| ExpandableComposite.EXPANDED);
		servicesSection.setLayout(new FillLayout());
		servicesSection.setText(Messages.services);
		Composite bigContainer = toolkit.createComposite( servicesSection );
		GridLayout tLayout = new GridLayout();
		tLayout.marginHeight = 0;
		tLayout.marginWidth = 0;
		tLayout.marginRight = 5;
		tLayout.verticalSpacing = 20;
		bigContainer.setLayout( tLayout );
		bigContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		servicesSection.setClient(bigContainer);
		
		// PROVIDES
		// Containing section
		Section section = toolkit.createSection( bigContainer,
					Section.DESCRIPTION
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
		this.providesViewer.setContentProvider( new EMFFeatureContentProvider(JbiPackage.Literals.SERVICES__PROVIDES));

		// Add the label provider (with style)
		this.providesLabeler = new EMFPCStyledLabelProvider( table );
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
		this.consumesViewer.setContentProvider( new EMFFeatureContentProvider(JbiPackage.Literals.SERVICES__CONSUMES));

		// Add the label provider (with style)
		this.consumesLabeler = new EMFPCStyledLabelProvider( table );
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
								&& event.character != SWT.DEL ) {
						return;
					}

					// Remove the selection
					ISelection s = ProvidesConsumesMasterPage.this.providesViewer.getSelection();
					if( s.isEmpty() || ! ( s instanceof IStructuredSelection )) {
						return;
					}

					Object o = ((IStructuredSelection) ProvidesConsumesMasterPage.this.providesViewer.getSelection()).getFirstElement();
					List<Object> toDelete = new ArrayList<Object>();
					toDelete.add(o);
					DeleteCommand deleteCommand = new DeleteCommand(suFormPage.getEditDomain(), toDelete);
					suFormPage.getEditDomain().getCommandStack().execute(deleteCommand);
					ProvidesConsumesMasterPage.this.providesViewer.refresh();
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
				for( Button button : selectionSensitiveButtons ) {
					button.setEnabled( enableButtons );
				}
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
					if( o instanceof EObject ) {
						List<EObject> toRemove = new ArrayList<EObject>();
						toRemove.add((EObject)o);
						DeleteCommand command = new DeleteCommand(suFormPage.getEditDomain(), toRemove);
						suFormPage.getEditDomain().getCommandStack().execute(command);
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
		AddCommand command = null;
		AbstractEndpoint newElement = null;
		if (provides) {
			newElement = JbiFactory.eINSTANCE.createProvides();
			command = new AddCommand(suFormPage.getEditDomain(), services.getProvides(), newElement);
		} else {
			newElement = JbiFactory.eINSTANCE.createConsumes();
			command = new AddCommand(suFormPage.getEditDomain(), services.getConsumes(), newElement);
		}
		newElement.setEndpointName("endpoint");
		newElement.setInterfaceName(new QName("interface"));
		newElement.setServiceName(new QName("service"));
		suFormPage.getEditDomain().getCommandStack().execute(command);
		
		TableViewer viewer = provides ? this.providesViewer : this.consumesViewer;
		viewer.refresh();
		viewer.setSelection( new StructuredSelection( newElement ));
		viewer.getTable().setFocus();
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
		if( o instanceof EObject ) {
			EObject item = (EObject)o;
			EList<? extends EObject> targetList = null;
			int targetIndex = 0;
			if (provides) {
				targetList = services.getProvides();
			} else {
				targetList = services.getConsumes();
			}
			
			if (up) {
				targetIndex = targetList.indexOf(item) - 1;
			} else {
				targetIndex = targetList.indexOf(item) + 1;
			}
			if (targetIndex < 0) {
				targetIndex = 0;
			}
			if (targetIndex >= targetList.size()) {
				targetIndex = targetList.size() - 1;
			}
			
			MoveCommand command = new MoveCommand(suFormPage.getEditDomain(), targetList, item, targetIndex);
			suFormPage.getEditDomain().getCommandStack().execute(command);
			/*if( provides )
				updateProvidesViewerInput();
			else
				updateConsumesViewerInput();

			viewer.setSelection( new StructuredSelection( new I ));*/
			viewer.refresh();
		}
	}


	public void updateMarkers(Map<IMarker, Node> markerToNode) {
		// TODO Auto-generated method stub
	}


	public boolean gotoMarker(Node node, String xPathLocation) {
		// TODO Auto-generated method stub
		return false;
	}


	public List<IMarker> getMarkersForDetails(Element element) {
		// TODO Auto-generated method stub
		return null;
	}



}
