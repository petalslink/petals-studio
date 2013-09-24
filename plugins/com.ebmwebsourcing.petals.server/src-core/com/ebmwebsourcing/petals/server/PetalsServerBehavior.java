/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.model.ServerBehaviourDelegate;
import org.ow2.petals.kernel.ws.api.PEtALSWebServiceException;
import org.ow2.petals.kernel.ws.client.PetalsClient;
import org.ow2.petals.kernel.ws.client.PetalsClientFactory;

import com.ebmwebsourcing.petals.server.runtime.PetalsRuntime;
import com.ebmwebsourcing.petals.server.server.PetalsServer;
import com.ebmwebsourcing.petals.server.ui.preferences.PetalsServerPreferencePage;

/**
 * The server behavior is in charge of maintaining the state of the server.
 * <p>
 * It handles, among other things, the stop and shutdown command of Petals
 * servers. Initially, it listens to the server by checking the system process and by
 * pinging the server at regular intervals.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServerBehavior extends ServerBehaviourDelegate {

	private IDebugEventSetListener processListener;
	private boolean keepOnPinging;
	private Thread pingThread;


	/**
	 * @return the Petals runtime
	 */
	public PetalsRuntime getPetalsRuntime() {

		PetalsRuntime petalsRuntime = null;
		if( getServer().getRuntime() != null )
			petalsRuntime = (PetalsRuntime) getServer().getRuntime().loadAdapter( PetalsRuntime.class, null );

		return petalsRuntime;
	}


	/**
	 * @return the Petals server
	 */
	public PetalsServer getPetalsServer() {
		PetalsServer server = (PetalsServer) getServer().loadAdapter( PetalsServer.class, null );
		return server;
	}


	/**
	 * @return the name of the class to use to start the server in a JVM
	 */
	public String getRuntimeClass() {
		return getPetalsServer().getPetalsRuntimeClass();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerBehaviourDelegate
	 * #initialize(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void initialize( IProgressMonitor monitor ) {
		super.initialize( monitor );

		// Get the server initial state
		boolean running = getPetalsServer().isRunning();
		if( running )
			setServerState( IServer.STATE_STARTED );
		else
			setServerState( IServer.STATE_STOPPED );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerBehaviourDelegate#dispose()
	 */
	@Override
	public void dispose() {
		stop( false, true );
		super.dispose();
	}


	/**
	 * Starts listening the system process and pinging the server.
	 * @param newProcess
	 */
	public void startListening( final IProcess newProcess ) {

		// Listen to the system process
		if( this.processListener != null || newProcess == null || this.pingThread != null )
			return;

		this.processListener = new IDebugEventSetListener() {
			@Override
			public void handleDebugEvents(DebugEvent[] events) {
				if( events != null ) {
					int size = events.length;
					for( int i=0; i<size; i++ ) {
						if( newProcess != null
									&& newProcess.equals( events[ i ].getSource())
									&& events[ i ].getKind() == DebugEvent.TERMINATE ) {

							// Atomic section
							synchronized( this ) {
								stopListening();
								setServerState( IServer.STATE_STOPPED );
							}
						}
					}
				}
			}
		};

		DebugPlugin.getDefault().addDebugEventListener( this.processListener );


		// Ping the server while it is starting
		final Thread startingThread = new Thread() {
			@Override
			public void run() {

				// Ping every second until timeout is over
				for( int i=0; i<getServer().getStartTimeout(); i++) {
					try {
						Thread.sleep( 1000 );

					} catch( InterruptedException e ) {
						// nothing
					}

					// Update the state
					boolean running = getPetalsServer().isRunning();

					// Atomic section
					synchronized( this ) {
						if( running ) {
							setServerState( IServer.STATE_STARTED );
							break;
						}
					}
				}

				// If the server is still not started, stop listening
				// No need to make this part atomic. The other thread is not started.
				if( ! getPetalsServer().isRunning()) {
					stopListening();
					setServerState( IServer.STATE_STOPPED );
				}
			};
		};

		startingThread.start();


		// Ping the server to check its state
		setKeepOnPinging( true );
		this.pingThread = new Thread() {
			@Override
			public void run() {

				// Wait the starting thread to finish
				try {
					startingThread.join();
				} catch( InterruptedException e1 ) {
					return;
				}

				// Ping every 10 seconds
				while( keepOnPinging()) {
					try {
						Thread.sleep( 10000);

					} catch( InterruptedException e ) {
						// nothing
					}

					// Update the state
					boolean running = getPetalsServer().isRunning();

					// Atomic section
					synchronized( this ) {
						if( keepOnPinging()) {
							if( running )
								setServerState( IServer.STATE_STARTED );
							else
								setServerState( IServer.STATE_STOPPED );
						}
					}
				}
			}
		};

		this.pingThread.setDaemon( true );
		this.pingThread.start();
	}


	/**
	 * Stops listening the system process and pinging the server.
	 */
	private void stopListening () {

		if( this.pingThread != null ) {
			setKeepOnPinging( false );
			this.pingThread = null;
		}

		if( this.processListener != null ) {
			DebugPlugin.getDefault().removeDebugEventListener( this.processListener );
			this.processListener = null;
		}
	}


	/**
	 * Stops the server.
	 * @param force true to stop the process if the normal stop command fails
	 * @see org.eclipse.wst.server.core.model.ServerBehaviourDelegate
	 * #stop(boolean)
	 */
	@Override
	public void stop( boolean force ) {
		stop( false, force );
	}


	/**
	 * Shutdowns the server.
	 * <p>
	 * Shutdown means all the deployed artifacts will be removed.
	 * </p>
	 *
	 * @param eraseAll true to erase all the deployed artifacts before stopping the container
	 * @param force true to stop the process if the normal shutdown command fails
	 */
	public void stop( boolean eraseAll, boolean force ) {

		int serverState = getServer().getServerState();
		if( serverState == IServer.STATE_STOPPED )
			return;

		// Atomic section
		synchronized( this ) {
			stopListening();
			setServerState( IServer.STATE_STOPPING );
		}

		// Stop with web services
		boolean normalShutdownFailed = false;
		String serverAddress = getPetalsServer().getWsUrlAsString();
		try {
			if( getPetalsServer().isRunning()) {
				PetalsClient client = PetalsClientFactory.getInstance().getClient( serverAddress );
				if( eraseAll )
					client.getRuntimeService().shutdownContainer();
				else
					client.getRuntimeService().stopContainer();
			}

		} catch( PEtALSWebServiceException e ) {
			normalShutdownFailed = true;
		}

		// Handle errors and forced stop
		if( normalShutdownFailed && force )
			terminateProcess();

		setServerState( IServer.STATE_STOPPED );
	}


	/**
	 * Terminates the launch process.
	 */
	private void terminateProcess () {

		try {
			ILaunch launch = getServer().getLaunch();
			if( launch != null )
				launch.terminate();

		} catch( DebugException e ) {
			PetalsServerPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Prepares the server to be launched, checking the runtime and cleaning the install directory.
	 *
	 * @param launch
	 * @param launchMode
	 * @param monitor
	 * @throws CoreException
	 */
	public void setupLaunch( ILaunch launch, String launchMode, IProgressMonitor monitor )
	throws CoreException {

		// Clean the server install directory
		if( getPetalsServer().isRunning())
			setServerState( IServer.STATE_STARTED );

		int serverState = getServer().getServerState();
		if( serverState == IServer.STATE_STARTED
					|| serverState == IServer.STATE_STARTING )
			return;

		if( getPetalsServer().isServerInstallationDirty()) {
			IStatus status = getPetalsServer().cleanServerInstallation();
			if( status != null && status.getSeverity() == IStatus.ERROR )
				throw new CoreException( status );
		}


		// Check the runtime (may have been edited)
		IStatus status = getPetalsRuntime().validate();
		if( status != null && status.getSeverity() == IStatus.ERROR )
			throw new CoreException( status );


		// Update the server state
		setServerRestartState( false );
		setServerState( IServer.STATE_STARTING );
		setMode( launchMode );
	}


	/**
	 * Adds the server libraries in the launch class path.
	 * @see org.eclipse.wst.server.core.model.ServerBehaviourDelegate
	 * #setupLaunchConfiguration(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void setupLaunchConfiguration(
				ILaunchConfigurationWorkingCopy workingCopy,
				IProgressMonitor monitor ) throws CoreException {

		// Add the server libraries in the class path
		List<String> classpath = new ArrayList<String>();
		for( Object o : workingCopy.getAttribute(
					IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
					Collections.emptyList())) {

			if( o instanceof String )
				classpath.add((String) o);
		}

		List<File> serverLibs = getPetalsServer().getPetalsServerLibraries();
		for( File serverLib : serverLibs ) {
			Path path = new Path( serverLib.getAbsolutePath());
			IRuntimeClasspathEntry entry = JavaRuntime.newArchiveRuntimeClasspathEntry( path );
			entry.setClasspathProperty( IRuntimeClasspathEntry.USER_CLASSES );
			classpath.add( entry.getMemento());
		}

		// Update and force the use of this class path in the launch configuration
		workingCopy.setAttribute( IJavaLaunchConfigurationConstants.ATTR_CLASSPATH, classpath );
		workingCopy.setAttribute( IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH, false );

		// Get the server arguments
		String args = null;
		final IPreferenceStore store = PetalsServerPlugin.getDefault().getPreferenceStore();
		if( store.contains( PetalsServerPreferencePage.START_IN_CONSOLE_MODE )) {
			String startMode = store.getString( PetalsServerPreferencePage.START_IN_CONSOLE_MODE );
			if( MessageDialogWithToggle.ALWAYS.equals( startMode ))
				args = "start -console";
			else if( MessageDialogWithToggle.NEVER.equals( startMode ))
				args = "start";
		}

		if( args == null ) {
			Display.getDefault().syncExec( new Runnable() {
				@Override
				public void run() {
					MessageDialogWithToggle.openYesNoQuestion(
								new Shell(), "Start Mode",
								"Do you want to start in console mode?",
								"Do not ask again", false, store,
								PetalsServerPreferencePage.START_IN_CONSOLE_MODE );
				};
			});

			String startMode = store.getString( PetalsServerPreferencePage.START_IN_CONSOLE_MODE );
			if( MessageDialogWithToggle.ALWAYS.equals( startMode ))
				args = "start -console";
			else
				args = "start";
		}

		workingCopy.setAttribute( IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, args );
	}


	/**
	 * Must be called when the server launch fails.
	 */
	public void signalLaunchFailed() {
		setServerState( IServer.STATE_STOPPED );
	}


	/**
	 * @return the keepOnPinging
	 */
	public synchronized boolean keepOnPinging() {
		return this.keepOnPinging;
	}


	/**
	 * @param keepOnPinging the keepOnPinging to set
	 */
	public synchronized void setKeepOnPinging( boolean keepOnPinging ) {
		this.keepOnPinging = keepOnPinging;
	}
}
