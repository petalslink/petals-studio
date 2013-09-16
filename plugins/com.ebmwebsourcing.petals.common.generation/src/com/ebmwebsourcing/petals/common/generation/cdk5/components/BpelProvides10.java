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
public class BpelProvides10 extends CdkProvides5 {

	private String processName;
	private int poolSize = 0;


	/**
	 * Constructor.
	 */
	public BpelProvides10() {
		super();
		addNamespace( "bpel", "http://petals.ow2.org/components/petals-bpel-engine/version-1" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuffer buf = new StringBuffer();
		buf.append( "\t\t\t<bpel:bpel>" + this.processName + "</bpel:bpel>\n" );
		buf.append( "\t\t\t<bpel:poolsize>" + this.poolSize + "</bpel:poolsize>\n" );
		return buf.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "BPEL";
	}


	/**
	 * @param processName the processName to set
	 */
	public void setProcessName( String processName ) {
		this.processName = processName;
	}


	/**
	 * @param poolSize the poolSize to set
	 */
	public void setPoolSize( int poolSize ) {
		this.poolSize = poolSize;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}
}
