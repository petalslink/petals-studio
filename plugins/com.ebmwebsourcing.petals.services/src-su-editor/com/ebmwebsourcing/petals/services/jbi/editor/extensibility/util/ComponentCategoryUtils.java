package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

public class ComponentCategoryUtils {

	private static final String COMPONENT_CATEGORY_EXTENSION_POINT = PetalsServicesPlugin.getDefault().getBundle().getSymbolicName() + ".componentCategory";
	
	private static ComponentCategoryUtils INSTANCE;
	
	private Map<String, ComponentCategory> categories; 
	
	private ComponentCategoryUtils() {
		loadCategories();
	}
	
	public static synchronized ComponentCategoryUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ComponentCategoryUtils();
		}
		return INSTANCE;
	}
	
	private void loadCategories() {
		categories = new HashMap<String, ComponentCategory>();
		for (IConfigurationElement elt : Platform.getExtensionRegistry().getConfigurationElementsFor(COMPONENT_CATEGORY_EXTENSION_POINT)) {
			ComponentCategory category = new ComponentCategory(elt);
			categories.put(category.getId(), category);
		}
	}

	public ComponentCategory getCategory(String value) {
		return categories.get(value);
	}
	
	public Collection<ComponentCategory> getAllCategories() {
		return categories.values();
	}
}
