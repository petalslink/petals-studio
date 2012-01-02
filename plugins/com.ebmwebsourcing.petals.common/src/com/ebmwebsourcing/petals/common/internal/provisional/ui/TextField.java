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

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TextField extends AbstractWizardWidget<String> {

	private int style = SWT.BORDER;
	private Text text;
	private Label label;
	private final boolean aligned;


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param overSeveralLines
	 */
	public TextField(
				String initialValue,
				Composite parent,
				String baseLabel,
				String labelSuffix,
				String labelTooltip,
				boolean isOptional,
				boolean overSeveralLines ) {

		this(
					initialValue, parent, baseLabel, labelSuffix,
					labelTooltip, isOptional, overSeveralLines, true );
	}


	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param overSeveralLines
	 * @param aligned true to align the label and the text, false to display them on separate lines
	 */
	public TextField(
				String initialValue,
				Composite parent,
				String baseLabel,
				String labelSuffix,
				String labelTooltip,
				boolean isOptional,
				boolean overSeveralLines,
				boolean aligned ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional );
		this.aligned = aligned;

		int addStyle = overSeveralLines ? SWT.MULTI : SWT.SINGLE;
		this.style = this.style | addStyle;
		createControls( parent );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createControls( Composite parent ) {

		if( this.aligned ) {
			this.label = new Label( parent, SWT.NONE );
			this.label.setText( this.labelText );
			this.label.setToolTipText( this.labelTooltip );

			this.text = new Text( parent, this.style );
			this.text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		}
		else {
			this.label = new Label( parent, SWT.NONE );
			this.label.setText( this.labelText );
			this.label.setToolTipText( this.labelTooltip );

			GridData layoutData = new GridData();
			layoutData.horizontalSpan = 2;
			layoutData.verticalIndent = 5;
			this.label.setLayoutData( layoutData );

			// this.text = new Text( parent, this.style );
			this.text = new Text( parent, SWT.MULTI | SWT.BORDER );
			layoutData = new GridData( SWT.FILL, SWT.FILL, true, true );
			layoutData.horizontalSpan = 2;
			this.text.setLayoutData( layoutData );
		}

		refreshAll();
		this.text.addModifyListener( new ModifyListener () {
			public void modifyText( ModifyEvent e ) {
				String value = TextField.this.text.getText();
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
	}
}
