package com.ebmwebsourcing.petals.common.internal.provisional.utils;


public class NameUtils {

	/**
	 * Create a SU name from a service name.
	 * This method does not add, check or modify file extensions.
	 *
	 * @param suType the component used by the SU (e.g. FTP, XSLT...).
	 * @param serviceName the service name.
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>.
	 * @return the generated SU name.
	 */
	public static String createSuName( String suType, String serviceName, boolean isConsume ) {
		String suMode = ( isConsume ) ? "consume" : "provide";
		return "su-" + suType + "-" + serviceName + "-" + suMode;
	}

	/**
	 * Create a SA name from a service name.
	 * This method does not add, check or modify file extensions.
	 *
	 * @param suType the component used by the SU (e.g. FTP, XSLT...).
	 * @param serviceName the service name.
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>.
	 * @return the generated SU name.
	 */
	public static String createSaName( String suType, String serviceName, boolean isConsume ) {
		String suMode = ( isConsume ) ? "consume" : "provide";
		return "sa-" + suType + "-" + serviceName + "-" + suMode;
	}

	/**
	 * Create a SA name from a SU name.
	 * This method does not add, check or modify file extensions.
	 *
	 * @param suName a service unit archive name.
	 * @return the generated SU name.
	 */
	public static String createSaName( String suName ) {
		if( suName == null )
			return "";

		if( suName.startsWith( "su-" ))
			return suName.replaceFirst( "su-", "sa-" );

		return "sa-" + suName;
	}
}
