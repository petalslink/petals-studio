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
package org.eclipse.bpel.ui.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;

/**
 * class that implements a row/column layout used for BPEL Flows
 */
public class RowColumnLayout extends AbstractLayout {

	protected Map<IFigure, Object> constraints = new HashMap<IFigure, Object>();

	private static final int minWidthHeight = 30;
	private int totalHeight, totalWidth;
	private Rectangle region;
	private int[] rowHeights;
	private int[] colWidths;
	boolean spanned = false;
	private int gridWidth, gridHeight;
	private int[] widthOffsets;
	private int[] heightOffsets;
	private int rows = 0, cols = 0;
	private static final boolean debugThis = false;

	public int getNumberOfColumns() {
		return this.cols;
	}

	public int getNumberOfRows() {
		return this.rows;
	}

	public boolean isSpanned() {
		return this.spanned;
	}

	public void setSpanned(boolean spanned) {
		//TODO:  setSpanned(true) not supported yet
		this.spanned = spanned;
	}

	private Rectangle getChildRegion(IFigure f, int availableX, int availableY) {
		if (this.spanned) {
			Dimension sz = f.getPreferredSize();
			int w = (int) Math.round((float) (sz.width) / this.gridWidth + 0.5);
			int h = (int) Math.round((float) (sz.height) / this.gridHeight + 0.5);
			return new Rectangle(availableX, availableY, w, h);
		}
		return new Rectangle(availableX, availableY, 1, 1);
	}

	public void characterizeGrid(IFigure f) {
		initVars();
		this.region = new Rectangle(0, 0, -1, -1);
		int unresolved = 0;
		ListIterator<IFigure> children = f.getChildren().listIterator();
		while (children.hasNext()) {
			IFigure child = children.next();
			Rectangle r = (Rectangle) this.constraints.get(child);
			if (r == null || r.width < 0 || r.height < 0) {
				unresolved++;
				if (RowColumnLayout.debugThis) {
					if (r != null) System.out.println("unresolved figure " + r + " " + child); //$NON-NLS-1$ //$NON-NLS-2$
					else System.out.println("unresolved figure " + child); //$NON-NLS-1$
				}
				continue;
			}

			if (RowColumnLayout.debugThis) System.out.println("resolved figure " + r + " " + child); //$NON-NLS-1$ //$NON-NLS-2$

			if (this.region.height < 0)
				this.region.setBounds(r);
			else
				this.region.union(r);
		}

		if (this.region.height >= 0) {
			this.rows = this.region.getBottomRight().y;
			this.cols = this.region.getBottomRight().x;
		}

		// we have to resolve the unresolved items by calculating a new row-column for it
		if (unresolved > 0) {
			children = f.getChildren().listIterator();
			while (children.hasNext()) {
				IFigure child = children.next();
				Rectangle r = (Rectangle) this.constraints.get(child);
				if (r == null || r.width < 0 || r.height < 0) {
					if (RowColumnLayout.debugThis) System.out.println("trying to resolve figure at " + child); //$NON-NLS-1$
					r = getChildRegion(child, this.cols, 0);
					this.constraints.put(child, r);
					if (RowColumnLayout.debugThis) System.out.println("new rect " + r); //$NON-NLS-1$
					if (this.region.height < 0)
						this.region.setBounds(r);
					else
						this.region.union(r);
					this.cols = this.region.getBottomRight().x;
				}
			}
		}

		// update again
		if (this.region.x >= 0) {
			this.rows = this.region.getBottomRight().y;
			this.cols = this.region.getBottomRight().x;
		}
		else {
			return;
		}

		if (this.rows < 0)
			return;

		if (RowColumnLayout.debugThis)	{
			System.out.println("bounds  " + this.region);	 //$NON-NLS-1$
			System.out.println("rows " + this.rows); //$NON-NLS-1$
			System.out.println("cols " + this.cols); //$NON-NLS-1$
		}

		// allocate an array of row, col sizes;
		this.rowHeights = new int[this.rows];
		this.colWidths = new int[this.cols];

		for (int i = 0; i < this.cols; i++) {
			this.colWidths[i] = minWidthHeight;
		}
		for (int i = 0; i < this.rows; i++) {
			this.rowHeights[i] = minWidthHeight;
		}

		if (!isSpanned()) {
			children = f.getChildren().listIterator();
			while (children.hasNext()) {
				IFigure child = children.next();
				Rectangle rowcol = (Rectangle) this.constraints.get(child);

				if (RowColumnLayout.debugThis)	{
					System.out.println("child " + child + " " + rowcol);	 //$NON-NLS-1$ //$NON-NLS-2$
				}

				Dimension sz = getChildSize(child, -1, -1);
				if (rowcol.x >= this.cols || rowcol.y >= this.rows)
					throw new IllegalArgumentException();
				if (rowcol.x < 0 || rowcol.y < 0)
					throw new IllegalArgumentException();
				this.rowHeights[rowcol.y] = Math.max(this.rowHeights[rowcol.y], sz.height+10);
				this.colWidths[rowcol.x] = Math.max(this.colWidths[rowcol.x], sz.width+10);
			}
		}

		for (int i = 0; i < this.rows; i++)
			this.totalHeight += this.rowHeights[i];
		for (int i = 0; i < this.cols; i++)
			this.totalWidth += this.colWidths[i];

		// calculate the offsets of each cell in the coordinate space
		this.widthOffsets = new int[this.cols];
		this.heightOffsets = new int[this.rows];
		for (int i = 1; i < this.cols; i++)
			this.widthOffsets[i] = this.colWidths[i - 1] + this.widthOffsets[i - 1];
		for (int i = 1; i < this.rows; i++)
			this.heightOffsets[i] = this.rowHeights[i - 1] + this.heightOffsets[i - 1];
	}

	/**
	 * Provides the given child's preferred size
	 * @param child	The Figure whose preferred size needs to be calculated
	 * @param wHint	The width hint to be used when calculating the child's preferred size
	 * @param hHint	The height hint to be used when calculating the child's preferred size
	 * @return the child's preferred size
	 */
	protected Dimension getChildSize(IFigure child, int wHint, int hHint) {
		return child.getPreferredSize(wHint, hHint);
	}

	@Override
	public Object getConstraint(IFigure figure) {
		return this.constraints.get(figure);
	}

	public Point getOrigin(IFigure parent) {
		return parent.getClientArea().getLocation();
	}

	private void initVars() {
		this.totalHeight = 0;
		this.totalWidth = 0;
		this.rowHeights = null;
		this.colWidths = null;
		this.gridWidth = 40;
		this.gridHeight = 40;
		this.widthOffsets = null;
		this.heightOffsets = null;
		this.rows = 0;
		this.cols = 0;
	}

	@Override
	public void remove(IFigure figure) {
		super.remove(figure);
		this.constraints.remove(figure);
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
	 * Sets the layout constraint of the given figure.
	 *
	 * @param figure  Figure for which the constarint is being set.
	 * @param newConstraint  Constraint for the input figure.
	 * @see  #getConstraint(IFigure)
	 * @since 2.0
	 */
	@Override
	public void setConstraint(IFigure figure, Object newConstraint) {
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null)
			this.constraints.put(figure, newConstraint);
		if (RowColumnLayout.debugThis) {
			if (newConstraint != null)
				System.out.println("setConstraint("+ figure + " " + newConstraint + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {

		characterizeGrid(container);

		Dimension prefSize = new Dimension();
		prefSize.height += this.totalHeight;
		prefSize.width += this.totalWidth;
		prefSize.width += container.getInsets().getWidth();
		prefSize.height += container.getInsets().getHeight();
		prefSize.union(getBorderPreferredSize(container));
		return prefSize;
	}

	/**
	 * @see org.eclipse.draw2d.LayoutManager#layout(IFigure)
	 */
	public void layout(IFigure parent) {
		characterizeGrid(parent);
		if (this.rows == 0 || this.cols == 0)
			return;
		int row, col;
		Point offset = getOrigin(parent);
		Point centeringOffset = new Point();
		IFigure f;

		Iterator<IFigure> children;
		children = parent.getChildren().iterator();
		while (children.hasNext()) {
			f = children.next();
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds == null)
				continue;
			if (RowColumnLayout.debugThis) System.out.println("bounds " + " " + f + " " + bounds); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			Rectangle newChildArea = new Rectangle();
			row = bounds.y;
			col = bounds.x;

			newChildArea.x = this.widthOffsets[col];
			newChildArea.y = this.heightOffsets[row];
			if (isSpanned()) {
				newChildArea.height = this.gridHeight * bounds.height;
				newChildArea.width = this.gridWidth * bounds.width;
			} else {
				newChildArea.height = this.rowHeights[row];
				newChildArea.width = this.colWidths[col];
			}
			centeringOffset.x = (newChildArea.width - f.getPreferredSize().width) / 2;
			centeringOffset.y = (newChildArea.height - f.getPreferredSize().height) / 2;
			newChildArea = newChildArea.getTranslated(offset);
			newChildArea = newChildArea.getTranslated(centeringOffset);
			newChildArea.width = f.getPreferredSize().width;
			newChildArea.height = f.getPreferredSize().height;
			f.setBounds(newChildArea);
		}
	}

	/**
	 * @param pt - location to test
	 * @param gridInfo - returns the grid information from that point
	 * @param pixelRegionInfo - returns the pixel region of that grid
	 * @return true if the point pass the hittest.
	 */
	public boolean getGridInfoFromLocation(Point pt, Rectangle gridInfo, Rectangle pixRegion) {
		Rectangle hitTest = new Rectangle();
		for (int i = 0; i < this.cols; i++)
			for (int k = 0; k < this.rows; k++) {
				hitTest.x = this.widthOffsets[i];
				hitTest.y = this.heightOffsets[k];
				hitTest.width = this.colWidths[i];
				hitTest.height = this.rowHeights[k];
				if (hitTest.contains(pt)) {
					gridInfo.x = i;
					gridInfo.y = k;
					gridInfo.setSize(1, 1);
					pixRegion.setBounds(hitTest);
					return true;
				}
			}
		return false;
	}

	public boolean isDroppableAt(EditPart parentPart, EditPart test, Rectangle gridInfo) {
		IFigure f = ((GraphicalEditPart)parentPart).getContentPane();
		ListIterator<IFigure> children = f.getChildren().listIterator();
		while (children.hasNext()) {
			IFigure child = children.next();
			Rectangle r = (Rectangle) this.constraints.get(child);
			if (r.getLocation().equals(gridInfo.getLocation())) {
				return false;
			}
		}
		return true;
	}

	public Rectangle [] getDividers() {
		if (this.rows <= 1 && this.cols <= 1)
			return null;
		Rectangle [] rects = new Rectangle[(this.rows-1)+(this.cols-1)];
		int n = 0;
		for (int i = 0; i < (this.cols-1); i++) {
			rects[n++] = new Rectangle(new Point(this.widthOffsets[i+1], 0), new Dimension(0, this.totalHeight));
		}
		for (int i = 0; i < (this.rows-1); i++) {
			rects[n++] = new Rectangle(new Point(0, this.heightOffsets[i+1]), new Dimension(this.totalWidth, 0));
		}
		return rects;
	}
}
