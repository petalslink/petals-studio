/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ftp.ftp3.util;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService;

import com.ebmwebsourcing.petals.services.ftp.ftp3.*;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Provides;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package
 * @generated
 */
public class Ftp3AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ftp3Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ftp3AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Ftp3Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Ftp3Switch<Adapter> modelSwitch =
		new Ftp3Switch<Adapter>() {
			@Override
			public Adapter caseFtpProvides(FtpProvides object) {
				return createFtpProvidesAdapter();
			}
			@Override
			public Adapter caseAbstractExtensibleElement(AbstractExtensibleElement object) {
				return createAbstractExtensibleElementAdapter();
			}
			@Override
			public Adapter caseAbstractEndpoint(AbstractEndpoint object) {
				return createAbstractEndpointAdapter();
			}
			@Override
			public Adapter caseCDKService(CDKService object) {
				return createCDKServiceAdapter();
			}
			@Override
			public Adapter caseProvides(Provides object) {
				return createProvidesAdapter();
			}
			@Override
			public Adapter caseCDK5Provides(CDK5Provides object) {
				return createCDK5ProvidesAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides <em>Ftp Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides
	 * @generated
	 */
	public Adapter createFtpProvidesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.sun.java.xml.ns.jbi.AbstractExtensibleElement <em>Abstract Extensible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.sun.java.xml.ns.jbi.AbstractExtensibleElement
	 * @generated
	 */
	public Adapter createAbstractExtensibleElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint <em>Abstract Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.sun.java.xml.ns.jbi.AbstractEndpoint
	 * @generated
	 */
	public Adapter createAbstractEndpointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService <em>CDK Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService
	 * @generated
	 */
	public Adapter createCDKServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.sun.java.xml.ns.jbi.Provides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.sun.java.xml.ns.jbi.Provides
	 * @generated
	 */
	public Adapter createProvidesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides <em>CDK5 Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides
	 * @generated
	 */
	public Adapter createCDK5ProvidesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Ftp3AdapterFactory
