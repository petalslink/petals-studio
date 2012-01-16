package com.ebmwebsourcing.petals.services.su.editor.extensibility;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public interface JbiEditorDetailsContribution {

	public void addMainSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, ISharedEdition ise);
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, ISharedEdition ise);

}
