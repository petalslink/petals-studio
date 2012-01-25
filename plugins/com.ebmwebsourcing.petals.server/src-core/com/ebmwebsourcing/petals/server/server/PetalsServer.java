/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ServerDelegate;

import com.ebmwebsourcing.petals.server.PetalsServerPlugin;
import com.ebmwebsourcing.petals.server.handlers.IPetalsVersionHandler;
import com.ebmwebsourcing.petals.server.runtime.PetalsRuntime;
import com.ebmwebsourcing.petals.server.utils.NetworkUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServer
extends ServerDelegate
implements IPetalsServer, IPetalsServerWorkingCopy {

	private transient IPetalsVersionHandler versionHandler;
	private transient URL wsUrl;

	private final String HOST_ID = "petals-host";
	private final String WS_PREFIX = "petals-ws-prefix";
	private final String CONTAINER_NAME = "petals-container-name";
	private final String PORT = "petals-port";


	/**
	 * Get the Petals runtime for this server.
	 * @return Petals runtime for this server
	 */
	public PetalsRuntime getPetalsRuntime() {

		if( getServer().getRuntime() == null )
			return null;

		return (PetalsRuntime) getServer().getRuntime().loadAdapter( PetalsRuntime.class, null );
	}


	/**
	 * @return the class to call to launch the Petals server
	 */
	public String getPetalsRuntimeClass() {
		IPetalsVersionHandler handler = getPetalsVersionHandler();
		return handler.getRuntimeClass();
	}


	/**
	 * @return the server libraries
	 */
	public List<File> getPetalsServerLibraries() {

		IPetalsVersionHandler handler = getPetalsVersionHandler();
		IPath installPath = getPetalsRuntime().getRuntime().getLocation();
		List<File> files = handler.getRuntimeClasspath( installPath );
		return files;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerDelegate
	 * #canModifyModules(
	 * 		org.eclipse.wst.server.core.IModule[],
	 * 		org.eclipse.wst.server.core.IModule[])
	 */
	@Override
	public IStatus canModifyModules( IModule[] add, IModule[] remove ) {
		return Status.OK_STATUS;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerDelegate
	 * #getChildModules(org.eclipse.wst.server.core.IModule[])
	 */
	@Override
	public IModule[] getChildModules( IModule[] module ) {
		return new IModule[ 0 ];
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerDelegate
	 * #getRootModules(org.eclipse.wst.server.core.IModule)
	 */
	@Override
	public IModule[] getRootModules( IModule module ) throws CoreException {
		return new IModule[ 0 ];
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.ServerDelegate
	 * #modifyModules(
	 * 		org.eclipse.wst.server.core.IModule[],
	 * 		org.eclipse.wst.server.core.IModule[],
	 * 		org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void modifyModules( IModule[] add, IModule[] remove, IProgressMonitor monitor )
	throws CoreException {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.server.server.IPetalsServer
	 * #cleanServerInstallation(org.eclipse.core.runtime.IPath)
	 */
	public IStatus cleanServerInstallation() {

		IPetalsVersionHandler handler = getPetalsVersionHandler();
		IPath installPath = getPetalsRuntime().getRuntime().getLocation();
		IStatus	status = handler.cleanServerInstallation( installPath );
		return status;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.server.server.IPetalsServer
	 * #isServerInstallationDirty(org.eclipse.core.runtime.IPath)
	 */
	public boolean isServerInstallationDirty() {

		IPetalsVersionHandler handler = getPetalsVersionHandler();
		IPath installPath = getPetalsRuntime().getRuntime().getLocation();
		boolean dirty = handler.isServerInstallationDirty( installPath );
		return dirty;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.server.server.IPetalsServerWorkingCopy
	 * #isRunning()
	 */
	public boolean isRunning() {
		URI uri = UriAndUrlHelper.urlToUri( getWsUrl());
		return NetworkUtils.pingServer( uri, getPort(), 1, 0 );
	}


	/**
	 * @return the host
	 */
	public String getHost() {
		return getAttribute( this.HOST_ID, "" );
	}


	/**
	 * @param host the host to set
	 */
	public void setHost( String host ) {
		setAttribute( this.HOST_ID, host != null ? host : "" );
	}


	/**
	 * @return the wsPrefix
	 */
	public String getWsPrefix() {
		return getAttribute( this.WS_PREFIX, "" );
	}


	/**
	 * @param wsPrefix the wsPrefix to set
	 */
	public void setWsPrefix( String wsPrefix ) {
		setAttribute( this.WS_PREFIX, wsPrefix != null ? wsPrefix : "" );
	}


	/**
	 * @return the containerName
	 */
	public String getContainerName() {
		return getAttribute( this.CONTAINER_NAME, "" );
	}


	/**
	 * @param containerName the containerName to set
	 */
	public void setContainerName( String containerName ) {
		setAttribute( this.CONTAINER_NAME, containerName != null ? containerName : "" );
	}


	/**
	 * @return the port
	 */
	public int getPort() {
		return getAttribute( this.PORT, 7600 );
	}


	/**
	 * @param port the port to set
	 */
	public void setPort( int port ) {
		setAttribute( this.PORT, port );
	}


	/**
	 * Computes and returns the WS URL from the class fields.
	 * <p>
	 * If the protocol was specified with the host name, it is kept (assumed to be http or https).
	 * <br />Otherwise, http is used by default.
	 * </p>
	 *
	 * @return the WS URL, including the port, the WS prefix and the host.
	 */
	public URL getWsUrl() {

		if( this.wsUrl == null ) {
			StringBuilder sb = new StringBuilder();
			String host = getHost();
			if( host != null && ! host.startsWith( "http" ))
				sb.append( "http://" );

			sb.append( host );
			sb.append( ":" + getPort() + "/" + getWsPrefix());
			if( ! getWsPrefix().endsWith( "/" ))
				sb.append( "/" );

			try {
				this.wsUrl = new URL( sb.toString());

			} catch( MalformedURLException e ) {
				PetalsServerPlugin.log( e, IStatus.ERROR );
			}
		}

		return this.wsUrl;
	}


	/**
	 * @return the WS URL as a string (or null if the computed URL is invalid)
	 */
	public String getWsUrlAsString() {

		URL url = getWsUrl();
		return url == null ? null : url.toString();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.server.server.IPetalsServer
	 * #validateTopologyInformation()
	 */
	public String validateTopologyInformation() {

		String containerName = getContainerName();
		if( containerName == null || containerName.trim().length() == 0 )
			return "The container name cannot be empty.";

		String host = getHost();
		if( host == null || host.trim().length() == 0 )
			return "The host cannot be empty.";

		String wsPrefix = getWsPrefix();
		if( wsPrefix == null || wsPrefix.trim().length() == 0 )
			return "The web service prefix cannot be empty.";

		if( getPort() <= 0 )
			return "The port must be higher than 0.";

		return null;
	}


	/**
	 * Gets the Petals version handler for this server.
	 * @return version handler for this server
	 */
	private IPetalsVersionHandler getPetalsVersionHandler() {

		if( this.versionHandler == null ) {
			PetalsRuntime petalsRuntime = getPetalsRuntime();
			if( petalsRuntime != null)
				this.versionHandler = petalsRuntime.getVersionHandler();
		}

		return this.versionHandler;
	}
}
