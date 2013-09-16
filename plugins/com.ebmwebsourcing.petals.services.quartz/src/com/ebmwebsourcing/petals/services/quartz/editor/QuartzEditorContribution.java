/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.quartz.editor;

import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class QuartzEditorContribution extends EditorContributionSupport {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport
	 * #getProvidesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport
	 * #getConsumesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new QuartzConsumesEditorContribution();
	}
}
