package com.ebmwebsourcing.petals.services.jbi.wizard;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.SupportsUtil;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;

public class NewServiceWizardPage extends WizardPage implements IWizardPage {
	
	private AbstractEndpoint service;
	private ComponentVersionSupportExtensionDesc component;
	private String serviceName;
	
	public NewServiceWizardPage() {
		super("NewServiceWizardPage");
	}

	@Override
	public void createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		setControl(control);
		
		control.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(control, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		Label serviceNameLabel = new Label(composite, SWT.NONE);
		serviceNameLabel.setText(Messages.serviceName);
		final Text serviceNameText = new Text(composite, SWT.NONE);
		serviceNameText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		
		Label lblServiceMode = new Label(composite, SWT.NONE);
		lblServiceMode.setText(Messages.serviceMode);
		
		final Button provideButton = new Button(composite, SWT.RADIO);
		provideButton.setText(Messages.provideDescription/*"a PROVIDE imports an external system or application as a service into the bus"*/);
		new Label(composite, SWT.NONE);
		
		final Button consumeButton = new Button(composite, SWT.RADIO);
		consumeButton.setText(Messages.consumeDescription/*"a CONSUME exposes a service from inside the bus to another system or application"*/);
		
		Label lblAvailableServices = new Label(control, SWT.NONE);
		lblAvailableServices.setText(Messages.selectComponent);
		
		final FilteredTree filteredTree = new FilteredTree(control, SWT.BORDER, new PatternFilter(), true);
		GridData treeGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		treeGridData.heightHint = 300;
		filteredTree.setLayoutData(treeGridData);
		filteredTree.getViewer().setContentProvider(new ComponentSupportTreeContentProvider());
		filteredTree.getViewer().setLabelProvider(new ComponentSupportLabelProvider());
		
		Composite versionComposite = new Composite(control, SWT.NONE);
		versionComposite.setLayout(new GridLayout(2, false));
		Label versionLabel = new Label(versionComposite, SWT.NONE);
		versionLabel.setText(Messages.version);
		versionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		
		final ComboViewer versionViewer = new ComboViewer(versionComposite, SWT.READ_ONLY);
		versionViewer.setContentProvider(new ArrayContentProvider());
		versionViewer.setLabelProvider(new ComponentVersionSupportLabelProvider());
		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gd.widthHint = 100;
		versionViewer.getControl().setLayoutData(gd);
		
		setPageComplete(false);
		consumeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filteredTree.getViewer().setInput(SupportsUtil.getInstance().getAllConsumes());
				filteredTree.getViewer().refresh();
				service = JbiFactory.eINSTANCE.createConsumes();
			}
		});
		provideButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filteredTree.getViewer().setInput(SupportsUtil.getInstance().getAllProvides());
				filteredTree.getViewer().refresh();
				service = JbiFactory.eINSTANCE.createProvides();
			}
		});
		filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object item = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (item instanceof ComponentSupportExtensionDesc) {
					versionViewer.setInput(((ComponentSupportExtensionDesc) item).getVersionSupports());
				} else {
					// TODO clean
				}
				versionViewer.refresh();
				versionViewer.setSelection(new StructuredSelection(versionViewer.getElementAt(0)));
			}
		});
		versionViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				component = (ComponentVersionSupportExtensionDesc) ((IStructuredSelection)event.getSelection()).getFirstElement();
			}
		});
		serviceNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				serviceName = serviceNameText.getText();
				setPageComplete(serviceName != null && !serviceName.isEmpty());
			}
		});
		
	}

	public AbstractEndpoint getService() {
		return this.service;
	}

	public ComponentVersionSupportExtensionDesc getSelectedComponentVersion() {
		return this.component;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}

}
