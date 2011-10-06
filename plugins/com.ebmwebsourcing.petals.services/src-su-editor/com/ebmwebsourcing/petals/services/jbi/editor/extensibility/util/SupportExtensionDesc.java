package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.EditorContributionSupport;

public class SupportExtensionDesc {
	private static final String NAMESPACE_ELEMENT = "namespace";
	private static final String CDK_SUPPORT_CLASS_ELEMENT = "editorExtension";

	private final IConfigurationElement element;
	private final String namespace;

	public SupportExtensionDesc(IConfigurationElement ext) {
		this.element = ext;
		this.namespace = this.element.getAttribute(NAMESPACE_ELEMENT);
	}

	public String getNamespace() {
		return this.namespace;
	}

	public EditorContributionSupport createNewExtensionSupport() {
		try {
			return (EditorContributionSupport) this.element.createExecutableExtension(CDK_SUPPORT_CLASS_ELEMENT);
		} catch (Exception ex) {
			PetalsServicesPlugin.log(ex, IStatus.ERROR);
			return null;
		}
	}


}