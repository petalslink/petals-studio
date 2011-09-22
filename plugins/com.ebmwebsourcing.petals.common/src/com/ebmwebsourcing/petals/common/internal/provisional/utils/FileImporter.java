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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Imports one or several files into a folder from their URLs.
 * @author Vincent Zurczak - EBM WebSourcing
 * TODO: review this class, it is quite complicated to understand the way it works
 */
public class FileImporter {

	/**
	 * A counter used when renaming files.
	 */
	private int renamedCpt = 0;

	/**
	 * The top directory.
	 * <p>
	 * Any file imported by this instance is directly (or indirectly) in this directory.
	 * </p>
	 */
	private final File topDirectory;



	/**
	 * Constructor.
	 * @param topDirectory the top directory.
	 * <p>
	 * Any file imported by this instance is directly (or indirectly) in this directory.
	 * </p>
	 */
	public FileImporter( File topDirectory ) {
		this.topDirectory = topDirectory;
	}


	/**
	 * Imports a single file into a folder.
	 * <p>
	 * Equivalent to building a Map with one single element and sent to
	 * {@link #importFiles(Map, File, boolean, boolean, boolean)}.
	 * The key in this map is <i>fileUri</i> and its value is <i>file after import</i>.
	 * </p>
	 * 
	 * @param fileUri
	 * @param targetFileRelativePath
	 * @param targetFolder
	 * @param overwrite
	 * @param rename
	 * @param showHiddenFiles
	 * @return
	 * @throws FileImportsException
	 * @throws IllegalArgumentException
	 * 
	 * @see #importFiles(Map, File, boolean, boolean, boolean)
	 */
	public File importFile(
				String fileUri,
				String targetFileRelativePath,
				File targetFolder,
				boolean overwrite,
				boolean rename,
				boolean showHiddenFiles )
	throws FileImportsException, IllegalArgumentException {

		Map<String,String> urisAndRelativePath = new HashMap<String,String> ();
		if( targetFileRelativePath == null )
			targetFileRelativePath = "";

		urisAndRelativePath.put( fileUri, targetFileRelativePath );
		Map<String,File> importedFiles = importFiles( urisAndRelativePath, targetFolder, overwrite, rename, showHiddenFiles );
		return importedFiles.get( fileUri );
	}


	/**
	 * Imports a list of files into a folder.
	 * <p>
	 * All the files of the list will be imported, even if some fail.<br />
	 * Exceptions are stored into a FileImportsException instance.<br />
	 * Once all the files have been imported (or tried to), if the FileImportsException has stored
	 * any exception, it is thrown. This way, the user can determine which files could not be imported and why.
	 * </p>
	 * 
	 * <p>
	 * If a file to import has the same name than a file in the collecting folder, here is the way it works:
	 * </p>
	 * <ul>
	 * <li>If <b>overwrite</b> is true, then the file is overwritten.</li>
	 * <li>Otherwise, if <b>rename</b> is true, the imported file will be renamed.</li>
	 * <li>Otherwise, an exception will be raised stating that this file could not be imported.</li>
	 * </ul>
	 * 
	 * @param uriToRelativePath a map of file URIs to target path.
	 * <p>
	 * Key = file URI, as a string.<br />
	 * Value = relative path with respect to the target folder.
	 * </p>
	 * <p>
	 * Let the value empty ("") to import the file at the root of the folder.<br />
	 * If this value is the same than the file name found in the URL, then no
	 * intermediate folder is created.<br />
	 * If the relative path leads the file to be outside the destination folder, then this file
	 * is placed at the root of the folder.<br />
	 * In any case, the relative path should not begin with a slash (removed otherwise)
	 * and should not be null (replaced by "").
	 * </p>
	 * 
	 * @param targetFolder the folder where these files have to be imported.
	 * <p>
	 * Intermediate directories are created if necessary.
	 * </p>
	 * 
	 * @param overwrite true if a file should be overwritten in case of name conflict
	 * @param rename true if a file should be renamed in case of name conflict
	 * @param showHiddenFiles true if hidden files should be shown.
	 * <p>
	 * Typically, if a file is hidden, its name starts with a dot. Set this parameter to true
	 * will remove this dot.
	 * </p>
	 * <p>
	 * Example: ".toto.txt" becomes "toto.txt".
	 * </p>
	 * 
	 * @return a map containing the URIs with the file paths (after possible renaming).
	 * <p>
	 * The key is the file URI (as a string) and the value is the file after import.<br />
	 * The result is never null but may be empty.
	 * </p>
	 * 
	 * @throws FileImportsException if one or several exceptions occurred while importing files.
	 * @throws IllegalArgumentException if the folder is not a directory.
	 */
	public Map<String,File> importFiles(
				Map<String, String> uriToRelativePath,
				File targetFolder,
				boolean overwrite,
				boolean rename,
				boolean showHiddenFiles )
				throws FileImportsException, IllegalArgumentException {

		if( ! targetFolder.isDirectory())
			throw new IllegalArgumentException( targetFolder.getAbsolutePath() + " is not a directory." );

		Map<String,File> uriToFiles = new HashMap<String,File> ();
		FileImportsException finalException = new FileImportsException();


		// Import files.
		URI lastUri = null;
		IPath topDirectoryPath = new Path( this.topDirectory.getAbsolutePath()).makeAbsolute();
		for( Map.Entry<String,String> entry : uriToRelativePath.entrySet()) {
			try {
				lastUri = UriUtils.urlToUri( entry.getKey());

				//
				// Preparing the import of the file...
				//

				// Get the target file name - in the relative path or from the URL
				String targetPath = entry.getValue();
				if( targetPath == null )
					targetPath = "";

				targetPath = targetPath.replaceAll( "\\\\", "/" ).trim();
				if( targetPath.startsWith( "/" ))
					targetPath = targetPath.substring( 1 );

				// The file name is not in the relative path, get it from the URI
				String fileName = null;
				if( targetPath.endsWith( "/" )  || targetPath.length() == 0 ) {
					String urlPath = lastUri.toString().replaceAll( "\\\\", "/" );
					String[] parts = urlPath.split( "/" );
					fileName = parts[ parts.length - 1 ];
				}
				// Otherwise, extract the file name from the relative path
				else {
					String[] parts = targetPath.split( "/" );
					fileName = parts[ parts.length - 1 ];

					StringBuffer buf = new StringBuffer();
					for( int i=0; i<parts.length - 1; i++ )
						buf.append( parts[ i ] + "/" );

					targetPath = buf.toString();
				}

				// Clean up the file name
				if( fileName.startsWith( "." ) && showHiddenFiles )
					fileName = fileName.substring( 1 );
				fileName = IoUtils.createCleanFileName( fileName );

				// Make sure the target path is not outside the target folder
				IPath atf = new Path( targetFolder.getAbsolutePath()).append( targetPath ).makeAbsolute();
				if( ! topDirectoryPath.isPrefixOf( atf ))
					targetPath = "";

				// Create intermediate folders.
				if( targetPath.length() > 0 ) {
					targetFolder = new File( targetFolder, targetPath );
					if( ! targetFolder.exists() && ! targetFolder.mkdirs())
						throw new IOException( "Could not create the target directory (" + targetFolder.getAbsolutePath() + ")." );
				}

				//
				// Importing the file...
				//

				// Create the target file
				InputStream source = lastUri.toURL().openStream();
				File newFile = new File( targetFolder, fileName );
				if( ! newFile.exists()) {
					if( ! newFile.createNewFile())
						throw new IOException( "Could not create the target file (" + newFile.getAbsolutePath() + ")." );
				}
				else if( rename ) {
					int dotPosition = fileName.lastIndexOf( "." );
					do {
						if( dotPosition <= 0 )
							fileName += "_renamed_" + this.renamedCpt;
						else
							fileName = insertSuffixBeforeFileExtension( fileName, "_renamed_" + this.renamedCpt );

						newFile = new File( targetFolder, fileName );
						this.renamedCpt ++;

					} while( newFile.exists());

					if( ! newFile.createNewFile())
						throw new IOException( "Could not create the target file (" + newFile.getAbsolutePath() + ")." );
				}
				else if( ! overwrite ) {
					Exception e = new IOException( "File " + newFile.getAbsolutePath() + " already exists. Import failed." );
					finalException.failedImportUris.put( lastUri.toString(), e );
					continue;
				}

				// Register file name
				uriToFiles.put( lastUri.toString(), newFile );

				// Copy the file content in the target file
				OutputStream out = new FileOutputStream( newFile );
				byte buf[] = new byte[ 1024 ];
				int len;
				while(( len = source.read( buf )) > 0) {
					out.write( buf, 0, len);
				}
				out.close ();
				source.close ();

			} catch( FileNotFoundException e ) {
				finalException.failedImportUris.put( lastUri.toString(), e );

			} catch( IOException e ) {
				finalException.failedImportUris.put( lastUri.toString(), e );
			}
		}

		// Throw exception ?
		if( finalException.hasExceptions())
			throw finalException;

		return uriToFiles;
	}


	/**
	 * Inserts a suffix between the file name and its extension.
	 * 
	 * @param fileName the file name
	 * @param suffix the suffix to insert between the file name and the file extension
	 * @return suffix if the file name is null, or the file name followed by suffix if it does
	 * not contain an extension or if it is a hidden file, or the file name with the suffix
	 * at the right position otherwise.
	 */
	public static String insertSuffixBeforeFileExtension( String fileName, String suffix ) {

		if( fileName == null )
			return suffix;

		int index = fileName.lastIndexOf( '.' );
		if( index <= 0 )
			return fileName + suffix;

		return fileName.substring( 0, index ) + suffix + fileName.substring( index );
	}


	/**
	 * An exception that lists failures while importing files.
	 */
	public static class FileImportsException extends Exception {

		/**
		 * The serial ID.
		 */
		private static final long serialVersionUID = 3562089833670361410L;

		/**
		 * URI that failed to be imported and the associated exceptions.
		 */
		public final Map<String, Exception> failedImportUris = new HashMap<String, Exception> ();


		/**
		 * @return true if the class has at least one exception.
		 */
		protected boolean hasExceptions() {
			return this.failedImportUris.size() > 0;
		}


		/*
		 * (non-Javadoc)
		 * @see java.lang.Throwable#toString()
		 */
		@Override
		public String toString() {

			StringWriter stringWriter = new StringWriter();
			PrintWriter result = new PrintWriter( stringWriter );

			// Basic message
			if( this.failedImportUris.isEmpty())
				result = result.append( "No error was found while importing files.\n" );
			else if( this.failedImportUris.size() > 1 )
				result = result.append( this.failedImportUris.size() + " errors were found while importing files.\n" );
			else
				result = result.append( "1 error was found while importing files.\n" );

			// Detailed stack trace
			for( Map.Entry<String, Exception> e : this.failedImportUris.entrySet()) {
				result.print( "URI " + e.getKey() + " failed to be imported." );
				result.print( e.getValue().getMessage());
				e.getValue().printStackTrace( result );
			}

			result.flush();
			return stringWriter.toString();
		}
	}
}
