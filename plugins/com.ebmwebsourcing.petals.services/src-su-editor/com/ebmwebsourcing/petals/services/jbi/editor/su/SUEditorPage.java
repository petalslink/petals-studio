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
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsImages;
import com.ebmwebsourcing.petals.services.jbi.editor.AbstractJBIFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.SupportsUtil;
import com.ebmwebsourcing.petals.services.jbi.editor.su.wizard.NewServiceWizard;
import com.ebmwebsourcing.petals.services.jbi.wizard.ComponentSupportTreeContentProvider.SUType;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SUEditorPage extends AbstractJBIFormPage {

	private final class EListRemoveSelectionListener extends SelectionAdapter {
		private final Viewer servicesViewer;

		private EListRemoveSelectionListener(Viewer servicesViewer) {
			this.servicesViewer = servicesViewer;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (MessageDialog.openConfirm(getSite().getShell(), Messages.confimeRemoveEndpointTitle, Messages.confimeRemoveEndpointMessage)) {
				RemoveCommand deleteCommand = new RemoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint);
				getEditor().getEditingDomain().getCommandStack().execute(deleteCommand);
				servicesViewer.refresh();
			}
		}
	}

	private final class EListDownSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MoveCommand moveCommand = new MoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint, containmentList.indexOf(selectedEndpoint) + 1);
			getEditor().getEditingDomain().getCommandStack().execute(moveCommand);
		}
	}

	private final class EListUpSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MoveCommand moveCommand = new MoveCommand(getEditor().getEditingDomain(), containmentList, selectedEndpoint, containmentList.indexOf(selectedEndpoint) - 1);
			getEditor().getEditingDomain().getCommandStack().execute(moveCommand);
		}
	}

	private AbstractEndpoint selectedEndpoint;
	private EList<? extends AbstractEndpoint> containmentList;
	
	private JbiEditorDetailsContribution componentContributions;
	private FormToolkit toolkit;
	private Composite mainDetails;
	private Composite advancedDetails;
	private TableViewer providesViewer;
	private TableViewer consumesViewer;
	
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
		for (String ePackageNS : SupportsUtil.getInstance().getJBIExtensionEPackage()) {
			EPackage extensionPackage = EPackageRegistryImpl.INSTANCE.getEPackage(ePackageNS);
			for (Provides provide : getEditor().getJbiModel().getServices().getProvides()) {
				initializeCommand.append(new InitializeModelExtensionCommand(extensionPackage, provide));
			}
			for (Consumes consume : getEditor().getJbiModel().getServices().getConsumes()) {
				initializeCommand.append(new InitializeModelExtensionCommand(extensionPackage, consume));
			}
		}
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
		
		
		//
		// MASTER
		//
		
		
		Section servicesSection = managedForm.getToolkit().createSection(sashForm, Section.TITLE_BAR | Section.EXPANDED);
		managedForm.getToolkit().paintBordersFor(servicesSection);
		servicesSection.setText("Services");
		
		Composite servicesComposite = managedForm.getToolkit().createComposite(servicesSection, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(servicesComposite);
		servicesSection.setClient(servicesComposite);
		servicesComposite.setLayout(new GridLayout(1, false));

		// PROVIDES
		Form providesForm = managedForm.getToolkit().createForm(servicesComposite);
		providesForm.setLayoutData(new GridData(GridData.FILL_BOTH));
		providesForm.setText(Messages.provides);
		providesForm.getBody().setLayout(new GridLayout(2, false));
		providesViewer = new TableViewer(providesForm.getBody());
		providesViewer.setLabelProvider(new EMFPCStyledLabelProvider(providesViewer.getControl()));
		providesViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		providesViewer.setContentProvider(new ArrayContentProvider());
		
		Composite providesButtons = managedForm.getToolkit().createComposite(providesForm.getBody());
		providesButtons.setLayout(new GridLayout(1, false));
		Button newProvidesButton = managedForm.getToolkit().createButton(providesButtons, "New...", SWT.DEFAULT);
		newProvidesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		newProvidesButton.setImage(PetalsImages.getAdd());
		final Button removeProvidesButton = managedForm.getToolkit().createButton(providesButtons, "Remove", SWT.DEFAULT);
		removeProvidesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		removeProvidesButton.setImage(PetalsImages.getDelete());
		final Button upProvidesButton = managedForm.getToolkit().createButton(providesButtons, "", SWT.UP | SWT.ARROW);
		upProvidesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		final Button downProvidesButton = managedForm.getToolkit().createButton(providesButtons, "", SWT.DOWN | SWT.ARROW);
		downProvidesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		// dynamics
		getEditor().getDataBindingContext().bindValue(
				ViewersObservables.observeInput(providesViewer),
				EMFEditObservables.observeValue(getEditor().getEditingDomain(), getEditor().getJbiModel().getServices(), JbiPackage.Literals.SERVICES__PROVIDES));
		
		upProvidesButton.addSelectionListener(new EListUpSelectionListener());
		downProvidesButton.addSelectionListener(new EListDownSelectionListener());
		removeProvidesButton.addSelectionListener(new EListRemoveSelectionListener(providesViewer));
		newProvidesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWizard createEndpointWizard = new NewServiceWizard(getEditor().getJbiModel(), getEditor().getEditingDomain(), SUType.PROVIDES);
				if (new WizardDialog(getSite().getShell(), createEndpointWizard).open() == Dialog.OK) {
					providesViewer.refresh();
				}
			}
		});
		
		// CONSUMES
		Form consumesForm = managedForm.getToolkit().createForm(servicesComposite);
		consumesForm.setLayoutData(new GridData(GridData.FILL_BOTH));
		consumesForm.setText(Messages.consumes);
		consumesForm.getBody().setLayout(new GridLayout(2, false));
		consumesViewer = new TableViewer(consumesForm.getBody());
		consumesViewer.setLabelProvider(new EMFPCStyledLabelProvider(consumesViewer.getControl()));
		consumesViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		consumesViewer.setContentProvider(new ArrayContentProvider());
		
		Composite consumesButtons = managedForm.getToolkit().createComposite(consumesForm.getBody());
		consumesButtons.setLayout(new GridLayout(1, false));
		Button newConsumesButton = managedForm.getToolkit().createButton(consumesButtons, "New...", SWT.DEFAULT);
		newConsumesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		newConsumesButton.setImage(PetalsImages.getAdd());
		final Button removeConsumesButton = managedForm.getToolkit().createButton(consumesButtons, "Remove", SWT.DEFAULT);
		removeConsumesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		removeConsumesButton.setImage(PetalsImages.getDelete());
		final Button upConsumesButton = managedForm.getToolkit().createButton(consumesButtons, "", SWT.UP | SWT.ARROW);
		upConsumesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		final Button downConsumesButton = managedForm.getToolkit().createButton(consumesButtons, "", SWT.DOWN | SWT.ARROW);
		downConsumesButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		// dynamics
		getEditor().getDataBindingContext().bindValue(
				ViewersObservables.observeInput(consumesViewer),
				EMFEditObservables.observeValue(getEditor().getEditingDomain(), getEditor().getJbiModel().getServices(), JbiPackage.Literals.SERVICES__CONSUMES));
		upConsumesButton.addSelectionListener(new EListUpSelectionListener());
		downConsumesButton.addSelectionListener(new EListDownSelectionListener());
		removeConsumesButton.addSelectionListener(new EListRemoveSelectionListener(consumesViewer));
		newConsumesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWizard createEndpointWizard = new NewServiceWizard(getEditor().getJbiModel(), getEditor().getEditingDomain(), SUType.CONSUMES);
				if (new WizardDialog(getSite().getShell(), createEndpointWizard).open() == Dialog.OK) {
					consumesViewer.refresh();
				}
			}
		});
		
		
		providesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = ((IStructuredSelection)event.getSelection());
				if (selection.isEmpty()) {
					selectedEndpoint = null;
					upProvidesButton.setEnabled(false);
					downProvidesButton.setEnabled(false);
				} else {
					consumesViewer.setSelection(new StructuredSelection());
					selectedEndpoint = (Provides)selection.getFirstElement();
					containmentList = getEditor().getJbiModel().getServices().getProvides();
					upProvidesButton.setEnabled(containmentList.indexOf(selectedEndpoint) > 0);
					downProvidesButton.setEnabled(containmentList.indexOf(selectedEndpoint) != containmentList.size() - 1);
					refreshDetails();
				}
				removeProvidesButton.setEnabled(selectedEndpoint != null);
			}
		});
		consumesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = ((IStructuredSelection)event.getSelection());
				if (selection.isEmpty()) {
					selectedEndpoint = null;
					upConsumesButton.setEnabled(false);
					downConsumesButton.setEnabled(false);
				} else {
					providesViewer.setSelection(new StructuredSelection());
					selectedEndpoint = (Consumes)selection.getFirstElement();
					containmentList = getEditor().getJbiModel().getServices().getConsumes();
					upConsumesButton.setEnabled(containmentList.indexOf(selectedEndpoint) > 0);
					downConsumesButton.setEnabled(containmentList.indexOf(selectedEndpoint) != containmentList.size() - 1);
					refreshDetails();
				}
				removeConsumesButton.setEnabled(selectedEndpoint != null);
			}
		});
		
		
		providesViewer.setSelection(new StructuredSelection());
		consumesViewer.setSelection(new StructuredSelection());
		
		
		//
		// DETAILS
		//
		
		
		CTabFolder tabFolder = new CTabFolder(sashForm, SWT.BORDER);
		managedForm.getToolkit().adapt(tabFolder);
		managedForm.getToolkit().paintBordersFor(tabFolder);
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
		sashForm.setWeights(new int[] {1, 2});
		
		tabFolder.setSelection(tbtmGeneral);
		
	}
	
	private void re_fillMainDetailsContainer(FormToolkit toolkit, Composite generalDetails) {
		for (Control control : generalDetails.getChildren()) {
			control.dispose();
		}
		
		if (componentContributions != null) {
			componentContributions.addMainSUContent(selectedEndpoint, toolkit, generalDetails, getEditor());
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
				EditorContributionSupport support = componentExtensionDesc.createNewExtensionSupport();
				if (support != null) {
					this.componentContributions = componentExtensionDesc.createNewExtensionSupport().createJbiEditorContribution(selectedEndpoint);
				}
		    }
		} else {
			this.componentContributions = null;
		}
		
		re_fillMainDetailsContainer(toolkit, mainDetails);
		re_fillAdvancedDetailsContainer(toolkit, advancedDetails);
		
	}
	
}
