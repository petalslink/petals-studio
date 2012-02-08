/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;

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
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
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
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager.WsdlParsingListener;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class WSDLHelperTooltip extends ToolTip implements WsdlParsingListener {

	private final WsdlParsingJobManager wsdlParsingJob;
	private final FormToolkit toolkit;
	private final Provides service;
	private final ISharedEdition ise;
	private final Shell shell;


	/**
	 * Constructor.
	 * @param control
	 * @param tk
	 * @param service
	 * @param ise
	 * @param wsdlParsingJob
	 */
	public WSDLHelperTooltip( Control control, FormToolkit tk, Provides service, ISharedEdition ise, WsdlParsingJobManager wsdlParsingJob ) {
		super(control, NO_RECREATE, false);
		this.toolkit = tk;
		this.ise = ise;
		this.service = service;
		this.wsdlParsingJob = wsdlParsingJob;
		this.shell = control.getShell() ;

		// Already checked: there is one instance of the the tool tip.
		// Displaying it again and again does not add an infinite number of listeners
		this.wsdlParsingJob.addWsdlParsingListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.ToolTip
	 * #createToolTipContentArea(org.eclipse.swt.widgets.Event, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Composite createToolTipContentArea(Event event, Composite parent) {

		Form res = this.toolkit.createForm(parent);
		res.setText(Messages.wsdlTools);
		res.getBody().setLayout( new GridLayout());


		// Get values
		final List<JbiBasicBean> jbiBeans = this.wsdlParsingJob.getBeans();
		final URI wsdlUri = this.wsdlParsingJob.getWsdlUri();
		final File wsdlFile = IoUtils.convertToFile( wsdlUri );


		// Helper: import the WSDL in the project
		final Hyperlink importLink = this.toolkit.createHyperlink(res.getBody(), "Import this WSDL in the project", SWT.NONE );
		final IFolder resFolder = WSDLHelperTooltip.this.ise.getEditedFile().getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
		boolean enabled = wsdlFile == null && wsdlUri != null
				|| wsdlFile != null && ! resFolder.getLocation().isPrefixOf( new Path( wsdlFile.getAbsolutePath()));

		importLink.setEnabled( enabled );
		importLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				try {
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
		final Hyperlink selectServiceLink = this.toolkit.createHyperlink( res.getBody(), "Select a service in the WSDL to fill-in the properties below", SWT.NONE );
		selectServiceLink.setEnabled( ! jbiBeans.isEmpty());
		selectServiceLink.addHyperlinkListener( new HyperlinkAdapter() {
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

				dlg.setElements( jbiBeans.toArray());
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
		final Hyperlink openLink = this.toolkit.createHyperlink( res.getBody(), "Open in the WSDL editor", SWT.NONE );
		openLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		openLink.setEnabled( wsdlFile != null && wsdlFile.exists());
		openLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
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
		final Hyperlink updateLink = this.toolkit.createHyperlink(res.getBody(), "Update the service end-point in the WSDL", SWT.NONE );
		updateLink.setToolTipText( "Update the WSDL so that it declared end-point for this service is the one defined below" );
		updateLink.setEnabled( wsdlFile != null && wsdlFile.exists());
		updateLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				QName serviceName = WSDLHelperTooltip.this.service.getServiceName();
				String edptName = WSDLHelperTooltip.this.service.getEndpointName();

				if( ! WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, serviceName, edptName )) {
					MessageDialog.openError( WSDLHelperTooltip.this.shell,	"End-point Update Failure", "The end-point could not be updated in the WSDL." );
				} else {
					// Force the validation of the file
					try {
						WSDLHelperTooltip.this.ise.getEditedFile().touch( null );

					} catch( CoreException e1 ) {
						CdkPlugin.log( e1, IStatus.WARNING );
					}
				}
			}
		});

		return res;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object
	 * #finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		this.wsdlParsingJob.removeWsdlParsingListener( this );
	}


	/**
	 * Updates the GUI when the WSDL is parsed.
	 * FIXME: should we update the state of the hyper links?
	 */
	@Override
	public void notifyWsdlParsingDone() {
		// nothing
	}
}

