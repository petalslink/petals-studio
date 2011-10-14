package com.ebmwebsourcing.petals.services.jbi.wizard;

import java.util.Collections;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;

public class NewSUWizard extends Wizard implements IWorkbenchWizard {

	private NewServiceWizardPage page;
	private Jbi jbi;

	public NewSUWizard() {
		setWindowTitle(Messages.addService);
		this.jbi = JbiFactory.eINSTANCE.createJbi();
		jbi.setServices(JbiFactory.eINSTANCE.createServices());
	}
	
	@Override
	public void addPages() {
		page = new NewServiceWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		StringBuilder projectName = new StringBuilder();
		projectName.append("su-");
		AbstractEndpoint service = page.getService();
		ComponentVersionSupportExtensionDesc version = page.getSelectedComponentVersion();
		service.setEndpointName("endpointName");
		service.setInterfaceName(new QName(version.getNamespace(), "interfaceName"));
		service.setServiceName(new QName(version.getNamespace(), page.getServiceName()));
		service.setEndpointName(page.getServiceName() + "Endpoint");
		version.createServiceInitializer().initializeService(service);
		if (service instanceof Provides) {
			jbi.getServices().getProvides().add((Provides)service);
			projectName.append("provides-");
		} else if (page.getService() instanceof Consumes) {
			jbi.getServices().getConsumes().add((Consumes)service);
			projectName.append("consumes-");
		}
		projectName.append(version.getComponent().getId());
		projectName.append("-");
		projectName.append(service.getServiceName().getLocalPart());
		
		return createSUProject(projectName.toString());
	}

	private boolean createSUProject(String projectName) {
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).exists()) {
			// TODO
			return false;
		}
		
		try {
			IProject project = PetalsServicesProjectUtils.createSuProject(projectName, null,
					page.getSelectedComponentVersion().getComponent().getId(),
					page.getSelectedComponentVersion().getVersion(),
					jbi instanceof Provides ? "provides" : "consumes",
					true,
					new NullProgressMonitor());
			IFolder targetFolder = project.getFolder("src").getFolder("main").getFolder("jbi");
			IFile jbiFile = targetFolder.getFile("jbi.xml");
			ResourceSet resourceSet = new ResourceSetImpl();	
			Resource res = resourceSet.createResource(URI.createPlatformResourceURI(jbiFile.getFullPath().toString(), true));
			res.getContents().add(jbi);
			res.save(Collections.EMPTY_MAP);
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), jbiFile);
			return true;
		} catch (Exception ex) {
			PetalsServicesPlugin.log(ex, IStatus.ERROR);
			return false;
		}
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

}
