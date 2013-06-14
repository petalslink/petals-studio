/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

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
