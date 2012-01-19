/****************************************************************************
 * 
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.validation.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class ValidationEditorContribution extends EditorContributionSupport {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport#getProvidesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new ValidationProvidesEditorContribution();
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport#getConsumesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return null;
	}

}
