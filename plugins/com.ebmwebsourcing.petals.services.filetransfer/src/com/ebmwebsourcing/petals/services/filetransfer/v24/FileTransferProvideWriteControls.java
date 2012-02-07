/******************************************************************************
 * Copyright (c) 2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferProvideWriteControls {

	private Text directoryText, filenameText;
	private ComboViewer copyModeViewer;


	/**
	 * Creates the controls.
	 * @param container
	 */
	public void createControls( Composite container ) {

		SwtFactory.createLabel( container,  "Write Directory *:", "The directory in which the message content will be written" );
		this.directoryText = SwtFactory.createDirectoryBrowser( container ).getText();

		SwtFactory.createLabel( container, "Write Mode *:", "What part(s) of the message should be written" );
		this.copyModeViewer = SwtFactory.createDefaultComboViewer( container, true, true, CopyMode.values());

		SwtFactory.createLabel( container, "File Name:", "The base name of the file to write (will be appended the system date)" );
		this.filenameText = SwtFactory.createSimpleTextField( container, true );
	}


	/**
	 * @return the directoryText
	 */
	public Text getDirectoryText() {
		return this.directoryText;
	}


	/**
	 * @return the filenameText
	 */
	public Text getFilenameText() {
		return this.filenameText;
	}


	/**
	 * @return the copyModeViewer
	 */
	public ComboViewer getCopyModeViewer() {
		return this.copyModeViewer;
	}
}
