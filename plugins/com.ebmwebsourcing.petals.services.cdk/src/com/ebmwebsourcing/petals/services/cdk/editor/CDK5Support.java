package com.ebmwebsourcing.petals.services.cdk.editor;

import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;

public class CDK5Support extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new CDK5ProvidesDetails(hostDetailsPage);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new CDK5ConsumesDetails(hostDetailsPage);
	}

}
