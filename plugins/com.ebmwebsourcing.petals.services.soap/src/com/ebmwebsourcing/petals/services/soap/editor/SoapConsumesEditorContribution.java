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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers.CommonUIBean;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SoapConsumesEditorContribution extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {

		Composite composite = createCommonConsumeSection( mainTab, toolkit );
		CommonUIBean commonUiBean = JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, composite, ise );

		composite = createEditorSection( mainTab, toolkit, CDK5JBIEndpointUIHelper.CONSUME_TITLE, CDK5JBIEndpointUIHelper.CONSUME_DESC, true );
		CDK5JBIEndpointUIHelper.createConsumesUI( endpoint, toolkit, composite, ise, commonUiBean );

		composite = createEditorSection( mainTab, toolkit, "SOAP", true );
		EObjecttUIHelper.generateWidgets(
				endpoint,
				toolkit,
				composite,
				ise.getEditingDomain(),
				ise.getDataBindingContext(),
				true,
				SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME,
				SoapPackage.Literals.SOAP_CONSUMES__SOAP_ACTION );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "Miscellaneous" );
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
					SoapPackage.Literals.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR,
					SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT,
					SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT,
					SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT,
					SoapPackage.Literals.SOAP_CONSUMES__ENABLE_WSA,
					SoapPackage.Literals.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION );

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		CDK5JBIEndpointUIHelper.generateDefaultCdkWidgetsForConsumesEditor( endpoint, toolkit, composite, ise );
	}
}
