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

package com.ebmwebsourcing.petals.services.su.wizards.pages.provide.bc;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlAttribute;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.utils.XsdUtils;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.AbstractWizardListener;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.IntegerSpinner;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.KeyValueArrayDialog;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.KeyValueArrayDialog.KeyValue;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.LongSpinner;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
import com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage;

/**
 * CDK page for Binding Components in "provide".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BcProvideCdkPage extends XsdBasedAbstractSuPage  {

	private String exchangeProperties, messageProperties;


	/**
	 * Constructor.
	 * @param suType the SU type.
	 * @param suTypeVersion the version for this SU type.
	 */
	public BcProvideCdkPage( String suType, String suTypeVersion ) {
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
					PetalsServicesPlugin.PLUGIN_ID + ".provideCdkPageId" ); //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		// Default processing
		transformStructureForGeneration( this.getValues());
		List<XmlElement> cdkElements = XsdUtils.createXmlStructure(
					this.getValues(),
					FileImportManager.getFileImportManager());
		suBean.cdkElements.addAll( cdkElements );

		// Add CDK 5 parameters
		if( this.delaySpinner != null ) {

			//
			// Retry-policy
			XmlElement retryElt = new XmlElement();
			retryElt.setName( "petalsCDK:retrypolicy" );
			retryElt.setNillable( false );
			retryElt.setOptional( true );

			if( this.retryPolicyActivated ) {
				XmlElement attempsElt = new XmlElement();
				attempsElt.setName( "petalsCDK:attempts" );
				attempsElt.setNillable( false );
				attempsElt.setOptional( false );
				attempsElt.setValue( this.attempsSpinner.getValue().toString());

				retryElt.childrenElements.add( attempsElt );

				if( this.delaySpinner.getValue() != -1 ) {
					XmlElement delayElt = new XmlElement();
					delayElt.setName( "petalsCDK:delay" );
					delayElt.setNillable( false );
					delayElt.setOptional( true );
					delayElt.setValue( this.delaySpinner.getValue().toString());

					retryElt.childrenElements.add( delayElt );
				}
			}

			// Add the element right after the 'timeout'
			int pos = 0;
			for( XmlElement elt : suBean.cdkElements ) {
				if( "petalsCDK:timeout".equals( elt.getName()))
					break;
				pos ++;
			}

			pos ++;
			if( pos > suBean.cdkElements.size())
				pos = 0;

			suBean.cdkElements.add( pos, retryElt );


			//
			// Exchange-properties
			List<KeyValue> keyValues = KeyValueArrayDialog.parseValue( this.exchangeProperties );
			if( keyValues.size() > 0 ) {
				XmlElement exElt = new XmlElement();
				exElt.setName( "petalsCDK:exchange-properties" );
				exElt.setNillable( false );

				for( KeyValue kv : keyValues ) {
					XmlElement exPropertyElt = new XmlElement();
					exPropertyElt.setName( "petalsCDK:exchange-property" );
					exPropertyElt.setNillable( false );
					exPropertyElt.setValue( kv.getValue());
					exElt.childrenElements.add( exPropertyElt );

					XmlAttribute nameAttr = new XmlAttribute();
					nameAttr.setName( "name" );
					nameAttr.setValue( kv.getKey());
					exPropertyElt.attributes.add( nameAttr );
				}

				// Add the element right after the 'retrypolicy'
				suBean.cdkElements.add( ++ pos, exElt );
			}


			//
			// Message-properties
			keyValues = KeyValueArrayDialog.parseValue( this.messageProperties );
			if( keyValues.size() > 0 ) {
				XmlElement exElt = new XmlElement();
				exElt.setName( "petalsCDK:message-properties" );
				exElt.setNillable( false );

				for( KeyValue kv : keyValues ) {
					XmlElement msgPropertyElt = new XmlElement();
					msgPropertyElt.setName( "petalsCDK:message-property" );
					msgPropertyElt.setNillable( false );
					msgPropertyElt.setValue( "<!-- To complete -->" );
					exElt.childrenElements.add( msgPropertyElt );

					XmlAttribute nameAttr = new XmlAttribute();
					nameAttr.setName( "name" );
					nameAttr.setValue( kv.getKey());
					msgPropertyElt.attributes.add( nameAttr );

					XmlAttribute valueAttr = new XmlAttribute();
					valueAttr.setName( "message" );
					valueAttr.setValue( kv.getValue());
					msgPropertyElt.attributes.add( valueAttr );
				}

				// Add the element right after the 'retrypolicy'
				suBean.cdkElements.add( ++ pos, exElt );
			}
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
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setBasicFields(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {
		super.setBasicFields( suType, suTypeVersion, pluginId );

		this.xsdBasedWidgets = initializeCdkWidgets( suType, suTypeVersion, false, this.useCache );
		WizardConfiguration wc =
			RegisteredContributors.getInstance().getWizardConfigurationClass( suType, suTypeVersion, false );

		redefineItems( wc, this.xsdBasedWidgets );
		this.hasSomethingToDisplay = areValidItems( this.xsdBasedWidgets );
	}


	/**
	 * Additional elements for the CDK 5.
	 * No need to complete the framework, it is planned to drop these plug-ins soon.
	 */
	private IntegerSpinner attempsSpinner;
	private LongSpinner delaySpinner;
	private boolean retryPolicyActivated;

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addControlsAfterXsd( Composite parent ) {

		if( ! XsdNamespaceStore.getNamespaceStore().getNamespaces().containsValue(
		"http://petals.ow2.org/components/extensions/version-5" ))
			return;

		//
		// Exchange and message properties
		addPropertiesWidgets( parent );

		//
		// RETRY-policy
		final Button activateRetryPolicyButton = new Button( parent, SWT.CHECK );
		activateRetryPolicyButton.setText( "Activate retry policy" );
		activateRetryPolicyButton.setSelection( false );

		GridData gd = new GridData();
		gd.verticalIndent = 5;
		gd.horizontalSpan = 2;
		activateRetryPolicyButton.setLayoutData( gd );


		// Attempts spinner
		this.attempsSpinner = new IntegerSpinner( 1, parent, "attempts number:", "", "The number of attempts", false );
		this.attempsSpinner.addListener( new AbstractWizardListener () {
			public void valueHasChanged() {
				validate();
			}
		});

		// Delay spinner
		this.delaySpinner = new LongSpinner( Long.valueOf( 30000 ), parent, "Delay:", "", "The delay between every retry (ms)", false );
		this.delaySpinner.addListener( new AbstractWizardListener () {
			public void valueHasChanged() {
				validate();
			}
		});

		// Listener
		activateRetryPolicyButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				BcProvideCdkPage.this.retryPolicyActivated = activateRetryPolicyButton.getSelection();
				BcProvideCdkPage.this.attempsSpinner.setEnabled( BcProvideCdkPage.this.retryPolicyActivated );
				BcProvideCdkPage.this.delaySpinner.setEnabled( BcProvideCdkPage.this.retryPolicyActivated );
			}
		});

		activateRetryPolicyButton.notifyListeners( SWT.Selection, new Event());
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
				BcProvideCdkPage.this.exchangeProperties = exText.getText();
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
							BcProvideCdkPage.this.exchangeProperties,
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
				BcProvideCdkPage.this.messageProperties = msgText.getText();
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
							BcProvideCdkPage.this.messageProperties,
							false, null, "Name",
							true, new String[] { "in", "out", "fault" }, "Value" );

				if( dlg.open() == Window.OK )
					msgText.setText( dlg.getValue());
			}
		});
	}
}
