/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public interface FinishServiceCreationStrategy {

	/**
	 * TODO: to complete.
	 * @param wizard
	 * @param endpoint
	 * @param monitor
	 * @throws Exception
	 */
	public void finishWizard( AbstractServiceUnitWizard wizard, IProgressMonitor monitor ) throws Exception;

	/**
	 * TODO: to complete
	 * @param wizard
	 * @param monitor
	 * @return
	 */
	public IProject getSUProject( AbstractServiceUnitWizard wizard, IProgressMonitor monitor );
}
