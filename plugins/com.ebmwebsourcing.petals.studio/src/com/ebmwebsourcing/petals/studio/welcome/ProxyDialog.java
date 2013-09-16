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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to get proxy settings.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProxyDialog extends TitleAreaDialog {

	private String proxyHost, proxyUser, proxyPassword;
	private int proxyPort;


	/**
	 * Constructor.
	 * @param parentShell
	 */
	public ProxyDialog( Shell parentShell ) {
		super( parentShell );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		Composite container = new Composite((Composite) super.createDialogArea( parent ), SWT.NONE );
		container.setLayout( new GridLayout( 2, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		new Label( container, SWT.NONE ).setText( "Proxy Host*:" );
		Text text = new Text( container, SWT.BORDER | SWT.SINGLE );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.proxyHost != null )
			text.setText( this.proxyHost );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ProxyDialog.this.proxyHost = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Proxy Port*:" );
		Spinner spinner = new Spinner( container, SWT.BORDER );
		spinner.setLayoutData( new GridData( 100, SWT.DEFAULT ));
		if( this.proxyPort == 0 )
			this.proxyPort = 1;

		spinner.setValues( this.proxyPort, 1, Integer.MAX_VALUE, 0, 1, 100 );
		spinner.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ProxyDialog.this.proxyPort = ((Spinner) e.widget).getSelection();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Proxy Username:" );
		text = new Text( container, SWT.BORDER | SWT.SINGLE );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.proxyUser != null )
			text.setText( this.proxyUser );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ProxyDialog.this.proxyUser = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Proxy Password:" );
		text = new Text( container, SWT.BORDER | SWT.SINGLE );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.proxyPassword != null )
			text.setText( this.proxyPassword );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ProxyDialog.this.proxyPassword = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		// Update the dialog's title  and message
		getShell().setText( "Proxy Settings" );
		setTitle( "Proxy Settings" );
		setMessage( "Define the proxy settings." );

		return container;
	}


	/**
	 * Validates the user input.
	 */
	private void validate() {

		if( this.proxyHost == null || this.proxyHost.length() == 0 )
			updateStatus( "You have to provide the proxy address." );
		else if( this.proxyHost.startsWith( "http://" ))
			updateStatus( "The proxy host cannot be prefixed with a protocol." );
		else
			updateStatus( null );
	}


	/**
	 * Updates the dialog status.
	 * @param msg
	 */
	private void updateStatus( String msg ) {

		setErrorMessage( msg );
		if( getButton( IDialogConstants.OK_ID ) != null )
			getButton( IDialogConstants.OK_ID ).setEnabled( msg == null );
	}


	/**
	 * @return the proxyUser
	 */
	public String getProxyUser() {
		return this.proxyUser;
	}


	/**
	 * @return the proxyPassword
	 */
	public String getProxyPassword() {
		return this.proxyPassword;
	}


	/**
	 * @return the proxyPort
	 */
	public int getProxyPort() {
		return this.proxyPort;
	}


	/**
	 * @param proxyUser the proxyUser to set
	 */
	public void setProxyUser( String proxyUser ) {
		this.proxyUser = proxyUser;
	}


	/**
	 * @param proxyPassword the proxyPassword to set
	 */
	public void setProxyPassword( String proxyPassword ) {
		this.proxyPassword = proxyPassword;
	}


	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort( int proxyPort ) {
		this.proxyPort = proxyPort;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar( Composite parent ) {
		super.createButtonsForButtonBar( parent );

		validate();
		if( this.proxyHost == null )
			setErrorMessage( null );
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
}
