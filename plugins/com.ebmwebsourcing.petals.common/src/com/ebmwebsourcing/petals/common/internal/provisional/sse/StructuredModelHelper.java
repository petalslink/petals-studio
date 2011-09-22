/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.sse;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.contentmodel.util.DOMContentBuilder;
import org.eclipse.wst.xml.core.internal.contentmodel.util.DOMContentBuilderImpl;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl.XSDElementDeclarationAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 *
 * FIXME: this class relies on non-API code.
 * Changing the WTP version may result in troubles and not-working features.
 *
 * FIXME: create a new DOMContentBuilder that creates right elements?
 */
@SuppressWarnings( "restriction" )
public class StructuredModelHelper {

	public static final int INSERTION_APPEND = -1;
	public static final int INSERTION_ANYWHERE_VALID = -2;



	/**
	 * In WTP, element values are text nodes that are child of the element.
	 * <p>
	 * And {@link Element#setTextContent(String)} is not implemented.
	 * </p>
	 *
	 * @param element
	 * @param simpleValue
	 */
	public static void setElementSimpleValue( Element element, String simpleValue ) {

		Text textNode = element.getOwnerDocument().createTextNode( simpleValue );
		NodeList children = element.getChildNodes();
		for( int i=0; i<children.getLength(); i++ )
			element.removeChild( children.item( i ));

		element.appendChild( textNode );
	}


	/**
	 * Gets the value of an element.
	 * <p>
	 * If the element value is not simple (i.e. this element has children
	 * that are not text nodes), then null is returned.
	 * </p>
	 *
	 * @param element the element whose simple value must be found
	 * @return the element simple value, or null if the element has elements as children
	 */
	public static String getElementSimpleValue( Element element ) {

		boolean notSimple = false;

		StringBuilder result = new StringBuilder();
		NodeList children = element.getChildNodes();
		for( int i=0; i<children.getLength(); i++ ) {
			Node node = children.item( i );
			if( node instanceof Text ) {
				result.append(((Text) node).getNodeValue());
			}
			else {
				notSimple = true;
				break;
			}
		}

		return notSimple ? null : result.toString().trim();
	}


	/**
	 * Returns the XML node corresponding to the offset in the given document.
	 * @param document
	 * @param offset the offset
	 * @return a node or null if no node could be found
	 */
	public static Node getNodeByOffset( IDocument document, int offset ) {

		Node result = null;
		try {
			IStructuredModel model = StructuredModelManager.getModelManager().getExistingModelForRead( document );
			IndexedRegion indexedRegion = model.getIndexedRegion( offset );
			if( indexedRegion == null )
				indexedRegion = model.getIndexedRegion( offset - 1 );

			if( indexedRegion instanceof Node )
				result = (Node) indexedRegion;

		} catch( Exception e ) {
			e.printStackTrace();
		}

		return result;
	}


	/**
	 * Still experimental.
	 * <p>
	 * 1. We get the XSD reference matching the parent element.<br />
	 * 2. We get all the XSD references for the possible children of this parent element.<br />
	 * 3. For all the child references...<br />
	 * 4. We eliminate nodes that are not elements.<br />
	 * 5. We eliminate elements whose name is not the one we look for (prefix are ignored).<br />
	 * 6. We eliminate elements whose XSD reference's target name space is not declared in the current document.<br />
	 * 7. We eliminate elements whose insertion is impossible...<br />
	 * 7.a. We check if appending this child is valid ({@link #INSERTION_APPEND}).<br />
	 * 7.b. We check all the child positions until we find one valid ({@link #INSERTION_ANYWHERE_VALID}).<br />
	 * 7.c. We check the given index position.
	 * </p>
	 * <p>
	 * By default, when two elements have the same name but different name spaces, and can be inserted,
	 * then the name space prefix is not proposed in the created element.
	 * </p>
	 *
	 * @param parentElement
	 * @param newElementName
	 * @param insertionIndex
	 * @return
	 *
	 * FIXME: make the reporting and the error processing correct
	 */
	public static IndexedElement getChildElementToInsert( Element parentElement, String newElementName, int insertionIndex ) {

		int exactInsertionIndex = -1;
		CMElementDeclaration cmElt = null;

		// Get possibilities from the XML schemas
		Document doc = parentElement.getOwnerDocument();
		ModelQuery mQuery = ModelQueryUtil.getModelQuery( doc );
		CMElementDeclaration parentEd = mQuery.getCMElementDeclaration( parentElement );
		if( parentEd != null ) {

			List<?> nodes = mQuery.getAvailableContent( parentElement, parentEd, ModelQuery.INCLUDE_CHILD_NODES );
			int childrenSize = parentElement.getChildNodes().getLength();

			// Exit the loop as soon as one valid position is found
			childNodesLoop: for( Object o : nodes ) {
				if(((CMNode) o).getNodeType() != CMNode.ELEMENT_DECLARATION )
					continue;

				// Check element name
				cmElt = (CMElementDeclaration) o;
				if( ! newElementName.equals( cmElt.getNodeName()))
					continue;

				// Check element name space
				// Do not include elements whose name space is not already declared
				if( cmElt instanceof XSDElementDeclarationAdapter ) {
					XSDElementDeclarationAdapter a = (XSDElementDeclarationAdapter) cmElt;
					String nsUri = (String) a.getCMDocument().getProperty( XSDImpl.PROPERTY_TARGET_NAMESPACE_URI );
					if( nsUri == null
								|| DomUtils.lookupNamespacePrefix( nsUri, parentElement ) == null )
						continue;
				}
				// Yes, we're strict: only XSD models are expected
				else {
					continue;
				}


				// Append the new element
				if( insertionIndex == INSERTION_APPEND ) {
					if( mQuery.canInsert( parentElement, cmElt, childrenSize, ModelQuery.VALIDITY_STRICT )) {
						exactInsertionIndex = childrenSize;
						break childNodesLoop;
					}
				}

				// Insert at the first valid position
				else if( insertionIndex == INSERTION_ANYWHERE_VALID ) {
					for( int insertionPos = childrenSize; insertionPos >= 0; insertionPos -- ) {
						if( mQuery.canInsert( parentElement, cmElt, insertionPos, ModelQuery.VALIDITY_STRICT )) {
							exactInsertionIndex = insertionPos;
							break childNodesLoop;
						}
					}
				}

				// Insert at an exact index
				else {
					if( mQuery.canInsert( parentElement, cmElt, insertionIndex, ModelQuery.VALIDITY_STRICT )) {
						exactInsertionIndex = insertionIndex;
						break childNodesLoop;
					}
				}
			}
		}


		// Build the new element
		Element result = null;
		if( cmElt == null || exactInsertionIndex < 0 ) {
			PetalsCommonPlugin.log( "Unexpected result!", IStatus.ERROR );
		}
		else {
			DOMContentBuilder builder = new DOMContentBuilderImpl( doc );
			builder.setBuildPolicy( DOMContentBuilder.BUILD_ONLY_REQUIRED_CONTENT );
			builder.build( parentElement, cmElt );
			List<?> additions = builder.getResult();

			if( additions.size() == 1 ) {
				Object o = additions.get( 0 );
				if( o instanceof Element )
					result = (Element) o;
			}
			else {
				PetalsCommonPlugin.log( "Unexpected cardinality in result!", IStatus.ERROR );
			}
		}

		return new IndexedElement( result, exactInsertionIndex );
	}


	/**
	 * @param parentElement
	 * @param childElementName
	 * @param insertionIndex
	 * @return
	 */
	public static Element getOrCreateAndInsertChildElement( Element parentElement, String childElementName, int insertionIndex ) {

		Element result = DomUtils.getChildElement( parentElement, childElementName );
		if( result == null ) {
			IndexedElement ie = getChildElementToInsert( parentElement, childElementName, insertionIndex );
			if( ie.element != null && ie.insertionIndex != -1 ) {
				DomUtils.insertChildElement( parentElement, ie.element, insertionIndex );
				result = ie.element;
			}
		}

		return result;
	}


	/**
	 *
	 */
	public static class IndexedElement {
		Element element;
		int insertionIndex;

		/**
		 * Constructor.
		 * @param element
		 * @param insertionIndex
		 */
		private IndexedElement( Element element, int insertionIndex ) {
			this.element = element;
			this.insertionIndex = insertionIndex;
		}

		/**
		 * @return the element
		 */
		public Element getElement() {
			return this.element;
		}

		/**
		 * @return the insertionIndex
		 */
		public int getInsertionIndex() {
			return this.insertionIndex;
		}
	}
}
