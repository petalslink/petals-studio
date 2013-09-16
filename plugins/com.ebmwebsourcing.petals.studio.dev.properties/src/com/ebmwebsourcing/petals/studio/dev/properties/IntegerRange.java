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
 
package com.ebmwebsourcing.petals.studio.dev.properties;

/**
 * A class to define a range of integers.
 * @author Vincent Zurczak - Linagora
 */
public class IntegerRange {

	private final int min, max;
	private final boolean includeMin, includeMax;


	/**
	 * Constructor.
	 * @param min
	 * @param max
	 * @param minInclusive
	 * @param includeMax
	 */
	public IntegerRange( int min, int max, boolean includeMin, boolean includeMax ) {
		this.min = min;
		this.max = max;
		this.includeMin = includeMin;
		this.includeMax = includeMax;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return this.min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return this.max;
	}

	/**
	 * @return the includeMin
	 */
	public boolean isMinIncluded() {
		return this.includeMin;
	}

	/**
	 * @return the includeMax
	 */
	public boolean isMaxIncluded() {
		return this.includeMax;
	}
}
