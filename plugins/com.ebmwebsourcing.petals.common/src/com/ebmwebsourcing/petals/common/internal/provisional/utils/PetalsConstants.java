/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * The constants used here and there in this plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class PetalsConstants {

	/**
	 * Private constructor for utility class.
	 */
	private PetalsConstants() {
		// nothing
	}

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
	 * {@link #LOC_JAVA_RES_FOLDER} + "/jbi/jbi.xml"
	 * <p>
	 * Added in Petals 4 for components and shared libraries.
	 * </p>
	 */
	public static final String NEW_LOC_JBI_FILE = LOC_JAVA_RES_FOLDER + "/jbi/jbi.xml";

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
