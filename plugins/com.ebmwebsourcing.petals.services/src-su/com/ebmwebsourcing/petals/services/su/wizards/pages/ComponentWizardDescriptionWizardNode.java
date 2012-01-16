package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;

public class ComponentWizardDescriptionWizardNode implements IWizardNode {

	private AbstractServiceUnitWizard wizard;

	public ComponentWizardDescriptionWizardNode(AbstractServiceUnitWizard wizard) {
		this.wizard = wizard;
	}

	@Override
	public void dispose() {
	}

	@Override
	public Point getExtent() {
		return null;
	}

	@Override
	public IWizard getWizard() {
		return wizard;
	}

	@Override
	public boolean isContentCreated() {
		return false;
	}

}
