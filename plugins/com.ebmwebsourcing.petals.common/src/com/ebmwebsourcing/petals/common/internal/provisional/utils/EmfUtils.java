/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.util.Iterator;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EmfUtils {

	/**
	 * @param object
	 * @return
	 * FIXME: we do not handle attributes
	 */
	public static String getXpathExpression( EObject object ) {

		// Keep the found path elements
		StringBuilder sb = new StringBuilder();

		// Start looping...
		while( object != null ) {
			if( object.eContainmentFeature() != null ) {

				// We need the element name
				String name = ExtendedMetaData.INSTANCE.getName( object.eContainmentFeature());

				// Collection? Count the number of occurrences for this element.
				String index = "";
				if( object.eContainingFeature().isMany()) {
					Object o = object.eContainer().eGet( object.eContainingFeature());

					int pos = -1;
					if( o instanceof FeatureMap ) {
						for( Iterator<FeatureMap.Entry> it = ((FeatureMap) o).iterator(); it.hasNext(); ) {
							FeatureMap.Entry unknownObject = it.next();
							String unknownName = ExtendedMetaData.INSTANCE.getName( unknownObject.getEStructuralFeature());
							if( name.equals( unknownName )) {
								pos ++;
								if( object.equals( unknownObject.getValue()))
									break;
							}
						}

						index = "[" + (pos+1) + "]";
					}

					// Other EList instances
					else if( o instanceof EList<?> ) {
						for( Object oo : (EList<?>) o ) {
							if( oo instanceof EObject ) {
								String unknownName = ExtendedMetaData.INSTANCE.getName(((EObject) oo).eContainmentFeature());
								if( name.equals( unknownName )) {
									pos ++;
									if( object.equals( oo ))
										break;
								}
							}
						}

						index = "[" + (pos+1) + "]";
					}
				}

				// Name
				sb.insert( 0, "/*[local-name()='" + name + "']" + index );
			}

			// Prepare the next loop
			object = object.eContainer();

		}	// End of WHILE

		return sb.toString();
	}


	/**
	 * Converts an EMF URI to a java.net.URI.
	 * <p>
	 * This is only interesting for local files.
	 * </p>
	 *
	 * @param emfUri an EMF URI (may have specific schemes)
	 * @return a java.net.URI, or null if it could not be converted
	 */
	public static java.net.URI convertEmfUriToJavaNetUri( org.eclipse.emf.common.util.URI emfUri ) {

		File f = getFileFromEmfUri( emfUri );
		return f == null ? UriAndUrlHelper.urlToUri( emfUri.toString()) : f.toURI();
	}


	/**
	 * Converts an EMF URI to a java.net.URI.
	 * <p>
	 * This is only interesting for local files.
	 * </p>
	 *
	 * @param emfUri an EMF URI (may have specific schemes)
	 * @return a java.net.URI, or null if it could not be converted
	 */
	public static File getFileFromEmfUri( org.eclipse.emf.common.util.URI emfUri ) {

		File file = null;
		if( emfUri.isFile())
			file = new File( emfUri.toFileString());
		else if( emfUri.isPlatform()) {
			IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			path = path.append( emfUri.toPlatformString( true ));
			file = path.toFile();
		}

		return file;
	}


	/**
	 * Gets the file associated with an EObject.
	 * @param eo the EObject
	 * @return the associated file (may be null if the URI could not be resolved)
	 */
	public static File getUnderlyingFile( EObject eo ) {
		org.eclipse.emf.common.util.URI emfUri = eo.eResource().getURI();
		return getFileFromEmfUri( emfUri );
	}
}
