/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.xslt;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltDescription23 extends XsltDescription {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentVersion()
	 */
	@Override
	public String getComponentVersion() {
		return "2.3";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProxy()
	 */
	@Override
	public boolean isProxy() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getDefaultOperations()
	 */
	@Override
	public Map<QName,Mep> getDefaultOperations() {

		HashMap<QName,Mep> result = new HashMap<QName,Mep> ();
		result.put( new QName( "http://petals.ow2.org/components/xslt/version-2", "transform" ), Mep.IN_OUT );
		result.put( new QName( "http://petals.ow2.org/components/xslt/version-2", "transformToMtomAttachment" ), Mep.IN_OUT );

		return result;
	}
}
