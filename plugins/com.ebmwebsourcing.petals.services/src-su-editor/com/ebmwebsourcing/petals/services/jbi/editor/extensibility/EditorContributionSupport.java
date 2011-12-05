package com.ebmwebsourcing.petals.services.jbi.editor.extensibility;

import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

public abstract class EditorContributionSupport {
	
	protected abstract JbiEditorDetailsContribution getProvidesContribution();
	protected abstract JbiEditorDetailsContribution getConsumesContribution();
	
	public JbiEditorDetailsContribution createJbiEditorContribution(AbstractExtensibleElement initialElement) {
		if (initialElement instanceof Provides) {
			return getProvidesContribution();
		} else if (initialElement instanceof Consumes) {
			return getConsumesContribution();
		} else {
			return null;
		}
	}

}
