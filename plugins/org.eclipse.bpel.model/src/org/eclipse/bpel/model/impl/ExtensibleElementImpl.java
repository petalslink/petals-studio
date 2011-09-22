/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtensibleElementImpl.java,v 1.14 2009/04/14 10:50:36 smoser Exp $
 */
package org.eclipse.bpel.model.impl;

import java.util.HashMap;
import java.util.Map;

import javax.wsdl.extensions.ExtensibilityElement;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.BPELPlugin;
import org.eclipse.bpel.model.Documentation;
import org.eclipse.bpel.model.ExtensibleElement;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.wst.wsdl.WSDLElement;
import org.eclipse.wst.wsdl.internal.impl.WSDLElementImpl;
import org.w3c.dom.Element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extensible Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.ExtensibleElementImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensibleElementImpl extends
org.eclipse.wst.wsdl.internal.impl.ExtensibleElementImpl implements
ExtensibleElement {
	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * This is true if the Documentation containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean documentationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensibleElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BPELPackage.Literals.EXTENSIBLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Documentation getDocumentation() {
		return this.documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicSetDocumentation(
			Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = this.documentation;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldDocumentation,
					newDocumentation);
		}
		this.documentation = newDocumentation;
		boolean oldDocumentationESet = this.documentationESet;
		this.documentationESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
					oldDocumentation, newDocumentation, !oldDocumentationESet);
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
	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != this.documentation) {
			NotificationChain msgs = null;
			if (this.documentation != null)
				msgs = ((InternalEObject) this.documentation)
				.eInverseRemove(
						this,
						EOPPOSITE_FEATURE_BASE
						- BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
						null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject) newDocumentation)
				.eInverseAdd(
						this,
						EOPPOSITE_FEATURE_BASE
						- BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
						null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else {
			boolean oldDocumentationESet = this.documentationESet;
			this.documentationESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
						newDocumentation, newDocumentation,
						!oldDocumentationESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public NotificationChain basicUnsetDocumentation(NotificationChain msgs) {
		Documentation oldDocumentation = this.documentation;
		if (!this.isReconciling) {
			ReconciliationHelper.replaceChild(this, oldDocumentation, null);
		}
		this.documentation = null;
		boolean oldDocumentationESet = this.documentationESet;
		this.documentationESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.UNSET,
					BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
					oldDocumentation, null, oldDocumentationESet);
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
	public void unsetDocumentation() {
		if (this.documentation != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject) this.documentation).eInverseRemove(this,
					EOPPOSITE_FEATURE_BASE
					- BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION,
					null, msgs);
			msgs = basicUnsetDocumentation(msgs);
			if (msgs != null)
				msgs.dispatch();
		} else {
			boolean oldDocumentationESet = this.documentationESet;
			this.documentationESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET,
						BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION, null,
						null, oldDocumentationESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDocumentation() {
		return this.documentationESet;
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
		case BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION:
			return basicUnsetDocumentation(msgs);
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
		case BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION:
			return getDocumentation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION:
			setDocumentation((Documentation) newValue);
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
		case BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION:
			unsetDocumentation();
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
		case BPELPackage.EXTENSIBLE_ELEMENT__DOCUMENTATION:
			return isSetDocumentation();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Set the DOM element which has been read and which is the facade for this EMF
	 * object.
	 * 
	 * @see org.eclipse.wst.wsdl.internal.impl.WSDLElementImpl#setElement(org.w3c.dom.Element)
	 */

	@Override
	public void setElement(Element elm) {
		super.setElement(elm);

		// a pointer back to the EMF model.

		if (elm != null) {
			elm.setUserData("emf.model", this, null); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.wst.wsdl.internal.impl.WSDLElementImpl#getElement()
	 */

	@Override
	public Element getElement() {
		return super.getElement();
	}

	/**
	 * @generated NOT
	 */

	Map<String, Object> fTransients = null;

	/**
	 * This is not part of the EMF model.
	 * 
	 * @see org.eclipse.bpel.model.ExtensibleElement#getTransientProperty(java.lang.String)
	 * @generated NOT
	 */

	public <T extends Object> T getTransientProperty(String key) {
		if (this.fTransients == null) {
			return null;
		}
		Object obj = this.fTransients.get(key);
		if (obj == null) {
			return null;
		}
		return (T) obj;
	}

	/**
	 * @see org.eclipse.bpel.model.ExtensibleElement#setTransientProperty(java.lang.String, java.lang.Object)
	 * @generated NOT
	 */
	public <T extends Object> T setTransientProperty(String key, T value) {
		if (this.fTransients == null) {
			this.fTransients = new HashMap<String, Object>(7);
		}
		Object obj = this.fTransients.put(key, value);
		if (obj == null) {
			return null;
		}
		return (T) obj;
	}

	// Reconciliation stuff. Has copy in ExtensibilityElement
	// TODO: (DU) remove duplication
	@Override
	protected void reconcile(Element changedElement) {
		//	    reconcileAttributes(changedElement);
		//	    reconcileContents(changedElement);
		ReconciliationHelper.getInstance().reconcile(this, changedElement);
	}

	//	protected void reconcileContents(Element changedElement) {
	//	    List remainingModelObjects = new ArrayList(getWSDLContents());
	//
	//	    Collection<Element> contentNodes = ReconciliationHelper.getContentNodes(this, changedElement);
	//
	//	    Element theDocumentationElement = null;
	//
	//	    // for each applicable child node of changedElement
	//	    LOOP: for (Element child : contentNodes) {
	//	    	// Set Documentation element if exists
	//	    	/*if (WSDLConstants.DOCUMENTATION_ELEMENT_TAG.equals(child.getLocalName())
	//	    			&& WSDLConstants.isMatchingNamespace(child.getNamespaceURI(), WSDLConstants.WSDL_NAMESPACE_URI)) {
	//	    		// assume the first 'documentation' element is 'the' documentation element
	//	    		// 'there can be only one!'
	//	    		if (theDocumentationElement == null) {
	//	    			theDocumentationElement = child;
	//	    		}
	//	    	}*/
	//	    	// go thru the model objects to collect matching object for reuse
	//	    	for (Iterator contents = remainingModelObjects.iterator(); contents.hasNext();) {
	//	    		Object modelObject = (Object)contents.next();
	//	    		if (((WSDLElement)modelObject).getElement() == child) {
	//	    			contents.remove(); // removes the 'child' Node from the remainingModelObjects list
	//	    			continue LOOP;
	//	    		}
	//	    	}
	//
	//	    	// if the documentation element has changed... update it
	//	    	if (theDocumentationElement != getDocumentationElement()) {
	//	    		setDocumentationElement(theDocumentationElement);
	//	    	}
	//
	//	    	// we haven't found a matching model object for the Node, so we may need to
	//	    	// create a new model object
	//	    	handleUnreconciledElement(child, remainingModelObjects);
	//	    }
	//
	//	    // now we can remove the remaining model objects
	//	    handleReconciliation(remainingModelObjects);
	//	}

	@Override
	public void elementChanged(Element changedElement) {
		if (!isUpdatingDOM()) {
			if (!this.isReconciling) {
				this.isReconciling = true;
				try {
					reconcile(changedElement);

					WSDLElement theContainer = getContainer();
					if (theContainer != null
							&& theContainer.getElement() == changedElement) {
						((WSDLElementImpl) theContainer)
						.elementChanged(changedElement);
					}
				} finally {
					this.isReconciling = false;
				}
				traverseToRootForPatching();
			}
		}
	}

	public boolean isReconciling() {
		return this.isReconciling;
	}

	@Override
	public boolean isUpdatingDOM() {
		return super.isUpdatingDOM();
	}

	public void setUpdatingDOM(boolean updatingDOM) {
		this.updatingDOM = updatingDOM;
	}

	// VZ
	public ExtensibilityElement removeExtensibilityElement( ExtensibilityElement arg0 ) {
		BPELPlugin.log( "Implementation to review", null, IStatus.INFO );
		return arg0;
	}
	// VZ

} //ExtensibleElementImpl
