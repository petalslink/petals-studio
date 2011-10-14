/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.jbi.editor.su;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.jbi.editor.AbstractJBIFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.SupportsUtil;
import com.ebmwebsourcing.petals.services.jbi.editor.su.wizard.NewServiceWizard;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SUEditorPage extends AbstractJBIFormPage {

	private Object selectedTreeItem; // Can be PROVIDE or CONSUME
	private AbstractEndpoint selectedEndpoint;
	private EList<? extends AbstractEndpoint> containmentList;
	
	private JbiEditorDetailsContribution componentContributions;
	private FormToolkit toolkit;
	private Composite mainDetails;
	private Composite advancedDetails;
	
	/**
	 * Constructor.
	 * 
	 * @param editor
	 * @param id
	 * @param title
	 */
	public SUEditorPage( JbiFormEditor editor, String id, String title ) {
		super( editor, id, title );
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		CompoundCommand initializeCommand = new CompoundCommand();
		for (ComponentSupportExtensionDesc component : SupportsUtil.getInstance().getComponents()) {
			for (ComponentVersionSupportExtensionDesc version : component.getVersionSupports()) {
				EPackage extensionPackage = EPackageRegistryImpl.INSTANCE.getEPackage(version.getNamespace());
				for (Provides provide : getEditor().getJbiModel().getServices().getProvides()) {
					initializeCommand.append(new InitializeModelExtensionCommand(extensionPackage, provide));
				}
				for (Consumes consume : getEditor().getJbiModel().getServices().getConsumes()) {
					initializeCommand.append(new InitializeModelExtensionCommand(extensionPackage, consume));
				}
			}
		}
		getEditor().getEditingDomain().getCommandStack().execute(initializeCommand);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage
	 * #createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent( IManagedForm managedForm ) {
		toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("Service Unit");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(managedForm.getForm().getBody(), SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().adapt(sashForm);
		managedForm.getToolkit().paintBordersFor(sashForm);
		
		Section servicesSection = managedForm.getToolkit().createSection(sashForm, Section.TWISTIE | Section.TITLE_BAR | Section.EXPANDED);
		managedForm.getToolkit().paintBordersFor(servicesSection);
		servicesSection.setText("Services");
		servicesSection.setExpanded(true);
		
		Composite servicesComposite = managedForm.getToolkit().createComposite(servicesSection, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(servicesComposite);
		servicesSection.setClient(servicesComposite);
		servicesComposite.setLayout(new GridLayout(1, false));
		
		Tree tree = managedForm.getToolkit().createTree(servicesComposite, SWT.NONE);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().paintBordersFor(tree);
		final TreeViewer servicesViewer = new TreeViewer(tree);
		servicesViewer.setContentProvider(new ServicesContentProvider());
		servicesViewer.setLabelProvider(new EMFPCStyledLabelProvider(tree));
		
		Composite servicesButtonComposite = managedForm.getToolkit().createComposite(servicesComposite, SWT.NONE);
		servicesButtonComposite.setBounds(0, 0, 64, 64);
		managedForm.getToolkit().paintBordersFor(servicesButtonComposite);
		servicesButtonComposite.setLayout(new GridLayout(2, false));
		
		Button newButton = managedForm.getToolkit().createButton(servicesButtonComposite, "New...", SWT.NONE);
		newButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		newButton.setBounds(0, 0, 92, 29);
		
		final Button upButton = managedForm.getToolkit().createButton(servicesButtonComposite, "", SWT.UP | SWT.ARROW);
		upButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		upButton.setBounds(0, 0, 92, 29);
		
		final Button removeButton = managedForm.getToolkit().createButton(servicesButtonComposite, "Remove", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		removeButton.setBounds(0, 0, 92, 29);
		
		final Button downButton = managedForm.getToolkit().createButton(servicesButtonComposite, "", SWT.DOWN | SWT.ARROW);
		downButton.setBounds(0, 0, 92, 29);
		
		Section sctnNewSection = managedForm.getToolkit().createSection(sashForm, Section.TWISTIE | Section.TITLE_BAR | Section.EXPANDED);
		managedForm.getToolkit().paintBordersFor(sctnNewSection);
		sctnNewSection.setText("New Section");
		sctnNewSection.setExpanded(true);
		
		CTabFolder tabFolder = new CTabFolder(sctnNewSection, SWT.BORDER);
		managedForm.getToolkit().paintBordersFor(tabFolder);
		sctnNewSection.setClient(tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmGeneral = new CTabItem(tabFolder, SWT.NONE);
		tbtmGeneral.setText("Main");
		
		mainDetails = managedForm.getToolkit().createComposite(tabFolder, SWT.NONE);
		tbtmGeneral.setControl(mainDetails);
		managedForm.getToolkit().paintBordersFor(mainDetails);
		mainDetails.setLayout(new GridLayout(2, false));
		
		CTabItem tbtmAdvanced = new CTabItem(tabFolder, SWT.NONE);
		tbtmAdvanced.setText("Advanced");
		
		advancedDetails = managedForm.getToolkit().createComposite(tabFolder, SWT.NONE);
		tbtmAdvanced.setControl(advancedDetails);
		managedForm.getToolkit().paintBordersFor(advancedDetails);
		advancedDetails.setLayout(new GridLayout(2, false));
		
		CTabItem tbtmSource = new CTabItem(tabFolder, SWT.NONE);
		tbtmSource.setText("Source");
		sashForm.setWeights(new int[] {1, 1});
		
		tabFolder.setSelection(tbtmGeneral);
		
		// dynamics
		servicesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedTreeItem = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (selectedTreeItem instanceof AbstractEndpoint) {
					selectedEndpoint = (AbstractEndpoint)selectedTreeItem;
					if (selectedEndpoint instanceof Provides) {
						containmentList = getEditor().getJbiModel().getServices().getProvides();
					} else {
						containmentList = getEditor().getJbiModel().getServices().getConsumes();
					}
					upButton.setEnabled(containmentList.indexOf(selectedEndpoint) > 0);
					downButton.setEnabled(containmentList.indexOf(selectedEndpoint) != containmentList.size() - 1);
				} else {
					selectedEndpoint = null;
					upButton.setEnabled(false);
					downButton.setEnabled(false);
				}
				removeButton.setEnabled(selectedEndpoint != null);
			}
		});
		servicesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				refreshDetails();
			}
		});
		
		upButton.addSelectionListener(new SelectionAdapter() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
				MoveCommand moveCommand = new MoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint, containmentList.indexOf(selectedEndpoint) - 1);
				getEditor().getEditingDomain().getCommandStack().execute(moveCommand);
			}
		});
		downButton.addSelectionListener(new SelectionAdapter() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
				MoveCommand moveCommand = new MoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint, containmentList.indexOf(selectedEndpoint) + 1);
				getEditor().getEditingDomain().getCommandStack().execute(moveCommand);
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (MessageDialog.openConfirm(getSite().getShell(), Messages.confimeRemoveEndpointTitle, Messages.confimeRemoveEndpointMessage)) {
					RemoveCommand deleteCommand = new RemoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint);
					getEditor().getEditingDomain().getCommandStack().execute(deleteCommand);
					servicesViewer.refresh();
				}
			}
		});
		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWizard createEndpointWizard = new NewServiceWizard(getEditor().getJbiModel(), getEditor().getEditingDomain());
				if (new WizardDialog(getSite().getShell(), createEndpointWizard).open() == Dialog.OK) {
					servicesViewer.refresh();
				}
			}
		});
		
		// init state
		servicesViewer.setInput(getEditor().getJbiModel());
		servicesViewer.expandAll();
		removeButton.setEnabled(false);
		upButton.setEnabled(false);
		downButton.setEnabled(false);
	}
	
	private void re_fillMainDetailsContainer(FormToolkit toolkit, Composite generalDetails) {
		for (Control control : generalDetails.getChildren()) {
			control.dispose();
		}
		
		if (componentContributions != null) {
			componentContributions.addMainSUContent(selectedEndpoint, toolkit, generalDetails, getEditor());
		} else if (selectedTreeItem == ServicesContentProvider.CONSUME) {
			toolkit.createLabel(generalDetails, Messages.consumeDescription, SWT.WRAP).setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		} else if (selectedTreeItem == ServicesContentProvider.PROVIDE) {
			toolkit.createLabel(generalDetails, Messages.provideDescription, SWT.WRAP).setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		}
		
		generalDetails.layout(true);
	}
	
	private void re_fillAdvancedDetailsContainer(FormToolkit toolkit, Composite advancedDetails) {
		for (Control control : advancedDetails.getChildren()) {
			control.dispose();
		}
		
		if (componentContributions != null) {
			componentContributions.addAdvancedSUContent(selectedEndpoint, toolkit, advancedDetails, getEditor());
		}
		
		advancedDetails.layout(true);
	}
	
	private void refreshDetails() {
		if (selectedEndpoint != null) {
			ComponentVersionSupportExtensionDesc componentExtensionDesc = SupportsUtil.getInstance().getComponentExtensionDesc(selectedEndpoint);
			if (componentExtensionDesc != null) {
				this.componentContributions = componentExtensionDesc.createNewExtensionSupport().createJbiEditorContribution(selectedEndpoint);
		    }
		} else {
			this.componentContributions = null;
		}
		
		re_fillMainDetailsContainer(toolkit, mainDetails);
		re_fillAdvancedDetailsContainer(toolkit, advancedDetails);
		
	}
	
}
