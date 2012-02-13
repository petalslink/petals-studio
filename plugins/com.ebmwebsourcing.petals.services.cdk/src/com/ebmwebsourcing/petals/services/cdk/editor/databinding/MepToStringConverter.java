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

package com.ebmwebsourcing.petals.services.cdk.editor.databinding;

import org.eclipse.core.databinding.conversion.Converter;

import com.ebmwebsourcing.petals.common.generation.Mep;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MepToStringConverter extends Converter {

	public MepToStringConverter() {
		super( String.class, Mep.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #convert(java.lang.Object)
	 */
	@Override
	public Object convert( Object fromObject ) {
		Mep mep = (Mep) fromObject;
		return mep == Mep.UNKNOWN ? null : mep.toString();
	}
}
