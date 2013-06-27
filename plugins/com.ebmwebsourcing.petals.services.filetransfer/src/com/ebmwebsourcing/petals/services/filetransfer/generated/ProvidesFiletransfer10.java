package com.ebmwebsourcing.petals.services.filetransfer.generated;

public class ProvidesFiletransfer10 {

	/**
	 * Directory where files are written.
	 * <p>
	 * Format like C:\\ or /home
	 * ${env} environment variables are supported.
	 * Type: String
	 * </p>
	 */
	public static final String WRITE_DIRECTORY = "write-directory";

	/**
	 * Select the elements from the received Petals message to write.
	 * <p>
	 * Type: Enumeration{ content-only ; attachments-only ; content-and-attachments }
	 * </p>
	 */
	public static final String COPY_MODE = "copy-mode";

	/**
	 * Name of the file written by Petals (suffixed with the date time).
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String FILE_PATTERN = "file-pattern";

	/**
	 * Directory from which files are read (GetFiles contract).
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
	 * Enumeration values for the 'copy-mode' property.
	 */
	public enum CopyMode {
		ATTACHMENTS_ONLY("attachments-only"),
		CONTENT_AND_ATTACHMENTS("content-and-attachments"),
		CONTENT_ONLY("content-only");

		private String value;
		private CopyMode( String value ) {
			this.value = value;
		}
	
		public String toString() {
			return value;
		}
	
		public static CopyMode parse( String s ) {
			for( CopyMode val : values())
				if( val.toString().equalsIgnoreCase( s ))
					return val;
			
			return null;
		}
	}
}
