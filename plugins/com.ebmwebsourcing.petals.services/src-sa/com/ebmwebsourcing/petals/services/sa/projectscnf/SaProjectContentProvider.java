/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.projectscnf;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.AbstractTreeViewer;

import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectContentProvider;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.projectscnf.SuProjectCategory;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaProjectContentProvider extends PetalsProjectContentProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.model.BaseWorkbenchContentProvider
	 * #getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren( Object element ) {

		if( element instanceof IProject ) {
			IProject p = (IProject) element;
			if( ! p.isOpen())
				return new Object[ 0 ];

			try {
				// SA project: show the pom.xml, the jbi.xml and the SU projects
				if( ServiceProjectRelationUtils.isSaProject( p )) {

					List<Object> result = new ArrayList<Object>();
					IResource[] members = p.members();
					for( IResource member : members ) {
						if( ! member.isAccessible())
							continue;

						if( member instanceof IFile )
							result.add( member );
						else if( member instanceof IContainer && "target".equals( member.getName()))
							result.add( member );
					}

					try {
						IFile f = JbiXmlUtils.getJbiXmlFile( p );
						result.add( f );

					} catch( FileNotFoundException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );
					}

					for( IProject refP : p.getReferencedProjects()) {
						if( ! refP.isAccessible())
							continue;

						IPath loc = refP.getLocation();
						if( loc == null ) {
							PetalsServicesPlugin.log( "The location of the project " + refP.getName() + " could not be retrieved.", IStatus.ERROR );
							continue;
						}

						if( ServiceProjectRelationUtils.isSuProject( refP ))
							result.add( refP );
					}

					return result.toArray();
				}

				// SU project: show everything
				else {
					return p.members();
				}

			} catch( CoreException e ) {
				PetalsServicesPlugin.log( e, IStatus.WARNING );
			}

			return new Object[ 0 ];
		}

		return super.getChildren( element );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.model.BaseWorkbenchContentProvider
	 * #getParent(java.lang.Object)
	 */
	@Override
	public Object getParent( Object element ) {

		if( element instanceof IFile ) {
			IResource res = (IResource) element;
			if( "jbi.xml".equals( res.getName())
						|| "pom.xml".equals( res.getName()))
				return res.getProject();
		}

		return super.getParent( element );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectContentProvider
	 * #resourceChanged(org.eclipse.core.resources.IResourceDelta)
	 */
	@Override
	public void resourceChanged( IResourceDelta delta ) {

		final IResource resource = delta.getResource();
		if( resource instanceof IProject ) {

			// Change in an association SU-SA <=> Change in a SA Project's description
			if((delta.getFlags() & IResourceDelta.DESCRIPTION) != 0
						&& ServiceProjectRelationUtils.isSaProject((IProject) resource)) {

				PetalsProjectCategory cat = getProjectCategoryById( SuProjectCategory.SU_CATEGORY_ID );
				if( cat == null )
					PetalsServicesPlugin.log( "The SU project category could not be found.", IStatus.ERROR );
				else
					this.runnables.add( getRefreshRunnable( cat ));

				this.runnables.add( getRefreshRunnable( resource ));
			}
		}

		super.resourceChanged( delta );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectContentProvider
	 * #resourcesRemoved(java.util.Collection)
	 */
	@Override
	public void resourcesRemoved( Collection<IResource> resources ) {

		if( !( this.viewer instanceof AbstractTreeViewer ))
			return;

		// Deletion of SU projects => if there are associated SA, refresh them
		for( final IResource res : resources ) {

			// Handle projects specifically
			if( res instanceof IProject ) {
				List<IProject> saProjects = ServiceProjectRelationUtils.getReferencingSaProjects((IProject) res);
				for( IProject saProject : saProjects )
					this.runnables.add( getRefreshRunnable( saProject ));
			}
		}

		// And always apply the basic treatment
		super.resourcesRemoved( resources );
	}
}
