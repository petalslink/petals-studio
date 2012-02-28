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

package com.ebmwebsourcing.petals.services.sftp.wizards;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.sftp.SftpDescription11;
import com.ebmwebsourcing.petals.services.sftp.generated.SftpService11;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class SftpProvidesWizard11 extends AbstractServiceUnitWizard {

	/**
	 * Constructor.
	 */
	public SftpProvidesWizard11() {
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
		return new SftpDescription11();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		Cdk5Utils.setInitialProvidesValues((Provides)ae);

		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/sftp/version-1", "SftpInterface" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/sftp/version-1", "change-it" ));

		ae.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "SftpService.wsdl" );
		ae.eSet( SftpPackage.Literals.SFTP_PROVIDES__SERVER, "" );
		ae.eSet( SftpPackage.Literals.SFTP_PROVIDES__PORT, 22 );
		ae.eSet( SftpPackage.Literals.SFTP_PROVIDES__USER, "" );
		ae.eSet( SftpPackage.Literals.SFTP_PROVIDES__PASSWORD, "" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "SftpService.wsdl" );
		createFile( wsdlFile, new SftpService11().generate( abstractEndpoint ), monitor );
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
				SftpPackage.Literals.SFTP_PROVIDES__SERVER,
				SftpPackage.Literals.SFTP_PROVIDES__PORT,
				SftpPackage.Literals.SFTP_PROVIDES__USER,
				SftpPackage.Literals.SFTP_PROVIDES__PASSWORD,
				SftpPackage.Literals.SFTP_PROVIDES__PASSPHRASE,
				SftpPackage.Literals.SFTP_PROVIDES__FOLDER)
		};
	}
}
