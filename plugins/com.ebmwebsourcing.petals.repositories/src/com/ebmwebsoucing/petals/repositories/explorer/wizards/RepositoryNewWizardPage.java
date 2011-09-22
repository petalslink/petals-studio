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
package com.ebmwebsoucing.petals.repositories.explorer.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsoucing.petals.repositories.explorer.model.QueryApiBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryNewWizardPage extends WizardPage {

	private String repositoryName, repositoryDescription;
	private final List<QueryApiBean> queryApiBeans = new ArrayList<QueryApiBean> ();


	/**
	 * Constructor.
	 * @param pageName
	 */
	public RepositoryNewWizardPage( String pageName ) {
		super( pageName );
		setTitle( "Service Repository" );
		setDescription( "Define the properties of a new service repository." );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		layout.horizontalSpacing = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// Name
		Label label = new Label( container, SWT.NONE );
		label.setText( "Name:" );
		label.setToolTipText( "The name of the repository to create" );

		Text text = new Text( container, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				RepositoryNewWizardPage.this.repositoryName = ((Text) e.widget).getText();
				validate();
			}
		});


		// Description
		label = new Label( container, SWT.NONE );
		label.setText( "Description:" );
		label.setToolTipText( "The description of the repository to create" );

		text = new Text( container, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				RepositoryNewWizardPage.this.repositoryDescription = ((Text) e.widget).getText();
				validate();
			}
		});


		// Separation
		Composite comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginTop = 15;
		comp.setLayout( layout );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		comp.setLayoutData( layoutData );

		label = new Label( comp, SWT.NONE );
		label.setText( "Select the Query API this repository supports." );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		label.setLayoutData( layoutData );


		// Supported API
		final ListViewer viewer = new ListViewer( comp, SWT.SINGLE | SWT.BORDER );
		viewer.getList().setLayoutData( new GridData( GridData.FILL_BOTH ));

		viewer.setContentProvider( new ArrayContentProvider());
		viewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				String result = "";
				if( element instanceof QueryApiBean )
					result = ((QueryApiBean) element).getQueryApi().toString()
					+ " - " + ((QueryApiBean) element).getQueryUri();
				return result;
			}
		});

		viewer.setInput( this.queryApiBeans );


		// Buttons to add / remove an API
		Composite buttonsContainer = new Composite( comp, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttonsContainer.setLayout( layout );
		buttonsContainer.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		Button addButton = new Button( buttonsContainer, SWT.PUSH );
		addButton.setText( "Add..." );
		addButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		addButton.setToolTipText( "Add a new Query API" );
		addButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				QueryApiBeanDefinitionDialog dlg = new QueryApiBeanDefinitionDialog( getShell(), null );
				if( dlg.open() == Window.OK ) {
					RepositoryNewWizardPage.this.queryApiBeans.add( dlg.getQueryApiBean());
					viewer.refresh();
					validate();
				}
			}
		});

		Button removeButton = new Button( buttonsContainer, SWT.PUSH );
		removeButton.setText( "Remove" );
		removeButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		removeButton.setToolTipText( "Remove a Query API" );
		removeButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				if( ! viewer.getSelection().isEmpty()) {
					Object o = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
					RepositoryNewWizardPage.this.queryApiBeans.remove( o );
					viewer.refresh();
					validate();
				}
			}
		});


		setControl( container );
	}


	/**
	 * Validates the wizard fields.
	 */
	private void validate() {

		if( StringUtils.isEmpty( this.repositoryName )) {
			setErrorMessage( "You must specify the repository name." );
			setPageComplete( false );

		} else if( this.queryApiBeans.size() == 0 ) {
			setErrorMessage( "You must specify at least one Query API." );
			setPageComplete( false );

		} else {
			setErrorMessage( null );
			setPageComplete( true );
		}
	}


	/**
	 * @return the name
	 */
	public String getRepositoryName() {
		return this.repositoryName;
	}


	/**
	 * @return the description
	 */
	public String getRepositoryDescription() {
		return this.repositoryDescription;
	}


	/**
	 * @return the queryApiBeans
	 */
	public List<QueryApiBean> getQueryApiBeans() {
		return this.queryApiBeans;
	}
}
