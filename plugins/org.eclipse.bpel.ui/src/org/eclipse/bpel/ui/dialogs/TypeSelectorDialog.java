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
package org.eclipse.bpel.ui.dialogs;


import java.util.List;

import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.details.providers.CompositeContentProvider;
import org.eclipse.bpel.ui.details.providers.MessageTypeContentProvider;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.VariableTypeTreeContentProvider;
import org.eclipse.bpel.ui.details.providers.XSDSchemaFromResourceContentProvider;
import org.eclipse.bpel.ui.details.providers.XSDTypeOrElementContentProvider;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.wsdl.Message;


/**
 * Browse for data types available and choose that type.
 * 
 * In BPEL, types are just an aggregation of "visible" XML schemas. Visible schemas are
 * schemas imported in the BPEL source file via the import construct and schemas
 * present in WSDL files (which are also imported via the import construct).
 * 
 * When browsing for types, two modes are supported by this dialog.
 * 1) Browsing types which are already imported (constitute the type system of the process)
 * 2) Browsing for available types. This includes XML types visible to designer.
 * 
 * In addition, one can arbitrarily add an import to the process if the XML schema
 * representing the types is somewhere else (URL, outside file, etc).
 * 
 * It should be noted, that currently, no attempt is made to resolve duplicate type entries
 * in the schemas. This is not necessarily a bug.
 * 
 *  @author Michal Chmielewski (michal.chmielewski@oracle.com)  
 * 
 */

public class TypeSelectorDialog extends BrowseSelectorDialog {
		
	/* Button id for complex types */
	protected final static int BID_COMPLEX_TYPES = IDialogConstants.CLIENT_ID + 100;
	
	/* Button id for simple types */
	protected final static int BID_SIMPLE_TYPES = IDialogConstants.CLIENT_ID + 101;
	
	/* Button id for element declarations */
	protected final static int BID_ELEMENT_DECLARATIONS = IDialogConstants.CLIENT_ID + 102;
	
	/* Button id for messages */
	protected final static int BID_MESSAGES = IDialogConstants.CLIENT_ID + 103;
	
	/* Button id for primitives */
	protected final static int BID_XSD_PRIMITIVES = IDialogConstants.CLIENT_ID + 104;
		
	/* Whether messages ought to be shown */	
	private boolean showMessages = true;
	
		
	protected XSDTypeOrElementContentProvider xsdTypeProvider = new XSDTypeOrElementContentProvider();
	protected MessageTypeContentProvider messageTypeProvider = new MessageTypeContentProvider();	
	
	
	/* Filter types key */
	private static final String FILTER_TYPES_KEY = "FilterTypes"; //$NON-NLS-1$
	
	/* Show messages key in dialog settings */
	private static final String SHOW_MESSAGES_KEY = "ShowMessages"; //$NON-NLS-1$
	
			
	/* Which types to filter ? */
	private int FILTER_TYPES = XSDTypeOrElementContentProvider.INLCUDE_ALL;
	
	
	/**
	 * Create a brand new shiny Type Selector Dialog.
	 * 
	 * @param parent the parent shell
	 * @param eObj any BPEL model object
	 */
	
	public TypeSelectorDialog (Shell parent, EObject eObj ) {
		super(parent, new ModelLabelProvider(eObj));
		
		this.modelObject = eObj;
					
		resourceContentProvider = new XSDSchemaFromResourceContentProvider( modelObject.eResource().getResourceSet() );
		
		CompositeContentProvider ccp = new CompositeContentProvider();
		ccp.add( xsdTypeProvider );
		ccp.add( messageTypeProvider );
		contentProvider = ccp;
		
		treeContentProvider = new VariableTypeTreeContentProvider (true,true);
		
		setTitle(Messages.TypeSelectorDialog_4);
		setMessage(Messages.TypeSelectorDialog_5); 
		setLowerViewLabel(Messages.TypeSelectorDialog_7);		
		setBrowseFromLabel( Messages.TypeSelectorDialog_9 );
		
		// Restore some dialog settings ...
		IDialogSettings settings = getDialogSettings();					
		try {
			FILTER_TYPES = settings.getInt(FILTER_TYPES_KEY);
		} catch (Exception ex) {
			FILTER_TYPES = XSDTypeOrElementContentProvider.INLCUDE_ALL;
		}		
				
		try {
			showMessages = settings.getBoolean(SHOW_MESSAGES_KEY);
		} catch (Exception ex) {
			showMessages = false;
		}		
	}
	

	
	/** 
	 * Hook a load on the defaults into this dialog upon create.
	 * 
	 */
	
	@Override
	protected Control createContents(Composite parent) {
		
		Control control = super.createContents(parent);
			
		// Re-Create the state of the provider from the dialog settings.
		xsdTypeProvider.setFilter( FILTER_TYPES );
		
		refresh();		

		return control;
	}


	
	@Override
	protected void saveSettings () {
		super.saveSettings();		
		IDialogSettings settings = getDialogSettings();		
		settings.put ( FILTER_TYPES_KEY, FILTER_TYPES );		
		settings.put ( SHOW_MESSAGES_KEY, showMessages);		
	}
	
	/**
	 * Handle the check button and radio button callbacks.
	 * 
	 * @param id
	 * @param checked
	 */
	
	@Override
	protected void buttonPressed (int id, boolean checked, boolean bRefresh) {
		
		int bits = 0;
				
		switch (id) {
		case BID_SIMPLE_TYPES :
			bits = XSDTypeOrElementContentProvider.INCLUDE_SIMPLE_TYPES;
			break;
		
		case BID_COMPLEX_TYPES :
			bits = XSDTypeOrElementContentProvider.INCLUDE_COMPLEX_TYPES;
			break;

		case BID_ELEMENT_DECLARATIONS :
			bits = XSDTypeOrElementContentProvider.INCLUDE_ELEMENT_DECLARATIONS;
			break;

		case BID_XSD_PRIMITIVES :
			bits = XSDTypeOrElementContentProvider.INCLUDE_PRIMITIVES;
			break;
			
		case BID_MESSAGES :
			showMessages = checked;
			break;
							
		default :
			// exit the function here
			super.buttonPressed(id, checked,bRefresh);
			return;
		}

		
		// adjust the filter
		if (bits != 0) {
			if (checked) {
				FILTER_TYPES |= bits;
			} else {
				FILTER_TYPES &= ~bits;
			}
			xsdTypeProvider.setFilter (FILTER_TYPES);
		}
		
		if (bRefresh) {
			refresh();
		}
	}
		
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, 
				BID_ADD_IMPORT,
				Messages.TypeSelectorDialog_3, 
				false);		
		super.createButtonsForButtonBar(parent);
	}
	
	@Override
	protected void createBrowseFilterGroupButtons ( Group  group ) {
        
		createCheckButton(group,Messages.TypeSelectorDialog_14, BID_XSD_PRIMITIVES, 
				(FILTER_TYPES & XSDTypeOrElementContentProvider.INCLUDE_PRIMITIVES) > 0  );
		createCheckButton(group,Messages.TypeSelectorDialog_15, BID_SIMPLE_TYPES, 
				(FILTER_TYPES &  XSDTypeOrElementContentProvider.INCLUDE_SIMPLE_TYPES) > 0   );
		createCheckButton(group,Messages.TypeSelectorDialog_16, BID_COMPLEX_TYPES, 
				(FILTER_TYPES &  XSDTypeOrElementContentProvider.INCLUDE_COMPLEX_TYPES ) > 0 );
		createCheckButton(group,Messages.TypeSelectorDialog_17, BID_ELEMENT_DECLARATIONS,
				(FILTER_TYPES &  XSDTypeOrElementContentProvider.INCLUDE_ELEMENT_DECLARATIONS ) > 0 );
		createCheckButton(group,Messages.TypeSelectorDialog_18, BID_MESSAGES,
				showMessages);
		
		super.createBrowseFilterGroupButtons( group );
	}
	
	
	
	@Override
	protected List collectItemsFromImports() {
		
		List list = ModelHelper.getSchemas(modelObject,true);
		
		if (showMessages) {
			list.addAll ( ModelHelper.getDefinitions(modelObject) );
		}
		
		return list;
	}

	@Override
	protected void okPressed() {
		computeResult();
		Object obj = getFirstResult();
		// We need to check whether namespace prefix has been already defined
		if (obj instanceof Message) {
			if (!checkNamespace((Message) obj)){
				return;
			}
		}
		super.okPressed();
	}

	private boolean checkNamespace(Message message) {
		String targetNamespace = message.getQName().getNamespaceURI();
		String prefix = BPELUtils.getNamespacePrefix (modelObject, targetNamespace);
		if (prefix != null) {
			return true;
		}
		// We have to map the namespace to a prefix. 
		NamespaceMappingDialog dialog = new NamespaceMappingDialog(getShell(),modelObject);
		dialog.setNamespace( targetNamespace );
		
		if (dialog.open() == Window.CANCEL) {
			return false;
		}
		
		// define the prefix
		BPELUtils.setPrefix( ModelHelper.getProcess(modelObject), targetNamespace, dialog.getPrefix()); 		
		return true;
	}
			
}
