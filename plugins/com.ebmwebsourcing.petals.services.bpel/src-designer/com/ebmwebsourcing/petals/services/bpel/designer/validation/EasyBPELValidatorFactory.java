package com.ebmwebsourcing.petals.services.bpel.designer.validation;

import javax.xml.namespace.QName;

import org.eclipse.bpel.validator.model.IFactory;
import org.eclipse.bpel.validator.model.Validator;

public class EasyBPELValidatorFactory implements IFactory<Validator> {

	@Override
	public Validator create(QName qname) {
		return new EasyBPELValidator();
	}

}
