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
 
package com.ebmwebsourcing.petals.common.generation;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The common elements related to the generation of JBI descriptors for Petals components.
 * <p>
 * The version and the component name should be defined by any sub-class
 * constructor.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiXmlGenerationHelper {

	/**
	 * The value for a dynamic Petals' end-point name.
	 */
	public static final String AUTO_GENERATE = "autogenerate";

	protected boolean generateComments = true;
	protected String version = "";
	protected String componentName;
	protected String encoding = "UTF-8";
	protected boolean defaultIsBc = false;


	/**
	 * Create a JBI descriptor for a Petals component.
	 * @param beans a list of {@link AbstractJbiXmlBean} (not null and not empty).
	 * @return the jbi.xml content
	 */
	public StringBuilder createJbiDescriptor( AbstractJbiXmlBean... beans ) {

		if( beans == null )
			throw new IllegalArgumentException( "The list of beans cannot be empty." );

		StringBuilder sb = new StringBuilder();
		sb.append( "<?xml version=\"1.0\" encoding=\"" + this.encoding + "\"?>\n\n" );
		if( this.generateComments ) {
			sb.append( "<!-- JBI descriptor for the Petals component " );
			sb.append( this.componentName + " " + this.version + " -->\n" );
		}

		Map<String,String> namespaces = new TreeMap<String,String>();
		namespaces.put( "jbi", "http://java.sun.com/xml/ns/jbi" );
		namespaces.put( "xsi", "http://www.w3.org/2001/XMLSchema-instance" );
		for( AbstractJbiXmlBean bean : beans )
			namespaces.putAll( bean.getNamespaces());

		sb.append( "<jbi:jbi version=\"1.0\" \n" );
		for( Iterator<Map.Entry<String,String>> it = namespaces.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String,String> entry = it.next();
			if( it.hasNext())
				sb.append( "\txmlns:" + entry.getKey() + "=\"" + entry.getValue() + "\"\n" );
			else
				sb.append( "\txmlns:" + entry.getKey() + "=\"" + entry.getValue() + "\">\n\n" );
		}

		String isBc = "";
		if( beans.length > 0 )
			isBc = String.valueOf( beans[ 0 ].isBc());
		else
			isBc = String.valueOf( this.defaultIsBc );

		sb.append( "\t<jbi:services binding-component=\"" + isBc + "\">\n" );
		for( AbstractJbiXmlBean bean : beans ) {
			if( bean.isProvides())
				createProvideSection( sb, bean );
			else
				createConsumeSection( sb, bean );
		}

		sb.append( "\n\t</jbi:services>\n" );
		sb.append( "</jbi:jbi>\n" );
		return sb;
	}


	/**
	 * Creates a 'provides' section in the XML document.
	 * @param sb
	 * @param bean
	 */
	protected void createProvideSection( StringBuilder sb, AbstractJbiXmlBean bean ) {

		boolean sameNs =
			bean.getInterfaceNsUri() == null && bean.getServiceNsUri() == null
			|| bean.getInterfaceNsUri() != null && bean.getInterfaceNsUri().equals( bean.getServiceNsUri());

		String itfPrefix = null;
		String srvPrefix = null;
		for( Map.Entry<String,String> entry : bean.getNamespaces().entrySet()) {
			if( entry.getValue() != null ) {
				if( entry.getValue().equals( bean.getInterfaceNsUri()))
					itfPrefix = entry.getKey();
				else if( entry.getValue().equals( bean.getServiceNsUri()))
					srvPrefix = entry.getKey();
			}

			if( itfPrefix != null && srvPrefix != null )
				break;
		}

		sb.append( "\n\t\t<jbi:provides\n" );
		sb.append( "\t\t\tinterface-name=\"");
		if( itfPrefix == null )
			sb.append(( sameNs ? "generatedNs" : "interfaceNs" ) + ":" + bean.getInterfaceName() + "\"" );
		else
			sb.append( itfPrefix + ":" + bean.getInterfaceName() + "\"" );

		sb.append( "\n\t\t\tservice-name=\"");
		if( srvPrefix == null )
			sb.append(( sameNs ? "generatedNs" : "serviceNs" ) + ":" + bean.getServiceName() + "\"" );
		else
			sb.append( srvPrefix + ":" + bean.getServiceName() + "\"" );

		sb.append( "\n\t\t\tendpoint-name=\"" + bean.getEndpointName() + "\"" );
		if( itfPrefix == null || srvPrefix == null ) {
			if( sameNs )
				sb.append( "\n\t\t\txmlns:generatedNs=\"" + bean.getInterfaceNsUri() + "\"" );
			else {
				sb.append( "\n\t\t\txmlns:interfaceNs=\"" + bean.getInterfaceNsUri() + "\"" );
				sb.append( "\n\t\t\txmlns:serviceNs=\"" + bean.getServiceNsUri() + "\"" );
			}
		}

		sb.append( ">\n\n" );
		String cdk = bean.getCdkSection();
		if( this.generateComments && cdk != null ) {
			if( cdk.trim().length() > 0 )
				sb.append( "\t\t\t<!-- CDK elements -->\n" );
			else
				sb.append( "\t\t\t<!-- No CDK element -->\n" );
		}

		if( cdk != null )
			sb.append( cdk + "\n" );

		String component = bean.getComponentSection();
		if( this.generateComments && component != null ) {
			if( component.trim().length() > 0 )
				sb.append( "\t\t\t<!-- Component specific elements -->\n" );
			else
				sb.append( "\t\t\t<!-- No component specific element -->\n" );
		}

		if( component != null )
			sb.append( component + "\n" );

		sb.append( "\t\t</jbi:provides>\n" );
	}


	/**
	 * Creates a 'consumes' section in the XML document.
	 * @param sb
	 * @param bean
	 */
	protected void createConsumeSection( StringBuilder sb, AbstractJbiXmlBean bean ) {

		boolean sameNs =
			bean.getInterfaceNsUri() == null && bean.getServiceNsUri() == null
			|| bean.getInterfaceNsUri() != null && bean.getInterfaceNsUri().equals( bean.getServiceNsUri());

		String itfPrefix = null;
		String srvPrefix = null;
		for( Map.Entry<String,String> entry : bean.getNamespaces().entrySet()) {
			if( entry.getValue() != null ) {
				if( entry.getValue().equals( bean.getInterfaceNsUri()))
					itfPrefix = entry.getKey();
				else if( entry.getValue().equals( bean.getServiceNsUri()))
					srvPrefix = entry.getKey();
			}

			if( itfPrefix != null && srvPrefix != null )
				break;
		}

		sb.append( "\n\t\t<jbi:consumes\n" );
		sb.append( "\t\t\tinterface-name=\"");
		if( itfPrefix == null )
			sb.append(( sameNs ? "generatedNs" : "interfaceNs" ) + ":" + bean.getInterfaceName() + "\"" );
		else
			sb.append( itfPrefix + ":" + bean.getInterfaceName() + "\"" );

		if( bean.getServiceName() != null
					&& bean.getServiceName().trim().length() > 0
					&& bean.getServiceNsUri() != null
					&& bean.getServiceNsUri().trim().length() > 0 ) {

			sb.append( "\n\t\t\tservice-name=\"");
			if( srvPrefix == null )
				sb.append(( sameNs ? "generatedNs" : "serviceNs" ) + ":" + bean.getServiceName() + "\"" );
			else
				sb.append( srvPrefix + ":" + bean.getServiceName() + "\"" );
		}

		if( bean.getEndpointName() != null
					&& bean.getEndpointName().trim().length() > 0
					&& ! AUTO_GENERATE.equals( bean.getEndpointName().trim()))
			sb.append( "\n\t\t\tendpoint-name=\"" + bean.getEndpointName() + "\"" );

		if( itfPrefix == null || srvPrefix == null ) {
			if( sameNs )
				sb.append( "\n\t\t\txmlns:generatedNs=\"" + bean.getInterfaceNsUri() + "\"" );
			else {
				sb.append( "\n\t\t\txmlns:interfaceNs=\"" + bean.getInterfaceNsUri() + "\"" );
				sb.append( "\n\t\t\txmlns:serviceNs=\"" + bean.getServiceNsUri() + "\"" );
			}
		}

		sb.append( ">\n\n" );
		String cdk = bean.getCdkSection();
		if( this.generateComments && cdk != null ) {
			if( cdk.trim().length() > 0 )
				sb.append( "\t\t\t<!-- CDK elements -->\n" );
			else
				sb.append( "\t\t\t<!-- No CDK element -->\n" );
		}

		if( cdk != null )
			sb.append( cdk + "\n" );

		String component = bean.getComponentSection();
		if( this.generateComments && component != null ) {
			if( component.trim().length() > 0 )
				sb.append( "\t\t\t<!-- Component specific elements -->\n" );
			else
				sb.append( "\t\t\t<!-- No component specific element -->\n" );
		}

		if( component != null )
			sb.append( component + "\n" );

		sb.append( "\t\t</jbi:consumes>\n" );
	}


	/**
	 * @return the generateComments (true by default)
	 */
	public boolean isGenerateComments() {
		return this.generateComments;
	}


	/**
	 * @param generateComments the generateComments to set (true by default)
	 */
	public void setGenerateComments( boolean generateComments ) {
		this.generateComments = generateComments;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}


	/**
	 * @return the encoding (UTF-8 by default)
	 */
	public String getEncoding() {
		return this.encoding;
	}


	/**
	 * @param encoding the encoding to set (UTF-8 by default)
	 */
	public void setEncoding( String encoding ) {
		this.encoding = encoding;
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
	 * @param version the version to set
	 */
	public void setVersion( String version ) {
		this.version = version;
	}


	/**
	 * @param defaultIsBc the defaultIsBc to set
	 */
	public void setDefaultIsBc( boolean defaultIsBc ) {
		this.defaultIsBc = defaultIsBc;
	}
}
