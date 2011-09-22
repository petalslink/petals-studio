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

import com.ebmwebsourcing.petals.services.su.wizards.legacy.validation.URIValidator;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class UriField extends TextField {

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
	public UriField(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, false );
		this.validators.add( new URIValidator ());
	}
}
