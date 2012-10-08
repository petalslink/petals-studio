/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

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
