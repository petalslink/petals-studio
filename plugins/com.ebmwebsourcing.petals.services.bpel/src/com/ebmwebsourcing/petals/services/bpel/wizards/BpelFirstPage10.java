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

package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.ow2.easywsdl.wsdl.WSDLFactory;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.InterfaceType;
import org.ow2.easywsdl.wsdl.api.WSDLException;
import org.ow2.easywsdl.wsdl.api.WSDLReader;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.AbstractWizardListener;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FileUriBrowser;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.databinding.AbstractWizardWidgetEnabledObservable;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.databinding.BrowserWidgetValueObservable;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * The BPEL specific page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelFirstPage10 extends AbstractSuPage {

	private FileUriBrowser bpelBrowser, wsdlBrowser;
	private ComboViewer interfaceComboViewer;
	private DataBindingContext dbc;
	private WSDLReader reader;

	/**
	 * Constructor with no parameter.
	 * <p>
	 * Required to be instantiated from the plug-in registry.
	 * </p>
	 */
	public BpelFirstPage10() {
		try {
			reader = WSDLFactory.newInstance().newWSDLReader();
		} catch( WSDLException e1 ) {
			e1.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #validate()
	 */
	public void refreshImports() {
			// Get the process' WSDL
			IRunnableWithProgress wsdlParsingOperation = new IRunnableWithProgress() {
				public void run( IProgressMonitor monitor ) throws InvocationTargetException {
					try {
						monitor.beginTask( "Getting imports from the BPEL...", IProgressMonitor.UNKNOWN );
						URL url = new URL( BpelFirstPage10.this.bpelBrowser.getValue());
						List<URI> wsdlUris = PetalsBpelModules.getProcessWsdl( url );
						if( wsdlUris.size() > 0 ) {
							String wsdlUrl = wsdlUris.get( 0 ).toString();
							getWizard().getImportBPELStrategy().setWsdlUrl(wsdlUrl);
						}

					} catch( MalformedURLException e ) {
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


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		dbc = new DataBindingContext();
		
		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout();
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Group bpelGroup = new Group( container, SWT.NONE );
		bpelGroup.setText( "BPEL file" );
		bpelGroup.setLayout( new GridLayout());
		bpelGroup.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		////
		// create
		final Button createAllButton = new Button( bpelGroup, SWT.RADIO );
		createAllButton.setText( "Create a new BPEL process" );
		createAllButton.setSelection(true);
		// /create
		////
		
		////
		// import
		final Button importBpelButton = new Button( bpelGroup, SWT.RADIO );
		importBpelButton.setText( "Import an existing BPEL process" );
		importBpelButton.setSelection(false);
		this.bpelBrowser = new FileUriBrowser( "", bpelGroup, "BPEL URL", "", "", false, true, true );
		this.bpelBrowser.setFilterExtensions( new String[] { "*.bpel" });
		this.bpelBrowser.setFilterNames( new String[] { "BPEL (*.bpel)" });
		this.bpelBrowser.setEmptyValueAllowed( false );
		
		bpelBrowser.addListener(new AbstractWizardListener() {
			@Override
			public void valueHasChanged() {
				refreshImports();
			}
		});
		IObservableValue importBpelObservable = SWTObservables.observeSelection(importBpelButton);
		dbc.bindValue(importBpelObservable, new AbstractWizardWidgetEnabledObservable(bpelBrowser));
		dbc.bindValue(new BrowserWidgetValueObservable(bpelBrowser), PojoObservables.observeValue(getWizard().getImportBPELStrategy(), "importedBpel"));
		// /import
		////

		////
		// generate
		final Button generateBpelButton = new Button( bpelGroup, SWT.RADIO );
		generateBpelButton.setText( "Generate a BPEL skeleton from a WSDL file" );
		generateBpelButton.setSelection(false);
		this.wsdlBrowser = new FileUriBrowser( "", bpelGroup, "WSDL URL", "", "", false, true, true );
		this.wsdlBrowser.setFilterExtensions( new String[] { "*.wsdl" });
		this.wsdlBrowser.setFilterNames( new String[] { "WSDL (*.wsdl)" });
		this.wsdlBrowser.setEmptyValueAllowed( false );

		Label interfaceLabel = new Label( bpelGroup, SWT.NONE );
		interfaceLabel.setText( "Select the Port Type / Interface to use in the generation:" );
		interfaceLabel.setToolTipText( "The WSDL Port Type / Interface used to generate the BPEL process" );

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
		
		IObservableValue generateBpelObservable = SWTObservables.observeSelection(generateBpelButton);
		dbc.bindValue(generateBpelObservable, new AbstractWizardWidgetEnabledObservable(wsdlBrowser));
		dbc.bindValue(generateBpelObservable, SWTObservables.observeEnabled(interfaceLabel));
		dbc.bindValue(generateBpelObservable, SWTObservables.observeEnabled(interfaceComboViewer.getControl()));
		dbc.bindValue(new BrowserWidgetValueObservable(wsdlBrowser), PojoObservables.observeValue(getWizard().getGenerateBPELStrategy(), "wsdlURL"));
		IConverter itfToQname = new IConverter() {
			@Override
			public Object getToType() {
				return QName.class;
			}
			
			@Override
			public Object getFromType() {
				return InterfaceType.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				return ((InterfaceType)fromObject).getQName();
			}
		};
		dbc.bindValue(ViewersObservables.observeSingleSelection(interfaceComboViewer),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				new UpdateValueStrategy().setConverter(itfToQname),
				null);
		// /generate
		////


		
		this.bpelBrowser.addListener( new AbstractWizardListener() {
			public void valueHasChanged() {
				validate();
			}
		});

		this.wsdlBrowser.addListener( new AbstractWizardListener() {
			@Override
			public void valueHasChanged() {
				try {
					URI uri = UriUtils.urlToUri( BpelFirstPage10.this.wsdlBrowser.getValue());
					if( reader != null ) {
						Description wsdlDesc = reader.read( uri.toURL());
						getWizard().getGenerateBPELStrategy().setWsdlDescription(wsdlDesc);
						List<InterfaceType> interfaces = wsdlDesc.getInterfaces();
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

		setPageComplete( true );
		setControl( container );
		createAllButton.setSelection( true );
		importBpelButton.setSelection(false);
		generateBpelButton.setSelection(false);
		bpelBrowser.setEnabled(false);
		wsdlBrowser.setEnabled(false);
		interfaceLabel.setEnabled(false);
		interfaceComboViewer.getControl().setEnabled(false);
		createAllButton.notifyListeners( SWT.Selection, new Event());
	}
	
	@Override
	public BpelWizard10 getWizard() {
		return (BpelWizard10)super.getWizard();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}
	
	@Override
	public boolean validate() {
		return true;
	}
}
