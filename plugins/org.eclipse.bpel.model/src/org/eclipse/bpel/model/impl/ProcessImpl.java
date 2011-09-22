/**
 * <copyright>
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 * </copyright>
 */
package org.eclipse.bpel.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.CorrelationSets;
import org.eclipse.bpel.model.EventHandler;
import org.eclipse.bpel.model.Extensions;
import org.eclipse.bpel.model.FaultHandler;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.MessageExchanges;
import org.eclipse.bpel.model.PartnerLinks;
import org.eclipse.bpel.model.Variables;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getQueryLanguage <em>Query Language</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getSuppressJoinFailure <em>Suppress Join Failure</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getVariableAccessSerializable <em>Variable Access Serializable</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getPartnerLinks <em>Partner Links</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getFaultHandlers <em>Fault Handlers</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getEventHandlers <em>Event Handlers</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getCorrelationSets <em>Correlation Sets</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getExitOnStandardFault <em>Exit On Standard Fault</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getMessageExchanges <em>Message Exchanges</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ProcessImpl#getAbstractProcessProfile <em>Abstract Process Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessImpl extends ExtensibleElementImpl implements
org.eclipse.bpel.model.Process {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
	protected String targetNamespace = TARGET_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getQueryLanguage() <em>Query Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_LANGUAGE_EDEFAULT = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0";

	/**
	 * The cached value of the '{@link #getQueryLanguage() <em>Query Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLanguage()
	 * @generated
	 * @ordered
	 */
	protected String queryLanguage = QUERY_LANGUAGE_EDEFAULT;

	/**
	 * This is true if the Query Language attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean queryLanguageESet;

	/**
	 * The default value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_LANGUAGE_EDEFAULT = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0";

	/**
	 * The cached value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected String expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;

	/**
	 * This is true if the Expression Language attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean expressionLanguageESet;

	/**
	 * The default value of the '{@link #getSuppressJoinFailure() <em>Suppress Join Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuppressJoinFailure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SUPPRESS_JOIN_FAILURE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getSuppressJoinFailure() <em>Suppress Join Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuppressJoinFailure()
	 * @generated
	 * @ordered
	 */
	protected Boolean suppressJoinFailure = SUPPRESS_JOIN_FAILURE_EDEFAULT;

	/**
	 * This is true if the Suppress Join Failure attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean suppressJoinFailureESet;

	/**
	 * The default value of the '{@link #getVariableAccessSerializable() <em>Variable Access Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableAccessSerializable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VARIABLE_ACCESS_SERIALIZABLE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getVariableAccessSerializable() <em>Variable Access Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableAccessSerializable()
	 * @generated
	 * @ordered
	 */
	protected Boolean variableAccessSerializable = VARIABLE_ACCESS_SERIALIZABLE_EDEFAULT;

	/**
	 * This is true if the Variable Access Serializable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean variableAccessSerializableESet;

	/**
	 * The cached value of the '{@link #getPartnerLinks() <em>Partner Links</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartnerLinks()
	 * @generated
	 * @ordered
	 */
	protected PartnerLinks partnerLinks;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected Variables variables;

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity;

	/**
	 * The cached value of the '{@link #getFaultHandlers() <em>Fault Handlers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultHandlers()
	 * @generated
	 * @ordered
	 */
	protected FaultHandler faultHandlers;

	/**
	 * The cached value of the '{@link #getEventHandlers() <em>Event Handlers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventHandlers()
	 * @generated
	 * @ordered
	 */
	protected EventHandler eventHandlers;

	/**
	 * The cached value of the '{@link #getCorrelationSets() <em>Correlation Sets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationSets()
	 * @generated
	 * @ordered
	 */
	protected CorrelationSets correlationSets;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected Extensions extensions;

	/**
	 * The default value of the '{@link #getExitOnStandardFault() <em>Exit On Standard Fault</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExitOnStandardFault()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean EXIT_ON_STANDARD_FAULT_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getExitOnStandardFault() <em>Exit On Standard Fault</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExitOnStandardFault()
	 * @generated
	 * @ordered
	 */
	protected Boolean exitOnStandardFault = EXIT_ON_STANDARD_FAULT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMessageExchanges() <em>Message Exchanges</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageExchanges()
	 * @generated
	 * @ordered
	 */
	protected MessageExchanges messageExchanges;

	/**
	 * The default value of the '{@link #getAbstractProcessProfile() <em>Abstract Process Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractProcessProfile()
	 * @generated
	 * @ordered
	 */
	protected static final String ABSTRACT_PROCESS_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbstractProcessProfile() <em>Abstract Process Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractProcessProfile()
	 * @generated
	 * @ordered
	 */
	protected String abstractProcessProfile = ABSTRACT_PROCESS_PROFILE_EDEFAULT;

	/**
	 * This is true if the Abstract Process Profile attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean abstractProcessProfileESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BPELPackage.Literals.PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setName(String newName) {
		String oldName = this.name;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this, BPELConstants.AT_NAME,
						newName);
		}
		this.name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__NAME, oldName, this.name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetNamespace() {
		return this.targetNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setTargetNamespace(String newTargetNamespace) {
		String oldTargetNamespace = this.targetNamespace;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_TARGET_NAMESPACE, newTargetNamespace);
		}
		this.targetNamespace = newTargetNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__TARGET_NAMESPACE, oldTargetNamespace,
						this.targetNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQueryLanguage() {
		return this.queryLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setQueryLanguage(String newQueryLanguage) {
		String oldQueryLanguage = this.queryLanguage;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_QUERYLANGUAGE, newQueryLanguage);
		}
		this.queryLanguage = newQueryLanguage;
		boolean oldQueryLanguageESet = this.queryLanguageESet;
		this.queryLanguageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__QUERY_LANGUAGE, oldQueryLanguage,
						this.queryLanguage, !oldQueryLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetQueryLanguage() {
		String oldQueryLanguage = this.queryLanguage;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_QUERYLANGUAGE, (String) null);
		}
		boolean oldQueryLanguageESet = this.queryLanguageESet;
		this.queryLanguage = QUERY_LANGUAGE_EDEFAULT;
		this.queryLanguageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.PROCESS__QUERY_LANGUAGE, oldQueryLanguage,
						QUERY_LANGUAGE_EDEFAULT, oldQueryLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetQueryLanguage() {
		return this.queryLanguageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpressionLanguage() {
		return this.expressionLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setExpressionLanguage(String newExpressionLanguage) {
		String oldExpressionLanguage = this.expressionLanguage;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_EXPRESSIONLANGUAGE, newExpressionLanguage);
		}
		this.expressionLanguage = newExpressionLanguage;
		boolean oldExpressionLanguageESet = this.expressionLanguageESet;
		this.expressionLanguageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__EXPRESSION_LANGUAGE,
						oldExpressionLanguage, this.expressionLanguage,
						!oldExpressionLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetExpressionLanguage() {
		String oldExpressionLanguage = this.expressionLanguage;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_EXPRESSIONLANGUAGE, (String) null);
		}
		boolean oldExpressionLanguageESet = this.expressionLanguageESet;
		this.expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;
		this.expressionLanguageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.PROCESS__EXPRESSION_LANGUAGE,
						oldExpressionLanguage, EXPRESSION_LANGUAGE_EDEFAULT,
						oldExpressionLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExpressionLanguage() {
		return this.expressionLanguageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSuppressJoinFailure() {
		return this.suppressJoinFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setSuppressJoinFailure(Boolean newSuppressJoinFailure) {
		Boolean oldSuppressJoinFailure = this.suppressJoinFailure;
		this.suppressJoinFailure = newSuppressJoinFailure;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_SUPPRESS_JOIN_FAILURE, BPELUtils
						.boolean2XML(newSuppressJoinFailure));
		}
		boolean oldSuppressJoinFailureESet = this.suppressJoinFailureESet;
		this.suppressJoinFailureESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE,
						oldSuppressJoinFailure, this.suppressJoinFailure,
						!oldSuppressJoinFailureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetSuppressJoinFailure() {
		Boolean oldSuppressJoinFailure = this.suppressJoinFailure;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_SUPPRESS_JOIN_FAILURE, (String) null);
		}
		boolean oldSuppressJoinFailureESet = this.suppressJoinFailureESet;
		this.suppressJoinFailure = SUPPRESS_JOIN_FAILURE_EDEFAULT;
		this.suppressJoinFailureESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE,
						oldSuppressJoinFailure, SUPPRESS_JOIN_FAILURE_EDEFAULT,
						oldSuppressJoinFailureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSuppressJoinFailure() {
		return this.suppressJoinFailureESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVariableAccessSerializable() {
		return this.variableAccessSerializable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVariableAccessSerializable(
				Boolean newVariableAccessSerializable) {
		Boolean oldVariableAccessSerializable = this.variableAccessSerializable;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_VARIABLE_ACCESS_SERIALIZABLE, BPELUtils
						.boolean2XML(newVariableAccessSerializable));
		}
		this.variableAccessSerializable = newVariableAccessSerializable;
		boolean oldVariableAccessSerializableESet = this.variableAccessSerializableESet;
		this.variableAccessSerializableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE,
						oldVariableAccessSerializable, this.variableAccessSerializable,
						!oldVariableAccessSerializableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetVariableAccessSerializable() {
		Boolean oldVariableAccessSerializable = this.variableAccessSerializable;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_VARIABLE_ACCESS_SERIALIZABLE,
						(String) null);
		}
		boolean oldVariableAccessSerializableESet = this.variableAccessSerializableESet;
		this.variableAccessSerializable = VARIABLE_ACCESS_SERIALIZABLE_EDEFAULT;
		this.variableAccessSerializableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE,
						oldVariableAccessSerializable,
						VARIABLE_ACCESS_SERIALIZABLE_EDEFAULT,
						oldVariableAccessSerializableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVariableAccessSerializable() {
		return this.variableAccessSerializableESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerLinks getPartnerLinks() {
		return this.partnerLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetPartnerLinks(PartnerLinks newPartnerLinks,
				NotificationChain msgs) {
		PartnerLinks oldPartnerLinks = this.partnerLinks;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldPartnerLinks,
						newPartnerLinks);
		}
		this.partnerLinks = newPartnerLinks;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__PARTNER_LINKS,
						oldPartnerLinks, newPartnerLinks);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartnerLinks(PartnerLinks newPartnerLinks) {
		if (newPartnerLinks != this.partnerLinks) {
			NotificationChain msgs = null;
			if (this.partnerLinks != null)
				msgs = ((InternalEObject) this.partnerLinks).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__PARTNER_LINKS, null,
							msgs);
			if (newPartnerLinks != null)
				msgs = ((InternalEObject) newPartnerLinks).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__PARTNER_LINKS, null,
							msgs);
			msgs = basicSetPartnerLinks(newPartnerLinks, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__PARTNER_LINKS, newPartnerLinks,
						newPartnerLinks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variables getVariables() {
		return this.variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetVariables(Variables newVariables,
				NotificationChain msgs) {
		Variables oldVariables = this.variables;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldVariables, newVariables);
		}
		this.variables = newVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__VARIABLES,
						oldVariables, newVariables);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariables(Variables newVariables) {
		if (newVariables != this.variables) {
			NotificationChain msgs = null;
			if (this.variables != null)
				msgs = ((InternalEObject) this.variables)
				.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__VARIABLES, null, msgs);
			if (newVariables != null)
				msgs = ((InternalEObject) newVariables)
				.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__VARIABLES, null, msgs);
			msgs = basicSetVariables(newVariables, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__VARIABLES, newVariables, newVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getActivity() {
		return this.activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetActivity(Activity newActivity,
				NotificationChain msgs) {
		Activity oldActivity = this.activity;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldActivity, newActivity);
		}
		this.activity = newActivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__ACTIVITY,
						oldActivity, newActivity);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(Activity newActivity) {
		if (newActivity != this.activity) {
			NotificationChain msgs = null;
			if (this.activity != null)
				msgs = ((InternalEObject) this.activity).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE - BPELPackage.PROCESS__ACTIVITY,
							null, msgs);
			if (newActivity != null)
				msgs = ((InternalEObject) newActivity).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE - BPELPackage.PROCESS__ACTIVITY,
							null, msgs);
			msgs = basicSetActivity(newActivity, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__ACTIVITY, newActivity, newActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FaultHandler getFaultHandlers() {
		return this.faultHandlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetFaultHandlers(
				FaultHandler newFaultHandlers, NotificationChain msgs) {
		FaultHandler oldFaultHandlers = this.faultHandlers;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldFaultHandlers,
						newFaultHandlers);
		}
		this.faultHandlers = newFaultHandlers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__FAULT_HANDLERS,
						oldFaultHandlers, newFaultHandlers);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultHandlers(FaultHandler newFaultHandlers) {
		if (newFaultHandlers != this.faultHandlers) {
			NotificationChain msgs = null;
			if (this.faultHandlers != null)
				msgs = ((InternalEObject) this.faultHandlers).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__FAULT_HANDLERS, null,
							msgs);
			if (newFaultHandlers != null)
				msgs = ((InternalEObject) newFaultHandlers).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__FAULT_HANDLERS, null,
							msgs);
			msgs = basicSetFaultHandlers(newFaultHandlers, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__FAULT_HANDLERS, newFaultHandlers,
						newFaultHandlers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Extensions getExtensions() {
		return this.extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetExtensions(Extensions newExtensions,
				NotificationChain msgs) {
		Extensions oldExtensions = this.extensions;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldExtensions,
						newExtensions);
		}
		this.extensions = newExtensions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__EXTENSIONS,
						oldExtensions, newExtensions);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtensions(Extensions newExtensions) {
		if (newExtensions != this.extensions) {
			NotificationChain msgs = null;
			if (this.extensions != null)
				msgs = ((InternalEObject) this.extensions).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__EXTENSIONS, null, msgs);
			if (newExtensions != null)
				msgs = ((InternalEObject) newExtensions).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__EXTENSIONS, null, msgs);
			msgs = basicSetExtensions(newExtensions, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__EXTENSIONS, newExtensions,
						newExtensions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getExitOnStandardFault() {
		return this.exitOnStandardFault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setExitOnStandardFault(Boolean newExitOnStandardFault) {
		Boolean oldExitOnStandardFault = this.exitOnStandardFault;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_EXIT_ON_STANDARD_FAULT, BPELUtils
						.boolean2XML(newExitOnStandardFault));
		}
		this.exitOnStandardFault = newExitOnStandardFault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__EXIT_ON_STANDARD_FAULT,
						oldExitOnStandardFault, this.exitOnStandardFault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageExchanges getMessageExchanges() {
		return this.messageExchanges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	private NotificationChain basicSetMessageExchanges(
				MessageExchanges newMessageExchanges, NotificationChain msgs) {
		MessageExchanges oldMessageExchanges = this.messageExchanges;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldMessageExchanges,
						newMessageExchanges);
		}
		this.messageExchanges = newMessageExchanges;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__MESSAGE_EXCHANGES,
						oldMessageExchanges, newMessageExchanges);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setMessageExchanges(MessageExchanges newMessageExchanges) {
		if (newMessageExchanges != this.messageExchanges) {
			NotificationChain msgs = null;
			if (this.messageExchanges != null)
				msgs = ((InternalEObject) this.messageExchanges).eInverseRemove(
							this, EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__MESSAGE_EXCHANGES, null,
							msgs);
			if (newMessageExchanges != null)
				msgs = ((InternalEObject) newMessageExchanges).eInverseAdd(
							this, EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__MESSAGE_EXCHANGES, null,
							msgs);
			msgs = basicSetMessageExchanges(newMessageExchanges, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__MESSAGE_EXCHANGES,
						newMessageExchanges, newMessageExchanges));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAbstractProcessProfile() {
		return this.abstractProcessProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setAbstractProcessProfile(String newAbstractProcessProfile) {
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_ABSTRACT_PROFILES, newAbstractProcessProfile);
		}
		String oldAbstractProcessProfile = this.abstractProcessProfile;
		this.abstractProcessProfile = newAbstractProcessProfile;
		boolean oldAbstractProcessProfileESet = this.abstractProcessProfileESet;
		this.abstractProcessProfileESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE,
						oldAbstractProcessProfile, this.abstractProcessProfile,
						!oldAbstractProcessProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void unsetAbstractProcessProfile() {
		if (!this.isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
						BPELConstants.AT_ABSTRACT_PROFILES, (String) null);
		}
		String oldAbstractProcessProfile = this.abstractProcessProfile;
		boolean oldAbstractProcessProfileESet = this.abstractProcessProfileESet;
		this.abstractProcessProfile = ABSTRACT_PROCESS_PROFILE_EDEFAULT;
		this.abstractProcessProfileESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE,
						oldAbstractProcessProfile,
						ABSTRACT_PROCESS_PROFILE_EDEFAULT,
						oldAbstractProcessProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAbstractProcessProfile() {
		return this.abstractProcessProfileESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
				int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BPELPackage.PROCESS__PARTNER_LINKS:
			return basicSetPartnerLinks(null, msgs);
		case BPELPackage.PROCESS__VARIABLES:
			return basicSetVariables(null, msgs);
		case BPELPackage.PROCESS__ACTIVITY:
			return basicSetActivity(null, msgs);
		case BPELPackage.PROCESS__FAULT_HANDLERS:
			return basicSetFaultHandlers(null, msgs);
		case BPELPackage.PROCESS__EVENT_HANDLERS:
			return basicSetEventHandlers(null, msgs);
		case BPELPackage.PROCESS__CORRELATION_SETS:
			return basicSetCorrelationSets(null, msgs);
		case BPELPackage.PROCESS__IMPORTS:
			return ((InternalEList<?>) getImports())
			.basicRemove(otherEnd, msgs);
		case BPELPackage.PROCESS__EXTENSIONS:
			return basicSetExtensions(null, msgs);
		case BPELPackage.PROCESS__MESSAGE_EXCHANGES:
			return basicSetMessageExchanges(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BPELPackage.PROCESS__NAME:
			return getName();
		case BPELPackage.PROCESS__TARGET_NAMESPACE:
			return getTargetNamespace();
		case BPELPackage.PROCESS__QUERY_LANGUAGE:
			return getQueryLanguage();
		case BPELPackage.PROCESS__EXPRESSION_LANGUAGE:
			return getExpressionLanguage();
		case BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE:
			return getSuppressJoinFailure();
		case BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE:
			return getVariableAccessSerializable();
		case BPELPackage.PROCESS__PARTNER_LINKS:
			return getPartnerLinks();
		case BPELPackage.PROCESS__VARIABLES:
			return getVariables();
		case BPELPackage.PROCESS__ACTIVITY:
			return getActivity();
		case BPELPackage.PROCESS__FAULT_HANDLERS:
			return getFaultHandlers();
		case BPELPackage.PROCESS__EVENT_HANDLERS:
			return getEventHandlers();
		case BPELPackage.PROCESS__CORRELATION_SETS:
			return getCorrelationSets();
		case BPELPackage.PROCESS__IMPORTS:
			return getImports();
		case BPELPackage.PROCESS__EXTENSIONS:
			return getExtensions();
		case BPELPackage.PROCESS__EXIT_ON_STANDARD_FAULT:
			return getExitOnStandardFault();
		case BPELPackage.PROCESS__MESSAGE_EXCHANGES:
			return getMessageExchanges();
		case BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE:
			return getAbstractProcessProfile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BPELPackage.PROCESS__NAME:
			setName((String) newValue);
			return;
		case BPELPackage.PROCESS__TARGET_NAMESPACE:
			setTargetNamespace((String) newValue);
			return;
		case BPELPackage.PROCESS__QUERY_LANGUAGE:
			setQueryLanguage((String) newValue);
			return;
		case BPELPackage.PROCESS__EXPRESSION_LANGUAGE:
			setExpressionLanguage((String) newValue);
			return;
		case BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE:
			setSuppressJoinFailure((Boolean) newValue);
			return;
		case BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE:
			setVariableAccessSerializable((Boolean) newValue);
			return;
		case BPELPackage.PROCESS__PARTNER_LINKS:
			setPartnerLinks((PartnerLinks) newValue);
			return;
		case BPELPackage.PROCESS__VARIABLES:
			setVariables((Variables) newValue);
			return;
		case BPELPackage.PROCESS__ACTIVITY:
			setActivity((Activity) newValue);
			return;
		case BPELPackage.PROCESS__FAULT_HANDLERS:
			setFaultHandlers((FaultHandler) newValue);
			return;
		case BPELPackage.PROCESS__EVENT_HANDLERS:
			setEventHandlers((EventHandler) newValue);
			return;
		case BPELPackage.PROCESS__CORRELATION_SETS:
			setCorrelationSets((CorrelationSets) newValue);
			return;
		case BPELPackage.PROCESS__IMPORTS:
			getImports().clear();
			getImports().addAll((Collection<? extends Import>) newValue);
			return;
		case BPELPackage.PROCESS__EXTENSIONS:
			setExtensions((Extensions) newValue);
			return;
		case BPELPackage.PROCESS__EXIT_ON_STANDARD_FAULT:
			setExitOnStandardFault((Boolean) newValue);
			return;
		case BPELPackage.PROCESS__MESSAGE_EXCHANGES:
			setMessageExchanges((MessageExchanges) newValue);
			return;
		case BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE:
			setAbstractProcessProfile((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case BPELPackage.PROCESS__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BPELPackage.PROCESS__TARGET_NAMESPACE:
			setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
			return;
		case BPELPackage.PROCESS__QUERY_LANGUAGE:
			unsetQueryLanguage();
			return;
		case BPELPackage.PROCESS__EXPRESSION_LANGUAGE:
			unsetExpressionLanguage();
			return;
		case BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE:
			unsetSuppressJoinFailure();
			return;
		case BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE:
			unsetVariableAccessSerializable();
			return;
		case BPELPackage.PROCESS__PARTNER_LINKS:
			setPartnerLinks((PartnerLinks) null);
			return;
		case BPELPackage.PROCESS__VARIABLES:
			setVariables((Variables) null);
			return;
		case BPELPackage.PROCESS__ACTIVITY:
			setActivity((Activity) null);
			return;
		case BPELPackage.PROCESS__FAULT_HANDLERS:
			setFaultHandlers((FaultHandler) null);
			return;
		case BPELPackage.PROCESS__EVENT_HANDLERS:
			setEventHandlers((EventHandler) null);
			return;
		case BPELPackage.PROCESS__CORRELATION_SETS:
			setCorrelationSets((CorrelationSets) null);
			return;
		case BPELPackage.PROCESS__IMPORTS:
			getImports().clear();
			return;
		case BPELPackage.PROCESS__EXTENSIONS:
			setExtensions((Extensions) null);
			return;
		case BPELPackage.PROCESS__EXIT_ON_STANDARD_FAULT:
			setExitOnStandardFault(EXIT_ON_STANDARD_FAULT_EDEFAULT);
			return;
		case BPELPackage.PROCESS__MESSAGE_EXCHANGES:
			setMessageExchanges((MessageExchanges) null);
			return;
		case BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE:
			unsetAbstractProcessProfile();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case BPELPackage.PROCESS__NAME:
			return NAME_EDEFAULT == null ? this.name != null : !NAME_EDEFAULT
						.equals(this.name);
		case BPELPackage.PROCESS__TARGET_NAMESPACE:
			return TARGET_NAMESPACE_EDEFAULT == null ? this.targetNamespace != null
						: !TARGET_NAMESPACE_EDEFAULT.equals(this.targetNamespace);
		case BPELPackage.PROCESS__QUERY_LANGUAGE:
			return isSetQueryLanguage();
		case BPELPackage.PROCESS__EXPRESSION_LANGUAGE:
			return isSetExpressionLanguage();
		case BPELPackage.PROCESS__SUPPRESS_JOIN_FAILURE:
			return isSetSuppressJoinFailure();
		case BPELPackage.PROCESS__VARIABLE_ACCESS_SERIALIZABLE:
			return isSetVariableAccessSerializable();
		case BPELPackage.PROCESS__PARTNER_LINKS:
			return this.partnerLinks != null;
		case BPELPackage.PROCESS__VARIABLES:
			return this.variables != null;
		case BPELPackage.PROCESS__ACTIVITY:
			return this.activity != null;
		case BPELPackage.PROCESS__FAULT_HANDLERS:
			return this.faultHandlers != null;
		case BPELPackage.PROCESS__EVENT_HANDLERS:
			return this.eventHandlers != null;
		case BPELPackage.PROCESS__CORRELATION_SETS:
			return this.correlationSets != null;
		case BPELPackage.PROCESS__IMPORTS:
			return this.imports != null && !this.imports.isEmpty();
		case BPELPackage.PROCESS__EXTENSIONS:
			return this.extensions != null;
		case BPELPackage.PROCESS__EXIT_ON_STANDARD_FAULT:
			return EXIT_ON_STANDARD_FAULT_EDEFAULT == null ? this.exitOnStandardFault != null
						: !EXIT_ON_STANDARD_FAULT_EDEFAULT
						.equals(this.exitOnStandardFault);
		case BPELPackage.PROCESS__MESSAGE_EXCHANGES:
			return this.messageExchanges != null;
		case BPELPackage.PROCESS__ABSTRACT_PROCESS_PROFILE:
			return isSetAbstractProcessProfile();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventHandler getEventHandlers() {
		return this.eventHandlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetEventHandlers(
				EventHandler newEventHandlers, NotificationChain msgs) {
		EventHandler oldEventHandlers = this.eventHandlers;
		this.eventHandlers = newEventHandlers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__EVENT_HANDLERS,
						oldEventHandlers, newEventHandlers);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventHandlers(EventHandler newEventHandlers) {
		if (newEventHandlers != this.eventHandlers) {
			NotificationChain msgs = null;
			if (this.eventHandlers != null)
				msgs = ((InternalEObject) this.eventHandlers).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__EVENT_HANDLERS, null,
							msgs);
			if (newEventHandlers != null)
				msgs = ((InternalEObject) newEventHandlers).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__EVENT_HANDLERS, null,
							msgs);
			msgs = basicSetEventHandlers(newEventHandlers, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__EVENT_HANDLERS, newEventHandlers,
						newEventHandlers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorrelationSets getCorrelationSets() {
		return this.correlationSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetCorrelationSets(
				CorrelationSets newCorrelationSets, NotificationChain msgs) {
		CorrelationSets oldCorrelationSets = this.correlationSets;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldCorrelationSets,
						newCorrelationSets);
		}
		this.correlationSets = newCorrelationSets;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
						Notification.SET, BPELPackage.PROCESS__CORRELATION_SETS,
						oldCorrelationSets, newCorrelationSets);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationSets(CorrelationSets newCorrelationSets) {
		if (newCorrelationSets != this.correlationSets) {
			NotificationChain msgs = null;
			if (this.correlationSets != null)
				msgs = ((InternalEObject) this.correlationSets).eInverseRemove(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__CORRELATION_SETS, null,
							msgs);
			if (newCorrelationSets != null)
				msgs = ((InternalEObject) newCorrelationSets).eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
							- BPELPackage.PROCESS__CORRELATION_SETS, null,
							msgs);
			msgs = basicSetCorrelationSets(newCorrelationSets, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.PROCESS__CORRELATION_SETS, newCorrelationSets,
						newCorrelationSets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Import> getImports() {
		if (this.imports == null) {
			this.imports = new EObjectContainmentEList<Import>(Import.class, this,
						BPELPackage.PROCESS__IMPORTS);
		}
		return this.imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(this.name);
		result.append(", targetNamespace: "); //$NON-NLS-1$
		result.append(this.targetNamespace);
		result.append(", queryLanguage: "); //$NON-NLS-1$
		if (this.queryLanguageESet)
			result.append(this.queryLanguage);
		else
			result.append("<unset>"); //$NON-NLS-1$
		result.append(", expressionLanguage: "); //$NON-NLS-1$
		if (this.expressionLanguageESet)
			result.append(this.expressionLanguage);
		else
			result.append("<unset>"); //$NON-NLS-1$
		result.append(", suppressJoinFailure: "); //$NON-NLS-1$
		if (this.suppressJoinFailureESet)
			result.append(this.suppressJoinFailure);
		else
			result.append("<unset>"); //$NON-NLS-1$
		result.append(", variableAccessSerializable: "); //$NON-NLS-1$
		if (this.variableAccessSerializableESet)
			result.append(this.variableAccessSerializable);
		else
			result.append("<unset>"); //$NON-NLS-1$
		result.append(", exitOnStandardFault: "); //$NON-NLS-1$
		result.append(this.exitOnStandardFault);
		result.append(", abstractProcessProfile: "); //$NON-NLS-1$
		if (this.abstractProcessProfileESet)
			result.append(this.abstractProcessProfile);
		else
			result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

	@Override
	protected void adoptContent(EReference reference, Object object) {
		if (object instanceof Import) {
			ReconciliationHelper.adoptChild(this, this.imports, (Import) object,
						BPELConstants.ND_IMPORT);
		}
		super.adoptContent(reference, object);
	}

	@Override
	protected void orphanContent(EReference reference, Object obj) {
		if (obj instanceof Import) {
			ReconciliationHelper.orphanChild(this, (Import) obj);
		}
		super.orphanContent(reference, obj);
	}

	/**
	 * @customized
	 */
	private List fieldPostLoadRunnables;

	/**
	 * @see org.eclipse.bpel.model.Process#getPostLoadRunnables()
	 * @customized
	 */
	public List getPostLoadRunnables() {
		if (this.fieldPostLoadRunnables == null)
			this.fieldPostLoadRunnables = new ArrayList();
		return this.fieldPostLoadRunnables;
	}

} //ProcessImpl
