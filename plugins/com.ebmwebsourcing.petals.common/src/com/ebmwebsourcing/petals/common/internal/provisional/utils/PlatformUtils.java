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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.swt.graphics.FontData;

/**
 * A set of utilities for SWT and JFace.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class PlatformUtils {

	/**
	 * Private constructor for utility class.
	 */
	private PlatformUtils() {
		// nothing
	}


	/**
	 * @param originalData
	 * @param additionalStyle
	 * @return
	 */
	public static FontData[] getModifiedFontData( FontData[] originalData, int additionalStyle ) {

		FontData[] styleData = new FontData[ originalData.length ];
		for( int i=0; i<styleData.length; i++ ) {
			FontData base = originalData[ i ];
			styleData[ i ] = new FontData( base.getName(), base.getHeight(), base.getStyle() | additionalStyle );
		}

		return styleData;
	}


	/**
	 * @param originalData
	 * @param height
	 * @return
	 */
	public static FontData[] changeFontDataSize( FontData[] originalData, int height ) {

		FontData[] styleData = new FontData[ originalData.length ];
		for( int i=0; i<styleData.length; i++ ) {
			FontData base = originalData[ i ];
			styleData[ i ] = new FontData( base.getName(), height, base.getStyle());
		}

		return styleData;
	}


	/**
	 * Gets the appropriate adapter.
	 * @param sourceObject
	 * @param adapterType
	 * @return
	 */
	public static Object getAdapter( Object sourceObject, Class<?> adapterType ) {

		Assert.isNotNull(adapterType);
		if (sourceObject == null) {
			return null;
		}
		if (adapterType.isInstance(sourceObject)) {
			return sourceObject;
		}

		if (sourceObject instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) sourceObject;

			Object result = adaptable.getAdapter(adapterType);
			if (result != null) {
				// Sanity-check
				Assert.isTrue(adapterType.isInstance(result));
				return result;
			}
		}

		if (!(sourceObject instanceof PlatformObject)) {
			Object result = Platform.getAdapterManager().getAdapter(sourceObject, adapterType);
			if (result != null) {
				return result;
			}
		}

		return null;
	}


	/**
	 * Gets the project associated to this object.
	 * <p>
	 * If the element is adaptable to an IProject, then this project is returned.
	 * Otherwise, if the element is adaptable to an IResource, then the resource's project is returned.
	 * </p>
	 *
	 * @param o an object (not null)
	 * @return the associated project (or null if none was found)
	 */
	public static IProject getAdaptedProject( Object o ) {

		IProject project = (IProject) PlatformUtils.getAdapter( o, IProject.class );
		if( project == null ) {
			IResource res = (IResource) PlatformUtils.getAdapter( o, IResource.class );
			if( res != null )
				project = res.getProject();
		}

		return project;
	}
}
