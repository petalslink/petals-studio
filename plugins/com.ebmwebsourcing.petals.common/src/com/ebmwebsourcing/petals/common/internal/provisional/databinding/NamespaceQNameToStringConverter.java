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

package com.ebmwebsourcing.petals.common.internal.provisional.databinding;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.conversion.Converter;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class NamespaceQNameToStringConverter extends Converter {

	/**
	 * Constructor.
	 */
	public NamespaceQNameToStringConverter() {
		super( QName.class, String.class );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #convert(java.lang.Object)
	 */
	@Override
	public Object convert(Object fromObject) {
		return ((QName)fromObject).getNamespaceURI();
	}
}
