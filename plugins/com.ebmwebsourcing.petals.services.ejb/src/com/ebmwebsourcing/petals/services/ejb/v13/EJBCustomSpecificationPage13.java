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

package com.ebmwebsourcing.petals.services.ejb.v13;

import com.ebmwebsourcing.petals.services.ejb.v12.EJBCustomSpecificationPage12;

/**
 * Located after the VERSION page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EJBCustomSpecificationPage13 extends EJBCustomSpecificationPage12 {

	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public EJBCustomSpecificationPage13() {
		super( "EJBCustomSpecificationPage13" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setBasicFields(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {

		super.setBasicFields( suType, suTypeVersion, pluginId );
		registerNamespace( "ejb", "http://petals.ow2.org/components/ejb/version-1" );
		registerNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-5" );
	}
}
