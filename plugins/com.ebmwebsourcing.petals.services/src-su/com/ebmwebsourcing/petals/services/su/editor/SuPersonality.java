/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.su.editor;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.xpath.XPathConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.AbstractJbiEditorPersonality;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.editor.ServicesLabelProvider;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * The SU personality for the JBI editor.
 */
public class SuPersonality extends AbstractJbiEditorPersonality {

	private ILabelProvider statusLineLabelProvider;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #getStatusLineLabelProvider()
	 */
	@Override
	public ILabelProvider getStatusLineLabelProvider() {
		if( this.statusLineLabelProvider == null )
			this.statusLineLabelProvider = new ServicesLabelProvider();

		return this.statusLineLabelProvider;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #dispose()
	 */
	@Override
	public void dispose() {
		if( this.statusLineLabelProvider != null )
			this.statusLineLabelProvider.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #matchesPersonality(org.eclipse.core.resources.IFile)
	 */
	@Override
	public boolean matchesPersonality(Jbi jbi, IFile jbiXmlFile ) {
		return jbi.getServices() != null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #getTitle()
	 */
	@Override
	public String getTitle() {
		return "Service Unit";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #getTitleImage()
	 */
	@Override
	public Image getTitleImage() {
		return PetalsServicesPlugin.loadImage( "icons/obj16/service_unit.png" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #createControl(org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.services.su.editor.ISharedEdition)
	 */
	@Override
	public void createControl( Composite parent, ISharedEdition ise ) {
		new SuEditionComposite( parent, ise );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.AbstractJbiEditorPersonality
	 * #saveModel(com.sun.java.xml.ns.jbi.Jbi, org.eclipse.core.resources.IFile, org.eclipse.emf.edit.domain.EditingDomain)
	 */
	@Override
	public void saveModel( final Jbi model, final IFile editedFile, EditingDomain domain ) {

		Document doc = JbiXmlUtils.saveJbiXmlAsDocument( model );
		sortNodes( doc, true );

		String s = DomUtils.writeDocument( doc );
		try {
			editedFile.setContents( new ByteArrayInputStream( s.getBytes()), true, true, null );
		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Sorts the nodes.
	 * <p>
	 * This is not very clean, because this plug-in should not be aware
	 * of the CDK. However, a pure EMF approach is not possible.
	 * Customizing the XML save handler is too complicated. This approach works.
	 * </p>
	 * <p>
	 * One way to solve it would be to merge the JBI meta-model with the CDK model.
	 * But that would prevent this tooling from being used with a possible new CDK.
	 * </p>
	 *
	 * @param doc the document
	 */
	public static void sortNodes( Document doc, boolean indent ) {

		// Find all the provides and consumes
		if( doc == null )
			return;

		NodeList nodes = (NodeList) XPathUtils.evaluateXPathExpression(
				"//*[local-name()='provides' or local-name()='consumes']",
				doc.getDocumentElement(), XPathConstants.NODESET );

		if( nodes == null )
			return;

		// Sort their children
		for( int i=0; i<nodes.getLength(); i++ ) {
			Element elt = (Element) nodes.item( i );
			if( "provides".equalsIgnoreCase( DomUtils.getNodeName( elt ))) {
				LinkedHashMap<String,Element> map = new LinkedHashMap<String,Element>();
				map.put( "timeout", null );
				map.put( "validate-wsdl", null );
				map.put( "forward-security-subject", null );
				map.put( "forward-message-properties", null );
				map.put( "forward-attachments", null );
				map.put( "wsdl", null );

				sortChildren( elt, map, indent );
			}
			else {
				LinkedHashMap<String,Element> map = new LinkedHashMap<String,Element>();
				map.put( "timeout", null );
				map.put( "operation", null );
				map.put( "mep", null );

				sortChildren( elt, map, indent );
			}
		}
	}


	/**
	 * Sorts the children for a provides or consumes element.
	 * @param elt the provides or consumes element
	 * @param cdkElements the known CDK elements (keys = element names, values = DOM elements, initialized to null)
	 */
	private static void sortChildren( Element elt, LinkedHashMap<String,Element> cdkElements, boolean indent ) {

		ArrayList<Element> componentElements = new ArrayList<Element> ();
		ArrayList<Element> unknownCdkElements = new ArrayList<Element> ();


		// Sort children and remove them progressively
		for( int i=0; i<elt.getChildNodes().getLength(); i++ ) {
			Node node = elt.getChildNodes().item( i );
			if( !( node instanceof Element ))
				continue;

			Element childElt = (Element) node;
			String ns = childElt.getNamespaceURI();
			String name = DomUtils.getNodeName( childElt );

			// Component elements: preserve the original order
			if( ! ns.toLowerCase().contains( "components/extensions" )) {
				componentElements.add( childElt );
			}

			// CDK elements: store it and update the insertion list later
			else if( cdkElements.containsKey( name ))
				cdkElements.put( name, childElt );
			else
				unknownCdkElements.add( childElt );
		}


		// Remove all the children
		while( elt.getChildNodes().getLength() > 0 )
			elt.removeChild( elt.getChildNodes().item( 0 ));


		// Update the document with the right insertion order
		// Add some comments too
		String indentation = indent ? "\t\t\t" : "\t";
		elt.appendChild( elt.getOwnerDocument().createTextNode( "\n\n" + indentation ));
		elt.appendChild( elt.getOwnerDocument().createComment( "CDK Properties" ));

		elt.appendChild( elt.getOwnerDocument().createTextNode( "\n" + indentation ));
		for( Element e : cdkElements.values()) {
			if( e != null )
				elt.appendChild( e );
		}

		for( Element e : unknownCdkElements ) {
			elt.appendChild( e );
		}

		elt.appendChild( elt.getOwnerDocument().createTextNode( "\n\n" + indentation ));
		elt.appendChild( elt.getOwnerDocument().createComment( "Component's Specific Properties" ));

		elt.appendChild( elt.getOwnerDocument().createTextNode( "\n" + indentation ));
		for( Element e : componentElements ) {
			elt.appendChild( e );
		}
	}
}
