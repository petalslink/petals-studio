package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;

public class SupportsUtil {
	
	// KEYS
	private static final String COMPONENT_SUPPORT_EXTENSION_POINT = PetalsServicesPlugin.getDefault().getBundle().getSymbolicName() + ".componentExtension";
	
	// Singleton
	private static SupportsUtil INSTANCE;
	
	public synchronized static SupportsUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SupportsUtil();
		}
		return INSTANCE;
	}
	
	// logic
	private Map<String, ComponentVersionSupportExtensionDesc> namespaceToVersionSupport;
	private Set<ComponentSupportExtensionDesc> components;
	private Collection<String> extensionPackagesNS;
	
	private SupportsUtil() {
		namespaceToVersionSupport = new HashMap<String, ComponentVersionSupportExtensionDesc>();
		extensionPackagesNS = new HashSet<String>();
		components = new HashSet<ComponentSupportExtensionDesc>();
		loadComponentSupports();
	}

	private void loadComponentSupports() {
		for (IConfigurationElement elt : Platform.getExtensionRegistry().getConfigurationElementsFor(COMPONENT_SUPPORT_EXTENSION_POINT)) {
			ComponentSupportExtensionDesc componentSupportExtension = new ComponentSupportExtensionDesc(elt);
			components.add(componentSupportExtension);
			for (ComponentVersionSupportExtensionDesc ext : componentSupportExtension.getVersionSupports()) {
				String namespace = ext.getNamespace();
				if (namespace != null) {
					extensionPackagesNS.add(namespace);
					if (namespaceToVersionSupport.containsKey(namespace)) {
						PetalsServicesPlugin.log("Several ComponentSupports extensions for NS: " + namespace, IStatus.WARNING);
					}
					namespaceToVersionSupport.put(namespace, ext);
				}
			}
		}
	}

	public ComponentVersionSupportExtensionDesc getComponentExtensionDesc(AbstractExtensibleElement element) {
		ComponentVersionSupportExtensionDesc componentExtensionDesc = null;
		for (FeatureMap.Entry entry : element.getGroup()) {
			EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature instanceof Holder) {
				ComponentVersionSupportExtensionDesc ext = namespaceToVersionSupport.get(((Holder)feature).getExtendedMetaData().getNamespace());
				if (ext != null) {
					componentExtensionDesc = ext;
				}
			}
		}
		return componentExtensionDesc;
	}
	
	public Collection<ComponentSupportExtensionDesc> getComponents() {
		return this.components;
	}


	public Collection<ComponentSupportExtensionDesc> getAllConsumes() {
		List<ComponentSupportExtensionDesc> res = new ArrayList<ComponentSupportExtensionDesc>();
		for (ComponentSupportExtensionDesc comp : components) {
			if (comp.supportsConsumes()) {
				res.add(comp);
			}
		}
		return res;
	}
	
	public Collection<ComponentSupportExtensionDesc> getAllProvides() {
		List<ComponentSupportExtensionDesc> res = new ArrayList<ComponentSupportExtensionDesc>();
		for (ComponentSupportExtensionDesc comp : components) {
			if (comp.supportsProvides()) {
				res.add(comp);
			}
		}
		return res;
	}


	public Collection<String> getJBIExtensionEPackage() {
		return extensionPackagesNS;
	}
}
