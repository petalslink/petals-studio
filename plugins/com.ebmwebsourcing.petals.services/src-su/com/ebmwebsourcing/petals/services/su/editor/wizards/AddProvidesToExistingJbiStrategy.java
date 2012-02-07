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

package com.ebmwebsourcing.petals.services.su.editor.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class AddProvidesToExistingJbiStrategy implements FinishServiceCreationStrategy {

	private final Jbi jbi;
	private final EditingDomain domain;
	private final IProject project;


	/**
	 * Constructor.
	 * @param jbi
	 * @param domain
	 * @param enclosingProject
	 */
	public AddProvidesToExistingJbiStrategy(Jbi jbi, EditingDomain domain, IProject enclosingProject) {
		this.jbi = jbi;
		this.domain = domain;
		this.project = enclosingProject;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy
	 * #finishWizard(com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard,
	 * com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void finishWizard(AbstractServiceUnitWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) {
		this.domain.getCommandStack().execute(new AddCommand(this.domain, this.jbi.getServices().getProvides(), endpoint));
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy
	 * #getSUProject(com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IProject getSUProject(AbstractServiceUnitWizard wizard, IProgressMonitor monitor) {
		return this.project;
	}
}
