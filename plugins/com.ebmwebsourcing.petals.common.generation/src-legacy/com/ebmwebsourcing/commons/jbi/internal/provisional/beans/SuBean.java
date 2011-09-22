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

package com.ebmwebsourcing.commons.jbi.internal.provisional.beans;

import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A bean used to generate jbi.xml files for a Service Unit.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuBean {


	public static final String GENERATED_NS = "generatedNs";
	public static final String INTERFACE_NS = "interfaceNs";
	public static final String SERVICE_NS = "serviceNs";


	/** The list of name spaces of the document, each one being given as a couple <code>( prefix, URL )</code>. */
	private final Map<String, String> namespaces = new HashMap<String, String> ();

	/** The service name. */
	protected String serviceName;
	/** The service name name space. */
	protected String serviceNamespaceUri;

	/** The interface name. */
	protected String interfaceName;
	/** The interface name name space. */
	protected String interfaceNamespaceUri;

	/** The end point name. */
	protected String endpointName;

	/** The link type. */
	protected String linkType;
	/** True if the service unit is in consume mode. False by default. */
	protected boolean isConsume = false;
	/** True if the service unit is used to configure a binding component. True by default.  */
	protected boolean isBc = true;

	/** The component name which will <i>use</i> this service unit. */
	protected String componentName;
	/** The version of the component which will <i>use</i> this service unit. */
	protected String componentVersion;
	/** A human-readable explanation of what this service unit configures (e.g. FTP, XSLT). */
	protected String suType;

	/** An ordered list of XML elements relative to the CDK schema. */
	final public ArrayList<XmlElement> cdkElements = new ArrayList<XmlElement> ();
	/** An ordered list of XML elements relative to a component schema version. */
	final public ArrayList<XmlElement> specificElements = new ArrayList<XmlElement> ();

	/**
	 * The mapping between an URL and a list of XmlElements.
	 * 
	 * If the files associated with this URL have been renamed during the import, and
	 * that this URL is associated with these XmlElements, then their value
	 * is updated with the new file name. This update is taken into account during the generation
	 * of the jbi.xml for the service unit.
	 */
	final public Map<URL, List<XmlElement>> urlsToElements = new HashMap<URL, List<XmlElement>> ();

	/**
	 * The mapping between an URL and a list of XmlAttributes.
	 * 
	 * If the files associated with this URL have been renamed during the import, and
	 * that this URL is associated with these XmlAttributes, then their value
	 * is updated with the new file name. This update is taken into account during the generation
	 * of the jbi.xml for the service unit.
	 */
	final public Map<URL, List<XmlAttribute>> urlsToAttributes = new HashMap<URL, List<XmlAttribute>> ();



	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return this.serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName( String serviceName ) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceNameNs
	 */
	public String getServiceNamespaceUri() {
		return this.serviceNamespaceUri;
	}

	/**
	 * @param serviceNamespaceUri the serviceNameNs to set
	 */
	public void setServiceNamespaceUri( String serviceNamespaceUri ) {
		this.serviceNamespaceUri = serviceNamespaceUri;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return this.interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName( String interfaceName ) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the interfaceNameNs
	 */
	public String getInterfaceNamespaceUri() {
		return this.interfaceNamespaceUri;
	}

	/**
	 * @param interfaceNamespaceUri the interfaceNameNs to set
	 */
	public void setInterfaceNamespaceUri( String interfaceNamespaceUri) {
		this.interfaceNamespaceUri = interfaceNamespaceUri;
	}

	/**
	 * @return the endpointName
	 */
	public String getEndpointName() {
		return this.endpointName;
	}

	/**
	 * @param endpointName the endpointName to set
	 */
	public void setEndpointName( String endpointName ) {
		this.endpointName = endpointName;
	}

	/**
	 * @return the isConsume
	 */
	public boolean isConsume() {
		return this.isConsume;
	}

	/**
	 * @param isConsume the isConsume to set. False by default.
	 */
	public void setConsume( boolean isConsume ) {
		this.isConsume = isConsume;
	}

	/**
	 * @return the isBc
	 */
	public boolean isBc() {
		return this.isBc;
	}

	/**
	 * @param isBc the isBc to set. True by default.
	 */
	public void setBc( boolean isBc ) {
		this.isBc = isBc;
	}

	/**
	 * @return the linkType
	 */
	public String getLinkType() {
		return this.linkType;
	}

	/**
	 * @param linkType the linkType to set
	 */
	public void setLinkType( String linkType ) {
		this.linkType = linkType;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName( String componentName ) {
		this.componentName = componentName;
	}

	/**
	 * @return the componentVersion
	 */
	public String getComponentVersion() {
		return this.componentVersion;
	}

	/**
	 * @param componentVersion the componentVersion to set
	 */
	public void setComponentVersion( String componentVersion ) {
		this.componentVersion = componentVersion;
	}

	/**
	 * @return the suType
	 */
	public String getSuType() {
		return this.suType;
	}

	/**
	 * @param suType the suType to set
	 */
	public void setSuType(String suType) {
		this.suType = suType;
	}


	/**
	 * @return the CDK XML elements as a String.
	 */
	public String getCdkElementsAsString() {
		StringBuilder sb = new StringBuilder();
		for( XmlElement cdkElement : this.cdkElements )
			sb.append( cdkElement.toString( 3 ));

		String result = sb.toString();
		if( result.endsWith( "\n" ))
			result = result.substring( 0, result.length() - 1 );
		return result;
	}


	/**
	 * @return the component specific elements as a String.
	 */
	public String getSpecificsElementsAsString() {
		StringBuilder sb = new StringBuilder();
		for( XmlElement specificElement : this.specificElements )
			sb.append( specificElement.toString( 3 ));

		String result = sb.toString();
		if( result.endsWith( "\n" ))
			result = result.substring( 0, result.length() - 1 );
		return result;
	}


	/**
	 * @return compute and return all the namespaces to write in the jbi.xml file.
	 * <p>
	 * The namepaces are sorted by namespace prefix.
	 * </p>
	 */
	public Map<String, String> getComputedNamespaces() {

		this.namespaces.put( "xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		this.namespaces.put( "jbi", "http://java.sun.com/xml/ns/jbi" );

		// Name spaces for Service and Interface names.
		String servicePrefix = null, interfacePrefix = null;
		if( sameNsForInterfaceAndService ()) {
			servicePrefix = GENERATED_NS;
			interfacePrefix = GENERATED_NS;
			this.namespaces.put( interfacePrefix, this.interfaceNamespaceUri );
		}
		else {
			if( this.interfaceNamespaceUri != null && this.interfaceNamespaceUri.length() > 0 ) {
				interfacePrefix = INTERFACE_NS;
				this.namespaces.put( interfacePrefix, this.interfaceNamespaceUri );
			}

			if( this.serviceNamespaceUri != null && this.serviceNamespaceUri.length() > 0 ) {
				servicePrefix = SERVICE_NS;
				this.namespaces.put( servicePrefix, this.serviceNamespaceUri );
			}
		}

		TreeMap<String, String> map = new TreeMap<String, String>( Collator.getInstance() );
		map.putAll( this.namespaces );
		return map;
	}


	/**
	 * @return true if the interface-name and the service-name have the same namespace URI, false otherwise
	 */
	public boolean sameNsForInterfaceAndService() {
		return this.interfaceNamespaceUri != null && this.interfaceNamespaceUri.equals( this.serviceNamespaceUri );
	}


	/**
	 * @param prefix
	 * @param namespaceUri
	 */
	public void addNamespace( String prefix, String namespaceUri ) {
		this.namespaces.put( prefix, namespaceUri );
	}


	/**
	 * @param namespaces namespaces to add to this class (key=ns prefix, value=ns URI)
	 */
	public void addNamespaces( Map<String, String> namespaces ) {
		this.namespaces.putAll( namespaces );
	}
}
