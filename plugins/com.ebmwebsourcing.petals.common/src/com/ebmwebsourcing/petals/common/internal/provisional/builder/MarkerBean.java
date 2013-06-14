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

package com.ebmwebsourcing.petals.common.internal.provisional.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MarkerBean {

	private final int severity;
	private final String message, xPathLocation;
	private final IFile iFile;



	/**
	 * Constructor.
	 * @param severity
	 * @param message
	 * @param eobject
	 * @param iFile
	 */
	public MarkerBean( int severity, String message, EObject eobject, IFile iFile ) {

		this.severity = severity;
		this.message = message;
		this.xPathLocation = EmfUtils.getXpathExpression( eobject );
		this.iFile = iFile;
	}

	/**
	 * Constructor.
	 * @param severity
	 * @param message
	 * @param xPathLocation
	 * @param iFile
	 */
	public MarkerBean( int severity, String message, String xPathLocation, IFile iFile ) {

		this.severity = severity;
		this.message = message;
		this.xPathLocation = xPathLocation;
		this.iFile = iFile;
	}


	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return this.severity;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}


	/**
	 * @return the xPathLocation
	 */
	public String getxPathLocation() {
		return this.xPathLocation;
	}


	/**
	 * @return the iFile
	 */
	public IFile getiFile() {
		return this.iFile;
	}


	/**
	 * A convenience method to build marker beans from an EMF diagnostic.
	 * @param diagnostic
	 * @param iFile
	 * @return
	 */
	public static List<MarkerBean> getMarkerBeans( Diagnostic diagnostic, IFile iFile ) {

		List<MarkerBean> result = new ArrayList<MarkerBean> ();

		// Diagnostic has no child.
		if( diagnostic.getChildren() == null || diagnostic.getChildren().isEmpty()) {
			if( diagnostic.getSeverity() != Diagnostic.OK ) {

				try {
					int severity;
					if( diagnostic.getSeverity() < Diagnostic.WARNING )
						severity = IMarker.SEVERITY_INFO;
					else if( diagnostic.getSeverity() < Diagnostic.ERROR )
						severity = IMarker.SEVERITY_WARNING;
					else
						severity = IMarker.SEVERITY_ERROR;

					String message = diagnostic.getMessage();
					message += message.endsWith( "." ) ? "" : "."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					String xpath = "";
					List<?> data = diagnostic.getData();
					if( data != null && ! data.isEmpty()) {
						Object o = diagnostic.getData().get( 0 );
						if( o != null && o instanceof EObject )
							xpath = EmfUtils.getXpathExpression((EObject) o);
					}

					result.add( new MarkerBean( severity, message, xpath, iFile ));

				} catch( Exception e ) {
					PetalsCommonPlugin.log( e, IStatus.WARNING );
				}
			}
		}

		// Diagnostic has children.
		else {
			for( Diagnostic childDiagnostic : diagnostic.getChildren() ) {
				try {
					int severity;
					if( childDiagnostic.getSeverity() < Diagnostic.WARNING )
						severity = IMarker.SEVERITY_INFO;
					else if( childDiagnostic.getSeverity() < Diagnostic.ERROR )
						severity = IMarker.SEVERITY_WARNING;
					else
						severity = IMarker.SEVERITY_ERROR;

					String message = childDiagnostic.getMessage();
					message += message.endsWith( "." ) ? "" : "."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

					String xpath = "";
					List<?> data = childDiagnostic.getData();
					if( data != null && ! data.isEmpty()) {
						Object o = childDiagnostic.getData().get( 0 );
						if( o != null && o instanceof EObject )
							xpath = EmfUtils.getXpathExpression((EObject) o);
					}

					result.add( new MarkerBean( severity, message, xpath, iFile ));

				} catch( Exception e ) {
					PetalsCommonPlugin.log( e, IStatus.WARNING );
				}
			}
		}

		return result;
	}


	/**
	 * Determines whether there is a marker bean with an ERROR severity in the collection.
	 * @param markerBeans a collection of marker beans
	 * @return true if at least one bean has a severe error
	 */
	public static boolean containsCriticalError( Collection<MarkerBean> markerBeans ) {

		boolean result = false;
		for( MarkerBean bean : markerBeans ) {
			if( bean.severity == IMarker.SEVERITY_ERROR ) {
				result = true;
				break;
			}
		}

		return result;
	}
}
