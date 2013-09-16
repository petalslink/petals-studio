/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181Provides1x extends CdkProvides5 {

	private String className;


	/**
	 * Constructor.
	 */
	public Jsr181Provides1x() {
		super();
		addNamespace( "jsr181", "http://petals.ow2.org/components/jsr181/version-1" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuffer buf = new StringBuffer();
		buf.append( "\t\t\t<jsr181:class>" + this.className + "</jsr181:class>\n" );
		return buf.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "JSR-181";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean#isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}


	/**
	 * @param className the className to set
	 */
	public void setClassName( String className ) {
		this.className = className;
	}
}
