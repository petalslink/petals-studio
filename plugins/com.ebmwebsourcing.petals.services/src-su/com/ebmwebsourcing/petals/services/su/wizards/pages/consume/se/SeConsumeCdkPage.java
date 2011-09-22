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

package com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc.BcConsumeCdkPage;

/**
 * CDK page for Service Engines.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SeConsumeCdkPage extends BcConsumeCdkPage  {

	/**
	 * Constructor.
	 * @param suType the SU type.
	 * @param suTypeVersion the version for this SU type.
	 */
	public SeConsumeCdkPage( String suType, String suTypeVersion ) {
		super( suType, suTypeVersion );

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
					PetalsServicesPlugin.PLUGIN_ID + ".seCdkPageId" ); //$NON-NLS-1$
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
}
