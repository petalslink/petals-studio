package com.ebmwebsourcing.petals.services.mail.editor;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.DefaultJbiEditorContribution;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;

public class MailEditorContribution extends EditorContributionSupport {

	public MailEditorContribution() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new DefaultJbiEditorContribution(MailPackage.Literals.MAIL_PROVIDES, Cdk5Package.Literals.CDK5_PROVIDES);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new DefaultJbiEditorContribution(MailPackage.Literals.MAIL_CONSUMES, Cdk5Package.Literals.CDK5_CONSUMES);
	}

}
