/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * The class in charge of getting the contributions made to the extension-point defined by this plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RegisteredContributors {

	/**
	 * The identifier of the extension used to register SU versions.
	 */
	private static String EXTENSION_ID = "com.ebmwebsourcing.petals.services.contentExtension"; //$NON-NLS-1$

	/**
	 * The unique instance of RegisteredContributors.
	 */
	private static RegisteredContributors instance = new RegisteredContributors ();

	/**
	 * The collection of contributions.
	 */
	private final Set<JbiComponentInformation> componentInformationSet = new HashSet<JbiComponentInformation>();
	private final static String SU_TYPE = "SuType";



	/**
	 * Fill the structures <i>provideSuData</i>, <i>consumeSuData</i> and <i>seSuData</i> by getting the plugins using our extension-point.
	 */
	private RegisteredContributors() {

		// Get the registered BC in the extension registry.
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_ID );

		// Add predefined configurations
		this.componentInformationSet.addAll( getPredefinedComponents());

		// For each registered extension using our extension point...
		for( IConfigurationElement suNameElement : extensions ) {

			// Get the SU type
			String pluginId = suNameElement.getContributor().getName();
			String suType = suNameElement.getAttribute( SU_TYPE );
			if( StringUtils.isEmpty( suType )) {
				PetalsServicesPlugin.log( "No SU type was provided for " + pluginId, IStatus.WARNING );
				continue;
			}

			// Get the contributor's description
			String theClassName = suNameElement.getAttribute( "ContributorDescription" );
			if( StringUtils.isEmpty( theClassName )) {
				PetalsServicesPlugin.log( "No description class was provided for " + pluginId, IStatus.WARNING );
				continue;
			}

			SuContributorDescription contributorDesc;
			try {
				contributorDesc =
					(SuContributorDescription) suNameElement.createExecutableExtension( "ContributorDescription" );

			} catch( CoreException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
				continue;
			}


			// Get the versions registered for this component (check folders in "resources/xsd" of the associated plug-in).
			List<String> versions = getVersions( pluginId );
			Map<String,JbiComponentInformation> versionsInPluginXml = new HashMap<String,JbiComponentInformation> ();


			// Manage the versions mentioned in the plugin.xml file.
			IConfigurationElement[] suTypeChildren = suNameElement.getChildren();
			for( IConfigurationElement element : suTypeChildren ) {
				IConfigurationElement suVersionElement = element;
				if( ! suVersionElement.getName().equals( "Version" )) //$NON-NLS-1$
					continue;

				// Create the information to store.
				String versionName = suVersionElement.getAttribute( "SuVersion" );
				JbiComponentInformation comp = new JbiComponentInformation( suType, contributorDesc, versionName, pluginId );

				IConfigurationElement[] elts = suVersionElement.getChildren( "ConsumeDescription" );
				if( elts.length == 1 )
					comp.consumeDescription = elts[ 0 ].getAttribute( "description" );

				elts = suVersionElement.getChildren( "ProvideDescription" );
				if( elts.length == 1 )
					comp.provideDescription = elts[ 0 ].getAttribute( "description" );

				versionsInPluginXml.put( versionName, comp );
			}


			// Manage the other versions.
			for( String version : versions ) {
				JbiComponentInformation comp = versionsInPluginXml.get( version );
				if( comp == null )
					comp = new JbiComponentInformation( suType, contributorDesc, version, pluginId );

				if( ! comp.contributor.isProvide()
							&& ! comp.contributor.isConsume()) {
					PetalsServicesPlugin.log(
								comp.contributor.getComponentName() + " was ignored (neither a provider nor a consumer).",
								IStatus.WARNING );

				} else {
					this.componentInformationSet.add( comp );

					// Prepare the next step
					versionsInPluginXml.remove( version );
				}
			}

			// Handle versions which are only declared in the jbi.xml file
			for( JbiComponentInformation jci : versionsInPluginXml.values()) {
				if( ! jci.contributor.isProvide()
						&& ! jci.contributor.isConsume()) {
					PetalsServicesPlugin.log(
								jci.contributor.getComponentName() + " was ignored (neither a provider nor a consumer).",
								IStatus.WARNING );

				} else {
					this.componentInformationSet.add( jci );
				}
			}
		}
	}


	/**
	 * Get the versions by looking into the component plug-in resource folder.
	 * @param name
	 * @return
	 */
	private List<String> getVersions( String pluginId ) {

		List<String> result = new ArrayList<String> ();
		Bundle bundle = Platform.getBundle( pluginId );
		try {
			Enumeration<?> enume = bundle.getEntryPaths( "/" + SuMainConstants.COMPONENT_XSD_LOCATION ); //$NON-NLS-1$

			// Create versions
			if( enume != null ) {
				while( enume.hasMoreElements()) {
					Object o = enume.nextElement();
					if( o instanceof String ) {
						String[] segments = ((String) o).split( "/" ); //$NON-NLS-1$
						String version = segments[ segments.length - 1 ];
						if( ! version.startsWith( "." )) //$NON-NLS-1$
							result.add( version );
					}
				}
			}

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * @return the unique instance of <i>RegisteredContributors</i>.
	 */
	public static RegisteredContributors getInstance() {
		return instance;
	}


	// Classes registered in the extensions.


	/**
	 * Return an instance of the class in charge of performing the last actions on the created project,
	 * and registered in a plugin.xml file for the version <i>suTypeVersion</i> of the SU type <i>suType</i>.
	 *
	 * @param suType a type of registered SU.
	 * @param suTypeVersion the version of the previous SU type.
	 * @return the registered class of the wizard if it exists, or <i>null</i> otherwise.
	 */
	public LastActionsPerformer getLastActionsPerformerClass( String suType, String suTypeVersion ) {

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_ID );
		for( IConfigurationElement suTypeElement : extensions ) {

			String suTypeName = suTypeElement.getAttribute( SU_TYPE );
			if( suTypeName == null || ! suTypeName.equals( suType ))
				continue;

			/* Be here means we have found the right SU type => find the right version. */
			IConfigurationElement[] suTypeChildren = suTypeElement.getChildren();
			for( IConfigurationElement element : suTypeChildren ) {

				IConfigurationElement suVersionElement = element;
				if( !suVersionElement.getName().equals( "Version" )) //$NON-NLS-1$
					continue;

				String version = suVersionElement.getAttribute( "SuVersion" ); //$NON-NLS-1$
				if( version == null || !version.equals( suTypeVersion ))
					continue;

				/* Be here means we have found the right version - don't look further. */
				/* Create a new instance of the associated class - Java Reflect API can not work here. */
				try {
					String theClassName = suVersionElement.getAttribute( "LastActionsPerformer" );
					if( theClassName == null || "".equals( theClassName ))
						return null;

					Object theClass = suVersionElement.createExecutableExtension( "LastActionsPerformer" );
					return (LastActionsPerformer) theClass;

				} catch( CoreException e ) {	// log the error
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
				return null;	// don't loop anymore
			}
		}

		return null;
	}


	/**
	 * Return an instance of the class in charge of customizing the JBI page,
	 * and registered in a plugin.xml file for the version <i>suTypeVersion</i> of the SU type <i>suType</i>.
	 *
	 * @param suType a type of registered SU.
	 * @param suTypeVersion the version of the previous SU type.
	 * @param inConsumeMode true to get the consume configuration, false for the provide
	 * @return the registered class of the wizard if it exists, or an instance of {@link WizardConfiguration} otherwise.
	 */
	public WizardConfiguration getWizardConfigurationClass( String suType, String suTypeVersion, boolean inConsumeMode ) {

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_ID );
		for( IConfigurationElement suTypeElement : extensions ) {

			String suTypeName = suTypeElement.getAttribute( SU_TYPE );
			if( suTypeName == null || ! suTypeName.equals( suType ))
				continue;

			/* Be here means we have found the right SU type => find the right version. */
			IConfigurationElement[] suTypeChildren = suTypeElement.getChildren();
			for( IConfigurationElement element : suTypeChildren ) {

				IConfigurationElement suVersionElement = element;
				if( !suVersionElement.getName().equals( "Version" )) //$NON-NLS-1$
					continue;

				String version = suVersionElement.getAttribute( "SuVersion" ); //$NON-NLS-1$
				if( version == null || !version.equals( suTypeVersion ))
					continue;

				String attrName = inConsumeMode ? "ConsumeConfiguration" : "ProvideConfiguration";

				/* Be here means we have found the right version - don't look further. */
				/* Create a new instance of the associated class - Java Reflect API can not work here. */
				try {
					String theClassName = suVersionElement.getAttribute( attrName );
					if( theClassName == null || "".equals( theClassName ))
						return new WizardConfiguration ();

					Object theClass = suVersionElement.createExecutableExtension( attrName );
					return (WizardConfiguration) theClass;

				} catch( CoreException e ) {	// log the error
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}

				return new WizardConfiguration ();	// don't loop anymore
			}
		}

		return new WizardConfiguration ();
	}


	/**
	 * Return an instance of the class in charge of replacing the component page,
	 * and registered in a plugin.xml file for the version <i>suTypeVersion</i> of the SU type <i>suType</i>.
	 *
	 * @param suType a type of registered SU.
	 * @param suTypeVersion the version of the previous SU type.
	 * @param getCdkPage true if we want the custom CDK page, false for the custom component page.
	 * @return the registered class of the wizard if it exists, or <i>null</i> otherwise.
	 */
	public AbstractSuPage getCustomPage( String suType, String suTypeVersion, boolean getCdkPage ) {

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_ID );
		for( IConfigurationElement suTypeElement : extensions ) {

			String suTypeName = suTypeElement.getAttribute( SU_TYPE );
			if( suTypeName == null || ! suTypeName.equals( suType ))
				continue;

			/* Be here means we have found the right SU type => find the right version. */
			IConfigurationElement[] suTypeChildren = suTypeElement.getChildren();
			for( IConfigurationElement element : suTypeChildren ) {

				IConfigurationElement suVersionElement = element;
				if( !suVersionElement.getName().equals( "Version" ))
					continue;

				String version = suVersionElement.getAttribute( "SuVersion" );
				if( version == null || !version.equals( suTypeVersion ))
					continue;

				/* Be here means we have found the right version - don't look further. */
				/* Create a new instance of the associated class - Java Reflect API can not work here. */
				String theClassName = "";
				try {
					Object theClass;
					if( getCdkPage ) {
						theClassName = suVersionElement.getAttribute( "CustomCdkPage" );
						if( theClassName == null || theClassName.length() == 0 )
							return null;

						theClass = suVersionElement.createExecutableExtension( "CustomCdkPage" );
					}
					else {
						theClassName = suVersionElement.getAttribute( "CustomComponentPage" );
						if( theClassName == null || theClassName.length() == 0 )
							return null;

						theClass = suVersionElement.createExecutableExtension( "CustomComponentPage" );
					}

					return (AbstractSuPage) theClass;

				} catch( CoreException e ) {	// log the error
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}

				return null;	// don't loop anymore
			}
		}

		return null;
	}


	// Wizard pages.

	/**
	 *
	 */
	public enum CustomPagePosition {
		beforeJbiPage, beforeSpecificPage, beforeCdkPage, afterCdkPage;
	}


	/**
	 * Returns the wizard class registered for the version <i>suTypeVersion</i> of the SU type <i>suType</i>.
	 *
	 * @param suType a type of registered SU.
	 * @param suTypeVersion the version of the previous SU type.
	 * @param inConsumeMode
	 * @param customPagePosition
	 *
	 * @return the registered class of the wizard to display if it exists, or <i>null</i> otherwise.
	 * If the result is not null, the suType and suTypeVersion fields are already initialized.
	 * Same thing for the page description.
	 */
	public List<AbstractSuPage> getCustomWizardPages(
				String suType, String suTypeVersion, boolean inConsumeMode, CustomPagePosition customPagePosition ) {

		List<AbstractSuPage> result = new ArrayList<AbstractSuPage> ();
		JbiComponentInformation comp = resolveSuType( suType, suTypeVersion );
		if( comp == null )
			return result;

		// Get the registered SUs in the extension registry.
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor( EXTENSION_ID );
		for( IConfigurationElement suTypeElement : extensions ) {
			// Find the right component.
			String suTypeName = suTypeElement.getAttribute( SU_TYPE );
			if( suTypeName == null || ! suTypeName.equals( suType ))
				continue;

			// Find the right version.
			IConfigurationElement[] suTypeChildren = suTypeElement.getChildren();
			for( IConfigurationElement element : suTypeChildren ) {
				IConfigurationElement suVersionElement = element;
				if( !suVersionElement.getName().equals( "Version" ))
					continue;

				String version = suVersionElement.getAttribute( "SuVersion" ); //$NON-NLS-1$
				if( version == null || !version.equals( suTypeVersion ))
					continue;

				IConfigurationElement[] suVersionChildren = suVersionElement.getChildren();

				// Find a wizard page in the right mode and in the right position.
				for( IConfigurationElement element2 : suVersionChildren ) {
					IConfigurationElement suWizardPageElement = element2;
					if( !suWizardPageElement.getName().equals( "WizardPage" ))
						continue;

					String wizardPageMode = suWizardPageElement.getAttribute( "WizardPageMode" ); //$NON-NLS-1$
					String wizardPagePosition = suWizardPageElement.getAttribute( "WizardPagePosition" ); //$NON-NLS-1$

					// Provide, Both, SE.
					if( inConsumeMode && ! comp.contributor.isConsume())
						continue;

					if( !inConsumeMode && ! comp.contributor.isProvide())
						continue;

					if( inConsumeMode && wizardPageMode.equalsIgnoreCase( "provide" ))
						continue;

					if( !inConsumeMode && wizardPageMode.equalsIgnoreCase( "consume" ))
						continue;

					if( customPagePosition != CustomPagePosition.valueOf( wizardPagePosition ))
						continue;

					try {
						AbstractSuPage suPage = (AbstractSuPage) suWizardPageElement.createExecutableExtension( "WizardPageClass" );

						suPage.setSuType( suType );
						suPage.setSuTypeVersion( suTypeVersion );
						String desc;
						if( inConsumeMode )
							desc = getConsumeDescription( suTypeName, suTypeVersion );
						else
							desc = getProvideDescription( suTypeName, suTypeVersion );

						suPage.setDescription( desc );
						result.add( suPage );

					} catch( CoreException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );
					}
				}
			}
		}

		return result;
	}


	// Utilities.

	/**
	 * Gets a map associating Petals use cases and SU types.
	 * <p>
	 * Use cases that have no component are not in the resulting map.
	 * </p>
	 * @return a non-null map (key = use case, value = list of SU types)
	 */
	public Map<PetalsUseCase, List<String>> getUseCaseToSuTypes() {

		List<String> visitedTypes = new ArrayList<String> ();
		Map<PetalsUseCase, List<String>> result = new HashMap<PetalsUseCase,List<String>> ();
		for( JbiComponentInformation jci : this.componentInformationSet ) {

			if( visitedTypes.contains( jci.suType ))
				continue;

			for( PetalsUseCase uc : jci.contributor.getUsesCases()) {
				List<String> list = result.get( uc );
				if( list == null )
					list = new ArrayList<String> ();

				list.add( jci.suType );
				visitedTypes.add( jci.suType );
				result.put( uc, list );
			}
		}

		return result;
	}


	/**
	 * @param suType
	 * @return
	 */
	public SortedSet<String> getSupportedVersions( String suType ) {

		SortedSet<String> result = new TreeSet<String>();
		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( comp.suType.equals( suType ))
				result.add( comp.suTypeVersion );
		}

		return result;
	}


	/**
	 * @param componentName
	 * @return
	 */
	public SortedSet<String> getComponentVersions( String componentName ) {

		SortedSet<String> result = new TreeSet<String>();
		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( comp.contributor.getComponentName().equals( componentName ))
				result.add( comp.suTypeVersion );
		}

		return result;
	}


	/**
	 * @param componentName
	 * @return
	 */
	public Map<QName,Mep> getDefaultOperations( String componentName ) {

		Map<QName,Mep> result;
		JbiComponentInformation comp = resolveComponent( componentName, null );
		if( comp != null )
			result = comp.contributor.getDefaultOperations();
		else
			result = new HashMap<QName,Mep> ();

		// Hack for the KPI SE
		if( "petals-se-kpi".equals( componentName ))
			result.put( new QName( "Subscribe" ), Mep.IN_OUT );

		return result;
	}


	/**
	 * A SU type is expected to be contributed by only one plug-in.
	 * This method looks for the plug-in which "defined" this SU type.
	 * @param suType the SU type to look for.
	 * @return the plug-in ID of the contributor, or <i>null</i> if it was not found.
	 */
	public String getPluginId( String suType ) {
		JbiComponentInformation comp = resolveSuType( suType, null );
		return comp != null ? comp.pluginId : null;
	}


	/**
	 * Checks whether a wizard is available or not..
	 *
	 * @param suType a type of registered SU.
	 * @return true if the wizard is enabled, false otherwise
	 */
	public String getWizardEnablement( String suType ) {

		String errorMsg = null;
		JbiComponentInformation comp = resolveSuType( suType, null );
		if( comp != null )
			errorMsg = comp.contributor.isWizardEnabled();

		return errorMsg;
	}


	/**
	 * Return the consume description of this SU for the given version.
	 *
	 * @param suType
	 * @param suVersion
	 * @return
	 */
	public String getConsumeDescription( String suType, String suVersion ) {

		String result = null;
		JbiComponentInformation comp = resolveSuType( suType, suVersion );
		if( comp != null ) {
			result = comp.consumeDescription;
			if( result == null )
				result = comp.contributor.getConsumeDescription();
		}

		return result != null ? result : "";
	}


	/**
	 * Return the provide description of this SU for the given version.
	 *
	 * @param suType
	 * @param suVersion
	 * @return
	 */
	public String getProvideDescription( String suType, String suVersion ) {

		String result = null;
		JbiComponentInformation comp = resolveSuType( suType, suVersion );
		if( comp != null ) {
			result = comp.provideDescription;
			if( result == null )
				result = comp.contributor.getProvideDescription();
		}

		return result != null ? result : "";
	}


	/**
	 * @param suType
	 * @return
	 */
	public boolean isBc( String suType ) {
		JbiComponentInformation comp = resolveSuType( suType, null );
		return comp != null ? comp.contributor.isBc() : false;
	}


	/**
	 * @param suType
	 * @return
	 */
	public String getComponentName( String suType ) {
		JbiComponentInformation comp = resolveSuType( suType, null );
		return comp != null ? comp.contributor.getComponentName() : "";
	}


	/**
	 * @param suType
	 * @return
	 */
	public String getAnnotation( String suType ) {
		JbiComponentInformation comp = resolveSuType( suType, null );
		return comp != null ? comp.contributor.getAnnotation() : null;
	}


	/**
	 * @param componentName
	 * @return a list of SU types that are associated with this component name (not null)
	 */
	public List<String> getSuType( String componentName ) {

		List<String> result = new ArrayList<String> ();
		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( comp.contributor.getComponentName().equals( componentName ))
				result.add( comp.suType );
		}

		return result;
	}


	/**
	 * @param suType
	 * @param suTypeVersion
	 * @return
	 */
	public boolean worksInProvides( String suType, String suTypeVersion ) {
		JbiComponentInformation comp = resolveSuType( suType, suTypeVersion );
		return comp != null ? comp.contributor.isProvide() : false;
	}


	/**
	 * @param suType
	 * @param suTypeVersion
	 * @return
	 */
	public boolean worksInConsumes( String suType, String suTypeVersion ) {
		JbiComponentInformation comp = resolveSuType( suType, suTypeVersion );
		return comp != null ? comp.contributor.isConsume() : false;
	}


	/**
	 * @param suType
	 * @return
	 */
	public Set<ValidationRule> getValidationRules( String suType ) {
		JbiComponentInformation comp = resolveSuType( suType, null );
		Set<ValidationRule> result;
		if( comp != null )
			result = comp.contributor.getValidationRules();
		else
			result = Collections.emptySet();

		return result;
	}


	/**
	 * @param suType
	 * @param suVersion null for any version
	 * @return
	 */
	private JbiComponentInformation resolveSuType( String suType, String suVersion ) {
		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( comp.suType.equals( suType )
						&& ( suVersion == null || comp.suTypeVersion.equals( suVersion )))
				return comp;
		}

		return null;
	}


	/**
	 * Indicates whether a component type is known by the Studio.
	 * @param suType
	 * @return
	 */
	public boolean isKnownComponentType( String suType ) {
		return resolveSuType( suType, null ) != null;
	}


	/**
	 * Indicates whether a component is known by the Studio.
	 * @param componentName the component name
	 * @param componentVersion the component version, or null for any version
	 * @return the SU type if this component and this version are known, null otherwise
	 */
	private JbiComponentInformation resolveComponent( String componentName, String componentVersion ) {

		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( comp.contributor.getComponentName().equals( componentName )
						&& ( componentVersion == null || comp.suTypeVersion.equals( componentVersion )))
				return comp;
		}

		return null;
	}


	/**
	 * @param componentName
	 * @param componentVersion
	 * @return
	 */
	public boolean componentWorksInProvides( String componentName, String componentVersion ) {
		JbiComponentInformation comp = resolveComponent( componentName, componentVersion );
		return comp != null ? comp.contributor.isProvide() : false;
	}


	/**
	 * @param componentName
	 * @param componentVersion
	 * @return
	 */
	public boolean componentWorksInConsumes( String componentName, String componentVersion ) {
		JbiComponentInformation comp = resolveComponent( componentName, componentVersion );
		return comp != null ? comp.contributor.isConsume() : false;
	}


	/**
	 * @param componentName
	 * @return
	 */
	public boolean componentIsBc( String componentName ) {
		JbiComponentInformation comp = resolveComponent( componentName, null );
		return comp != null ? comp.contributor.isBc() : false;
	}


	/**
	 * Indicates whether a component is known by the Studio.
	 * @param componentName
	 * @param componentVersion
	 * @return
	 */
	public boolean isKnownComponent( String componentName, String componentVersion ) {
		return resolveComponent( componentName, componentVersion ) != null;
	}


	/**
	 * @return all the known component names
	 */
	public Set<String> getAllComponentNames() {

		Set<String> result = new HashSet<String> ();
		for( JbiComponentInformation comp : this.componentInformationSet ) {
			if( ! StringUtils.isEmpty( comp.contributor.getComponentName() ))
				result.add( comp.contributor.getComponentName());
		}

		return result;
	}


	/**
	 * A class to store the data to retrieve from the extension registry about one SU type version.
	 * This approach (use a bean) is better for scalability and modularity.
	 * Let this class in this file since this structure is clearly related to the
	 * extension-point structure and to the methods defined in this class.
	 */
	private static class JbiComponentInformation {

		/** The version of the SU type (e.g. "2.1"). */
		String suTypeVersion;

		/** The description of the SU wizard for the given version. */
		String provideDescription;

		/** The description of the SU wizard for the given version. */
		String consumeDescription;

		/** The plug-in ID of the contributor. */
		String pluginId;

		/** The description of the contributor. */
		SuContributorDescription contributor;

		/**
		 * The SU type (e.g. FTP, File Transfer).
		 */
		String suType;


		/**
		 * @param suType
		 * @param contributor
		 * @param suTypeVersion
		 * @param pluginId
		 */
		public JbiComponentInformation(
					String suType,
					SuContributorDescription contributor,
					String suTypeVersion,
					String pluginId ) {

			this.suType = suType;
			this.contributor = contributor;
			this.suTypeVersion = suTypeVersion;
			this.pluginId = pluginId;
		}


		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			int a1 = this.suType == null ? 17 : this.suType.charAt( 0 ) * this.suType.length();
			int a2 = this.suTypeVersion == null ? 17 : this.suTypeVersion.charAt( 0 ) * this.suTypeVersion.length();
			return a1 * a2;
		}


		/**
		 * Equality only if the SU types and versions are equal.
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals( Object o ) {
			boolean result = o != null && o instanceof JbiComponentInformation;
			if( !result )
				return false;

			JbiComponentInformation su = (JbiComponentInformation) o;
			result = this.suType == null ?
						su.suType == null
						: this.suType.equals( su.suType );

			if( !result )
				return false;

			result = this.suTypeVersion == null ? su.suTypeVersion == null : this.suTypeVersion.equals( su.suTypeVersion );
			return result;
		}


		/*
		 * (non-Jsdoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.suType + " - " + this.suTypeVersion;
		}
	}


	/**
	 * Used for components whose wizards are else where, or do not use the same mechanism than the others.
	 * @return a list of pre-defined configurations
	 */
	public List<JbiComponentInformation> getPredefinedComponents() {

		List<JbiComponentInformation> comps = new ArrayList<JbiComponentInformation> ();

		// Talend
		DefaultSuContributorDescription desc = new DefaultSuContributorDescription();
		desc.setComponentName( "petals-se-talend" );
		desc.setProvide( true );

		JbiComponentInformation talend = new JbiComponentInformation( "Talend", desc, "1.0", null );
		comps.add( talend );

		// KPI
		desc = new DefaultSuContributorDescription();
		desc.setComponentName( "petals-se-kpi" );
		desc.setConsume( true );

		JbiComponentInformation kpi = new JbiComponentInformation( "KPI", desc, "1.0", null );
		comps.add( kpi );

		return comps;
	}
}
