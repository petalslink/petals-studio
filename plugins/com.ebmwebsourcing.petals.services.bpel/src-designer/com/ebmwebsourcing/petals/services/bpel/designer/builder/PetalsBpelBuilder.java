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

package com.ebmwebsourcing.petals.services.bpel.designer.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;

/**
 * @deprecated Kept for  backward compatibility but does nothing anymore
 * <p>
 * The EasyBPEL validation was added as an extension of the official BPEL validation of the
 * Eclipse BPEL Designer.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
@Deprecated
public class PetalsBpelBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelBuilder";
	public static final String MARKER_TYPE = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelProblem";
	public static final String MARKER_XPATH_LINE = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelXpathLine";


	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	protected IProject[] build( int kind, Map args, IProgressMonitor monitor )
	throws CoreException {

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #clean(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void clean( IProgressMonitor monitor ) throws CoreException {

		// Remove markers from the file.
		if( getProject() != null ) {
			getProject().deleteMarkers( MARKER_TYPE, true, IResource.DEPTH_INFINITE );

			// Remove markers from the milestones
			// TODO: delete this after the release
			getProject().deleteMarkers( PetalsBpelPlugin.PLUGIN_ID + ".petalsMaestroProblem", true, IResource.DEPTH_INFINITE );
		}

		super.clean( monitor );
	}


	/**
	 * Runs a full build.
	 * @param monitor
	 * @throws CoreException
	 */
	protected void fullBuild( final IProgressMonitor monitor ) throws CoreException {
		// nothing
	}

	/**
	 * Runs an incremental build.
	 * @param delta
	 * @param monitor
	 * @throws CoreException
	 */
	protected void incrementalBuild( IResourceDelta delta, final IProgressMonitor monitor )
	throws CoreException {
		// nothing
	}
}
