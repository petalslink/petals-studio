/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.projectscnf;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class AttachedSuProjectsFilter extends ViewerFilter {

	/**
	 * Constructor.
	 */
	public AttachedSuProjectsFilter() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter
	 * #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select( Viewer viewer, Object parentElement, Object element ) {

		boolean result = true;
		PetalsProjectCategory category = null;
		if( parentElement instanceof PetalsProjectCategory ) {
			category = (PetalsProjectCategory) parentElement;
		}
		else if( parentElement instanceof TreePath ) {
			Object o = ((TreePath) parentElement).getFirstSegment();
			if( o instanceof PetalsProjectCategory )
				category = (PetalsProjectCategory) o;
		}

		boolean isInSuCategory = false;
		if( category != null
					&& category.getId().equals( SuProjectCategory.SU_CATEGORY_ID ))
			isInSuCategory = true;

		if( element instanceof IProject && isInSuCategory ) {
			IProject p = (IProject) element;
			result = p.isAccessible() && ServiceProjectRelationUtils.getOrphanSuProjects().contains( p );
		}

		return result;
	}
}
