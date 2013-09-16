/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.properties;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;

/**
 * The section to display the card ID of a service.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointSourcePropertySection extends AbstractPropertySection {

	private EndpointSource source;
	private Text nameText, idText;


	/**
	 * Constructor.
	 */
	public EndpointSourcePropertySection() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls( Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage ) {

		// Create the container
		super.createControls( parent, aTabbedPropertySheetPage );

		// Containing section
		Composite subParent = getWidgetFactory().createComposite( parent );
		GridLayout layout = new GridLayout();
		layout.marginWidth = 12;
		layout.marginHeight = 12;
		layout.verticalSpacing = 25;
		subParent.setLayout( layout );


		// First fields
		Section section = getWidgetFactory().createSection( subParent,
					ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setText( "Source Details" );

		Composite container = getWidgetFactory().createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		CLabel label = getWidgetFactory().createCLabel( container, "ID:" );
		label.setToolTipText( "The source ID" );
		this.idText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.idText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Name:" );
		label.setToolTipText( "The source name" );
		this.nameText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.nameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput( part, selection );
		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EndpointSource )
				this.source = (EndpointSource) input;
			else if( input instanceof EndpointBean )
				this.source = ((EndpointBean) input).getServiceUnit().getSource();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		if( this.idText != null
					&& ! this.idText.isDisposed()
					&& this.source != null ) {

			this.idText.setText( this.source.getId());
			this.idText.setToolTipText( this.source.getId());
			this.nameText.setText( this.source.getName());
			this.nameText.setToolTipText( this.source.getName());
		}
	}
}
