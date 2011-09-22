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

package com.ebmwebsourcing.petals.services.su.utils;

import org.eclipse.core.runtime.IStatus;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper.IndexedElement;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;

/**
 * A set of utilities specific to the SU DOM-based editor.
 * @author Vincent Zurczak - EBM WebSourcing
 * TODO: add CDK and component parts methods here.
 */
public class DomEditorHelper {

	/**
	 * Sets the WSDL value.
	 * <p>
	 * If the WSDL element does not exist, it is created and inserted in the document.
	 * </p>
	 * <p>
	 * You must call {@link AbstractServicesFormPage#startTransaction()} before calling this method
	 * and {@link AbstractServicesFormPage#stopTransaction()} after.
	 * </p>
	 *
	 * @param providesElt the provides element
	 * @param wsdlValue the value to set (can be null)
	 */
	public static void setWsdl( Element providesElt, String wsdlValue ) {

		Element newElt = DomUtils.getChildElement( providesElt, "wsdl" );
		IndexedElement ie = null;
		if( newElt == null ) {
			ie = StructuredModelHelper.getChildElementToInsert(
						providesElt, "wsdl",
						StructuredModelHelper.INSERTION_ANYWHERE_VALID );
			newElt = ie.getElement();
		}

		if( newElt == null ) {
			PetalsServicesPlugin.log( "Could not insert the WSDL mark-up in the XML document.", IStatus.ERROR );

		} else {
			if( ! StringUtils.isEmpty( wsdlValue )) {
				StructuredModelHelper.setElementSimpleValue( newElt, wsdlValue.trim());
				newElt.removeAttributeNS( "http://www.w3.org/2001/XMLSchema-instance", "nil" );

			} else {
				StructuredModelHelper.setElementSimpleValue( newElt, "" );
				Attr nillableAttr = newElt.getAttributeNodeNS( "http://www.w3.org/2001/XMLSchema-instance", "nil" );
				if( nillableAttr == null ) {
					DomUtils.addOrSetAttribute( newElt, "xsi:nil", "true" );
					DomUtils.addOrSetAttribute(
								newElt.getOwnerDocument().getDocumentElement(),
								"xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
				}
			}

			if( ie != null )
				DomUtils.insertChildElement( providesElt, newElt, ie.getInsertionIndex());
		}
	}


	/**
	 * Sets the operation value.
	 * <p>
	 * If the WSDL element does not exist, it is created and inserted in the document.
	 * </p>
	 * <p>
	 * You must call {@link AbstractServicesFormPage#startTransaction()} before this method
	 * and {@link AbstractServicesFormPage#stopTransaction()} after.
	 * </p>
	 *
	 * @param consumesElt the consumes element
	 * @param namespace the operation name space (can be null)
	 * @param localPart the operation's local name (can be null)
	 */
	public static void setOperation( Element consumesElt, String namespace, String localPart ) {

		Element newElt = DomUtils.getChildElement( consumesElt, "operation" );
		IndexedElement ie = null;
		if( newElt == null ) {
			ie = StructuredModelHelper.getChildElementToInsert(
						consumesElt, "operation",
						StructuredModelHelper.INSERTION_ANYWHERE_VALID );
			newElt = ie.getElement();
		}

		if( newElt == null ) {
			PetalsServicesPlugin.log( "Could not insert the operation mark-up in the XML document.", IStatus.ERROR );

		} else {
			DomUtils.addOrSetAttribute( newElt, "xmlns:op", StringUtils.isEmpty( namespace ) ? "" : namespace );
			if( ! StringUtils.isEmpty( localPart ))
				StructuredModelHelper.setElementSimpleValue( newElt, "op:" + localPart.trim());
			else
				StructuredModelHelper.setElementSimpleValue( newElt, "" );

			if( ie != null ) {
				DomUtils.insertChildElement( consumesElt, newElt, ie.getInsertionIndex());
				org.w3c.dom.Text breakLine = consumesElt.getOwnerDocument().createTextNode( "\n\t\t\t" );
				DomUtils.insertChildElement( consumesElt, breakLine, ie.getInsertionIndex() + 1 );
			}
		}
	}


	/**
	 * Sets the MEP value.
	 * <p>
	 * If the MEP element does not exist, it is created and inserted in the document.
	 * </p>
	 * <p>
	 * You must call {@link AbstractServicesFormPage#startTransaction()} before calling this method
	 * and {@link AbstractServicesFormPage#stopTransaction()} after.
	 * </p>
	 *
	 * @param consumesElt the consumes element
	 * @param mep the MEP to set (can be null)
	 */
	public static void setMep( Element consumesElt, Mep mep ) {

		Element newElt = DomUtils.getChildElement( consumesElt, "mep" );
		IndexedElement ie = null;
		if( newElt == null ) {
			ie = StructuredModelHelper.getChildElementToInsert(
						consumesElt, "mep",
						StructuredModelHelper.INSERTION_ANYWHERE_VALID );
			newElt = ie.getElement();
		}

		if( newElt == null ) {
			PetalsServicesPlugin.log( "Could not insert the operation mark-up in the XML document.", IStatus.ERROR );

		} else {
			if( mep != null && mep != Mep.UNKNOWN ) {
				StructuredModelHelper.setElementSimpleValue( newElt, mep.toString());
				newElt.removeAttributeNS( "http://www.w3.org/2001/XMLSchema-instance", "nil" );

			} else {
				StructuredModelHelper.setElementSimpleValue( newElt, "" );
				Attr nillableAttr = newElt.getAttributeNodeNS( "http://www.w3.org/2001/XMLSchema-instance", "nil" );
				if( nillableAttr == null ) {
					DomUtils.addOrSetAttribute( newElt, "xsi:nil", "true" );
					DomUtils.addOrSetAttribute(
								newElt.getOwnerDocument().getDocumentElement(),
								"xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
				}
			}

			if( ie != null )
				DomUtils.insertChildElement( consumesElt, newElt, ie.getInsertionIndex());
		}
	}
}
