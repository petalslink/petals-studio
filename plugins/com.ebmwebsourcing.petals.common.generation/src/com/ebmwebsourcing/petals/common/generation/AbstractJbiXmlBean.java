/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A bean used in the generation of the JBI descriptor for the SCA component of Petals.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractJbiXmlBean {

	private String interfaceName, serviceName, endpointName;
	private String interfaceNsUri, serviceNsUri;
	private boolean provides = true;
	private boolean bc = true;


	/**
	 * Name spaces are sorted by natural (key) prefix order.
	 */
	final Map<String,String> namespaces = new TreeMap<String,String>();
	final Map<String,File> fileNameToFile = new HashMap<String,File>();



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
	 * @return the interfaceNsUri
	 */
	public String getInterfaceNsUri() {
		return this.interfaceNsUri;
	}

	/**
	 * @param interfaceNsUri the interfaceNsUri to set
	 */
	public void setInterfaceNamespace( String interfaceNsUri ) {
		this.interfaceNsUri = interfaceNsUri;
	}

	/**
	 * @return the serviceNsUri
	 */
	public String getServiceNsUri() {
		return this.serviceNsUri;
	}

	/**
	 * @param serviceNsUri the serviceNsUri to set
	 */
	public void setServiceNamespace( String serviceNsUri ) {
		this.serviceNsUri = serviceNsUri;
	}

	/**
	 * @return the provides (true by default)
	 */
	public boolean isProvides() {
		return this.provides;
	}

	/**
	 * @param provides the provides to set
	 */
	public void setProvides( boolean provides ) {
		this.provides = provides;
	}

	/**
	 * Adds a name space declaration in the jbi.xml to generate.
	 * @param prefix
	 * @param uri
	 */
	public void addNamespace( String prefix, String uri ) {
		this.namespaces.put( prefix, uri );
	}

	/**
	 * @return the namespaces
	 */
	public Map<String, String> getNamespaces() {
		return this.namespaces;
	}

	/**
	 * Gets the CDK section as a string (which includes formatting).
	 * @return null to not display anything in the CDK section, including comments, or a string otherwise
	 */
	public abstract String getCdkSection();


	/**
	 * Gets the component section as a string (which includes formatting).
	 * @return null to not display anything in the component section, including comments, or a string otherwise
	 */
	public abstract String getComponentSection();


	/**
	 * @return the SU type (e.g. SOAP, EJB).
	 */
	public abstract String getSuType();


	/**
	 * @see java.util.Map#clear()
	 */
	public void clearFilesToImport() {
		this.fileNameToFile.clear();
	}

	/**
	 * @param zipKey
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public File getFileToImport( String zipKey ) {
		return this.fileNameToFile.get( zipKey );
	}

	/**
	 * @param zipKey
	 * @param file
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public File putFileToImport( String zipKey, File file ) {
		return this.fileNameToFile.put( zipKey, file );
	}

	/**
	 * @return
	 */
	public Map<String, File> getFilesToImport() {
		return this.fileNameToFile;
	}

	/**
	 * @return the bc
	 */
	public boolean isBc() {
		return this.bc;
	}

	/**
	 * @param bc the bc to set
	 */
	public void setBc( boolean bc ) {
		this.bc = bc;
	}
}
