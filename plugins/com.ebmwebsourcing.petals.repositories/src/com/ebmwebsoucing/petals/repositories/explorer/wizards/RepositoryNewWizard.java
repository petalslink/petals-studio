/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
