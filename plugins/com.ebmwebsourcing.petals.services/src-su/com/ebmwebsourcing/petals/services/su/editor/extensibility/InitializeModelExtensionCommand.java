/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.extensibility;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;

import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;

public class InitializeModelExtensionCommand extends AbstractCommand {
	
	private Set<EStructuralFeature> targetFeatures;
	private EPackage extensionPackage;
	private AbstractExtensibleElement element;
	
	public InitializeModelExtensionCommand(EPackage extensionPackage, AbstractExtensibleElement element) {
		this.extensionPackage = extensionPackage;
		this.element = element;
	}
	
	@Override
	public boolean prepare() {
		initializeFeatures();
		return needsAdditionalAttributes();
	}

	@Override
	public void execute() {
		for (EStructuralFeature targetFeature : targetFeatures) {
			Entry entry = getMatchingGroupEntry(targetFeature);
			if (entry != null) {
				Object value = getActualValue(entry.getValue());
				if (value != null) {
					element.getGroup().remove(entry);
					if (value instanceof String && targetFeature instanceof EAttribute) {
						EDataType expectedType = ((EAttribute)targetFeature).getEAttributeType();
						if (expectedType.equals(EcorePackage.Literals.EINT)) {
							element.eSet(targetFeature, Integer.valueOf((String)value));
						} else if (expectedType instanceof EEnum) {
							EEnum eEnum = (EEnum)expectedType;
							EEnumLiteral literal = eEnum.getEEnumLiteralByLiteral((String)value);
							element.eSet(targetFeature, literal.getInstance());
						} else if (expectedType.getInstanceClass().equals(boolean.class)) {
							element.eSet(targetFeature, Boolean.valueOf((String)value));
						} else {
							element.eSet(targetFeature, value);
						}
					} else {
						element.eSet(targetFeature, value);
					}
				}
			}
		}
	}

	public void initializeFeatures() {
		if (targetFeatures == null) {
			targetFeatures = new HashSet<EStructuralFeature>();
			for (EClassifier classifier : extensionPackage.getEClassifiers()) {
				if (classifier instanceof EClass) {
					EClass eClass = (EClass)classifier;
					for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
						((EStructuralFeatureImpl)feature).setFeatureID(-1);
						targetFeatures.add(feature);
					}
				}
			}
		}
	}
	
	private boolean needsAdditionalAttributes() {
		for (EStructuralFeature targetFeature : targetFeatures) {
			Entry actualCurrentEntry = getMatchingGroupEntry(targetFeature);
			// found another feature for the same name // Should use QName
			if (actualCurrentEntry != null && !targetFeatures.contains(actualCurrentEntry.getEStructuralFeature())) {
				return true;
			}
		}
		return false;
	}
	
	private Object getActualValue(Object value) {
		if (value instanceof AnyType) {
			return ((AnyType)value).getMixed().get(0).getValue();
		} else {
			return value;
		}
	}

	private Entry getMatchingGroupEntry(EStructuralFeature referenceFeature) {
		for (FeatureMap.Entry entry : element.getGroup()) {
			String actualCurrentFeatureName = entry.getEStructuralFeature().getName();
			String actualNamespace = ExtendedMetaData.INSTANCE.getNamespace(entry.getEStructuralFeature());
			String referenceNamespace = ExtendedMetaData.INSTANCE.getName(referenceFeature);
			if (actualNamespace.equals(referenceNamespace) &&
					(actualCurrentFeatureName.equals(referenceFeature.getName()) || // static feature name
					actualCurrentFeatureName.equals(ExtendedMetaData.INSTANCE.getName(referenceFeature))
				)) { // or XML feature name
				return entry;
			}
		}
		return null;
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}

}
