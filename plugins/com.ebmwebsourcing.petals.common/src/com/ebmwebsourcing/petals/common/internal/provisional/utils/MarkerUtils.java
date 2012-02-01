/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MarkerUtils {

	/**
	 * Gets the {@link IMessageProvider} constant from an {@link IMarker} severity constant.
	 * @param markerSeverity
	 * @return
	 */
	public static int getMessageSeverityFromMarkerSeverity( int markerSeverity ) {

		int type;
		switch( markerSeverity ) {
		case IMarker.SEVERITY_ERROR:
			type = IMessageProvider.ERROR;
			break;
		case IMarker.SEVERITY_WARNING:
			type = IMessageProvider.WARNING;
			break;
		case IMarker.SEVERITY_INFO:
			type = IMessageProvider.INFORMATION;
			break;
		default:
			type = IMessageProvider.NONE;
		}

		return type;
	}


	/**
	 * Gets the maximum severity.
	 * @param markers a list of markers (not null)
	 * @return the maximum severity
	 * @see IStatus
	 */
	public static int getMaximumSeverity( List<IMarker> markers ) {

		int result = IStatus.OK;
		loop: for( IMarker marker : markers ) {
			int severity = marker.getAttribute( IMarker.SEVERITY, IStatus.OK );
			switch( severity ) {
			case IMarker.SEVERITY_ERROR:
				result = IStatus.ERROR;
				break loop;
			case IMarker.SEVERITY_WARNING:
				result = IStatus.WARNING;
				break;
			case IMarker.SEVERITY_INFO:
				if( result == IStatus.OK )
					result = IStatus.INFO;
				break;
			}
		}

		return result;
	}



	/**
	 * Not sensitive to name spaces (for instance, but not sure either it will be one day).
	 */
	private static XPath X_PATH = XPathFactory.newInstance().newXPath();


	/**
	 * Resolves the line numbers on marked files.
	 * @param file the marked file
	 * @param markerId the ID of the markers to find
	 * @param markerAttribute the attribute whose value is an XPath expression.
	 * <p>
	 * The XPath expression is applied on the file's in-memory document.<br />
	 * If the document was not already loaded (e.g. by an editor), then nothing is resolved.<br />
	 * If an element is found, then its line number is resolved using WTP's XML API.
	 * The found line number is then written on the file marker.
	 * </p>
	 */
	@SuppressWarnings( "restriction" )
	public static void resolveLineNumbers( IFile file, String markerId, String markerAttribute ) {

		IStructuredModel model = null;
		try {
			int line = 0;
			model = StructuredModelManager.getModelManager().getExistingModelForRead( file );
			if( model != null
						&& file.exists()
						&& markerAttribute != null
						&& markerAttribute.trim().length() != 0 ) {

				// Get the markers from the file
				IMarker[] markers;
				try {
					markers = file.findMarkers( markerId, true, IResource.DEPTH_ZERO );
				} catch( CoreException e1 ) {
					e1.printStackTrace();
					markers = new IMarker[ 0 ];
				}

				if( markers == null )
					markers = new IMarker[ 0 ];

				for( IMarker marker : markers ) {

					// Get the XPath expression from the right attribute
					String xpathLocation = marker.getAttribute( markerAttribute, null );
					if( StringUtils.isEmpty( xpathLocation ))
						continue;

					// Resolve the XML element and get its line number
					Node node = null;
					try {
						node = (Node) X_PATH.evaluate( xpathLocation, ((IDOMModel) model).getDocument(), XPathConstants.NODE );
					} catch( XPathExpressionException e ) {
						PetalsCommonPlugin.log( e, IStatus.WARNING, "Failed to find the element " + xpathLocation + "." );
					}

					if( node != null ) {
						int offset = -1;
						if( node instanceof IDOMElement )
							offset = ((IDOMElement) node).getStartOffset();
						else if( node instanceof IDOMAttr )
							offset = ((IDOMAttr) node).getStartOffset();

						line = model.getStructuredDocument().getLineOfOffset( offset ) + 1;
					}

					// Debug
					else {
						System.out.println( "=> '" + xpathLocation + "' could not be resolved." );
					}

					// Update the line attribute on the marker
					try {
						marker.setAttribute( IMarker.LINE_NUMBER, line );
					} catch( CoreException e ) {
						e.printStackTrace();
					}
				}
			}

		} finally {
			if( model != null )
				model.releaseFromRead();
		}
	}
}
