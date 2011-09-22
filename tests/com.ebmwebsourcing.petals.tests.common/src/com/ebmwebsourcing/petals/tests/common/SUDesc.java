package com.ebmwebsourcing.petals.tests.common;

public class SUDesc {

	private String serviceName;
	private String endpoint;
	private String projectName;
	private String wsdlName;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getProjectName() {
		return projectName;
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
