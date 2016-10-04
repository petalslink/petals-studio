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
 
package com.ebmwebsourcing.petals.services.sftp.wizards;

import com.ebmwebsourcing.petals.services.sftp.SftpDescription16;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;

/**
 * @author Victor Noel - Linagora
 */
public class SftpProvidesWizard16 extends SftpProvidesWizard13 {

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SftpDescription16();
	}
}
