package com.ebmwebsourcing.petals.tests.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import org.eclipse.core.runtime.Platform;

public class FileTestUtil {
	
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
			try {
				InputStream commandOutput = Runtime.getRuntime().exec("/usr/bin/lsof").getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(commandOutput));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.contains(f.getAbsolutePath())) {
						return true;
					}
				}
				return false;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		} else {
			// Add your platform-specific check for file opened here
			return false;
		}
	}
}
