package com.ebmwebsourcing.petals.services.soap.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class SoapProvides40Page extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, dbc,
				SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
				SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
				SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
				SoapPackage.Literals.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT,
				SoapPackage.Literals.SOAP_PROVIDES__CLEANUP_TRANSPORT,
				SoapPackage.Literals.SOAP_PROVIDES__PROXY_HOST,
				SoapPackage.Literals.SOAP_PROVIDES__PROXY_PORT,
				SoapPackage.Literals.SOAP_PROVIDES__PROXY_USER,
				SoapPackage.Literals.SOAP_PROVIDES__PROXY_PASSWORD,
				SoapPackage.Literals.SOAP_PROVIDES__PROXY_DOMAIN);
		
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
