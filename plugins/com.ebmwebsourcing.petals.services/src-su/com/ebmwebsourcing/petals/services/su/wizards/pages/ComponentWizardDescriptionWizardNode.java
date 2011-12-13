package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

public class ComponentWizardDescriptionWizardNode implements IWizardNode {

	private ComponentWizardHandler handler;
	private PetalsMode petalsMode;
	private ComponentCreationWizard wizard;

	public ComponentWizardDescriptionWizardNode(ComponentWizardHandler handler, PetalsMode petalsMode) {
		this.handler = handler;
		this.petalsMode = petalsMode;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Point getExtent() {
		return null;
	}

	@Override
	public IWizard getWizard() {
		if (wizard == null) {
			wizard = new ComponentCreationWizard(handler, petalsMode);
		}
		return wizard;
	}

	@Override
	public boolean isContentCreated() {
		// TODO Auto-generated method stub
		return false;
	}

}
