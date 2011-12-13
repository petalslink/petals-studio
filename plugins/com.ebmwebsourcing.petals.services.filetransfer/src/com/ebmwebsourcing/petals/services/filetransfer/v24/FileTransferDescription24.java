/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferDescription;

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
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProxy()
	 */
	@Override
	public boolean isProxy() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getSupportedMep()
	 */
	@Override
	public Mep[] getSupportedMep() {
		return new Mep[] { Mep.IN_ONLY };
	}
}
