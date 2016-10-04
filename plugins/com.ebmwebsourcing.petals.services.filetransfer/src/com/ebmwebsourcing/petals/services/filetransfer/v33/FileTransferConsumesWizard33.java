/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.filetransfer.v33;

import com.ebmwebsourcing.petals.services.filetransfer.v30.wizard.FileTransferConsumesWizard30;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;

/**
 * @author Victor Noel - Linagora
 */
public class FileTransferConsumesWizard33 extends FileTransferConsumesWizard30 {

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FileTransferDescription33();
	}
}
