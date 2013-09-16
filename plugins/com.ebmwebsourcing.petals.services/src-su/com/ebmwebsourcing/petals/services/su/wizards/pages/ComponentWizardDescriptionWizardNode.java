/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class ComponentWizardDescriptionWizardNode implements IWizardNode {

	private final AbstractServiceUnitWizard wizard;

	public ComponentWizardDescriptionWizardNode(AbstractServiceUnitWizard wizard) {
		this.wizard = wizard;
	}

	@Override
	public void dispose() {
		// nothing
	}

	@Override
	public Point getExtent() {
		return null;
	}

	@Override
	public IWizard getWizard() {
		return this.wizard;
	}

	@Override
	public boolean isContentCreated() {
		return false;
	}
}
