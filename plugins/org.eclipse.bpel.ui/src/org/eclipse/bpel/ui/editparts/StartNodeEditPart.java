/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.editparts;

import org.eclipse.bpel.model.EventHandler;
import org.eclipse.bpel.model.FaultHandler;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.adapters.IEventHandlerHolder;
import org.eclipse.bpel.ui.adapters.IFaultHandlerHolder;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.editparts.policies.BPELSelectionEditPolicy;
import org.eclipse.bpel.ui.figures.CenteredConnectionAnchor;
import org.eclipse.bpel.ui.figures.ILayoutAware;
import org.eclipse.bpel.ui.uiextensionmodel.StartNode;
import org.eclipse.bpel.ui.util.BPELDragEditPartsTracker;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;


public class StartNodeEditPart extends BPELEditPart implements NodeEditPart, ILayoutAware{

	protected Image image;
	protected int imageWidth;
	protected int imageHeight;

	//Determines the border which will be added to both sides (left/right)
	//As a result this value is the distance to any attached handlers.
	public static final int BORDER_WIDTH = 8;

	// Whether the process has a fault handler and event handler
	protected boolean hasFH, hasEH;

	// Whether to show each of the actual handlers.
	// TODO: Initialize these from the preferences store
	protected boolean showFH = false, showEH = false;

	// Fault handler, Compensation handler and Event handler support
	protected int faultImageWidth, faultImageHeight;
	protected int eventImageWidth, eventImageHeight;
	protected Image faultImage;
	protected Image eventImage;
	protected Rectangle rectFault;
	protected Rectangle rectEvent;

	private IFigure faultImageFigure;
	private IFigure eventImageFigure;

	private class StartNodeFigure extends ImageFigure {
		/**
		 * 
		 */
		public StartNodeFigure() {
			super(StartNodeEditPart.this.image);
		}

		/**
		 * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
		 */
		@Override
		public void paint(Graphics graphics) {
			super.paint(graphics);
			computeHandlerIconPositions(ModelHelper.isHorizontalLayout(getModel()));
			if (StartNodeEditPart.this.hasFH) {
				graphics.pushState();
				graphics.setClip(StartNodeEditPart.this.faultImageFigure.getBounds().getCopy());
				StartNodeEditPart.this.faultImageFigure.paint(graphics);
				graphics.popState();
			}
			if (StartNodeEditPart.this.hasEH) {
				graphics.pushState();
				graphics.setClip(StartNodeEditPart.this.eventImageFigure.getBounds().getCopy());
				StartNodeEditPart.this.eventImageFigure.paint(graphics);
				graphics.popState();
			}
		}
	}

	/**
	 * Brand new shiny start edit part.
	 */
	public StartNodeEditPart() {
		// Initialize images for fault and event handler decorations
		this.faultImage = BPELUIPlugin.getDefault().getImage(IBPELUIConstants.ICON_FAULT_INDICATOR);
		org.eclipse.swt.graphics.Rectangle r = this.faultImage.getBounds();
		this.faultImageWidth = r.width;
		this.faultImageHeight = r.height;

		this.eventImage = BPELUIPlugin.getDefault().getImage(IBPELUIConstants.ICON_EVENT_INDICATOR);
		r = this.eventImage.getBounds();
		this.eventImageWidth = r.width;
		this.eventImageHeight = r.height;
	}

	@Override
	protected void addAllAdapters() {
		super.addAllAdapters();
		this.adapter.addToObject(getProcess());
	}


	@Override
	protected void removeAllAdapters() {
		this.adapter.removedFrom(getProcess());
		super.removeAllAdapters();
	}

	@Override
	protected IFigure createFigure() {
		if (this.image == null) {
			ILabeledElement element = BPELUtil.adapt(getStartNode(), ILabeledElement.class);
			this.image = element.getSmallImage(getStartNode());
			Rectangle rect = this.image.getBounds();
			this.imageWidth = rect.width;
			this.imageHeight = rect.height;
		}
		this.hasFH = getFaultHandler() != null;
		this.hasEH = getEventHandler() != null;
		// Cause the handlerIcons don't belong to the
		// bounds of the StartNodeFigure (as a result of
		// moving them a bit to the right in vertical layout and a bit to to the bottom
		// in horizontal layout) hit testing
		// doesn't reach the Startnode. So add an invisible
		// border
		StartNodeFigure snf = new StartNodeFigure();
		if(!ModelHelper.isHorizontalLayout(getModel()))
			snf.setBorder(new MarginBorder(0,7+BORDER_WIDTH,0,7+BORDER_WIDTH));
		else
			snf.setBorder(new MarginBorder(5,0,5,0));


		this.faultImageFigure = new ImageFigure(this.faultImage);
		this.faultImageFigure.setParent(snf);

		this.eventImageFigure = new ImageFigure(this.eventImage);
		this.eventImageFigure.setParent(snf);

		return snf;
	}

	/**
	 * @see org.eclipse.bpel.ui.editparts.BPELEditPart#refreshVisuals()
	 */
	@Override
	public void refreshVisuals() {
		super.refreshVisuals();
		// Refresh any decorations on this edit part
		this.hasFH = getFaultHandler() != null;
		this.hasEH = getEventHandler() != null;
		// Force a repaint, as the decorations.

		// VZ: run the repaint in a separate thread
		Display.getDefault().asyncExec( new Runnable() {
			public void run() {
				getFigure().repaint();
			}
		});
		// VZ
	}

	/**
	 * Return the start node.
	 * @return return the start node.
	 */
	public StartNode getStartNode() {
		return (StartNode)getModel();
	}

	/**
	 * Return the process.
	 * @return the process
	 */

	public Process getProcess() {
		return getStartNode().getProcess();
	}

	@Override
	protected void createEditPolicies() {
		// Don't call super because we don't want a component edit policy
		// or a direct edit policy.

		// Show the selection rectangle
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new BPELSelectionEditPolicy(false, false){
			@Override
			protected int getWestInset() {
				Insets ins = ((StartNodeEditPart)getHost()).getFigure().getInsets();
				if(ins != null){
					return ins.left;
				}
				return super.getWestInset();
			}

			@Override
			protected int getEastInset() {
				Insets ins = ((StartNodeEditPart)getHost()).getFigure().getInsets();
				if(ins != null){
					return ins.right;
				}
				return super.getEastInset();
			}
		});
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connEditPart) {
		// Start nodes cannot be the target of a connection.
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connEditPart) {
		// Start nodes can be the source of an implicit connection
		// anchored at the bottom centre of the node.
		return new CenteredConnectionAnchor(getFigure(), CenteredConnectionAnchor.BOTTOM, 0);
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		// TODO: Translate point to figure, call other method
		return getSourceConnectionAnchor((ConnectionEditPart)null);
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		// TODO: Translate point to figure, call other method
		return getTargetConnectionAnchor((ConnectionEditPart)null);
	}

	public void setShowFaultHandler(boolean showFaultHandler) {
		this.showFH = showFaultHandler;
		// Notify the process too.
		ProcessEditPart processEditPart = (ProcessEditPart)getParent();
		processEditPart.setShowFaultHandler(showFaultHandler);
		// Call refresh so that both refreshVisuals and refreshChildren will be called.
		refresh();
	}

	public void setShowEventHandler(boolean showEventHandler) {
		this.showEH = showEventHandler;
		// Notify the process too.
		ProcessEditPart processEditPart = (ProcessEditPart)getParent();
		processEditPart.setShowEventHandler(showEventHandler);
		// Call refresh so that both refreshVisuals and refreshChildren will be called.
		refresh();
	}

	public boolean getShowFaultHandler() {
		return this.showFH;
	}

	public boolean getShowEventHandler() {
		return this.showEH;
	}


	public FaultHandler getFaultHandler() {
		IFaultHandlerHolder holder = BPELUtil.adapt(getProcess(), IFaultHandlerHolder.class);
		if (holder != null) {
			return holder.getFaultHandler(getProcess());
		}
		return null;
	}

	public EventHandler getEventHandler() {
		IEventHandlerHolder holder = BPELUtil.adapt(getProcess(), IEventHandlerHolder.class);
		if (holder != null) {
			return holder.getEventHandler(getProcess());
		}
		return null;
	}
	/**
	 * Is point in fault image.
	 * @param x
	 * @param y
	 * @return true if it is, false if its not.
	 */
	public boolean isPointInFaultImage(int x, int y) {
		if (this.hasFH) {
			Point p = new Point(x, y);
			getFigure().translateToRelative(p);
			return this.rectFault.contains(p.x, p.y);
		}
		return false;
	}

	/**
	 * Is point in event image.
	 * @param x
	 * @param y
	 * @return true if it is, false if its not.
	 */
	public boolean isPointInEventImage(int x, int y) {
		if (this.hasEH) {
			Point p = new Point(x, y);
			getFigure().translateToRelative(p);
			return this.rectEvent.contains(p.x, p.y);
		}
		return false;
	}


	/**
	 * @see org.eclipse.bpel.ui.editparts.BPELEditPart#getDragTracker(org.eclipse.gef.Request)
	 */
	@Override
	public DragTracker getDragTracker(Request request) {
		return new BPELDragEditPartsTracker(this) {
			@Override
			protected boolean handleDoubleClick(int button) {
				return true;
			}

			@Override
			protected boolean handleButtonDown(int button) {
				Point point = getLocation();
				if (isPointInFaultImage(point.x, point.y)) {
					setShowFaultHandler(!StartNodeEditPart.this.showFH);
					return true;
				}
				if (isPointInEventImage(point.x, point.y)) {
					setShowEventHandler(!StartNodeEditPart.this.showEH);
					return true;
				}
				return super.handleButtonDown(button);
			}
		};
	}

	private void computeHandlerIconPositions(boolean horizontal){
		org.eclipse.draw2d.geometry.Rectangle bounds = getFigure().getBounds();
		if(horizontal){
			this.rectFault = new Rectangle(bounds.x, bounds.y + bounds.height - this.faultImageHeight, this.faultImageWidth, this.faultImageHeight);
			this.rectEvent = new Rectangle(bounds.x +this.faultImageWidth, bounds.y + bounds.height - this.eventImageHeight, this.eventImageWidth, this.eventImageHeight);

		}else{
			this.rectFault = new Rectangle(bounds.x + bounds.width - this.faultImageWidth - BORDER_WIDTH, bounds.y-1, this.faultImageWidth, this.faultImageHeight);
			this.rectEvent = new Rectangle(bounds.x + bounds.width - this.eventImageWidth - BORDER_WIDTH, bounds.y + bounds.height - this.eventImageHeight+1, this.eventImageWidth, this.eventImageHeight);
		}

		// Apply the bounds to the figures
		this.faultImageFigure.setBounds(new org.eclipse.draw2d.geometry.Rectangle(this.rectFault.x,this.rectFault.y,this.rectFault.width,this.rectFault.height));
		this.eventImageFigure.setBounds(new org.eclipse.draw2d.geometry.Rectangle(this.rectEvent.x,this.rectEvent.y,this.rectEvent.width,this.rectEvent.height));
	}

	public Rectangle getRectFault() {
		return this.rectFault;
	}

	public Rectangle getRectEvent() {
		return this.rectEvent;
	}

	public void switchLayout(boolean horizontal) {
		if(horizontal){
			getFigure().setBorder(new MarginBorder(5,0,5,0));
		}else{
			getFigure().setBorder(new MarginBorder(0,7+BORDER_WIDTH,0,7+BORDER_WIDTH));
		}

	}

	public IFigure getFaultImageFigure() {
		return this.faultImageFigure;
	}

	public IFigure getEventImageFigure() {
		return this.eventImageFigure;
	}
}
