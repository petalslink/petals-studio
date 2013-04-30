/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.jbiproperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * A class in charge of managing the JBI properties of a Petals (SU) project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSPPropertiesManager {

	/**
	 * The name of the properties file
	 */
	public static final String PROPERTIES_FILENAME = ".petals.properties";

	/**
	 * The component name (as defined by the developer)
	 */
	public static final String COMPONENT_NAME = "petals.component.name";

	/**
	 * The component ID, which the component name but possibly updated for deployment reasons
	 */
	public static final String COMPONENT_DEPLOYMENT_ID = "petals.component.id";

	/**
	 * The component version
	 */
	public static final String COMPONENT_VERSION = "petals.component.version";

	/**
	 * The component function
	 */
	public static final String COMPONENT_FUNCTION = "petals.component.function";


	/**
	 * Loads the properties from a project.
	 * @param project any project (not mandatory a Petals project)
	 * @return a properties (never null, possibly empty)
	 */
	public static Properties getProperties( IProject project ) {
		return getProperties( project.getFile( PROPERTIES_FILENAME ).getLocation().toFile());
	}


	/**
	 * Loads the properties from a project.
	 * @param propertiesFile a properties file
	 * @return a properties (never null, possibly empty)
	 */
	public static Properties getProperties( File propertiesFile ) {

		// Get the properties
		Properties result = new Properties();
		if( propertiesFile.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream( propertiesFile );
				result.load( fis );

			} catch( FileNotFoundException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( IOException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} finally {
				try {
					if( fis != null )
						fis.close();

				} catch( IOException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		}

		// Set the default properties
		if( ! result.containsKey( COMPONENT_FUNCTION ))
			result.setProperty( COMPONENT_FUNCTION, "" );

		if( ! result.containsKey( COMPONENT_NAME ))
			result.setProperty( COMPONENT_NAME, "" );

		// By default, the component ID is the component name
		// See PETALSSTUD-143: Component type != Component ID
		if( ! result.containsKey( COMPONENT_DEPLOYMENT_ID ))
			result.setProperty( COMPONENT_DEPLOYMENT_ID, result.getProperty( COMPONENT_NAME ));

		if( ! result.containsKey( COMPONENT_VERSION ))
			result.setProperty( COMPONENT_VERSION, "" );

		return result;
	}


	/**
	 * Saves the Petals properties.
	 * @param properties the properties to save
	 * @param project a Petals SU project (the nature is checked)
	 * @throws IOException
	 */
	public static void saveProperties( Properties properties, IProject project ) throws IOException {

		String comment = "Created with Petals Studio.";
		IFile f = project.getFile( PROPERTIES_FILENAME );
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( f.getLocation().toFile());
			properties.store( fos, comment );

		} finally {
			try {
				if( fos != null )
					fos.close();

			} finally {
				try {
					f.refreshLocal( IResource.DEPTH_ZERO, null );
				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.WARNING );
				}
			}
		}
	}
}
