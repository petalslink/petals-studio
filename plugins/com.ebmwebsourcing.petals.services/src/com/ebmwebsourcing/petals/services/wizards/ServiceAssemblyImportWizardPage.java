/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.explorer.sources.SaDirectorySource;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceAssemblyImportWizardPage extends AbstractPetalsServiceCreationWizardPage {

	private boolean reloadSa = false;
	private final Map<File,SaDirectorySource> saFileToSaDirectorySource =
		new HashMap<File,SaDirectorySource> ();


	/**
	 * Constructor.
	 */
	public ServiceAssemblyImportWizardPage() {
		super( "Service Assembly Import", "Import a Service Assembly as a set of Petals projects." );
	}


	/**
	 * Validates the page entries.
	 */
	@Override
	protected boolean validate() {

		// SA file
		if( this.saFileToSaDirectorySource.isEmpty()) {
			setErrorMessage( "You must select at least one Service Assembly to import." );
			setPageComplete( false );
			return false;
		}

		for( File f : this.saFileToSaDirectorySource.keySet()) {
			if( ! f.exists()) {
				setErrorMessage( f.getAbsolutePath() + " does not exist." );
				setPageComplete( false );
				return false;
			}
		}

		// Super validation
		if( ! super.validate())
			return false;

		// Validation succeeded, reload the SA
		IWorkspaceRoot wr = ResourcesPlugin.getWorkspace().getRoot();
		if( this.reloadSa ) {

			List<SaImportBean> saBeans = new ArrayList<SaImportBean>();
			for( Map.Entry<File,SaDirectorySource> entry : this.saFileToSaDirectorySource.entrySet()) {

				// Build the SA directory source
				SaDirectorySource saSrc = entry.getValue();
				if( saSrc != null ) {
					saSrc.dispose();
					saSrc = null;
				}

				saSrc = new SaDirectorySource( entry.getKey(), true, true );
				this.saFileToSaDirectorySource.put( entry.getKey(), saSrc );

				// Get the SU
				SaImportBean saBean = null;
				for( ServiceUnitBean sub : saSrc.getServiceUnits()) {

					// Create the SA only once
					if( saBean == null ) {
						saBean = new SaImportBean();
						saBean.setProjectName( sub.getServiceAssemblyId());
						saBean.setJbiXmlLocation( sub.getSaJbiXmlLocation());

						IProject p = wr.getProject( sub.getServiceAssemblyId());
						saBean.setToCreate( ! p.exists());
						saBeans.add( saBean );
					}

					// Create the SU
					SuImportBean suBean = new SuImportBean();
					String suName = sub.getServiceUnitName();

					suBean.setProjectName( suName );
					suBean.setComponentName( sub.getComponentName());
					suBean.setJbiXmlLocation( sub.getJbiXmlLocation());

					// Resolve a version to propose and the SU type
					suBean.setSuType( ExtensionManager.INSTANCE.findComponentAlias( sub.getComponentName()));
					Set<String> knownVersions = ExtensionManager.INSTANCE.findComponentVersions( sub.getComponentName());
					List<String> displayOrder = new ArrayList<String> ( knownVersions );
					Collections.reverse( displayOrder );

					// Handle unknown versions
					if( displayOrder.isEmpty())
						displayOrder.add( "1.0-SNAPSHOT" );

					String[] versions = new String[ displayOrder.size()];
					suBean.setSupportedVersions( displayOrder.toArray( versions ));
					suBean.setComponentVersion( suBean.getSupportedVersions()[ 0 ]);

					IProject p = wr.getProject( suName );
					suBean.setToCreate( ! p.exists());
					saBean.addSuBean( suBean );
				}
			}

			// Update the viewer
			updateImportBeans( saBeans, true );
			this.reloadSa = false;
		}

		setErrorMessage( null );
		setPageComplete( true );
		return true;
	}


	/**
	 * Disposes the resources.
	 */
	@Override
	public void releaseResources() {

		for( SaDirectorySource saSrc : this.saFileToSaDirectorySource.values()) {
			if( saSrc != null ) {
				saSrc.dispose();
				saSrc = null;
			}
		}

		this.saFileToSaDirectorySource.clear();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #createWidgetsBeforeProjectLocation(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgetsBeforeProjectLocation( Composite container ) {

		Label l = new Label( container, SWT.NONE );
		l.setText( "Service Assembly locations:" );

		Composite locContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = layout.marginWidth = 0;
		locContainer.setLayout( layout );
		locContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final org.eclipse.swt.widgets.List list =
			new org.eclipse.swt.widgets.List( locContainer, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.heightHint = 60;
		list.setLayoutData( layoutData );

		Button b = new Button( locContainer, SWT.PUSH );
		b.setText( "Browse..." );
		b.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));
		b.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( list.getShell(), SWT.MULTI );
				dlg.setFilterNames( new String[] { "Service Assembly (*.zip)" }); //$NON-NLS-1$
				dlg.setFilterExtensions( new String[] { "*.zip" }); //$NON-NLS-1$
				String path = dlg.open();

				if( path != null ) {

					// Clear all the SA directories
					releaseResources();

					// Add the selected files
					List<String> paths = new ArrayList<String> ();
					for( String s : dlg.getFileNames()) {
						File f = new File( dlg.getFilterPath(), s );
						ServiceAssemblyImportWizardPage.this.saFileToSaDirectorySource.put( f, null );
						paths.add( f.getAbsolutePath());
					}

					// Update the text
					String[] items = new String[ paths.size()];
					list.setItems( paths.toArray( items ));
					list.setFocus();

					ServiceAssemblyImportWizardPage.this.reloadSa = true;
					validate();
				}
			}
		});
	}
}
