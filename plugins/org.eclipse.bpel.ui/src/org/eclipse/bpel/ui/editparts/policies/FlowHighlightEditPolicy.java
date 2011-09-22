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
package org.eclipse.bpel.ui.editparts.policies;

import org.eclipse.bpel.ui.editparts.BPELEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;


public class FlowHighlightEditPolicy extends ContainerHighlightEditPolicy {

	private Polyline insertionLine = null;
	private Polyline insertionLine2 = null;

	public FlowHighlightEditPolicy(boolean resizable) {
		super(resizable, true);
	}

	private Point translateToRelativePoint(Point pt) {
		Point newpt = pt.getCopy();
		((GraphicalEditPart)getHost()).getContentPane().translateToRelative(newpt);
		((GraphicalEditPart)getHost().getRoot()).getFigure().translateToAbsolute(newpt);
		return newpt;
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		super.eraseTargetFeedback(request);
		if (this.insertionLine != null) {
			this.insertionLine = null;
			this.insertionLine2 = null;
		}
	}

	@Override
	public void showTargetFeedback(Request request) {
		super.showTargetFeedback(request);
		if (request.getType().equals(RequestConstants.REQ_ADD)
					|| request.getType().equals(RequestConstants.REQ_CREATE)) {

			if (((BPELEditPart) getHost()).canExecuteRequest(request)) {
				showHighlight(request);
			}
		}
	}

	protected void showHighlight(Request request) {
		final int WIDTH = 25;
		Point p = null;
		if (request instanceof ChangeBoundsRequest) {
			p = ((ChangeBoundsRequest)request).getLocation().getCopy();
		}
		else if (request instanceof CreateRequest) {
			p = ((CreateRequest)request).getLocation().getCopy();
		}

		if (p != null) {
			this.insertionLine = new Polyline();
			this.insertionLine.setLineWidth(1);
			this.insertionLine.setOutlineXOR(true);
			this.insertionLine.setForegroundColor(ColorConstants.darkGray);

			this.insertionLine2 = new Polyline();
			this.insertionLine2.setLineWidth(1);
			this.insertionLine2.setOutlineXOR(true);
			this.insertionLine2.setForegroundColor(ColorConstants.darkGray);

			this.insertionLine.addPoint(translateToRelativePoint(new Point(p.x, p.y - WIDTH)));
			this.insertionLine.addPoint(translateToRelativePoint(new Point(p.x, p.y + WIDTH)));
			this.insertionLine2.addPoint(translateToRelativePoint(new Point(p.x - WIDTH, p.y)));
			this.insertionLine2.addPoint(translateToRelativePoint(new Point(p.x + WIDTH, p.y)));
		}
	}

	protected int selectedState = -1;

	@Override
	protected void setSelectedState(int type) {
		this.selectedState = type;
		super.setSelectedState(type);
	}

	// make this visible so the editpart can poke it when the collapsed state changes
	public void setResizable(boolean resizable) {
		this.fResizable = resizable;
		if (this.selectedState != -1) {
			// force selection handles to be refreshed.
			int previousState = this.selectedState;
			setSelectedState(-1);
			setSelectedState(previousState);
		}
	}
}
