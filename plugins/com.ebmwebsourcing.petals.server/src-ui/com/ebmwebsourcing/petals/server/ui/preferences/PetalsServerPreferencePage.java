/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.ui.preferences;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ebmwebsourcing.petals.server.PetalsServerPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServerPreferencePage
extends PreferencePage
implements IWorkbenchPreferencePage {

	public static final String START_IN_CONSOLE_MODE = "petals.server.start.in.console.mode";

	private FieldEditor startModeField;


	/**
	 * Constructor which defines the title, the description and the preference store of this page.
	 */
	public PetalsServerPreferencePage() {
		super( "Preferences for Petals servers" );
		setPreferenceStore( PetalsServerPlugin.getDefault().getPreferenceStore());
		setDescription( "The preferences used when interacting with Petals servers." );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage
	 * #init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		String[][] labelAndValues = new String[][] {
					new String[] { "Yes", MessageDialogWithToggle.ALWAYS },
					new String[] { "No", MessageDialogWithToggle.NEVER },
					new String[] { "Prompt", MessageDialogWithToggle.PROMPT }
		};

		this.startModeField = new RadioGroupFieldEditor(
					START_IN_CONSOLE_MODE, "Start in Console mode",
					3, labelAndValues, container, true );

		return container;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performOk()
	 */
	@Override
	public boolean performOk() {
		this.startModeField.store();
		return super.performOk();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performDefaults()
	 */
	@Override
	protected void performDefaults() {
		this.startModeField.loadDefault();
		super.performDefaults();
	}
}
