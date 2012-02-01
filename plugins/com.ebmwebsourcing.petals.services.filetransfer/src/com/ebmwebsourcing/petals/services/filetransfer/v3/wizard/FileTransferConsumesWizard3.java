/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v3.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package;
import com.ebmwebsourcing.petals.services.filetransfer.v24.wizard.FiletransferConsumesPage;
import com.ebmwebsourcing.petals.services.filetransfer.v3.FileTransferDescription3;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferConsumesWizard3 extends AbstractServiceUnitWizard {

	
	public FileTransferConsumesWizard3() {
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
		return new FileTransferDescription3();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.eSet(Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000);
		ae.eSet(Cdk5Package.Literals.CDK5_CONSUMES__MEP, Mep.IN_ONLY);
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
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FOLDER,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FILENAME,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT
		) };
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
