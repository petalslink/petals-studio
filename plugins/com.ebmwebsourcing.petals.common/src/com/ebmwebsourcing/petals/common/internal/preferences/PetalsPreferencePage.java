/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;

/**
 * Petals preferences page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private BooleanFieldEditor logJaxWsField;


	/**
	 * Constructor.
	 */
	public PetalsPreferencePage() {
		setDescription( "Configure the general preferences for Petals tools." );
		setPreferenceStore( PetalsCommonPlugin.getDefault().getPreferenceStore());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Log JAX-WS traces
		Group group = new Group( container, SWT.NONE );
		group.setLayout( new GridLayout());
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		layoutData.verticalIndent = 10;
		group.setLayoutData( layoutData );
		group.setText( "JAX-WS" );

		Composite subContainer = new Composite( group, SWT.NONE );
		subContainer.setLayout( new GridLayout());

		this.logJaxWsField = new BooleanFieldEditor(
					PreferencesManager.PREFS_LOG_JAXWS,
					"Log all the JAX-WS traces",
					subContainer );
		this.logJaxWsField.setPage( this );
		this.logJaxWsField.setPreferenceStore( getPreferenceStore());
		this.logJaxWsField.load();

		return container;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performOk()
	 */
	@Override
	public boolean performOk() {
		this.logJaxWsField.store();
		return super.performOk();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performDefaults()
	 */
	@Override
	protected void performDefaults() {
		this.logJaxWsField.loadDefault();
		super.performDefaults();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage
	 * #init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init( IWorkbench workbench ) {
		// nothing
	}
}