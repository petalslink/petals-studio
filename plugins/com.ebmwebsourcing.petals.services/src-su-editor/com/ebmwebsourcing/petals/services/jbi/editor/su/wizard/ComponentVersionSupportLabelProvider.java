package com.ebmwebsourcing.petals.services.jbi.editor.su.wizard;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;

public class ComponentVersionSupportLabelProvider extends LabelProvider implements IBaseLabelProvider {

	@Override
	public String getText(Object item) {
		return ((ComponentVersionSupportExtensionDesc)item).getVersion();
	}
}
