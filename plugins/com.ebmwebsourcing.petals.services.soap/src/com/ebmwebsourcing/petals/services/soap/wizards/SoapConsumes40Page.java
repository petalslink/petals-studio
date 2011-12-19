package com.ebmwebsourcing.petals.services.soap.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class SoapConsumes40Page extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, dbc,
				SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME,
				SoapPackage.Literals.SOAP_CONSUMES__SOAP_ACTION,
				SoapPackage.Literals.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT,
				SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT,
				SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT);
		
		setControl(container);
	}

	@Override
	public boolean validate() {
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}

}
