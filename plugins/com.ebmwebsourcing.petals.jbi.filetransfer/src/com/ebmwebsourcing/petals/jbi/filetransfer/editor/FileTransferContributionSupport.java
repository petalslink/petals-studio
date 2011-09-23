package com.ebmwebsourcing.petals.jbi.filetransfer.editor;

import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.ContributionSupport;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.defaultpages.DefaultJbiEditorContribution;
import com.ebmwebsourcing.petals.jbi.editor.form.su.CompounedSUDetailsPage;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;


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
