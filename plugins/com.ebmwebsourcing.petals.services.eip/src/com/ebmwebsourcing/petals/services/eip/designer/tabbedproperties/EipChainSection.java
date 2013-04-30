/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipChainSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipChainEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;

/**
 * The main (and only one) section for the Petals tab (properties of a JBI binding).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChainSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipChain eipChain;
	private boolean enableListener;

	private Text titleText, descText, versionText;


	/**
	 * Constructor.
	 */
	public EipChainSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipChain.PROPERTY_CHAIN_DESCRIPTION.equals( evt.getPropertyName())
					|| EipChain.PROPERTY_CHAIN_VERSION.equals( evt.getPropertyName())
					|| EipChain.PROPERTY_CHAIN_TITLE.equals( evt.getPropertyName()))
			refresh();
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
		Composite container = getWidgetFactory().createPlainComposite( parent, SWT.NONE );
		container.setLayout( new GridLayout( 4, false ));

		// Fields
		CLabel label = getWidgetFactory().createCLabel( container, "Title:" );
		label.setToolTipText( "The title of the diagram" );

		this.titleText = getWidgetFactory().createText( container, "" );
		this.titleText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.titleText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipChainSection.this.enableListener ) {
					EipChainSetAttributeCommand cmd =
						new EipChainSetAttributeCommand( EipChain.PROPERTY_CHAIN_TITLE );

					cmd.setEipChain( EipChainSection.this.eipChain );
					cmd.setNewValue( EipChainSection.this.titleText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Version:" );
		label.setToolTipText( "The version of the diagram" );

		this.versionText = getWidgetFactory().createText( container, "" );
		this.versionText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.versionText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipChainSection.this.enableListener ) {
					EipChainSetAttributeCommand cmd =
						new EipChainSetAttributeCommand( EipChain.PROPERTY_CHAIN_VERSION );

					cmd.setEipChain( EipChainSection.this.eipChain );
					cmd.setNewValue( EipChainSection.this.versionText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Description:" );
		label.setToolTipText( "A description of the diagram and the EIP chain" );
		label.setLayoutData( new GridData( SWT.TOP, SWT.DEFAULT, false, false ));

		this.descText = getWidgetFactory().createText( container, "", SWT.MULTI );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 3;
		layoutData.heightHint = 70;
		this.descText.setLayoutData( layoutData );
		this.descText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EipChainSection.this.enableListener ) {
					EipChainSetAttributeCommand cmd =
						new EipChainSetAttributeCommand( EipChain.PROPERTY_CHAIN_DESCRIPTION );

					cmd.setEipChain( EipChainSection.this.eipChain );
					cmd.setNewValue( EipChainSection.this.descText.getText());
					executeCommand( cmd );
				}
			}
		});
	}


	/**
	 * Executes a command on top of the EIP editor's command stack.
	 * @param command
	 */
	private void executeCommand( Command command ) {

		IEditorPart part = getPart().getSite().getPage().getActiveEditor();
		CommandStack commandStack = (CommandStack) part.getAdapter( CommandStack.class );
		if( commandStack != null )
			commandStack.execute( command );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput( part, selection );

		// Do not listen to model changes from the previous input
		if( this.eipChain != null )
			this.eipChain.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EipChainEditPart )
				this.eipChain = (EipChain) ((EipChainEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.eipChain != null )
			this.eipChain.addPropertyChangeListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		this.enableListener = false;
		if( this.titleText != null
					&& ! this.titleText.isDisposed()
					&& this.eipChain != null ) {

			// Save the caret position
			Text focusText = null;
			if( this.titleText.isFocusControl())
				focusText = this.titleText;
			else if( this.versionText.isFocusControl())
				focusText = this.versionText;
			else if( this.descText.isFocusControl())
				focusText = this.descText;

			int caret = focusText != null ? focusText.getCaretPosition() : -1;

			// Update values
			this.titleText.setText( this.eipChain.getTitle() != null ? this.eipChain.getTitle() : "" );
			this.versionText.setText( this.eipChain.getVersion() != null ? this.eipChain.getVersion() : "" );
			this.descText.setText( this.eipChain.getDescription() != null ? this.eipChain.getDescription() : "" );

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );
		}

		this.enableListener = true;
	}
}
