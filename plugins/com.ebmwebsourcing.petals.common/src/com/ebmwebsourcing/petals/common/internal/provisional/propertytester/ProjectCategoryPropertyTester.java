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
 
package com.ebmwebsourcing.petals.common.internal.provisional.propertytester;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * A property tester to check whether an SU project is a sketch project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProjectCategoryPropertyTester extends PropertyTester {

	/**
	 * Constructor.
	 */
	public ProjectCategoryPropertyTester() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester
	 * #test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test( Object receiver, String property, Object[] args, Object expectedValue ) {

		boolean result = false;
		if( expectedValue instanceof String ) {
			if( receiver instanceof PetalsProjectCategory )
				result = expectedValue.equals(((PetalsProjectCategory) receiver).getId());

			else {
				// Find a resource
				IResource res = (IResource) PlatformUtils.getAdapter( receiver, IResource.class );

				// Case "external libraries"
				if( res == null && receiver instanceof IJavaElement ) {
					try {
						res = ((IJavaElement) receiver).getParent().getCorrespondingResource();

					} catch( JavaModelException e ) {
						PetalsCommonPlugin.log( e, IStatus.ERROR );
					}
				}

				// Find the associated category - and manage some exceptional cases
				if( receiver instanceof IClasspathContainer ) {
					result = false;

				} else if( res == null ) {
					// Ignore it
					// Try the DnD of an end-point bean on the viewer to see why...

				} else if( ! res.isAccessible()) {
					result = true;

				} else {
					List<PetalsProjectCategory> cats = PetalsProjectManager.INSTANCE.getCategories( res.getProject());
					if( cats != null ) {
						for( PetalsProjectCategory cat : cats ) {
							if( expectedValue.equals( cat.getId())) {
								result = true;
								break;
							}
						}
					}
				}
			}
		}

		return result;
	}
}
