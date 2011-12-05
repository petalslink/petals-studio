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
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipNodeSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * The section for the service properties of an EIP.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipInterfaceSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipNode eip;
	private boolean enableListener;
	private File wsdlFile;

	private Text itfNsText, itfNameText, srvNsText, srvNameText, edptText, wsdlText;
	private Hyperlink selectWsdlServiceLink, openWsdlLink;


	/**
	 * Constructor.
	 */
	public EipInterfaceSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( AbstractNode.PROPERTY_SERVICE_NAME.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_SERVICE_NAMESPACE.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_INTERFACE_NAME.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_INTERFACE_NAMESPACE.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_ENDPOINT_NAME.equals( evt.getPropertyName())
					|| EipNode.PROPERTY_WSDL_URI.equals( evt.getPropertyName()))
			refresh();
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

		// Create the WSDL part
		createWsdlParts( container );
		this.wsdlText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( EipNode.PROPERTY_WSDL_URI );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.wsdlText.getText());
					executeCommand( cmd );
				}
			}
		});

		// Interface
		CLabel label = getWidgetFactory().createCLabel( container, "Interface name:" );
		label.setToolTipText( "The local part of the interface name" );

		this.itfNameText = getWidgetFactory().createText( container, "" );
		this.itfNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNameText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( AbstractNode.PROPERTY_INTERFACE_NAME );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.itfNameText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Interface namespace:" );
		label.setToolTipText( "The name space URI of the interface name" );

		this.itfNsText = getWidgetFactory().createText( container, "" );
		this.itfNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNsText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( AbstractNode.PROPERTY_INTERFACE_NAMESPACE );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.itfNsText.getText());
					executeCommand( cmd );
				}
			}
		});

		// Service
		label = getWidgetFactory().createCLabel( container, "Service name:" );
		label.setToolTipText( "The local part of the service name" );

		this.srvNameText = getWidgetFactory().createText( container, "" );
		this.srvNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNameText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( AbstractNode.PROPERTY_SERVICE_NAME );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.srvNameText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Service namespace:" );
		label.setToolTipText( "The name space URI of the service name" );

		this.srvNsText = getWidgetFactory().createText( container, "" );
		this.srvNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNsText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( AbstractNode.PROPERTY_SERVICE_NAMESPACE );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.srvNsText.getText());
					executeCommand( cmd );
				}
			}
		});

		// End-point
		label = getWidgetFactory().createCLabel( container, "End-point name:" );
		label.setToolTipText( "The end-point name (a simple string)" );

		this.edptText = getWidgetFactory().createText( container, "" );
		this.edptText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.edptText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipInterfaceSection.this.enableListener ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( AbstractNode.PROPERTY_ENDPOINT_NAME );

					cmd.setEipNode( EipInterfaceSection.this.eip );
					cmd.setNewValue( EipInterfaceSection.this.edptText.getText());
					executeCommand( cmd );
				}
			}
		});
	}


	/**
	 * Executes a command on top of the EIP editor's command stack.
	 * @param command
	 */
	private void executeCommand( Command command ) {

		IEditorPart part = getPart().getSite().getPage().getActiveEditor();
		CommandStack commandStack = (CommandStack) part.getAdapter( CommandStack.class );
		if( commandStack != null )
			commandStack.execute( command );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput( part, selection );

		// Do not listen to model changes from the previous input
		if( this.eip != null )
			this.eip.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EipNodeEditPart )
				this.eip = (EipNode) ((EipNodeEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.eip != null )
			this.eip.addPropertyChangeListener( this );

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
		if( this.itfNsText != null
					&& ! this.itfNsText.isDisposed()
					&& this.eip != null ) {

			// Save the caret position
			Text focusText = null;
			if( this.itfNsText.isFocusControl())
				focusText = this.itfNsText;
			else if( this.itfNameText.isFocusControl())
				focusText = this.itfNameText;
			else if( this.srvNameText.isFocusControl())
				focusText = this.srvNameText;
			else if( this.srvNsText.isFocusControl())
				focusText = this.srvNsText;
			else if( this.edptText.isFocusControl())
				focusText = this.edptText;
			else if( this.wsdlText.isFocusControl())
				focusText = this.wsdlText;

			int caret = focusText != null ? focusText.getCaretPosition() : -1;

			// Update values
			this.itfNsText.setText( this.eip.getInterfaceNamespace() != null ? this.eip.getInterfaceNamespace() : "" );
			this.itfNameText.setText( this.eip.getInterfaceName() != null ? this.eip.getInterfaceName() : "" );
			this.srvNsText.setText( this.eip.getServiceNamespace() != null ? this.eip.getServiceNamespace() : "" );
			this.srvNameText.setText( this.eip.getServiceName() != null ? this.eip.getServiceName() : "" );
			this.edptText.setText( this.eip.getEndpointName() != null ? this.eip.getEndpointName() : "" );
			this.wsdlText.setText( this.eip.getWsdlUri() != null ? this.eip.getWsdlUri() : "" );

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );
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
		label.setToolTipText( "The URL of a WSDL description file" );

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
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			public void widgetSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( container.getShell());
				dlg.setText( "WSDL Selection" );
				dlg.setOverwrite( true );
				dlg.setFilterNames( new String[] { "WSDL Files (*.wsdl)" });
				dlg.setFilterExtensions( new String[] { "*.wsdl" });
				String path = dlg.open();

				if( path != null ) {
					EipInterfaceSection.this.wsdlText.setText( new File( path ).toURI().toString());
					updateHelpersEnablement();

					// Parse the WSDL
					List<JbiBasicBean> wsdlBeans = parseWsdl();
					if( wsdlBeans.size() > 0 )
						fillInFields( wsdlBeans.get( 0 ));
				}
			}
		});

		// Add the composite for the helpers
		Composite helpersComposite = getWidgetFactory().createPlainComposite( subSubContainer, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		helpersComposite.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		helpersComposite.setLayoutData( layoutData );

		addHelpers( helpersComposite );
	}


	/**
	 * Forces the WSDL parsing.
	 */
	private List<JbiBasicBean> parseWsdl() {

		List<JbiBasicBean> wsdlBeans = new ArrayList<WsdlUtils.JbiBasicBean> ();
		if( ! StringUtils.isEmpty( this.wsdlText.getText())) {
			try {
				wsdlBeans.addAll( WsdlUtils.INSTANCE.parse( this.wsdlText.getText()));

			} catch( IllegalArgumentException e ) {
				// nothing

			} catch( InvocationTargetException e ) {
				MessageDialog.openError(
						this.wsdlText.getShell(),
						"WSDL Parsing Failure",
						"The WSDL parsing failed: no service description was found in the referenced WSDL file." );
			}
		}

		return wsdlBeans;
	}


	/**
	 * Enables or disables the hyper links in function of the WSDL existence.
	 */
	private void updateHelpersEnablement() {

		boolean hasWsdl = ! StringUtils.isEmpty( this.wsdlText.getText());
		if( this.selectWsdlServiceLink != null
					&& ! this.selectWsdlServiceLink.isDisposed())
			this.selectWsdlServiceLink.setEnabled( hasWsdl );

		this.wsdlFile = null;
		if( hasWsdl
					&& this.openWsdlLink != null
					&& ! this.openWsdlLink.isDisposed() ) {

			File f = null;
			try {
				URI uri = UriUtils.urlToUri( this.wsdlText.getText());
				f = new File( uri );

			} catch( Exception e ) {
				// nothing
			}

			if( f != null && f.exists())
				this.wsdlFile = f;
		}

		this.openWsdlLink.setEnabled( this.wsdlFile != null );
	}


	/**
	 * Adds the provides helpers.
	 */
	private void addHelpers( final Composite helpersComposite ) {

		// Select a service in a WSDL
		this.selectWsdlServiceLink = getWidgetFactory().createHyperlink(
					helpersComposite,
					"Select a service in the WSDL to fill-in the properties below",
					SWT.NONE );

		this.selectWsdlServiceLink.setToolTipText( "A WSDL may describe several services instead of just one" );
		this.selectWsdlServiceLink.addHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				PCStyledLabelProvider lp = new PCStyledLabelProvider( EipInterfaceSection.this.wsdlText );
				StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog(
							EipInterfaceSection.this.wsdlText.getShell(), lp );

				dlg.setFilter( "*" );
				dlg.setSize( 120, 20 );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Selection" );
				dlg.setMessage( "Select the service to expose inside Petals." );

				List<JbiBasicBean> wsdlBeans = parseWsdl();
				if( ! wsdlBeans.isEmpty()) {
					dlg.setElements( wsdlBeans.toArray());
					if( dlg.open() == Window.OK ) {
						JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
						fillInFields( bean );
					}
				}

				lp.dispose();
			}
		});


		// Open the WSDL in the default editor
		this.openWsdlLink = getWidgetFactory().createHyperlink( helpersComposite, "Open in the WSDL editor", SWT.NONE );
		this.openWsdlLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		this.openWsdlLink.addHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {

				if( EipInterfaceSection.this.wsdlFile != null ) {
					IFileStore fileStore = EFS.getLocalFileSystem().getStore( new Path( EipInterfaceSection.this.wsdlFile.getAbsolutePath()));
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditorOnFileStore( page, fileStore );

					} catch (PartInitException e1 ) {
						PetalsEipPlugin.log( e1, IStatus.ERROR );
					}
				}
			}
		});
	}


	/**
	 * @param bean
	 */
	private void fillInFields( JbiBasicBean bean ) {

		String srvName = bean.getServiceName().getLocalPart();
		String srvNs = bean.getServiceName().getNamespaceURI();
		EipInterfaceSection.this.srvNameText.setText( srvName != null ? srvName : "" );
		EipInterfaceSection.this.srvNsText.setText( srvNs != null ? srvNs : "" );

		String itfName = bean.getInterfaceName().getLocalPart();
		String itfNs = bean.getInterfaceName().getNamespaceURI();
		EipInterfaceSection.this.itfNameText.setText( itfName != null ? itfName : "" );
		EipInterfaceSection.this.itfNsText.setText( itfNs != null ? itfNs : "" );

		String edptName = bean.getEndpointName() != null ? bean.getEndpointName() : "";
		if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( edptName )) {
			EipInterfaceSection.this.edptText.setEnabled( false );
			EipInterfaceSection.this.edptText.setText( "" );
		} else {
			EipInterfaceSection.this.edptText.setEnabled( true );
			EipInterfaceSection.this.edptText.setText( edptName );
		}
	}
}
