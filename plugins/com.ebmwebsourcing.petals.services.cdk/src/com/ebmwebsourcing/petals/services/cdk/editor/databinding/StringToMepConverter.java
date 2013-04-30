/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
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
 * @author Mickael Istria - EBM WebSourcing
 */
public class StringToMepConverter extends Converter {

	public StringToMepConverter() {
		super( Mep.class, String.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter
	 * #convert(java.lang.Object)
	 */
	@Override
	public Object convert( Object fromObject ) {
		Mep mep = Mep.whichMep((String) fromObject);
		return mep;
	}
}
