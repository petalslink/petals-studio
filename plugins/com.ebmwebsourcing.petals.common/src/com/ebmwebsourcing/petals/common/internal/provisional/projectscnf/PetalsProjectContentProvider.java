/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.projectscnf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.navigator.CommonViewer;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener;
import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;
import com.ebmwebsourcing.petals.common.internal.projectscnf.StatisticsTimer;

/**
 * The base content provider to use for all the contributions to the Petals projects view.
 * <p>
 * This content provider relies on a project manager, which caches and manages associations between
 * a project category and projects. This content provider supports resources and Java elements for
 * Java projects (in particular source folders and Java packages).
 * </p>
 * <p>
 * This content provider is notified every time a work space change is made.
 * Viewer modifications are grouped and executed in a separate thread to gain performances.
 * </p>
 *
 * <p>
 * Given the complexity of all the cases it has to handle, tests should be
 * automated for this content provider. These tests were performed manually for the
 * moment. There are listed here after.
 * </p>
 * <ul>
 * 	<li>SU project, not Java: create a file at the root.</li>
 * 	<li>SU project, not Java: delete a file at the root.</li>
 * 	<li>SU project, not Java: create a folder at the root.</li>
 * 	<li>SU project, not Java: delete a folder at the root.</li>
 *
 * 	<li>SU project, Java: create a file at the root.</li>
 * 	<li>SU project, Java: delete a file at the root.</li>
 * 	<li>SU project, Java: create a folder at the root.</li>
 * 	<li>SU project, Java: delete a folder at the root.</li>
 *
 * 	<li>SU project, Java: create a file in a source folder.</li>
 * 	<li>SU project, Java: delete a file in a source folder.</li>
 * 	<li>SU project, Java: create a folder in a source folder.</li>
 * 	<li>SU project, Java: delete a folder in a source folder.</li>
 * 	<li>SU project, Java: create a package in a source folder.</li>
 * 	<li>SU project, Java: delete a package in a source folder.</li>
 *
 * 	<li>SU project, Java: create a file in a Java package.</li>
 * 	<li>SU project, Java: delete a file in a Java package.</li>
 * 	<li>SU project, Java: create a folder in a Java package.</li>
 * 	<li>SU project, Java: delete a folder in a Java package.</li>
 * 	<li>SU project, Java: create a package in a Java package.</li>
 * 	<li>SU project, Java: delete a package in a Java package.</li>
 *
 * 	<li>SU project, Java: create a class file in an existing Java package.</li>
 * 	<li>SU project, Java: create a class file in a non-existing Java package.</li>
 * 	<li>SU project, Java: delete a class file from a Java package.</li>
 *
 * 	<li>Create a SU project with a wizard.</li>
 * 	<li>Create a SA project with a wizard.</li>
 * 	<li>Create a SL project with a wizard.</li>
 * 	<li>Create a component project with a wizard.</li>
 * 	<li>Delete a project from the workspace (not from the disk) and import it back in the workspace.</li>
 *
 * 	<li>(Sub-classes) Create an association between a SU and a SA project.</li>
 * 	<li>(Sub-classes) Delete the association between a SU and a SA project.</li>
 * 	<li>(Sub-classes) Delete a SA project which is associated with a SU project.</li>
 * 	<li>(Sub-classes) Delete a SU project which is associated with a SA project.</li>
 * </ul>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectContentProvider implements ITreeContentProvider, IPetalsProjectResourceChangeListener {

	protected Viewer viewer;
	protected final List<Runnable> runnables = new ArrayList<Runnable> ();


	/**
	 * Constructor.
	 */
	public PetalsProjectContentProvider() {
		PetalsProjectManager.INSTANCE.addListener( this );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren( Object element ) {

		StatisticsTimer statisticsTimer = new StatisticsTimer();
		statisticsTimer.setEnabled( false );
		statisticsTimer.start( " [ CHILDREN ] " );

		String child;
		if( element instanceof IResource )
			child = ((IResource) element).getName();
		else if( element instanceof PetalsProjectCategory )
			child = ((PetalsProjectCategory) element).getLabel();
		else
			child = "";

		statisticsTimer.track( " [ CHILDREN ] Getting children for " + element.getClass().getSimpleName() + " : " + child );

		try {
			if( element instanceof IResource
						&& ! ((IResource) element).isAccessible())
				return new Object[ 0 ];

			// Work space root is not handled here but in another content provider
			// Categories
			if( element instanceof PetalsProjectCategory ) {
				List<IProject> projects = PetalsProjectManager.INSTANCE.getProjects((PetalsProjectCategory) element);
				if( projects == null )
					return new Object[ 0 ];

				if(((PetalsProjectCategory) element).isRootProjectVisible())
					return projects.toArray();

				List<Object> children = new ArrayList<Object> ();
				for( IProject project : projects )
					children.addAll( Arrays.asList( getChildren( project )));

				return children.toArray();
			}

			// Package fragment root
			if( element instanceof IPackageFragmentRoot ) {
				try {
					List<Object> list = new ArrayList<Object> ();
					if( PetalsProjectManager.isJavaLayoutFlat()) {
						Object[] nonJavaResources = ((IPackageFragmentRoot) element).getNonJavaResources();
						Object[] javaChildren = ((IPackageFragmentRoot) element).getChildren();
						list.addAll( Arrays.asList( nonJavaResources ));
						list.addAll( Arrays.asList( javaChildren ));

					} else {
						list.addAll( findHierarchicalChildren((IPackageFragmentRoot) element, (CommonViewer) this.viewer));
					}

					return list.toArray();

				} catch( JavaModelException e ) {
					PetalsCommonPlugin.log( e, IStatus.ERROR );
				}
			}

			// Package fragment
			if( element instanceof IPackageFragment )  {
				try {
					List<Object> list = new ArrayList<Object> ();
					if( PetalsProjectManager.isJavaLayoutFlat()) {
						Object[] nonJavaResources = ((IPackageFragment) element).getNonJavaResources();
						Object[] javaChildren = ((IPackageFragment) element).getChildren();
						list.addAll( Arrays.asList( nonJavaResources ));
						list.addAll( Arrays.asList( javaChildren ));

					} else {
						list.addAll( findHierarchicalChildren((IPackageFragment) element, (CommonViewer) this.viewer));
					}

					return list.toArray();

				} catch( JavaModelException e ) {
					PetalsCommonPlugin.log( e, IStatus.ERROR );
				}
			}

			// Other resources
			if( element instanceof IContainer ) {

				// Prepare the main variables
				IProject project = ((IContainer) element).getProject();
				List<Object> children = new ArrayList<Object> ();
				IJavaProject javaProject = null;
				try {
					if( project.hasNature( JavaCore.NATURE_ID ))
						javaProject = JavaCore.create( project );

				} catch( CoreException e1 ) {
					PetalsCommonPlugin.log( e1, IStatus.ERROR );
				}

				// Java projects have a special treatment
				try {
					if( javaProject != null && element instanceof IProject )
						children.addAll( Arrays.asList( javaProject.getAllPackageFragmentRoots()));

				} catch( CoreException e1 ) {
					PetalsCommonPlugin.log( e1, IStatus.ERROR );
				}

				// Default behavior
				try {
					List<IResource> members = Arrays.asList(((IContainer) element).members());
					if( javaProject != null ) {
						for( IResource res : members ) {
							if( JavaCore.create( res ) == null )
								children.add( res );
						}
					}
					else
						children.addAll( members );

				} catch( CoreException e ) {
					PetalsCommonPlugin.log( e, IStatus.ERROR );
				}

				return children.toArray();
			}

			return new Object[ 0 ];

		} finally {
			statisticsTimer.stop( " [ CHILDREN ] " );
		}
	}


	/**
	 * Finds one of the categories displayed by this content provider.
	 * @param id the ID of the category to find
	 * @return the {@link PetalsProjectCategory} or null if the ID does not match anything
	 */
	protected PetalsProjectCategory getProjectCategoryById( String id ) {

		PetalsProjectCategory result = null;
		for( PetalsProjectCategory cat : PetalsProjectManager.INSTANCE.getProjectCategories()) {
			if( id.equals( cat.getId())) {
				result = cat;
				break;
			}
		}

		return result;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getParent(java.lang.Object)
	 */
	@Override
	public Object getParent( Object element ) {

		if( element instanceof PetalsProjectCategory )
			return null;

		IResource res = null;
		if( element instanceof IResource )
			res = (IResource) element;

		else if( element instanceof IJavaProject )
			res = ((IJavaProject) element).getProject();

		// Projects should return a category
		// Otherwise, selection won't work when we want to reveal a new project
		if( res instanceof IProject ) {
			List<PetalsProjectCategory> cats = PetalsProjectManager.INSTANCE.getCategories((IProject) res);
			if( cats != null && cats.size() > 0 )
				return cats.get( 0 );
			else
				return null;
		}

		// If the parent is a project and this root project is not displayed, then return the Petals category
		if( res != null && res.getParent() instanceof IProject ) {
			Object parent = getParent( res.getParent());
			if( parent instanceof PetalsProjectCategory
						&& ! ((PetalsProjectCategory) parent).isRootProjectVisible())
				return parent;
		}

		// Otherwise, return the parent
		if( res != null )
			return res.getParent();

		if( element instanceof IJavaElement ) {
			// PETALSSTUD-165: Selection of Java resources fails for JSR-181
			IJavaElement elt = ((IJavaElement) element).getParent();
			if( elt instanceof IJavaProject )
				return ((IJavaProject) elt).getProject();

			// PETALSSTUD-165: Selection of Java resources fails for JSR-181
			return elt;
		}

		return null;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
		this.viewer = viewer;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #dispose()
	 */
	@Override
	public void dispose() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements( Object inputElement ) {
		return new Object[ 0 ];
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren( Object element ) {

		StatisticsTimer statisticsTimer = new StatisticsTimer();
		statisticsTimer.setEnabled( false );
		statisticsTimer.start( " [ HAS CHILDREN ] " );

		String child;
		if( element instanceof IResource )
			child = ((IResource) element).getName();
		else if( element instanceof PetalsProjectCategory )
			child = ((PetalsProjectCategory) element).getLabel();
		else
			child = "";

		statisticsTimer.track( " [ HAS CHILDREN ] Element: " + element.getClass().getSimpleName() + " : " + child );

		try {
			boolean result = false;
			try {
				if( element instanceof IContainer ) {
					result = ((IContainer) element).isAccessible()
					&& ((IContainer) element).members().length > 0;

				} else if( element instanceof IJavaElement ) {
					result = ((IJavaElement) element).getJavaModel().hasChildren();

				} else if( element instanceof PetalsProjectCategory ) {
					List<IProject> projects = PetalsProjectManager.INSTANCE.getProjects((PetalsProjectCategory) element);
					result = projects != null && projects.size() > 0;
				}


			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}

			return result;

		} finally {
			statisticsTimer.stop( " [ HAS CHILDREN ] " );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #resourcesAdded(java.util.Collection)
	 */
	@Override
	public void resourcesAdded( final Collection<IResource> resources ) {

		if( !( this.viewer instanceof AbstractTreeViewer ))
			return;

		// Compute as much things as possible outside the UI thread
		// Group elements to reduce the number of runnables
		List<IProject> projects = new ArrayList<IProject> ();
		Map<Object,List<IResource>> parentToResources = new HashMap<Object,List<IResource>> ();
		for( final IResource res : resources ) {
			if( res instanceof IProject ) {
				projects.add((IProject) res);

			} else {
				final Object parent = getParent( res );
				List<IResource> list = parentToResources.get( parent );
				if( list == null )
					list = new ArrayList<IResource>();

				list.add( res );
				parentToResources.put( parent, list );
			}
		}

		// Update the viewer
		final AbstractTreeViewer treeViewer = (AbstractTreeViewer) this.viewer;

		// Projects
		for( final IProject p : projects ) {
			List<PetalsProjectCategory> cats = PetalsProjectManager.INSTANCE.getCategories( p );
			if( cats == null )
				cats = Collections.emptyList();

			for( final PetalsProjectCategory cat : cats ) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						boolean visible = true;
						if( treeViewer.getFilters() != null ) {

							for( int i=0; visible && i<treeViewer.getFilters().length; i++ ) {
								ViewerFilter filter = treeViewer.getFilters()[ i ];
								visible = filter.select( treeViewer, cat, p );
							}
						}

						if( visible ) {
							treeViewer.getControl().setRedraw( false );
							treeViewer.add( cat, p );
							treeViewer.getControl().setRedraw( true );
						}
					}
				};

				this.runnables.add( runnable );
			}
		}

		// Other resources
		for( final Map.Entry<Object,List<IResource>> entry : parentToResources.entrySet()) {

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					treeViewer.getControl().setRedraw( false );
					treeViewer.add( entry.getKey(), entry.getValue().toArray());
					treeViewer.getControl().setRedraw( true );
				}
			};

			this.runnables.add( runnable );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #resourcesRemoved(java.util.Collection)
	 */
	@Override
	public void resourcesRemoved( final Collection<IResource> resources ) {

		if( !( this.viewer instanceof AbstractTreeViewer ))
			return;

		// Compute as much things as possible outside the UI thread
		// Group elements to reduce the number of runnables
		Map<Object,List<IResource>> parentToResources = new HashMap<Object,List<IResource>> ();
		for( final IResource res : resources ) {
			final Object parent = getParent( res );
			List<IResource> list = parentToResources.get( parent );
			if( list == null )
				list = new ArrayList<IResource>();

			list.add( res );
			parentToResources.put( parent, list );
		}

		// Update the viewer
		final AbstractTreeViewer treeViewer = (AbstractTreeViewer) this.viewer;
		for( final Map.Entry<Object,List<IResource>> entry : parentToResources.entrySet()) {

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					treeViewer.getControl().setRedraw( false );
					if( entry.getKey() == null )
						treeViewer.remove( entry.getValue().toArray());
					else
						treeViewer.remove( entry.getKey(), entry.getValue().toArray());
					treeViewer.getControl().setRedraw( true );
				}
			};

			this.runnables.add( runnable );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #resourceChanged(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public void resourceChanged( IResourceDelta delta ) {

		if(( delta.getFlags() & IResourceDelta.TYPE ) != 0 )
			this.runnables.add( getRefreshRunnable( delta.getResource()));

		if(( delta.getFlags() & IResourceDelta.OPEN ) != 0 ) {
			if( delta.getResource().isAccessible())
				this.runnables.add( getUpdateRunnable( delta.getResource()));
			else
				this.runnables.add( getRefreshRunnable( delta.getResource()));
		}

		if(( delta.getFlags()
					& (IResourceDelta.SYNC | IResourceDelta.DESCRIPTION)) != 0 )
			this.runnables.add( getUpdateRunnable( delta.getResource()));

		if(( delta.getFlags() & IResourceDelta.REPLACED ) != 0 )
			this.runnables.add( getRefreshRunnable( delta.getResource()));
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #markerChanged(org.eclipse.core.resources.IMarkerDelta[])
	 */
	@Override
	public void markerChanged( IMarkerDelta[] markerDeltas ) {

		for( IMarkerDelta delta : markerDeltas )
			this.runnables.add( getMarkerRefreshRunnable( delta.getResource()));
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #prepareNotification()
	 */
	@Override
	public void prepareNotification() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener
	 * #terminateNotification()
	 */
	@Override
	public void terminateNotification() {

		if( this.viewer.getControl() == null
				|| this.viewer.getControl().isDisposed()
				|| this.runnables.isEmpty())
			return;

		// Duplicate the list to avoid concurrent modifications
		final List<Runnable> myRunnables = new ArrayList<Runnable> ();
		myRunnables.addAll( this.runnables );

		// Synchronous execution - not asynchronous because otherwise, it is not
		// possible to select resources at the end of a wizard (refresh runnables may be executed after the selection).
		// And the selected element may not be visible at the time when it is tried to be selected.
		this.viewer.getControl().getDisplay().syncExec( new Runnable(){
			@Override
			public void run() {
				Control ctrl = PetalsProjectContentProvider.this.viewer.getControl();
				if( ctrl != null && ! ctrl.isDisposed()) {
					for( Runnable runnable : myRunnables )
						runnable.run();
				}
			}
		});

		// We can clear them now
		this.runnables.clear();
	}


	/**
	 * Return a runnable for refreshing a resource.
	 * @param element the element to refresh
	 * @return Runnable
	 */
	protected final Runnable getRefreshRunnable( final Object element ) {
		return new Runnable(){
			@Override
			public void run() {
				((StructuredViewer) PetalsProjectContentProvider.this.viewer).refresh( element, true );
			}
		};
	}


	/**
	 * Return a runnable for refreshing the markers on a resource.
	 * @param resource
	 * @return Runnable
	 */
	protected final Runnable getMarkerRefreshRunnable( final IResource resource ) {
		return new Runnable(){
			@Override
			public void run() {

				IResource res = resource;
				while( res != null && !( res.getParent() instanceof IWorkspaceRoot )) {
					((StructuredViewer) PetalsProjectContentProvider.this.viewer).refresh( res, true );
					res = res.getParent();
				}
			}
		};
	}


	/**
	 * Return a runnable for updating a resource.
	 * @param resource
	 * @return Runnable
	 */
	protected final Runnable getUpdateRunnable( final IResource resource ) {
		return new Runnable(){
			@Override
			public void run() {
				((StructuredViewer) PetalsProjectContentProvider.this.viewer).update( resource, null );
			}
		};
	}


	/**
	 * Finds all the children for a package fragment.
	 * <p>
	 * This method checks against viewer filters if an element should be part of the result.
	 * This is necessary, as an example, for the "empty package" filter.
	 * </p>
	 *
	 * @param root the package fragment root (not null)
	 * @param viewer the viewer (not null)
	 * @return a non-null list of children (possibly empty)
	 * @throws JavaModelException
	 */
	private static List<Object> findHierarchicalChildren( IPackageFragmentRoot root, CommonViewer viewer )
	throws JavaModelException {

		// Get the direct children
		List<Object> children = findDirectChildren( root );

		// Are they visible?
		List<Object> toRemove = new ArrayList<Object> ();
		for( ViewerFilter filter : viewer.getFilters()) {
			for( Object o : children ) {
				if( ! filter.select( viewer, root, o ))
					toRemove.add( o );
			}
		}

		// Update the direct children
		children.removeAll( toRemove );

		// And add the sub-children
		for( Object o : toRemove ) {
			if( o instanceof IPackageFragment )
				children.addAll( findHierarchicalChildren((IPackageFragment) o, viewer ));
		}

		return children;
	}


	/**
	 * Finds all the children for a package fragment.
	 * <p>
	 * This method checks against viewer filters if an element should be part of the result.
	 * This is necessary, as an example, for the "empty package" filter.
	 * </p>
	 *
	 * @param fragment the package fragment (not null)
	 * @param viewer the viewer (not null)
	 * @return a non-null list of children (possibly empty)
	 * @throws JavaModelException
	 */
	private static List<Object> findHierarchicalChildren( IPackageFragment fragment, CommonViewer viewer )
	throws JavaModelException {

		// Get the direct children
		List<Object> children = findDirectChildren( fragment );

		// Are they visible?
		List<Object> toRemove = new ArrayList<Object> ();
		for( ViewerFilter filter : viewer.getFilters()) {
			for( Object o : children ) {
				if( ! filter.select( viewer, fragment, o ))
					toRemove.add( o );
			}
		}

		// Update the direct children
		children.removeAll( toRemove );

		// And add the sub-children
		for( Object o : toRemove ) {
			if( o instanceof IPackageFragment )
				children.addAll( findHierarchicalChildren((IPackageFragment) o, viewer ));
		}

		return children;
	}


	/**
	 * Finds the direct children for a package fragment (non Java resources, compilation units, sub-packages).
	 * @param fragment the fragment (not null)
	 * @return a non-null list of children
	 * @throws JavaModelException
	 */
	private static List<Object> findDirectChildren( IPackageFragment fragment  )
	throws JavaModelException {

		List<Object> result = new ArrayList<Object> ();
		result.addAll( Arrays.asList( fragment.getNonJavaResources()));
		result.addAll( findDirectSubPackages( null, fragment ));

		for( Object child : fragment.getChildren()) {
			if( !( child instanceof IPackageFragment ))
				result.add( child );
		}

		return result;
	}


	/**
	 * Finds the direct children for a package fragment root (non Java resources, compilation units, sub-packages).
	 * @param root the fragment (not null)
	 * @return a non-null list of children
	 * @throws JavaModelException
	 */
	private static List<Object> findDirectChildren( IPackageFragmentRoot root  )
	throws JavaModelException {

		List<Object> result = new ArrayList<Object> ();
		result.addAll( Arrays.asList( root.getNonJavaResources()));
		result.addAll( findDirectSubPackages( root, null ));

		for( Object child : root.getChildren()) {
			if( !( child instanceof IPackageFragment ))
				result.add( child );
		}

		return result;
	}


	/**
	 * Finds the direct children for a given package.
	 * <p>
	 * Copied (or almost) from the JDT.
	 * </p>
	 *
	 * @param parent the package fragment root (will be searched from <code>fragment</code> if null)
	 * @param fragment the fragment to display (can be null if <code>parent</code> is not)
	 * @return a list of Java elements to display
	 * @throws JavaModelException if an error occurred with the Java model
	 */
	private static List<IPackageFragment> findDirectSubPackages( IPackageFragmentRoot parent, IPackageFragment fragment )
	throws JavaModelException {

		// Find the package parent?
		if( parent == null ) {
			IJavaElement elt = fragment;
			while( elt.getParent() != null ) {
				elt = elt.getParent();
				if( elt instanceof IPackageFragmentRoot ) {
					parent = (IPackageFragmentRoot) elt;
					break;
				}
			}
		}

		// Find the direct children
		List<IPackageFragment> result = new ArrayList<IPackageFragment> ();
		if( parent != null ) {
			IJavaElement[] children = parent.getChildren();
			String prefix = fragment != null ? fragment.getElementName() + '.' : ""; //$NON-NLS-1$
			int prefixLen = prefix.length();
			for( IJavaElement element : children ) {
				IPackageFragment curr = (IPackageFragment) element;
				String name = curr.getElementName();
				if( name.startsWith( prefix )
							&& name.length() > prefixLen
							&& name.indexOf( '.', prefixLen ) == -1 )
					result.add( curr );
				else if( fragment == null && curr.isDefaultPackage())
					result.add( curr );
			}
		}

		return result;
	}
}
