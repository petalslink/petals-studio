/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.croquis.internal.projectscnf;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;

import com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CroquisProjectCategory extends PetalsProjectCategory {

	public static final String CROQUIS_CATEGORY_ID = "petals.category.croquis";


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return PetalsCommonPlugin.getImageDescriptor( "icons/obj16/croquis.png" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getLabel()
	 */
	@Override
	public String getLabel() {
		return "Croquis";
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.IDescriptionProvider
	 * #getDescription(java.lang.Object)
	 */
	public String getDescription( Object o ) {
		return "The croquis for Petals ESB";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getDisplayOrder()
	 */
	@Override
	public int getDisplayOrder() {
		return 6;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getId()
	 */
	@Override
	public String getId() {
		return CROQUIS_CATEGORY_ID;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #projectMatches(org.eclipse.core.resources.IProject)
	 */
	@Override
	public boolean projectMatches( IProject project ) {

		/* In a first time, we assume croquis will be limited to folders.
		 * If by any (bad) luck, a croquis had to be an IProject (e.g. for Java reasons), then we would see...
		 */
		boolean result = false;
		if( project.exists()
					&& ICroquisExtension.CROQUIS_PROJECT_NAME.equals( project.getName())) {
			result = true;
		}

		return result;
	};


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #isRootProjectVisible()
	 */
	@Override
	public boolean isRootProjectVisible() {
		return false;
	}
}
