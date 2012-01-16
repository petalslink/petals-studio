/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferDescription24;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferConsumesWizard24 extends AbstractServiceUnitWizard {

	
	public FileTransferConsumesWizard24() {
		super();
		settings.showWsdl = false;
		settings.activateInterfaceName = false;
	}
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FileTransferDescription24();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
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
		return new AbstractSuWizardPage[] { new FiletransferConsumesPage() };
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

}
