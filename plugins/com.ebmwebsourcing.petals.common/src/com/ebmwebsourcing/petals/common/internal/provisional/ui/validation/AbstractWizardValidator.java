/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.ui.validation;

import java.lang.reflect.ParameterizedType;

/**
 * An abstract class to validate of an object of a given class.
 * 
 * @param <T> the type of the object to validate and to display
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractWizardValidator<T> {

	/**
	 * @return the generic type if it can be computed, <code>Object.class</code> otherwise
	 */
	public Class<?> getGenericType() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<?>) pt.getActualTypeArguments()[ 0 ];
	}

	/**
	 * Validate an object whose class is the generic type.
	 * 
	 * @param value the object to validate
	 * @return <code>null</code> if the validation succeeds, an error message otherwise
	 */
	public abstract String validate( T value );
}
