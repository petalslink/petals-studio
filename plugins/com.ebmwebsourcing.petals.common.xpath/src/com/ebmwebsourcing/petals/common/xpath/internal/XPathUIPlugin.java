/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.xpath.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class XPathUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.bpel.xpath10.ui";

	// The shared instance
	private static XPathUIPlugin plugin;

	/**
	 * The constructor
	 */
	public XPathUIPlugin() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin
	 * #start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin
	 * #stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}


	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static XPathUIPlugin getDefault() {
		return plugin;
	}


	/**
	 * Logs an exception.
	 * @param e the exception to log
	 * @param severity the severity, given as one of the {@link IStatus} constants
	 */
	public static void log( Exception e, int severity ) {

		String msg = e.getMessage();
		if( msg == null || msg.trim().length() == 0 )
			msg = "An error occurred.";

		IStatus status = new Status( severity, PLUGIN_ID, msg, e );
		getDefault().getLog().log( status );
	}


	/**
	 * Logs a message.
	 * @param message the message to log
	 * @param severity the severity, given as one of the {@link IStatus} constants
	 */
	public static void log( String message, int severity ) {
		IStatus status = new Status( severity, PLUGIN_ID, message );
		getDefault().getLog().log( status );
	}


	/**
	 * Logs a message and an exception.
	 * @param e the exception to log
	 * @param severity the severity, given as one of the {@link IStatus} constants
	 * @param message the message to log
	 */
	public static void log( Exception e, int severity, String message ) {
		IStatus status = new Status( severity, PLUGIN_ID, message, e );
		getDefault().getLog().log( status );
	}
}
