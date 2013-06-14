/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NetworkUtils {

	/**
	 * Pings a server by using {@link InetAddress#isReachable(int)}.
	 * <p>
	 * The full URI is not used. Only the server and the port are pinged.<br />
	 * The ping is made at most <i>retry</i> times, every <i>timeout</i>
	 * milliseconds, until the ping is successful or the loop end is reached.
	 * </p>
	 *
	 * @param uri the server URI
	 * @param port the port to ping
	 * @param retry the number of tries
	 * @param timeout the time (in milliseconds) between each tentative (can be zero)
	 * @return true if the ping succeeded, false otherwise
	 */
	public static boolean pingServer( URI uri, int port, int retry, int timeout ) {

		boolean success = false;
		try {
			Socket socket = new Socket( uri.getHost(), port );
			InetAddress inetAddress = socket.getInetAddress();

			for( int i=0; i<retry && ! success; i++ ) {
				success = inetAddress != null && inetAddress.isReachable( 3000 );

				try {
					if( timeout > 0 )
						Thread.sleep( timeout );

				} catch( InterruptedException e ) {
					e.printStackTrace();
				}
			}

		} catch( UnknownHostException e ) {
			// TODO: nothing or log?

		} catch( IOException e ) {
			// TODO: nothing or log?
		}

		return success;
	}


	/**
	 * Pings an URL by using {@link HttpURLConnection#getResponseCode()}.
	 * <p>
	 * Opens a connection for this URL.<br />
	 * If the connection is an HTTP one, the response code must be 200.<br />
	 * This connection is built at most <i>retry</i> times, every <i>timeout</i>
	 * milliseconds, until the ping is successful.
	 * </p>
	 *
	 * @param url the URL to ping
	 * @param retry the number of tries
	 * @param timeout the time (in milliseconds) between each tentative (can be zero)
	 * @return true if the ping succeeded, false otherwise
	 */
	public static boolean pingHttp( URL url, int retry, int timeout ) {

		boolean success = false;
		for( int i=0; i<retry && ! success; i++ ) {
			try {
				URLConnection conn = url.openConnection();
				if( conn instanceof HttpURLConnection ) {
					int responseCode = ((HttpURLConnection)conn).getResponseCode();
					success = 200 == responseCode;
				}
				else {
					success = true;
				}

			}	catch( IOException e ) {
				success = false;
			}

			try {
				if( timeout > 0 )
					Thread.sleep( timeout );

			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		}

		return success;
	}


	/**
	 * Gets the IP address associated with this host, with no scheme.
	 * <p>
	 * If the IP address cannot be resolved, the parameter is returned.
	 * </p>
	 * <p>
	 * If the IP address is an URI / URL as a string, only the host part is kept.
	 * </p>
	 * <p>
	 * localhost => the IP address of the local host (e.g. 192.168.1.22)
	 * 127.0.0.1 => the IP address of the local host (e.g. 192.168.1.22)
	 * host name => the IP address of the host (e.g. 192.168.1.22)
	 * IP address => the IP address (e.g. 192.168.1.22)
	 * </p>
	 *
	 * @param hostOrIpAddress the host name or its IP address
	 * @return the IP address associated with this host
	 */
	public static String resolveUrl( String hostOrIpAddress ) {

		String host = hostOrIpAddress;
		try {
			host = UriAndUrlHelper.urlToUri( hostOrIpAddress ).getHost();
		} catch( Exception e1 ) {
			// nothing
		}

		try {
			if( "localhost".equalsIgnoreCase( host )
						|| "127.0.0.1".equals( host ))
				return InetAddress.getLocalHost().getHostAddress();

			return InetAddress.getByName( host ).getHostAddress();

		} catch( UnknownHostException e ) {
			e.printStackTrace();
		}

		return hostOrIpAddress;
	}


	/**
	 * Checks whether a host is the local host.
	 * @param hostOrIpAddress the host name or its IP address
	 * @return true if the host is the local host, false otherwise or if it could not be determined.
	 *
	 * FIXME: use network interfaces?
	 * See http://www.jguru.com/faq/view.jsp?EID=790132
	 */
	public static boolean isLocalHost( String hostOrIpAddress ) {

		try {
			String resolvedUrl = resolveUrl( hostOrIpAddress );
			String localHostUrl = InetAddress.getLocalHost().getHostAddress();
			return resolvedUrl.equals( localHostUrl );

		} catch( UnknownHostException e ) {
			e.printStackTrace();
		}

		// In case of doubt, return false
		return false;
	}
}
