/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions.generic;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * A wizard page to define the target component (and to create a default SU for this component).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GenericSuWizardPage extends AbstractSuWizardPage {

	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public GenericSuWizardPage() {
		super( "GenericSuWizardPage" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		setMessage( null, IMessageProvider.INFORMATION );

		// Validate the fields
		String error = null;
		ComponentVersionDescription desc = getWizard().getComponentVersionDescription();
		if( StringUtils.isEmpty( desc.getComponentName()))
			error = "You have to set the component name.";
		else if( StringUtils.isEmpty( desc.getComponentAlias()))
			error = "You have to define the service type (e.g. SOAP).";

		// Update the UI
		setPageComplete( error == null );
		setErrorMessage( error );
		return error == null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( final Composite parent ) {

		// Keep the component description
		final GenericComponentDescription genDesc = (GenericComponentDescription) getWizard().getComponentVersionDescription();


		// Create the composite container and define its layout.
		final Composite container = SwtFactory.createComposite( parent );
		SwtFactory.applyNewGridLayout( container, 2, false, 20, 15, 5, 15 );
		SwtFactory.applyGrabbingGridData( container );


		// The fields to fill-in
		SwtFactory.createLabel( container, "Component Type:", "The type of the target component" );
		Combo typeCombo = SwtFactory.createDropDownCombo( container, true, true );
		typeCombo.add( "Binding Component" );
		typeCombo.add( "Service Engine" );
		typeCombo.select( genDesc.isBc() ? 0 : 1 );
		typeCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				boolean isBc = ((Combo) e.widget).getSelectionIndex() == 0 ? true : false;
				genDesc.setBc( isBc );
				validate();
			}
		});

		SwtFactory.createLabel( container, "Component Name:", "The name of the target component" );
		Text text = SwtFactory.createSimpleTextField( container, true );
		text.setText( genDesc.getComponentName());
		text.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String componentName = ((Text) e.widget).getText().trim();
				genDesc.setComponentName( componentName );
				validate();
			}
		});

		SwtFactory.createLabel( container, "Component Version:", "The version of the target component" );
		text = SwtFactory.createSimpleTextField( container, true );
		genDesc.setComponentVersion( "1.0" );
		text.setText( "1.0" );
		text.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String version = ((Text) e.widget).getText().trim();
				genDesc.setComponentVersion( version );
				validate();
			}
		});

		SwtFactory.createLabel( container, "Component Alias:", "The component's alias (e.g. SOAP, FTP...)" );
		text = SwtFactory.createSimpleTextField( container, true );
		text.setText( genDesc.getComponentAlias());
		text.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String type = ((Text) e.widget).getText().trim();
				genDesc.setComponentAlias( type );
				validate();
			}
		});

//		SwtFactory.createLabel( container, "CDK Version:", "The version of the CDK to use" );
//		Combo cdkCombo = new Combo( container, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
//		cdkCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
//		cdkCombo.add( "" );
//		cdkCombo.add( "4.x" );
//		cdkCombo.add( "5.x" );
//		cdkCombo.addSelectionListener( new SelectionAdapter() {
//			@Override
//			public void widgetSelected( SelectionEvent e ) {
//				// String version = ((Combo) e.widget).getText();
//				// TODO : commented to remove deps on editor to CDK
//				// GenericSuWizardPage.this.cdkNamespaceUri = CdkXsdManager.getInstance().resolveCdkVersion( version.replace( 'x', '0' ));
//				// validate();
//			}
//		});


		// Complete the page
		validate();
		String msg = getErrorMessage();
		if( msg != null ) {
			setErrorMessage( null );
			setMessage( msg, IMessageProvider.INFORMATION );
		}

		setControl( container );
	}
}
