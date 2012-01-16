package com.ebmwebsourcing.petals.services.ftp.editor;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages.DefaultJbiEditorContribution;

public class FTPEditorContribution extends EditorContributionSupport {

	public FTPEditorContribution() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new DefaultJbiEditorContribution(Ftp3Package.Literals.FTP_PROVIDES, Cdk5Package.Literals.CDK5_PROVIDES);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return null;
	}

}
