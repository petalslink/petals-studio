package com.ebmwebsourcing.petals.jbi.editor.form.extensibility;

import com.ebmwebsourcing.petals.jbi.editor.form.su.CompounedSUDetailsPage;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

public abstract class ContributionSupport {
	
	private AbstractExtensibleElement element;
	
	protected abstract JbiEditorDetailsContribution getProvidesContribution(CompounedSUDetailsPage hostDetailsPage);
	protected abstract JbiEditorDetailsContribution getConsumesContribution(CompounedSUDetailsPage hostDetailsPage);
	
	public JbiEditorDetailsContribution createJbiEditorContribution(CompounedSUDetailsPage hostDetailsPage) {
		if (element instanceof Provides) {
			return getProvidesContribution(hostDetailsPage);
		} else if (element instanceof Consumes) {
			return getConsumesContribution(hostDetailsPage);
		} else {
			return null;
		}
	}
	
	public AbstractExtensibleElement getElement() {
		return this.element;
	}
	
	public void setElement(AbstractExtensibleElement element) {
		this.element = element;
	}

}
