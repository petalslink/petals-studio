package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public interface FinishServiceCreationStrategy {

	public void finishWizard(ComponentCreationWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) throws Exception;

	public IProject getSUProject(ComponentCreationWizard wizard, IProgressMonitor monitor);
}
