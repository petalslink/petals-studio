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

package com.ebmwebsourcing.commons.jbi.internal.provisional.client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SaBean;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SuBean;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SuBeanForSa;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlAttribute;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.FileImporter;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiXmlGenerator;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiZipper;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.FileImporter.FileImportsException;

/**
 * A class which generates service assembly archive files from a minimal set of required information.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiArchiveGenerator {
	/** The unique instance of this class. */
	private static JbiArchiveGenerator instance = new JbiArchiveGenerator();

	/** Private constructor (singleton pattern). */
	private JbiArchiveGenerator() {}

	/**
	 * @return the unique instance of this class.
	 */
	public static JbiArchiveGenerator getInstance() {
		return instance;
	}

	/**
	 * Generate a service assembly *.zip file for one service unit.
	 * 
	 * @param filesToImportUrls a map associating a list of file URLs and relative path to import, and a SuBean.
	 * @see FileImporter#importFiles(Map, File, boolean, boolean, boolean) for more details about this map.
	 * 
	 * @param temporaryFolder the empty folder (directory) where temporary files can be created.
	 * This folder must be empty. If it is not, it will be emptied by this method.
	 * If it fails to empty it, an exception is raised.
	 * Temporary files are deleted at the end, except the SA *.zip file if it was saved
	 * into this directory.
	 * 
	 * @param suBean the bean used to generate the content of the jbi.xml file for the service unit.
	 * @param saZipFile the file where the service assembly *.zip file should be saved, or its parent folder.
	 * If the given file is not a directory, its content will be erased and replaced by the new archive.
	 * If the given file is a directory, the *.zip file is created inside this directory. Its name is
	 * generated from the service name of the SuBean.
	 * In this last case, existing files are overwritten.
	 * 
	 * @throws IllegalArgumentException if one argument is invalid (typically some files are not directories).
	 * @throws FileImportsException if files could not be imported in the given location.
	 * @throws IOException if errors occurred during the creation of files.
	 * @return the created service assembly *.zip file.
	 */
	public final File createJbiArchive(
				Map<URL, String> filesToImportUrls, File temporaryFolder,
				SuBean suBean, File saZipFile
	) throws IllegalArgumentException, FileImportsException, IOException {

		// Work on parameters.
		Map<SuBean, Map<URL, String>> files = new HashMap<SuBean, Map<URL, String>> ();
		files.put( suBean, filesToImportUrls );

		List<SuBean> suBeans = new ArrayList<SuBean> ();
		suBeans.add( suBean );

		// Update SA name if required.
		if( saZipFile.isDirectory()) {
			String saName = JbiNameFormatter.createSaName(
						suBean.getSuType(), suBean.getServiceName(), suBean.isConsume());
			saZipFile = new File( saZipFile, saName );
		}

		return createJbiArchive( suBeans, files, saZipFile, temporaryFolder );
	}

	/**
	 * Generate a service assembly *.zip file for several service units.
	 * 
	 * @param suBeans the beans used to generate the content of the jbi.xml files for the service units.
	 * @param filesToImportUrls a map associating a map of file URLs and relative paths to import, and a SuBean.
	 * @see FileImporter#importFiles(Map, File, boolean, boolean, boolean) for more details about this map.
	 * 
	 * @param saZipFile the file where the service assembly *.zip file should be saved, or its parent folder.
	 * If the given file is not a directory, its content will be erased and replaced by the new archive.
	 * If the given file is a directory, a *.zip file with the same name is created inside this directory.
	 * In this last case, existing files are overwritten.
	 * 
	 * @param temporaryFolder the empty folder (directory) where temporary files can be created.
	 * This folder must be empty. If it is not, it will be emptied by this method.
	 * If it fails to empty it, an exception is raised.
	 * Temporary files are deleted at the end, except the SA *.zip file if it was saved into this directory.
	 * 
	 * @throws IllegalArgumentException if one argument is invalid (typically some files are not directories).
	 * @throws FileImportsException if files could not be imported in the given location.
	 * @throws IOException if errors occurred during the creation of files.
	 * @return the created service assembly *.zip file.
	 */
	public final File createJbiArchive(
				List<SuBean> suBeans, Map<SuBean, Map<URL, String>> filesToImportUrls,
				File saZipFile, File temporaryFolder
	) throws IllegalArgumentException, FileImportsException, IOException {

		//
		// Delete all the files from the temporary directory.
		for( File file : temporaryFolder.listFiles()) {
			if( !file.isHidden())
				file.delete();
		}

		// Ensure that only hidden files remain.
		for( File file : temporaryFolder.listFiles()) {
			if( !file.isHidden() && !file.getName().startsWith( "." ))
				throw new IllegalArgumentException( "Temporary folder " + temporaryFolder.getAbsolutePath() + " is not empty." );
		}

		//
		// Create a temporary sub folder.
		File subFolder = new File( temporaryFolder, "subFolder" );
		if( subFolder.mkdir() == false )
			throw new IOException( "Temporary sub folder " + subFolder.getAbsolutePath() + " could not be created." );

		//
		// Make all the SUs.
		//
		List<SuBeanForSa> sus = new ArrayList<SuBeanForSa> ();
		List<File> suZipFiles = new ArrayList<File> ();
		for( SuBean suBean : suBeans ) {
			//
			// Create the jbi.xml file for the SU.
			createJbiSuContent( suBean, filesToImportUrls.get( suBean ), subFolder );

			//
			// Generate the archive file for the SU and save it in the folder above.
			SuBeanForSa suBeanForSa = new SuBeanForSa( suBean );
			sus.add( suBeanForSa );

			File suZipFile = new File( temporaryFolder, suBeanForSa.getZipArtifact());
			JbiZipper.getInstance().createSuZipFile( suZipFile, subFolder );
			suZipFiles.add( suZipFile );

			//
			// Delete all the files from the temporary temporary sub folder.
			for( File file : subFolder.listFiles()) {
				if( !file.isHidden() && !file.getName().startsWith( "." ))
					file.delete();
			}
		}

		//
		// All the zips of SUs have been generated. Delete the sub folder.
		subFolder.delete();

		//
		// Create the jbi.xml file for the SA and the archive.
		SaBean saBean = new SaBean();
		saBean.setSus( sus );

		// SA name = file name without the extension or directory name.
		String saName;
		if( saZipFile.isDirectory())
			saName = saZipFile.getName();
		else
			saName = JbiNameFormatter.removeFileExtension( saZipFile.getName());

		saBean.setSaName( saName );
		saBean.setDescription( "Service assembly archive for " + sus.size()
					+ ((sus.size() > 1) ? " service units." : " service unit."));
		createJbiSaContent( saBean, temporaryFolder, suZipFiles, saZipFile  );

		//
		// Delete files from the temporary folder, except the generated SA (if it was saved in).
		for( File file : temporaryFolder.listFiles()) {
			if( file.equals( saZipFile ))
				continue;

			if( ! file.isHidden() && ! file.getName().startsWith( "." ) && ! file.delete())
				file.deleteOnExit();
		}

		return saZipFile;
	}

	/**
	 * Create the content of the SU (jbi.xml file and import the required files in the temporary folder).
	 * 
	 * @param suBean the bean used to generate the content of the jbi.xml file for the service unit.
	 * @param filesToImport a map of file URLs to import.
	 * @param temporaryFolder the folder (directory) where temporary files can be created.
	 * 
	 * @throws IllegalArgumentException if file import argument is wrong.
	 * @throws FileImportsException if files could not be imported in the given location.
	 * @throws IOException if errors occurred during the creation of files.
	 */
	public final void createJbiSuContent( SuBean suBean, Map<URL, String> filesToImport, File temporaryFolder )
	throws IOException, IllegalArgumentException, FileImportsException {

		//
		// Import files.
		Map<URL, String> renamedFiles = Collections.emptyMap();
		if( filesToImport != null ) {
			// Imported files are not hidden files.
			renamedFiles =
				FileImporter.getInstance().importFiles( filesToImport, temporaryFolder, false, true, true, true );
		}

		//
		// Update the SuBean with information from renamed files.
		for( Map.Entry<URL, String> entry : renamedFiles.entrySet()) {
			List<XmlElement> elements = suBean.urlsToElements.get( entry.getKey());
			if( elements != null ) {
				for( XmlElement element : elements )
					element.setValue( entry.getValue());
			}

			List<XmlAttribute> attributes = suBean.urlsToAttributes.get( entry.getKey());
			if( attributes != null ) {
				for( XmlAttribute attribute : attributes )
					attribute.setValue( entry.getValue());
			}
		}

		//
		// Generate the jbi.xml for the SU.
		String suJbiXmlContent = JbiXmlGenerator.getInstance().generateJbiXmlFileForSu( suBean );
		File suJbiXml = new File( temporaryFolder, "jbi.xml" );
		try {
			if( !suJbiXml.exists())
				suJbiXml.createNewFile();
		} catch( Exception e ) {
			e.printStackTrace();
		}

		FileWriter writer = new FileWriter( suJbiXml );
		writer.write( suJbiXmlContent );
		writer.close();
	}


	/**
	 * Create the content of the SA (jbi.xml file and zip all the files in a SA archive).
	 * 
	 * @param saBean the bean with the SA data.
	 * @param temporaryFolder the folder (directory) where temporary files can be created.
	 * @param suZipFiles the list of files corresponding to the SU zip files.
	 * @param saZipFile the destination file for the SA archive.
	 * @throws IOException if the SA archive creation failed.
	 */
	public final void createJbiSaContent(
				SaBean saBean, File temporaryFolder, List<File> suZipFiles, File saZipFile )
	throws IOException {

		//
		// Generate the jbi.xml for the SA.
		String saJbiXmlContent = JbiXmlGenerator.getInstance().generateJbiXmlFileForSa( saBean );
		File saJbiXml = new File( temporaryFolder, "jbi.xml" );	// Override the previous jbi.xml

		try {
			if( !saJbiXml.exists())
				saJbiXml.createNewFile();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		FileWriter writer = new FileWriter( saJbiXml );
		writer.write( saJbiXmlContent );
		writer.close();

		//
		// Generate the archive file for the SA.
		if( saZipFile.isDirectory())
			saZipFile = new File( saZipFile, saBean.getSaName() + ".zip" );
		JbiZipper.getInstance().createSaZipFile( saZipFile, suZipFiles, saJbiXml );
	}
}
