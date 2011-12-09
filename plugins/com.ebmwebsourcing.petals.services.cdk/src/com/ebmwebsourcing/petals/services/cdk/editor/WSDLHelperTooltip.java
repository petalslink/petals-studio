package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.wizards.WsdlImportWizard;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.cdk.CdkPlugin;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.su.EMFPCStyledLabelProvider;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager.WsdlParsingListener;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

public class WSDLHelperTooltip extends ToolTip implements WsdlParsingListener {

	private final WsdlParsingJobManager wsdlParsingJob;
	private final FormToolkit toolkit;
	private final Provides service;
	private final JbiFormEditor editor;
	private final Shell shell;

	public WSDLHelperTooltip(Control control, FormToolkit tk, Provides service, JbiFormEditor editor, Shell shell) {
		super(control, NO_RECREATE, false);
		this.toolkit = tk;
		this.service = service;
		this.editor = editor;
		this.wsdlParsingJob = new WsdlParsingJobManager();
		this.shell = shell;
	}

	@Override
	protected Composite createToolTipContentArea(Event event, Composite parent) {
		final File wsdlFile = getWSDL();
		Form res = this.toolkit.createForm(parent);
		res.setText(Messages.wsdlTools);
		res.getBody().setLayout(new GridLayout(1, false));

		if (wsdlFile == null || !wsdlFile.exists()) {
			this.toolkit.createLabel(res.getBody(), Messages.invalidWSDLLocation);
		}

		final Hyperlink importLink = this.toolkit.createHyperlink(res.getBody(), "Import this WSDL in the project", SWT.NONE );
		importLink.setEnabled(wsdlFile != null && wsdlFile.exists());
		importLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				URI wsdlURI = wsdlFile.toURI();
				if( wsdlURI != null ) {
					WsdlImportWizard wiz = new WsdlImportWizard();
					IFolder resFolder = WSDLHelperTooltip.this.editor.getEditedFile().getProject().getFolder(PetalsConstants.LOC_RES_FOLDER);

					wiz.setInitialContainer( resFolder );
					wiz.setInitialWsdlUri( wsdlURI.toString());

					IWorkbench workbench = PlatformUI.getWorkbench();
					wiz.init( workbench, null );

					WizardDialog dlg = new WizardDialog( workbench.getActiveWorkbenchWindow().getShell(), wiz );
					if( dlg.open() == Window.OK ) {
						File importedFile = wiz.getWsdlFileAfterImport();
						String value = IoUtils.getBasicRelativePath( resFolder.getLocation().toFile(), importedFile );
						SetCommand command = new SetCommand(WSDLHelperTooltip.this.editor.getEditingDomain(), WSDLHelperTooltip.this.service, Cdk5Package.Literals.CDK5_PROVIDES__WSDL, value);
					}
				}
			}
		});

		final Hyperlink selectServiceLink = this.toolkit.createHyperlink(res.getBody(), "Select a service in the WSDL to fill-in the properties below", SWT.NONE );
		selectServiceLink.setEnabled(wsdlFile != null && wsdlFile.exists());
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

				List<JbiBasicBean> wsdlBeans = parseWsdl();
				dlg.setElements( wsdlBeans.toArray());
				if( dlg.open() == Window.OK ) {
					CompoundCommand cc = new CompoundCommand();


					JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
					cc.append( new SetCommand(
							WSDLHelperTooltip.this.editor.getEditingDomain(), WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME,
							bean.getServiceName()));

					cc.append( new SetCommand(
							WSDLHelperTooltip.this.editor.getEditingDomain(), WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME,
							bean.getInterfaceName()));

					String edptName = bean.getEndpointName() != null ? bean.getEndpointName() : "";
					cc.append( new SetCommand(
							WSDLHelperTooltip.this.editor.getEditingDomain(), WSDLHelperTooltip.this.service,
							JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME,
							edptName));

					WSDLHelperTooltip.this.editor.getEditingDomain().getCommandStack().execute(cc);
				}

				lp.dispose();
			}
		});

		final Hyperlink openLink = this.toolkit.createHyperlink( res.getBody(), "Open in the WSDL editor", SWT.NONE );
		openLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		openLink.setEnabled(wsdlFile != null && wsdlFile.exists());
		openLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				IFile f = ResourceUtils.getIFile( wsdlFile );
				if( f != null ) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, f, true );

					} catch( PartInitException exception ) {
						CdkPlugin.log( exception, IStatus.ERROR );
					}
				}
			}
		});

		final Hyperlink updateLink = this.toolkit.createHyperlink(res.getBody(), "Update the service end-point in the WSDL", SWT.NONE );
		updateLink.setToolTipText( "Update the WSDL so that it declared end-point for this service is the one defined below" );
		updateLink.setEnabled(wsdlFile != null && wsdlFile.exists());
		updateLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {
				hide();
				// Enabled only when the WSDL points to an existing file
				QName serviceName = WSDLHelperTooltip.this.service.getServiceName();
				String edptName = WSDLHelperTooltip.this.service.getEndpointName();

				if( ! WsdlUtils.INSTANCE.updateEndpointNameInWsdl(wsdlFile, serviceName, edptName )) {
					MessageDialog.openError(
								WSDLHelperTooltip.this.shell,
								"End-point Update Failure",
								"The end-point could not be updated in the WSDL." );
				} else {
					// Force the validation of the file
					try {
						WSDLHelperTooltip.this.editor.getEditedFile().touch( null );
					} catch( CoreException e1 ) {
						PetalsServicesPlugin.log( e1, IStatus.WARNING );
					}
				}
			}
		});

		return res;
	}


	@Override
	public void activate() {
		super.activate();
		if (this.wsdlParsingJob != null) {
			this.wsdlParsingJob.addWsdlParsingListener( this );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #dispose()
	 */
	@Override
	public void deactivate() {
		if (this.wsdlParsingJob != null) {
			this.wsdlParsingJob.removeWsdlParsingListener( this );
		}
		super.deactivate();
	}



	/**
	 * Updates the GUI when the WSDL is parsed.
	 */
	@Override
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

			URI uri = getWSDL().toURI();

			// Parse and update...
			if( uri != null ) {
				List<JbiBasicBean> _beans = null;
				try {
					_beans = WsdlUtils.INSTANCE.parse( uri );
				} catch( IllegalArgumentException e ) {
					// nothing

				} catch( InvocationTargetException e ) {
					// FIXME: nothing?
				}

				if( _beans != null )
					wsdlBeans.addAll( _beans );
			}

			// ... or report an error
			else {
				MessageDialog.openError(
							this.editor.getSite().getShell(),
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
		if( wsdlBeans.size() > 0 ) {
			JbiBasicBean firstBean = wsdlBeans.get( 0 );
			CompoundCommand cc = new CompoundCommand();
			cc.append(new SetCommand(this.editor.getEditingDomain(), this.service, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, firstBean.getInterfaceName()));
			cc.append(new SetCommand(this.editor.getEditingDomain(), this.service, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, firstBean.getServiceName()));
			cc.append(new SetCommand(this.editor.getEditingDomain(), this.service, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, firstBean.getEndpointName()));
			this.editor.getEditingDomain().getCommandStack().execute(cc);
		}
	}


	private File getWSDL() {
		String wsdl = null;
		try {
			wsdl = (String) this.service.eGet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL);
			File wsdlFile = new File(wsdl); // TODO support cases of relative path
			if (!wsdlFile.exists()) {
				wsdlFile = this.editor.getEditedFile().getParent().getFile(new Path(wsdl)).getLocation().toFile();
			}
			return wsdlFile;
		} catch (Exception ex) {
			// NOTHING here, just no wsdl
			return null;
		}

	}

}

