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
package com.ebmwebsourcing.petals.services.validation.editor;

import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.validation.validation.ValidationPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 *
 */
public class ValidationProvidesEditorContribution implements JbiEditorDetailsContribution {

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

		{
			Section ejbSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
			ejbSection.setText("Validate message against an XSD Schema");
			ejbSection.setLayoutData(new GridData(GridData.FILL_BOTH));
			Composite ejbComposite = toolkit.createComposite(ejbSection);
			ejbComposite.setLayout(new GridLayout(2, false));
			ejbSection.setClient(ejbComposite);
			
			SwtFactory.createLabel(ejbComposite, "XSD Schema", "Relative path to the XSD File");
			TextWithButtonComposite browser = SwtFactory.createFileBrowser(ejbComposite, false, false, ".xsd");
			browser.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			ise.getDataBindingContext().bindValue(SWTObservables.observeText(browser.getText(), SWT.Modify),
					EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, ValidationPackage.Literals.VALIDATION_PROVIDES__SCHEMA));
		}
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {
		advancedTab.setLayout(new GridLayout(1, false));
		advancedTab.setLayoutData(new GridData(GridData.FILL_BOTH));

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
