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
 
package com.ebmwebsourcing.petals.services.filetransfer.v24;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
	 * @param editorStyle
	 */
	public void createControls( Composite container, boolean editorStyle ) {

		Label l = SwtFactory.createLabel( container,  "Write Directory *:", "The directory in which the message content will be written" );
		this.directoryText = SwtFactory.createDirectoryBrowser( container ).getText();
		if( editorStyle )
			l.setForeground( container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE ));

		l = SwtFactory.createLabel( container, "Write Mode *:", "What part(s) of the message should be written" );
		this.copyModeViewer = SwtFactory.createDefaultComboViewer( container, true, true, CopyMode.values());
		if( editorStyle )
			l.setForeground( container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE ));

		l = SwtFactory.createLabel( container, "File Name:", "The base name of the file to write (will be appended the system date)" );
		this.filenameText = SwtFactory.createSimpleTextField( container, true );
		if( editorStyle )
			l.setForeground( container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE ));
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
