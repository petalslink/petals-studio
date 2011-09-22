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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.PetalsXsdAnnotations;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem;

/**
 * Generate SWT widgets from a list of abstract HCI items.
 * Besides, this class is also responsible of creating the listeners for value modifications.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 * TODO: this class and this package are a mess. Use the *.swt package from this plug-in.
 * TODO: use *.swt classes to update the values easily.
 */
public class SwtWidgetGenerator {

	/**
	 * The map which associate an XML key with a value.
	 * This will be used when writing the values in an XML file.
	 */
	private LinkedHashMap<SerializationItem, String> values = new LinkedHashMap<SerializationItem, String> ();
	private final Map<SerializationItem, Widget> widgets = new HashMap<SerializationItem, Widget> ();

	/** The object which created this instance. */
	private final WidgetGeneratorLink link;



	/**
	 * Constructor.
	 * @param link the object which created the instance.
	 */
	public SwtWidgetGenerator( WidgetGeneratorLink link ) {
		this.link = link;
	}


	/**
	 * Generate widgets from a list of HCI items.
	 * @param widgets the list of HCI items. It can be empty but not null.
	 * @param table the parent composite. It is expected to have a layout with two columns.
	 * @param verticalSpacing the spacing between each row.
	 */
	public void generateWidgets( List<HciItem> widgets, Composite table, int verticalSpacing ) {
		for( HciItem h : widgets ) {
			Widget w = generateWidget( h, table, verticalSpacing );
			if( w != null )
				this.widgets.put( h, w );
		}
	}


	/**
	 * Generate a widget for a given HCI item and create everything required (listeners...).
	 * @param h the abstract HCI item.
	 * @param table the parent composite.
	 * @param verticalSpacing
	 */
	private Widget generateWidget( final HciItem h, Composite table, int verticalSpacing ) {

		// Items with no HCI to display.
		if( ! h.isVisible()) {
			if( h.getDefaultValue() != null )
				this.values.put( h, h.getDefaultValue());
			else
				this.values.put( h, "" ); //$NON-NLS-1$
			return null;
		}

		// The label.
		Label label = new Label( table, SWT.NONE );
		String labelName;
		if( h.getType() != HciType.QNAME )
			labelName = createLabel( h.getLabelName(), h.isOptional() || h.isNillable(), "" );
		else
			labelName = createLabel( h.getLabelName(), h.isOptional() || h.isNillable(), " namespace" );
		label.setText( labelName );

		String tooltip = h.getTooltip();
		label.setToolTipText( tooltip );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = verticalSpacing;
		label.setLayoutData( layoutData );

		// maxOccurs supposed to be greater or equal to minOccurs
		if( h.getMaxOccurs() > 1 )
			return generateComplexWidget( h, table, verticalSpacing );

		return generateSimpleWidget( h, table, verticalSpacing );
	}


	/**
	 * Generate a widget to edit values with multiple cardinalities.
	 * @param h the abstract HCI item.
	 * @param table the parent composite.
	 * @param verticalSpacing
	 * @return the widget whose value is modified by the user.
	 */
	private Widget generateComplexWidget( final HciItem h, Composite table, int verticalSpacing ) {

		final Composite helper = new Composite( table, SWT.NONE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = verticalSpacing;
		helper.setLayoutData( layoutData );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = layout.marginHeight = 0;
		helper.setLayout( layout );

		final Text fileText = new Text( helper, SWT.SINGLE | SWT.BORDER );
		fileText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		fileText.setData( h );
		fileText.setToolTipText( Messages.SwtWidgetGenerator_0 );

		// Modify listener
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = ((Text) e.widget).getText();
				SwtWidgetGenerator.this.values.put( h, text );
				SwtWidgetGenerator.this.link.widgetChanged();
			}
		});

		// Default value
		if( h.getDefaultValue() != null ) {
			fileText.setText( h.getDefaultValue());
			this.values.put( h, h.getDefaultValue());
		}
		else
			this.values.put( h, "" ); //$NON-NLS-1$

		// Browse button
		Button browse = new Button( helper, SWT.PUSH );
		browse.setText( Messages.SwtWidgetGenerator_38 );
		browse.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TypedListDialog dialog =
					new TypedListDialog( helper.getShell(), fileText.getText(), h.getType(), h.getValues());

				int result = dialog.open();
				if( result == Window.OK ) {
					String newValue = dialog.getText();
					fileText.setText( newValue );
					fileText.update();

					SwtWidgetGenerator.this.values.put( h, newValue );
					dialogChanged();
				}
			}
		});

		return fileText;
	}


	/**
	 * Generate a widget to edit simple values (no multiple cardinalities).
	 * @param h the abstract HCI item.
	 * @param table the parent composite.
	 * @param verticalSpacing
	 */
	private Widget generateSimpleWidget( final HciItem h, Composite table, int verticalSpacing) {

		// The second widget.
		switch( h.getType()) {
		case STRING:
			Text text = new Text( table, SWT.SINGLE | SWT.BORDER );
			GridData stringLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			text.setToolTipText( h.getTooltip());
			stringLayoutData.verticalIndent = verticalSpacing;
			text.setLayoutData( stringLayoutData );
			text.setData( h );

			// Default value
			if( h.getDefaultValue() != null ) {
				text.setText( h.getDefaultValue());
				this.values.put( h, h.getDefaultValue());
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Listener
			text.addModifyListener( new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String text = ((Text) e.widget).getText();
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});
			return text;

		case QNAME:
			final Text nsText = new Text( table, SWT.SINGLE | SWT.BORDER );
			stringLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			stringLayoutData.verticalIndent = verticalSpacing;
			nsText.setLayoutData( stringLayoutData );
			nsText.setToolTipText( h.getTooltip());
			nsText.setData( h );

			Label label = new Label( table, SWT.NONE );
			String labelName = createLabel( h.getLabelName(), h.isOptional() || h.isNillable(), "" );
			label.setText( labelName );

			String tooltip = h.getTooltip();
			label.setToolTipText( tooltip );

			final Text nameText = new Text( table, SWT.SINGLE | SWT.BORDER );
			stringLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			stringLayoutData.verticalIndent = verticalSpacing;
			nameText.setLayoutData( stringLayoutData );
			nameText.setToolTipText( h.getTooltip());
			nameText.setData( h );

			// Default value
			if( h.getDefaultValue() != null ) {
				String ns = JbiNameFormatter.extractNamespaceUri( h.getDefaultValue());
				nsText.setText( ns );

				String name = JbiNameFormatter.removeNamespaceElements( h.getDefaultValue());
				nameText.setText( name );
				this.values.put( h, h.getDefaultValue());
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Listeners
			ModifyListener qnameListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String nsUri = nsText.getText();
					String localPart = nameText.getText();
					SwtWidgetGenerator.this.values.put( h, "{" + nsUri + "}" + localPart );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			};

			nsText.addModifyListener( qnameListener );
			nameText.addModifyListener( qnameListener );

			// Update not implemented!
			return nsText;

		case BOOLEAN:
			Combo combo = new Combo( table, SWT.READ_ONLY | SWT.BORDER );
			GridData comboLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			comboLayoutData.verticalIndent = verticalSpacing;
			combo.setLayoutData( comboLayoutData );
			combo.setData( h );
			combo.setToolTipText( h.getTooltip());
			combo.add( Messages.SwtWidgetGenerator_1 );
			combo.add( Messages.SwtWidgetGenerator_2 );

			// Default value
			if( h.getDefaultValue() != null ) {
				boolean defaultValue = Boolean.parseBoolean( h.getDefaultValue());
				this.values.put( h, String.valueOf( defaultValue ));
				if( defaultValue )
					combo.select( 0 );
				else
					combo.select( 1 );
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Listener
			combo.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String text = ((Combo) e.widget).getText();
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});
			return combo;

		case INTEGER:
			Spinner intSpinner = new Spinner( table, SWT.BORDER );
			intSpinner.setToolTipText( h.getTooltip());
			intSpinner.setData( h );

			GridData intLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			intLayoutData.verticalIndent = verticalSpacing;
			intSpinner.setLayoutData( intLayoutData );

			// Default value
			int value = Integer.MIN_VALUE;
			try {
				if( h.getDefaultValue() != null )
					value = Integer.parseInt( h.getDefaultValue());
			} catch( Exception e ) {
				// nothing
			}

			intSpinner.setIncrement( 1 );
			intSpinner.setPageIncrement( 10 );
			intSpinner.setMaximum( Integer.MAX_VALUE );
			intSpinner.setMinimum( Integer.MIN_VALUE );
			intSpinner.setDigits( 0 );

			if( value != Integer.MIN_VALUE ) {
				this.values.put( h, String.valueOf( value ));
				intSpinner.setSelection( value );
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Optional: with the spinner, an integer has always a value. It can't be optional.
			// Listener
			intSpinner.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String text = String.valueOf(((Spinner) e.widget).getSelection());
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});
			return intSpinner;

		case URI:
			Text uriText = new Text( table, SWT.SINGLE | SWT.BORDER );
			GridData uriLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			uriLayoutData.verticalIndent = verticalSpacing;
			uriText.setLayoutData( uriLayoutData );
			uriText.setToolTipText( h.getTooltip());
			uriText.setData( h );

			// Default value
			if( h.getDefaultValue() != null ) {
				uriText.setText( h.getDefaultValue());
				this.values.put( h, h.getDefaultValue());
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Listener
			uriText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String text = ((Text) e.widget).getText();
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});
			return uriText;

		case DOUBLE:
			Spinner doubleSpinner = new Spinner( table, SWT.BORDER );
			doubleSpinner.setToolTipText( h.getTooltip());
			doubleSpinner.setData( h );

			GridData doubleLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			doubleLayoutData.verticalIndent = verticalSpacing;
			doubleSpinner.setLayoutData( doubleLayoutData );

			// Default value
			double doubleValue = Integer.MIN_VALUE;
			try {
				if( h.getDefaultValue() != null )
					doubleValue = Double.parseDouble( h.getDefaultValue());
			} catch( Exception e ) {
				// nothing
			}

			doubleSpinner.setIncrement( 1 );
			doubleSpinner.setPageIncrement( 100 );
			doubleSpinner.setMaximum( Integer.MAX_VALUE );
			doubleSpinner.setMinimum( Integer.MIN_VALUE );
			doubleSpinner.setDigits( 2 );

			if( doubleValue != Integer.MIN_VALUE ) {
				this.values.put( h, String.valueOf( doubleValue ));

				// Perform the multiplication for the spinner after having registered the value in the map.
				doubleValue *=  100.0;
				doubleSpinner.setSelection((int) doubleValue );		// otherwise, we don't have the digits.
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Optional: with the spinner, a double has always a value. It can't be optional.
			// Listener
			doubleSpinner.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					int intValue = ((Spinner) e.widget).getSelection();
					int digits = ((Spinner) e.widget).getDigits();
					double value = intValue / Math.pow( 10, digits );

					String text = String.valueOf( value );
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});
			return doubleSpinner;

		case ENUM:
			int style = SWT.BORDER;
			if( ! PetalsXsdAnnotations.containsAnnotation(
						PetalsXsdAnnotations.EDITABLE_COMBO, h.getAnnotations()))
				style |= SWT.READ_ONLY;

			final Combo enumCombo = new Combo( table, style );
			enumCombo.setToolTipText( h.getTooltip());
			enumCombo.setData( h );

			boolean hasLongEnum = false;
			for( String enumValue : h.getValues()) {
				enumCombo.add( enumValue );
				hasLongEnum = hasLongEnum || enumValue.length() > 20;
			}

			GridData enumLayoutData = new GridData( GridData.FILL_HORIZONTAL );
			enumLayoutData.verticalIndent = verticalSpacing;
			if( hasLongEnum )
				enumLayoutData.widthHint = 0;
			enumCombo.setLayoutData( enumLayoutData );

			// Default value
			if( h.getDefaultValue() != null ) {
				loop: for( int i=0; i<enumCombo.getItemCount(); i++ ) {
					if( enumCombo.getItem( i ).equals( h.getDefaultValue())) {
						enumCombo.select( i );
						break loop;
					}
				}
			this.values.put( h, h.getDefaultValue());
			}
			else
				this.values.put( h, "" ); //$NON-NLS-1$

			// Listener
			enumCombo.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					String text = enumCombo.getItem( enumCombo.getSelectionIndex());
					if( PetalsXsdAnnotations.containsAnnotation(
								PetalsXsdAnnotations.SURROUND_WITH_CDATA, h.getAnnotations())) {
						text = "<![CDATA[" + text + "]]>";
					}

					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});

			// Display all the values or use a scroll?
			int itemCount = enumCombo.getItemCount();
			if( itemCount > 15 )
				enumCombo.setVisibleItemCount( 15 );
			else
				enumCombo.setVisibleItemCount( itemCount );

			return enumCombo;

		case FILE:
			Composite browser = new Composite( table, SWT.NONE );
			GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
			layoutData.verticalIndent = verticalSpacing;
			browser.setLayoutData( layoutData );

			GridLayout layout = new GridLayout( 2, false );
			layout.marginWidth = layout.marginHeight = 0;
			browser.setLayout( layout );

			final Text fileText = new Text( browser, SWT.SINGLE | SWT.BORDER );
			fileText.setEditable( false );
			fileText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			fileText.setToolTipText( h.getTooltip());
			fileText.setData( h );

			// Keep the order.
			this.values.put( h, "" ); //$NON-NLS-1$

			// Modify listener
			fileText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					String text = ((Text) e.widget).getText();
					SwtWidgetGenerator.this.values.put( h, text );
					SwtWidgetGenerator.this.link.widgetChanged();
				}
			});

			// Browse button
			Button browse = new Button( browser, SWT.PUSH );
			browse.setText( Messages.SwtWidgetGenerator_4 );
			browse.addSelectionListener( new SelectionAdapter() {
				/*
				 * (non-Javadoc)
				 * @see org.eclipse.swt.events.SelectionAdapter
				 * #widgetSelected(org.eclipse.swt.events.SelectionEvent)
				 */
				@Override
				public void widgetSelected(SelectionEvent e) {
					FileDialog dlg = new FileDialog( fileText.getShell(), SWT.SINGLE );
					dlg.setText( Messages.SwtWidgetGenerator_5 );
					dlg.setFilterNames( new String[] { Messages.SwtWidgetGenerator_6 });
					dlg.setFilterExtensions( new String[] { "*.*" }); //$NON-NLS-1$

					String path = PreferencesManager.getSavedLocation();
					if( path.trim().length() > 0 )
						dlg.setFilterPath( path );

					String fn = dlg.open();
					if (fn != null) {
						try {
							path = dlg.getFilterPath();
							PreferencesManager.setSavedLocation( path );

							String filePath = new File( fn ).toURI().toURL().toString();
							fileText.setText( filePath );
							fileText.setSelection( filePath.length());

						} catch( MalformedURLException e1 ) {
							// e1.printStackTrace();
						}
					}
				}
			});
			return fileText;
		default: break;
		}

		return null;
	}


	/**
	 * Called each time a widget's value is modified.
	 * For every widget, it makes sure:
	 * <ul>
	 * 	<li>Non-optional elements are filled.</li>
	 * 	<li>Check element cardinalities.</li>
	 * 	<li>For URI, ensure the URL is valid.</li>
	 * </ul>
	 * 
	 * @return the error message or null if none was found.
	 */
	public String dialogChanged() {

		// Every value managed by this instance is registered into values.
		for( Map.Entry<SerializationItem, String> entry : this.values.entrySet()) {

			SerializationItem item = entry.getKey();
			String value = entry.getValue();
			String errorName = "\"" + createErrorName( item.getXsdName()) + "\""; //$NON-NLS-1$ //$NON-NLS-2$

			// Skip invisible items.
			if( !item.isVisible())
				continue;

			// Non-optional and non-nillable elements are filled.
			if( !item.isOptional()
						&& !item.isNillable()
						&& (value == null || "".equals( value ))) {				 //$NON-NLS-1$
				return NLS.bind( Messages.SwtWidgetGenerator_15, errorName );
			}

			// Cardinalities are checked.
			if( item.getMaxOccurs() > 1 ) {
				int validValuesCpt = 0;

				String[] parts = value.split( ";;" ); //$NON-NLS-1$
				for( String string : parts ) {
					if( ! "".equals( string.trim())) //$NON-NLS-1$
						validValuesCpt ++;
				}

				if( validValuesCpt < item.getMinOccurs())
					return NLS.bind( Messages.SwtWidgetGenerator_33, errorName, item.getMinOccurs());
				if( validValuesCpt > item.getMaxOccurs())
					return NLS.bind( Messages.SwtWidgetGenerator_34, errorName, item.getMaxOccurs());
			}

			// URI are valid URIs.
			if( item.getType() == HciType.URI &&
						( !item.isOptional() ||
									item.isOptional() && !value.equals( "" ))) { //$NON-NLS-1$
				if( item.getMaxOccurs() > 1 ) {

					ArrayList<String> vv = new ArrayList<String> ();
					String[] parts = value.split( ";;" ); //$NON-NLS-1$
					for( String string : parts ) {
						string = string.trim();
						if( ! "".equals( string )) //$NON-NLS-1$
							vv.add( string );
					}

					int position = 1;
					for( String string : vv ) {
						try {
							new URI( string );
						} catch( Exception e ) {
							return NLS.bind( Messages.SwtWidgetGenerator_35, errorName, position );
						}
						position ++;
					}
				}
				else {
					try {
						new URI( value );
					} catch( Exception e ) {
						return NLS.bind( Messages.SwtWidgetGenerator_36, errorName );
					}
				}
			}

			// QNames are valid QNames
			if( item.getType() == HciType.QNAME &&
						( !item.isOptional() ||
									item.isOptional() && !value.equals( "" ))) { //$NON-NLS-1$

				String ns = JbiNameFormatter.extractNamespaceUri( value );
				if( ns.length() == 0 )
					return "The " + errorName + " namespace is invalid.";

				try {
					new URL( ns );
				} catch( Exception e ) {
					return "The " + errorName + " namespace is not a valid URL.";
				}

				String name = JbiNameFormatter.removeNamespaceElements( value );
				if( name.length() == 0 )
					return "You have to provide the " + errorName + ".";
			}
		}

		// No error found, return null.
		return null;
	}


	/**
	 * Create almost the same thing than {@link SwtWidgetGenerator#createLabel(String)} but in lower case.
	 * @param name the string used as source for the error name.
	 * @return a name used for error messages.
	 */
	private String createErrorName( String name ) {
		if( name == null )
			return null;

		String result = createLabel( name, false, "" ).toLowerCase();
		if( result.endsWith( ": " )) //$NON-NLS-1$
			result = result.substring( 0, result.length() - 2 );

		return result.trim();
	}


	/**
	 * Create a clean label name (no namespace, capitalize every word).
	 * @param string the string used as source for the label name.
	 * @param isOptional true if the element has to be marked as optional in the page.
	 * @return a clean label name.
	 */
	private String createLabel( String string, boolean isOptional, String suffix ) {

		if( string == null )
			return " "; //$NON-NLS-1$

		String name = string + suffix;

		// Remove name-spaces.
		String[] parts = name.split( ":" ); //$NON-NLS-1$
		if( parts.length > 1 )
			name = parts[ parts.length - 1 ];

		// Add ":" or "*:" at the end.
		String suffix2 = (isOptional ? " " + Messages.SwtWidgetGenerator_32 : "") + ":";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		name = name.trim();
		if( !name.endsWith( suffix2 ))
			name += suffix2;

		// Replace "_" and "-" by spaces.
		name = name.replace( "-", " " ); //$NON-NLS-1$ //$NON-NLS-2$
		name = name.replace( "_", " " ); //$NON-NLS-1$ //$NON-NLS-2$
		name = name.replace( ".", " " ); //$NON-NLS-1$ //$NON-NLS-2$

		// Capitalize letters.
		parts = name.split( " " ); //$NON-NLS-1$
		StringBuilder result = new StringBuilder();
		for( String part : parts ) {
			result.append( part.substring(0, 1).toUpperCase());
			result.append( part.substring(1).toLowerCase());
			result.append( " " ); //$NON-NLS-1$
		}
		return result.toString();
	}


	/**
	 * @return the values
	 */
	public LinkedHashMap<SerializationItem, String> getValues() {
		return this.values;
	}


	/**
	 * @param values the values to set
	 */
	public void setValues( LinkedHashMap<SerializationItem, String> values ) {
		this.values = values;
	}


	/**
	 * @param values the values to set
	 */
	public void setValuesInWidgets( Map<HciItem, String> values ) {

		for( Map.Entry<HciItem, String> entry : values.entrySet()) {
			Widget w = this.widgets.get( entry.getKey());

			if( w == null ) {
				if( this.values.containsKey( entry.getKey())
							&& ! StringUtils.isEmpty( entry.getValue()))
					this.values.put( entry.getKey(), entry.getValue());
				continue;
			}

			else if( w instanceof Text ) {
				if( entry.getKey().getType() != HciType.QNAME )
					((Text) w).setText( entry.getValue());
				else {
					// QName: the NS widget is the current one
					String ns = NamespaceUtils.extractNamespaceUri( entry.getValue());
					String op = NamespaceUtils.removeNamespaceElements( entry.getValue());

					boolean skip = entry.getKey().isOptional() || entry.getKey().isNillable();
					skip = skip && StringUtils.isEmpty( ns ) && StringUtils.isEmpty( op );
					if( ! skip ) {
						((Text) w).setText( ns );
						boolean match = false;
						for( Control c : ((Text) w).getParent().getChildren()) {

							if( c.equals( w ))
								match = true;
							else if( match && c instanceof Text ) {
								((Text) c).setText( op );
								break;
							}
						}
					}
				}
			}

			else if( w instanceof Combo ) {
				Combo c = (Combo) w;
				loop: for( int i=0; i<c.getItemCount(); i++ ) {
					if( c.getItem( i ).equals( entry.getValue())) {
						c.select( i );
						break loop;
					}
				}
			}

			else if( w instanceof Spinner ) {
				int value = Integer.valueOf( entry.getValue());
				((Spinner) w).setSelection( value );
			}
		}
	}
}
