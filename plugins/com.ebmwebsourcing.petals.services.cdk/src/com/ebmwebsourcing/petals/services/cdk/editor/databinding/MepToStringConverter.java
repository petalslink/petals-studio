/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
