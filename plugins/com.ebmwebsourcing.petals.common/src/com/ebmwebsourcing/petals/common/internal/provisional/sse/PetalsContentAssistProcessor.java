/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.sse;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQueryAction;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xml.ui.internal.contentassist.XMLContentAssistProcessor;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl.XSDElementDeclarationAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 *
 * FIXME: this class relies on non-API code.
 * Changing the WTP version may result in troubles and not-working features.
 */
@SuppressWarnings( "restriction" )
public class PetalsContentAssistProcessor extends XMLContentAssistProcessor {

	/**
	 * Overrides the super method so that it only proposes relevant candidates.
	 * <p>
	 * In particular, this implementation looks at the available name spaces
	 * to determine whether a candidate is a good one or not.
	 * </p>
	 *
	 * (non-Javadoc)
	 * @see org.eclipse.wst.xml.ui.internal.contentassist.AbstractContentAssistProcessor
	 * #getAvailableChildElementDeclarations(org.w3c.dom.Element, int, int)
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	protected List getAvailableChildElementDeclarations( Element parentElement, int childPosition, int kindOfAction ) {

		List<Object> cmnodes = new ArrayList<Object> ();

		Document doc = parentElement.getOwnerDocument();
		ModelQuery mQuery = ModelQueryUtil.getModelQuery( doc );
		CMElementDeclaration parentEd = mQuery.getCMElementDeclaration( parentElement );
		if( parentEd != null ) {

			List<?> modelQueryActions = new ArrayList<Object> ();
			mQuery.getInsertActions(
						parentElement,
						parentEd,
						childPosition,
						ModelQuery.INCLUDE_CHILD_NODES | ModelQuery.INCLUDE_SEQUENCE_GROUPS,
						ModelQuery.VALIDITY_NONE,
						modelQueryActions );

			boolean isBlockValid = mQuery.isContentValid( parentElement );
			for( Object o : modelQueryActions ) {
				ModelQueryAction action = (ModelQueryAction) o;
				if( childPosition < 0 ||
							action.getStartIndex() <= childPosition
							&& childPosition <= action.getEndIndex()
							&& action.getKind() == kindOfAction) {

					CMNode actionCMNode = action.getCMNode();
					if( actionCMNode == null || cmnodes.contains( actionCMNode ))
						continue;

					// Check element name space
					// Do not include elements whose name space is not already declared
					if( actionCMNode instanceof XSDElementDeclarationAdapter ) {
						XSDElementDeclarationAdapter a = (XSDElementDeclarationAdapter) actionCMNode;
						String nsUri = (String) a.getCMDocument().getProperty( XSDImpl.PROPERTY_TARGET_NAMESPACE_URI );
						if( nsUri == null
									|| DomUtils.lookupNamespacePrefix( nsUri, parentElement ) == null )
							continue;
					}
					// Yes, we're strict: only XSD models are expected
					else {
						continue;
					}

					// Is insertion valid?
					// If the parent is valid before the insertion, apply a strict validation
					if( isBlockValid ) {
						if( ! mQuery.canInsert( parentElement, actionCMNode, childPosition, ModelQuery.VALIDITY_STRICT ))
							continue;
					}

					// Otherwise, use a partial one, based on the declared name spaces
					else {
						if( ! mQuery.canInsert( parentElement, actionCMNode, childPosition, ModelQuery.VALIDITY_PARTIAL ))
							continue;
					}

					cmnodes.add( actionCMNode );
				}
			}
		}

		return cmnodes;
	}
}
