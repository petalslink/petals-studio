/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
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
