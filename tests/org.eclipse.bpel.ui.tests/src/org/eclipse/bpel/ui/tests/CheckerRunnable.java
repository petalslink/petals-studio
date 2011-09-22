/*******************************************************************************
* Copyright (c) 2011 PetalsLink
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Mickael Istria, PetalsLink - initial API and implementation
*******************************************************************************/
package org.eclipse.bpel.ui.tests;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
abstract class CheckerRunnable implements Runnable {
	protected boolean result;

	@Override
	public void run() {
		result = evaluateCondition();
	}
	
	public abstract boolean evaluateCondition();

	public boolean getResult() {
		return result;
	}
}