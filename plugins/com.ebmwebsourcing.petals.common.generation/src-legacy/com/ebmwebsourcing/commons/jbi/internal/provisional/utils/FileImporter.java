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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Import one or several files into a folder from their URLs.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileImporter {

	/** The unique instance of this class. */
	private static FileImporter instance = new FileImporter();
	/** */
	private static int renamedCpt = 0;



	/** Private constructor (singleton pattern). */
	private FileImporter() {}


	/**
	 * @return the unique instance of this class.
	 */
	public static FileImporter getInstance() {
		return instance;
	}


	/**
	 * Import a file into a folder.
	 * If a file to import has the same name than a file in the collecting folder,
	 * here is the way it works:
	 * <ul>
	 * <li>If <b>overwrite</b> is true, then the file is overwritten.</li>
	 * <li>Otherwise, if <b>rename</b> is true, the imported file will be renamed (by appending the date at the end).</li>
	 * <li>Otherwise, an exception will be raised stating that this file could not be imported.</li>
	 * </ul>
	 * 
	 * @param url the URL of a file to import.
	 * @param fileRelativePath the relative path of the file in <b>folder</b>.
	 * It can be null, but in any case, it should not begin with a slash.
	 * If it is the same thing than the file name found in the URL, then
	 * no intermediate folder is created.
	 * 
	 * @param folder the folder where these files have to be imported.
	 * @param overwrite true if a file should be overwritten in case of name conflict.
	 * @param rename true if a file should be renamed in case of name conflict.
	 * @param showHiddenFiles true if hidden files should be shown.
	 * Typically, if a file is hidden, its name starts with a dot. Set this parameter to true
	 * will remove this dot.
	 * Example: ".toto.txt" becomes "toto.txt".
	 * @param reportImportConflict
	 * 
	 * @return the path of the imported file (the file may have been renamed)
	 * @throws FileImportsException if one or several exceptions occurred while importing files.
	 * @throws IllegalArgumentException if the folder is not a directory.
	 */
	public String importFile(
				URL url, String fileRelativePath, File folder,
				boolean overwrite, boolean rename,
				boolean showHiddenFiles, boolean reportImportConflict )
	throws FileImportsException, IllegalArgumentException {

		Map<URL, String> urlsAndRelativePath = new HashMap<URL, String> ();
		fileRelativePath = fileRelativePath == null ? "" : fileRelativePath;
		urlsAndRelativePath.put( url, fileRelativePath );

		Map<URL, String> importedFiles = importFiles( urlsAndRelativePath, folder, overwrite,
					rename, showHiddenFiles, reportImportConflict );
		return importedFiles.get( url );
	}


	/**
	 * Import a list of files into a folder.
	 * If an exception is thrown while importing a file, it does not prevent this method
	 * from importing the other files in the list. This exception is stored into a FileImportsException.
	 * Once all the files have been imported (or tried to), if the FileImportsException has stored
	 * any exception, it is thrown. This way, the user can determine which files could not be imported and why.
	 * 
	 * If a file to import has the same name than a file in the collecting folder, here is the way it works:
	 * <ul>
	 * <li>If <b>overwrite</b> is true, then the file is overwritten.</li>
	 * <li>Otherwise, if <b>rename</b> is true, the imported file will be renamed (by appending the date at the end
	 * 		- millisecond precision). If the newly renamed file also exists, an exception will be thrown.</li>
	 * <li>Otherwise, an exception will be raised stating that this file could not be imported.</li>
	 * </ul>
	 * 
	 * @param urlsAndRelativePath a map of file URLs to import.
	 * Key = file URL.
	 * Value = relative path with respect to folder.
	 * Let the value empty ("") to import the file at the root of the folder.
	 * If this value is the same than the file name found in the URL, then no
	 * intermediate folder is created.
	 * If the relative path leads the file to be outside the destination folder, then this file
	 * is placed at the root of the folder.
	 * In any case, the relative path should not begin with a slash and should not be null.
	 * 
	 * @param folder the folder where these files have to be imported.
	 * @param overwrite true if a file should be overwritten in case of name conflict.
	 * @param rename true if a file should be renamed in case of name conflict.
	 * @param showHiddenFiles true if hidden files should be shown.
	 * Typically, if a file is hidden, its name starts with a dot. Set this parameter to true
	 * will remove this dot.
	 * Example: ".toto.txt" becomes "toto.txt".
	 * 
	 * TODO: Typically, if a file is hidden, its name starts with a dot. Well, not on Windows.
	 * @param reportImportConflict
	 * 
	 * @return a map containing the URLs with the file names (possibly renamed).
	 * The key is the URL and the value is the relative file path. Never null and should contain the file name.
	 * @throws FileImportsException if one or several exceptions occurred while importing files.
	 * @throws IllegalArgumentException if the folder is not a directory.
	 */
	public Map<URL, String> importFiles(
				Map<URL, String> urlsAndRelativePath,
				File folder,
				boolean overwrite,
				boolean rename,
				boolean showHiddenFiles,
				boolean reportImportConflict )
				throws FileImportsException, IllegalArgumentException {

		if( !folder.isDirectory())
			throw new IllegalArgumentException( folder.getAbsolutePath() + " is not a directory." );

		Map<URL, String> fileNames = new HashMap<URL, String> ();
		FileImportsException finalException = new FileImportsException();

		// Import files.
		URL lastUrl = null;
		for( Map.Entry<URL, String> importedUrl : urlsAndRelativePath.entrySet()) {
			boolean importFailed = false;

			try {
				lastUrl = importedUrl.getKey();

				/* Get the file name
				 * It can be either in the relative path, or if it's not,
				 * we have to take it from the URL
				 */
				String path = importedUrl.getValue();
				path = path.replaceAll( "\\\\", "/" );
				String[] parts = path.split( "/" );
				String fileName = parts[ parts.length - 1 ];


				StringBuilder sb = new StringBuilder();
				for( int i=0; i<parts.length - 1; i++ )
					sb.append( parts[ i ] + "/" );

				path = sb.toString();
				if( fileName.length() == 0 ) {	// Get the file name from the URL
					String urlPath = importedUrl.getKey().toExternalForm();
					urlPath = urlPath.replaceAll( "\\\\", "/" );
					parts = urlPath.split( "/" );
					fileName = parts[ parts.length - 1 ];
				}

				if( fileName.startsWith( "." ) && showHiddenFiles )
					fileName = fileName.substring( 1 );
				fileName = createCleanFileName( fileName );

				// Make sure this relative path does not save files outside the destination folder.
				File destinationFolder = new File( folder, path );
				String destinationFolderPath = destinationFolder.getAbsolutePath().toLowerCase();
				String folderPath = folder.getAbsolutePath().toLowerCase();
				if( !destinationFolderPath.startsWith( folderPath ))
					path = "";

				// Create intermediate folders.
				if( path.length() > 0 ) {
					folder = new File( folder, path );
					if( ! folder.exists() && ! folder.mkdirs())
						throw new IOException( "Could not create " + folder.getAbsolutePath());
				}

				// Prepare files.
				InputStream source = importedUrl.getKey().openStream();
				File newFile = new File( folder, fileName );
				if( ! newFile.exists()) {
					newFile.createNewFile();
				}
				else if( overwrite ) {
					PrintWriter printwriter = new PrintWriter( new FileOutputStream( newFile ));
					printwriter.println( "" );
					printwriter.close();
				}
				else if( rename ) {
					int dotPosition = fileName.lastIndexOf( "." );
					if( dotPosition <= 0 )
						fileName += "_renamed_" + renamedCpt++;
					else
						fileName =
							JbiNameFormatter.insertSuffixBeforeFileExtension(
										fileName, "_renamed_" + renamedCpt++ );

					// There are few chances for this file to already exist.
					// Otherwise, it will throw an exception.
					newFile = new File( folder, fileName );
					if( ! newFile.createNewFile())
						throw new IOException( "Could not create " + newFile.getAbsolutePath());
				}
				else
					importFailed = true;

				// Register file name
				fileNames.put( importedUrl.getKey(), newFile.getAbsolutePath());

				// Exception logged if import fails.
				if( importFailed ){
					if( reportImportConflict ) {
						Exception e = new IOException( "File " + newFile.getAbsolutePath() + " already exists. Import failed." );
						finalException.failedImportUrls.put( lastUrl, e );
					}

					continue;
				}

				// Write into the file.
				OutputStream out = new FileOutputStream( newFile );
				byte buf[] = new byte[ 1024 ];
				int len;
				while(( len = source.read( buf )) > 0) {
					out.write( buf, 0, len);
				}
				out.close ();
				source.close ();

			} catch( Exception e ) {
				finalException.failedImportUrls.put( lastUrl, e );
			}
		}

		// Throw exception ?
		if( finalException.hasExceptions())
			throw finalException;

		return fileNames;
	}


	/**
	 * Create a clean file name from a string.
	 * <p>
	 * The string argument corresponds to a file name or a file path.
	 * </p>
	 * 
	 * @param filePath
	 * @return a file name with no special character
	 */
	public static String createCleanFileName( String filePath ) {

		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat( "dd_MM_yyyy__'at'__HH_mm_ss" );

		if( filePath == null || filePath.length() == 0 )
			return "importedFile__" + sdf.format( calendar.getTime());

		filePath = filePath.replaceAll( "\\\\", "/" );
		boolean startsWithSlash = filePath.charAt( 0 ) == '/';
		String[] parts = filePath.split( "/" );
		String fileName = parts[ parts.length - 1 ];

		// Replace strange characters in file name.
		// Case "jsp?value=""
		int markIndex = fileName.lastIndexOf( '?' );
		if( markIndex > 0 ) {
			if( markIndex + 1 < fileName.length())
				fileName = fileName.substring( markIndex + 1 );
			else
				fileName = "importedFile__" + sdf.format( calendar.getTime());
		}

		if( fileName.equalsIgnoreCase( "wsdl" ))
			fileName += ".wsdl";

		filePath = startsWithSlash ? "/" : "";
		for( int i=0; i<parts.length - 1; i++ )
			filePath += parts[ i ] + "/";
		filePath += fileName;

		// In fact, replace every character except numbers, letters, '.', '_' and '-'.
		filePath = filePath.replaceAll( "[^-./_\\w]", "_" );

		return filePath;
	}


	/**
	 * List exception which occurred while importing a list of files.
	 */
	public static class FileImportsException extends Exception {

		/** */
		private static final long serialVersionUID = 3562089833670361410L;

		/** URL that failed to be imported and the associated exceptions. */
		public Map<URL, Exception> failedImportUrls = new HashMap<URL, Exception> ();


		/**
		 * @see List#isEmpty()
		 * @return true if the class has at least one exception.
		 */
		protected boolean hasExceptions() {
			return this.failedImportUrls.size() >= 1;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Throwable#toString()
		 */
		@Override
		public String toString() {
			StringWriter stringWriter = new StringWriter();
			PrintWriter result = new PrintWriter( stringWriter );

			// Basic message.
			if( this.failedImportUrls.isEmpty())
				result = result.append(
				"FileImportsException: no error was found while importing files.\n" );
			else if( this.failedImportUrls.size() > 1 )
				result = result.append(
							"FileImportsException: " + this.failedImportUrls.size() + " errors were found while importing files.\n" );
			else
				result = result.append(
				"FileImportsException: 1 error was found while importing files.\n" );

			// Detailed stack trace.
			for( Map.Entry<URL, Exception> e : this.failedImportUrls.entrySet()) {
				result.print( "URL " + e.getKey() + " failed to be imported." );
				result.print( e.getValue().getMessage());
				e.getValue().printStackTrace( result );
			}

			result.flush();
			return stringWriter.toString();
		}
	}
}
