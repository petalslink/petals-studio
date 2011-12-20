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

package com.ebmwebsourcing.petals.services.sql.wizards;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.sql.SqlDescription11;
import com.ebmwebsourcing.petals.services.sql.generated.SqlService;
import com.ebmwebsourcing.petals.services.sql.sql.SqlPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SqlProvidesWizard11 extends ComponentCreationWizard {

	public SqlProvidesWizard11() {
		getDialogSettings().put( SuWizardSettings.WSDL_SHOW, "false" );
		getDialogSettings().put( SuWizardSettings.ITF_NAME_ACTIVATE, "false" );
	}
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SqlDescription11();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/sql/version-1", "SqlInterface" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/sql/version-1", "change-it" ));
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__MAX_ACTIVE, 8);
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__MAX_IDLE, 8);
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__MIN_IDLE, 0);
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__MAX_WAIT, -1);
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS, -1);
		ae.eSet(SqlPackage.Literals.SQL_PROVIDES__METADATA, false);
	}


	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuPage[] {
				new SqlProvides11Page()
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
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceDirectory.getFile( "SqlService.wsdl" );
		createFile( wsdlFile, new SqlService().generate( newlyCreatedEndpoint ), monitor );
		return Status.OK_STATUS;
	}


	@Override
	protected boolean isJavaProject() {
		return false;
	}
}
