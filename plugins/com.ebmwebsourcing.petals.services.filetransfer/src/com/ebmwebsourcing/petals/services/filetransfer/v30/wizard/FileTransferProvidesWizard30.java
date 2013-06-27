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

package com.ebmwebsourcing.petals.services.filetransfer.v30.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.generated.ProvidesCdk10;
import com.ebmwebsourcing.petals.services.filetransfer.generated.FileTransferService;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ProvidesFiletransfer20;
import com.ebmwebsourcing.petals.services.filetransfer.v30.FileTransferDescription30;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferProvidesWizard30 extends AbstractServiceUnitWizard {

	/**
	 * Constructor.
	 */
	public FileTransferProvidesWizard30() {
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
		return new FileTransferDescription30();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues() {
		this.suWizardModel.getEndpoint().setInterfaceName(new QName("http://petals.ow2.org/components/filetransfer/version-3", "FileTransferType"));
		this.suWizardModel.getEndpoint().setServiceName( new QName( "http://petals.ow2.org/components/filetransfer/version-3", "change-it" ));
		this.suWizardModel.getCdkModel().set( ProvidesCdk10.WSDL, "FileTransferService.wsdl" );
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "FileTransferService.wsdl" );
		createFile( wsdlFile, new FileTransferService().generate( this.suWizardModel.getEndpoint()), monitor );
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
				this.suWizardModel.getComponentModel(),
				ProvidesFiletransfer20.FOLDER,
				ProvidesFiletransfer20.BACKUP_DIRECTORY,
				ProvidesFiletransfer20.FILENAME
		)};
	}
}
