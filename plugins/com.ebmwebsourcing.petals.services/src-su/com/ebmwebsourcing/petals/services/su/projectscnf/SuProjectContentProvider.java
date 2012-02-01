/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.projectscnf;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.AbstractTreeViewer;

import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectContentProvider;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuProjectContentProvider extends PetalsProjectContentProvider {

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectContentProvider
	 * #resourcesRemoved(java.util.Collection)
	 */
	@Override
	public void resourcesRemoved( Collection<IResource> resources ) {

		if( !( this.viewer instanceof AbstractTreeViewer ))
			return;

		// Deletion of SA projects => refresh the SU category
		// Associations are cached, so refreshing does not take long
		for( final IResource res : resources ) {
			if( res instanceof IProject ) {

				PetalsProjectCategory cat = getProjectCategoryById( SuProjectCategory.SU_CATEGORY_ID );
				if( cat == null )
					PetalsServicesPlugin.log( "The SU project category could not be found.", IStatus.ERROR );
				else
					this.runnables.add( getRefreshRunnable( cat ));
			}
		}

		// And always apply the basic treatment
		super.resourcesRemoved( resources );
	}
}
