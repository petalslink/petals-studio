package com.ebmwebsourcing.petals.services.soap.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

public class SoapEditorContribution extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new SoapProvidesEditorContribution(); 
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new SoapConsumesEditorContribution();
	}

}
