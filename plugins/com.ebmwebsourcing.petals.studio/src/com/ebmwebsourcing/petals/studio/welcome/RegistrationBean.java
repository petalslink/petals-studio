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

/**
 * A bean used to save and restore user information for the registration process.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RegistrationBean {

	// Used data
	private String email, company, name, phone, language;
	private boolean registered, joker;
	private String lastRegisteredVersion;

	// Proxy data
	private String proxyHost, proxyUser, proxyPassword;
	private int proxyPort;


	/**
	 * Empty constructor (package visibility).
	 */
	RegistrationBean() {
		// nothing
	}


	/**
	 * @return the email
	 */
	protected final String getEmail() {
		return this.email;
	}


	/**
	 * @param email the email to set
	 */
	protected final void setEmail( String email ) {
		this.email = email;
	}


	/**
	 * @return the company
	 */
	protected final String getCompany() {
		return this.company;
	}


	/**
	 * @param company the company to set
	 */
	protected final void setCompany( String company ) {
		this.company = company;
	}


	/**
	 * @return the name
	 */
	protected final String getName() {
		return this.name;
	}


	/**
	 * @param name the name to set
	 */
	protected final void setName( String name ) {
		this.name = name;
	}


	/**
	 * @return the phone
	 */
	protected final String getPhone() {
		return this.phone;
	}


	/**
	 * @param phone the phone to set
	 */
	protected final void setPhone( String phone ) {
		this.phone = phone;
	}


	/**
	 * @return the language
	 */
	protected final String getLanguage() {
		return this.language;
	}


	/**
	 * @param language the language to set
	 */
	protected final void setLanguage( String language ) {
		this.language = language;
	}


	/**
	 * @return the registered
	 */
	protected final boolean isRegistered() {
		return this.registered;
	}


	/**
	 * @param registered the registered to set
	 */
	protected final void setRegistered( boolean registered ) {
		this.registered = registered;
	}


	/**
	 * @return the joker
	 */
	public boolean isJoker() {
		return this.joker;
	}


	/**
	 * @param joker the joker to set
	 */
	public void setJoker( boolean joker ) {
		this.joker = joker;
	}


	/**
	 * @return the lastRegisteredVersion
	 */
	protected final String getLastRegisteredVersion() {
		return this.lastRegisteredVersion;
	}


	/**
	 * @param lastRegisteredVersion the lastRegisteredVersion to set
	 */
	protected final void setLastRegisteredVersion( String lastRegisteredVersion ) {
		this.lastRegisteredVersion = lastRegisteredVersion;
	}


	/**
	 * @return the proxyHost
	 */
	public String getProxyHost() {
		return this.proxyHost;
	}


	/**
	 * @param proxyHost the proxyHost to set
	 */
	public void setProxyHost( String proxyHost ) {
		this.proxyHost = proxyHost;
	}


	/**
	 * @return the proxyUser
	 */
	public String getProxyUser() {
		return this.proxyUser;
	}


	/**
	 * @param proxyUser the proxyUser to set
	 */
	public void setProxyUser( String proxyUser ) {
		this.proxyUser = proxyUser;
	}


	/**
	 * @return the proxyPassword
	 */
	public String getProxyPassword() {
		return this.proxyPassword;
	}


	/**
	 * @param proxyPassword the proxyPassword to set
	 */
	public void setProxyPassword( String proxyPassword ) {
		this.proxyPassword = proxyPassword;
	}


	/**
	 * @return the proxyPort
	 */
	public Integer getProxyPort() {
		return this.proxyPort;
	}


	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort( int proxyPort ) {
		this.proxyPort = proxyPort;
	}
}
