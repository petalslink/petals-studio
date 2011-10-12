package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;

public class ComponentVersionSupportExtensionDesc {

	private static final String NAMESPACE_ELEMENT = "namespace";
	private static final String EDITOR_EXTENSION_CLASS_ELEMENT = "editorExtension";
	
	private final IConfigurationElement element;
	private final String namespace;
	private final String id;

	public ComponentVersionSupportExtensionDesc(IConfigurationElement ext) {
		this.element = ext;
		this.namespace = this.element.getAttribute(NAMESPACE_ELEMENT);
		this.id = this.element.getAttribute("id");
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

	public String getVersion() {
		return this.id;
	}
	
}
