/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class IoUtils {

	/**
	 * Private constructor for utility class.
	 */
	private IoUtils() {
		// nothing
	}


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
	 * Return the relative position of <code>file</code> with respect to originFile.
	 * @param originFile the absolute file which acts as the <i>origin</i>.
	 * @param file the file whose relative path must be computed with respect to originFile.
	 * @return the relative path of <code>file</code> with respect to originFile.
	 * @see UriAndUrlHelper#getRelativeLocationToUri(URI, URI)
	 */
	public static String getRelativeLocationToFile( File originFile, File file ) {

		String result = file.getAbsolutePath();
		try {
			URI originUri = UriAndUrlHelper.urlToUri( originFile.toURI().toURL());
			URI fileUri = UriAndUrlHelper.urlToUri( file.toURI().toURL());
			result = UriAndUrlHelper.getRelativeLocationToUri( originUri, fileUri ).toString();

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


	/**
	 * Converts an URI into a file.
	 * <p>
	 * The returned file might not exist.
	 * </p>
	 *
	 * @param uri any URI
	 * @return the file if the URI points to a file, null otherwise
	 */
	public static File convertToFile( String uri ) {
		return convertToFile( UriAndUrlHelper.urlToUri( uri ));
	}


	/**
	 * Converts an URI into a file.
	 * <p>
	 * The returned file might not exist.
	 * </p>
	 *
	 * @param uri any URI
	 * @return the file if the URI points to a file, null otherwise
	 */
	public static File convertToFile( URI uri ) {

		File result = null;
		try {
			if( uri != null )
				result = new File( uri );

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		return result;
	}


	/**
	 * Closes a stream quietly.
	 * @param is an input stream
	 */
	public static void closeQuietly( InputStream is ) {
		try {
			if( is != null )
				is.close();

		} catch( IOException e ) {
			// nothing
		}
	}


	/**
	 * Closes a stream quietly.
	 * @param os an output stream
	 */
	public static void closeQuietly( OutputStream os ) {
		try {
			if( os != null )
				os.close();

		} catch( IOException e ) {
			// nothing
		}
	}
}
