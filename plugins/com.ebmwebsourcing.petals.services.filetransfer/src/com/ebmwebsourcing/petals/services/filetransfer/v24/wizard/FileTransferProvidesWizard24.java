/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
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

import com.ebmwebsourcing.petals.services.cdk.generated.ProvidesCdk10;
import com.ebmwebsourcing.petals.services.filetransfer.generated.GetFilesWsdl24;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ProvidesFiletransfer10;
import com.ebmwebsourcing.petals.services.filetransfer.generated.WriteWsdl24;
import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferDescription24;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing
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
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FileTransferDescription24();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues() {
		this.suWizardModel.getEndpoint().setServiceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "change-it" ));
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus performLastActions( IFolder resourceFolder, IProgressMonitor monitor ) {

		// Deal with the parameters
		// Note: this approach seems more efficient than data-binding, since it guarantees insertion order in every case.
		// Unsettable attributes is a poor workaround. Maybe every component should have a helper to sort features.
		String wsdlContent;
		if( this.page.getContract() == Contract.WRITE_FILES ) {
			this.suWizardModel.getComponentModel().set( ProvidesFiletransfer10.WRITE_DIRECTORY, this.page.getWriteDirectory());
			this.suWizardModel.getComponentModel().set( ProvidesFiletransfer10.COPY_MODE, this.page.getCopyMode().toString());
			this.suWizardModel.getComponentModel().set( ProvidesFiletransfer10.FILE_PATTERN, this.page.getFilePattern());
			wsdlContent = new WriteWsdl24().generate( this.suWizardModel.getEndpoint());

		} else {
			this.suWizardModel.getComponentModel().set( ProvidesFiletransfer10.READ_DIRECTORY, this.page.getReadDirectory());
			this.suWizardModel.getComponentModel().set( ProvidesFiletransfer10.BACKUP_DIRECTORY, this.page.getBackupDirectory());
			wsdlContent = new GetFilesWsdl24().generate( this.suWizardModel.getEndpoint());
		}


		// Deal with the interface name and WSDL
		String wsdlName = this.suWizardModel.getEndpoint().getInterfaceName().getLocalPart() + ".wsdl";
		this.suWizardModel.getCdkModel().set( ProvidesCdk10.WSDL, wsdlName );
		createFile( resourceFolder.getFile( wsdlName ), wsdlContent, monitor );

		return Status.OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeJbi() {
		this.page = new FiletransferProvidesPage();
		return new AbstractSuWizardPage[] { this.page };
	}
}
