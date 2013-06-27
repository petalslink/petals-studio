/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v30.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PropertiesModelUIHelper;
import com.ebmwebsourcing.petals.services.cdk.api.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ProvidesFiletransfer20;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransfer3ProvidesJbiEditorContribution extends JbiEditorDetailsContribution {

	@Override
	public void addMainSUContent(
			AbstractEndpoint edpt,
			AbstractModel componentModel,
			AbstractModel cdkModel,
			FormToolkit toolkit,
			Composite mainTab,
			IFile editedFile ) {

		Composite composite = createCommonProvideSection( mainTab, toolkit );
		CDK5JBIEndpointUIHelper.createProvidesUI( edpt, cdkModel, toolkit, composite, editedFile );

		composite = createEditorSection( mainTab, toolkit, "File Transfer Parameters", true );
		PropertiesModelUIHelper.generateWidgets(
				toolkit, composite, componentModel,
				ProvidesFiletransfer20.FOLDER,
				ProvidesFiletransfer20.BACKUP_DIRECTORY,
				ProvidesFiletransfer20.FILENAME );
	}

	@Override
	public void addAdvancedSUContent(
			AbstractEndpoint edpt,
			AbstractModel componentModel,
			AbstractModel cdkModel,
			FormToolkit toolkit,
			Composite advancedTab ) {

		Composite composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		CDK5JBIEndpointUIHelper.generateDefaultCdkWidgetsForProvidesEditor( edpt, cdkModel, toolkit, composite );
	}
}
