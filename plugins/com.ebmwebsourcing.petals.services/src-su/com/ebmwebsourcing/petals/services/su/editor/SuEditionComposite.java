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

package com.ebmwebsourcing.petals.services.su.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsImages;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.EMFPCStyledLabelProvider;
import com.ebmwebsourcing.petals.services.su.editor.wizards.AddConsumesToExistingJbiStrategy;
import com.ebmwebsourcing.petals.services.su.editor.wizards.AddProvidesToExistingJbiStrategy;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.wizards.NewServiceUnitSelectionWizard;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * The composite to display in the JBI form editor for service-units.
 * @author Micka�l Istria - EBM WebSourcing
 */
public class SuEditionComposite extends SashForm implements ISharedEdition {

	private final ISharedEdition ise;
	private AbstractEndpoint selectedEndpoint;
	private EList<? extends AbstractEndpoint> containmentList;

	private JbiEditorDetailsContribution componentContributions;
	private Composite mainDetails;
	private Composite advancedDetails;
	private TableViewer providesViewer;
	private TableViewer consumesViewer;
	private final LabelProvider labelProvider;



	/**
	 * Constructor.
	 * @param parent
	 */
	public SuEditionComposite( Composite parent, ISharedEdition ise ) {
		super( parent, SWT.NONE );
		this.ise = ise;
		this.labelProvider = new EMFPCStyledLabelProvider( this );

		setLayoutData ( new GridData( GridData.FILL_BOTH ));
		getFormToolkit().adapt( this );
		getFormToolkit().paintBordersFor( this );

		initModel();
		createLeftWidgets();
		createRightWidgets();
		setWeights( new int[]{ 1, 1 });
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

		super.dispose();
	}


	/**
	 * Initializes the model.
	 */
	private void initModel() {

		CompoundCommand initializeCommand = new CompoundCommand();
		for( ComponentVersionDescription description : ExtensionManager.INSTANCE.findAllComponentVersionDescriptions()) {
			EPackage extensionPackage = EPackageRegistryImpl.INSTANCE.getEPackage( description.getNamespace());
			InitializeModelExtensionCommand command = null;

			for( Provides provide : this.ise.getJbiModel().getServices().getProvides()) {
				command = new InitializeModelExtensionCommand( extensionPackage, provide );
				if( command.prepare())
					initializeCommand.append( command );
			}

			for( Consumes consume : this.ise.getJbiModel().getServices().getConsumes()) {
				command = new InitializeModelExtensionCommand( extensionPackage, consume );
				if( command.prepare())
					initializeCommand.append( command );
			}
		}

		getEditingDomain().getCommandStack().execute( initializeCommand );
		((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
	}


	/**
	 * Initializes the widgets on the left side.
	 */
	protected void createLeftWidgets() {

		Composite servicesComposite = getFormToolkit().createComposite( this );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		servicesComposite.setLayout( layout );

		// Provides
		Form providesForm = getFormToolkit().createForm( servicesComposite );
		providesForm.setLayoutData( new GridData( GridData.FILL_BOTH ));
		providesForm.setText( Messages.provides );
		layout = new GridLayout(2, false );
		layout.marginHeight = 0;
		providesForm.getBody().setLayout( layout );

		this.providesViewer = new TableViewer( providesForm.getBody());
		this.providesViewer.getControl().setLayoutData( new GridData(GridData.FILL_BOTH));
		this.providesViewer.setLabelProvider( this.labelProvider );
		this.providesViewer.setContentProvider( new ArrayContentProvider());

		Composite providesButtons = getFormToolkit().createComposite( providesForm.getBody());
		layout = new GridLayout();
		layout.marginHeight = 0;
		providesButtons.setLayout( layout );
		providesButtons.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));

		Button newProvidesButton = getFormToolkit().createButton(providesButtons, "New...", SWT.NONE);
		newProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, true, 1, 1 ));
		newProvidesButton.setImage( PetalsImages.getAdd());
		newProvidesButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				IWizard createEndpointWizard = new NewServiceUnitSelectionWizard(
						PetalsMode.provides,
						new AddProvidesToExistingJbiStrategy( getJbiModel(), getEditingDomain(), getEditedFile().getProject()));

				if( new WizardDialog(getShell(), createEndpointWizard ).open() == Dialog.OK )
					SuEditionComposite.this.providesViewer.refresh();
			}
		});

		final Button removeProvidesButton = getFormToolkit().createButton( providesButtons, "Remove", SWT.NONE );
		removeProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		removeProvidesButton.setImage( PetalsImages.getDelete());
		removeProvidesButton.addSelectionListener( new EListRemoveSelectionListener( this.providesViewer ));

		final Button upProvidesButton = getFormToolkit().createButton(providesButtons, "", SWT.NONE);
		upProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		upProvidesButton.setText( "&Up" );
		upProvidesButton.addSelectionListener( new EListUpSelectionListener());

		final Button downProvidesButton = getFormToolkit().createButton(providesButtons, "", SWT.NONE);
		downProvidesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		downProvidesButton.setText( "&Down" );
		downProvidesButton.addSelectionListener( new EListDownSelectionListener());

		getDataBindingContext().bindValue(
				ViewersObservables.observeInput( this.providesViewer ),
				EMFEditObservables.observeValue( getEditingDomain(), getJbiModel().getServices(), JbiPackage.Literals.SERVICES__PROVIDES ));


		// Consumes
		Form consumesForm = getFormToolkit().createForm( servicesComposite );
		consumesForm.setLayoutData( new GridData( GridData.FILL_BOTH ));
		consumesForm.setText( Messages.consumes );
		layout = new GridLayout(2, false );
		layout.marginHeight = 0;
		consumesForm.getBody().setLayout( layout );

		this.consumesViewer = new TableViewer( consumesForm.getBody());
		this.consumesViewer.getControl().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.consumesViewer.setLabelProvider( this.labelProvider );
		this.consumesViewer.setContentProvider( new ArrayContentProvider());

		Composite consumesButtons = getFormToolkit().createComposite( consumesForm.getBody());
		layout = new GridLayout();
		layout.marginHeight = 0;
		consumesButtons.setLayout( layout );
		consumesButtons.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));

		Button newConsumesButton = getFormToolkit().createButton( consumesButtons, "New...", SWT.NONE );
		newConsumesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		newConsumesButton.setImage( PetalsImages.getAdd());
		newConsumesButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				IWizard createEndpointWizard = new NewServiceUnitSelectionWizard(
						PetalsMode.consumes,
						new AddConsumesToExistingJbiStrategy( getJbiModel(), getEditingDomain(), getEditedFile().getProject()));

				if( new WizardDialog( getShell(), createEndpointWizard ).open() == Dialog.OK )
					SuEditionComposite.this.consumesViewer.refresh();
			}
		});

		final Button removeConsumesButton = getFormToolkit().createButton( consumesButtons, "Remove", SWT.NONE );
		removeConsumesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		removeConsumesButton.setImage( PetalsImages.getDelete());
		removeConsumesButton.addSelectionListener( new EListRemoveSelectionListener(this.consumesViewer));

		final Button upConsumesButton = getFormToolkit().createButton( consumesButtons, "", SWT.NONE );
		upConsumesButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		upConsumesButton.setText( "&Up" );
		upConsumesButton.addSelectionListener( new EListUpSelectionListener());

		final Button downConsumesButton = getFormToolkit().createButton( consumesButtons, "", SWT.NONE );
		downConsumesButton.setLayoutData( new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1 ));
		downConsumesButton.setText( "&Down" );
		downConsumesButton.addSelectionListener( new EListDownSelectionListener());

		getDataBindingContext().bindValue(
				ViewersObservables.observeInput( this.consumesViewer ),
				EMFEditObservables.observeValue( getEditingDomain(), getJbiModel().getServices(), JbiPackage.Literals.SERVICES__CONSUMES ));



		this.providesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = ((IStructuredSelection)event.getSelection());
				if (selection.isEmpty()) {
					SuEditionComposite.this.selectedEndpoint = null;
					upProvidesButton.setEnabled(false);
					downProvidesButton.setEnabled(false);
				} else {
					SuEditionComposite.this.consumesViewer.setSelection(new StructuredSelection());
					SuEditionComposite.this.selectedEndpoint = (Provides)selection.getFirstElement();
					SuEditionComposite.this.containmentList = getJbiModel().getServices().getProvides();
					upProvidesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) > 0);
					downProvidesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) != SuEditionComposite.this.containmentList.size() - 1);
					refreshDetails();
				}
				removeProvidesButton.setEnabled(SuEditionComposite.this.selectedEndpoint != null);
			}
		});


		this.consumesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = ((IStructuredSelection)event.getSelection());
				if (selection.isEmpty()) {
					SuEditionComposite.this.selectedEndpoint = null;
					upConsumesButton.setEnabled(false);
					downConsumesButton.setEnabled(false);
				} else {
					SuEditionComposite.this.providesViewer.setSelection(new StructuredSelection());
					SuEditionComposite.this.selectedEndpoint = (Consumes)selection.getFirstElement();
					SuEditionComposite.this.containmentList = getJbiModel().getServices().getConsumes();
					upConsumesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) > 0);
					downConsumesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) != SuEditionComposite.this.containmentList.size() - 1);
					refreshDetails();
				}
				removeConsumesButton.setEnabled(SuEditionComposite.this.selectedEndpoint != null);
			}
		});


		this.providesViewer.setSelection(new StructuredSelection());
		this.consumesViewer.setSelection(new StructuredSelection());


	}

	/**
	 * Initializes the widgets on the right side.
	 */
	protected void createRightWidgets() {

		Composite container = getFormToolkit().createComposite( this );
		GridLayout layout = new GridLayout();
		layout.marginTop = 10;
		layout.marginHeight = 0;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final CTabFolder tabFolder = new CTabFolder( container, SWT.BOTTOM | SWT.BORDER );
		getFormToolkit().adapt( tabFolder );
		getFormToolkit().paintBordersFor( tabFolder );
		tabFolder.setSimple( true );
		tabFolder.setSelectionBackground( Display.getCurrent().getSystemColor( SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT ));
		tabFolder.setLayoutData( new GridData( GridData.FILL_BOTH ));

		CTabItem tbtmGeneral = new CTabItem( tabFolder, SWT.NONE );
		tbtmGeneral.setText( "Main" );

		this.mainDetails = getFormToolkit().createComposite( tabFolder, SWT.NONE );
		tbtmGeneral.setControl( this.mainDetails );
		getFormToolkit().paintBordersFor( this.mainDetails );
		this.mainDetails.setLayout( new GridLayout( 2, false ));

		CTabItem tbtmAdvanced = new CTabItem( tabFolder, SWT.NONE );
		tbtmAdvanced.setText( "Advanced" );
		final ScrolledComposite advancedScrollContainer = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
		advancedScrollContainer.setExpandHorizontal(true);
		advancedScrollContainer.setExpandVertical(true);
		tbtmAdvanced.setControl( advancedScrollContainer );
		advancedScrollContainer.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		
		this.advancedDetails = getFormToolkit().createComposite( advancedScrollContainer, SWT.NONE );
		advancedScrollContainer.setContent(advancedDetails);
		getFormToolkit().paintBordersFor( this.advancedDetails );
		this.advancedDetails.setLayout( new GridLayout( 2, false ));
		advancedScrollContainer.addControlListener(new ControlListener() {
			@Override
			public void controlResized(ControlEvent e) {
				Point contentSize = advancedDetails.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				advancedScrollContainer.setMinSize(contentSize);
			}
			
			@Override
			public void controlMoved(ControlEvent e) {
			}
		});

		CTabItem tbtmSource = new CTabItem( tabFolder, SWT.NONE );
		tbtmSource.setText( "Source" );
		tabFolder.setSelection( tbtmGeneral );

	}

	private void re_fillMainDetailsContainer(FormToolkit toolkit, Composite generalDetails) {
		for (Control control : generalDetails.getChildren()) {
			control.dispose();
		}

		if (this.componentContributions != null) {
			this.componentContributions.addMainSUContent(this.selectedEndpoint, toolkit, generalDetails, this );
		}

		generalDetails.layout(true);
	}

	private void re_fillAdvancedDetailsContainer(FormToolkit toolkit, Composite advancedDetails) {
		for (Control control : advancedDetails.getChildren()) {
			control.dispose();
		}

		if (this.componentContributions != null) {
			this.componentContributions.addAdvancedSUContent(this.selectedEndpoint, toolkit, advancedDetails, this );
		}

		advancedDetails.layout(true);
	}

	private void refreshDetails() {
		if (this.selectedEndpoint != null) {
			ComponentVersionDescription componentDesc = ExtensionManager.INSTANCE.findComponentDescription(this.selectedEndpoint);
			if (componentDesc != null) {
				EditorContributionSupport support = componentDesc.createNewExtensionSupport();
				if (support != null) {
					this.componentContributions = support.createJbiEditorContribution(this.selectedEndpoint);
				}
		    }
		} else {
			this.componentContributions = null;
		}

		re_fillMainDetailsContainer( this.ise.getFormToolkit(), this.mainDetails);
		re_fillAdvancedDetailsContainer( this.ise.getFormToolkit(), this.advancedDetails);

	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.ISharedEdition
	 * #getEditingDomain()
	 */
	@Override
	public EditingDomain getEditingDomain() {
		return this.ise.getEditingDomain();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.ISharedEdition
	 * #getEditedFile()
	 */
	@Override
	public IFile getEditedFile() {
		return this.ise.getEditedFile();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.ISharedEdition
	 * #getDataBindingContext()
	 */
	@Override
	public DataBindingContext getDataBindingContext() {
		return this.ise.getDataBindingContext();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.ISharedEdition
	 * #getJbiModel()
	 */
	@Override
	public Jbi getJbiModel() {
		return this.ise.getJbiModel();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.ISharedEdition
	 * #getFormToolkit()
	 */
	@Override
	public FormToolkit getFormToolkit() {
		return this.ise.getFormToolkit();
	}


	/**
	 * A selection to remove an element from a viewer.
	 * @author Micka�l Istria - EBM WebSourcing
	 */
	private final class EListRemoveSelectionListener extends DefaultSelectionListener {
		private final Viewer servicesViewer;

		/**
		 * Constructor.
		 * @param servicesViewer
		 */
		private EListRemoveSelectionListener( Viewer servicesViewer ) {
			this.servicesViewer = servicesViewer;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionAdapter
		 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected( SelectionEvent e ) {
			if( MessageDialog.openConfirm( getShell(), Messages.confimeRemoveEndpointTitle, Messages.confimeRemoveEndpointMessage )) {
				RemoveCommand deleteCommand = new RemoveCommand(
						getEditingDomain(),
						SuEditionComposite.this.containmentList,
						SuEditionComposite.this.selectedEndpoint);

				getEditingDomain().getCommandStack().execute( deleteCommand );
				this.servicesViewer.refresh();
			}
		}
	}


	/**
	 * A selection to move an element downward in a viewer.
	 * @author Micka�l Istria - EBM WebSourcing
	 */
	private final class EListDownSelectionListener extends DefaultSelectionListener {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionAdapter
		 * #widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected( SelectionEvent e ) {
			MoveCommand moveCommand = new MoveCommand(
					getEditingDomain(),
					SuEditionComposite.this.containmentList,
					SuEditionComposite.this.selectedEndpoint,
					SuEditionComposite.this.containmentList.indexOf( SuEditionComposite.this.selectedEndpoint ) + 1 );
			getEditingDomain().getCommandStack().execute( moveCommand );
		}
	}


	/**
	 * A selection to move an element upward in a viewer.
	 * @author Micka�l Istria - EBM WebSourcing
	 */
	private final class EListUpSelectionListener extends DefaultSelectionListener {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionListener
		 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected( SelectionEvent e ) {
			MoveCommand moveCommand = new MoveCommand(
					getEditingDomain(),
					SuEditionComposite.this.containmentList,
					SuEditionComposite.this.selectedEndpoint,
					SuEditionComposite.this.containmentList.indexOf( SuEditionComposite.this.selectedEndpoint ) - 1 );
			getEditingDomain().getCommandStack().execute( moveCommand );
		}
	}
}
