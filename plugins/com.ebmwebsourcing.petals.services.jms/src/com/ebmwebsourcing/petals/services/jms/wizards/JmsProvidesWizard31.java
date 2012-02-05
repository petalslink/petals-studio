/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
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

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.jms.JmsDescription31;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JmsProvidesWizard31 extends AbstractServiceUnitWizard {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);

		endpoint.eSet( JmsPackage.Literals.JMS_EXTENSION__JNDI_PROVIDER_URL, "" );
		endpoint.eSet( JmsPackage.Literals.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY, "" );
		endpoint.eSet( JmsPackage.Literals.JMS_EXTENSION__JNDI_DESTINATION_NAME, "" );
		endpoint.eSet( JmsPackage.Literals.JMS_EXTENSION__JNDI_CONNECTION_FACTORY, "" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				JmsPackage.Literals.JMS_EXTENSION__JNDI_PROVIDER_URL,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_DESTINATION_NAME,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_CONNECTION_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__USER,
				JmsPackage.Literals.JMS_EXTENSION__PASSWORD,
				JmsPackage.Literals.JMS_EXTENSION__TRANSACTED,
				JmsPackage.Literals.JMS_PROVIDES__MAX_ACTIVE,
				JmsPackage.Literals.JMS_PROVIDES__MAX_IDLE,
				JmsPackage.Literals.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES,
				JmsPackage.Literals.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS,
				JmsPackage.Literals.JMS_PROVIDES__TEST_WHILE_IDLE)
		};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		newlyCreatedEndpoint.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, this.finalWsdlFileLocation );
		return Status.OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new JmsDescription31();
	}
}
