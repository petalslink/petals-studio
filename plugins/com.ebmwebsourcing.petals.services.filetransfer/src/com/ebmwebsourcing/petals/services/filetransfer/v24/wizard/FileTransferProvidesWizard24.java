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

package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.ebmwebsourcing.petals.services.filetransfer.generated.GetFilesWsdl24;
import com.ebmwebsourcing.petals.services.filetransfer.generated.WriteWsdl24;
import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferDescription24;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class FileTransferProvidesWizard24 extends AbstractServiceUnitWizard {

	private FiletransferProvidesPage page;


	/**
	 * This component version has two different contracts.
	 */
	enum Contract {
		WRITE_FILES, READ_FILES
	}


	/**
	 * Constructor.
	 */
	public FileTransferProvidesWizard24() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
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
		ae.setServiceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "change-it" ));
		Cdk5Utils.setInitialProvidesValues((Provides) ae);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		// Deal with the parameters
		// Note: this approach seems more efficient than data-binding, since it guarantees insertion order in every case.
		// Unsettable attributes is a poor workaround. Maybe every component should have a helper to sort features.
		String wsdlContent;
		if( this.page.getContract() == Contract.WRITE_FILES ) {
			abstractEndpoint.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, this.page.getWriteDirectory());
			abstractEndpoint.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE, this.page.getCopyMode());
			abstractEndpoint.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN, this.page.getFilePattern());
			wsdlContent = new WriteWsdl24().generate( abstractEndpoint );

		} else {
			abstractEndpoint.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__READ_DIRECTORY, this.page.getReadDirectory());
			abstractEndpoint.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY, this.page.getBackupDirectory());
			wsdlContent = new GetFilesWsdl24().generate( abstractEndpoint );
		}


		// Deal with the interface name and WSDL
		String wsdlName = abstractEndpoint.getInterfaceName().getLocalPart() + ".wsdl";
		abstractEndpoint.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, wsdlName );
		createFile( resourceFolder.getFile( wsdlName ), wsdlContent, monitor );

		return Status.OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		this.page = new FiletransferProvidesPage();
		return new AbstractSuWizardPage[] { this.page };
	}
}
