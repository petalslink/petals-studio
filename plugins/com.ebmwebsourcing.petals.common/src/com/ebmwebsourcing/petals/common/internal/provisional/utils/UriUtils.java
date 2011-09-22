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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class UriUtils {

	/**
	 * Builds an URI from an URL (with an handle for URLs not compliant with RFC 2396).
	 * @param url
	 * @return
	 */
	public static URI urlToUri( URL url ) {

		URI uri;
		try {
			// Possible failing step.
			uri = url.toURI();
		} catch( Exception e ) {
			// URL did not comply with RFC 2396 => illegal un-escaped characters.
			try {
				uri = new URI(
						url.getProtocol(), url.getUserInfo(),
						url.getHost(), url.getPort(),
						url.getPath(), url.getQuery(), url.getRef());
			} catch( Exception e1 ) {
				// No automatic repair.
				throw new IllegalArgumentException( "Broken URL: " + url );
			}
		}

		uri = uri.normalize();
		return uri;
	}


	/**
	 * Builds an URI from an URL string (with an handle for URLs not compliant with RFC 2396).
	 * @param urlAsString
	 * @return
	 */
	public static URI urlToUri( String urlAsString ) {

		try {
			URL url = new URL( urlAsString );
			return urlToUri( url );

		} catch( Exception e ) {
			// e.printStackTrace();
		}

		throw new IllegalArgumentException( "Broken URL: " + urlAsString );
	}


	/**
	 * Builds an URL from a string and escapes illegal characters.
	 * @param path
	 * @return
	 */
	public static String convertFilePathToUrl( String path ) {

		URL url;
		try {
			url = new URL( path );
		} catch( MalformedURLException e1 ) {
			try {
				URI uri = new File( path ).toURI();
				uri.normalize();
				url = uri.toURL();

			} catch( Exception e ) {
				throw new IllegalArgumentException( "Broken URL: " + path );
			}
		}

		return url.toString();
	}


	/**
	 * Builds an URI from an URI and a suffix.
	 * 
	 * <p>
	 * This suffix can be an absolute URL, or a relative path
	 * with respect to the first URI. In this case, the suffix is resolved
	 * with respect to the URI.
	 * </p>
	 * <p>
	 * If the suffix is already an URL, its is returned.<br />
	 * If the suffix is a relative file path and cannot be resolved, an exception is thrown.
	 * </p>
	 * <p>
	 * The returned URI is normalized.
	 * </p>
	 * 
	 * @param referenceUri the reference URI
	 * @param uriSuffix the URI suffix (not null)
	 * @return the new URI
	 * @throws Exception if the resolution failed
	 */
	public static URI buildNewURI( URI referenceUri, String uriSuffix ) throws Exception {

		if( uriSuffix == null )
			throw new NullPointerException( "The URI suffix cannot be null." );

		uriSuffix = uriSuffix.replaceAll( "\\\\", "/" );
		URI importUri = null;
		try {	// Absolute URL ?
			importUri = urlToUri( new URL( uriSuffix ));

		} catch( Exception e ) {
			try {	// Relative URL ?
				importUri = referenceUri.resolve( new URI( null, uriSuffix, null ));

			} catch( Exception e2 ) {
				String msg =
						"An URI could not be built from the URI " + referenceUri.toString()
						+ " and the suffix " + uriSuffix + ".";
				throw new Exception( msg, e2 );
			}
		}

		return importUri.normalize();
	}


	/**
	 * Determines whether a string is an absolute URI.
	 * @param uriAsString the potential URI, as a string
	 * @return true if it is an absolute URI, false otherwise or if it is not a valid URI
	 */
	public static boolean isAbsoluteUri( String uriAsString ) {

		boolean result;
		try {
			URI uri = urlToUri( uriAsString );
			result = uri.isAbsolute();

		} catch( Exception e ) {
			result = false;
		}

		return result;
	}


	/**
	 * Returns the relative position of <code>uri</code> with respect to <code>originUri</code>.
	 * 
	 * @param originUri the URI which acts as the <i>origin</i>.
	 * @param uri the URI whose relative path must be computed with respect to originUri.
	 * @return the relative path of <code>uri</code> with respect to originUri, if they
	 * are on the same host and have a same scheme, or <code>uri<code> otherwise.
	 */
	public static URI getRelativeLocationToUri( URI originUri, URI uri ) {

		URI result = uri;
		try {
			originUri = UriUtils.urlToUri( originUri.toURL());
			uri = UriUtils.urlToUri( uri.toURL());
			URI relativeUri = originUri.relativize( uri ).normalize();
			if( ! uri.equals( relativeUri )) {
				result = relativeUri;
			}
			else {
				// Do they have the same scheme?
				String originScheme = originUri.getScheme();
				String scheme = uri.getScheme();
				boolean sameScheme = false;
				if( originScheme != null )
					sameScheme = originScheme.equals( scheme );
				else
					sameScheme = scheme == null;

				// Do they have the same host?
				String originHost = originUri.getHost();
				String host = uri.getHost();
				boolean sameHost = false;
				if( originHost != null )
					sameHost = originHost.equals( host );
				else
					sameHost = host == null;

				// Make a special treatment for "file" scheme.
				// Are they on the same disk partition?
				// If no, then there is no relative path to compute.
				boolean samePartition = true;
				if( sameScheme && "file".equalsIgnoreCase( originScheme )) {

					String originDevice = new Path( originUri.getPath()).getDevice();
					String device = new Path( uri.getPath()).getDevice();
					if( originDevice != null )
						samePartition = originDevice.equals( device );
					else
						samePartition = device == null;
				}

				// If yes for both, we can define a relative location.
				// The only reason why URI#relativize( ... ) could have failed previously,
				// is because this method only works when "uri" is a child of "originUri".
				// Otherwise, we have to insert ".." when required.
				if( sameScheme && sameHost && samePartition ) {
					String originPath = originUri.toString().replaceAll( "\\\\", "/" );
					String path = uri.toString().replaceAll( "\\\\", "/" );

					// Split the paths
					String[] originParts = originPath.split( "/" );
					String[] fileParts = path.split( "/" );

					int matchSegmentsCpt = 0;
					for( int i=0; i<originParts.length; i++ ) {
						if( i >= fileParts.length )
							break;

						String otherSegment = fileParts[ i ];
						String segment = originParts[ i ];
						if( segment.equalsIgnoreCase( otherSegment ))
							matchSegmentsCpt ++;
						else
							break;
					}

					// Build the relative location
					StringBuffer relativePath = new StringBuffer();
					if( matchSegmentsCpt > 0 ) {

						for( int i=matchSegmentsCpt; i<originParts.length - 1; i++ )
							relativePath.append( "../" );
						for( int i=matchSegmentsCpt; i<fileParts.length - 1; i++ )
							relativePath.append( fileParts[ i ] + "/" );
						relativePath.append( fileParts[ fileParts.length - 1 ]);

						// Remove extra ".." and "."
						result = new URI( null, relativePath.toString(), null );
					}
				}
			}

		} catch( URISyntaxException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} catch( MalformedURLException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
