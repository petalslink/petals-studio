/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import javax.xml.namespace.QName;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferPlugin;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.v24.wizard.FileTransferProvidesWizard24.Contract;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FiletransferProvidesPage extends AbstractSuWizardPage {

	private Contract contract = Contract.WRITE_FILES;
	private String writeDirectory, readDirectory, backupDirectory, filePattern;
	private CopyMode copyMode = CopyMode.CONTENT_AND_ATTACHMENTS;
	// private final TransferMode transferMode = TransferMode.CONTENT;
	// private final int pollingPeriod = 1000;
	private Image contractImage;


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.contractImage != null && ! this.contractImage.isDisposed())
			this.contractImage.dispose();

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		String error = null;

		// PROVIDE mode
		if( this.contract == Contract.WRITE_FILES ) {
			if( StringUtils.isEmpty( this.writeDirectory ))
				error = "You have to define the write directory.";

		} else {
			if( StringUtils.isEmpty( this.readDirectory ))
				error = "You have to define the directory to read.";
		}

		// CONSUME mode
//		else {
//			if( StringUtils.isEmpty( this.readDirectory ))
//				error = "You have to define the directory to read.";
//			else if( this.pollingPeriod <= 0 )
//				error = "The polling period must be superior to zero.";
//		}

		// Update the UI
		updateStatus( error );
		return error == null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( final Composite parent ) {

		// Create the composite container and define its layout.
		this.contractImage = FileTransferPlugin.loadImage( "icons/obj16/contract.gif" );
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// Select the service to consume
		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).margins( 0, 0 ).extendedMargins( 0, 0, 0, 19 ).applyTo( subContainer );
		GridDataFactory.swtDefaults().align( SWT.FILL, SWT.CENTER ).span( 2, 1 ).applyTo( subContainer );

		SwtFactory.createLabel( subContainer, "", null ).setImage( this.contractImage );
		SwtFactory.createLabel( subContainer, "Select the service contrat to use:", "The contract to use and configure" );

		Combo contractCombo = new Combo( subContainer, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
		contractCombo.add( "Writer contract: write incoming messages on a file system" );
		contractCombo.add( "Reader contract: read and list files from a given directory" );
		contractCombo.select( this.contract == Contract.WRITE_FILES ? 0 : 1 );

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		contractCombo.setLayoutData( layoutData );
		contractCombo.addSelectionListener( new DefaultSelectionListener() {
			public void widgetSelected( SelectionEvent e ) {

				String name;
				if( ((Combo) e.widget).getSelectionIndex() == 0 ) {
					FiletransferProvidesPage.this.contract = Contract.WRITE_FILES;
					name = "WriteFiles";

				} else {
					FiletransferProvidesPage.this.contract = Contract.READ_FILES;
					name = "GetFiles";
				}

				getNewlyCreatedEndpoint().setInterfaceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", name ));
				updateSubWidgets( container );
				validate();
			}
		});

		// Add the default widgets
		contractCombo.notifyListeners( SWT.Selection, new Event());


//			// Directories first
//			String[] labels = { "Read Directory:", "Backup Directory:" };
//			String[] tooltips = { "The directory to read", "The directory into which read files are moved (the temporary directory by default)" };
//			final Text[] texts = new Text[ 2 ];
//
//			for( int i=0; i<labels.length; i++ ) {
//				Label l = new Label( container, SWT.NONE );
//				l.setText( labels[ i ]);
//				l.setToolTipText( tooltips[ i ]);
//
//				texts[ i ] = createFileBrowser( container );
//				final int cpt = i;
//				texts[ i ].addModifyListener( new ModifyListener() {
//					public void modifyText( ModifyEvent e ) {
//						setValue( cpt, ((Text) e.widget).getText().trim());
//						validate();
//					}
//				});
//			}
//
//			// Other details
//			Label l = new Label( container, SWT.NONE );
//			l.setText( "Transfer Mode:" );
//			l.setToolTipText( "The way files are sent into Petals ESB" );
//
//			final ComboViewer viewer = new ComboViewer( container, SWT.SINGLE | SWT.BORDER );
//			viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
//			viewer.setContentProvider( new ArrayContentProvider());
//			viewer.setLabelProvider( new LabelProvider());
//			viewer.setInput( TransferMode.values());
//			viewer.setSelection( new StructuredSelection( this.transferMode ));
//
//			viewer.addSelectionChangedListener( new ISelectionChangedListener() {
//				public void selectionChanged( SelectionChangedEvent event ) {
//					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
//					FTComponentPage.this.transferMode = (TransferMode) o;
//				}
//			});
//
//			l = new Label( container, SWT.NONE );
//			l.setText( "File Pattern:" );
//			l.setToolTipText( "The file name pattern to filter the resources to process" );
//
//			Text text = new Text( container, SWT.SINGLE | SWT.BORDER );
//			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
//			text.setText( "*" );
//			text.addModifyListener( new ModifyListener() {
//				public void modifyText( ModifyEvent e ) {
//					FTComponentPage.this.filePattern = ((Text) e.widget).getText().trim();
//					validate();
//				}
//			});
//
//			l = new Label( container, SWT.NONE );
//			l.setText( "Polling Period:" );
//			l.setToolTipText( "The time between each polling" );
//
//			final Spinner pollingSpinner = new Spinner( container, SWT.BORDER );
//			pollingSpinner.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
//			pollingSpinner.setMaximum( Integer.MAX_VALUE );
//			pollingSpinner.setMinimum( 0 );
//			pollingSpinner.setIncrement( 100 );
//			pollingSpinner.setPageIncrement( 1000 );
//			pollingSpinner.setDigits( 0 );
//			pollingSpinner.setSelection( this.pollingPeriod );
//			pollingSpinner.addModifyListener( new ModifyListener() {
//				public void modifyText( ModifyEvent e ) {
//					FTComponentPage.this.pollingPeriod = pollingSpinner.getSelection();
//					validate();
//				}
//			});


		// Complete the page
		validate();
		setErrorMessage( null );
	}


	/**
	 * Create widgets in function of the contract (provides mode).
	 * @param container
	 */
	private void updateSubWidgets( Composite container ) {

		// Remove children - except the first one
		int childCpt = 0;
		for( Control c : container.getChildren()) {
			childCpt ++;
			if( childCpt > 1 && ! c.isDisposed())
				c.dispose();
		}

		// Add the new children: "write" mode first
		if( this.contract == Contract.WRITE_FILES ) {
			SwtFactory.createLabel( container,  "Write Directory *:", "The directory in which the message content will be written" );
			Text text = SwtFactory.createDirectoryBrowser( container ).getText();
			if( this.writeDirectory != null )
				text.setText( this.writeDirectory );

			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FiletransferProvidesPage.this.writeDirectory = ((Text) e.widget).getText().trim();
					validate();
				}
			});

			SwtFactory.createLabel( container, "Write Mode *:", "What part(s) of the message should be written" );
			final ComboViewer viewer = SwtFactory.createDefaultComboViewer( container, true, true, CopyMode.values());
			viewer.setSelection( new StructuredSelection( this.copyMode ));
			viewer.addSelectionChangedListener( new ISelectionChangedListener() {
				public void selectionChanged( SelectionChangedEvent event ) {
					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
					FiletransferProvidesPage.this.copyMode = (CopyMode) o;
				}
			});


			SwtFactory.createLabel( container, "File Name:", "The base name of the file to write (will be appended the system date)" );
			text = SwtFactory.createSimpleTextField( container, true );
			if( this.filePattern != null )
				text.setText( this.filePattern );

			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FiletransferProvidesPage.this.filePattern = ((Text) e.widget).getText().trim();
					validate();
				}
			});
		}

		// "Get files" mode then
		else {
			String[] labels = { "Read Directory *:", "Backup Directory:" };
			String[] tooltips = { "The directory to read", "The directory into which read files are moved (the temporary directory by default)" };
			final Text[] texts = new Text[ 2 ];

			for( int i=0; i<labels.length; i++ ) {
				SwtFactory.createLabel( container, labels[ i ], tooltips[ i ]);

				texts[ i ] = SwtFactory.createDirectoryBrowser( container ).getText();
				String value = i == 0 ? this.readDirectory : this.backupDirectory;
				if( value != null )
					texts[ i ].setText( value );

				final int cpt = i;
				texts[ i ].addModifyListener( new ModifyListener() {
					public void modifyText( ModifyEvent e ) {
						setValue( cpt, ((Text) e.widget).getText().trim());
						validate();
					}
				});
			}
		}

		container.layout();
	}


	/**
	 * @param i
	 * @param value
	 */
	private void setValue( int i, String value ) {
		if( i == 0 )
			this.readDirectory = value;
		else
			this.backupDirectory = value;
	}


	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return this.contract;
	}


	/**
	 * @return the writeDirectory
	 */
	public String getWriteDirectory() {
		return this.writeDirectory;
	}


	/**
	 * @return the readDirectory
	 */
	public String getReadDirectory() {
		return this.readDirectory;
	}


	/**
	 * @return the backupDirectory
	 */
	public String getBackupDirectory() {
		return this.backupDirectory;
	}


	/**
	 * @return the filePattern
	 */
	public String getFilePattern() {
		return this.filePattern;
	}


	/**
	 * @return the copyMode
	 */
	public CopyMode getCopyMode() {
		return this.copyMode;
	}
}
