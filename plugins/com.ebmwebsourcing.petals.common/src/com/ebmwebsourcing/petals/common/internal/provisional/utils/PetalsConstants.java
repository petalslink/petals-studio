/****************************************************************************
 *
 * Copyright (c) 2009-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * The constants used here and there in this plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsConstants {

	/**
	 * src/main/jbi/
	 */
	public static final String LOC_RES_FOLDER = "src/main/jbi";

	/**
	 * pom.xml
	 */
	public static final String LOC_POM_FILE = "pom.xml";

	/**
	 * {@link #LOC_RES_FOLDER} + "/jbi.xml"
	 */
	public static final String LOC_JBI_FILE = LOC_RES_FOLDER + "/jbi.xml";

	/**
	 * src/main/java
	 */
	public static final String LOC_SRC_FOLDER = "src/main/java";

	/**
	 * src/main/resources
	 */
	public static final String LOC_JAVA_RES_FOLDER = "src/main/resources";

	/**
	 * bin
	 */
	public static final String LOC_BIN_FOLDER = "bin";

	/**
	 * The marker ID for jbi.xml files.
	 */
	public static final String MARKER_ID_JBI_XML = "com.ebmwebsourcing.petals.common.markers";

	/**
	 * The "XPath Location" attribute for Petals error markers.
	 */
	public static final String MARKER_XPATH_LOCATION_ATTRIBUTE = "com.ebmwebsourcing.petals.common.xpath.attribute";

	/**
	 * The value for dynamic Petals "endpoint-name"s.
	 */
	public static final String AUTO_GENERATE = "autogenerate";

	/**
	 * The view ID for the Petals project explorer.
	 */
	public static final String PETALS_PROJECT_EXPLORER_VIEW_ID = "com.ebmwebsourcing.petals.common.projects";

	/**
	 * The JBI name space URI.
	 */
	public static final String JBI_NS = "http://java.sun.com/xml/ns/jbi";

	/**
	 * The default group ID for Maven projects.
	 */
	public static final String DEFAULT_GROUP_ID = "org.ow2.petals";

	/**
	 * The default version of the Petals Maven plug-in.
	 */
	public static final String DEFAULT_PETALS_MAVEN_PLUGIN = "2.1.2";

	/**
	 * The default version for Maven projects.
	 */
	public static final String DEFAULT_ARTIFACT_VERSION = "1.0-SNAPSHOT";

	/**
	 * The ID of the Petals common plug-in (useful to build image descriptors).
	 */
	public static final String PETALS_COMMON_PLUGIN_ID = PetalsCommonPlugin.PLUGIN_ID;
}
