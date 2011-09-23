package com.ebmwebsourcing.petals.jbi.editor.form.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.jbi.editor.form.JbiFormEditorPlugin;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.ContributionSupport;

class SupportExtensionDesc {
	private static final String PRIORITY_ELEMENT = "priority";
	private static final String NAMESPACE_ELEMENT = "namespace";
	private static final String CDK_SUPPORT_CLASS_ELEMENT = "jbiExtensionSupportClass";
	
	private IConfigurationElement element;
	private int priority;
	private String namespace;
	
	public SupportExtensionDesc(IConfigurationElement ext) {
		this.element = ext;
		try {
			this.priority = Integer.parseInt(element.getAttribute(PRIORITY_ELEMENT));
		} catch (Exception ex) {
			this.priority = 0;
			JbiFormEditorPlugin.log(ex, IStatus.WARNING);
		}
		this.namespace = element.getAttribute(NAMESPACE_ELEMENT);
	}

	public String getNamespace() {
		return this.namespace;
	}

	public int getPriority() {
		return this.priority;
	}

	public ContributionSupport createNewExtensionSupport() {
		try {
			return (ContributionSupport) element.createExecutableExtension(CDK_SUPPORT_CLASS_ELEMENT);
		} catch (Exception ex) {
			JbiFormEditorPlugin.log(ex, IStatus.ERROR);
			return null;
		}
	}
	
	
}