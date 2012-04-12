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

package com.ebmwebsourcing.petals.services.su.ui;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.progress.WorkbenchJob;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;

/**
 * A job that parses WSDLs.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlParsingJobManager {

	private WsdlParsingJob parsingJob;
	private URI wsdlUri;
	private final List<JbiBasicBean> beans = new ArrayList<JbiBasicBean> ();
	private final List<WsdlParsingListener> listeners = new ArrayList<WsdlParsingListener> ();


	/**
	 * <p>
	 * Calling this method cancels the job schedule, and reschedule it
	 * in the next 2 seconds.
	 * </p>
	 * @param wsdlFile
	 */
	public void setWsdlFile( File wsdlFile ) {

		if( this.parsingJob != null )
			this.parsingJob.cancel();

		this.wsdlUri = wsdlFile.toURI();

		this.parsingJob = new WsdlParsingJob();
		this.parsingJob.schedule( 1000 );
	}


	/**
	 * Set the WSDL to parse.
	 * <p>
	 * Calling this method cancels the job schedule, and reschedule it
	 * in the next 2 seconds.
	 * </p>
	 * @param wsdlUri
	 */
	public void setWsdlUri( URI wsdlUri ) {

		if( this.parsingJob != null )
			this.parsingJob.cancel();

		this.wsdlUri = wsdlUri;

		this.parsingJob = new WsdlParsingJob();
		this.parsingJob.schedule( 1000 );
	}


	/**
	 * @return the wsdlUri
	 */
	public URI getWsdlUri() {
		return this.wsdlUri;
	}


	/**
	 * Cancels the scheduled job.
	 * @return true if the job could be canceled, false otherwise
	 */
	public boolean cancel() {

		boolean cancelWorked = true;
		if( this.parsingJob != null )
			cancelWorked = this.parsingJob.cancel();

		return cancelWorked;
	}


	/**
	 * @return the beans
	 */
	public List<JbiBasicBean> getBeans() {
		return this.beans;
	}


	/**
	 * An interface to implement to be notified when the WSDL parsing is done.
	 */
	public interface WsdlParsingListener {
		void notifyWsdlParsingDone();
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addWsdlParsingListener( WsdlParsingListener listener ) {
		return this.listeners.add( listener );
	}


	/**
	 * @param listener
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean removeWsdlParsingListener( WsdlParsingListener listener ) {
		return this.listeners.remove( listener );
	}


	/**
	 * A workbench job that parses WSDLs.
	 */
	private class WsdlParsingJob extends WorkbenchJob {

		/**
		 * Constructor.
		 */
		WsdlParsingJob() {
			super( "WSDL Parsing" );
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.progress.UIJob
		 * #runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public IStatus runInUIThread( IProgressMonitor monitor ) {

			// Parse the WSDL
			WsdlParsingJobManager.this.beans.clear();
			try {
				boolean parse = true;
				if( WsdlParsingJobManager.this.wsdlUri == null )
					parse = false;
				else if( ! WsdlParsingJobManager.this.wsdlUri.getScheme().equals( "file" ))
					parse = true;
				else if( ! new File( WsdlParsingJobManager.this.wsdlUri ).exists())
					parse = false;

				if( parse )
					WsdlParsingJobManager.this.beans.addAll( WsdlUtils.INSTANCE.parse( WsdlParsingJobManager.this.wsdlUri ));

			} catch( Exception e ) {
				// nothing
			}

			// Notify the listeners
			for( WsdlParsingListener listener : WsdlParsingJobManager.this.listeners )
				listener.notifyWsdlParsingDone();

			return Status.OK_STATUS;
		}
	}
}
