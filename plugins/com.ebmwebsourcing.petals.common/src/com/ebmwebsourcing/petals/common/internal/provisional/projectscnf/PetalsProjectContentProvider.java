/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
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
import com.ebmwebsourcing.petals.common.internal.projectscnf.EmptyJavaPackageFilter;
import com.ebmwebsourcing.petals.common.internal.projectscnf.IPetalsProjectResourceChangeListener;
import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;
import com.ebmwebsourcing.petals.common.internal.projectscnf.StatisticsTimer;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;

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
						list.addAll( findFlatChildren( element, (CommonViewer) this.viewer ));
					} else {
						list.addAll( findHierarchicalChildren( element, (CommonViewer) this.viewer));
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
						list.addAll( findFlatChildren( element, (CommonViewer) this.viewer ));
					} else {
						list.addAll( findHierarchicalChildren( element, (CommonViewer) this.viewer));
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

		// What do we have?
		IResource res = null;
		IJavaElement javaElement = null;
		boolean isInJavaProject = false;

		if( element instanceof IResource ) {
			res = (IResource) element;
			try {
				if( ((IResource) element).exists())
					isInJavaProject = res.getProject().hasNature( JavaCore.NATURE_ID );

				if( isInJavaProject )
					javaElement = JavaCore.create( res );

			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}

		} else if( element instanceof IJavaElement ) {
			isInJavaProject = true;
			javaElement = (IJavaElement) element;
			res = javaElement.getResource();
		}

		// Projects should return a category
		// Otherwise, selection won't work when we want to reveal a new project
		if( res instanceof IProject ) {
			List<PetalsProjectCategory> cats = PetalsProjectManager.INSTANCE.getCategories((IProject) res);
			return cats != null && cats.size() > 0 ? cats.get( 0 ) : null;
		}

		// If the parent is a project and this root project is not displayed, then return the Petals category
		if( res != null && res.getParent() instanceof IProject ) {
			Object parent = getParent( res.getParent());
			if( parent instanceof PetalsProjectCategory
						&& ! ((PetalsProjectCategory) parent).isRootProjectVisible())
				return parent;
		}

		// Java elements
		if( javaElement != null ) {
			// PETALSSTUD-165: Selection of Java resources fails for JSR-181
			if( element instanceof IPackageFragment ) {
				PetalsCnfPackageFragment ppf = PetalsProjectManager.INSTANCE.dirtyViewerMap.get( javaElement );
				if( ppf != null ) {
					if( ppf.getParent() instanceof PetalsCnfPackageFragment )
						return ((PetalsCnfPackageFragment) ppf.getParent()).getFragment();

					return ppf.getParent();
				}
			}

			IJavaElement elt = javaElement.getParent();
			if( elt instanceof IJavaProject )
				return ((IJavaProject) elt).getProject();

			return elt;
			// PETALSSTUD-165: Selection of Java resources fails for JSR-181
		}

		// Otherwise, return the parent
		if( res != null ) {
			// Be careful, the parent of a resource may sometimes be a Java element (e.g. a file in a package)
			res = res.getParent();
			javaElement = JavaCore.create( res );
			if( javaElement instanceof IJavaProject )
				return res.getProject();

			if( javaElement instanceof IPackageFragment || javaElement instanceof IPackageFragmentRoot )
				return javaElement;

			return res;
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
		PetalsProjectManager.INSTANCE.dirtyViewerMap.clear();
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
					if( element instanceof IPackageFragment
							&& ((IPackageFragment) element).isDefaultPackage())
						result = ((IPackageFragment) element).getNonJavaResources().length > 0 || ((IPackageFragment) element).hasChildren();
					else
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
				if( parent != null )
					parentToResources.put( parent, list );
				else
					PetalsCommonPlugin.log( "Could not find the parent for " + res, IStatus.ERROR );
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
	 * #resourceChanged(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public void elementChanged( Object viewerObject ) {
		this.runnables.add( getRefreshRunnable( viewerObject ));
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
	 * Returns a runnable for refreshing the markers on a resource.
	 * @param resource
	 * @return Runnable
	 */
	protected final Runnable getMarkerRefreshRunnable( final IResource resource ) {
		return new Runnable(){
			@Override
			public void run() {

				IResource res = resource;
				while( res != null && !( res instanceof IWorkspaceRoot )) {
					Object toRefresh;
					IJavaElement elt;

					if( res instanceof IProject )
						toRefresh = res;
					else if(( elt = JavaCore.create( res )) != null )
						toRefresh = elt;
					else
						toRefresh = res;

					((StructuredViewer) PetalsProjectContentProvider.this.viewer).refresh( toRefresh, true );
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
	 * Finds the children to display in <i>flat</i> mode.
	 * <p>
	 * This method is also in charge of filtering empty packages.
	 * </p>
	 *
	 * @param elt a package fragment or a package fragment root
	 * @param viewer the viewer
	 * @return a non-null list of children
	 * @throws JavaModelException
	 */
	private static List<Object> findFlatChildren( Object elt, CommonViewer viewer ) throws JavaModelException {

		// Include empty packages is handled in the content viewer
		boolean includeEmpty = true;
		for( ViewerFilter filter : viewer.getFilters()) {
			if( filter instanceof EmptyJavaPackageFilter )
				includeEmpty = false;
		}

		// Get the elements to show
		List<Object> children = new ArrayList<Object> ();
		Object[] javaChildren = null;
		if( elt instanceof IPackageFragment ) {
			javaChildren = ((IPackageFragment) elt).getChildren();
			Object[] nonJavaResources = ((IPackageFragment) elt).getNonJavaResources();
			children.addAll( Arrays.asList( nonJavaResources ));

		} else if( elt instanceof IPackageFragmentRoot ) {
			javaChildren = ((IPackageFragmentRoot) elt).getChildren();
			Object[] nonJavaResources = ((IPackageFragmentRoot) elt).getNonJavaResources();
			children.addAll( Arrays.asList( nonJavaResources ));

		} else {
			throw new JavaModelException( new Exception( "Expected a package fragment or package fragment root." ), IStatus.ERROR );
		}

		// Filter the Java elements
		if( includeEmpty ) {
			children.addAll( Arrays.asList( javaChildren ));

		} else {
			for( Object o : javaChildren ) {
				if( o instanceof IPackageFragment ) {
					if(((IPackageFragment) o).getNonJavaResources().length > 0
							|| ((IPackageFragment) o).getChildren().length > 0 )
						children.add( o );

				} else {
					children.add( o );
				}
			}
		}

		return children;
	}


	/**
	 * Finds the children to display in <i>hierarchical</i> mode.
	 * <p>
	 * This method is also in charge of filtering empty packages.
	 * </p>
	 *
	 * @param elt a package fragment or a package fragment root
	 * @param viewer the viewer
	 * @return a non-null list of children
	 * @throws JavaModelException
	 */
	private static List<Object> findHierarchicalChildren( Object elt, CommonViewer viewer ) throws JavaModelException {

		// Include empty packages is handled in the content viewer
		boolean includeEmpty = true;
		for( ViewerFilter filter : viewer.getFilters()) {
			if( filter instanceof EmptyJavaPackageFilter ) {
				includeEmpty = false;
				break;
			}
		}

		// Get the elements to show
		List<Object> children = new ArrayList<Object> ();
		List<Object> javaChildren = new ArrayList<Object> ();
		if( elt instanceof IPackageFragment ) {
			javaChildren.addAll( Arrays.asList(((IPackageFragment) elt).getChildren()));
			javaChildren.addAll( JavaUtils.findDirectSubPackages( null, (IPackageFragment) elt ));

			Object[] nonJavaResources = ((IPackageFragment) elt).getNonJavaResources();
			children.addAll( Arrays.asList( nonJavaResources ));

		} else if( elt instanceof IPackageFragmentRoot ) {
			javaChildren.addAll( Arrays.asList(((IPackageFragmentRoot) elt).getChildren()));
			Object[] nonJavaResources = ((IPackageFragmentRoot) elt).getNonJavaResources();
			children.addAll( Arrays.asList( nonJavaResources ));

		} else {
			throw new JavaModelException( new Exception( "Expected a package fragment or package fragment root." ), IStatus.ERROR );
		}

		// Filter the Java elements
		if( includeEmpty ) {
			children.addAll( javaChildren );
			List<IPackageFragment> subPackagesToHide = new ArrayList<IPackageFragment> ();	// for fragment roots
			for( Object child : javaChildren ) {
				if( child instanceof IPackageFragment )
					subPackagesToHide.addAll( JavaUtils.findDirectSubPackages( null, (IPackageFragment) child ));
			}

			children.removeAll( subPackagesToHide );

		} else {
			children.clear();
			children.addAll( findNonEmptyHierarchicalChildren( elt ));
		}

		// Associate every package fragment with a wrapper
		PetalsCnfPackageFragment parentFragment = null;
		if( elt instanceof IPackageFragment )
			parentFragment = PetalsProjectManager.INSTANCE.dirtyViewerMap.get( elt );

		for( Object child : children ) {
			if( child instanceof IPackageFragment ) {
				PetalsCnfPackageFragment fragment = new PetalsCnfPackageFragment((IPackageFragment) child, parentFragment );
				PetalsProjectManager.INSTANCE.dirtyViewerMap.put((IPackageFragment) child, fragment );
			}
		}

		return children;
	}


	/**
	 * Determines if a package fragment can be displayed.
	 * <p>
	 * This method is associated with the hierarchical mode with the empty package filter enabled.
	 * </p>
	 * <ul>
	 * <li>Packages that contain non-Java resources or Java resources must be displayed.</li>
	 * <li>Packages that only have sub-packages may be displayed only if they have at least two children with resources.</li>
	 * <li>Other packages cannot be displayed.</li>
	 * </ul>
	 *
	 * @param fragment the fragment to look at
	 * @param isFragmentRoot true if the original parent is a fragment root
	 * @return true if the fragment can be displayed, false otherwise
	 * @throws JavaModelException
	 */
	private static List<Object> findNonEmptyHierarchicalChildren( Object root ) throws JavaModelException {

		List<Object> children = new ArrayList<Object> ();

		// Handle the root element
		List<IPackageFragment> packagesToLook;
		if( root instanceof IPackageFragment ) {
			Object[] nonJavaResources = ((IPackageFragment) root).getNonJavaResources();
			children.addAll( Arrays.asList( nonJavaResources ));

			packagesToLook = JavaUtils.findDirectSubPackages( null, (IPackageFragment) root);
			for( IJavaElement elt : ((IPackageFragment) root).getChildren()) {
				if( !( elt instanceof IPackageFragment ))
					children.add( elt );
			}

		} else if( root instanceof IPackageFragmentRoot ) {
			 packagesToLook = JavaUtils.findDirectSubPackages((IPackageFragmentRoot) root, null );
			 Object[] nonJavaResources = ((IPackageFragmentRoot) root).getNonJavaResources();
			 children.addAll( Arrays.asList( nonJavaResources ));

		} else {
			throw new JavaModelException( new Exception( "Expected a package fragment or package fragment root." ), IStatus.ERROR );
		}

		// Sub-packages are visible
		while( ! packagesToLook.isEmpty()) {
			IPackageFragment f = packagesToLook.iterator().next();
			int pCpt = 0;
			boolean hasOtherChildren = false;

			List<Object> subChildren = findNonEmptyHierarchicalChildren( f );
			for( Object o : subChildren ) {
				if( o instanceof IPackageFragment )
					pCpt ++;
				else
					hasOtherChildren = true;
			}

			// If a sub-package is visible, then count it
			if( hasOtherChildren || pCpt > 1 )
				children.add( f );
			// Otherwise, try to see if one of its (sub-)sub-packages is visible
			else
				packagesToLook.addAll( JavaUtils.findDirectSubPackages( null, f ));

			packagesToLook.remove( f );
		}

		return children;
	}
}
