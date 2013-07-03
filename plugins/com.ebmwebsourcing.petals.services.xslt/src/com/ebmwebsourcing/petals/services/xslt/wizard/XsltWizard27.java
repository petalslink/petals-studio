/****************************************************************************
 *
 * Copyright (c) 2012-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.xslt.wizard;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.xslt.XsltDescription27;

/**
 * @author Vincent Zurczak - Linagora
 */
public class XsltWizard27 extends XsltWizard25 {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription27();
	}
}
