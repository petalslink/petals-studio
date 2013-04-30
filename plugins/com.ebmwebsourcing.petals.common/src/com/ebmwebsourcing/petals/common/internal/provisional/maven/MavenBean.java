/****************************************************************************
 * 
 * Copyright (c) 2008-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.maven;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A bean with information used to generate pom.xml files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenBean {

	private String artifactId, groupId, name, description, version;
	private String parentArtifactId, parentGroupId, parentVersion = "";
	private String componentName, componentVersion;

	public final List<MavenBean> dependencies = new ArrayList<MavenBean> ();


	/**
	 * Constructor.
	 */
	public MavenBean() {

		// Group ID
		this.groupId = PreferencesManager.getMavenGroupId();
		if( StringUtils.isEmpty( this.groupId ))
			this.groupId = PetalsConstants.DEFAULT_GROUP_ID;

		// Version
		this.version = PetalsConstants.DEFAULT_ARTIFACT_VERSION;
	}


	/**
	 * Equality if they have the same class, same artifactId, groupId and version.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if( obj == null || ! obj.getClass().equals( getClass()))
			return false;

		MavenBean bean = (MavenBean) obj;

		// Artifact ID
		if( this.artifactId == null ) {
			if( bean.artifactId != null )
				return false;
		}
		else if( !this.artifactId.equals( bean.artifactId ))
			return false;

		// Group ID
		if( this.groupId == null ) {
			if( bean.groupId != null )
				return false;
		}
		else if( !this.groupId.equals( bean.groupId ))
			return false;

		// Version
		if( this.version == null ) {
			if( bean.version != null )
				return false;
		}
		else if( !this.version.equals( bean.version ))
			return false;

		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int aId = this.artifactId == null || this.artifactId.length() == 0
		? 13 : this.artifactId.charAt( 0 ) * this.artifactId.length();
		int gId = this.groupId == null || this.groupId.length() == 0
		? 13 : this.groupId.charAt( 0 ) * this.groupId.length();
		int vId = this.version == null || this.version.length() == 0
		? 13 : this.version.charAt( 0 ) * this.version.length();

		return aId * vId * gId;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getArtifactId();
	}


	/**
	 * @return the artifactId
	 */
	public final String getArtifactId() {
		return this.artifactId != null ? this.artifactId : "";
	}


	/**
	 * @param artifactId the artifactId to set
	 */
	public final void setArtifactId( String artifactId ) {
		this.artifactId = artifactId;
	}


	/**
	 * @return the groupId
	 */
	public final String getGroupId() {
		return this.groupId;
	}


	/**
	 * @param groupId the groupId to set
	 */
	public final void setGroupId( String groupId ) {
		this.groupId = groupId;
	}


	/**
	 * @return the name
	 */
	public final String getName() {
		return this.name != null ? this.name : "";
	}


	/**
	 * @param name the name to set
	 */
	public final void setName( String name ) {
		this.name = name;
	}


	/**
	 * @return the description
	 */
	public final String getDescription() {
		return this.description != null ? this.description : "";
	}


	/**
	 * @param description the description to set
	 */
	public final void setDescription( String description ) {
		this.description = description;
	}


	/**
	 * @return the version
	 */
	public final String getVersion() {
		return this.version != null ? this.version : "";
	}


	/**
	 * @param version the version to set
	 */
	public final void setVersion( String version ) {
		this.version = version;
	}


	/**
	 * @return the petalsMavenPluginVersion
	 */
	public final String getPetalsMavenPluginVersion() {

		String version = PreferencesManager.getMavenPluginVersion();
		if( StringUtils.isEmpty( version ))
			version = PetalsConstants.DEFAULT_PETALS_MAVEN_PLUGIN;

		return version;
	}


	/**
	 * @return the parentArtifactId
	 */
	public final String getParentArtifactId() {
		return this.parentArtifactId != null ? this.parentArtifactId : "";
	}


	/**
	 * @param parentArtifactId the parentArtifactId to set
	 */
	public final void setParentArtifactId( String parentArtifactId ) {
		this.parentArtifactId = parentArtifactId;
	}


	/**
	 * @return the parentGroupId
	 */
	public final String getParentGroupId() {
		return this.parentGroupId != null ? this.parentGroupId : "";
	}


	/**
	 * @param parentGroupId the parentGroupId to set
	 */
	public final void setParentGroupId( String parentGroupId ) {
		this.parentGroupId = parentGroupId;
	}


	/**
	 * @return the parentVersion
	 */
	public final String getParentVersion() {
		return this.parentVersion != null ? this.parentVersion : "";
	}


	/**
	 * @param parentVersion the parentVersion to set
	 */
	public final void setParentVersion( String parentVersion ) {
		this.parentVersion = parentVersion;
	}


	/**
	 * @return the componentName
	 */
	public final String getComponentName() {
		return this.componentName != null ? this.componentName : "";
	}


	/**
	 * @param componentName the componentName to set
	 */
	public final void setComponentName( String componentName ) {
		this.componentName = componentName;
	}


	/**
	 * @return the componentVersion
	 */
	public final String getComponentVersion() {
		return this.componentVersion != null ? this.componentVersion : "";
	}


	/**
	 * @param componentVersion the componentVersion to set
	 */
	public final void setComponentVersion( String componentVersion ) {
		this.componentVersion = componentVersion;
	}
}
