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
 
package com.ebmwebsourcing.petals.services.su.propertytester;

import java.util.Properties;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.su.nature.SuNature;

/**
 * A property tester to test the target component of a SU project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentPropertyTester extends PropertyTester {

	/**
	 * Constructor.
	 */
	public ComponentPropertyTester() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester
	 * #test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test( Object receiver, String property, Object[] args, Object expectedValue ) {

		boolean result = false;
		if( receiver instanceof IResource
					&& expectedValue instanceof String ) {

			IProject p = ((IResource) receiver).getProject();
			try {
				if( p.isAccessible()
							&& p.hasNature( SuNature.NATURE_ID )) {

					Properties prop = PetalsSPPropertiesManager.getProperties( p );
					String component = prop.getProperty( PetalsSPPropertiesManager.COMPONENT_NAME );
					result = ((String) expectedValue).equalsIgnoreCase( component );
				}

			} catch( CoreException e ) {
				// nothing
			}
		}

		return result;
	}
}
