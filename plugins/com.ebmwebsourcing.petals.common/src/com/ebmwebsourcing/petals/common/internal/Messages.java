/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal;

import org.eclipse.osgi.util.NLS;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.ebmwebsourcing.petals.common.internal.messages"; //$NON-NLS-1$

	// Package "*.validation.constraints"
	public static String ConsumerConstraints_0;
	public static String ConsumerConstraints_4;

	public static String ConsumesConstraints_0;

	// Package "*.wizard"
	public static String NewJbiXmlWizard_0;
	public static String NewJbiXmlWizard_1;
	public static String NewJbiXmlWizard_2;

	public static String NewJbiXmlWizardPage_0;
	public static String NewJbiXmlWizardPage_1;
	public static String NewJbiXmlWizardPage_2;
	public static String NewJbiXmlWizardPage_5;
	public static String NewJbiXmlWizardPage_6;
	public static String NewJbiXmlWizardPage_8;

	// Package "*.utils"
	public static String WsdlUpdater_9;
	
	public static String fieldNotSet;

	static {
		// initialize resource bundle
		NLS.initializeMessages( BUNDLE_NAME, Messages.class );
	}

	private Messages() {
		// nothing
	}
}
