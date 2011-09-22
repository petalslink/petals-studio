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
package com.ebmwebsourcing.petals.services.su.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceChange;

import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringExtension;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.PetalsRefactoringBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsRefactoringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * A refactoring handler for SU projects.
 * <p>
 * There are two aspects:
 * </p>
 * <ul>
 * 		<li>The project is renamed. It impacts the SU's POM, and SA projects (POM + jbi.xml).</li>
 * 		<li>
 * 			The project must respect the SU naming convention, and contain a service name.
 * 			This second aspect may impact several kinds of files, depending on the options.
 * 			<ul>
 * 				<li>The jbi.xml and the WSDL files are impacted by default (just the service name).</li>
 * 				<li>If derived names must be updated, then any text containing the service name will be refactored.</li>
 * 				<li>If the references must be updated, then this replacement is extended to all the files in the "src" directory.</li>
 * 			</ul>
 * 		</li>
 * </ul>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RefactoringExtensionForSu extends MavenProjectRefactoringExtension {

	public final static String UPDATE_SERVICE_NAME = "update_the service_name";
	public final static String UPDATE_DERIVED_NAMES = "update_the _derived_names";


	/**
	 * Creates the changes in a SU project.
	 * <p>
	 * The following policy is applied:
	 * </p>
	 * <ul>
	 * 		<li>The project name is updated in all the files of the "src" directory.</li>
	 * 		<li>If the service name must be updated, it us updated in all the files of the "src" directory.</li>
	 * 		<li>If derived names must be updated, then all the files are introspected.</li>
	 * 		<li>Note that the last options are exclusive: it is either one, or the other.</li>
	 * </ul>
	 * 
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringExtension
	 * #createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange( IProgressMonitor pm )
	throws CoreException, OperationCanceledException {

		// FIXME: do we need to use the progress monitor? See with important files (e.g. BPEL projects)
		// Get the information to make decisions
		CompositeChange compositeChange = new CompositeChange( "Intermediary" );
		List<Change> resourceChangesList = new ArrayList<Change> ();

		Boolean updateserviceName = getInfo().getOptions().get( UPDATE_SERVICE_NAME );
		Boolean updateDerivations = getInfo().getOptions().get( UPDATE_DERIVED_NAMES );


		// Store reusable values
		String oldServiceName = null;
		String newServiceName = null;
		String quotedOldProjectName = Pattern.quote( getInfo().getProject().getName());


		// Build the replacements
		List<PetalsRefactoringBean> beans = new ArrayList<PetalsRefactoringBean> ();

		PetalsRefactoringBean projectBean = new PetalsRefactoringBean();
		projectBean.setLeftRegex( null );
		projectBean.setRightRegex( null );
		projectBean.setRegex( quotedOldProjectName );
		projectBean.setNewValue( getInfo().getNewName());
		beans.add( projectBean );

		if( updateserviceName ) {
			oldServiceName = getServiceName( getInfo().getProject().getName());
			newServiceName = getServiceName( getInfo().getNewName());
			if( ! oldServiceName.equals( newServiceName )) {

				PetalsRefactoringBean serviceBean = new PetalsRefactoringBean();
				serviceBean.setLeftRegex( updateDerivations ? null : "\\W" );
				serviceBean.setRightRegex( updateDerivations ? null : "\\W" );
				serviceBean.setRegex( Pattern.quote( oldServiceName ));
				serviceBean.setNewValue( newServiceName );

				beans.add( serviceBean );
			}
		}

		// For all the files in the "src" directory...
		IContainer container = getInfo().getProject().getFolder( "src" );
		for( IFile file : ResourceUtils.getFiles( "*", Arrays.asList( container ))) {

			// Text file changes
			TextFileChange textFileChange = PetalsRefactoringUtils.buildTextFileChange( file, beans );
			if( textFileChange != null )
				compositeChange.add( textFileChange );

			// Update a file name too?
			// => project name
			RenameResourceChange resourceChange = renameResource( file, quotedOldProjectName, getInfo().getNewName());
			if( resourceChange != null )
				resourceChangesList.add( resourceChange );

			// => Derivation
			if( updateDerivations ) {
				resourceChange = renameResource( file, oldServiceName, newServiceName );
				if( resourceChange != null )
					resourceChangesList.add( resourceChange );
			}
		}


		// SA appear at the end of the changes list.
		// SA projects are only impacted by the project name
		for( IProject project : getInfo().getProject().getReferencingProjects()) {
			if( project.isAccessible()
						&& ServiceProjectRelationUtils.isSaProject( project )) {

				// Update the jbi.xml and the POM
				IFile[] files = new IFile[] {
							project.getFile( PetalsConstants.LOC_JBI_FILE ),
							project.getFile( PetalsConstants.LOC_POM_FILE )
				};

				// Update them
				for( IFile file : files ) {
					if( ! file.exists())
						continue;

					TextFileChange textFileChange = PetalsRefactoringUtils.buildTextFileChange(
								file,
								Pattern.quote( getInfo().getProject().getName()),
								null,
								null,
								getInfo().getNewName());

					if( textFileChange != null )
						compositeChange.add( textFileChange );
				}
			}
		}


		// Add the resource changes at the end (easier to read)
		for( Change change : resourceChangesList )
			compositeChange.add( change );

		return compositeChange;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringExtension
	 * #determineEnablement()
	 */
	@Override
	public void determineEnablement() {
		boolean enabled = ServiceProjectRelationUtils.isSuProject( getInfo().getProject());
		setEnabled( enabled );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringExtension
	 * #checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor,
	 * org.eclipse.ltk.core.refactoring.RefactoringStatus,
	 * org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public void checkFinalConditions(
				IProgressMonitor pm,
				RefactoringStatus status,
				CheckConditionsContext context )
	throws CoreException, OperationCanceledException {

		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringExtension
	 * #checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor,
	 * org.eclipse.ltk.core.refactoring.RefactoringStatus)
	 */
	@Override
	public void checkInitialConditions( IProgressMonitor pm, RefactoringStatus status )
	throws CoreException, OperationCanceledException {

		// nothing
	}


	/**
	 * Gets the service name from a SU name.
	 * @param suName
	 * @return
	 */
	private String getServiceName( String suName ) {

		String serviceName = suName;
		int index = serviceName.lastIndexOf( '-' );
		if( index > 0 )
			serviceName = serviceName.substring( 0, index );

		index = serviceName.lastIndexOf( '-' );
		if( index > 0 && index != serviceName.length())
			serviceName = serviceName.substring( index + 1 );

		return serviceName;
	}


	/**
	 * Checks whether a resource should be renamed.
	 * <p>
	 * This method checks whether changing the service name impacts the file name.
	 * If so, a resource change is returned (null otherwise).
	 * </p>
	 * 
	 * @param file the file that may have to be renamed
	 * @param oldServiceName the old service name (quoted)
	 * @param newServiceName the new service name
	 * @return a rename resource change if the file must be renamed, null otherwise
	 */
	private RenameResourceChange renameResource(
				IFile file,
				String oldServiceName,
				String newServiceName ) {

		RenameResourceChange resourceChange = null;

		// Replace the resource with the new project name
		if( oldServiceName != null && newServiceName != null ) {
			String newName = file.getName().replaceAll( oldServiceName, newServiceName );
			if( ! newName.equals( file.getName())) {
				resourceChange = new RenameResourceChange(
							file.getFullPath(),
							newName );
			}
		}

		return resourceChange;
	}
}
