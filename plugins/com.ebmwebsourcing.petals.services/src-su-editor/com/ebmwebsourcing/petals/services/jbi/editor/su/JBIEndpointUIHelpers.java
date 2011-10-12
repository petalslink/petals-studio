package com.ebmwebsourcing.petals.services.jbi.editor.su;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.QNameToStringConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringIsQNameValidator;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringToQNameConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.EEnumLiteralsProvider;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.EEnumNameLabelProvider;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;

public class JBIEndpointUIHelpers {

	private static class EntryDescription {
		public Object widget;
		public EAttribute attribute;

		public EntryDescription(Object widget, EAttribute att) {
			this.widget = widget;
			this.attribute = att;
		}
	}
	
	public static void createCommonEndpointUI(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, JbiFormEditor editor, DataBindingContext dbc) {
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

		
		dbc.bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(interfaceText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
				new UpdateValueStrategy().setConverter(new QNameToStringConverter()));

		dbc.bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(serviceText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME),
				new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
				new UpdateValueStrategy().setConverter(new QNameToStringConverter()));
		
		dbc.bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(edptNameText, SWT.Modify)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME));
	}
	
	public static void createDefaultWidgetByEIntrospection(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails,	JbiFormEditor editor, DataBindingContext dbc, EClass[] extensionClasses) {
		for (EClass extensionClass : extensionClasses) {
			InitializeModelExtensionCommand fixExtensionCommand = new InitializeModelExtensionCommand(extensionClass.getEPackage(), endpoint);
			if (fixExtensionCommand.canExecute()) {
				editor.getEditingDomain().getCommandStack().execute(fixExtensionCommand);
			}
		}
		
		List<EntryDescription> entries = new ArrayList<EntryDescription>();
		
		for (EClass extensionClass : extensionClasses) {
			for (EStructuralFeature feature : extensionClass.getEAllStructuralFeatures()) {
				if (isInPackage(feature, extensionClass) && feature instanceof EAttribute
					&& !feature.getEType().equals(EcorePackage.Literals.EFEATURE_MAP_ENTRY)) {
					Object widget = null;
					EAttribute attr = (EAttribute)feature;
					toolkit.createLabel(advancedDetails, attr.getName());
					if (attr.getEType().equals(EcorePackage.Literals.ESTRING)) {
						widget = toolkit.createText(advancedDetails, "", SWT.BORDER);
						((Text)widget).setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
					} else if (attr.getEType().equals(EcorePackage.Literals.EINT) || attr.getEType().equals(EcorePackage.Literals.EINTEGER_OBJECT)) {
						widget = new Spinner(advancedDetails, SWT.DEFAULT);
					} else if (attr.getEType() instanceof EEnum) {
						widget = new ComboViewer(advancedDetails, SWT.READ_ONLY | SWT.FLAT);
						ComboViewer viewer = (ComboViewer)widget;
						viewer.setContentProvider(new EEnumLiteralsProvider());
						viewer.setLabelProvider(new EEnumNameLabelProvider());
						viewer.setInput(attr.getEType());
					} else if (attr.getEType().equals(EcorePackage.Literals.EBOOLEAN) || attr.getEType().equals(EcorePackage.Literals.EBOOLEAN_OBJECT)) {
						widget = toolkit.createButton(advancedDetails, "", SWT.CHECK);
					}
					if (widget != null) {
						entries.add(new EntryDescription(widget, attr));
					}
				}
			}
		}

		for (EntryDescription entry : entries) {
			IObservableValue widgetObservable = null;
			if (entry.widget instanceof Text) {
				widgetObservable = SWTObservables.observeDelayedValue(300, SWTObservables.observeText((Text)entry.widget, SWT.Modify));
			} else if (entry.widget instanceof Spinner) {
				widgetObservable = SWTObservables.observeSelection((Spinner)entry.widget);
			} else if (entry.widget instanceof ISelectionProvider) {
				widgetObservable = ViewersObservables.observeSingleSelection((ISelectionProvider)entry.widget);
			} else if (entry.widget instanceof Button) {
				widgetObservable = SWTObservables.observeSelection((Button)entry.widget);
			}
			if (widgetObservable != null) {
				dbc.bindValue(
					widgetObservable,
					EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, entry.attribute));
			}
		}
	}

	private static boolean isInPackage(EStructuralFeature feature, EClass extensionClass) {
		return feature.eContainer() instanceof EClass && ((EClass)feature.eContainer()).getEPackage().equals(extensionClass.getEPackage());
	}
}
