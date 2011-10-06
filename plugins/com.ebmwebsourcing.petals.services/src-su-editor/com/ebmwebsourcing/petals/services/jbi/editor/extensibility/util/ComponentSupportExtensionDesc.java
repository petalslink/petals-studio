package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;

public class ComponentSupportExtensionDesc extends SupportExtensionDesc {

	private static final String CDK_NAMESPACE_ELEMENT = "cdkNamespace";
	
	private String cdkSupportNamespace;
	
	public ComponentSupportExtensionDesc(IConfigurationElement ext) {
		super(ext);
		this.cdkSupportNamespace = ext.getAttribute(CDK_NAMESPACE_ELEMENT);
	}
	
	public SupportExtensionDesc getCDKSupportExtensionDesc() {
		return SupportsUtil.getInstance().getCDKSupport(cdkSupportNamespace);
	}
}
