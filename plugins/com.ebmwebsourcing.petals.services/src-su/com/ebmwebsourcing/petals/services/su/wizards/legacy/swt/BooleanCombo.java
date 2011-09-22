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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BooleanCombo extends AbstractWizardWidget<Boolean> {

	private Combo combo;
	private Label label;


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 */
	public BooleanCombo(
				Boolean initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional );
		createControls( parent );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createControls( Composite parent ) {

		this.label = new Label( parent, SWT.NONE );
		this.label.setText( this.labelText );
		this.label.setToolTipText( this.labelTooltip );

		this.combo = new Combo( parent, SWT.READ_ONLY | SWT.BORDER );
		this.combo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.combo.add( "True" );
		this.combo.add( "False" );

		refreshAll();
		this.combo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean value = BooleanCombo.this.combo.getSelectionIndex() == 0 ? true : false;
				setValue( value );
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #refreshAll()
	 */
	@Override
	protected void refreshAll() {

		if( this.combo == null || this.combo.isDisposed())
			return;

		if( getValue())
			this.combo.select( 0 );
		else
			this.combo.select( 1 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #isEmptyValue()
	 */
	@Override
	protected boolean isEmptyValue() {
		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #getValueAsString()
	 */
	@Override
	protected String getValueAsString() {
		return getValue().toString();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {
		this.label.setEnabled( enabled );
		this.combo.setEnabled( enabled );
	}
}
