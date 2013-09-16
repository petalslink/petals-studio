package com.ebmwebsourcing.petals.services.sql.generated;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

/**
  * This class was generated by EMF JET. 
  * <b>DO NOT MODIFY IT!</b>
  * @author Adrien LOUIS - EBM WebSourcing 
  */
public class SqlService13 {

  protected static String nl;
  public static synchronized SqlService13 create(String lineSeparator)
  {
    nl = lineSeparator;
    SqlService13 result = new SqlService13();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + NL + "<wsdl:definitions " + NL + "\txmlns:tns=\"http://petals.ow2.org/components/sql/version-1\"" + NL + "\txmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" " + NL + "\txmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\tname=\"SQLInterface\" " + NL + "\txmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\"" + NL + "\ttargetNamespace=\"http://petals.ow2.org/components/sql/version-1\">" + NL + "" + NL + "\t<wsdl:types>" + NL + "\t\t<xsd:schema elementFormDefault=\"qualified\"" + NL + "\t\t\t\t\txmlns:tns=\"http://petals.ow2.org/components/sql/version-1\"" + NL + "\t\t\t\t\txmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\t\t\t\t\ttargetNamespace=\"http://petals.ow2.org/components/sql/version-1\">" + NL + "" + NL + "\t\t\t<xsd:element name=\"sql\" type=\"xsd:string\" />" + NL + "\t\t\t<xsd:element name=\"result\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"row\" type=\"tns:rowType\" maxOccurs=\"unbounded\" minOccurs=\"0\" />" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"rowType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"column\" type=\"tns:columnType\" maxOccurs=\"unbounded\" minOccurs=\"0\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t<xsd:attribute name=\"index\" type=\"xsd:int\" use=\"optional\" />" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t<xsd:complexType name=\"columnType\">" + NL + "\t\t\t\t<xsd:simpleContent>" + NL + "\t\t\t\t\t<xsd:extension base=\"xsd:string\">" + NL + "\t\t\t\t\t\t<xsd:attribute name=\"name\" type=\"xsd:string\" use=\"optional\" />" + NL + "\t\t\t\t\t\t<xsd:attribute name=\"type\" type=\"xsd:string\" use=\"optional\" />" + NL + "\t\t\t\t\t</xsd:extension>" + NL + "\t\t\t\t</xsd:simpleContent>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"updated\" type=\"xsd:string\" />" + NL + "\t\t\t<xsd:element name=\"storedProcedure\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"parameter\" type=\"tns:parameter\" maxOccurs=\"unbounded\" minOccurs=\"0\" />" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"parameter\">" + NL + "\t\t\t\t<xsd:simpleContent>" + NL + "\t\t\t\t\t<xsd:extension base=\"xsd:string\">" + NL + "\t\t\t\t\t\t<xsd:attribute name=\"rank\" type=\"xsd:int\" use=\"required\" />" + NL + "\t\t\t\t\t</xsd:extension>" + NL + "\t\t\t\t</xsd:simpleContent>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t<xsd:element name=\"storedProcedureResult\" type=\"xsd:string\" />" + NL + "\t\t</xsd:schema>" + NL + "\t</wsdl:types>" + NL + "" + NL + "\t<wsdl:message name=\"statementRequest\">" + NL + "\t\t<wsdl:part name=\"sql\" element=\"tns:sql\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"selectResponse\">" + NL + "\t\t<wsdl:part name=\"result\" element=\"tns:result\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"statementResponse\">" + NL + "\t\t<wsdl:part name=\"updated\" element=\"tns:updated\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"storedProcedureRequest\">" + NL + "\t\t<wsdl:part name=\"storedProcedure\" element=\"tns:storedProcedure\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"storedProcedureResponse\">" + NL + "\t\t<wsdl:part name=\"storedProcedureResult\" element=\"tns:storedProcedureResult\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"xmlStoredProcedureResponse\">" + NL + "\t\t<wsdl:part name=\"xmlStoredProcedureResult\" element=\"tns:storedProcedure\" />" + NL + "\t</wsdl:message>" + NL + "" + NL + "\t<wsdl:portType name=\"";
  protected final String TEXT_2 = "\">" + NL + "\t\t<wsdl:operation name=\"select\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:selectResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"insert\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"delete\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"update\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"insertWithResponse\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:statementResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"deleteWithResponse\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:statementResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"updateWithResponse\">" + NL + "\t\t\t<wsdl:input message=\"tns:statementRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:statementResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"storedProcedure\">" + NL + "\t\t\t<wsdl:input message=\"tns:storedProcedureRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:storedProcedureResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"XMLStoredProcedure\">" + NL + "\t\t\t<wsdl:input message=\"tns:storedProcedureRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:xmlStoredProcedureResponse\" />" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:portType>" + NL + "" + NL + "\t<!--" + NL + "\t\tThis binding section is defined only to expose this WSDL via SOAP" + NL + "\t\tcomponent to external service consumers" + NL + "\t-->" + NL + "\t<wsdl:binding name=\"SQLBinding\" type=\"tns:";
  protected final String TEXT_3 = "\">" + NL + "\t\t<soap:binding style=\"document\"" + NL + "\t\t\ttransport=\"http://schemas.xmlsoap.org/soap/http\" />" + NL + "" + NL + "\t\t<wsdl:operation name=\"select\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sql/version-1/select\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"insert\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sql/version-1/insert\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"delete\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sql/version-1/delete\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"update\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sql/version-1/update\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"insertWithResponse\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sql/version-1/insertWithResponse\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"deleteWithResponse\">" + NL + "\t\t\t<soap:operation soapAction=\"http://petals.ow2.org/components/sql/version-1/deleteWithResponse\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"updateWithResponse\">" + NL + "\t\t\t<soap:operation soapAction=\"http://petals.ow2.org/components/sql/version-1/updateWithResponse\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"storedProcedure\">" + NL + "\t\t\t<soap:operation soapAction=\"http://petals.ow2.org/components/sql/version-1/storedProcedure\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "" + NL + "\t\t<wsdl:operation name=\"XMLStoredProcedure\">" + NL + "\t\t\t<soap:operation soapAction=\"http://petals.ow2.org/components/sql/version-1/XMLStoredProcedure\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:binding>" + NL + "" + NL + "\t<wsdl:service name=\"";
  protected final String TEXT_4 = "\">" + NL + "\t\t<wsdl:port name=\"";
  protected final String TEXT_5 = "\" binding=\"tns:SQLBinding\">" + NL + "\t\t\t<soap:address location=\"jbi://mySQLSUEndpointName\" />" + NL + "\t\t</wsdl:port>" + NL + "\t</wsdl:service>" + NL + "</wsdl:definitions>";
  protected final String TEXT_6 = NL;

	 public String generate( Object argument )
  {
    final StringBuffer stringBuffer = new StringBuffer();
     AbstractEndpoint data = (AbstractEndpoint) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( data.getInterfaceName().getLocalPart() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( data.getInterfaceName().getLocalPart() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( data.getServiceName().getLocalPart() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( data.getEndpointName() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}