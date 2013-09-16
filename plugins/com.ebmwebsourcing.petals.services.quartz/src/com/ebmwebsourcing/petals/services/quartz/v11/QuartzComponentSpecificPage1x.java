/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.quartz.v11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.quartz.QuartzControls;
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


		// The controls
		QuartzControls controls = new QuartzControls();
		controls.createControls( res, false );


		// The CRON
		this.cron = (String) getNewlyCreatedEndpoint().eGet( QuartzPackage.Literals.QUARTZ_CONSUMES__CRON_EXPRESSION );
		if( this.cron != null )
			controls.getCronText().setText( this.cron );

		controls.getCronText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				QuartzComponentSpecificPage1x.this.cron = ((Text) e.widget).getText().trim();
				validate();
			}
		});


		// The message skeleton
		this.msgSkeleton = (String) getNewlyCreatedEndpoint().eGet( QuartzPackage.Literals.QUARTZ_CONSUMES__CONTENT );
		if( this.msgSkeleton != null )
			controls.getMsgText().setText( this.msgSkeleton );

		controls.getMsgText().addModifyListener( new ModifyListener() {
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
