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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.ErrorReporter;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.PetalsXsdAnnotations;

/**
 * A simple XSD locator and parser which parse XSD's as XML files.
 * <p>
 * This class defines the XSD keywords which are relevant for the generation.
 * </p>
 * 
 * <p>
 * To be more precise about what this class does, here are the exact actions it undertakes:
 * </p>
 * <ul>
 * <li>Given an eclipse plug-in ID and a relative folder path, it locates XSD files to parse.</li>
 * </li>Then, for every found XSD file, it parse it.</li>
 * <li>For every parsed file, this class creates an XsdFileRegistry, which contains information about the XSD.</li>
 * <li>Any node in the XSD results in an object registration in the XsdFileRegistry.</li>
 * </ul>
 * 
 * <p>
 * The result of the parsing is a collection of XsdFileRegistry's,
 * each one of them containing a tree (I said a tree, not a graph - more than XSD, that's XML we parse).
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsdParser {

	/* For Test only. */
	public XsdGlobalRegistry parse( String folderPath, List<String> xsdFiles  ) {
		return parseXSDs( folderPath, xsdFiles );
	}
	/* END of test method. */


	/** */
	private XsdNamespaceStore namespaceStore;



	/**
	 * Parse XSD files contained in the folder "folderPath" relatively to the root of the plugin "pluginId".
	 * What we must take care about here, is the fact that the plugin is in the "plugins" folder of Eclipse,
	 * and not in the workspace. Therefore, the plugin might be in a jar file and the way we access XSD files
	 * must be adapted in consequence.
	 * 
	 * @param folderPath the plug-in relative folder path which contains the XSDs.
	 * @param pluginId the ID of the plug-in which contains the XSD files to parse.
	 * @param namespaceStore
	 * @return the XsdGlobalRegistry containing
	 */
	public XsdGlobalRegistry parse( String folderPath, String pluginId, XsdNamespaceStore namespaceStore ) {

		this.namespaceStore = namespaceStore;
		List<String> xsdFiles = new ArrayList<String> ();
		String pluginRootFolderPath = getPluginRootFolderPath( pluginId );

		// Now, find the XSD files in this root folder.
		Enumeration<?> enume =
			Platform.getBundle( pluginId ).getEntryPaths( folderPath );

		// The file paths are relative to the plugin root path.
		if( enume != null ) {
			while( enume.hasMoreElements()) {
				Object o = enume.nextElement();
				if( o instanceof String
							&& ((String) o).endsWith( ".xsd" )) { //$NON-NLS-1$
					xsdFiles.add((String) o );
				}
			}
		}

		// Eventually, parse all the XSD files.
		return parseXSDs( pluginRootFolderPath, xsdFiles );
	}


	/**
	 * Get the root folder of the plugin "pluginId".
	 * What we must take care about here, is the fact that the plugin is in the "plugins" folder of Eclipse,
	 * and not in the workspace. Therefore, the plugin might be in a jar file and the way we access XSD files
	 * must be adapted in consequence.
	 * 
	 * @param pluginId the ID of the plugin.
	 * @return the root folder URL of the plugin, returned as an string.
	 */
	private String getPluginRootFolderPath( String pluginId ) {
		// Find the root folder in the bundle.
		URL url = Platform.getBundle( pluginId ).getEntry( "/" ); //$NON-NLS-1$
		try {
			IPath path = new Path( FileLocator.toFileURL( url ).getPath());
			String rootFolderPath = path.makeAbsolute().toString();
			return rootFolderPath;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ""; //$NON-NLS-1$
	}


	/* ****************************************
	 * 		PARSING ALGORITHMS
	 ******************************************/

	/**
	 * This method builds a collection of trees whose nodes are references to elements of the XSDs.
	 * Each XSD file has its own tree, the tree being an XsdFileRegistry object, which also stores namespaces.
	 * Each XsdFileRegistry is registered in a global registry, an XsdGlobalRegistry object. In this global registry,
	 * an XsdFileRegistry is identified by its targetNamespace.
	 * 
	 * Consequently, the XSD's you parse with this class should have different target namespaces and each
	 * namespace prefix should point to the URI. If you do not respect these conditions, the result is undetermined,
	 * i.e. it might work as well as it could fail.
	 * 
	 * @param xsdFiles the list of XSD file relative paths (relative to the root of the plugin).
	 * @param rootFolderPath the plugin root path.
	 * @return a collection of XsdFileRegistry, one per XSD file.
	 * They will be used later on to build something unified and coherent for the HCI generation.
	 */
	private XsdGlobalRegistry parseXSDs( String rootFolderPath, List<String> xsdFiles ) {
		SAXBuilder sxb = new SAXBuilder();
		XsdGlobalRegistry superRegistry = new XsdGlobalRegistry();

		if( !rootFolderPath.endsWith( "/" )) //$NON-NLS-1$
			rootFolderPath += "/"; //$NON-NLS-1$

		/* Each file is parsed to fill in a structure (registry here).
		 * And each time we create and fill a such structure, we register it into a
		 * higher-level registry. This "super" registry will be in charge of resolving
		 * namespaces and dependencies between XSD files.
		 */
		for( String filename : xsdFiles ) {
			File file = new File( rootFolderPath + filename );
			if( !file.exists())
				continue;

			XsdFileRegistry registry = new XsdFileRegistry();
			parseXsd( file, sxb, registry );
			superRegistry.registerXsdFileRegistry( registry );
		}
		return superRegistry;
	}


	/**
	 * Parse a single XSD file.
	 * @param file the XSD file to parse.
	 * @param saxBuilder the SAX builder.
	 * @param registry the registry for this file (container for the XML tree).
	 */
	private void parseXsd( File file, SAXBuilder saxBuilder, XsdFileRegistry registry ) {
		try {
			Document document = saxBuilder.build( file );
			if( document.hasRootElement()) {
				Element rootElement = document.getRootElement();

				// Now, parse the root element recursively.
				parseNodeElement( rootElement, null, registry, file.getName());
			}

		} catch (JDOMException e) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );

		} catch (IOException e) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Parse an XSD-as-XML node element.
	 * @param element the JDom element to parse.
	 * @param parentKey the registry key of the parent element.
	 * @param registry the registry which stores information about the nodes of this file.
	 * @param fileName the XSD file name (used to log exceptions).
	 */
	private void parseNodeElement( Element element, String parentKey, XsdFileRegistry registry, String fileName ) {
		if( element == null )
			return;

		String name = element.getName();
		String key = null;	// The key which identifies an element in the registry.

		// case Schema (register name spaces)
		if( name.equalsIgnoreCase( "schema" )) { //$NON-NLS-1$
			// target namespace
			List<?> attributes = element.getAttributes();
			for( Object o : attributes ) {
				if( o instanceof Attribute ) {
					Attribute attribute = (Attribute) o;
					if( attribute.getName().equalsIgnoreCase( "targetNamespace" )) { //$NON-NLS-1$
						String attributeValue = attribute.getValue();
						if( attributeValue != null )
							registry.setTargetNamespace( attributeValue );
						else {
							String errorMsg = NLS.bind( Messages.XsdParser_7, fileName );
							ErrorReporter.INSTANCE.registerError( "XSD-parsing", errorMsg, IStatus.ERROR, null );
							return; // Undetermined behavior in this case.
						}
					}
				}
			}

			// get name spaces
			List<?> namespaces = element.getAdditionalNamespaces();
			for( Object o : namespaces ) {
				if( o instanceof Namespace ) {
					Namespace ns = (Namespace) o;
					String nsPrefix = ns.getPrefix();
					String nsUri = ns.getURI();
					if( nsPrefix != null && nsUri != null ) {
						registry.registerXmlns( nsPrefix, nsUri, this.namespaceStore );
					}
				}
			}
		}

		// case Simple or ComplexType
		else if( name.equalsIgnoreCase( "complexType" ) //$NON-NLS-1$
					|| name.equalsIgnoreCase( "simpleType" )) { //$NON-NLS-1$
			String nameAttr = element.getAttributeValue( "name" ); //$NON-NLS-1$
			if( nameAttr != null ) {
				key = registry.registerType( parentKey, nameAttr );
			}
		}

		// case Element
		else if( name.equalsIgnoreCase( "element" )) { //$NON-NLS-1$
			String nameAttr = element.getAttributeValue( "name" ); //$NON-NLS-1$
			String typeAttr = element.getAttributeValue( "type" ); //$NON-NLS-1$
			String refAttr = element.getAttributeValue( "ref" );			 //$NON-NLS-1$
			String defaultValue = element.getAttributeValue( "default" ); //$NON-NLS-1$
			String maxOccurs = element.getAttributeValue( "maxOccurs" ); //$NON-NLS-1$
			String minOccurs = element.getAttributeValue( "minOccurs" ); //$NON-NLS-1$
			String nillable = element.getAttributeValue( "nillable" ); //$NON-NLS-1$

			if(nameAttr != null && typeAttr != null || refAttr != null )
				key = registry.registerElement(
							parentKey, nameAttr, typeAttr, refAttr, defaultValue,
							minOccurs, maxOccurs, nillable );
		}

		// case Attribute
		else if( name.equalsIgnoreCase( "attribute" )) { //$NON-NLS-1$
			String nameAttr = element.getAttributeValue( "name" ); //$NON-NLS-1$
			String typeAttr = element.getAttributeValue( "type" ); //$NON-NLS-1$
			String refAttr = element.getAttributeValue( "ref" ); //$NON-NLS-1$
			String use = element.getAttributeValue( "use" ); //$NON-NLS-1$
			String defaultValue = element.getAttributeValue( "default" ); //$NON-NLS-1$

			if( nameAttr != null && typeAttr != null )
				key = registry.registerAttribute( parentKey, nameAttr, typeAttr, refAttr, defaultValue, use );
		}

		// case Extension
		else if( name.equalsIgnoreCase( "extension" )) { //$NON-NLS-1$
			String baseAttr = element.getAttributeValue( "base" ); //$NON-NLS-1$
			if( baseAttr != null ) {
				key = registry.registerExtension( parentKey, baseAttr );
			}
		}

		// case Restriction
		else if( name.equalsIgnoreCase( "restriction" )) { //$NON-NLS-1$
			String baseAttr = element.getAttributeValue( "base" ); //$NON-NLS-1$
			key = registry.registerRestriction( parentKey, baseAttr );
		}

		// case Enumeration
		else if( name.equalsIgnoreCase( "enumeration" )) { //$NON-NLS-1$
			String valueAttr = element.getAttributeValue( "value" ); //$NON-NLS-1$
			if( valueAttr != null ) {
				key = registry.registerEnumerationValue( parentKey, valueAttr );
			}
		}

		// case Documentation
		else if( name.equalsIgnoreCase( "documentation" )) { //$NON-NLS-1$
			String docValue = element.getValue().trim();
			docValue = docValue.replaceAll( "\\s{2,}", "\n" );
			String langAttr =
				element.getAttributeValue( "lang", Namespace.XML_NAMESPACE ); //$NON-NLS-1$

			if( docValue != null ) {
				if( "NO HCI".equalsIgnoreCase( docValue ))	// Still be able to work with the previous "annotation system"
					key = registry.registerAppinfoValue( parentKey, PetalsXsdAnnotations.NO_HCI, "" );
				else
					key = registry.registerDocumentationValue( parentKey, docValue, langAttr );
			}
		}

		// case AppInfo
		else if( name.equalsIgnoreCase( "appinfo" ) && element.getChildren() != null ) { //$NON-NLS-1$
			for( Object childElement : element.getChildren()) {

				if( childElement instanceof Element ) {
					String annotationName = ((Element) childElement).getName();
					String annotationValue = ((Element) childElement).getValue();
					if( annotationName != null )
						key = registry.registerAppinfoValue( parentKey, annotationName, annotationValue );
				}
			}

			return;
		}

		// otherwise, do nothing
		else {
			key = parentKey;
		}

		// parse children nodes
		if( element.getChildren() == null )
			return;
		for( Object childElement : element.getChildren()) {
			if( childElement instanceof Element )
				parseNodeElement((Element) childElement, key, registry, fileName );
		}
	}
}
