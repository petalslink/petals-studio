package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class EEnumLiteralsProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		EEnum eEnum = (EEnum)inputElement;
		List<Object> res = new ArrayList<Object>();
		for (EEnumLiteral lit : eEnum.getELiterals()) {
			res.add(lit.getInstance());
		}
		return res.toArray();
	}

}
