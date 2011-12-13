package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.EditingDomain;
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

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

public class EObjecttUIHelper {
	
	private static class EntryDescription {
		public Object widget;
		public EAttribute attribute;

		public EntryDescription(Object widget, EAttribute att) {
			this.widget = widget;
			this.attribute = att;
		}
	}
	
	/**
	 * Generate a 2 column list with left column containing description of widgets
	 * and right column containing widget
	 * @param eObject the eObject to edit
	 * @param toolkit a {@link FormToolkit} to create widgets
	 * @param parent
	 * @param domain the {@link EditingDomain} in case of transactional edition. Can be null, then no transaction is used.
	 * @param dbc
	 * @param toProcessFeatures list of features to edit.
	 */
	public static void generateWidgets(EObject eObject, FormToolkit toolkit, Composite parent, EditingDomain domain, DataBindingContext dbc, EStructuralFeature... toProcessFeatures) {
		List<EntryDescription> entries = new ArrayList<EntryDescription>();
		for (EStructuralFeature feature : toProcessFeatures) {
			Object widget = null;
			EAttribute attr = (EAttribute)feature;
			toolkit.createLabel(parent, attr.getName());
			Class<?> instanceClass = attr.getEType().getInstanceClass(); 
			if (instanceClass.equals(String.class)) {
				widget = toolkit.createText(parent, "", SWT.BORDER);
				((Text)widget).setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			} else if (instanceClass.equals(Integer.class) || instanceClass.equals(int.class)) {
				widget = new Spinner(parent, SWT.DEFAULT);
			} else if (instanceClass.isEnum()) {
				widget = new ComboViewer(parent, SWT.READ_ONLY | SWT.FLAT);
				ComboViewer viewer = (ComboViewer)widget;
				viewer.setContentProvider(new EEnumLiteralsProvider());
				viewer.setLabelProvider(new EEnumNameLabelProvider());
				viewer.setInput(attr.getEType());
			} else if (instanceClass.equals(Boolean.class) || instanceClass.equals(boolean.class)) {
				widget = toolkit.createButton(parent, "", SWT.CHECK);
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
				if (domain != null) {
					dbc.bindValue(
						widgetObservable,
						EMFEditObservables.observeValue(domain, eObject, entry.attribute));
				} else {
					dbc.bindValue(
						widgetObservable,
						EMFObservables.observeValue(eObject, entry.attribute));
				}
			}
		}
	}
}
