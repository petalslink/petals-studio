package com.ebmwebsourcing.petals.services.jbi.editor.su;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.QNameToStringConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringIsQNameValidator;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringToQNameConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.common.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;

public class JBIEndpointUIHelpers {

	public static void createCommonEndpointUI(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, JbiFormEditor editor) {
		Label label = toolkit.createLabel( generalDetails, "Interface qname:" );
		label.setToolTipText( "The Qualified Name '{namespace}element' of the interface (must match an interface declared in the WSDL)" );

		Text interfaceText = toolkit.createText(generalDetails, "", SWT.SINGLE | SWT.BORDER);
		interfaceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		
		label = toolkit.createLabel( generalDetails, "Service qname:" );
		label.setToolTipText( "The Qualified Name '{namespace}element' of the service (must match a service declared in the WSDL)" );

		Text serviceText = toolkit.createText( generalDetails, "", SWT.SINGLE | SWT.BORDER );
		serviceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Label edptNameLabel = toolkit.createLabel( generalDetails, "End-point name:" );
		edptNameLabel.setToolTipText( "The end-point name, meaning the service location (must match the one declared in the WSDL)" );

		Text edptNameText = toolkit.createText( generalDetails, "", SWT.SINGLE | SWT.BORDER );
		edptNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		
		editor.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(interfaceText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
				new UpdateValueStrategy().setConverter(new QNameToStringConverter()));

		editor.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(serviceText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME),
				new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
				new UpdateValueStrategy().setConverter(new QNameToStringConverter()));
		
		editor.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(edptNameText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME));
	}
	
	public static void createDefaultWidgetByEIntrospection(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, JbiFormEditor editor, EClass[] extensionClasses) {
		for (EClass extensionClass : extensionClasses) {
			InitializeModelExtensionCommand fixExtensionCommand = new InitializeModelExtensionCommand(extensionClass.getEPackage(), endpoint);
			if (fixExtensionCommand.canExecute()) {
				editor.getEditingDomain().getCommandStack().execute(fixExtensionCommand);
			}
		}
		
		List<EStructuralFeature> toProcessFeaturesList = new ArrayList<EStructuralFeature>();
		for (EClass extensionClass : extensionClasses) {
			for (EStructuralFeature feature : extensionClass.getEAllStructuralFeatures()) {
				if (isInPackage(feature, extensionClass) && feature instanceof EAttribute && !feature.getEType().equals(EcorePackage.Literals.EFEATURE_MAP_ENTRY)) {
					toProcessFeaturesList.add(feature);
				}
			}
		}
		EStructuralFeature[] toProcessFeatures = toProcessFeaturesList.toArray(new EStructuralFeature[toProcessFeaturesList.size()] );
		
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, advancedDetails, editor, toProcessFeatures);
	}


	private static boolean isInPackage(EStructuralFeature feature, EClass extensionClass) {
		return feature.eContainer() instanceof EClass && ((EClass)feature.eContainer()).getEPackage().equals(extensionClass.getEPackage());
	}
}
