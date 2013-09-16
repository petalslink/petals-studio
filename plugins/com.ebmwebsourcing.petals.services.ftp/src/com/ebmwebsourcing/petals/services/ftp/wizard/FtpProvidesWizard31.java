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
 
package com.ebmwebsourcing.petals.services.ftp.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription31;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.ftp.generated.FtpService31;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Micka�l Istria - EBM WebSourcing
 */
public class FtpProvidesWizard31 extends AbstractServiceUnitWizard {

	/**
	 * Constructor.
	 */
	public FtpProvidesWizard31() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FtpDescription31();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint abstractEndpoint ) {
		Cdk5Utils.setInitialProvidesValues((Provides) abstractEndpoint);

		abstractEndpoint.setInterfaceName( new QName( "http://petals.ow2.org/components/ftp/version-3", "FtpInterface" ));
		abstractEndpoint.setServiceName( new QName( "http://petals.ow2.org/components/ftp/version-3", "change-it" ));
		abstractEndpoint.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "FtpService.wsdl");

		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__SERVER, "");
		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__PORT, 21);
		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__USER, "");
		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__PASSWORD, "");
		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__FOLDER, "");
		this.endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__FILENAME, "*.*");
	}



	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "FtpService.wsdl" );
		createFile( wsdlFile, new FtpService31().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getLastCustomWizardPages()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				Ftp3Package.Literals.FTP_PROVIDES__SERVER,
				Ftp3Package.Literals.FTP_PROVIDES__PORT,
				Ftp3Package.Literals.FTP_PROVIDES__USER,
				Ftp3Package.Literals.FTP_PROVIDES__PASSWORD,
				Ftp3Package.Literals.FTP_PROVIDES__FOLDER,
				Ftp3Package.Literals.FTP_PROVIDES__FILENAME)
		};
	}
}
