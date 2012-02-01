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

package com.ebmwebsourcing.petals.services.jms.wizards;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep;
import com.ebmwebsourcing.petals.services.jms.JmsDescription31;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JmsConsumesWizard31 extends AbstractServiceUnitWizard {

	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		endpoint.eSet(Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000);
		endpoint.eSet(Cdk5Package.Literals.CDK5_CONSUMES__MEP, Mep.IN_ONLY);
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				JmsPackage.Literals.JMS_EXTENSION__JNDI_PROVIDER_URL,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_DESTINATION_NAME,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_CONNECTION_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__USER,
				JmsPackage.Literals.JMS_EXTENSION__PASSWORD,
				JmsPackage.Literals.JMS_EXTENSION__TRANSACTED)
		};
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new JmsDescription31();
	}

}
