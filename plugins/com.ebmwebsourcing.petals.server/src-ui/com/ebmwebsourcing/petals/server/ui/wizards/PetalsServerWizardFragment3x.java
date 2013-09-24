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

package com.ebmwebsourcing.petals.server.ui.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.TaskModel;
import org.eclipse.wst.server.ui.wizard.IWizardHandle;
import org.eclipse.wst.server.ui.wizard.WizardFragment;

import com.ebmwebsourcing.petals.server.PetalsServerPlugin;
import com.ebmwebsourcing.petals.server.server.IPetalsServerWorkingCopy;
import com.ebmwebsourcing.petals.server.utils.TopologyHandler3x;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServerWizardFragment3x extends WizardFragment {

	private IServerWorkingCopy serverWc;
	private IPetalsServerWorkingCopy petalsServerWc;
	private IWizardHandle wizard;

	private String host, wsPrefix, containerName;
	private int port;
	private String serverName;

	private Text serverNameText, hostText, wsPrefixText, containerNameText;
	private Spinner portSpinner;



	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#hasComposite()
	 */
	@Override
	public boolean hasComposite() {
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#enter()
	 */
	@Override
	public void enter() {

		// Get the model
		Object o = getTaskModel().getObject( TaskModel.TASK_SERVER );
		if( o instanceof IServer ) {
			o = ((IServer) o).createWorkingCopy();
			getTaskModel().putObject( TaskModel.TASK_SERVER, o );
		}

		this.serverWc = (IServerWorkingCopy) o;
		if( this.serverWc.getOriginal() == null ) {
			try {
				this.serverWc.save( true, null );

			} catch( CoreException e ) {
				PetalsServerPlugin.log( e, IStatus.ERROR );
			}
		}

		this.petalsServerWc = (IPetalsServerWorkingCopy) this.serverWc.loadAdapter( IPetalsServerWorkingCopy.class, null );

		// Pre-fill information
		if( this.serverNameText != null )
			this.serverNameText.setText( this.serverWc.getName());

		if( hasLocalTopology() && this.host == null )
			parseTopology();

		if( this.containerName != null )
			this.containerNameText.setText( this.containerName );

		if( this.host != null )
			this.hostText.setText( this.host );

		if( this.wsPrefix != null )
			this.wsPrefixText.setText( this.wsPrefix );

		this.portSpinner.setSelection( this.port );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#exit()
	 */
	@Override
	public void exit() {
		// nothing
	}


	/**
	 * @return
	 */
	private boolean hasLocalTopology() {

		boolean hasLocalTopology = this.serverWc.getRuntime().getLocation() != null;
		return hasLocalTopology;
	}


	/**
	 *
	 */
	private void parseTopology() {

		try {
			IPath installPath = this.serverWc.getRuntime().getLocation();
			File rootFile = installPath.toFile();
			if( ! rootFile.exists() )
				return;

			File serverFile = new File( rootFile, "conf/server.properties" );
			if( ! serverFile.exists() )
				return;

			Properties serverProperties = new Properties();
			FileInputStream fis = null;
			try {
				// Parse the petalsServerWc properties
				fis = new FileInputStream( serverFile );
				serverProperties.load( fis );
				String containerName = serverProperties.getProperty( "petals.container.name", null );
				if( containerName == null )
					return;

				// Parse the topology.xml
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();

				TopologyHandler3x handler = new TopologyHandler3x( containerName );
				File topologyFile = new File( rootFile, "conf/topology.xml" );
				parser.parse( topologyFile, handler );

				this.containerName = containerName;
				this.host = handler.getHost();
				this.wsPrefix = handler.getWsPrefix();
				this.port = handler.getPort();

			} catch( Exception e1 ) {
				PetalsServerPlugin.log( e1, IStatus.ERROR );

			} finally {
				try {
					if( fis != null )
						fis.close();

				} catch( IOException e ) {
					PetalsServerPlugin.log( e, IStatus.ERROR );
				}
			}

		} finally {
			// Whatever happens, validate the fields
			validate();
		}
	}


	/**
	 * Validates the page data.
	 */
	public void validate() {

		// Validate the runtime
		this.petalsServerWc.setContainerName( this.containerName );
		this.petalsServerWc.setHost( this.host );
		this.petalsServerWc.setPort( this.port );
		this.petalsServerWc.setWsPrefix( this.wsPrefix );
		this.serverWc.setName( this.serverName );

		String errorMsg = this.petalsServerWc.validateTopologyInformation();
		this.wizard.setMessage( errorMsg, IMessageProvider.ERROR );
		setComplete( errorMsg == null );
		this.wizard.update();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment
	 * #createComposite(org.eclipse.swt.widgets.Composite, org.eclipse.wst.server.ui.wizard.IWizardHandle)
	 */
	@Override
	public Composite createComposite( Composite parent, IWizardHandle wizard ) {

		// Wizard
		this.wizard = wizard;
		wizard.setTitle( "Petals server" );
		wizard.setDescription( "Create a new Petals server." );
		wizard.setImageDescriptor( PetalsServerPlugin.getImageDescriptor( "icons/wizban/pstudio_64x64.png" ));

		// Composite
		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginTop = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Redefine the petalsServerWc name
		new Label( container, SWT.NONE ).setText( "Server name:" );
		this.serverNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.serverNameText.setLayoutData( layoutData );
		this.serverNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PetalsServerWizardFragment3x.this.serverName = PetalsServerWizardFragment3x.this.serverNameText.getText();
				validate();
			}
		});


		// Topology parameters
		Composite separatorComposite = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginTop = 20;
		separatorComposite.setLayout( layout );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		separatorComposite.setLayoutData( layoutData );

		Label titleLabel = new Label( separatorComposite, SWT.NONE );
		titleLabel.setText( "From the topology.xml file" );
		Label separatorLabel = new Label( separatorComposite, SWT.SEPARATOR | SWT.HORIZONTAL );
		separatorLabel.setLayoutData( new GridData( SWT.FILL, SWT.BOTTOM, true, false ));


		new Label( container, SWT.NONE ).setText( "Container:" );
		this.containerNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.containerNameText.setLayoutData( layoutData );
		this.containerNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PetalsServerWizardFragment3x.this.containerName = PetalsServerWizardFragment3x.this.containerNameText.getText();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Host:" );
		this.hostText = new Text( container, SWT.BORDER | SWT.SINGLE );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.hostText.setLayoutData( layoutData );
		this.hostText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PetalsServerWizardFragment3x.this.host = PetalsServerWizardFragment3x.this.hostText.getText();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "WS prefix:" );
		this.wsPrefixText = new Text( container, SWT.BORDER | SWT.SINGLE );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.wsPrefixText.setLayoutData( layoutData );
		this.wsPrefixText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PetalsServerWizardFragment3x.this.wsPrefix = PetalsServerWizardFragment3x.this.wsPrefixText.getText();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Port:" );
		this.portSpinner = new Spinner( container, SWT.BORDER | SWT.SINGLE );
		this.portSpinner.setValues( 9600, 0, Integer.MAX_VALUE, 0, 1, 10 );
		this.portSpinner.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PetalsServerWizardFragment3x.this.port = PetalsServerWizardFragment3x.this.portSpinner.getSelection();
				validate();
			}
		});

		this.portSpinner.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				PetalsServerWizardFragment3x.this.port = PetalsServerWizardFragment3x.this.portSpinner.getSelection();
				validate();
			}
		});

		return container;
	}
}
