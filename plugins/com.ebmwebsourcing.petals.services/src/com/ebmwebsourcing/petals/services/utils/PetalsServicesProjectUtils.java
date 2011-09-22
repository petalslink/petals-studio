/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
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
import java.io.InputStream;
import java.net.URI;
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
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * Utility methods related to Petals service projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServicesProjectUtils {

	/**
	 * Creates a SU project and its structure.
	 *
	 * @param eclipseSuBean the data retrieved from the wizard
	 * @param monitor the monitor used to interact with the user during this operation
	 * @return the created project
	 * @throws CoreException
	 * @throws Exception
	 */
	public static IProject createSuProject(
				EclipseSuBean eclipseSuBean,
				IProgressMonitor monitor )
	throws CoreException, Exception {

		return createSuProject(
					eclipseSuBean.getProjectName(),
					eclipseSuBean.getProjectLocation(),
					eclipseSuBean.getComponentName(),
					eclipseSuBean.getComponentVersion(),
					eclipseSuBean.getSuType(),
					eclipseSuBean.isCreateJavaProject(),
					monitor );
	}


	/**
	 * Creates a SU project and its structure, and sets its properties (natures, comments, source folders).
	 * <p>
	 * Makes the monitor progress increase of 5 units.
	 * </p>
	 *
	 * @param projectName
	 * @param projectLocationURI
	 * @param componentName
	 * @param componentVersion
	 * @param suType
	 * @param createJavaProject
	 * @param monitor the monitor used to interact with the user during this operation
	 * @return the created project
	 * @throws CoreException
	 * @throws Exception
	 */
	public static IProject createSuProject(
				String projectName,
				URI projectLocationURI,
				String componentName,
				String componentVersion,
				String suType,
				boolean createJavaProject,
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
		monitor.subTask( Messages.AbstractSuWizard_12 );
		IProjectDescription description = newProject.getDescription();
		String[] natures = description.getNatureIds();

		String[] newNatures;
		if( createJavaProject )
			newNatures = new String[ natures.length + 2 ];
		else
			newNatures = new String[ natures.length + 1 ];

		System.arraycopy( natures, 0, newNatures, 0, natures.length );
		newNatures[ natures.length ] = SuNature.NATURE_ID;
		if( newNatures.length > natures.length + 1 )
			newNatures[ natures.length + 1 ] = JavaCore.NATURE_ID;
		description.setNatureIds( newNatures );
		newProject.setDescription( description, monitor );
		monitor.worked( 1 );

		// Register some properties in project
		Properties projectProperties = new Properties();
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_FUNCTION, suType );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_NAME, componentName );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_VERSION, componentVersion );
		PetalsSPPropertiesManager.saveProperties( projectProperties, newProject );


		//
		// Set Java properties
		if( createJavaProject ) {
			monitor.subTask( "Setting up Java project properties..." );
			JavaUtils.createJavaProject( newProject );
		}

		monitor.worked( 1 );
		createPetalsSuProjectStructure(
					createJavaProject, componentName,
					componentVersion, newProject, monitor );

		return newProject;
	}


	/**
	 * Creates the file and directory structure for a Petals project.
	 * <p>
	 * Makes the monitor progress increase of 2 units.
	 * </p>
	 *
	 * @param createJavaProject
	 * @param componentName
	 * @param componentVersion
	 * @param project
	 * @param monitor
	 * @throws CoreException
	 */
	public static void createPetalsSuProjectStructure(
				boolean createJavaProject,
				String componentName,
				String componentVersion,
				IProject project,
				IProgressMonitor monitor ) throws CoreException {

		//
		// Possible project structures
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

		// 1st-level folder.
		String[] folderNames;
		if( createJavaProject )
			folderNames = javaMavenProj;
		else
			folderNames = basicMavenProj;

		for( String folderName : folderNames ) {
			IFolder folder = project.getFolder( folderName );
			if( ! folder.exists())
				folder.create( true, true, monitor );
		}

		monitor.worked( 1 );

		//
		// pom.xml ?
		monitor.subTask( "Creating Maven files..." );
		IFile pomXml = project.getFile( "pom.xml" );
		MavenBean bean = MavenUtils.getPomParentElements();
		bean.setName( project.getName());
		bean.setArtifactId( project.getName());
		bean.setComponentName( componentName );
		bean.setComponentVersion( componentVersion );

		byte[] input = PetalsServicePomManager.INSTANCE.getSuPom( bean, componentName ).getBytes();
		InputStream is = new ByteArrayInputStream( input );
		if( ! pomXml.exists())
			pomXml.create( is, true, monitor );
		else
			pomXml.setContents( is, true, true, monitor );

		monitor.worked( 1 );
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
