/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.welcome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.studio.PetalsStudioPlugin;
import com.ebmwebsourcing.petals.studio.utils.Base64;
import com.ebmwebsourcing.petals.studio.utils.VersionUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class RegistrationManager {

	private final static String EMAIL = "petals.studio.email";
	private final static String COMPANY = "petals.studio.company";
	private final static String NAME = "petals.studio.name";
	private final static String PHONE = "petals.studio.phone";
	private final static String LANG = "petals.studio.language";
	private final static String REGISTERED = "petals.studio.done";
	private final static String LAST_STUDIO_VERSION = "petals.studio.version";
	private final static String JOKER = "petals.studio.joker";

	private final static String PROXY_HOST = "petals.studio.proxy.host";
	private final static String PROXY_USER = "petals.studio.proxy.user";
	private final static String PROXY_PWD = "petals.studio.proxy.password";
	private final static String PROXY_PORT = "petals.studio.proxy.port";

	private final File registrationFile;
	private final Key secretKey;
	private final Cipher cipher;
	private RegistrationBean backupRegistrationBean;

	private static RegistrationManager instance;


	/**
	 * Constructor.
	 * @throws IOException if the registration file does not exist and could not be created
	 * @throws GeneralSecurityException
	 */
	private RegistrationManager() throws IOException, GeneralSecurityException {

		// Get or create the backup file
		this.registrationFile = new File( System.getProperty( "user.home" ), ".PetalsStudio.backup" );
		if( ! this.registrationFile.exists() && ! this.registrationFile.createNewFile()) {
			IOException e = new IOException( "Could not create the registration file." );
			PetalsStudioPlugin.log( e, IStatus.ERROR, "Could not create the registration file." );
			throw e;
		}

		// Get the key (always the same, we do not store critical data).
		// Plus, they are stored on the user's machine.

		// PBEKeySpec keySpec = new PBEKeySpec( "BlabluBloBlik".toCharArray());
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );
		// this.secretKey = keyFactory.generateSecret( keySpec );

		this.secretKey = new SecretKeySpec( "BlabluBloBlik".getBytes(), "Blowfish" );

		// Get the cipher
		this.cipher = Cipher.getInstance( "Blowfish" );
	}


	/**
	 * Backup registration data.
	 * @param bean a registration bean
	 * @return true if the backup succeeded, false otherwise
	 */
	public boolean backupRegistrationData( RegistrationBean bean ) {

		boolean registered = false;
		try {
			// Prepare the elements to serialize
			Properties properties = new Properties();
			properties.setProperty( NAME, encode( bean.getName()));
			properties.setProperty( EMAIL, encode( bean.getEmail()));
			properties.setProperty( COMPANY, encode( bean.getCompany()));
			properties.setProperty( PHONE, encode( bean.getPhone()));
			properties.setProperty( LANG, encode( bean.getLanguage()));
			properties.setProperty( REGISTERED, encode( String.valueOf( bean.isRegistered())));
			properties.setProperty( LAST_STUDIO_VERSION, encode( bean.getLastRegisteredVersion()));

			if( bean.getProxyHost() != null )
				properties.setProperty( PROXY_HOST, encode( bean.getProxyHost()));

			if( bean.getProxyPassword() != null )
				properties.setProperty( PROXY_PWD, encode( bean.getProxyPassword()));

			if( bean.getProxyUser() != null )
				properties.setProperty( PROXY_USER, encode( bean.getProxyUser()));

			if( bean.getProxyPort() != null )
				properties.setProperty( PROXY_PORT, encode( String.valueOf( bean.getProxyPort())));


			// Serialize the elements
			FileOutputStream os = null;
			try {
				os = new FileOutputStream( this.registrationFile );
				properties.store( os, "Written by Petals Studio" );
				registered = true;
				this.backupRegistrationBean = bean;

			} catch( Exception e ) {
				PetalsStudioPlugin.log( e, IStatus.WARNING );

			} finally {
				try {
					if( os != null )
						os.close();

				} catch( IOException e ) {
					PetalsStudioPlugin.log( e, IStatus.ERROR );
				}
			}

		} catch( Exception e ) {
			PetalsStudioPlugin.log( e, IStatus.ERROR );
		}

		return registered;
	}


	/**
	 * Restores registration data from the backup file.
	 * @return a registration bean (never null)
	 */
	public RegistrationBean restoreRegistrationData() {

		if( this.backupRegistrationBean == null ) {

			// Get the elements to read
			Properties properties = new Properties();
			FileInputStream is = null;
			try {
				is = new FileInputStream( this.registrationFile );
				properties.load( is );

			} catch( Exception e ) {
				PetalsStudioPlugin.log( e, IStatus.WARNING );

			} finally {
				try {
					if( is != null )
						is.close();

				} catch( IOException e ) {
					PetalsStudioPlugin.log( e, IStatus.ERROR );
				}
			}

			// Fill-in the registration bean
			try {
				this.backupRegistrationBean = new RegistrationBean();
				String name = properties.getProperty( NAME );
				this.backupRegistrationBean.setName( decode( name ));

				String email = properties.getProperty( EMAIL );
				this.backupRegistrationBean.setEmail( decode( email ));

				String company = properties.getProperty( COMPANY );
				this.backupRegistrationBean.setCompany( decode( company ));

				String phone = properties.getProperty( PHONE );
				this.backupRegistrationBean.setPhone( decode( phone ));

				String lang = properties.getProperty( LANG );
				this.backupRegistrationBean.setLanguage( decode( lang ));

				String registeredAS = properties.getProperty( REGISTERED );
				registeredAS = decode( registeredAS );
				boolean registered = Boolean.valueOf( registeredAS );
				this.backupRegistrationBean.setRegistered( registered );

				String jokerAS = properties.getProperty( JOKER );
				jokerAS = decode( jokerAS );
				boolean joker = Boolean.valueOf( jokerAS );
				this.backupRegistrationBean.setJoker( joker );

				String lastVersion = properties.getProperty( LAST_STUDIO_VERSION );
				this.backupRegistrationBean.setLastRegisteredVersion( decode( lastVersion ));

				String proxyHost = properties.getProperty( PROXY_HOST );
				if( proxyHost != null )
					this.backupRegistrationBean.setProxyHost( decode( proxyHost ));

				String proxyUser = properties.getProperty( PROXY_USER );
				if( proxyUser != null )
					this.backupRegistrationBean.setProxyUser( decode( proxyUser ));

				String proxyPwd = properties.getProperty( PROXY_PWD );
				if( proxyPwd != null )
					this.backupRegistrationBean.setProxyPassword( decode( proxyPwd ));

				String proxyPort = properties.getProperty( PROXY_PORT );
				if( proxyPort != null ) {
					String value = decode( proxyPort );
					int port = Integer.valueOf( value );
					this.backupRegistrationBean.setProxyPort( port );
				}

			} catch( Exception e ) {
				PetalsStudioPlugin.log( e, IStatus.ERROR );
			}
		}

		return this.backupRegistrationBean;
	}


	/**
	 * Encodes user information using the Blowfish encryption algorithm and a Base64.
	 * @param s the string to encode
	 * @return the encoded string
	 * @throws Exception if something went wrong
	 */
	public String encode( String s ) throws Exception {

		String result = "";
		if( s != null && s.trim().length() > 0 ) {

			this.cipher.init( Cipher.ENCRYPT_MODE, this.secretKey );
			byte[] encrypted = this.cipher.doFinal( s.getBytes());

			encrypted = Base64.encode( encrypted );
			result = new String( encrypted );
		}

		return result;
	}


	/**
	 * Decodes user information using the Blowfish encryption algorithm and a Base64.
	 * @param s the string to decode
	 * @return the decoded string
	 * @throws Exception if something went wrong
	 */
	private String decode( String s ) throws Exception {

		String result = "";
		if( s != null && s.trim().length() > 0 ) {
			byte[] decrypted = Base64.decode( s.getBytes());

			this.cipher.init( Cipher.DECRYPT_MODE, this.secretKey );
			decrypted = this.cipher.doFinal( decrypted );
			result = new String( decrypted );
		}

		return result;
	}


	/**
	 * @return the unique instance of this class
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public synchronized static RegistrationManager getInstance()
	throws IOException, GeneralSecurityException {

		if( instance == null )
			instance = new RegistrationManager();

		return instance;
	}


	/**
	 * Determine whether the registration process is required.
	 * @return true if the registration is required, false otherwise
	 */
	public static boolean needsRegistration() {

		boolean needsRegistration = true;
		try {
			// Get the saved data
			RegistrationManager mng = getInstance();
			RegistrationBean bean = mng.restoreRegistrationData();

			// Check the registration status and the last registered version
			String registeredVersion = VersionUtils.getTwoDigitVersion( bean.getLastRegisteredVersion());
			String currentVersion = VersionUtils.getProductVersion( false );

			// Dev mode
			if( VersionUtils.DEV_VERSION.equals( VersionUtils.getProductVersion( true )))
				needsRegistration = false;

			// Consultants
			else if( bean.isJoker())
				needsRegistration = false;

			// "1.0".compareTo( "1.1" ) returns a negative number
			else if( registeredVersion.trim().length() == 0	// Not registered
						|| registeredVersion.compareTo( currentVersion ) < 0 )	// Former version
				needsRegistration = true;

			else if( ! bean.isRegistered())
				needsRegistration = true;

			else
				needsRegistration = false;

		} catch( IOException e ) {
			PetalsStudioPlugin.log( e, IStatus.WARNING, "It could not be determined if the registration process was required. (IO)" );

		} catch( GeneralSecurityException e ) {
			PetalsStudioPlugin.log( e, IStatus.WARNING, "It could not be determined if the registration process was required. (Security)" );
		}

		return needsRegistration;
	}
}
