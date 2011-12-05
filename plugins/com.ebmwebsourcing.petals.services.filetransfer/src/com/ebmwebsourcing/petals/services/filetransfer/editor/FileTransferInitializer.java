package com.ebmwebsourcing.petals.services.filetransfer.editor;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ServiceInitializer;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

public class FileTransferInitializer extends ServiceInitializer {
	
	@Override
	public void initializeService(Provides provide) {
		provide.eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE, CopyMode.CONTENT_AND_ATTACHMENTS);
		provide.eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, System.getProperty("java.io.tmpdir"));
	}

	@Override
	public void initializeService(Consumes consumes) {
		consumes.eSet(FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY, System.getProperty("java.io.tmpdir"));
		consumes.eSet(FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, TransferMode.CONTENT);
	}

}
