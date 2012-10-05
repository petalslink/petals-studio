/******************************************************************************
 * Copyright (c) 2012, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.su;

import org.eclipse.swt.widgets.Listener;

/**
 * A modify listener that can be enabled or disabled.
 * <p>
 * Sub-classes simply need to call {@link #isEnabled()}
 * in {@link #handleEvent(org.eclipse.swt.widgets.Event)}.
 * </p>
 *
 * @author Vincent Zurczak - Linagora
 */
public abstract class ActivableListener implements Listener {

	private boolean enabled = true;


	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled( boolean enabled ) {
		this.enabled = enabled;
	}
}
