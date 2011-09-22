/**
 *  Copyright (c) 2009-2011, EBM WebSourcing
 * 
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 * 
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 * $Id$
 */
package com.sun.java.xml.ns.jbi.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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

import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * This is the item provider adapter for a {@link com.sun.java.xml.ns.jbi.DocumentRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentRootItemProvider
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
	 * @param adapterFactory
	 * @generated
	 */
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
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
		if (this.itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addArtifactsZipPropertyDescriptor(object);
			addBootstrapClassNamePropertyDescriptor(object);
			addComponentNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addPathElementPropertyDescriptor(object);
		}
		return this.itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Artifacts Zip feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addArtifactsZipPropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_artifactsZip_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_artifactsZip_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__ARTIFACTS_ZIP,
								true,
								false,
								false,
								ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
								null,
								null));
	}

	/**
	 * This adds a property descriptor for the Bootstrap Class Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBootstrapClassNamePropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_bootstrapClassName_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_bootstrapClassName_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME,
								true,
								false,
								false,
								ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
								null,
								null));
	}

	/**
	 * This adds a property descriptor for the Component Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addComponentNamePropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_componentName_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_componentName_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_NAME,
								true,
								false,
								false,
								ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
								null,
								null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_description_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_description_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__DESCRIPTION,
								true,
								false,
								false,
								ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
								null,
								null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_name_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_name_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__NAME,
								true,
								false,
								false,
								ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
								null,
								null));
	}

	/**
	 * This adds a property descriptor for the Path Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPathElementPropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_DocumentRoot_pathElement_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_DocumentRoot_pathElement_feature", "_UI_DocumentRoot_type"),
								JbiPackage.Literals.DOCUMENT_ROOT__PATH_ELEMENT,
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
		if (this.childrenFeatures == null) {
			super.getChildrenFeatures(object);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_NAME);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTION);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTIONS);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMER);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMES);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__IDENTIFICATION);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__JBI);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDER);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDES);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_ASSEMBLY);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__SERVICES);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_UNIT);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH);
			this.childrenFeatures.add(JbiPackage.Literals.DOCUMENT_ROOT__TARGET);
		}
		return this.childrenFeatures;
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
	 * This returns DocumentRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((DocumentRoot)object).getName();
		return label == null || label.length() == 0 ?
					getString("_UI_DocumentRoot_type") :
						getString("_UI_DocumentRoot_type") + " " + label;
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

		switch (notification.getFeatureID(DocumentRoot.class)) {
		case JbiPackage.DOCUMENT_ROOT__ARTIFACTS_ZIP:
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME:
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_NAME:
		case JbiPackage.DOCUMENT_ROOT__DESCRIPTION:
		case JbiPackage.DOCUMENT_ROOT__NAME:
		case JbiPackage.DOCUMENT_ROOT__PATH_ELEMENT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
		case JbiPackage.DOCUMENT_ROOT__JBI:
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
		case JbiPackage.DOCUMENT_ROOT__TARGET:
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
					(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH,
								JbiFactory.eINSTANCE.createClassPath()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT,
								JbiFactory.eINSTANCE.createComponent()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_NAME,
								JbiFactory.eINSTANCE.createComponentClassName()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH,
								JbiFactory.eINSTANCE.createClassPath()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTION,
								JbiFactory.eINSTANCE.createConnection()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTIONS,
								JbiFactory.eINSTANCE.createConnections()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMER,
								JbiFactory.eINSTANCE.createConsumer()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMES,
								JbiFactory.eINSTANCE.createConsumes()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__IDENTIFICATION,
								JbiFactory.eINSTANCE.createIdentification()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__JBI,
								JbiFactory.eINSTANCE.createJbi()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDER,
								JbiFactory.eINSTANCE.createProvider()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDES,
								JbiFactory.eINSTANCE.createProvides()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_ASSEMBLY,
								JbiFactory.eINSTANCE.createServiceAssembly()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__SERVICES,
								JbiFactory.eINSTANCE.createServices()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_UNIT,
								JbiFactory.eINSTANCE.createServiceUnit()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH,
								JbiFactory.eINSTANCE.createClassPath()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.DOCUMENT_ROOT__TARGET,
								JbiFactory.eINSTANCE.createTarget()));
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

		boolean qualify =
			childFeature == JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH ||
			childFeature == JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH ||
			childFeature == JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH;

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
