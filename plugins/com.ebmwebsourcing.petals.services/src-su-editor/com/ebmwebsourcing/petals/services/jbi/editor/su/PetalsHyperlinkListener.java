/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.jbi.editor.su;

import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsHyperlinkListener implements IHyperlinkListener {

	/**
	 * Does nothing.
	 * @see org.eclipse.ui.forms.events.IHyperlinkListener
	 * #linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
	 */
	public void linkActivated( HyperlinkEvent e ) {
		// nothing
	}


	/**
	 * Changes the foreground color.
	 * @see org.eclipse.ui.forms.events.IHyperlinkListener
	 * #linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent)
	 */
	public void linkEntered( HyperlinkEvent e ) {
		// TODO:
	}


	/**
	 * Restores the default foreground color.
	 * @see org.eclipse.ui.forms.events.IHyperlinkListener
	 * #linkExited(org.eclipse.ui.forms.events.HyperlinkEvent)
	 */
	public void linkExited( HyperlinkEvent e ) {
		// TODO:
	}
}
