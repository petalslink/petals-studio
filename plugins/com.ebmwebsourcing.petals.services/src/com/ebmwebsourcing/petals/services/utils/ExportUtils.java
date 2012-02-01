/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils.ExportableClassPath;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExportUtils {

	/**
	 * Exports one or several SU projects into a service assembly.
	 *
	 * @param saName the SA name (SA file name = SA name + ".zip")
	 * @param targetSaPath the absolute file path to the SA file
	 * @param projects the projects to package
	 * @param monitor the progress monitor
	 * @return a status indicating the result of the package operation
	 */
	public static IStatus exportSuProject(
				String saName,
				String targetSaPath,
				Collection<IProject> projects,
				IProgressMonitor monitor ) {

		IStatus result = Status.OK_STATUS;
		Map<String,File> suEntryNameToSuFile = new HashMap<String,File>( projects.size());
		Map<String,String> suNameToComponentName = new HashMap<String,String>( projects.size());

		// Package the SU(s)
		for( IProject p : projects ) {
			if( ! p.isAccessible())
				continue;

			String errorMsgPrefix = p.getName() + ": ";
			File suFile = null;
			try {
				// Check the component name
				Properties pProperties = PetalsSPPropertiesManager.getProperties( p );
				String componentName= pProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, "" );
				if( StringUtils.isEmpty( componentName ))
					throw new NullPointerException( "The component name is not defined." );

				// Package the project
				String suName = p.getName();
				suFile = packageSuProject( p, suName, monitor );
				suEntryNameToSuFile.put( suName + ".zip", suFile );
				suNameToComponentName.put( suName, componentName );

			} catch( FileNotFoundException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( IOException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( NullPointerException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( CoreException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;
			}
		}

		// Package the SA
		try {
			if( result.isOK()) {
				String saJbiXml = JbiUtils.generateJbiXmlForSA( saName, suNameToComponentName );
				File saFile = JbiUtils.createJbiArchive( targetSaPath, saJbiXml, suEntryNameToSuFile );
				assert saFile != null && saFile.exists();
			}

		} catch( IOException e ) {
			result = StatusUtils.createStatus( e, "SA creation: " );

		} finally {

			// Delete the temporary SU files
			for( File suFile : suEntryNameToSuFile.values()) {
				if( suFile != null
							&& suFile.exists()
							&& ! suFile.delete())
					suFile.deleteOnExit();
			}
		}

		return result;
	}


	/**
	 * Exports one or several SU projects into a service assembly.
	 *
	 * @param outputDirectory the output directory
	 * @param saProject the SA project to package
	 * @param monitor the progress monitor
	 * @return a status indicating the result of the package operation
	 */
	public static IStatus exportSaProject( String outputDirectory, IProject saProject, IProgressMonitor monitor ) {

		// Get the SU projects
		List<IProject> projects = new ArrayList<IProject>();
		try {
			for( IProject refP : saProject.getReferencedProjects()) {
				if( refP.isAccessible() && ServiceProjectRelationUtils.isSuProject( refP ))
					projects.add( refP );
			}

		} catch( CoreException e1 ) {
			PetalsServicesPlugin.log( e1, IStatus.ERROR );
		}

		IStatus result = Status.OK_STATUS;
		Map<String,File> suEntryNameToSuFile = new HashMap<String,File>( projects.size());

		// Package the SU(s)
		for( IProject p : projects ) {
			String errorMsgPrefix = p.getName() + ": ";
			File suFile = null;
			try {
				String suName = p.getName();
				suFile = packageSuProject( p, suName, monitor );
				suEntryNameToSuFile.put( suName + ".zip", suFile );

			} catch( FileNotFoundException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( IOException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( NullPointerException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;

			} catch( CoreException e ) {
				result = StatusUtils.createStatus( e, errorMsgPrefix );
				break;
			}
		}

		// Package the SA
		try {
			if( result.isOK()) {
				IFile jbiXmlFile = saProject.getFile( PetalsConstants.LOC_JBI_FILE );
				InputStream is = jbiXmlFile.getContents();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IoUtils.copyStream( is, os );
				is.close();

				String saJbiXml = os.toString( "UTF-8" );
				String targetSaPath = new File( outputDirectory, saProject.getName() + ".zip" ).getAbsolutePath();
				File saFile = JbiUtils.createJbiArchive( targetSaPath, saJbiXml, suEntryNameToSuFile );
				assert saFile != null && saFile.exists();
			}

		} catch( IOException e ) {
			result = StatusUtils.createStatus( e, "SA creation: " );

		} catch( CoreException e ) {
			result = StatusUtils.createStatus( e, "SA creation: " );

		} finally {

			// Delete the temporary SU files
			for( File suFile : suEntryNameToSuFile.values()) {
				if( suFile != null
							&& suFile.exists()
							&& ! suFile.delete())
					suFile.deleteOnExit();
			}
		}

		return result;
	}


	/**
	 * Exports one bulk SU into a service assembly.
	 *
	 * @param suName the SU name (SU file name = SU name + ".zip")
	 * @param saName
	 * @param targetSaPath the absolute file path to the SA file
	 * @param componentName the component name
	 * @param tempDir the project root file
	 * @return a status indicating the result of the package operation
	 *
	 * FIXME: do we need to return a status? The result is not used.
	 */
	public static IStatus exportSuBulkProject(
				String suName,
				String saName,
				String targetSaPath,
				String componentName,
				File tempDir ) {

		IStatus result = Status.OK_STATUS;
		Map<String,File> suEntryNameToSuFile = new HashMap<String,File>( 1 );

		// Package the SU(s)
		String errorMsgPrefix = suName + ": ";
		File suFile = null;
		try {
			// Create the SU
			suFile = new File( System.getProperty( "java.io.tmpdir" ), suName + ".zip" );
			suFile = JbiUtils.createJbiArchive( suFile.getAbsolutePath(), new File( tempDir, PetalsConstants.LOC_RES_FOLDER ));
			suEntryNameToSuFile.put( suName + ".zip", suFile );

		} catch( FileNotFoundException e ) {
			result = StatusUtils.createStatus( e, errorMsgPrefix );

		} catch( IOException e ) {
			result = StatusUtils.createStatus( e, errorMsgPrefix );

		} catch( NullPointerException e ) {
			result = StatusUtils.createStatus( e, errorMsgPrefix );
		}

		// Package the SA
		try {
			if( result.isOK()) {
				String saJbiXml = JbiUtils.generateJbiXmlForSA( componentName, saName, suName );
				File saFile = JbiUtils.createJbiArchive( targetSaPath, saJbiXml, suEntryNameToSuFile );
				assert saFile != null && saFile.exists();
			}

		} catch( IOException e ) {
			result = StatusUtils.createStatus( e, "SA creation: " );

		} finally {

			// Delete the temporary SU files
			if( suFile != null
						&& suFile.exists()
						&& ! suFile.delete())
				suFile.deleteOnExit();
		}

		return result;
	}


	/**
	 * Packages a SU project.
	 * <p>
	 * If the project is a Java project, a JAR is created for the Java resources, and the
	 * dependencies are added in the generated SU.
	 * </p>
	 *
	 * @param suProject the SU project
	 * @param suName the SU name
	 * @param monitor the progress monitor
	 * @return the created SU file (in a temporary directory)
	 * @throws CoreException if something went wrong
	 * @throws IOException if something went wrong
	 */
	private static File packageSuProject( IProject suProject, String suName, IProgressMonitor monitor )
	throws CoreException, IOException {

		File suFile = null;
		ExportableClassPath classpath = null;
		try {
			// Package the project
			// Java project
			if( suProject.hasNature( JavaCore.NATURE_ID )) {
				IJavaProject jp = JavaCore.create( suProject );
				classpath = JavaUtils.getExportableClasspath( jp, monitor );
			}

			// Build the entries to package
			IFolder resourcesFolder = suProject.getFolder( PetalsConstants.LOC_RES_FOLDER );
			Map<String,File> zipEntryToFile = JbiUtils.buildPackagingMap( resourcesFolder.getLocation().toFile());
			if( classpath != null ) {
				for( File jarFile : classpath.getExportableClassPath()) {

					String entry = jarFile.getName();
					String suffix = "";
					int id = 1;
					while( zipEntryToFile.containsKey( entry + suffix ))
						suffix = "-" + id;

					zipEntryToFile.put( entry + suffix, jarFile );
				}
			}

			// Replace the entry for the jbi.xml file
			File jbiXmlFile = zipEntryToFile.remove( "jbi.xml" );
			if( jbiXmlFile != null )
				zipEntryToFile.put( "META-INF/jbi.xml", jbiXmlFile );

			// Package the entries
			suFile = new File( System.getProperty( "java.io.tmpdir" ), suName + ".zip" );
			suFile = JbiUtils.createJbiArchive( suFile.getAbsolutePath(), zipEntryToFile );

		} finally {
			// Delete the temporary files
			if( classpath != null )
				classpath.deleteImplementationJars();
		}

		return suFile;
	}
}
