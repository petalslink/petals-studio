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

package com.ebmwebsourcing.petals.services.su.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlAttribute;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.FileImporter;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.FileImporter.FileImportsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileImportManager {

	/**
	 * A map of file URLs to import.
	 * Key = file URL. Value = relative path with respect to folder (should include the file name).
	 * Let the value empty ("") to import the file at the root of the folder.
	 * In any case, it should not begin with a slash. Should not be null.
	 */
	private final Map<URL, String> urlsAndRelativePath = new HashMap<URL, String> ();
	private final Map<URL, List<XmlElement>> urlsAndXmlFilesElements = new HashMap<URL, List<XmlElement>> ();
	private final Map<URL, List<XmlAttribute>> urlsAndXmlFilesAttributes = new HashMap<URL, List<XmlAttribute>> ();



	/**
	 * Private empty constructor.
	 */
	private FileImportManager () {
		// nothing
	}


	/**
	 * 
	 */
	public void clear() {
		this.urlsAndRelativePath.clear();
		this.urlsAndXmlFilesElements.clear();
		this.urlsAndXmlFilesAttributes.clear();
	}


	/**
	 * Imports the files in the resources folder of the created project.
	 * 
	 * @param createdProject
	 * @throws FileImportsException
	 * @throws IllegalArgumentException
	 * @throws Exception if the project structure is invalid.
	 */
	public void importFiles( IProject createdProject ) throws IllegalArgumentException, FileImportsException, Exception {

		IPath folderPath = createdProject.getFolder( PetalsConstants.LOC_RES_FOLDER ).getLocation();

		// WsdlUtils.importAllFilesReferencedInWsdl( eclipseSuBean, createdProject );
		Map<URL, String> xsdAndWsdls = new HashMap<URL, String> ();
		for( URL fileUrl : this.urlsAndRelativePath.keySet()) {

			String url = fileUrl.toString().toLowerCase();
			if( url.endsWith( ".wsdl" ) || url.endsWith( ".xsd" ) || url.endsWith( "?wsdl" )) {
				String relativePath = this.urlsAndRelativePath.get( fileUrl );
				xsdAndWsdls.put( fileUrl, relativePath );
			}
		}

		for( URL fileUrl : xsdAndWsdls.keySet())
			this.urlsAndRelativePath.remove( fileUrl );


		// Propagate changes in the XML structure - common files (no XSD, no WSDL).
		Map<URL, String> commonFiles =
			FileImporter.getInstance().importFiles( this.urlsAndRelativePath, folderPath.toFile(), false, true, false, true );

		for( Map.Entry<URL, String> entry : commonFiles.entrySet()) {
			String filepath = entry.getValue();
			URL url = entry.getKey();

			// Get the relative location of the file with respect to the resource folder.
			File file = new File( filepath );
			filepath = IoUtils.getRelativeLocationToFile( folderPath.toFile(), file );

			List<XmlElement> fileElements = this.urlsAndXmlFilesElements.get( url );
			if( fileElements != null ) {
				for( XmlElement fileElement : fileElements )
					fileElement.setValue( filepath );
			}

			List<XmlAttribute> fileAttributes = this.urlsAndXmlFilesAttributes.get( url );
			if( fileAttributes != null ) {
				for( XmlAttribute fileAttribute : fileAttributes )
					fileAttribute.setValue( filepath );
			}
		}


		// Propagate changes in the XML structure - XSD and XSDL files.
		WsdlImportUtils wsdlUtils = new WsdlImportUtils();	// Share the same "import context"
		for( Map.Entry<URL, String> entry : xsdAndWsdls.entrySet()) {

			URL url = entry.getKey();
			String uriAsString = UriUtils.urlToUri( url ).toString().replaceAll( "///", "/" );
			Map<String,File> urlToFile = wsdlUtils.importWsdlOrXsdAndDependencies( folderPath.toFile(), uriAsString );
			File importedFile = urlToFile.get( uriAsString );
			if( importedFile == null ) {
				PetalsServicesPlugin.log( "Could not import file from " + url, IStatus.WARNING );
				continue;
			}

			// Get the relative location of the file with respect to the resource folder.
			String filename = IoUtils.getRelativeLocationToFile( folderPath.toFile(), importedFile );
			List<XmlElement> fileElements = this.urlsAndXmlFilesElements.get( url );
			if( fileElements != null ) {
				for( XmlElement fileElement : fileElements ) {
					if( fileElement != null )
						fileElement.setValue( filename );
				}
			}

			List<XmlAttribute> fileAttributes = this.urlsAndXmlFilesAttributes.get( url );
			if( fileAttributes != null ) {
				for( XmlAttribute fileAttribute : fileAttributes ) {
					if( fileAttribute != null )
						fileAttribute.setValue( filename );
				}
			}
		}
	}


	/**
	 * @param fileElement
	 * @param url
	 * @param relativePath
	 */
	public void registerXmlFileElement( XmlElement fileElement, String url, String relativePath ) {

		if( url == null )
			return;

		URL realUrl;
		try {
			realUrl = new URL( url );
			relativePath = relativePath == null ? "" : relativePath;
			this.urlsAndRelativePath.put( realUrl, relativePath );

			List<XmlElement> elements = this.urlsAndXmlFilesElements.get( realUrl );
			if( elements == null ) {
				elements = new ArrayList<XmlElement> ();
				this.urlsAndXmlFilesElements.put( realUrl, elements );
			}

			elements.add( fileElement );

		} catch( MalformedURLException e ) {
			e.printStackTrace();
		}
	}


	/**
	 * @param fileAttribute
	 * @param url
	 * @param relativePath
	 */
	public void registerXmlFileAttribute( XmlAttribute fileAttribute, String url, String relativePath ) {
		if( url == null )
			return;

		URL realUrl;
		try {
			realUrl = new URL( url );
			relativePath = relativePath == null ? "" : relativePath;
			this.urlsAndRelativePath.put( realUrl, relativePath );

			List<XmlAttribute> attributes = this.urlsAndXmlFilesAttributes.get( realUrl );
			if( attributes == null ) {
				attributes = new ArrayList<XmlAttribute> ();
				this.urlsAndXmlFilesAttributes.put( realUrl, attributes );
			}

			attributes.add( fileAttribute );

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	private static FileImportManager manager = new FileImportManager ();

	/**
	 * @return the file import manager instance
	 */
	public static FileImportManager getFileImportManager() {
		return manager;
	}
}
