package com.ebmwebsourcing.petals.jbi.editor.form.cdk5;

import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;

public class CDK5Support extends ContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new CDK5ProvidesDetails(hostDetailsPage);
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage) {
		return new CDK5ConsumesDetails(hostDetailsPage);
	}

}
