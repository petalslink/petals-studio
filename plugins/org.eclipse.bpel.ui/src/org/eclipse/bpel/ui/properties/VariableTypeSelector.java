/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.properties;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.viewers.CComboViewer;
import org.eclipse.bpel.common.ui.details.widgets.DecoratedLabel;
import org.eclipse.bpel.common.ui.details.widgets.StatusLabel2;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.common.ui.flatui.FlatFormLayout;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.details.providers.AddNullFilter;
import org.eclipse.bpel.ui.details.providers.AddSelectedObjectFilter;
import org.eclipse.bpel.ui.details.providers.FaultContentProvider;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.ModelViewerSorter;
import org.eclipse.bpel.ui.details.providers.OperationContentProvider;
import org.eclipse.bpel.ui.details.providers.PortTypeContentProvider;
import org.eclipse.bpel.ui.details.providers.VariableTypeTreeContentProvider;
import org.eclipse.bpel.ui.uiextensionmodel.VariableExtension;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.BrowseUtil;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.wsdl.Fault;
import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.Output;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * A composite for selecting either a "Data Type" (XSD type, or XSD element containing
 * an anonymous XSD type) an "Interface" type (actually a WSDL message, specified by
 * choosing then Interface+Operation+direction/fault)
 */
public class VariableTypeSelector extends Composite {

	public static final int KIND_UNKNOWN = VariableExtension.KIND_UNKNOWN;
	public static final int KIND_INTERFACE = VariableExtension.KIND_INTERFACE;
	public static final int KIND_DATATYPE = VariableExtension.KIND_DATATYPE;

	protected static final int DATATYPE_RADIO_CONTEXT = 1;
	protected static final int INTERFACE_RADIO_CONTEXT = 2;

	protected static final int OPERATION_INPUTRADIO_CONTEXT = 3;
	protected static final int OPERATION_OUTPUTRADIO_CONTEXT = 4;
	protected static final int OPERATION_FAULTRADIO_CONTEXT = 5;
	protected static final int OPERATION_COMBO_CONTEXT = 6;
	protected static final int INTERFACE_COMBO_CONTEXT = 7;
	protected static final int INTERFACE_BROWSE_CONTEXT = 8;
	protected static final int DATATYPE_BROWSE_CONTEXT = 9;
	protected static final int FAULT_COMBO_CONTEXT = 10;

	protected int lastChangeContext = -1;

	protected boolean inUpdate = false;

	protected int kindHint = KIND_UNKNOWN;

	public static final int STANDARD_LABEL_WIDTH_SM = 125;
	public static final int STANDARD_LABEL_WIDTH_AVG = STANDARD_LABEL_WIDTH_SM * 5/4;

	// private static final StructuredViewer dataTypeViewer = null;

	protected EObject variableType;
	protected Variable myVariable;

	protected Button dataTypeRadio, interfaceRadio;
	protected Composite dataTypeComposite, interfaceComposite;

	protected TabbedPropertySheetWidgetFactory wf;

	protected CComboViewer interfaceViewer;
	protected Button interfaceBrowseButton;
	protected CComboViewer operationViewer;
	protected Button operationInputRadio, operationOutputRadio, operationFaultRadio;
	protected CComboViewer faultViewer;

	protected Label interfaceLabel, operationLabel, faultLabel;

	protected AddSelectedObjectFilter interfaceAddSelectedObjectFilter;
	protected AddSelectedObjectFilter operationAddSelectedObjectFilter;
	protected AddSelectedObjectFilter faultAddSelectedObjectFilter;

	protected Button dataTypeBrowseButton;
	protected Hyperlink dataTypeNameText;
	protected Tree dataTypeTree;
	protected TreeViewer dataTypeTreeViewer;
	protected StatusLabel2 dataTypeLabel;

	protected BPELEditor bpelEditor;
	protected Callback callback;
	protected Shell shell;
	protected boolean allowElements = false;
	protected boolean nullFilterAdded = false;

	public VariableTypeSelector(Composite parent, int style, BPELEditor bpelEditor,
				TabbedPropertySheetWidgetFactory wf, Callback callback, boolean allowElements)
	{
		super(parent, style);
		this.bpelEditor = bpelEditor;
		this.shell = bpelEditor.getSite().getShell();
		this.wf = wf;
		this.callback = callback;
		this.allowElements = allowElements;

		Composite parentComposite = createComposite(this);
		this.setLayout(new FillLayout(SWT.VERTICAL));

		FlatFormLayout formLayout = new FlatFormLayout();

		formLayout.marginWidth = formLayout.marginHeight = 0;
		parentComposite.setLayout(formLayout);

		createDataTypeWidgets(parentComposite);
	}

	/**
	 * Refresh the given CComboViewer, and also make sure selectedObject is selected in it.
	 */
	protected void refreshCCombo(CComboViewer viewer, Object selectedObject) {
		viewer.refresh();
		if (selectedObject == null) {
			// work-around the null check in StructuredSelection(Object) ctor.
			viewer.setSelectionNoNotify(new StructuredSelection(Collections.singletonList(null)), false);
		} else {
			viewer.setSelectionNoNotify(new StructuredSelection(selectedObject), false);
		}
	}

	protected void selectComposite(int n) {

		this.interfaceRadio.setSelection(n == Callback.KIND_INTERFACE);

		this.interfaceComposite.setVisible(n == Callback.KIND_INTERFACE);
		this.dataTypeRadio.setSelection(n == Callback.KIND_DATATYPE);
		this.dataTypeComposite.setVisible(n == Callback.KIND_DATATYPE);
	}

	/**
	 * Expects either a WSDL message, an XSD type, or an XSD element.
	 */
	public void setVariableType(EObject variableType) {
		//System.out.println("setVariableType: "+variableType);
		this.variableType = variableType;
		refresh();
	}

	/**
	 * This method is preferred over the EObject method if the caller has a BPELVariable
	 * model object, because this method supports hints stored in the VariableExtension.
	 */
	public void setVariable(Variable variable) {
		this.kindHint = KIND_UNKNOWN;
		this.myVariable = variable;

		if (variable != null) {
			if (variable.getMessageType() != null) {
				setVariableType(variable.getMessageType()); return;
			}
			if (variable.getType() != null) {
				setVariableType(variable.getType()); return;
			}
			if (variable.getXSDElement() != null) {
				setVariableType(variable.getXSDElement()); return;
			}
			VariableExtension varExt = (VariableExtension)ModelHelper.getExtension(variable);
			if (varExt != null) {
				this.kindHint = varExt.getVariableKind();
			}
		}
		setVariableType(null);
	}

	protected void updateDataTypeWidgets() {

		String name = null;
		ILabeledElement label =  (ILabeledElement) BPELUtil.adapt(this.variableType, ILabeledElement.class, this.myVariable);
		if (label != null) {
			name = label.getLabel(this.variableType);
		}

		if (name == null) {
			this.dataTypeNameText.setText(Messages.VariableTypeSelector_None_1);
			this.dataTypeNameText.setEnabled(false);
			this.dataTypeTreeViewer.setInput(null);
			this.dataTypeLabel.setText(Messages.VariableTypeSelector_Data_Type_2);
			return ;
		}

		this.dataTypeNameText.setText(name);
		if (this.variableType instanceof XSDTypeDefinition) {
			this.dataTypeNameText.setEnabled(true);
			this.dataTypeTreeViewer.setInput(this.variableType);
			this.dataTypeLabel.setText(Messages.VariableTypeSelector_Data_Type_2);
		} else if (this.variableType instanceof XSDElementDeclaration) {
			this.dataTypeNameText.setEnabled(true);
			this.dataTypeTreeViewer.setInput(this.variableType);
			this.dataTypeLabel.setText(Messages.VariableTypeSelector_0);
		} else if (this.variableType instanceof Message) {
			this.dataTypeNameText.setEnabled(true);
			this.dataTypeTreeViewer.setInput(this.variableType);
			this.dataTypeLabel.setText(Messages.VariableTypeSelector_1);
		}

		// scroll to the top ...
		ScrollBar bar = this.dataTypeTree.getVerticalBar();
		if (bar != null) {
			bar.setSelection(0);
		}

	}

	protected void updateInterfaceWidgets()  {
		PortType portType = null;
		Operation operation = null;
		Message message = null;
		if (this.variableType instanceof Message) {
			message = (Message)this.variableType;
		}
		if (message != null) operation = BPELUtil.getOperationFromMessage(message);
		if (operation != null) portType = (PortType)operation.eContainer();

		this.interfaceViewer.setInput(this.bpelEditor.getProcess());
		this.interfaceAddSelectedObjectFilter.setSelectedObject(portType);
		this.inUpdate = true;
		try {
			refreshCCombo(this.interfaceViewer, portType);

			if (operation == null) {
				if (!this.nullFilterAdded) {
					this.operationViewer.addFilter(AddNullFilter.getInstance());
					this.nullFilterAdded = true;
				}
			} else {
				if (this.nullFilterAdded) {
					this.operationViewer.removeFilter(AddNullFilter.getInstance());
					this.nullFilterAdded = false;
				}
			}
			this.operationViewer.setInput(portType);
			this.operationAddSelectedObjectFilter.setSelectedObject(operation);
			refreshCCombo(this.operationViewer, operation);
		} finally {
			this.inUpdate = false;
		}
		updateFaultRadio(message, operation);
	}

	protected void updateCompositeSelection() {
		int kind = this.kindHint;
		if (this.variableType instanceof Message) {
			kind = KIND_INTERFACE;
		} else if (this.variableType instanceof XSDTypeDefinition) {
			kind = KIND_DATATYPE;
		} else if (this.variableType instanceof XSDElementDeclaration) {
			kind = KIND_DATATYPE;
		}
		if (kind == KIND_UNKNOWN) kind = KIND_DATATYPE;
		selectComposite(kind);
		doChildLayout();
	}

	protected void doChildLayout() {
		this.dataTypeComposite.layout(true);
	}

	/**
	 * 
	 */

	public void refresh() {
		// updateInterfaceWidgets();
		updateDataTypeWidgets();
		// updateCompositeSelection();
	}

	/**
	 * Returns either a WSDL message, an XSD type, an XSD element, or null.
	 * @return the variable type
	 */

	public EObject getVariableType() {
		return this.variableType;
	}

	protected Composite createFlatFormComposite(Composite parent) {
		Composite result = createComposite(parent);
		FlatFormLayout formLayout = new FlatFormLayout();
		formLayout.marginWidth = formLayout.marginHeight = 0;
		result.setLayout(formLayout);
		return result;

	}

	/**
	 * If the widgets are set for faults and the new operation doesn't have any,
	 * this won't work - use computeMessageFromOperation instead.
	 */
	protected Message getStoreMessageFromOperation(Operation operation) {
		// TODO: dialog box if there are no operations?
		Message message = null;
		if (operation != null) {
			if (this.operationFaultRadio.getSelection()) {
				StructuredSelection sel = (StructuredSelection)this.faultViewer.getSelection();
				Fault fault = (Fault)sel.getFirstElement();
				if (fault != null) message = fault.getEMessage();
				return message;
			} else if (this.operationOutputRadio.getSelection()) {
				Output output = operation.getEOutput();
				if (output != null) message = output.getEMessage();
				return message;
			} else {
				Input input = operation.getEInput();
				if (input != null) message = input.getEMessage();
				return message;
			}
		}
		return message;
	}

	class RadioListener implements SelectionListener {
		int index;

		/** Radio listener for indexed button index.
		 * @param index
		 */
		public RadioListener(int index) {
			this.index = index;
		}

		public void widgetSelected(SelectionEvent e) {
			if (!((Button) e.widget).getSelection())
				return;
			VariableTypeSelector.this.lastChangeContext = this.index;
			VariableTypeSelector.this.callback.selectRadioButton(this.index);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}

	protected void createRadioButtonWidgets(Composite composite) {
		FlatFormData data;

		this.dataTypeRadio = createButton(composite, Messages.VariableTypeSelector_Data_Type_1, SWT.RADIO);
		data = new FlatFormData();
		data.top = new FlatFormAttachment(0, 0);
		data.left = new FlatFormAttachment(0, 0);
		this.dataTypeRadio.setLayoutData(data);

		this.interfaceRadio = createButton(composite, Messages.VariableTypeSelector_Interface_1, SWT.RADIO);
		data = new FlatFormData();
		data.top = new FlatFormAttachment(0, 0);
		data.left = new FlatFormAttachment(this.dataTypeRadio, IDetailsAreaConstants.HSPACE);
		this.interfaceRadio.setLayoutData(data);

		this.interfaceRadio.addSelectionListener(new RadioListener(Callback.KIND_INTERFACE));
		this.dataTypeRadio.addSelectionListener(new RadioListener(Callback.KIND_DATATYPE));
	}

	protected void createInterfaceWidgets(Composite parent) {
		FlatFormData data;

		Composite composite = this.interfaceComposite = createFlatFormComposite(parent);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.interfaceRadio, IDetailsAreaConstants.VMARGIN);
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.bottom = new FlatFormAttachment(100, 0);
		this.interfaceComposite.setLayoutData(data);

		this.interfaceBrowseButton = createButton(composite, Messages.VariableTypeSelector_Browse_1, SWT.PUSH);

		this.interfaceLabel = createLabel(composite, Messages.VariableTypeSelector_Interface_2);
		CCombo interfaceCombo = createCCombo(composite);
		this.interfaceViewer = new CComboViewer(interfaceCombo);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(interfaceCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(interfaceCombo, 0, SWT.CENTER);
		this.interfaceLabel.setLayoutData(data);

		this.interfaceAddSelectedObjectFilter = new AddSelectedObjectFilter();
		this.interfaceViewer.addFilter(AddNullFilter.getInstance());
		this.interfaceViewer.addFilter(this.interfaceAddSelectedObjectFilter);
		this.interfaceViewer.setLabelProvider(new ModelLabelProvider());
		this.interfaceViewer.setContentProvider(new PortTypeContentProvider());
		this.interfaceViewer.setSorter(ModelViewerSorter.getInstance());
		this.interfaceViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				PortType portType = (PortType)selection.getFirstElement();
				Operation operation = null;
				if (portType != null && !portType.getEOperations().isEmpty()) {
					operation = (Operation)portType.getEOperations().get(0);
				}
				VariableTypeSelector.this.lastChangeContext = INTERFACE_COMBO_CONTEXT;

				Message message = computeMessageFromOperation(operation);
				VariableTypeSelector.this.callback.selectMessageType(message);
				updateFaultRadio(message, operation);
			}
		});

		this.interfaceBrowseButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				PortType portType = BrowseUtil.browseForPortType(VariableTypeSelector.this.bpelEditor.getResourceSet(), VariableTypeSelector.this.shell);
				if (portType != null) {
					Operation operation = null;
					if (!portType.getEOperations().isEmpty()) {
						operation = (Operation)portType.getEOperations().get(0);
					}
					VariableTypeSelector.this.lastChangeContext = INTERFACE_BROWSE_CONTEXT;

					Message message = computeMessageFromOperation(operation);
					VariableTypeSelector.this.callback.selectMessageType(message);
					updateFaultRadio(message, operation);
				}
			}
		});

		this.operationLabel = createLabel(composite, Messages.VariableTypeSelector_Operation_1);
		CCombo operationCombo = createCCombo(composite);
		this.operationViewer = new CComboViewer(operationCombo);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(operationCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(operationCombo, 0, SWT.CENTER);
		this.operationLabel.setLayoutData(data);

		this.operationAddSelectedObjectFilter = new AddSelectedObjectFilter();
		//operationViewer.addFilter(AddNullFilter.getInstance());
		this.operationViewer.addFilter(this.operationAddSelectedObjectFilter);
		this.operationViewer.setLabelProvider(new ModelLabelProvider());
		this.operationViewer.setContentProvider(new OperationContentProvider());
		this.operationViewer.setSorter(ModelViewerSorter.getInstance());
		this.operationViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				Operation operation = (Operation)selection.getFirstElement();
				VariableTypeSelector.this.lastChangeContext = OPERATION_COMBO_CONTEXT;
				Message message = computeMessageFromOperation(operation);
				VariableTypeSelector.this.callback.selectMessageType(message);
				updateFaultRadio(message, operation);
			}
		});

		Label directionLabel = createLabel(composite, Messages.VariableTypeSelector_Direction_1);

		// TODO: should these radio buttons really be a check box for the Reply case??
		// - for now no, since I'm afraid it might be kind of confusing.

		this.operationInputRadio = createButton(composite, Messages.VariableTypeSelector_Input_1, SWT.RADIO);
		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.operationViewer.getCCombo(), IDetailsAreaConstants.VSPACE);
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(directionLabel, STANDARD_LABEL_WIDTH_SM));
		this.operationInputRadio.setLayoutData(data);
		this.operationInputRadio.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (!VariableTypeSelector.this.operationInputRadio.getSelection()) return;
				StructuredSelection selection = (StructuredSelection)VariableTypeSelector.this.operationViewer.getSelection();
				Operation operation = (Operation)selection.getFirstElement();
				VariableTypeSelector.this.lastChangeContext = OPERATION_INPUTRADIO_CONTEXT;
				VariableTypeSelector.this.callback.selectMessageType(getStoreMessageFromOperation(operation));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		this.operationOutputRadio = createButton(composite, Messages.VariableTypeSelector_Output_1, SWT.RADIO);
		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.operationInputRadio, 0, SWT.TOP);
		data.left = new FlatFormAttachment(this.operationInputRadio, IDetailsAreaConstants.HSPACE);
		this.operationOutputRadio.setLayoutData(data);
		this.operationOutputRadio.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (!VariableTypeSelector.this.operationOutputRadio.getSelection()) return;
				StructuredSelection selection = (StructuredSelection)VariableTypeSelector.this.operationViewer.getSelection();
				Operation operation = (Operation)selection.getFirstElement();
				VariableTypeSelector.this.lastChangeContext = OPERATION_OUTPUTRADIO_CONTEXT;
				VariableTypeSelector.this.callback.selectMessageType(getStoreMessageFromOperation(operation));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		this.operationFaultRadio = createButton(composite, Messages.VariableTypeSelector_Fault_1, SWT.RADIO);
		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.operationOutputRadio, 0, SWT.TOP);
		data.left = new FlatFormAttachment(this.operationOutputRadio, IDetailsAreaConstants.HSPACE);
		this.operationFaultRadio.setLayoutData(data);
		this.operationFaultRadio.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (!VariableTypeSelector.this.operationFaultRadio.getSelection()) return;
				StructuredSelection selection = (StructuredSelection)VariableTypeSelector.this.operationViewer.getSelection();
				Operation operation = (Operation)selection.getFirstElement();
				VariableTypeSelector.this.lastChangeContext = OPERATION_FAULTRADIO_CONTEXT;
				// Get the fault from the operation.
				Fault fault = (Fault)operation.getEFaults().get(0);
				Message message = fault.getEMessage();
				VariableTypeSelector.this.callback.selectMessageType(message);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		this.faultLabel = createLabel(composite, Messages.VariableTypeSelector_Fault_2);

		CCombo faultCombo = createCCombo(composite);
		this.faultViewer = new CComboViewer(faultCombo);

		//		data = new FlatFormData();
		//		data.top = new FlatFormAttachment(operationFaultRadio, IDetailsAreaConstants.VSPACE);
		//		data.left = new FlatFormAttachment(faultLabel, IDetailsAreaConstants.HSPACE);
		//		faultCombo.setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(faultCombo, 0, SWT.CENTER);
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(faultCombo, -IDetailsAreaConstants.HSPACE);
		this.faultLabel.setLayoutData(data);

		this.faultAddSelectedObjectFilter = new AddSelectedObjectFilter();
		this.faultViewer.addFilter(this.faultAddSelectedObjectFilter);
		this.faultViewer.setLabelProvider(new ModelLabelProvider());
		this.faultViewer.setContentProvider(new FaultContentProvider());
		this.faultViewer.setSorter(ModelViewerSorter.getInstance());
		this.faultViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)VariableTypeSelector.this.operationViewer.getSelection();
				Operation operation = (Operation)selection.getFirstElement();
				VariableTypeSelector.this.lastChangeContext = FAULT_COMBO_CONTEXT;
				VariableTypeSelector.this.callback.selectMessageType(getStoreMessageFromOperation(operation));
			}
		});

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(operationCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.operationInputRadio, 0, SWT.CENTER);
		directionLabel.setLayoutData(data);

		internalSetLayoutData();
	}

	protected void createDataTypeWidgets(Composite parent) {
		FlatFormData data;

		Composite composite = this.dataTypeComposite = createFlatFormComposite(parent);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VMARGIN);
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.bottom = new FlatFormAttachment(100, 0);
		this.dataTypeComposite.setLayoutData(data);


		this.dataTypeBrowseButton = createButton(composite, Messages.VariableTypeSelector_Browse_2, SWT.PUSH);
		DecoratedLabel label = new DecoratedLabel(composite,SWT.LEFT);
		label.setText(Messages.VariableTypeSelector_Data_Type_2);
		this.wf.adapt(label);
		this.dataTypeLabel = new StatusLabel2( label );
		this.dataTypeNameText = createHyperlink(composite, "", SWT.NONE); //$NON-NLS-1$
		this.dataTypeNameText.setToolTipText(Messages.VariableTypeSelector_3);
		this.dataTypeNameText.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				BPELUtil.openEditor(getVariableType(), VariableTypeSelector.this.bpelEditor);
			}
		});

		Label dataTypeTreeLabel = createLabel(composite, Messages.VariableTypeSelector_2);

		// Tree viewer for variable structure ...
		this.dataTypeTree = this.wf.createTree(composite, SWT.NONE);
		VariableTypeTreeContentProvider variableContentProvider = new VariableTypeTreeContentProvider(true, true);
		this.dataTypeTreeViewer = new TreeViewer(this.dataTypeTree);
		this.dataTypeTreeViewer.setContentProvider(variableContentProvider);
		this.dataTypeTreeViewer.setLabelProvider(new ModelTreeLabelProvider());
		this.dataTypeTreeViewer.setInput ( null );
		this.dataTypeTreeViewer.setAutoExpandLevel(3);
		// end tree viewer for variable structure

		// layout data type label
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.dataTypeLabel.getControl(),
					STANDARD_LABEL_WIDTH_SM));
		data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		data.right = new FlatFormAttachment(60,0);
		this.dataTypeNameText.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0,IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(this.dataTypeNameText, -IDetailsAreaConstants.HSPACE);
		// data.top = new FlatFormAttachment(dataTypeNameText, IDetailsAreaConstants.VSPACE, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.dataTypeNameText,0,SWT.BOTTOM);
		this.dataTypeLabel.setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.dataTypeNameText, -2, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.dataTypeLabel.getLabel(), +2, SWT.BOTTOM);
		data.right = new FlatFormAttachment(100,-IDetailsAreaConstants.HSPACE);
		this.dataTypeBrowseButton.setLayoutData(data);

		this.dataTypeBrowseButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {

				Object xsdType = BrowseUtil.browseForXSDTypeOrElement(VariableTypeSelector.this.bpelEditor.getProcess(), getShell());
				if (xsdType != null) {
					VariableTypeSelector.this.lastChangeContext = DATATYPE_BROWSE_CONTEXT;
					if (xsdType instanceof XSDTypeDefinition) {
						// TODO:WDG: if type is anonymous, use enclosing element
						VariableTypeSelector.this.callback.selectXSDType((XSDTypeDefinition)xsdType);
					} else if (xsdType instanceof XSDElementDeclaration) {
						VariableTypeSelector.this.callback.selectXSDElement((XSDElementDeclaration)xsdType);
					} else if (xsdType instanceof Message) {
						VariableTypeSelector.this.callback.selectMessageType((Message) xsdType);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) { }
		});

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.dataTypeLabel.getLabel(), 3*IDetailsAreaConstants.VSPACE, SWT.BOTTOM);
		dataTypeTreeLabel.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.dataTypeLabel.getLabel(), STANDARD_LABEL_WIDTH_SM));
		data.top = new FlatFormAttachment(dataTypeTreeLabel,0, SWT.TOP);
		data.right = new FlatFormAttachment(100,  -IDetailsAreaConstants.HSPACE) ;
		data.bottom = new FlatFormAttachment(100, -IDetailsAreaConstants.HSPACE);
		this.dataTypeTree.setLayoutData(data);

	}

	public interface Callback {
		public static final int KIND_DATATYPE = VariableExtension.KIND_DATATYPE;
		public static final int KIND_INTERFACE = VariableExtension.KIND_INTERFACE;
		public void selectRadioButton(int index);

		public void selectXSDType(XSDTypeDefinition xsdType);
		public void selectXSDElement(XSDElementDeclaration xsdElement);
		public void selectMessageType(Message message);
	}

	public Object getUserContext() {
		return Integer.valueOf( this.lastChangeContext );
	}
	public void restoreUserContext(Object userContext) {
		switch (((Integer)userContext).intValue()) {
		case DATATYPE_RADIO_CONTEXT: this.dataTypeRadio.setFocus(); return;
		case INTERFACE_RADIO_CONTEXT: this.interfaceRadio.setFocus(); return;
		case OPERATION_INPUTRADIO_CONTEXT: this.operationInputRadio.setFocus(); return;
		case OPERATION_OUTPUTRADIO_CONTEXT: this.operationOutputRadio.setFocus(); return;
		case OPERATION_FAULTRADIO_CONTEXT: this.operationFaultRadio.setFocus(); return;
		case OPERATION_COMBO_CONTEXT: this.operationViewer.getCCombo().setFocus(); return;
		case INTERFACE_COMBO_CONTEXT: this.interfaceViewer.getCCombo().setFocus(); return;
		case INTERFACE_BROWSE_CONTEXT: this.interfaceBrowseButton.setFocus(); return;
		case DATATYPE_BROWSE_CONTEXT: this.dataTypeBrowseButton.setFocus(); return;
		case FAULT_COMBO_CONTEXT: this.faultViewer.getCCombo().setFocus(); return;
		}
		throw new IllegalStateException();
	}

	@Override
	public void setEnabled(boolean enabled) {
		setEnabled(enabled, this,0);
	}

	void setEnabled ( boolean enabled, Control control , int depth) {

		if (control instanceof Composite) {
			Composite root = (Composite) control;
			Control list[] = root.getChildren();
			for( Control element : list ) {
				setEnabled(enabled,element,depth+1);
			}
		}
		if (depth > 0) {
			control.setEnabled(enabled);
		}
	}


	protected Button createButton(Composite parent, String text, int style) {
		return this.wf.createButton(parent, text, style);
	}
	protected Composite createComposite(Composite parent) {
		return this.wf.createComposite(parent);
	}
	protected Label createLabel(Composite parent, String text) {
		return this.wf.createLabel(parent, text);
	}
	protected Hyperlink createHyperlink(Composite parent, String text, int style) {
		return this.wf.createHyperlink(parent, text, style);
	}
	protected CCombo createCCombo(Composite parent) {
		return this.wf.createCCombo(parent);
	}

	protected void internalSetLayoutData() {
		FlatFormData data = new FlatFormData();
		// data.left = new FlatFormAttachment(50, -IDetailsAreaConstants.CENTER_SPACE + IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.interfaceViewer.getControl(), -1, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.interfaceViewer.getControl(), +1, SWT.BOTTOM);
		data.right = new FlatFormAttachment(100,-IDetailsAreaConstants.HSPACE);
		this.interfaceBrowseButton.setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(0, 2);
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.interfaceLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.interfaceBrowseButton, -IDetailsAreaConstants.HSPACE);
		this.interfaceViewer.getControl().setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.interfaceViewer.getControl(), IDetailsAreaConstants.VSPACE);
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.operationLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.interfaceBrowseButton, -IDetailsAreaConstants.HSPACE);
		this.operationViewer.getControl().setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.operationFaultRadio, IDetailsAreaConstants.VSPACE);
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.faultLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.interfaceBrowseButton, -IDetailsAreaConstants.HSPACE);
		this.faultViewer.getCCombo().setLayoutData(data);
	}

	protected void updateFaultRadio(Message message, Operation operation) {
		boolean isInput = true;
		boolean isOutput = false;
		boolean isFault = false;
		Fault fault = null;
		if (operation != null && message != null) {
			Iterator it = operation.getEFaults().iterator();
			while (it.hasNext()) {
				Fault nextFault = (Fault)it.next();
				Message faultMessage = nextFault.getEMessage();
				if (faultMessage != null && faultMessage.getQName() != null) {
					if (faultMessage.getQName().equals(message.getQName())) {
						isFault = true;
						isInput = false;
						isOutput = false;
						fault = nextFault;
					}
				}
			}
			Output output = operation.getEOutput();
			if (output != null) {
				Message outputMessage = output.getEMessage();
				if (outputMessage != null && outputMessage.getQName() != null) {
					if (outputMessage.getQName().equals(message.getQName())) {
						isOutput = true;
						isInput = false;
						isFault = false;
					}
				}
			}
		}
		this.operationInputRadio.setSelection(isInput);
		this.operationOutputRadio.setSelection(isOutput);
		this.operationFaultRadio.setSelection(isFault);
		this.operationFaultRadio.setVisible(operation != null && !operation.getEFaults().isEmpty());
		this.faultLabel.setVisible(isFault);
		this.faultViewer.getCCombo().setVisible(isFault);
		if (isFault) {
			if (operation != null) {
				this.faultViewer.setInput(operation);
				refreshCCombo(this.faultViewer, fault);
			}
		}
		this.layout(true);
		this.faultViewer.getCCombo().getParent().redraw();
		this.faultViewer.getCCombo().redraw();
		this.interfaceComposite.layout(true);
	}

	/**
	 * This one handles faults, also whacks the radio buttons when appropriate.
	 * Use this one after changing the operation, and the other one when
	 * leaving the operation alone.
	 */
	protected Message computeMessageFromOperation(Operation operation) {
		Message message = null;
		if (this.operationFaultRadio.getSelection()) {
			if (operation.getEFaults().isEmpty()) {
				this.operationFaultRadio.setSelection(false);
				this.operationOutputRadio.setSelection(true);
				message = getStoreMessageFromOperation(operation);
			} else {
				Fault fault = (Fault)operation.getEFaults().get(0);
				message = fault.getEMessage();
			}
		} else {
			message = getStoreMessageFromOperation(operation);
		}
		return message;
	}
}
