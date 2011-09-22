/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerImageStore;
import com.ebmwebsourcing.petals.services.eip.designer.edit.policies.ConnectionDeleteEditPolicy;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.RouterUtils;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * The edit part for connections.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConnectionEditPart extends AbstractConnectionEditPart
implements PropertyChangeListener {

	private Label connectionLabel;


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {

		// Be able to delete connections
		installEditPolicy( EditPolicy.CONNECTION_ROLE, new ConnectionDeleteEditPolicy());

		// Be able to add and reconnect connections
		installEditPolicy( EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
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
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart
	 * #createFigure()
	 */
	@Override
	protected IFigure createFigure() {

		PolylineConnection connection = (PolylineConnection) super.createFigure();
		connection.setLineWidth( 2 );
		connection.setAntialias( SWT.ON );
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate( PolygonDecoration.TRIANGLE_TIP );
		connection.setTargetDecoration( decoration );

		this.connectionLabel = new Label();
		this.connectionLabel.setBorder( new MarginBorder( 4 ));
		this.connectionLabel.setBackgroundColor( ColorConstants.yellow );
		this.connectionLabel.setOpaque( true );
		connection.add( this.connectionLabel, new MidpointLocator( connection, 0 ));

		return connection;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #activate()
	 */
	@Override
	public void activate() {
		super.activate();
		((EipConnection) getModel()).addPropertyChangeListener( this );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #deactivate()
	 */
	@Override
	public void deactivate() {
		((EipConnection) getModel()).removePropertyChangeListener( this );
		super.deactivate();
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipConnection.PROPERTY_CONNECTION_SOURCE.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( EipConnection.PROPERTY_ERROR.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( EipConnection.PROPERTY_CONDITION_NAME.equals( evt.getPropertyName()))
			refreshVisuals();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {

		// Deal with the lines
		EipConnection connModel = (EipConnection)getModel();
		EipNode eip = connModel.getSource();
		PolylineConnection connection = (PolylineConnection) getFigure();
		connection.setLineStyle( SWT.LINE_SOLID );
		if( eip == null ) {
			this.connectionLabel.setText( "" );

		} else {
			switch( eip.getEipType()) {
			case AGGREGATOR:
			case SPLITTER:
			case BRIDGE:
				this.connectionLabel.setText( " target " );
				break;

			case DISPATCHER:
				this.connectionLabel.setText( "broadcast" );
				break;

			case ROUTING_SLIP:
			case SCATTER_GATHER:
				int index = eip.getOutgoingConnections().indexOf( connModel ) + 1;
				this.connectionLabel.setText( "Invocation " + index );
				break;

			case WIRETAP:
				index = eip.getOutgoingConnections().indexOf( connModel );
				int size = eip.getOutgoingConnections().size();
				if( size - index == 1 ) {
					this.connectionLabel.setText( "Monitoring" );
					connection.setLineStyle( SWT.LINE_DASH );
				}
				else
					this.connectionLabel.setText( "Normal Flow" );
				break;

			case DYNAMIC_ROUTER:
			case ROUTER:
				if( connModel.shouldHaveCondition()) {
					if( RouterUtils.isRoutingByContent( eip )) {
						String s = connModel.getConditionName();
						this.connectionLabel.setText( StringUtils.isEmpty( s ) ? "?" : s );

					} else {
						String s = NamespaceUtils.removeNamespaceElements( connModel.getConditionExpression());
						this.connectionLabel.setText( "if< " + (StringUtils.isEmpty( s ) ? "?" : s) + " >" );
					}
				}
				else
					this.connectionLabel.setText( "Default" );
				break;

			default:
				break;
			}
		}


		// Deal with the error markers
		if( ! connModel.getErrorMessages().isEmpty()) {
			this.connectionLabel.setIcon( EipDesignerImageStore.INSTANCE.getErrorIcon());
			this.connectionLabel.setToolTip( new Label( createTooltip( connModel.getErrorMessages(), IStatus.ERROR )));

		} else if( ! connModel.getWarningMessages().isEmpty()) {
			this.connectionLabel.setIcon( EipDesignerImageStore.INSTANCE.getWarningIcon());
			this.connectionLabel.setToolTip( new Label( createTooltip( connModel.getWarningMessages(), IStatus.WARNING )));

		} else {
			this.connectionLabel.setIcon( null );
			this.connectionLabel.setToolTip( null );
		}
	}


	/**
	 * @param issues a non-null list of messages
	 * @param level one of the IStatus constants to indicate the error level
	 * @return
	 */
	private String createTooltip( List<String> issues, int level ) {

		if( issues.size() > 1 ) {
			StringBuilder sb = new StringBuilder();
			if( level == IStatus.ERROR )
				sb.append( issues.size() + " errors were found." );
			else
				sb.append( issues.size() + " warnings were found." );

			for( String s : issues ) {
				sb.append( "\n+ " + s );
			}
			String tooltip = sb.toString();
			return tooltip;

		} else {
			return issues.get( 0 );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #setSelected(int)
	 */
	@Override
	public void setSelected( int value ) {
		super.setSelected( value );

		if( value == EditPart.SELECTED_PRIMARY ) {
			getFigure().setForegroundColor( ColorConstants.gray );
			// this.connectionLabel.setBackgroundColor( ColorConstants.gray );
		}
		else {
			getFigure().setForegroundColor( ColorConstants.black );
			// this.connectionLabel.setBackgroundColor( ColorConstants.yellow );
		}
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
}
