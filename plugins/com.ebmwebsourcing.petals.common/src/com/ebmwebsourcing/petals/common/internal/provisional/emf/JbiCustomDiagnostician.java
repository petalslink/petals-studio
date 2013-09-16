/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.sun.java.xml.ns.jbi.DocumentRoot;

/**
 * A diagnostician used format error messages for EMF resources.
 * <p>
 * Inspired from the GMF diagnostician but made to avoid additional dependencies.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiCustomDiagnostician extends Diagnostician {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Diagnostician
	 * #getObjectLabel(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public String getObjectLabel( EObject eObject ) {

		if( eObject.eIsProxy() || eObject instanceof DocumentRoot )
			return ""; //$NON-NLS-1$

		// Compute prefix (get container names recursively).
		String prefix = ""; //$NON-NLS-1$
		EObject eContainer = eObject.eContainer();
		while( eContainer instanceof EAnnotation )
			eContainer = eContainer.eContainer();
		if( eContainer != null )
			prefix = getObjectLabel( eContainer );

		// Get current element name.
		EAttribute nameAttribute = null;
		EStructuralFeature feature = eObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
		if (feature != null) {
			if (feature instanceof EAttribute) {
				EClassifier type = feature.getEType();
				if( type != null && type.getInstanceClass() == String.class)
					nameAttribute = (EAttribute) feature;
			}
		}

		String name = null;
		if( nameAttribute != null )
			name = (String) eObject.eGet( nameAttribute );

		if( name == null || name.equals( "" )) //$NON-NLS-1$
			name = "<" + eObject.eClass().getName() + ">"; //$NON-NLS-1$ //$NON-NLS-2$

		if( prefix.equals( "" )) //$NON-NLS-1$
			return name;

		return prefix + "::" + name; //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Diagnostician
	 * #getFeatureLabel(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public String getFeatureLabel(EStructuralFeature eStructuralFeature) {
		String s = eStructuralFeature.getName();
		return s;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Diagnostician
	 * #getValueLabel(org.eclipse.emf.ecore.EDataType, java.lang.Object)
	 */
	@Override
	public String getValueLabel(EDataType eDataType, Object value) {
		String s = EcoreUtil.convertToString(eDataType, value);
		return s;
	}
}
