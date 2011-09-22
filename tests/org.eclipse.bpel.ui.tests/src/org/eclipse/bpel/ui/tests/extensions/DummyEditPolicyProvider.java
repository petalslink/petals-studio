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
package org.eclipse.bpel.ui.tests.extensions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.ui.extensions.IEditPolicyProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
public class DummyEditPolicyProvider implements IEditPolicyProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.extensions.IEditPolicyProvider#appliesTo(org.eclipse.bpel.ui.extensions.EditPart)
	 */
	@Override
	public boolean appliesTo(EditPart editPart) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.extensions.IEditPolicyProvider#getEditPolicies()
	 */
	@Override
	public Map<Object, EditPolicy> getEditPolicies() {
		Map<Object, EditPolicy> policy = new HashMap<Object, EditPolicy>();
		policy.put(this, new DummyEditPolicy());
		return policy;
	}

}
