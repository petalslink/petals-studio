/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v44;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Victor Noel - Linagora
 */
public class SoapProvidesWizard44 extends AbstractServiceUnitWizard {

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SoapDescription44();
	}

	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);
		getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__WSA_TO, null);
		getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION, SoapVersion.SOAP_11);
		getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE, false);
		getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_PROVIDES__MODE, SoapMode.SOAP );
	}

	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SoapProvidesWizardPage44()};
	}

	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		newlyCreatedEndpoint.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, this.finalWsdlFileLocation );
		return Status.OK_STATUS;
	}
}
