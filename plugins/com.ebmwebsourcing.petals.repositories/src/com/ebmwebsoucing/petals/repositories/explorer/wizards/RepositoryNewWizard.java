/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsoucing.petals.repositories.explorer.wizards;

import org.eclipse.jface.wizard.Wizard;

import com.ebmwebsoucing.petals.repositories.explorer.RepositoryManager;
import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryNewWizard extends Wizard {

	private RepositoryNewWizardPage page;


	/**
	 * Constructor.
	 */
	public RepositoryNewWizard() {
		setWindowTitle( "New Service Repository" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new RepositoryNewWizardPage( "MainPage" );
		addPage( this.page );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		Repository repository = new Repository();
		repository.setName( this.page.getName());
		repository.setDescription( this.page.getDescription());
		repository.addAllQueryApiBean( this.page.getQueryApiBeans());

		RepositoryManager.getInstance().addRepository( repository );

		return true;
	}
}
