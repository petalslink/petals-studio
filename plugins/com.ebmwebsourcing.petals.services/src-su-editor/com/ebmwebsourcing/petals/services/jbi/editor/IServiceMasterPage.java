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

package com.ebmwebsourcing.petals.services.jbi.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.forms.IManagedForm;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IServiceMasterPage {

	/**
	 * Creates the page content.
	 * @param managedForm
	 */
	public void createContent( IManagedForm managedForm );


	/**
	 * Updates the viewer showing the provides and consumes blocks.
	 */
	public void update();


	/**
	 * Updates the markers in the viewers.
	 * @param markerToNode
	 */
	public void updateMarkers( Map<IMarker,Node> markerToNode );


	/**
	 * Shows and selects the part which is wrong and associated with this marker.
	 * @param node (not null)
	 * @param xPathLocation
	 * @return true if a matching widget could be found, false otherwise
	 */
	public boolean gotoMarker( Node node, String xPathLocation );


	/**
	 * Gets the markers for the details page associated with this element.
	 * @param element
	 * @return a list of markers (possibly null)
	 */
	public List<IMarker> getMarkersForDetails( Element element );


	/**
	 * Disposes the resources.
	 */
	public void dispose();
}
