package com.ebmwebsourcing.petals.services.filetransfer.generated;

public class ConsumesFiletransfer20 {

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

	/**
	 * Polling period in milliseconds.
	 * <p>
	 * Type: long
	 * </p>
	 */
	public static final String POLLING_PERIOD = "polling-period";

	/**
	 * The name pattern for files being polled by Petals.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String FILENAME = "filename";

	/**
	 * Transfer the file into Petals as an XML pay-load or as an attachment.
	 * <p>
	 * Type: Enumeration{ content ; attachment }
	 * </p>
	 */
	public static final String TRANSFER_MODE = "transfer-mode";

	/**
	 * A message skeleton that will be populated at runtime and sent to the consumed services.
	 * <p>
	 * It may contain <b>$content</b> or <b>$attachment</b>, or none of them.
	 * This is the way the component should link the file with the returned message.
	 * </p>
	 * <p>
	 * <b>$content</b> will be replaced by the file content.
	 * <b>$attachment</b> will set the file in attachment. It will be replaced by
	 * a xop:include element which references the attachment (MTOM-like).
	 * Type: String
	 * </p>
	 */
	public static final String BASE_MESSAGE = "base-message";

	/**
	 * The maximum number of processor threads created by the poller to process files.
	 * <p>
	 * Type: integer
	 * </p>
	 */
	public static final String PROCESSOR_POOL_SIZE = "processor-pool-size";

	/**
	 * Define eviction time in milliseconds for processor threads created by the poller.
	 * <p>
	 * Type: long
	 * </p>
	 */
	public static final String PROCESSOR_POOL_TIMEOUT = "processor-pool-timeout";

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
