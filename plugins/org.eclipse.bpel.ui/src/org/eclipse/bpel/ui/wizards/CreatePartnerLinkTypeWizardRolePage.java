/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.wizards;

import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.PortTypeTreeContentProvider;
import org.eclipse.bpel.ui.details.tree.PortTypeTreeNode;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.PortType;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Aug 14, 2006
 * 
 */
public class CreatePartnerLinkTypeWizardRolePage extends WizardPage {

	static final int SIZING_TEXT_FIELD_WIDTH = 250;

	Text roleName;
	Tree portTypeTree;
	TreeViewer portTypeViewer;
	PortType portType;

	Definition wsdlDefinition;

	private CreatePartnerLinkTypeWizardRolePage fOtherRolePage;

	boolean fOptional = false;


	/**
	 * @param pageName
	 */
	protected CreatePartnerLinkTypeWizardRolePage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());

		initializeDialogUnits(parent);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createRoleName(composite);

		setPageComplete(validatePage());

		// no errors on opening up the wizard
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);

		// figure out the what needs to go
	}

	/**
	 * @param parent
	 * @return
	 */
	Composite createRoleName ( Composite parent ) {

		Composite fields = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		fields.setLayout(layout);
		fields.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Role Name
		Label label = new Label(fields, SWT.NONE);
		label.setText(Messages.CreatePartnerLinkTypeWizardRolePage_0);
		label.setFont(parent.getFont());

		// new project name entry field
		this.roleName = new Text(fields, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		this.roleName.setLayoutData(data);
		this.roleName.setFont(parent.getFont());

		this.roleName.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				setPageComplete(validatePage());
			}
		});
		// Role Name

		// portType
		label = new Label(fields, SWT.NONE);
		label.setText(Messages.CreatePartnerLinkTypeWizardRolePage_1);
		label.setFont(parent.getFont());

		// new project name entry field
		this.portTypeTree = new Tree(fields, SWT.BORDER);
		this.portTypeViewer = new TreeViewer( this.portTypeTree );
		this.portTypeViewer.setContentProvider( new PortTypeTreeContentProvider (true));
		this.portTypeViewer.setLabelProvider( new ModelTreeLabelProvider() );
		this.portTypeViewer.setInput( this.wsdlDefinition );

		data = new GridData(GridData.FILL_BOTH);
		this.portTypeTree.setLayoutData(data);
		this.portTypeTree.setFont(parent.getFont());

		this.portTypeViewer.addSelectionChangedListener( new ISelectionChangedListener () {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection ssel = (IStructuredSelection) event.getSelection();
				Object obj = ssel.getFirstElement();
				if (obj != null && obj instanceof PortTypeTreeNode) {
					PortTypeTreeNode pttn = (PortTypeTreeNode) obj;
					setPortType( (PortType) pttn.getModelObject() );
					setPageComplete(validatePage());
				} else {
					setPortType(null);
					setPageComplete(validatePage());
				}
			}
		});
		// portType


		return fields;
	}

	boolean validatePage () {
		String roleNCName = this.roleName.getText();

		if (this.fOptional) {
			if (this.portType == null && roleNCName.length() == 0) {
				setMessage(Messages.CreatePartnerLinkTypeWizardRolePage_2,INFORMATION);
				return true;
			}
		}

		if (this.portType == null) {
			setMessage(Messages.CreatePartnerLinkTypeWizardRolePage_3,ERROR);
			return false;
		}

		IInputValidator validator = BPELUtil.getNCNameValidator();
		String msg = validator.isValid( roleNCName );
		if (msg != null) {
			setMessage(msg,ERROR);
			return false;
		}

		if (this.fOtherRolePage != null) {
			if (roleNCName.equals( this.fOtherRolePage.getRoleName() )) {
				setMessage(Messages.CreatePartnerLinkTypeWizardRolePage_4,ERROR);
				return false;
			}
		}

		setMessage(null,NONE);
		return true;
	}

	public void setDefinition ( Definition defn ) {
		this.wsdlDefinition = defn;
	}


	/**
	 * @param portType2
	 */

	public void setPortType(PortType pt) {
		this.portType = pt;
	}

	public PortType getPortType () {
		return this.portType;
	}


	public String getRoleName () {
		return this.roleName.getText();
	}


	/**
	 * @param rolePage2
	 */
	public void setOtherRolePage(CreatePartnerLinkTypeWizardRolePage otherRole) {
		this.fOtherRolePage = otherRole;
	}

	/**
	 * @param b
	 */
	public void setOptional(boolean b) {
		this.fOptional = b;
	}


	/**
	 * @return
	 */
	public boolean isSpecified() {
		if (this.fOptional) {
			if (this.portType == null) {
				return false;
			}
		}
		return isPageComplete();
	}

}
