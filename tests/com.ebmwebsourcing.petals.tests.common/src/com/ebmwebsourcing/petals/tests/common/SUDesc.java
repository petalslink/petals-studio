/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.tests.common;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SUDesc {

	private String serviceName;
	private String endpoint;
	private String projectName;
	private String wsdlName;

	public String getServiceName() {
		return this.serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getEndpoint() {
		return this.endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getProjectName() {
		return this.projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setWsdlName(String wsdlName) {
		this.wsdlName = wsdlName;
	}
	public String getWsdlName() {
		return this.wsdlName;
	}
}
