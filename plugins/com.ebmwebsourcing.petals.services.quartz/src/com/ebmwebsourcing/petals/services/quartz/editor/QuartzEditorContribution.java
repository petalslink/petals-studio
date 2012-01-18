package com.ebmwebsourcing.petals.services.quartz.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

public class QuartzEditorContribution extends EditorContributionSupport {

	public QuartzEditorContribution() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new QuartzConsumesEditorContribution();
	}

}
