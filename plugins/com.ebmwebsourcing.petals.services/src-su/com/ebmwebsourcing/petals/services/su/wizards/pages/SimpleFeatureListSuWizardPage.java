/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PropertiesModelUIHelper;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModelListener;

/**
 * @author Mickael Istria - EBM WebSourcing
 * @author Vincent Zurczak - Linagora
 */
public class SimpleFeatureListSuWizardPage extends AbstractSuWizardPage implements AbstractModelListener {

	private final AbstractModel model;
	private final String[] propertyNames;


	/**
	 * Constructor.
	 * @param model
	 * @param propertyNames
	 */
	public SimpleFeatureListSuWizardPage( AbstractModel model, String... propertyNames ) {
		this.propertyNames = propertyNames;
		this.model = model;
		model.addModelListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {
		if( this.model != null )
			this.model.removeModelListener( this );

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		setControl( container );

		PropertiesModelUIHelper.generateWidgets(
				new FormToolkit( getShell().getDisplay()),
				container, this.model, this.propertyNames );

		validate();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		String msg = null;
		for( String key : this.model.getPropertyKeys()) {
			msg = this.model.validatePropertyValue( key );
			if( msg != null ) {
				msg = msg.replace( key, PropertiesModelUIHelper.generateBaseLabelText( key ));
				break;
			}
		}

		updateStatus( msg );
		return msg == null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {
		super.setVisible( visible );

		// Do not display an error when it becomes visible
		if( visible && getErrorMessage() != null ) {
			setErrorMessage( null );
			setPageComplete( false );
		}
	}


	/**
	 * @param property
	 */
	@Override
	public void propertyChanged( String property ) {
		validate();
	}
}
