package org.eclipse.bpel.ui.dialogs;

import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.PartnerLinkTypeTreeContentProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class PartnerLinkRoleSelectorDialog extends StatusDialog {

	private int fSelectedRole;
	private final EList<Role> fRoles;
	private Tree fTree;
	private TreeViewer fTreeViewer;
	private PartnerLinkTypeTreeContentProvider treeContentProvider;
	private final PartnerLinkType fPartnerLinkType;

	public PartnerLinkRoleSelectorDialog(Shell shell, EList<Role> list,
				PartnerLinkType plinkType) {
		super(shell);
		this.fRoles = list;
		this.fPartnerLinkType = plinkType;
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout());
		group.setText("Roles");

		createRadioButton(group, this.fRoles.get(0).getName(), 0, true);
		createRadioButton(group, this.fRoles.get(1).getName(), 1, false);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 400;
		group.setLayoutData(gridData);

		createLowerView(composite);
		this.fTreeViewer.setInput(this.fPartnerLinkType);

		return composite;

	}

	protected Button createRadioButton(Composite parent, String label, int id,
				boolean checked) {

		Button button = new Button(parent, SWT.RADIO);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(Integer.valueOf(id));
		button.setSelection(checked);

		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				Button b = (Button) event.widget;
				PartnerLinkRoleSelectorDialog.this.fSelectedRole = ((Integer) b.getData()).intValue();
			}
		});

		return button;

	}

	private Object createLowerView(Composite parent) {
		this.treeContentProvider = new PartnerLinkTypeTreeContentProvider(true);
		this.fTree = new Tree(parent, SWT.BORDER);
		this.fTreeViewer = new TreeViewer(this.fTree);
		this.fTreeViewer.setContentProvider(this.treeContentProvider);
		this.fTreeViewer.setLabelProvider(new ModelTreeLabelProvider());
		this.fTreeViewer.setInput(null);
		this.fTreeViewer.setAutoExpandLevel(5);
		GridData data = new GridData();
		data.grabExcessVerticalSpace = true;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.minimumHeight = 200;
		this.fTree.setLayoutData(data);
		return this.fTree;
	}

	public int getSelectedRole() {
		return this.fSelectedRole;
	}

}
