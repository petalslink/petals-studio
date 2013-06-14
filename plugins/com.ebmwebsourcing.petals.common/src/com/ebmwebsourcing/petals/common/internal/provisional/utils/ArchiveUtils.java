/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * A sedt of utilities related to archives (ZIP...).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ArchiveUtils {

	/**
	 * Copies a Petals archive but replaces the JBI descriptor by another one.
	 * @param sourceFile the source archive
	 * @param targetFile the target archive (cannot exist)
	 * @param newJbiXmlContent the new content for the jvi.xml file
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void copyAndUpdatePetalsArchive( File sourceFile, File targetFile, ByteArrayInputStream newJbiXmlContent )
	throws ZipException, IOException {

		// Create the target file
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream( targetFile ));

		// Open the source file
		ZipFile zipFile = new ZipFile( sourceFile );
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		// And start the copy
		while( entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();

			if( "META-INF/jbi.xml".equals( entry.getName())) {
				ZipEntry newEntry = new ZipEntry( entry.getName());
				out.putNextEntry( newEntry );
				IoUtils.copyStream( newJbiXmlContent, out );

			} else {
				out.putNextEntry( entry );
				IoUtils.copyStream( zipFile.getInputStream( entry ), out );
			}

			out.closeEntry();
		}

		// Close the streams
		out.close();
		zipFile.close();
	}
}
