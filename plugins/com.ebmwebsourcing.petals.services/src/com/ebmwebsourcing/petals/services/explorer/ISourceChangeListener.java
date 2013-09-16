/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.explorer;

import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface ISourceChangeListener {

	public void sourceChanged( EndpointSource source );
	public void sourceAdded( EndpointSource source );
	public void sourceRemoved( EndpointSource source );
}
