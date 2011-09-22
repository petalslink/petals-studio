/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.adapters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.model.adapters.AbstractStatefulAdapter;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.notify.Notification;

/**
 * Adapter for an object which may (or may not) support IContainer.
 * This class forwards the IContainer methods to a delegate which subclasses
 * should construct in createContainerDelegate().
 */
public abstract class ContainerAdapter 
            extends AbstractStatefulAdapter 
            implements IContainer,  IMarkerHolder, AdapterNotification  {

	IContainer containerDelegate = null;
	
	protected final IContainer getContainerDelegate(Object object) {
		if (containerDelegate == null) {
			containerDelegate = createContainerDelegate();
		}
		return containerDelegate;
	}
	
	
	
	/**
	 * @see org.eclipse.bpel.model.adapters.AbstractAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {		
		super.notifyChanged(notification);
		switch (notification.getEventType()) {
			case NOTIFICATION_MARKERS_STALE : 
				fMarkers.clear();
				break;
			case NOTIFICATION_MARKER_ADDED :
				fMarkers.add ( (IMarker) notification.getNewValue() );
				break;
			case NOTIFICATION_MARKER_DELETED :
				fMarkers.remove ( notification.getOldValue() );
				break;								
		}				
	}
	
	ArrayList<IMarker> fMarkers = new ArrayList<IMarker>();

	static IMarker [] EMPTY_MARKERS = {};
	
	/** (non-Javadoc)
	 * @see org.eclipse.bpel.ui.adapters.IMarkerHolder#getMarkers(java.lang.Object)
	 */

	public IMarker[] getMarkers (Object object) {
		
		if (fMarkers.size() == 0) {
			return EMPTY_MARKERS;
		}
		return fMarkers.toArray( EMPTY_MARKERS );						
	}
	
	
	/**
	 * Subclasses must override this to create the actual IContainer
	 * implementation.  This method should not return null.
	 */
	protected abstract IContainer createContainerDelegate();
	
	/* IContainer */

	public boolean addChild(Object object, Object child, Object insertBefore) {
		return getContainerDelegate(object).addChild(object, child, insertBefore);
	}
	public List getChildren(Object object) {
		return getContainerDelegate(object).getChildren(object);
	}
	public boolean removeChild(Object object, Object child) {
		return getContainerDelegate(object).removeChild(object, child);
	}
	public boolean replaceChild(Object object, Object oldChild, Object newChild) {
		return getContainerDelegate(object).replaceChild(object, oldChild, newChild);
	}
	public Object getNextSiblingChild(Object object, Object child) {
		return getContainerDelegate(object).getNextSiblingChild(object, child);
	}
	public boolean canAddObject(Object object, Object child, Object insertBefore) {
		return getContainerDelegate(object).canAddObject(object, child, insertBefore);
	}
	
	public boolean canRemoveChild (Object object, Object child ) {
		return getContainerDelegate(object).canRemoveChild(object, child );
	}
	
}
