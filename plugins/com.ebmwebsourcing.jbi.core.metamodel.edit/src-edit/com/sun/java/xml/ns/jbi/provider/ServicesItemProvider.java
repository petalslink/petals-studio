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

import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Services;

/**
 * This is the item provider adapter for a {@link com.sun.java.xml.ns.jbi.Services} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ServicesItemProvider
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
	public ServicesItemProvider(AdapterFactory adapterFactory) {
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

			addBindingComponentPropertyDescriptor(object);
		}
		return this.itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Binding Component feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBindingComponentPropertyDescriptor(Object object) {
		this.itemPropertyDescriptors.add
		(createItemPropertyDescriptor
					(((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(),
								getResourceLocator(),
								getString("_UI_Services_bindingComponent_feature"),
								getString("_UI_PropertyDescriptor_description", "_UI_Services_bindingComponent_feature", "_UI_Services_type"),
								JbiPackage.Literals.SERVICES__BINDING_COMPONENT,
								true,
								false,
								false,
								ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
			this.childrenFeatures.add(JbiPackage.Literals.SERVICES__PROVIDES);
			this.childrenFeatures.add(JbiPackage.Literals.SERVICES__CONSUMES);
			this.childrenFeatures.add(JbiPackage.Literals.SERVICES__GROUP);
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
	 * This returns Services.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Services"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * <p>
	 * 	Updated the way the binding-component attribute is displayed.
	 * </p>
	 */
	@Override
	public String getText(Object object) {
		Services services = (Services)object;
		return getString("_UI_Services_type") + " (" + services.isBindingComponent() + ")";
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

		switch (notification.getFeatureID(Services.class)) {
		case JbiPackage.SERVICES__BINDING_COMPONENT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case JbiPackage.SERVICES__PROVIDES:
		case JbiPackage.SERVICES__CONSUMES:
		case JbiPackage.SERVICES__GROUP:
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
					(JbiPackage.Literals.SERVICES__PROVIDES,
								JbiFactory.eINSTANCE.createProvides()));

		newChildDescriptors.add
		(createChildParameter
					(JbiPackage.Literals.SERVICES__CONSUMES,
								JbiFactory.eINSTANCE.createConsumes()));
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