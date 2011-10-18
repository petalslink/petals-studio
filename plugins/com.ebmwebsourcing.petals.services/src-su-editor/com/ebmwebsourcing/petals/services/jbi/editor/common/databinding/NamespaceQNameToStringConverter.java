package com.ebmwebsourcing.petals.services.jbi.editor.common.databinding;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.conversion.Converter;

public class NamespaceQNameToStringConverter extends Converter {

	public NamespaceQNameToStringConverter() {
		super(QName.class, String.class);
	}

	@Override
	public Object convert(Object fromObject) {
		return ((QName)fromObject).getNamespaceURI();
	}

}
