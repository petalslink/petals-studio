/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
