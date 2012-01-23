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

package com.ebmwebsourcing.petals.services.eip.su;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.handlers.IHandlerService;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipWizardPage extends AbstractSuWizardPage {

	private Image tipImage, screenshotImg;


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the main structure
		Composite container = new Composite( parent, SWT.BORDER );
		container.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));

		GridLayout layout = new GridLayout( 2, false );
		layout.verticalSpacing = 23;
		layout.horizontalSpacing = 12;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );

		FormText ft = new FormText( container, SWT.WRAP );
		ft.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		ft.marginWidth = 7;

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 12;
		layoutData.widthHint = 380;
		ft.setLayoutData( layoutData );

		// Load images
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( "icons/obj16/smartmode_co.gif" );
			this.tipImage = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}

		ft.setImage( "tip", this.tipImage );

		// Add some text
		StringBuilder sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p><img href=\"tip\" /> <b>EIP Wizard</b></p>" );
		sb.append( "<p>The Petals EIP component cannot be configured using this wizard anymore.<br />" );
		sb.append( "Instead, <b>croquis</b> must be used. Croquis allow to design graphically an EIP chain." );
		sb.append( "An EIP croquis can be exported either as Petals projects or directly as a service assembly.</p>" );
		sb.append( "</form>" );
		ft.setText( sb.toString(), true, false );


		// Add the image and a link
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( "icons/screenshots/overview.png" );
			this.screenshotImg = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}

		new Label( container, SWT.NONE ).setImage( this.screenshotImg );
		ft = new FormText( container, SWT.NONE );
		ft.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		ft.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, false ));

		sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p>The picture on the left shows an example of EIP croquis.</p>" );
		sb.append( "<p>Click <a href=\"croquis\">here to open the croquis wizard</a>.</p>" );
		sb.append( "<p vspace=\"false\">Or click the <b>Finish</b> button to close this wizard.</p>" );
		sb.append( "</form>" );
		ft.setText( sb.toString(), true, false );


		// React to link activation
		ft.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				// Close the wizard, which does nothing anyway
				getWizard().getContainer().getShell().close();

				// Open the croquis wizard in a new dialog
				ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService( ICommandService.class );
				Command cmd = cmdService.getCommand( IWorkbenchCommandConstants.FILE_NEW );
				try {
					IParameter paramId = cmd.getParameter( IWorkbenchCommandConstants.FILE_NEW_PARM_WIZARDID );
					Parameterization parm = new Parameterization( paramId, "com.ebmwebsourcing.petals.common.croquisWizard" );

					ParameterizedCommand parmCommand = new ParameterizedCommand( cmd, new Parameterization[] { parm });
					IHandlerService ds = (IHandlerService) PlatformUI.getWorkbench().getService( IHandlerService.class );
					ds.executeCommand( parmCommand, null );

				} catch( Exception e1 ) {
					PetalsEipPlugin.log( e1, IStatus.ERROR );
				}
			}
		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.tipImage != null && ! this.tipImage.isDisposed())
			this.tipImage.dispose();

		if( this.screenshotImg != null && ! this.screenshotImg.isDisposed())
			this.screenshotImg.dispose();

		super.dispose();
	}
}
