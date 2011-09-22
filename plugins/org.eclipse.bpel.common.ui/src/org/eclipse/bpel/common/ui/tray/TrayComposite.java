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

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class TrayComposite extends SashForm {

	private static final int DEFAULT_TRAY_WIDTH = 92;

	public static final int EVENT_COLLAPSED_STATE_CHANGED = 9999999;
	public static final int STATE_EXPANDED = 1;
	public static final int STATE_COLLAPSED = 2;

	public int marginHeight = 0;
	public int marginWidth = 0;

	protected Composite editorControl;
	protected TraySash sashControl;
	protected Control trayControl;
	protected int trayState;
	protected int trayWidth;

	public class ResizeListener extends MouseAdapter implements MouseMoveListener {
		protected boolean dragging = false;
		protected boolean correctState = false;
		protected int origX;

		@Override
		public void mouseDown(MouseEvent me) {
			if (me.button != 1)
				return;
			this.dragging = true;
			this.correctState = isInState(STATE_EXPANDED);
			this.origX = me.x;
		}

		public void mouseMove(MouseEvent me) {
			if (this.dragging && this.correctState) {
				int shiftAmount = me.x - this.origX;
				int newWidth = TrayComposite.this.trayWidth - shiftAmount;
				Point minSize = TrayComposite.this.trayControl.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				Point maxSize = TrayComposite.this.getSize();
				if (newWidth >= minSize.x && newWidth <= (maxSize.x / 2)) {
					setTrayWidth(newWidth);
				}
			}
		}
		@Override
		public void mouseUp(MouseEvent me) {
			this.dragging = false;
			this.correctState = false;
		}
	}

	/**
	 * @param parent
	 * @param style
	 */
	public TrayComposite(Composite parent, int style) {
		super(parent, style | SWT.HORIZONTAL);

		this.editorControl = new Composite( this, SWT.NONE );
		this.editorControl.setLayout( new FillLayout());

		this.sashControl = new TraySash(this, this, SWT.VERTICAL);

		setState(STATE_COLLAPSED);
		setTrayWidth(DEFAULT_TRAY_WIDTH);

		addListener( SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				layout();
			}
		});
	}

	public Composite getEditorComposite() {
		return this.editorControl;
	}

	/**
	 * set the tray composite
	 * @param trayControl
	 */
	public void setTrayControl(Control trayControl) {
		Assert.isTrue( trayControl.getParent() == this );
		this.trayControl = trayControl;
	}

	protected boolean isInState(int state) {
		return (this.trayState & state) != 0;
	}

	/**
	 * Sets the tray state to either STATE_EXPANDED or STATE_COLLAPSED.
	 * @param trayState
	 */
	public void setState(int trayState) {
		if (this.trayState != trayState) {
			this.trayState = trayState;

			Event event = new Event();
			event.type = EVENT_COLLAPSED_STATE_CHANGED;
			notifyListeners(EVENT_COLLAPSED_STATE_CHANGED, event);

			layout();
		}
	}

	public void setTrayWidth(int width) {
		if (this.trayWidth != width) {
			this.trayWidth = width;
			layout();
		}
	}

	@Override
	public void layout(boolean changed) {

		if (this.editorControl == null
					|| this.editorControl.isDisposed()
					|| this.trayControl == null
					|| this.trayControl.isDisposed())
			return;

		Rectangle area = getClientArea();
		if (area.width == 0 || area.height == 0)
			return;

		setRedraw(false);

		if (isInState(STATE_COLLAPSED)) {
			int titleWidth = this.sashControl.computeSize(-1, -1).x;
			this.editorControl.setBounds(area.x, area.y, area.width - titleWidth, area.height);
			this.sashControl.setBounds(area.x + area.width - titleWidth, area.y, titleWidth, area.height);
			this.trayControl.setVisible(false);

		} else if (isInState(STATE_EXPANDED)) {
			int titleWidth = this.sashControl.computeSize(-1, -1).x;
			if (changed) {
				int editorWeight = area.width - titleWidth - this.trayWidth;
				int sashWeight = titleWidth >= 0 ? titleWidth : 0;
				int trayWeight = this.trayWidth >= 0 ? this.trayWidth : 0;

				if (editorWeight < 0) {
					editorWeight = 0;
				}

				setWeights(new int[] {editorWeight, sashWeight, trayWeight});
			}

			this.editorControl.setBounds(area.x, area.y, area.width - titleWidth - this.trayWidth, area.height);
			this.sashControl.setBounds(area.x + area.width - titleWidth - this.trayWidth, area.y, titleWidth, area.height);
			this.trayControl.setBounds(area.x + area.width - this.trayWidth, area.y, this.trayWidth, area.height);
			this.trayControl.setVisible(true);
		}

		this.sashControl.layout(true);

		setRedraw(true);
		update();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Scrollable#getClientArea()
	 */
	@Override
	public Rectangle getClientArea() {
		Rectangle rect = super.getClientArea();
		rect.x += this.marginWidth;
		rect.y += this.marginHeight;
		rect.width -= this.marginWidth * 2;
		rect.height -= this.marginHeight * 2;
		return rect;
	}
}
