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

package com.ebmwebsourcing.petals.common.internal.provisional.builder;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A basis builder to validate jbi.xml files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class JbiXmlBuilder extends IncrementalProjectBuilder {

	/**
	 * Constructor.
	 */
	public JbiXmlBuilder() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	protected final IProject[] build( int kind, Map args, IProgressMonitor monitor )
			throws CoreException {

		if( kind == FULL_BUILD ) {
			fullBuild( monitor );
		} else {
			IResourceDelta delta = getDelta( getProject());
			if( delta == null )
				fullBuild( monitor );
			else
				incrementalBuild( delta, monitor );
		}

		return null;
	}


	/**
	 * Checks if the user selected the "cancel" button in the progress monitor.
	 * @param monitor
	 */
	protected final void checkCancel( IProgressMonitor monitor ) {

		if( monitor != null && monitor.isCanceled()) {
			forgetLastBuiltState();	// do a full build next time
			throw new OperationCanceledException();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #clean(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected final void clean( IProgressMonitor monitor ) throws CoreException {

		// Remove markers from the file.
		if( getProject() != null )
			getProject().deleteMarkers( PetalsConstants.MARKER_ID_JBI_XML, true, IResource.DEPTH_INFINITE );

		super.clean( monitor );
	}


	/**
	 * Builds the project incrementally.
	 * @param delta
	 * @param monitor
	 */
	protected abstract void incrementalBuild( IResourceDelta delta, IProgressMonitor monitor )
			throws CoreException;


	/**
	 * Makes a full build of the project.
	 * @param monitor
	 */
	protected abstract void fullBuild( IProgressMonitor monitor )
			throws CoreException;


	/**
	 * Validates a jbi.xml file.
	 * <p>
	 * The base implementation validates it with respect to the JBI meta-model.
	 * </p>
	 * 
	 * @param jbiXmlFile
	 */
	protected List<MarkerBean> validate( IFile jbiXmlFile ) {

		// General validation
		Diagnostic diagnostic;
		Jbi jbi = null;
		try {
			// Validate with respect to the JBI meta-model
			jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
			diagnostic = JbiXmlUtils.validateJbiXmlModel( jbi );

		} catch( InvalidJbiXmlException e ) {
			diagnostic = new BasicDiagnostic(
					Diagnostic.ERROR,
					jbiXmlFile.getLocation().toOSString(),
					0,
					"Invalid jbi.xml: the file could not be loaded and is probably a mal-formed XML document.",
					new Object[ 0 ]);
		}

		return MarkerBean.getMarkerBeans( diagnostic, jbiXmlFile );
	}


	/**
	 * Creates a marker from a {@link MarkerBean}.
	 */
	protected final void createFileMarker( MarkerBean markerBean ) {

		try {
			IMarker marker =  markerBean.getiFile().createMarker( PetalsConstants.MARKER_ID_JBI_XML );

			// The line number will be set later, by another Eclipse contributor
			marker.setAttribute( IMarker.LINE_NUMBER, 1 );
			marker.setAttribute( IMarker.PRIORITY, IMarker.PRIORITY_NORMAL );
			marker.setAttribute( IMarker.SEVERITY, markerBean.getSeverity());
			marker.setAttribute( IMarker.MESSAGE, markerBean.getMessage());
			marker.setAttribute( PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE,  markerBean.getxPathLocation());

		} catch( CoreException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Validates a jbi.xml.
	 * @param jbiXmlFile
	 */
	protected void validateAndMarkJbiXmlFile( IFile jbiXmlFile ) {

		if( jbiXmlFile.exists()) {

			// Remove markers from the file.
			try {
				jbiXmlFile.deleteMarkers( PetalsConstants.MARKER_ID_JBI_XML, true, IResource.DEPTH_ZERO );
			} catch( CoreException e ) {
				e.printStackTrace();
			}

			// Check that it's a valid XML file.
			List<MarkerBean> markerBeans = validate( jbiXmlFile );
			for( MarkerBean bean : markerBeans )
				createFileMarker( bean );

					// Update the line numbers
					resolveLineNumbers( jbiXmlFile );
		}
	}


	/**
	 * Resolves the line numbers for the errors marked with an XPATH location attribute.
	 * @param file the marked file
	 */
	public static void resolveLineNumbers( IFile file ) {
		MarkerUtils.resolveLineNumbers(
				file,
				PetalsConstants.MARKER_ID_JBI_XML,
				PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE );
	}
}
