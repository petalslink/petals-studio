/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.xslt.wizard;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.ebmwebsourcing.petals.services.xslt.XsltDescription25;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltService25;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM Websourcing
 */
public class XsltWizard25 extends XsltWizard23 {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription25();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getLastCustomWizardPages()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] {
				new XsltProvideSpecificPage(),
				new SimpleFeatureListSuWizardPage(
					XsltPackage.Literals.XSLT_PROVIDES__XSLT_ENGINE_FACTORY_CLASS_NAME,
					XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MIN,
					XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MAX)
		};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.xslt.wizard.XsltWizard23
	 * #getWsdlContent(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected String getWsdlContent( AbstractEndpoint abstractEndpoint ) {
		return new XsltService25().generate( abstractEndpoint );
	}
}
