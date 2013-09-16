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
 
package com.ebmwebsourcing.petals.services.eip.designer.wizards;

import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage;

/**
 * A page to export an EIP chain as a set of Petals MAven projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectExportWizardPage extends AbstractPetalsServiceCreationWizardPage {

	/**
	 * Constructor.
	 */
	public MavenProjectExportWizardPage() {
		super( "EIP Chain Export", "Export an EIP chain as a set of Petals Maven projects." );
	}


	/* (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #releaseResources()
	 */
	@Override
	protected void releaseResources() {
		// nothing
	}


	/* (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #createWidgetsBeforeProjectLocation(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgetsBeforeProjectLocation( Composite container ) {
		// nothing
	}
}
