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

package com.ebmwebsourcing.petals.services.quartz;

import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;


/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QuartzControls {

	private Text cronText;
	private StyledText msgText;


	/**
	 * Creates the Quartz controls.
	 * @param parent the parent
	 */
	public void createControls( final Composite parent ) {

		SwtFactory.createLabel( parent, Messages.cronExpression, "A CRON expression to schedule service invocations" );
		this.cronText = SwtFactory.createSimpleTextField( parent, true );

		Link cronLink = new Link( parent, SWT.NONE );
		cronLink.setText("<A>" + Messages.cronHelp + "</A>");
		cronLink.setLayoutData(new GridData(SWT.RIGHT, SWT.DEFAULT, true, false, 2, 1));
		cronLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
					browser.openURL( new URL("http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger"));

				} catch (Exception ex) {
					PetalsQuartzPlugin.log( ex, IStatus.ERROR );
					new ErrorDialog(
							parent.getShell(),
							Messages.couldNotOpenEditorTitle,
							Messages.couldNotOpenEditorMessage,
							new Status(IStatus.ERROR, PetalsQuartzPlugin.PLUGIN_ID, ex.getMessage()), 0).open();
				}
			}
		});


		// The message skeleton
		Label l = SwtFactory.createLabel( parent, Messages.content, "A XML message to send to the target service" );
		GridDataFactory.swtDefaults().indent( 0, 5 ).span( 2, 1 ).applyTo( l );

		this.msgText = SwtFactory.createXmlTextViewer( parent );
		GridDataFactory.swtDefaults().align( SWT.FILL, SWT.CENTER ).span( 2, 1 ).applyTo( this.msgText );
	}


	/**
	 * @return the cronText
	 */
	public Text getCronText() {
		return this.cronText;
	}


	/**
	 * @return the msgText
	 */
	public StyledText getMsgText() {
		return this.msgText;
	}
}
