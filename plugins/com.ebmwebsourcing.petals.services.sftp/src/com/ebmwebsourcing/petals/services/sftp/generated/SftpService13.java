package com.ebmwebsourcing.petals.services.sftp.generated;

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
public class SftpService13 {

  protected static String nl;
  public static synchronized SftpService13 create(String lineSeparator)
  {
    nl = lineSeparator;
    SftpService13 result = new SftpService13();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + NL + "<wsdl:definitions xmlns:tns=\"http://petals.ow2.org/components/sftp/version-1\"" + NL + "\txmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\tname=\"sftpInterface\" targetNamespace=\"http://petals.ow2.org/components/sftp/version-1\"" + NL + "\txmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\">" + NL + "" + NL + "\t<wsdl:types>" + NL + "\t\t<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\t\t\telementFormDefault=\"qualified\" targetNamespace=\"http://petals.ow2.org/components/sftp/version-1\">" + NL + "" + NL + "\t\t\t<xsd:element name=\"get\" type=\"tns:getType\"></xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"getType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"0\">" + NL + "\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t<xsd:element name=\"getResponse\" type=\"tns:getResponseType\">" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"getResponseType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:any></xsd:any>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t" + NL + "\t\t\t<xsd:element name=\"getAsAttachment\" type=\"tns:getType\" />" + NL + "\t\t\t<xsd:element name=\"getAsAttachmentResponse\" type=\"tns:getAsAttachmentResponse\" />" + NL + "\t\t\t<xsd:complexType name=\"getAsAttachmentResponse\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"attachment\" type=\"tns:attachmentType\"></xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mget\" type=\"tns:mgetType\"></xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"mgetType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"unbounded\" minOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"0\"></xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mgetResponse\" type=\"tns:mgetResponse\">" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"mgetResponse\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"attachments\" type=\"tns:attachmentType\"></xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:complexType name=\"anyType\"></xsd:complexType>" + NL + "\t\t\t<xsd:element name=\"put\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"body\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "\t\t\t\t\t\t\tminOccurs=\"1\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\"" + NL + "\t\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"1\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"0\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mput\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"0\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t\t<xsd:element name=\"attachments\" type=\"tns:attachmentType\"" + NL + "\t\t\t\t\t\t\tminOccurs=\"1\" maxOccurs=\"1\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "" + NL + "\t\t\t<xsd:element name=\"dir\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\t\tmaxOccurs=\"1\" minOccurs=\"0\">" + NL + "\t\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:element name=\"dirResponse\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\"" + NL + "\t\t\t\t\t\t\tmaxOccurs=\"unbounded\" minOccurs=\"0\"></xsd:element>" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "" + NL + "\t\t\t<xsd:complexType name=\"ConnectionType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"server\" type=\"xsd:string\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"port\" type=\"xsd:string\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"user\" type=\"xsd:string\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"password\" type=\"xsd:string\" minOccurs=\"0\" maxOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"folder\" type=\"xsd:string\" minOccurs=\"0\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"1\">" + NL + "\t\t\t\t\t\t<xsd:annotation>" + NL + "\t\t\t\t\t\t\t<xsd:documentation>Folder on the FTP server to read/write" + NL + "\t\t\t\t\t\t\t\t(optional : The default value is the default directory of the" + NL + "\t\t\t\t\t\t\t\tftp user)</xsd:documentation>" + NL + "\t\t\t\t\t\t</xsd:annotation>" + NL + "\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"passphrase\" type=\"xsd:string\" minOccurs=\"0\" maxOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"private-key\" type=\"xsd:string\" minOccurs=\"0\" maxOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"overwrite\" type=\"xsd:boolean\" default=\"false\">" + NL + "\t\t\t\t\t\t<xsd:annotation>" + NL + "\t\t\t\t\t\t\t<xsd:documentation>Defined if the component have to overwrite the existing remote file. If \"false\", the file is not copied and a fault is returned to the caller saying \"The file already exists\"</xsd:documentation>" + NL + "\t\t\t\t\t\t</xsd:annotation>" + NL + "\t\t\t\t\t</xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"del\" type=\"tns:delType\">" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"delType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\"" + NL + "\t\t\t\t\t\tminOccurs=\"1\" maxOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t\t<xsd:element name=\"connection\" type=\"tns:ConnectionType\"" + NL + "\t\t\t\t\t\tminOccurs=\"0\" maxOccurs=\"1\"></xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:complexType name=\"attachmentType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:base64Binary\"" + NL + "\t\t\t\t\t\tmaxOccurs=\"unbounded\" minOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t" + NL + "\t\t\t<!-- FAULT type -->" + NL + "\t\t\t<xsd:element name=\"ioFault\" type=\"tns:ioFaultType\"></xsd:element>" + NL + "\t\t\t<xsd:complexType name=\"ioFaultType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"message\" type=\"xsd:string\"></xsd:element>" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t            <!-- configurationFault definition -->" + NL + "            <xsd:element name=\"configurationFault\" type=\"tns:configurationFaultType\"></xsd:element>" + NL + "            <xsd:complexType name=\"configurationFaultType\">" + NL + "                <xsd:sequence>" + NL + "                    <xsd:element name=\"message\" type=\"xsd:string\"></xsd:element>" + NL + "                </xsd:sequence>" + NL + "            </xsd:complexType>" + NL + "\t\t\t" + NL + "\t\t</xsd:schema>" + NL + "\t</wsdl:types>" + NL + "" + NL + "\t<!-- Message Part -->" + NL + "\t<wsdl:message name=\"getRequest\">" + NL + "\t\t<wsdl:part name=\"getRequest\" element=\"tns:get\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getResponse\">" + NL + "\t\t<wsdl:part name=\"getResponse\" element=\"tns:getResponse\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachmentRequest\">" + NL + "\t\t<wsdl:part name=\"getAsAttachment\" element=\"tns:getAsAttachment\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachmentResponse\">" + NL + "\t\t<wsdl:part name=\"getAsAttachmentResponse\" element=\"tns:getAsAttachmentResponse\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mgetRequest\">" + NL + "\t\t<wsdl:part name=\"mgetRequest\" element=\"tns:mget\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mgetResponse\">" + NL + "\t\t<wsdl:part name=\"mgetResponse\" element=\"tns:mgetResponse\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"putRequest\">" + NL + "\t\t<wsdl:part name=\"putRequest\" element=\"tns:put\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mputRequest\">" + NL + "\t\t<wsdl:part name=\"mputRequest\" element=\"tns:mput\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dirRequest\">" + NL + "\t\t<wsdl:part name=\"dirRequest\" element=\"tns:dir\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dirResponse\">" + NL + "\t\t<wsdl:part name=\"dirResponse\" element=\"tns:dirResponse\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"delRequest\">" + NL + "\t\t<wsdl:part name=\"delRequest\" element=\"tns:del\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<!-- FAULT message section -->" + NL + "\t<wsdl:message name=\"get_ioFaultMsg\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"get_ConfigurationFault\">" + NL + "\t\t<wsdl:part name=\"configurationFault\" element=\"tns:configurationFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mGet_ioFaultMsg\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mGet_ConfigurationFault\">" + NL + "\t\t<wsdl:part name=\"configurationFault\" element=\"tns:configurationFault\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dir_ioFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachment_ioFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachment_ConfigurationFault\">" + NL + "\t\t<wsdl:part name=\"configurationFault\" element=\"tns:configurationFault\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"checkFileFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "\t\t<wsdl:message name=\"dirFault1\">" + NL + "\t\t<wsdl:part name=\"configurationFault\" element=\"tns:configurationFault\"></wsdl:part>" + NL + "\t</wsdl:message>" + NL + "" + NL + "\t<!-- Interface Part -->" + NL + "\t<wsdl:portType name=\"";
  protected final String TEXT_2 = "\">" + NL + "\t\t<wsdl:operation name=\"get\">" + NL + "\t\t\t<wsdl:input message=\"tns:getRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:getResponse\" />" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:get_ioFaultMsg\"></wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:get_ConfigurationFault\"></wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"getAsAttachment\">" + NL + "\t\t\t<wsdl:input message=\"tns:getAsAttachmentRequest\"></wsdl:input>" + NL + "\t\t\t<wsdl:output message=\"tns:getAsAttachmentResponse\"></wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:getAsAttachment_ioFault\"></wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:getAsAttachment_ConfigurationFault\"></wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mget\">" + NL + "\t\t\t<wsdl:input message=\"tns:mgetRequest\"></wsdl:input>" + NL + "\t\t\t<wsdl:output message=\"tns:mgetResponse\"></wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:mGet_ioFaultMsg\"></wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:mGet_ConfigurationFault\"></wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"put\">" + NL + "\t\t\t<wsdl:input message=\"tns:putRequest\"></wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mput\">" + NL + "\t\t\t<wsdl:input message=\"tns:mputRequest\"></wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"dir\">" + NL + "\t\t\t<wsdl:input message=\"tns:dirRequest\"></wsdl:input>" + NL + "\t\t\t<wsdl:output message=\"tns:dirResponse\"></wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:dir_ioFault\"></wsdl:fault>" + NL + "            <wsdl:fault name=\"fault1\" message=\"tns:dirFault1\"></wsdl:fault>" + NL + "        </wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"del\">" + NL + "\t\t\t<wsdl:input message=\"tns:delRequest\"></wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:portType>" + NL + "" + NL + "\t<!--" + NL + "\t\tThis binding section is defined only to expose this WSDL via SOAP" + NL + "\t\tcomponent to external service consumers" + NL + "\t-->" + NL + "" + NL + "\t<wsdl:binding name=\"SFtpServiceSoapBinding\" type=\"tns:";
  protected final String TEXT_3 = "\">" + NL + "\t\t<soap:binding style=\"document\"" + NL + "\t\t\ttransport=\"http://schemas.xmlsoap.org/soap/http\" />" + NL + "\t\t<wsdl:operation name=\"get\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/get\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"getAsAttachment\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/getAsAttachment\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mget\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/mget\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"put\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/put\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mput\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/mput\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"dir\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/dir\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"del\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/sftp/version-1/del\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:binding>" + NL + "" + NL + "\t<!--" + NL + "\t\tThis section must be overridden at the SU definition and the matching" + NL + "\t\tService and" + NL + "\t-->" + NL + "    <wsdl:service name=\"";
  protected final String TEXT_4 = "\">" + NL + "\t\t<wsdl:port name=\"";
  protected final String TEXT_5 = "\" binding=\"tns:SFtpServiceSoapBinding\">" + NL + "\t\t\t<soap:address location=\"jbi://mySFTPSUEndpointName\" />" + NL + "\t\t</wsdl:port>" + NL + "\t</wsdl:service>" + NL + "</wsdl:definitions>";
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