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

package com.ebmwebsourcing.petals.services.su.properties;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;

/**
 * A property page for SU projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuPropertyPage extends PropertyPage {

	private Text componentNameText, componentIdText, componentVersionText;
	private Text componentFunctionText;
	private IProject selectedProject;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout( 2, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		new Label( container, SWT.NONE ).setText( "Component Name:" );
		this.componentNameText = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.componentNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( container, SWT.NONE ).setText( "Component Deployment ID:" );
		this.componentIdText = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.componentIdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( container, SWT.NONE ).setText( "Component Version:" );
		this.componentVersionText = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.componentVersionText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( container, SWT.NONE ).setText( "Function:" );
		this.componentFunctionText = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.componentFunctionText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.selectedProject = (IProject) getElement();
		Properties projectProperties = PetalsSPPropertiesManager.getProperties( this.selectedProject );
		String componentName = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_NAME, "" );
		this.componentNameText.setText( componentName );

		String componentId = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, "" );
		this.componentIdText.setText( componentId );

		String componentVersion = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_VERSION, "" );
		this.componentVersionText.setText( componentVersion );

		String suType = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_FUNCTION, "" );
		this.componentFunctionText.setText( suType );

		return container;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {

		String componentName = this.componentNameText.getText();
		String componentId = this.componentIdText.getText();
		String componentVersion = this.componentVersionText.getText();
		String suType = this.componentFunctionText.getText();

		Properties projectProperties = new Properties();
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_FUNCTION, suType );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_NAME, componentName );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, componentId );
		projectProperties.put( PetalsSPPropertiesManager.COMPONENT_VERSION, componentVersion );
		try {
			PetalsSPPropertiesManager.saveProperties( projectProperties, this.selectedProject );

		} catch( Exception e ) {
			MessageDialog.openError( getShell(), "Error", "An error occurred while saving the properties." );
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		performApply();
		return super.performOk();
	}
}
