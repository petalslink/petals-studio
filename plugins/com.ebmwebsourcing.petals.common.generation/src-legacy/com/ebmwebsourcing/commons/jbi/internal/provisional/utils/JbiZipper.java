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

package com.ebmwebsourcing.commons.jbi.internal.provisional.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class used to archive files as service unit and service assembly *.zip files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiZipper {

	/** The unique instance of this class. */
	private static JbiZipper instance = new JbiZipper();

	/** Private constructor (singleton pattern). */
	private JbiZipper() {}

	/**
	 * @return the unique instance of this class.
	 */
	public static JbiZipper getInstance() {
		return instance;
	}

	/**
	 * Add recursively entries into the zip file.
	 * Hidden files are skipped and are not added in the result.
	 * 
	 * @param rootFolder the root folder.
	 * @param folderEntry the zip entry (not null).
	 * @param filesToZip the file to zip and associated with this entry (not null).
	 */
	private void addRecursiveFiles( File rootFolder, String folderEntry, Map<String, File> filesToZip ) {

		for( File file : rootFolder.listFiles()) {
			if( file.isHidden() || file.getName().startsWith( "." ))
				continue;

			if( !folderEntry.equals( "" ) && !folderEntry.endsWith( "/" ))
				folderEntry += "/";

			if( !file.isDirectory())
				filesToZip.put( folderEntry + file.getName(), file );
			else
				addRecursiveFiles( file, folderEntry + file.getName(), filesToZip );
		}
	}

	/**
	 * Create a *.zip file for a service unit.
	 * Hidden files and files whose name begins with a dot are skipped and are not zipped.
	 * 
	 * @param zipFile the resulting *.zip file.
	 * @param rootFiles the root files to include.
	 * These files, their sub-files and sub-folders will be added in the archive.
	 * 
	 * @return the service unit zip file.
	 * @throws IOException if the zip could not be created.
	 * @throws IllegalArgumentException
	 */
	public File createSuZipFile( File zipFile, List<File> rootFiles ) throws IllegalArgumentException, IOException {
		if( !zipFile.getName().endsWith( ".zip" ))
			throw new IllegalArgumentException( zipFile.getAbsolutePath() + " does not have a valid zip file name." );

		// Build the list of files to zip.
		Map<String, File> filesToZip = new HashMap<String, File> ();
		for( File file : rootFiles ) {
			if( !file.exists()
						|| file.isHidden()
						|| file.getName().startsWith( "." ))
				continue;

			else if( file.getName().equals( "jbi.xml" )
						&& ! "META-INF".equals( file.getParentFile().getName()))
				filesToZip.put( "META-INF/jbi.xml", file );

			else if( !file.isDirectory())
				filesToZip.put( file.getName(), file );

			else
				addRecursiveFiles( file, file.getName(), filesToZip );
		}

		// Create the zip file.
		return Zipper.getInstance().createZipFile( zipFile, filesToZip );
	}

	/**
	 * Create a *.zip file for a service unit with all the files contained in the folder argument.
	 * Equivalent to JbiZipper#createSuZipFile( zipFile, Arrays.asList( folderContainer.listFiles())).
	 * @param zipFile
	 * 
	 * @param folderContainer the folder containing all the files to include in the SU zip.
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @see JbiZipper#createSuZipFile(File, List)
	 */
	public File createSuZipFile( File zipFile, File folderContainer ) throws IOException, IllegalArgumentException {
		if( !folderContainer.isDirectory())
			throw new IllegalArgumentException( folderContainer.getAbsolutePath() + " is not a directory." );

		List<File> rootFiles = Arrays.asList( folderContainer.listFiles());
		return createSuZipFile( zipFile, rootFiles );
	}

	/**
	 * Create a *.zip file for a service unit.
	 * Equivalent to JbiZipper#createSuZipFile( new File( zipFilePath ),  folderContainer ).
	 * 
	 * @param zipFilePath the resulting *.zip file path.
	 * @param folderContainer
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @see JbiZipper#createSuZipFile(File, File)
	 */
	public File createSuZipFile( String zipFilePath, File folderContainer ) throws IOException, IllegalArgumentException {
		return createSuZipFile( new File( zipFilePath ), folderContainer );
	}


	/**
	 * Create a *.zip file for a service assembly.
	 * Hidden files are skipped and are not zipped.
	 * 
	 * @param zipFile the resulting *.zip file.
	 * @param suZipFiles
	 * @param saJbiXml
	 * For other organizations, use directly {@link Zipper#createZipFile(File, Map)}.
	 * 
	 * @return the service assembly zip file.
	 * @throws IOException
	 */
	public File createSaZipFile( File zipFile, List<File> suZipFiles, File saJbiXml ) throws IOException {
		if( !zipFile.getName().endsWith( ".zip" ))
			throw new IllegalArgumentException( zipFile.getAbsolutePath() + " does not have a valid zip file name." );

		// Build the list of files to zip.
		Map<String, File> filesToZip = new HashMap<String, File> ();
		filesToZip.put( "META-INF/jbi.xml", saJbiXml );

		for( File file : suZipFiles ) {
			if( file.isHidden() || file.getName().startsWith( "." ))
				continue;

			filesToZip.put( file.getName(), file );
		}

		// Create the zip file.
		return Zipper.getInstance().createZipFile( zipFile, filesToZip );
	}

	/**
	 * Create a *.zip file for a service assembly.
	 * Equivalent to JbiZipper#createSaZipFile(File, List, File)
	 * but with only one zip file for SU in the second list argument.
	 * 
	 * @param zipFile
	 * @param suZipFile
	 * @param saJbiXml
	 * @return
	 * @throws IOException
	 * @see JbiZipper#createSaZipFile(File, List, File)
	 */
	public File createSaZipFile( File zipFile, File suZipFile, File saJbiXml ) throws IOException {
		List<File> suZipFiles = new ArrayList<File> ();
		suZipFiles.add( suZipFile );
		return createSaZipFile( zipFile, suZipFiles, saJbiXml );
	}
}
