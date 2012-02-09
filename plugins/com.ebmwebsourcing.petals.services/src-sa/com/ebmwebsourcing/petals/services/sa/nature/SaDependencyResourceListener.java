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
package com.ebmwebsourcing.petals.services.sa.nature;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * A resource listener in charge of monitoring the dependencies of SA projects.
 * <p>
 * When one of its dependencies changes, the SA project is built.
 * The dependencies are always SU projects. If the JBI descriptor or
 * the POM are modified, the JBI descriptor of the SA will be validated.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaDependencyResourceListener implements IResourceChangeListener {

	/**
	 * The size of the blocking queue.
	 * <p>
	 * 1000 was chosen arbitrarily.<br />
	 * In fact, 1000 SA projects in a same workspace would be huge!
	 * </p>
	 */
	private static final int SA_PROJECTS_QUEUE_SIZE = 1000;

	/**
	 * The unique instance of this class.
	 */
	private static SaDependencyResourceListener instance;

	/**
	 * A blocking queue to store the SA projects whose dependencies must be watched.
	 */
	private final LinkedBlockingQueue<IProject> saProjects;

	/**
	 * A basic lock to avoid an infinite loop (build => post build event => build => ...).
	 */
	private final AtomicBoolean lock = new AtomicBoolean( false );


	/**
	 * Private constructor.
	 */
	private SaDependencyResourceListener() {
		this.saProjects = new LinkedBlockingQueue<IProject>( SA_PROJECTS_QUEUE_SIZE );
	}


	/**
	 * @return the unique instance of this class
	 */
	public synchronized static SaDependencyResourceListener getInstance() {
		if( instance == null ) {
			instance = new SaDependencyResourceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener( instance, IResourceChangeEvent.POST_BUILD );
		}

		return instance;
	}


	/**
	 * Registers a SA project whose dependencies must be monitored.
	 * @param saProject the SA project
	 *
	 */
	public void registerSaProject( IProject saProject ) {
		this.saProjects.add( saProject );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener
	 * #resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged( IResourceChangeEvent event ) {

		// Do not propagate builds that come from this builder.
		// Otherwise, we end up with an infinite loop.
		if( event.getSource() instanceof IProject
					&& ((IProject) event.getSource()).isAccessible()
					&& ServiceProjectRelationUtils.isSaProject((IProject) event.getSource()))
			return;


		// Check every project
		for( IProject saProject : this.saProjects ) {

			// Check the existence of the SA project
			if( ! saProject.exists()) {
				this.saProjects.remove( saProject );
				continue;
			}


			// Get the SA dependencies
			List<IProject> suProjects = null;
			if( saProject.isAccessible()) {
				try {
					suProjects = Arrays.asList( saProject.getReferencedProjects());

				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}

			// Determine whether a build should be done or not
			if( suProjects != null ) {

				// Check the modified resources
				DependencyDeltaVisitor deltaVisitor = new DependencyDeltaVisitor( suProjects );
				try {
					event.getDelta().accept( deltaVisitor );

				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}

				// Build?
				if( deltaVisitor.buildSaProject && ! this.lock.get()) {
					try {
						saProject.build( IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
						this.lock.set( true );

					} catch( CoreException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );
					}
				}
			}
		}

		this.lock.set( false );
	}


	/**
	 * The delta visitor used to determine whether a referenced SU project has changed.
	 */
	private static class DependencyDeltaVisitor implements IResourceDeltaVisitor {
		private final List<IProject> suProjects;
		private boolean buildSaProject = false;

		/**
		 * @param suProjects
		 */
		public DependencyDeltaVisitor( List<IProject> suProjects ) {
			this.suProjects = suProjects;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor
		 * #visit(org.eclipse.core.resources.IResourceDelta)
		 */
		@Override
		public boolean visit( IResourceDelta delta ) throws CoreException {

			// Get the resource.
			IResource resource = delta.getResource();
			boolean visit = false;
			if( resource != null ) {
				if( resource instanceof IWorkspaceRoot ) {
					visit = true;

				} else if( resource instanceof IProject
							&& this.suProjects.contains( resource )) {

					if( delta.getKind() == IResourceDelta.REMOVED
								|| (delta.getFlags() & IResourceDelta.LOCAL_CHANGED) != 0
								|| (delta.getFlags() & IResourceDelta.OPEN) != 0)
						this.buildSaProject = true;
					else
						visit = true;

				} else if( resource instanceof IFile ) {
					if( "pom.xml".equals( resource.getName())
								|| "jbi.xml".equals( resource.getName()))
						this.buildSaProject = true;

				} else {
					visit = PetalsConstants.LOC_JBI_FILE.startsWith( resource.getProjectRelativePath().toString());
				}
			}

			return visit;
		}
	}
}
