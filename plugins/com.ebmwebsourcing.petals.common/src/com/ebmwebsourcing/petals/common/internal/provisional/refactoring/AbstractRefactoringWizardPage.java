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
