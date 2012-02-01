/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v3.editor;

import org.eclipse.emf.ecore.EClass;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages.DefaultJbiEditorContribution;

/**
 * @author Micka�l Istria - EBM WebSourcing
 */
public class FileTransfer3ContributionSupport extends EditorContributionSupport {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport
	 * #getProvidesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getProvidesContribution() {
		return new FileTransfer3ProvidesJbiEditorContribution();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.EditorContributionSupport
	 * #getConsumesContribution()
	 */
	@Override
	protected JbiEditorDetailsContribution getConsumesContribution() {
		return new FileTransfer3ConsumesJbiEditorContribution();
	}
}
