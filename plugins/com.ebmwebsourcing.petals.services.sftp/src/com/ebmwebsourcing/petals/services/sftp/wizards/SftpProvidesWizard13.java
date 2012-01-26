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

package com.ebmwebsourcing.petals.services.sftp.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.sftp.SftpDescription13;
import com.ebmwebsourcing.petals.services.sftp.generated.SftpService12;
import com.ebmwebsourcing.petals.services.sftp.generated.SftpService13;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SftpProvidesWizard13 extends SftpProvidesWizard12 {

	public SftpProvidesWizard13() {
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
		return new SftpDescription13();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		IFile wsdlFile = resourceFolder.getFile( "SftpService.wsdl" );
		createFile( wsdlFile, new SftpService12().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				SftpPackage.Literals.SFTP_PROVIDES__SERVER,
				SftpPackage.Literals.SFTP_PROVIDES__PORT,
				SftpPackage.Literals.SFTP_PROVIDES__USER,
				SftpPackage.Literals.SFTP_PROVIDES__PASSWORD,
				SftpPackage.Literals.SFTP_PROVIDES__PRIVATEKEY,
				SftpPackage.Literals.SFTP_PROVIDES__PASSPHRASE,
				SftpPackage.Literals.SFTP_PROVIDES__FOLDER,
				SftpPackage.Literals.SFTP_PROVIDES__MAX_IDLE_TIME,
				SftpPackage.Literals.SFTP_PROVIDES__OVERWRITE,
				SftpPackage.Literals.SFTP_PROVIDES__MAX_CONNECTION)
		};
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		createFile(resourceDirectory.getFile("SftpService.wsdl"), new SftpService13().generate(endpoint), monitor);
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}
}
