/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.legacy.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class BrowserWidget extends AbstractWizardWidget<String> {

	protected Text text;
	protected Button browse;
	protected Label label;
	private final boolean alignAll;


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param alignAll
	 */
	protected BrowserWidget(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional, boolean alignAll ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional );
		this.alignAll = alignAll;
		createControls( parent );
	}


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param label
	 * @param labelTooltip
	 * @param isOptional
	 * @param alignAll
	 */
	protected BrowserWidget(
				String initialValue, Composite parent,
				String label,
				String labelTooltip, boolean isOptional, boolean alignAll ) {

		super( initialValue, parent, label, labelTooltip, isOptional );
		this.alignAll = alignAll;
		createControls( parent );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createControls( Composite parent ) {

		Composite browser = new Composite( parent, SWT.NONE );
		browser.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		GridLayout layout;
		if( this.alignAll ) {
			layout = new GridLayout( 3, false );
			this.label = new Label( browser, SWT.NONE );
		}
		else {
			layout = new GridLayout( 2, false );
			this.label = new Label( browser, SWT.NONE );
			GridData layoutData = new GridData();
			layoutData.horizontalSpan = 2;
			this.label.setLayoutData( layoutData );
		}

		layout.marginWidth = layout.marginHeight = 0;
		browser.setLayout( layout );

		this.label.setText( this.labelText );
		this.label.setToolTipText( this.labelTooltip );

		this.text = new Text( browser, SWT.SINGLE | SWT.BORDER );
		this.text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.browse = new Button( browser, SWT.PUSH );
		this.browse.setText( "Browse..." );
		this.browse.addSelectionListener( new SelectionListener () {
			public void widgetDefaultSelected( SelectionEvent e ) {
				activateButtonListener();
			}

			public void widgetSelected( SelectionEvent e ) {
				activateButtonListener();
			}
		});

		refreshAll();
		this.text.addModifyListener( new ModifyListener () {
			public void modifyText( ModifyEvent e ) {
				String value = BrowserWidget.this.text.getText();
				setValue( value );
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #refreshAll()
	 */
	@Override
	protected void refreshAll() {

		if( this.text == null || this.text.isDisposed())
			return;

		String value = getValue();
		if( value != null )
			this.text.setText( value );
		else
			this.text.setText( "" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #isEmptyValue()
	 */
	@Override
	protected boolean isEmptyValue() {
		return StringUtils.isEmpty( getValue());
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #getValueAsString()
	 */
	@Override
	protected String getValueAsString() {
		return getValue();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {
		this.label.setEnabled( enabled );
		this.text.setEnabled( enabled );
		this.browse.setEnabled( enabled );
	}


	/**
	 * Define what's happening when the button is clicked.
	 */
	protected abstract void activateButtonListener();
}
