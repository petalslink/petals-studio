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

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import java.io.InputStream;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferDescription;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferPlugin;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ConsumesFiletransfer10;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ProvidesFiletransfer10;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferDescription24 extends FileTransferDescription {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentVersion()
	 */
	@Override
	public String getComponentVersion() {
		return "2.4";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #loadComponentModel(com.ebmwebsourcing.petals.services.su.wizards.PetalsMode)
	 */
	@Override
	public InputStream loadComponentModel( PetalsMode petalsMode ) {
		String loc = petalsMode == PetalsMode.provides ? "provides" : "consumes";
		return PlatformUtils.loadPluginResource( FileTransferPlugin.PLUGIN_ID, "/model/" + loc + "-FileTransfer-1.0.properties" );
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentNamespace()
	 */
	@Override
	public String getComponentNamespace() {
		return "http://petals.ow2.org/components/filetransfer/version-2";
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
				ProvidesFiletransfer10.WRITE_DIRECTORY,
				ProvidesFiletransfer10.COPY_MODE,
				ProvidesFiletransfer10.FILE_PATTERN,
				ProvidesFiletransfer10.READ_DIRECTORY,
				ProvidesFiletransfer10.BACKUP_DIRECTORY
			};

		} else {
			result = new String[] {
				ConsumesFiletransfer10.READ_DIRECTORY,
				ConsumesFiletransfer10.BACKUP_DIRECTORY,
				ConsumesFiletransfer10.TRANSFER_MODE,
				ConsumesFiletransfer10.FILE_PATTERN,
				ConsumesFiletransfer10.POLLING_PERIOD
			};
		}

		return result;
	}
}
