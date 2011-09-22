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

package com.ebmwebsourcing.petals.services.su.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * Manage all the errors which can occur when using the Petals Eclipse plug-ins.
 * @author Vincent Zurczak - EBM WebSourcing
 * @deprecated
 */
@Deprecated
public class ErrorReporter {

	public static final ErrorReporter INSTANCE = new ErrorReporter();
	private final List<ErrorBean> errors = new ArrayList<ErrorBean> ();
	private final Comparator<ErrorBean> comparator = new Comparator<ErrorBean> () {
		public int compare( ErrorBean o1, ErrorBean o2 ) {

			int status1 = o1 != null ? o1.status : IStatus.INFO;
			int status2 = o2 != null ? o2.status : IStatus.INFO;
			if( status1 != status2 )
				return status1 - status2;

			String summary1 = o1 != null && o1.shortSummary != null ? o1.shortSummary : "";
			String summary2 = o2 != null && o2.shortSummary != null ? o2.shortSummary : "";
			return summary1.compareTo( summary2 );
		}
	};


	/**
	 * Private constructor.
	 */
	private ErrorReporter() {}



	/**
	 * @return true if errors were reported and not logged.
	 */
	public boolean errorsOccured() {
		return this.errors.size() > 0;
	}


	/**
	 * Log errors.
	 * <p>
	 * The registered errors are cleared after calling this method.
	 * </p>
	 */
	public void generateLog() {

		if( !errorsOccured())
			return;

		// Sort the elements and create a status for each one
		Collections.sort( this.errors, this.comparator );
		List<IStatus> statuses = new ArrayList<IStatus>( this.errors.size());
		for( ErrorBean error : this.errors ) {

			String msg = error.shortSummary != null ? (error.shortSummary + ": ") : "";
			String message = error.message;
			message = message == null && error.throwable != null ? error.throwable.getMessage() : message;
			message = message == null ? "" : message;
			if( message.length() == 1 )
				message = message.toUpperCase();
			else if( message.length() > 1 )
				message = ("" + message.charAt( 0 )).toUpperCase() + message.substring( 1 );

			msg += message;
			IStatus status = new Status( error.status, PetalsServicesPlugin.PLUGIN_ID, IStatus.OK, msg, error.throwable );
			statuses.add( status );
		}


		// Log the status
		if( statuses.size() == 1 )
			PetalsServicesPlugin.getDefault().getLog().log( statuses.get( 0 ));
		else {
			MultiStatus status = new MultiStatus(
						PetalsServicesPlugin.PLUGIN_ID,
						IStatus.OK,
						statuses.toArray( new IStatus[ statuses.size() ]),
						"Errors occurred while using the Petals Eclipse plug-ins.",
						null );
			PetalsServicesPlugin.getDefault().getLog().log( status );
		}


		// Open the log view
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.showView( "org.eclipse.pde.runtime.LogView" ); //$NON-NLS-1$

		} catch( PartInitException e1 ) {
			e1.printStackTrace();
		}

		clearErrors();
	}


	/**
	 * Register an error.
	 * 
	 * @param shortSummary
	 * @param message
	 * @param status
	 * @param throwable
	 */
	public void registerError( String shortSummary, String message, int status, Throwable throwable ) {
		this.errors.add( new ErrorBean( shortSummary, message, status, throwable  ));
	}


	/**
	 * Clear all the errors.
	 */
	private void clearErrors() {
		this.errors.clear();
	}


	/**
	 * 
	 */
	private static class ErrorBean {
		String shortSummary;
		String message;
		int status;
		Throwable throwable;

		/**
		 * @param shortSummary
		 * @param message
		 * @param status
		 * @param throwable
		 */
		public ErrorBean( String shortSummary, String message, int status, Throwable throwable ) {
			this.shortSummary = shortSummary;
			this.message = message;
			this.status = status;
			this.throwable = throwable;
		}
	}
}
