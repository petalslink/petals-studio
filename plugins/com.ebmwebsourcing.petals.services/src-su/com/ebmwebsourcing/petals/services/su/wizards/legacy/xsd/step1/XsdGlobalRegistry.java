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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1;

import java.util.HashMap;
import java.util.Map;

import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdItem;

/**
 * The global registry contains references to all the XSD files which were parsed by an instance of XsdParser.
 * <p>
 * The references do not point on XSD files but on their XsdFileRegistry, the object containing required data
 * about these XSD files.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsdGlobalRegistry {

	/**
	 * The Map which contains the XsdFileRegistry's.
	 * <p>
	 * An XsdFileRegistry contains information about a given XSD file.
	 * The key is the targetNamespace of the XSD file.
	 * </p>
	 */
	private final Map<String, XsdFileRegistry> internalRegistry = new HashMap<String, XsdFileRegistry> ();



	/**
	 * Register an "XSD file" in the global registry.
	 * <p>
	 * Null argument won't be registered.
	 * If this registry was already registered, it is registered again and erases the previously-registered one.
	 * </p>
	 * 
	 * @param fileRegistry the XsdFileRegistry of the XSD file to register.
	 */
	public void registerXsdFileRegistry( XsdFileRegistry fileRegistry ) {
		if( fileRegistry == null )
			return;
		String key = fileRegistry.getTargetNamespace();
		this.internalRegistry.put( key, fileRegistry );
	}


	/**
	 * Print the content of all the registries.
	 */
	public void printContent() {
		for( XsdFileRegistry registry : this.internalRegistry.values())
			registry.printRegistry();
	}


	/**
	 * Look for a given element in the registered XSD.
	 * The search takes in account the namespace.
	 * @param name the name to look for (might include a namespace).
	 * @return the Xsd Item associated with this name, or null if it was not found.
	 */
	public XsdItem getElementWithNs( String name ) {
		// No null element.
		if( name == null )
			return null;

		// Try to find it without using the namespace.
		for( XsdFileRegistry registry : this.internalRegistry.values()) {
			XsdItem elt = registry.getXsdItemById( name );
			if( elt != null )
				return elt;
		}

		// Try to find it using the namespace.
		String[] parsed = name.split( ":" ); //$NON-NLS-1$
		if( parsed.length == 2 ) {
			// check the namespaces of the registries
			for( XsdFileRegistry registry : this.internalRegistry.values()) {
				String namespaceUri = registry.getNsUrlByPrefix( parsed[ 0 ]);
				if( namespaceUri == null )
					continue;

				XsdFileRegistry rightRegistry = this.internalRegistry.get( namespaceUri );
				if( rightRegistry == null )
					return null;

				XsdItem elt = rightRegistry.getXsdItemById( parsed[ 1 ]);
				return elt;
			}
		}
		return null;
	}
}
