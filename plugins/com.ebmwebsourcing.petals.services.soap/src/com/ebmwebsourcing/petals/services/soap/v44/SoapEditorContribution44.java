/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v44;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

/**
 * @author Victor Noel - Linagora
 */
public class SoapEditorContribution44 extends EditorContributionSupport {

	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new SoapProvidesEditorContribution44();
	}

	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new SoapConsumesEditorContribution44();
	}
}
