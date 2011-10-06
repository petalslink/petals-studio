package com.ebmwebsourcing.petals.services.filetransfer.editor;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.DefaultJbiEditorContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;


public class FileTransferContributionSupport extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new DefaultJbiEditorContribution(hostDetailsPage, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new DefaultJbiEditorContribution(hostDetailsPage, FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES);
	}

}
