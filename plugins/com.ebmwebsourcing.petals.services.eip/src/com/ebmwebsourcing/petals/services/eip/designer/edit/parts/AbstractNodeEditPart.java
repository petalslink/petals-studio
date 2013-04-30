/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.edit.policies.DeleteAbstractNodeEditPolicy;
import com.ebmwebsourcing.petals.services.eip.designer.edit.policies.EipConnectionPolicy;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * An edit part for EIP nodes.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractNodeEditPart extends AbstractGraphicalEditPart
implements PropertyChangeListener, NodeEditPart {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {

		// Allow removal of the associated model element
		installEditPolicy( EditPolicy.COMPONENT_ROLE, new DeleteAbstractNodeEditPolicy());
		installEditPolicy( EditPolicy.GRAPHICAL_NODE_ROLE, new EipConnectionPolicy());
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #activate()
	 */
	@Override
	public void activate() {
		super.activate();
		((AbstractNode) getModel()).addPropertyChangeListener( this );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #deactivate()
	 */
	@Override
	public void deactivate() {
		((AbstractNode) getModel()).removePropertyChangeListener( this );
		super.deactivate();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #isSelectable()
	 */
	@Override
	public boolean isSelectable() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( AbstractNode.PROPERTY_LAYOUT.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( AbstractNode.PROPERTY_SERVICE_NAME.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( AbstractNode.PROPERTY_ERROR.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( AbstractNode.PROPERTY_INCOMING_CONNECTION.equals( evt.getPropertyName()))
			refreshTargetConnections();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #getModelSourceConnections()
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	public List getModelSourceConnections() {
		return Collections.emptyList();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #getModelTargetConnections()
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	public List getModelTargetConnections() {

		List<Object> result = new ArrayList<Object> ();
		EipConnection conn = ((AbstractNode) getModel()).getIncomingConnection();
		if( conn != null )
			result.add( conn );

		return result;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #performRequest(org.eclipse.gef.Request)
	 */
	@Override
	public void performRequest( Request req ) {

		// Double-click a node => show the properties view
		if( RequestConstants.REQ_OPEN.equals( req.getType())) {
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.showView( IPageLayout.ID_PROP_SHEET );

			} catch( PartInitException e ) {
				PetalsEipPlugin.log( e, IStatus.WARNING );
			}
		}

		// Otherwise => nothing
		else {
			super.performRequest( req );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.NodeEditPart
	 * #getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor( ConnectionEditPart connection ) {
		return new ChopboxAnchor( getFigure());
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.NodeEditPart
	 * #getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor( Request request ) {
		return new ChopboxAnchor( getFigure());
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.NodeEditPart
	 * #getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor( ConnectionEditPart connection ) {
		return new ChopboxAnchor( getFigure());
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.NodeEditPart
	 * #getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor( Request request ) {
		return new ChopboxAnchor( getFigure());
	}
}
