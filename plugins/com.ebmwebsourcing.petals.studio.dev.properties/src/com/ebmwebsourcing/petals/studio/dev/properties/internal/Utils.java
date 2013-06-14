/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties.internal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Vincent Zurczak - Linagora
 */
public class Utils {

	/**
	 * @param string
	 * @return true if the argument is null or is only made up of white spaces.
	 */
	public static boolean isEmpty( String string ) {
		return string == null || string.trim().length() == 0;
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
	 * Forces the creation of a file and all of the above directories.
	 * @param f a file
	 * @param content the file content (not null)
	 * @param monitor a progress monitor
	 * @throws CoreException
	 */
	public static void forceIFileCreation( IFile f, String content, IProgressMonitor monitor ) throws CoreException {

		if( ! f.exists()) {
			if( ! f.getParent().exists())
				forceIFolderCreation((IFolder) f.getParent(), monitor );

			f.create( new ByteArrayInputStream( content.getBytes()), true, monitor );
			monitor.worked( 1 );
		}
	}


	/**
	 * Forces the creation of a folder and all of the above directories.
	 * @param f a folder
	 * @param monitor a progress monitor
	 * @throws CoreException
	 */
	public static void forceIFolderCreation( IFolder f, IProgressMonitor monitor ) throws CoreException {

		if( ! f.exists()) {
			if( ! f.getParent().exists())
				forceIFolderCreation((IFolder) f.getParent(), monitor );

			f.create( true, true, monitor );
			monitor.worked( 1 );
		}
	}
}
