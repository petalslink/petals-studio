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

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.filetransfer.FileTransferPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * A wizard page which provides some better helpers for configuration parameters (provides).
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class FTComponentPage extends AbstractSuPage {

	/**
	 * Configuration mode: true => "write" contract, false => "get files" contract
	 */
	private boolean writeContract = true;

	private String writeDirectory, readDirectory, backupDirectory, filePattern;
	private CopyMode copyMode = CopyMode.CONTENT_AND_ATTACHMENTS;
	private TransferMode transferMode = TransferMode.CONTENT;
	private int pollingPeriod = 1000;
	private Image contractImage;



	/**
	 * The copy mode.
	 */
	public enum CopyMode {
		CONTENT_ONLY, ATTACHMENTS_ONLY, CONTENT_AND_ATTACHMENTS;

		/*
		 * (non-Jsdoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {

			String result;
			switch( this ) {
			case CONTENT_ONLY:
				result = "content-only";
				break;

			case ATTACHMENTS_ONLY:
				result = "attachments-only";
				break;

			case CONTENT_AND_ATTACHMENTS:
				result = "content-and-attachments";
				break;

			default:
				result = "";
			}

			return result;
		};
	}


	/**
	 * The copy mode.
	 */
	public enum TransferMode {
		CONTENT, ATTACHMENT;

		/*
		 * (non-Jsdoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {

			String result;
			switch( this ) {
			case CONTENT:
				result = "content";
				break;

			case ATTACHMENT:
				result = "attachment";
				break;

			default:
				result = "";
			}

			return result;
		};
	}


	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public FTComponentPage() {
		// This page overrides the specific page.
		// The page title must be this one.
		super( SuMainConstants.PAGE_SPECIFIC_JBI_DATA );

		// Get the image
		try {
			ImageDescriptor desc = FileTransferPlugin.getImageDescriptor( "icons/obj16/contract.gif" );
			this.contractImage = desc.createImage();
		} catch( Exception e ) {
			FileTransferPlugin.log( e, IStatus.WARNING );
		}
	}


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
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setBasicFields(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {

		super.setBasicFields( suType, suTypeVersion, pluginId );
		registerNamespace( "filetransfer", "http://petals.ow2.org/components/filetransfer/version-2" );
		registerNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-5" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.pages.AbstractSuPage#
	 * setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// Nothing.
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #updatePage(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.WizardConfiguration)
	 */
	public void updatePage( WizardConfiguration wizardConfiguration ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		setMessage( null, IMessageProvider.INFORMATION );
		String error = null;

		// PROVIDE mode
		if( isProvides()) {
			if( this.writeContract ) {
				if( StringUtils.isEmpty( this.writeDirectory ))
					error = "You have to define the write directory.";

			} else {
				if( StringUtils.isEmpty( this.readDirectory ))
					error = "You have to define the directory to read.";
			}
		}

		// CONSUME mode
		else {
			if( StringUtils.isEmpty( this.readDirectory ))
				error = "You have to define the directory to read.";
			else if( this.pollingPeriod <= 0 )
				error = "The polling period must be superior to zero.";
		}

		// Update the UI
		setPageComplete( error == null );
		setErrorMessage( error );
		return error == null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		// PROVIDE mode
		if( isProvides()) {
			if( this.writeContract ) {
				suBean.specificElements.add( createXmlElement( "filetransfer:write-directory", false, false, this.writeDirectory ));
				suBean.specificElements.add( createXmlElement( "filetransfer:copy-mode", false, false, this.copyMode.toString()));
				suBean.specificElements.add( createXmlElement( "filetransfer:file-pattern", false, true, this.filePattern ));

			} else {
				suBean.specificElements.add( createXmlElement( "filetransfer:read-directory", false, false, this.readDirectory ));
				suBean.specificElements.add( createXmlElement( "filetransfer:backup-directory", false, true, this.backupDirectory ));
			}

			// Overwrite the interface name
			String name = this.writeContract ? "WriteFiles" : "GetFiles";
			suBean.setInterfaceName( name );

			// Define the name of the WSDL
			suBean.setCreatedWsdlMarkupValue( name + ".wsdl" );
			suBean.customObjects.put( "write-contract", this.writeContract );
		}

		// CONSUME mode
		else {
			suBean.specificElements.add( createXmlElement( "filetransfer:read-directory", false, false, this.readDirectory ));
			suBean.specificElements.add( createXmlElement( "filetransfer:backup-directory", false, true, this.backupDirectory ));
			suBean.specificElements.add( createXmlElement( "filetransfer:transfer-mode", false, false, this.transferMode.toString()));
			suBean.specificElements.add( createXmlElement( "filetransfer:file-pattern", false, true, this.filePattern ));
			suBean.specificElements.add( createXmlElement( "filetransfer:polling-period", false, true, String.valueOf( this.pollingPeriod )));
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( final Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// PROVIDE mode
		if( isProvides()) {

			// Select the service to consume
			Composite subContainer = new Composite( container, SWT.NONE );
			layout = new GridLayout( 2, false );
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			layout.marginBottom = 19;
			subContainer.setLayout( layout );
			GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
			layoutData.horizontalSpan = 2;
			subContainer.setLayoutData( layoutData );

			Label l = new Label( subContainer, SWT.NONE );
			l.setImage( this.contractImage );

			l = new Label( subContainer, SWT.NONE );
			l.setText( "Select the service contrat to use:" );
			l.setToolTipText( "The contract to use and configure" );

			Combo contractCombo = new Combo( subContainer, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
			contractCombo.add( "Writer contract: write incoming messages on a file system" );
			contractCombo.add( "Reader contract: read and list files from a given directory" );
			contractCombo.select( this.writeContract ? 0 : 1 );

			layoutData = new GridData( GridData.FILL_HORIZONTAL );
			layoutData.horizontalSpan = 2;
			contractCombo.setLayoutData( layoutData );
			contractCombo.addSelectionListener( new SelectionListener() {
				public void widgetSelected( SelectionEvent e ) {
					FTComponentPage.this.writeContract = ((Combo) e.widget).getSelectionIndex() == 0;
					updateSubWidgets( container );
					validate();
				}

				public void widgetDefaultSelected( SelectionEvent e ) {
					widgetSelected( e );
				}
			});

			// Add the default widgets
			updateSubWidgets( container );
		}

		// CONSUME mode
		else {
			// Directories first
			String[] labels = { "Read Directory:", "Backup Directory:" };
			String[] tooltips = { "The directory to read", "The directory into which read files are moved (the temporary directory by default)" };
			final Text[] texts = new Text[ 2 ];

			for( int i=0; i<labels.length; i++ ) {
				Label l = new Label( container, SWT.NONE );
				l.setText( labels[ i ]);
				l.setToolTipText( tooltips[ i ]);

				texts[ i ] = createFileBrowser( container );
				final int cpt = i;
				texts[ i ].addModifyListener( new ModifyListener() {
					public void modifyText( ModifyEvent e ) {
						setValue( cpt, ((Text) e.widget).getText().trim());
						validate();
					}
				});
			}

			// Other details
			Label l = new Label( container, SWT.NONE );
			l.setText( "Transfer Mode:" );
			l.setToolTipText( "The way files are sent into Petals ESB" );

			final ComboViewer viewer = new ComboViewer( container, SWT.SINGLE | SWT.BORDER );
			viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			viewer.setContentProvider( new ArrayContentProvider());
			viewer.setLabelProvider( new LabelProvider());
			viewer.setInput( TransferMode.values());
			viewer.setSelection( new StructuredSelection( this.transferMode ));

			viewer.addSelectionChangedListener( new ISelectionChangedListener() {
				public void selectionChanged( SelectionChangedEvent event ) {
					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
					FTComponentPage.this.transferMode = (TransferMode) o;
				}
			});

			l = new Label( container, SWT.NONE );
			l.setText( "File Pattern:" );
			l.setToolTipText( "The file name pattern to filter the resources to process" );

			Text text = new Text( container, SWT.SINGLE | SWT.BORDER );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			text.setText( "*" );
			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FTComponentPage.this.filePattern = ((Text) e.widget).getText().trim();
					validate();
				}
			});

			l = new Label( container, SWT.NONE );
			l.setText( "Polling Period:" );
			l.setToolTipText( "The time between each polling" );

			final Spinner pollingSpinner = new Spinner( container, SWT.BORDER );
			pollingSpinner.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			pollingSpinner.setMaximum( Integer.MAX_VALUE );
			pollingSpinner.setMinimum( 0 );
			pollingSpinner.setIncrement( 100 );
			pollingSpinner.setPageIncrement( 1000 );
			pollingSpinner.setDigits( 0 );
			pollingSpinner.setSelection( this.pollingPeriod );
			pollingSpinner.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FTComponentPage.this.pollingPeriod = pollingSpinner.getSelection();
					validate();
				}
			});
		}

		// Complete the page
		validate();
		String msg = getErrorMessage();
		if( msg != null ) {
			setErrorMessage( null );
			setMessage( msg, IMessageProvider.INFORMATION );
		}

		setControl( container );
	}


	/**
	 * Creates a XML element.
	 * @param name the element name (with the prefix)
	 * @param nillable true if the element can be null, false otherwise
	 * @param optional true if optional, false otherwise
	 * @param value the element's value
	 * @return a XML element
	 */
	private XmlElement createXmlElement( String name, boolean nillable, boolean optional, String value ) {

		XmlElement elt = new XmlElement();
		elt.setName( name );
		elt.setValue( value );
		elt.setNillable( nillable );
		elt.setOptional( optional );

		return elt;
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
		if( this.writeContract ) {
			Label l = new Label( container, SWT.NONE );
			l.setText( "Write Directory:" );
			l.setToolTipText( "The directory in which the message content will be written" );

			Text text = createFileBrowser( container );
			if( this.writeDirectory != null )
				text.setText( this.writeDirectory );

			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FTComponentPage.this.writeDirectory = ((Text) e.widget).getText().trim();
					validate();
				}
			});

			l = new Label( container, SWT.NONE );
			l.setText( "Write Mode:" );
			l.setToolTipText( "What part(s) of the message should be written" );

			final ComboViewer viewer = new ComboViewer( container, SWT.SINGLE | SWT.BORDER );
			viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			viewer.setContentProvider( new ArrayContentProvider());
			viewer.setLabelProvider( new LabelProvider());
			viewer.setInput( CopyMode.values());
			viewer.setSelection( new StructuredSelection( this.copyMode ));

			viewer.addSelectionChangedListener( new ISelectionChangedListener() {
				public void selectionChanged( SelectionChangedEvent event ) {
					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
					FTComponentPage.this.copyMode = (CopyMode) o;
				}
			});

			l = new Label( container, SWT.NONE );
			l.setText( "File Name:" );
			l.setToolTipText( "The base name of the file to write (will be appended the system date)" );

			text = new Text( container, SWT.SINGLE | SWT.BORDER );
			if( this.filePattern != null )
				text.setText( this.filePattern );

			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			text.addModifyListener( new ModifyListener() {
				public void modifyText( ModifyEvent e ) {
					FTComponentPage.this.filePattern = ((Text) e.widget).getText().trim();
					validate();
				}
			});
		}

		// "Get files" mode then
		else {
			String[] labels = { "Read Directory:", "Backup Directory:" };
			String[] tooltips = { "The directory to read", "The directory into which read files are moved (the temporary directory by default)" };
			final Text[] texts = new Text[ 2 ];

			for( int i=0; i<labels.length; i++ ) {
				Label l = new Label( container, SWT.NONE );
				l.setText( labels[ i ]);
				l.setToolTipText( tooltips[ i ]);

				texts[ i ] = createFileBrowser( container );
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
	 * Creates a text with a browse button to select a directory on the disk.
	 * @param container the parent
	 * @return the created text
	 */
	private Text createFileBrowser( Composite container ) {

		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text text = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = new Button( subContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				DirectoryDialog dlg = new DirectoryDialog( getShell());
				dlg.setMessage( "Select the directory to read." );
				dlg.setText( "Directory Selection" );

				String value = text.getText().trim();
				if( ! StringUtils.isEmpty( value ))
					dlg.setFilterPath( value );

				value = dlg.open();
				if( value != null )
					text.setText( value );
			}
		});

		return text;
	}
}
