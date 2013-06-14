/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.cdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CdkXsdManager {

	/** The unique instance of this class. */
	private static CdkXsdManager instance = new CdkXsdManager();

	/**
	 * The location of the basis folder containing XSD files of the CDK.
	 * The location is relative to the work space root.
	 * Current value is "resources/xsdCdk/".
	 */
	public final static String xsdLocation = "resources/xsd/";

	/**
	 * The map which associates a name space URL to a folder name in this plug-in.
	 * The URL is the one of the CDK. It can be found in CDKjbi.xsd with the prefix petalsCDK.
	 * The folder is the leaf folder containing the XSD files.
	 */
	private final Map<String, String> mapping = new HashMap<String, String> ();


	/** Private constructor (singleton pattern). */
	private CdkXsdManager() {
		this.mapping.put( "http://petals.ow2.org/components/extensions/version-4.0", "4.0" );
		this.mapping.put( "http://petals.ow2.org/components/extensions/version-5", "5.0" );
	}

	/**
	 * @return the unique instance of this class.
	 */
	public static CdkXsdManager getInstance() {
		return instance;
	}

	/**
	 * @param namespaceUrl
	 * @return the associated folder as registered in <i>mapping</i>, or null if it was not found.
	 */
	public String resolveCdkNamespace( String namespaceUrl ) {
		String folderName = this.mapping.get( namespaceUrl );
		if( folderName == null )
			return null;

		return xsdLocation + folderName;
	}

	/**
	 * Check all the URLS.
	 * When one has a folder associated registered in <i>mapping</i>, return this one (the first one).
	 * @param namespaceUrls
	 * @return the first folder that matches, or null if it was not found.
	 */
	public String resolveCdkNamespace( Collection<String> namespaceUrls ) {

		for( String namespaceUrl : namespaceUrls ) {
			String folderName = this.mapping.get( namespaceUrl );
			if( folderName != null )
				return xsdLocation + folderName;
		}

		return null;
	}

	/**
	 * @param version a CDK version
	 * @return the name space URI associated with this version
	 */
	public String resolveCdkVersion( String version ) {

		String result = null;
		for( Map.Entry<String,String> entry : this.mapping.entrySet()) {
			if( version.equals( entry.getValue())) {
				result = entry.getKey();
				break;
			}
		}

		return result;
	}
}
