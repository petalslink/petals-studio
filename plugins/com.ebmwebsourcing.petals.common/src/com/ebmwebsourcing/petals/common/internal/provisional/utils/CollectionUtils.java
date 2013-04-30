/****************************************************************************
 *
 * Copyright (c) 2012-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CollectionUtils {

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
