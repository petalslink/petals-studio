/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sa.refactoring;

import com.ebmwebsourcing.petals.common.internal.provisional.commands.AbstractProjectRefactorHandler;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.AbstractRefactoringWizardPage;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringProcessor;

/**
 * A handler to refactor SA projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaProjectRefactorHandler extends AbstractProjectRefactorHandler {

	private AbstractRefactoringWizardPage userPage;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.commands.AbstractProjectRefactorHandler
	 * #getRefactoringProcessor()
	 */
	@Override
	public MavenProjectRefactoringProcessor getRefactoringProcessor() {

		RefactoringExtensionForSa ext = new RefactoringExtensionForSa();
		MavenProjectRefactoringProcessor processor =
			new MavenProjectRefactoringProcessor( ext );

		return processor;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.commands.AbstractProjectRefactorHandler
	 * #getUserInputPage()
	 */
	@Override
	public AbstractRefactoringWizardPage getUserInputPage() {

		if( this.userPage == null )
			this.userPage = new SaRefactorUserPage();

		return this.userPage;
	}
}
