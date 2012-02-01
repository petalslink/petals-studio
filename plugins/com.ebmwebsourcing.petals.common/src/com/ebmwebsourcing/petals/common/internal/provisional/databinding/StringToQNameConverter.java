/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.databinding;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.conversion.IConverter;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class StringToQNameConverter implements IConverter {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #getFromType()
	 */
	@Override
	public Object getFromType() {
		return String.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #getToType()
	 */
	@Override
	public Object getToType() {
		return QName.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #convert(java.lang.Object)
	 */
	@Override
	public Object convert( Object fromObject ) {

		QName result;
		String qname = String.valueOf( fromObject );
		if( NamespaceUtils.isShortenNamespace( qname ))
			result = NamespaceUtils.buildQName( qname );
		else
			result = new QName( qname );

		return result;
	}
}
