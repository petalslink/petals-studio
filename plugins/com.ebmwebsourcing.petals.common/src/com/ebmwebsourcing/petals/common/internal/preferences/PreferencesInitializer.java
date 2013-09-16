/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PreferencesInitializer extends AbstractPreferenceInitializer {

	/**
	 * Constructor.
	 */
	public PreferencesInitializer() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
	 * #initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {

		// Set the default version of the Petals Maven plug-in
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_MAVEN_PLUGIN_VERSION,
					PetalsConstants.DEFAULT_PETALS_MAVEN_PLUGIN );

		// Set a default group ID
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_MAVEN_GROUP_ID,
					PetalsConstants.DEFAULT_GROUP_ID );

		// Enable the automatic POM update
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_UPDATE_MAVEN_POM,
					true );

		// Disable the use of customized POM
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_USE_CUSTOMIZED_POM,
					false );

		// Do not log all the JAX-WS traces
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_LOG_JAXWS,
					false );

		// Store the SERVICES plug-in preferences
		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_ACTIVATE_CACHE,
					true );

		PreferencesManager.getPreferenceStore().setDefault(
					PreferencesManager.PREFS_HIDE_INCOMPATIBLE_SERVICES,
					false );
	}
}
