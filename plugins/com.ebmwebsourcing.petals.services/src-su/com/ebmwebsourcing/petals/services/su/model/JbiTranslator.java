/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.model;

import java.io.InputStream;
import java.math.BigDecimal;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.ebmwebsourcing.petals.studio.dev.properties.SupportedTypes;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * This class makes the bridge between a jbi.xml file and a SU model.
 * @author Vincent Zurczak - Linagora
 */
public class JbiTranslator {

	/**
	 * Creates a jbi.xml from a SU model and a description of component version.
	 * @param suWizardModel
	 * @param desc
	 * @return the jbi.xml content as a string
	 */
	public String createJbiXml( SuWizardModel suWizardModel, ComponentVersionDescription desc ) {

		// Create the jbi.xml shape
		Jbi jbiInstance;
		jbiInstance = JbiFactory.eINSTANCE.createJbi();
		jbiInstance.setVersion( new BigDecimal("1.0"));
		jbiInstance.setServices(JbiFactory.eINSTANCE.createServices());
		jbiInstance.getServices().setBindingComponent( desc.isBc());

		PetalsMode petalsMode;
		if( suWizardModel.getEndpoint() instanceof Provides ) {
			petalsMode = PetalsMode.provides;
			jbiInstance.getServices().getProvides().add((Provides) suWizardModel.getEndpoint());
		} else {
			petalsMode = PetalsMode.consumes;
			jbiInstance.getServices().getConsumes().add((Consumes) suWizardModel.getEndpoint());
		}

		// Add properties in the XML
		Document doc = JbiXmlUtils.saveJbiXmlAsDocument( jbiInstance );
		NodeList nl = doc.getDocumentElement().getElementsByTagNameNS( "*", petalsMode == PetalsMode.provides ? "provides" : "consumes" );
		if( nl.getLength() != 1 ) {
			PetalsServicesPlugin.log( new Exception( "One node was expected!" ), IStatus.ERROR );

		} else {
			// Add the CDK elements
			Element providesOrConsumes = (Element) nl.item( 0 );
			insertProperties( suWizardModel.getCdkModel(), providesOrConsumes, desc, null, petalsMode );

			// Add the component elements
			String prefix = desc.getComponentAlias().toLowerCase().replaceAll( "-|_|\\s|\\.", "" );
			insertProperties( suWizardModel.getComponentModel(), providesOrConsumes, desc, prefix, petalsMode );
		}

		return DomUtils.writeDocument( doc );
	}


	/**
	 * Creates a jbi.xml from a SU model.
	 * @param suWizardModel
	 * @return the jbi.xml content as a string
	 */
	public String createJbiXml( Jbi jbiInstance, SuEditorModel model ) {

		// EMF --> DOM
		Document doc = JbiXmlUtils.saveJbiXmlAsDocument( jbiInstance );

		// Add provides properties
		NodeList nl = doc.getDocumentElement().getElementsByTagNameNS( "*", "provides" );
		if( nl.getLength() != 1 ) {
			PetalsServicesPlugin.log( new Exception( "One node was expected!" ), IStatus.ERROR );

		} else {
			for( int i=0; i<nl.getLength(); i++ ) {
				Element provides = (Element) nl.item( i );

				// Add the CDK elements
				AbstractModel am = model.getProvidesCdkModels().get( i );
				insertProperties( am, provides, model.getDesc(), null, PetalsMode.provides );

				// Add the component elements
				am = model.getProvidesComponentModels().get( i );
				String prefix = model.getDesc().getComponentAlias().toLowerCase().replaceAll( "-|_|\\s|\\.", "" );
				insertProperties( am, provides, model.getDesc(), prefix, PetalsMode.provides );
			}
		}

		// Add consumes properties
		nl = doc.getDocumentElement().getElementsByTagNameNS( "*", "consumes" );
		if( nl.getLength() != 1 ) {
			PetalsServicesPlugin.log(
					new Exception( "One node was expected!" ), IStatus.ERROR );

		} else {
			for( int i = 0; i < nl.getLength(); i++ ) {
				Element consumes = (Element) nl.item( i );

				// Add the CDK elements
				AbstractModel am = model.getConsumesCdkModels().get( i );
				insertProperties( am, consumes, model.getDesc(), null, PetalsMode.consumes );

				// Add the component elements
				am = model.getConsumesComponentModels().get( i );
				String prefix = model.getDesc().getComponentAlias().toLowerCase().replaceAll( "-|_|\\s|\\.", "" );
				insertProperties( am, consumes, model.getDesc(), prefix, PetalsMode.consumes );
			}
		}

		return DomUtils.writeDocument( doc );
	}


	/**
	 * Reads a jbi.xml and builds a model for the editor.
	 * <p>
	 * As a reminder, this model is a transition toward a future change in Petals, hopefully.<br />
	 * JBI.xml files should stick to the specification, and additional properties should be externalized.
	 * CDK properties in a "cdk.properties" file, component properties in a "component.properties" file.
	 * </p>
	 *
	 * @param jbiXmlFile
	 * @return an instance of a model for the SU editor
	 */
	public SuEditorModel readFromJbiXml( IFile jbiXmlFile ) {

		SuEditorModel result = null;
		Document doc = DomUtils.buildDocument( jbiXmlFile.getLocation().toFile());
		if( doc != null ) {
			String xpath = "//*[local-name()='provides' or local-name()='consumes']";
			NodeList nodes = (NodeList) XPathUtils.evaluateXPathExpression( xpath, doc, XPathConstants.NODESET );

			// For all "provides" or "consumes"...
			for( int i=0; i<nodes.getLength(); i++ ) {
				Element elt = (Element) nodes.item( i );

				// Find the right description...
				// ... but only if we have not yet found it
				for( int j=0; result == null && j<elt.getChildNodes().getLength(); j++ ) {
					Node child = elt.getChildNodes().item( i );
					if( !( child instanceof Element ))
						continue;

					ComponentVersionDescription desc = findDesc((Element) child);
					if( desc != null )
						result = new SuEditorModel( desc );
				}

				// Parse the elements
				// Unknown model
				if( result == null )
					break;

				// Parse the model elements
				PetalsMode petalsMode = elt.getNodeName().toLowerCase().endsWith( "provides" ) ? PetalsMode.provides : PetalsMode.consumes;
				AbstractModel model = parseElement( elt, result.getDesc(), true, petalsMode );
				if( petalsMode == PetalsMode.provides )
					result.getProvidesCdkModels().add( model );
				else
					result.getConsumesCdkModels().add( model );

				model = parseElement( elt, result.getDesc(), false, petalsMode );
				if( petalsMode == PetalsMode.provides )
					result.getProvidesComponentModels().add( model );
				else
					result.getConsumesComponentModels().add( model );
			}
		}

		return result;
	}


	/**
	 * Finds the component description from a DOM element.
	 * @param elt
	 * @return a component version description, or null if none matched
	 */
	private ComponentVersionDescription findDesc( Element elt ) {

		for( ComponentVersionDescription desc : ExtensionManager.INSTANCE.findAllComponentVersionDescriptions()) {
			if( desc.getComponentNamespace().equals( elt.getNamespaceURI()))
					return desc;
		}

		return null;
	}


	/**
	 * Parses an element and builds an abstract model.
	 * @param elt the provides or consumes element
	 * @param desc the component version description to use
	 * @param cdk true if we look for CDK parameters, false for component ones
	 * @param petalsMode the Petals mode
	 * @return an abstract model (never null)
	 */
	private AbstractModel parseElement( Element elt, ComponentVersionDescription desc, boolean cdk, PetalsMode petalsMode ) {

		String ns = cdk ? desc.getCdkNamespace() : desc.getComponentNamespace();
		InputStream is = cdk ? desc.loadCdkModel( petalsMode ) : desc.loadComponentModel( petalsMode );
		AbstractModel model = AbstractModel.create( is );
		IoUtils.closeQuietly( is );

		for( int i=0; i<elt.getChildNodes().getLength(); i++ ) {
			Node child = elt.getChildNodes().item( i );
			if( !( child instanceof Element ))
				continue;

			if( ! ns.equals( elt.getNamespaceURI()))
				continue;

			String key = elt.getNodeName();
			String value = elt.getTextContent().trim();
			if( model.getType( key ) == SupportedTypes.QNAME
					&& value.contains( ":" )) {
				String local = NamespaceUtils.removeNamespaceElements( value );
				String uri = NamespaceUtils.getNamespaceUri( value, child );
				value = "{" + uri + "}" + local;
			}

			model.set( key, value );
		}

		return model;
	}


	/**
	 * Inserts properties in a DOM model.
	 * @param model
	 * @param providesOrConsumes
	 * @param desc
	 * @param prefix the component name space or null for the CDK
	 * @param petalsMode
	 */
	private void insertProperties(
			AbstractModel model,
			Element providesOrConsumes,
			ComponentVersionDescription desc,
			String prefix,
			PetalsMode petalsMode ) {

		// Declare the name spaces and add comments
		Text bl = providesOrConsumes.getOwnerDocument().createTextNode( "\n\n\t\t\t" );
		providesOrConsumes.appendChild( bl );

		boolean isCdk = prefix == null;
		String commentText = isCdk ? "CDK parameters" : "Component parameters";
		Comment comment =  providesOrConsumes.getOwnerDocument().createComment( commentText );
		providesOrConsumes.appendChild( comment );

		bl = providesOrConsumes.getOwnerDocument().createTextNode( "\n\t\t\t" );
		providesOrConsumes.appendChild( bl );

		if( prefix == null )
			prefix = "cdk";

		String ns = isCdk ? desc.getCdkNamespace() : desc.getComponentNamespace();
		DomUtils.addOrSetAttribute(
				providesOrConsumes.getOwnerDocument().getDocumentElement(),
				"xmlns:" + prefix, ns );

		// Add the values
		boolean hasNullable = false;
		String[] sortedProperties = isCdk ? desc.getSortedCdkProperties( petalsMode ) : desc.getSortedComponentProperties( petalsMode );
		for( String key : sortedProperties ) {

			if( AbstractModel.PROPERTY_VERSION.equals( key ))
				continue;

			String value = model.getTrimmedPropertyValue( key );
			Element elt = providesOrConsumes.getOwnerDocument().createElement( prefix + ":" + key );

			if( StringUtils.isEmpty( value )) {
				if( model.isRequired( key )
						&& model.isNullable( key )) {
					DomUtils.addOrSetAttribute( elt, "xsi:nil", "true" );
					hasNullable = true;
				}

			} else {
				if( model.getType( key ) == SupportedTypes.QNAME ) {
					providesOrConsumes.appendChild( elt );
					QName qName = model.getQNameValue( key );

					if( ! StringUtils.isEmpty( qName.getNamespaceURI())) {
						elt.setTextContent( "qname:" + qName.getLocalPart());
						DomUtils.addOrSetAttribute( elt, "xmlns:qname", qName.getNamespaceURI());

					} else {
						elt.setTextContent( qName.getLocalPart());
					}

				} else {
					elt.setTextContent( value );
					providesOrConsumes.appendChild( elt );
				}
			}
		}

		// Was nullable declared?
		if( hasNullable ) {
			DomUtils.addOrSetAttribute(
					providesOrConsumes.getOwnerDocument().getDocumentElement(),
					"xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		}
	}
}
