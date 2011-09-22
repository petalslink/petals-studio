/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.runtime.QualifiedName;

/**
 * The constants used here and there in this plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuMainConstants {


	public static final String CDK_PLUGIN_ID = "com.ebmwebsourcing.petals.services.cdk";



	/*
	 * Wizard Page Names.
	 */

	/** The name of "JBI-generic" page. */
	public final static String PAGE_GENERAL_JBI_DATA = "JbiPage"; //$NON-NLS-1$

	/** The name of "component-specific" page. */
	public final static String PAGE_SPECIFIC_JBI_DATA = "SpecificPage"; //$NON-NLS-1$

	/** The name of "CDK-specific" page. */
	public final static String PAGE_CDK_SPECIFIC_DATA = "CdkPage"; //$NON-NLS-1$

	/*
	 * Default paths.
	 */

	/** The "component plug-in" relative path of the "XSDs" folder. */
	public static final String COMPONENT_XSD_LOCATION = "resources/xsd/"; //$NON-NLS-1$

	/** The "component plug-in" relative path of the "icons" folder. */
	public static final String COMPONENT_WIZ_ICONS_LOCATION = "icons/wizban/"; //$NON-NLS-1$

	/**
	 * Serialization is used in this plug-in to cache HCI generation and avoid
	 * parsing XSD files each time we run a wizard. However, due to the fact this
	 * plug-in might evolve and change its serialization system, we have to associate
	 * the cache project with a number identifying the serialization system.
	 * 
	 * This attribute is the serialization system version ID.
	 */
	public final static String CACHE_SERIAL_SYSTEM_VERSION = "1.1.6.qualifier"; //$NON-NLS-1$

	/** The property associated with any cache folder. */
	public static final QualifiedName CACHE_VERSION_AS_QUALIFIED_NAME =
		new QualifiedName( "http://www.ebmwebsourcing.com", "CACHE_VERSION" ); //$NON-NLS-1$ //$NON-NLS-2$

	/** The name of the project containing the cache for the HCI generation. */
	public final static String PETALS_SU_CACHE_FOLDER_NAME = ".PEtALS_su_cache"; //$NON-NLS-1$
}
