package com.ebmwebsourcing.commons.jbi.internal.generated;

import java.util.Map;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SuBean;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;

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
  * @author Vincent Zurczak - EBM WebSourcing 
  */
public class SuJbiXmlTemplate {

  protected static String nl;
  public static synchronized SuJbiXmlTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    SuJbiXmlTemplate result = new SuJbiXmlTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<!-- " + NL + "\tJBI descriptor for the Petals' \"";
  protected final String TEXT_2 = "\" component (";
  protected final String TEXT_3 = ")." + NL + "\tOriginally created for the version ";
  protected final String TEXT_4 = " of the component." + NL + " -->" + NL + "<jbi:jbi version=\"1.0\" ";
  protected final String TEXT_5 = ">" + NL + "\t" + NL + "\t<!-- Import a Service into Petals or Expose a Petals Service => use a BC. -->" + NL + "\t<jbi:services binding-component=\"";
  protected final String TEXT_6 = "\">" + NL + "\t";
  protected final String TEXT_7 = NL + "\t\t<!-- Import a Service into Petals => provides a Service. -->" + NL + "\t\t<jbi:provides \t\t" + NL + "\t\t\tinterface-name=\"";
  protected final String TEXT_8 = "\"" + NL + "\t\t\tservice-name=\"";
  protected final String TEXT_9 = "\"" + NL + "\t\t\tendpoint-name=\"";
  protected final String TEXT_10 = "\">";
  protected final String TEXT_11 = NL + "\t\t<!-- Expose a Petals Service => consumes a Service. -->" + NL + "\t\t<jbi:consumes" + NL + "\t\t\tinterface-name=\"";
  protected final String TEXT_12 = "\"";
  protected final String TEXT_13 = NL + "\t\t\tservice-name=\"";
  protected final String TEXT_14 = "\"";
  protected final String TEXT_15 = NL + "\t\t\tendpoint-name=\"";
  protected final String TEXT_16 = "\"";
  protected final String TEXT_17 = ">";
  protected final String TEXT_18 = NL + "\t" + NL + "\t\t\t<!-- CDK specific elements -->";
  protected final String TEXT_19 = NL;
  protected final String TEXT_20 = NL + "\t\t" + NL + "\t\t\t<!-- Component specific elements -->\t";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = "\t\t\t" + NL + "\t\t</jbi:provides>";
  protected final String TEXT_23 = NL + "\t\t</jbi:consumes>";
  protected final String TEXT_24 = NL + "\t</jbi:services>" + NL + "</jbi:jbi>";

	 public String generate( Object argument )
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2008-2009, EBM WebSourcing
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 **/

     
	SuBean suBean = (SuBean) argument;

    stringBuffer.append(TEXT_1);
    stringBuffer.append( suBean.getComponentName() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( suBean.getSuType() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( suBean.getComponentVersion() );
    stringBuffer.append(TEXT_4);
     
	for( Map.Entry<String, String> entry : suBean.getComputedNamespaces().entrySet()) { 
		
    stringBuffer.append( "\n\txmlns:" + entry.getKey() + "=\"" + entry.getValue() + "\"" );
     
	} 
	
    stringBuffer.append(TEXT_5);
    stringBuffer.append( suBean.isBc() );
    stringBuffer.append(TEXT_6);
     	
	if( !suBean.isConsume()) { 
	
    stringBuffer.append(TEXT_7);
    stringBuffer.append( (suBean.sameNsForInterfaceAndService() ? SuBean.GENERATED_NS : SuBean.INTERFACE_NS) + ":" + suBean.getInterfaceName() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( (suBean.sameNsForInterfaceAndService() ? SuBean.GENERATED_NS : SuBean.SERVICE_NS) + ":" + suBean.getServiceName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append( suBean.getEndpointName() );
    stringBuffer.append(TEXT_10);
    
	} else { 
	
    stringBuffer.append(TEXT_11);
    stringBuffer.append( (suBean.sameNsForInterfaceAndService() ? SuBean.GENERATED_NS : SuBean.INTERFACE_NS) + ":" + suBean.getInterfaceName() );
    stringBuffer.append(TEXT_12);
     
			if( ! JbiNameFormatter.PETALS_AUTO_GENERATE_ENDPOINT.equals( suBean.getEndpointName())) {
				if( suBean.getServiceName() != null && !"".equals( suBean.getServiceName())) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append( (suBean.sameNsForInterfaceAndService() ? SuBean.GENERATED_NS : SuBean.SERVICE_NS) + ":" + suBean.getServiceName() );
    stringBuffer.append(TEXT_14);
    
				} 
				if( suBean.getEndpointName() != null && !"".equals( suBean.getEndpointName())) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append( suBean.getEndpointName() );
    stringBuffer.append(TEXT_16);
    
				}
			}
		
    stringBuffer.append(TEXT_17);
    
	}		
	
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append( suBean.getCdkElementsAsString() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append( suBean.getSpecificsElementsAsString() );
     	
	if( !suBean.isConsume()) { 
	
    stringBuffer.append(TEXT_22);
    
	} else { 
	
    stringBuffer.append(TEXT_23);
     
	} 
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}