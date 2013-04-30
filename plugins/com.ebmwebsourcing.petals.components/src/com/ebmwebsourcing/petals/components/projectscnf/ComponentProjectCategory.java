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
package com.ebmwebsourcing.petals.components.projectscnf;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;

import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentProjectCategory extends PetalsProjectCategory {

	public static String COMPONENT_CATEGORY_ID = "petals.category.components";


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return PetalsComponentsPlugin.getImageDescriptor( "icons/obj16/component.png" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getLabel()
	 */
	@Override
	public String getLabel() {
		return "JBI Components";
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.IDescriptionProvider
	 * #getDescription(java.lang.Object)
	 */
	@Override
	public String getDescription( Object o ) {
		return "The component projects for Petals ESB";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getDisplayOrder()
	 */
	@Override
	public int getDisplayOrder() {
		return 1;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #getId()
	 */
	@Override
	public String getId() {
		return COMPONENT_CATEGORY_ID;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory
	 * #projectMatches(org.eclipse.core.resources.IProject)
	 */
	@Override
	public boolean projectMatches( IProject project ) {

		boolean result = false;
		if( project.exists()) {
			File jbiXmlFile = project.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
			if( ! jbiXmlFile.exists())
				jbiXmlFile = project.getLocation().append( PetalsConstants.NEW_LOC_JBI_FILE ).toFile();

			result = jbiXmlFile.exists() && JbiXmlUtils.describesComponent( jbiXmlFile );
		}

		return result;
	};
}
