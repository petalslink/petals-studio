/****************************************************************************
 * 
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.xslt.wizard;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.ebmwebsourcing.petals.services.xslt.XsltDescription25;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class XsltWizard25 extends XsltWizard23 {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription25();
	}
	
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] {
				new XsltProvideSpecificPage(),
				new SimpleFeatureListSuWizardPage(
					XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY,
					XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MIN,
					XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MAX)
		};
	}
}
