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
 
package com.ebmwebsourcing.petals.common.internal.refactoring;

import org.eclipse.ltk.core.refactoring.participants.ProcessorBasedRefactoring;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;


/**
 * A refactoring for Maven projects.
 * <p>
 * It updates the project name and the POM elements.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectRefactoring extends ProcessorBasedRefactoring {

	private final RefactoringProcessor processor;


	/**
	 * Constructor.
	 * @param processor
	 */
	public MavenProjectRefactoring( final RefactoringProcessor processor ) {
		super( processor );
		this.processor = processor;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.ProcessorBasedRefactoring
	 * #getProcessor()
	 */
	@Override
	public RefactoringProcessor getProcessor() {
		return this.processor;
	}
}
