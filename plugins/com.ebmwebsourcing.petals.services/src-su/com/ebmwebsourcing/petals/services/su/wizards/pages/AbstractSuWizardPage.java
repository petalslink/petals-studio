/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * The abstract page used in the wizards of this plug-in.
 * <p>
 * Every sub-class instantiated from the extension-point should extend this class
 * or one of its subclasses, and define an empty constructor.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractSuWizardPage extends WizardPage {

	/**
	 * Count the number of these pages and ensure they never have the same name.
	 */
	private static int cpt = 0;



	/**
	 * Constructor defining the page name.
	 */
	public AbstractSuWizardPage() {
		super( "AbstractSuPage_" + cpt++ );
	}


	/**
	 * Constructor defining the page name.
	 * @param pageName
	 */
	public AbstractSuWizardPage( String pageName ) {
		super( pageName );
	}


	/**
	 * Registers a help ID and associates it with a wizard.
	 * @param container the container
	 * @param helpId the context help ID
	 */
	protected void setHelpContextId( Composite container, String helpId ) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp( container, helpId );
	}

	/**
	 * Validates the page's input.
	 * <p>
	 * This call must be explicit, it is not automatic.
	 * </p>
	 * @return true if the page is valid, false otherwise
	 */
	public abstract boolean validate();


	/**
	 * Display the error message provided as parameter and prevent the user from going further in the wizard.
	 * @param message the error message to display, or null to display nothing.
	 */
	protected final void updateStatus( String message ) {
		setErrorMessage( message );
		setPageComplete( message == null );
	}

	/**
	 * @return the first provides or consumes block (not null)
	 */
	protected AbstractEndpoint getNewlyCreatedEndpoint() {
		return ((AbstractServiceUnitWizard) getWizard()).getNewlyCreatedEndpoint();
	}

	
	@Override
	public AbstractServiceUnitWizard getWizard() {
		return (AbstractServiceUnitWizard) super.getWizard();
	}

}
