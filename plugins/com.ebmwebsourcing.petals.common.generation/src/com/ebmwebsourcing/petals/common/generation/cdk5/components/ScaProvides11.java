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
 
package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaProvides11 extends CdkProvides5 {

	private String compositeFileLocation;


	/**
	 * Constructor.
	 */
	public ScaProvides11() {
		super();
		addNamespace( "sca", "http://petals.ow2.org/components/sca/version-1" );

	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {
		return "\t\t\t<sca:composite-file>" + this.compositeFileLocation + "</sca:composite-file>";
	}


	/**
	 * @param compositeFileLocation the compositeFileLocation to set
	 */
	public void setCompositeFileLocation( String compositeFileLocation ) {
		this.compositeFileLocation = compositeFileLocation;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "SCA";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}
}
