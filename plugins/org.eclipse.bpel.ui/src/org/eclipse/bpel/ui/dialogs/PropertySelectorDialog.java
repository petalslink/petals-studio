/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.dialogs;

import java.util.Set;

import org.eclipse.bpel.model.CorrelationSet;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.messageproperties.Property;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.properties.PropertiesLabelProvider;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * Asks the user to select a variable.
 */
public class PropertySelectorDialog extends SelectionAndCreationDialog {

	protected static class TypeLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof Property) {
				Property property = (Property)element;
				Object type = property.getType();
				if (type != null) {
					if (type instanceof XSDTypeDefinition) {
						XSDTypeDefinition typeDefinition = (XSDTypeDefinition) type;
						return typeDefinition.getName();
					} else if (type instanceof XSDElementDeclaration) {
						XSDElementDeclaration xsdElement = (XSDElementDeclaration) type;
						return xsdElement.getName();
					}
				}
			}
			return Messages.PropertySelectorDialog_None_1;
		}
	}

	protected CorrelationSet correlationSet;
	protected Text filterText;
	protected Table lowerList;
	protected TabbedPropertySheetWidgetFactory wf;

	/**
	 * The modelObject is the model element that indicates the scope in which the
	 * variable should be visible.
	 * @param parent
	 * @param correlationSet
	 * @param wf
	 */
	public PropertySelectorDialog(Shell parent, CorrelationSet correlationSet, TabbedPropertySheetWidgetFactory wf) {
		super(parent, new PropertiesLabelProvider(), new TypeLabelProvider());
		setTitle(Messages.PropertySelectorDialog_Select_a_Property_2);
		this.correlationSet = correlationSet;
		this.wf = wf;
		setMessage(Messages.PropertySelectorDialog_Property_Name_wildcards_3);
		setUpperListLabel(Messages.PropertySelectorDialog_Matches_4);
		setLowerListLabel(Messages.PropertySelectorDialog_Property_Type_5);
		refreshProperties();
	}

	/**
	 * Finds all available properties from this process and the artifacts WSDL.
	 */
	protected void refreshProperties() {
		Process process = ModelHelper.getProcess(this.correlationSet);
		Set<Property> properties = ModelHelper.getAvailableProperties(process);

		// remove properties from the current correlation set
		for( Property property : this.correlationSet.getProperties() ) {
			properties.remove(property);
		}

		Property[] propertiesArray = properties.toArray(new Property[properties.size()]);
		setElements(propertiesArray);
		if (this.fFilteredList != null) {
			setListElements(propertiesArray);
		}

		if (propertiesArray.length > 0 && this.fFilteredList != null) {
			this.filterText.setEnabled(true);
			this.lowerList.setEnabled(true);
			this.fFilteredList.setEnabled(true);
		}
	}

	@Override
	protected void handleNewButtonPushed() {
		Process process = ModelHelper.getProcess(this.correlationSet);
		BPELEditor bpelEditor = ModelHelper.getBPELEditor(process);
		String newName = this.filterText.getText();
		EditMessagePropertyDialog dialog = new EditMessagePropertyDialog(getShell(), null, newName, bpelEditor, this.wf);
		if (dialog.open() == Window.OK) {
			Property property = dialog.getProperty();
			if (property != null) {
				refreshProperties();
				setFilter(property.getName());
				setLowerSelectedElement(property);

				// give focus to the dialog's OK button
				getOkButton().setFocus();
			}
		}
	}

	/**
	 * Returns the chosen property or null.
	 * @return
	 */
	public Property getProperty() {
		if (getResult().length > 0) {
			return (Property)getResult()[0];
		}
		return null;
	}

	/**
	 * Only used to get the Text instance.
	 */
	@Override
	protected Text createFilterText(Composite parent) {
		this.filterText = super.createFilterText(parent);
		return this.filterText;
	}

	/**
	 * Only used to get the Table instance.
	 */
	@Override
	protected Table createLowerList(Composite parent) {
		this.lowerList = super.createLowerList(parent);
		return this.lowerList;
	}
}
