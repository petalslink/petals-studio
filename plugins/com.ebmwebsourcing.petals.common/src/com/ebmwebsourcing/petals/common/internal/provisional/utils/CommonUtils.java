/******************************************************************************
 * Copyright (c) 2013, Linagora
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

/**
 * @author Vincent Zurczak - Linagora
 */
public class CommonUtils {

	/**
	 * @param o1
	 * @param o2
	 * @return true either if both objects are null or if they are equal
	 */
	public static boolean areEqual( Object o1, Object o2 ) {
		return o1 == null ? o2 == null : o1.equals( o2 );
	}
}
