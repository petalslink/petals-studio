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

package com.ebmwebsourcing.petals.services.filetransfer.v30.wizard;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormText;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.filetransfer.generated.ConsumesFiletransfer20;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * A page to select the invocation mode.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferConsumesWizardPage30 extends AbstractSuWizardPage {

	private String xmlContent;
	private boolean useMsgSkeleton, contentTransfer;


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Controls
		Composite container = new Composite( parent, SWT.BORDER );
		GridLayoutFactory.swtDefaults().extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );

		SwtFactory.createLabel( container, "Select the Transfer Mode:", "What will be transfered in the bus and how" );
		Combo combo =  SwtFactory.createDropDownCombo( container, true, true );
		combo.add( "By Content: transfered files must be XML files. File content = Message content." );
		combo.add( "By Attachment: files are transfered as attachments. The message content is static." );
		combo.add( "By Contract: the sent message is based on a skeleton to match the WSDL of the target service." );

		FormText ft = new FormText( container, SWT.WRAP );
		GridDataFactory.swtDefaults().indent( 0, 20 ).align( SWT.FILL, SWT.CENTER ).hint( 500, SWT.DEFAULT ).applyTo( ft );

		StringBuilder sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p>Define the skeleton of the message to send to the invoked service. The following variables are available:</p>" );
		sb.append( "<li><b>$content</b>: will be replaced by the file content.</li>" );
		sb.append( "<li><b>$attachment</b>: will set the file in attachment. It will be replaced by a xop:include element which references the attachment (MTOM-like).</li>" );
		sb.append( "</form>" );

		ft.setText( sb.toString(), true, false );
		final StyledText xmlViewer = SwtFactory.createXmlTextViewer( container );
		GridDataFactory.swtDefaults().align( SWT.FILL, SWT.FILL ).grab( true, true ).minSize( SWT.DEFAULT, 150 ).applyTo( xmlViewer.getParent());
		xmlViewer.setText( "<!-- Skeleton of XML Document -->" );


		// Initialize
		combo.select( 0 );
		xmlViewer.setEnabled( false );
		xmlViewer.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND ));


		// Listeners
		xmlViewer.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				FileTransferConsumesWizardPage30.this.xmlContent = xmlViewer.getText().trim();
				getWizard().getSuModel().getComponentModel().set(
						ConsumesFiletransfer20.BASE_MESSAGE,
						FileTransferConsumesWizardPage30.this.xmlContent );

				validate();
			}
		});

		combo.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				int index = ((Combo) e.widget).getSelectionIndex();
				Color bgColor;
				if( index == 0 ) {
					bgColor = getShell().getDisplay().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND );
					FileTransferConsumesWizardPage30.this.useMsgSkeleton = false;
					FileTransferConsumesWizardPage30.this.contentTransfer = true;

				} else if( index == 1 ) {
					bgColor = getShell().getDisplay().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND );
					FileTransferConsumesWizardPage30.this.useMsgSkeleton = false;
					FileTransferConsumesWizardPage30.this.contentTransfer = false;

				} else {
					bgColor = getShell().getDisplay().getSystemColor( SWT.COLOR_WHITE );
					FileTransferConsumesWizardPage30.this.useMsgSkeleton = true;
				}

				xmlViewer.setEnabled( FileTransferConsumesWizardPage30.this.useMsgSkeleton );
				xmlViewer.setBackground( bgColor );
				validate();
			}
		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage#validate()
	 */
	@Override
	public boolean validate() {

		String msg = null;
		if( this.useMsgSkeleton ) {
			if( StringUtils.isEmpty( this.xmlContent ))
				msg = "You must provide a message skeleton.";
			else if( ! DomUtils.isValidXmlDocument( this.xmlContent ))
				msg = "The XML message is an invalid XML document.";
		}

		updateStatus( msg );
		return msg == null;
	}


	/**
	 * @return the xmlContent
	 */
	public String getXmlContent() {
		return this.xmlContent;
	}


	/**
	 * @return the useMsgSkeleton
	 */
	public boolean isUseMsgSkeleton() {
		return this.useMsgSkeleton;
	}


	/**
	 * @return the contentTransfer
	 */
	public boolean isContentTransfer() {
		return this.contentTransfer;
	}
}
