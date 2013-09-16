/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
	public void elementChanged( Object viewerObject );
	public void resourceChanged( IResourceDelta delta );
	public void markerChanged( IMarkerDelta[] markerDeltas );
}
