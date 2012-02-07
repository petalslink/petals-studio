/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24.editor;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferProvideGetControls;
import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferProvideWriteControls;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferProvidesJbiEditorContribution extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent( final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise ) {

		Composite composite = createEditorSection( mainTab, toolkit, "Identification", true );
		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, composite, ise);
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, composite, ise);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "File Transfer Parameters", true );
		if( "writefiles".equalsIgnoreCase( endpoint.getInterfaceName().getLocalPart())) {
			FileTransferProvideWriteControls controls = new FileTransferProvideWriteControls();
			controls.createControls( composite );

			IObservableValue widgetObservable = SWTObservables.observeDelayedValue( 300, SWTObservables.observeText( controls.getDirectoryText(), SWT.Modify ));
			IObservableValue iov = EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY );
			ise.getDataBindingContext().bindValue( widgetObservable, iov );

			widgetObservable = ViewersObservables.observeSingleSelection((ISelectionProvider) controls.getCopyModeViewer());
			iov = EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE );
			ise.getDataBindingContext().bindValue( widgetObservable, iov );

			widgetObservable = SWTObservables.observeDelayedValue( 300, SWTObservables.observeText( controls.getFilenameText(), SWT.Modify ));
			iov = EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN );
			ise.getDataBindingContext().bindValue( widgetObservable, iov );

		} else {
			FileTransferProvideGetControls controls = new FileTransferProvideGetControls();
			controls.createControls( composite );

			IObservableValue widgetObservable = SWTObservables.observeDelayedValue( 300, SWTObservables.observeText( controls.getReadText(), SWT.Modify ));
			IObservableValue iov = EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES__READ_DIRECTORY );
			ise.getDataBindingContext().bindValue( widgetObservable, iov );

			widgetObservable = SWTObservables.observeDelayedValue( 300, SWTObservables.observeText( controls.getBackupText(), SWT.Modify ));
			iov = EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY );
			ise.getDataBindingContext().bindValue( widgetObservable, iov );
		}

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		Cdk5Utils.generateDefaultCdkWidgetsForProvidesEditor( endpoint, toolkit, composite, ise );
	}
}
