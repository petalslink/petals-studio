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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc.BcConsumeCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se.SeConsumeCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.bc.BcProvideCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideCdkPage;

/**
 * A wizard page to define the target component (and to create a default SU for this component).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GenericSuWizardPage extends AbstractSuPage {

	private String componentName, cdkNamespaceUri;
	private boolean isBc;


	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public GenericSuWizardPage() {
		super( "GenericSuWizardPage" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.pages.AbstractSuPage#
	 * setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// Nothing.
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #updatePage(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.WizardConfiguration)
	 */
	public void updatePage( WizardConfiguration wizardConfiguration ) {
		// nothing
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
		if( StringUtils.isEmpty( this.componentName ))
			error = "You have to set the component name.";
		else if( StringUtils.isEmpty( this.suType ))
			error = "You have to define the service type (e.g. SOAP).";


		// Propagate the data to the project page
		AbstractSuPage nextPage = (AbstractSuPage) getWizard().getPage( "ProjectPage" );
		if( nextPage != null )
			nextPage.setBasicFields( this.suType, this.suTypeVersion, "" );


		// Register the CDK name space
		XsdNamespaceStore.getNamespaceStore().store( "petalsCDK", this.cdkNamespaceUri != null ? this.cdkNamespaceUri : "anything but a valid uri" );


		// Deal with the CDK - add dynamically a CDK page in the wizard
		XsdBasedAbstractSuPage cdkPage;
		if( this.isBc ) {
			if( ! isProvides())
				cdkPage = new BcConsumeCdkPage( this.suType, this.suTypeVersion );
			else
				cdkPage = new BcProvideCdkPage( this.suType, this.suTypeVersion );
		}
		else {
			if( ! isProvides())
				cdkPage = new SeConsumeCdkPage( this.suType, this.suTypeVersion );
			else
				cdkPage = new SeProvideCdkPage( this.suType, this.suTypeVersion );
		}

		cdkPage.setUseCache( false );
		if( cdkPage.hasSomethingToDisplay())
			((PetalsSuNewWizard) getWizard()).addPage( cdkPage );


		// Update the UI
		setPageComplete( error == null );
		setErrorMessage( error );
		return error == null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		suBean.setSuType( this.suType );
		suBean.setComponentVersion( this.suTypeVersion );
		suBean.setComponentName( this.componentName );
		suBean.setBc( this.isBc );
		suBean.setContributorId( "" );

		if( ! StringUtils.isEmpty( this.cdkNamespaceUri ))
			suBean.addNamespace( "petalsCDK", this.cdkNamespaceUri );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( final Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// The fields to fill-in
		Label l = new Label( container, SWT.NONE );
		l.setText( "Component Type:" );
		l.setToolTipText( "The type of the target component" );

		Combo typeCombo = new Combo( container, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
		typeCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		typeCombo.add( "Binding Component" );
		typeCombo.add( "Service Engine" );
		typeCombo.select( this.isBc ? 0 : 1 );
		typeCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				GenericSuWizardPage.this.isBc = ((Combo) e.widget).getSelectionIndex() == 0 ? true : false;
				validate();
			}
		});

		l = new Label( container, SWT.NONE );
		l.setText( "Component Name:" );
		l.setToolTipText( "The name of the target component" );

		Text text = new Text( container, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				GenericSuWizardPage.this.componentName = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		l = new Label( container, SWT.NONE );
		l.setText( "Component Version:" );
		l.setToolTipText( "The version of the target component" );

		text = new Text( container, SWT.SINGLE | SWT.BORDER );
		if( this.suTypeVersion != null )
			text.setText( this.suTypeVersion );

		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				GenericSuWizardPage.this.suTypeVersion = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		l = new Label( container, SWT.NONE );
		l.setText( "Service Unit Type:" );
		l.setToolTipText( "The service unit type (e.g. SOAP, FTP...)" );

		text = new Text( container, SWT.SINGLE | SWT.BORDER );
		if( this.suType != null )
			text.setText( this.suType );

		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				GenericSuWizardPage.this.suType = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		l = new Label( container, SWT.NONE );
		l.setText( "CDK Version:" );
		l.setToolTipText( "The version of the CDK to use" );

		Combo cdkCombo = new Combo( container, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
		cdkCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		cdkCombo.add( "" );
		cdkCombo.add( "4.x" );
		cdkCombo.add( "5.x" );
		cdkCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				String version = ((Combo) e.widget).getText();
				// TODO : commented to remove deps on editor to CDK
				// GenericSuWizardPage.this.cdkNamespaceUri = CdkXsdManager.getInstance().resolveCdkVersion( version.replace( 'x', '0' ));
				validate();
			}
		});


		// Complete the page
		validate();
		String msg = getErrorMessage();
		if( msg != null ) {
			setErrorMessage( null );
			setMessage( msg, IMessageProvider.INFORMATION );
		}

		setControl( container );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {
		// nothing
	}
}
