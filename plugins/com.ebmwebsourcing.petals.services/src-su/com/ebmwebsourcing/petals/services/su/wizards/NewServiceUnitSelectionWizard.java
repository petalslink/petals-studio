/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ChoicePage;

/**
 * @author Micka�l Istria - EBM WebSourcing
 */
public class NewServiceUnitSelectionWizard extends Wizard implements INewWizard, IExecutableExtension {

	private PetalsMode petalsMode;
	private FinishServiceCreationStrategy strategy;


	/**
	 * Constructor.
	 */
	public NewServiceUnitSelectionWizard() {
		setForcePreviousAndNextButtons( true );
	}


	/**
	 * Constructor.
	 * @param mode
	 * @param strategy
	 */
	public NewServiceUnitSelectionWizard(PetalsMode mode, FinishServiceCreationStrategy strategy) {
		this();
		this.strategy = strategy;
		this.petalsMode = mode;
		init();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension
	 * #setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setInitializationData( IConfigurationElement config, String propertyName, Object data ) throws CoreException {
		this.petalsMode = "provides".equalsIgnoreCase( String.valueOf( data )) ? PetalsMode.provides : PetalsMode.consumes;
		this.strategy = new CreateJBIStrategy();
		init();
	}


	/**
	 * Initializes some data.
	 */
	public void init() {
		if (this.petalsMode == PetalsMode.provides) {
			setWindowTitle(Messages.provideTitle);
		} else {
			setWindowTitle(Messages.consumeTitle);
		}
	}


	/**
	 * Adds the first page of the wizard.
	 */
	@Override
	public void addPages() {
		addPage( new ChoicePage( this.petalsMode, this.strategy ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {
		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #canFinish()
	 */
	@Override
	public boolean canFinish() {
		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// nothing
	}
}
