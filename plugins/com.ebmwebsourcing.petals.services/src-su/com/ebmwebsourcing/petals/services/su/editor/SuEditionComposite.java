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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
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
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.OpenSourceEditorHyperlinkListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsImages;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages.DefaultJbiEditorContribution;
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
 * @author Mickael Istria - EBM WebSourcing
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuEditionComposite extends SashForm implements ISharedEdition {

	private final ISharedEdition ise;
	private AbstractEndpoint selectedEndpoint;
	private EList<? extends AbstractEndpoint> containmentList;

	private JbiEditorDetailsContribution componentContributions;
	private Composite mainDetails, advancedDetails;
	private TableViewer providesViewer, consumesViewer;
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

		// Create the widgets
		initModel();
		createLeftWidgets();
		createRightWidgets();
		setWeights( new int[]{ 1, 1 });

		// Initial selection
		AbstractEndpoint ae = null;
		TableViewer viewer = null;

		if( ! getJbiModel().getServices().getProvides().isEmpty()) {
			ae = getJbiModel().getServices().getProvides().get( 0 );
			viewer = this.providesViewer;
		} else if( ! getJbiModel().getServices().getConsumes().isEmpty()) {
			ae = getJbiModel().getServices().getConsumes().get( 0 );
			viewer = this.consumesViewer;
		}

		if( viewer != null ) {
			viewer.setSelection( new StructuredSelection( ae ));
			viewer.getTable().notifyListeners( SWT.Selection, new Event());
		}
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

		// Sort the packages: the CDK must be the first one.
		// Components must follow.
		ArrayList<EPackage> cdkPackages = new ArrayList<EPackage> ();
		ArrayList<EPackage> componentPackages = new ArrayList<EPackage> ();
		for( ComponentVersionDescription description : ExtensionManager.INSTANCE.findAllComponentVersionDescriptions()) {
			EPackage extensionPackage = EPackageRegistryImpl.INSTANCE.getEPackage( description.getNamespace());

			if( extensionPackage == null )
				PetalsServicesPlugin.log( "No package for " + description.getComponentName(), IStatus.ERROR );
			else if( extensionPackage.getNsURI() == null )
				PetalsServicesPlugin.log( "Ignoring an empty package in the SU editor.", IStatus.ERROR );
			else if( extensionPackage.getNsURI().toLowerCase().contains( "components/extensions/" ))
				cdkPackages.add( extensionPackage );
			else
				componentPackages.add( extensionPackage );
		}

		ArrayList<EPackage> sortedPackages = new ArrayList<EPackage> ();
		sortedPackages.addAll( cdkPackages );
		sortedPackages.addAll( componentPackages );


		// Create and invoke the command
		CompoundCommand initializeCommand = new CompoundCommand();
		for( EPackage extensionPackage : sortedPackages ) {
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
				IStructuredSelection selection = ((IStructuredSelection) SuEditionComposite.this.providesViewer.getSelection());
				if (selection.isEmpty()) {
					SuEditionComposite.this.selectedEndpoint = null;
					upProvidesButton.setEnabled(false);
					downProvidesButton.setEnabled(false);

				} else {
					SuEditionComposite.this.consumesViewer.setSelection(new StructuredSelection());
					SuEditionComposite.this.selectedEndpoint = (Provides) selection.getFirstElement();
					SuEditionComposite.this.containmentList = getJbiModel().getServices().getProvides();
					upProvidesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) > 0);
					downProvidesButton.setEnabled(
							SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint)
							!= SuEditionComposite.this.containmentList.size() - 1);

					refreshDetails();
				}

				removeProvidesButton.setEnabled(SuEditionComposite.this.selectedEndpoint != null);
			}
		});


		this.consumesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = ((IStructuredSelection) SuEditionComposite.this.consumesViewer.getSelection());
				if (selection.isEmpty()) {
					SuEditionComposite.this.selectedEndpoint = null;
					upConsumesButton.setEnabled(false);
					downConsumesButton.setEnabled(false);

				} else {
					SuEditionComposite.this.providesViewer.setSelection(new StructuredSelection());
					SuEditionComposite.this.selectedEndpoint = (Consumes)selection.getFirstElement();
					SuEditionComposite.this.containmentList = getJbiModel().getServices().getConsumes();
					upConsumesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(SuEditionComposite.this.selectedEndpoint) > 0);
					downConsumesButton.setEnabled(SuEditionComposite.this.containmentList.indexOf(
							SuEditionComposite.this.selectedEndpoint)
							!= SuEditionComposite.this.containmentList.size() - 1);

					refreshDetails();
				}

				removeConsumesButton.setEnabled(SuEditionComposite.this.selectedEndpoint != null);
			}
		});
	}


	/**
	 * Initializes the widgets on the right side.
	 */
	protected void createRightWidgets() {

		Composite container = getFormToolkit().createComposite( this );
		GridLayoutFactory.swtDefaults().margins( 10, 0 ).extendedMargins( 0, 0, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final ScrolledPageBook pageBook = new ScrolledPageBook( container );
		pageBook.setLayoutData( new GridData( GridData.FILL_BOTH ));
		getFormToolkit().adapt( pageBook );


		// Tabs
		Composite[] controls = new Composite[ 3 ];
		this.mainDetails = createScrollable( pageBook.getContainer(), controls, 0 );
		this.advancedDetails = createScrollable( pageBook.getContainer(), controls, 1 );

		Composite sourceContainer = getFormToolkit().createComposite( pageBook.getContainer(), SWT.NONE );
		controls[ 2 ] = sourceContainer;
		GridLayoutFactory.swtDefaults().extendedMargins( 5, 5, 7, 0 ).spacing( 0, 14 ).applyTo( sourceContainer );
		createSourceTabContent( sourceContainer );


		// Add the labels
		Composite labelContainer = getFormToolkit().createComposite( container );
		GridLayoutFactory.swtDefaults().numColumns( 3 ).margins( 5, 0 ).extendedMargins( 5, 0, 0, 0 ).spacing( 8, 0 ).applyTo( labelContainer );
		String[] labels = { "Main", "Advanced", "Source" };
		final List<Label> navigationLabels = new ArrayList<Label> ();

		Listener mouseDownListener = new Listener() {
			@Override
			public void handleEvent( Event event ) {
				pageBook.showPage( event.widget.getData());

				// Highlight the selected tab
				for( Label l : navigationLabels ) {
					Color color;
					if( l != event.widget && l.getParent() != event.widget )
						color = Display.getDefault().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND );
					else
						color = Display.getDefault().getSystemColor( SWT.COLOR_WHITE );

					l.setBackground( color );
					l.getParent().setBackground( color );
				}
			}
		};


		// Register the pages and bind it all
		int i=0;
		for( String label : labels ) {
			Label l = createTabLabel( i, label, labelContainer, mouseDownListener );
			navigationLabels.add( l );
			pageBook.registerPage( i, controls[ i ]);
			i ++;
		}


		// Force to display the first tab (and force it to be highlighted)
		navigationLabels.get( 0 ).notifyListeners( SWT.MouseDown, new Event());
	}


	/**
	 * Creates a label for the tab (wrapped in a composite for better display).
	 * @param index the tab index
	 * @param parent the container for the label
	 * @param mouseDownListener the listener for when a tab is selected
	 * @return the created label
	 */
	private Label createTabLabel( int index, String title, Composite parent, Listener mouseDownListener ) {

		// Wrap the labels in a composite
		Composite c = getFormToolkit().createComposite( parent, SWT.BORDER );
		c.setLayout( new GridLayout());
		c.setLayoutData( new GridData( 90, 25 ));
		c.setData( index );

		// Deal with the content
		Label l = getFormToolkit().createLabel( c, title );
		l.setLayoutData( new GridData( SWT.CENTER, SWT.CENTER, true, true ));
		l.setData( index );

		// The click listener
		l.addListener( SWT.MouseDown, mouseDownListener );
		c.addListener( SWT.MouseDown, mouseDownListener );

		return l;
	}


	/**
	 * Creates a scrollable that can be displayed in a scrolled page book.
	 * @param parent
	 * @param controls the controls that will associated with a page
	 * @param index the index of the controls (page order, 0-based index)
	 * @return the composite in which children can be added
	 */
	private Composite createScrollable( Composite parent, Control[] controls, int index ) {

		Composite container = getFormToolkit().createComposite( parent );
		controls[ index ] = container;
		GridLayoutFactory.swtDefaults().margins( 0, 0 ).extendedMargins( 0, 0, 7, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Since we are going to put sections in it, we cannot use a scrolled composite.
		// Section#reflow searches for a SharedScrolledComposite instance.
		// Using a scrolled composite will result in updating the entire editor and having a too big width.
		// The solution is to use an instance of SharedScrolledComposite as a parent => ScrolledForm.
		ScrolledForm scrolledForm = getFormToolkit().createScrolledForm( container );
		scrolledForm.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Composite result = scrolledForm.getBody();
		result.setLayout( new GridLayout());
		result.setLayoutData( new GridData( GridData.FILL_BOTH ));

		return result;
	}


	/**
	 * @param container
	 */
	private void createSourceTabContent( Composite container ) {

		// Create an explanation text
		StringBuilder sb  = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p>This is just an overview. To edit the sources, please, use the <a>Petals Source Editor</a>.</p>" );
		sb.append( "</form>" );

		FormText formText = this.ise.getFormToolkit().createFormText( container, false );
		formText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		formText.setText( sb.toString(), true, false );
		formText.addHyperlinkListener( new OpenSourceEditorHyperlinkListener( this.ise.getEditedFile(), false ));


		// Show the source
		final StyledText sourceViewerText = SwtFactory.createXmlTextViewer( container, false );
		sourceViewerText.setEditable( false );
		sourceViewerText.getParent().setLayoutData( new GridData( GridData.FILL_BOTH ));
		sourceViewerText.setToolTipText( "Sources cannot be edited directly in this editor" );

		ISelectionChangedListener listener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				if( event.getSelection().isEmpty())
					return;

				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( o instanceof AbstractEndpoint )
					refreshSourceViewer( sourceViewerText, (AbstractEndpoint) o, null );
			}
		};

		this.providesViewer.addSelectionChangedListener( listener );
		this.consumesViewer.addSelectionChangedListener( listener );


		// Refresh the viewer when the model changes
		final AtomicBoolean lock = new AtomicBoolean( false );

		// We use a lock because writing the model as a string results in resource change => infinite loop.
		// Notification filters are not of great help here.
		((TransactionalEditingDomain) getEditingDomain()).addResourceSetListener( new ResourceSetListenerImpl(  ) {
			@Override
			public boolean isPostcommitOnly() {
				return true;
			}

			@Override
			public void resourceSetChanged( ResourceSetChangeEvent event ) {
				if( ! lock.get())
					refreshSourceViewer( sourceViewerText, SuEditionComposite.this.selectedEndpoint, lock );
				else
					lock.set( false );
			}
		});
	}


	/**
	 * Refreshes the source viewer.
	 * @param sourceText
	 * @param endpoint
	 * @param lock
	 */
	private void refreshSourceViewer( final StyledText sourceText, AbstractEndpoint endpoint, AtomicBoolean lock ) {

		// Lock here. It will be released above.
		if( lock != null )
			lock.set( true );

		String s;
		try {
			if( endpoint != null )
				s = JbiXmlUtils.writePartialJbiXmlModel( endpoint );
			else
				s = "<!-- Nothing to serialize -->";

		} catch( IOException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			s = "<!-- \nThe selection could be serialized.\nAn error occured.\nCheck the logs for more details.\n -->";
		}

		final String text = s;
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				sourceText.setText( text );
			}
		});
	}


	/**
	 * @param toolkit
	 * @param generalDetails
	 */
	private void re_fillMainDetailsContainer(FormToolkit toolkit, Composite generalDetails) {

		for (Control control : generalDetails.getChildren())
			control.dispose();

		if (this.componentContributions != null) {
			GridLayoutFactory.swtDefaults().spacing( 0, 20 ).applyTo( generalDetails );
			this.componentContributions.addMainSUContent(this.selectedEndpoint, toolkit, generalDetails, this );
		}

		generalDetails.layout(true);
	}


	/**
	 * @param toolkit
	 * @param advancedDetails
	 */
	private void re_fillAdvancedDetailsContainer(FormToolkit toolkit, Composite advancedDetails) {

		for (Control control : advancedDetails.getChildren())
			control.dispose();

		if (this.componentContributions != null) {
			GridLayoutFactory.swtDefaults().spacing( 0, 20 ).applyTo( advancedDetails );
			this.componentContributions.addAdvancedSUContent(this.selectedEndpoint, toolkit, advancedDetails, this );
		}

		advancedDetails.layout(true);
	}


	/**
	 * Refreshes the right part.
	 */
	private void refreshDetails() {

		this.componentContributions = null;
		if( this.selectedEndpoint != null ) {
			ComponentVersionDescription componentDesc = ExtensionManager.INSTANCE.findComponentDescription( this.selectedEndpoint );
			if( componentDesc != null ) {
				EditorContributionSupport support = componentDesc.createNewExtensionSupport();
				if( support != null )
					this.componentContributions = support.createJbiEditorContribution( this.selectedEndpoint );
				else
					this.componentContributions = new DefaultJbiEditorContribution();

			} else {
				this.componentContributions = new DefaultJbiEditorContribution();
			}
		}

		re_fillMainDetailsContainer( this.ise.getFormToolkit(), this.mainDetails );
		re_fillAdvancedDetailsContainer( this.ise.getFormToolkit(), this.advancedDetails );
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
	 * @author Mickael Istria - EBM WebSourcing
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
	 * @author Mickael Istria - EBM WebSourcing
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
	 * @author Mickael Istria - EBM WebSourcing
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
