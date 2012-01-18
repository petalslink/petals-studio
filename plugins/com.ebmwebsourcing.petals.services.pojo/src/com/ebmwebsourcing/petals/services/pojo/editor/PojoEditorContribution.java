package com.ebmwebsourcing.petals.services.pojo.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

public class PojoEditorContribution extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new PojoProvidesEditorContribution();
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return null;
	}

}
