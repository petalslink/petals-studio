/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.swt.graphics.FontData;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A set of utilities for SWT and JFace.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PlatformUtils {

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


	/**
	 * Loads a plug-in resource.
	 * @param pluginId the plug-in ID
	 * @param relativeLocation the relative location (must start with '/')
	 * @return an input stream (possibly null)
	 */
	public static InputStream loadPluginResource( String pluginId, String relativeLocation ) {

		InputStream inputStream = null;
		try {
			URL url = new URL( "platform:/plugin/" + pluginId + relativeLocation );
		    inputStream = url.openConnection().getInputStream();

		} catch( IOException e ) {
		    PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return inputStream;
	}
}
