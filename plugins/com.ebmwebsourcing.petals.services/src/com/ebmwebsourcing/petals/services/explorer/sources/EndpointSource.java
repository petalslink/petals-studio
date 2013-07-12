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

package com.ebmwebsourcing.petals.services.explorer.sources;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * An abstract class representing a source of end-points.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class EndpointSource {

	protected String name, id, description;
	private Collection<ServiceUnitBean> serviceUnits;


	/**
	 * Constructor with two setters.
	 * @param name the source name
	 * @param id the source id
	 */
	public EndpointSource( String name, String id ) {
		this.name = name;
		this.id = id;
	}


	/**
	 * @return the serviceUnits
	 */
	public final Collection<ServiceUnitBean> getServiceUnits() {

		if( this.serviceUnits == null ) {
			final IRunnableWithProgress rwp = new IRunnableWithProgress() {

				@Override
				public void run( IProgressMonitor monitor )
						throws InvocationTargetException, InterruptedException {
					try {
						monitor.beginTask( "Getting service units for " + EndpointSource.this.name + "...", IProgressMonitor.UNKNOWN );
						EndpointSource.this.serviceUnits = refreshServiceUnits( monitor );
					} finally {
						monitor.done();
					}
				}
			};

			Display.getDefault().syncExec( new Runnable() {
				@Override
				public void run() {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().run( false, false, rwp );

					} catch( InvocationTargetException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );

					} catch( InterruptedException e ) {
						// nothing
					}
				}
			});
		}

		return this.serviceUnits;
	}


	/**
	 * Refreshes the list of service units.
	 * <p>
	 * The list will be refreshed on the next call to {@link #getServiceUnits()}.
	 * </p>
	 */
	public final void refreshServiceUnitList() {
		this.serviceUnits = null;
	}


	/**
	 * Gets the service units from this source.
	 * <p>
	 * This method should be called in a runnable with a progress monitor.
	 * </p>
	 *
	 * @param monitor a progress monitor, already initialized (unknown count)
	 * @return the service units from this source
	 */
	protected abstract Collection<ServiceUnitBean> refreshServiceUnits( IProgressMonitor monitor );


	/**
	 * Destroys this source.
	 */
	public abstract void dispose();


	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( String id ) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription( String description ) {
		this.description = description;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		return obj != null
				&& obj instanceof EndpointSource
				&& ((EndpointSource) obj).id.equals( this.id );
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.id == null ? 137 : 199 * this.id.charAt( 0 ) * this.id.length();
	}


	/**
	 * Gets the end-points from a jbi.xml for service unit.
	 * @param jbi the JBI model
	 * @param suBean the SU bean to complete
	 */
	protected void getEndpointBeans( Jbi jbi, ServiceUnitBean suBean ) {

		for( Provides provides : jbi.getServices().getProvides()) {
			EndpointBean bean = new EndpointBean();

			// Simple fields
			bean.setEndpointName( provides.getEndpointName());
			bean.setInterfaceName( provides.getInterfaceName());
			bean.setServiceName( provides.getServiceName());

			// WSDL
			String wsdlValue = JbiXmlUtils.getWsdlValue( provides );
			bean.setWsdlLocation( wsdlValue );

			bean.setServiceUnit( suBean );
			suBean.addEndpoint( bean );
		}
	}


	/**
	 * Gets the directory path which stores the WSDLs for the given service unit.
	 * <p>
	 * Be careful, this is applied to service-units and not to end-points.<br />
	 * When the WSDL container location is computed, it must be done for all the
	 * end-points of the service-unit.
	 * </p>
	 *
	 * @param suBean
	 * @return the directory path where is located the WSDL of this jbi.xml.
	 * <p>
	 * This method cannot return null, but can return "" in case of problem.
	 * </p>
	 */
	public abstract String getWsdlContainerLocation( ServiceUnitBean suBean );
}
