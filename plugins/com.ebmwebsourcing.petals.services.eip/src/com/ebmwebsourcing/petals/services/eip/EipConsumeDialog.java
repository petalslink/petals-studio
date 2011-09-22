/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.eip;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.XPathSourceViewerConfiguration;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.su.ui.OperationLabelProvider;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * The dialog which contains fields to define a consumed project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConsumeDialog extends TitleAreaDialog {

	// Graphical fields
	protected Text interfaceNameText, interfaceNamespaceText;
	protected Text serviceNameText, serviceNamespaceText, endpointNameText;
	protected Text opNameText, opNamespaceText;
	protected StyledText xpathConditionText;
	protected ComboViewer operationViewer, mepViewer;

	private Image propertiesImg, projectImg, tipImg;
	private ControlDecoration operationDecoration;

	// Internal states used in the UI
	protected boolean initialized = false;
	protected final IDialogSettings dialogSettings;

	// Data fields
	protected Map<QName,Mep> opNameToMep = new HashMap<QName,Mep> ();
	protected Mep invocationMep = Mep.UNKNOWN;

	/**
	 * The 1-relative position of the consumed project edited in this dialog.
	 */
	private final int index;

	/**
	 * The EIP bean containing the elements edited by this dialog plus a backup element for restoration.
	 */
	protected EipConsumeDataBean eipBean;

	/**
	 * The consumed end-point bean.
	 */
	protected EndpointBean endpointBean;



	/**
	 * Constructor.
	 * 
	 * @param index the 1-relative position of the edited consumed project in the table of projects.
	 * @param eipBean the EIP bean previously edited, if this project was already edited (null if edited for the first time).
	 * @param eipConsumesPage
	 */
	public EipConsumeDialog( int index, EipConsumeDataBean eipBean, EipConsumesPage eipConsumesPage ) {

		// The shell
		super( eipConsumesPage.getShell());
		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX );
		this.dialogSettings = eipConsumesPage.getWizard().getDialogSettings();

		// The images
		try {
			this.propertiesImg = PetalsEipPlugin.getImageDescriptor( "icons/obj16/properties.gif" ).createImage();
			this.projectImg = PetalsEipPlugin.getImageDescriptor( "icons/obj16/project.gif" ).createImage();
			this.tipImg = PetalsEipPlugin.getImageDescriptor( "icons/obj16/smartmode_co.gif" ).createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}

		// Other
		this.index = index;
		this.eipBean = eipBean.duplicate();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #getDialogBoundsSettings()
	 */
	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		return super.getDialogBoundsSettings();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		Composite bigContainer = (Composite) super.createDialogArea( parent );

		setTitle( "Invoked Service " + (this.index + 1));
		getShell().setText( "EIP :: Invoked Service " + (this.index + 1));
		getShell().setImage( this.propertiesImg );
		setMessage( "Identify the service to invoke." );

		// Add a scrolled composite for smaller resolutions
		ScrolledComposite sc = new ScrolledComposite( bigContainer, SWT.H_SCROLL | SWT.V_SCROLL );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		sc.setLayoutData( layoutData );

		//
		// Body of the dialog.
		Composite container = new Composite( sc, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.verticalSpacing = 18;
		layout.marginWidth = 12;
		container.setLayout( layout );

		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.verticalIndent = 6;
		layoutData.widthHint = 600;
		container.setLayoutData( layoutData );

		// End-point selection link
		new Label( container, SWT.NONE ).setImage( this.tipImg );
		final Link selectLink = new Link( container, SWT.NO_FOCUS );
		selectLink.setText( "<A>Select a service</A> from the Petals Services view." );
		selectLink.setToolTipText( "Select an end-point to consume among referenced end-points" );
		selectLink.addSelectionListener( new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// Get the end-point to consume
				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( selectLink );
				if( bean != null ) {
					String srvName = bean.getServiceName() != null ? bean.getServiceName().getLocalPart() : null;
					String srvNs = bean.getServiceName() != null ? bean.getServiceName().getNamespaceURI() : null;
					String itfName = bean.getServiceName() != null ? bean.getInterfaceName().getLocalPart() : null;
					String itfNs = bean.getServiceName() != null ? bean.getInterfaceName().getNamespaceURI() : null;
					String edpt;
					if( PetalsConstants.AUTO_GENERATE.equals( bean.getEndpointName()))
						edpt = "";
					else
						edpt = bean.getEndpointName();

					// Update the text fields
					EipConsumeDialog.this.interfaceNameText.setText( itfName != null ? itfName : "" );
					EipConsumeDialog.this.interfaceNamespaceText.setText( itfNs != null ? itfNs : "" );
					EipConsumeDialog.this.serviceNameText.setText( srvName!= null ? srvName : "" );
					EipConsumeDialog.this.serviceNamespaceText.setText( srvNs!= null ? srvNs : "" );
					EipConsumeDialog.this.endpointNameText.setText( edpt );

					// Update the operation parts
					EipConsumeDialog.this.endpointBean = bean;
					updateOperationViewer();
				}
			}
		});

		//
		// Service properties
		createBasicGUI( container );
		createInvocationGUI( container );
		if( this.eipBean.mustHaveCondition())
			createConditionGUI( container );

		//
		// Finish preparing the scrolled composite
		container.setSize( container.computeSize( 600, SWT.DEFAULT ));
		sc.setContent( container );

		//
		// Initialize widgets.
		initializeWidgetValues( this.eipBean );

		//
		// Listeners
		final ModifyListener modifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				validate();
			}
		};

		this.interfaceNameText.addModifyListener( modifyListener );
		this.interfaceNamespaceText.addModifyListener( modifyListener );
		this.serviceNameText.addModifyListener( modifyListener );
		this.serviceNamespaceText.addModifyListener( modifyListener );
		this.endpointNameText.addModifyListener( modifyListener );
		if( this.xpathConditionText != null )
			this.xpathConditionText.addModifyListener( modifyListener );

		this.interfaceNameText.setFocus();
		this.interfaceNameText.setSelection( this.interfaceNameText.getText().length());
		return bigContainer;
	}


	/**
	 * @param container
	 */
	protected void createConditionGUI( Composite container ) {

		Group group = new Group( container, SWT.SHADOW_ETCHED_OUT );
		group.setText( "Routing Condition" );
		group.setLayout( new GridLayout());

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		group.setLayoutData( layoutData );

		Label label = new Label( group, SWT.NONE );
		label.setText( "Test the incoming message's content with a XPath condition." );
		String tooltip = "If true, call the corresponding 'Consumes' service. Otherwise, the last 'Consumes' service is called as default.";
		label.setToolTipText( tooltip );

		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 10;
		label.setLayoutData( layoutData );

		Composite editor = new Composite( group, SWT.BORDER );
		editor.setLayout( new FillLayout ());
		editor.setLayoutData( new GridData( GridData.FILL_BOTH ));

		int style = SWT.V_SCROLL | SWT.MULTI | SWT.BORDER;
		ISourceViewer viewer = new SourceViewer( editor, new VerticalRuler( 0 ), style );
		ColorManager cManager = new ColorManager ();
		viewer.configure( new XPathSourceViewerConfiguration( cManager ));

		viewer.getTextWidget().setLayoutData( new GridData( GridData.FILL_BOTH ));
		IDocument document = new Document( "" );
		viewer.setDocument( document );

		this.xpathConditionText = viewer.getTextWidget();
	}


	/**
	 * @param container
	 */
	private void createBasicGUI( Composite container ) {

		Group group = new Group( container, SWT.SHADOW_OUT );
		group.setText( "Service Identifers" );
		group.setLayout( new GridLayout( 4, false ));

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		group.setLayoutData( layoutData );


		// Interface.
		new Label( group, SWT.NONE ).setText( "Interface Name:" );
		this.interfaceNameText = new Text( group, SWT.SINGLE | SWT.BORDER );
		this.interfaceNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( group, SWT.NONE ).setText( "Interface Namespace:" );
		this.interfaceNamespaceText = new Text( group, SWT.SINGLE | SWT.BORDER );
		this.interfaceNamespaceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// Service.
		new Label( group, SWT.NONE).setText( "Service Name:" );
		this.serviceNameText = new Text( group, SWT.SINGLE | SWT.BORDER);
		this.serviceNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( group, SWT.NONE).setText( "Service Namespace:" );
		this.serviceNamespaceText = new Text( group, SWT.SINGLE | SWT.BORDER);
		this.serviceNamespaceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// End-point.
		new Label( group, SWT.NONE ).setText( "Endpoint Name:" );
		this.endpointNameText = new Text( group, SWT.SINGLE | SWT.BORDER );
		this.endpointNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
	}


	/**
	 * @param container
	 */
	protected void createInvocationGUI( Composite container ) {

		// Create the parent
		Group invocationGroup = new Group( container, SWT.SHADOW_OUT );
		invocationGroup.setText( "Invocation Properties" );
		invocationGroup.setLayout( new GridLayout( 4, false ));

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		invocationGroup.setLayoutData( layoutData );


		// The operation helper
		new Label( invocationGroup, SWT.NONE ).setText( "Operation helper:" );
		this.operationViewer = new ComboViewer( invocationGroup );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		this.operationViewer.getCombo().setLayoutData( layoutData );

		this.operationViewer.setContentProvider( new ArrayContentProvider());
		this.operationViewer.setLabelProvider( new OperationLabelProvider());
		this.operationViewer.setInput( this.opNameToMep.keySet());
		this.operationDecoration = new ControlDecoration( this.operationViewer.getCombo(), SWT.LEFT | SWT.BOTTOM );


		// The manual fields
		new Label( invocationGroup, SWT.NONE ).setText( "Operation name:" );
		this.opNameText = new Text( invocationGroup, SWT.SINGLE | SWT.BORDER );
		this.opNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( invocationGroup, SWT.NONE ).setText( "Operation namespace:" );
		this.opNamespaceText = new Text( invocationGroup, SWT.SINGLE | SWT.BORDER );
		this.opNamespaceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// The MEP
		new Label( invocationGroup, SWT.NONE ).setText( "Invocation MEP:" );
		this.mepViewer = new ComboViewer( invocationGroup, SWT.BORDER | SWT.READ_ONLY | SWT.DROP_DOWN );
		this.mepViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.mepViewer.setContentProvider( new ArrayContentProvider());
		this.mepViewer.setLabelProvider( new LabelProvider());
		this.mepViewer.setInput( Mep.values());


		// The listeners
		this.operationViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				ISelection selection = EipConsumeDialog.this.operationViewer.getSelection();
				if( ! selection.isEmpty()) {
					Object o = ((IStructuredSelection) selection).getFirstElement();
					EipConsumeDialog.this.invocationMep = EipConsumeDialog.this.opNameToMep.get( o );
					EipConsumeDialog.this.mepViewer.setSelection( new StructuredSelection( EipConsumeDialog.this.invocationMep ));

					QName op = (QName) o;
					EipConsumeDialog.this.opNameText.setText( op.getLocalPart() != null ? op.getLocalPart() : "" );
					EipConsumeDialog.this.opNamespaceText.setText( op.getNamespaceURI() != null ? op.getNamespaceURI() : "" );
					// Validation is implicit with the previous calls
				}
			}
		});

		Listener listener = new Listener() {
			public void handleEvent( Event event ) {
				validate();
			}
		};

		this.opNamespaceText.addListener( SWT.Modify, listener );
		this.opNameText.addListener( SWT.Modify, listener );
		this.mepViewer.getCombo().addListener( SWT.Selection, listener );
	}


	/**
	 * Updates the operation viewer.
	 */
	protected void updateOperationViewer() {

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		this.operationViewer.getCombo().setLayoutData( layoutData );

		this.opNameToMep.clear();
		if( this.endpointBean != null )
			this.opNameToMep.putAll( ConsumeUtils.getOperations( Arrays.asList( this.endpointBean )));

		if( ! this.opNameToMep.isEmpty()) {
			this.operationViewer.getCombo().setEnabled( true );
			layoutData.horizontalIndent = 14;
			this.operationViewer.getCombo().getParent().layout();

			this.operationDecoration.setImage( this.tipImg );
			this.operationDecoration.show();

		} else {
			// The order matters! Do not try to optimize the code.
			this.operationViewer.getCombo().getParent().layout();
			this.operationDecoration.hide();
			this.operationViewer.getCombo().setEnabled( false );
		}

		EipConsumeDialog.this.operationViewer.refresh();
		this.operationViewer.getCombo().setVisibleItemCount( this.opNameToMep.size());
	}


	/**
	 * Validates the dialog data.
	 */
	public void validate() {

		if( ! this.initialized )
			return;

		// Interface name
		if( this.interfaceNamespaceText.getText().trim().length() == 0 ) {
			updateStatus( "You have to provide the interface namespace." );
			return;
		}

		if( this.interfaceNameText.getText().trim().length() == 0 ) {
			updateStatus( "You have to provide the interface name." );
			return;
		}

		// End-point and service names
		if( this.serviceNameText.getText().trim().length() > 0
					&& this.serviceNamespaceText.getText().trim().length() == 0 ) {
			updateStatus( "You have to provide the service namespace." );
			return;
		}

		if( this.serviceNamespaceText.getText().trim().length() > 0
					&& this.serviceNameText.getText().trim().length() == 0 ) {
			updateStatus( "You have to provide the service name." );
			return;
		}


		// The operation and the MEP
		if( this.opNamespaceText.getText().trim().length() == 0 ) {
			updateStatus( "You have to define the namespace of the operation to invoke." );
			return;
		}

		if( this.opNameText.getText().trim().length() == 0 ) {
			updateStatus( "You have to define the name of the operation to invoke." );
			return;
		}

		this.invocationMep = (Mep) ((IStructuredSelection) this.mepViewer.getSelection()).getFirstElement();
		if( this.invocationMep == Mep.UNKNOWN ) {
			updateStatus( "You have to define the invocation MEP." );
			return;
		}


		// The XPath condition is syntactically correct
		if( this.xpathConditionText != null ) {
			if( this.xpathConditionText.getText().length() == 0 ) {
				updateStatus( "You have to provide an XPath condition." );
				return;
			}

			String msg = XPathUtils.validateXPathExpression( this.xpathConditionText.getText());
			if( msg != null ) {
				updateStatus( msg );
				return;
			}
		}

		// Everything is right, store the values.
		updateStatus( null );
		setMessage( "Identify the service to invoke." );
		this.eipBean.setServiceName( this.serviceNameText.getText());
		this.eipBean.setServiceNamespace( this.serviceNamespaceText.getText());

		this.eipBean.setInterfaceName( this.interfaceNameText.getText());
		this.eipBean.setInterfaceNamespace( this.interfaceNamespaceText.getText());

		this.eipBean.setEndpointName( this.endpointNameText.getText());
		this.eipBean.setEndpointBean( this.endpointBean );
		this.eipBean.setMep( this.invocationMep );
		this.eipBean.setInvokedOperation( new QName(
					this.opNamespaceText.getText().trim(),
					this.opNameText.getText().trim()));

		if( this.xpathConditionText != null )
			this.eipBean.setCondition( this.xpathConditionText.getText());
	}


	/**
	 * Updates the status of the dialog.
	 * <p>
	 * If the message is null, enable the OK button and display the normal header.
	 * Otherwise, disable the OK button and display the error message in the header.
	 * </p>
	 * 
	 * @param message the error message.
	 */
	protected void updateStatus( String message ) {

		setErrorMessage( message );
		Button okButton = getButton( IDialogConstants.OK_ID );
		if( okButton != null )
			okButton.setEnabled( message == null );
	}


	/**
	 * @return the bean filled in this dialog.
	 */
	public EipConsumeDataBean getData() {
		return this.eipBean;
	}


	/**
	 * Initializes the dialog fields from the given argument.
	 * @param eipBean an EIP bean whose values are used to initialize the dialog widgets.
	 */
	protected void initializeWidgetValues( EipConsumeDataBean eipBean ) {

		// Service name.
		String serviceName = eipBean.getServiceName();
		if( serviceName != null ) {
			String serviceNs = eipBean.getServiceNamespace();
			if( serviceNs != null )
				this.serviceNamespaceText.setText( serviceNs );
			this.serviceNameText.setText( serviceName );
		}
		else
			this.serviceNameText.setText( "" );

		// Interface name.
		String interfaceName = eipBean.getInterfaceName();
		if( interfaceName != null ) {
			String interfaceNs = eipBean.getInterfaceNamespace();
			if( interfaceNs != null )
				this.interfaceNamespaceText.setText( interfaceNs );
			this.interfaceNameText.setText( interfaceName );
		}
		else
			this.interfaceNameText.setText( "" );

		// End-point and condition.
		String endpointName = eipBean.getEndpointName();
		if( endpointName != null )
			this.endpointNameText.setText( endpointName );
		else
			this.endpointNameText.setText( "" );

		String condition = eipBean.getCondition();
		if( this.xpathConditionText != null ) {
			if( this.xpathConditionText.isEnabled() && condition != null )
				this.xpathConditionText.setText( condition );
			else
				this.xpathConditionText.setText( "" );
		}

		// Operation and MEP
		this.endpointBean = this.eipBean.getEndpointBean();
		updateOperationViewer();

		if( this.opNameToMep.size() > 0
					&& eipBean.getInvokedOperation() != null ) {
			this.operationViewer.setSelection( new StructuredSelection( this.eipBean.getInvokedOperation()));
		} else {
			this.operationViewer.getCombo().setEnabled( false );
		}

		// The end-point bean and the invoked operation / MEP may be not synchronized
		if( eipBean.getMep() != null )
			this.mepViewer.setSelection( new StructuredSelection( eipBean.getMep()));

		if( eipBean.getInvokedOperation() != null ) {
			if( eipBean.getInvokedOperation().getLocalPart() != null )
				this.opNameText.setText( eipBean.getInvokedOperation().getLocalPart());

			if( eipBean.getInvokedOperation().getNamespaceURI() != null )
				this.opNamespaceText.setText( eipBean.getInvokedOperation().getNamespaceURI());
		}

		this.initialized = true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #close()
	 */
	@Override
	public boolean close() {

		if( this.propertiesImg != null ) {
			this.propertiesImg.dispose();
			this.propertiesImg = null;
		}

		if( this.projectImg != null ) {
			this.projectImg.dispose();
			this.projectImg = null;
		}

		if( this.tipImg != null ) {
			this.tipImg.dispose();
			this.tipImg = null;
		}

		return super.close();
	}
}
