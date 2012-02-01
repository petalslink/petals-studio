package com.ebmwebsourcing.petals.services.jms.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

public class JMSEditorExtension extends EditorContributionSupport {

	public JMSEditorExtension() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new JMSProvidesEditorExtension();
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new JMSConsumesEditorExtension();
	}

}
