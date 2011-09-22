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

package com.ebmwebsourcing.petals.services.xslt.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
@Deprecated
public class PetalsXsltNature implements IProjectNature {

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = "com.ebmwebsourcing.petals.services.xslt.nature";

	private IProject project;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature
	 * #configure()
	 */
	public void configure() throws CoreException {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature
	 * #deconfigure()
	 */
	public void deconfigure() throws CoreException {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature
	 * #getProject()
	 */
	public IProject getProject() {
		return this.project;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature
	 * #setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
	}
}
