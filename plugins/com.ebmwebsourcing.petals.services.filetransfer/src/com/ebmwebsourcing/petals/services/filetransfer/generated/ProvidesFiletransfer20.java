package com.ebmwebsourcing.petals.services.filetransfer.generated;

public class ProvidesFiletransfer20 {

	/**
	 * Name of the file written by Petals (suffixed with the date time).
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String FILENAME = "filename";

	/**
	 * Directory where read files are moved.
	 * <p>
	 * Format like C:\\ or /home
	 * ${env} environment variables are supported.
	 * Type: String
	 * </p>
	 */
	public static final String BACKUP_DIRECTORY = "backup-directory";

	/**
	 * Working directory where files are written or read.
	 * <p>
	 * Format like C:\\ or /home
	 * ${env} environment variables are supported.
	 * Type: String, required
	 * </p>
	 */
	public static final String FOLDER = "folder";
}
