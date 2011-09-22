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

package com.ebmwebsourcing.petals.services.su.wizards.generation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SuBean;

/**
 * The bean used in the Eclipse plug-ins by to store pages data.<br  />
 * These additional information are not used in the library <b>commons-jbi</b>.<br />
 * They are retrieved by the wizard from the pages and used to generate other things
 * than <i>classical</i> SUs (e.g. a project in the work space). They may also be used in
 * plug-in components which use their own template for the SU jbi.xml file.
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EclipseSuBean extends SuBean {

	/** The contributor plug-in ID. */
	private String contributorId;
	/** The project name. */
	private String projectName;
	/** The project location. */
	private URI projectLocation;
	/** The URL of the WSDL to import. */
	private String wsdlUrl;
	/** */
	private boolean createJavaProject;
	private String realEndpointName;

	/**
	 * The JBI page is supposed to give the WSDL URL to the bean used for the generation.
	 * However, in some cases (e.g. XQuare), the WSDL is not imported but created.
	 * Thus, we would only give the path of the WSDL in the created project. And not an URL to import.
	 * This element is here for that.
	 *
	 * Setting its value will print it in the jbi.xml for the CDK WSDL element.
	 * If you set this field to an non-null value, then you should ensure that wsdlUrl is null.
	 */
	private String createdWsdlMarkupValue;


	/**
	 * Data that can be used to store various objects.
	 * By default, this field is not used in the completion, but it could be in a contributed jbi.xml formatter.
	 */
	final public Map<Object, Object> customObjects = new HashMap<Object, Object> ();



	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the contributorId
	 */
	public String getContributorId() {
		return this.contributorId;
	}

	/**
	 * @param contributorId the contributorId to set
	 */
	public void setContributorId(String contributorId) {
		this.contributorId = contributorId;
	}

	/**
	 * @return the wsdlUrl
	 */
	public String getWsdlUrl() {
		return this.wsdlUrl;
	}

	/**
	 * @param wsdlUrl the wsdlUrl to set
	 */
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	/**
	 * @return the createdWsdlMarkupValue
	 */
	public String getCreatedWsdlMarkupValue() {
		return this.createdWsdlMarkupValue;
	}

	/**
	 * @param createdWsdlMarkupValue the wsdlMarkupValue to set
	 */
	public void setCreatedWsdlMarkupValue(String createdWsdlMarkupValue) {
		this.createdWsdlMarkupValue = createdWsdlMarkupValue;
	}

	/**
	 * @return the projectLocation
	 */
	public URI getProjectLocation() {
		return this.projectLocation;
	}

	/**
	 * @param projectLocation the projectLocation to set
	 */
	public void setProjectLocation( URI projectLocation ) {
		this.projectLocation = projectLocation;
	}

	/**
	 * @return the createJavaProject
	 */
	public boolean isCreateJavaProject() {
		return this.createJavaProject;
	}

	/**
	 * @param createJavaProject the createJavaProject to set
	 */
	public void setCreateJavaProject( boolean createJavaProject ) {
		this.createJavaProject = createJavaProject;
	}


	/**
	 * @return the realEndpointName
	 */
	public String getRealEndpointName() {
		return this.realEndpointName;
	}


	/**
	 * @param realEndpointName the realEndpointName to set
	 */
	public void setRealEndpointName( String realEndpointName ) {
		this.realEndpointName = realEndpointName;
	}
}
