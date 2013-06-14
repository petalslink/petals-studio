/****************************************************************************
 * 
 * Copyright (c) 2008-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

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
