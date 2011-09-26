package com.ebmwebsourcing.petals.jbi.editor.form.common.databinding;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.conversion.IConverter;

public class StringToQNameConverter implements IConverter {

	@Override
	public Object getFromType() {
		return String.class;
	}

	@Override
	public Object getToType() {
		return QName.class;
	}

	@Override
	public Object convert(Object fromObject) {
		String qname = (String)fromObject;
		if (qname.contains("{") && qname.contains("}")) {
			String namespace = qname.substring(qname.indexOf("{") + 1, qname.indexOf("}"));
			String local = qname.substring(qname.indexOf("}") + 1, qname.length());
			return new QName(namespace, local);
		} else {
			return new QName(qname);
		}
	}

}
