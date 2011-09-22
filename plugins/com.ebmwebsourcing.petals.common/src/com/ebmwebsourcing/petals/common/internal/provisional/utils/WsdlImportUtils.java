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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * A class that can import a WSDL (1.1 and 2.0) and all its dependencies (XSD, XML, WSDL).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlImportUtils {

	private final List<WsdlImportError> errors = new ArrayList<WsdlImportError>();
	private static SAXBuilder saxBuilder = new SAXBuilder();
	private static XMLOutputter xmlOutputter = new XMLOutputter( Format.getPrettyFormat());

	public static String WSDL20_NS = "http://www.w3.org/ns/wsdl";
	public static String WSDL11_NS = "http://schemas.xmlsoap.org/wsdl/";
	public static String SCHEMA_NS = "http://www.w3.org/2001/XMLSchema";

	public static org.jdom.Namespace XSI = org.jdom.Namespace.getNamespace( "xsi", "http://http://www.w3.org/2001/XMLSchema-instance" );
	public static org.jdom.Namespace WSDL20 = org.jdom.Namespace.getNamespace( WSDL20_NS );
	public static org.jdom.Namespace WSDL11 = org.jdom.Namespace.getNamespace( WSDL11_NS );
	public static org.jdom.Namespace SCHEMA = org.jdom.Namespace.getNamespace( SCHEMA_NS );

	/**
	 * The file importer.
	 */
	private FileImporter fileImporter;

	/**
	 * A map containing the URIs that were already imported.
	 * <p>
	 * Key = the URI of the imported file as a string
	 * Value = the file after the import
	 * </p>
	 */
	private final Map<String,File> alreadyImportedUris = new HashMap<String,File> ();



	/**
	 * Imports a WSDL or a XSD will all its dependencies.
	 * @param targetDirectory
	 * @param wsdlUris
	 * @return
	 * FIXME: review error handling
	 */
	public Map<String,File> importWsdlOrXsdAndDependencies( File targetDirectory, String... wsdlUris ) {

		this.errors.clear();
		this.fileImporter = new FileImporter( targetDirectory );
		for( String wsdlUriAsString : wsdlUris ) {
			URI wsdlUri = UriUtils.urlToUri( wsdlUriAsString.replaceAll( "///", "/" ));
			importWsdlOrXsdAndDependencies( wsdlUri, targetDirectory, "" );
		}

		return this.errors.isEmpty() ? this.alreadyImportedUris : null;
	}


	/**
	 * Imports a WSDL or a XSD (available at <b>referencerUri</b>) and its dependencies in <b>destinationFolder</b>.
	 * <p>
	 * A referencer is a file that imports other file(s).<br />
	 * Imports refer to the imported file.
	 * </p>
	 * <p>
	 * All the imports element of the document are resolved. The associated files
	 * are imported in the given folder and renamed if necessary (in which case,
	 * the location is updated in the referencer file).
	 * </p>
	 * <p>
	 * This method processes the imports recursively.<br />
	 * A call to this method (without the recursive calls) is in charge of
	 * importing the 1-depth-level imports and make the required renaming operations.
	 * </p>
	 * <p>
	 * Dependency cycles result in an exception and in the abortion of the current processing.
	 * </p>
	 * 
	 * @param referencerUri the URI of the file that may have imports to process
	 * @param targetDirectory the folder where the files should be saved.
	 * @param locationAttrValue
	 * @return the full file path after import, or null if the document is an invalid XSD or WSDL file.
	 */
	private File importWsdlOrXsdAndDependencies(
				final URI referencerUri,
				final File targetDirectory,
				final String locationAttrValue
	) {

		// Get the WSDL document.
		Document referencerDocument = null;
		try {
			URLConnection connection = referencerUri.toURL().openConnection();
			InputStream is = connection.getInputStream();
			referencerDocument = saxBuilder.build( is );

		} catch( Exception e ) {
			String msg = "The document at " + referencerUri.toString() + " could not be parsed (critical error).";
			this.errors.add( new WsdlImportError( msg, e, WsdlImportStatus.error ));
			return null;
		}


		// Check the root element is a valid one.
		Element root = referencerDocument.getRootElement();
		String rootName = root.getName();

		if( ! rootName.equals( "definitions" )&& root.getNamespaceURI().equals( WSDL11_NS ))
			return null;

		if( ! rootName.equals( "description" ) && root.getNamespaceURI().equals( WSDL20_NS ))
			return null;

		if( ! rootName.equals( "schema" ) && root.getNamespaceURI().equals( SCHEMA_NS ))
			return null;


		// Import this file
		boolean isAbsoluteUrl = false;
		try {
			new URL( locationAttrValue );
			isAbsoluteUrl = true;

		} catch( Exception e ) {
			// nothing
		}

		String relativePosition = ( isAbsoluteUrl || locationAttrValue == null ) ? "" : locationAttrValue;
		File importedFile = this.alreadyImportedUris.get( referencerUri.toString());
		try {
			if( importedFile == null ) {
				importedFile = this.fileImporter.importFile(
							referencerUri.toString(),
							relativePosition,
							targetDirectory,
							false, true, false );

				if( importedFile == null )
					throw new NullPointerException( "The imported file was not found." );
			}

		} catch( Exception e ) {
			e.printStackTrace();
			String msg = "The file at " + referencerUri.toString() + " could not be imported.";
			this.errors.add( new WsdlImportError( msg, e, WsdlImportStatus.error ));
		}


		// Associate the URI (before import) with the file path (after import)
		this.alreadyImportedUris.put( referencerUri.toString(), importedFile );


		// Get all the imports (no recursion)
		Map<Element, String> flatImportLocations = getFlatImportLocations( root );
		for( Map.Entry<Element, String> flatImportLocation : flatImportLocations.entrySet()) {

			String locationAttribute = flatImportLocation.getValue();
			URI locationAttributeAsUri;
			try {
				locationAttributeAsUri = UriUtils.buildNewURI( referencerUri, locationAttribute );

			} catch( Exception e ) {
				this.errors.add( new WsdlImportError( e.getMessage(), e, WsdlImportStatus.error ));
				continue;
			}

			// Import the remote file - make sure it was not already imported.
			File locationAttributeAsFile = this.alreadyImportedUris.get( locationAttributeAsUri.toString());
			if( locationAttributeAsFile == null ) {
				locationAttributeAsFile = importWsdlOrXsdAndDependencies(
							locationAttributeAsUri,
							importedFile.getParentFile(),
							locationAttribute );
			}

			// Compute the relative path of "locationAttributeAsFile" with respect to "importedFile"
			String fileRelativeLocation = IoUtils.getRelativeLocationToFile( importedFile, locationAttributeAsFile );
			Element element = flatImportLocation.getKey();

			if( null != element.getAttributeValue( "location" ))
				element.getAttribute( "location" ).setValue( fileRelativeLocation );
			else if( null != element.getAttributeValue( "schemaLocation" ))
				element.getAttribute( "schemaLocation" ).setValue( fileRelativeLocation );
			else {
				String msg =
					"The import location '" + locationAttribute + "' could not be replaced by '"
					+ fileRelativeLocation + "' into the file '" + importedFile.getAbsolutePath() + "'.";
				this.errors.add( new WsdlImportError( msg, null, WsdlImportStatus.error ));
			}

		}	// End of FOR loop.


		// Last step: propagate the changes in the documents.
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( importedFile );
			xmlOutputter.output( referencerDocument, fos );

		} catch( Exception e ) {
			String msg =
				"The locations of imported files could not be updated in a document that references them"
				+ " (referencer file = " + importedFile.getAbsolutePath() + ").";
			this.errors.add( new WsdlImportError( msg, e, WsdlImportStatus.error ));

		} finally {
			if( fos != null ) {
				try {
					fos.close();
				} catch( IOException e ) {
					// nothing
				}
			}
		}

		return importedFile;
	}


	/**
	 * Gets all WSDL import elements for a given JDOM element.
	 * <p>
	 * For a WSDL file represented as JDOM document, import elements are retrieved
	 * by this method and the locations of the referenced files are returned as a list of string.
	 * Thus, the most logical use of this method is giving it the root element of a WSDL document
	 * to get all the imported files' locations.
	 * </p>
	 * <p>
	 * This method only gets the flat imports, not the recursive imports (the files
	 * referenced by the referenced files of this WSDL).
	 * </p>
	 * 
	 * @param docRoot
	 * @return a non-null list of referenced files' locations.
	 * <p>
	 * Key = DOM element that contain the import.
	 * Value = import location as written in the document.
	 * </p>
	 */
	private Map<Element, String> getFlatImportLocations( Element docRoot ) {

		List<Element> importElements = new ArrayList<Element> ();

		// Get in WSDL import or include
		for( Object impt : docRoot.getChildren( "import", WSDL11 )) {
			importElements.add((Element) impt);
		}
		for( Object impt : docRoot.getChildren( "import", WSDL20 )) {
			importElements.add((Element) impt);
		}
		for( Object impt : docRoot.getChildren( "include", WSDL20 )) {
			importElements.add((Element) impt);
		}

		// Get in schema
		Element types = docRoot.getChild( "types", WSDL11 );
		List<Element> schemas = new ArrayList<Element> ();

		if( "schema".equals( docRoot.getName())
					&& SCHEMA_NS.equals( docRoot.getNamespaceURI())) {
			schemas.add( docRoot );
		}
		else if( types != null ) {
			for( Object schema : types.getChildren( "schema", SCHEMA ))
				schemas.add((Element) schema);
		}
		else {
			types = docRoot.getChild( "types", WSDL20 );
			if( types != null ) {
				for( Object schema : types.getChildren( "schema", SCHEMA ))
					schemas.add((Element) schema);
			}
		}

		for( Element schema : schemas ) {
			for( Object impt : schema.getChildren( "import", SCHEMA )) {
				importElements.add((Element) impt);
			}
			for( Object impt : schema.getChildren( "include", SCHEMA )) {
				importElements.add((Element) impt);
			}
		}

		// Get in shemaLocation
		if( docRoot.getAttributeValue( "schemaLocation", XSI ) != null) {
			String val = docRoot.getAttributeValue("schemaLocation", XSI);
			StringTokenizer st = new StringTokenizer( val, " " );
			while( st.hasMoreTokens()) {

				String ns = st.nextToken();
				if( st.hasMoreTokens()) {
					String location = st.nextToken();
					Element e = new Element( "import", WSDL20 );
					e.setAttribute( "schemaLocation", location );
					e.setAttribute( "namespace", ns );
					importElements.add( e );
				}
			}
		}

		// From here, we have a list of import elements for the document argument.
		// Now, we have to get the locations as string and associate them with element in a map.
		Map<Element, String> imports = new HashMap<Element, String> ();
		for( Element importElement : importElements ) {

			String location = importElement.getAttributeValue( "location" );
			location = location != null ? location : importElement.getAttributeValue( "schemaLocation" );
			if( location != null && !"".equals( location ))
				imports.put( importElement, location );
		}

		return imports;
	}


	/**
	 * @return the errors
	 */
	public List<WsdlImportError> getErrors() {
		return this.errors;
	}


	/**
	 * An enumeration to indicate the error level for an error relative to WSDL import.
	 */
	public enum WsdlImportStatus {
		error, warning, info
	}


	/**
	 * A class defining an error relative to WSDL import.
	 */
	public static class WsdlImportError {

		String message;
		Exception exception;
		WsdlImportStatus status;


		/**
		 * Constructor.
		 * @param message
		 * @param exception
		 * @param status
		 */
		public WsdlImportError( String message, Exception exception, WsdlImportStatus status ) {
			this.message = message;
			this.exception = exception;
			this.status = status;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return this.message;
		}

		/**
		 * @return the exception
		 */
		public Exception getException() {
			return this.exception;
		}

		/**
		 * @return the status
		 */
		public WsdlImportStatus getStatus() {
			return this.status;
		}
	}
}
