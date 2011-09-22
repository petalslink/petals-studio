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

package com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser.JbiBasicBean;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractJbiPage;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * JBI page for Binding Components in "consume".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BcConsumeJbiPage extends AbstractJbiPage {

	private Button invokeByServiceButton, invokeByEndpointButton;
	private Image tipImage, tooltipImage;
	private Font boldFont;

	private EndpointBean consumedBean;
	private FixedShellTooltip helpTooltip;
	private boolean tooltipWasVisible = false;
	private boolean invokeByServiceName = false;
	private boolean invokeByEndpointName = false;


	/**
	 * Constructor.
	 * @param suType
	 * @param suTypeVersion
	 */
	public BcConsumeJbiPage( String suType, String suTypeVersion ) {
		super( suType, suTypeVersion );

		setDescription( "Define the JBI properties of the service to consume." );
		this.isConsume = true;

		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/smartmode_co.gif" );
			if( desc != null )
				this.tipImage = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {

		if( this.helpTooltip != null )
			this.helpTooltip.dispose();

		if( this.tipImage != null && ! this.tipImage.isDisposed())
			this.tipImage.dispose();

		if( this.tooltipImage != null && ! this.tooltipImage.isDisposed())
			this.tooltipImage.dispose();

		if( this.boldFont != null && ! this.boldFont.isDisposed())
			this.boldFont.dispose();

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractJbiPage
	 * #createCustomControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createCustomControls( Composite container ) {

		// Usual controls
		addWorkspaceBrowser( container );
		createCommonControls( container, 20 );
		createCheckboxes( container );


		// Add a tool tip to display in case of problem
		this.helpTooltip = new FixedShellTooltip( getShell(), true, 90 ) {
			@Override
			public void populateTooltip( Composite parent ) {

				GridLayout layout = new GridLayout();
				layout.verticalSpacing = 2;
				parent.setLayout( layout );
				parent.setLayoutData( new GridData( GridData.FILL_BOTH ));
				parent.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_LIST_BACKGROUND ));

				try {
					ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(
							PetalsConstants.PETALS_COMMON_PLUGIN_ID, "icons/petals/teaching.png" );

					if( desc != null )
						BcConsumeJbiPage.this.tooltipImage = desc.createImage();

					parent.setBackgroundMode( SWT.INHERIT_DEFAULT );
					Label imgLabel = new Label( parent, SWT.NONE );
					imgLabel.setImage( BcConsumeJbiPage.this.tooltipImage );
					imgLabel.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, true ));

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.WARNING );
				}

				FontData[] fd = PlatformUtils.getModifiedFontData( getFont().getFontData(), SWT.BOLD );
				BcConsumeJbiPage.this.boldFont = new Font( getShell().getDisplay(), fd );
				Label titleLabel = new Label( parent, SWT.NONE );
				titleLabel.setFont( BcConsumeJbiPage.this.boldFont );
				GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
				layoutData.verticalIndent = 5;
				titleLabel.setLayoutData( layoutData );
				titleLabel.setText( "What does this error mean?" );

				StringBuilder sb = new StringBuilder();
				sb.append( "The " + BcConsumeJbiPage.this.suType + " component can only invoke (consume) services with the " );
				sb.append( getDialogSettings().get( SettingConstants.SUPPORTED_MEP ) + " MEP.\n" );
				sb.append( "But the service you chose has no (predefined) operation that supports this invocation MEP." );

				Label l = new Label( parent, SWT.WRAP );
				l.setText( sb.toString());
				layoutData = new GridData();
				layoutData.verticalIndent = 8;
				l.setLayoutData( layoutData );

				RowLayout rowLayout = new RowLayout( SWT.HORIZONTAL );
				rowLayout.marginLeft = 0;
				rowLayout.marginTop = 8;
				rowLayout.marginRight = 0;
				rowLayout.marginBottom = 0;
				rowLayout.spacing = 0;

				Composite rowComposite = new Composite( parent, SWT.NONE );
				rowComposite.setLayout( rowLayout );
				rowComposite.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, true ));

				new Label( rowComposite, SWT.NONE ).setText( "Please, note that there is an " );
				Link link = new Link( rowComposite, SWT.WRAP | SWT.NO_FOCUS );
				link.setText( "<A>option in the Petals preferences</A>" );
				new Label( rowComposite, SWT.WRAP ).setText( "." );
				new Label( rowComposite, SWT.WRAP ).setText( " to not display incompatible services." );

				link.addSelectionListener( new SelectionListener() {

					public void widgetSelected( SelectionEvent e ) {
						widgetDefaultSelected( e );
					}

					public void widgetDefaultSelected( SelectionEvent e ) {
						try {
							Dialog dlg = PreferencesUtil.createPreferenceDialogOn(
									new Shell(),
									"com.ebmwebsourcing.petals.services.prefs.services",
									null, null );

							if( dlg.open() == Window.OK )
								validate();

						} catch( Exception e1 ) {
							PetalsServicesPlugin.log( e1, IStatus.ERROR );
						}
					}
				});
			}
		};

		this.helpTooltip.hide();
	}


	/**
	 * The widgets to define the invocation properties.
	 * @param container
	 */
	private void createCheckboxes( Composite container ) {

		// Add the button asking if we should generate the end-point.
		new Label( container, SWT.NONE ).setText( "" ); //$NON-NLS-1$
		this.invokeByServiceButton = new Button( container, SWT.CHECK );
		this.invokeByServiceButton.setText( "Invoke by service name" );
		this.invokeByServiceButton.setToolTipText( "Use this service name in the selection of a service provider" );
		this.invokeByServiceButton.setSelection( this.invokeByServiceName );

		new Label( container, SWT.NONE ).setText( "" ); //$NON-NLS-1$
		this.invokeByEndpointButton = new Button( container, SWT.CHECK );
		this.invokeByEndpointButton.setText( "Invoke by end-point name" );
		this.invokeByEndpointButton.setToolTipText( "Use this end-point name in the selection of a service provider" );
		this.invokeByEndpointButton.setSelection( this.invokeByEndpointName );
		this.invokeByEndpointButton.setEnabled( this.invokeByServiceName );


		// Listeners
		this.invokeByServiceButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				BcConsumeJbiPage.this.invokeByServiceName = ((Button) e.widget).getSelection();
				BcConsumeJbiPage.this.serviceNameCombo.getCCombo().setEnabled( BcConsumeJbiPage.this.invokeByServiceName );
				BcConsumeJbiPage.this.serviceNamespaceCombo.getCCombo().setEnabled( BcConsumeJbiPage.this.invokeByServiceName );

				if( ! BcConsumeJbiPage.this.invokeByServiceName ) {
					BcConsumeJbiPage.this.invokeByEndpointName = false;
					BcConsumeJbiPage.this.endpointNameText.setEnabled( false );
				} else {
					BcConsumeJbiPage.this.invokeByEndpointButton.notifyListeners( SWT.Selection, new Event());
				}

				BcConsumeJbiPage.this.invokeByEndpointButton.setEnabled( BcConsumeJbiPage.this.invokeByServiceName );
				validate();
			}
		});

		this.invokeByEndpointButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				BcConsumeJbiPage.this.invokeByEndpointName = ((Button) e.widget).getSelection();
				BcConsumeJbiPage.this.endpointNameText.setEnabled( BcConsumeJbiPage.this.invokeByEndpointName );
				validate();
			}
		});

		this.invokeByServiceButton.notifyListeners( SWT.Selection, new Event());
		this.invokeByEndpointButton.notifyListeners( SWT.Selection, new Event());
	}


	/**
	 * Adds a browser to look for "provide" end-points in the workspace.
	 * @param table
	 */
	private void addWorkspaceBrowser( final Composite table ) {

		Composite container = new Composite( table, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginBottom = 5;
		container.setLayout( layout );

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		container.setLayoutData( layoutData );
		new Label( container, SWT.NONE ).setImage( this.tipImage );


		// Add an hyper link
		Link selectLink = new Link( container, SWT.NONE );
		selectLink.setText( "<A>Select a service</A> from the Petals Services view to fill in these fields automatically." );
		selectLink.setToolTipText( "Select an end-point to consume among the currently referenced end-points" );
		selectLink.addSelectionListener( new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// Is there a filtering MEP?
				String mepAsString = getDialogSettings().get( SettingConstants.SUPPORTED_MEP );
				Mep filterMep = Mep.whichMep( mepAsString );

				// Display the selection dialog
				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( table, filterMep );
				BcConsumeJbiPage.this.consumedBean = bean;
				if( bean == null )
					return;

				// Update the wizard page
				try {
					BcConsumeJbiPage.this.wsdlBeans.clear();
					JbiBasicBean jbiBean = new JbiBasicBean();

					String srvName = bean.getServiceName() != null ? bean.getServiceName().getLocalPart() : null;
					String srvNs = bean.getServiceName() != null ? bean.getServiceName().getNamespaceURI() : null;
					jbiBean.setServiceName( srvName );
					jbiBean.setServiceNs( srvNs );

					String itfName = bean.getServiceName() != null ? bean.getInterfaceName().getLocalPart() : null;
					String itfNs = bean.getServiceName() != null ? bean.getInterfaceName().getNamespaceURI() : null;
					jbiBean.setInterfaceName( itfName );
					jbiBean.setInterfaceNs( itfNs );

					boolean byEdpt = true;
					if( PetalsConstants.AUTO_GENERATE.equals( bean.getEndpointName())
							|| "autogenerate".equals( bean.getEndpointName())) {
						jbiBean.setEndpointName( "" );
						byEdpt = false;
					} else {
						jbiBean.setEndpointName( bean.getEndpointName());
					}

					BcConsumeJbiPage.this.wsdlBeans.add( jbiBean );
					updateViewers();

					BcConsumeJbiPage.this.invokeByEndpointButton.setSelection( byEdpt );
					BcConsumeJbiPage.this.invokeByServiceButton.setSelection( true );
					BcConsumeJbiPage.this.invokeByServiceButton.notifyListeners( SWT.Selection, new Event());
					BcConsumeJbiPage.this.invokeByEndpointButton.notifyListeners( SWT.Selection, new Event());

				} catch( Exception e1 ) {
					PetalsServicesPlugin.log( e1, IStatus.ERROR );
				}

				validate();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		setMessage( null );
		setMessage( null, IMessageProvider.WARNING );

		// Interface name
		IDialogSettings settings = getWizard().getDialogSettings();
		if( settings.getBoolean( SettingConstants.ITF_VALIDATE )) {
			if( this.interfaceNamespaceCombo.getCCombo().getText().trim().length() == 0 ) {
				updateStatus( "You have to provide the interface namepsace." );
				return false;
			}

			try {
				new URI( this.interfaceNamespaceCombo.getCCombo().getText());
			} catch( URISyntaxException e ) {
				updateStatus( "The interface namespace is not a valid URI." );
				return false;
			}

			if( this.interfaceNameCombo.getCCombo().getText().trim().length() == 0 ) {
				updateStatus( Messages.ConsumeJbiPage_9 );
				return false;
			}
		}

		// End-point and service names
		if( this.invokeByServiceName ) {

			// SRV name space URI
			if( this.serviceNamespaceCombo.getCCombo().getText().trim().length() == 0 ) {
				updateStatus( "You have to provide the service namepsace." );
				return false;
			}

			try {
				new URI( this.serviceNamespaceCombo.getCCombo().getText());
			} catch( URISyntaxException e ) {
				updateStatus( "The service namespace is not a valid URI." );
				return false;
			}

			// Fill-in both fields
			if( this.serviceNamespaceCombo.getCCombo().getText().trim().length() > 0
					&& this.serviceNameCombo.getCCombo().getText().trim().length() == 0 ) {
				updateStatus( "You have to provide the service name." );
				return false;
			}
		}

		// EDPT name
		if( this.invokeByEndpointName && StringUtils.isEmpty( this.endpointNameText.getText())) {
			updateStatus( "You have to define the end-point name." );
			return false;
		}


		// Update data.
		updateStatus( null );
		this.interfaceName = this.interfaceNameCombo.getCCombo().getText();
		this.interfaceNamespace = this.interfaceNamespaceCombo.getCCombo().getText();

		this.serviceName = this.invokeByServiceName ? this.serviceNameCombo.getCCombo().getText() : "";
		this.serviceNamespace = this.invokeByServiceName ? this.serviceNamespaceCombo.getCCombo().getText() : "";
		this.endpointName = this.invokeByEndpointName ? this.endpointNameText.getText() : "";


		// Make sure this service can be invoked (MEP)
		// Do not rely on the consumed bean, values may have been changed manually
		String mepAsString = getDialogSettings().get( SettingConstants.SUPPORTED_MEP );
		Mep filterMep = Mep.whichMep( mepAsString );
		Map<QName,Mep> ops = ConsumeUtils.getValidOperationsForConsume(
				new QName( this.interfaceNamespace, this.interfaceName ),
				new QName( this.serviceNamespace, this.serviceName ),
				this.endpointName );

		if( filterMep != Mep.UNKNOWN ) {
			if( ! ops.isEmpty()) {
				if( ! ConsumeUtils.supportsMep( ops, filterMep )) {
					setMessage( "This service cannot be invoked with the " + mepAsString + " MEP.", IMessageProvider.ERROR );
					this.helpTooltip.show();
					this.tooltipWasVisible = true;

				} else {
					this.helpTooltip.hide();
					this.tooltipWasVisible = false;
				}

			} else {
				setMessage( "This service may not be invocable with the " + mepAsString + " MEP.", IMessageProvider.WARNING );
				this.helpTooltip.show();
				this.tooltipWasVisible = true;
			}
		}

		// Keep values - e.g. for the project page
		settings.put( SettingConstants.SRV_NAME_VALUE, this.serviceName );
		settings.put( SettingConstants.SRV_NS_VALUE, this.serviceNamespace );
		settings.put( SettingConstants.ITF_NAME_VALUE, this.interfaceName );
		settings.put( SettingConstants.ITF_NS_VALUE, this.interfaceNamespace );
		settings.put( SettingConstants.EDPT_NAME_VALUE, this.endpointName );

		if( this.consumedBean != null ) {
			settings.put( SettingConstants.CONSUMED_COMPONENT_NAME, this.consumedBean.getComponentName());
			settings.put( SettingConstants.CONSUMED_WSDL_URI, String.valueOf( this.consumedBean.getWsdlUri()));
		} else {
			settings.put( SettingConstants.CONSUMED_COMPONENT_NAME, "" );
			settings.put( SettingConstants.CONSUMED_WSDL_URI, (String) null );
		}

		// - Register the SOAP service name in the wizard configuration (for the SOAP plug-in)
		if( this.selectedBean != null )
			settings.put( SettingConstants.SOAP_SERVICE_NAME, this.selectedBean.getServiceName());
		//

		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {
		super.setVisible( visible );

		if( this.helpTooltip != null && this.tooltipWasVisible ) {
			if( visible )
				this.helpTooltip.show();
			else
				this.helpTooltip.hide();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId(Composite container) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				container,
				PetalsServicesPlugin.PLUGIN_ID + ".consumeJbiPageId" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractJbiPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		super.fillInData( suBean );
		suBean.setConsume( true );
	}
}
