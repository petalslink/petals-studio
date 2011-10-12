package com.ebmwebsourcing.petals.services.jbi.editor.su.wizard;

import javax.xml.namespace.QName;

import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.wizard.Wizard;

import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util.ComponentVersionSupportExtensionDesc;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;

public class NewServiceWizard extends Wizard {

	private NewServiceWizardPage page;
	private Jbi jbi;
	private EditingDomain editingDomain;

	public NewServiceWizard(Jbi jbi, EditingDomain editingDomain) {
		setWindowTitle(Messages.addService);
		this.jbi = jbi;
		this.editingDomain = editingDomain;
	}
	
	@Override
	public void addPages() {
		page = new NewServiceWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		AbstractEndpoint service = page.getService();
		ComponentVersionSupportExtensionDesc component = page.getSelectedComponentVersion();
		service.setEndpointName("endpointName");
		service.setInterfaceName(new QName(component.getNamespace(), "interfaceName"));
		service.setServiceName(new QName(component.getNamespace(), "serviceName"));
		component.createServiceInitializer().initializeService(service);
		AddCommand addCommand = null;
		if (service instanceof Provides) {
			addCommand = new AddCommand(editingDomain, jbi.getServices().getProvides(), service);
		} else if (page.getService() instanceof Consumes) {
			addCommand = new AddCommand(editingDomain, jbi.getServices().getConsumes(),	service);
		}
		editingDomain.getCommandStack().execute(addCommand);
		return true;
	}

}
