/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.bpel.designer.validation;

import javax.xml.namespace.QName;

import org.eclipse.bpel.validator.model.IFactory;
import org.eclipse.bpel.validator.model.Validator;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class EasyBPELValidatorFactory implements IFactory<Validator> {

	@Override
	public Validator create(QName qname) {
		return new EasyBPELValidator();
	}
}
