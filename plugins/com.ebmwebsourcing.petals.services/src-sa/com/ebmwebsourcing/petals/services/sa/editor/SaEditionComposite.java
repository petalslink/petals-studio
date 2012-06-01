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

package com.ebmwebsourcing.petals.services.sa.editor;

import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultTreeContentProvider;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsImages;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.editor.ServicesLabelProvider;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;
import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.ServiceUnit;
import com.sun.java.xml.ns.jbi.Target;

/**
 * The composite to display in the JBI form editor for service assemblies.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaEditionComposite extends SashForm {

	private final ISharedEdition ise;
	private EObject viewerSelection;

	private final Color blueFont;
	private TreeViewer viewer;
	private LabelProvider labelProvider;
	private ScrolledPageBook pageBook;
	private Text saNameText, saDescText, suNameText, suDescText, suArtifactsText, suComponentText;



	/**
	 * Constructor.
	 * @param parent
	 */
	public SaEditionComposite( Composite parent, ISharedEdition ise ) {
		super( parent, SWT.NONE );
		this.ise = ise;
		this.blueFont = getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );

		setLayoutData ( new GridData( GridData.FILL_BOTH ));
		ise.getFormToolkit().adapt( this );
		ise.getFormToolkit().paintBordersFor( this );

		createLeftWidgets();
		createRightWidgets();
		setWeights( new int[]{ 1, 1 });

		this.viewer.getTree().setFocus();
		this.viewer.setSelection( new StructuredSelection( ise.getJbiModel().getServiceAssembly()));
		this.viewer.getTree().notifyListeners( SWT.Selection, new Event());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget
	 * #dispose()
	 */
	@Override
	public void dispose() {
		if( this.labelProvider != null )
			this.labelProvider.dispose();

		if( this.blueFont != null && ! this.blueFont.isDisposed())
			this.blueFont.dispose();

		super.dispose();
	}


	/**
	 * Initializes the widgets on the left side.
	 */
	protected void createLeftWidgets() {

		FormToolkit toolkit = this.ise.getFormToolkit();
		Composite container = toolkit.createComposite( this );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginTop = 11;
		layout.marginRight = 7;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Section section = toolkit.createSection( container, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Service Assembly's Content" );
		section.setDescription( "Select the elements to edit." );

		container = toolkit.createComposite( section );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// Add the tree
		Tree tree = toolkit.createTree( container, SWT.BORDER | SWT.MULTI );
		this.viewer = new TreeViewer( tree );
		this.viewer.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Add the content provider
		this.labelProvider = new ServicesLabelProvider();
		this.viewer.setLabelProvider( this.labelProvider );
		this.viewer.setContentProvider( new DefaultTreeContentProvider() {
			@Override
			public Object[] getElements( Object inputElement ) {
				Object o = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly();
				return new Object[] { o };
			}

			@Override
			public boolean hasChildren( Object element ) {
				return getChildren( element ).length > 0;
			}

			@Override
			public Object getParent( Object element ) {
				return null;
			}

			@Override
			public Object[] getChildren( Object parentElement ) {

				Object[] result = new Object[ 0 ];
				if( parentElement instanceof ServiceAssembly )
					result = ((ServiceAssembly) parentElement).getServiceUnit().toArray();

				return result;
			}
		});

		this.viewer.setInput( new Object());
		this.viewer.expandAll();


		// Add the buttons
		Composite buttonsComposite = toolkit.createComposite( container );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));


		Button newSuButton = this.ise.getFormToolkit().createButton( buttonsComposite, "New...", SWT.PUSH );
		newSuButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));
		newSuButton.setImage( PetalsImages.INSTANCE.getAdd());
		newSuButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				// Select a SU project
				List<IProject> suProjects = ServiceProjectRelationUtils.getAllSuProjects();
				ListDialog dlg = new ListDialog( new Shell());
				dlg.setAddCancelButton( true );
				dlg.setContentProvider( new ArrayContentProvider());
				dlg.setLabelProvider( new WorkbenchLabelProvider());
				dlg.setInput( suProjects );
				dlg.setTitle( "Service Unit Selection" );
				dlg.setMessage( "Select a Service Unit project." );

				if( dlg.open() != Window.OK )
					return;

				// Create the SU element
				IProject selectedProject = (IProject) dlg.getResult()[ 0 ];

				ServiceUnit su = JbiFactory.eINSTANCE.createServiceUnit();
				Identification id = JbiFactory.eINSTANCE.createIdentification();
				id.setName( selectedProject.getName());
				id.setDescription( "" );
				su.setIdentification( id );

				Target target = JbiFactory.eINSTANCE.createTarget();
				target.setArtifactsZip( selectedProject.getName() + ".zip" );
				Properties properties = PetalsSPPropertiesManager.getProperties( selectedProject );
				String componentName = properties.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, "" );
				target.setComponentName( componentName );
				su.setTarget( target );

				EList<?> list = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly().getServiceUnit();
				AddCommand addCommand = new AddCommand( SaEditionComposite.this.ise.getEditingDomain(), list, su );
				SaEditionComposite.this.ise.getEditingDomain().getCommandStack().execute( addCommand );

				// Update the viewers
				SaEditionComposite.this.viewer.refresh();
				SaEditionComposite.this.viewer.expandAll();
				SaEditionComposite.this.viewer.setSelection( new StructuredSelection( su ));
				SaEditionComposite.this.viewer.getTree().notifyListeners( SWT.Selection, new Event());
				SaEditionComposite.this.viewer.getTree().setFocus();
			}
		});


		final Button removeProvidesButton = this.ise.getFormToolkit().createButton( buttonsComposite, "Remove", SWT.PUSH );
		removeProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));
		removeProvidesButton.setImage( PetalsImages.INSTANCE.getDelete());
		removeProvidesButton.addSelectionListener( new DefaultSelectionListener() {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionListener
			 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected( SelectionEvent e ) {
				if( MessageDialog.openConfirm( getShell(), Messages.confimeRemoveEndpointTitle, Messages.confimeRemoveEndpointMessage )) {

					ServiceAssembly sa = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly();
					Object o = ((IStructuredSelection) SaEditionComposite.this.viewer.getSelection()).getFirstElement();
					RemoveCommand deleteCommand = new RemoveCommand( SaEditionComposite.this.ise.getEditingDomain(), sa.getServiceUnit(), o );
					SaEditionComposite.this.ise.getEditingDomain().getCommandStack().execute( deleteCommand );

					SaEditionComposite.this.pageBook.removePage( o );
					SaEditionComposite.this.viewer.refresh();
					SaEditionComposite.this.viewer.expandAll();
					SaEditionComposite.this.viewer.setSelection( new StructuredSelection( sa ));
					SaEditionComposite.this.viewer.getTree().notifyListeners( SWT.Selection, new Event());
				}
			}
		});


		final Button upProvidesButton = this.ise.getFormToolkit().createButton( buttonsComposite, "", SWT.PUSH );
		upProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));
		upProvidesButton.setText( "&Up" );
		upProvidesButton.addSelectionListener( new DefaultSelectionListener() {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionListener
			 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected( SelectionEvent e ) {

				EList<?> list = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly().getServiceUnit();
				Object o = ((IStructuredSelection) SaEditionComposite.this.viewer.getSelection()).getFirstElement();
				MoveCommand moveCommand = new MoveCommand( SaEditionComposite.this.ise.getEditingDomain(), list, o, list.indexOf(o ) - 1 );
				SaEditionComposite.this.ise.getEditingDomain().getCommandStack().execute( moveCommand );
			}
		});


		final Button downProvidesButton = this.ise.getFormToolkit().createButton( buttonsComposite, "", SWT.PUSH );
		downProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));
		downProvidesButton.setText( "&Down" );
		downProvidesButton.addSelectionListener( new DefaultSelectionListener() {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionListener
			 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected( SelectionEvent e ) {

				EList<?> list = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly().getServiceUnit();
				Object o = ((IStructuredSelection) SaEditionComposite.this.viewer.getSelection()).getFirstElement();
				MoveCommand moveCommand = new MoveCommand( SaEditionComposite.this.ise.getEditingDomain(), list, o, list.indexOf(o ) + 1 );
				SaEditionComposite.this.ise.getEditingDomain().getCommandStack().execute( moveCommand );
			}
		});


		this.viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				Object o = ((IStructuredSelection) SaEditionComposite.this.viewer.getSelection()).getFirstElement();
				EList<?> sus = SaEditionComposite.this.ise.getJbiModel().getServiceAssembly().getServiceUnit();
				boolean isSa = o instanceof ServiceAssembly;
				boolean isFirst = sus.indexOf( o ) == 0;
				boolean isLast = sus.indexOf( o ) == sus.size() - 1;

				downProvidesButton.setEnabled( ! isSa && ! isLast );
				upProvidesButton.setEnabled( ! isSa && ! isFirst );
				removeProvidesButton.setEnabled( ! isSa );
			}
		});
	}


	/**
	 * Initializes the widgets on the right side.
	 */
	protected void createRightWidgets() {

		this.pageBook = new ScrolledPageBook( this );
		this.pageBook.setLayoutData( new GridData( GridData.FILL_BOTH ));

		this.viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				if( SaEditionComposite.this.viewer.getSelection().isEmpty())
					return;

				Object o = ((IStructuredSelection) SaEditionComposite.this.viewer.getSelection()).getFirstElement();
				SaEditionComposite.this.viewerSelection = (EObject) o;

				if( ! SaEditionComposite.this.pageBook.hasPage( o )) {
					Composite parent = SaEditionComposite.this.pageBook.getContainer();
					if( o instanceof ServiceAssembly )
						SaEditionComposite.this.pageBook.registerPage( o, createSaSection( parent ));
					else if( o instanceof ServiceUnit )
						SaEditionComposite.this.pageBook.registerPage( o, createSuSection( parent ));
					else
						SaEditionComposite.this.pageBook.showEmptyPage();
				}

				SaEditionComposite.this.pageBook.showPage( o );
			}
		});
	}


	/**
	 * Creates the section for the properties of a service assembly.
	 * @param parent the parent
	 * @return a new section
	 */
	private Composite createSaSection( Composite parent ) {

		// Container
		FormToolkit toolkit = this.ise.getFormToolkit();
		Composite container = toolkit.createComposite( parent );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginTop = 11;
		layout.marginLeft = 3;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Section section = toolkit.createSection( container, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Service Assembly's Properties" );
		section.setDescription( "Edit the properties of the selected service assembly." );

		container = toolkit.createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );


		// Texts
		final ServiceAssembly sa = (ServiceAssembly) this.viewerSelection;
		Label label = SwtFactory.createLabel( container, "Name:", "The name of the service assembly" );
		label.setForeground( this.blueFont );

		this.saNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.saNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.saNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				SaEditionComposite.this.viewer.refresh( sa, true );
			}
		});

		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.saNameText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), sa.getIdentification(), JbiPackage.Literals.IDENTIFICATION__NAME ));


		label = SwtFactory.createLabel( container, "Description:", "The description of the service assembly" );
		label.setForeground( this.blueFont );
		label.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		this.saDescText = toolkit.createText( container, "", SWT.MULTI | SWT.BORDER );
		GridData layoutData = new GridData( SWT.FILL, SWT.BEGINNING, true, false );
		layoutData.heightHint = 60;
		this.saDescText.setLayoutData( layoutData );
		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.saDescText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), sa.getIdentification(), JbiPackage.Literals.IDENTIFICATION__DESCRIPTION ));

		return section.getParent();
	}


	/**
	 * Creates the section for the properties of a service unit.
	 * @param parent the parent
	 * @return a new section
	 */
	private Composite createSuSection( Composite parent ) {

		// Container
		FormToolkit toolkit = this.ise.getFormToolkit();
		Composite container = toolkit.createComposite( parent );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginTop = 11;
		layout.marginLeft = 3;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Section section = toolkit.createSection( container, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Service Unit's Properties" );
		section.setDescription( "Edit the properties of the selected service unit." );

		container = toolkit.createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );


		// Texts
		final ServiceUnit su = (ServiceUnit) this.viewerSelection;
		Label label = SwtFactory.createLabel( container, "Name:", "The name of the service assembly" );
		label.setForeground( this.blueFont );

		this.suNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.suNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.suNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				SaEditionComposite.this.viewer.refresh( su, true );
			}
		});

		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.suNameText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), su.getIdentification(), JbiPackage.Literals.IDENTIFICATION__NAME ));


		label = SwtFactory.createLabel( container, "Description:", "The description of the service assembly" );
		label.setForeground( this.blueFont );
		label.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		this.suDescText = toolkit.createText( container, "", SWT.MULTI | SWT.BORDER );
		GridData layoutData = new GridData( SWT.FILL, SWT.BEGINNING, true, false );
		layoutData.heightHint = 60;
		this.suDescText.setLayoutData( layoutData );
		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.suDescText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), su.getIdentification(), JbiPackage.Literals.IDENTIFICATION__DESCRIPTION ));


		label = SwtFactory.createLabel( container, "Zip Artifact:", "The name of the *.zip artifact" );
		label.setForeground( this.blueFont );

		this.suArtifactsText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.suArtifactsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.suArtifactsText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), su.getTarget(), JbiPackage.Literals.TARGET__ARTIFACTS_ZIP ));


		label = SwtFactory.createLabel( container, "Component name:", "The name of the target component" );
		label.setForeground( this.blueFont );

		this.suComponentText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.suComponentText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( this.suComponentText ),
				EMFEditObservables.observeValue( this.ise.getEditingDomain(), su.getTarget(), JbiPackage.Literals.TARGET__COMPONENT_NAME ));

		return section.getParent();
	}
}
