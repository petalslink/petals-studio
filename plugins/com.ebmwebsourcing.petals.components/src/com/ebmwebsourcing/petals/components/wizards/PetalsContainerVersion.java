/******************************************************************************
 * Copyright (c) 2012, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.components.wizards;

/**
 * @author Vincent Zurczak - Linagora
 */
public enum PetalsContainerVersion {

	petals3_1, petals4_x;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		String result = "";
		switch( this ) {
		case petals3_1:
			result = "Petals 3.1.x";
			break;
		case petals4_x:
			result = "Petals 4.x";
			break;
		}

		return result;
	};
}
