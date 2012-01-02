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

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EnumCombo extends AbstractWizardWidget<String> {

	private Combo combo;
	private Label label;
	private Collection<String> choices;


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param choices
	 */
	public EnumCombo(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional, Collection<String> choices ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional );

		this.choices = choices;
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

		refreshAll();
		this.combo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String value = EnumCombo.this.combo.getText();
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

		this.combo.removeAll();
		if( this.choices != null ) {
			for( String choice : this.choices )
				this.combo.add( choice );
		}

		String value = getValue();
		if( value != null && this.choices.contains( value )) {
			int index = this.combo.indexOf( value );
			this.combo.select( index );
		}
	}


	/**
	 * @param choices the choices to set
	 */
	public void setChoices( Collection<String> choices ) {
		this.choices = choices;
		refreshAll();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #isEmptyValue()
	 */
	@Override
	protected boolean isEmptyValue() {
		return StringUtils.isEmpty( getValue());
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #getValueAsString()
	 */
	@Override
	protected String getValueAsString() {
		return getValue();
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
