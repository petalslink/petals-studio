package com.ebmwebsourcing.petals.services.jbi.wizard;

import java.util.ArrayList;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.wizard.ComponentSupportTreeContentProvider.SUType;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;

public class NewServiceWizardPage extends WizardPage implements IWizardPage {
	

	private AbstractEndpoint service;
	private ComponentVersionSupportExtensionDesc component;
	private String serviceName;
	private SUType suType;
	
	protected NewServiceWizardPage(SUType suType) {
		super("NewServiceWizardPage");
		this.suType = suType;
		if (suType == SUType.PROVIDES) {
			service = JbiFactory.eINSTANCE.createProvides();
			setTitle(Messages.createProvidesSU);
			setDescription(Messages.createProvidesSUDescription);
			setImageDescriptor(PetalsServicesPlugin.getImageDescriptor("icons/others/fleche.png"));
		} else {
			service = JbiFactory.eINSTANCE.createConsumes();
			setTitle(Messages.createConsumesSU);
			setDescription(Messages.createConsumesSUDescription);
			setImageDescriptor(PetalsServicesPlugin.getImageDescriptor("icons/others/fleche.png"));
		}
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
		final Text serviceNameText = new Text(composite, SWT.BORDER);
		serviceNameText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		
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
		filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object item = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (item instanceof ComponentSupportExtensionDesc) {
					versionViewer.setInput(((ComponentSupportExtensionDesc) item).getVersionSupports());
					versionViewer.refresh();
					versionViewer.setSelection(new StructuredSelection(versionViewer.getElementAt(0)));
				} else {
					versionViewer.setInput(new ArrayList<ComponentSupportExtensionDesc>());
					versionViewer.refresh();
					versionViewer.setSelection(new StructuredSelection());
				}
				setPageComplete(isPageComplete());
			}
		});
		versionViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				component = (ComponentVersionSupportExtensionDesc) ((IStructuredSelection)event.getSelection()).getFirstElement();
				setPageComplete(isPageComplete());
			}
		});
		serviceNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				serviceName = serviceNameText.getText();
				setPageComplete(NewServiceWizardPage.this.isPageComplete());
			}
		});

		filteredTree.getViewer().setInput(suType);
		
		filteredTree.getViewer().refresh();
		filteredTree.getViewer().expandAll();
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

	@Override
	public boolean isPageComplete() {
		return serviceName != null && !serviceName.isEmpty() && component != null;
	}

}
