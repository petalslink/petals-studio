package org.eclipse.bpel.ui.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.ExtensibleElement;
import org.eclipse.wst.wsdl.MessageReference;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.Types;
import org.eclipse.wst.wsdl.WSDLElement;
import org.eclipse.wst.wsdl.ui.internal.extensions.ExtensibleTypeSystemProvider;
import org.eclipse.wst.wsdl.ui.internal.extensions.ITypeSystemProvider;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BPELEditorUtil extends BPELConstants
{
	protected static BPELEditorUtil instance;
	protected BPELNodeAssociationManager nodeAssociationManager = new BPELNodeAssociationManager();

	protected HashMap elementNameToTypeMap = new HashMap();

	public static final int ELEMENT = 14;
	public static final int TYPE = 15;

	public static BPELEditorUtil getInstance()
	{
		if (instance == null)
		{
			instance = new BPELEditorUtil();
		}
		return instance;
	}

	public BPELEditorUtil()
	{
		//FIXME add bpel tags here
		/*elementNameToTypeMap.put(BINDING_ELEMENT_TAG, new Integer(BINDING));
    elementNameToTypeMap.put(DEFINITION_ELEMENT_TAG, new Integer(DEFINITION));
    elementNameToTypeMap.put(DOCUMENTATION_ELEMENT_TAG, new Integer(DOCUMENTATION));
    elementNameToTypeMap.put(FAULT_ELEMENT_TAG, new Integer(FAULT));
    elementNameToTypeMap.put(IMPORT_ELEMENT_TAG, new Integer(IMPORT));
    elementNameToTypeMap.put(INPUT_ELEMENT_TAG, new Integer(INPUT));
    elementNameToTypeMap.put(MESSAGE_ELEMENT_TAG, new Integer(MESSAGE));
    elementNameToTypeMap.put(OPERATION_ELEMENT_TAG, new Integer(OPERATION));
    elementNameToTypeMap.put(OUTPUT_ELEMENT_TAG, new Integer(OUTPUT));
    elementNameToTypeMap.put(PART_ELEMENT_TAG, new Integer(PART));
    elementNameToTypeMap.put(PORT_ELEMENT_TAG, new Integer(PORT));
    elementNameToTypeMap.put(PORT_TYPE_ELEMENT_TAG, new Integer(PORT_TYPE));
    elementNameToTypeMap.put(SERVICE_ELEMENT_TAG, new Integer(SERVICE));
    elementNameToTypeMap.put(TYPES_ELEMENT_TAG, new Integer(TYPES));*/
	}

	public String getBPELType(Element element)
	{
		String result = "";
		return element.getLocalName();
	}

	protected List getParentElementChain(Element element)
	{
		List list = new ArrayList();
		while (element != null)
		{
			list.add(0, element);
			Node node = element.getParentNode();
			element = (node != null && node.getNodeType() == Node.ELEMENT_NODE) ? (Element)node : null;
		}
		return list;
	}

	public Object findModelObjectForElement(Process process, Element targetElement)
	{
		Object o = this.nodeAssociationManager.getModelObjectForNode(process, targetElement);
		return o;
	}

	public Element getElementForObject(Object o)
	{
		return ((WSDLElement)o).getElement();
	}

	public Node getNodeForObject(Object o)
	{
		return this.nodeAssociationManager.getNode(o);
	}

	// Provide a mapping between Definitions and ITypeSystemProviders
	//  private Hashtable typeSystemProviders = new Hashtable();
	private ITypeSystemProvider typeSystemProvider;

	public ITypeSystemProvider getTypeSystemProvider(Definition definition)
	{
		if (this.typeSystemProvider == null) {
			this.typeSystemProvider = new ExtensibleTypeSystemProvider();
		}
		return this.typeSystemProvider;
	}

	public void setTypeSystemProvider(Definition definition, ITypeSystemProvider typeSystemProvider)
	{
		//   typeSystemProviders.put(definition,typeSystemProvider);
	}

	public List getExtensibilityElementNodes(ExtensibleElement extensibleElement)
	{
		// For Types, I need to get all the schemas
		if (extensibleElement instanceof Types)
		{
			Types xsdEE = (Types)extensibleElement;
			return xsdEE.getSchemas();
		}

		return extensibleElement.getEExtensibilityElements();
	}

	/*
	 * Returns a list of 'children' of the given object model (WSDLElement).
	 */
	public static List getModelGraphViewChildren(Object object) {

		List childList = new ArrayList();

		if (object instanceof PortType) {
			PortType portType = (PortType) object;
			childList.addAll(portType.getOperations());
		}
		else if (object instanceof Operation) {
			Operation operation = (Operation) object;

			if (operation.getEInput() != null) {
				childList.add(operation.getEInput());
			}
			if (operation.getEOutput() != null) {
				childList.add(operation.getEOutput());
			}
			childList.addAll(operation.getFaults().values());
		}
		else if (object instanceof MessageReference) {
			MessageReference messageReference = (MessageReference) object;
			childList.add(messageReference.getEMessage());
		}

		return childList;
	}
}
