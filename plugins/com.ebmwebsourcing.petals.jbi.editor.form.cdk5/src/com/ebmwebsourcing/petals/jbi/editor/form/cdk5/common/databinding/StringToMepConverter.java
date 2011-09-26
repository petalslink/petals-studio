package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.common.databinding;

import org.eclipse.core.databinding.conversion.Converter;

import com.ebmwebsourcing.petals.common.generation.Mep;

public class StringToMepConverter extends Converter {

	public StringToMepConverter() {
		super(String.class, Mep.class);
	}

	@Override
	public Object convert(Object fromObject) {
		return Mep.valueOf((String)fromObject);
	}

}
