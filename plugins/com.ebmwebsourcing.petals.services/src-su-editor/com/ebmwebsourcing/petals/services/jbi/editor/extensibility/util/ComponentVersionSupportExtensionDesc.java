package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.ServiceInitializer;

public class ComponentVersionSupportExtensionDesc {

	private static final String NAMESPACE_ELEMENT = "namespace";
	private static final String EDITOR_EXTENSION_CLASS_ELEMENT = "editorExtension";
	private static final String SERVICE_INITIALIZER_CLASS_ELEMENT = "serviceInitializer";
	
	private final IConfigurationElement element;
	private final String namespace;
	private final String id;
	private ComponentSupportExtensionDesc component;

	public ComponentVersionSupportExtensionDesc(IConfigurationElement ext, ComponentSupportExtensionDesc component) {
		this.element = ext;
		this.namespace = this.element.getAttribute(NAMESPACE_ELEMENT);
		this.id = this.element.getAttribute("id");
		this.component = component;
	}

	public String getNamespace() {
		return this.namespace;
	}

	public EditorContributionSupport createNewExtensionSupport() {
		try {
			return (EditorContributionSupport) this.element.createExecutableExtension(EDITOR_EXTENSION_CLASS_ELEMENT);
		} catch (Exception ex) {
			PetalsServicesPlugin.log(ex, IStatus.ERROR);
			return null;
		}
	}
	
	public ServiceInitializer createServiceInitializer() {
		try {
			return (ServiceInitializer) this.element.createExecutableExtension(SERVICE_INITIALIZER_CLASS_ELEMENT);
		} catch (Exception ex) {
			PetalsServicesPlugin.log(ex, IStatus.ERROR);
			return null;
		}
	}

	public String getVersion() {
		return this.id;
	}

	public ComponentSupportExtensionDesc getComponent() {
		return this.component;
	}
	
}
