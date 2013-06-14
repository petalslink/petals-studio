/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.nature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.ebmwebsourcing.petals.common.internal.provisional.builder.JbiXmlBuilder;
import com.ebmwebsourcing.petals.common.internal.provisional.builder.MarkerBean;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.ServiceUnit;

/**
 * An incremental builder to validate jbi.xml files of SA projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaBuilder extends JbiXmlBuilder {

	/**
	 * The ID of this builder.
	 */
	public static final String BUILDER_ID = "com.ebmwebsourcing.petals.services.saBuilder";	 //$NON-NLS-1$



	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #startupOnInitialize()
	 */
	@Override
	protected void startupOnInitialize() {
		super.startupOnInitialize();
		SaDependencyResourceListener.getInstance().registerSaProject( getProject());
	}


	/**
	 * Runs a full build.
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	protected void fullBuild( final IProgressMonitor monitor ) throws CoreException {

		// Build...
		try {
			clean( monitor );
			getProject().accept( new SaResourceVisitor( monitor ));

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Runs an incremental build.
	 * @param delta
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	protected void incrementalBuild( IResourceDelta delta, IProgressMonitor monitor)
			throws CoreException {
		delta.accept( new SaDeltaVisitor( monitor ));
	}


	/**
	 * The delta visitor used for the incremental build.
	 */
	private class SaDeltaVisitor implements IResourceDeltaVisitor {
		private final IProgressMonitor monitor;

		/**
		 * @param monitor
		 */
		public SaDeltaVisitor( IProgressMonitor monitor ) {
			this.monitor = monitor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor
		 * #visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit( IResourceDelta delta ) throws CoreException {

			// Check for cancellation.
			checkCancel( this.monitor );

			// Get the resource.
			IResource resource = delta.getResource();
			if( resource == null )
				return false;

			// Do the job: check if the META-INF/jbi.xml file was modified and update project properties.
			boolean visitChildren = visitResource( resource );
			if( this.monitor != null )
				this.monitor.worked( 1 );

			// Return true to continue visiting children.
			return visitChildren;
		}
	}


	/**
	 * Checks whether this resource must be validated or not.
	 * <p>
	 * If the resource is a container, true is returned.<br />
	 * If the resource is a jbi.xml file, it is validated and marked if necessary.<br />
	 * Otherwise, the resource is ignored.
	 * </p>
	 * 
	 * @param resource the resource to check
	 * @return true if children resources should be visited, false otherwise
	 */
	private boolean visitResource( IResource resource ) {

		if( resource instanceof IContainer )
			return true;

		// We only want one the file "jbi.xml".
		if( resource instanceof IFile
				&& "jbi.xml".equals( resource.getName()))  //$NON-NLS-1$
			validateAndMarkJbiXmlFile((IFile) resource);

		return false;
	}


	/**
	 * The resource visitor for the full build.
	 */
	private class SaResourceVisitor implements IResourceVisitor {
		private final IProgressMonitor monitor;

		/**
		 * @param monitor
		 */
		public SaResourceVisitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceVisitor
		 * #visit(org.eclipse.core.resources.IResource)
		 */
		public boolean visit( IResource resource ) {

			// Check for cancellation.
			checkCancel( this.monitor );

			// Do the job: check if the META-INF/jbi.xml file was modified and update project properties.
			boolean visitChildren = visitResource( resource );
			if( this.monitor != null )
				this.monitor.worked( 1 );

			// Return true to continue visiting children.
			return visitChildren;
		}
	}


	/**
	 * Validates a jbi.xml file.
	 * @param jbiXmlFile
	 */
	@Override
	protected List<MarkerBean> validate( IFile jbiXmlFile ) {

		// This builder can only work if the project has the SU nature
		// MM validation
		List<MarkerBean> markerBeans = super.validate( jbiXmlFile );
		if( ! markerBeans.isEmpty())
			return markerBeans;


		// Check the file position
		IFile expectedFile = null;
		try {
			expectedFile = JbiXmlUtils.getJbiXmlFile( jbiXmlFile.getProject());
		} catch( FileNotFoundException e1 ) {
			// nothing
		}

		Jbi jbi = null;
		try {
			jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		if( expectedFile == null || ! jbiXmlFile.equals( expectedFile )) {
			markerBeans.add( new MarkerBean(
					IMarker.SEVERITY_INFO,
					"This jbi.xml file is not placed at one of the expected locations (" + PetalsConstants.LOC_JBI_FILE + ").",
					jbi,
					jbiXmlFile ));
		}


		// Get the SU
		List<IProject> suDependencies = new ArrayList<IProject> ();
		if( jbi != null
				&&jbi.getServiceAssembly() != null
				&& jbi.getServiceAssembly().getServiceUnit() != null ) {

			IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
			for( ServiceUnit su : jbi.getServiceAssembly().getServiceUnit()) {

				String suName = su.getIdentification() != null ? su.getIdentification().getName() : "";
				IProject p = iwr.getProject( suName );
				if( ! p.exists()) {
					MarkerBean bean = new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"No matching Service Unit project could be found in the workspace.",
							su, jbiXmlFile );
					markerBeans.add( bean );

					// Add the reference anyway (in case where the project would be imported later)
					suDependencies.add( p );

				} else if( suDependencies.contains( p )) {
					MarkerBean bean = new MarkerBean(
							IMarker.SEVERITY_ERROR,
							"Duplicate reference to this Service Unit.",
							su, jbiXmlFile );
					markerBeans.add( bean );

				} else {
					// SU project?
					File suJbiXmlFile = p.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
					boolean isSu = suJbiXmlFile.exists() && JbiXmlUtils.describesServiceUnit( suJbiXmlFile );
					if( ! isSu ) {
						MarkerBean bean = new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The referenced service unit is not a Service Unit project.",
								su, jbiXmlFile );
						markerBeans.add( bean );
					}
					else {
						// Referenced by several SA?
						if( ServiceProjectRelationUtils.isReferencedBySeveralSa( p )) {
							MarkerBean bean = new MarkerBean(
									IMarker.SEVERITY_INFO,
									"The service unit " + p.getName() + " is included into several Service Assembly projects.",
									su, jbiXmlFile );
							markerBeans.add( bean );
						}

						// Closed SU project?
						if( ! p.isAccessible()) {
							MarkerBean bean = new MarkerBean(
									IMarker.SEVERITY_INFO,
									"The service unit project " + p.getName() + " is closed and thus cannot be exported.",
									su, jbiXmlFile );
							markerBeans.add( bean );
						}
					}

					// Add the reference anyway (in case where the project would be imported later)
					suDependencies.add( p );
				}
			}
		}


		// Add the dependencies
		IProgressMonitor monitor = new NullProgressMonitor();
		try {
			IProjectDescription desc = jbiXmlFile.getProject().getDescription();
			IProject[] projects = new IProject[ suDependencies.size()];
			desc.setReferencedProjects( suDependencies.toArray( projects ));
			jbiXmlFile.getProject().setDescription( desc, monitor );

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}


		// Update the Maven dependencies
		if( PreferencesManager.regeneratePomAutomatically()) {

			// Granted permission => get the SU's POM
			List<File> suPoms = new ArrayList<File>( suDependencies.size());
			for( IProject p : suDependencies ) {
				if( p.isAccessible()) {
					IFile pomFile = p.getFile( PetalsConstants.LOC_POM_FILE );
					if( pomFile.exists())
						suPoms.add( pomFile.getLocation().toFile());
				}
			}

			// Update the SA's POM
			IFile saPomFile = jbiXmlFile.getProject().getFile( PetalsConstants.LOC_POM_FILE );
			MavenUtils.setPomDependencies( saPomFile.getLocation().toFile(), suPoms );
			try {
				saPomFile.refreshLocal( IResource.DEPTH_ZERO, monitor );

			} catch( CoreException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}
		}

		return markerBeans;
	}
}
