/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
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
