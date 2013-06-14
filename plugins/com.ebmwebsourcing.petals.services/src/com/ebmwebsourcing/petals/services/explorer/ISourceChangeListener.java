/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

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
