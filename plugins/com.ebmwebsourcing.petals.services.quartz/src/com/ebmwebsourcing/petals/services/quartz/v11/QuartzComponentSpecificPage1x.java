/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.quartz.v11;

import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.quartz.Messages;
import com.ebmwebsourcing.petals.services.quartz.PetalsQuartzPlugin;
import com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QuartzComponentSpecificPage1x extends AbstractSuWizardPage {

	private String msgSkeleton, cron;

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout( new GridLayout( 2, false ));
		setControl(res);


		// The CRON
		Label cronLabel = new Label(res, SWT.NONE);
		cronLabel.setText(Messages.cronExpression);
		Text cronText = new Text(res, SWT.BORDER);
		cronText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));

		Link cronLink = new Link(res, SWT.NONE);
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
							getShell(),
							Messages.couldNotOpenEditorTitle,
							Messages.couldNotOpenEditorMessage,
							new Status(IStatus.ERROR, PetalsQuartzPlugin.PLUGIN_ID, ex.getMessage()), 0).open();
				}
			}
		});

		this.cron = (String) getNewlyCreatedEndpoint().eGet( QuartzPackage.Literals.QUARTZ_CONSUMES__CRON_EXPRESSION );
		if( this.cron != null )
			cronText.setText( this.cron );

		cronText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				QuartzComponentSpecificPage1x.this.cron = ((Text) e.widget).getText().trim();
				validate();
			}
		});


		// The message skeleton
		Label l = new Label( res, SWT.NONE );
		l.setText( Messages.content );

		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 5;
		l.setLayoutData( layoutData );

		StyledText msgText = SwtFactory.createXmlTextViewer( res );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		msgText.getParent().setLayoutData( layoutData );

		this.msgSkeleton = (String) getNewlyCreatedEndpoint().eGet( QuartzPackage.Literals.QUARTZ_CONSUMES__CONTENT );
		if( this.msgSkeleton != null )
			msgText.setText( this.msgSkeleton );

		msgText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				QuartzComponentSpecificPage1x.this.msgSkeleton = ((StyledText) e.widget).getText().trim();
				validate();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		if( StringUtils.isEmpty( this.cron )) {
			this.updateStatus( "You have to provide a CRON expression to trigger service invocations." );
			return false;

		} else if( StringUtils.isEmpty( this.msgSkeleton )) {
			this.updateStatus( "You have to provide a valid XML message to send to the service." );
			return false;

		} else {
			if( ! DomUtils.isValidXmlDocument( this.msgSkeleton )) {
				updateStatus( "The XML message is an invalid XML document." );
				return false;
			}
		}

		// this.content = "<![CDATA[" + this.contentTextField.getText().trim() + "]]>";

		// EMF serialization will deal with CDATA escape (save options)
		getNewlyCreatedEndpoint().eSet( QuartzPackage.Literals.QUARTZ_CONSUMES__CRON_EXPRESSION, this.cron );
		getNewlyCreatedEndpoint().eSet( QuartzPackage.Literals.QUARTZ_CONSUMES__CONTENT, this.msgSkeleton );

		updateStatus( null );
		return true;
	}
}
