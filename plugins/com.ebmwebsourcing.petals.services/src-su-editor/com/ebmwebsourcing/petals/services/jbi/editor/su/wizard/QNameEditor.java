package com.ebmwebsourcing.petals.services.jbi.editor.su.wizard;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.services.Messages;

public class QNameEditor extends Dialog {

	private QName qname;

	public QNameEditor(Shell parentShell, QName initialValue) {
		super(parentShell);
		this.qname = new QName(initialValue.getNamespaceURI(), initialValue.getLocalPart());
	}

	public QName getQName() {
		return qname;
	}
	
	@Override
	public Control createDialogArea(Composite parent) {
		Composite res = (Composite)super.createDialogArea(parent);
		res.setLayout(new GridLayout(2, false));
		Label nsLabel = new Label(res, SWT.NONE);
		nsLabel.setText(Messages.namespace);
		nsLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		final Text nsText = new Text(res, SWT.BORDER);
		nsText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		nsText.setText(qname.getNamespaceURI());
		Label localLabel = new Label(res, SWT.NONE);
		localLabel.setText(Messages.localPart);
		localLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		final Text localText = new Text(res, SWT.BORDER);
		localText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		localText.setText(qname.getLocalPart());
		
		nsText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				qname = new QName(nsText.getText(), qname.getLocalPart());
				getButton(OK).setEnabled(canClickOK());
			}
		});
		localText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				qname = new QName(qname.getNamespaceURI(), localText.getText());
				getButton(OK).setEnabled(canClickOK());
			}
		});
		
		return res;
	}

	protected boolean canClickOK() {
		return true;
	}

}
