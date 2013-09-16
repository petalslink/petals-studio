/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * A selection listener which has the same behavior for the two methods to implement.
 * <p>
 * This class reduces the amount of code to write and makes code maintenance easier.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class DefaultSelectionListener implements SelectionListener {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener
	 * #widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public final void widgetDefaultSelected( SelectionEvent e ) {
		widgetSelected( e );
	}
}
