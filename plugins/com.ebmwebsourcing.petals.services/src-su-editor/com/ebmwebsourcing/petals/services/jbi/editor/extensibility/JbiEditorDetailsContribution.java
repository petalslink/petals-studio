package com.ebmwebsourcing.petals.services.jbi.editor.extensibility;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public interface JbiEditorDetailsContribution {

	public void addMainSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, JbiFormEditor editor);
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, JbiFormEditor editor);
	
}
