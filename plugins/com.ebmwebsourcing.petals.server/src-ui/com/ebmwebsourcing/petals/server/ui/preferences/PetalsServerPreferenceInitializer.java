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
 
package com.ebmwebsourcing.petals.server.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ebmwebsourcing.petals.server.PetalsServerPlugin;

/**
 * Class used to initialize default preference values.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServerPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
	 * #initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = PetalsServerPlugin.getDefault().getPreferenceStore();
		store.setDefault( PetalsServerPreferencePage.START_IN_CONSOLE_MODE, MessageDialogWithToggle.PROMPT );
	}
}
