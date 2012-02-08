/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * A tool tip implemented with a shell and intended for fixed locations.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class FixedShellTooltip implements ControlListener {

	private final Shell shell;
	private final Control controlToFollow;
	private final boolean right;
	private final int topMargin;
	private final AtomicBoolean shown = new AtomicBoolean( false );


	/**
	 * Constructor.
	 * @param controlToFollow the control to which this shell will be stuck to
	 * @param right true if the tool tip should be placed on the right, false to put it on the left
	 * @param topMargin the margin between the top of the tool tip and the top of the control
	 */
	public FixedShellTooltip( Control controlToFollow, boolean right, int topMargin ) {

		this.controlToFollow = controlToFollow;
		this.topMargin = topMargin;
		this.right = right;

		this.shell = new Shell( controlToFollow.getShell(), SWT.TOOL );
		populateTooltip( this.shell );
		this.shell.pack();

		controlToFollow.addControlListener( this );
		controlMoved( null );
	}


	/**
	 * Populates the tool tip.
	 * @param parent
	 */
	public abstract void populateTooltip( Composite parent );


	/**
	 * Shows the tool tip (with a small delay).
	 */
	public void show() {
		this.shown.set( true );
		this.shell.getDisplay().asyncExec( new Runnable() {
			@Override
			public void run() {

				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					e.printStackTrace();
				}

				if( FixedShellTooltip.this.shown.get())
					FixedShellTooltip.this.shell.setVisible( true );
			}
		});
	}


	/**
	 * Hides the tool tip.
	 */
	public void hide() {
		this.shown.set( false );
		this.shell.setVisible( false );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.swt.events.ControlListener
	 * #controlResized(org.eclipse.swt.events.ControlEvent)
	 */
	@Override
	public void controlResized( ControlEvent e ) {

		Rectangle rect = this.controlToFollow.getBounds();
		if( this.right )
			FixedShellTooltip.this.shell.setLocation( rect.x + rect.width, rect.y + this.topMargin );
		else
			FixedShellTooltip.this.shell.setLocation( rect.x - this.shell.getSize().x, rect.y + this.topMargin );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.swt.events.ControlListener
	 * #controlMoved(org.eclipse.swt.events.ControlEvent)
	 */
	@Override
	public void controlMoved( ControlEvent e ) {
		Rectangle rect = this.controlToFollow.getBounds();
		if( this.right )
			FixedShellTooltip.this.shell.setLocation( rect.x + rect.width, rect.y + this.topMargin );
		else
			FixedShellTooltip.this.shell.setLocation( rect.x - this.shell.getSize().x, rect.y + this.topMargin );
	}


	/**
	 * Disposes the created resources.
	 * <p>
	 * Sub-classes that override this method should call super.dispose() at the end.
	 * </p>
	 */
	public void dispose() {

		if( ! this.controlToFollow.isDisposed())
			this.controlToFollow.removeControlListener( this );

		if( ! this.shell.isDisposed()) {
			this.shell.close();
			this.shell.dispose();
		}
	}
}
