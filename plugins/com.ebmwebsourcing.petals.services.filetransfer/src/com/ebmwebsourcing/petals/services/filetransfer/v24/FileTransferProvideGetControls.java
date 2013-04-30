/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferProvideGetControls {

	private Text readText, backupText;


	/**
	 * Creates the controls.
	 * @param container
	 * @param editorStyle
	 */
	public void createControls( Composite container, boolean editorStyle ) {

		Label l = SwtFactory.createLabel( container, "Read Directory *:", "The directory to read" );
		this.readText = SwtFactory.createDirectoryBrowser( container ).getText();
		if( editorStyle )
			l.setForeground( container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE ));

		l = SwtFactory.createLabel( container, "Backup Directory:", "The directory into which read files are moved (the temporary directory by default)" );
		this.backupText = SwtFactory.createDirectoryBrowser( container ).getText();
		if( editorStyle )
			l.setForeground( container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE ));
	}


	/**
	 * @return the readText
	 */
	public Text getReadText() {
		return this.readText;
	}


	/**
	 * @return the backupText
	 */
	public Text getBackupText() {
		return this.backupText;
	}
}
