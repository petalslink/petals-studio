/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.maven.PetalsServicePomManager;
import com.ebmwebsourcing.petals.services.sa.nature.SaNature;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.su.nature.SuNature;

/**
 * Utility methods related to Petals service projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServicesProjectUtils {

	/**
	 * Creates a SU project with its structure and its properties set (natures, comments, source folders).
	 *
	 * @param projectName the project name
	 * @param projectLocationURI the project's location URI (null to create in the workspace)
	 * @param componentName the component's name
	 * @param componentVersion the component's version
	 * @param componentAlias the component's alias (e.g. SOAP)
	 * @param isJavaProject true to create a Java project, false otherwise
	 * @param monitor the progress monitor
	 * @return the created project
	 * @throws CoreException
	 * @throws IOException
	 */
	public static IProject createSuProject(
				String projectName,
				URI projectLocationURI,
				String componentName,
				String componentVersion,
				String componentAlias,
				boolean isJavaProject,
				IProgressMonitor monitor )
	throws CoreException, IOException {

		return createSuProject(
				projectName, projectLocationURI, componentName, componentVersion,
				componentAlias, isJavaProject, null, monitor );
	}


	/**
	 * Creates a SU project with its structure and its properties set (natures, comments, source folders).
	 *
	 * @param projectName the project name
	 * @param projectLocationURI the project's location URI (null to create in the workspace)
	 * @param componentName the component's name
	 * @param componentVersion the component's version
	 * @param componentAlias the component's alias (e.g. SOAP)
	 * @param isJavaProject true to create a Java project, false otherwise
	 * @param additionalDependencies a list of Maven dependencies (can be null)
	 * @param monitor the progress monitor
	 * @return the created project
	 * @throws CoreException
	 * @throws IOException
	 */
	public static IProject createSuProject(
				String projectName,
				URI projectLocationURI,
				String componentName,
				String componentVersion,
				String componentAlias,
				boolean isJavaProject,
				List<MavenBean> additionalDependencies,
				IProgressMonitor monitor )
	throws CoreException, IOException {

		// Create the project
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );

		// ProjectLocation = null => create it in the workspace
		if( projectLocationURI != null ) {
			IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription( projectName );
			projectDescription.setLocationURI( projectLocationURI );
			project.create( projectDescription, monitor );
		}
		else
			project.create( monitor );
		project.open( monitor );
		monitor.worked( 1 );


		// Add the required natures
		monitor.subTask( Messages.AbstractSuWizard_12 );
		IProjectDescription description = project.getDescription();
		String[] natures = description.getNatureIds();
		String[] newNatures;
		if( isJavaProject )
			newNatures = new String[ natures.length + 2 ];
		else
			newNatures = new String[ natures.length + 1 ];

		System.arraycopy( natures, 0, newNatures, 0, natures.length );
		newNatures[ natures.length ] = SuNature.NATURE_ID;
		if( newNatures.length > natures.length + 1 )
			newNatures[ natures.length + 1 ] = JavaCore.NATURE_ID;
		description.setNatureIds( newNatures );
		project.setDescription( description, monitor );
		monitor.worked( 1 );


		// Register some properties in the project
		Properties projectProperties = new Properties();
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_FUNCTION, componentAlias );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_NAME, componentName );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_VERSION, componentVersion );
		PetalsSPPropertiesManager.saveProperties( projectProperties, project );


		// Set the Java properties
		if( isJavaProject ) {
			monitor.subTask( "Setting up Java project properties..." );
			JavaUtils.createJavaProject( project );
		}

		monitor.worked( 1 );


		// Create the directories
		monitor.subTask( "Creating directories..." );
		String[] javaMavenProj = new String[] {
				"src",
				"src/main",
				PetalsConstants.LOC_JAVA_RES_FOLDER,
				PetalsConstants.LOC_SRC_FOLDER,
				PetalsConstants.LOC_RES_FOLDER,
				PetalsConstants.LOC_BIN_FOLDER
		};

		String[] basicMavenProj = new String[] {
				"src",
				"src/main",
				PetalsConstants.LOC_JAVA_RES_FOLDER,
				PetalsConstants.LOC_RES_FOLDER
		};

		String[] folderNames = isJavaProject ? javaMavenProj : basicMavenProj;
		for( String folderName : folderNames ) {
			IFolder folder = project.getFolder( folderName );
			if( ! folder.exists())
				folder.create( true, true, monitor );
		}

		monitor.worked( 1 );


		// Create the POM
		monitor.subTask( "Creating Maven files..." );
		IFile pomXml = project.getFile( "pom.xml" );
		MavenBean bean = MavenUtils.getPomParentElements();

		bean.setName( project.getName());
		bean.setArtifactId( project.getName());
		bean.setComponentName( componentName );
		bean.setComponentVersion( componentVersion );
		if( additionalDependencies != null )
			bean.dependencies.addAll( additionalDependencies );

		byte[] input = PetalsServicePomManager.INSTANCE.getSuPom( bean, componentName ).getBytes();
		InputStream is = new ByteArrayInputStream( input );
		if( ! pomXml.exists())
			pomXml.create( is, true, monitor );
		else
			pomXml.setContents( is, true, true, monitor );

		monitor.worked( 1 );
		return project;
	}


	/**
	 * Creates a SA project and its structure, and sets its properties.
	 * <p>
	 * The jbi.xml file is not created by this method.
	 * </p>
	 * <p>
	 * Makes the monitor progress increase of 5 units.
	 * </p>
	 *
	 * @param projectName
	 * @param projectLocationURI
	 * @param bean
	 * @param monitor
	 * @return
	 * @throws CoreException
	 * @throws Exception
	 */
	public static IProject createSaProject(
				String projectName,
				URI projectLocationURI,
				MavenBean bean,
				IProgressMonitor monitor )
	throws CoreException, Exception {

		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		// Project
		IProject newProject = wsRoot.getProject( projectName );
		if( ! newProject.exists()) {

			if( projectLocationURI != null ) {
				IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription( projectName );
				projectDescription.setLocationURI( projectLocationURI );
				newProject.create( projectDescription, monitor );
			}
			else	// ProjectLocation = null => create it in the workspace
				newProject.create( monitor );
		}

		newProject.open( monitor );
		monitor.worked( 1 );


		//
		// Add the SU nature and set project properties.
		monitor.subTask( "Adding SA nature to the project..." );
		IProjectDescription description = newProject.getDescription();
		String[] natures = description.getNatureIds();

		String[] newNatures = new String[ natures.length + 1 ];
		System.arraycopy( natures, 0, newNatures, 0, natures.length );
		newNatures[ natures.length ] = SaNature.NATURE_ID;
		description.setNatureIds( newNatures );
		newProject.setDescription( description, monitor );
		monitor.worked( 1 );


		// Creating the structure
		monitor.subTask( "Creating the file structure..." );
		String[] folders = new String[] { "src", "src/main", PetalsConstants.LOC_RES_FOLDER };
		for( String path : folders ) {
			IFolder folder = newProject.getFolder( path );
			if( ! folder.exists())
				folder.create( true, true, monitor );
		}
		monitor.worked( 1 );


		// Create the Maven files
		monitor.subTask( "Creating the pom.xml file..." );
		String pomXmlContent = PetalsServicePomManager.INSTANCE.getSaPom( bean );
		final IFile pomXmlFile = newProject.getFile( "pom.xml" );
		if( ! pomXmlFile.exists())
			pomXmlFile.create( new ByteArrayInputStream( pomXmlContent.getBytes()), true, monitor );
		else
			pomXmlFile.setContents( new ByteArrayInputStream( pomXmlContent.getBytes()), true, true, monitor );
		monitor.worked( 1 );

		return newProject;
	}


	/**
	 * Gets the name of the component associated with a project.
	 * @param suProject a SU project
	 * @return the associated component's name
	 * @throws Exception if the project is not a SU project or if it could not be accessed
	 */
	public static String getComponentName( IProject suProject )
	throws Exception {

		if( suProject.isAccessible()) {
			Properties prop = PetalsSPPropertiesManager.getProperties( suProject );
			return prop.getProperty( PetalsSPPropertiesManager.COMPONENT_NAME );
		}

		throw new Exception( "The project is not accessible." );
	}
}
