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

package com.ebmwebsourcing.petals.services.ftp.wizard;

import java.nio.charset.Charset;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription31;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription32;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription33;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.ftp.generated.FtpService31;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FtpProvidesWizard31 extends AbstractServiceUnitWizard {
	
	public FtpProvidesWizard31() {
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
		return new FtpDescription31();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint abstractEndpoint ) {
		abstractEndpoint.setInterfaceName( new QName( "http://petals.ow2.org/components/ftp/version-3", "FtpInterface" ));
		abstractEndpoint.setServiceName( new QName( "http://petals.ow2.org/components/ftp/version-3", "change-it" ));
		abstractEndpoint.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "FtpService.wsdl");
		endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__PORT, 21);
		endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__FILENAME, "*.*");
	}




	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		IFile wsdlFile = resourceFolder.getFile( "FtpService.wsdl" );
		createFile( wsdlFile, new FtpService31().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				Ftp3Package.Literals.FTP_PROVIDES__SERVER,
				Ftp3Package.Literals.FTP_PROVIDES__PORT,
				Ftp3Package.Literals.FTP_PROVIDES__USER,
				Ftp3Package.Literals.FTP_PROVIDES__PASSWORD,
				Ftp3Package.Literals.FTP_PROVIDES__FOLDER,
				Ftp3Package.Literals.FTP_PROVIDES__FILENAME)
		};
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}
}
