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
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.palette.EipConnectionCreationTool;

/**
 * An action to connect an EIP node to another node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionToAction extends SelectionAction {

	private EipNodeEditPart eipPart;


	/**
	 * Constructor.
	 * @param part
	 */
	public ConnectionToAction( IWorkbenchPart part ) {
		super( part );
		setLazyEnablementCalculation( true );

		setText( "Connect to" );
		setId( "com.ebmwebsourcing.petals.services.eip.contextmenu.connectTo" );
		setToolTipText( "Connect this EIP node to another node" );
		setDescription( "Connect this EIP node to another node." );
		setImageDescriptor( PetalsEipPlugin.getImageDescriptor( "icons/obj16/connection.png" ));
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction
	 * #calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {

		this.eipPart = null;
		ISelection s = getWorkbenchPart().getSite().getSelectionProvider().getSelection();
		if( s instanceof IStructuredSelection
					&& ((IStructuredSelection) s).size() == 1 ) {

			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof EipNodeEditPart )
				this.eipPart = (EipNodeEditPart) o;
		}

		return this.eipPart != null;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {

		Point point = null;
		point = this.eipPart.getFigure().getClientArea().getCenter();

		Point p = point;
		this.eipPart.getFigure().translateToAbsolute(p);

		Canvas canvas = (Canvas) this.eipPart.getViewer().getControl();
		Event event = new Event();
		event.button = 1;
		event.count = 0;
		event.detail = 0;
		event.end = 0;
		event.height = 0;
		event.keyCode = 0;
		event.start = 0;
		event.stateMask = 0;
		event.time = 9516624; // any old time... doesn't matter
		event.type = 3;
		event.widget = canvas;
		event.width = 0;
		event.x = p.x + 3;
		event.y = p.y + 3;


		EipConnectionCreationTool myConnectTool = new EipConnectionCreationTool();
		myConnectTool.performConnectionStartWith( this.eipPart );
		this.eipPart.getViewer().getEditDomain().setActiveTool( myConnectTool );
		canvas.notifyListeners( SWT.MouseDown, event );
	}
}
