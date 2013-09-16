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
 
package com.ebmwebsourcing.petals.common.internal.provisional.projectscnf;

import java.util.List;

import org.eclipse.core.resources.IProject;

import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;

/**
 * A proxy class to access some (API) methods from {@link PetalsProjectManager}.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectProxyManager {

	/**
	 * @param categoryId the category ID
	 * @return the associated projects (can be null)
	 */
	public List<IProject> getProjects( String categoryId ) {
		return PetalsProjectManager.INSTANCE.getProjects( categoryId );
	}
}
