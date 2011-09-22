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

package com.ebmwebsourcing.petals.services.su.editor.blocks;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.ide.IDE;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUpdater;
import com.ebmwebsourcing.petals.common.internal.provisional.wizards.WsdlImportWizard;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.su.ui.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager.WsdlParsingListener;
import com.ebmwebsourcing.petals.services.su.utils.DomEditorHelper;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GeneralProvidesDetails
extends GeneralAbstractDetails
implements WsdlParsingListener {

	private Button generateEdptButton;
	private Text wsdlText;
	private final WsdlParsingJobManager wsdlParsingJob = new WsdlParsingJobManager ();
	private String edptBackup;


	/**
	 * Constructor.
	 * @param page
	 */
	public GeneralProvidesDetails( AbstractServicesFormPage page ) {
		super( page );
		this.sectionTitle = "Provides properties";
		this.sectionDescription = "Edit the interface, service and end-point names of the provided service.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentAfter(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addContentAfter( Composite parent ) {

		FormToolkit toolkit = this.managedForm.getToolkit();

		// Baaaad!
		toolkit.createLabel( parent, "" );

		// Add the button and its label
		this.generateEdptButton = toolkit.createButton( parent, "", SWT.CHECK );
		this.generateEdptButton.setText( "Generate the end-point at deployment time" );
		this.generateEdptButton.setToolTipText( "The end-point name will be generated by Petals on deployment" );
		this.generateEdptButton.addSelectionListener( new SelectionListener() {

			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				boolean selected = GeneralProvidesDetails.this.generateEdptButton.getSelection();
				GeneralProvidesDetails.this.edptNameText.setEnabled( ! selected );
				GeneralProvidesDetails.this.edptNameLabel.setEnabled( ! selected );

				if( GeneralProvidesDetails.this.reportChanges ) {
					if( selected )
						GeneralProvidesDetails.this.edptBackup = GeneralProvidesDetails.this.edptNameText.getText().trim();
					else if( GeneralProvidesDetails.this.edptBackup == null
								|| GeneralProvidesDetails.this.edptBackup.trim().length() == 0 )
						GeneralProvidesDetails.this.edptBackup = GeneralProvidesDetails.this.srvNameText.getText() + "Endpoint";

					String edptValue = selected ? PetalsConstants.AUTO_GENERATE : GeneralProvidesDetails.this.edptBackup;
					DomUtils.addOrSetAttribute( GeneralProvidesDetails.this.selectedElement, "endpoint-name", edptValue );
					GeneralProvidesDetails.this.page.updatePage();
				}
			}
		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentBefore(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addContentBefore( Composite parent ) {

		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		FormToolkit toolkit = this.managedForm.getToolkit();

		// The label browser
		Label wsdlLabel = toolkit.createLabel( parent, "WSDL location:" );
		wsdlLabel.setToolTipText( "The relative path of the WSDL in the service unit or an URL" );
		wsdlLabel.setForeground( blueFont );

		Composite container = toolkit.createComposite( parent );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.wsdlText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='wsdl']", this.wsdlText );

		Button browseButton = toolkit.createButton( container, "Browse...", SWT.PUSH );
		browseButton.setToolTipText( "Select a WSDL located in the project resources" );
		browseButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				IProject project = GeneralProvidesDetails.this.editedFile.getProject();
				IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

				// FIXME: replace this dialog by one that only shows WSDLs?
				ElementTreeSelectionDialog dlg = ResourceUtils.createWorkspaceFileSelectionDialog(
							GeneralProvidesDetails.this.wsdlText.getShell(), resourceFolder, "wsdl" );

				dlg.setTitle( "WSDL Selection" );
				dlg.setMessage( "Select a WSDL file located in the project's resource directory." );

				File file = JbiXmlUtils.getWsdlFile(
							GeneralProvidesDetails.this.editedFile,
							GeneralProvidesDetails.this.wsdlText.getText());

				if( file != null ) {
					IFile wsdlFile = ResourceUtils.getIFile( file );
					if( wsdlFile != null )
						dlg.setInitialElementSelections( Arrays.asList( wsdlFile ));
				}

				if( dlg.open() == Window.OK ) {
					IFile selectedFile = (IFile) dlg.getResult()[ 0 ];
					int rfsc = resourceFolder.getFullPath().segmentCount();
					String wsdlValue = selectedFile.getFullPath().removeFirstSegments( rfsc ).toString();
					GeneralProvidesDetails.this.wsdlText.setText( wsdlValue );
				}
			}
		});


		// The helpers
		toolkit.createLabel( parent, "" );

		Section section = toolkit.createSection( parent, ExpandableComposite.TWISTIE );
		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.clientVerticalSpacing = 10;
		section.setText( "Helpers" );
		section.setForeground( blueFont );

		container = toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginBottom = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setClient( container );

		final Hyperlink importLink = toolkit.createHyperlink(
					container, "Import this WSDL in the project", SWT.NONE );
		importLink.setToolTipText( "Import this WSDL in the project" );
		importLink.setEnabled( false );
		importLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				URI wsdlUri = null;
				File wsdlFile = JbiXmlUtils.getWsdlFile(
							GeneralProvidesDetails.this.editedFile,
							GeneralProvidesDetails.this.wsdlText.getText());

				if( wsdlFile != null )
					wsdlUri = wsdlFile.toURI();
				else
					wsdlUri = UriUtils.urlToUri( GeneralProvidesDetails.this.wsdlText.getText());

				if( wsdlUri != null ) {
					WsdlImportWizard wiz = new WsdlImportWizard();
					IFolder resFolder = GeneralProvidesDetails.this.editedFile.getProject().getFolder(
								PetalsConstants.LOC_RES_FOLDER );

					wiz.setInitialContainer( resFolder );
					wiz.setInitialWsdlUri( wsdlUri.toString());

					IWorkbench workbench = PlatformUI.getWorkbench();
					wiz.init( workbench, null );

					WizardDialog dlg = new WizardDialog( workbench.getActiveWorkbenchWindow().getShell(), wiz );
					if( dlg.open() == Window.OK ) {
						File importedFile = wiz.getWsdlFileAfterImport();
						String value = IoUtils.getBasicRelativePath( resFolder.getLocation().toFile(), importedFile );
						GeneralProvidesDetails.this.wsdlText.setText( value );
					}
				}
			}
		});

		final Hyperlink selectServiceLink = toolkit.createHyperlink(
					container, "Select a service in the WSDL to fill-in the properties below", SWT.NONE );

		selectServiceLink.setToolTipText( "A WSDL may describe several services instead of just one" );
		selectServiceLink.setEnabled( false );
		selectServiceLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				PCStyledLabelProvider lp = new PCStyledLabelProvider( GeneralProvidesDetails.this.wsdlText );
				StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog(
							GeneralProvidesDetails.this.wsdlText.getShell(), lp );

				dlg.setFilter( "*" );
				dlg.setSize( 120, 20 );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Selection" );
				dlg.setMessage( "Select the service to expose inside Petals." );

				List<JbiBasicBean> wsdlBeans = parseWsdl();
				dlg.setElements( wsdlBeans.toArray());
				if( dlg.open() == Window.OK ) {

					JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
					String srvName = bean.getServiceName();
					String srvNs = bean.getServiceNs();
					GeneralProvidesDetails.this.srvNameText.setText( srvName != null ? srvName : "" );
					GeneralProvidesDetails.this.srvNsText.setText( srvNs != null ? srvNs : "" );

					String itfName = bean.getInterfaceName();
					String itfNs = bean.getInterfaceNs();
					GeneralProvidesDetails.this.itfNameText.setText( itfName != null ? itfName : "" );
					GeneralProvidesDetails.this.itfNsText.setText( itfNs != null ? itfNs : "" );

					String edptName = bean.getEndpointName() != null ? bean.getEndpointName() : "";
					if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( edptName )) {
						GeneralProvidesDetails.this.edptNameLabel.setEnabled( false );
						GeneralProvidesDetails.this.edptNameText.setEnabled( false );
						GeneralProvidesDetails.this.generateEdptButton.setSelection( true );
						GeneralProvidesDetails.this.edptNameText.setText( "" );
					}
					else {
						GeneralProvidesDetails.this.edptNameLabel.setEnabled( true );
						GeneralProvidesDetails.this.edptNameText.setEnabled( true );
						GeneralProvidesDetails.this.generateEdptButton.setSelection( false );
						GeneralProvidesDetails.this.edptNameText.setText( edptName );
					}
				}

				lp.dispose();
			}
		});

		final Hyperlink openLink = toolkit.createHyperlink( container, "Open in the WSDL editor", SWT.NONE );
		openLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		openLink.setEnabled( false );
		openLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				// This link is enabled only if the WSDL references an existing file
				File file = JbiXmlUtils.getWsdlFile(
							GeneralProvidesDetails.this.editedFile,
							GeneralProvidesDetails.this.wsdlText.getText());

				IFile f = ResourceUtils.getIFile( file );
				if( f != null ) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, f, true );

					} catch( PartInitException exception ) {
						PetalsServicesPlugin.log( exception, IStatus.ERROR );
					}
				}
			}
		});

		final Hyperlink updateLink = toolkit.createHyperlink(
					container, "Update the service end-point in the WSDL", SWT.NONE );
		updateLink.setToolTipText( "Update the WSDL so that it declared end-point for this service is the one defined below" );
		updateLink.setEnabled( false );
		updateLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				// Enabled only when the WSDL points to an existing file
				File f = JbiXmlUtils.getWsdlFile(
							GeneralProvidesDetails.this.editedFile,
							GeneralProvidesDetails.this.wsdlText.getText());

				QName serviceName = new QName(
							GeneralProvidesDetails.this.srvNsText.getText(),
							GeneralProvidesDetails.this.srvNameText.getText());

				QName interfaceName = new QName(
						GeneralProvidesDetails.this.itfNsText.getText(),
						GeneralProvidesDetails.this.itfNameText.getText());

				String edptName = GeneralProvidesDetails.this.generateEdptButton.getSelection()
				? PetalsConstants.AUTO_GENERATE : GeneralProvidesDetails.this.edptNameText.getText();

				if( ! WsdlUpdater.getInstance().update( f, serviceName, interfaceName, edptName )) {
					MessageDialog.openError(
								GeneralProvidesDetails.this.wsdlText.getShell(),
								"End-point Update Failure",
								"The end-point could not be updated in the WSDL." );
				}
				else {
					// Force the validation of the file
					try {
						GeneralProvidesDetails.this.editedFile.touch( null );

					} catch( CoreException e1 ) {
						PetalsServicesPlugin.log( e1, IStatus.WARNING );
					}
				}
			}
		});


		// WSDL text listener
		this.wsdlText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {

				// Change the value
				if( GeneralProvidesDetails.this.reportChanges ) {

					// Remember the caret position
					int offset = GeneralProvidesDetails.this.wsdlText.getCaretPosition();
					//

					GeneralProvidesDetails.this.page.startTransaction();
					DomEditorHelper.setWsdl(
								GeneralProvidesDetails.this.selectedElement,
								GeneralProvidesDetails.this.wsdlText.getText());
					GeneralProvidesDetails.this.page.stopTransaction();

					// Restore the caret position
					GeneralProvidesDetails.this.wsdlText.setSelection( offset );
					//
				}

				// Update the hyper links
				URI uri = null;
				File wsdlFile = JbiXmlUtils.getWsdlFile(
							GeneralProvidesDetails.this.editedFile,
							GeneralProvidesDetails.this.wsdlText.getText());

				if( wsdlFile != null ) {
					uri = wsdlFile.toURI();
					IFile f = ResourceUtils.getIFile( wsdlFile );

					openLink.setEnabled( f != null );
					selectServiceLink.setEnabled( true );
					updateLink.setEnabled( true );

					IPath resFolderPath = GeneralProvidesDetails.this.editedFile.getProject().getFolder(
								PetalsConstants.LOC_RES_FOLDER ).getLocation();

					boolean isInResFolder = resFolderPath.isPrefixOf( new Path( wsdlFile.getAbsolutePath()));
					importLink.setEnabled( ! isInResFolder );
				}
				else {
					if((uri = getWsdlUri()) != null ) {
						openLink.setEnabled( false );
						selectServiceLink.setEnabled( true );
						importLink.setEnabled( true );
						updateLink.setEnabled( false );
					}
					else {
						openLink.setEnabled( false );
						selectServiceLink.setEnabled( false );
						importLink.setEnabled( false );
						updateLink.setEnabled( false );
					}
				}

				// Parse the WSDL
				if( uri != null
							&& GeneralProvidesDetails.this.reportChanges )
					GeneralProvidesDetails.this.wsdlParsingJob.setWsdlUri( uri );
			}
		});


		// Register itself as a listener
		this.wsdlParsingJob.addWsdlParsingListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #dispose()
	 */
	@Override
	public void dispose() {
		this.wsdlParsingJob.removeWsdlParsingListener( this );
		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #refreshCustomWidgets()
	 */
	@Override
	public void refreshCustomWidgets() {

		if( this.selectedElement == null
					|| this.itfNsText == null 	// page already loaded?
					|| this.itfNsText.isDisposed())
			return;


		// End-point
		String name = this.selectedElement.getAttribute( "endpoint-name" );
		if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( name )) {
			this.edptNameLabel.setEnabled( false );
			this.edptNameText.setEnabled( false );
			this.generateEdptButton.setSelection( true );
			this.edptNameText.setText( name );
		}
		else {
			this.edptNameLabel.setEnabled( true );
			this.edptNameText.setEnabled( true );
			this.edptNameText.setText( name );
			this.generateEdptButton.setSelection( false );
		}


		// WSDL
		Element wsdlElt = DomUtils.getChildElement( this.selectedElement, "wsdl" );
		String wsdl = wsdlElt != null ? StructuredModelHelper.getElementSimpleValue( wsdlElt ) : null;
		this.wsdlText.setText( wsdl != null ? wsdl : "" );
	}


	/**
	 * @return the WSDL URI if the WSDL value is a valid URI, null otherwise
	 */
	private URI getWsdlUri() {

		URI uri = null;
		try {
			if( this.wsdlText.getText().trim().length() > 0 )
				uri = UriUtils.urlToUri( this.wsdlText.getText());
		} catch( Exception e ) {
			// nothing
		}

		return uri;
	}


	/**
	 * Updates the GUI when the WSDL is parsed.
	 */
	public void notifyWsdlParsingDone() {

		List<JbiBasicBean> wsdlBeans = parseWsdl();
		refreshWidgetsFromWsdl( wsdlBeans );
	}


	/**
	 * Forces the WSDL parsing.
	 */
	private List<JbiBasicBean> parseWsdl() {

		List<JbiBasicBean> wsdlBeans = new ArrayList<JbiBasicBean> ();
		if( this.wsdlParsingJob.cancel()) {

			// Get the WSDL URI
			File f = JbiXmlUtils.getWsdlFile( this.editedFile, this.wsdlText.getText());
			URI uri;
			if( f == null )
				uri = getWsdlUri();
			else
				uri = f.toURI();

			// Parse and update...
			if( uri != null ) {
				List<JbiBasicBean> _beans = null;
				try {
					_beans = WsdlParser.getInstance().parse( uri.toString());
				} catch( IllegalArgumentException e ) {
					// nothing
				}

				if( _beans != null )
					wsdlBeans.addAll( _beans );
			}

			// ... or report an error
			else {
				MessageDialog.openError(
							this.wsdlText.getShell(),
							"WSDL Parsing Failure",
				"The WSDL parsing failed: no service description was found in the referenced WSDL file." );
			}
		}

		return wsdlBeans;
	}


	/**
	 * Refreshes the UI with the values resulting from the WSDL parsing.
	 */
	private void refreshWidgetsFromWsdl( List<JbiBasicBean> wsdlBeans ) {

		this.reportChanges = false;
		if( wsdlBeans.size() > 0 ) {
			JbiBasicBean firstBean = wsdlBeans.get( 0 );

			String srvName = firstBean.getServiceName() != null ? firstBean.getServiceName() : "";
			this.srvNameText.setText( srvName );

			String srvNs = firstBean.getServiceNs() != null ? firstBean.getServiceNs() : "";
			this.srvNsText.setText( srvNs );

			String itfName = firstBean.getInterfaceName() != null ? firstBean.getInterfaceName() : "";
			this.itfNameText.setText( itfName );

			String itfNs = firstBean.getInterfaceNs() != null ? firstBean.getInterfaceNs() : "";
			this.itfNsText.setText( itfNs );

			String edptName = firstBean.getEndpointName() != null ? firstBean.getEndpointName() : "";
			if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( edptName )) {
				this.edptNameLabel.setEnabled( false );
				this.edptNameText.setEnabled( false );
				this.generateEdptButton.setSelection( true );
				this.edptNameText.setText( "" );
			}
			else {
				this.edptNameLabel.setEnabled( true );
				this.edptNameText.setEnabled( true );
				this.generateEdptButton.setSelection( false );
				this.edptNameText.setText( edptName );
			}
		}

		this.reportChanges = true;
	}


	/**
	 * Edits an attribute of {@link #selectedElement}.
	 * @param widget the edition widget, that determines the attribute to edit
	 */
	@Override
	protected void editAttribute( Text widget ) {

		// Remember the caret position
		int offset = widget.getCaretPosition();
		//

		String value = widget.getText().trim();
		String prefix = "";
		String attributeName = null;
		if( widget == this.itfNameText ) {
			attributeName = "interface-name";
			prefix = "itfNs:";

			if( this.selectedElement.getAttribute( ITF_NS_DECL ) == null ) {
				// Propagate the name space
				String itfValue = this.selectedElement.getAttribute( "interface-name" );
				String ns = NamespaceUtils.getNamespaceUri( itfValue, this.selectedElement );
				if( ns == null || ns.trim().length() == 0 )
					ns = "http://";

				DomUtils.addOrSetAttribute( this.selectedElement, ITF_NS_DECL, ns );
			}
		}
		else if( widget == this.srvNameText ) {
			attributeName = "service-name";
			prefix = "srvNs:";

			if( this.selectedElement.getAttribute( SRV_NS_DECL ) == null ) {
				// Propagate the name space
				String srvValue = this.selectedElement.getAttribute( "service-name" );
				String ns = NamespaceUtils.getNamespaceUri( srvValue, this.selectedElement );
				if( ns == null || ns.trim().length() == 0 )
					ns = "http://";

				DomUtils.addOrSetAttribute( this.selectedElement, SRV_NS_DECL, ns );
			}
		}
		else if( widget == this.itfNsText ) {
			attributeName = ITF_NS_DECL;

			// Force the element to use this name space
			String itfValue = this.selectedElement.getAttribute( "interface-name" );
			String lp = NamespaceUtils.removeNamespaceElements( itfValue );
			if( lp == null )
				lp = "";
			DomUtils.addOrSetAttribute( this.selectedElement, "interface-name", "itfNs:" + lp );
		}
		else if( widget == this.srvNsText ) {
			attributeName = SRV_NS_DECL;

			// Force the element to use this name space
			String srvValue = this.selectedElement.getAttribute( "service-name" );
			String lp = NamespaceUtils.removeNamespaceElements( srvValue );
			if( lp == null )
				lp = "";
			DomUtils.addOrSetAttribute( this.selectedElement, "service-name", "srvNs:" + lp );
		}
		else if( widget == this.edptNameText ) {
			attributeName = "endpoint-name";
		}

		// Do not define empty name spaces
		String attributeValue = prefix + value;
		DomUtils.addOrSetAttribute( this.selectedElement, attributeName, attributeValue );
		this.page.updatePage();

		// Restore the caret position
		widget.setSelection( offset );
		//
	}
}
