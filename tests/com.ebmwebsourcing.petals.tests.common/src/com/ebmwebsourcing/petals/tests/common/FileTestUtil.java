/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.tests.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import org.eclipse.core.runtime.Platform;

/**
 * Utilities related to files.
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTestUtil {

	/**
	 * Checks whether a file is open or not.
	 * @param f the file to check
	 * @return true if the file is open, false otherwise
	 */
	public static boolean fileOpen(File f) {
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			RandomAccessFile rf;
			try {
				rf = new RandomAccessFile(f, "rw");
				rf.getChannel().lock().release();
				rf.close();
				return false;
			} catch (Exception ex) {
				return true;
			}
		} else if (Platform.getOS().equals(Platform.OS_LINUX)) {
			InputStream commandOutput = null;
			BufferedReader reader = null;
			try {
				commandOutput = Runtime.getRuntime().exec("/usr/bin/lsof").getInputStream();
				reader = new BufferedReader( new InputStreamReader(commandOutput));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.contains(f.getAbsolutePath())) {
						return true;
					}
				}
				return false;

			} catch (Exception ex) {
				throw new RuntimeException(ex);

			} finally {

				if( reader != null ) {
					try {
						reader.close();
					} catch( IOException e ) {
						e.printStackTrace();
					}
				}
			}

		} else {
			// Add your platform-specific check for file opened here
			return false;
		}
	}
}
