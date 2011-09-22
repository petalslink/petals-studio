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

import org.eclipse.swt.widgets.Composite;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DoubleSpinner extends NumericSpinner<Double> {

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
	protected DoubleSpinner(
				Double initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, 1, 100, 2 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.NumericSpinner
	 * #divide(int, double)
	 */
	@Override
	protected Double divide( int value, double divider ) {
		return value / divider;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.NumericSpinner
	 * #multiply(java.lang.Number, double)
	 */
	@Override
	protected Number multiply( Double value, double multiplier ) {
		return value * multiplier;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #isEmptyValue()
	 */
	@Override
	protected boolean isEmptyValue() {
		return getValue() == 0.0;
	}
}
