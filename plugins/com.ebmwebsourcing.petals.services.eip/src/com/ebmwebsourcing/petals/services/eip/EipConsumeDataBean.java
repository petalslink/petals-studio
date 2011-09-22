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

package com.ebmwebsourcing.petals.services.eip;

import java.util.Map;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;

/**
 * The bean used to store specific data about a consumed project in the EIP plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConsumeDataBean {

	/** The service name. */
	private String serviceName;

	/** The interface name. */
	private String interfaceName;

	/** The end-point name. */
	private String endpointName;

	/** EIP condition. */
	private String condition;

	/** The service name space URI. */
	private String serviceNamespace;

	/** The interface name space URI. */
	private String interfaceNamespace;

	/**
	 * The consumed end-point bean (can be null).
	 */
	private EndpointBean endpointBean;

	/**
	 * True if this bean must have a condition, false otherwise.
	 */
	private boolean mustHaveCondition;

	/**
	 * Set to true means the condition is a XPath condition (false for operation).
	 */
	private boolean xpathCondition;

	/**
	 * The invoked operation.
	 */
	private QName invokedOperation;;

	/**
	 * The invocation MEP.
	 */
	private Mep mep;

	/** jbi.xml comment for this bean. */
	private String comment;

	/** */
	private final String suType;



	/**
	 * @param suType
	 */
	public EipConsumeDataBean( String suType ) {
		this.suType = suType;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return this.serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return this.interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the endpointName
	 */
	public String getEndpointName() {
		return this.endpointName;
	}

	/**
	 * @param endpointName the endpointName to set
	 */
	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}

	/**
	 * @return the invokedOperation
	 */
	public QName getInvokedOperation() {
		return this.invokedOperation;
	}

	/**
	 * @param invokedOperation the invokedOperation to set
	 */
	public void setInvokedOperation( QName invokedOperation ) {
		this.invokedOperation = invokedOperation;
	}

	/**
	 * @return the mep
	 */
	public Mep getMep() {
		return this.mep;
	}

	/**
	 * @param mep the mep to set
	 */
	public void setMep( Mep mep ) {
		this.mep = mep;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return this.condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the serviceNamespace
	 */
	public String getServiceNamespace() {
		return this.serviceNamespace;
	}

	/**
	 * @param serviceNamespace the serviceNamespace to set
	 */
	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	/**
	 * @return the interfaceNamespace
	 */
	public String getInterfaceNamespace() {
		return this.interfaceNamespace;
	}

	/**
	 * @param interfaceNamespace the interfaceNamespace to set
	 */
	public void setInterfaceNamespace(String interfaceNamespace) {
		this.interfaceNamespace = interfaceNamespace;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.Element2Map
	 * #getFilesToImport()
	 */
	public Map<String, String> getFilesToImport() {
		return null;
	}

	/**
	 * Duplicates the bean.
	 * @return
	 */
	public EipConsumeDataBean duplicate() {

		EipConsumeDataBean clone = new EipConsumeDataBean( this.suType );
		clone.comment = this.comment;

		clone.interfaceName = this.interfaceName;
		clone.interfaceNamespace = this.interfaceNamespace;
		clone.serviceName = this.serviceName;
		clone.serviceNamespace = this.serviceNamespace;
		clone.endpointName = this.endpointName;

		clone.condition = this.condition;
		clone.xpathCondition = this.xpathCondition;
		clone.mustHaveCondition = this.mustHaveCondition;

		clone.invokedOperation = this.invokedOperation;
		clone.mep = this.mep;
		clone.endpointBean = this.endpointBean;

		return clone;
	}

	/**
	 * @return the conditionActivated
	 */
	public boolean hasCondition() {
		return ! StringUtils.isEmpty( getCondition());
	}

	public String getComment() {
		// Comment defined.
		if( this.comment != null )
			return this.comment;

		// Generate a comment.
		String comment = "";
		if( mustHaveCondition ()) {
			comment += "( test = ";
			comment += getCondition() != null ? getCondition() : "";
			comment += " )";
		}

		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the xpathCondition
	 */
	public boolean isXpathCondition() {
		return this.xpathCondition;
	}


	/**
	 * @return the endpointBean
	 */
	public EndpointBean getEndpointBean() {
		return this.endpointBean;
	}


	/**
	 * @param endpointBean the endpointBean to set
	 */
	public void setEndpointBean( EndpointBean endpointBean ) {
		this.endpointBean = endpointBean;
	}

	/**
	 * @param xpathCondition the xpathCondition to set
	 */
	public void setXpathCondition( boolean xpathCondition ) {
		this.xpathCondition = xpathCondition;
	}

	/**
	 * @return the mustHaveCondition
	 */
	public boolean mustHaveCondition() {
		return this.mustHaveCondition;
	}

	/**
	 * @param mustHaveCondition the mustHaveCondition to set
	 */
	public void setMustHaveCondition( boolean mustHaveCondition ) {
		this.mustHaveCondition = mustHaveCondition;
	}
}
