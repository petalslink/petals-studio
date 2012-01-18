package com.ebmwebsourcing.petals.services.jsr181.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

public class JaxWsEditorContribution extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new JaxWsProvidesEditorContribution();
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		// TODO Auto-generated method stub
		return null;
	}

}
