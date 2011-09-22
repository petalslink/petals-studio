/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.properties;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.Assign;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Copy;
import org.eclipse.bpel.model.From;
import org.eclipse.bpel.model.Query;
import org.eclipse.bpel.model.To;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.messageproperties.Property;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide;
import org.eclipse.bpel.ui.commands.InsertCopyCommand;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.VariableTreeContentProvider;
import org.eclipse.bpel.ui.details.tree.ITreeNode;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Part;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.ContentModelManager;
import org.eclipse.wst.xml.core.internal.contentmodel.util.DOMContentBuilderImpl;
import org.eclipse.wst.xml.core.internal.contentmodel.util.DOMNamespaceInfoManager;
import org.eclipse.wst.xml.core.internal.contentmodel.util.DOMWriter;
import org.eclipse.wst.xml.core.internal.contentmodel.util.NamespaceInfo;
import org.eclipse.wst.xml.ui.internal.wizards.NewXMLGenerator;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDTypeDefinition;
import org.w3c.dom.Document;

/**
 * An AssignCategory presenting a tree from which the user can select any of: -
 * a Variable - a Part within a Variable - some XSD element within a Part within
 * a Variable.
 *
 */

public class VariablePartAssignCategory extends AssignCategoryBase {

	Label fNameLabel;
	Text fNameText;
	Tree fVariableTree;

	TreeViewer fVariableViewer;

	VariableTreeContentProvider variableContentProvider;

	protected VariablePartAssignCategory(BPELPropertySection anOwnerSection) {
		super(anOwnerSection);
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.IAssignCategory#getName()
	 */
	@Override
	public String getName() {
		return Messages.VariablePartAssignCategory_Variable_or_Part_1;
	}

	protected boolean isPropertyTree() {
		return false;
	}

	/**
	 * This is for XPath specific queries.
	 *
	 * TODO: Need to somehow externalized this for for non-XPath languages.
	 */
	protected void updateQueryFieldFromTreeSelection() {

		if (displayQuery() == false || this.fChangeHelper.isNonUserChange()
					|| this.fModelObject == null) {
			return;
		}

		IStructuredSelection sel = (IStructuredSelection) this.fVariableViewer
		.getSelection();
		Object[] path = this.variableContentProvider.getPathToRoot(sel
					.getFirstElement());

		StringBuilder builder = new StringBuilder();
		ArrayList<String> querySegments = new ArrayList<String>();

		for (Object next : path) {

			Object eObj = BPELUtil.resolveXSDObject(BPELUtil.adapt(next,
						ITreeNode.class).getModelObject());
			builder.setLength(0);

			String targetNamespace = null;
			String namespacePrefix = null;

			if (eObj instanceof XSDAttributeDeclaration) {

				XSDAttributeDeclaration att = (XSDAttributeDeclaration) eObj;
				targetNamespace = att.getTargetNamespace();
				builder.append("/@");

				if (targetNamespace != null) {

					namespacePrefix = BPELUtil.lookupOrCreateNamespacePrefix(
								this.fModelObject, targetNamespace, "xsd", null );
					if (namespacePrefix == null) {
						break;
					}

					builder.append(namespacePrefix).append(":");
				}
				builder.append(att.getName());

			} else if (eObj instanceof XSDElementDeclaration) {

				XSDElementDeclaration elm = (XSDElementDeclaration) eObj;
				targetNamespace = elm.getTargetNamespace();
				int maxOccurs = XSDUtils.getMaxOccurs(elm);

				builder.append("/");
				if (targetNamespace != null) {
					namespacePrefix = BPELUtil.lookupOrCreateNamespacePrefix(
								this.fModelObject, targetNamespace, "xsd", null );
					if (namespacePrefix == null) {
						break;
					}
					builder.append(namespacePrefix).append(":");
				}

				builder.append(elm.getName());
				// Unbounded or bounded by something higher then 1.
				if (maxOccurs != 1) {
					builder.append("[1]");
				}
			}

			// If the current builder has length > 0, then there is a query
			// segment for us to put in.
			if (builder.length() > 0) {
				querySegments.add(builder.toString());
			}
		}

		Collections.reverse(querySegments);
		builder.setLength(0);
		for (String s : querySegments) {
			builder.append(s);
		}

		if (builder.length() > 0) {
			builder.deleteCharAt(0);
		}

		try {
			this.fChangeHelper.startNonUserChange();

			this.fNameText.setText(builder.toString());
			this.fNameText.setEnabled(true);
			this.fNameLabel.setEnabled(true);

		} finally {
			this.fChangeHelper.finishNonUserChange();
		}
	}

	@Override
	protected void createClient2(Composite parent) {

		FlatFormData data;

		this.fVariableTree = this.fWidgetFactory
		.createTree(parent, SWT.NONE /* SWT.BORDER */);

		if (displayQuery()) {
			// area for query string and wizard button
			this.fNameLabel = this.fWidgetFactory.createLabel(parent,
						Messages.VariablePartAssignCategory_Query__8);
			this.fNameText = this.fWidgetFactory.createText(parent, ""); //$NON-NLS-1$
			data = new FlatFormData();
			data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(
						this.fNameText, STANDARD_LABEL_WIDTH_SM));
			data.right = new FlatFormAttachment(100,
						-IDetailsAreaConstants.HSPACE);
			data.bottom = new FlatFormAttachment(100, 0);

			data.top = new FlatFormAttachment(100, -1
						* (this.fNameText.getLineHeight() + 4 * this.fNameText
									.getBorderWidth()) - IDetailsAreaConstants.VSPACE);
			this.fNameText.setLayoutData(data);

			this.fChangeHelper.startListeningTo(this.fNameText);
			this.fChangeHelper.startListeningForEnter(this.fNameText);

			data = new FlatFormData();
			data.left = new FlatFormAttachment(0, 0);
			data.right = new FlatFormAttachment(this.fNameText,
						-IDetailsAreaConstants.HSPACE);
			data.top = new FlatFormAttachment(this.fNameText, 0, SWT.CENTER);
			this.fNameLabel.setLayoutData(data);
		}

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.top = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		if (displayQuery()) {
			data.bottom = new FlatFormAttachment(this.fNameText,
						-IDetailsAreaConstants.VSPACE, SWT.TOP);
		} else {
			data.bottom = new FlatFormAttachment(100, 0);
		}

		// data.borderType = IBorderConstants.BORDER_2P2_BLACK;
		this.fVariableTree.setLayoutData(data);

		this.variableContentProvider = new VariableTreeContentProvider(true,
					isPropertyTree(), displayQuery());
		this.fVariableViewer = new TreeViewer(this.fVariableTree);
		this.fVariableViewer.setContentProvider(this.variableContentProvider);
		this.fVariableViewer.setLabelProvider(new ModelTreeLabelProvider());
		// TODO: does this sorter work at the top level? does it affect nested
		// levels too?
		// variableViewer.setSorter(ModelViewerSorter.getInstance());
		this.fVariableViewer.setInput(this.fOwnerSection.getModel());

		this.fVariableViewer
		.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateQueryFieldFromTreeSelection();
			}
		});

		this.fChangeHelper.startListeningTo(this.fVariableTree);
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.IAssignCategory#isCategoryForModel(org.eclipse.emf.ecore.EObject)
	 */

	@Override
	public boolean isCategoryForModel(EObject aModel) {

		if (aModel == null) {
			return false;
		}
		IVirtualCopyRuleSide side = BPELUtil.adapt(aModel,
					IVirtualCopyRuleSide.class);
		if (side == null) {
			return false;
		}
		return side.getVariable() != null
		&& side.getProperty() != null == isPropertyTree();
	}

	@SuppressWarnings("nls")
	@Override
	protected void load(IVirtualCopyRuleSide side) {

		// VZ
		if( this.fNameText.isDisposed())
			return;
		// VZ

		this.fChangeHelper.startNonUserChange();
		try {
			this.fVariableViewer.setSelection(StructuredSelection.EMPTY, false);
			if (displayQuery()) {
				this.fNameText.setText(EMPTY_STRING);
				this.fNameText.setEnabled(true);
				this.fNameLabel.setEnabled(true);
			}
		} finally {
			this.fChangeHelper.finishNonUserChange();
		}

		ArrayList<ITreeNode> pathToNode = new ArrayList<ITreeNode>();
		ITreeNode node = null;

		// First, find the variable node.
		Object context = side.getVariable();
		if (context != null) {

			Object[] items = this.variableContentProvider
			.getElements(this.fVariableViewer.getInput());
			node = this.variableContentProvider.findModelNode(items, context, 0);
			if (node != null) {
				pathToNode.add(node);
			}
		}

		// Find the part (or property) node within the container node.
		if (isPropertyTree()) {
			context = side.getProperty();
		} else {
			context = side.getPart();
		}

		if (node != null && context != null) {
			Object[] items = this.variableContentProvider.getChildren(node);
			node = this.variableContentProvider.findModelNode(items, context,
						this.variableContentProvider.isCondensed() ? 0 : 1);
			if (node != null) {
				pathToNode.add(node);
			}
		}

		if (context == null) {
			context = side.getVariable();
		}

		String query = null;

		if (node != null && context != null) {

			Query queryObject = side.getQuery();
			if (queryObject != null) {
				// TODO: we shouldn't ignore the queryLanguage here!!
				query = queryObject.getValue();
			}

			if (query != null && !query.equals(EMPTY_STRING)) {

				int tokenCount = 0;
				outer: for (String token : query.split("\\/")) {
					tokenCount += 1;
					if (token.length() == 0) {
						// Is it the first empty string preceeding the first /
						if (tokenCount == 1) {
							continue;
						}
						// could be // , as in //foo:bar/bar, which is
						// impossible to show here.
						break outer;
					}

					QueryStep step = new QueryStep(token);
					step.updateNamespaceURI(this.fModelObject);

					if (step.fAxis.equals("child") == false) {
						break outer;
					}

					Object[] items = this.variableContentProvider.getChildren(node);

					inner: for (Object item : items) {

						Object originalMatch = ((ITreeNode) item)
						.getModelObject();
						Object match = BPELUtil.resolveXSDObject(originalMatch);

						if (match instanceof XSDElementDeclaration) {
							XSDElementDeclaration elmDecl = (XSDElementDeclaration) match;

							if (match(step, elmDecl) == false) {
								continue;
							}
							node = this.variableContentProvider.findModelNode(items,
										originalMatch, 0);
							// no matching node, we are done
							if (node == null) {
								break outer;
							}
							pathToNode.add(node);
							break inner;

						} else if (match instanceof XSDAttributeDeclaration) {

							XSDAttributeDeclaration attrDecl = (XSDAttributeDeclaration) match;
							if (match(step, attrDecl)) {
								node = this.variableContentProvider.findModelNode(
											items, originalMatch, 0);
								if (node != null) {
									pathToNode.add(node);
								}
							}
							// attribute is the leaf node
							break outer;
						}
					}
				}
			}
		}

		if (pathToNode.size() > 0) {
			node = pathToNode.get(pathToNode.size() - 1);
		}

		if (node != null) {

			this.fChangeHelper.startNonUserChange();
			try {
				if (displayQuery()) {
					this.fNameText.setText(query == null ? EMPTY_STRING : query);
				}
				this.fVariableViewer.expandToLevel(node, 0);
				this.fVariableViewer.setSelection(new StructuredSelection(node),
							true);
			} finally {
				this.fChangeHelper.finishNonUserChange();
			}

		}
	}

	boolean match(QueryStep step, XSDNamedComponent xsdNamed) {
		// local name
		if (step.fLocalPart.equals(xsdNamed.getName()) == false) {
			return false;
		}

		// namespace
		return step.fNamespaceURI.equals(xsdNamed.getTargetNamespace()) || step.fNamespaceURI
					.equals(EMPTY_STRING) && xsdNamed.getTargetNamespace() == null;
	}

	@Override
	protected void store(IVirtualCopyRuleSide side) {
		IStructuredSelection sel = (IStructuredSelection) this.fVariableViewer
		.getSelection();

		Object[] path = this.variableContentProvider.getPathToRoot(sel
					.getFirstElement());
		String query = displayQuery() ? this.fNameText.getText() : EMPTY_STRING;

		side.setVariable(null);
		side.setPart(null);
		side.setProperty(null);
		side.setQuery(null);

		for (Object n : path) {
			ITreeNode treeNode = BPELUtil.adapt(n, ITreeNode.class);

			Object model = treeNode.getModelObject();
			if (model instanceof Variable) {
				side.setVariable((Variable) model);
			}
			if (model instanceof Part) {
				side.setPart((Part) model);
			}
			if (model instanceof Property) {
				side.setProperty((Property) model);
			}
		}

		query = query.trim();
		if (displayQuery() && query.length() > 0) {
			Query queryObject = BPELFactory.eINSTANCE.createQuery();
			queryObject
			.setQueryLanguage(BPELConstants.XMLNS_XPATH_QUERY_LANGUAGE);
			queryObject.setValue(query);
			side.setQuery(queryObject);
		} else {
			side.setQuery(null);
		}

		// From?
		if (side.isSource())
			return;

		// if initializer doesn't exist and query != null then we should
		// generate it
		if (query == null)
			return;
		Variable var = side.getVariable();
		if (isInitializerExist(var, side))
			return;
		if (MessageDialog
					.openQuestion(
								PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getShell(),
								"Initializer",
								NLS
								.bind(
											"Variable {0} doesn't have initializer. Should it be generated?",
											new Object[] { var.getName() }))) {
			initTargetVariable(var, side);
		}
	}

	private boolean isInitializerExist(Variable var, IVirtualCopyRuleSide side) {
		Assign a = (Assign) ((To) side.getCopyRuleSide()).getContainer()
		.getContainer();
		EList<Copy> list = a.getCopy();
		for (Copy copy : list) {
			if (copy.getFrom().getLiteral() == null)
				continue;
			To to = copy.getTo();
			Variable v = to.getVariable();
			if (v == null)
				continue;
			if (!v.equals(var))
				continue;
			Part p = to.getPart();
			if (p == null)
				continue;
			if (p.equals(side.getPart()))
				return true;
		}
		return false;
	}

	@SuppressWarnings( "restriction" )
	private void initTargetVariable(Variable var, IVirtualCopyRuleSide side) {
		String rootElement = null;
		String uriWSDL = null;

		// Variable is defined using "messageType"
		Message msg = var.getMessageType();
		if (msg != null) {
			XSDElementDeclaration declaration = side.getPart().getElementDeclaration();
			if (declaration != null) {
				uriWSDL = declaration.getSchema().getSchemaLocation();
				rootElement = declaration.getName();
			}
		}

		// Variable is defined using "type"
		XSDTypeDefinition type = var.getType();
		if (type != null) {
			QName qname = new QName(type.getTargetNamespace(), type.getName());
			rootElement = qname.getLocalPart();

			// VZ: test the resource - no resource if a variable uses a simple XML type, no WSDL behind
			if( type.eResource() != null )
				uriWSDL = type.eResource().getURI().toString();
		}

		// Variable is defined using "element"
		XSDElementDeclaration element = var.getXSDElement();
		if (element != null) {
			QName qname = new QName(element.getTargetNamespace(), element
						.getName());
			rootElement = qname.getLocalPart();
			uriWSDL = element.eResource().getURI().toString();
		}

		// Incomplete variable definition
		if (rootElement == null || uriWSDL == null) {
			return;
		}

		MyNewXMLGenerator generator = new MyNewXMLGenerator();
		generator.setRootElementName(rootElement);

		CMDocument cmdoc = ContentModelManager.getInstance().createCMDocument(
					uriWSDL, "xsd");
		generator.setCMDocument(cmdoc);
		generator.createNamespaceInfoList();

		try {
			String literal = generator.createXML("tmp");
			Copy copy = BPELFactory.eINSTANCE.createCopy();
			Assign a = (Assign) ((To) side.getCopyRuleSide()).getContainer()
			.getContainer();
			getCommandFramework()
			.execute(
						wrapInShowContextCommand(new InsertCopyCommand(a,
									copy, 0)));
			To to = BPELFactory.eINSTANCE.createTo();
			From from = BPELFactory.eINSTANCE.createFrom();
			copy.setFrom(from);
			copy.setTo(to);
			from.setLiteral(literal);
			to.setVariable(side.getVariable());
			to.setPart(side.getPart());
		} catch (Exception e) {
			throw new IllegalStateException(
			"Can't generate initializer, check WSDL file");
		}
	}

	@Override
	protected void basicSetInput(EObject newInput) {

		super.basicSetInput(newInput);

		/**
		 * During initialization of variable, the list of available variables
		 * must not include the current variable and the variables logically
		 * after. So we just alter the input to the variable viewer, if we are
		 * looking at a variable. In assign statement, where the model is Copy,
		 * this does not happen.
		 */

		EObject container = newInput.eContainer();
		if (container instanceof Variable) {

			this.fChangeHelper.startNonUserChange();
			try {
				this.fVariableViewer.setInput(container);
			} finally {
				this.fChangeHelper.finishNonUserChange();
			}
		}
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return null;
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	@Override
	public void restoreUserContext(Object userContext) {
		this.fVariableTree.setFocus();
	}

	private boolean displayQuery() {
		return !isPropertyTree();
	}

	/**
	 * Parse the query step into this object.
	 *
	 * TODO: This has to be externalized someplace. We can't just assume it is
	 * XPath all the time ...
	 */

	@SuppressWarnings("nls")
	static class QueryStep {

		String fAxis = "child";

		String fPrefix = EMPTY_STRING;
		String fLocalPart = EMPTY_STRING;
		String fNamespaceURI = EMPTY_STRING;

		@SuppressWarnings("nls")
		QueryStep(String step) {

			int axisMark = step.indexOf("::");
			if (axisMark >= 0) {
				this.fAxis = step.substring(0, axisMark);
				step = step.substring(axisMark + 2);
			}

			int qnameMark = step.indexOf(":");
			if (qnameMark < 0) {
				this.fLocalPart = step;
			} else {
				this.fLocalPart = step.substring(qnameMark + 1);
				this.fPrefix = step.substring(0, qnameMark);
			}

			if (this.fLocalPart.charAt(0) == '@') {
				this.fLocalPart = this.fLocalPart.substring(1);
			}

			int arrayMark1 = this.fLocalPart.indexOf('[');
			int arrayMark2 = this.fLocalPart.indexOf(']');
			if (arrayMark2 > arrayMark1 && arrayMark1 > 0) {
				this.fLocalPart = this.fLocalPart.substring(0, arrayMark1);
			}
		}

		void updateNamespaceURI(EObject eObj) {
			if (this.fPrefix.length() > 0) {
				this.fNamespaceURI = BPELUtils.getNamespace(eObj, this.fPrefix);
				if (this.fNamespaceURI == null) {
					this.fNamespaceURI = "urn:unresolved:"
						+ System.currentTimeMillis() + ":" + this.fPrefix;
				}
			} else {
				this.fNamespaceURI = EMPTY_STRING;
			}
		}

	}

	@SuppressWarnings( "restriction" )
	static class MyNewXMLGenerator extends NewXMLGenerator {

		public String createXML(String xmlFileName) throws Exception {
			CMDocument cmDocument = getCMDocument();

			// create the xml model
			CMNamedNodeMap nameNodeMap = cmDocument.getElements();
			CMElementDeclaration cmElementDeclaration = (CMElementDeclaration) nameNodeMap
			.getNamedItem(getRootElementName());

			Document xmlDocument = DocumentBuilderFactory.newInstance()
			.newDocumentBuilder().newDocument();
			MyDOMContentBuilderImpl contentBuilder = new MyDOMContentBuilderImpl(
						xmlDocument);

			contentBuilder.setBuildPolicy(this.buildPolicy);
			contentBuilder.createDefaultRootContent(cmDocument,
						cmElementDeclaration, this.namespaceInfoList);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
						outputStream);

			DOMWriter domWriter = new DOMWriter(outputStreamWriter);

			// TODO... instead of relying on file extensions, we need to keep
			// track of the grammar type
			// better yet we should reate an SSE document so that we can format
			// it
			// nicely before saving
			// then we won't need the DOMWriter at all
			//
			domWriter.print(xmlDocument);
			outputStream.flush();
			outputStream.close();

			return outputStream.toString();
		}

	}

	@SuppressWarnings( "restriction" )
	static class MyDOMContentBuilderImpl extends DOMContentBuilderImpl {

		public MyDOMContentBuilderImpl(Document document) {
			super(document);
		}

		@Override
		public void createDefaultRootContent(CMDocument cmDocument,
					CMElementDeclaration rootCMElementDeclaration) throws Exception {
			if (this.namespaceInfoList != null) {
				DOMNamespaceInfoManager manager = new DOMNamespaceInfoManager();
				String name = rootCMElementDeclaration.getNodeName();
				if (this.namespaceInfoList.size() > 0) {
					NamespaceInfo info = (NamespaceInfo) this.namespaceInfoList
					.get(0);
					if (info.prefix != null && info.prefix.length() > 0) {
						name = info.prefix + ":" + name; //$NON-NLS-1$
					}
				}
				this.rootElement = createElement(rootCMElementDeclaration, name,
							this.document);
				manager.addNamespaceInfo(this.rootElement, this.namespaceInfoList, true);
			}
			createDefaultContent(this.document, rootCMElementDeclaration);
		}
	}
}
