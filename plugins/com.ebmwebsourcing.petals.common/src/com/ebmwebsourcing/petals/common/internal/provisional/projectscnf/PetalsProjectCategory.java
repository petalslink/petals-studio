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

package com.ebmwebsourcing.petals.common.internal.provisional.projectscnf;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.navigator.IDescriptionProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class PetalsProjectCategory implements IDescriptionProvider {

	/**
	 * Determines whether a project matches a certain criteria.
	 * @param project
	 * @return
	 */
	public abstract boolean projectMatches( IProject project );


	/**
	 * @return an image descriptor
	 */
	public abstract ImageDescriptor getImageDescriptor();


	/**
	 * @return the label for this category
	 */
	public abstract String getLabel();


	/**
	 * @return an integer than defines the display order in the projects view.
	 * <p>
	 * Lowest values are displayed first.
	 * </p>
	 */
	public abstract int getDisplayOrder();


	/**
	 * @return the category ID
	 */
	public abstract String getId();


	/**
	 * Determines if a matching project is visible or if only its children are displayed.
	 * @return true to make the root visible, false to only show its children
	 */
	public boolean isRootProjectVisible() {
		return true;
	}
}
