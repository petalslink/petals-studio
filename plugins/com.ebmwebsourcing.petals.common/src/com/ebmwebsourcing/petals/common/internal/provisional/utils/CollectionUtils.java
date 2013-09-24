/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class CollectionUtils {

	/**
	 * Private constructor for utility class.
	 */
	private CollectionUtils() {
		// nothing
	}

	/**
	 * Converts a collection into an array.
	 * @param collection the collection to convert
	 * @param clazz the class of the items in the collections
	 * @return a non-null array
	 */
	public static <T> T[] convertToArray( Collection<T> collection, Class<T> clazz ) {
		T[] result = (T[]) Array.newInstance( clazz, collection.size());
		return collection.toArray( result );
	}
}
