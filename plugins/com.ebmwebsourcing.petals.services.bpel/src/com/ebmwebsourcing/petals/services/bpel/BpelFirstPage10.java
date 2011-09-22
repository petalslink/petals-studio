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

package com.ebmwebsourcing.petals.services.bpel;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.ow2.easywsdl.wsdl.WSDLFactory;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.InterfaceType;
import org.ow2.easywsdl.wsdl.api.WSDLException;
import org.ow2.easywsdl.wsdl.api.WSDLReader;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;
import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.AbstractWizardListener;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.FileUriBrowser;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * The BPEL specific page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelFirstPage10 extends AbstractSuPage {

	private String bpelUrl, bpelName;
	private BpelCreationMode creationMode;
	private URI wsdlLocation;
	private Description wsdlDescription;
	private QName selectedInterface;

	private FileUriBrowser bpelBrowser, wsdlBrowser;
	private ComboViewer interfaceComboViewer;
	private Label interfaceLabel;

	public enum BpelCreationMode {
		importBpel, generateBpel, createAll;
	}


	/**
	 * Constructor with no parameter.
	 * <p>
	 * Required to be instantiated from the plug-in registry.
	 * </p>
	 */
	public BpelFirstPage10() {
		super( "BpelFirstPage10" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.provide.se.SeProvideCdkPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		// Indicate whether the BPEL should be generated
		suBean.customObjects.put( "generateBpel", this.creationMode );

		// Set the BPEL element value
		XmlElement bpelElement = new XmlElement();
		bpelElement.setName( "bpel-engine:bpel" );
		if( this.creationMode == BpelCreationMode.createAll ) {
			bpelElement.setValue( this.bpelName );
		}
		else if( this.creationMode == BpelCreationMode.generateBpel ) {
			bpelElement.setValue( this.bpelName );
			suBean.setWsdlUrl( this.wsdlLocation.toString());

			// Store the description and the interface name
			suBean.customObjects.put( "wsdlDescription", this.wsdlDescription );
			suBean.customObjects.put( "wsdlName", this.selectedInterface );
		}
		else {
			try {
				FileImportManager mng = FileImportManager.getFileImportManager();
				URL url = new URL( this.bpelUrl );
				bpelElement.setValue( url.toString());
				mng.registerXmlFileElement( bpelElement, url.toString(), null );

				Set<String> uris = new HashSet<String> ();
				for( URI uri : PetalsBpelModules.getWsdlImportUris( url, true, true ).values())
					uris.add( uri.toString());

				for( URI uri : PetalsBpelModules.getWsdlImportUris( url, false, false ).values())
					uris.add( uri.toString());

				for( String uri : uris ) {
					mng.registerXmlFileElement( null, uri, null );
				}

			} catch( Exception e ) {
				PetalsBpelPlugin.log( e, IStatus.ERROR );
			}
		}

		suBean.specificElements.add( bpelElement );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Import an existing WSDL
		if( this.creationMode == BpelCreationMode.generateBpel ) {

			// Validate the location
			if( this.wsdlBrowser.getValue().trim().length() == 0 ) {
				updateStatus( "You must provide a valid WSDL URL." );
				return false;
			}

			try {
				this.wsdlLocation = UriUtils.urlToUri( BpelFirstPage10.this.wsdlBrowser.getValue());
				this.bpelName = new Path( this.wsdlLocation.getPath()).removeFileExtension().lastSegment();
				if( this.bpelName.endsWith( "?wsdl" ))
					this.bpelName = "baseProcess.bpel";
				else
					this.bpelName += ".bpel";

				String wsdlName = new Path( this.bpelName ).removeFileExtension().toString() + ".wsdl";

				// Update the other fields in the JBI page
				getWizard().getDialogSettings().put( SettingConstants.PROVIDED_WSDL_URI, this.wsdlLocation.toString());
				getWizard().getDialogSettings().put( SettingConstants.WSDL_HIDDEN_VALUE, wsdlName );

			} catch( Exception e1 ) {
				updateStatus( "You must provide a valid WSDL URL." );
				return false;
			}

			// Get the interface to use
			this.selectedInterface = null;
			if( ! this.interfaceComboViewer.getSelection().isEmpty()) {
				Object o = ((IStructuredSelection) this.interfaceComboViewer.getSelection()).getFirstElement();
				this.selectedInterface = ((InterfaceType) o).getQName();
			}
		}

		// Import an existing BPEL
		else if( this.creationMode == BpelCreationMode.importBpel ) {

			// Validate the location
			if( this.bpelBrowser.getValue().trim().length() == 0 ) {
				updateStatus( "You must provide a valid BPEL URL." );
				return false;
			}

			// Get the process' WSDL
			getWizard().getDialogSettings().put( SettingConstants.WSDL_HIDDEN_VALUE, "" );
			IRunnableWithProgress wsdlParsingOperation = new IRunnableWithProgress() {
				public void run( IProgressMonitor monitor ) throws InvocationTargetException {
					try {
						monitor.beginTask( "Getting imports from the BPEL...", IProgressMonitor.UNKNOWN );
						URL url = new URL( BpelFirstPage10.this.bpelBrowser.getValue());
						BpelFirstPage10.this.bpelName = new Path( url.getPath()).lastSegment();
						List<URI> wsdlUris = PetalsBpelModules.getProcessWsdl( url );
						if( wsdlUris.size() > 0 ) {
							String wsdlUrl = wsdlUris.get( 0 ).toString();
							getWizard().getDialogSettings().put( SettingConstants.PROVIDED_WSDL_URI, wsdlUrl );
						}

					} catch( MalformedURLException e ) {
						getWizard().getDialogSettings().put( SettingConstants.PROVIDED_WSDL_URI, "" );
						PetalsBpelPlugin.log( e, IStatus.ERROR );

					} finally {
						monitor.done();
					}
				}
			};

			try {
				getContainer().run( true, false, wsdlParsingOperation );

			} catch( Exception e ) {
				PetalsBpelPlugin.log( e, IStatus.ERROR );
			}
		}

		// Generate everything from zero
		else {
			this.bpelName = "NewProcess.bpel";
			getWizard().getDialogSettings().put( SettingConstants.PROVIDED_WSDL_URI, "" );
			getWizard().getDialogSettings().put( SettingConstants.WSDL_HIDDEN_VALUE, "NewProcessDefinition.wsdl" );
		}

		// Save values
		this.bpelUrl = this.bpelBrowser.getValue();
		String name = this.bpelName.substring( 0, this.bpelName.length() - 5 );
		getWizard().getDialogSettings().put( SettingConstants.SRV_NAME_VALUE, name );

		updateStatus( null );
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout();
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// BPEL
		Group bpelGroup = new Group( container, SWT.NONE );
		bpelGroup.setText( "BPEL file" );
		bpelGroup.setLayout( new GridLayout());
		bpelGroup.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button createAllButton = new Button( bpelGroup, SWT.RADIO );
		createAllButton.setText( "Create a new BPEL process" );

		final Button importBpelButton = new Button( bpelGroup, SWT.RADIO );
		importBpelButton.setText( "Import an existing BPEL process" );

		this.bpelBrowser = new FileUriBrowser( "", bpelGroup, "BPEL URL", "", "", false, true, true );
		this.bpelBrowser.setFilterExtensions( new String[] { "*.bpel" });
		this.bpelBrowser.setFilterNames( new String[] { "BPEL (*.bpel)" });
		this.bpelBrowser.setEmptyValueAllowed( false );

		final Button generateBpelButton = new Button( bpelGroup, SWT.RADIO );
		generateBpelButton.setText( "Generate a BPEL skeleton from a WSDL file" );

		this.wsdlBrowser = new FileUriBrowser( "", bpelGroup, "WSDL URL", "", "", false, true, true );
		this.wsdlBrowser.setFilterExtensions( new String[] { "*.wsdl" });
		this.wsdlBrowser.setFilterNames( new String[] { "WSDL (*.wsdl)" });
		this.wsdlBrowser.setEmptyValueAllowed( false );

		this.interfaceLabel = new Label( bpelGroup, SWT.NONE );
		this.interfaceLabel.setText( "Select the Port Type / Interface to use in the generation:" );
		this.interfaceLabel.setToolTipText( "The WSDL Port Type / Interface used to generate the BPEL process" );

		this.interfaceComboViewer = new ComboViewer( bpelGroup, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		this.interfaceComboViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.interfaceComboViewer.setContentProvider( new ArrayContentProvider());
		this.interfaceComboViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {

				String result = "";
				if( element instanceof InterfaceType ) {
					StringBuffer sb = new StringBuffer();
					sb.append(((InterfaceType) element).getQName().getLocalPart() + " - " );
					sb.append(((InterfaceType) element).getQName().getNamespaceURI());
					result = sb.toString();
				}

				return result;
			}
		});


		// Listeners & co.
		createAllButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				if( createAllButton.getSelection()) {
					BpelFirstPage10.this.bpelBrowser.setEnabled( false );
					BpelFirstPage10.this.wsdlBrowser.setEnabled( false );
					BpelFirstPage10.this.interfaceLabel.setEnabled( false );
					BpelFirstPage10.this.interfaceComboViewer.getCombo().setEnabled( false );
					BpelFirstPage10.this.creationMode = BpelCreationMode.createAll;
					validate();
				}
			}
		});

		importBpelButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				if( importBpelButton.getSelection()) {
					BpelFirstPage10.this.bpelBrowser.setEnabled( true );
					BpelFirstPage10.this.wsdlBrowser.setEnabled( false );
					BpelFirstPage10.this.interfaceLabel.setEnabled( false );
					BpelFirstPage10.this.interfaceComboViewer.getCombo().setEnabled( false );
					BpelFirstPage10.this.creationMode = BpelCreationMode.importBpel;
					validate();
				}
			}
		});

		generateBpelButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				if( generateBpelButton.getSelection()) {
					BpelFirstPage10.this.bpelBrowser.setEnabled( false );
					BpelFirstPage10.this.wsdlBrowser.setEnabled( true );
					BpelFirstPage10.this.interfaceLabel.setEnabled( true );
					BpelFirstPage10.this.interfaceComboViewer.getCombo().setEnabled( true );
					BpelFirstPage10.this.creationMode = BpelCreationMode.generateBpel;
					validate();
				}
			}
		});

		this.bpelBrowser.addListener( new AbstractWizardListener() {
			public void valueHasChanged() {
				validate();
			}
		});

		WSDLReader reader = null;
		try {
			reader = WSDLFactory.newInstance().newWSDLReader();
		} catch( WSDLException e1 ) {
			e1.printStackTrace();
		}

		final WSDLReader _reader = reader;
		this.wsdlBrowser.addListener( new AbstractWizardListener() {

			@Override
			public void valueHasChanged() {

				try {
					URI uri = UriUtils.urlToUri( BpelFirstPage10.this.wsdlBrowser.getValue());
					if( _reader != null ) {
						BpelFirstPage10.this.wsdlDescription = _reader.read( uri.toURL());
						List<InterfaceType> interfaces = BpelFirstPage10.this.wsdlDescription.getInterfaces();
						BpelFirstPage10.this.interfaceComboViewer.setInput( interfaces );
						BpelFirstPage10.this.interfaceComboViewer.refresh();

						if( interfaces.size() > 0 ) {
							Object o = interfaces.get( 0 );
							BpelFirstPage10.this.interfaceComboViewer.setSelection( new StructuredSelection( o ));
						}
					}

				} catch( Exception e ) {
					e.printStackTrace();

				} finally {
					validate();
				}
			}
		});

		this.interfaceComboViewer.getCombo().addSelectionListener( new SelectionListener() {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				validate();
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {
				validate();
			}
		});

		setPageComplete( true );
		setControl( container );
		createAllButton.setSelection( true );
		createAllButton.notifyListeners( SWT.Selection, new Event());
	}
}
