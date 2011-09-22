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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.utils.XsdUtils;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage;

/**
 * Component-specific page for Binding Components in "provide".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BcProvideSpecificPage extends XsdBasedAbstractSuPage {

	/**
	 * Constructor.
	 * @param suType the SU type.
	 * @param suTypeVersion the version for this SU type.
	 * @param pluginId the contributor plug-in ID
	 */
	public BcProvideSpecificPage( String suType, String suTypeVersion, String pluginId ) {
		super( SuMainConstants.PAGE_SPECIFIC_JBI_DATA, suType, suTypeVersion );
		this.pluginId = pluginId;

		setDescription( "Define the specific properties for this version of the component." );
		if( suType != null )
			setBasicFields( suType, suTypeVersion, pluginId );
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
					PetalsServicesPlugin.PLUGIN_ID + ".provideSpecificPageId" ); //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		transformStructureForGeneration( this.getValues());
		List<XmlElement> specificsElements = XsdUtils.createXmlStructure(
					this.getValues(),
					FileImportManager.getFileImportManager());
		suBean.specificElements.addAll( specificsElements );
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

		// Update the SOAP fields - not a generic mechanism
		String soapAddress = getDialogSettings().get( SettingConstants.SOAP_ADDRESS_VALUE );
		if( soapAddress != null ) {
			HciItem item = new HciItem();
			item.setXsdName( "soap:address" );
			wc.itemValues.put( item, soapAddress );
		}

		String soapVersion = getDialogSettings().get( SettingConstants.SOAP_VERSION_VALUE );
		if( soapVersion != null ) {
			HciItem item = new HciItem();
			item.setXsdName( "soap:soap-version" );
			wc.itemValues.put( item, soapVersion );
		}

		if( wc.itemValues != null && ! wc.itemValues.isEmpty())
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

		this.xsdBasedWidgets = initializeSpecificWidgets( suType, suTypeVersion, pluginId, false, this.useCache );
		WizardConfiguration wc =
			RegisteredContributors.getInstance().getWizardConfigurationClass( suType, suTypeVersion, false );

		redefineItems( wc, this.xsdBasedWidgets );
		this.hasSomethingToDisplay = areValidItems( this.xsdBasedWidgets );
	}
}
