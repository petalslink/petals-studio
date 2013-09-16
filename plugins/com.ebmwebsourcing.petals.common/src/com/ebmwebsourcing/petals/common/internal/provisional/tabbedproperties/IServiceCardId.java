/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties;

import javax.xml.namespace.QName;

/**
 * An interface to define the properties of a service's card ID.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IServiceCardId {

	/**
	 * @return the service name
	 */
	public QName getServiceName();

	/**
	 * @return the interface name
	 */
	public QName getInterfaceName();

	/**
	 * @return the end-point name
	 */
	public String getEndpointName();

	/**
	 * @return the location of the WSDL (can be null, absolute or relative URL if it is a SU)
	 */
	public String getWsdlLocation();

	/**
	 * @return the kind of implementation used by this service (e.g. EJB, SOAP...)
	 */
	public String getImplementationType();
}
