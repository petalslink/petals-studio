/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v44;

import com.ebmwebsourcing.petals.services.soap.SoapDescription;


/**
 * @author Victor Noel - Linagora
 */
public class SoapDescription44 extends SoapDescription {

	@Override
	public String getComponentVersion() {
		return "4.4.2";
	}

	@Override
	public boolean isProvide() {
		return true;
	}

	@Override
	public boolean isConsume() {
		return true;
	}

	@Override
	public boolean isProxy() {
		return false;
	}
}
