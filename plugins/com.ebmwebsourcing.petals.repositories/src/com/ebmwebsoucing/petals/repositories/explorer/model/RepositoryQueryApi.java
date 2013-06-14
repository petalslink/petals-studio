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
package com.ebmwebsoucing.petals.repositories.explorer.model;

/**
 * A list of API to query service repositories.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum RepositoryQueryApi {

	uddi_inquiry_v2;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		String result;
		switch( this ) {
		case uddi_inquiry_v2:
			result = "UDDI Inquiry v2";
			break;
		default:
			result = "";
		}

		return result;
	};
}
