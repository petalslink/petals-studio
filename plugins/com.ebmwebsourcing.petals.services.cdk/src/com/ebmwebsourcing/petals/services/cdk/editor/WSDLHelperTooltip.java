/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.cdk.CdkPlugin;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.su.editor.su.EMFPCStyledLabelProvider;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class WSDLHelperTooltip {

	private final AtomicBoolean shown = new AtomicBoolean( false );
	private final WsdlParsingJobManager wsdlParsingJob;
	private final Shell shell;
	private final Control controlToFollow;

	private Hyperlink updateLink, importLink, selectServiceLink, openLink;
	private final FormToolkit toolkit;
	private final Provides service;
	private final ISharedEdition ise;


	/**
	 * Constructor.
	 * @param control
	 * @param tk
	 * @param service
	 * @param ise
	 * @param wsdlParsingJob
	 */
	public WSDLHelperTooltip( Control control, FormToolkit tk, Provides service, ISharedEdition ise, WsdlParsingJobManager wsdlParsingJob ) {
		this.shell = new Shell( control.getShell(), SWT.TOOL );
		this.toolkit = tk;
		this.ise = ise;
		this.service = service;
		this.wsdlParsingJob = wsdlParsingJob;
		this.controlToFollow = control;

		populateTooltip();
		this.shell.pack();
	}


	/**
	 * Shows the tool tip (with a small delay).
	 */
	public void show() {

		if( this.shown.get())
			return;

		this.shown.set( true );

		Point point = this.controlToFollow.toDisplay( 10, 10 );
		this.shell.setLocation( point );

		this.shell.getDisplay().asyncExec( new Runnable() {
			@Override
			public void run() {

				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					// nothing
				}

				updateEnablement();
				if( WSDLHelperTooltip.this.shown.get())
					WSDLHelperTooltip.this.shell.setVisible( true );
			}
		});
	}


	/**
	 * Hides the tool tip.
	 */
	public void hide() {
		if( ! this.shown.get())
			return;

		this.shown.set( false );
		this.shell.setVisible( false );
	}


	/**
	 * Populates the tool tip.
	 */
	private void populateTooltip() {

		// Deal with the SHELL content
		GridLayoutFactory.swtDefaults().applyTo( this.shell );
		this.shell.setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.shell.setBackground( this.shell.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		Form res = this.toolkit.createForm( this.shell );
		res.setText(Messages.wsdlTools);
		res.getBody().setLayout( new GridLayout());


		// Helper: import the WSDL in the project
		this.importLink = this.toolkit.createHyperlink(res.getBody(), "Import this WSDL in the project", SWT.NONE );
		final IFolder resFolder = WSDLHelperTooltip.this.ise.getEditedFile().getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );

		this.importLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				hide();
				try {
					URI wsdlUri = WSDLHelperTooltip.this.wsdlParsingJob.getWsdlUri();
					Map<String,File> map = new WsdlImportHelper().importWsdlOrXsdAndDependencies( resFolder.getLocation().toFile(), wsdlUri.toString());
					File importedFile = map.get( wsdlUri.toString());
					String value = IoUtils.getRelativeLocationToFile( resFolder.getLocation().toFile(), importedFile );
					SetCommand cmd = new SetCommand(
							WSDLHelperTooltip.this.ise.getEditingDomain(),
							WSDLHelperTooltip.this.service,
							Cdk5Package.Literals.CDK5_PROVIDES__WSDL,
							value );

					WSDLHelperTooltip.this.ise.getEditingDomain().getCommandStack().execute( cmd );

				} catch( Exception e1 ) {
					CdkPlugin.log( e1, IStatus.ERROR );
				}
			}
		});


		// Helper: select a service in the WSDL
		this.selectServiceLink = this.toolkit.createHyperlink( res.getBody(), "Select a service in the WSDL to fill-in the properties below", SWT.NONE );
		this.selectServiceLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				hide();
				EMFPCStyledLabelProvider lp = new EMFPCStyledLabelProvider(WSDLHelperTooltip.this.shell);
				StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog(WSDLHelperTooltip.this.shell, lp );

				dlg.setFilter( "*" );
				dlg.setSize( 120, 20 );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Selection" );
				dlg.setMessage( "Select the service to expose inside Petals." );

				dlg.setElements( WSDLHelperTooltip.this.wsdlParsingJob.getBeans().toArray());
				if( dlg.open() == Window.OK ) {
					CompoundCommand cc = new CompoundCommand();
					JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
					cc.append( new SetCommand(
							WSDLHelperTooltip.this.ise.getEditingDomain(),
							WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME,
							bean.getServiceName()));

					cc.append( new SetCommand(
							WSDLHelperTooltip.this.ise.getEditingDomain(), WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME,
							bean.getInterfaceName()));

					cc.append( new SetCommand(
							WSDLHelperTooltip.this.ise.getEditingDomain(), WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME,
							bean.getEndpointName()));

					WSDLHelperTooltip.this.ise.getEditingDomain().getCommandStack().execute( cc );
				}

				lp.dispose();
			}
		});


		// Helper: open in the WSDL editor
		this.openLink = this.toolkit.createHyperlink( res.getBody(), "Open in the WSDL editor", SWT.NONE );
		this.openLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		this.openLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();

				URI wsdlUri = WSDLHelperTooltip.this.wsdlParsingJob.getWsdlUri();
				File wsdlFile = IoUtils.convertToFile( wsdlUri );
				IFile f = ResourceUtils.getIFile( wsdlFile );
				if( f == null )
					return;

				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor( page, f, true );

				} catch( PartInitException exception ) {
					CdkPlugin.log( exception, IStatus.ERROR );
				}
			}
		});


		// Helper: update the end-point name in the WSDL
		this.updateLink = this.toolkit.createHyperlink(res.getBody(), "Update the service end-point in the WSDL", SWT.NONE );
		this.updateLink.setToolTipText( "Update the WSDL so that it declared end-point for this service is the one defined below" );
		this.updateLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				QName serviceName = WSDLHelperTooltip.this.service.getServiceName();
				String edptName = WSDLHelperTooltip.this.service.getEndpointName();
				URI wsdlUri = WSDLHelperTooltip.this.wsdlParsingJob.getWsdlUri();

				// Perform an update if, and only if, there is no ambiguity in the WSDL.
				// It is too complicated (and there are too many error cases) to keep the old end-point name value.
				// So, the user may have to modify the WSDL by hand in some situations.
				try {
					List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( wsdlUri );
					int candidates = 0;
					for( JbiBasicBean bean : beans ) {
						if( serviceName.equals( bean.getServiceName()))
							candidates ++;
					}

					if( candidates > 1 ) {
						MessageDialog.openInformation(
								WSDLHelperTooltip.this.shell, "Ambiguous Situation",
								"The right WSDL port to update could not be determined. Please, update the WSDL manually." );
					} else {
						File wsdlFile = IoUtils.convertToFile( wsdlUri );
						if( ! WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, serviceName, null, edptName )) {
							MessageDialog.openError( WSDLHelperTooltip.this.shell,	"End-point Update Failure", "The end-point could not be updated in the WSDL." );
						} else {
							// Force the validation of the file
							WSDLHelperTooltip.this.ise.getEditedFile().touch( null );
						}
					}

				} catch( InvocationTargetException e1 ) {
					MessageDialog.openError(
							WSDLHelperTooltip.this.shell, "Invalid WSDL",
							"The WSDL file could not be parsed. Make sure the URL and the file are both valid." );

				} catch( CoreException e1 ) {
					CdkPlugin.log( e1, IStatus.WARNING );
				}
			}
		});


		res.getBody().addListener( SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent( Event e ) {
				hide();
			}
		});
	}


	/**
	 * Updates the enablement state of the links.
	 */
	private void updateEnablement() {

		// Get values
		final List<JbiBasicBean> jbiBeans = this.wsdlParsingJob.getBeans();
		final URI wsdlUri = this.wsdlParsingJob.getWsdlUri();
		final File wsdlFile = IoUtils.convertToFile( wsdlUri );

		final IFolder resFolder = WSDLHelperTooltip.this.ise.getEditedFile().getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
		boolean enabled = wsdlFile == null && wsdlUri != null
				|| wsdlFile != null && ! resFolder.getLocation().isPrefixOf( new Path( wsdlFile.getAbsolutePath()));

		this.importLink.setEnabled( enabled );
		this.openLink.setEnabled( wsdlFile != null && wsdlFile.exists());
		this.updateLink.setEnabled( wsdlFile != null && wsdlFile.exists());
		this.selectServiceLink.setEnabled( ! jbiBeans.isEmpty());

	}
}

