package com.ebmwebsourcing.petals.jbi.editor.form.extensibility.util;

import org.eclipse.core.runtime.IConfigurationElement;

public class ComponentSupportExtensionDesc extends SupportExtensionDesc {

	private static final String CDK_NAMESPACE_ELEMENT = "cdkNamespace";
	
	private String cdkSupportNamespace;
	private SupportExtensionDesc cdkSupport;
	
	public ComponentSupportExtensionDesc(IConfigurationElement ext) {
		super(ext);
		this.cdkSupportNamespace = ext.getAttribute(CDK_NAMESPACE_ELEMENT);
		this.cdkSupport = SupportsUtil.getInstance().getCDKSupport(cdkSupportNamespace);
	}
	
	public SupportExtensionDesc getCDKSupportExtensionDesc() {
		return cdkSupport;
	}
}
