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

import java.io.File;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.IConditionChecker;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.ltk.core.refactoring.participants.ValidateEditChecker;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceChange;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsRefactoringUtils;

/**
 * A refactoring processor for Petals Maven projects.
 * <p>
 * This processor supports participants (e.g. for the service
 * projects, where the service-name may have to be changed in the
 * jbi.xml and the WSDL).
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectRefactoringProcessor extends RefactoringProcessor {

	private MavenProjectRefactoringInfo info;
	private final MavenProjectRefactoringExtension[] extensions;


	/**
	 * Constructor.
	 * @param extensions
	 */
	public MavenProjectRefactoringProcessor( MavenProjectRefactoringExtension... extensions ) {
		if( extensions == null )
			this.extensions = new MavenProjectRefactoringExtension[ 0 ];
		else
			this.extensions = extensions;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor,
	 * org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public RefactoringStatus checkFinalConditions(
				IProgressMonitor pm,
				CheckConditionsContext context )
	throws CoreException, OperationCanceledException {

		RefactoringStatus status = new RefactoringStatus();
		pm.beginTask( "Scanning files...", 30 );
		try {

			// Make sure this renaming won't result in conflicts
			IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( this.info.getNewName());
			if( p.exists())
				status.addFatalError( "There is already a project called " + this.info.getNewName() + "." );
			else {
				File f = this.info.getProject().getLocation().toFile().getParentFile();
				if( new File( f, this.info.getNewName()).exists())
					status.addFatalError( "There is already a file with this name at this location." );
			}

			pm.worked( 20 );

			// Check the extensions
			for( MavenProjectRefactoringExtension extension : this.extensions ) {
				if( status.isOK())
					extension.checkFinalConditions( pm, status, context );
			}

			// Validate the impacted files (POM)
			if( status.isOK() && context != null ) {
				IConditionChecker checker = context.getChecker( ValidateEditChecker.class );
				ValidateEditChecker editChecker = (ValidateEditChecker) checker;
				editChecker.addFile( this.info.getProject().getFile( PetalsConstants.LOC_POM_FILE ));
			}

		} finally {
			pm.done();
		}

		return status;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions( IProgressMonitor pm )
	throws CoreException, OperationCanceledException {

		RefactoringStatus status = new RefactoringStatus();
		if( this.info.getProject() == null )
			status.addFatalError( "The project cannot be null." );
		else if( ! this.info.getProject().exists())
			status.addFatalError( this.info.getProject().getName() + " does not exist." );
		else if( ! this.info.getProject().isAccessible())
			status.addFatalError( "The project must be open." );
		else if( ! this.info.getProject().getFile( PetalsConstants.LOC_POM_FILE ).exists())
			status.addFatalError( "This project does not contain a Maven POM." );
		else {
			for( MavenProjectRefactoringExtension extension : this.extensions ) {
				extension.checkInitialConditions( pm, status );
				if( ! status.isOK())
					break;
			}
		}

		return status;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange( IProgressMonitor pm )
	throws CoreException, OperationCanceledException {

		CompositeChange compositeChange = new CompositeChange( "Refactoring of " + this.info.getProject().getName() );
		try {
			pm.beginTask( "Collecting changes...", 40 + this.extensions.length * 10 );

			// A file change contains a tree of edits, first add the root of them
			IFile pomFile = this.info.getProject().getFile( PetalsConstants.LOC_POM_FILE );
			TextFileChange textFileChange = PetalsRefactoringUtils.buildTextFileChange(
						pomFile,
						Pattern.quote( this.info.getProject().getName()),
						null,
						null,
						this.info.getNewName());

			if( textFileChange != null )
				compositeChange.add( textFileChange );
			pm.worked( 20 );

			// Add the partipant's changes
			for( MavenProjectRefactoringExtension extension : this.extensions ) {
				if( extension.isEnabled()) {
					Change participantChange = extension.createChange( pm );

					if( participantChange instanceof CompositeChange )
						compositeChange.merge((CompositeChange) participantChange );
					else if( participantChange != null )
						compositeChange.add( participantChange );

					pm.worked( 10 );
				}
			}

			// Update the project name
			RenameResourceChange resourceChange = new RenameResourceChange(
						this.info.getProject().getFullPath(),
						this.info.getNewName());

			compositeChange.add( resourceChange );
			pm.worked( 20 );

		} finally {
			pm.done();
		}

		return compositeChange;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #getElements()
	 */
	@Override
	public Object[] getElements() {
		return new Object[] { this.info.getProject()};
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return getClass().getName();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "Refactoring for Petals Maven projects";
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #isApplicable()
	 */
	@Override
	public boolean isApplicable() throws CoreException {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor
	 * #loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus,
	 * org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	@Override
	public RefactoringParticipant[] loadParticipants(
				RefactoringStatus status,
				SharableParticipants sharedParticipants )
	throws CoreException {

		return new RefactoringParticipant[ 0 ];
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo( MavenProjectRefactoringInfo info ) {
		this.info = info;

		for( MavenProjectRefactoringExtension extension : this.extensions )
			extension.setInfo( info );
	}
}
