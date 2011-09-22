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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * A class used to archive files in a *.zip file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Zipper {

	/** The unique instance of this class. */
	private static Zipper instance = new Zipper();

	/** Private constructor (singleton pattern). */
	private Zipper() {};

	/**
	 * @return the unique instance of this class.
	 */
	public static Zipper getInstance() {
		return instance;
	}

	/**
	 * This method allows you to create a zip file which contains a set of {@link File} objects given in parameter.
	 * 
	 * @param zipFile the resulting zip file.
	 * @param filesToZip the list of files to add into the zip (key = entry, value = file).
	 * @throws IOException might be thrown while interacting with the zip file.
	 */
	public final File createZipFile( File zipFile, Map<String, File> filesToZip ) throws IOException {

		ZipOutputStream zos = new ZipOutputStream( new FileOutputStream( zipFile ));

		// Content
		for( Map.Entry<String, File> entry : filesToZip.entrySet()) {
			if( entry.getValue().exists())
				addFileToZip( zos, entry.getValue(), entry.getKey());
		}

		// Comment
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat( "MM dd, yyyy 'at' HH:mm:ss" );
		String comment = "File generated on " + sdf.format( calendar.getTime());
		zos.setComment( comment );

		zos.close();
		return zipFile;
	}

	/**
	 * This method allows you to create a zip file which contains a set of files or directories given in parameter.
	 * 
	 * @param zipPath the path where the zip file should be saved.
	 * @param filesToZip the list of files to add into the zip (key = entry, value = file).
	 * @throws IOException might be thrown while interacting with the zip file.
	 */
	public final File createZipFile( String zipFilePath, Map<String, File> filesToZip ) throws IOException {
		return createZipFile( new File( zipFilePath ), filesToZip );
	}

	/**
	 * This method adds a file into the zip archive.
	 * 
	 * @param out the zip file.
	 * @param iFile the file to add into the zip.
	 * @param entry the entry for the file to add into the zip.
	 */
	private final void addFileToZip( ZipOutputStream out, File file, String entry ) {

		byte[] buf = new byte[ 1024 ];	// Create a buffer for reading the files
		ZipEntry ze = new ZipEntry( entry );
		try {
			out.putNextEntry( ze );
			InputStream in = new FileInputStream( file );
			int len;
			while ((len = in.read( buf )) > 0) {
				out.write( buf, 0, len);
			}
			in.close ();
			out.closeEntry ();

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
