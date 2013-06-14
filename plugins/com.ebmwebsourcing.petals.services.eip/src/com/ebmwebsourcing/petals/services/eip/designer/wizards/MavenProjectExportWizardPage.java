/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
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
