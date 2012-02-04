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

package com.ebmwebsourcing.petals.services.ftp.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.ftp.FtpDescription32;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.ftp.generated.FtpService32;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class FtpProvidesWizard32 extends FtpProvidesWizard31 {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FtpDescription32();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.ftp.wizard.FtpProvidesWizard31
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues(AbstractEndpoint endpoint) {
		super.presetServiceValues(endpoint);
		endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__DELETE_PROCESSED_FILES, false);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.ftp.wizard.FtpProvidesWizard31
	 * #getCustomWizardPagesAfterProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				Ftp3Package.Literals.FTP_PROVIDES__SERVER,
				Ftp3Package.Literals.FTP_PROVIDES__PORT,
				Ftp3Package.Literals.FTP_PROVIDES__USER,
				Ftp3Package.Literals.FTP_PROVIDES__PASSWORD,
				Ftp3Package.Literals.FTP_PROVIDES__FOLDER,
				Ftp3Package.Literals.FTP_PROVIDES__FILENAME,
				Ftp3Package.Literals.FTP_PROVIDES__DELETE_PROCESSED_FILES)
		};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "FtpService.wsdl" );
		createFile( wsdlFile, new FtpService32().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}

}
