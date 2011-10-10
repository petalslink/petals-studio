package com.ebmwebsourcing.petals.services.jbi.editor.extensibility;

import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

public abstract class EditorContributionSupport {
	
	private AbstractExtensibleElement element;
	
	protected abstract JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage);
	protected abstract JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage);
	
	public JbiEditorDetailsContribution createJbiEditorContribution(AbstractExtensibleElement initialElement, CompounedSUDetailsPage hostDetailsPage) {
		if (initialElement instanceof Provides) {
			return getProvidesContribution(hostDetailsPage);
		} else if (initialElement instanceof Consumes) {
			return getConsumesContribution(hostDetailsPage);
		} else {
			return null;
		}
	}

}
