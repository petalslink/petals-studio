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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package;
import com.ebmwebsourcing.petals.services.filetransfer.v3.FileTransferDescription3;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferConsumesWizard3 extends AbstractServiceUnitWizard {

	private FileTransferConsumesWizardPage3 secondPage;


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FileTransferDescription3();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		Cdk5Utils.setInitialConsumesValues((Consumes) ae);
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FOLDER, "" );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY, "" );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD, 1000 );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FILENAME, "*" );

		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE, TransferMode.CONTENT );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE, "" );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE, 5 );
		ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT, 10000 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {

		SimpleFeatureListSuWizardPage firstPage = new SimpleFeatureListSuWizardPage(
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FOLDER,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__FILENAME,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT
		);

		this.secondPage = new FileTransferConsumesWizardPage3();
		return new AbstractSuWizardPage[] { firstPage, this.secondPage };
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint ae, IProgressMonitor monitor) {

		// Deal with the base-message and transfer type choice
		if( this.secondPage.isUseMsgSkeleton()) {
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE, null );
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE, this.secondPage.getXmlContent());

		} else if( this.secondPage.isContentTransfer()) {
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE, TransferMode.CONTENT );
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE, null );

		} else {
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE, TransferMode.ATTACHMENT );
			ae.eSet( Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE, null );
		}

		// MEP + operations
		ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, this.settings.invokedOperation );
		Cdk5Utils.setMep( ae, this.settings.invocationMep );

		// Remove unused values
		hackEmfModel( ae,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE,
				Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE );

		return Status.OK_STATUS;
	}

}
