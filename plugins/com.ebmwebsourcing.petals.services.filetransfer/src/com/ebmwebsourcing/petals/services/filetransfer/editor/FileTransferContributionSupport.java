package com.ebmwebsourcing.petals.services.filetransfer.editor;

import org.eclipse.emf.ecore.EClass;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.DefaultJbiEditorContribution;


public class FileTransferContributionSupport extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new DefaultJbiEditorContribution(new EClass[] { Cdk5Package.Literals.CDK5_PROVIDES, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES });
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new DefaultJbiEditorContribution(new EClass[] { Cdk5Package.Literals.CDK5_CONSUMES, FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES} );
	}

}
