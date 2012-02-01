/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.StyledFilteredList;

/**
 * An element list selection dialog that supports {@link StyledCellLabelProvider}s.
 * <p>
 * The code was taken from {@link AbstractElementListSelectionDialog} and modified
 * to work with {@link StyledFilteredList}s.
 * </p>
 * 
 * @contributor Vincent Zurczak - EBM WebSourcing
 */
public class StyledElementListSelectionDialog extends SelectionStatusDialog {

	//
	// Code from AbstractElementListSelectionDialog
	//

	// VZ: changed the type of the renderer
	private final IStyledLabelProvider fRenderer;

	private boolean fIgnoreCase = true;

	private boolean fIsMultipleSelection = false;

	private Label fMessage;

	// VZ
	protected StyledFilteredList fFilteredList;
	// VZ

	private Text fFilterText;

	private ISelectionStatusValidator fValidator;

	private String fFilter = null;

	private String fEmptyListMessage = ""; //$NON-NLS-1$

	private String fEmptySelectionMessage = ""; //$NON-NLS-1$

	private int fWidth = 60;

	private int fHeight = 18;

	private Object[] fSelection = new Object[0];

	/**
	 * Constructs a list selection dialog.
	 * @param parent The parent for the list.
	 * @param renderer ILabelProvider for the list
	 */
	// VZ: changed the name, visibility and parameter type in the constructor
	public StyledElementListSelectionDialog( Shell parent, IStyledLabelProvider renderer ) {
		super( parent );
		this.fRenderer = renderer;
	}

	/**
	 * Handles default selection (double click).
	 * By default, the OK button is pressed.
	 */
	protected void handleDefaultSelected() {
		if (validateCurrentSelection()) {
			buttonPressed(IDialogConstants.OK_ID);
		}
	}

	/**
	 * Specifies if sorting, filtering and folding is case sensitive.
	 * @param ignoreCase
	 */
	public void setIgnoreCase(boolean ignoreCase) {
		this.fIgnoreCase = ignoreCase;
	}

	/**
	 * Returns if sorting, filtering and folding is case sensitive.
	 * @return boolean
	 */
	public boolean isCaseIgnored() {
		return this.fIgnoreCase;
	}

	/**
	 * Specifies if multiple selection is allowed.
	 * @param multipleSelection
	 */
	public void setMultipleSelection(boolean multipleSelection) {
		this.fIsMultipleSelection = multipleSelection;
	}

	/**
	 * Sets the list size in unit of characters.
	 * @param width  the width of the list.
	 * @param height the height of the list.
	 */
	public void setSize(int width, int height) {
		this.fWidth = width;
		this.fHeight = height;
	}

	/**
	 * Sets the message to be displayed if the list is empty.
	 * @param message the message to be displayed.
	 */
	public void setEmptyListMessage(String message) {
		this.fEmptyListMessage = message;
	}

	/**
	 * Sets the message to be displayed if the selection is empty.
	 * @param message the message to be displayed.
	 */
	public void setEmptySelectionMessage(String message) {
		this.fEmptySelectionMessage = message;
	}

	/**
	 * Sets an optional validator to check if the selection is valid.
	 * The validator is invoked whenever the selection changes.
	 * @param validator the validator to validate the selection.
	 */
	public void setValidator(ISelectionStatusValidator validator) {
		this.fValidator = validator;
	}

	/**
	 * Sets the elements of the list (widget).
	 * To be called within open().
	 * @param elements the elements of the list.
	 */
	protected void setListElements(Object[] elements) {
		Assert.isNotNull(this.fFilteredList);
		this.fFilteredList.setElements(elements);
	}

	/**
	 * Sets the filter pattern.
	 * @param filter the filter pattern.
	 */
	public void setFilter(String filter) {
		if (this.fFilterText == null) {
			this.fFilter = filter;
		} else {
			this.fFilterText.setText(filter);
		}
	}

	/**
	 * Returns the current filter pattern.
	 * @return returns the current filter pattern or <code>null<code> if filter was not set.
	 */
	public String getFilter() {
		if (this.fFilteredList == null) {
			return this.fFilter;
		}
		return this.fFilteredList.getFilter();
	}

	/**
	 * Sets the selection referenced by an array of elements.
	 * Empty or null array removes selection.
	 * To be called within open().
	 * @param selection the indices of the selection.
	 */
	protected void setSelection(Object[] selection) {
		Assert.isNotNull(this.fFilteredList);
		this.fFilteredList.setSelection(selection);
	}

	/**
	 * Returns an array of the currently selected elements.
	 * To be called within or after open().
	 * @return returns an array of the currently selected elements.
	 */
	protected Object[] getSelectedElements() {
		Assert.isNotNull(this.fFilteredList);
		return this.fFilteredList.getSelection();
	}

	/**
	 * Creates the message text widget and sets layout data.
	 * @param composite the parent composite of the message area.
	 */
	@Override
	protected Label createMessageArea(Composite composite) {
		Label label = super.createMessageArea(composite);

		GridData data = new GridData();
		data.grabExcessVerticalSpace = false;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.BEGINNING;
		label.setLayoutData(data);

		this.fMessage = label;

		return label;
	}

	/**
	 * Handles a selection changed event.
	 * By default, the current selection is validated.
	 */
	protected void handleSelectionChanged() {
		validateCurrentSelection();
	}

	/**
	 * Validates the current selection and updates the status line
	 * accordingly.
	 * @return boolean <code>true</code> if the current selection is
	 * valid.
	 */
	protected boolean validateCurrentSelection() {
		Assert.isNotNull(this.fFilteredList);

		IStatus status;
		Object[] elements = getSelectedElements();

		if (elements.length > 0) {
			if (this.fValidator != null) {
				status = this.fValidator.validate(elements);
			} else {
				status = new Status(IStatus.OK, PlatformUI.PLUGIN_ID,
							IStatus.OK, "", //$NON-NLS-1$
							null);
			}
		} else {
			if (this.fFilteredList.isEmpty()) {
				status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
							IStatus.ERROR, this.fEmptyListMessage, null);
			} else {
				status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
							IStatus.ERROR, this.fEmptySelectionMessage, null);
			}
		}

		updateStatus(status);

		return status.isOK();
	}

	/*
	 * @see Dialog#cancelPressed
	 */
	@Override
	protected void cancelPressed() {
		setResult(null);
		super.cancelPressed();
	}

	/**
	 * Creates a filtered list.
	 * @param parent the parent composite.
	 * @return returns the filtered list widget.
	 */
	protected StyledFilteredList createFilteredList(Composite parent) {
		int flags = SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL
		| (this.fIsMultipleSelection ? SWT.MULTI : SWT.SINGLE);

		StyledFilteredList list = new StyledFilteredList( parent, flags, this.fRenderer );

		GridData data = new GridData();
		data.widthHint = convertWidthInCharsToPixels(this.fWidth);
		data.heightHint = convertHeightInCharsToPixels(this.fHeight);
		data.grabExcessVerticalSpace = true;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		list.setLayoutData(data);
		list.setFont(parent.getFont());
		list.setFilter((this.fFilter == null ? "" : this.fFilter)); //$NON-NLS-1$

		list.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				handleDefaultSelected();
			}

			public void widgetSelected(SelectionEvent e) {
				handleWidgetSelected();
			}
		});

		this.fFilteredList = list;

		return list;
	}

	// 3515
	private void handleWidgetSelected() {
		Object[] newSelection = this.fFilteredList.getSelection();

		if (newSelection.length != this.fSelection.length) {
			this.fSelection = newSelection;
			handleSelectionChanged();
		} else {
			for (int i = 0; i != newSelection.length; i++) {
				if (!newSelection[i].equals(this.fSelection[i])) {
					this.fSelection = newSelection;
					handleSelectionChanged();
					break;
				}
			}
		}
	}

	protected Text createFilterText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);

		GridData data = new GridData();
		data.grabExcessVerticalSpace = false;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.BEGINNING;
		text.setLayoutData(data);
		text.setFont(parent.getFont());

		text.setText((this.fFilter == null ? "" : this.fFilter)); //$NON-NLS-1$

		Listener listener = new Listener() {
			public void handleEvent(Event e) {
				StyledElementListSelectionDialog.this.fFilteredList.setFilter(StyledElementListSelectionDialog.this.fFilterText.getText());
			}
		};
		text.addListener(SWT.Modify, listener);

		text.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_DOWN) {
					StyledElementListSelectionDialog.this.fFilteredList.setFocus();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		this.fFilterText = text;

		return text;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#open()
	 */
	@Override
	public int open() {
		super.open();
		return getReturnCode();
	}

	private void access$superCreate() {
		super.create();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#create()
	 */
	@Override
	public void create() {

		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				access$superCreate();

				Assert.isNotNull(StyledElementListSelectionDialog.this.fFilteredList);

				if (StyledElementListSelectionDialog.this.fFilteredList.isEmpty()) {
					handleEmptyList();
				} else {
					validateCurrentSelection();
					StyledElementListSelectionDialog.this.fFilterText.selectAll();
					StyledElementListSelectionDialog.this.fFilterText.setFocus();
				}
			}
		});

	}

	/**
	 * Handles empty list by disabling widgets.
	 */
	protected void handleEmptyList() {
		this.fMessage.setEnabled(false);
		this.fFilterText.setEnabled(false);
		this.fFilteredList.setEnabled(false);
		updateOkState();
	}

	/**
	 * Update the enablement of the OK button based on whether or not there
	 * is a selection.
	 *
	 */
	protected void updateOkState() {
		Button okButton = getOkButton();
		if (okButton != null) {
			okButton.setEnabled(getSelectedElements().length != 0);
		}
	}

	/**
	 * Gets the optional validator used to check if the selection is valid.
	 * The validator is invoked whenever the selection changes.
	 * @return the validator to validate the selection, or <code>null</code>
	 * if no validator has been set.
	 * 
	 * @since 3.5
	 */
	protected ISelectionStatusValidator getValidator() {
		return this.fValidator;
	}


	// Code from ElementListSelectionDialog

	private Object[] fElements;

	/**
	 * Sets the elements of the list.
	 * @param elements the elements of the list.
	 */
	public void setElements(Object[] elements) {
		this.fElements = elements;
	}

	/*
	 * @see SelectionStatusDialog#computeResult()
	 */
	@Override
	protected void computeResult() {
		setResult(Arrays.asList(getSelectedElements()));
	}

	/*
	 * @see Dialog#createDialogArea(Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);

		createMessageArea(contents);
		createFilterText(contents);
		createFilteredList(contents);

		setListElements(this.fElements);

		setSelection(getInitialElementSelections().toArray());

		return contents;
	}
}
