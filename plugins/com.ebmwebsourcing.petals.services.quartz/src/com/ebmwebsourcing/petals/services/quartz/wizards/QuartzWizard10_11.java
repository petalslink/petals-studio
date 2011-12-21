/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.quartz.wizards;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.quartz.QuartzDescription10_11;
import com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QuartzWizard10_11 extends ComponentCreationWizard {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new QuartzDescription10_11();
	}

	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		endpoint.eSet(QuartzPackage.Literals.QUARTZ_CONSUMES__CRON_EXPRESSION, "* */5 * * *");
		endpoint.eSet(QuartzPackage.Literals.QUARTZ_CONSUMES__CONTENT, "<!-- Put the XML message to send here -->");
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuPage[] {
			new QuartzComponentSpecificPage10()
		};
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterProject() {
		return null;
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesBeforeProject() {
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected IStatus performLastActions(IFolder resourceDirectory,	AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}
}
