package com.ebmwebsourcing.petals.services.jbi.editor.common.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.EEnumLiteralsProvider;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages.EEnumNameLabelProvider;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public class EObjecttUIHelper {
	
	private static class EntryDescription {
		public Object widget;
		public EAttribute attribute;

		public EntryDescription(Object widget, EAttribute att) {
			this.widget = widget;
			this.attribute = att;
		}
	}
	
	public static void generateWidgets(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, JbiFormEditor editor, EStructuralFeature[] toProcessFeatures) {
		List<EntryDescription> entries = new ArrayList<EntryDescription>();
		for (EStructuralFeature feature : toProcessFeatures) {
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
				editor.getDataBindingContext().bindValue(
					widgetObservable,
					EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, entry.attribute));
			}
		}
	}
}
