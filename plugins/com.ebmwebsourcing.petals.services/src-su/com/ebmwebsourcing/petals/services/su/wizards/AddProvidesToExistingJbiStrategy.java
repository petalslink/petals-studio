package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;

public class AddProvidesToExistingJbiStrategy implements FinishServiceCreationStrategy {

	private Jbi jbi;
	private EditingDomain domain;
	private IProject project;
	
	public AddProvidesToExistingJbiStrategy(Jbi jbi, EditingDomain domain, IProject enclosingProject) {
		this.jbi = jbi;
		this.domain = domain;
		this.project = enclosingProject;
	}
	
	@Override
	public void finishWizard(ComponentCreationWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) {
		domain.getCommandStack().execute(new AddCommand(domain, jbi.getServices().getProvides(), endpoint));
	}

	@Override
	public IProject getSUProject(ComponentCreationWizard wizard, IProgressMonitor monitor) {
		return this.project;
	}

}
