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

package com.ebmwebsourcing.petals.services.pojo.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.pojo.PetalsPojoPlugin;
import com.ebmwebsourcing.petals.services.pojo.PojoDescription22;
import com.ebmwebsourcing.petals.services.pojo.pojo.PojoPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PojoWizard2x extends ComponentCreationWizard {

	private boolean useExistingImplementation = false;
	private PojoProvideSpecificPage2x pojoProvideSpecificPage2x;
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new PojoDescription22();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		// TODO maybe?
		// -> import JARS
		// -> Generate skeletton of class
		// FIXME
		abstractEndpoint.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, jbiProvidePage.getWsdlFileName());
		IStatus result = Status.OK_STATUS;
		if( !useExistingImplementation ) {
			try {
				JavaUtils.createJavaProject( resourceFolder.getProject());
				IFolder srcFolder = resourceFolder.getProject().getFolder(PetalsConstants.LOC_SRC_FOLDER);
				IFolder targetFolder = srcFolder.getFolder("com/ebmwebsourcing/formation");
				if (!targetFolder.exists()) {
					targetFolder.getLocation().toFile().mkdirs();
					srcFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				}
				IFile file = targetFolder.getFile("MyFirstPojo.java");
				if (!file.exists()) {
					file.create(getClass().getResourceAsStream("MyFirstPojo.java_"), true, monitor);
				}
				abstractEndpoint.eSet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME, "com.ebmwebsourcing.formation.MyFirstPojo");
			} catch( CoreException e ) {
				PetalsPojoPlugin.log( e, IStatus.ERROR );
			}
		} else {
			for (String jarPath : pojoProvideSpecificPage2x.getJarPath()) {
				try {
					java.io.File jarFile = new java.io.File(jarPath);
					IoUtils.copyFile(jarFile, new java.io.File(resourceFolder.getLocation().toFile(), jarFile.getName()), false);
				} catch (Exception ex) {
					PetalsPojoPlugin.log(ex, IStatus.ERROR);
				}
			}
			
		}

		return result;
	}


	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);
	}


	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		pojoProvideSpecificPage2x = new PojoProvideSpecificPage2x();
		return new AbstractSuWizardPage[] {
			pojoProvideSpecificPage2x
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
	protected boolean isJavaProject() {
		return !useExistingImplementation;
	}


	public boolean isUseExistingImplementation() {
		return useExistingImplementation;
	}


	public void setUseExistingImplementation(boolean useExistingImplementation) {
		this.useExistingImplementation = useExistingImplementation;
	}
}
