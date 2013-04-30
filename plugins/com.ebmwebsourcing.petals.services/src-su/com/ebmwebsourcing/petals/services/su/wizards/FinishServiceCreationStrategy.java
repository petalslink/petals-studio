/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Micka�l Istria - EBM WebSourcing
 */
public interface FinishServiceCreationStrategy {

	/**
	 * TODO: to complete.
	 * @param wizard
	 * @param endpoint
	 * @param monitor
	 * @throws Exception
	 */
	public void finishWizard(AbstractServiceUnitWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) throws Exception;

	/**
	 * TODO: to complete
	 * @param wizard
	 * @param monitor
	 * @return
	 */
	public IProject getSUProject(AbstractServiceUnitWizard wizard, IProgressMonitor monitor);
}
