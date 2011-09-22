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

import org.eclipse.bpel.model.adapters.AdapterProvider;
import org.eclipse.bpel.model.messageproperties.util.MessagepropertiesAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;



/**
 * BPELUIMessagePropertiesAdapterFactory for generating adapters.
 * 
 * We use an instance of AdapterProvider that caches singleton adapters.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 23, 2007
 *
 */

public class BPELUIMessagePropertiesAdapterFactory extends MessagepropertiesAdapterFactory {
	
	static BPELUIMessagePropertiesAdapterFactory instance;
	
	AdapterProvider provider;
	
	private BPELUIMessagePropertiesAdapterFactory () {
		provider = new AdapterProvider();
	}
	
	/**
	 * Get the instance of this factory.
	 * 
	 * @return an instance of this factory.
	 */
	
	public static BPELUIMessagePropertiesAdapterFactory getInstance() {
		if (instance == null) {
			instance = new BPELUIMessagePropertiesAdapterFactory();
		}
		return instance;
	}

	/**
	 * @see org.eclipse.bpel.model.messageproperties.util.MessagepropertiesAdapterFactory#createPropertyAdapter()
	 */
	@Override
	public Adapter createPropertyAdapter() {
		return provider.getAdapter( PropertyAdapter.class );
	}
	
	
	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#adaptNew(org.eclipse.emf.common.notify.Notifier, java.lang.Object)
	 */
	
	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		Adapter adapter = createAdapter(target, type);
		if (adapter == null) {
			return null;
		}
		associate(adapter, target);
		return adapter.isAdapterForType(type) ? adapter : null;
	}
	
	
	
	@Override
	protected Object resolve(Object object, Object type) {
		return null;
	}

}
