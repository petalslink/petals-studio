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
package com.ebmwebsourcing.petals.services.eip.designer.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean;
import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipConsumes25;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25Aggregator;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25Bridge;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25Dispatcher;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25DynamicRouter;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25Router;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25RoutingSlip;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25ScatterGather;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25Splitter;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25.EipProvides25WireTap;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipExportUtils {

	/**
	 * Creates the content of a jbi.xml for the SU associated with an EIP node.
	 * @param eip the EIP whose properties must be reported in the jbi.xml
	 * @param newWsdlLocation the updated WSDL location to set in the jbi.xml
	 * @return the string representation of the jbi.xml
	 */
	public static String createJbiXmlContent( EipNode eip, String newWsdlLocation ) {

		// Create the jbi.xml file
		CdkProvides5 jbiXmlBean = null;
		switch( eip.getEipType()) {
		case AGGREGATOR:
			jbiXmlBean = new EipProvides25Aggregator();
			((EipProvides25Aggregator) jbiXmlBean).setTest( eip.getProperties().get( EipProperty.TEST ));
			((EipProvides25Aggregator) jbiXmlBean).setAggregatorCorrelation( eip.getProperties().get( EipProperty.AGGREGATOR_CORRELATION ));
			break;

		case BRIDGE:
			jbiXmlBean = new EipProvides25Bridge();
			boolean b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_TO_EXCEPTION ));
			((EipProvides25Bridge) jbiXmlBean).setFaultToException( b );
			break;

		case DISPATCHER:
			jbiXmlBean = new EipProvides25Dispatcher();
			break;

		case DYNAMIC_ROUTER:
			jbiXmlBean = new EipProvides25DynamicRouter();
			b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_TO_EXCEPTION ));
			((EipProvides25DynamicRouter) jbiXmlBean).setFaultToException( b );

			for( EipConnection conn : eip.getOutgoingConnections()) {
				if( conn.shouldHaveCondition())
					((EipProvides25DynamicRouter) jbiXmlBean).addRoutingCondition( conn.getConditionExpression());
			}
			break;

		case ROUTER:
			jbiXmlBean = new EipProvides25Router();
			b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_TO_EXCEPTION ));
			((EipProvides25Router) jbiXmlBean).setFaultToException( b );

			String routingCriteria = eip.getProperties().get( EipProperty.ROUTING_CRITERIA );
			boolean isXpath = routingCriteria == null
					|| EipProperty.ROUTING_CRITERIA_BY_CONTENT.equals( routingCriteria );
			((EipProvides25Router) jbiXmlBean).setXpathCondition( isXpath );

			for( EipConnection conn : eip.getOutgoingConnections()) {
				if( conn.shouldHaveCondition())
					((EipProvides25Router) jbiXmlBean).addRoutingCondition( conn.getConditionExpression());
			}
			break;

		case ROUTING_SLIP:
			jbiXmlBean = new EipProvides25RoutingSlip();
			b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_TO_EXCEPTION ));
			((EipProvides25RoutingSlip) jbiXmlBean).setFaultToException( b );
			break;

		case SCATTER_GATHER:
			jbiXmlBean = new EipProvides25ScatterGather();
			b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_ROBUST ));
			((EipProvides25ScatterGather) jbiXmlBean).setFaultRobust(  b );

			b = Boolean.valueOf( eip.getProperties().get( EipProperty.EXCEPTION_ROBUST ));
			((EipProvides25ScatterGather) jbiXmlBean).setExceptionRobust( b );
			break;

		case SPLITTER:
			jbiXmlBean = new EipProvides25Splitter();
			((EipProvides25Splitter) jbiXmlBean).setTest( eip.getProperties().get( EipProperty.TEST ));

			b = Boolean.valueOf( eip.getProperties().get( EipProperty.FAULT_ROBUST ));
			((EipProvides25Splitter) jbiXmlBean).setFaultRobust(  b );

			b = Boolean.valueOf( eip.getProperties().get( EipProperty.EXCEPTION_ROBUST ));
			((EipProvides25Splitter) jbiXmlBean).setExceptionRobust( b );

			b = Boolean.valueOf( eip.getProperties().get( EipProperty.ATTACHMENT ));
			((EipProvides25Splitter) jbiXmlBean).setAttachment( b );

			break;

		case WIRETAP:
			jbiXmlBean = new EipProvides25WireTap();
			String s = eip.getProperties().get( EipProperty.WIRETAP_WAY );
			((EipProvides25WireTap) jbiXmlBean).setWireTapWay( s != null ? s.toLowerCase() : null );
			break;
		}

		if( jbiXmlBean == null ) {
			String msg = "An EIP pattern was not recognized and failed to be exported: " + eip.getEipType();
			PetalsEipPlugin.log( msg, IStatus.ERROR );
			throw new NullPointerException( msg );
		}


		// Update the properties
		jbiXmlBean.setEndpointName( eip.getEndpointName());
		jbiXmlBean.setServiceName( eip.getServiceName());
		jbiXmlBean.setServiceNamespace( eip.getServiceNamespace());
		jbiXmlBean.setInterfaceName( eip.getInterfaceName());
		jbiXmlBean.setInterfaceNamespace( eip.getInterfaceNamespace());
		jbiXmlBean.setWsdl( newWsdlLocation );


		// Create the consume sections
		List<AbstractJbiXmlBean> beans = new ArrayList<AbstractJbiXmlBean> ();
		beans.add( jbiXmlBean );
		for( EipConnection conn : eip.getOutgoingConnections()) {

			if( conn.getTarget() == null )
				continue;

			EipConsumes25 consumeBean = new EipConsumes25();
			if( conn.isConsumeItf()) {
				consumeBean.setInterfaceName( conn.getTarget().getInterfaceName());
				consumeBean.setInterfaceNamespace( conn.getTarget().getInterfaceNamespace());
			}

			if( conn.isConsumeSrvPossible() && conn.isConsumeSrv()) {
				consumeBean.setServiceName( conn.getTarget().getServiceName());
				consumeBean.setServiceNamespace( conn.getTarget().getServiceNamespace());
			}

			if( conn.isConsumeEdptPossible() && conn.isConsumeEdpt()) {
				consumeBean.setEndpointName( conn.getTarget().getEndpointName());
			}

			consumeBean.setMep( Mep.whichMep( conn.getConsumeMep()));
			if( conn.getConsumeOperation() != null
						&& NamespaceUtils.isShortenNamespace( conn.getConsumeOperation())) {
				QName operation = NamespaceUtils.buildQName( conn.getConsumeOperation());
				consumeBean.setOperation( operation );
			}

			beans.add( consumeBean );
		}


		// Generate the jbi.xml
		AbstractJbiXmlBean[] _beans = new AbstractJbiXmlBean[ beans.size()];
		_beans = beans.toArray( _beans );

		JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
		genDelegate.setComponentName( "petals-se-eip" );
		return genDelegate.createJbiDescriptor( _beans ).toString();
	}
}
