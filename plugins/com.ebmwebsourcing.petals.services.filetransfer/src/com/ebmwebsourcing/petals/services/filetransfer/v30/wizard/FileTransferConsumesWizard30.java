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

import com.ebmwebsourcing.petals.services.filetransfer.generated.ConsumesFiletransfer20;
import com.ebmwebsourcing.petals.services.filetransfer.v30.FileTransferDescription30;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferConsumesWizard30 extends AbstractServiceUnitWizard {

	private FileTransferConsumesWizardPage30 secondPage;


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
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {

		SimpleFeatureListSuWizardPage firstPage = new SimpleFeatureListSuWizardPage(
				this.suWizardModel.getComponentModel(),
				ConsumesFiletransfer20.FOLDER,
				ConsumesFiletransfer20.BACKUP_DIRECTORY,
				ConsumesFiletransfer20.FILENAME,
				ConsumesFiletransfer20.POLLING_PERIOD,
				ConsumesFiletransfer20.PROCESSOR_POOL_SIZE,
				ConsumesFiletransfer20.PROCESSOR_POOL_TIMEOUT
		);

		this.secondPage = new FileTransferConsumesWizardPage30();
		return new AbstractSuWizardPage[] { firstPage, this.secondPage };
	}
}
