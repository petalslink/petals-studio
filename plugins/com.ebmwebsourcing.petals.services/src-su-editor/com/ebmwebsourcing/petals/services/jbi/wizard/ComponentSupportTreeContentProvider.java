package com.ebmwebsourcing.petals.services.jbi.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentCategory;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentCategoryUtils;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;

public class ComponentSupportTreeContentProvider implements	ITreeContentProvider {

	public enum SUType {
		PROVIDES, CONSUMES
	}
	
	private SUType suType;
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.suType = (SUType)newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return ComponentCategoryUtils.getInstance().getAllCategories().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<ComponentSupportExtensionDesc> res = new ArrayList<ComponentSupportExtensionDesc>();
		if (parentElement instanceof ComponentCategory) {
			for (ComponentSupportExtensionDesc component : ((ComponentCategory)parentElement).getComponents()) {
				if (suType == SUType.PROVIDES && component.supportsProvides()) {
					res.add(component);
				} else if (suType == SUType.CONSUMES && component.supportsConsumes()) {
					res.add(component);
				}
			}
			return res.toArray();
		} else {
			return null;
		}
	}

	@Override
	public Object getParent(Object element) {
		// unused
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof ComponentCategory;
	}

}
