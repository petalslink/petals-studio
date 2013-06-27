package com.ebmwebsourcing.petals.services.filetransfer.generated;

public class ConsumesFiletransfer10 {

	/**
	 * Directory from which files are read.
	 * <p>
	 * Format like C:\\ or /home
	 * ${env} environment variables are supported.
	 * Type: String
	 * </p>
	 */
	public static final String READ_DIRECTORY = "read-directory";

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
	 * Transfer the file into Petals as an XML pay-load or as an attachment.
	 * <p>
	 * Type: Enumeration{ content ; attachment }
	 * </p>
	 */
	public static final String TRANSFER_MODE = "transfer-mode";

	/**
	 * Name pattern used to filter the files to read.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String FILE_PATTERN = "file-pattern";

	/**
	 * Polling period in milliseconds.
	 * <p>
	 * Type: long
	 * </p>
	 */
	public static final String POLLING_PERIOD = "polling-period";

	/**
	 * Enumeration values for the 'transfer-mode' property.
	 */
	public enum TransferMode {
		CONTENT("content"),
		ATTACHMENT("attachment");

		private String value;
		private TransferMode( String value ) {
			this.value = value;
		}
	
		public String toString() {
			return value;
		}
	
		public static TransferMode parse( String s ) {
			for( TransferMode val : values())
				if( val.toString().equalsIgnoreCase( s ))
					return val;
			
			return null;
		}
	}
}
