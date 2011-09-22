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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * @param <T>
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class NumericSpinner<T extends Number> extends AbstractWizardWidget<T> {

	protected int increment, pageIncrement, digit;
	protected Spinner spinner;
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
	 * @param increment
	 * @param pageIncrement
	 * @param digit
	 */
	protected NumericSpinner(
				T initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional,

				int increment, int pageIncrement, int digit ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional );

		this.increment = increment;
		this.pageIncrement = pageIncrement;
		this.digit = digit < 0 ? 0 : digit;
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

		this.spinner = new Spinner( parent, SWT.BORDER );
		this.spinner.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.spinner.setMaximum( Integer.MAX_VALUE );
		this.spinner.setMinimum( Integer.MIN_VALUE );
		this.spinner.setIncrement( this.increment );
		this.spinner.setPageIncrement( this.pageIncrement );
		this.spinner.setDigits( this.digit );

		refreshAll();
		this.spinner.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				T value = getValue();
				value = divide( NumericSpinner.this.spinner.getSelection(), Math.pow( 10, NumericSpinner.this.digit ));
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

		if( this.spinner == null || this.spinner.isDisposed())
			return;

		T value = getValue();
		if( value != null ) {
			Number allDigits = multiply( value, Math.pow( 10, this.digit ));
			this.spinner.setSelection( allDigits.intValue());
		}
		else
			this.spinner.setSelection( 0 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #getValueAsString()
	 */
	@Override
	protected String getValueAsString() {
		T value = getValue();
		return value == null ? null : "" + value;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {
		this.label.setEnabled( enabled );
		this.spinner.setEnabled( enabled );
	}


	/**
	 * Divide value by divider.
	 * 
	 * @param value
	 * @param divider
	 * @return
	 */
	protected abstract T divide( int value, double divider );


	/**
	 * Multiply value by multiplier.
	 * 
	 * @param multiplier
	 * @param value
	 * @return
	 */
	protected abstract Number multiply( T value, double multiplier );
}
