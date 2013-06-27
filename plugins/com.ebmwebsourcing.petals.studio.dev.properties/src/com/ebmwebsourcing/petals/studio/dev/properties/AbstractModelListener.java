/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties;

/**
 * A listener for abstract models.
 * @author Vincent Zurczak - Linagora
 */
public interface AbstractModelListener {

	/**
	 * Indicates a property changed.
	 * @param property the property name (may be null)
	 */
	void propertyChanged( String property );
}
