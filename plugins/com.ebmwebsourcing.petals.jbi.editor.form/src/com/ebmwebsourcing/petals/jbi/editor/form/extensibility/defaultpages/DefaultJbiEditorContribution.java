/**
 *  Copyright (c) 2011, EBM WebSourcing
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.jbi.editor.form.extensibility.defaultpages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.jbi.editor.form.su.CompounedSUDetailsPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public class DefaultJbiEditorContribution implements JbiEditorDetailsContribution {

	private class EntryDescription {
		public Object widget;
		public EAttribute attribute;

		public EntryDescription(Object widget, EAttribute att) {
			this.widget = widget;
			this.attribute = att;
		}
	}
	
	private EClass extensionClass;
	private CompounedSUDetailsPage hostDetailsPage;
	private AbstractServicesFormPage generalFormPage;
	private List<EntryDescription> entries;
	private AbstractEndpoint selectedItem;
	
	private DataBindingContext dbc;

	public DefaultJbiEditorContribution(CompounedSUDetailsPage hostDetailsPage, EClass extensionEClass) {
		this.extensionClass = extensionEClass;
		this.hostDetailsPage = hostDetailsPage;
		this.generalFormPage = hostDetailsPage.getGeneralPage();
	}
	
	@Override
	public void refresh() {
		this.selectedItem = hostDetailsPage.getSelectedEndpoint();
		
		if (selectedItem == null || entries == null || entries.isEmpty()) {
			return;
		}
		
		if (dbc != null) {
			dbc.dispose();
		}
		dbc = new DataBindingContext();
		
		InitializeModelExtensionCommand fixExtensionCommand = new InitializeModelExtensionCommand(extensionClass.getEPackage(), selectedItem);
		if (fixExtensionCommand.canExecute()) {
			generalFormPage.getEditDomain().getCommandStack().execute(fixExtensionCommand);
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
					EMFEditObservables.observeValue(generalFormPage.getEditDomain(), selectedItem, entry.attribute));
			}
		}

	}

	@Override
	public void addSUContentBefore(Composite container) {
	}

	@Override
	public void addSUContentAfter(Composite parent) {
		entries = new ArrayList<EntryDescription>();
		FormToolkit toolkit = hostDetailsPage.getManagedForm().getToolkit();
		
		Section section = toolkit.createSection(parent, Section.EXPANDED);
		section.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		section.setText(extensionClass.getName());
		Composite container = toolkit.createComposite(section);
		container.setLayout(new GridLayout(2, false));
		section.setClient(container);
		
		for (EStructuralFeature feature : extensionClass.getEAllStructuralFeatures()) {
			if (isInPackage(feature) && feature instanceof EAttribute) {
				Object widget = null;
				EAttribute attr = (EAttribute)feature;
				toolkit.createLabel(container, attr.getName());
				if (attr.getEType().equals(EcorePackage.Literals.ESTRING)) {
					widget = toolkit.createText(container, "", SWT.BORDER);
					((Text)widget).setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
				} else if (attr.getEType().equals(EcorePackage.Literals.EINT) || attr.getEType().equals(EcorePackage.Literals.EINTEGER_OBJECT)) {
					widget = new Spinner(container, SWT.DEFAULT);
				} else if (attr.getEType() instanceof EEnum) {
					widget = new ComboViewer(container, SWT.READ_ONLY | SWT.FLAT);
					ComboViewer viewer = (ComboViewer)widget;
					viewer.setContentProvider(new EEnumLiteralsProvider());
					viewer.setLabelProvider(new EEnumNameLabelProvider());
					viewer.setInput(attr.getEType());
				} else if (attr.getEType().equals(EcorePackage.Literals.EBOOLEAN) || attr.getEType().equals(EcorePackage.Literals.EBOOLEAN_OBJECT)) {
					widget = toolkit.createButton(container, "", SWT.CHECK);
				}
				if (widget != null) {
					entries.add(new EntryDescription(widget, attr));
				}
			}
		}
	}

	private boolean isInPackage(EStructuralFeature feature) {
		return feature.eContainer() instanceof EClass && ((EClass)feature.eContainer()).getEPackage().equals(extensionClass.getEPackage());
	}

	@Override
	public void addSUContentAfterEndpoint(Composite container) {
	}

}
