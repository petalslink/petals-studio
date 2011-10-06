package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.util;

import java.util.HashMap;
import java.util.Map;

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
	private static final String CDK_SUPPORT_EXTENSION_POINT = PetalsServicesPlugin.getDefault().getBundle().getSymbolicName() + ".cdkSupport";
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
	
	private Map<String, SupportExtensionDesc> cdkSupportExtensions;
	private SupportExtensionDesc defaultCDKSupport;
	private Map<String, ComponentSupportExtensionDesc> componentSupportExtensions;
	
	private SupportsUtil() {
		loadCDKSupports();
		loadComponentSupports();
	}

	private void loadCDKSupports() {
		cdkSupportExtensions = new HashMap<String, SupportExtensionDesc>();
		for (IConfigurationElement elt : Platform.getExtensionRegistry().getConfigurationElementsFor(CDK_SUPPORT_EXTENSION_POINT)) {
			SupportExtensionDesc ext = new SupportExtensionDesc(elt);
			String namespace = ext.getNamespace();
			if (cdkSupportExtensions.containsKey(namespace)) {
				PetalsServicesPlugin.log("Several CDKSupport extensions for NS: " + namespace, IStatus.WARNING);
			}
			cdkSupportExtensions.put(namespace, ext);
			if (defaultCDKSupport == null) {
				defaultCDKSupport = ext;
			}
		}
	}
	
	private void loadComponentSupports() {
		componentSupportExtensions = new HashMap<String, ComponentSupportExtensionDesc>();
		for (IConfigurationElement elt : Platform.getExtensionRegistry().getConfigurationElementsFor(COMPONENT_SUPPORT_EXTENSION_POINT)) {
			for (IConfigurationElement child : elt.getChildren("ComponentVersionSupport")) {
				ComponentSupportExtensionDesc ext = new ComponentSupportExtensionDesc(child);
				String namespace = ext.getNamespace();
				if (componentSupportExtensions.containsKey(namespace)) {
					PetalsServicesPlugin.log("Several ComponentSupports extensions for NS: " + namespace, IStatus.WARNING);
				}
				componentSupportExtensions.put(namespace, ext);
			}
		}
	}

//	
//	public ContributionSupport getCDKSupportFor(AbstractExtensibleElement element) {
//		ContributionSupport res = null;
//		SupportExtensionDesc cdkSupportDesc = null;
//		for (FeatureMap.Entry entry : element.getGroup()) {
//			EStructuralFeature feature = entry.getEStructuralFeature();
//			if (feature instanceof Holder) {
//				SupportExtensionDesc ext = cdkSupportExtensions.get(((Holder)feature).getExtendedMetaData().getNamespace());
//				if (ext != null) {
//					cdkSupportDesc = ext;
//				}	
//			}
//		}
//		if (cdkSupportDesc != null) {
//			res = cdkSupportDesc.createNewExtensionSupport();
//		}
//		if (res == null) {
//			res = getComponentSupportFor(element).createNewExtensionSupport();
//		}
//		res.setElement(element);
//		return res;
//	}
	
//	public EditorContributionSupport getComponentSupportFor(AbstractExtensibleElement element) {
//		EditorContributionSupport res = null;
//		ComponentSupportExtensionDesc componentExtensionDesc = getComponentExtensionDesc(element);
//		
//		if (componentExtensionDesc != null) {
//			res = componentExtensionDesc.createNewExtensionSupport();
//		}
//		if (res != null) {
//			res.setElement(element);
//		}
//		return res;
//	}

	public ComponentSupportExtensionDesc getComponentExtensionDesc(AbstractExtensibleElement element) {
		ComponentSupportExtensionDesc componentExtensionDesc = null;
		for (FeatureMap.Entry entry : element.getGroup()) {
			EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature instanceof Holder) {
				ComponentSupportExtensionDesc ext = componentSupportExtensions.get(((Holder)feature).getExtendedMetaData().getNamespace());
				if (ext != null) {
					componentExtensionDesc = ext;
				}
			}
		}
		return componentExtensionDesc;
	}

	public SupportExtensionDesc getCDKSupport(String cdkSupportNamespace) {
		SupportExtensionDesc res = componentSupportExtensions.get(cdkSupportNamespace);
		if (res != null) {
			return res;
		} else {
			return defaultCDKSupport;
		}
	}
}
