package com.ebmwebsourcing.petals.services.mail.editor;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages.DefaultJbiEditorContribution;

public class MailEditorContribution extends EditorContributionSupport {

	public MailEditorContribution() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new MailProvidesEditorContribution();
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new MailConsumesEditorContribution();
	}

}
