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
 
package com.ebmwebsourcing.petals.services.sql.wizards;

import com.ebmwebsourcing.petals.services.sql.SqlDescription15;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;

/**
 * @author Vincent Zurczak - Linagora
 */
public class SqlProvidesWizard15 extends SqlProvidesWizard13 {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SqlDescription15();
	}
}
