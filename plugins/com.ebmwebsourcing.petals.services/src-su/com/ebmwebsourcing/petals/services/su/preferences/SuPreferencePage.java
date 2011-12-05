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

package com.ebmwebsourcing.petals.services.su.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.services.su.Messages;

/**
 * Petals preference page for the SU plug-in.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private FieldEditor hideWrongConsumesField;


	/**
	 * Constructor which defines the title, the description and the preference store of this page.
	 */
	public SuPreferencePage() {
		super( Messages.SuPreferencePage_0 );
		setDescription( Messages.SuPreferencePage_1 );
		setPreferenceStore( PreferencesManager.getPreferenceStore());
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage
	 * #init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Consumes preferences
		Group group = new Group( container, SWT.NONE );
		group.setLayout( new GridLayout());
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		layoutData.verticalIndent = 10;
		group.setLayoutData( layoutData );
		group.setText( "Service Consumption" );

		Composite subContainer = new Composite( group, SWT.NONE );
		subContainer.setLayout( new GridLayout());

		this.hideWrongConsumesField = new BooleanFieldEditor(
					PreferencesManager.PREFS_HIDE_INCOMPATIBLE_SERVICES,
					"Hide incompatible services in consume dialogs.",
					subContainer );
		this.hideWrongConsumesField.setPage( this );
		this.hideWrongConsumesField.setPreferenceStore( getPreferenceStore());
		this.hideWrongConsumesField.load();

		return container;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performOk()
	 */
	@Override
	public boolean performOk() {
		this.hideWrongConsumesField.store();
		return super.performOk();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performDefaults()
	 */
	@Override
	protected void performDefaults() {
		this.hideWrongConsumesField.loadDefault();
		super.performDefaults();
	}
}