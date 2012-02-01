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
