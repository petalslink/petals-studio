package com.ebmwebsourcing.petals.services.jbi.editor.extensibility;

import org.eclipse.swt.widgets.Composite;

public interface JbiEditorDetailsContribution {

	public void addSUContentBefore(Composite container);
	public void addSUContentAfterEndpoint(Composite container);
	public void addSUContentAfter(Composite container);
	public void refresh();
	
}
