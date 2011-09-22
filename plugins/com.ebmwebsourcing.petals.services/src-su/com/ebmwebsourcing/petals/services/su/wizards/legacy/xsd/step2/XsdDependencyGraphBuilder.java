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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.DependencyAnnotation;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdGlobalRegistry;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdAnnotation;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdAttribute;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdDocumentation;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdElement;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdEnumeration;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdExtension;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdRestriction;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdFileRegistry.XsdType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step3.HciItemListBuilder;

/**
 * Build a list of abstract HCI elements (known as HciItem).
 * <p>
 * This list might be projected on any Java graphical library, e.g. SWT, Swing...
 * </p>
 * <p>
 * If there are losses or translation issues from the XSD to the HCI,
 * then the issue is probably here since we filter most of the parsed elements here.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 * 
 * TODO: this class is a mess. Refactor it all.
 */
public class XsdDependencyGraphBuilder {

	public static final BaseType INTEGER_BASE_TYPE = new BaseType();
	public static final BaseType DOUBLE_BASE_TYPE = new BaseType();
	public static final BaseType STRING_BASE_TYPE = new BaseType();
	public static final BaseType URI_BASE_TYPE = new BaseType();
	public static final BaseType BOOLEAN_BASE_TYPE = new BaseType();
	public static final BaseType QNAME_BASE_TYPE = new BaseType();
	public static final BaseType FILE_BASE_TYPE = new BaseType();
	public static final BaseType UNRESOLVED_BASE_TYPE = new BaseType();



	/**
	 * Build the list of abstract HCI elements.
	 * @param rootTypeId name of an XSD <b>Type</b>.
	 * @param registry the XsdGlobalRegistry containing data about parsed XSD files.
	 * @return a list of abstract HCI elements to display on screen, or null if an error occurred.
	 */
	public List<HciItem> buildTree( String rootTypeId, XsdGlobalRegistry registry ) {

		// Any argument null means it is an error.
		if( rootTypeId == null || registry == null ) {
			return null;
		}

		// Get the root element. If null, it is an error.
		XsdItem rootElt = registry.getElementWithNs( rootTypeId );
		if( rootElt == null || !( rootElt instanceof XsdType ))
			return null;

		/*	STEPS
		 * -------
		 * -------
		 * 
		 * 
		 * There are two steps before we got abstract HCI items.
		 * First, remember that we have a collection of trees in the registry.
		 * Each tree contains the XSD-as-XML nodes hierarchy.
		 * 
		 * =====
		 * 
		 * The first step consists in building a graph of dependency and in skipping useless XSD items.
		 * Skipped elements are documentation and enumerations. They are merged in other items.
		 * About the graph:
		 * 
		 * ** the graph root is the root element whose name was given in argument
		 * ** the vertices are the elements contained in the registry and in relation (directly or indirectly) with the root element
		 * ** as said before, these vertices exclude some XSD items
		 * ** a vertex is in relation with another one if it is one of its children OR if it is its type
		 * ** that's why we had a tree and we know have a graph: types are injected in the dependencies.
		 * 
		 * The vertices are custom-types defined in this class.
		 * Also, this graph defines the allowed dependencies between vertices types.
		 * Example : a restriction can only have dependencies with enumerations.
		 * Example : an attribute is mandatory a child of an extension.
		 * See the code to know in details which dependencies are allowed. That's the first filtering.
		 * 
		 * =====
		 * 
		 * The second step consist in building HCI items by using this graph.
		 * Every vertex has a list of HCI items.
		 * 
		 * If 'A' depends on 'B', then "HCI items of A" include "HCI items of B".
		 * Besides, we have the root of the graph and the dependencies between vertices.
		 * Cycles are "roughly" resolved by keeping a list of vertices currently in visitation.
		 * This processing might result in incomplete HCI. In the case of a cycle, an exception is logged.
		 * 
		 * The associated algorithm relies on these ideas and the fact that simple types (xs:string...)
		 * define a basic HCI element. The result of this second step is the result to return for this method.
		 */

		// From XML trees to a dependency graph...
		Map<String, Vertex> graph = new HashMap<String, Vertex> ();
		Vertex rootVertex = buildGraphWithType((XsdType) rootElt, registry, graph );

		//		System.out.println( "Graph content______________________\n" );
		//		for( Map.Entry<String, Vertex> entry : graph.entrySet())
		//			System.out.println( entry.getKey() + " _ " + entry.getValue().getClass().toString());

		// Return the list of HCI items associated with the graph root.
		return new HciItemListBuilder( rootVertex ).get( rootVertex.id );
	}


	/* ******************************************************
	 * Methods to build the simplified graph of dependencies.
	 ********************************************************/

	/**
	 * Add a type vertex to the graph.
	 * @param xsdType the XSD type item to add.
	 * @param registry the XSD files' registry.
	 * @param graph the graph (list of vertices).
	 * @return the associated vertex which was created.
	 */
	private Type buildGraphWithType( XsdType xsdType, XsdGlobalRegistry registry, Map<String, Vertex> graph ) {

		if( graph.containsKey( xsdType.getTypeName()))
			return (Type) graph.get( xsdType.getTypeName());

		HciType type = HciType.resolveType( xsdType.getTypeName());

		if( type != HciType.UNRESOLVED ) {	// Base type: xs:string, ...
			BaseType baseType = resolveBaseType( type );
			graph.put( xsdType.getTypeName(), baseType );
			return baseType;
		}

		// complex type (only sequences for now, no choice...).
		SequenceType sequenceType = new SequenceType();
		sequenceType.id = xsdType.getId();
		sequenceType.name = xsdType.getTypeName();
		graph.put( xsdType.getId(), sequenceType );

		/* Get children elements which are neither documentation, nor enumerations.
		 * For each valid child, add its vertex result in the dependencies.
		 * Children here should be elements.
		 */
		for( String childId : xsdType.getChildren()) {
			// If the vertex was already defined for this child, get it.
			Vertex childVertex = graph.get( childId );

			if( childVertex == null ) {	// vertex to build
				XsdItem childItem = registry.getElementWithNs( childId );
				if( childItem != null ) {

					if( childItem instanceof XsdRestriction )
						childVertex = buildGraphWithRestriction((XsdRestriction) childItem, registry, graph, sequenceType );
					else if( childItem instanceof XsdExtension )
						childVertex = buildGraphWithExtension((XsdExtension) childItem, registry, graph, sequenceType );
					else if( childItem instanceof XsdElement )
						childVertex = buildGraphWithElement((XsdElement) childItem, registry, graph );
				}
			}	// end "if : childVertex == null"

			if( childVertex != null )
				sequenceType.dependencies.add( childVertex );
		}

		return sequenceType;
	}


	/**
	 * Add an element vertex to the graph.
	 * @param xsdElement the XSD element item to add.
	 * @param registry the XSD files' registry.
	 * @param graph the graph (list of vertices).
	 * @return the associated vertex which was created.
	 */
	private Item buildGraphWithElement( XsdElement xsdElement, XsdGlobalRegistry registry, Map<String, Vertex> graph ) {

		// Differentiate elements which references another element from complete elements.
		// => Referencer elements.
		if( xsdElement.getRef() != null ) {
			RefElement refElement = new RefElement ();
			refElement.id = xsdElement.getId();
			refElement.name = xsdElement.getRef();
			refElement.defaultValue = xsdElement.getDefaultValue();
			refElement.isNillable = "true".equalsIgnoreCase( xsdElement.getNillable()); //$NON-NLS-1$
			refElement.optional = "0".equals( xsdElement.getMinOccurs()); //$NON-NLS-1$

			try {
				refElement.minOccurs = Integer.parseInt( xsdElement.getMinOccurs());
			} catch( Exception e ) {
				refElement.minOccurs = 1;
			}

			try {
				refElement.maxOccurs = Integer.parseInt( xsdElement.getMaxOccurs());
			} catch( Exception e ) {
				if( "unbounded".equalsIgnoreCase( xsdElement.getMaxOccurs())) //$NON-NLS-1$
					refElement.maxOccurs = Integer.MAX_VALUE;
				else
					refElement.maxOccurs = refElement.minOccurs;
			}

			graph.put( xsdElement.getId(), refElement );

			// If the vertex was already defined for the referenced element, get it.
			Vertex refVertex = graph.get( xsdElement.getRef());
			if( refVertex == null ) {	// vertex to build
				XsdItem refItem = registry.getElementWithNs( xsdElement.getRef());
				if( !xsdElement.equals( refItem ) && refItem != null && refItem instanceof XsdElement )
					refVertex = buildGraphWithElement((XsdElement) refItem, registry, graph );
			}

			if( refVertex != null )
				refElement.dependencies.add( refVertex );

			// Process documentation children.
			for( String childId : xsdElement.getChildren()) {
				XsdItem childItem = registry.getElementWithNs( childId );

				if( childItem != null && childItem instanceof XsdDocumentation ) {
					String docValue = ((XsdDocumentation) childItem ).getDocumentationValue();
					String lang = ((XsdDocumentation) childItem ).getLang();
					if( lang == null )
						lang = Locale.getDefault().getLanguage();
					refElement.documentations.put( lang, docValue );
				}

				if( childItem != null && childItem instanceof XsdAnnotation ) {
					String name = ((XsdAnnotation) childItem ).getAnnotationName();
					String value = ((XsdAnnotation) childItem ).getAnnotationValue();
					refElement.annotations.add( new DependencyAnnotation( name, value ));
				}
			}

			return refElement;
		}

		// => Complete elements.
		Element element = new Element ();
		element.id = xsdElement.getId();
		element.defaultValue = xsdElement.getDefaultValue();
		element.isNillable = "true".equalsIgnoreCase( xsdElement.getNillable()); //$NON-NLS-1$
		element.optional = "0".equals( xsdElement.getMinOccurs()); //$NON-NLS-1$
		element.name = xsdElement.getName();

		graph.put( xsdElement.getId(), element );

		// resolve type - if the vertex was already defined for this child, get it.
		Vertex typeVertex = graph.get( xsdElement.getType());
		if( typeVertex == null ) {	// vertex to build
			HciType type = HciType.resolveType( xsdElement.getType());

			// case Simple Type
			if( type != HciType.UNRESOLVED ) {	// Base type: xs:string, ...
				BaseType baseType = resolveBaseType( type );
				graph.put( xsdElement.getType(), baseType );
				typeVertex = baseType;
			}

			// case Complex Type
			else {
				XsdItem typeItem = registry.getElementWithNs( xsdElement.getType());
				if( typeItem != null && typeItem instanceof XsdType )
					typeVertex = buildGraphWithType((XsdType) typeItem, registry, graph );
			}
		}

		if( typeVertex != null )
			element.dependencies.add( typeVertex );

		// Process documentation children.
		for( String childId : xsdElement.getChildren()) {
			XsdItem childItem = registry.getElementWithNs( childId );

			if( childItem != null && childItem instanceof XsdDocumentation ) {
				String docValue = ((XsdDocumentation) childItem ).getDocumentationValue();
				String lang = ((XsdDocumentation) childItem ).getLang();
				if( lang == null )
					lang = Locale.getDefault().getLanguage();
				element.documentations.put( lang, docValue );
			}

			if( childItem != null && childItem instanceof XsdAnnotation ) {
				String name = ((XsdAnnotation) childItem ).getAnnotationName();
				String value = ((XsdAnnotation) childItem ).getAnnotationValue();
				element.annotations.add( new DependencyAnnotation( name, value ));
			}
		}

		return element;
	}


	/**
	 * Add a type extension vertex to the graph.
	 * @param xsdExtension the XSD extension item to add.
	 * @param registry the XSD files' registry.
	 * @param graph the graph (list of vertices).
	 * @param parentType the type vertex which is the "<i>parent</i>" of this vertex in the XSD file.
	 * @return the associated vertex which was created.
	 */
	private ExtensionType buildGraphWithExtension(XsdExtension xsdExtension, XsdGlobalRegistry registry, Map<String, Vertex> graph, SequenceType parentType) {

		// Here, the XsdExtension is removed.
		// Before, we had "Type -> Extension -> several attributes".
		// Now, we will have "a type" with dependencies in the graph.

		ExtensionType extensionType = new ExtensionType ();
		extensionType.id = parentType.id;
		extensionType.name = parentType.name;
		graph.put( parentType.id, extensionType );

		/* Get children elements which are neither documentation, nor enumerations.
		 * For each valid child, add its vertex result in the dependencies.
		 * Children here should attributes.
		 */
		for( String childId : xsdExtension.getChildren()) {
			// If the vertex was already defined for this child, get it.
			Vertex childVertex = graph.get( childId );

			if( childVertex == null ) {	// vertex to build
				XsdItem childItem = registry.getElementWithNs( childId );
				if( childItem != null ) {
					if( childItem instanceof XsdAttribute )
						childVertex = buildGraphWithAttribute((XsdAttribute) childItem, registry, graph, extensionType );
					else if( childItem instanceof XsdElement )
						childVertex = buildGraphWithElement((XsdElement) childItem, registry, graph );
				}
			}	// end of "if : childVertex == null"

			if( childVertex != null )
				extensionType.dependencies.add( childVertex );
		}

		// There is another dependency to add here, which is the base type.
		// Avoid possible loops. Case "ns:type" where "ns" has the same URI than the targetNamespace.
		if( xsdExtension.getBase().equals( parentType.name )
					|| xsdExtension.getBase().endsWith( ":" + parentType.name )) //$NON-NLS-1$
			return extensionType;	// TODO: better idea ??? Check name space too...

		// If the type vertex was already defined for this child, get it.
		Vertex typeVertex = graph.get( xsdExtension.getBase());

		if( typeVertex == null ) {	// vertex to build
			XsdItem typeItem = registry.getElementWithNs( xsdExtension.getBase());
			if( typeItem != null && typeItem instanceof XsdType ) {
				typeVertex = buildGraphWithType((XsdType) typeItem, registry, graph );
			}
		}

		// typeVertex is still null ? Maybe this is a base type.
		if( typeVertex == null ) {
			HciType type = HciType.resolveType( xsdExtension.getBase());
			if( type != HciType.UNRESOLVED ) {
				BaseType baseType = resolveBaseType( type );
				graph.put( xsdExtension.getBase(), baseType );
				typeVertex = baseType;
			}
		}

		if( typeVertex != null )
			extensionType.dependencies.add( typeVertex );
		return extensionType;
	}


	/**
	 * Add an attribute vertex to the graph.
	 * We do not care about the <i>ref</i> attribute of "XSD attributes".
	 * 
	 * @param xsdAttribute the XSD attribute item to add.
	 * @param registry the XSD files' registry.
	 * @param graph the graph (list of vertices).
	 * @param parent the extension type parent.
	 * @return the associated vertex which was created.
	 */
	private Attribute buildGraphWithAttribute( XsdAttribute xsdAttribute, XsdGlobalRegistry registry, Map<String, Vertex> graph, ExtensionType parent ) {

		Attribute attribute = new Attribute();
		attribute.id = xsdAttribute.getId();
		attribute.name = xsdAttribute.getAttributeName();
		attribute.optional = "optional".equalsIgnoreCase( xsdAttribute.getUse()); //$NON-NLS-1$
		attribute.parent = parent;
		attribute.defaultValue = xsdAttribute.getDefaultValue();
		graph.put( xsdAttribute.getId(), attribute );

		// resolve type - if the vertex was already defined for this child, get it.
		Vertex typeVertex = graph.get( xsdAttribute.getType());
		if( typeVertex == null ) {	// vertex to build
			HciType type = HciType.resolveType( xsdAttribute.getType());

			// case Simple Type
			if( type != HciType.UNRESOLVED ) {	// Base type: xs:string, ...
				BaseType baseType = resolveBaseType( type );
				graph.put( xsdAttribute.getType(), baseType );
				typeVertex = baseType;
			}

			// case Complex Type
			else {
				XsdItem typeItem = registry.getElementWithNs( xsdAttribute.getType());
				if( typeItem != null && typeItem instanceof XsdType )
					typeVertex = buildGraphWithType((XsdType) typeItem, registry, graph );
			}
		}

		if( typeVertex != null )
			attribute.dependencies.add( typeVertex );

		// Process documentation children.
		for( String childId : xsdAttribute.getChildren()) {
			XsdItem childItem = registry.getElementWithNs( childId );
			if( childItem != null && childItem instanceof XsdDocumentation ) {

				String docValue = ((XsdDocumentation) childItem ).getDocumentationValue();
				String lang = ((XsdDocumentation) childItem ).getLang();
				if( lang == null )
					lang = Locale.getDefault().getLanguage();
				attribute.documentations.put( lang, docValue );
			}

			if( childItem != null && childItem instanceof XsdAnnotation ) {
				String name = ((XsdAnnotation) childItem ).getAnnotationName();
				String value = ((XsdAnnotation) childItem ).getAnnotationValue();
				attribute.annotations.add( new DependencyAnnotation( name, value ));
			}
		}

		return attribute;
	}


	/**
	 * Add a type restriction to the graph.
	 * @param xsdRestriction the XSD restriction item to add.
	 * @param registry the XSD files' registry.
	 * @param graph the graph (list of vertices).
	 * @param parentType the parent type.
	 * @return the associated vertex which was created.
	 */
	private EnumType buildGraphWithRestriction( XsdRestriction xsdRestriction, XsdGlobalRegistry registry, Map<String, Vertex> graph, SequenceType parentType ) {

		// Here, the XsdRestriction is removed.
		// Before, we had "Type -> Restriction -> several Enums".
		// Now, we will have "just enums" in the graph.

		EnumType enumType = new EnumType();
		enumType.id = parentType.id;
		enumType.name = parentType.name;
		graph.put( parentType.id, enumType );

		// Simply check its children. We are looking for enumerations, that's it.
		for( String childId : xsdRestriction.getChildren()) {
			XsdItem childItem = registry.getElementWithNs( childId );
			if( childItem != null && childItem instanceof XsdEnumeration ) {
				String enumValue = ((XsdEnumeration) childItem ).getValue();
				enumType.enumValues.add( enumValue );
			}
		}

		return enumType;
	}


	/**
	 * Return the base type vertex associated with a given HciType.
	 * @param type the HciType.
	 * @return the associated base type vertex.
	 */
	private BaseType resolveBaseType( HciType type ) {
		switch( type ) {
		case BOOLEAN: return BOOLEAN_BASE_TYPE;
		case DOUBLE: return DOUBLE_BASE_TYPE;
		case INTEGER: return INTEGER_BASE_TYPE;
		case STRING: return STRING_BASE_TYPE;
		case FILE: return FILE_BASE_TYPE;
		case URI: return URI_BASE_TYPE;
		case QNAME: return QNAME_BASE_TYPE;
		default: return UNRESOLVED_BASE_TYPE;
		}
	}


	/**
	 * The abstract class defining a graph vertex.
	 */
	public static abstract class Vertex {
		public String id;
		public List<Vertex> dependencies = new ArrayList<Vertex> ();

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "(" + this.id + ")";
		}
	}


	/**
	 * The abstract class defining a graph vertex for a type.
	 */
	public static abstract class Type extends Vertex {
		public String name;
	}
	/** The class defining a graph vertex for a base type. */
	public static class BaseType extends Type {}
	/** The class defining a graph vertex for a enumeration type. */
	public static class EnumType extends Type {
		public List<String> enumValues = new ArrayList<String> ();
	}
	/** The class defining a graph vertex for a type extension. */
	public static class ExtensionType extends Type {
		public List<Attribute> attributes = new ArrayList<Attribute> ();
	}
	/**
	 * The class defining a graph vertex for a type (potentially made up of <b>sequence</b> of elements).
	 * Only sequences are managed. Choice are not.
	 */
	public static class SequenceType extends Type {}

	/**
	 * The abstract class defining a graph vertex for an Item.
	 */
	public static abstract class Item extends Vertex {
		public String name;
		public String defaultValue;
		public Type type;
		public boolean optional = false;
		public Map<String, String> documentations = new HashMap<String, String> ();
		public List<DependencyAnnotation> annotations = new ArrayList<DependencyAnnotation> ();

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.name + " (" + this.id + ")";
		}
	}
	/** The class defining a graph vertex for an attribute. */
	public static class Attribute extends Item {
		public ExtensionType parent;	// Not used for now in HCI generation, but keep it anyway.
	}
	/** The class defining a graph vertex for an element. */
	public static class Element extends Item {
		public boolean isNillable;
	}
	/** The class defining a graph vertex for an element which references another element. */
	public static class RefElement extends Item {
		public boolean isNillable;
		public int minOccurs, maxOccurs;
	}
}
