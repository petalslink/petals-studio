package com.ebmwebsourcing.petals.services.jbi.editor.common.databinding;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.conversion.IConverter;

public class QNameToStringConverter implements IConverter {

	@Override
	public Object getFromType() {
		return QName.class;
	}

	@Override
	public Object getToType() {
		return String.class;
	}

	@Override
	public Object convert(Object fromObject) {
		return ((QName)fromObject).toString();
	}

}
