/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.sources.CurrentWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.explorer.sources.ExternalWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.SaDirectorySource;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * The class in charge of managing all the end-point sources.
 * <p>
 * When a source must be added, removed, or is changed, it must be signaled
 * in this class. The changes will be propagated to listeners.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SourceManager implements IResourceChangeListener {

	private static final String EXT_WK_SRC_PREF = "petals.service-explorer.external-workspaces";
	private static final String SA_FLDR_SRC_PREF = "petals.service-explorer.sa-folders";
	private static SourceManager instance = new SourceManager ();

	private final Set<EndpointSource> sources = new HashSet<EndpointSource> ();
	private final List<ISourceChangeListener> listeners = new ArrayList<ISourceChangeListener> ();
	private final CurrentWorkspaceSource currentWorkspaceSource = new CurrentWorkspaceSource();
	private boolean restoredSources = false;


	/**
	 * Constructor.
	 */
	private SourceManager() {

		// Listen to the workbench
		ResourcesPlugin.getWorkspace().addResourceChangeListener( this, IResourceChangeEvent.POST_BUILD );

		// Listen to the workbench
		// Release resources when the workbench is closed
		PlatformUI.getWorkbench().addWorkbenchListener( new IWorkbenchListener() {

			public boolean preShutdown( IWorkbench workbench, boolean forced ) {

				// Do not be notified by workspace changes anymore
				ResourcesPlugin.getWorkspace().removeResourceChangeListener( SourceManager.this );

				// Dispose remaining sources
				for( EndpointSource source : SourceManager.this.sources )
					source.dispose();

				return true;
			}


			public void postShutdown( IWorkbench workbench ) {
				// nothing
			}
		});
	}



	/**
	 * @return
	 */
	public static SourceManager getInstance() {
		return instance;
	}


	/**
	 * Returns the list of end-point sources.
	 * <p>
	 * If sources were stored and not yet restored, they are restored.
	 * </p>
	 * 
	 * @return the sources
	 */
	public Collection<EndpointSource> getSources() {

		if( ! this.restoredSources ) {
			restoreSavedSources();
			this.restoredSources = true;
		}

		return Collections.unmodifiableSet( this.sources );
	}


	/**
	 * @param source
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addSource( EndpointSource source ) {

		boolean result = this.sources.add( source );
		for( ISourceChangeListener listener : this.listeners )
			listener.sourceAdded( source );

		storeSources();
		return result;
	}


	/**
	 * @param source
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean removeSource( EndpointSource source ) {

		boolean result = this.sources.remove( source );
		for( ISourceChangeListener listener : this.listeners )
			listener.sourceRemoved( source );

		source.dispose();
		storeSources();
		return result;
	}


	/**
	 * @param source
	 */
	public void updateSource( EndpointSource source ) {

		source.refreshServiceUnitList();
		for( ISourceChangeListener listener : this.listeners )
			listener.sourceChanged( source );
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean registerListener( ISourceChangeListener listener ) {
		return this.listeners.add( listener );
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean unregisterListener( ISourceChangeListener listener ) {
		return this.listeners.remove( listener );
	}


	/**
	 * Restores the saved sources.
	 * <p>
	 * If no source was saved, the workspace source is added.
	 * </p>
	 */
	private void restoreSavedSources() {

		// Workspace
		this.sources.add( this.currentWorkspaceSource );

		// External workspaces
		IPreferenceStore store = PetalsServicesPlugin.getDefault().getPreferenceStore();
		String prefs = store.getString( EXT_WK_SRC_PREF );
		for( String pref : prefs.split( ";;" )) {

			if( pref.trim().length() == 0 )
				continue;

			File f = new File( pref );
			if( f.exists())
				this.sources.add( new ExternalWorkspaceSource( f ));
		}

		// SA Folders
		prefs = store.getString( SA_FLDR_SRC_PREF );
		for( String pref : prefs.split( ";;" )) {

			if( pref.trim().length() == 0 )
				continue;

			File f = new File( pref );
			if( f.exists())
				this.sources.add( new SaDirectorySource( f, false, false ));
		}
	}


	/**
	 * Saves the sources in the preferences, so that they can be restored at next startup.
	 */
	private void storeSources() {

		StringBuffer extWk = new StringBuffer();
		StringBuffer saFldr = new StringBuffer();
		for( EndpointSource source : this.sources ) {

			if( source instanceof ExternalWorkspaceSource ) {
				extWk.append(((ExternalWorkspaceSource) source).getDirectory().getAbsolutePath());
				extWk.append( ";;" );
			}
			else if( source instanceof SaDirectorySource ) {
				saFldr.append(((SaDirectorySource) source).getDirectoryOrSaFile().getAbsolutePath());
				saFldr.append( ";;" );
			}
		}

		IPreferenceStore store = PetalsServicesPlugin.getDefault().getPreferenceStore();
		store.setValue( EXT_WK_SRC_PREF, extWk.toString());
		store.setValue( SA_FLDR_SRC_PREF, saFldr.toString());
	}


	/**
	 * Handles workspace changes to notify listeners when workspace end-points were modified.
	 */
	public void resourceChanged( IResourceChangeEvent event ) {

		try {
			event.getDelta().accept( new IResourceDeltaVisitor() {
				boolean stopSearching = false;
				public boolean visit( IResourceDelta delta ) throws CoreException {

					if( this.stopSearching )
						return false;

					IResource resource = delta.getResource();

					// Processing projects
					if( resource instanceof IProject ) {
						IProject project = (IProject) resource;

						// Project was deleted: update the workspace source and do not go further
						if( delta.getKind() == IResourceDelta.REMOVED ) {
							updateSource( SourceManager.this.currentWorkspaceSource );
							this.stopSearching = true;
							return false;
						}

						// Filter the project
						return project.isAccessible() && ServiceProjectRelationUtils.isSuProject( project );
					}

					// Processing files
					else if( resource instanceof IFile ) {
						if( resource.getName().equals( "jbi.xml" )
									|| resource.getName().equals( ".jbi-metadata" )) {

							updateSource( SourceManager.this.currentWorkspaceSource );
							this.stopSearching = true;
						}

						return false;
					}

					return true;
				}
			});

		} catch( CoreException e ) {
			e.printStackTrace();
		}
	}
}
