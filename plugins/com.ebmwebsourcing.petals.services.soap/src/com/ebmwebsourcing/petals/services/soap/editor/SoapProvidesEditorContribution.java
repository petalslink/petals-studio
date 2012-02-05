/****************************************************************************
 *
 * Copyright (c) 2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.soap.editor;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class SoapProvidesEditorContribution implements JbiEditorDetailsContribution {


	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {
		mainTab.setLayout(new GridLayout(1, false));
		mainTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section identificationSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText("Identification");
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite identificationComposite = toolkit.createComposite(identificationSection);
		identificationComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(identificationComposite);

		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, identificationComposite, ise);
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, identificationComposite, ise);

		Section soapSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		soapSection.setText("SOAP");
		soapSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite soapComposite = toolkit.createComposite(soapSection);
		soapSection.setClient(soapComposite);
		soapComposite.setLayout(new GridLayout(2, false));
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, soapComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
				SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION);
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {
		advancedTab.setLayout(new GridLayout(1, false));
		advancedTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		{
			Section ejbSection = toolkit.createSection(advancedTab, Section.TITLE_BAR);
			ejbSection.setText("SOAP: Other");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);

			EObjecttUIHelper.generateWidgets(endpoint, toolkit, ejbComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
					SoapPackage.Literals.SOAP_PROVIDES__CLEANUP_TRANSPORT,
					SoapPackage.Literals.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME,
					SoapPackage.Literals.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD,
					SoapPackage.Literals.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR);
		}
		{
			Section ejbSection = toolkit.createSection(advancedTab, Section.TITLE_BAR);
			ejbSection.setText("SOAP: WSA");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);

			EObjecttUIHelper.generateWidgets(endpoint, toolkit, ejbComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_PROVIDES__ENABLE_WSA,
					SoapPackage.Literals.SOAP_PROVIDES__WSA_FROM,
					SoapPackage.Literals.SOAP_PROVIDES__WSA_REPLY_TO,
					SoapPackage.Literals.SOAP_PROVIDES__WSA_FAULT_TO);
		}
		{
			Section ejbSection = toolkit.createSection(advancedTab, Section.TITLE_BAR);
			ejbSection.setText("SOAP: Proxy");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);

			EObjecttUIHelper.generateWidgets(endpoint, toolkit, ejbComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_PROVIDES__PROXY_HOST,
					SoapPackage.Literals.SOAP_PROVIDES__PROXY_PORT,
					SoapPackage.Literals.SOAP_PROVIDES__PROXY_USER,
					SoapPackage.Literals.SOAP_PROVIDES__PROXY_PASSWORD,
					SoapPackage.Literals.SOAP_PROVIDES__PROXY_DOMAIN);
		}
		{
			Section ejbSection = toolkit.createSection(advancedTab, Section.TITLE_BAR);
			ejbSection.setText("SOAP: Headers");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);

			EObjecttUIHelper.generateWidgets(endpoint, toolkit, ejbComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_PROVIDES__HEADERS_FILTER,
					SoapPackage.Literals.SOAP_PROVIDES__INJECT_HEADERS,
					SoapPackage.Literals.SOAP_PROVIDES__HEADERS_TO_INJECT);
		}
		{
			Section ejbSection = toolkit.createSection(advancedTab, Section.TITLE_BAR);
			ejbSection.setText("SOAP: https");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);

			EObjecttUIHelper.generateWidgets(endpoint, toolkit, ejbComposite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE,
					SoapPackage.Literals.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD,
					SoapPackage.Literals.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE,
					SoapPackage.Literals.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD);
		}
		{
			Section cdkSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
			cdkSection.setText("CDK");
			cdkSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite cdkComposite = toolkit.createComposite(cdkSection);
			cdkComposite.setLayout(new GridLayout(2, false));
			cdkSection.setClient(cdkComposite);

			JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, cdkComposite, ise, Cdk5Package.Literals.CDK5_PROVIDES);
		}
	}

}
