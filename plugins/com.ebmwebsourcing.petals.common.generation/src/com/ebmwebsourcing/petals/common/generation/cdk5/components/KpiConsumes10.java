/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import java.io.File;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.generation.cdk5.CdkConsumes5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiConsumes10 extends CdkConsumes5 {

	private QName consumedInterface;
	private QName consumedService;
	private String consumedEndpoint;
	private File xslFile;
	private FlowType flow;
	private String xpathExpression;


	/**
	 * Constructor.
	 */
	public KpiConsumes10() {
		super();

		addNamespace( "kpi", "http://petals.ow2.org/petals-se-kpi" );
		addNamespace( "notification", "http://petals.ow2.org/petals-se-notification" );
		addNamespace( "wsn-br", "http://docs.oasis-open.org/wsn/br-2" );
		addNamespace( "st", "http://www.ebmwebsourcing.com/wsnotification/specificTypes" );

		setOperation( new QName( "Subscribe" ));
		setMep( Mep.IN_OUT );
		setInterfaceName( "NotificationBroker" );
		setInterfaceNamespace( "http://docs.oasis-open.org/wsn/br-2" );
		setServiceName( "NotificationBrokerService" );
		setServiceNamespace( "http://petals.ow2.org/petals-se-notification" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuilder sb = new StringBuilder();
		sb.append( "\t\t\t<!-- Subscription to process on init : -->\n" );
		sb.append( "\t\t\t<kpi:TopicExpressionDialect>http://docs.oasis-open.org/wsn/t-1/TopicExpression/Full" );
		sb.append( "</kpi:TopicExpressionDialect>\n");
		sb.append( "\t\t\t<kpi:TopicExpressionNamespace>http://petals.ow2.org/topic</kpi:TopicExpressionNamespace>\n" );
		sb.append( "\t\t\t<kpi:TopicExpressionPrefix>petals</kpi:TopicExpressionPrefix>\n" );
		sb.append( "\t\t\t<kpi:TopicExpressionContent>petals:component/cdk/producer/" + this.flow + "[@wstop:topic='true']" );
		sb.append( "</kpi:TopicExpressionContent>\n\n" );

		// Specify part
		sb.append( "\t\t\t<!-- Use the SOA dialect, or the XPath dialect, or both of them -->\n\n" );

		// SOA dialect
		if( this.consumedInterface == null
					&& this.consumedService == null )
			sb.append( "\t\t\t<!--\n" );

		sb.append( "\t\t\t<kpi:MessageContentSOADialect>" );
		sb.append( "http://www.ebmwebsourcing.com/wsnotification/soaparameter</kpi:MessageContentSOADialect>\n" );

		sb.append( "\t\t\t<kpi:MessageContentSOAInterfaceNamespace>" );
		if( this.consumedInterface != null )
			sb.append( this.consumedInterface.getNamespaceURI());
		sb.append( "</kpi:MessageContentSOAInterfaceNamespace>\n" );

		sb.append( "\t\t\t<kpi:MessageContentSOAInterfaceName>" );
		if( this.consumedInterface != null )
			sb.append( this.consumedInterface.getLocalPart());
		sb.append( "</kpi:MessageContentSOAInterfaceName>\n" );

		sb.append( "\t\t\t<kpi:MessageContentSOAServiceNamespace>" );
		if( this.consumedService != null )
			sb.append( this.consumedService.getNamespaceURI());
		sb.append( "</kpi:MessageContentSOAServiceNamespace>\n" );

		sb.append( "\t\t\t<kpi:MessageContentSOAServiceName>" );
		if( this.consumedService != null )
			sb.append( this.consumedService.getLocalPart());
		sb.append( "</kpi:MessageContentSOAServiceName>\n" );

		if( this.consumedEndpoint != null
					&& this.consumedEndpoint.trim().length() > 0
					&& ! JbiXmlGenerationHelper.AUTO_GENERATE.equals( this.consumedEndpoint )) {
			sb.append( "\t\t\t<kpi:MessageContentSOAEndpoint>" );
			sb.append( this.consumedEndpoint );
			sb.append( "</kpi:MessageContentSOAEndpoint>\n" );
		}

		if( this.consumedInterface == null
					&& this.consumedService == null )
			sb.append( "\t\t\t-->\n" );

		sb.append( "\n" );

		// XPath dialect
		if( this.xpathExpression == null )
			sb.append( "\t\t\t<!--\n" );

		sb.append( "\t\t\t<kpi:MessageContentXpathDialect>" ) ;
		sb.append( "http://www.w3.org/TR/1999/REC­xpath­19991116</kpi:MessageContentXpathDialect>\n" );
		sb.append( "\t\t\t<kpi:MessageContentXpath>" + (this.xpathExpression != null ? this.xpathExpression : "") + "</kpi:MessageContentXpath>\n" );

		if( this.xpathExpression == null )
			sb.append( "\t\t\t-->\n" );

		sb.append( "\n\t\t\t<kpi:TransformPolicy>" );
		if( this.xslFile != null )
			sb.append( this.xslFile.getName());
		sb.append( "</kpi:TransformPolicy>\n" );

		// Default parts
		sb.append( "\t\t\t<kpi:ContextPolicyCorrelationId>true</kpi:ContextPolicyCorrelationId>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyInterface>true</kpi:ContextPolicyInterface>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyEndpoint>true</kpi:ContextPolicyEndpoint>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyMeuuid>true</kpi:ContextPolicyMeuuid>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyNotifDate>true</kpi:ContextPolicyNotifDate>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyService>true</kpi:ContextPolicyService>\n" );
		sb.append( "\t\t\t<kpi:ContextPolicyStatus>true</kpi:ContextPolicyStatus>\n" );
		sb.append( "\t\t\t<kpi:ProcessPolicyAction>create</kpi:ProcessPolicyAction>" );

		return sb.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "KPI";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean#isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}


	/**
	 * @param consumedInterface the consumedInterface to set
	 */
	public void setConsumedInterface( QName consumedInterface ) {
		this.consumedInterface = consumedInterface;
	}


	/**
	 * @param consumedService the consumedService to set
	 */
	public void setConsumedService( QName consumedService ) {
		this.consumedService = consumedService;
	}


	/**
	 * @param consumedEndpoint the consumedEndpoint to set
	 */
	public void setConsumedEndpoint( String consumedEndpoint ) {
		this.consumedEndpoint = consumedEndpoint;
	}


	/**
	 * Set the XSL style sheet file.
	 * <p>
	 * This file will be automatically added to the list of files to import.
	 * </p>
	 * 
	 * @param xslFile the xslFile to set
	 */
	public void setXslFile( File xslFile ) {

		this.xslFile = xslFile;
		if( xslFile != null )
			putFileToImport( xslFile.getName(), xslFile );
	}


	/**
	 * @param flow the flow to set
	 */
	public void setFlow( FlowType flow ) {
		this.flow = flow;
	}


	/**
	 * @param xpathExpression the xpathExpression to set
	 */
	public void setXpathExpression( String xpathExpression ) {
		this.xpathExpression = xpathExpression;
	}


	/**
	 * The flow types.
	 */
	public enum FlowType {
		in, out, fault, status
	}
}
