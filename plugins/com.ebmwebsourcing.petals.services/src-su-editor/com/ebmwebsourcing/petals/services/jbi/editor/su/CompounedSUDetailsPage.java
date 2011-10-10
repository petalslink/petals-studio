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

package com.ebmwebsourcing.petals.services.jbi.editor.su;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.ebmwebsourcing.petals.services.jbi.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.QNameToStringConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringIsQNameValidator;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.StringToQNameConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.SupportsUtil;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CompounedSUDetailsPage implements IDetailsPage {


	protected final static String ITF_NS_DECL = "xmlns:itfNs";
	protected final static String SRV_NS_DECL = "xmlns:srvNs";

	protected IManagedForm managedForm;
	protected final AbstractServicesFormPage page;

	protected Text interfaceText;
	protected Text serviceText;
	protected Text edptNameText;
	protected Label edptNameLabel;

	protected String sectionTitle, sectionDescription;
	protected boolean reportChanges = true; // TODO: get rid of this flag thx to databinding
	protected Jbi jbi;
	protected AbstractEndpoint selectedEndpoint;

	private DataBindingContext dbc;
	private JbiEditorDetailsContribution cdkContributions;
	private JbiEditorDetailsContribution componentContributions;

	/**
	 * Constructor.
	 * @param page
	 * @param selectedEndpoint 
	 */
	public CompounedSUDetailsPage( AbstractServicesFormPage page, AbstractEndpoint selectedEndpoint ) {
		this.page = page;
		this.jbi = page.getJbi();
		ComponentSupportExtensionDesc componentExtensionDesc = SupportsUtil.getInstance().getComponentExtensionDesc(selectedEndpoint);
		if (componentExtensionDesc != null) {
			this.componentContributions = componentExtensionDesc.createNewExtensionSupport().createJbiEditorContribution(selectedEndpoint, this);
			this.cdkContributions = componentExtensionDesc.getCDKSupportExtensionDesc().createNewExtensionSupport().createJbiEditorContribution(selectedEndpoint, this);
	    }
		this.selectedEndpoint = selectedEndpoint;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents( Composite parent ) {

		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		GridLayout layout = new GridLayout ();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 20;
		parent.setLayout( layout );


		// Containing section
		FormToolkit toolkit = this.managedForm.getToolkit();
		Section section = toolkit.createSection( parent,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.clientVerticalSpacing = 10;
		if (this.sectionTitle != null) {
			section.setText( this.sectionTitle );
		}
		if (this.sectionDescription != null) {
			section.setDescription( this.sectionDescription );
		}

		Composite container = toolkit.createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL ));
		section.setClient( container );


		// Add fields before
		cdkContributions.addSUContentBefore(container);
		if (componentContributions != null) {
			componentContributions.addSUContentBefore(container);
		}


		// Add common UI

		Label label = toolkit.createLabel( container, "Interface qname:" );
		label.setToolTipText( "The Qualified Name '{namespace}element' of the interface (must match an interface declared in the WSDL)" );
		label.setForeground( blueFont );

		this.interfaceText = toolkit.createText(container, "", SWT.SINGLE | SWT.BORDER);
		this.interfaceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		
		label = toolkit.createLabel( container, "Service qname:" );
		label.setToolTipText( "The Qualified Name '{namespace}element' of the service (must match a service declared in the WSDL)" );
		label.setForeground( blueFont );

		this.serviceText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.serviceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.edptNameLabel = toolkit.createLabel( container, "End-point name:" );
		this.edptNameLabel.setToolTipText( "The end-point name, meaning the service location (must match the one declared in the WSDL)" );
		this.edptNameLabel.setForeground( blueFont );

		this.edptNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.edptNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		cdkContributions.addSUContentAfterEndpoint(container);

		// Add fields after
		cdkContributions.addSUContentAfter(parent);
		if (componentContributions != null) {
			componentContributions.addSUContentAfter(parent);
		}


//		final ModifyListener sameNsModifyListener = new ModifyListener() {
//			public void modifyText( ModifyEvent e ) {
//				String serviceNs = GeneralAbstractDetails.this.serviceText.getText();
//				String interfaceNs = GeneralAbstractDetails.this.interfaceText.getText();
//
//				Color fgColor;
//				if( serviceNs.trim().length() > 0
//							&& serviceNs.equals( interfaceNs )) {
//					fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_DARK_GREEN );
//				} else {
//					fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );
//				}
//
//				GeneralAbstractDetails.this.serviceText.setForeground( fgColor );
//				GeneralAbstractDetails.this.interfaceText.setForeground( fgColor );
//			}
//		};
//
//		FocusListener nsFocusListener = new FocusAdapter() {
//			@Override
//			public void focusGained( FocusEvent e ) {
//				((Text) e.widget).addModifyListener( sameNsModifyListener );
//				((Text) e.widget).notifyListeners( SWT.Modify, new Event());
//			}
//
//			@Override
//			public void focusLost( FocusEvent e ) {
//				((Text) e.widget).removeModifyListener( sameNsModifyListener );
//				Color fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );
//				GeneralAbstractDetails.this.serviceText.setForeground( fgColor );
//				GeneralAbstractDetails.this.interfaceText.setForeground( fgColor );
//			}
//		};
//
//		this.itfNameText.addListener( SWT.Modify, attributeModifyListener );
//		this.interfaceText.addListener( SWT.Modify, attributeModifyListener );
//		this.srvNameText.addListener( SWT.Modify, attributeModifyListener );
//		this.serviceText.addListener( SWT.Modify, attributeModifyListener );
//		this.edptNameText.addListener( SWT.Modify, attributeModifyListener );
//
//		this.interfaceText.addFocusListener( nsFocusListener );
//		this.serviceText.addFocusListener( nsFocusListener );

		KeyListener defaultNsKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {

				if( e.stateMask == SWT.CTRL
							&& e.character == ' ' ) {
					Text text = (Text) e.widget;

					if( text.getText().trim().length() == 0
								|| "http://".equals( text.getText())) {
						text.setText( "http://petals.ow2.org/" );
						text.setSelection( 22 );
					}
				}
			}
		};

		this.serviceText.addKeyListener( defaultNsKeyListener );
		this.interfaceText.addKeyListener( defaultNsKeyListener );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #dispose()
	 */
	public void dispose() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #initialize(org.eclipse.ui.forms.IManagedForm)
	 */
	public void initialize( IManagedForm form ) {
		this.managedForm = form;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #isDirty()
	 */
	public boolean isDirty() {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #isStale()
	 */
	public boolean isStale() {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #refresh()
	 */
	public final void refresh() {

		if (dbc != null) {
			dbc.dispose();
		}
		dbc = new DataBindingContext();
		
		if( this.selectedEndpoint == null
					|| this.interfaceText == null 	// page already loaded?
					|| this.interfaceText.isDisposed())
			return;

		try {
			// Do not propagate indefinitely model changes
			this.reportChanges = false;

			dbc.bindValue(
					SWTObservables.observeDelayedValue(200, SWTObservables.observeText(interfaceText, SWT.Modify)),
					EMFEditObservables.observeValue(page.getEditDomain(), selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
					new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
					new UpdateValueStrategy().setConverter(new QNameToStringConverter()));

			dbc.bindValue(
					SWTObservables.observeDelayedValue(200, SWTObservables.observeText(serviceText, SWT.Modify)),
					EMFEditObservables.observeValue(page.getEditDomain(), selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME),
					new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
					new UpdateValueStrategy().setConverter(new QNameToStringConverter()));
			
			dbc.bindValue(
					SWTObservables.observeDelayedValue(200, SWTObservables.observeText(edptNameText, SWT.Modify)),
					EMFEditObservables.observeValue(page.getEditDomain(), selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME));
			
			// End-point: deal with it in sub-classes
			cdkContributions.refresh();
			if (componentContributions != null) {
				componentContributions.refresh();
			}


		} finally {

			// Propagate (again) model changes
			this.reportChanges = true;
		}
	}



	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #setFocus()
	 */
	public void setFocus() {
		this.interfaceText.setFocus();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #setFormInput(java.lang.Object)
	 */
	public boolean setFormInput( Object input ) {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IPartSelectionListener
	 * #reportChanges(org.eclipse.ui.forms.IFormPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged( IFormPart part, ISelection selection ) {

		IStructuredSelection ssel = (IStructuredSelection) selection;
		if( ssel.size() == 1 ) {
			this.selectedEndpoint = (AbstractEndpoint) ssel.getFirstElement();
		} else
			this.selectedEndpoint = null;

		refresh();
	}

	public void commit(boolean onSave) {
		// DO NOTHING
	}


	public void setSectionTitle(String string) {
		this.sectionTitle = string;
	}


	public void setSectionDescription(String string) {
		this.sectionDescription = string;
	}


	public IManagedForm getManagedForm() {
		return this.managedForm;
	}


	public AbstractServicesFormPage getGeneralPage() {
		return this.page;
	}


	public AbstractEndpoint getSelectedEndpoint() {
		return this.selectedEndpoint;
	}


	public boolean isReportChanges() {
		return reportChanges;
	}


	public void setEndpointEnabled(boolean b) {
		edptNameLabel.setEnabled(b);
		edptNameText.setEnabled(b);
	}


	public void setReportChanges(boolean b) {
		this.reportChanges = b;
	}
	
	public DataBindingContext getDataBindingContext() {
		return this.dbc;
	}
}
