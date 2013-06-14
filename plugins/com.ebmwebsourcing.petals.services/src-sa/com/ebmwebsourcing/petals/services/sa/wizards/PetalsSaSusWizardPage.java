/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.navigator.ResourceComparator;

import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * The wizard page asking the SU projects to add to the created SA.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSaSusWizardPage extends WizardPage {

	/**
	 * The SU projects to associated with the SA.
	 */
	private final List<IProject> suProjects = new ArrayList<IProject>();


	/**
	 * The constructor.
	 * @param pageName
	 */
	public PetalsSaSusWizardPage( String pageName ) {
		super( pageName );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 10;
		layout.horizontalSpacing = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( container, SWT.NONE ).setText( "Select the SU projects to include:" );
		final CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(
					container, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL );

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 200;
		viewer.getTable().setLayoutData( layoutData );
		viewer.setContentProvider( new ArrayContentProvider() {
			@Override
			public Object[] getElements( Object inputElement ) {
				return ServiceProjectRelationUtils.getAllSuProjects().toArray();
			}
		});

		viewer.setLabelProvider( new LabelProvider () {
			@Override
			public String getText(Object element) {
				String text = "";
				if( element instanceof IProject )
					text = ((IProject) element).getName();

				return text;
			}
		});

		viewer.setComparator( new ResourceComparator( ResourceComparator.NAME ));
		viewer.addCheckStateListener( new ICheckStateListener() {
			public void checkStateChanged( CheckStateChangedEvent event ) {
				IProject p = (IProject) event.getElement();
				if( event.getChecked())
					PetalsSaSusWizardPage.this.suProjects.add( p );
				else
					PetalsSaSusWizardPage.this.suProjects.remove( p );

				validate();
			}
		})


		;

		viewer.setInput( new Object());
		setControl( container );
	}


	/**
	 * @return a list of SU projects to add to the SA
	 */
	public List<IProject> getSuProjectsToAdd() {
		return this.suProjects;
	}


	/**
	 * Validates the selection.
	 */
	private void validate() {

		ArrayList<IProject> alreadyUsed = new ArrayList<IProject> ();
		for( IProject p : this.suProjects ) {
			if( ServiceProjectRelationUtils.getReferencingSaProjects( p ).size() > 0 )
				alreadyUsed.add( p );
		}

		String msg = null;
		if( alreadyUsed.size() > 1 )
			msg = alreadyUsed.size() + " of the selected service units are already part of another Service Assembly project.";
		else if( alreadyUsed.size() == 1 )
			msg = alreadyUsed.get( 0 ).getName() + " is already included by another Service Assembly project.";

		setMessage( msg, IMessageProvider.INFORMATION );
	}
}
