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
package org.eclipse.bpel.ui.extensions;

import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractEditPart;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
public interface IEditPolicyProvider {

	/**
	 * @param editPart
	 * @return true if this provider must set its editpolicies on this editpart
	 */
	public boolean appliesTo(EditPart editPart);

	/**
	 * @return the editpolicies to set. Keys are the editPolicy keys.
	 * The entries in result are passed to {@link AbstractEditPart#installEditPolicy(Object, EditPolicy)} 
	 */
	public Map<Object, EditPolicy> getEditPolicies();

}
