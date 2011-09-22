/****************************************************************************
 * 
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.projectscnf;

import java.util.Collection;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

/**
 * An interface to filter resource changes that may impact the Petals projects view.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IPetalsProjectResourceChangeListener {

	public void prepareNotification();
	public void terminateNotification();

	public void resourcesAdded( Collection<IResource> resources );
	public void resourcesRemoved( Collection<IResource> resources );
	public void resourceChanged( IResourceDelta delta );
	public void markerChanged( IMarkerDelta[] markerDeltas );
}
