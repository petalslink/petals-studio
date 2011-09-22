/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.adapters;

import java.util.ArrayList;

import org.eclipse.bpel.model.adapters.AbstractStatefulAdapter;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.notify.Notification;


/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jun 7, 2007
 *
 */

public class ExpressionAdapter extends AbstractStatefulAdapter implements IMarkerHolder, AdapterNotification {

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
	
}
