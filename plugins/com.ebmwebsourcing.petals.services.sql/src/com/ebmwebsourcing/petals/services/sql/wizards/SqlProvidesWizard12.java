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
 
package com.ebmwebsourcing.petals.services.sql.wizards;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.sql.SqlDescription12;
import com.ebmwebsourcing.petals.services.sql.generated.SqlService12;
import com.ebmwebsourcing.petals.services.sql.sql.SqlPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SqlProvidesWizard12 extends AbstractServiceUnitWizard {

	/**
	 * Constructor.
	 */
	public SqlProvidesWizard12() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SqlDescription12();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/sql/version-1", "SqlInterface" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/sql/version-1", "change-it" ));
		Cdk5Utils.setInitialProvidesValues((Provides) ae);

		ae.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "SqlService.wsdl" );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__URL, "" );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__USER, "" );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__PASSWORD, "" );

		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__MAX_ACTIVE, 8 );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__MAX_IDLE, 8 );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__MIN_IDLE, 0 );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__MAX_WAIT, -1 );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS, -1 );
		ae.eSet( SqlPackage.Literals.SQL_PROVIDES__METADATA, false );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] {
				new SimpleFeatureListSuWizardPage(
						SqlPackage.Literals.SQL_PROVIDES__URL,
						SqlPackage.Literals.SQL_PROVIDES__USER,
						SqlPackage.Literals.SQL_PROVIDES__PASSWORD,
						SqlPackage.Literals.SQL_PROVIDES__DRIVER,
						SqlPackage.Literals.SQL_PROVIDES__MAX_ACTIVE,
						SqlPackage.Literals.SQL_PROVIDES__MAX_IDLE,
						SqlPackage.Literals.SQL_PROVIDES__MIN_IDLE,
						SqlPackage.Literals.SQL_PROVIDES__METADATA,
						SqlPackage.Literals.SQL_PROVIDES__STORED_PROCEDURE)
		};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceDirectory.getFile( "SqlService.wsdl" );
		createFile( wsdlFile, new SqlService12().generate( newlyCreatedEndpoint ), monitor );
		return Status.OK_STATUS;
	}
}
