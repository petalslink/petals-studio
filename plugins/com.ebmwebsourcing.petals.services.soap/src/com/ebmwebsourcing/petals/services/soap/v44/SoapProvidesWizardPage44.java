/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v44;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjectUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.v41.SoapProvidesWizardPage41;

/**
 * @author Victor Noel - Linagora
 */
public class SoapProvidesWizardPage44 extends SoapProvidesWizardPage41 {

	/**
	 * Constructor.
	 */
	public SoapProvidesWizardPage44() {
		super(
			SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
			SoapPackage.Literals.SOAP_PROVIDES__MODE
			);
	}

	@Override
	public void createControl(Composite parent) {
		this.dbc = new DataBindingContext();

		Composite res = new Composite(parent, SWT.NONE);
		SwtFactory.applyNewGridLayout( res, 2, false, 15, 15, 0, 15 );
		setControl(res);

		FormToolkit tk = new FormToolkit(getShell().getDisplay());
		EObjectUIHelper.generateWidgets(getNewlyCreatedEndpoint(), tk, res, null, this.dbc, false,
			SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE);
	}
}
