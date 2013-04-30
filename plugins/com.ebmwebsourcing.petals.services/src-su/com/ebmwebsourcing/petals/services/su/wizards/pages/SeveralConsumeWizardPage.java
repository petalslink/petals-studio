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

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SeveralConsumeWizardPage extends AbstractSuWizardPage {

	protected  final Set<EndpointBean> edptBeans = new HashSet<EndpointBean>();

	private PCStyledLabelProvider labelProvider;
	private TableViewer viewer;


	/**
	 * @param pageName the page name
	 * @param suType the SU type
	 * @param suTypeVersion the version of the SU type (of its component)
	 */
	public SeveralConsumeWizardPage( String pageName, String suType, String suTypeVersion ) {
		super();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.labelProvider != null )
			this.labelProvider.dispose();

		super.dispose();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		if( this.edptBeans.size() == 0 ) {
			updateStatus( "You must select at least one service end-point." );
			return false;
		}

		updateStatus( null );
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// First page of the wizard: force the size
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.widthHint = 500;
		layoutData.heightHint = 340;
		parent.setLayoutData( layoutData );

		/* create the composite container and define its layout */
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout();
		layout.marginLeft = 15;
		layout.marginTop = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Add the other widgets
		Label l = new Label( container, SWT.NONE );
		l.setText( "Select the end-points to watch." );

		// The end-point table
		Composite subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		this.viewer = new TableViewer( subContainer, SWT.BORDER | SWT.SINGLE );
		this.viewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.viewer.setContentProvider( new ArrayContentProvider());
		this.labelProvider = new PCStyledLabelProvider( container );
		this.viewer.setLabelProvider( new DelegatingStyledCellLabelProvider( this.labelProvider ));

		// The buttons
		Composite buttonsComposite = new Composite( subContainer, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.BEGINNING, false, true ));

		Button addButton = new Button( buttonsComposite, SWT.PUSH );
		addButton.setText( "&Add" );
		addButton.setToolTipText( "Displays the end-points from the Petals Services view" );
		addButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		addButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( container );
				if( bean != null ) {
					SeveralConsumeWizardPage.this.edptBeans.add( bean );
					SeveralConsumeWizardPage.this.viewer.setInput( SeveralConsumeWizardPage.this.edptBeans );
					SeveralConsumeWizardPage.this.viewer.refresh();
					validate();
				}
			}
		});

		Button removeButton = new Button( buttonsComposite, SWT.PUSH );
		removeButton.setText( "&Remove" );
		removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		removeButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				if( SeveralConsumeWizardPage.this.viewer.getSelection().isEmpty())
					return;

				EndpointBean bean = (EndpointBean)
				((IStructuredSelection) SeveralConsumeWizardPage.this.viewer.getSelection()).getFirstElement();
				SeveralConsumeWizardPage.this.edptBeans.remove( bean );
				SeveralConsumeWizardPage.this.viewer.setInput( SeveralConsumeWizardPage.this.edptBeans );
				SeveralConsumeWizardPage.this.viewer.refresh();
				validate();
			}
		});

		// End-up correctly
		setControl( container );
		validate();
		setErrorMessage( null );
	}


	/**
	 * @return the edptBeans
	 */
	public Set<EndpointBean> getEndpointBeans() {
		return this.edptBeans;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #canFlipToNextPage()
	 */
	@Override
	public boolean canFlipToNextPage() {
		return this.edptBeans.size() > 0 && getNextPage() != null;
	}
}
