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

package com.ebmwebsourcing.petals.services.filetransfer.v30;

import java.io.InputStream;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferDescription;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferPlugin;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ConsumesFiletransfer20;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ProvidesFiletransfer20;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferDescription30 extends FileTransferDescription {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentVersion()
	 */
	@Override
	public String getComponentVersion() {
		return "3.0";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #loadComponentModel(com.ebmwebsourcing.petals.services.su.wizards.PetalsMode)
	 */
	@Override
	public InputStream loadComponentModel( PetalsMode petalsMode ) {
		String loc = petalsMode == PetalsMode.provides ? "provides" : "consumes";
		return PlatformUtils.loadPluginResource( FileTransferPlugin.PLUGIN_ID, "/model/" + loc + "-FileTransfer-2.0.properties" );
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentNamespace()
	 */
	@Override
	public String getComponentNamespace() {
		return "http://petals.ow2.org/components/filetransfer/version-3";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getSortedComponentProperties(com.ebmwebsourcing.petals.services.su.wizards.PetalsMode)
	 */
	@Override
	public String[] getSortedComponentProperties( PetalsMode petalsMode ) {

		String[] result;
		if( petalsMode == PetalsMode.provides ) {
			result = new String[] {
				ProvidesFiletransfer20.FILENAME,
				ProvidesFiletransfer20.BACKUP_DIRECTORY,
				ProvidesFiletransfer20.FOLDER
			};

		} else {
			result = new String[] {
				ConsumesFiletransfer20.FOLDER,
				ConsumesFiletransfer20.BACKUP_DIRECTORY,
				ConsumesFiletransfer20.POLLING_PERIOD,
				ConsumesFiletransfer20.TRANSFER_MODE,
				ConsumesFiletransfer20.BASE_MESSAGE,
				ConsumesFiletransfer20.PROCESSOR_POOL_SIZE,
				ConsumesFiletransfer20.PROCESSOR_POOL_TIMEOUT
			};
		}

		return result;
	}
}
