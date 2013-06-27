/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions.generic;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class GenericComponentWizard extends AbstractServiceUnitWizard {

	private final GenericComponentDescription genDesc = new GenericComponentDescription();


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesBeforeJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeJbi() {
		return new AbstractSuWizardPage[] { new GenericSuWizardPage()};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return this.genDesc;
	}


	@Override
	public AbstractModel getCdkModel() {
		return null;
	}


	@Override
	public AbstractModel getComponentModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
