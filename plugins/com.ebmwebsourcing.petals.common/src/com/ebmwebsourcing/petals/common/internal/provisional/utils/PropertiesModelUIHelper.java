/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;

/**
 * @author Vincent Zurczak - Linagora
 */
public class PropertiesModelUIHelper {

	/**
	 * @param toolkit
	 * @param parent
	 * @param model
	 * @param propertyNames
	 * @return
	 */
	public static Map<String,Control> generateWidgets( FormToolkit toolkit, Composite parent, AbstractModel model, String... propertyNames ) {

		if( propertyNames == null )
			throw new IllegalArgumentException( "There must be at least one specified property." );

		Map<String,Control> result = new HashMap<String,Control> ();
		for( String property : propertyNames ) {
			generateLabel( parent, model, property );
			Control c = generateWidget( toolkit, parent, model, property );
			if( c != null )
				result.put( property, c );
		}

		return result;
	}


	/**
	 * Generates the base label for a property.
	 * @param key a key (not null)
	 * @return a formatted string
	 */
	public static String generateBaseLabelText( String key ) {

		String label = StringUtils.camelCaseToHuman( key );
		label = StringUtils.capitalize( label );

		return label;
	}


	/**
	 * Binds a widgets with the model.
	 * <p>
	 * Set the initial value and listen to changes.
	 * </p>
	 *
	 * @param widget
	 * @param model
	 * @param property
	 */
	public static void bind( Control widget, final AbstractModel model, final String property ) {

		String value = model.getTrimmedPropertyValue( property );
		if( widget instanceof Text ) {
			((Text) widget).setText( value );
			((Text) widget).addModifyListener( new ModifyListener() {
				@Override
				public void modifyText( ModifyEvent e ) {
					String value = ((Text) e.widget).getText().trim();
					model.set( property, value );
				}
			});

		} else if( widget instanceof Combo ) {
			int index = ((Combo) widget).indexOf( value );
			if( index != -1 )
				((Combo) widget).select( index );

			((Combo) widget).addSelectionListener( new DefaultSelectionListener() {
				@Override
				public void widgetSelected( SelectionEvent e ) {
					String value = ((Combo) e.widget).getText();
					model.set( property, value );
				}
			});

		} else if( widget instanceof Spinner ) {
			try {
				int selection = Integer.valueOf( value );
				((Spinner) widget).setSelection( selection );

			} catch( NumberFormatException e1 ) {
				PetalsCommonPlugin.log( e1, IStatus.ERROR );
			}

			((Spinner) widget).addModifyListener(  new ModifyListener() {
				@Override
				public void modifyText( ModifyEvent e ) {
					String value = ((Spinner) e.widget).getText().trim();
					model.set( property, value );
				}
			});

		} else {
			PetalsCommonPlugin.log( "Could not bind widget: " + widget.getClass().getSimpleName(), IStatus.ERROR );
		}
	}


	/**
	 * Generates a label from a property name.
	 * @param parent the composite parent
	 * @param model the abstract model
	 * @param property the property name
	 */
	private static void generateLabel( Composite parent, AbstractModel model, String property ) {

		String label = generateBaseLabelText( property );
		if( model.isRequired( property ))
			label += " *";
		label += ":";

		Label l = new Label( parent, SWT.NONE );
		l.setText( label );

		String doc = model.getDocumentation( property );
		if( ! StringUtils.isEmpty( doc ))
			l.setToolTipText( doc );
	}


	/**
	 *
	 * @param toolkit
	 * @param parent the composite parent
	 * @param model the abstract model
	 * @param property the property name
	 */
	private static Control generateWidget( FormToolkit toolkit, Composite parent, final AbstractModel model, final String property ) {

		// Create the UI
		Control widget = null;
		switch( model.getType( property )) {
		case BOOLEAN:
			ComboViewer viewer = new ComboViewer( parent, SWT.READ_ONLY | SWT.FLAT );
			viewer.setContentProvider( new ArrayContentProvider());
			viewer.setLabelProvider( new LabelProvider());
			viewer.setInput( new Boolean[] { Boolean.TRUE, Boolean.FALSE });

			widget = viewer.getCombo();
			GridDataFactory.swtDefaults().hint( 100, SWT.DEFAULT ).minSize( 100, SWT.DEFAULT ).applyTo( widget );
			break;

		case ENUMERATION:
			viewer = new ComboViewer( parent, SWT.READ_ONLY | SWT.FLAT );
			viewer.setContentProvider( new ArrayContentProvider());
			viewer.setLabelProvider( new LabelProvider());
			viewer.setInput( model.getEnumeration( property ));
			viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			widget = viewer.getCombo();
			break;

		case LONG:
		case INTEGER:
			widget = new Spinner( parent, SWT.BORDER );
			GridDataFactory.swtDefaults().hint( 100, SWT.DEFAULT ).minSize( 100, SWT.DEFAULT ).applyTo(widget);
			((Spinner) widget).setMaximum( Integer.MAX_VALUE );
			break;

		case STRING:
			String lowered = property.toLowerCase();
			if( lowered.contains( "password" ) || lowered.contains( "passphrase" ))
				widget = SwtFactory.createPasswordField( parent, false ).getText();
			else if( lowered.contains( "folder" ) || lowered.contains( "directory" ))
				widget = SwtFactory.createDirectoryBrowser( parent ).getText();
			else
				widget = toolkit.createText(parent, "", SWT.BORDER);

			((Text) widget).setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			break;

		case DOUBLE:
		case FLOAT:
		case LIST:
			widget = toolkit.createText(parent, "", SWT.BORDER);
			((Text) widget).setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			break;
		}

		if( widget != null )
			bind( widget, model, property );

		return widget;
	}
}
