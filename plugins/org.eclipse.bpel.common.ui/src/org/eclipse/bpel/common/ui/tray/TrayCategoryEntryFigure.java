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
package org.eclipse.bpel.common.ui.tray;

import java.util.List;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The basis figure for tray elements.
 * <p>
 * Edited by VZ (change the background color on selection events,
 * rather than setting a border.
 * </p>
 */
public class TrayCategoryEntryFigure extends SelectionBorderFigure {

	// VZ: border defines the margin around an entry (variable name, partner name) in the tray.
	public static final int BORDER = 3;

	/**
	 * Used to align the text of this entry with the text on the categories.
	 */
	protected class EntryLayout extends AbstractLayout {

		public void layout(IFigure container) {
			List<?> elements = container.getChildren();
			Label text = (Label)elements.get(0);
			Rectangle containerBounds = container.getBounds();
			Rectangle constraint = getCategoryTitleLabelBounds();
			int width = containerBounds.width - constraint.x;
			text.setBounds(new Rectangle(constraint.x, containerBounds.y, width, containerBounds.height));
		}

		@Override
		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
			// use the minimum width and the preferred height plus some spacing
			Dimension result = new Dimension();
			result.width = TrayCategoryEntryFigure.this.nameLabel.getMinimumSize(wHint, hHint).width;
			result.height = TrayCategoryEntryFigure.this.nameLabel.getPreferredSize(wHint, hHint).height;
			result.height += (BORDER * 2);
			return result;
		}
	}

	protected Label nameLabel;
	protected IFigure labelPositionReference;
	protected TrayCategoryEntryEditPart part;

	public TrayCategoryEntryFigure(IFigure labelPositionReference, TrayCategoryEntryEditPart part) {
		super();
		this.labelPositionReference = labelPositionReference;
		this.part = part;
		setLayoutManager(new EntryLayout());
		setBorder( new MarginBorder(BORDER) {

			// VZ: I commented the overriding.
			//			@Override
			//			public void paint(IFigure figure, Graphics graphics, Insets in) {
			//
			//				// VZ: this code draws the lines between the tray elements (variables, partners).
			//				Rectangle rect = figure.getBounds().getCopy();
			//				Color color = CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_LIGHT_BACKGROUND);
			//				graphics.setForegroundColor(color);
			//				graphics.drawLine(rect.x, rect.y + rect.height - 1, rect.x + rect.width, rect.y + rect.height - 1);
			//			}
		});

		this.nameLabel = new Label();
		this.nameLabel.setLabelAlignment(PositionConstants.LEFT);
		add(this.nameLabel);
	}

	public void setText(String text) {
		this.nameLabel.setText(text);
	}

	public String getText() {
		return this.nameLabel.getText();
	}

	protected Rectangle getCategoryTitleLabelBounds() {
		return this.labelPositionReference.getBounds();
	}

	public Label getLabel() {
		return this.nameLabel;
	}

	public TrayCategoryEntryEditPart getEditPart() {
		return this.part;
	}
}
