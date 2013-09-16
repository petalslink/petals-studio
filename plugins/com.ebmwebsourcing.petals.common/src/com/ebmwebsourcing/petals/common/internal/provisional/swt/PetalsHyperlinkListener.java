/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.swt;

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
