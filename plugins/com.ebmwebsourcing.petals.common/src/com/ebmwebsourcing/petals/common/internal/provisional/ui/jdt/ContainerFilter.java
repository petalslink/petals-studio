/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.ui.jdt;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters out all packages and folders
 *
 * TODO: move it to Eclipse STP
 * Comes from the plug-in "org.eclipse.jdt.ui".
 * @contributor Vincent Zurczak - EBM WebSourcing (added a public modifier on the class)
 */
public class ContainerFilter extends ViewerFilter {

	public static final boolean FILTER_CONTAINERS = true;
	public static final boolean FILTER_NON_CONTAINERS = false;
	private final boolean fFilterContainers;

	public ContainerFilter(boolean filterContainers) {
		this.fFilterContainers= filterContainers;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter
	 * #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		boolean isContainer= element instanceof IContainer;
		if (!isContainer && element instanceof IJavaElement) {
			int type= ((IJavaElement)element).getElementType();
			isContainer= type == IJavaElement.JAVA_MODEL
			|| type == IJavaElement.JAVA_PROJECT
			|| type == IJavaElement.PACKAGE_FRAGMENT
			|| type ==IJavaElement.PACKAGE_FRAGMENT_ROOT;
		}
		return (this.fFilterContainers && !isContainer) || (!this.fFilterContainers && isContainer);
	}
}
