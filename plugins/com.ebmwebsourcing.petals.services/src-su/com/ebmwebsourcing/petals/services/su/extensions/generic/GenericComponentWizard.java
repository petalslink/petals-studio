package com.ebmwebsourcing.petals.services.su.extensions.generic;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public class GenericComponentWizard extends AbstractServiceUnitWizard {

	private final GenericComponentDescription genDesc = new GenericComponentDescription();
	
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return new AbstractSuWizardPage[] {
				new GenericSuWizardPage()
		};
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected IStatus performLastActions(IFolder resourceDirectory,	AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return genDesc;
	}

}
