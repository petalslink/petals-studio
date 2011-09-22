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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.PetalsXsdAnnotations;

/**
 * Registers the name spaces found during the parsing of XSD files in a SU wizard.
 * Each new wizard instance should clear its list before register new name spaces.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsdNamespaceStore {

	/** The map of name spaces to print in the jbi.xml file (key=prefix, value=url). */
	private Map<String, String> namespaces = new HashMap<String, String> ();

	/** */
	private final static String[] forbiddenPrefixes = new String[] {
		"jbi", "interfaceNs", "serviceNs", "generatedNs", "xs", "xsi" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	};
	/** */
	private static int nsCpt = 0;


	/**
	 * Empty private constructor.
	 */
	private XsdNamespaceStore() {}


	/**
	 * Clears the registered namespaces.
	 */
	public void clear() {
		this.namespaces.clear();
	}


	/**
	 * Unique name space declarations.
	 * <p>
	 * Reserved prefixes: "jbi", "interfaceNs", "serviceNs", "generatedNs", "xs", "xsi".
	 * </p>
	 * <p>
	 * {@link PetalsXsdAnnotations#PETALS_ANNOTATION_NS} name space should not be stored.
	 * It is just used to annotate XSD elements.
	 * </p>
	 * 
	 * @param namespacePrefix
	 * @param namespaceUri
	 */
	public void store( String namespacePrefix, String namespaceUri ) {

		if( namespacePrefix == null
					|| namespaceUri == null
					|| namespaceUri.trim().length() == 0
					|| PetalsXsdAnnotations.PETALS_ANNOTATION_NS.equals( namespaceUri ))
			return;

		if( namespacePrefix.equals( "" )) //$NON-NLS-1$
			return;
		if( Arrays.asList( forbiddenPrefixes ).contains( namespacePrefix ))
			return;

		this.namespaces.put( namespacePrefix, namespaceUri );
	}


	/**
	 * Register a name space URI and generate a unique prefix.
	 * @param namespaceUri
	 * @return the generated namespace prefix or null if the namespace URI is null
	 */
	public String store( String namespaceUri ) {
		if( namespaceUri == null || namespaceUri.trim().length() == 0 )
			return null;

		String namespacePrefix = null;
		do {
			namespacePrefix = "gen" + nsCpt;
			nsCpt ++;
		} while( this.namespaces.containsKey( namespacePrefix ));

		this.namespaces.put( namespacePrefix, namespaceUri );
		return namespacePrefix;
	}


	/**
	 * @return the namespaces
	 */
	public Map<String, String> getNamespaces() {
		return Collections.unmodifiableMap( this.namespaces );
	}


	/**
	 * @param namespaces the namespaces to set
	 */
	public void setNamespaces( Map<String, String> namespaces ) {
		this.namespaces = namespaces;
	}


	/**
	 * @param namespaces the namespaces to add.
	 */
	public void addAll( Map<String, String> namespaces ) {
		if( namespaces == null )
			return;

		// Do not add all of them, ensure there are no duplicate and no forbidden.
		nsLoop : for( Map.Entry<String, String> namespace : namespaces.entrySet()) {
			if( namespace == null )
				continue;

			for( String forbiddenPrefix : forbiddenPrefixes ) {
				if( forbiddenPrefix.equals( namespace.getKey()))
					continue nsLoop;
			}

			this.namespaces.put( namespace.getKey(), namespace.getValue());
		}
	}


	/**
	 * 
	 */
	private static XsdNamespaceStore store = new XsdNamespaceStore ();

	/**
	 * @return the namespace store instance
	 */
	public static XsdNamespaceStore getNamespaceStore() {
		return store;
	}
}
