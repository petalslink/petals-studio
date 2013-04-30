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
package com.ebmwebsourcing.petals.services.su.ui;

import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A dialog to edit service operations for jbi.xml files.
 * <p>
 * This dialog was originally used in the CDK page (consume wizard).
 * It was replaced by a more direct approach.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceOperationDialog extends TitleAreaDialog {

	private final Map<QName, Mep> opNameToMep;
	private QName customOperation;
	private Mep customMep;

	private boolean useCustomOperation;
	private Text nameText, nsText;
	private ComboViewer mepViewer;


	/**
	 * Constructor.
	 * @param parentShell
	 * @param opNameToMep (not null)
	 */
	public ServiceOperationDialog( Shell parentShell, Map<QName, Mep> opNameToMep ) {
		super( parentShell );
		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX );
		this.opNameToMep = opNameToMep;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		// Create the parent
		Composite bigContainer = (Composite) super.createDialogArea( parent );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		bigContainer.setLayout( layout );
		bigContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Composite container = new Composite( bigContainer, SWT.NONE );
		container.setLayout( new GridLayout( 2, true ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Put a viewer on the left
		final TableViewer viewer = new TableViewer( container, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION );
		viewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		viewer.setContentProvider( new ArrayContentProvider());
		viewer.setLabelProvider( new DelegatingStyledCellLabelProvider( new OperationLabelProvider()));
		viewer.setInput( this.opNameToMep.keySet());


		// Add widgets on the right
		Composite rightPart = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		rightPart.setLayout( layout );
		rightPart.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final Button customOpButton = new Button( rightPart, SWT.CHECK );
		customOpButton.setText( "Define a custom operation" );
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		customOpButton.setLayoutData( layoutData );

		Label l = new Label( rightPart, SWT.NONE );
		l.setText( "Name space:" );
		l.setToolTipText( "The operation's name space" );

		this.nsText = new Text( rightPart, SWT.BORDER | SWT.SINGLE );
		this.nsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		l = new Label( rightPart, SWT.NONE );
		l.setText( "Name:" );
		l.setToolTipText( "The operation's name" );

		this.nameText = new Text( rightPart, SWT.BORDER | SWT.SINGLE );
		this.nameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		l = new Label( rightPart, SWT.NONE );
		l.setText( "MEP:" );
		l.setToolTipText( "The Message Exchange Pattern" );

		this.mepViewer = new ComboViewer( rightPart, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		this.mepViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.mepViewer.setContentProvider( new ArrayContentProvider());
		this.mepViewer.setLabelProvider( new LabelProvider());
		this.mepViewer.setInput( Mep.values());


		// Complete the dialog properties
		getShell().setText( "Operation Viewer" );
		setTitle( "Operation Viewer" );
		setMessage( "View and edit service operations." );


		// Add the listeners
		customOpButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				ServiceOperationDialog.this.useCustomOperation = customOpButton.getSelection();
				ServiceOperationDialog.this.nsText.setEditable( customOpButton.getSelection());
				ServiceOperationDialog.this.nameText.setEditable( customOpButton.getSelection());
				ServiceOperationDialog.this.mepViewer.getCombo().setEnabled( customOpButton.getSelection());
				validate();
			}
		});

		viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {
				Object o = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
				ServiceOperationDialog.this.nsText.setText(((QName) o).getNamespaceURI());
				ServiceOperationDialog.this.nameText.setText(((QName) o).getLocalPart());

				Mep mep = ServiceOperationDialog.this.opNameToMep.get( o );
				ServiceOperationDialog.this.mepViewer.setSelection( new StructuredSelection( mep ));
			}
		});

		customOpButton.setSelection( false );
		customOpButton.notifyListeners( SWT.Selection, new Event());

		ModifyListener modifyListener = new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				if((((Text) e.widget).getStyle() & SWT.READ_ONLY) == 0 )
					validate();
			}
		};

		this.nameText.addModifyListener( modifyListener );
		this.nsText.addModifyListener( modifyListener );
		this.mepViewer.getCombo().addSelectionListener( new SelectionListener() {
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			public void widgetSelected( SelectionEvent e ) {
				if(((Combo) e.widget).isEnabled())
					validate();
			}
		});

		return bigContainer;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #getInitialSize()
	 */
	@Override
	protected Point getInitialSize () {
		return new Point( 600, 400 );
	}


	/**
	 * Validates the user fields.
	 */
	private void validate() {

		String msg = null;
		this.customOperation = null;
		this.customMep = Mep.UNKNOWN;
		if( this.useCustomOperation ) {

			if( StringUtils.isEmpty( this.nsText.getText()))
				msg = "An operation name space cannot be empty.";
			else if( StringUtils.isEmpty( this.nameText.getText()))
				msg = "An operation name cannot be empty.";
			else {
				ServiceOperationDialog.this.customOperation = new QName( this.nsText.getText(), this.nameText.getText());
				if( ! this.mepViewer.getSelection().isEmpty())
					this.customMep = (Mep) ((IStructuredSelection) this.mepViewer.getSelection()).getFirstElement();
			}
		}

		setErrorMessage( msg );
		Button okButton = getButton( IDialogConstants.OK_ID );
		if( okButton != null )
			okButton.setEnabled( msg == null );
	}


	/**
	 * @return the customOperation (null if no custom operation was defined)
	 */
	public QName getCustomOperation() {
		return this.customOperation;
	}


	/**
	 * @return the customMep (only makes sense if the custom operation is not null)
	 */
	public Mep getCustomMep() {
		return this.customMep;
	}
}
