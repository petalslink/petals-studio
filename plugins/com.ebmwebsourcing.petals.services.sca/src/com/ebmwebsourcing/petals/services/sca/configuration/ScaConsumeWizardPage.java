/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.configuration;

import org.eclipse.jface.wizard.IWizardPage;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SeveralConsumeWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaConsumeWizardPage extends SeveralConsumeWizardPage {

	/**
	 * Constructor.
	 */
	public ScaConsumeWizardPage() {
		super( "SeveralConsumePages", "SCA", "1.0" );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		suBean.customObjects.put( "Endpoints", getEndpointBeans());
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #displayPage()
	 */
	@Override
	public boolean displayPage() {

		boolean createComposition = false;
		IWizardPage page = getWizard().getPage( ScaWizardPage11.PAGE_NAME );
		if( page instanceof ScaWizardPage11 )
			createComposition = ((ScaWizardPage11) page).mustCreateComposition();

		return createComposition;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.SeveralConsumeWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		boolean result = true;
		if(( result = super.validate()) == true ) {
			for( EndpointBean bean : this.edptBeans ) {
				if( bean.getWsdlLocation() == null ) {
					updateStatus( bean.getServiceName().getLocalPart() + " does not have a WSDL." );
					result = false;
					break;
				}
			}
		}

		return result;
	}
}
