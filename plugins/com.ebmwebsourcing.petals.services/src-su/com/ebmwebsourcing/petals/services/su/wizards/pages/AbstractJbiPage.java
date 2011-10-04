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

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * The abstract wizard page showing JBI standard fields.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractJbiPage extends AbstractSuPage {

	/** String values to keep for when the wizard page isn't be visible anymore. */
	protected String interfaceName, interfaceNamespace, serviceName, serviceNamespace, endpointName;

	/** Common controls. */
	protected ComboViewer interfaceNameCombo, interfaceNamespaceCombo, serviceNameCombo, serviceNamespaceCombo;
	/** */
	protected Text endpointNameText;
	/** */
	protected boolean isConsume = false;

	/** */
	protected List<JbiBasicBean> wsdlBeans = new ArrayList<JbiBasicBean> ();
	protected JbiBasicBean selectedBean;
	private final Semaphore semaphore = new Semaphore( 1 );


	/**
	 * Constructor.
	 * @param suType the component type used by the SU (e.g. FTP, XSLT...).
	 * @param suTypeVersion the version of the Petals component used by the SU.
	 */
	public AbstractJbiPage( String suType, String suTypeVersion ) {
		super( SuMainConstants.PAGE_GENERAL_JBI_DATA, suType, suTypeVersion );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public final void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Create controls in the form.
		createCustomControls( container );

		// Complete wizard
		boolean isComplete = validate();
		if( !isComplete )
			setErrorMessage( null );
		setPageComplete( isComplete );
		setControl( container );
		this.interfaceNamespaceCombo.getCCombo().setFocus();
	}


	/**
	 * This method defines the controls to add in the page.
	 * It should use {@link AbstractJbiPage#createCommonControls(Composite)}
	 * @param container the parent container with a two-column layout
	 */
	protected abstract void createCustomControls( Composite container );


	/**
	 * @param container
	 */
	protected void createCommonControls( Composite container ) {
		createCommonControls( container, 0 );
	}


	/**
	 * Create JBI common controls (service, interface and endpoint names).
	 * @param container
	 * @param marginTop the margin above the first widget.
	 */
	protected void createCommonControls( Composite container, int marginTop ) {

		Object input = new Object ();
		ArrayContentProvider ctProvider = new ArrayContentProvider () {
			@Override
			public Object[] getElements( Object inputElement ) {
				return AbstractJbiPage.this.wsdlBeans.toArray();
			}
		};

		ModifyListener modifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if( !AbstractJbiPage.this.semaphore.tryAcquire())
					return;

				try {
					validate ();
				} catch( Exception e2 ) {
					e2.printStackTrace();
				} finally {
					AbstractJbiPage.this.semaphore.release();
				}
			}
		};

		KeyListener defaultNsKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {

				if( e.stateMask == SWT.CTRL
							&& e.character == ' '
								&& ((CCombo) e.widget).getText().trim().length() == 0 ) {
					e.doit = false;
					((CCombo) e.widget).setText( "http://petals.ow2.org/" );
				}
			}
		};


		// Field "interface namespace"
		final int verticalIndent = 15;
		Label l = new Label( container, SWT.NONE );
		l.setText( "Interface Namespace:" );
		GridData layoutData = new GridData();
		layoutData.verticalIndent = 15;
		l.setLayoutData( layoutData );

		this.interfaceNamespaceCombo = new ComboViewer( new CCombo( container, SWT.BORDER ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 15;
		this.interfaceNamespaceCombo.getCCombo().setLayoutData( layoutData );
		this.interfaceNamespaceCombo.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				String n = ((JbiBasicBean) element).getInterfaceNs();
				return n != null ? n : "";
			}
		});

		this.interfaceNamespaceCombo.setContentProvider( ctProvider );
		this.interfaceNamespaceCombo.setInput( input );
		this.interfaceNamespaceCombo.getCCombo().addModifyListener( modifyListener );
		this.interfaceNamespaceCombo.addPostSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( org.eclipse.jface.viewers.SelectionChangedEvent event ) {
				comboChanged( event );
			}
		});

		this.interfaceNamespaceCombo.getCCombo().addKeyListener( defaultNsKeyListener );


		// Add a field "interface-name".
		l = new Label( container, SWT.NONE );
		l.setText( "Interface Name:" );

		this.interfaceNameCombo = new ComboViewer( new CCombo( container, SWT.BORDER ));
		this.interfaceNameCombo.getCCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.interfaceNameCombo.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				String n = ((JbiBasicBean) element).getInterfaceName();
				return n != null ? n : "";
			}
		});

		this.interfaceNameCombo.setContentProvider( ctProvider );
		this.interfaceNameCombo.setInput( input );
		this.interfaceNameCombo.getCCombo().addModifyListener( modifyListener );
		this.interfaceNameCombo.addPostSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( org.eclipse.jface.viewers.SelectionChangedEvent event ) {
				comboChanged( event );
			}
		});


		// Field "service namespace"
		l = new Label( container, SWT.NONE );
		l.setText( "Service Namespace:" );
		layoutData = new GridData();
		layoutData.verticalIndent = verticalIndent;
		l.setLayoutData( layoutData );

		this.serviceNamespaceCombo = new ComboViewer( new CCombo( container, SWT.BORDER ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = verticalIndent;
		this.serviceNamespaceCombo.getCCombo().setLayoutData( layoutData );
		this.serviceNamespaceCombo.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				String n = ((JbiBasicBean) element).getServiceNs();
				return n != null ? n : "";
			}
		});

		this.serviceNamespaceCombo.setContentProvider( ctProvider );
		this.serviceNamespaceCombo.setInput( input );
		this.serviceNamespaceCombo.getCCombo().addModifyListener( modifyListener );
		this.serviceNamespaceCombo.addPostSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( org.eclipse.jface.viewers.SelectionChangedEvent event ) {
				comboChanged( event );
			}
		});

		this.serviceNamespaceCombo.getCCombo().addKeyListener( defaultNsKeyListener );


		// Add a field "service-name".
		l = new Label( container, SWT.NONE);
		l.setText( "Service Name:" );

		this.serviceNameCombo = new ComboViewer( new CCombo( container, SWT.BORDER ));
		this.serviceNameCombo.getCCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.serviceNameCombo.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				String n = ((JbiBasicBean) element).getServiceName();
				return n != null ? n : "";
			}
		});

		this.serviceNameCombo.setContentProvider( ctProvider );
		this.serviceNameCombo.setInput( input );
		this.serviceNameCombo.getCCombo().addModifyListener( new ModifyListener () {
			public void modifyText( ModifyEvent e ) {

				if( ! AbstractJbiPage.this.semaphore.tryAcquire())
					return;

				try {
					if( !AbstractJbiPage.this.isConsume &&
								( AbstractJbiPage.this.endpointNameText.getText().length() == 0
											|| AbstractJbiPage.this.endpointNameText.getText().endsWith( "Endpoint" ))) { //$NON-NLS-1$

						String servN = AbstractJbiPage.this.serviceNameCombo.getCCombo().getText();
						if( servN != null && servN.trim().length() > 0 )
							AbstractJbiPage.this.endpointNameText.setText( servN + "Endpoint" );
					}

					validate ();
				} catch( Exception e2 ) {
					e2.printStackTrace();
				} finally {
					AbstractJbiPage.this.semaphore.release();
				}
			}
		});

		this.serviceNameCombo.addPostSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( org.eclipse.jface.viewers.SelectionChangedEvent event ) {
				comboChanged( event );
			}
		});


		// Add a field "endpoint-name".
		l = new Label( container, SWT.NONE );
		layoutData = new GridData ();
		layoutData.verticalIndent = verticalIndent;
		l.setLayoutData( layoutData );
		l.setText( "End-point Name:" );

		this.endpointNameText = new Text( container, SWT.SINGLE | SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 20;
		this.endpointNameText.setLayoutData( layoutData );
		this.endpointNameText.addModifyListener( new ModifyListener () {
			public void modifyText( ModifyEvent e ) {
				validate();
			}
		});

		comboChanged( null );
	}


	/**
	 * Called when the input of one of the viewers changes.
	 * @param event
	 */
	protected void comboChanged( SelectionChangedEvent event ) {

		if( ! this.semaphore.tryAcquire())
			return;

		try {
			if( event != null ) {
				ISelection s = event.getSelection();
				if( !s.isEmpty() && s instanceof IStructuredSelection ) {
					JbiBasicBean bean = (JbiBasicBean) ((IStructuredSelection) s).getFirstElement();
					if( bean == this.selectedBean )
						return;

					this.selectedBean = bean;
				}
			}
			else if( this.wsdlBeans.size() > 0 )
				this.selectedBean = this.wsdlBeans.get( 0 );
			else {
				this.selectedBean = null;
				return;
			}


			ISelection s = new StructuredSelection( this.selectedBean );
			this.serviceNameCombo.setSelection( s );
			this.serviceNamespaceCombo.setSelection( s );
			this.interfaceNameCombo.setSelection( s );
			this.interfaceNamespaceCombo.setSelection( s );

			if( this.selectedBean.getEndpointName() != null ) {
				this.endpointNameText.setText( this.selectedBean.getEndpointName());
			}
			else if( this.endpointNameText.getText().length() == 0
						|| this.endpointNameText.getText().endsWith( "Endpoint" )) { //$NON-NLS-1$

				String servN = this.selectedBean.getServiceName();
				if( servN != null && servN.trim().length() > 0 )
					this.endpointNameText.setText( servN + "Endpoint" );
			}

			this.endpointNameText.setSelection( this.endpointNameText.getText().length());

		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			this.semaphore.release();
		}
	}


	/**
	 * Updates the viewers.
	 */
	protected void updateViewers() {

		if( this.serviceNameCombo == null )
			return;

		this.serviceNameCombo.refresh();
		this.serviceNamespaceCombo.refresh();
		this.interfaceNameCombo.refresh();
		this.interfaceNamespaceCombo.refresh();
		comboChanged( null );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		suBean.setEndpointName( this.endpointName );
		suBean.setInterfaceName( this.interfaceName );
		suBean.setInterfaceNamespaceUri( this.interfaceNamespace );
		suBean.setServiceName( this.serviceName );
		suBean.setServiceNamespaceUri( this.serviceNamespace );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {

		if( this.interfaceNameCombo == null )
			return;

		IDialogSettings settings = getWizard().getDialogSettings();

		// Activation
		boolean activate = settings.getBoolean( SettingConstants.ITF_NS_ACTIVATE );
		this.interfaceNamespaceCombo.getCCombo().setEnabled( activate );

		activate = settings.getBoolean( SettingConstants.ITF_NAME_ACTIVATE );
		this.interfaceNameCombo.getCCombo().setEnabled( activate );

		activate = settings.getBoolean( SettingConstants.SRV_NS_ACTIVATE );
		this.serviceNamespaceCombo.getCCombo().setEnabled( activate );

		activate = settings.getBoolean( SettingConstants.SRV_NAME_ACTIVATE );
		this.serviceNameCombo.getCCombo().setEnabled( activate );

		activate = settings.getBoolean( SettingConstants.EDPT_NAME_ACTIVATE );
		this.endpointNameText.setEnabled( activate );

		// Set string values
		String endpoint = settings.get( SettingConstants.EDPT_NAME_VALUE );
		if( StringUtils.areAllEmpty(
					endpoint,
					settings.get( SettingConstants.ITF_NAME_VALUE ),
					settings.get( SettingConstants.ITF_NS_VALUE ),
					settings.get( SettingConstants.SRV_NAME_VALUE ),
					settings.get( SettingConstants.SRV_NS_VALUE )))
			return;

		JbiBasicBean bean = new JbiBasicBean();
		bean.setInterfaceName( settings.get( SettingConstants.ITF_NAME_VALUE ));
		bean.setInterfaceNs( settings.get( SettingConstants.ITF_NS_VALUE ));
		bean.setServiceName( settings.get( SettingConstants.SRV_NAME_VALUE ));
		bean.setServiceNs( settings.get( SettingConstants.SRV_NS_VALUE ));

		this.wsdlBeans = new ArrayList<JbiBasicBean>( 1 );
		this.wsdlBeans.add( bean );
		updateViewers();

		this.endpointNameText.setText( endpoint );

		String errorMsg = getErrorMessage();
		if( errorMsg != null ) {
			setMessage( errorMsg, IMessageProvider.INFORMATION );
			setErrorMessage( null );
		}
	}
}
