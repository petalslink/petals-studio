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
package com.ebmwebsourcing.petals.common.internal.refactoring;

import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.AbstractRefactoringWizardPage;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringInfo;

/**
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectRefactoringWizard extends RefactoringWizard {

	private final AbstractRefactoringWizardPage userInputPage;

	/**
	 * Constructor.
	 * <p>
	 * Both the processor and the user input page must have been
	 * initialized first. They must have been passed the model object
	 * used to store refactoring information. For Petals projects,
	 * this class is {@link MavenProjectRefactoringInfo}.
	 * </p>
	 * 
	 * @param refactoring
	 * @param userInputPage
	 */
	public MavenProjectRefactoringWizard(
				Refactoring refactoring,
				AbstractRefactoringWizardPage userInputPage ) {

		super( refactoring, DIALOG_BASED_USER_INTERFACE );
		this.userInputPage = userInputPage;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ltk.ui.refactoring.RefactoringWizard
	 * #addUserInputPages()
	 */
	@Override
	protected void addUserInputPages() {
		setDefaultPageTitle( getRefactoring().getName());
		addPage( this.userInputPage );
	}
}
