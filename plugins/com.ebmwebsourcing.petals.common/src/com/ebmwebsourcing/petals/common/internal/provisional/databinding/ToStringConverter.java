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

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class ToStringConverter implements IConverter {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #getFromType()
	 */
	@Override
	public Object getFromType() {
		return Object.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #getToType()
	 */
	@Override
	public Object getToType() {
		return String.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #convert(java.lang.Object)
	 */
	@Override
	public Object convert( Object fromObject ) {
		return fromObject == null ? null : fromObject.toString();
	}
}
