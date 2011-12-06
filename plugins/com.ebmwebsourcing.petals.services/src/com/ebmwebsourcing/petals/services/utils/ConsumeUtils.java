/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Control;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.explorer.SourceManager;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;

/**
 * Utilities to define service consumers.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConsumeUtils {

	/**
	 * Opens a dialog to select an end-point to consume.
	 * <p>
	 * Equivalent to {@link #selectEndpointToConsume(Control, Mep)}
	 * where the MEP is {@link Mep#UNKNOWN}.
	 * </p>
	 *
	 * @param parent the dialog parent
	 * @return an-end-point, or null <code>Cancel</code> was clicked
	 */
	public static EndpointBean selectEndpointToConsume( Control parent ) {
		return selectEndpointToConsume( parent, Mep.UNKNOWN );
	}


	/**
	 * Opens a dialog to select an end-point to consume.
	 * @param parent the dialog parent
	 * @param mep the MEP to filter the results (MEP.UNKNOWN to display all the services)
	 * @return an-end-point, or null <code>Cancel</code> was clicked
	 */
	public static EndpointBean selectEndpointToConsume( Control parent, final Mep mep ) {

		PCStyledLabelProvider lp = new PCStyledLabelProvider( parent, mep );
		StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog( parent.getShell(), lp );

		Collection<EndpointSource> edptSources = SourceManager.getInstance().getSources();
		List<EndpointBean> edpts = new ArrayList<EndpointBean> ();
		for( EndpointSource source : edptSources ) {

			Collection<ServiceUnitBean> suBeans = source.getServiceUnits();
			for( ServiceUnitBean suBean : suBeans ) {
				if( PreferencesManager.hideIncompatibleServicesInConsumeDialog()) {
					for( EndpointBean edpt : suBean.getEndpoints()) {
						if( edpt.supportsMep( mep ))
							edpts.add( edpt );
					}
				}
				else {
					edpts.addAll( suBean.getEndpoints());
				}
			}
		}

		Collections.sort( edpts, new Comparator<EndpointBean> () {
			@Override
			public int compare( EndpointBean o1, EndpointBean o2 ) {

				// MEP
				boolean invocable1 = o1.getOperationNameToMep().isEmpty() || o1.supportsMep( mep );
				boolean invocable2 = o2.getOperationNameToMep().isEmpty() || o2.supportsMep( mep );
				if( invocable1 && ! invocable2 )
					return -1;

				if( invocable2 && ! invocable1 )
					return 1;

				// Names
				String s1 = o1.getServiceName() != null ? o1.getServiceName().getLocalPart() : "";
				s1 = s1 == null ? "" : s1;

				String s2 = o2.getServiceName() != null ? o2.getServiceName().getLocalPart() : "";
				s2 = s2 == null ? "" : s2;

				return s1.compareTo( s2 );
			}
		});

		dlg.setFilter( "*" );
		dlg.setSize( 120, 20 );
		dlg.setMultipleSelection( false );
		dlg.setTitle( "Service Selection" );
		dlg.setMessage( "Select the service to consume." );
		dlg.setElements( edpts.toArray( new Object[ edpts.size()]));

		EndpointBean result = null;
		if( dlg.open() == Window.OK )
			result = (EndpointBean) dlg.getResult()[ 0 ];

		lp.dispose();
		return result;
	}


	/**
	 * Gets a list of potential operations for a consume section.
	 * <p>
	 * This method uses the Petals services that are registered in the associated view.
	 * For each of them, it checks if the provided end-point matches the consume parameters.
	 * If so, it gets the operations, either from the WSDL, or from the default component operations.
	 * </p>
	 * <p>
	 * Note that ambiguities are not managed by this method (i.e. if there are two operations with
	 * the same name but with different MEP).
	 * </p>
	 *
	 * @param itf the interface name (can be null)
	 * @param srv the service name (can be null)
	 * @param edpt the end-point name (can be null)
	 * @return a non-null map of operations - MEP
	 */
	public static Map<QName,Mep> getValidOperationsForConsume( QName itf, QName srv, String edpt ) {

		List<EndpointBean> beans = new ArrayList<EndpointBean> ();
		Collection<EndpointSource> edptSources = SourceManager.getInstance().getSources();
		for( EndpointSource source : edptSources ) {

			Collection<ServiceUnitBean> suBeans = source.getServiceUnits();
			for( ServiceUnitBean suBean : suBeans )
				beans.addAll( suBean.getEndpoints());
		}


		// Hack for KPI
		EndpointBean kpiBean = new EndpointBean();
		kpiBean.setInterfaceName( new QName( "http://docs.oasis-open.org/wsn/br-2", "NotificationBroker" ));
		kpiBean.setServiceName( new QName( "http://petals.ow2.org/petals-se-notification", "NotificationBrokerService" ));
		kpiBean.setEndpointName( PetalsConstants.AUTO_GENERATE );
		beans.add( kpiBean );

		// Get the right providers
		List<EndpointBean> filteredBeans = new ArrayList<EndpointBean> ();
		for( EndpointBean edptBean : beans ) {

			// EDPT bean cannot have null values
			if( itf != null
					&& edptBean.getInterfaceName() != null
					&& ! itf.equals( edptBean.getInterfaceName()))
				continue;

			if( srv != null
					&& edptBean.getServiceName() != null
					&& ! srv.equals( edptBean.getServiceName()))
				continue;

			if( edpt != null
					&& edptBean.getEndpointName() != null
					&& ! edpt.equals( edptBean.getEndpointName()))
				continue;

			filteredBeans.add( edptBean );
		}

		// Now, get the operations and MEP
		return getOperations( filteredBeans );
	}


	/**
	 * Gets a list of operations for Petals services.
	 * <p>
	 * Note that ambiguities are not managed by this method (i.e. if there are two operations with
	 * the same name but with different MEP).
	 * </p>
	 *
	 * @param beans a list of end-point beans
	 * @return a non-null map of operations - MEP
	 * @see EndpointBean#getOperationNameToMep()
	 */
	public static Map<QName,Mep> getOperations( List<EndpointBean> beans ) {

		Map<QName,Mep> result = new HashMap<QName,Mep> ();
		for( EndpointBean endpointBean : beans ) {
			result.putAll( endpointBean.getOperationNameToMep());
		}

		return result;
	}


	/**
	 * Determines whether this service has at least one operation that supports this invocation MEP.
	 * @param mep an invocation MEP
	 * @param operationsToMep the map (operation = mep) - not null
	 * @return true if there is at least one operation that supports this MEP, false otherwise
	 */
	public static boolean supportsMep( Map<QName,Mep> operationsToMep, Mep mep ) {
		return mep == Mep.UNKNOWN || operationsToMep.values().contains( mep );
	}


	/**
	 * Gets a list of operations for a service that is described by these parameters.
	 * <p>
	 * Note that ambiguities are not managed by this method (i.e. if there are two operations with
	 * the same name but with different MEP).
	 * </p>
	 *
	 * @param wsdlUri the WSDL URI (can be null)
	 * @param itfName the interface name
	 * @param itfNs the interface name space
	 * @param srvName the service name
	 * @param srvNs the service name space
	 * @param edptName the end-point name
	 * @param targetComponent the target component (on which component is deployed this end-point)
	 * @param componentVersion the component version
	 * @return a non-null map of operations - MEP
	 */
	public static Map<QName,Mep> getOperations(
			URI wsdlUri,
			String itfName, String itfNs,
			String srvName, String srvNs,
			String edptName,
			String targetComponent,
			String componentVersion ) {

		Map<QName,Mep> result = new HashMap<QName,Mep> ();

		// WSDL operations
		if( wsdlUri != null ) {
			result.putAll( WsdlUtils.INSTANCE.getOperations(
					wsdlUri,
					itfName, itfNs,
					srvName, srvNs,
					edptName ));

		} else if( targetComponent != null ) {
			// Are there any default operation?
			result.putAll( ExtensionManager.INSTANCE.findDefaultOperations( targetComponent, componentVersion ));
		}

		// Hack for the Talend SE
		if( "petals-se-talend".equals( targetComponent )) {
			QName opToHack = null;
			for( Map.Entry<QName,Mep> entry : result.entrySet()) {
				if( "executeJobOnly".equals( entry.getKey().getLocalPart())) {
					opToHack = entry.getKey();
					break;
				}
			}

			if( opToHack != null )
				result.put( opToHack, Mep.IN_ONLY );
		}

		return result;
	}
}
