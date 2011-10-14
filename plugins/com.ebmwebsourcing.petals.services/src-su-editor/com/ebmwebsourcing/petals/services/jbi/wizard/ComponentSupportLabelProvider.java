package com.ebmwebsourcing.petals.services.jbi.wizard;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;

public class ComponentSupportLabelProvider extends LabelProvider implements	IBaseLabelProvider {

	@Override
	public String getText(Object item) {
		if (item instanceof ComponentSupportExtensionDesc) {
			return ((ComponentSupportExtensionDesc) item).getId();
		} else {
			return super.getText(item);
		}
	}
}
