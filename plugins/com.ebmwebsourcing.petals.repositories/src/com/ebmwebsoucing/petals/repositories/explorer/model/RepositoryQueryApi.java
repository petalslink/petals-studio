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
