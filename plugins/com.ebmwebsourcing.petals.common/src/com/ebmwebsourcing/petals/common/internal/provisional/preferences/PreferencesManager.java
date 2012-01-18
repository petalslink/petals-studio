/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.preferences;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A manager for all the preferences in the studio.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PreferencesManager {

	/**
	 * The Maven plug-in version for SU projects.
	 */
	public static final String PREFS_MAVEN_PLUGIN_VERSION = "PetalsMavenPluginVersion";

	/**
	 * The Maven parent file for SU and SA projects.
	 */
	public static final String PREFS_MAVEN_POM_PARENT = "PetalsMavenPomParent";

	/**
	 * The default group ID for SU and SA projects.
	 */
	public static final String PREFS_MAVEN_GROUP_ID = "PetalsMavenGroupId";

	/**
	 * True to update automatically POM dependencies, false otherwise.
	 */
	public static final String PREFS_UPDATE_MAVEN_POM = "PetalsUpdateMavenPom";

	/**
	 * True to log all the JAX-WS traces, false otherwise.
	 */
	public static final String PREFS_LOG_JAXWS = "PetalsLogJaxWsTraces";

	/**
	 * True to use customized POM, false otherwise.
	 */
	public static final String PREFS_USE_CUSTOMIZED_POM = "UseCustomizedPoms";

	/**
	 * The location of the customized POM.
	 */
	public static final String PREFS_CUSTOMIZED_POM_LOCATION = "CustomizedPomLocation";

	/**
	 * True to activate the SU cache, false otherwise.
	 */
	public static final String PREFS_ACTIVATE_CACHE = "ActivatePetalsStudioCache";

	/**
	 * True to hide incompatible services in consume dialogs, false otherwise.
	 */
	public static final String PREFS_HIDE_INCOMPATIBLE_SERVICES = "HideIncompatibleServicesForConsumers";

	/**
	 * Remember the last location for a file or directory dialog.
	 */
	public static final String PREFS_SAVED_LOCATION = "LastDialogLocation";


	/**
	 * @return the group ID for Maven
	 */
	public static String getMavenGroupId() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getString( PREFS_MAVEN_GROUP_ID );
	}


	/**
	 * @return the version of the Maven plug-in for Petals
	 */
	public static String getMavenPluginVersion() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getString( PREFS_MAVEN_PLUGIN_VERSION );
	}


	/**
	 * @return the location of the POM's parent
	 */
	public static String getMavenPomParent() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getString( PREFS_MAVEN_POM_PARENT );
	}


	/**
	 * @return the location of the directory that contains the customized templates
	 */
	public static String getCustomizedTemplatesLocation() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getString( PREFS_CUSTOMIZED_POM_LOCATION );
	}


	/**
	 * @return true if customized templates must be used, false otherwise
	 */
	public static boolean useCustomizedTemplates() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( PREFS_USE_CUSTOMIZED_POM );
	}


	/**
	 * @return true if SA's POM should be regenerated automatically, false otherwise
	 */
	public static boolean regeneratePomAutomatically() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( PREFS_UPDATE_MAVEN_POM );
	}


	/**
	 * @return true if all the JAX-WS traces should be logged, false otherwise
	 */
	public static boolean logAllJaxWsTraces() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( PREFS_LOG_JAXWS );
	}


	/**
	 * @return true if the SU cache should be enabled, false otherwise
	 */
	public static boolean isSuCacheEnabled() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( PREFS_ACTIVATE_CACHE );
	}


	/**
	 * @return true if incompatible services should be hidden in consume dialogs, false otherwise
	 */
	public static boolean hideIncompatibleServicesInConsumeDialog() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getBoolean( PREFS_HIDE_INCOMPATIBLE_SERVICES );
	}


	/**
	 * @return the last saved location
	 */
	public static String getSavedLocation() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore().getString( PREFS_SAVED_LOCATION );
	}


	/**
	 * Saves a location in the preferences.
	 * @param location the location to save
	 */
	public static void setSavedLocation( String location ) {
		PetalsCommonPlugin.getDefault().getPreferenceStore().setValue( PREFS_SAVED_LOCATION, location );
	}


	/**
	 * @return the preference store to use in the studio
	 */
	public static IPreferenceStore getPreferenceStore() {
		return PetalsCommonPlugin.getDefault().getPreferenceStore();
	}


	/**
	 * @return true if the configuration for POM templates is valid.
	 * <p>
	 * Such a configuration is valid if:
	 * </p>
	 * <ul>
	 * 		<li>Custom templates are not used.</li>
	 * 		<li>Custom templates are used and the root location points to an existing directory.</li>
	 * </ul>
	 */
	public static boolean isMavenTemplateConfigurationValid() {

		boolean result = true;
		if( useCustomizedTemplates()) {
			String loc = getCustomizedTemplatesLocation();
			if( loc != null ) {
				File f = new File( loc );
				result = f.exists() && f.isDirectory();
			}
		}

		return result;
	}
}
