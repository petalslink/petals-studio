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

package com.ebmwebsourcing.petals.studio.dev.properties.internal.model;

import com.ebmwebsourcing.petals.studio.dev.properties.SupportedTypes;

/**
 * @author Vincent Zurczak - Linagora
 */
public class MyProperty {

	private String name, defaultValue, documentation;
	private boolean required, nullable;
	private SupportedTypes type = SupportedTypes.STRING;

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue( String defaultValue ) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return this.required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired( boolean required ) {
		this.required = required;
	}

	/**
	 * @return the nullable
	 */
	public boolean isNullable() {
		return this.nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable( boolean nullable ) {
		this.nullable = nullable;
	}

	/**
	 * @return the type
	 */
	public SupportedTypes getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType( SupportedTypes type ) {
		this.type = type;
	}

	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return this.documentation;
	}

	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation( String documentation ) {
		this.documentation = documentation;
	}
}
