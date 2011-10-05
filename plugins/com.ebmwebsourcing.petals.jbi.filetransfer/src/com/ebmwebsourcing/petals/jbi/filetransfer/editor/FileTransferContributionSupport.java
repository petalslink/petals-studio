package com.ebmwebsourcing.petals.jbi.filetransfer.editor;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.DefaultJbiEditorContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;


public class FileTransferContributionSupport extends ContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new DefaultJbiEditorContribution(hostDetailsPage, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new DefaultJbiEditorContribution(hostDetailsPage, FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES);
	}

}
