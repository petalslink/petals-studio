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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;
import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferDescription24;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferConsumesWizard24 extends AbstractServiceUnitWizard {

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
	public void presetServiceValues( AbstractEndpoint ae ) {
		Cdk5Utils.setInitialConsumesValues((Consumes) ae);
		ae.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__READ_DIRECTORY, "" );
		ae.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY, "" );
		ae.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, TransferMode.CONTENT );
		ae.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN, "*" );
		ae.eSet( Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__POLLING_PERIOD, 1000 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__READ_DIRECTORY,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__POLLING_PERIOD
		)};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint ae, IProgressMonitor monitor) {

		// MEP + operations
		ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, this.settings.invokedOperation );
		Cdk5Utils.setMep( ae, this.settings.invocationMep );

		// Remove unused values
		hackEmfModel( ae, Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY );

		return Status.OK_STATUS;
	}
}
