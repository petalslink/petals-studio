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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KeyValueArrayDialog extends TitleAreaDialog {

	private List<KeyValue> values;
	private TableViewer viewer;
	private static AtomicInteger keyCpt = new AtomicInteger( 1 );

	private final boolean keyCombo, valueCombo;
	private final String keyColumnName, valueColumnName;
	private final String[] keyEnum, valueEnum;


	/**
	 * Constructor.
	 * @param parentShell
	 * @param initialValue
	 * @param keyIsComboEditor
	 * @param keyEnum if keyIsComboEditor, not null and with at least one element
	 * @param keyColumnName
	 * @param valueIsComboEditor
	 * @param valueEum if valueIsComboEditor, not null and with at least one element
	 * @param valueColumnName
	 */
	public KeyValueArrayDialog(
				Shell parentShell, String initialValue,
				boolean keyIsComboEditor, String[] keyEnum, String keyColumnName,
				boolean valueIsComboEditor, String[] valueEum, String valueColumnName ) {

		super( parentShell );
		this.values = parseValue( initialValue );

		this.keyColumnName = keyColumnName;
		this.keyCombo = keyIsComboEditor;
		this.keyEnum = keyEnum;

		this.valueColumnName = valueColumnName;
		this.valueCombo = valueIsComboEditor;
		this.valueEnum = valueEum;

		keyCpt.set( this.values.size() + 1 );
		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		// Container
		Composite bigContainer = new Composite((Composite) super.createDialogArea( parent ), SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 10;
		bigContainer.setLayout( layout );
		bigContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Viewer
		Composite tableContainer = new Composite( bigContainer, SWT.NONE );
		tableContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		this.viewer = new TableViewer( tableContainer,
					SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER
					| SWT.V_SCROLL | SWT.H_SCROLL | SWT.HIDE_SELECTION );

		this.viewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.viewer.getTable().setHeaderVisible( true );
		this.viewer.getTable().setLinesVisible( true );

		// Table columns
		TableColumn keyColumn = new TableColumn( this.viewer.getTable(), SWT.LEFT );
		keyColumn.setWidth( 120 );
		keyColumn.setResizable( true );
		keyColumn.setText( this.keyColumnName );

		TableColumn valueColumn = new TableColumn( this.viewer.getTable(), SWT.CENTER );
		valueColumn.setWidth( 120 );
		valueColumn.setResizable( true );
		valueColumn.setText( this.valueColumnName );

		// Column layout
		TableColumnLayout columnLayout = new TableColumnLayout ();
		columnLayout.setColumnData( keyColumn, new ColumnWeightData( 50, 120 ));
		columnLayout.setColumnData( valueColumn, new ColumnWeightData( 50, 120 ));
		tableContainer.setLayout( columnLayout );

		// TableViewer
		this.viewer.setContentProvider( new ArrayContentProvider());
		this.viewer.setLabelProvider( new ITableLabelProvider() {

			public void dispose() {}
			public void addListener( ILabelProviderListener listener ) {}
			public void removeListener( ILabelProviderListener listener ) {}

			public boolean isLabelProperty( Object element, String property ) {
				return false;
			}

			public String getColumnText( Object element, int columnIndex ) {
				if( element != null && element instanceof KeyValue ) {
					if( columnIndex == 0 )
						return ((KeyValue) element).key;
					return ((KeyValue) element).value;
				}

				return "";
			}

			public Image getColumnImage( Object element, int columnIndex ) {
				return null;
			}
		});

		// Cell editors
		CellEditor keyEditor;
		if( this.keyCombo )
			keyEditor = new ComboBoxCellEditor( this.viewer.getTable(), this.keyEnum );
		else
			keyEditor = new TextCellEditor( this.viewer.getTable());

		CellEditor valueEditor;
		if( this.valueCombo )
			valueEditor = new ComboBoxCellEditor( this.viewer.getTable(), this.valueEnum );
		else
			valueEditor = new TextCellEditor( this.viewer.getTable());

		this.viewer.setColumnProperties( new String[] { this.keyColumnName, this.valueColumnName });
		this.viewer.setCellEditors( new CellEditor[] { keyEditor, valueEditor });
		this.viewer.setCellModifier( new ICellModifier() {

			public void modify( Object element, String property, Object value ) {

				Object o = ((TableItem)element).getData();
				if( o instanceof KeyValue ) {

					// Case text editor
					if( value instanceof String ) {
						if( KeyValueArrayDialog.this.keyColumnName.equals( property ))
							((KeyValue) o).key = (String) value;
						else
							((KeyValue) o).value = (String) value;
					}

					// Case combo editor
					else if( value instanceof Integer ) {
						if( KeyValueArrayDialog.this.keyColumnName.equals( property ))
							((KeyValue) o).key = KeyValueArrayDialog.this.keyEnum[(Integer) value];
						else
							((KeyValue) o).value = KeyValueArrayDialog.this.valueEnum[(Integer) value];
					}

					KeyValueArrayDialog.this.viewer.update( o, null );
				}
			}

			public Object getValue( Object element, String property ) {

				if( element instanceof KeyValue ) {

					// Case key column
					if( KeyValueArrayDialog.this.keyColumnName.equals( property )) {
						String result = ((KeyValue) element).key;
						if( KeyValueArrayDialog.this.keyCombo )
							return Arrays.asList( KeyValueArrayDialog.this.keyEnum ).indexOf( result );
						return result;
					}

					// Case value column
					else {
						String result = ((KeyValue) element).value;
						if( KeyValueArrayDialog.this.valueCombo )
							return Arrays.asList( KeyValueArrayDialog.this.valueEnum ).indexOf( result );
						return result;
					}
				}

				return "";
			}

			public boolean canModify( Object element, String property ) {
				return true;
			}
		});

		TableViewerEditor.create(
					this.viewer,
					new ColumnViewerEditorActivationStrategy( this.viewer ),
					ColumnViewerEditor.TABBING_HORIZONTAL
					| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
					| ColumnViewerEditor.TABBING_VERTICAL );


		// Input
		this.viewer.setInput( this.values );


		// Buttons
		Composite buttonsComposite = new Composite( bigContainer, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));

		Button addButton = new Button( buttonsComposite, SWT.PUSH );
		addButton.setText( "&Add" );
		addButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		addButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				int keyValue = keyCpt.getAndIncrement();
				String key = "Key-" + keyValue;
				if( KeyValueArrayDialog.this.keyCombo )
					key = KeyValueArrayDialog.this.keyEnum[ 0 ];

				String value = "Value-" + keyValue;
				if( KeyValueArrayDialog.this.valueCombo )
					value = KeyValueArrayDialog.this.valueEnum[ 0 ];

				KeyValue kv = new KeyValue( key, value );
				KeyValueArrayDialog.this.values.add( kv );
				KeyValueArrayDialog.this.viewer.refresh();
				KeyValueArrayDialog.this.viewer.editElement( kv, 0 );
			}
		});

		Button removeButton = new Button( buttonsComposite, SWT.PUSH );
		removeButton.setText( "&Remove" );
		removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		removeButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				IStructuredSelection ss = (IStructuredSelection) KeyValueArrayDialog.this.viewer.getSelection();
				if( ! ss.isEmpty()) {
					KeyValueArrayDialog.this.values.remove( ss.getFirstElement());
					KeyValueArrayDialog.this.viewer.refresh();
				}
			}
		});


		// Miscellaneous
		getShell().setText( "Key-value editor" );
		setTitle( "Key-value editor" );
		setMessage( "Edit a set of key-values." );
		return bigContainer;
	}


	/**
	 * @param value
	 * @return
	 */
	public static List<KeyValue> parseValue( String value ) {

		List<KeyValue> result = new ArrayList<KeyValue> ();
		if( value == null )
			return result;

		for( String keyValue : value.split( ";" )) {
			String[] ops = keyValue.split( "=" );
			if( ops.length == 2 )
				result.add( new KeyValue( ops[ 0 ].trim(), ops[ 1 ].trim()));
		}

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize () {
		return new Point( 500, 300 );
	}


	/**
	 * @return the value
	 */
	public String getValue() {

		StringBuilder value = new StringBuilder();
		for( KeyValue kv : this.values )
			value.append( kv.key + "=" + kv.value + ";" );

		return value.toString();
	}


	/**
	 * @param value the value to set
	 */
	public void setValue( String value ) {
		this.values = parseValue( value );
		keyCpt.set( this.values.size() + 1 );
	}


	/**
	 * A class representing the association key-value.
	 * <p>
	 * Map and Properties cannot be used instead, because we cannot
	 * instantiate Map.Entry. This impossibility causes issues when you
	 * create a new key-value and that you want to select the model
	 * element to edit.
	 * </p>
	 */
	public static class KeyValue {

		String key, value;

		/**
		 * Constructor.
		 * @param key
		 * @param value
		 */
		public KeyValue( String key, String value ) {
			this.key = key;
			this.value = value;
		}


		/**
		 * @return the key
		 */
		public String getKey() {
			return this.key;
		}


		/**
		 * @return the value
		 */
		public String getValue() {
			return this.value;
		}


		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals( Object obj ) {

			if( obj == null || ! obj.getClass().equals( getClass()))
				return false;

			// Key are equals
			KeyValue kv = (KeyValue) obj;
			if( kv.key == null ) {
				if( this.key != null )
					return false;
			}

			else if( ! kv.key.equals( this.key ))
				return false;

			// Values are equals
			if( kv.value == null )
				return this.value == null;

			else if( ! kv.value.equals( this.value ))
				return false;

			return true;
		}


		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			int khc = 17 * (this.key == null ? 71 : this.key.hashCode());
			int vhc = 17 * (this.value == null ? 71 : this.value.hashCode());
			return khc * vhc;
		}
	}
}
