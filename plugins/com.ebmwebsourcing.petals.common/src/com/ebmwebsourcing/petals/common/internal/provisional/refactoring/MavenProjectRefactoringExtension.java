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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class MavenProjectRefactoringExtension {

	private boolean enabled;
	private MavenProjectRefactoringInfo info;


	/**
	 * 
	 * @param pm
	 * @return
	 * @throws CoreException
	 * @throws OperationCanceledException
	 */
	public abstract Change createChange( IProgressMonitor pm )
	throws CoreException, OperationCanceledException;


	/**
	 * 
	 * @param pm
	 * @param status
	 * @throws CoreException
	 * @throws OperationCanceledException
	 */
	public abstract void checkInitialConditions( IProgressMonitor pm, RefactoringStatus status )
	throws CoreException, OperationCanceledException;


	/**
	 * 
	 * @param pm
	 * @param status
	 * @param context
	 * @throws CoreException
	 * @throws OperationCanceledException
	 */
	public abstract void checkFinalConditions(
				IProgressMonitor pm,
				RefactoringStatus status,
				CheckConditionsContext context )
	throws CoreException, OperationCanceledException;


	/**
	 * 
	 */
	public abstract void determineEnablement();


	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled( boolean enabled ) {
		this.enabled = enabled;
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
		determineEnablement();
	}
}
