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

package com.ebmwebsourcing.petals.services.wizards.beans;

/**
 * A bean used in the creation of a Petals SA project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class ServiceImportBean {

	private static int idCounter = 1;

	private final int id;
	private boolean toCreate = true;
	private String projectName, jbiXmlLocation;
	private boolean overwrite = false;


	/**
	 * Constructor.
	 */
	protected ServiceImportBean() {
		this.id = idCounter ++;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName( String projectName ) {
		this.projectName = projectName;
	}

	/**
	 * @return the toCreate
	 */
	public boolean isToCreate() {
		return this.toCreate;
	}

	/**
	 * @param toCreate the toCreate to set
	 */
	public void setToCreate( boolean toCreate ) {
		this.toCreate = toCreate;
	}

	/**
	 * @return the jbiXmlLocation
	 */
	public String getJbiXmlLocation() {
		return this.jbiXmlLocation;
	}

	/**
	 * @param jbiXmlLocation the jbiXmlLocation to set
	 */
	public void setJbiXmlLocation( String jbiXmlLocation ) {
		this.jbiXmlLocation = jbiXmlLocation;
	}

	/**
	 * @return the overwrite
	 */
	public boolean isToOverwrite() {
		return this.overwrite;
	}

	/**
	 * @param overwrite the overwrite to set
	 */
	public void setOverwrite( boolean overwrite ) {
		this.overwrite = overwrite;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.id;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		boolean equals = false;
		if( obj instanceof ServiceImportBean )
			equals = this.id == ((ServiceImportBean) obj).id;

		return equals;
	}
}
