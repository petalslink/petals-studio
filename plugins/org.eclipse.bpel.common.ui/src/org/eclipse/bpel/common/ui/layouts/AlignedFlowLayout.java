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
package org.eclipse.bpel.common.ui.layouts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;

/** A version of FlowLayout that doesn't wrap the children
 * and allows aligning in both orientations
 * as well as alignment of the children relative to each other
 * 
 * some parameters you can set:
 * 
 * horizontal - orientation of children
 * 	if true, children laid across, and width respected
 * 	if false. children laid downwards, height respected.
 * 
 * fill - if true and layout is horizontal, height not respected
 * 		  if false and layout is horizontal, height respected
 *        if true and layout is vertical, width not respected
 * 
 * fillParent - if true, tries to fill the child to the container size
 * 
 * horizontal or vertical alignment
 * 	specifies which 1 of 3 areas the children are placed into
 * 
 * ALIGN_BEGIN means either left or top
 * ALIGN_END means either right or bottom
 * ALIGN_CENTER means in the middle
 * 
 * secondaryAlignment - how the children are lined up in secondary axis
 *   if horizontal - specfies if children are aligned to their tops, center or bottom
 * 	 if vertical - specifies if children are aligned to left, right or center.
 * 
 */

public class AlignedFlowLayout extends AbstractHintLayout {
	/*
	 * Constants defining the alignment of the components
	 */
	public static final int ALIGN_CENTER = 0, ALIGN_BEGIN = 1, ALIGN_END = 2;
	public static final boolean HORIZONTAL = true, VERTICAL = false;

	protected boolean horizontal = true;
	protected boolean fill = false;
	protected boolean fillParent = false;

	protected Transposer transposer;
	{
		this.transposer = new Transposer();
		this.transposer.setEnabled(!this.horizontal);
	}

	/*
	 * Internal state
	 */
	protected int horizontalAlignment = ALIGN_BEGIN;
	protected int verticalAlignment = ALIGN_BEGIN;
	protected int secondaryAlignment = ALIGN_BEGIN;


	protected int horizontalSpacing = 5, verticalSpacing = 5;
	private WorkingData data = null;

	/**
	 * Holds the necessary information for layout calculations.
	 */
	static class WorkingData {
		int rowHeight, rowWidth, rowCount, rowX, rowY;
		Rectangle bounds[], area;
		IFigure row[];
		Dimension spacing;
	}

	public AlignedFlowLayout() {
	}

	public AlignedFlowLayout(boolean isHorizontal) {
		setHorizontal(isHorizontal);
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		int cHorizontalSpacing = this.horizontalSpacing;
		if (!isHorizontal()) {
			cHorizontalSpacing = this.verticalSpacing;
		}

		// Subtract out the insets from the hints
		if (wHint > -1)
			wHint = Math.max(0, wHint - container.getInsets().getWidth());
		if (hHint > -1)
			hHint = Math.max(0, hHint - container.getInsets().getHeight());

		// Figure out the new hint that we are interested in based on the orientation
		// Ignore the other hint (by setting it to -1).  NOTE: The children of the
		// parent figure will then be asked to ignore that hint as well.
		int maxWidth;
		if (isHorizontal()) {
			maxWidth = wHint;
			hHint = -1;
		} else {
			maxWidth = hHint;
			wHint = -1;
		}
		if (maxWidth <= 0) {
			maxWidth = Integer.MAX_VALUE;
		}

		// The preferred dimension that is to be calculated and returned
		Dimension prefSize = new Dimension();

		List children = container.getChildren();
		int width = 0;
		int height = 0;
		IFigure child;
		Dimension childSize;

		//Build the sizes for each row, and update prefSize accordingly
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			childSize = this.transposer.t(getChildSize(child, wHint, hHint));
			if (i == 0) {
				width = childSize.width;
				height = childSize.height;
			} else
			{
				// fit another child.
				width += childSize.width + cHorizontalSpacing;
				height = Math.max(height, childSize.height);
			}
		}

		// Flush out the last row's data
		prefSize.height += height;
		prefSize.width = Math.max(prefSize.width, width);

		// Transpose the dimension back, and compensate for the border.
		prefSize = this.transposer.t(prefSize);
		prefSize.width += container.getInsets().getWidth();
		prefSize.height += container.getInsets().getHeight();
		prefSize.union(getBorderPreferredSize(container));
		return prefSize;
	}

	/**
	 * Provides the given child's preferred size
	 * 
	 * @param child	The Figure whose preferred size needs to be calculated
	 * @param wHint	The width hint to be used when calculating the child's preferred size
	 * @param hHint	The height hint to be used when calculating the child's preferred size
	 * @return the child's preferred size
	 */
	protected Dimension getChildSize(IFigure child, int wHint, int hHint) {
		return child.getPreferredSize(wHint, hHint);
	}

	public int getHorizontalAlignment() {
		return this.horizontalAlignment;
	}

	public int getVerticalSpacing() {
		return this.verticalSpacing;
	}

	public int getVerticalAlignment() {
		return this.verticalAlignment;
	}

	public int getHorizontalSpacing() {
		return this.horizontalSpacing;
	}

	/**
	 * Initializes the state of row data, which is internal
	 * to the layout process.
	 */
	private void initRow() {
		this.data.rowX = 0;
		this.data.rowHeight = 0;
		this.data.rowWidth = 0;
		this.data.rowCount = 0;
	}

	/**
	 * Initializes state data for laying out children, based
	 * on the Figure given as input.
	 *
	 * @param parent  Figure for which the children are to
	 *                 be arranged.
	 * @since 2.0
	 */
	private void initVariables(IFigure parent) {
		this.data.row = new IFigure[parent.getChildren().size()];
		this.data.bounds = new Rectangle[this.data.row.length];
	}

	public boolean isHorizontal() {
		return this.horizontal;
	}

	@Override
	protected boolean isSensitiveHorizontally(IFigure parent) {
		return isHorizontal();
	}

	@Override
	protected boolean isSensitiveVertically(IFigure parent) {
		return !isHorizontal();
	}

	public void layout(IFigure parent) {
		this.data = new WorkingData();
		Rectangle relativeArea = parent.getClientArea();
		this.data.area = this.transposer.t(relativeArea);
		this.data.spacing = this.transposer.t(new Dimension(this.horizontalSpacing, this.verticalSpacing));

		Iterator iterator = parent.getChildren().iterator();
		int dx;

		//Calculate the hints to be passed to children
		int wHint = -1;
		int hHint = -1;
		if (isHorizontal())
			wHint = parent.getClientArea().width;
		else
			hHint = parent.getClientArea().height;

		initVariables(parent);
		initRow();
		int i = 0;
		while (iterator.hasNext()) {
			IFigure f = (IFigure) iterator.next();
			Dimension pref = this.transposer.t(getChildSize(f, wHint, hHint));
			Rectangle r = new Rectangle(0, 0, pref.width, pref.height);
			r.x = this.data.rowX;
			r.y = this.data.rowY;
			dx = r.width + this.data.spacing.width;
			this.data.rowX += dx;
			this.data.rowWidth += dx;
			this.data.rowHeight = Math.max(this.data.rowHeight, r.height);
			if (this.fillParent)
				this.data.rowHeight = Math.max(this.data.area.height, r.height);

			this.data.row[this.data.rowCount] = f;
			this.data.bounds[this.data.rowCount] = r;
			this.data.rowCount++;
			i++;
		}
		if (this.data.rowCount != 0)
			layoutRow(parent);
		this.data = null;
	}

	/**
	 * Layouts one row of components. This is done based on
	 * the layout's orientation, minor alignment and major alignment.
	 *
	 * @param parent  Figure whose children are to be placed.
	 * @since 2.0
	 */
	protected void layoutRow(IFigure parent) {
		int majorAdjustment = 0;
		int minorAdjustment = 0;
		int justification = 0;
		int correctHorizontalAlignment = this.horizontalAlignment;
		int correctVerticalAlignment = this.verticalAlignment;

		majorAdjustment = this.data.area.width - this.data.rowWidth;
		if (!isHorizontal()) {
			correctHorizontalAlignment = this.verticalAlignment;
			correctVerticalAlignment = this.horizontalAlignment;
		}
		switch (correctHorizontalAlignment) {
		case ALIGN_BEGIN :
			majorAdjustment = 0;
			break;
		case ALIGN_CENTER :
			majorAdjustment /= 2;
			break;
		case ALIGN_END :
			break;
		}
		minorAdjustment = this.data.area.height - this.data.rowHeight;
		switch (correctVerticalAlignment) {
		case ALIGN_BEGIN :
			minorAdjustment = 0;
			break;
		case ALIGN_CENTER :
			minorAdjustment /= 2;
			break;
		case ALIGN_END :
			break;
		}

		for (int j = 0; j < this.data.rowCount; j++) {
			if (this.fill) {
				this.data.bounds[j].height = this.data.rowHeight;
			} else {
				justification = this.data.rowHeight - this.data.bounds[j].height;
				switch (this.secondaryAlignment) {
				case ALIGN_BEGIN :
					justification = 0;
					break;
				case ALIGN_CENTER :
					justification /= 2;
					break;
				case ALIGN_END :
					break;
				}
				this.data.bounds[j].y += minorAdjustment + justification;
			}
			this.data.bounds[j].x += majorAdjustment;

			setBoundsOfChild(parent, this.data.row[j], this.transposer.t(this.data.bounds[j]));
		}
		this.data.rowY += this.data.spacing.height + this.data.rowHeight;
		initRow();
	}

	/**
	 * Sets the given bounds for the child figure input.
	 *
	 * @param parent  Parent Figure which holds the child.
	 * @param child   Child Figure whose bounds are to be set.
	 * @param bounds  The size of the child to be set.
	 * @since 2.0
	 */
	protected void setBoundsOfChild(IFigure parent, IFigure child, Rectangle bounds) {
		parent.getClientArea(Rectangle.SINGLETON);
		bounds.translate(Rectangle.SINGLETON.x, Rectangle.SINGLETON.y);
		child.setBounds(bounds);
	}

	/**
	 * Sets flag based on layout orientation.
	 * If in Horizontal orientation, all Figures will have the same height.
	 * If in vertical orientation, all Figures will have the same width.
	 *
	 * @param value  Fill state desired.
	 * @since 2.0
	 */
	public void setStretchMinorAxis(boolean value) {
		this.fill = value;
	}

	public void setHorizontal(boolean flag) {
		if (this.horizontal == flag)
			return;
		invalidate();
		this.horizontal = flag;
		this.transposer.setEnabled(!this.horizontal);
	}

	public void setHorizontalAlignment(int align) {
		this.horizontalAlignment = align;
	}

	/**
	 * Sets the spacing in pixels to be used between children in
	 * the direction parallel to the layout's orientation.
	 *
	 * @param n  Amount of major space.
	 * @see  #setHorizontalSpacing(int)
	 * @since 2.0
	 */
	public void setVerticalSpacing(int n) {
		this.verticalSpacing = n;
	}

	public void setVerticalAlignment(int align) {
		this.verticalAlignment = align;
	}

	public void setHorizontalSpacing(int n) {
		this.horizontalSpacing = n;
	}

	public int getSecondaryAlignment() {
		return this.secondaryAlignment;
	}

	public void setSecondaryAlignment(int i) {
		this.secondaryAlignment = i;
	}

	/**
	 * 
	 * @param fillParent - if setStretchMinorAxis is true, setting
	 * this flag will make the stretch fill the client area of the container
	 */
	public void setStretchMinorAxisToParent(boolean fillParent) {
		this.fillParent = fillParent;
	}


}