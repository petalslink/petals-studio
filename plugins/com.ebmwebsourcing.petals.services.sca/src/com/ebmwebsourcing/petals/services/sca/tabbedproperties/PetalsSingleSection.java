/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.tabbedproperties;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.soa.sca.sca1_0.diagram.extension.edit.parts.ElementEditPart;
import org.eclipse.soa.sca.sca1_0.model.sca.BaseService;
import org.eclipse.soa.sca.sca1_0.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_0.model.sca.JavaInterface;
import org.eclipse.soa.sca.sca1_0.model.sca.Service;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.FrascatiPackage;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.wizards.WsdlImportWizard;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * The main (and only one) section for the Petals tab (properties of a JBI binding).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSingleSection extends AbstractPropertySection {

	private EditingDomain editingDomain;
	private JBIBinding jbiBinding;
	private boolean enableListener;

	private Text itfNsText, itfNameText, srvNsText, srvNameText, edptText, wsdlText;
	private Hyperlink selectWsdlServiceLink, openWsdlLink, generateWsdlLink;
	private Composite helpersComposite;


	/**
	 * Constructor.
	 */
	public PetalsSingleSection() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls( Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage ) {

		// Create the container
		super.createControls( parent, aTabbedPropertySheetPage );
		Composite container = getWidgetFactory().createPlainComposite( parent, SWT.NONE );
		container.setLayout( new GridLayout( 4, false ));

		ModifyListener modifyListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				if( PetalsSingleSection.this.enableListener )
					updateJbiBinding();
			}
		};

		// Create the WSDL part
		createWsdlParts( container );
		this.wsdlText.addModifyListener( modifyListener );

		// Interface
		getWidgetFactory().createCLabel( container, "Interface name:" );
		this.itfNameText = getWidgetFactory().createText( container, "" );
		this.itfNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNameText.addModifyListener( modifyListener );

		getWidgetFactory().createCLabel( container, "Interface namespace:" );
		this.itfNsText = getWidgetFactory().createText( container, "" );
		this.itfNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNsText.addModifyListener( modifyListener );

		// Service
		getWidgetFactory().createCLabel( container, "Service name:" );
		this.srvNameText = getWidgetFactory().createText( container, "" );
		this.srvNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNameText.addModifyListener( modifyListener );

		getWidgetFactory().createCLabel( container, "Service namespace:" );
		this.srvNsText = getWidgetFactory().createText( container, "" );
		this.srvNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNsText.addModifyListener( modifyListener );

		// End-point
		getWidgetFactory().createCLabel( container, "End-point name:" );
		this.edptText = getWidgetFactory().createText( container, "" );
		this.edptText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.edptText.addModifyListener( modifyListener );
	}


	/**
	 * Updates the JBI binding with the properties values.
	 */
	private void updateJbiBinding() {

		// Update the model.
		CompoundCommand cc = new CompoundCommand ();
		SetCommand sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_InterfaceName(),
					this.itfNameText.getText());
		cc.append( sc );

		sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_InterfaceNamespace(),
					this.itfNsText.getText());
		cc.append( sc );

		sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_ServiceName(),
					this.srvNameText.getText());
		cc.append( sc );

		sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_ServiceNamespace(),
					this.srvNsText.getText());
		cc.append( sc );

		sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_EndpointName(),
					this.edptText.getText());
		cc.append( sc );

		sc = new SetCommand(
					this.editingDomain,
					this.jbiBinding,
					FrascatiPackage.eINSTANCE.getJBIBinding_Wsdl(),
					this.wsdlText.getText());
		cc.append( sc );

		this.editingDomain.getCommandStack().execute( cc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput(part, selection);
		this.editingDomain = null;
		this.jbiBinding = null;

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof ElementEditPart ) {
				this.editingDomain = ((ElementEditPart) input).getEditingDomain();
				Object o = ((ElementEditPart) input).getModel();
				if( o instanceof NodeImpl ) {
					EObject eo =((NodeImpl) o).basicGetElement();
					if( eo instanceof JBIBinding )
						this.jbiBinding = (JBIBinding) eo;
				}
			}
		}

		removeHelpers();
		if( this.jbiBinding != null ) {
			if( this.jbiBinding.eContainer() instanceof BaseService )
				addProvidesHelpers();
			else
				addConsumesHelpers();
		}

		this.helpersComposite.layout();
		updateHelpersEnablement();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		this.enableListener = false;
		if( this.itfNsText != null ) {
			this.itfNsText.setText( this.jbiBinding.getInterfaceNamespace() != null ? this.jbiBinding.getInterfaceNamespace() : "" );
			this.itfNameText.setText( this.jbiBinding.getInterfaceName() != null ? this.jbiBinding.getInterfaceName() : "" );
			this.srvNsText.setText( this.jbiBinding.getServiceNamespace() != null ? this.jbiBinding.getServiceNamespace() : "" );
			this.srvNameText.setText( this.jbiBinding.getServiceName() != null ? this.jbiBinding.getServiceName() : "" );
			this.edptText.setText( this.jbiBinding.getEndpointName() != null ? this.jbiBinding.getEndpointName() : "" );
			this.wsdlText.setText( this.jbiBinding.getWsdl() != null ? this.jbiBinding.getWsdl() : "" );
		}

		this.enableListener = true;
	}


	/**
	 * Creates the WSDL widgets for a JBI binding set on a SCA reference.
	 * @param container the parent (SWT) composite
	 */
	private void createWsdlParts( final Composite container ) {

		// Add the edition parts
		CLabel label = getWidgetFactory().createCLabel( container, "WSDL location:" );
		GridData layoutData = new GridData( SWT.DEFAULT, SWT.TOP, false, false );
		layoutData.verticalIndent = 7;
		label.setLayoutData( layoutData );

		Composite subContainer = getWidgetFactory().createPlainComposite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, true );
		layout.marginBottom = 17;
		subContainer.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		subContainer.setLayoutData( layoutData );

		// Only use the left half-part
		Composite subSubContainer = getWidgetFactory().createPlainComposite( subContainer, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subSubContainer.setLayout( layout );
		subSubContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Add the WSDL browser
		this.wsdlText = getWidgetFactory().createText( subSubContainer, "", SWT.READ_ONLY );
		this.wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = getWidgetFactory().createButton( subSubContainer, "Browse...", SWT.PUSH );
		browseButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				File compositeFile = getCompositeFile();
				IFile f = ResourceUtils.getIFile( compositeFile );
				if( f != null ) {
					IProject project = f.getProject();
					IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

					// FIXME: replace this dialog by one that only shows WSDLs
					ElementTreeSelectionDialog dlg = new ElementTreeSelectionDialog(
								PetalsSingleSection.this.wsdlText.getShell(),
								new WorkbenchLabelProvider(),
								new WorkbenchContentProvider() {

									@Override
									public Object[] getChildren( Object element ) {

										Object[] result = super.getChildren( element );
										if( result == null )
											result = new Object[ 0 ];

										List<Object> filteredResult = new ArrayList<Object>();
										for( Object o : result ) {
											if( !( o instanceof IFile )
														|| "wsdl".equals(((IFile) o).getFileExtension()))
												filteredResult.add( o );
										}

										return filteredResult.toArray();
									}
								});

					dlg.setInput( resourceFolder );
					dlg.setTitle( "WSDL Selection" );
					dlg.setMessage( "Select a WSDL file located in the project's resource directory." );

					File file = getWsdlFile();
					if( file != null ) {
						IFile wsdlFile = ResourceUtils.getIFile( file );
						if( wsdlFile != null )
							dlg.setInitialElementSelections( Arrays.asList( wsdlFile ));
					}

					if( dlg.open() == Window.OK ) {

						// Set the WSDL location
						IFile selectedFile = (IFile) dlg.getResult()[ 0 ];
						int rfsc = resourceFolder.getFullPath().segmentCount();
						String wsdlValue = selectedFile.getFullPath().removeFirstSegments( rfsc ).toString();
						PetalsSingleSection.this.wsdlText.setText( wsdlValue );
						updateHelpersEnablement();

						// Parse the WSDL
						List<JbiBasicBean> wsdlBeans = parseWsdl();
						if( wsdlBeans.size() > 0 )
							fillInFields( wsdlBeans.get( 0 ));
					}
				}
			}
		});

		// Add the composite for the helpers
		this.helpersComposite = getWidgetFactory().createPlainComposite( subSubContainer, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		this.helpersComposite.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.helpersComposite.setLayoutData( layoutData );
	}


	/**
	 * Forces the WSDL parsing.
	 */
	private List<JbiBasicBean> parseWsdl() {

		List<JbiBasicBean> wsdlBeans = new ArrayList<JbiBasicBean> ();
		List<JbiBasicBean> _beans = null;
		try {
			_beans = WsdlUtils.INSTANCE.parse( getWsdlFile().toURI());

		} catch( IllegalArgumentException e ) {
			// nothing
		}

		if( _beans != null ) {
			wsdlBeans.addAll( _beans );
		} else {
			MessageDialog.openError(
						this.wsdlText.getShell(),
						"WSDL Parsing Failure",
			"The WSDL parsing failed: no service description was found in the referenced WSDL file." );
		}

		return wsdlBeans;
	}


	/**
	 * @return the WSDL file from the value of the WSDL text
	 */
	private File getWsdlFile() {
		File compositeFile = getCompositeFile();
		IFile f = ResourceUtils.getIFile( compositeFile );
		File result = null;
		if( f != null && f.exists())
			result = JbiXmlUtils.getWsdlFile( f.getProject().getLocation().toFile(), this.wsdlText.getText());
		return result;
	}


	/**
	 * @return the composite file from the JBI binding
	 */
	private File getCompositeFile() {
		return EmfUtils.getFileFromEmfUri( PetalsSingleSection.this.jbiBinding.eResource().getURI());
	}


	/**
	 * Enables or disables the hyper links in function of the WSDL existence.
	 */
	private void updateHelpersEnablement() {
		File f = getWsdlFile();
		if( this.selectWsdlServiceLink != null
					&& ! this.selectWsdlServiceLink.isDisposed())
			this.selectWsdlServiceLink.setEnabled( f != null );

		if( this.openWsdlLink != null
					&& ! this.openWsdlLink.isDisposed())
			this.openWsdlLink.setEnabled( f != null );
	}


	/**
	 * Removes all the helpers from the properties.
	 */
	private void removeHelpers() {
		for( Control childControl : this.helpersComposite.getChildren()) {
			if( ! childControl.isDisposed())
				childControl.dispose();
		}
	}


	/**
	 * Adds the provides helpers.
	 */
	private void addProvidesHelpers() {

		// First hyper link
		this.generateWsdlLink = getWidgetFactory().createHyperlink(
					this.helpersComposite, "Generate a WSDL from the service's Java interface", SWT.NONE );

		this.generateWsdlLink.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				File compositeFile = getCompositeFile();
				IFile f = ResourceUtils.getIFile( compositeFile );
				if( f != null && f.exists()) {
					String className = getInterfaceName((BaseService) PetalsSingleSection.this.jbiBinding.eContainer());
					final IFolder jbiFolder = f.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
					IJavaProject jp = JavaCore.create( f.getProject());

					// Create the WSDL name
					int dotPosition = className.lastIndexOf( '.' );
					String wsdlName = className.substring( dotPosition + 1 ) + ".wsdl";

					// Open a dialog to ask the WSDL name
					IInputValidator validator = new IInputValidator() {
						@Override
						public String isValid( String newText ) {
							String msg = null;
							if( newText.trim().length() == 0 )
								msg = "The WSDL name cannot be empty.";
							else if( jbiFolder.getFile( newText ).exists())
								msg = "This file already exist.";
							return msg;
						}
					};

					InputDialog wsdlNameDialog = new InputDialog(
								PetalsSingleSection.this.generateWsdlLink.getShell(),
								"WSDL Name",
								"Indicate the name of the WSDL file to create.",
								wsdlName,
								validator );

					// Create a WSDL?
					boolean errorOccurred = false;
					if( wsdlNameDialog.open() == Window.OK ) {
						wsdlName = wsdlNameDialog.getValue();

						File wsdlFile = WsdlExtUtils.generateWsdlFile( wsdlName, className, jbiFolder.getLocation().toString(), jp );
						if( wsdlFile.exists()) {

							// Set the new WSDL in the JBI binding
							String wsdlValue = IoUtils.getRelativeLocationToFile( jbiFolder.getLocation().toFile(), wsdlFile );
							PetalsSingleSection.this.wsdlText.setText( wsdlValue );

							try {
								jbiFolder.refreshLocal( IResource.DEPTH_INFINITE, null );
							} catch( CoreException e1 ) {
								// nothing
							}

							updateHelpersEnablement();

							// Parse the WSDL
							List<JbiBasicBean> wsdlBeans = parseWsdl();
							if( wsdlBeans.size() > 0 )
								fillInFields( wsdlBeans.get( 0 ));

						} else {
							errorOccurred = true;
						}
					}

					if( errorOccurred )
						MessageDialog.openError( new Shell(), "WSDL Generation Error", "An error occurred. See the log for more details." );
				}
			}
		});


		// Second hyper link
		this.selectWsdlServiceLink = getWidgetFactory().createHyperlink(
					this.helpersComposite, "Select a service in the WSDL to fill-in the properties below", SWT.NONE );

		this.selectWsdlServiceLink.setToolTipText( "A WSDL may describe several services instead of just one" );
		this.selectWsdlServiceLink.addHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				PCStyledLabelProvider lp = new PCStyledLabelProvider( PetalsSingleSection.this.wsdlText );
				StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog(
							PetalsSingleSection.this.wsdlText.getShell(), lp );

				dlg.setFilter( "*" );
				dlg.setSize( 120, 20 );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Selection" );
				dlg.setMessage( "Select the service to expose inside Petals." );

				List<JbiBasicBean> wsdlBeans = parseWsdl();
				dlg.setElements( wsdlBeans.toArray());
				if( dlg.open() == Window.OK ) {
					JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
					fillInFields( bean );
				}

				lp.dispose();
			}
		});


		// Third hyper link
		addCommonHelpers();
	}


	/**
	 * @param baseService
	 * @return
	 */
	private String getInterfaceName( BaseService baseService ) {

		// Find the composite service
		BaseService service = (BaseService) PetalsSingleSection.this.jbiBinding.eContainer();
		Service compositeService = null;
		if( service instanceof ComponentService ) {
			org.eclipse.soa.sca.sca1_0.model.sca.Composite scaComposite =
				(org.eclipse.soa.sca.sca1_0.model.sca.Composite) service.eContainer().eContainer();

			for( Service cs : scaComposite.getService()) {
				if( service.equals( cs.getPromote())) {
					compositeService = cs;
					break;
				}
			}

		} else {
			compositeService = (Service) service;
		}

		// Find the class name
		String className = null;
		if( compositeService != null ) {
			if( compositeService.getInterface() instanceof JavaInterface ) {
				className = ((JavaInterface) compositeService.getInterface()).getInterface();

			} else {
				ComponentService sc = compositeService.getPromote();
				if( sc.getInterface() instanceof JavaInterface )
					className = ((JavaInterface) sc.getInterface()).getInterface();
			}
		}

		return className;
	}


	/**
	 * @param bean
	 */
	private void fillInFields( JbiBasicBean bean ) {

		String srvName = bean.getServiceName();
		String srvNs = bean.getServiceNs();
		PetalsSingleSection.this.srvNameText.setText( srvName != null ? srvName : "" );
		PetalsSingleSection.this.srvNsText.setText( srvNs != null ? srvNs : "" );

		String itfName = bean.getInterfaceName();
		String itfNs = bean.getInterfaceNs();
		PetalsSingleSection.this.itfNameText.setText( itfName != null ? itfName : "" );
		PetalsSingleSection.this.itfNsText.setText( itfNs != null ? itfNs : "" );

		String edptName = bean.getEndpointName() != null ? bean.getEndpointName() : "";
		if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( edptName )) {
			PetalsSingleSection.this.edptText.setEnabled( false );
			PetalsSingleSection.this.edptText.setText( "" );
		} else {
			PetalsSingleSection.this.edptText.setEnabled( true );
			PetalsSingleSection.this.edptText.setText( edptName );
		}
	}


	/**
	 * Adds the consumes helpers.
	 */
	private void addConsumesHelpers() {

		// First hyper link
		Hyperlink link = getWidgetFactory().createHyperlink(
					this.helpersComposite, "Import a WSDL in this project", SWT.NONE );

		link.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				File compositeFile = getCompositeFile();
				IFile f = ResourceUtils.getIFile( compositeFile );
				if( f != null ) {
					WsdlImportWizard wiz = new WsdlImportWizard();
					IFolder resFolder = f.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
					wiz.setInitialContainer( resFolder );
					IWorkbench workbench = PlatformUI.getWorkbench();
					wiz.init( workbench, null );

					WizardDialog dlg = new WizardDialog( workbench.getActiveWorkbenchWindow().getShell(), wiz );
					if( dlg.open() == Window.OK ) {
						File importedFile = wiz.getWsdlFileAfterImport();
						String value = IoUtils.getBasicRelativePath( resFolder.getLocation().toFile(), importedFile );
						PetalsSingleSection.this.wsdlText.setText( value );
					}
				}
			}
		});


		// Second hyper link
		link = getWidgetFactory().createHyperlink( this.helpersComposite, "Select a service from the Petals Services view", SWT.NONE );
		link.setToolTipText( "Select an end-point to consume among the currently referenced end-points" );
		link.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( PetalsSingleSection.this.helpersComposite );
				if( bean == null )
					return;

				try {
					PetalsSingleSection.this.wsdlText.setText( "" );
					String srvName = bean.getServiceName() != null ? bean.getServiceName().getLocalPart() : "";
					String srvNs = bean.getServiceName() != null ? bean.getServiceName().getNamespaceURI() : "";
					PetalsSingleSection.this.srvNameText.setText( srvName );
					PetalsSingleSection.this.srvNsText.setText( srvNs );

					String itfName = bean.getServiceName() != null ? bean.getInterfaceName().getLocalPart() : "";
					String itfNs = bean.getServiceName() != null ? bean.getInterfaceName().getNamespaceURI() : "";
					PetalsSingleSection.this.itfNameText.setText( itfName );
					PetalsSingleSection.this.itfNsText.setText( itfNs );

					if( PetalsConstants.AUTO_GENERATE.equals( bean.getEndpointName()))
						PetalsSingleSection.this.edptText.setText( "" );
					else
						PetalsSingleSection.this.edptText.setText( bean.getEndpointName());

					updateHelpersEnablement();

				} catch( Exception e1 ) {
					PetalsScaPlugin.log( e1, IStatus.ERROR );
				}
			}
		});


		// Third hyper link
		addCommonHelpers();
	}


	/**
	 * Adds the common helpers.
	 */
	private void addCommonHelpers() {

		this.openWsdlLink = getWidgetFactory().createHyperlink( this.helpersComposite, "Open in the WSDL editor", SWT.NONE );
		this.openWsdlLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		this.openWsdlLink.addHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {
				File file = getWsdlFile();
				IFile f = ResourceUtils.getIFile( file );
				if( f != null ) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, f, true );

					} catch( PartInitException exception ) {
						PetalsScaPlugin.log( exception, IStatus.ERROR );
					}
				}
			}
		});
	}
}
