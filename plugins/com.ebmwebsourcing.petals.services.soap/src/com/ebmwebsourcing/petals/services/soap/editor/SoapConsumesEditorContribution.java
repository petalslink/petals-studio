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

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class SoapConsumesEditorContribution implements JbiEditorDetailsContribution {

	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {
		mainTab.setLayout(new GridLayout(1, false));
		mainTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section identificationSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText("Identification");
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite identificationComposite = toolkit.createComposite(identificationSection);
		identificationComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(identificationComposite);

		CDK5JBIEndpointUIHelper.createConsumesUI(endpoint, toolkit, identificationComposite, ise);
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {
		advancedTab.setLayout(new GridLayout(1, false));
		advancedTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section componentSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
		componentSection.setText("SOAP");
		componentSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite componentComposite = toolkit.createComposite(componentSection);
		componentComposite.setLayout(new GridLayout(2, false));
		componentSection.setClient(componentComposite);
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, componentComposite, ise, SoapPackage.Literals.SOAP_CONSUMES);
		
		Section cdkSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
		cdkSection.setText("CDK");
		cdkSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite cdkComposite = toolkit.createComposite(cdkSection);
		cdkComposite.setLayout(new GridLayout(2, false));
		cdkSection.setClient(cdkComposite);
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, cdkComposite, ise, Cdk5Package.Literals.CDK5_CONSUMES);
	}
}
