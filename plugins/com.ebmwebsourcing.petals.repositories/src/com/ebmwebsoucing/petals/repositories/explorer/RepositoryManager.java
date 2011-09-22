/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsoucing.petals.repositories.explorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;

/**
 * The class in charge of managing all the end-point sources.
 * <p>
 * When a source must be added, removed, or is changed, it must be signaled
 * in this class. The changes will be propagated to listeners.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryManager {

	private static RepositoryManager instance = new RepositoryManager ();

	private final Collection<Repository> repositories = new HashSet<Repository> ();
	private final List<IRepositoryChangeListener> listeners = new ArrayList<IRepositoryChangeListener> ();

	private boolean restoredSources = false;


	/**
	 * Constructor.
	 */
	private RepositoryManager() {

		// Listen to the workbench
		// Release resources when the workbench is closed
		PlatformUI.getWorkbench().addWorkbenchListener( new IWorkbenchListener() {

			public boolean preShutdown( IWorkbench workbench, boolean forced ) {

				// Dispose remaining repositories
				for( Repository repo : RepositoryManager.this.repositories )
					repo.releaseResource();

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
	public static RepositoryManager getInstance() {
		return instance;
	}


	/**
	 * Returns the list of repositories.
	 * <p>
	 * If repositories were stored and not yet restored, they are restored.
	 * </p>
	 * 
	 * @return the repositories
	 */
	public Collection<Repository> getRepositories() {

		if( ! this.restoredSources ) {
			// restoreSavedSources();
			this.restoredSources = true;
		}

		return Collections.unmodifiableCollection( this.repositories );
	}


	/**
	 * @param repository
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addRepository( Repository repository ) {

		boolean result = this.repositories.add( repository );
		for( IRepositoryChangeListener listener : this.listeners )
			listener.repositoryAdded( repository );

		// storeSources();
		return result;
	}


	/**
	 * @param repository
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean removeRepository( Repository repository ) {

		boolean result = this.repositories.remove( repository );
		for( IRepositoryChangeListener listener : this.listeners )
			listener.repositoryRemoved( repository );

		repository.releaseResource();
		// storeSources();
		return result;
	}


	/**
	 * @param repository
	 */
	public void updateRepository( Repository repository ) {

		// repository.refreshServiceUnitList();
		for( IRepositoryChangeListener listener : this.listeners )
			listener.repositoryChanged( repository );
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean registerListener( IRepositoryChangeListener listener ) {
		return this.listeners.add( listener );
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean unregisterListener( IRepositoryChangeListener listener ) {
		return this.listeners.remove( listener );
	}


	//	/**
	//	 * Restores the saved sources.
	//	 * <p>
	//	 * If no source was saved, the workspace source is added.
	//	 * </p>
	//	 */
	//	private void restoreSavedSources() {
	//
	//		// Workspace
	//		this.sources.add( this.currentWorkspaceSource );
	//
	//		// External workspaces
	//		IPreferenceStore store = PetalsServicesPlugin.getDefault().getPreferenceStore();
	//		String prefs = store.getString( EXT_WK_SRC_PREF );
	//		for( String pref : prefs.split( ";;" )) {
	//
	//			if( pref.trim().length() == 0 )
	//				continue;
	//
	//			File f = new File( pref );
	//			if( f.exists())
	//				this.sources.add( new ExternalWorkspaceSource( f ));
	//		}
	//
	//		// SA Folders
	//		prefs = store.getString( SA_FLDR_SRC_PREF );
	//		for( String pref : prefs.split( ";;" )) {
	//
	//			if( pref.trim().length() == 0 )
	//				continue;
	//
	//			File f = new File( pref );
	//			if( f.exists())
	//				this.sources.add( new SaDirectorySource( f, false, false ));
	//		}
	//	}
	//
	//
	//	/**
	//	 * Saves the sources in the preferences, so that they can be restored at next startup.
	//	 */
	//	private void storeSources() {
	//
	//		StringBuffer extWk = new StringBuffer();
	//		StringBuffer saFldr = new StringBuffer();
	//		for( EndpointSource source : this.sources ) {
	//
	//			if( source instanceof ExternalWorkspaceSource ) {
	//				extWk.append(((ExternalWorkspaceSource) source).getDirectory().getAbsolutePath());
	//				extWk.append( ";;" );
	//			}
	//			else if( source instanceof SaDirectorySource ) {
	//				saFldr.append(((SaDirectorySource) source).getDirectoryOrSaFile().getAbsolutePath());
	//				saFldr.append( ";;" );
	//			}
	//		}
	//
	//		IPreferenceStore store = PetalsServicesPlugin.getDefault().getPreferenceStore();
	//		store.setValue( EXT_WK_SRC_PREF, extWk.toString());
	//		store.setValue( SA_FLDR_SRC_PREF, saFldr.toString());
	//	}
}
