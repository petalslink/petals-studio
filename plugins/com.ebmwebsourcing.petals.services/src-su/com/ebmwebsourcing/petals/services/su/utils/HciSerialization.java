/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.CacheItem;

/**
 * Manage the serialization and de-serialization of HCI objects.
 * This constitutes a cache for the HCI generation, avoiding us
 * to parse XSD files every time a wizard runs.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class HciSerialization {

	/** The unique instance of this class. */
	private static HciSerialization instance = new HciSerialization ();


	/** Private constructor. */
	private HciSerialization() {}

	/**
	 * @return the unique instance of this class.
	 */
	public static HciSerialization getInstance() {
		return instance;
	}

	/**
	 * Retrieve the widgets and the namespaces cached into this file.
	 * @param fileName
	 * @return
	 */
	public CacheItem retrieveCache( String fileName ) {

		// Try to find the cache folder.
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject cacheProject = workspaceRoot.getProject( SuMainConstants.PETALS_SU_CACHE_FOLDER_NAME );
		if( !cacheProject.exists())
			return null;

		// Check cache version ID.
		if( !hasGoodVersion( cacheProject ))
			return null;

		// Try to find the cache file for the given argument.
		IFile cacheFile = cacheProject.getFile( fileName );
		if( !cacheFile.exists())
			return null;

		try {
			InputStream fis = cacheFile.getContents();
			ObjectInputStream in = new ObjectInputStream( fis );
			CacheItem item = (CacheItem) in.readObject();
			in.close();
			fis.close();

			return item;
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Save the widgets and the namespaces into the argument file.
	 * @param fileName
	 * @param cachceItem
	 */
	public void saveToCache( String fileName, CacheItem cachceItem ) {
		// Try to find the cache folder.
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject cacheProject = workspaceRoot.getProject( SuMainConstants.PETALS_SU_CACHE_FOLDER_NAME );
		NullProgressMonitor monitor = new NullProgressMonitor ();

		// Project exists ?
		if( !cacheProject.exists()
					|| ( !hasGoodVersion( cacheProject ))) {
			try {
				cacheProject.create( monitor );
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
		}

		// Project exists. Open it if necessary.
		if( !cacheProject.isOpen()) {
			try {
				cacheProject.open( monitor );
			} catch (CoreException e) {
				e.printStackTrace();
				return;
			}
		}

		// Add cache system version as project property.
		try {
			cacheProject.setPersistentProperty(
						SuMainConstants.CACHE_VERSION_AS_QUALIFIED_NAME,
						SuMainConstants.CACHE_SERIAL_SYSTEM_VERSION );
		} catch (CoreException e1) {
			e1.printStackTrace();
		}

		// Create the serialization stream.
		ByteArrayInputStream inputStream = null;
		try {
			ByteArrayOutputStream fos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream( fos );
			out.writeObject( cachceItem );
			out.close();
			// fos.close() is useless here.

			inputStream = new ByteArrayInputStream( fos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( inputStream == null )
			return;

		// Write the stream into an IFile.
		IFile cacheFile = cacheProject.getFile( fileName );
		try {
			if( !cacheFile.exists())
				cacheFile.create( inputStream, true, new NullProgressMonitor ());
			else
				cacheFile.setContents( inputStream,  true, true, new NullProgressMonitor ());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check the version associated with a cache project.
	 * The version should be equal to the one registered in the SuMainConstants class.
	 * If the version is not the good one, the project will be deleted and the method
	 * will return false. Otherwise, it returns true.
	 * 
	 * @param cacheProject not null.
	 * @return true if the project has the right serialization system version as property, false otherwise.
	 */
	private boolean hasGoodVersion( IProject cacheProject ) {
		// Check the cache system id associated with the project.
		String cacheId = null;
		try {
			Object o = cacheProject.getPersistentProperty( SuMainConstants.CACHE_VERSION_AS_QUALIFIED_NAME );
			if( o != null && String.class.equals( o.getClass()))
				cacheId = (String) o;
		} catch (CoreException e1) {
			e1.printStackTrace();
		}

		// The cache ID should be equal to the ID given in the SuMainConstants class.
		if( cacheId == null
					|| !cacheId.equals( SuMainConstants.CACHE_SERIAL_SYSTEM_VERSION )) {
			// delete cache folder
			try {
				cacheProject.delete( true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}

			return false;
		}

		return true;
	}

	/**
	 * Clear the cache.
	 * Delete the Petals_cache folder and all the files it contains.
	 */
	public void clearCache() {

		// Try to find the cache folder.
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject cacheProject = workspaceRoot.getProject( SuMainConstants.PETALS_SU_CACHE_FOLDER_NAME );
		try {
			cacheProject.delete( true, true, new NullProgressMonitor());

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * @return true if the cache exists, false otherwise
	 */
	public boolean cacheExists() {

		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject cacheProject = workspaceRoot.getProject( SuMainConstants.PETALS_SU_CACHE_FOLDER_NAME );
		return cacheProject.isAccessible();
	}
}
