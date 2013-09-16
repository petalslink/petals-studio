/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.metamodel.validation;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.ui.IStartup;

import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * This class installs an EMF <code>EValidator</code> on the Library package
 * when we start up. This validator adapts EMF's <code>EValidator</code> API
 * to the EMF Model Validation Service API.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Startup implements IStartup {

	/**
	 * Constructor.
	 */
	public Startup() {
		super();
	}

	/**
	 * Install the validator.
	 */
	public void earlyStartup() {
		EValidator.Registry.INSTANCE.put( JbiPackage.eINSTANCE, new EValidatorAdapter());
	}
}
