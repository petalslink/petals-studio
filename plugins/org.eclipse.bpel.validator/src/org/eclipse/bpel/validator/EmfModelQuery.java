/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.validator;

import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.model.util.ImportResolver;
import org.eclipse.bpel.model.util.ImportResolverRegistry;
import org.eclipse.bpel.model.util.WSDLUtil;
import org.eclipse.bpel.model.util.XSDUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Part;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * 
 * This class glues the validator code to the EMF model and performs various queries
 * on the model.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Dec 13, 2006
 *
 */

@SuppressWarnings("nls")

public class EmfModelQuery {
		
	static final String CONTEXT_MSG = "The EMF context object object cannot be null";
	
	/**
	 * @param eObj
	 * @param qname
	 * @return the partner link type
	 */
	
	public static EObject lookupPartnerLinkType (EObject eObj, QName qname) {

		EObject top = getRoot ( eObj );
		
		if (top instanceof Definition) {
			return WSDLUtil.resolveBPELPartnerLinkType((Definition) top, qname);
		}
		if (top instanceof Process) {		
			return scanImports ( (Process) top, qname, WSDLUtil.BPEL_PARTNER_LINK_TYPE );
		}
		return null;
	}

	/**
	 * @param eObj
	 * @param qname
	 * @return the port type, or null if it cannot be found.
	 */
	
	public static EObject lookupPortType (EObject eObj, QName qname) {
		
		EObject top = getRoot ( eObj );	
		
		// Look within this definition
		if (top instanceof Definition) {			
			return WSDLUtil.resolvePortType((Definition) top, qname);
		}
		
		// Look within imports of process
		if (top instanceof Process) {			
			return scanImports ( (Process) top, qname, WSDLUtil.WSDL_PORT_TYPE );			
		}		
		return null;
	}

	/**
	 * Lookup the XSD type by the QName given.
	 * 
	 * @param eObj
	 * @param qname
	 * @return the XSD Type
	 */
	
	public static EObject lookupXSDType (EObject eObj, QName qname) {
		
		EObject top = getRoot ( eObj );
				
		// Look within this definition
		if (top instanceof Definition) {			
			return WSDLUtil.resolveXSDTypeDefinition((Definition) top, qname);
		}		
		// They could be in a schema as well
		if (top instanceof XSDSchema) {
			return XSDUtil.resolveTypeDefinition((XSDSchema) top, qname);
		}
		
		// Look within imports of process
		if (top instanceof Process) {			
			return scanImports ( (Process) top, qname, XSDUtil.XSD_TYPE_DEFINITION );			
		}		
		return null;
	}

	
	/**
	 * Lookup message by the QName given.
	 * 
	 * @param eObj the the object to query
	 * @param qname
	 * @return the message EOBject
	 */
	
	public static EObject lookupMessage (EObject eObj, QName qname) {
		
		EObject top = getRoot ( eObj );
		
		// Look within this definition
		if (top instanceof Definition) {			
			return WSDLUtil.resolveMessage( (Definition) top, qname);
		}
				
		// Look within imports of process
		if (top instanceof Process) {			
			return scanImports ( (Process) top, qname, WSDLUtil.WSDL_MESSAGE );			
		}
		return null;	
	}
	

	/**
	 * 
	 * @param eObj the reference context object.
	 * @param qname the QName of the declaration.
	 * @return the XSDElement for for the given name or null if it does not exist
	 */
	
	public static EObject lookupXSDElement(EObject eObj, QName qname) {
		
		EObject top = getRoot(eObj);		
		
		// Look within this definition
		if (top instanceof Definition) {			
			return WSDLUtil.resolveXSDElementDeclaration((Definition) top, qname);
		}
		// They could be in a schema as well
		if (top instanceof XSDSchema) {
			return XSDUtil.resolveElementDeclaration((XSDSchema) top, qname);
		}
		// Look within imports of process
		if (top instanceof Process) {			
			return scanImports ( (Process) top, qname, XSDUtil.XSD_ELEMENT_DECLARATION );			
		}		
		return null;
	}

	
	/**
	 * Since roles are part of a partner link type, the context object in this
	 * case must be the partner link type object.
	 *  
	 * @param eObj
	 * @param name
	 * @return the role object.
	 */
	
	
	@SuppressWarnings("nls")
	public static EObject lookupRole (EObject eObj, String name) {
		assertTrue(eObj != null, "Context object cannot be null");
		
		if (eObj instanceof PartnerLink) {
			eObj = ((PartnerLink)eObj).getPartnerLinkType();
		}
		
		if (eObj instanceof PartnerLinkType) {			
			return WSDLUtil.findRole ( (PartnerLinkType) eObj , name );
		}
		
		return null;
	}



	/**
	 * 
	 * @param eObj the context object.
	 * @param name the name of the operation.
	 * @return the operation or null
	 */
	
	@SuppressWarnings("nls")
	public static EObject lookupOperation (EObject eObj, String name) {
		
		assertTrue(eObj != null, CONTEXT_MSG);
		
		// partner link -> partner link type
		if (eObj instanceof PartnerLink) {
			eObj = ((PartnerLink) eObj).getPartnerLinkType();			
		}

		// if partner link type -> it has to be an operation on one of the roles
		// 
		if (eObj instanceof PartnerLinkType) {			
			PartnerLinkType plt = (PartnerLinkType) eObj;
			// This is a bit screwy. The partner link can have potentially 2 roles.
			for(Object n : plt.getRole()) {
				Role r = (Role) n;			
				eObj = lookupOperation(r, name);
				if (eObj != null) {
					return eObj;
				}
			}					
		}
		
		if (eObj instanceof Role) {
			return lookupOperation((Role) eObj, name);
		}
		return null;
	}
	
	
	
	static EObject lookupOperation (Role role, String name) {
		
		// a role has the port type which must have the operation		
		PortType portType = (PortType) role.getPortType();				
		return WSDLUtil.findOperation(portType,name);				
	}
	

	
	/**
	 * @param eObj
	 * @param qname
	 * @param axis the axis to use
	 * @return the reference to the name step lookup.
	 */
	
	public static EObject lookupNameStep (EObject eObj, QName qname, int axis ) {
		
		assertTrue(eObj != null,CONTEXT_MSG);
		
		eObj = resolveNameStepContext (eObj);
		
		/** 
		 * This is the meat of this method. Once we have the element declaration and
		 * type we should be able to ascertain if the QName step passed is correct.
		 */
		
		if (eObj instanceof XSDElementDeclaration) {
			// This is the case where we may have something like (^^ denotes context) 
			//   $foo.payload/tns:bar/tns:foo
			//                ^^^^^^^
			XSDElementDeclaration elm = (XSDElementDeclaration) eObj;
			// check if this step is valid or not			
			eObj = elm.getType();
		}
		
		eObj = XSDUtils.getEnclosingTypeDefinition(eObj);
		
		String localName = qname.getLocalPart();
		String nsURI = qname.getNamespaceURI();
		
		if (eObj instanceof XSDComplexTypeDefinition) {
			
			XSDComplexTypeDefinition type = (XSDComplexTypeDefinition) eObj;
			
			// System.out.println("_______ Looking for: " + qname );
			// System.out.println("Type: " + type.getName() + "{" + type.getTargetNamespace() + "}");
			
			if (axis == 0) {
				// Look in the child elements
				for(Object item : XSDUtils.getChildElements(type)) {
					XSDElementDeclaration next = (XSDElementDeclaration) item;				
					// System.out.println("Element Declaration: " + next.getName() + "{" + next.getTargetNamespace() + "}");
					if (localName.equals( next.getName()) && sameNamespace(nsURI,next.getTargetNamespace())) {
						return next.getType();
					}
				}
			} else if (axis == 1) {
				
				// Look in attributes
				for(XSDAttributeDeclaration next : XSDUtils.getChildAttributes( type )) {
					if (localName.equals( next.getName()) && sameNamespace(nsURI,next.getTargetNamespace())) {
						return next.getType();
					}
				}
				
			}
			
			
		} else if (eObj instanceof XSDSimpleTypeDefinition) {
			
			XSDSimpleTypeDefinition type = (XSDSimpleTypeDefinition) eObj;
			if (localName.equals ( type.getName() ) && sameNamespace(nsURI,type.getTargetNamespace()) ) {					
				return type;
			}
			
		}
		
		return null;
	}


	
	static EObject resolveNameStepContext ( EObject eObj ) {
		
		if (eObj instanceof Variable) {
			// This is the case where we may have something like (^^ denotes context) 
			//   $foo/tns:bar
			//    ^^^
			Variable v = (Variable) eObj;
			eObj = v.getXSDElement();
			if (eObj == null) {
				eObj = v.getType();
			}
			if (eObj == null) {
				eObj = v.getMessageType();
			}
		}

		// WSDL Message
		if (eObj instanceof Message) {
			Message msg = (Message) eObj;
			List<?> parts = msg.getEParts();
			if (parts.size() == 1) {
				eObj = (Part) parts.get(0);
			}			
		}
		
		// Part of a message
		if (eObj instanceof Part) {
			// This is the case where we may have something like (^^ denotes context) 
			//   $foo.payload/tns:bar 
			//        ^^^^^^^
			Part part = (Part) eObj;
			eObj = part.getElementDeclaration();
			if (eObj == null) {
				eObj = part.getTypeDefinition();
			}			
		}

		return eObj;
	}
	
	
	/**
	 * @param eObj
	 * @param name
	 * @return the message part 
	 */
	
	@SuppressWarnings("nls")
	public static EObject lookupMessagePart (EObject eObj, String name) {
		assertTrue(eObj != null,CONTEXT_MSG);
		
		if (eObj instanceof Variable) {
			eObj = ((Variable)eObj).getMessageType();
		}		
		if (eObj instanceof Message) {						
			return WSDLUtil.findPart( (Message) eObj, name );
		}
		
		if (eObj instanceof Part) {
			return eObj;
		}
		return null;
	}

	
	/**
	 * Look up the import. Here we lookup the import to get either the schema or
	 * the WSDL definitions. If the context node is Import and the name is  
	 * @param eObj the reference object.
	 * @param name
	 * 
	 * @return the root of the model represented by this import.
	 */
	
	@SuppressWarnings("nls")
	public static EObject lookupImport (EObject eObj, String name) {
		
		assertTrue( eObj != null, CONTEXT_MSG );
		
		if (eObj instanceof Import) {
			
			Import imp = (Import) eObj;
			                         	   
    	    for (ImportResolver r : ImportResolverRegistry.INSTANCE.getResolvers(imp.getImportType()) ) {
    	    	EObject result =  r.resolve(imp,null,null,ImportResolver.TOP);
    	    	if (result != null) {
                    return result;
                }
    	    }
		}
		
		return null;
	}


	
	/**
	 * Get the root element in the resource.
	 * 
	 * @param eObj
	 * @return the root object 
	 */
	@SuppressWarnings("nls")
	static EObject getRoot ( EObject eObj ) {
	
		assertTrue(eObj != null, CONTEXT_MSG );
		EObject top = eObj;
		while (top.eContainer() != null) {
			top = top.eContainer();
		}		
		
		assertTrue( top != null, "The top object cannot be null");
		return top ;
	}
	
	/**
	 * Scan the imports and resolve the items requested.
	 * 
	 * @param process
	 * @param qname
	 * @param refType
	 * @return the object resolved by scanning the imports.
	 */
	
	static EObject scanImports (  Process process, QName qname , String refType ) {
		
		EObject result = null;
		
		for(Object n : process.getImports()) {
            Import imp = (Import) n;                                    
            if (imp.getLocation() == null ) {
            	continue;
            }
            
    	    ImportResolver[] resolvers = ImportResolverRegistry.INSTANCE.getResolvers(imp.getImportType());
    	    for(ImportResolver r : resolvers) {
                result = r.resolve(imp, qname, null, refType);
                if (result != null) {
                	return result;
                }                
            }
    	    
        } 
        
        return null;
	}


	static void assertTrue ( boolean mustBeTrue , String msg ) {
		if (!mustBeTrue) {
			throw new RuntimeException( msg );
		}
	}
	
	
	static boolean isEmpty ( String value ) {
    	return value == null || value.length() == 0;
    }

	/**
	 * Check compatible partner activity type.
	 * 
	 * @param src
	 * @param dst
	 * @return true if compatible, false if not.
	 */
	
	public static boolean compatiblePartnerActivityMessages (EObject src, EObject dst) {
		
		assertTrue(src != null, CONTEXT_MSG);
		assertTrue(dst != null, CONTEXT_MSG);
		
		Message srcMsg = null;
		Message dstMsg = null;
		
		if (src instanceof Message) {
			srcMsg = (Message) src;			
		}
		if (dst instanceof Message) {
			dstMsg = (Message) dst;
		}
		
		// both messages, and both non null - check if same.
		if (srcMsg != null && dstMsg != null) {
			return srcMsg.equals(dstMsg);
		}
		
		// either source OR destination is not a message, check for the XSDElement variant.
		
		XSDElementDeclaration srcXSD = null;
		XSDElementDeclaration dstXSD = null;
		
		if (src instanceof XSDElementDeclaration) {
			srcXSD = (XSDElementDeclaration) src;
		}
		if (dst instanceof XSDElementDeclaration) {
			dstXSD = (XSDElementDeclaration) dst;
		}
		
		// source is XSD element, destination message may have 1 part defined as that element
		if (srcXSD != null && dstMsg != null) {
			
			List<?> parts = dstMsg.getEParts();
			if (parts.size() != 1) {
				return false;
			}
			
			Part part = (Part) parts.get(0);
			return srcXSD.equals( part.getElementDeclaration() );			
		}
		
		// destination is XSD element, source message may have 1 part defined as that element
		if (dstXSD != null && srcMsg != null) {
			
			List<?> parts = srcMsg.getEParts();
			if (parts.size() != 1) {
				return false;
			}
			
			Part part = (Part) parts.get(0);
			return dstXSD.equals( part.getElementDeclaration() );						
		}
		
		// otherwise, incompatible partner activity messages
		return false;
	}

	/**
	 * Lookup property by name.
	 * 
	 * @param eObj the reference object
	 * @param qname QName the name of the property
	 * @return the property 
	 */
	public static EObject lookupProperty(EObject eObj, QName qname) {
		
		EObject top = getRoot(eObj);		
		
		// Look within this definition
		if (top instanceof Definition) {			
			return WSDLUtil.resolveBPELProperty((Definition) top, qname);
		}
		// Look within imports of process
		if (top instanceof Process) {			
			return scanImports ( (Process) top, qname, WSDLUtil.BPEL_PROPERTY );			
		}		
		return null;
	}

	/**
	 * @param src the source type
	 * @param dst the destination type
	 * @return if the types are compatible, false otherwise
	 */
	
	public static boolean compatibleType(EObject src, EObject dst) {
		assertTrue(src != null, CONTEXT_MSG);
		assertTrue(dst != null, CONTEXT_MSG);
		
		Message srcMsg = null;
		Message dstMsg = null;
		
		if (src instanceof Message) {
			srcMsg = (Message) src;			
		}
		if (dst instanceof Message) {
			dstMsg = (Message) dst;
		}
		
		// both messages, and both non null - check if same.
		if (srcMsg != null && dstMsg != null) {
			return srcMsg.equals(dstMsg);
		}
		
		// either source OR destination is not a message, check for the XSDElement variant.
		
		XSDElementDeclaration srcXSD = null;
		XSDElementDeclaration dstXSD = null;
		
		if (src instanceof XSDElementDeclaration) {
			srcXSD = (XSDElementDeclaration) src;
		}
		if (dst instanceof XSDElementDeclaration) {
			dstXSD = (XSDElementDeclaration) dst;
		}
		
		if (srcXSD != null && dstXSD != null) {
			return srcXSD.equals(dstXSD);
		}
		
		XSDTypeDefinition srcType = null;
		XSDTypeDefinition dstType = null;
		
		if (src instanceof XSDTypeDefinition) {
			srcType = (XSDTypeDefinition) src;
		}
		if (dst instanceof XSDTypeDefinition) {
			dstType = (XSDTypeDefinition) dst;
		}
		
		if (srcType != null && dstType != null) {
			
			if (srcType.equals(dstType)) {
				return true;
			}
			
			// check if src is derived from dst.
			//   1) src is NCName, dst is string --> compatible.
			//   2) reverse is not always true			
			XSDTypeDefinition baseType = srcType.getBaseType();			
			do {
				// System.out.println("Checking: " + dstType + " against baseType: " + baseType);
				if (dstType.equals(baseType)) {
					return true;
				}
				baseType = baseType.getBaseType();
			} while ( baseType.equals(baseType.getBaseType()) == false );								
		}
		
		// otherwise, incompatible partner activity messages
		return false;
	}

	/**
	 * @param eObj the object
	 * @return returns if simple type, false otherwise.
	 */
	
	public static boolean isSimpleType(EObject eObj) {
		assertTrue(eObj != null, CONTEXT_MSG);
		
		if (eObj instanceof XSDSimpleTypeDefinition) {
			return true;
		}
		return false;
	}

	/**
	 * Lookup type of the part of the message.
	 * @param eObj the reference object
	 * @param qname the part name.
	 * @return the type of the part
	 */
	
	public static EObject lookupTypeOfPart (EObject eObj, QName qname) {
		EObject obj = lookupMessagePart(eObj, qname.getLocalPart());
		if (obj == null || obj instanceof Part == false) {
			return null;
		}
		Part part = (Part) obj;
		if (part.getElementDeclaration() != null) {
			return part.getElementDeclaration().getTypeDefinition();
		}
		return part.getTypeDefinition();
	}
	
	
	/**
	 * An null NS URI is the default namespace "". 
	 * EMF seems to return null as the default target namespace.
	 * 
	 * @param nsURI
	 * @param nsURI2
	 * @return true if the namespaces are the same, false otherwise. 
	 */
	static boolean sameNamespace ( String nsURI, String nsURI2 ) {
		
		if (nsURI == null) {
			nsURI = XMLConstants.NULL_NS_URI;
		}
		if (nsURI2 == null) {
			nsURI2 = XMLConstants.NULL_NS_URI;
		}
		return nsURI.equals(nsURI2);
	}
	
}
