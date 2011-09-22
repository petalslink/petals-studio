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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory;


/**
 * BPELUIWSDLAdapterFactory for generating adapters.
 * 
 * We use an instance of AdapterProvider that caches singleton adapters.
 *
 * @author IBM
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 23, 2007
 *
 */

@SuppressWarnings("restriction")

public class BPELUIWSDLAdapterFactory extends WSDLAdapterFactory {
	
	static BPELUIWSDLAdapterFactory instance;
		
	AdapterProvider provider;	
	
	/**
	 * Private constructor to allow only a singleton instances
	 * @param provider
	 */
	
	private BPELUIWSDLAdapterFactory () {	
		this.provider = new AdapterProvider();
	}

	
	/**
	 * Get the instance of this factory.
	 * 
	 * @return an instance of this factory.
	 */
	
	public static BPELUIWSDLAdapterFactory getInstance() {
		if (instance == null) {
			instance = new BPELUIWSDLAdapterFactory();
		}
		return instance;
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createMessageAdapter()
	 */
	@Override
	public Adapter createMessageAdapter() {
		return provider.getAdapter( MessageAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createFaultAdapter()
	 */
	@Override
	public Adapter createFaultAdapter() {
		return provider.getAdapter( FaultAdapter.class );
	}
	
	
	/**
	 * @return the compensation adapter.
	 */
	
	public Adapter createCompensationAdapter() {
		return provider.getAdapter( CompensateAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createOperationAdapter()
	 */
	
	@Override
	public Adapter createOperationAdapter() {
		return provider.getAdapter( OperationAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createPartAdapter()
	 */
	@Override
	public Adapter createPartAdapter() {
		return provider.getAdapter( PartAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createPortTypeAdapter()
	 */
	@Override
	public Adapter createPortTypeAdapter() {
		return provider.getAdapter( PortTypeAdapter.class );
	}

	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createInputAdapter()
	 */
	@Override	
	public Adapter createInputAdapter() {
		return provider.getAdapter(InputMessageAdapter.class);
	}

	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createOutputAdapter()
	 */
	@Override
	public Adapter createOutputAdapter() {
		return provider.getAdapter(OutputMessageAdapter.class);
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

