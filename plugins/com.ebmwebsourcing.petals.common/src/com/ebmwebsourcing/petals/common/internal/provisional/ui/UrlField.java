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

import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.validation.URLValidator;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class UrlField extends TextField {

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
	public UrlField(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, false );
		this.validators.add( new URLValidator ());
	}
}
