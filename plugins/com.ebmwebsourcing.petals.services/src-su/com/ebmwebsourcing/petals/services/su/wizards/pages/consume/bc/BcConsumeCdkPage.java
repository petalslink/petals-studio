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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlAttribute;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.ui.OperationLabelProvider;
import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.utils.XsdUtils;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.KeyValueArrayDialog;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.KeyValueArrayDialog.KeyValue;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
import com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * CDK page for Binding Components in "consume".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BcConsumeCdkPage extends XsdBasedAbstractSuPage {

	// Fields to be used during the final generation
	private String opName, opNs;
	private Mep mep = Mep.UNKNOWN;
	private String exchangeProperties, messageProperties;

	// Fields to help in the UI
	private boolean operationUIinitialized = false;

	/**
	 * The URI of the WSDL that was set in the wizard.
	 */
	private URI wsdlUri;

	/**
	 * UNKNOWN: the user can use any MEP. Otherwise, only operations with this MEP can be used.
	 */
	private Mep filterMep;

	// UI
	private Composite operationParent;
	private Control refreshLimiter;


	/**
	 * Constructor.
	 * @param suType the SU type.
	 * @param suTypeVersion the version for this SU type.
	 */
	public BcConsumeCdkPage( String suType, String suTypeVersion ) {
		super( SuMainConstants.PAGE_CDK_SPECIFIC_DATA, suType, suTypeVersion );

		setDescription( "Define the CDK properties." );
		if( suType != null )
			setBasicFields( suType, suTypeVersion, this.pluginId );
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
					PetalsServicesPlugin.PLUGIN_ID + ".consumeCdkPageId" ); //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		setMessage( null, IMessageProvider.WARNING );

		// Hack for the SOAP BC: do not validate the operation and the MEP
		String msg = null;
		if( ! isSoap()) {
			if( StringUtils.isEmpty( this.opNs )
						&& StringUtils.isEmpty( this.opName )) {

				msg = "You should set an operation to invoke";
				if( this.filterMep != Mep.UNKNOWN )
					msg += " (only operations using the " + this.filterMep + " MEP are supported).";
				else
					msg += ".";
			}

			else if( StringUtils.isEmpty( this.opNs ) != StringUtils.isEmpty( this.opName ))
				msg = "A service operation must be a qualified name (namespace URI + name).";
			else if( this.mep == Mep.UNKNOWN )
				msg = "You have to select a Message Exchange Pattern.";
		}

		// Other validation
		boolean result = super.validate();
		if( result ) {
			setMessage( msg, IMessageProvider.WARNING );

			// MEP
			if( msg == null && this.filterMep != Mep.UNKNOWN )
				setMessage( "This component only supports the " + this.filterMep + " MEP.", IMessageProvider.WARNING );
		}

		return result;
	}


	/**
	 * @return true if this page is used in a SOAP wizard
	 */
	private boolean isSoap() {
		return "soap".equalsIgnoreCase( this.suType ) || "rest".equalsIgnoreCase( this.suType );
	}


	/**
	 * @return true if this page is used in a wizard for a 4.x version
	 * <p>
	 * Does only make sense for the version 4.x of the SOAP wizards.
	 * </p>
	 */
	private boolean isVersion4x() {
		return this.suTypeVersion != null && this.suTypeVersion.startsWith( "4." );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #setWizard(org.eclipse.jface.wizard.IWizard)
	 */
	@Override
	public void setWizard( IWizard newWizard ) {
		super.setWizard( newWizard );
		String mepAsString = getDialogSettings().get( SettingConstants.SUPPORTED_MEP );
		this.filterMep = Mep.whichMep( mepAsString );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		transformStructureForGeneration( this.getValues());
		List<XmlElement> cdkElements = XsdUtils.createXmlStructure(
					this.getValues(),
					FileImportManager.getFileImportManager());
		suBean.cdkElements.addAll( cdkElements );

		// Add the elements right after the 'timeout'
		int pos = 0;
		for( XmlElement elt : suBean.cdkElements ) {
			if( "petalsCDK:timeout".equals( elt.getName()))
				break;
			pos ++;
		}

		if( pos > suBean.cdkElements.size())
			pos = suBean.cdkElements.size();


		// Operation
		if( ! StringUtils.isEmpty( this.opName ) && ! StringUtils.isEmpty( this.opNs )) {
			XmlElement elt = new XmlElement();
			elt.setName( "petalsCDK:operation" );
			elt.setNillable( false );
			elt.setOptional( false );
			elt.setValue( "{" + this.opNs + "}" + this.opName );
			suBean.cdkElements.add( ++ pos, elt );
		}


		// Exchange-properties
		List<KeyValue> keyValues = KeyValueArrayDialog.parseValue( this.exchangeProperties );
		if( keyValues.size() > 0 ) {
			XmlElement elt = new XmlElement();
			elt.setName( "petalsCDK:exchange-properties" );
			elt.setNillable( false );

			for( KeyValue kv : keyValues ) {
				XmlElement exPropertyElt = new XmlElement();
				exPropertyElt.setName( "petalsCDK:exchange-property" );
				exPropertyElt.setNillable( false );
				exPropertyElt.setValue( kv.getValue());
				elt.childrenElements.add( exPropertyElt );

				XmlAttribute nameAttr = new XmlAttribute();
				nameAttr.setName( "name" );
				nameAttr.setValue( kv.getKey());
				exPropertyElt.attributes.add( nameAttr );
			}

			suBean.cdkElements.add( ++ pos, elt );
		}


		// Message-properties
		keyValues = KeyValueArrayDialog.parseValue( this.messageProperties );
		if( keyValues.size() > 0 ) {
			XmlElement elt = new XmlElement();
			elt.setName( "petalsCDK:message-properties" );
			elt.setNillable( false );

			for( KeyValue kv : keyValues ) {
				XmlElement msgPropertyElt = new XmlElement();
				msgPropertyElt.setName( "petalsCDK:message-property" );
				msgPropertyElt.setNillable( false );
				msgPropertyElt.setValue( "<!-- To complete -->" );
				elt.childrenElements.add( msgPropertyElt );

				XmlAttribute nameAttr = new XmlAttribute();
				nameAttr.setName( "name" );
				nameAttr.setValue( kv.getKey());
				msgPropertyElt.attributes.add( nameAttr );

				XmlAttribute valueAttr = new XmlAttribute();
				valueAttr.setName( "message" );
				valueAttr.setValue( kv.getValue());
				msgPropertyElt.attributes.add( valueAttr );
			}

			suBean.cdkElements.add( ++ pos, elt );
		}


		// MEP
		XmlElement elt = new XmlElement();
		elt.setName( "petalsCDK:mep" );
		XmlElement mepElement = null;
		for( XmlElement runElt : suBean.cdkElements ) {
			if( runElt.getName().equals( elt.getName())) {
				mepElement = runElt;
				break;
			}
		}

		if( mepElement == null ) {
			elt.setNillable( false );
			elt.setOptional( false );
			elt.setValue( this.mep == Mep.UNKNOWN ? null : this.mep.toString());
			suBean.cdkElements.add( ++ pos, elt );

		} else {
			mepElement.setValue( this.mep == Mep.UNKNOWN ? null : this.mep.toString());
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration () {

		// If the page controls have not already been created
		if( this.widgetGenerator == null )
			return;

		WizardConfiguration wc = ((PetalsSuNewWizard) getWizard()).getWizardConfiguration();
		if( wc.itemValues != null && !wc.itemValues.isEmpty())
			this.widgetGenerator.setValuesInWidgets( wc.itemValues );

		// Get the WSDL operations, but only if the WSDL URI changed
		URI newWsdlUri = null;
		String uriAsString = getDialogSettings().get( SettingConstants.CONSUMED_WSDL_URI );
		if( uriAsString != null && ! "null".equalsIgnoreCase( uriAsString ))
			newWsdlUri = UriUtils.urlToUri( uriAsString );

		if( isSoap()) {
			if( isVersion4x())
				addManualMepWidgets( true );

		} else {
			addOperationWidgets( newWsdlUri );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setBasicFields(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {
		super.setBasicFields( suType, suTypeVersion, pluginId );

		this.xsdBasedWidgets = initializeCdkWidgets( suType, suTypeVersion, true, this.useCache );
		WizardConfiguration wc =
			RegisteredContributors.getInstance().getWizardConfigurationClass( suType, suTypeVersion, true );

		redefineItems( wc, this.xsdBasedWidgets );
		this.hasSomethingToDisplay = areValidItems( this.xsdBasedWidgets );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addControlsAfterXsd( Composite parent ) {
		addPropertiesWidgets( parent );
		this.operationParent = parent;
	}


	/**
	 * Adds the widgets to add properties in the SU.
	 * @param parent the parent
	 */
	private void addPropertiesWidgets( Composite parent ) {

		if( ! XsdNamespaceStore.getNamespaceStore().getNamespaces().containsValue(
		"http://petals.ow2.org/components/extensions/version-5" ))
			return;

		//
		// EXCHANGE-properties
		new Label( parent, SWT.NONE ).setText( "Exchange Properties:" );

		Composite browserComposite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		browserComposite.setLayout( layout );
		browserComposite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text exText = new Text( browserComposite, SWT.SINGLE | SWT.BORDER );
		exText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		exText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				BcConsumeCdkPage.this.exchangeProperties = exText.getText();
				validate();
			}
		});

		Button button = new Button( browserComposite, SWT.PUSH );
		button.setText( "Edit..." );
		button.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				KeyValueArrayDialog dlg = new KeyValueArrayDialog(
							getShell(),
							BcConsumeCdkPage.this.exchangeProperties,
							false, null, "Name",
							false, null, "Value" );

				if( dlg.open() == Window.OK )
					exText.setText( dlg.getValue());
			}
		});


		//
		// MESSAGE-properties
		new Label( parent, SWT.NONE ).setText( "Message Properties:" );

		browserComposite = new Composite( parent, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		browserComposite.setLayout( layout );
		browserComposite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text msgText = new Text( browserComposite, SWT.SINGLE | SWT.BORDER );
		msgText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		msgText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				BcConsumeCdkPage.this.messageProperties = msgText.getText();
				validate();
			}
		});

		button = new Button( browserComposite, SWT.PUSH );
		button.setText( "Edit..." );
		button.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				KeyValueArrayDialog dlg = new KeyValueArrayDialog(
							getShell(),
							BcConsumeCdkPage.this.messageProperties,
							false, null, "Name",
							true, new String[] { "in", "out", "fault" }, "Value" );

				if( dlg.open() == Window.OK )
					msgText.setText( dlg.getValue());
			}
		});
	}


	/**
	 * Adds the appropriate widgets for the operation and the MEP.
	 * @param wsdlUri the WSDL URI that was set in the wizard previously
	 */
	private void addOperationWidgets( URI wsdlUri ) {

		// No operation to display? Do nothing...
		if( this.operationParent == null )
			return;

		// No change? Do nothing...
		if( this.operationUIinitialized &&
					( wsdlUri == this.wsdlUri || wsdlUri != null && wsdlUri.equals( this.wsdlUri )))
			return;

		// From here, we know a change occurred.
		// Either another WSDL was set in the wizard, or...
		// ... a WSDL was set whereas there was not one before, or...
		// ... a WSDL was removed whereas there was one before.
		this.wsdlUri = wsdlUri;
		this.operationUIinitialized = true;

		// Remove previous widgets
		boolean found = false;
		for( Control c : this.operationParent.getChildren()) {
			found = found || c.equals( this.refreshLimiter );
			if( found )
				c.dispose();
		}

		// Get the list of operations
		final Map<QName,Mep> _opNameToMep = ConsumeUtils.getOperations(
					wsdlUri,
					getDialogSettings().get( SettingConstants.ITF_NAME_VALUE ),
					getDialogSettings().get( SettingConstants.ITF_NS_VALUE ),
					getDialogSettings().get( SettingConstants.SRV_NAME_VALUE ),
					getDialogSettings().get( SettingConstants.SRV_NS_VALUE ),
					getDialogSettings().get( SettingConstants.EDPT_NAME_VALUE ),
					getDialogSettings().get( SettingConstants.CONSUMED_COMPONENT_NAME ));

		final Map<QName,Mep> opNameToMep = _opNameToMep.isEmpty() ? null : _opNameToMep;

		// No operation: display the fields for manual edition
		if( opNameToMep == null ) {

			// The operation name space
			Label l = new Label( this.operationParent, SWT.NONE );
			l.setText( "Operation namespace:" );
			l.setToolTipText( "The namespace of the operation to invoke" );
			this.refreshLimiter = l;

			Text text = new Text( this.operationParent, SWT.SINGLE | SWT.BORDER );
			text.setText( this.opNs != null ? this.opNs : "" );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					BcConsumeCdkPage.this.opNs = ((Text) e.widget).getText();
					validate();
				}
			});

			// The operation name
			l = new Label( this.operationParent, SWT.NONE );
			l.setText( "Operation name:" );
			l.setToolTipText( "The name of the operation to invoke" );

			text = new Text( this.operationParent, SWT.SINGLE | SWT.BORDER );
			text.setText( this.opName != null ? this.opName : "" );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					BcConsumeCdkPage.this.opName = ((Text) e.widget).getText();
					validate();
				}
			});

			// The MEP
			addManualMepWidgets( false );
		}

		// There is an URI: parse it and update the widgets
		else {
			Label l = new Label( this.operationParent, SWT.NONE );
			l.setText( "Operation to Invoke:" );
			l.setToolTipText( "The operation to invoke" );
			this.refreshLimiter = l;

			// Viewer
			final ComboViewer operationViewer = new ComboViewer( this.operationParent, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER );
			operationViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			operationViewer.setContentProvider( new ArrayContentProvider());
			operationViewer.setLabelProvider( new OperationLabelProvider());

			if( this.filterMep == Mep.UNKNOWN ) {
				operationViewer.setInput( opNameToMep.keySet());

			} else {
				Map<QName,Mep> subSet = new HashMap<QName,Mep> ();
				for( Map.Entry<QName,Mep> entry : opNameToMep.entrySet()) {
					if( entry.getValue() == this.filterMep )
						subSet.put( entry.getKey(), this.filterMep );
				}

				operationViewer.setInput( subSet.keySet());
			}

			// The listener
			operationViewer.addSelectionChangedListener( new ISelectionChangedListener() {
				public void selectionChanged( SelectionChangedEvent event ) {

					if( ! operationViewer.getSelection().isEmpty()) {
						Object o = ((IStructuredSelection) operationViewer.getSelection()).getFirstElement();
						BcConsumeCdkPage.this.opName = ((QName) o).getLocalPart();
						BcConsumeCdkPage.this.opNs = ((QName) o).getNamespaceURI();
						BcConsumeCdkPage.this.mep = opNameToMep.get( o );
					} else {
						BcConsumeCdkPage.this.opName = null;
						BcConsumeCdkPage.this.opNs = null;
						BcConsumeCdkPage.this.mep = Mep.UNKNOWN;
					}

					validate();
				}
			});

			// Initial selection
			operationViewer.getCombo().setVisibleItemCount( opNameToMep.size());
			if( this.opName != null
						&& this.opNs != null
						&& opNameToMep.get( new QName( this.opNs, this.opName )) != null )
				operationViewer.setSelection( new StructuredSelection( new QName( this.opNs, this.opName )));
			else
				operationViewer.getCombo().select( 0 );
			operationViewer.getCombo().notifyListeners( SWT.Selection, new Event());
		}

		this.operationParent.layout();
	}


	/**
	 * Adds the appropriate widget to select a MEP.
	 * @param clearParent true to erase other children in the parent, false to keep them
	 */
	private void addManualMepWidgets( boolean clearParent ) {

		// Clear the parent?
		if( clearParent ) {
			boolean found = false;
			for( Control c : this.operationParent.getChildren()) {
				found = found || c.equals( this.refreshLimiter );
				if( found )
					c.dispose();
			}
		}

		// Add the combo for the MEP
		Label l = new Label( this.operationParent, SWT.NONE );
		l.setText( "Invocation MEP:" );
		l.setToolTipText( "The Message Exchange Pattern" );

		ComboViewer mepViewer = new ComboViewer( this.operationParent, SWT.READ_ONLY | SWT.BORDER | SWT.DROP_DOWN );
		mepViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		mepViewer.setContentProvider( new ArrayContentProvider());
		mepViewer.setLabelProvider( new LabelProvider());

		if( this.filterMep == Mep.UNKNOWN ) {
			mepViewer.setInput( Mep.values());
		} else {
			mepViewer.setInput( Arrays.asList( this.filterMep ));
			this.mep = this.filterMep;
		}

		mepViewer.setSelection( new StructuredSelection( this.mep ));
		mepViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				BcConsumeCdkPage.this.mep = (Mep) o;
				validate();
			}
		});
	}
}
