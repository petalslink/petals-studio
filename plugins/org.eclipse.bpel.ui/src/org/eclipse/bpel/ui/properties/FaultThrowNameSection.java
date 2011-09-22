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

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.IOngoingChange;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.Catch;
import org.eclipse.bpel.model.FaultHandler;
import org.eclipse.bpel.model.Invoke;
import org.eclipse.bpel.model.Throw;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.SetFaultNameCommand;
import org.eclipse.bpel.ui.commands.SetFaultNamespaceCommand;
import org.eclipse.bpel.ui.commands.SetVariableCommand;
import org.eclipse.bpel.ui.dialogs.VariableSelectorDialog;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.bpel.ui.util.NamespaceUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


/**
 * Details section for the fault name referenced in a Throw activity.
 */
public class FaultThrowNameSection extends BPELPropertySection {

	protected static final int NAME_BUILTIN_CONTEXT = 0;
	protected static final int NAMESPACE_CONTEXT = 1;
	protected static final int VARIABLE_CONTEXT = 2;
	protected static final int BUILTINRADIO_CONTEXT = 3;
	protected static final int USERDEFRADIO_CONTEXT = 4;
	protected static final int NAME_USERDEF_CONTEXT = 5;
	protected static final int FAULT_VARIABLE_CONTEXT = 6;

	protected int lastChangeContext = -1;

	protected boolean isCatch, isFaultTypeEnabled;

	protected Composite parentComposite;
	protected Composite faultTypeComposite, namespaceComposite, faultNameComposite, faultUserDefNameComposite, faultVariableNameComposite;

	protected Button builtinRadio, userdefRadio;
	protected Button /*namespaceBrowseButton,*/ variableBrowseButton;

	protected Text faultNamespaceText, faultUserDefText, variableNameText;
	protected CCombo faultNameCombo;
	protected Label variableName;

	protected ChangeTracker faultNameTracker, faultNamespaceTracker,
	faultUserDefNameTracker, variableNameTracker;

	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] {
					/* model object */
					new MultiObjectAdapter() {
						@Override
						public void notify(Notification n) {
							if (ModelHelper.isFaultNameAffected(getInput(), n) && FaultThrowNameSection.this.builtinRadio.getSelection()) {
								updateFaultNameWidgets();
							}
							else if (ModelHelper.isFaultNameAffected(getInput(), n) && FaultThrowNameSection.this.userdefRadio.getSelection()) {
								updateUserDefFaultNameWidgets();
							}

							if (FaultThrowNameSection.this.isFaultTypeEnabled && ModelHelper.isFaultNamespaceAffected(getInput(), n)) {
								updateFaultNamespaceWidgets();
							}
							if (ModelHelper.isVariableAffected(getInput(), n, ModelHelper.OUTGOING)) {
								updateFaultVariableWidgets();
								updateVariableWidgets();
							}
						}
					},
		};
	}

	protected boolean isNamespaceUserDef() {
		return this.isFaultTypeEnabled && this.userdefRadio.getSelection();
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	protected void doChildLayout() {
		FlatFormData data = null;
		FlatFormData data1 = null;
		if (this.isFaultTypeEnabled) {
			if (isNamespaceUserDef()) {
				data = (FlatFormData)this.faultUserDefNameComposite.getLayoutData();
				data.top = new FlatFormAttachment(this.namespaceComposite, IDetailsAreaConstants.VSPACE);
				data1 = (FlatFormData)this.faultVariableNameComposite.getLayoutData();
				data1.top = new FlatFormAttachment(this.faultUserDefNameComposite, IDetailsAreaConstants.VSPACE);
			} else {
				data = (FlatFormData)this.faultNameComposite.getLayoutData();
				data.top = new FlatFormAttachment(this.faultTypeComposite, IDetailsAreaConstants.VSPACE);
				data1 = (FlatFormData)this.faultVariableNameComposite.getLayoutData();
				data1.top = new FlatFormAttachment(this.faultNameComposite, IDetailsAreaConstants.VSPACE);
			}
		}

		this.faultTypeComposite.setVisible(this.isFaultTypeEnabled);
		this.namespaceComposite.setVisible(isNamespaceUserDef());
		this.faultNameComposite.setVisible(!isNamespaceUserDef());
		this.faultUserDefNameComposite.setVisible(isNamespaceUserDef());
		this.faultVariableNameComposite.setVisible(true);
		this.parentComposite.layout(true);
	}

	@Override
	protected void basicSetInput(EObject input) {
		super.basicSetInput(input);
		rearrangeWidgets();
	}

	protected void rearrangeWidgets() {
		// hack hack.
		this.isCatch = (getInput() instanceof Catch);

		this.isFaultTypeEnabled = true;
		if (this.isCatch) {
			FaultHandler faultHandler = (FaultHandler)getInput().eContainer();
			if (faultHandler != null) {
				Object maybeInvoke = faultHandler.eContainer();
				if (maybeInvoke instanceof Invoke)  this.isFaultTypeEnabled = false;
			}
		}
		doChildLayout();
	}

	protected void createFaultTypeWidgets(Composite parent) {
		FlatFormData data;

		Composite composite = this.faultTypeComposite = createFlatFormComposite(parent);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(0, 0);
		composite.setLayoutData(data);

		Label faultTypeLabel = this.fWidgetFactory.createLabel(composite, Messages.FaultThrowNameDetails_Fault_Type__13);
		this.builtinRadio = this.fWidgetFactory.createButton(composite, Messages.FaultThrowNameDetails_Built_in_14, SWT.RADIO);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(faultTypeLabel, STANDARD_LABEL_WIDTH_SM));
		// hack: fudge vertical alignment.
		data.top = new FlatFormAttachment(0, 2);
		this.builtinRadio.setLayoutData(data);
		this.builtinRadio.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (!FaultThrowNameSection.this.builtinRadio.getSelection()) return;
				doChildLayout();
				CompoundCommand compound = new CompoundCommand();
				Command cmd = new SetFaultNamespaceCommand(getInput(), BPELConstants.NAMESPACE);
				if (cmd.canExecute()) compound.add(cmd);
				Command cmd1 = new SetFaultNameCommand(getInput(), BPELConstants.standardFaults[0]);
				if(cmd1.canExecute()) compound.add(cmd1);
				FaultThrowNameSection.this.lastChangeContext = BUILTINRADIO_CONTEXT;
				getCommandFramework().execute(wrapInShowContextCommand(compound));
				updateUserDefFaultNameWidgets();
				updateFaultNameWidgets();
				updateFaultNamespaceWidgets();
				updateFaultVariableWidgets();
			}
			public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
		});

		this.userdefRadio = this.fWidgetFactory.createButton(composite, Messages.FaultThrowNameDetails_User_defined_15, SWT.RADIO);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.builtinRadio, IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(0, 2);
		this.userdefRadio.setLayoutData(data);
		this.userdefRadio.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (!FaultThrowNameSection.this.userdefRadio.getSelection()) return;
				doChildLayout();
				// Set the namespace to the process namespace.
				Command cmd = new SetFaultNamespaceCommand(getInput(), getProcess().getTargetNamespace());
				FaultThrowNameSection.this.lastChangeContext = USERDEFRADIO_CONTEXT;
				getCommandFramework().execute(wrapInShowContextCommand(cmd));
				updateUserDefFaultNameWidgets();
				updateFaultNameWidgets();
				updateFaultNamespaceWidgets();
				updateFaultVariableWidgets();
			}
			public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
		});

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.builtinRadio, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.builtinRadio, 0, SWT.CENTER);
		faultTypeLabel.setLayoutData(data);
	}

	protected void createChangeTrackers() {
		IOngoingChange change = new IOngoingChange() {
			public String getLabel() {
				return IBPELUIConstants.CMD_EDIT_FAULTNAME;
			}
			public Command createApplyCommand() {
				String name = FaultThrowNameSection.this.faultNameCombo.getText();
				FaultThrowNameSection.this.lastChangeContext = NAME_BUILTIN_CONTEXT;
				Command command;
				CompoundCommand c = new CompoundCommand();
				c.add(new SetFaultNameCommand(getInput(), "".equals(name)? null : name)); //$NON-NLS-1$
				Command c2 = new SetFaultNamespaceCommand(getInput(), BPELConstants.NAMESPACE);
				if (c2.canExecute())  c.add(c2);
				command = c;
				return wrapInShowContextCommand(command);
			}
			public void restoreOldState() {
				updateFaultNameWidgets();
			}
		};
		this.faultNameTracker = new ChangeTracker(this.faultNameCombo, change, getCommandFramework());

		change = new IOngoingChange() {
			public String getLabel() {
				return IBPELUIConstants.CMD_EDIT_FAULTNAME;
			}
			public Command createApplyCommand() {
				String s = FaultThrowNameSection.this.faultNamespaceText.getText();
				FaultThrowNameSection.this.lastChangeContext = NAMESPACE_CONTEXT;
				return wrapInShowContextCommand(new SetFaultNamespaceCommand(
							getInput(), "".equals(s)? null : s)); //$NON-NLS-1$
			}
			public void restoreOldState() {
				updateFaultNameWidgets();
			}
		};
		this.faultNamespaceTracker = new ChangeTracker(this.faultNamespaceText, change, getCommandFramework());

		change = new IOngoingChange() {
			public String getLabel() {
				return IBPELUIConstants.CMD_EDIT_FAULTNAME;
			}
			public Command createApplyCommand() {
				String s = FaultThrowNameSection.this.faultUserDefText.getText();
				FaultThrowNameSection.this.lastChangeContext = NAME_USERDEF_CONTEXT;
				return wrapInShowContextCommand(new SetFaultNameCommand(
							getInput(), "".equals(s)? null : s)); //$NON-NLS-1$
			}
			public void restoreOldState() {
				updateUserDefFaultNameWidgets();
			}
		};
		this.faultUserDefNameTracker = new ChangeTracker(this.faultUserDefText, change, getCommandFramework());
	}

	protected void createFaultNameWidgets(Composite parent) {
		FlatFormData data;

		Composite composite = this.faultNameComposite = createFlatFormComposite(parent);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.namespaceComposite, IDetailsAreaConstants.VSPACE);
		composite.setLayoutData(data);

		Label faultNameLabel = this.fWidgetFactory.createLabel(composite, Messages.FaultThrowNameDetails_Fault_Name__16);
		this.faultNameCombo = this.fWidgetFactory.createCCombo(composite);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(faultNameLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(50, -SHORT_BUTTON_WIDTH-IDetailsAreaConstants.HSPACE-IDetailsAreaConstants.CENTER_SPACE);
		data.top = new FlatFormAttachment(0, 0);

		this.faultNameCombo.setItems(BPELConstants.standardFaults);
		this.faultNameCombo.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.faultNameCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.faultNameCombo, 0, SWT.CENTER);
		faultNameLabel.setLayoutData(data);
	}

	protected void createNamespaceWidgets(Composite parent) {
		FlatFormData data;

		final Composite composite = this.namespaceComposite = createFlatFormComposite(parent);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.faultTypeComposite, IDetailsAreaConstants.VSPACE);
		composite.setLayoutData(data);

		//namespaceBrowseButton = wf.createButton(composite, "Browse...", SWT.PUSH);

		Label faultNamespaceLabel = this.fWidgetFactory.createLabel(composite, Messages.FaultThrowNameDetails_Namespace__21);
		this.faultNamespaceText = this.fWidgetFactory.createText(composite, ""); //$NON-NLS-1$

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(faultNamespaceLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(50, -SHORT_BUTTON_WIDTH-IDetailsAreaConstants.HSPACE-IDetailsAreaConstants.CENTER_SPACE);
		data.top = new FlatFormAttachment(0, +1);
		data.bottom = new FlatFormAttachment(100, -1);
		this.faultNamespaceText.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.faultNamespaceText, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.faultNamespaceText, 0, SWT.CENTER);
		faultNamespaceLabel.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.faultNamespaceText, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(100, 0);
		data.left = new FlatFormAttachment(100, -STANDARD_BUTTON_WIDTH);
		data.top = new FlatFormAttachment(this.faultNamespaceText, -1, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.faultNamespaceText, +1, SWT.BOTTOM);
		//namespaceBrowseButton.setLayoutData(data);

		//		namespaceBrowseButton.addSelectionListener(new SelectionListener() {
		//			public void widgetSelected(SelectionEvent e) {
		//				WorkbenchFileSelectionDialog dialog = new WorkbenchFileSelectionDialog(
		//					composite.getShell(), BPELUtil.lastWSDLFilePath, IBPELUIConstants.BROWSE_WSDL_DLG_TITLE,
		//						IBPELUIConstants.EXTENSION_DOT_WSDL);
		//				if (dialog.open() == WorkbenchFileSelectionDialog.OK) {
		//					IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(dialog.getLocationPath());
		//					// TODO: Load the target file and find the target namespace
		//				}
		//			}
		//			public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
		//		});
	}

	protected void createUserDefFaultNameWidgets(Composite parent) {
		FlatFormData data;

		Composite composite = this.faultUserDefNameComposite = createFlatFormComposite(parent);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.namespaceComposite, IDetailsAreaConstants.VSPACE);
		composite.setLayoutData(data);

		Label faultUserDefNameLabel = this.fWidgetFactory.createLabel(composite, Messages.FaultThrowNameDetails_Fault_Name__24);
		this.faultUserDefText = this.fWidgetFactory.createText(composite, ""); //$NON-NLS-1$

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(faultUserDefNameLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(50, -SHORT_BUTTON_WIDTH-IDetailsAreaConstants.HSPACE-IDetailsAreaConstants.CENTER_SPACE);
		data.top = new FlatFormAttachment(0, +1);
		this.faultUserDefText.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.faultUserDefText, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.faultUserDefText, 0, SWT.CENTER);
		faultUserDefNameLabel.setLayoutData(data);
	}

	protected void createVariableWidgets(Composite parent) {
		FlatFormData data;

		final Composite composite = this.faultVariableNameComposite = createFlatFormComposite(parent);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.faultNameComposite, IDetailsAreaConstants.VSPACE);
		composite.setLayoutData(data);

		Label variableLabel = this.fWidgetFactory.createLabel(composite, Messages.FaultThrowNameDetails_Fault_Variable__27);
		this.variableName = this.fWidgetFactory.createLabel(composite, "", SWT.NONE); //$NON-NLS-1$
		this.variableBrowseButton = this.fWidgetFactory.createButton(composite, Messages.FaultThrowNameSection_Browse_1, SWT.PUSH);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(variableLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.variableBrowseButton, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(0, 0);
		data.height = FigureUtilities.getTextExtents(this.variableBrowseButton.getText(), this.variableBrowseButton.getFont()).height + 4;
		this.variableName.setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment(this.variableName, 0, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.variableName, 2, SWT.BOTTOM);
		data.left = new FlatFormAttachment(50, -BPELUtil.calculateButtonWidth(this.variableBrowseButton, SHORT_BUTTON_WIDTH)-IDetailsAreaConstants.CENTER_SPACE);
		data.right = new FlatFormAttachment(50, -IDetailsAreaConstants.CENTER_SPACE);
		this.variableBrowseButton.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.variableName, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.variableName, 0, SWT.CENTER);
		variableLabel.setLayoutData(data);

		this.variableBrowseButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = composite.getShell();
				EObject model = getInput();
				VariableSelectorDialog dialog = new VariableSelectorDialog(shell, model, ModelHelper.getVariableType(model, ModelHelper.OUTGOING));
				dialog.setTitle(Messages.FaultThrowNameSection_Select_Fault_Variable_2);
				if (dialog.open() == Window.OK) {
					Variable variable = dialog.getVariable();
					Command command = new SetVariableCommand(FaultThrowNameSection.this.fModelObject, variable, ModelHelper.OUTGOING);
					FaultThrowNameSection.this.lastChangeContext = VARIABLE_CONTEXT;
					getCommandFramework().execute(wrapInShowContextCommand(command));
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
		});
	}

	@Override
	protected void createClient(Composite parent) {
		Composite composite = this.parentComposite = createFlatFormComposite(parent);
		createFaultTypeWidgets(composite);
		createUserDefFaultNameWidgets(composite);
		createNamespaceWidgets(composite);
		createFaultNameWidgets(composite);
		createVariableWidgets(composite);
		createChangeTrackers();

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
					this.parentComposite, IHelpContextIds.PROPERTY_PAGE_FAULT);
	}

	protected void updateFaultNameWidgets() {
		Assert.isNotNull(getInput());
		this.faultNameTracker.stopTracking();
		try {
			String s = ModelHelper.getFaultName(getInput());
			if (s == null)  s = ""; //$NON-NLS-1$
			if (!s.equals(this.faultNameCombo.getText()))  this.faultNameCombo.setText(s);
		} finally {
			this.faultNameTracker.startTracking();
		}
	}

	protected void updateFaultNamespaceWidgets() {
		Assert.isNotNull(getInput());
		if (this.isFaultTypeEnabled) {
			this.faultNamespaceTracker.stopTracking();
			try {
				String s = ModelHelper.getFaultNamespace(getInput());
				if (s == null) {
					this.faultNamespaceText.setText("");//$NON-NLS-1$
				} else {
					if (!s.equals(this.faultNamespaceText.getText())) {
						this.faultNamespaceText.setText(NamespaceUtils.convertUriToNamespace(s));
					}
				}
			} finally {
				this.faultNamespaceTracker.startTracking();
			}
		}
	}

	protected void updateUserDefFaultNameWidgets() {
		Assert.isNotNull(getInput());
		this.faultUserDefNameTracker.stopTracking();
		try {
			String s = ModelHelper.getFaultName(getInput());
			if (s == null)  s = ""; //$NON-NLS-1$
			if (!s.equals(this.faultUserDefText.getText()))  this.faultUserDefText.setText(s);
		} finally {
			this.faultUserDefNameTracker.startTracking();
		}
	}

	protected void updateFaultTypeWidgets() {
		Assert.isNotNull(getInput());
		String faultNamespace = ModelHelper.getFaultNamespace(getInput());

		boolean isBuiltin = BPELConstants.NAMESPACE.equals(faultNamespace);
		if (faultNamespace == null) {
			if (ModelHelper.getFaultName(getInput()) == null)  isBuiltin = true;
		}

		this.builtinRadio.setSelection(isBuiltin);
		this.userdefRadio.setSelection(!isBuiltin);
		doChildLayout();
	}

	protected void updateFaultVariableWidgets() {
		Assert.isNotNull(getInput());
		Variable v = ((Throw)getInput()).getFaultVariable();
		String s = (v==null)? null : v.getName();
		if (s == null)  s = ""; //$NON-NLS-1$
		if (!s.equals(this.variableName.getText())) this.variableName.setText(s);
	}

	protected void updateVariableWidgets() {
		Variable variable = ModelHelper.getVariable(getInput(), ModelHelper.OUTGOING);
		if (variable == null) {
			this.variableName.setText(Messages.FaultThrowNameSection_None_3);
			this.variableName.setEnabled(false);
		} else {
			ILabeledElement labeledElement = BPELUtil.adapt(variable, ILabeledElement.class);
			this.variableName.setText(labeledElement.getLabel(variable));
			this.variableName.setEnabled(true);
		}
	}

	@Override
	public void refresh() {
		super.refresh();
		updateFaultTypeWidgets();
		updateFaultNamespaceWidgets();
		updateFaultNameWidgets();
		updateUserDefFaultNameWidgets();
		updateFaultVariableWidgets();
		updateVariableWidgets();
	}

	@Override
	public Object getUserContext() {
		return Integer.valueOf( this.lastChangeContext );
	}
	@Override
	public void restoreUserContext(Object userContext) {
		int i = ((Integer)userContext).intValue();
		switch (i) {
		case NAME_BUILTIN_CONTEXT: this.faultNameCombo.setFocus(); return;
		case NAMESPACE_CONTEXT: this.faultNamespaceText.setFocus(); return;
		case BUILTINRADIO_CONTEXT: this.builtinRadio.setFocus(); return;
		case USERDEFRADIO_CONTEXT: this.userdefRadio.setFocus(); return;
		case NAME_USERDEF_CONTEXT: this.faultUserDefText.setFocus(); return;
		case FAULT_VARIABLE_CONTEXT: this.variableNameText.setFocus(); return;
		}
		throw new IllegalStateException();
	}
}
