/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.soap.wizards;

import java.io.File;
import java.net.URI;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class SoapProvidesSUWizardPage extends SimpleFeatureListSuWizardPage {

	public SoapProvidesSUWizardPage() {
		super(
			SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
			SoapPackage.Literals.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT,
			SoapPackage.Literals.SOAP_PROVIDES__CLEANUP_TRANSPORT,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_HOST,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_PORT,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_USER,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_PASSWORD,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_DOMAIN
			);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		getNewlyCreatedEndpoint().eAdapters().add(this);
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		FormToolkit tk = new FormToolkit(getShell().getDisplay());
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), tk, res, null, dbc,
			SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
			SoapPackage.Literals.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT,
			SoapPackage.Literals.SOAP_PROVIDES__CLEANUP_TRANSPORT);
		
		Group proxyGroup = new Group(res, SWT.NONE);
		proxyGroup.setText("Proxy");
		proxyGroup.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
		proxyGroup.setLayout(new GridLayout(2, false));
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), tk, proxyGroup, null, dbc,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_HOST,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_PORT,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_USER,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_PASSWORD,
			SoapPackage.Literals.SOAP_PROVIDES__PROXY_DOMAIN);
		
		setControl(res);
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		String wsdl = getWizard().getSelectedWSDLForProvide();
		try {
			if (wsdl != null && new File(new URI(wsdl)).exists()) {

				JbiBasicBean service = WsdlUtils.INSTANCE.parse(wsdl).get(0);
				// Address
				String oldSoapAddress = (String) getNewlyCreatedEndpoint().eGet(SoapPackage.Literals.SOAP_PROVIDES__ADDRESS);
				String newSoapAddress = service.getSoapAddress();
				if (newSoapAddress != null && !newSoapAddress.equals(oldSoapAddress)) {
					getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__ADDRESS, newSoapAddress);	
				}
				// version
				SoapVersion oldSoapVersion = (SoapVersion) getNewlyCreatedEndpoint().eGet(SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION);
				com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.SoapVersion newSoapVersion = service.getSoapVersion();
				if (newSoapVersion != null && !newSoapVersion.toString().equals(oldSoapVersion.toString())) {
					getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION, SoapVersion.get(newSoapVersion.toString()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // TODO replaceme
		}
	}

}
