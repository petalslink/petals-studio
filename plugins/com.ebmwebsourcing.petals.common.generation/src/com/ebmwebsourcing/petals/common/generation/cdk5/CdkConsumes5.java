/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation.cdk5;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean;
import com.ebmwebsourcing.petals.common.generation.Mep;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class CdkConsumes5 extends AbstractJbiXmlBean {

	QName operation;
	String timeout;
	Mep mep;


	/**
	 * Constructor.
	 * <p>
	 * Must be called by sub-classes.
	 * </p>
	 */
	public CdkConsumes5() {
		super();
		addNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-5" );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.jbi.core.jbixml.JbiBeanDelegate#getCdkSection()
	 */
	@Override
	public String getCdkSection() {

		String result = "";
		if( this.operation != null ) {
			String nsDecl = "";
			String opDecl = this.operation.getLocalPart();
			if( this.operation.getNamespaceURI() != null
						&& ! XMLConstants.DEFAULT_NS_PREFIX.equals( this.operation.getNamespaceURI())) {
				nsDecl = " xmlns:op=\"" + this.operation.getNamespaceURI() + "\"";
				opDecl = "op:" + opDecl;
			}

			result += "\t\t\t<petalsCDK:operation" + nsDecl + ">" + opDecl + "</petalsCDK:operation>\n";
		}

		if( this.timeout != null && ! "0".equals( this.timeout.trim()))
			result += "\t\t\t<petalsCDK:timeout>" + this.timeout + "</petalsCDK:timeout>\n";

		if( this.mep != null && this.mep != Mep.UNKNOWN )
			result += "\t\t\t<petalsCDK:mep>" + this.mep + "</petalsCDK:mep>\n";
		else
			result += "\t\t\t<petalsCDK:mep xsi:nil=\"true\" />\n";

		return result;
	}


	/**
	 * @param operation the operation to set
	 */
	public void setOperation( QName operation ) {
		this.operation = operation;
	}


	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout( String timeout ) {
		this.timeout = timeout;
	}


	/**
	 * @param mep the MEP to set
	 */
	public void setMep( Mep mep ) {
		this.mep = mep;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.jbi.core.jbixml.JbiBeanDelegate#isProvides()
	 */
	@Override
	public boolean isProvides() {
		return false;
	}
}
