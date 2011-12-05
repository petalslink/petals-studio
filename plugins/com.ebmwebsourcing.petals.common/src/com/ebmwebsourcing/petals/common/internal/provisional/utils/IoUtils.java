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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class IoUtils {

	/**
	 * Deletes files recursively.
	 * @param files the files to delete
	 * @throws IOException if a file could not be deleted
	 */
	public static void deleteFilesRecursively( File... files ) throws IOException {

		if( files == null )
			return;

		for( File file : files ) {
			if( file.exists()) {
				if( file.isDirectory())
					deleteFilesRecursively( file.listFiles());

				if( ! file.delete())
					throw new IOException( file.getAbsolutePath() + " could not be deleted." );
			}
		}
	}


	/**
	 * Copies the content from in into os.
	 * <p>
	 * Neither <i>in</i> nor <i>os</i> are closed by this method.<br />
	 * They must be explicitly closed after this method is called.
	 * </p>
	 *
	 * @param in
	 * @param os
	 * @throws IOException
	 */
	public static void copyStream( InputStream in, OutputStream os ) throws IOException {

		byte[] buf = new byte[ 1024 ];
		int len;
		while((len = in.read( buf )) > 0) {
			os.write( buf, 0, len );
		}
	}


	/**
	 * Copies the content from in into outputFile.
	 * <p>
	 * <i>in</i> is not closed by this method.<br />
	 * It must be explicitly closed after this method is called.
	 * </p>
	 *
	 * @param in
	 * @param outputFile will be created if it does not exist
	 * @throws IOException
	 */
	public static void copyStream( InputStream in, File outputFile ) throws IOException {

		if( ! outputFile.exists() && ! outputFile.createNewFile())
			throw new IOException( "Failed to create " + outputFile.getAbsolutePath() + "." );

		OutputStream os = new FileOutputStream( outputFile );
		copyStream( in, os );
		os.close ();
	}


	/**
	 * Copies the content from inputFile into outputFile.
	 *
	 * @param inputFile
	 * @param outputFile will be created if it does not exist
	 * @throws IOException
	 */
	public static void copyStream( File inputFile, File outputFile ) throws IOException {

		InputStream is = new FileInputStream( inputFile );
		copyStream( is, outputFile );
		is.close();
	}


	/**
	 * Copies the content from inputFile into an output stream.
	 *
	 * @param inputFile
	 * @param os the output stream
	 * @throws IOException
	 */
	public static void copyStream( File inputFile, OutputStream os ) throws IOException {

		InputStream is = new FileInputStream( inputFile );
		copyStream( is, os );
		is.close();
	}


	/**
	 * Reads a text file content and returns it as a string.
	 * <p>
	 * The file is tried to be read with UTF-8 encoding.
	 * If it fails, the default system encoding is used.
	 * </p>
	 *
	 * @param file the file whose content must be loaded
	 * @return the file content
	 * @throws IOException if the file content could not be read
	 */
	public static String readFileContent( File file ) throws IOException {

		String result = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IoUtils.copyStream( file, os );
		try {
			result = os.toString( "UTF-8" );
		} catch( Exception e ) {
			result = os.toString();
		}

		return result;
	}


	/**
	 * Reads a text file content and returns it as a string.
	 *
	 * @param file the file whose content must be loaded
	 * @return the file content
	 * @throws IOException if the file content could not be read
	 */
	public static String readFileContent( IFile file ) throws IOException {
		return readFileContent( file.getLocation().toFile());
	}


	/**
	 * Creates a clean file name from a string.
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
		if( markIndex != -1 ) {

			// Parse with respect to the first '?'
			String beforeMark, afterMark;
			if( markIndex == 0 ) {
				beforeMark = "";
				afterMark = fileName;
			}
			else if( markIndex + 1 < fileName.length()) {
				beforeMark = fileName.substring( 0, markIndex );
				afterMark = fileName.substring( markIndex + 1 );
			}
			else {
				beforeMark = fileName;
				afterMark = "";
			}

			// Build the file name
			if( "wsdl".equalsIgnoreCase( afterMark )) {
				if( beforeMark.length() > 0 )
					fileName = beforeMark + ".wsdl";
				else
					fileName = "importedWsdl.wsdl";
			}

			// File imported by a WSDL (?wsdl=toto.xsd)
			else if( afterMark.startsWith( "wsdl=" )) {
				afterMark = afterMark.substring( 5 );
				if( afterMark.length() > 0 )
					fileName = afterMark;
			}
		}

		filePath = startsWithSlash ? "/" : "";
		StringBuffer buf = new StringBuffer( filePath );
		for( int i=0; i<parts.length - 1; i++ )
			buf.append( parts[ i ] + "/" );
		buf.append( fileName );

		// In fact, replace every character except numbers, letters, '.', '_' and '-'.
		filePath = buf.toString().replaceAll( "[^-./_\\w]", "_" );

		return filePath;
	}


	/**
	 * @param path1 the path of a file
	 * @param path2 the path of a file
	 * @return if path1 is in an ancestor of path2, the relative path of path2 with respect to path1.
	 * The absolute path of path2 otherwise.
	 */
	public static IPath getBasicRelativePath( IPath path1, IPath path2 ) {

		IPath parentPath;
		if( path1.toFile().isFile())
			parentPath = path1.removeLastSegments( 1 );
		else
			parentPath = path1;

		if( parentPath.isPrefixOf( path2 )) {
			path2 = path2.removeFirstSegments( parentPath.segmentCount());
			path2 = path2.setDevice( null );
		}

		return path2;
	}


	/**
	 * @param file1
	 * @param file2
	 * @return if file1 is in an ancestor of file2, the relative path of file2 with respect to file1.
	 * The absolute path of file2 otherwise.
	 */
	public static String getBasicRelativePath( File file1, File file2 ) {

		IPath path1 = new Path( file1.getAbsolutePath());
		IPath path2 = new Path( file2.getAbsolutePath());
		return getBasicRelativePath( path1, path2 ).toString();
	}


	/**
	 * Return the relative position of <code>file</code> with respect to originFile.
	 * <p>
	 * Legacy and more complete than {@link #getBasicRelativePath(File, File)}.
	 * </p>
	 *
	 * @param originFile the absolute file which acts as the <i>origin</i>.
	 * @param file the file whose relative path must be computed with respect to originFile.
	 * @return the relative path of <code>file</code> with respect to originFile.
	 * @see UriUtils#getRelativeLocationToUri(URI, URI)
	 */
	public static String getRelativeLocationToFile( File originFile, File file ) {

		String result = file.getAbsolutePath();
		try {
			URI originUri = UriUtils.urlToUri( originFile.toURI().toURL());
			URI fileUri = UriUtils.urlToUri( file.toURI().toURL());
			result = UriUtils.getRelativeLocationToUri( originUri, fileUri ).toString();

		} catch( MalformedURLException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Copies a file (or a directory) in a directory.
	 *
	 * @param source a file or a directory
	 * <p>
	 * If source is a directory, this directory will be copied in the target directory.
	 * </p>
	 *
	 * @param targetDirectory the directory in which the file must be copied
	 * @param deleteSourceAfter true to delete the source file after the copy is done
	 * @throws IOException if something went wrong
	 */
	public static void copyFileInDirectory( File source, File targetDirectory, boolean deleteSourceAfter )
	throws IOException {

		File target = new File( targetDirectory, source.getName());
		copyFile( source, target, deleteSourceAfter );
	}


	/**
	 * Copies a source file in a target file.
	 *
	 * @param source a file or a directory
	 * <p>
	 * If source is a directory, only its content is copied in the target directory.
	 * </p>
	 *
	 * @param target a file or a directory (must have the same type than the source: file or directory)
	 * @param deleteSourceAfter true to delete the source file after the copy is done
	 * @throws IOException
	 */
	public static void copyFile( File source, File target, boolean deleteSourceAfter )
	throws IOException {

		// Copy a directory
		if( source.isDirectory()) {
			if( ! target.exists() && ! target.mkdir())
				throw new IOException( "Could not create the directory " + target.getAbsolutePath());
			else if( target.exists() && ! target.isDirectory())
				throw new IOException( target + " already exists and is not a directory." );

			File[] children = source.listFiles();
			for( File sourceChild : children ) {
				File destChild = new File( target, sourceChild.getName());
				copyFile( sourceChild, destChild, deleteSourceAfter );
			}
		}

		// Copy a file
		else {
			if( ! target.exists() && ! target.createNewFile())
				throw new IOException( "Could not create the file " + target.getAbsolutePath());
			else if( target.exists() && ! target.isFile())
				throw new IOException( target + " already exists and is not a file." );

			copyStream( source, target );
		}

		if( deleteSourceAfter )
			deleteFilesRecursively( source );
	}


	/**
	 * Moves a source file (or directory) to another location in a robust way.
	 * <p>
	 * Directory handling is classic: the move results from the creation of another directory
	 * and in which is moved the content from the source directory.
	 * </p>
	 * <p>
	 * Files are tried to be moved using {@link File#renameTo(File)}.<br />
	 * If it fails, it copies the source file in the target file, before deleting the source.
	 * </p>
	 *
	 * @param source the source file (or directory)
	 * @param target the target file (or directory)
	 * @param overwrite true to overwrite the target file
	 * <p>
	 * If the target is a file, the content is overwritten.<br />
	 * If the target is a directory, the directory is deleted before being replaced
	 * by the moved directory.
	 * </p>
	 *
	 * @throws IOException if something went wrong
	 */
	public static void moveFileRobustly( File source, File target, boolean overwrite ) throws IOException {

		// Move a directory
		if( source.isDirectory()) {

			// If it exists and needs to be overwritten, delete it with all its contents
			if( target.exists()) {
				if( ! overwrite )
					throw new IOException( target + " already exists (overwrite = false)." );
				else if( ! target.isDirectory())
					throw new IOException( target + " already exists and is not a directory." );

				deleteFilesRecursively( target );
			}

			if( ! target.mkdir())
				throw new IOException( "Could not create " + target.getAbsolutePath());

			File[] children = source.listFiles();
			for( File sourceChild : children ) {
				File targetChild = new File( target, sourceChild.getName());
				moveFileRobustly( sourceChild, targetChild, overwrite );
			}

			deleteFilesRecursively( source );
		}

		// Move a file
		else {
			if( target.exists()) {
				if( ! overwrite )
					throw new IOException( target + " already exists (overwrite = false)." );
				else if( ! target.isFile())
					throw new IOException( target + " already exists and is not a file." );

				copyStream( source, target );
			}
			else if( ! source.renameTo( target ))
				copyFile( source, target, true );
		}
	}
}
