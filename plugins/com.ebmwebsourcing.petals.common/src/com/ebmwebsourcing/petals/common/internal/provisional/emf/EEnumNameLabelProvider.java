package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

public class EEnumNameLabelProvider extends LabelProvider implements IBaseLabelProvider {
	
	@Override
	public String getText(Object e) {
		if (e instanceof Enumerator) {
			return ((Enumerator)e).getLiteral();
		} else {
			return super.getText(e);
		}
	}

}
