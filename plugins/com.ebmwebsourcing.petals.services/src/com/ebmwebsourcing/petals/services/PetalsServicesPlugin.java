/****************************************************************************
 *
 * Copyright (c) 2009-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;

/**
 * The activator class controls the plug-in life cycle
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServicesPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.ebmwebsourcing.petals.services";

	// The shared instance
	private static PetalsServicesPlugin plugin;


	/**
	 * The constructor
	 */
	public PetalsServicesPlugin() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin
	 * #start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start( BundleContext context ) throws Exception {
		super.start( context );
		plugin = this;

		// Initialize feature IDs
		try {
			for (ComponentVersionDescription description : ExtensionManager.INSTANCE.findAllComponentVersionDescriptions()) {
				EPackage extensionPackage = EPackageRegistryImpl.INSTANCE.getEPackage(description.getNamespace());
				if (extensionPackage != null) {
					new InitializeModelExtensionCommand( extensionPackage, null ).initializeFeatures();
				} else {
					log("Could not load model for component [" + description.getComponentName() + "] (ns=" + description.getNamespace() + ")", IStatus.ERROR);
				}
			}

		} catch( Exception e ) {
			log( e, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin
	 * #stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop( BundleContext context ) throws Exception {
		plugin = null;
		super.stop( context );
	}


	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static PetalsServicesPlugin getDefault() {
		return plugin;
	}


	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor( String path ) {
		return imageDescriptorFromPlugin( PLUGIN_ID, path );
	}


	/**
	 * Loads the image located by this path in the current plug-in.
	 * <p>
	 * If the path is invalid or if the image could not be loaded, a log entry is created.
	 * </p>
	 *
	 * @param path the relative image path in the plug-in
	 * @return the image or null if it could not be loaded
	 */
	public static Image loadImage( String path ) {

		Image img = null;
		try {
			ImageDescriptor desc = getImageDescriptor( path );
			if( desc != null )
				img = desc.createImage();

		} catch( Exception e ) {
			log( e, IStatus.WARNING );
		}

		return img;
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
