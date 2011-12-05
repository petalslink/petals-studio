package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

public class ComponentSupportExtensionDesc {

	private List<ComponentVersionSupportExtensionDesc> versions;
	private String id;
	private String label;
	private boolean supportProvides;
	private boolean supportConsumes;
	private static final String SUPPORT_PROVIDES = "supportProvides";
	private static final String SUPPORT_CONSUMES = "supportConsumes";
	
	public ComponentSupportExtensionDesc(IConfigurationElement elt) {
		id = elt.getAttribute("id");
		label = elt.getAttribute("label");
		this.supportProvides = Boolean.parseBoolean(elt.getAttribute(SUPPORT_PROVIDES));
		this.supportConsumes = Boolean.parseBoolean(elt.getAttribute(SUPPORT_CONSUMES));
		versions = new ArrayList<ComponentVersionSupportExtensionDesc>();
		for (IConfigurationElement versionExt : elt.getChildren("ComponentVersionSupport")) {
			ComponentVersionSupportExtensionDesc versionSupport = new ComponentVersionSupportExtensionDesc(versionExt, this);
			versions.add(versionSupport);
		}
		for (IConfigurationElement categoryExt : elt.getChildren("category")) {
			ComponentCategory category = ComponentCategoryUtils.getInstance().getCategory(categoryExt.getAttribute("id"));
			if (category != null) {
				category.getComponents().add(this);
			} else {
				PetalsServicesPlugin.log("Category [" + categoryExt.getValue() + "] does not exist for component [" + id + "]", IStatus.ERROR);
			}
		}
	}

	public Collection<ComponentVersionSupportExtensionDesc> getVersionSupports() {
		return versions;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public boolean supportsConsumes() {
		return supportConsumes;
	}
	
	public boolean supportsProvides() {
		return supportProvides;
	}
}
