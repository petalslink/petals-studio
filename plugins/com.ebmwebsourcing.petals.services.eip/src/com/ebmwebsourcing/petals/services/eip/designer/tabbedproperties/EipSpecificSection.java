/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.XPathSourceViewerConfiguration;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipNodeSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;

/**
 * The section for EIP specific properties.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipSpecificSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipNode eip;

	private Composite subContainer;
	private ComboViewer patternViewer;
	private boolean notifyChanges = true;

	private final List<StyledText> textFields = new ArrayList<StyledText> ();


	/**
	 * Constructor.
	 */
	public EipSpecificSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipNode.PROPERTY_EIP_TYPE.equals( evt.getPropertyName()))
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
		GridLayout layout = new GridLayout( 2, false );
		layout.marginTop = 10;
		container.setLayout( layout );

		// Add the other widgets
		Label l = getWidgetFactory().createLabel( container, "Enterprise Integration Pattern:" );
		l.setToolTipText( "The Enterprise Integration Pattern" );

		CCombo combo = getWidgetFactory().createCCombo( container, SWT.READ_ONLY | SWT.FLAT | SWT.NO_FOCUS );
		GridData layoutData = new GridData();
		layoutData.widthHint = 240;
		layoutData.horizontalIndent = 5;
		combo.setLayoutData( layoutData );

		this.patternViewer = new ComboViewer( combo );
		this.patternViewer.setContentProvider( new ArrayContentProvider());
		this.patternViewer.setLabelProvider( new LabelProvider());
		this.patternViewer.setInput( EIPtype.values());
		this.patternViewer.getCCombo().setVisibleItemCount( EIPtype.values().length );

		this.subContainer = getWidgetFactory().createComposite( container );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		this.subContainer.setLayout( layout );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 5;
		this.subContainer.setLayoutData( layoutData );

		if( this.eip != null ) {
			this.patternViewer.setSelection( new StructuredSelection( this.eip.getEipType()));
			buildPatternWidgets();
		}

		// Listeners
		this.patternViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				// Notify the change
				if( EipSpecificSection.this.notifyChanges ) {
					EipNodeSetAttributeCommand cmd =
						new EipNodeSetAttributeCommand( EipNode.PROPERTY_EIP_TYPE );

					cmd.setEipNode( EipSpecificSection.this.eip );
					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
					cmd.setNewValue( o );
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
		if( this.eip != null )
			this.eip.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EipNodeEditPart )
				this.eip = (EipNode) ((EipNodeEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.eip != null )
			this.eip.addPropertyChangeListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		if( this.patternViewer != null
					&& ! this.patternViewer.getCCombo().isDisposed()
					&& this.eip != null ) {

			// Save the caret position - if there is one
			StyledText focusText = null;
			for( StyledText st : this.textFields ) {
				if( st.isFocusControl()) {
					focusText = st;
					break;
				}
			}

			int caret = focusText != null ? focusText.getCaretOffset() : -1;

			// Update values
			this.notifyChanges = false;
			this.patternViewer.setSelection( new StructuredSelection( this.eip.getEipType()));

			// Build the UI
			buildPatternWidgets();
			this.notifyChanges = true;

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );
		}
	}


	/**
	 * Builds the pattern widgets.
	 */
	private void buildPatternWidgets() {

		// Constant for this method
		final String buttonValue = "ButtonRealValue";

		// Remove all the previous ones
		this.textFields.clear();
		for( Control c : this.subContainer.getChildren())
			c.dispose();

		// Build the new widgets
		Listener listener = new Listener() {
			public void handleEvent( Event e ) {

				String value;
				if( e.widget instanceof Text )
					value = ((Text) e.widget).getText();
				if( e.widget instanceof StyledText )
					value = ((StyledText) e.widget).getText();
				else if( e.widget instanceof CCombo )
					value = ((CCombo) e.widget).getText();
				else if( e.widget instanceof Button ) {

					// Works only for routing criteria
					value = (String) ((Button) e.widget).getData( buttonValue );

					// By default, a button indicates a value
					if( value == null )
						value = String.valueOf(((Button) e.widget).getSelection());
				} else
					value = null;

				EipProperty property = (EipProperty) e.widget.getData();
				EipNodeSetAttributeCommand cmd =
					new EipNodeSetAttributeCommand( property.toString());

				cmd.setEipNode( EipSpecificSection.this.eip );
				cmd.setNewValue( value );
				executeCommand( cmd );
			}
		};

		// Create the UI from the properties
		for( EipProperty property : this.eip.getSupportedProperties()) {

			// Add the specific widget
			Label label;
			String value;
			switch( property.getType()) {

			case BOOLEAN:
				Button button = getWidgetFactory().createButton( this.subContainer, property.getDisplayLabel(), SWT.CHECK );
				value = this.eip.getProperties().get( property );
				if( Boolean.parseBoolean( value ))
					button.setSelection( true );

				GridData layoutData = new GridData();
				layoutData.horizontalSpan = 2;
				button.setLayoutData( layoutData );

				button.setData( property );
				button.addListener( SWT.Selection, listener );
				button.setToolTipText( property.getTooltipText());
				break;

			case WIRETAP_ENUM:
				label = getWidgetFactory().createLabel( this.subContainer, property.getDisplayLabel() + ":" );
				label.setToolTipText( property.getTooltipText());

				CCombo combo = getWidgetFactory().createCCombo( this.subContainer, SWT.READ_ONLY | SWT.DROP_DOWN );
				combo.setItems( new String[] { "Request", "Response", "Request-on-response", "Request-response" });

				value = this.eip.getProperties().get( property );
				int index = -1;
				if( value != null )
					index = combo.indexOf( value );

				if( index >= 0 ) {
					combo.select( index );
				}
				else if(( value = property.getDefaultValue()) != null ) {
					this.eip.setEipProperty( property, value );
					index = combo.indexOf( value );
					if( index >= 0 )
						combo.select( index );
				}

				combo.setData( property );
				combo.addListener( SWT.Selection, listener );
				break;

			case ROUTING_CRITERIA_ENUM:
				Button xpathCriteriaButton = getWidgetFactory().createButton( this.subContainer, "Route Messages by Content (XPath)", SWT.RADIO );
				xpathCriteriaButton.setToolTipText( "Messages will be tested againt a XPath condition" );

				Button opCriteriaButton = getWidgetFactory().createButton( this.subContainer, "Route Messages by Invoked Operation", SWT.RADIO );
				opCriteriaButton.setToolTipText( "Messages will be forwarded to the service which matches the invoked operation" );

				value = this.eip.getProperties().get( property );
				if( EipProperty.ROUTING_CRITERIA_BY_OPERATION.equals( value )) {
					opCriteriaButton.setSelection( true );
					xpathCriteriaButton.setSelection( false );

				} else {
					opCriteriaButton.setSelection( false );
					xpathCriteriaButton.setSelection( true );
				}

				xpathCriteriaButton.setData( property );
				xpathCriteriaButton.setData( buttonValue, EipProperty.ROUTING_CRITERIA_BY_CONTENT );

				opCriteriaButton.setData( property );
				opCriteriaButton.setData( buttonValue, EipProperty.ROUTING_CRITERIA_BY_OPERATION );

				xpathCriteriaButton.addListener( SWT.Selection, listener );
				opCriteriaButton.addListener( SWT.Selection, listener );
				break;

			case XPATH:
				label = getWidgetFactory().createLabel( this.subContainer, property.getDisplayLabel() + ":" );
				label.setToolTipText( property.getTooltipText());
				label.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));

				Composite editor = getWidgetFactory().createComposite( this.subContainer, SWT.BORDER );
				editor.setLayout( new FillLayout ());
				layoutData = new GridData( GridData.FILL_HORIZONTAL );
				layoutData.heightHint = 60;
				editor.setLayoutData( layoutData );

				int style = SWT.V_SCROLL | SWT.MULTI | SWT.BORDER;
				final ISourceViewer viewer = new SourceViewer( editor, new VerticalRuler( 0 ), style );
				ColorManager cManager = new ColorManager ();
				viewer.configure( new XPathSourceViewerConfiguration( cManager ));

				viewer.getTextWidget().setLayoutData( new GridData( GridData.FILL_BOTH ));
				value = this.eip.getProperties().get( property );
				IDocument document = new Document( value == null ? "" : value );
				viewer.setDocument( document );
				viewer.getTextWidget().setData( property );
				viewer.getTextWidget().addListener( SWT.Modify, listener );

				this.textFields.add( viewer.getTextWidget());
				break;

			default:
				break;
			}
		}

		// Lay out
		this.subContainer.getParent().getParent().getParent().layout();
		this.subContainer.layout();
	}
}
