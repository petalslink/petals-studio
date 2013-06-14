/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.sun.java.xml.ns.jbi.provider;


import com.sun.java.xml.ns.jbi.Component;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.sun.java.xml.ns.jbi.Component} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addBootstrapClassNamePropertyDescriptor(object);
			addBootstrapClassLoaderDelegationPropertyDescriptor(object);
			addComponentClassLoaderDelegationPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Bootstrap Class Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBootstrapClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Component_bootstrapClassName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Component_bootstrapClassName_feature", "_UI_Component_type"),
				 JbiPackage.Literals.COMPONENT__BOOTSTRAP_CLASS_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Bootstrap Class Loader Delegation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBootstrapClassLoaderDelegationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Component_bootstrapClassLoaderDelegation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Component_bootstrapClassLoaderDelegation_feature", "_UI_Component_type"),
				 JbiPackage.Literals.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Component Class Loader Delegation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addComponentClassLoaderDelegationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Component_componentClassLoaderDelegation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Component_componentClassLoaderDelegation_feature", "_UI_Component_type"),
				 JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Component_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Component_type_feature", "_UI_Component_type"),
				 JbiPackage.Literals.COMPONENT__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__IDENTIFICATION);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_NAME);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_PATH);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__BOOTSTRAP_CLASS_PATH);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__SHARED_LIBRARY_LIST);
			childrenFeatures.add(JbiPackage.Literals.COMPONENT__GROUP);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Component.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Component"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Component)object).getBootstrapClassName();
		return label == null || label.length() == 0 ?
			getString("_UI_Component_type") :
			getString("_UI_Component_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Component.class)) {
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME:
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION:
			case JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION:
			case JbiPackage.COMPONENT__TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case JbiPackage.COMPONENT__IDENTIFICATION:
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
			case JbiPackage.COMPONENT__GROUP:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(JbiPackage.Literals.COMPONENT__IDENTIFICATION,
				 JbiFactory.eINSTANCE.createIdentification()));

		newChildDescriptors.add
			(createChildParameter
				(JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_NAME,
				 JbiFactory.eINSTANCE.createComponentClassName()));

		newChildDescriptors.add
			(createChildParameter
				(JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_PATH,
				 JbiFactory.eINSTANCE.createClassPath()));

		newChildDescriptors.add
			(createChildParameter
				(JbiPackage.Literals.COMPONENT__BOOTSTRAP_CLASS_PATH,
				 JbiFactory.eINSTANCE.createClassPath()));

		newChildDescriptors.add
			(createChildParameter
				(JbiPackage.Literals.COMPONENT__SHARED_LIBRARY_LIST,
				 FeatureMapUtil.createEntry
					(JbiPackage.Literals.COMPONENT__SHARED_LIBRARY,
					 JbiFactory.eINSTANCE.createSharedLibraryType1())));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry)childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

		boolean qualify =
			childFeature == JbiPackage.Literals.COMPONENT__COMPONENT_CLASS_PATH ||
			childFeature == JbiPackage.Literals.COMPONENT__BOOTSTRAP_CLASS_PATH;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return JbiEditPlugin.INSTANCE;
	}

}
