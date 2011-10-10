package com.ebmwebsourcing.petals.services.jbi.editor.common.databinding;

import org.eclipse.core.databinding.conversion.IConverter;

public class ToStringConverter implements IConverter {

	@Override
	public Object getFromType() {
		return Object.class;
	}

	@Override
	public Object getToType() {
		return String.class;
	}

	@Override
	public Object convert(Object fromObject) {
		return fromObject.toString();
	}

}
