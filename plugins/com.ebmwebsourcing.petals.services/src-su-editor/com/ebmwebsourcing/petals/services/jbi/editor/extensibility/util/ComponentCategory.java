package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IConfigurationElement;

public class ComponentCategory {

	private String label;
	private String id;
	private String description;
	private ArrayList<ComponentSupportExtensionDesc> components;

	public ComponentCategory(IConfigurationElement elt) {
		this.label = elt.getAttribute("label");
		this.id = elt.getAttribute("id");
		this.description = elt.getAttribute("description");
		this.components = new ArrayList<ComponentSupportExtensionDesc>();
	}

	public String getLabel() {
		return this.label;
	}

	public String getDescription() {
		return this.description;
	}

	public String getId() {
		return this.id;
	}

	public Collection<ComponentSupportExtensionDesc> getComponents() {
		return this.components;
	}

}
