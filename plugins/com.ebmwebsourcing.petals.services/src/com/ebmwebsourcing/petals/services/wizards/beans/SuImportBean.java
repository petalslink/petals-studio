/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.wizards.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * A bean used in the creation of a Petals SU project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuImportBean extends ServiceImportBean {

	private final Map<String,Object> keyToObject;

	private String projectName;
	private String componentName, componentVersion;
	private String suType;
	private boolean consume = false;
	private String[] supportedVersions;


	/**
	 * Constructor.
	 */
	public SuImportBean() {
		super();
		this.keyToObject = new HashMap<String,Object> ();
	}

	/**
	 * @return the projectName
	 */
	@Override
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	@Override
	public void setProjectName( String projectName ) {
		this.projectName = projectName;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return this.componentName != null ? this.componentName : "";
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
		return this.componentVersion != null ? this.componentVersion : "";
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
		return this.suType != null ? this.suType : "";
	}

	/**
	 * @param suType the suType to set
	 */
	public void setSuType( String suType ) {
		this.suType = suType;
	}

	/**
	 * @return the supportedVersions
	 */
	public String[] getSupportedVersions() {
		return this.supportedVersions;
	}

	/**
	 * @param supportedVersions the supportedVersions to set
	 */
	public void setSupportedVersions( String[] supportedVersions ) {
		this.supportedVersions = supportedVersions;
	}

	/**
	 * @return the consume
	 */
	public boolean isConsume() {
		return this.consume;
	}

	/**
	 * @param consume the consume to set
	 */
	public void setConsume( boolean consume ) {
		this.consume = consume;
	}

	/**
	 * @return the keyToObject
	 */
	public Map<String,Object> getKeyToObject() {
		return this.keyToObject;
	}
}
