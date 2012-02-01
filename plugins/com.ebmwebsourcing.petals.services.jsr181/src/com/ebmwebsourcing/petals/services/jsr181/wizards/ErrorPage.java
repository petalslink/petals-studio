/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.jsr181.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.services.jsr181.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

public class ErrorPage extends AbstractSuWizardPage {

	private Exception error;

	public ErrorPage(Exception prereq) {
		this.error = prereq;
	}

	@Override
	public void createControl(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(1, false));
		Label jsrErrorLabel = new Label(res, SWT.WRAP);
		jsrErrorLabel.setText(Messages.jsrErrorLabel);
		Text errorText = new Text(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		errorText.setText(toText(error));
		errorText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		setControl(res);
	}

	/**
	 * @param error2
	 * @return
	 */
	private String toText(Exception error) {
		StringBuilder res = new StringBuilder();
		res.append(error.getLocalizedMessage());
		for (StackTraceElement elt : error.getStackTrace()) {
			res.append("    ");
			res.append(elt.toString());
		}
		return res.toString();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
