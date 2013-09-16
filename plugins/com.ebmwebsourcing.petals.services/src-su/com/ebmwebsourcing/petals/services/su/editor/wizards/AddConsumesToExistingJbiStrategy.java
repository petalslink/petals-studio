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
 * @author Mickael Istria - EBM WebSourcing
 */
public class AddConsumesToExistingJbiStrategy implements FinishServiceCreationStrategy {

	private final Jbi jbi;
	private final EditingDomain domain;
	private final IProject project;


	/**
	 * Constructor.
	 * @param jbi
	 * @param domain
	 * @param enclosingProject
	 */
	public AddConsumesToExistingJbiStrategy(Jbi jbi, EditingDomain domain, IProject enclosingProject) {
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
		this.domain.getCommandStack().execute(new AddCommand(this.domain, this.jbi.getServices().getConsumes(), endpoint));
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
