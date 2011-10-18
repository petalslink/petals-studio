package com.ebmwebsourcing.petals.services.jbi.editor.su.wizard;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

public class QNameEditor extends Dialog {

	private QName qname;

	public QNameEditor(Shell parentShell, QName initialValue) {
		super(parentShell);
		this.qname = new QName(initialValue.getNamespaceURI(), initialValue.getLocalPart());
	}

	public QName getQName() {
		return qname;
	}

}
