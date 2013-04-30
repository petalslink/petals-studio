/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
