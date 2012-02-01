/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectProxyManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.sa.projectscnf.SaProjectCategory;
import com.ebmwebsourcing.petals.services.su.projectscnf.SuProjectCategory;

/**
 * A set of utilities to get relation and dependencies between SU and SA projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceProjectRelationUtils {

	/**
	 * @param projectsToExclude
	 * @return
	 */
	public static List<IProject> getOrphanSuProjects( IProject... projectsToExclude ) {

		PetalsProjectProxyManager proxyManager = new PetalsProjectProxyManager();
		List<IProject> saProjects = proxyManager.getProjects( SaProjectCategory.SA_CATEGORY_ID );
		List<IProject> referencedProjects = new ArrayList<IProject> ();
		for( IProject p : saProjects ) {
			if( p.isAccessible()) {
				try {
					referencedProjects.addAll( Arrays.asList( p.getReferencedProjects()));

				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		}

		List<IProject> suProjects = proxyManager.getProjects( SuProjectCategory.SU_CATEGORY_ID );
		suProjects.removeAll( referencedProjects );
		if( projectsToExclude != null )
			suProjects.removeAll( Arrays.asList( projectsToExclude ));

		return suProjects;
	}


	/**
	 * Gets all the SA projects that references this SU.
	 * @param suProject a SU project (not null)
	 * @return a (non-null) list of SA projects
	 */
	public static List<IProject> getReferencingSaProjects( IProject suProject ) {

		List<IProject> saProjects = new PetalsProjectProxyManager().getProjects( SaProjectCategory.SA_CATEGORY_ID );
		List<IProject> referencingSaProjects = new ArrayList<IProject> ();
		for( IProject p : suProject.getReferencingProjects()) {
			if( p.isAccessible()
						&& saProjects.contains( p ))
				referencingSaProjects.add( p );
		}

		return referencingSaProjects;
	}


	/**
	 * Determines if a SU is referenced by several SA projects.
	 * @param suProject the SU project
	 * @return true if it is referenced by several SA, false otherwise
	 */
	public static boolean isReferencedBySeveralSa( IProject suProject ) {
		return getReferencingSaProjects( suProject ).size() > 1;
	}


	/**
	 * Gets all the SU projects from the work space.
	 * @return a (non-null) list of SU projects
	 */
	public static List<IProject> getAllSuProjects() {
		return new PetalsProjectProxyManager().getProjects( SuProjectCategory.SU_CATEGORY_ID );
	}


	/**
	 * @param p a project
	 * @return true if the project is a SA project, false otherwise
	 */
	public static boolean isSaProject( IProject p ) {

		boolean result = false;
		if( p.getLocation() != null ) {
			File jbiXmlFile = p.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
			result = jbiXmlFile.exists() && JbiXmlUtils.describesServiceAssembly( jbiXmlFile );
		}

		return result;
	}


	/**
	 * @param p a project
	 * @return true if the project is a SU project, false otherwise
	 */
	public static boolean isSuProject( IProject p ) {

		boolean result = false;
		if( p.getLocation() != null ) {
			File jbiXmlFile = p.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
			result = jbiXmlFile.exists() && JbiXmlUtils.describesServiceUnit( jbiXmlFile );
		}

		return result;
	}
}
