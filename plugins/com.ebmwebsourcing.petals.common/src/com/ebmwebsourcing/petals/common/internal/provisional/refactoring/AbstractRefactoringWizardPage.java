/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.refactoring;

import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractRefactoringWizardPage extends UserInputWizardPage {

	private MavenProjectRefactoringInfo info;


	/**
	 * Constructor.
	 */
	public AbstractRefactoringWizardPage() {
		super( "User Input Page" );
	}


	/**
	 * @return the info
	 */
	public MavenProjectRefactoringInfo getInfo() {
		return this.info;
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo( MavenProjectRefactoringInfo info ) {
		this.info = info;
	}
}
