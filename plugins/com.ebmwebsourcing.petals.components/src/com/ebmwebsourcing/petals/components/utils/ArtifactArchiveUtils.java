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
package com.ebmwebsourcing.petals.components.utils;

import java.io.File;
import java.net.URI;
import java.util.Properties;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A set of helpers related to zipped components and shared libraries.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ArtifactArchiveUtils {

	public static String SL_VERSION = "sl-version";
	public static String SL_NAME = "sl-name";


	/**
	 * Gets the version of the shared library from its JBI descriptor.
	 * @param slFile the shared library file
	 * @return a properties containing the keys {@link #SL_NAME} and {@link #SL_VERSION}
	 * @throws InvalidJbiXmlException
	 */
	public static Properties getSharedLibraryVersion( File slFile ) throws InvalidJbiXmlException {
		return getSharedLibraryVersion( slFile.toURI());
	}


	/**
	 * Gets the version of the shared library from its JBI descriptor.
	 * @param slUri the shared library URI
	 * @return a properties containing the keys {@link #SL_NAME} and {@link #SL_VERSION}
	 * @throws InvalidJbiXmlException
	 */
	public static Properties getSharedLibraryVersion( URI slUri ) throws InvalidJbiXmlException {

		Jbi jbi = JbiXmlUtils.getJbiXmlModel( slUri );
		Properties result = new Properties();
		if( jbi != null
				&& jbi.getSharedLibrary() != null ) {

			if( jbi.getSharedLibrary().getVersion() instanceof String )
				result.put( SL_VERSION, jbi.getSharedLibrary().getVersion());

			if( jbi.getSharedLibrary().getIdentification() != null )
				result.put( SL_NAME, jbi.getSharedLibrary().getIdentification().getName());
		}

		return result;
	}
}
