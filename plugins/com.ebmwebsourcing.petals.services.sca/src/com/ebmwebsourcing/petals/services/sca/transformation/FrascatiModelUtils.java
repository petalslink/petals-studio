/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.transformation;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils;
import org.eclipse.soa.sca.sca1_0.model.sca.provider.ScaItemProviderAdapterFactory;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.provider.FrascatiItemProviderAdapterFactory;

/**
 * A set of utility methods related to the SCA meta-model.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FrascatiModelUtils extends ScaModelUtils {

	/**
	 * Constructor.
	 */
	public FrascatiModelUtils() {

		ComposedAdapterFactory adapterFactory =
			new ComposedAdapterFactory( ComposedAdapterFactory.Descriptor.Registry.INSTANCE );

		adapterFactory.addAdapterFactory( new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory( new ScaItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory( new FrascatiItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory( new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack commandStack = new BasicCommandStack();
		this.editingDomain = new AdapterFactoryEditingDomain(
					adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}
}
