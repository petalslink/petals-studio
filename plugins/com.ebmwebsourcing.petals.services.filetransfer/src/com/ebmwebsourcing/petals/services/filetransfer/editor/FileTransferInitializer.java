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

package com.ebmwebsourcing.petals.services.filetransfer.editor;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ServiceInitializer;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class FileTransferInitializer extends ServiceInitializer {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ServiceInitializer
	 * #initializeService(com.sun.java.xml.ns.jbi.Provides)
	 */
	@Override
	public void initializeService(Provides provide) {
		provide.eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE, CopyMode.CONTENT_AND_ATTACHMENTS);
		provide.eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, System.getProperty("java.io.tmpdir"));
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ServiceInitializer
	 * #initializeService(com.sun.java.xml.ns.jbi.Consumes)
	 */
	@Override
	public void initializeService(Consumes consumes) {
		consumes.eSet(FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY, System.getProperty("java.io.tmpdir"));
		consumes.eSet(FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, TransferMode.CONTENT);
	}
}
