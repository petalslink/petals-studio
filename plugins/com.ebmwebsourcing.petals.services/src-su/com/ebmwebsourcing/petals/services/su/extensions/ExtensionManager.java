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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * The class in charge of getting the contributions made to the extension-point defined by this plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExtensionManager {

	/**
	 * The identifier of the extension used to register SU versions.
	 */
	private static String EXTENSION_ID = "com.ebmwebsourcing.petals.services.componentExtension"; //$NON-NLS-1$

	/**
	 * The unique instance of RegisteredContributors.
	 */
	public static ExtensionManager INSTANCE = new ExtensionManager ();

	/**
	 * The component descriptions.
	 */
	private final List<ComponentVersionDescription> descriptions;


	/**
	 * Constructor.
	 */
	private ExtensionManager() {
		this.descriptions = findComponentVersionClass( "componentVersionDescription", ComponentVersionDescription.class );
	}


	/**
	 * Finds the wizard handlers.
	 * @return the wizard handlers (never null, possibly empty)
	 */
	public List<ComponentWizardHandler> findWizardandlers(PetalsMode mode) {
		List<ComponentWizardHandler> handlers = new ArrayList<ComponentWizardHandler>();
		for (ComponentWizardHandler handler : findComponentVersionClass("wizardExtension", ComponentWizardHandler.class )) {
			if (handler.getComponentVersionDescription().isConsume() && mode == PetalsMode.consumes) {
				handlers.add(handler);
			}
			if (handler.getComponentVersionDescription().isProvide() && mode == PetalsMode.provides) {
				handlers.add(handler);
			}
		}
		return handlers;
	}


	/**
	 * Finds a class associated with a component version.
	 * @return the wizard handlers (never null, possibly empty)
	 */
	@SuppressWarnings( "unchecked" )
	private <T> List<T> findComponentVersionClass(String attributeName, Class<T> theClass ) {

		List<T> result = new ArrayList<T> ();
		IConfigurationElement[] extensions = Platform.getExtensionRegistry().getConfigurationElementsFor( EXTENSION_ID );
		for( IConfigurationElement elt : extensions ) {

			for( IConfigurationElement child : elt.getChildren( "ComponentVersionSupport" )) {

				String theClassName = child.getAttribute( attributeName );
				if( StringUtils.isEmpty( theClassName )) {
					PetalsServicesPlugin.log( "No class was provided for " + child.getContributor().getName(), IStatus.WARNING );
					continue;
				}

				try {
					Object o = child.createExecutableExtension( attributeName );
					if( theClass.isInstance( o ))
						result.add((T) o );
					else
						PetalsServicesPlugin.log( "Expected " + theClass + " but found " + o.getClass() + ".", IStatus.ERROR );

				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		}

		return result;
	}


	/**
	 * Finds the component alias (e.g. SOAP, FTP).
	 * @param componentName the component name
	 * @return the component alias, or null if it was not found
	 */
	public String findComponentAlias( String componentName ) {

		String alias = null;
		for( ComponentVersionDescription desc : this.descriptions ) {
			if( ! desc.getComponentName().equals( componentName ))
				continue;

			alias = desc.getComponentAlias();
			break;
		}

		return alias;
	}


	/**
	 * Finds the versions associated with this component.
	 * @param componentName the component name
	 * @return the associated versions (never null)
	 */
	public SortedSet<String> findComponentVersions( String componentName ) {

		SortedSet<String> result = new TreeSet<String> ();
		for( ComponentVersionDescription desc : this.descriptions ) {
			if( desc.getComponentName().equals( componentName ))
				result.add( desc.getComponentVersion());
		}

		return result;
	}


	/**
	 * Finds all the component names.
	 * @return a non-null collection of component names
	 */
	public Set<String> findAllComponentNames() {

		SortedSet<String> result = new TreeSet<String> ();
		for( ComponentVersionDescription desc : this.descriptions )
			result.add( desc.getComponentName());

		return result;
	}


	/**
	 * Finds the default operations for a component.
	 * @param componentName the component name
	 * @param componentVersion the component version
	 * @return a (never null) map with the default operations (key = operation name, value = MEP)
	 */
	public Map<QName,Mep> findDefaultOperations( String componentName, String componentVersion ) {
		ComponentVersionDescription desc = findComponentDescription( componentName, componentVersion );
		return desc != null ? desc.getDefaultOperations() : new HashMap<QName,Mep>( 0 );
	}


	/**
	 * Finds a component description
	 * @param componentName the component name
	 * @param componentVersion the component version (null to pick up the first found version)
	 * @return a component version's description, or null if none was found
	 */
	public ComponentVersionDescription findComponentDescription( String componentName, String componentVersion ) {

		ComponentVersionDescription result = null;
		for( ComponentVersionDescription desc : this.descriptions ) {
			if( desc.getComponentName().equals( componentName )) {
				if( componentVersion == null || desc.getComponentVersion().equals( componentVersion )) {
					result = desc;
					break;
				}
			}
		}

		return result;
	}
}
