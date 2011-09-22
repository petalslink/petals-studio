package com.ebmwebsourcing.petals.services.filetransfer.generated;

import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

/**
  * This class was generated by EMF JET. 
  * <b>DO NOT MODIFY IT!</b>
  * @author Adrien LOUIS - EBM WebSourcing 
  */
public class FileTransferService {

  protected static String nl;
  public static synchronized FileTransferService create(String lineSeparator)
  {
    nl = lineSeparator;
    FileTransferService result = new FileTransferService();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<wsdl:definitions" + NL + "\txmlns:tns=\"http://petals.ow2.org/components/filetransfer/version-3\"" + NL + "\txmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" " + NL + "\txmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\tname=\"FileTransfer\" " + NL + "\ttargetNamespace=\"http://petals.ow2.org/components/filetransfer/version-3\"" + NL + "\txmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\">" + NL + "" + NL + "\t<wsdl:types>" + NL + "\t\t<xsd:schema" + NL + "\t\t\txmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + NL + "\t\t\telementFormDefault=\"qualified\"" + NL + "\t\t\ttargetNamespace=\"http://petals.ow2.org/components/filetransfer/version-3\">" + NL + "" + NL + "\t\t\t<xsd:element name=\"get\" type=\"tns:getType\"/>" + NL + "\t\t\t<xsd:complexType name=\"getType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" maxOccurs=\"1\" minOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t" + NL + "\t\t\t<xsd:element name=\"getResponse\" type=\"tns:getResponseType\" />" + NL + "\t\t\t<xsd:complexType name=\"getResponseType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:any minOccurs=\"0\" maxOccurs=\"unbounded\" namespace=\"##other\" processContents=\"lax\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"getAsAttachment\" type=\"tns:getType\" />" + NL + "\t\t\t<xsd:element name=\"getAsAttachmentResponse\" type=\"tns:getAsAttachmentResponse\" />" + NL + "\t\t\t<xsd:complexType name=\"getAsAttachmentResponse\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"attachment\" type=\"tns:attachmentType\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mget\" type=\"tns:mgetType\"/>" + NL + "\t\t\t<xsd:complexType name=\"mgetType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" maxOccurs=\"unbounded\" minOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mgetResponse\" type=\"tns:mgetResponse\"/>" + NL + "\t\t\t" + NL + "\t\t\t<xsd:complexType name=\"mgetResponse\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"attachments\" type=\"tns:attachmentsType\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"put\" type=\"tns:putRequestType\" />" + NL + "\t\t\t<xsd:complexType name=\"putRequestType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:any />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"mput\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"attachments\" type=\"tns:attachmentsType\" minOccurs=\"1\" maxOccurs=\"1\" />" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "" + NL + "\t\t\t<xsd:element name=\"dir\"/>" + NL + "\t\t\t" + NL + "\t\t\t<xsd:element name=\"dirResponse\">" + NL + "\t\t\t\t<xsd:complexType>" + NL + "\t\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" maxOccurs=\"unbounded\" minOccurs=\"0\" />" + NL + "\t\t\t\t\t</xsd:sequence>" + NL + "\t\t\t\t</xsd:complexType>" + NL + "\t\t\t</xsd:element>" + NL + "" + NL + "\t\t\t<xsd:element name=\"del\" type=\"tns:delType\"/>" + NL + "\t\t\t<xsd:complexType name=\"delType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" minOccurs=\"1\" maxOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<xsd:element name=\"checkFile\" type=\"tns:checkFileType\" />" + NL + "\t\t\t<xsd:complexType name=\"checkFileType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" minOccurs=\"1\" maxOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t<xsd:element name=\"checkFileResponse\" type=\"tns:checkFileResponse\" />" + NL + "\t\t\t<xsd:complexType name=\"checkFileResponse\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:string\" maxOccurs=\"1\" minOccurs=\"1\" />" + NL + "\t\t\t\t\t<xsd:element name=\"exist\" maxOccurs=\"1\" minOccurs=\"1\" type=\"xsd:boolean\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<!-- Attachments -->" + NL + "\t\t\t<xsd:complexType name=\"attachmentsType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:base64Binary\" maxOccurs=\"unbounded\" minOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t<xsd:complexType name=\"attachmentType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"filename\" type=\"xsd:base64Binary\" maxOccurs=\"1\" minOccurs=\"1\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "" + NL + "\t\t\t<!-- Fault type -->" + NL + "\t\t\t<xsd:element name=\"ioFault\" type=\"tns:ioFaultType\" />" + NL + "\t\t\t<xsd:complexType name=\"ioFaultType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"message\" type=\"xsd:string\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t\t" + NL + "\t\t\t<xsd:element name=\"missingElementFault\" type=\"tns:missingElementFaultType\" />" + NL + "\t\t\t<xsd:complexType name=\"missingElementFaultType\">" + NL + "\t\t\t\t<xsd:sequence>" + NL + "\t\t\t\t\t<xsd:element name=\"message\" type=\"xsd:string\" />" + NL + "\t\t\t\t</xsd:sequence>" + NL + "\t\t\t</xsd:complexType>" + NL + "\t\t</xsd:schema>" + NL + "\t</wsdl:types>" + NL + "" + NL + "\t<!-- Message Part -->" + NL + "\t<wsdl:message name=\"getRequest\">" + NL + "\t\t<wsdl:part name=\"getRequest\" element=\"tns:get\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getResponse\">" + NL + "\t\t<wsdl:part name=\"getResponse\" element=\"tns:getResponse\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mgetRequest\">" + NL + "\t\t<wsdl:part name=\"mgetRequest\" element=\"tns:mget\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mgetResponse\">" + NL + "\t\t<wsdl:part name=\"mgetResponse\" element=\"tns:mgetResponse\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"putRequest\">" + NL + "\t\t<wsdl:part name=\"putRequest\" element=\"tns:put\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mputRequest\">" + NL + "\t\t<wsdl:part name=\"mputRequest\" element=\"tns:mput\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dirRequest\">" + NL + "\t\t<wsdl:part name=\"dirRequest\" element=\"tns:dir\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dirResponse\">" + NL + "\t\t<wsdl:part name=\"dirResponse\" element=\"tns:dirResponse\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"delRequest\">" + NL + "\t\t<wsdl:part name=\"delRequest\" element=\"tns:del\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachmentRequest\">" + NL + "\t\t<wsdl:part name=\"getAsAttachmentRequest\" element=\"tns:getAsAttachment\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachmentResponse\">" + NL + "\t\t<wsdl:part name=\"getAsAttachmentResponse\" element=\"tns:getAsAttachmentResponse\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"checkFileRequest\">" + NL + "\t\t<wsdl:part name=\"checkFileRequest\" element=\"tns:checkFile\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"checkFileResponse\">" + NL + "\t\t<wsdl:part name=\"checkFileResponse\" element=\"tns:checkFileResponse\"/>" + NL + "\t</wsdl:message>" + NL + "" + NL + "\t<!-- FAULT message section -->" + NL + "\t<wsdl:message name=\"get_ioFaultMsg\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"get_MissEltFault\">" + NL + "\t\t<wsdl:part name=\"missingElementFault\" element=\"tns:missingElementFault\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mGet_ioFaultMsg\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"mGet_MissEltFault\">" + NL + "\t\t<wsdl:part name=\"missingElementFault\" element=\"tns:missingElementFault\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"dir_ioFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachment_ioFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"/>" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"getAsAttachment_MissEltFault\">" + NL + "\t\t<wsdl:part name=\"missingElementFault\" element=\"tns:missingElementFault\" />" + NL + "\t</wsdl:message>" + NL + "\t<wsdl:message name=\"checkFileFault\">" + NL + "\t\t<wsdl:part name=\"ioFault\" element=\"tns:ioFault\"/>" + NL + "\t</wsdl:message>" + NL + "" + NL + "\t<!-- Interface Part -->" + NL + "\t<wsdl:portType name=\"";
  protected final String TEXT_2 = "\">" + NL + "\t\t<wsdl:operation name=\"get\">" + NL + "\t\t\t<wsdl:input message=\"tns:getRequest\" />" + NL + "\t\t\t<wsdl:output message=\"tns:getResponse\" />" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:get_ioFaultMsg\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:get_MissEltFault\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"getAsAttachment\">" + NL + "\t\t\t<wsdl:input message=\"tns:getAsAttachmentRequest\"/>" + NL + "\t\t\t<wsdl:output message=\"tns:getAsAttachmentResponse\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:getAsAttachment_ioFault\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:getAsAttachment_MissEltFault\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mget\">" + NL + "\t\t\t<wsdl:input message=\"tns:mgetRequest\"/>" + NL + "\t\t\t<wsdl:output message=\"tns:mgetResponse\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:mGet_ioFaultMsg\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault1\" message=\"tns:mGet_MissEltFault\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"put\">" + NL + "\t\t\t<wsdl:input message=\"tns:putRequest\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mput\">" + NL + "\t\t\t<wsdl:input message=\"tns:mputRequest\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"dir\">" + NL + "\t\t\t<wsdl:input message=\"tns:dirRequest\"/>" + NL + "\t\t\t<wsdl:output message=\"tns:dirResponse\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:dir_ioFault\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"del\">" + NL + "\t\t\t<wsdl:input message=\"tns:delRequest\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"checkFile\">" + NL + "\t\t\t<wsdl:input message=\"tns:checkFileRequest\"/>" + NL + "\t\t\t<wsdl:output message=\"tns:checkFileResponse\"/>" + NL + "\t\t\t<wsdl:fault name=\"fault\" message=\"tns:checkFileFault\"/>" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:portType>" + NL + "" + NL + "\t<!--" + NL + "\t\tThis binding section is defined only to expose this WSDL via SOAP" + NL + "\t\tcomponent to external service consumers" + NL + "\t-->" + NL + "\t<wsdl:binding name=\"FileTransferServiceSoapBinding\"" + NL + "\t\ttype=\"tns:";
  protected final String TEXT_3 = "\">" + NL + "" + NL + "\t\t<soap:binding style=\"document\"" + NL + "\t\t\ttransport=\"http://schemas.xmlsoap.org/soap/http\" />" + NL + "\t\t<wsdl:operation name=\"get\">" + NL + "" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/get\" />" + NL + "\t\t\t<wsdl:input>" + NL + "" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t<wsdl:operation name=\"mget\">" + NL + "" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/mget\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"put\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/put\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"mput\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/mput\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"dir\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/dir\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"del\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/del\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"getAsAttachment\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/getAsAttachment\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t\t<wsdl:fault name=\"fault1\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault1\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t\t" + NL + "\t\t<wsdl:operation name=\"checkFile\">" + NL + "\t\t\t<soap:operation" + NL + "\t\t\t\tsoapAction=\"http://petals.ow2.org/components/filetransfer/version-3/checkFile\" />" + NL + "\t\t\t<wsdl:input>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:input>" + NL + "\t\t\t<wsdl:output>" + NL + "\t\t\t\t<soap:body use=\"literal\" />" + NL + "\t\t\t</wsdl:output>" + NL + "\t\t\t<wsdl:fault name=\"fault\">" + NL + "\t\t\t\t<soap:fault use=\"literal\" name=\"fault\" />" + NL + "\t\t\t</wsdl:fault>" + NL + "\t\t</wsdl:operation>" + NL + "\t</wsdl:binding>" + NL + "" + NL + "\t<!--" + NL + "\t\tThe service..." + NL + "\t-->" + NL + "\t<wsdl:service name=\"";
  protected final String TEXT_4 = "\">" + NL + "\t\t<wsdl:port name=\"";
  protected final String TEXT_5 = "\" binding=\"tns:FileTransferServiceSoapBinding\">" + NL + "\t\t\t<soap:address location=\"jbi://myFileTransferSUEndpointName\" />" + NL + "\t\t</wsdl:port>" + NL + "\t</wsdl:service>" + NL + "</wsdl:definitions>";
  protected final String TEXT_6 = NL;

	 public String generate( Object argument )
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
EclipseSuBean data = (EclipseSuBean) argument; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append( data.getInterfaceName() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( data.getInterfaceName() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( data.getServiceName() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( data.getEndpointName() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}