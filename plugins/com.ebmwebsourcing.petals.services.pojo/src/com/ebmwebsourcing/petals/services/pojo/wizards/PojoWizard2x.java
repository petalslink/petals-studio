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

import java.util.Collection;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.services.pojo.PetalsPojoPlugin;
import com.ebmwebsourcing.petals.services.pojo.PojoDescription22;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PojoWizard2x extends ComponentCreationWizard {

	private boolean useExistingImplementation = false;
	
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
		IStatus result = Status.OK_STATUS;
		boolean createJavaProject = true; // (Boolean) eclipseSuBean.customObjects.get( "create-java-project" );
		if( createJavaProject ) {
			try {
				JavaUtils.createJavaProject( resourceFolder.getProject());

			} catch( CoreException e ) {
				PetalsPojoPlugin.log( e, IStatus.ERROR );
			}
		}

		return result;
	}


	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuPage[] {
			new PojoProvideSpecificPage2x()
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
