/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.generation;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * A class to extend to perform additional actions after wizard completion.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class LastActionsPerformer {

	/**
	 * Perform additional actions after wizard completion.
	 * <p>
	 * That can be create or import new files in the created project.
	 * This project is given as an argument in the method.
	 * </p>
	 *
	 * @param resourceFolder the resource folder of the created project
	 * @param eclipseSuBean the bean with data to format and write
	 * @param resourcesToSelect a non-null list in which resources can be added for selection
	 * @param monitor the monitor.
	 */
	public abstract void performLastActions( IFolder resourceFolder, EclipseSuBean eclipseSuBean, List<Object> resourcesToSelect, IProgressMonitor monitor );


	/**
	 * Create the file and write its content.
	 *
	 * @param eclipseSuBean
	 * @param destination
	 * @param monitor
	 */
	protected void createFile( IFile destination, String content, IProgressMonitor monitor ) {
		try {
			if( content == null )
				content = "Result was null. Make your code correct.";

			ByteArrayInputStream inputStream = new ByteArrayInputStream( content.getBytes());
			if( ! destination.exists())
				destination.create( inputStream, true, monitor );
			else
				destination.setContents( inputStream, true, true, monitor );
			inputStream.close();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}
}
