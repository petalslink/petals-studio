package com.ebmwebsourcing.petals.services.su.editor.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;

public class AddConsumesToExistingJbiStrategy implements FinishServiceCreationStrategy {

	private Jbi jbi;
	private EditingDomain domain;
	private IProject project;
	
	public AddConsumesToExistingJbiStrategy(Jbi jbi, EditingDomain domain, IProject enclosingProject) {
		this.jbi = jbi;
		this.domain = domain;
		this.project = enclosingProject;
	}
	
	@Override
	public void finishWizard(AbstractServiceUnitWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) {
		domain.getCommandStack().execute(new AddCommand(domain, jbi.getServices().getConsumes(), endpoint));
	}

	@Override
	public IProject getSUProject(AbstractServiceUnitWizard wizard, IProgressMonitor monitor) {
		return this.project;
	}

}
