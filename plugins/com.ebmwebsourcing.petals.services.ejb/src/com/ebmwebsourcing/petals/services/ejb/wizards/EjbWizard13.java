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

package com.ebmwebsourcing.petals.services.ejb.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.ejb.EjbDescription13;
import com.ebmwebsourcing.petals.services.ejb.PetalsEjbPlugin;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion;
import com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EjbWizard13 extends AbstractServiceUnitWizard {

	private String ejbClassName;
	private Collection<File> jeeLibs, ejbLibs;
	private String wsdlName;

	public EjbWizard13() {
		super();
		this.settings.showJbiPage = false;
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new EjbDescription13();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		Object ejbVersion = abstractEndpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__EJB_VERSION);
		if (ejbVersion != EjbVersion.V30 && ejbVersion != EjbVersion.V31) {
			abstractEndpoint.eSet(EjbPackage.Literals.EJB_PROVIDES__EJB_HOME_INTERFACE, null);
		}

		List<String> paths = new ArrayList<String> ();
		List<IFile> jars = ResourceUtils.getFilesByRegexp( resourceFolder, ".*\\.(jar|zip)" );
		for( IFile f : jars )
			paths.add( f.getLocation().toString());

		for( File f : this.jeeLibs)
			paths.add( f.getAbsolutePath());

		String[] classpath = new String[ paths.size()];
		classpath = paths.toArray( classpath );
		String outputPathAS = resourceFolder.getLocation().toString();

		File f = WsdlExtUtils.generateWsdlFile(
					this.wsdlName,
					outputPathAS,
					this.ejbClassName,
					classpath,
					outputPathAS,
					abstractEndpoint.getEndpointName(),
					abstractEndpoint.getServiceName().toString(),
					monitor);

		if( ! f.exists()) {
			String msg = "The generation of the WSDL from the EJB interface failed.";
			PetalsEjbPlugin.log( msg, IStatus.ERROR );
		}

		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		endpoint.eSet(EjbPackage.Literals.EJB_PROVIDES__MARSHALLING_ENGINE, XmlEngine.JAXB);
		endpoint.eSet(EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL, "rmi://server:1099");
		endpoint.eSet(EjbPackage.Literals.EJB_PROVIDES__EJB_VERSION, EjbVersion.V30);
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard
	 * #getCustomWizardPagesAfterProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] {
			new EJBDetailsPage()
		};
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard
	 * #getCustomWizardPagesBeforeProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return new AbstractSuWizardPage[] {
				new EJBCustomSpecificationPage12()
		};
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard
	 * #importAdditionalFiles(org.eclipse.core.resources.IFolder, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard#isJavaProject()
	 */
	@Override
	protected boolean isJavaProject() {
		return false;
	}

	/**
	 * @param files
	 */
	public void setEJBFiles( Collection<File> files ) {
		this.ejbLibs = files;
	}

	/**
	 * @param files
	 */
	public void setJEEFiles( Collection<File> files ) {
		this.jeeLibs = files;
	}

	/**
	 * @param trim
	 */
	public void setWsdlName(String trim) {
		this.wsdlName = trim;
	}
}
