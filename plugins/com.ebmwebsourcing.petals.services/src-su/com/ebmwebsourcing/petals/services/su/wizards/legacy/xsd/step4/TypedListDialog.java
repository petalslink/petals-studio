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
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4.ListAsModel.ListAsModelViewer;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4.ListAsModel.StringModel;

/**
 * A dialog, with a table viewer associated with buttons "Add", "Remove", "Move Up", "MoveDown".
 * The editor used with the viewer depends on the HciType.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TypedListDialog extends Dialog {

	/** The type of the elements to retrieve into the table viewer. */
	private final HciType hciType;
	/** The values to display if the HciType is an enumeration. */
	private String[] enumValues;

	/** The model object used as input for the viewer. */
	private final ListAsModel listAsModel = new ListAsModel();

	/** The table viewer used into this dialog. */
	private TableViewer tableViewer;

	/** */
	final static int TEXT_LIMIT = 255;


	/**
	 * Constructor.
	 * @param parentShell the shell used as parent for the dialog.
	 * @param initialValue the values to set as initial input into the table viewer.
	 * Elements are separated by two semicolons, e.g. "value1 ;; value2 ;; value3".
	 * @param hciType
	 * @param enumValues
	 */
	public TypedListDialog( Shell parentShell, String initialValue, HciType hciType, List<String> enumValues ) {
		super( parentShell );

		// Store values.
		this.hciType = hciType;
		if( enumValues == null )
			this.enumValues = new String[ 0 ];
		else
			this.enumValues = enumValues.toArray( new String[ enumValues.size() ]);

		// Build the model.
		if( initialValue == null || "".equals( initialValue )) //$NON-NLS-1$
			return;

		String[] parts = initialValue.split( ";;" ); //$NON-NLS-1$
		for( String string : parts ) {
			string = string.trim();
			if( !"".equals( string )) //$NON-NLS-1$
				this.listAsModel.add( new StringModel( string ));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {
		Composite container = (Composite) super.createDialogArea( parent );
		container.setLayout( new GridLayout( 2, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		getShell().setText( "Editing Element List" );

		// Create the table viewer.
		createTableAndViewer( container );

		// Buttons on the right.
		Composite buttonsContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginLeft = 5;
		layout.marginHeight = 0;
		buttonsContainer.setLayout( layout );
		buttonsContainer.setLayoutData( new GridData( SWT.RIGHT, SWT.TOP, false, true ));

		// "add", "remove", "up", "down"
		Button b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( Messages.TypedListDialog_3 );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringModel newModel = new StringModel( "" ); //$NON-NLS-1$
				TypedListDialog.this.listAsModel.add( newModel );
				TypedListDialog.this.tableViewer.setSelection( new StructuredSelection( newModel ), true );
				TypedListDialog.this.tableViewer.getTable().setFocus();
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( Messages.TypedListDialog_5 );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringModel stringModel = getSelection();
				if( stringModel != null )
					TypedListDialog.this.listAsModel.remove( stringModel );
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( Messages.TypedListDialog_6 );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringModel stringModel = getSelection();
				if( stringModel != null )
					TypedListDialog.this.listAsModel.moveUp( stringModel );
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( Messages.TypedListDialog_7 );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringModel stringModel = getSelection();
				if( stringModel != null )
					TypedListDialog.this.listAsModel.moveDown( stringModel );
			}
		});

		/* Dispose resources when the widget is disposed. */
		container.addDisposeListener( new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				TypedListDialog.this.tableViewer.getLabelProvider().dispose();
				TypedListDialog.this.tableViewer.getContentProvider().dispose();
			}
		});

		return container;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar( Composite parent ) {
		createButton(
					parent, IDialogConstants.OK_ID,
					IDialogConstants.OK_LABEL, true );
		createButton(
					parent, IDialogConstants.CANCEL_ID,
					IDialogConstants.CANCEL_LABEL, false );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize () {
		return new Point( 500, 375 );
	}

	/**
	 * @return
	 */
	private StringModel getSelection() {
		IStructuredSelection structuredSelection = (IStructuredSelection) this.tableViewer.getSelection();
		return (StringModel) structuredSelection.getFirstElement();
	}

	/**
	 * @param parent
	 */
	private void createTableAndViewer( Composite parent ) {
		int style =
			SWT.SINGLE | SWT.BORDER
			| SWT.H_SCROLL | SWT.V_SCROLL
			| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		// TableViewer.
		this.tableViewer = new TableViewer( parent, style );
		this.tableViewer.setUseHashlookup( true );
		this.tableViewer.setContentProvider( new ListAsModelContentProvider());

		// TableColumn.
		TableViewerColumn column = new TableViewerColumn( this.tableViewer, SWT.NONE );
		column.getColumn().setWidth( 400 );
		column.getColumn().setText( Messages.TypedListDialog_8 );

		// Label Provider.
		column.setLabelProvider( new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				return ((StringModel) element).getText();
			}

			@Override
			public Image getImage(Object element) {
				ImageDescriptor elementImageDesc =
					PetalsServicesPlugin.getImageDescriptor( "icons/obj16/element_obj.gif" ); //$NON-NLS-1$

				if(((StringModel) element).isValueValid())
					return elementImageDesc.createImage();

				// Add overlay icon.
				ImageDescriptor overlayImageDesc =
					PetalsServicesPlugin.getImageDescriptor( "icons/obj16/error-overlay.gif" ); //$NON-NLS-1$

				DecorationOverlayIcon overlayIcon = new DecorationOverlayIcon(
							elementImageDesc.createImage(),
							overlayImageDesc,
							IDecoration.BOTTOM_RIGHT
				);

				return overlayIcon.createImage();
			}
		});

		// Editing Support.
		column.setEditingSupport(new AbstractEditingSupport( this.tableViewer ) {
			@Override
			protected Object getValue(Object element) {
				return ((StringModel) element).getText();
			}

			@Override
			protected void doSetValue(Object element, Object value) {
				if( value != null )
					((StringModel) element).setText( value.toString());
			}
		});

		// Configure table properties.
		this.tableViewer.getTable().setLinesVisible( true );
		this.tableViewer.getTable().setHeaderVisible( false );
		this.tableViewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Enable tool tips to show error messages.
		this.tableViewer.getTable().addMouseTrackListener( new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				TableItem rightItem = TypedListDialog.this.tableViewer.getTable().getItem( new Point( e.x, e.y ));

				if( rightItem == null )
					TypedListDialog.this.tableViewer.getTable().setToolTipText( null );
				else {
					StringModel model = (StringModel) rightItem.getData();
					String errorMsg = null;
					if( ! model.isValueValid())
						errorMsg = model.getErrorMsg();
					TypedListDialog.this.tableViewer.getTable().setToolTipText( errorMsg );
				}
			}
		});

		// Click somewhere else than on an item sets an empty selection.
		this.tableViewer.getTable().addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown( MouseEvent e ) {
				if( TypedListDialog.this.tableViewer.getTable().getItem( new Point( e.x, e.y )) == null )
					TypedListDialog.this.tableViewer.setSelection( new StructuredSelection());
			}
		});

		this.tableViewer.setInput( this.listAsModel );
	}

	/**
	 * @return the model
	 */
	public ListAsModel getListAsModel() {
		return this.listAsModel;
	}

	/**
	 * @return the elements displayed by the table viewer.
	 * Elements are separated by two semicolons, e.g. "value1 ;; value2 ;; value3".
	 */
	public String getText() {

		StringBuilder result = new StringBuilder();
		for( StringModel model : this.listAsModel.getList())
			result.append( model.getText().trim() + " ;; " ); //$NON-NLS-1$

		return result.toString();
	}

	/**
	 * Update model attribute.
	 * @param msgError the error message
	 * @param stringModel the string model element to update.
	 */
	private void setError( String msgError, StringModel stringModel ) {
		if( stringModel == null )
			return;

		if( msgError != null ) {
			getButton( IDialogConstants.OK_ID ).setEnabled( false );
			stringModel.setValueValid( false );
			stringModel.setErrorMsg( msgError );
		}
		else {
			getButton( IDialogConstants.OK_ID ).setEnabled( true );
			stringModel.setValueValid( true );
		}
	}

	/**
	 * The content provider used by the viewer.
	 */
	class ListAsModelContentProvider
	implements IStructuredContentProvider, ListAsModelViewer {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider
		 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if( newInput != null )
				((ListAsModel) newInput).addChangeListener( this );
			if( oldInput != null )
				((ListAsModel) oldInput).removeChangeListener( this );
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			TypedListDialog.this.listAsModel.removeChangeListener( this );
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider
		 * #getElements(java.lang.Object)
		 */
		public Object[] getElements(Object parent) {
			if( parent != null && parent.getClass().equals( ListAsModel.class ))
				return ((ListAsModel) parent).getList().toArray();
			return new Object[ 0 ];
		}

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.ListAsModelViewer
		 * #add(java.lang.String)
		 */
		public void add(StringModel value) {
			TypedListDialog.this.tableViewer.add( value );
		}

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.ListAsModelViewer
		 * #remove(java.lang.String)
		 */
		public void remove(StringModel value) {
			TypedListDialog.this.tableViewer.remove( value );
		}

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.ListAsModelViewer
		 * #update(com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.StringModel)
		 */
		public void update(StringModel value) {
			TypedListDialog.this.tableViewer.update( value, null );
		}

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.ListAsModelViewer
		 * #moveDown(com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.StringModel)
		 */
		public void moveDown( StringModel value ) {
			TypedListDialog.this.tableViewer.refresh();
		}

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.ListAsModelViewer
		 * #moveUp(com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.table.ListAsModel.StringModel)
		 */
		public void moveUp( StringModel value ) {
			TypedListDialog.this.tableViewer.refresh();
		}
	}

	/**
	 * A cell editor to open a file dialog.
	 */
	static class FileDialogCellEditor extends DialogCellEditor {

		/**
		 * @param parent
		 */
		public FileDialogCellEditor( Composite parent ) {
			super( parent );
		}

		@Override
		protected Object openDialogBox( Control cellEditorWindow ) {
			FileDialog dlg = new FileDialog( cellEditorWindow.getShell(), SWT.SINGLE );
			dlg.setText( Messages.SwtWidgetGenerator_5 );
			dlg.setFilterNames( new String[] { Messages.SwtWidgetGenerator_6 });
			dlg.setFilterExtensions( new String[] { "*.*" }); //$NON-NLS-1$

			String result = dlg.open();
			if( result != null ) {
				try {
					return new File( result ).toURI().toURL().toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * An abstract editing support for a table viewer.
	 * The cell editor associated with the viewer depends on the HciType.
	 */
	protected abstract class AbstractEditingSupport extends EditingSupport {
		/** The cell editor to associate with the table viewer. */
		private CellEditor editor;


		/**
		 * Constructor.
		 * Creates the appropriate cell editor (with its valdiation) based on the HciType.
		 * @param viewer
		 */
		public AbstractEditingSupport( TableViewer viewer ) {
			super( viewer );
			switch( TypedListDialog.this.hciType ) {

			case INTEGER:
				TextCellEditor intEditor = new TextCellEditor( viewer.getTable());
				((Text) intEditor.getControl()).setTextLimit( TEXT_LIMIT );
				this.editor = intEditor;

				intEditor.setValidator( new ICellEditorValidator() {
					public String isValid(Object value) {

						if( value != null && String.class.equals( value.getClass())) {
							try {
								Integer.parseInt((String) value );
								setError( null, getSelection());
							} catch( Exception e ) {
								setError( NLS.bind( Messages.TypedListDialog_2, value ), getSelection());
							}
						}
						return null;
					}
				});
				break;

			case STRING:
				TextCellEditor textEditor = new TextCellEditor( viewer.getTable());
				((Text) textEditor.getControl()).setTextLimit( TEXT_LIMIT );
				this.editor = textEditor;
				break;

			case URI:
				TextCellEditor uriEditor = new TextCellEditor( viewer.getTable());
				((Text) uriEditor.getControl()).setTextLimit( TEXT_LIMIT );
				this.editor = uriEditor;

				uriEditor.setValidator( new ICellEditorValidator() {
					public String isValid(Object value) {

						if( value != null && String.class.equals( value.getClass())) {
							try {
								new URI((String) value );
								setError( null, getSelection());
							} catch( Exception e ) {
								setError( NLS.bind( Messages.TypedListDialog_18, value ), getSelection());
							}
						}
						return null;
					}
				});
				break;

			case ENUM:
				this.editor = new ComboBoxCellEditor( viewer.getTable(), TypedListDialog.this.enumValues, SWT.READ_ONLY );
				break;

			case DOUBLE:
				TextCellEditor doubleEditor = new TextCellEditor( viewer.getTable());
				((Text) doubleEditor.getControl()).setTextLimit( TEXT_LIMIT );
				this.editor = doubleEditor;

				doubleEditor.setValidator( new ICellEditorValidator() {
					public String isValid(Object value) {

						if( value != null && String.class.equals( value.getClass())) {
							try {
								Double.parseDouble((String) value );
								setError( null, getSelection());
							} catch( Exception e ) {
								setError( NLS.bind( Messages.TypedListDialog_20, value ), getSelection());
							}
						}
						return null;
					}
				});
				break;

			case FILE:
				this.editor = new FileDialogCellEditor( viewer.getTable());
				break;

			default: break;
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
		 */
		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
		 */
		@Override
		protected CellEditor getCellEditor(Object element) {
			return this.editor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
		 */
		@Override
		protected void setValue(Object element, Object value) {
			doSetValue(element, value);
			getViewer().update(element, null);
		}

		/**
		 * Update the element with the value.
		 */
		protected abstract void doSetValue(Object element, Object value);
	}
}
