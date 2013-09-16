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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.ui.jarpackager.IJarExportRunnable;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JavaUtils {

	/**
	 * Get the referenced projects from a Java project.
	 * <p>The result includes the argument project.</p>
	 *
	 * @param javaProject
	 * @return
	 */
	public static List<IJavaProject> getJavaProjectDependencies( IJavaProject javaProject ) {

		if( javaProject == null )
			return Collections.emptyList();

		List<IJavaProject> result = new ArrayList<IJavaProject> ();
		result.add( javaProject );

		String[] projectNames;
		try {
			projectNames = javaProject.getRequiredProjectNames();
		} catch( JavaModelException e1 ) {
			e1.printStackTrace();
			projectNames = new String[ 0 ];
		}

		for( String projectName : projectNames ) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );
			try {
				if( !project.exists()
							|| !project.isOpen()
							|| !project.hasNature( JavaCore.NATURE_ID ))
					continue;

			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
				continue;
			}

			IJavaProject p = JavaCore.create( project );
			result.add( p );
		}

		return result;
	}


	/**
	 * Updates the class path of a Java project with libraries embedded by the studio.
	 * @param jp the Java project
	 * @param folders the folder names
	 * @throws IOException
	 * @throws JavaModelException
	 */
	public static void updateClasspathWithProjectLibraries( IJavaProject jp, IProgressMonitor monitor, String... folders )
	throws IOException, JavaModelException {

		if( folders == null )
			return;

		// Keep the current entries
		ArrayList<IClasspathEntry> entries = new ArrayList<IClasspathEntry> ();
		entries.addAll( Arrays.asList( jp.getRawClasspath()));
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept( File dir, String name ) {
				return name.endsWith( ".jar" ) || name.endsWith( ".zip" );
			}
		};


		for( String folder : folders ) {
			File pojoLibPath = ResourceUtils.getPluginBinaryPath( "com.ebmwebsourcing.petals.libs.esb", folder ); //$NON-NLS-1$
			if( pojoLibPath == null ) {
				PetalsCommonPlugin.log( "Could not find the Petals libraries in the distribution.", IStatus.ERROR );
				throw new IOException( "Petals libraries could not be located." );
			}

			// Add the libraries in the project class path
			File[] jarFiles = pojoLibPath.listFiles( filter );
			if( jarFiles != null ) {
				for( File jarFile : jarFiles ) {
					IPath path = new Path( jarFile.getAbsolutePath());
					IClasspathEntry entry = JavaCore.newLibraryEntry( path, null, null );
					entries.add( entry );
				}
			}
		}

		IClasspathEntry[] newEntries = CollectionUtils.convertToArray( entries, IClasspathEntry.class );
		if( ! jp.hasClasspathCycle( newEntries ))
			jp.setRawClasspath( newEntries, monitor );
	}


	/**
	 * Makes an Eclipse project a Java project.
	 * <p>
	 * If the project does not exist, or is not accessible, then nothing is done.<br />
	 * If the project does not have the Java nature, this nature is added.
	 * </p>
	 * <p>
	 * The default output directory is "bin". If this directory does not exist, it is created.
	 * If the creation fails, then no output directory is set.
	 * </p>
	 * <p>
	 * The default source directory is "src/main/java". If this directory does not exist, it is created.
	 * If the creation fails, then no source directory is set.
	 * </p>
	 *
	 * @param project the project to transform into a Java project
	 * @return the created Java project
	 * @throws CoreException if something went wrong
	 */
	public static IJavaProject createJavaProject( IProject project )
	throws CoreException {

		IJavaProject jp = null;
		if( project.isAccessible()) {

			// Add the Java nature?
			if( ! project.hasNature( JavaCore.NATURE_ID )) {

				IProjectDescription description = project.getDescription();
				String[] natures = description.getNatureIds();
				String[] newNatures = new String[ natures.length + 1 ];

				System.arraycopy( natures, 0, newNatures, 0, natures.length );
				newNatures[ natures.length ] = JavaCore.NATURE_ID;
				description.setNatureIds( newNatures );
				project.setDescription( description, null );
			}

			// Set the default class path
			jp = JavaCore.create( project );
			IProgressMonitor monitor = new NullProgressMonitor();

			//
			// Output location
			IPath ppath = project.getFullPath();
			IPath binPath = ppath.append( PetalsConstants.LOC_BIN_FOLDER );
			File binFile = ResourcesPlugin.getWorkspace().getRoot().getLocation().append( binPath ).toFile();
			if(binFile.exists() && binFile.isDirectory()
						|| ! binFile.exists() && binFile.mkdirs()) {
				jp.setOutputLocation( binPath, monitor );
			}

			Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
			entries.addAll( Arrays.asList( jp.getRawClasspath()));
			entries.add( JavaRuntime.getDefaultJREContainerEntry());

			//
			// Remove the "src" entry
			IClasspathEntry srcEntry = null;
			for( IClasspathEntry entry : entries ) {
				if( entry.getEntryKind() == IClasspathEntry.CPE_SOURCE ) {
					srcEntry = entry;
					break;
				}
			}

			//
			// Specify a new source entry
			if( srcEntry != null )
				entries.remove( srcEntry );

			String[] srcPaths = new String[] { PetalsConstants.LOC_SRC_FOLDER, PetalsConstants.LOC_JAVA_RES_FOLDER };
			for( String s : srcPaths ) {
				IPath srcPath = ppath.append( s );
				File srcFile = ResourcesPlugin.getWorkspace().getRoot().getLocation().append( srcPath ).toFile();
				if( srcFile.exists() && srcFile.isDirectory()
							|| ! srcFile.exists() && srcFile.mkdirs()) {

					project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
					srcEntry = JavaCore.newSourceEntry( srcPath );
					entries.add( srcEntry );
				}
				else {
					PetalsCommonPlugin.log( "Could not set '" + s + "' as a source folder.", IStatus.ERROR );
				}
			}

			jp.setRawClasspath( entries.toArray( new IClasspathEntry[ entries.size()]), monitor );
		}

		return jp;
	}


	/**
	 * Gets the source folders of a IJavaProject.
	 * @param javaProject
	 * @return the list of source folders in this Java project
	 */
	public static List<IClasspathEntry> getSourceFolders( IJavaProject javaProject ) {

		List<IClasspathEntry> result = new ArrayList<IClasspathEntry>();
		try {
			for( IClasspathEntry entry : javaProject.getRawClasspath()) {
				if( entry.getEntryKind() == IClasspathEntry.CPE_SOURCE )
					result.add( entry );
			}

		} catch( JavaModelException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Get the class path from Java project.
	 *
	 * @param javaProject
	 * @param getReferencedProjectClasspath
	 * @param binaryDirectory
	 * @return the class path as a list of string locations.
	 */
	public static List<String> getClasspath(
				IJavaProject javaProject,
				boolean getReferencedProjectClasspath,
				boolean binaryDirectory ) {

		List<String> paths = new ArrayList<String>();
		try {
			if( javaProject != null ) {

				// Get the raw class path
				IClasspathEntry[] entries = javaProject.getRawClasspath();
				for( IClasspathEntry entry : entries ) {
					switch( entry.getEntryKind()) {

					case IClasspathEntry.CPE_PROJECT:
						if( ! getReferencedProjectClasspath )
							break;

						String projectName = entry.getPath().toString();
						IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );
						IJavaProject jProject = JavaCore.create( project );

						List<String> subPaths = getClasspath( jProject, true, binaryDirectory );
						paths.addAll( subPaths );
						break;

					case IClasspathEntry.CPE_LIBRARY:
						IPath path = entry.getPath();
						paths.add( path.toString());
						break;

					case IClasspathEntry.CPE_VARIABLE:
						entry = JavaCore.getResolvedClasspathEntry( entry );
						if( entry != null ) {
							path = entry.getPath();
							paths.add( path.toString());
						}
						break;

					}
				}

				// Add the "bin" directory?
				if( binaryDirectory
							&& javaProject.getOutputLocation() != null ) {
					IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
					path = path.append( javaProject.getOutputLocation());
					paths.add( path.toString());
				}
			}

		} catch( JavaModelException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return paths;
	}


	/**
	 * Creates a JAR file containing all the resources contained in the source folders of a Java project.
	 * @param jp the Java project
	 * @param monitor the progress monitor
	 * @return the JAR file, which was saved in the temporary folder (and should be deleted after use)
	 * @throws InvocationTargetException if the creation failed
	 * @throws InterruptedException if the creation was interrupted
	 */
	public static File createDefaultJar( IJavaProject jp, IProgressMonitor monitor )
	throws InvocationTargetException, InterruptedException {

		// Create a default JAR for the implementation
		JarPackageData jarDescription = new JarPackageData();
		jarDescription.setIncludeDirectoryEntries( true );
		jarDescription.setExportClassFiles( true );
		jarDescription.setOverwrite( true );
		jarDescription.setIncludeDirectoryEntries( true );

		jarDescription.setExportJavaFiles( false );
		jarDescription.setCompress( true );
		jarDescription.setExportErrors( true );
		jarDescription.setExportWarnings( true );

		// Add all the files in the source folders
		List<Object> filesToPackage = new ArrayList<Object> ();
		for( IClasspathEntry entry : getSourceFolders( jp )) {

			// PETALSSTUD-130: use the project location and not the workspace root as a reference
			File f = jp.getProject().getLocation().append( entry.getPath().removeFirstSegments( 1 )).toFile();
			// PETALSSTUD-130

			IFolder folder = (IFolder) ResourceUtils.getResource( f );
			if( folder != null ) {
				List<IFile> files = ResourceUtils.getFilesByRegexp( folder, ".*" );
				filesToPackage.addAll( files );
			}
		}

		Object[] elements = new Object[ filesToPackage.size()];
		jarDescription.setElements( filesToPackage.toArray( elements ));

		// Create the JAR
		IPath jarLocation = new Path( System.getProperty( "java.io.tmpdir" )).append( jp.getProject().getName() + ".jar" );

		// Bug: Windows => path separator = "\"
		// But IPath#isAbsolute() only checks for "/" path separators
		// => We replace the path separator
		// Otherwise, the JAR is created, but not at the location we would expect
		jarLocation = new Path( jarLocation.toString().replaceAll( "\\\\", "/" ));
		jarDescription.setJarLocation( jarLocation );

		IJarExportRunnable runnable = jarDescription.createJarExportRunnable( null );
		runnable.run( monitor );
		IStatus status = runnable.getStatus();
		if( ! status.isOK())
			PetalsCommonPlugin.getDefault().getLog().log( status );

		return jarLocation.toFile();
	}


	/**
	 * Creates a new source folder.
	 * <p>
	 * If the folder does not exist, it is created.<br />
	 * If the directory is already in the class path, then this method does nothing.
	 * </p>
	 *
	 * @param jp the Java project
	 * @param folderName the folder name
	 * @param monitor the progress monitor
	 * @return the created folder
	 * @throws CoreException if something went wrong
	 */
	public static IFolder createSourceFolder(
				IJavaProject jp,
				String folderName,
				IProgressMonitor monitor )
	throws CoreException {

		IFolder newSourceFolder = jp.getProject().getFolder( folderName );
		if( ! newSourceFolder.exists())
			newSourceFolder.create( true, true, monitor );

		IClasspathEntry srcEntry = JavaCore.newSourceEntry( newSourceFolder.getFullPath());
		Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
		entries.addAll( Arrays.asList( jp.getRawClasspath()));
		entries.add( srcEntry );
		jp.setRawClasspath(
					entries.toArray( new IClasspathEntry[ entries.size()]),
					monitor );

		return newSourceFolder;
	}


	/**
	 * Gets a set of JAR files from a Java project, in order to package them.
	 * @param jp the Java project
	 * @param monitor the progress monitor
	 * @return an instance of {@link ExportableClassPath} (never null)
	 */
	public static ExportableClassPath getExportableClasspath( IJavaProject jp, IProgressMonitor monitor ) {

		ExportableClassPath result = new ExportableClassPath();
		try {
			// Check the class path
			IClasspathEntry[] entries = jp.getRawClasspath();
			for( IClasspathEntry entry : entries )
				updateExportableResult( result, entry, monitor );

			// Package the implementation
			File jarFile = createDefaultJar( jp, monitor );
			result.implementationJars.add( jarFile );
			monitor.worked( 1 );

		} catch( JavaModelException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} catch( InvocationTargetException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} catch( InterruptedException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Populates an {@link ExportableClassPath} instance from a class path entry.
	 * @param result the {@link ExportableClassPath} instance to populate
	 * @param entry a class path entry
	 * @param monitor the progress monitor
	 */
	private static void updateExportableResult( ExportableClassPath result, IClasspathEntry entry, IProgressMonitor monitor ) {

		switch( entry.getEntryKind()) {

		case IClasspathEntry.CPE_PROJECT:
			String projectName = entry.getPath().toString();
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );
			IJavaProject jProject = JavaCore.create( project );

			ExportableClassPath subResult = getExportableClasspath( jProject, monitor );
			result.implementationJars.addAll( subResult.implementationJars );
			result.libraryJars.addAll( subResult.libraryJars );
			monitor.worked( 1 );
			break;

		case IClasspathEntry.CPE_LIBRARY:
			IPath path = entry.getPath();
			if( path != null ) {
				File f = path.toFile();
				if( f.exists()) {
					if( f.getName().endsWith( ".zip" )
								|| f.getName().endsWith( ".jar" ))
						result.libraryJars.add( f );
				}
			}
			break;

		case IClasspathEntry.CPE_VARIABLE:
			entry = JavaCore.getResolvedClasspathEntry( entry );
			if( entry != null )
				updateExportableResult( result, entry, monitor );

			break;
		}
	}


	/**
	 * This class is used to manage the class path elements to export for a Java project.
	 * <p>
	 * It manages through 2 distinct lists the libraries used in the project, and the
	 * implementations JAR that are built during the export operation.
	 * </p>
	 * <p>
	 * Once this class has been used, the method {@link #deleteImplementationJars()} should
	 * be invoked.
	 * </p>
	 */
	public static class ExportableClassPath {

		private final Set<File> libraryJars = new HashSet<File> ();
		private final Set<File> implementationJars = new HashSet<File> ();


		/**
		 * @return the class path, including both the library and the
		 */
		public Set<File> getExportableClassPath() {

			Set<File> result = new HashSet<File> ();
			result.addAll( this.libraryJars );
			result.addAll( this.implementationJars );

			return result;
		}


		/**
		 * Deletes the all (temporary) implementation JAR files.
		 */
		public void deleteImplementationJars() {

			for( File f : this.implementationJars ) {

				if( ! f.exists())
					PetalsCommonPlugin.log( f.getAbsolutePath() + " does not exist.", IStatus.WARNING );

				if( f.exists() && ! f.delete())
					f.deleteOnExit();
			}
		}
	}


	/**
	 * Finds the direct children for a given package.
	 * <p>
	 * Copied (or almost) from the JDT.
	 * </p>
	 *
	 * @param parent the package fragment root (will be searched from <code>fragment</code> if null)
	 * @param fragment the fragment to analyze (can be null if <code>parent</code> is not)
	 * @return a list of package fragments
	 * @throws JavaModelException if an error occurred with the Java model
	 */
	public static List<IPackageFragment> findDirectSubPackages( IPackageFragmentRoot parent, IPackageFragment fragment )
	throws JavaModelException {

		// Special case for the default package
		if( fragment != null && fragment.isDefaultPackage())
			return Collections.emptyList();

		// Find the package parent?
		if( parent == null ) {
			IJavaElement elt = fragment;
			while( elt.getParent() != null ) {
				elt = elt.getParent();
				if( elt instanceof IPackageFragmentRoot ) {
					parent = (IPackageFragmentRoot) elt;
					break;
				}
			}
		}

		// Find the direct children
		List<IPackageFragment> result = new ArrayList<IPackageFragment> ();
		if( parent != null ) {
			IJavaElement[] children = parent.getChildren();
			String prefix = fragment != null ? fragment.getElementName() + '.' : ""; //$NON-NLS-1$
			int prefixLen = prefix.length();
			for( IJavaElement element : children ) {
				IPackageFragment curr = (IPackageFragment) element;
				String name = curr.getElementName();
				if( name.startsWith( prefix )
							&& name.length() > prefixLen
							&& name.indexOf( '.', prefixLen ) == -1 )
					result.add( curr );
				else if( fragment == null && curr.isDefaultPackage())
					result.add( curr );
			}
		}

		return result;
	}
}
