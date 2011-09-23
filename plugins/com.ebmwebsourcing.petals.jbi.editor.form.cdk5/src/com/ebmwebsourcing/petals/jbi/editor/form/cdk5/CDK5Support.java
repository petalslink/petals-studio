package com.ebmwebsourcing.petals.jbi.editor.form.cdk5;

import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.ContributionSupport;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.jbi.editor.form.su.CompounedSUDetailsPage;

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
