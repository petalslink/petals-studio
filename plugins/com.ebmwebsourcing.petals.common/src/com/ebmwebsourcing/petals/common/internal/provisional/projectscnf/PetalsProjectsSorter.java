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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * Sort elements in the Petals projects view.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectsSorter extends ViewerSorter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator
	 * #compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare( Viewer viewer, Object e1, Object e2 ) {

		// The viewer can only contain IResources (projects, folders, files)
		// and categories (IWorkbenchAdapters)
		if( e1 instanceof IResource && e2 instanceof IResource ) {
			IResource r1 = (IResource) e1;
			IResource r2 = (IResource) e2;

			if( r1.getType() == r2.getType()) {
				if( r1.getType() == IResource.FILE ) {
					if( "jbi.xml".equals( r1.getName()))
						return -1;
					else if( "jbi.xml".equals( r2.getName()))
						return 1;

					String ext1 = r1.getFileExtension();
					String ext2 = r2.getFileExtension();
					if( ext1 == null )
						return ext2 == null ? 0 : -1;

					if( ext2 == null )
						return 1;

					if( ! ext1.equals( ext2 ))
						return ext1.compareTo( ext2 );
				}

				return r1.getName().compareTo( r2.getName());
			}

			if( r1.getType() == IResource.PROJECT )
				return -2;

			if( r2.getType() == IResource.PROJECT )
				return 2;

			if( r1.getType() == IResource.FOLDER )
				return -1;

			if( r2.getType() == IResource.FOLDER )
				return 1;

			return 0;
		}

		// Java resources are displayed first
		if( e1 instanceof IJavaElement && !( e2 instanceof IJavaElement ))
			return -1;

		if( e2 instanceof IJavaElement && !( e1 instanceof IJavaElement ))
			return 1;

		if( e2 instanceof IJavaElement && e1 instanceof IJavaElement )
			return compareJavaElements((IJavaElement) e1, (IJavaElement) e2 );

		// Comparison of categories are specific
		if( e1 instanceof PetalsProjectCategory && e2 instanceof PetalsProjectCategory ) {
			int n1 = ((PetalsProjectCategory) e1).getDisplayOrder();
			int n2 = ((PetalsProjectCategory) e2).getDisplayOrder();
			return n1 - n2;
		}

		// Otherwise, make the other comparisons
		if( e1 instanceof IWorkbenchAdapter && !( e1 instanceof IResource )) {

			if( e2 instanceof IWorkbenchAdapter && !( e2 instanceof IResource )) {
				String label1 = ((IWorkbenchAdapter) e1).getLabel( e1 );
				String label2 = ((IWorkbenchAdapter) e2).getLabel( e2 );
				return label1.compareTo( label2 );
			}

			return -1;
		}

		if( e2 instanceof IWorkbenchAdapter && !( e2 instanceof IResource ))
			return 1;

		return super.compare( viewer, e1, e2 );
	}


	/**
	 * Compares Java elements.
	 * @param j1
	 * @param j2
	 * @return an integer for sorting (0 if equivalent, <0 to display j1 first, >0 to display j2 first)
	 */
	private int compareJavaElements( IJavaElement j1, IJavaElement j2 ) {

		// Fragment roots have a special treatment
		if( j1 instanceof IPackageFragmentRoot && j2 instanceof IPackageFragmentRoot ) {
			IPackageFragmentRoot p1 = (IPackageFragmentRoot) j1;
			IPackageFragmentRoot p2 = (IPackageFragmentRoot) j2;

			try {
				if( p1.getKind() == p2.getKind())
					return p1.getElementName().compareTo( p2.getElementName());
				else if( p1.getKind() == IPackageFragmentRoot.K_SOURCE )
					return -1;
				else
					return 1;

			} catch( JavaModelException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}

			return p1.getElementName().compareTo( p2.getElementName());
		}

		// Otherwise, it depends on the element type
		if( j1.getElementType() == j2.getElementType())
			return j1.getElementName().compareTo( j2.getElementName());
		else
			return j1.getElementType() - j2.getElementType();
	}
}
