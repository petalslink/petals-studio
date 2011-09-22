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

import org.eclipse.bpel.common.ui.CommonUIPlugin;
import org.eclipse.bpel.common.ui.details.IDetailsColors;
import org.eclipse.draw2d.Cursors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * The container for the palette on the right, with the variables and partners.
 */
public class TraySash extends Composite {

	// VZ: I am responsible for all the commented lines in this class.
	// Do not remove any of them.

	//	protected static final Image ARROW_LEFT = CommonUIPlugin.getDefault().getImageRegistry().get(ICommonUIConstants.ICON_KEY_TRAY_EXPAND_BUTTON);
	//	protected static final Image ARROW_RIGHT = CommonUIPlugin.getDefault().getImageRegistry().get(ICommonUIConstants.ICON_KEY_TRAY_COLLAPSE_BUTTON);
	//
	//	protected static final Point TEXTURE_SIZE = new Point(50, 50);
	//
	//	// VZ: commented this constant
	//	// protected static final int TEXTURE_SPACING = 4;
	//
	//	protected static class TrayTextureCanvas extends Canvas {
	//
	//		// VZ: commented the constants.
	//		// protected final int MAX_ROWS = (TEXTURE_SIZE.y / TEXTURE_SPACING);
	//		// protected final int MAX_COLUMNS = 2;
	//
	//		public TrayTextureCanvas(Composite parent, int style) {
	//			super(parent, SWT.NO_REDRAW_RESIZE | style);
	//
	//			// VZ: no BG color
	//			// setBackground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_LIGHT_BACKGROUND));
	//			// VZ
	//
	//			// VZ: The next commented lines were commented by me.
	//			// Do not delete them.
	//
	//			//			addPaintListener(new PaintListener() {
	//			//				public void paintControl(PaintEvent e) {
	//			//
	//			//					int y;
	//			//					int height = getBounds().height;
	//			//					int textureHeight = TrayTextureCanvas.this.MAX_ROWS * TEXTURE_SPACING;
	//			//					int availableHeight = height - textureHeight;
	//			//					int imageHeight = 66;
	//			//					if (isTop()) {
	//			//						y = Math.min(0, (availableHeight - imageHeight));
	//			//					} else {
	//			//						y = Math.max(textureHeight, (height - imageHeight));
	//			//					}
	//			//
	//			//					// paint texture background
	//			//					// VZ: no BG color
	//			//					// e.gc.setBackground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_LIGHT_BACKGROUND));
	//			//					// VZ
	//			//
	//			//					if (isTop()) {
	//			//						e.gc.fillRectangle(0, (y + imageHeight), getBounds().width, getBounds().height);
	//			//					} else {
	//			//						e.gc.fillRectangle(0, 0, getBounds().width, y);
	//			//					}
	//			//
	//			//					// paint texture
	//			//					y = 0;
	//			//					if (isTop()) {
	//			//						y = getBounds().height;
	//			//					}
	//			//
	//			//					// VZ: the following code adds the two dots "::" around the clickable button.
	//			//					for(int i = 0; i < TrayTextureCanvas.this.MAX_ROWS; i++) {
	//			//						int x = (TEXTURE_SIZE.x - ((TEXTURE_SPACING-1) * TrayTextureCanvas.this.MAX_COLUMNS)) / 2;
	//			//						for(int j = 0; j < TrayTextureCanvas.this.MAX_COLUMNS; j++) {
	//			//							e.gc.setForeground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_DARK_SHADOW));
	//			//							e.gc.drawLine(x, y, x, y+1);
	//			//							e.gc.setForeground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_CANVAS));
	//			//							e.gc.drawLine(x+1, y, x+1, y+1);
	//			//							x += TEXTURE_SPACING;
	//			//						}
	//			//						if (isTop()) {
	//			//							y -= TEXTURE_SPACING;
	//			//						} else {
	//			//							y += TEXTURE_SPACING;
	//			//						}
	//			//					}
	//			//				}
	//			//				protected boolean isTop() {
	//			//					return (getStyle() & SWT.TOP) != 0;
	//			//				}
	//			//			});
	//		}
	//
	//		@Override
	//		public Point computeSize(int wHint, int hHint, boolean changed) {
	//			return TEXTURE_SIZE;
	//		}
	//	}
	//
	//	protected class TrayButtonCanvas extends Canvas {
	//		protected class TrayArrowButton extends Button {
	//
	//			public TrayArrowButton() {
	//
	//				super( getButtonImage());
	//				setBorder(null);
	//				setRolloverEnabled(true);
	//
	//				addActionListener(new ActionListener() {
	//					public void actionPerformed(ActionEvent event) {
	//						if (TraySash.this.trayComposite.isInState(TrayComposite.STATE_COLLAPSED))
	//							TraySash.this.trayComposite.setState(TrayComposite.STATE_EXPANDED);
	//						else
	//							TraySash.this.trayComposite.setState(TrayComposite.STATE_COLLAPSED);
	//					}
	//				});
	//
	//				TraySash.this.trayComposite.addListener( TrayComposite.EVENT_COLLAPSED_STATE_CHANGED, new Listener() {
	//					public void handleEvent(Event event) {
	//						setContents(new ImageFigure(getButtonImage()));
	//					}
	//				});
	//			}
	//
	//			@Override
	//			protected void paintBorder(Graphics graphics) {
	//				if (hasFocus()) {
	//					graphics.setForegroundColor(ColorConstants.black);
	//					graphics.setBackgroundColor(ColorConstants.white);
	//
	//					org.eclipse.draw2d.geometry.Rectangle area = getClientArea();
	//					graphics.drawFocus(area.x, area.y, area.width - 1, area.height - 1);
	//				}
	//			}
	//		}
	//
	//		protected LightweightSystem lws;
	//		public TrayButtonCanvas(Composite parent) {
	//			super(parent, SWT.NO_REDRAW_RESIZE);
	//			setCursor(Cursors.ARROW);
	//			this.lws = new LightweightSystem();
	//			this.lws.setControl(this);
	//			this.lws.setContents( new TrayArrowButton());
	//		}
	//
	//		@Override
	//		public Point computeSize(int wHint, int hHint, boolean changed) {
	//			Dimension size = this.lws.getRootFigure().getPreferredSize(wHint, hHint);
	//			size.union(new Dimension(wHint, hHint));
	//			return new Point(size.width, size.height * 2);
	//		}
	//
	//		private Image getButtonImage() {
	//			return TraySash.this.trayComposite.isInState(TrayComposite.STATE_COLLAPSED) ?  ARROW_LEFT : ARROW_RIGHT;
	//		}
	//	}

	protected TrayComposite trayComposite;

	public TraySash(TrayComposite composite, Composite parent, int style) {

		super(parent, SWT.NONE);
		this.trayComposite = composite;
		// VZ: no BG color
		// setBackground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_TRAY_BACKGROUND));
		// VZ

		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 3;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout( layout );

		/*
		 *  VZ: the palette has a large margin.
		 *  In this margin, there are three stages.
		 *  # At the center, there is the clickable button (show / hide the tray).
		 *  # Above, there is the top texture.
		 *  # And below, there is the bottom texture.
		 */

		// VZ: I commented ALL the lines below.
		// This hides the clickable button (and the large margin) that can show / hide the tray.

		//		Control topTexture = new TrayTextureCanvas(this, SWT.TOP);
		//		Control button = new TrayButtonCanvas(this);
		//		Control bottomTexture = new TrayTextureCanvas(this, SWT.BOTTOM);

		//		topTexture.setLayoutData(new GridData(GridData.BEGINNING | GridData.FILL_BOTH));
		//		button.setLayoutData(new GridData(GridData.CENTER | GridData.FILL_HORIZONTAL));
		//		bottomTexture.setLayoutData(new GridData(GridData.END | GridData.FILL_BOTH));

		// add listeners
		MouseTrackListener listener = new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				if (TraySash.this.trayComposite.isInState(TrayComposite.STATE_EXPANDED))
					setCursor(Cursors.SIZEWE);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				if (TraySash.this.trayComposite.isInState(TrayComposite.STATE_EXPANDED))
					setCursor(Cursors.ARROW);
			}
		};
		addMouseTrackListener(listener);
		//		topTexture.addMouseTrackListener(listener);
		//		bottomTexture.addMouseTrackListener(listener);

		TrayComposite.ResizeListener resizeListener = this.trayComposite.new ResizeListener();
		addMouseListener(resizeListener);
		addMouseMoveListener(resizeListener);
		//		topTexture.addMouseListener(resizeListener);
		//		topTexture.addMouseMoveListener(resizeListener);
		//		bottomTexture.addMouseListener(resizeListener);
		//		bottomTexture.addMouseMoveListener(resizeListener);

		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_DARK_SHADOW));
				e.gc.drawLine(0, 0, 0, getBounds().height);
			}
		});
	}
}
