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
package com.ebmwebsourcing.petals.services.xslt.editor;

import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class XsltProvidesEditorContribution extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent( final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise ) {

		Composite composite = createEditorSection( mainTab, toolkit, "Identification", true );
		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, composite, ise);
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, composite, ise);

		composite = createEditorSection( mainTab, toolkit, "XSLT Parameters", true );
		SwtFactory.createLabel( composite, "XSL Stylesheet *:", "Relative path to the XSLT File");
		TextWithButtonComposite browser = SwtFactory.createFileBrowser( composite, false, false, "XSLT" );
		browser.setLayoutData( new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		ise.getDataBindingContext().bindValue( SWTObservables.observeText(browser.getText(), SWT.Modify),
		EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, XsltPackage.Literals.XSLT_PROVIDES__STYLESHEET ));
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "XSLT Parameters" );
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				XsltPackage.Literals.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME,
				XsltPackage.Literals.XSLT_PROVIDES__XSLT_ENGINE_FACTORY_CLASS_NAME,
				XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MIN,
				XsltPackage.Literals.XSLT_PROVIDES__TRANSFORMER_FACTORY_MAX );

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		Cdk5Utils.generateDefaultCdkWidgetsForProvidesEditor( endpoint, toolkit, composite, ise );
	}
}
