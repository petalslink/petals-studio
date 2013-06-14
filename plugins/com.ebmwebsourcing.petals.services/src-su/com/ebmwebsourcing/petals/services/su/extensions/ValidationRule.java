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
package com.ebmwebsourcing.petals.services.su.extensions;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A class that defines a validation rule for jbi.xml parameters.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationRule {

	/**
	 * The constant to use to ignore name space URI comparisons.
	 */
	public final static String ANY_NAMESPACE = "AnyNamespace";


	/**
	 * FILE: indicates that the existence of a file in the resource directory must be checked.
	 * XPATH: indicates the syntactic correctness of a XPath expression must be checked.
	 * JAVA: indicates this class must exist in the class path of the project.
	 * URI: indicates the URI must be syntactically correct.
	 */
	public static enum ValidationRuleType {
		FILE, XPATH, JAVA, URI;
	}

	private final String namespaceUri;
	private final String elementName;
	private final ValidationRuleType ruleType;
	private final boolean nullAccepted;
	private final boolean emptyValueAccepted;
	private String fileExtension;


	/**
	 * Constructor.
	 * @param namespaceUri
	 * @param elementName
	 * @param ruleType
	 * @param nullAccepted
	 * @param emptyValueAccepted
	 */
	public ValidationRule(
				String namespaceUri,
				String elementName,
				ValidationRuleType ruleType,
				boolean nullAccepted,
				boolean emptyValueAccepted ) {

		this.namespaceUri = namespaceUri;
		this.elementName = elementName;
		this.ruleType = ruleType;
		this.nullAccepted = nullAccepted;
		this.emptyValueAccepted = emptyValueAccepted;
	}

	/**
	 * @return the namespaceUri
	 */
	public String getNamespaceUri() {
		return this.namespaceUri;
	}

	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return this.elementName;
	}

	/**
	 * @return the ruleType
	 */
	public ValidationRuleType getRuleType() {
		return this.ruleType;
	}

	/**
	 * @return the nullAccepted
	 */
	public boolean isNullAccepted() {
		return this.nullAccepted;
	}

	/**
	 * @return the emptyValueAccepted
	 */
	public boolean isEmptyValueAccepted() {
		return this.emptyValueAccepted;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int i = StringUtils.isEmpty( this.namespaceUri ) ? 17 : this.namespaceUri.charAt( 0 );
		int j = StringUtils.isEmpty( this.elementName ) ? 13 : this.elementName.charAt( 0 );
		return (i + 37) * j;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		boolean result = false;
		if( obj instanceof ValidationRule ) {
			ValidationRule vr = (ValidationRule) obj;
			if( hasSameQName( vr.namespaceUri, vr.elementName ))
				result = this.ruleType == vr.ruleType;
		}

		return result;
	}

	/**
	 * @param namespaceUri a name space URI
	 * @param elementName an element name
	 * @return true if this instance has the same values as the parameters, false otherwise
	 */
	public boolean hasSameQName( String namespaceUri, String elementName ) {

		boolean result = false;
		if( this.namespaceUri == null && namespaceUri == null
					|| this.namespaceUri != null && this.namespaceUri.equals( namespaceUri )
					|| ANY_NAMESPACE.equals( this.namespaceUri )) {

			if( this.elementName == null && elementName == null
						|| this.elementName != null && this.elementName.equals( elementName )) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * @return the fileExtension (null for any extension)
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * @param fileExtension the file extension to set (null for any extension)
	 */
	public void setFileExtension( String fileExtension ) {
		this.fileExtension = fileExtension;
	}
}
