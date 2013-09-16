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
 
package com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipProvides25ScatterGather extends CdkProvides5 {

	private boolean faultRobust, exceptionRobust;


	/**
	 * Constructor.
	 */
	public EipProvides25ScatterGather() {
		super();
		addNamespace( "eip", "http://petals.ow2.org/components/eip/version-2" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuffer buf = new StringBuffer();
		buf.append( "\t\t\t<eip:eip>scatter-gather</eip:eip>\n" );
		buf.append( "\t\t\t<eip:fault-robust>" + this.faultRobust + "</eip:fault-robust>\n" );
		buf.append( "\t\t\t<eip:exception-robust>" + this.exceptionRobust + "</eip:exception-robust>" );

		return buf.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public final String getSuType() {
		return "EIP";
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


	/**
	 * @param faultRobust the faultRobust to set
	 */
	public void setFaultRobust( boolean faultRobust ) {
		this.faultRobust = faultRobust;
	}


	/**
	 * @param exceptionRobust the exceptionRobust to set
	 */
	public void setExceptionRobust( boolean exceptionRobust ) {
		this.exceptionRobust = exceptionRobust;
	}
}
