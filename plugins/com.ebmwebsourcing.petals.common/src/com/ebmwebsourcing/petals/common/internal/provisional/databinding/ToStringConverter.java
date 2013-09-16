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
 
package com.ebmwebsourcing.petals.common.internal.provisional.databinding;

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * @author Micka�l Istria - EBM WebSourcing
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
