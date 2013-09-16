/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.projectscnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsCnfPackageFragment;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectManager implements IResourceChangeListener, IResourceDeltaVisitor {

	public static final PetalsProjectManager INSTANCE = new PetalsProjectManager();
	private static final String FLAT_LAYOUT_PREF = "petals.flat.layout.preference";
	private static final String EXTENSION_POINT = "com.ebmwebsourcing.petals.common.projectCategories";

	private List<PetalsProjectCategory> categories = null;
	private final Map<IProject,List<PetalsProjectCategory>> projectToCategories;
	private final Map<PetalsProjectCategory,List<IProject>> categoryToProjects;
	private final ConcurrentLinkedQueue<IPetalsProjectResourceChangeListener> listeners;

	/**
	 * This map is populated by the content provider and used by the label provider.
	 * <p>
	 * This is a workaround to make the display mode and empty package filtering work.
	 * </p>
	 */
	public final Map<IPackageFragment,PetalsCnfPackageFragment> dirtyViewerMap = new ConcurrentHashMap<IPackageFragment,PetalsCnfPackageFragment> ();
	private final StatisticsTimer statisticsTimer;


	/**
	 * Constructor.
	 */
	private PetalsProjectManager() {

		this.statisticsTimer = new StatisticsTimer();
		// Set it to false or comment it for a release (only for tests)
		this.statisticsTimer.setEnabled( false );
		//

		this.projectToCategories = new HashMap<IProject,List<PetalsProjectCategory>> ();
		this.categoryToProjects = new HashMap<PetalsProjectCategory,List<IProject>> ();
		this.listeners = new ConcurrentLinkedQueue<IPetalsProjectResourceChangeListener> ();
	}


	/**
	 * @param listener the listener to add
	 * @see java.util.List#add(java.lang.Object)
	 */
	public void addListener( IPetalsProjectResourceChangeListener listener ) {
		this.listeners.add( listener );
	}


	/**
	 * @param listener the listener to remove
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public void removeListener( IPetalsProjectResourceChangeListener listener ) {
		this.listeners.remove( listener );
	}


	/**
	 * @return a non-null list of project categories
	 */
	public synchronized List<PetalsProjectCategory> getProjectCategories() {

		// Start RECORDING
		this.statisticsTimer.start( "[ CATEGORIES ] " );

		// Need to build the categories?
		if( this.categories == null ) {
			this.categories = new ArrayList<PetalsProjectCategory> ();

			// Get the categories from the extension point
			IExtensionRegistry reg = Platform.getExtensionRegistry();
			IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_POINT );
			for( IConfigurationElement elt : extensions ) {

				String className = elt.getAttribute( "class" );
				if( StringUtils.isEmpty( className )) {
					PetalsCommonPlugin.log( "No project category was found for " + elt.getContributor().getName(), IStatus.ERROR );
					continue;
				}

				try {
					PetalsProjectCategory cat = (PetalsProjectCategory) elt.createExecutableExtension( "class" );
					this.categories.add( cat );

				} catch( CoreException e ) {
					PetalsCommonPlugin.log( "A project category could not be instantiated - " + className, IStatus.ERROR );
				}
			}

			// Record the time
			this.statisticsTimer.track( "[ CATEGORIES ] Got all the categories from the extension-point." );

			// Initialize the associations
			rebuildCategoryAssociations();

			// Listen to work space changes
			int events = IResourceChangeEvent.POST_CHANGE | IResourceChangeEvent.POST_BUILD;
			ResourcesPlugin.getWorkspace().addResourceChangeListener( this, events );
		}

		// End of recording
		this.statisticsTimer.stop( "[ CATEGORIES ] " );

		return this.categories;
	}


	/**
	 * Rebuilds the association between projects and Petals categories.
	 * <p>
	 * For performance reasons, these associations are cached.
	 * Note that the work space is not refreshed by this method.
	 * </p>
	 */
	public void rebuildCategoryAssociations() {

		this.projectToCategories.clear();
		this.statisticsTimer.track( "[ CATEGORIES ] Going through the workspace." );
		for( IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			for( PetalsProjectCategory cat : getProjectCategories()) {
				if( cat.projectMatches( p )) {

					List<PetalsProjectCategory> cats = this.projectToCategories.get( p );
					if( cats == null )
						cats = new ArrayList<PetalsProjectCategory> ();

					cats.add( cat );
					this.projectToCategories.put( p, cats );
				}
			}
		}

		this.statisticsTimer.track( "[ CATEGORIES ] Went through the workspace." );
		rebuildCategoryReversedAssociation();
		this.statisticsTimer.track( "[ CATEGORIES ] Built reversed associations." );
	}


	/**
	 * @param project the project
	 * @return the associated categories (can be null)
	 */
	public List<PetalsProjectCategory> getCategories( IProject project ) {
		return this.projectToCategories.get( project );
	}


	/**
	 * @param category the category
	 * @return the associated projects (can be null)
	 */
	public List<IProject> getProjects( PetalsProjectCategory category ) {
		return this.categoryToProjects.get( category );
	}


	/**
	 * @param categoryId the category ID
	 * @return the associated projects (can be null)
	 */
	public List<IProject> getProjects( String categoryId ) {

		PetalsProjectCategory cat = null;
		for( PetalsProjectCategory c : this.categoryToProjects.keySet()) {
			if( c.getId().equals( categoryId )) {
				cat = c;
				break;
			}
		}

		return cat == null ? new ArrayList<IProject> () : this.categoryToProjects.get( cat );
	}


	/**
	 * Refreshes the association for a given project.
	 * @param project the project whose associations must be refreshed
	 * @param refreshReversedAssociations true to refresh the reversed associations, false if this action is called after
	 */
	public void refreshAssociation( IProject project, boolean refreshReversedAssociations ) {

		this.projectToCategories.remove( project );
		for( PetalsProjectCategory cat : getProjectCategories()) {
			if( cat.projectMatches( project )) {

				List<PetalsProjectCategory> cats = this.projectToCategories.get( project );
				if( cats == null )
					cats = new ArrayList<PetalsProjectCategory> ();

				cats.add( cat );
				this.projectToCategories.put( project, cats );
			}
		}

		if( refreshReversedAssociations )
			rebuildCategoryReversedAssociation();
	}


	/**
	 * Rebuilds the association between projects and Petals categories.
	 */
	private void rebuildCategoryReversedAssociation() {

		this.categoryToProjects.clear();
		for( Map.Entry<IProject,List<PetalsProjectCategory>> entry : this.projectToCategories.entrySet()) {
			for( PetalsProjectCategory cat : entry.getValue()) {

				List<IProject> projects = this.categoryToProjects.get( cat );
				if( projects == null )
					projects = new ArrayList<IProject> ();

				projects.add( entry.getKey());
				this.categoryToProjects.put( cat, projects );
			}
		}
	}


	/**
	 * @param flatLayout true for a flat layout, false otherwise
	 */
	public static void storeJavaLayoutPreference( boolean flatLayout ) {
		PetalsCommonPlugin.getDefault().getPreferenceStore().setValue( FLAT_LAYOUT_PREF, flatLayout );
	}


	/**
	 * @return true if the Java packages should be displayed in a flat way, false for hierarchical
	 */
	public static boolean isJavaLayoutFlat() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( FLAT_LAYOUT_PREF );
	}


	private final Set<IResource> addedResources = new HashSet<IResource> ();
	private final Set<IResource> removedResources = new HashSet<IResource> ();


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener
	 * #resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged( IResourceChangeEvent event ) {

		try {
			// Start recording
			this.statisticsTimer.start( " [ CHANGE ] " );

			// Disable graphical updates
			for( IPetalsProjectResourceChangeListener listener : this.listeners )
				listener.prepareNotification();

			// Clear the collections
			this.addedResources.clear();
			this.removedResources.clear();

			// Process the changes
			if( event.getType() == IResourceChangeEvent.POST_CHANGE ) {
				event.getDelta().accept( this );
				rebuildCategoryReversedAssociation();

				// Signal the modifications - removed first and additions last
				if( ! this.removedResources.isEmpty()) {
					for( IPetalsProjectResourceChangeListener listener : this.listeners )
						listener.resourcesRemoved( this.removedResources );
				}

				if( ! this.addedResources.isEmpty()) {
					for( IPetalsProjectResourceChangeListener listener : this.listeners )
						listener.resourcesAdded( this.addedResources );
				}

			} else if( event.getType() == IResourceChangeEvent.POST_BUILD ) {
				IMarkerDelta[] markerDeltas = event.findMarkerDeltas( null, true );
				for( IPetalsProjectResourceChangeListener listener : this.listeners )
					listener.markerChanged( markerDeltas );
			}

		} catch( CoreException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} finally {

			// Re-enable graphical updates
			for( IPetalsProjectResourceChangeListener listener : this.listeners )
				listener.terminateNotification();

			// Stop recording
			this.statisticsTimer.stop( " [ CHANGE ] " );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor
	 * #visit(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public boolean visit( IResourceDelta delta ) throws CoreException {

		// Update the project associations
		if( delta.getResource() instanceof IProject ) {

			// Addition
			if( delta.getKind() == IResourceDelta.ADDED ) {
				this.statisticsTimer.track( " [ CHANGE ] Starting project addition..." );
				refreshAssociation((IProject) delta.getResource(), false );
				this.addedResources.add( delta.getResource());
				this.statisticsTimer.track( " [ CHANGE ] Project addition propagated." );
			}

			// Removal
			else if( delta.getKind() == IResourceDelta.REMOVED ) {
				this.statisticsTimer.track( " [ CHANGE ] Starting project removal..." );
				refreshAssociation((IProject) delta.getResource(), false );
				this.removedResources.add( delta.getResource());
				this.statisticsTimer.track( " [ CHANGE ] Project removal propagated." );
			}

			// Other interesting modification
			else if(( delta.getFlags() & IResourceDelta.LOCAL_CHANGED) != 0
						|| ( delta.getFlags() & IResourceDelta.DESCRIPTION) != 0 ) {
				refreshAssociation((IProject) delta.getResource(), false );
			}
		}

		// Non-projects
		else {
			// Special treatment for Java packages
			Object javaFragment = PlatformUtils.getAdapter( delta.getResource(), IJavaElement.class );
			if( javaFragment instanceof IPackageFragment ) {
				for( IPetalsProjectResourceChangeListener listener : this.listeners )
					listener.elementChanged(((IPackageFragment) javaFragment).getParent());
			}

			// Default behavior for all the files and directories
			else if( delta.getKind() == IResourceDelta.ADDED )
				this.addedResources.add( delta.getResource());

			else if( delta.getKind() == IResourceDelta.REMOVED )
				this.removedResources.add( delta.getResource());

			// Addition or removal of a jbi.xml => update the project's associations
			if( "jbi.xml".equals( delta.getResource().getName())) {

				// We may have to add or remove a project from the view if the jbi.xml changes
				// To avoid uncomfortable issues for a user (like a wrong modification in a jbi.xml => wrong XML => the project disappears from the view),
				// we only monitor the apparition of valid jbi.xml files.
				// Such a case can occur with the JSR-181 (WSDL-first approach) or any other SU project which is not created with a (valid) jbi.xml.
				IProject p = delta.getResource().getProject();
				List<PetalsProjectCategory> cats = this.projectToCategories.get( p );
				boolean presentBefore = cats != null && ! cats.isEmpty();

				refreshAssociation( p, false );
				cats = this.projectToCategories.get( p );
				boolean presentAfter = cats != null && ! cats.isEmpty();

				if( ! presentBefore && presentAfter )
					this.addedResources.add( p );
			}
		}

		// Signal other modifications
		this.statisticsTimer.track( " [ CHANGE ] Starting other propagation..." );
		for( IPetalsProjectResourceChangeListener listener : this.listeners )
			listener.resourceChanged( delta );

		this.statisticsTimer.track( " [ CHANGE ] Other propagation done." );
		return delta.getResource() instanceof IContainer;
	}
}
