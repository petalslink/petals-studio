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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;

/**
 * Stores XSD data and nodes of a given XSD file.
 * <p>
 * For each node type, we have defined an associated bean.
 * We assume we will find the following elements in a XSD:
 * <ul>
 * 	<li>Type (SimpleType or ComplexType);</li>
 * 	<li>Element (Element which defines a real element or which references another element);</li>
 * 	<li>Restriction (a type Restriction);</li>
 * 	<li>Extension (a type Extension);</li>
 * 	<li>Enumeration (an Enumeration);</li>
 * 	<li>Attribute (an Attribute);</li>
 * 	<li>Annotation, with documentation and appinfo as children.</li>
 * </ul>
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsdFileRegistry {

	/** The target namespace of the XSD file. */
	private String targetNamespace;
	/**
	 * The list of namespaces defined in the XSD file.
	 * The key is the namespace prefix, the value is the namespace URL.
	 */
	private final Map<String, String> xmlns = new HashMap<String, String> ();
	/**
	 * The map which stores all the nodes found during the parsing.
	 * The key is the ID of the XSD item, the value is the XSD item.
	 */
	private final Map<String, XsdItem> registry = new HashMap<String, XsdItem> ();

	/** Shared integer used to identify restriction XSD items. */
	private static int restrictionsCpt = 0;
	/** Shared integer used to identify extension XSD items. */
	private static int extensionsCpt = 0;
	/** Shared integer used to identify enumeration XSD items. */
	private static int enumerationsCpt = 0;
	/** Shared integer used to identify referencer elements. */
	private static int elementsWithoutNameCpt = 0;
	/** Shared integer used to identify documentation XSD items. */
	private static int documentationsCpt = 0;
	/** Shared integer used to identify attribute XSD items. */
	private static int attributesCpt = 0;
	/** Shared integer used to identify appinfo XSD items. */
	private static int appinfoCpt = 0;



	/**
	 * Register an extension XSD item.
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param baseAttr the base type.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerExtension( String parentId, String baseAttr ) {

		// create the ID of the extension
		String extensionName = "extension " + extensionsCpt; //$NON-NLS-1$
		extensionsCpt++;

		// create the extension element and put it into the map
		XsdExtension extension = new XsdExtension( extensionName );
		extension.base = baseAttr;
		this.registry.put( extensionName, extension );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( extensionName );
		}
		return extensionName;
	}


	/**
	 * Register a restriction XSD item.
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param baseAttr the base type.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerRestriction( String parentId, String baseAttr ) {

		// create the ID of the restriction
		String restrictionName = "restriction " + restrictionsCpt; //$NON-NLS-1$
		restrictionsCpt++;

		// create the restriction element and put it into the map
		XsdRestriction restriction = new XsdRestriction( restrictionName );
		restriction.base = baseAttr;
		this.registry.put( restrictionName, restriction );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( restrictionName );
		}
		return restrictionName;
	}


	/**
	 * Register an enumeration value.
	 * @param valueAttr the value of this enumeration.
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerEnumerationValue( String parentId, String valueAttr ) {

		// create the ID of the enumeration
		String enumerationName = "enumeration " + enumerationsCpt; //$NON-NLS-1$
		enumerationsCpt++;

		// create the enumeration element and put it into the map
		XsdEnumeration enumeration = new XsdEnumeration( enumerationName );
		enumeration.value = valueAttr;
		this.registry.put( enumerationName, enumeration );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( enumerationName );
		}
		return enumerationName;
	}


	/**
	 * Register a documentation element.
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param docValue the documentation content.
	 * @param langAttr the language used for the documentation.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerDocumentationValue( String parentId, String docValue, String langAttr ) {

		// create the ID of the documentation
		String documentationName = "documentation " + documentationsCpt; //$NON-NLS-1$
		documentationsCpt++;

		// create the documentation element and put it into the map
		XsdDocumentation documentation = new XsdDocumentation( documentationName );
		documentation.documentationValue = docValue;
		documentation.lang = langAttr;
		this.registry.put( documentationName, documentation );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( documentationName );
		}
		return documentationName;
	}


	/**
	 * Register a documentation element.
	 * 
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param annotationName
	 * @param annotationValue
	 * 
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerAppinfoValue( String parentId, String annotationName, String annotationValue ) {

		// create the ID of the documentation
		String appinfoName = "appinfo " + appinfoCpt; //$NON-NLS-1$
		appinfoCpt++;

		// create the documentation element and put it into the map
		XsdAnnotation appinfo = new XsdAnnotation( appinfoName );
		appinfo.annotationName = annotationName;
		appinfo.annotationValue = annotationValue;
		this.registry.put( appinfoName, appinfo );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( appinfoName );
		}
		return appinfoName;
	}


	/**
	 * Register an XSD type item.
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param typeName the type name (e.g. named ComplexType).
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerType( String parentId, String typeName ) {

		// create the type element and put it into the map
		XsdType type = new XsdType( typeName );
		type.typeName = typeName;
		this.registry.put( typeName, type );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( typeName );
		}
		return typeName;
	}


	/**
	 * Register an XSD element item (complete or referencer).
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param nameAttr the element name.
	 * @param typeAttr the type name.
	 * @param refAttr the referenced element name.
	 * @param defaultValue the default value.
	 * @param minOccurs the minOccurs value.
	 * @param maxOccurs the maxOccurs value.
	 * @param nillable if the element is nillable.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerElement(
				String parentId, String nameAttr, String typeAttr, String refAttr,
				String defaultValue, String minOccurs, String maxOccurs, String nillable ) {

		// Create the ID: is it a complete element or a referencer element ?
		String elementId = null;
		if( nameAttr != null )
			elementId = nameAttr;
		else {
			elementId = "referencerElement " + elementsWithoutNameCpt; //$NON-NLS-1$
			elementsWithoutNameCpt++;
		}

		// create the element item and put it into the map
		XsdElement element = new XsdElement( elementId );
		element.name = nameAttr;
		element.type = typeAttr;
		element.ref = refAttr;
		element.defaultValue = defaultValue;
		element.minOccurs = minOccurs;
		element.maxOccurs = maxOccurs;
		element.nillable = nillable;

		this.registry.put( elementId, element );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( elementId );
		}
		return elementId;
	}


	/**
	 * Register an XSD attribute item (complete or referencer).
	 * @param parentId the ID of the parent in the XSD-as-XML tree.
	 * @param nameAttr the element name.
	 * @param typeAttr the type name.
	 * @param refAttr the referenced element name.
	 * @param defaultValue the default value.
	 * @param use the use value of the attribute.
	 * @return the ID of the created XsdItem for this node.
	 */
	public String registerAttribute(
				String parentId, String nameAttr, String typeAttr,
				String refAttr, String defaultValue, String use ) {

		// create the ID of the extension
		String attributeId = "attribute " + attributesCpt; //$NON-NLS-1$
		attributesCpt++;

		// create the attribute item and put it into the map
		XsdAttribute attribute = new XsdAttribute( attributeId );
		attribute.attributeName = nameAttr;
		attribute.type = typeAttr;
		attribute.ref = refAttr;
		attribute.use = use;
		attribute.defaultValue = defaultValue;

		this.registry.put( attributeId, attribute );

		// register it as a child of its parent
		if( parentId != null ) {
			XsdItem parent = this.registry.get( parentId );
			if( parent != null )
				parent.addChild( attributeId );
		}
		return attributeId;
	}


	/* *************************************
	 * 		Classes used to store data.
	 ***************************************/

	/**
	 * The basic bean which stores data about an XSD node.
	 */
	public abstract class XsdItem {
		/** The ID of the item. It must be unique. */
		private final String id;
		/** The list of children's ID (that's a tree element). */
		private final List<String> children = new ArrayList<String> ();


		/** @return the children the unmodifiable list of children. */
		public List<String> getChildren() { return Collections.unmodifiableList( this.children ); }

		/** @param id the ID of the item. */
		public XsdItem( String id ) { this.id = id; }

		/** @return the name */
		public String getId() { return this.id; }

		/** @param childName the child name. */
		public void addChild( String childName ) { this.children.add( childName ); }

		/**
		 * Print the children of an XSD item.
		 * @return the children as a readable text
		 */
		public String getChildrenAsString() {

			StringBuilder result = new StringBuilder(
						"\nXSD item '" + this.id + "' has " + this.children.size()  //$NON-NLS-1$ //$NON-NLS-2$
						+ " " + ((this.children.size() > 1) ? "children" : "child" ) + " registered." ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

			for( String childName : this.children )
				result.append( "\n--" + childName ); //$NON-NLS-1$

			return result.toString();
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public abstract String toString();

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals( Object object ) {
			return
			object != null
			&& this.getClass().equals( object.getClass())
			&& this.getId().equals(((XsdItem) object).getId());
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return this.id == null ? 37 : this.id.charAt( 0 ) * this.id.length() * 19;
		}
	}


	/**
	 * The bean which stores data about an XSD type node.
	 */
	public class XsdType extends XsdItem {
		/** The type name. */
		private String typeName;

		/** @param id the ID of the element. */
		public XsdType(String id) { super( id ); }

		/** @return the type name. */
		public String getTypeName() { return this.typeName; }

		@Override
		public String toString() {
			return "XSD item '" + getId() + "': Xsd Type whose name is '" + this.typeName + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}


	/**
	 * The bean which stores data about an XSD documentation node.
	 */
	public class XsdDocumentation extends XsdItem {
		/** The content of the documentation. */
		private String documentationValue;
		/** The language used for this documentation. */
		private String lang;

		/** @param id the ID of the element. */
		public XsdDocumentation(String id) { super( id ); }

		/** @return the documentationValue */
		public String getDocumentationValue() { return this.documentationValue; }

		/** @return the language. */
		public String getLang() {
			return this.lang;
		}

		@Override
		public String toString() {
			return "XSD item '" + getId() + "': Xsd Documentation whose content is '" + this.documentationValue + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}


	/**
	 * The bean which stores data about an XSD appinfo node.
	 */
	public class XsdAnnotation extends XsdItem {

		/** The content of the documentation. */
		private String annotationName, annotationValue;

		/** @param id the ID of the element. */
		public XsdAnnotation(String id) { super( id ); }


		/**
		 * @return the annotationName
		 */
		public String getAnnotationName() {
			return this.annotationName;
		}

		/**
		 * @return the annotationValue
		 */
		public String getAnnotationValue() {
			return this.annotationValue;
		}

		@Override
		public String toString() {
			return "XSD item '" + getId()
			+ "': Xsd annotation whose content is ("
			+ this.annotationName + "=" + this.annotationValue + ").";
		}
	}


	/**
	 * The bean which stores data about an XSD element node.
	 */
	public class XsdElement extends XsdItem {
		/** True if the element is nillable. */
		private String nillable;
		/** The element type. */
		private String type;
		/** The element name. */
		private String name;
		/** The referenced element name. */
		private String ref;
		/** The default value. */
		private String defaultValue;
		/** The minOccurs value. */
		private String minOccurs;
		/** The maxOccurs value. */
		private String maxOccurs;

		/** @param id the ID of the element. */
		public XsdElement( String id ) { super( id ); }

		/** @return the type */
		public String getType() { return this.type; }

		/** @return the name */
		public String getName() { return this.name; }

		/** @return the referenced element id. */
		public String getRef() { return this.ref; }

		/** @return the defaultValue */
		public String getDefaultValue() { return this.defaultValue; }

		/** @return the minOccurs */
		public String getMinOccurs() { return this.minOccurs; }

		/** @return the maxOccurs */
		public String getMaxOccurs() { return this.maxOccurs; }

		/** @return the nillable */
		public String getNillable() {
			return this.nillable;
		}

		@Override
		public String toString() {
			String result = "XSD item '" + getId() + "': Xsd Element with the following values:\n"; //$NON-NLS-1$ //$NON-NLS-2$
			result += "Name = " + this.name; //$NON-NLS-1$
			result += "\nType = " + this.type; //$NON-NLS-1$
			result += "\nReferenced Element id = " + this.ref; //$NON-NLS-1$
			result += "\nDefault Value = " + this.defaultValue; //$NON-NLS-1$
			result += "\nMin Occurs = " + this.minOccurs; //$NON-NLS-1$
			result += "\nMax Occurs = " + this.maxOccurs; //$NON-NLS-1$
			return result;
		}
	}


	/**
	 * The bean which stores data about an XSD restriction node.
	 */
	public class XsdRestriction extends XsdItem {
		/** The base type. */
		private String base;

		/** @param id the ID of the element. */
		public XsdRestriction(String id) { super( id ); }

		/** @return the base */
		public String getBase() { return this.base; }

		@Override
		public String toString() {
			return "XSD item '" + getId() + "': Xsd Restriction whose basis element id is '" + this.base + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}


	/**
	 * The bean which stores data about an XSD enumeration node.
	 */
	public class XsdEnumeration extends XsdItem {
		/** The enumeration value. */
		private String value;

		/** @param id the ID of the element. */
		public XsdEnumeration( String id ) { super( id ); }

		/** @return the value */
		public String getValue() { return this.value; }

		@Override
		public String toString() {
			return "XSD item '" + getId() + "': Xsd Enumeration Value '" + this.value + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}


	/**
	 * The bean which stores data about an XSD extension node.
	 */
	public class XsdExtension extends XsdItem {
		/** The base type. */
		private String base;

		/** @param id the ID of the element. */
		public XsdExtension(String id) { super( id ); }

		/** @return the base */
		public String getBase() { return this.base; }

		@Override
		public String toString() {
			return "XSD item '" + getId() + "': Xsd Extension whose basis element id is '" + this.base + "'."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}


	/**
	 * The bean which stores data about an XSD attribute node.
	 */
	public class XsdAttribute extends XsdItem {
		/** The default value. */
		private String defaultValue;
		/** The use attribute. */
		private String use;
		/** The attribute type. */
		private String type;
		/** The attribute name. */
		private String attributeName;
		/** The referenced attribute name. */
		private String ref;

		/** @param id the ID of the element. */
		public XsdAttribute( String id ) { super( id ); }

		/** @return the attributeName */
		public String getAttributeName() { return this.attributeName; }

		/** @return the defaultValue */
		public String getDefaultValue() { return this.defaultValue; }

		/** @return the use */
		public String getUse() { return this.use; }

		/** @return the type */
		public String getType() { return this.type; }

		/** @return the referenced element id. */
		public String getRef() { return this.ref; }

		@Override
		public String toString() {
			String result = "XSD item '" + getId() + "': Xsd Element with the following values:\n"; //$NON-NLS-1$ //$NON-NLS-2$
			result += "Attribute Name = " + this.attributeName; //$NON-NLS-1$
			result += "\nAttribute Type = " + this.type; //$NON-NLS-1$
			result += "\nReferenced Element id = " + this.ref; //$NON-NLS-1$
			result += "\nDefault Value = " + this.defaultValue; //$NON-NLS-1$
			result += "\nUse = " + this.use; //$NON-NLS-1$
			return result;
		}
	}


	/**
	 * Print the content of this registry.
	 */
	public void printRegistry() {
		String separator = "-----------------------------------------------"; //$NON-NLS-1$
		System.out.println( "\n" + separator + "\n" + separator + "" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		System.out.println( "Registry Content for ns=\"" + this.targetNamespace	//$NON-NLS-1$
					+ "\" (contains " //$NON-NLS-1$
					+ ((this.registry != null) ? this.registry.size() : 0)
					+ " elements).\n"  //$NON-NLS-1$
					+ separator + "\n" + separator + "\n" ); //$NON-NLS-1$ //$NON-NLS-2$

		// Display registered name spaces
		System.out.println( "Registered Namespaces (" + this.xmlns.size() + ")." ); //$NON-NLS-1$ //$NON-NLS-2$
		for( Map.Entry<String, String> entry : this.xmlns.entrySet()) {
			System.out.println( "xmlns:" + entry.getKey() + "=\"" + entry.getValue() + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		System.out.println( "\n" + separator + "\n" ); //$NON-NLS-1$ //$NON-NLS-2$

		// Read the Map.
		if( this.registry == null )
			System.out.println( "Empty" );			 //$NON-NLS-1$
		else {
			StringBuilder result = new StringBuilder();
			for( Map.Entry<String, XsdItem> entry : this.registry.entrySet()) {
				result.append( entry.getValue().toString() + "\n" ); //$NON-NLS-1$
				result.append( entry.getValue().getChildrenAsString() + "\n" + separator + "\n" ); //$NON-NLS-1$ //$NON-NLS-2$
			}
			System.out.println( result.toString());
		}
	}


	/*  ******************
	 * 		Name Spaces
	 ******************* */

	/**
	 * @return the target namespace of the associated XSD file.
	 */
	public String getTargetNamespace() {
		return this.targetNamespace;
	}

	/**
	 * @param targetNamespace
	 */
	public void setTargetNamespace( String targetNamespace ) {
		this.targetNamespace = targetNamespace;
	}

	/**
	 * Register a namespace for the associated XSD file.
	 * @param namespaceId the namespace prefix.
	 * @param namespaceUrl the namespace URL.
	 * @param namespaceStore the namespace store
	 */
	public void registerXmlns( String namespaceId, String namespaceUrl, XsdNamespaceStore namespaceStore ) {
		this.xmlns.put( namespaceId, namespaceUrl );
		namespaceStore.store( namespaceId, namespaceUrl );
	}

	/**
	 * Find the XSD item whose ID is the argument.
	 * @param element the element ID to look for.
	 * @return the XSD item associated with this ID, or null if it was not found.
	 */
	public XsdItem getXsdItemById( String element ) {
		return this.registry.get( element );
	}

	/**
	 * Find the namespace associated with this key.
	 * @param key the namespace prefix.
	 * @return the namespace URL associated with this key, or null if it was not found.
	 */
	public String getNsUrlByPrefix( String key ) {
		return this.xmlns.get( key );
	}
}
