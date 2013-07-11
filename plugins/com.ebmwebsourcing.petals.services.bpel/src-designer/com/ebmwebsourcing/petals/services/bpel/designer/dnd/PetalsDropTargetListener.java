/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.designer.dnd;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Invoke;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.PartnerLinks;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.Variables;
import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypeFactory;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.adapters.IContainer;
import org.eclipse.bpel.ui.commands.AddPartnerLinkCommand;
import org.eclipse.bpel.ui.commands.AddVariableCommand;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.CreatePartnerLinkTypeCommand;
import org.eclipse.bpel.ui.commands.InsertInContainerCommand;
import org.eclipse.bpel.ui.commands.SetSelectionCommand;
import org.eclipse.bpel.ui.dnd.TextDropTargetListener;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Import;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.util.WSDLConstants;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;

/**
 * A DnD handler for Petals services.
 * @author Vincent Zurczak - EBM WebSourcing
 * FIXME: this DnD is not perfect. It can be applied only once. No need to waste time on it, we have to focus on the new BPEL designer.
 */
public class PetalsDropTargetListener extends TextDropTargetListener {

	/**
	 * Constructor.
	 * @param viewer
	 * @param editor
	 */
	public PetalsDropTargetListener( GraphicalViewer viewer, BPELEditor editor ) {
		super( viewer, editor );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpel.ui.dnd.TextDropTargetListener
	 * #isEnabled(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public boolean isEnabled (DropTargetEvent event) {
		boolean enabled = false;
		if( TextTransfer.getInstance().isSupportedType( event.currentDataType )) {
			// We cannot use the content of the dragged data because it is
			// platform dependent (works on Windows XP, not on Linux - and not on Windows 7)
			// => We use the selection instead.

			ISelection s =
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();

			if( ! s.isEmpty()
						&& s instanceof IStructuredSelection ) {
				Object o = ((IStructuredSelection) s).getFirstElement();
				enabled = EndpointBean.class.equals( o.getClass());
			}
		}
		return enabled;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpel.ui.dnd.TextDropTargetListener
	 * #drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public void drop( final DropTargetEvent event ) {
		if( getTransfer().isSupportedType( event.currentDataType )) {
			String data = (String) getTextTransfer().nativeToJava( event.currentDataType );

			// Hack: on Linux, data is null
			if( data == null ) {
				if( event.data instanceof String )
					data = (String) event.data;
			}

			if( data != null
						&& data.startsWith( "petals-" )
						&& event.widget instanceof DropTarget ) {

				// Validate the drop
				DropTarget target = (DropTarget) event.widget;
				Point absolutePoint = new Point( event.x, event.y );
				Point relativePoint = target.getControl().toControl( absolutePoint );
				PetalsDropTestResult result = validateDropLocation( relativePoint.x, relativePoint.y );

				// Drop is allowed?
				if( result.allowed ) {
					String wsdlUri = null, edptName = null;
					String itfName = null, itfNs = null;
					for( String part : data.split( "\\|" )) {

						if( part.startsWith( "edptName=" ))
							edptName = part.substring( 9 );

						else if( part.startsWith( "wsdlUri=" ))
							wsdlUri = part.substring( 8 );

						else if( part.startsWith( "itfName=" ))
							itfName = part.substring( 8 );

						else if( part.startsWith( "itfNs=" ))
							itfNs = part.substring( 6 );
					}


					// No URI: open a dialog for the user
					if( StringUtils.isEmpty( wsdlUri )
								|| StringUtils.isEmpty( edptName )
								|| StringUtils.isEmpty( itfName )
								|| StringUtils.isEmpty( itfNs )) {
						MessageDialog.openInformation( new Shell(), "No WSDL to drop", "The dropped service does not have a WSDL." );

					} else {
						// Get the operation to use in the invocation
						QName portTypeName = new QName( itfNs, itfName );
						QName operationToInvoke = selectOperationToInvoke( wsdlUri, portTypeName );
						if( operationToInvoke != null ) {

							// Import the WSDL
							String newWsdlUri = importWsdlInProject( wsdlUri, edptName );

							// Add the BPEL elements
							if( newWsdlUri != null )
								addPetalsBpelElements( newWsdlUri, portTypeName, operationToInvoke, result );
							else
								MessageDialog.openInformation( new Shell(),
										"No WSDL to import",
										"The WSDL of the dropped service could or should not be imported. " +
										"This feature can only be used as an initial helper to create a partner link." );
						}
					}
				}
			}
		}
	}


	/**
	 * Gets the operation to invoke for the given port type.
	 * @param wsdlUri the WSDL URI
	 * @param portTypeName the port type name
	 * @return null if no operation was selected or if the port type name is invalid, an operation name otherwise
	 */
	private QName selectOperationToInvoke( String wsdlUri, QName portTypeName ) {

		// Load the WSDL
		Definition wsdlDefinition = loadWsdl( wsdlUri );

		// Find the right port type
		PortType rightPortType = null;
		if( wsdlDefinition != null )
			rightPortType = findPortType( wsdlDefinition, portTypeName );

		// Select an operation to invoke
		QName operation = null;
		if( rightPortType != null
					&& rightPortType.getOperations() != null
					&& rightPortType.getOperations().size() > 0 ) {
			OperationSelectionDialog dlg = new OperationSelectionDialog( new Shell(), rightPortType );
			if( dlg.open() == Window.OK )
				operation = dlg.getSelectedOperation();
		}

		return operation;
	}


	/**
	 * Imports a WSDL in a project.
	 * <p>
	 * This may occur when dragging and dropping an end-point from a SA or from a
	 * Petals server.
	 * </p>
	 * <p>
	 * The imported WSDL will be placed in a folder called <i>importedWsdlContainer</i>,
	 * located in the resource folder of a Petals project.
	 * </p>
	 * <p>
	 * The concerned project is the one used by the editor with the focus.
	 * </p>
	 *
	 * @param wsdlUri the WSDL URI (the one to check, before a potential import)
	 * @param importedWsdlContainer the folder name in which the WSDL should be imported in case of import
	 * @return the imported URI or null if the WSDL could not or should not be imported
	 */
	private String importWsdlInProject( String wsdlUri, String importedWsdlContainer ) {

		IFile openFile = ((FileEditorInput) this.fEditor.getEditorInput()).getFile();
		if( openFile == null )
			return null;

		try {
			// Determine in which folder import the WSDL
			IProject project = openFile.getProject();
			IFolder folder = project.getFolder( "src/main/jbi" );
			if( folder.exists())
				folder = folder.getFolder( importedWsdlContainer );
				if( ! folder.exists()) {
					folder.create( true, true, new NullProgressMonitor());

				// Import the WSDL
				Map<String,File> result = new WsdlImportHelper().importWsdlOrXsdAndDependencies( folder.getLocation().toFile(), wsdlUri );
				File f = result.get( wsdlUri );
				if( f != null )
					return f.toURI().toString();
			}

		} catch( Exception e ) {
			BPELUIPlugin.log( e, IStatus.ERROR );
		}

		// If the WSDL could not be imported, return null
		// Otherwise, we will go through the whole process again, even if this WSDL and this service
		// were already added to the process
		return null;
	}


	/**
	 * Validates and prepares the drop.
	 * @param dropLocationX
	 * @param dropLocationY
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( "unchecked" )
	private PetalsDropTestResult validateDropLocation( int dropLocationX, int dropLocationY ) {

		PetalsDropTestResult result = new PetalsDropTestResult();

		// Get the process element that must be appended a new invocation
		org.eclipse.draw2d.geometry.Point dropLocation = new org.eclipse.draw2d.geometry.Point( dropLocationX, dropLocationY );
		EditPart editPart = this.fEditor.getGraphicalViewer().findObjectAt( dropLocation );
		IContainer<Object> container = null;

		if( editPart != null
					&& editPart.getModel() instanceof EObject
					&& ( container = BPELUtil.adapt( editPart.getModel(), IContainer.class )) != null ) {

			// Find the element inside the container and before the drop location.
			// It means we want to insert the invoke before the activity located under the drop location.
			// When there is no activity after, then we will append the invoke in the sequence.
			for( int y=10; y<80; y+=10 ) {	// 80 is an arbitrary value

				org.eclipse.draw2d.geometry.Point testLocation =
					new org.eclipse.draw2d.geometry.Point( dropLocationX, y + dropLocationY );

				EditPart childEditPart = this.fEditor.getGraphicalViewer().findObjectAt( testLocation );
				if( childEditPart != editPart
							&& childEditPart.getModel() instanceof EObject
							&& editPart.getModel().equals(((EObject) childEditPart.getModel()).eContainer())) {

					result.previousActivity = (EObject) childEditPart.getModel();
					break;
				}
			}

			// Create the invoke and test its addition
			final Invoke invoke = BPELFactory.eINSTANCE.createInvoke();
			if ( container.canAddObject( editPart.getModel(), invoke, result.previousActivity )) {
				result.allowed = true;
				result.invokeParent = (EObject) editPart.getModel();
				result.invoke = invoke;
			}
		}

		return result;
	}


	/**
	 * Adds all the required elements to invoke the dropped Petals service.
	 * <p>
	 * Both the process and its interface are updated.
	 * </p>
	 *
	 * @param newWsdlUri the WSDL URI
	 * @param portTypeName the name of the port type to use
	 * @param operationToInvoke the operation to invoke (not null)
	 * @param petalsDropTestResult the location where to create the invocation
	 */
	private void addPetalsBpelElements(
				String newWsdlUri,
				QName portTypeName,
				QName operationToInvoke,
				final PetalsDropTestResult petalsDropTestResult ) {

		// Find the right port type
		Definition wsdlDefinition = loadWsdl( newWsdlUri );
		if( wsdlDefinition == null )
			return;

		PortType rightPortType = findPortType( wsdlDefinition, portTypeName );
		if( rightPortType == null )
			return;


		// Add a command to import the WSDL in the BPEL process
		CompoundCommand cc = new CompoundCommand( "Adding a Petals service in the process" );
		org.eclipse.bpel.model.Import bpelImport = BPELFactory.eINSTANCE.createImport();
		bpelImport.setImportType(  WSDLConstants.WSDL_NAMESPACE_URI );
		bpelImport.setNamespace( wsdlDefinition.getTargetNamespace());

		File processFile = EmfUtils.getUnderlyingFile( this.fEditor.getProcess());
		File wsdlFile = EmfUtils.getUnderlyingFile( wsdlDefinition );
		String relativeLocation = IoUtils.getRelativeLocationToFile( processFile, wsdlFile );
		bpelImport.setLocation( relativeLocation );

		SimpleAddBpelImportCommand addImportCmd = new SimpleAddBpelImportCommand( this.fEditor.getProcess(), bpelImport );
		if( addImportCmd.canDoExecute())
			cc.add( addImportCmd );

		// Get the WSDL to write the partner role
		// If the WSDL artifacts already exists, use it.
		// Otherwise, use the imported WSDL.
		Definition targetDefinition = null;
		IFile bpelFile = (IFile) this.fEditor.getEditModelClient().getEditModel().getPrimaryFile();
		if( BPELUtil.getArtifactsWSDLFile( bpelFile ).exists())
			targetDefinition = this.fEditor.getArtifactsDefinition();
		else
			targetDefinition = wsdlDefinition;


		// Create a partner link for the right port type
		PartnerLink defaultPartner = null;
		String itfName = rightPortType.getQName().getLocalPart();

		Role plRole = PartnerlinktypeFactory.eINSTANCE.createRole();
		plRole.setName( itfName + "Role" );
		plRole.setPortType( rightPortType );

		PartnerLinkType plType = PartnerlinktypeFactory.eINSTANCE.createPartnerLinkType();
		plType.setName( itfName + "PLT" );
		plType.getRole().add( plRole );

		PartnerLink partnerLink = BPELFactory.eINSTANCE.createPartnerLink();
		partnerLink.setName( itfName + "Partner" );
		partnerLink.setPartnerLinkType( plType );
		partnerLink.setPartnerRole( plRole );


		// Check if this partner link already exists before
		PartnerLinks pls = this.fEditor.getProcess().getPartnerLinks();
		for( int i=0; i<pls.getChildren().size(); i++ ) {

			PartnerLink pl = pls.getChildren().get( i );
			if( pl.getPartnerLinkType() == null )
				continue;

			// FIXME: for instance, we limit equality between partner links to
			// ++ Equality of partner link names.
			// ++ Equality of partner link type names.

			// In a perfect world, we would rather look at the port types and...
			// rename the partner link to create in case of name conflict (and different port types)
			if( partnerLink.getName().equals( pl.getName())) {
				PartnerLinkType plt1 = partnerLink.getPartnerLinkType();
				PartnerLinkType plt2 = pl.getPartnerLinkType();
				if( plt1.getName() == null && plt2.getName() == null
							|| plt1.getName() != null && plt1.getName().equals( plt2.getName())) {

					if( defaultPartner == null ) {
						defaultPartner = pl;
						break;
					}
				}
			}
		}

		// The partner does not exist, create it
		if( defaultPartner == null ) {
			defaultPartner = partnerLink;

			// Create an import in the artifacts WSDL
			// FIXME: this step is useless because the BPEL Designer deletes all the imports
			// from the artifacts WSDL and recomputes them all on save action.
//			Import newImport = WSDLFactory.eINSTANCE.createImport();
//			newImport.setNamespaceURI( wsdlDefinition.getTargetNamespace());
//
//			File artifactWsdlFile = EmfUtils.getUnderlyingFile( targetDefinition );
//			relativeLocation = IoUtils.getRelativeLocationToFile( artifactWsdlFile, wsdlFile );
//			newImport.setLocationURI( relativeLocation );
//
//			SimpleAddWsdlImportCommand customAddCommand = new SimpleAddWsdlImportCommand( targetDefinition, newImport );
//			cc.add( customAddCommand );

			// Add the partner link type in the artifact definition
			CreatePartnerLinkTypeCommand createPartnerLinkTypeCmd =
				new CreatePartnerLinkTypeCommand( targetDefinition, plType );
			cc.add( createPartnerLinkTypeCmd );

			// Add the partner link in the BPEL
			AddPartnerLinkCommand addPartnerLinkCmd = new AddPartnerLinkCommand(
						this.fEditor.getProcess().getPartnerLinks(),
						partnerLink );
			cc.add( addPartnerLinkCmd );
		}


		// Update the invocation
		petalsDropTestResult.invoke.setPartnerLink( defaultPartner );
		petalsDropTestResult.invoke.setName( "invokeOn" + defaultPartner.getName());
		Object portType = defaultPartner.getPartnerRole().getPortType();
		Operation theOperation = null;
		if( portType instanceof PortType ) {
			for( Object op : ((PortType) portType).getOperations()) {
				String localName = ((Operation) op).getName();
				String nsUri = ((PortType) portType).getQName().getNamespaceURI();

				if( localName.equals( operationToInvoke.getLocalPart())
							&& nsUri.equals( operationToInvoke.getNamespaceURI())) {
					theOperation = (Operation) op;
					break;
				}
			}
		}

		if( theOperation == null )
			return;


		// Set the operation to invoke
		petalsDropTestResult.invoke.setOperation( theOperation );


		// Create an input variable
		String variableName = findUnusedVariableName(
					defaultPartner.getName() + "Request",
					this.fEditor.getProcess().getVariables());

		Variable inputVar = BPELFactory.eINSTANCE.createVariable();
		inputVar.setName( variableName );
		if( theOperation.getEInput() != null )
			inputVar.setMessageType( theOperation.getEInput().getEMessage());

		petalsDropTestResult.invoke.setInputVariable( inputVar );
		cc.add( new AddVariableCommand( this.fEditor.getProcess().getVariables(), inputVar ));


		// Create an output variable?
		if( theOperation.getOutput() != null ) {
			variableName = findUnusedVariableName(
						defaultPartner.getName() + "Response",
						this.fEditor.getProcess().getVariables());

			Variable outputVar = BPELFactory.eINSTANCE.createVariable();
			outputVar.setName( variableName );
			if( theOperation.getEOutput() != null )
				outputVar.setMessageType( theOperation.getEOutput().getEMessage());

			petalsDropTestResult.invoke.setOutputVariable( outputVar );
			cc.add( new AddVariableCommand( this.fEditor.getProcess().getVariables(), outputVar ));
		}

		cc.add( new InsertInContainerCommand(
					petalsDropTestResult.invokeParent,
					petalsDropTestResult.invoke,
					petalsDropTestResult.previousActivity ));

		cc.add( new SetSelectionCommand ( petalsDropTestResult.invokeParent, true ) );
		cc.add( new SetSelectionCommand ( petalsDropTestResult.invoke, false ) );

		if( cc.canDoExecute())
			this.fEditor.getCommandFramework().execute( cc );


		// Show the properties of the invoke
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {

					// Show the properties page
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IViewPart view = page.showView( "org.eclipse.ui.views.PropertySheet" );

					// Display a specific tab
					TabbedPropertySheetPage tpPage = (TabbedPropertySheetPage) view.getAdapter( TabbedPropertySheetPage.class );
					if( tpPage != null )
						tpPage.setSelectedTab( "org.eclipse.bpel.ui.tabs.details" );

				} catch( PartInitException e ) {
					BPELUIPlugin.log( e, IStatus.WARNING );
				}

				PetalsDropTargetListener.this.fEditor.selectModelObject( petalsDropTestResult.invoke );
			}
		};

		this.fEditor.getSite().getShell().getDisplay().asyncExec( runnable );
		try {
			bpelFile.getProject().refreshLocal( IResource.DEPTH_INFINITE, new NullProgressMonitor());

		} catch( CoreException e ) {
			BPELUIPlugin.log( e );
		}
	}


	/**
	 * The elements required to performed the drop.
	 */
	private static class PetalsDropTestResult {
		EObject invokeParent;
		EObject previousActivity;
		Invoke invoke;
		boolean allowed = false;
	}


	/**
	 * Finds a new variable name.
	 * @param baseName the base for the variable name
	 * @param variables the process variables
	 * @return an unused variable name
	 */
	private String findUnusedVariableName( String baseName, Variables variables ) {

		boolean firstLoop = true;
		String name = baseName;
		bigLoop: for( int i=1; i<Integer.MAX_VALUE; i++ ) {

			if( firstLoop )
				firstLoop = false;
			else
				name = baseName + i;

			for( Variable var : variables.getChildren()) {
				if( name.equals( var.getName()))
					continue bigLoop;
			} // End of loop: we checked all the variables and we did not find it

			break bigLoop;
		}

		return name;
	}


	/**
	 * Loads a WSDL definition.
	 * @param wsdlUri the WSDL URI
	 * @return the associated definition or null in case of failure or if it is not a WSDL
	 */
	private Definition loadWsdl( String wsdlUri ) {

		URI uri = URI.createURI( wsdlUri );
		Resource resource = null;
		ResourceSet editorResourceSet = this.fEditor.getResourceSet();
		try {
			resource = editorResourceSet.getResource( uri, true );

		} catch( Exception e ) {
			BPELUIPlugin.log( e, IStatus.ERROR );
		}

		Object loaded = null;
		if( resource != null && resource.getContents().size() > 0 )
			loaded = resource.getContents().get( 0 );

		return loaded instanceof Definition ? (Definition) loaded : null;
	}


	/**
	 * Finds a port type by name, looking in imports if necessary.
	 * @param wsdlDefinition the WSDL definition
	 * @param portTypeName the port type name
	 * @return the matching port type or null if it was not found
	 */
	private PortType findPortType( Definition wsdlDefinition, QName portTypeName ) {

		PortType rightPortType = null;

		// Watch current port types
		for( Object o : wsdlDefinition.getPortTypes().values()) {
			if( portTypeName.equals(((PortType) o).getQName())) {
				rightPortType = (PortType) o;
				break;
			}
		}

		// Get port types from the imports
		if( rightPortType == null ) {
			Map<?,?> imports = wsdlDefinition.getImports();
			for( Map.Entry<?,?> entry : imports.entrySet()) {

				List<?> list = (List<?>) entry.getValue();
				for( Object o : list ) {
					if( !( o instanceof Import ))
						continue;

					Definition impDef = ((Import) o).getEDefinition();
					if( impDef == null )
						continue;

					for( Object pt : impDef.getPortTypes().values()) {
						if( portTypeName.equals(((PortType) pt).getQName())) {
							rightPortType = (PortType) pt;
							break;
						}
					}
				}
			}
		}

		return rightPortType;
	}
}
