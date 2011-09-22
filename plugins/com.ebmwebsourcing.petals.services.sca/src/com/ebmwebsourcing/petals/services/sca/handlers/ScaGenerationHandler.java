/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.handlers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.soa.sca.core.common.utils.ScaResourcesFilter;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils.InvalidScaModelException;
import org.eclipse.soa.sca.sca1_0.common.utils.SuperComposite;
import org.eclipse.soa.sca.sca1_0.model.sca.Binding;
import org.eclipse.soa.sca.sca1_0.model.sca.Composite;
import org.eclipse.soa.sca.sca1_0.model.sca.JavaInterface;
import org.eclipse.soa.sca.sca1_0.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_0.model.sca.Service;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.FrascatiFactory;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.ScaProvides11;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser.JbiBasicBean;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.sca.transformation.FrascatiModelUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaGenerationHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( final ExecutionEvent event ) throws ExecutionException {

		final IFile compositeFile = getSelectedComposite();
		if( compositeFile != null ) {

			// Need for a confirmation?
			// Need for a confirmation?
			String msg = "Would you like to generate a new jbi.xml file right after the WSDL generation?\n"
				+ "This jbi.xml will rely on all the generated WSDL definitions.\n\n"
				+ "Please, note this will overwrite the existing one.";

			final boolean generateJbiXml = MessageDialog.openQuestion( new Shell(), "Also generate a new jbi.xml?", msg );

			// Define the generation context
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

				@Override
				protected void execute( IProgressMonitor monitor )
				throws CoreException, InvocationTargetException, InterruptedException {
					try {
						monitor.beginTask( "Generating JBI artifacts...", IProgressMonitor.UNKNOWN );
						generateMissingElements( generateJbiXml, compositeFile, monitor );

					} catch( InvalidScaModelException e ) {
						throw new InvocationTargetException( e );

					} catch( IOException e ) {
						throw new InvocationTargetException( e );

					} finally {
						monitor.done();
					}
				}
			};

			// Run it
			try {
				IProgressService ps = PlatformUI.getWorkbench().getProgressService();
				ps.busyCursorWhile( op );

			} catch( InterruptedException e ) {
				// nothing

			} catch( InvocationTargetException e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR, "A problem occurred while generating files for SCA." );
				MessageDialog.openError(
							new Shell(),
							"Generation Error",
				"A problem occurred while generating files for SCA. Check the log for more details." );
			}
		}

		return null;
	}


	/**
	 * Updates the handler state from the current selection.
	 * @return the first selected composite file, or null otherwise
	 */
	private IFile getSelectedComposite() {

		IFile compositeFile = null;
		try {
			final ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			Object o;
			if( selection != null
						&& ! selection.isEmpty()
						&& selection instanceof IStructuredSelection
						&& ( o = ((IStructuredSelection) selection).getFirstElement()) instanceof IFile
						&& "composite".equals(((IFile) o).getFileExtension())) {
				compositeFile = (IFile) o;
			}

		} catch( Exception e ) {
			PetalsScaPlugin.log( e, IStatus.WARNING );
		}

		return compositeFile;
	}


	/**
	 * Generates the jbi.xml from the JBI bindings in the selected composites.
	 * 
	 * @param generateJbiXml true to generate a new jbi.xml, false otherwise
	 * @param compositeFile the selected composite
	 * @param monitor the progress monitor
	 * @throws CoreException if the project resources could not be manipulated
	 * @throws InvalidScaModelException if the composite could not be read
	 * @throws IOException
	 */
	public void generateMissingElements( boolean generateJbiXml, IFile compositeFile, IProgressMonitor monitor )
	throws CoreException, InvalidScaModelException, IOException {

		// Load the composite
		FrascatiModelUtils scaModelUtils = new FrascatiModelUtils();
		Composite c = scaModelUtils.getCompositeFile( compositeFile );

		// Generate the missing WSDL
		createMissingJbiBindings( compositeFile, c, scaModelUtils, monitor );

		// Get the JBI bindings
		if( generateJbiXml )
			generateJbiXml( compositeFile, c, scaModelUtils, monitor );
	}


	/**
	 * Creates the missing JBI bindings.
	 * 
	 * @param compositeFile the composite file
	 * @param c the loaded composite
	 * @param scaModelUtils the model utilities
	 * @param monitor the progress monitor
	 * @throws IOException if the updated composite could not be saved
	 * @throws CoreException if the project could not be refreshed
	 */
	private void createMissingJbiBindings(
				IFile compositeFile,
				Composite c,
				FrascatiModelUtils scaModelUtils,
				IProgressMonitor monitor )
	throws IOException, CoreException {

		// Common variables
		CompoundCommand compoundCommand = new CompoundCommand( "Creating missing JBI bindings" );
		IFolder resFolder = compositeFile.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );

		// For all the services...
		for( Service service : c.getService()) {

			// Find a JBI binding
			JBIBinding jbiBinding = null;
			for( Binding binding : service.getBinding()) {
				if( binding instanceof JBIBinding ) {
					jbiBinding = (JBIBinding) binding;
					break;
				}
			}

			if( jbiBinding == null ) {
				for( Binding binding : service.getPromote().getBinding()) {
					if( binding instanceof JBIBinding ) {
						jbiBinding = (JBIBinding) binding;
						break;
					}
				}
			}

			// Find the Java interface
			String className = null;
			if( service.getInterface() instanceof JavaInterface )
				className = ((JavaInterface) service.getInterface()).getInterface();
			else if( service.getPromote().getInterface() instanceof JavaInterface )
				className = ((JavaInterface) service.getPromote().getInterface()).getInterface();

			if( className == null )
				continue;

			// Create the WSDL name
			String wsdlName;
			IContainer destination;
			if( jbiBinding == null
						|| jbiBinding.getWsdl() == null ) {
				int dotPosition = className.lastIndexOf( '.' );
				wsdlName = className.substring( dotPosition + 1 ) + ".wsdl";
				destination = resFolder;

			} else {
				IFile targetFile = resFolder.getFile( jbiBinding.getWsdl());
				wsdlName = targetFile.getName();
				destination = targetFile.getParent();
			}

			// Create a WSDL
			IJavaProject jp = JavaCore.create( compositeFile.getProject());
			File wsdlFile = WsdlExtUtils.generateWsdlFile( wsdlName, className, destination.getLocation().toString(), jp );
			if( ! wsdlFile.exists())
				continue;

			// If no JBI binding, generate one
			if( jbiBinding != null )
				continue;

			// Create a JBI binding
			jbiBinding = FrascatiFactory.eINSTANCE.createJBIBinding();
			List<JbiBasicBean> jbiBeans = WsdlParser.getInstance().parse( wsdlFile.toURI().toString());
			if( jbiBeans.size() > 0 ) {
				jbiBinding.setEndpointName( jbiBeans.get( 0 ).getEndpointName());
				jbiBinding.setInterfaceName( jbiBeans.get( 0 ).getInterfaceName());
				jbiBinding.setInterfaceNamespace( jbiBeans.get( 0 ).getInterfaceNs());
				jbiBinding.setServiceName( jbiBeans.get( 0 ).getServiceName());
				jbiBinding.setServiceNamespace( jbiBeans.get( 0 ).getServiceNs());

				String wsdlValue = IoUtils.getRelativeLocationToFile( resFolder.getLocation().toFile(), wsdlFile );
				jbiBinding.setWsdl( wsdlValue );

			} else {
				PetalsScaPlugin.log( wsdlFile + " does not contain any service.", IStatus.WARNING );
				continue;
			}

			// Replace the current bindings
			EList<JBIBinding> compositeJbiBindings = new BasicEList<JBIBinding> ();
			SetCommand setCommand = new SetCommand(
						scaModelUtils.getEditingDomain(),
						service.getPromote(),
						ScaPackage.Literals.BASE_SERVICE__BINDING,
						compositeJbiBindings );
			compoundCommand.append( setCommand );

			compositeJbiBindings.add( jbiBinding );
			setCommand = new SetCommand(
						scaModelUtils.getEditingDomain(),
						service,
						ScaPackage.Literals.BASE_SERVICE__BINDING,
						compositeJbiBindings );
			compoundCommand.append( setCommand );
		}

		// Save the composite
		scaModelUtils.getEditingDomain().getCommandStack().execute( compoundCommand );
		Map<Object,Object> saveOptions = new HashMap<Object,Object>();
		saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));
		c.eResource().save( saveOptions );
		compositeFile.getProject().refreshLocal( IResource.DEPTH_INFINITE, monitor );
	}


	/**
	 * Generates a new jbi.xml
	 * 
	 * @param compositeFile the composite file
	 * @param c the loaded composite
	 * @param scaModelUtils the model utilities
	 * @param monitor the progress monitor
	 * @throws CoreException if resource I/O failed
	 */
	private void generateJbiXml(
				IFile compositeFile,
				Composite c,
				FrascatiModelUtils scaModelUtils,
				IProgressMonitor monitor )
	throws CoreException {

		// Get the composites in the "class path" => binary folders
		IProject[] refProjects;
		try {
			refProjects = compositeFile.getProject().getReferencedProjects();
		} catch( CoreException e ) {
			refProjects = new IProject[ 0 ];
		}

		Set<IContainer> binaryFolders = ScaResourcesFilter.getBinaryContainers( compositeFile.getProject());
		for( IProject p : refProjects ) {
			Set<IContainer> f = ScaResourcesFilter.getBinaryContainers( p );
			binaryFolders.addAll( f );
		}

		IContainer[] rootContainers = new IContainer[ binaryFolders.size()];
		rootContainers = binaryFolders.toArray( rootContainers );

		// Load the super composite
		SuperComposite sc = new SuperComposite( c, rootContainers, scaModelUtils );
		List<ScaProvides11> providesList = new ArrayList<ScaProvides11> ();
		for( Service service : sc.getService()) {

			// Find the JBI bindings
			EList<Binding> serviceBindings = new BasicEList<Binding>();
			if( ! service.getBinding().isEmpty())
				serviceBindings.addAll( service.getBinding());
			else
				serviceBindings.addAll( service.getPromote().getBinding());

			JBIBinding jbiBinding = null;
			for( Binding binding : serviceBindings ) {
				if( binding instanceof JBIBinding ) {
					jbiBinding = (JBIBinding) binding;
					break;
				}
			}

			if( jbiBinding == null )
				continue;

			// Build the provides block
			ScaProvides11 bean = new ScaProvides11 ();
			bean.setServiceName( jbiBinding.getServiceName());
			bean.setInterfaceName( jbiBinding.getInterfaceName());
			bean.setEndpointName( jbiBinding.getEndpointName());
			bean.setServiceNamespace( jbiBinding.getServiceNamespace());
			bean.setInterfaceNamespace( jbiBinding.getInterfaceNamespace());

			bean.setWsdl( jbiBinding.getWsdl());
			String relativeLoc = IoUtils.getRelativeLocationToFile(
						compositeFile.getProject().getFolder( PetalsConstants.LOC_SRC_FOLDER ).getLocation().toFile(),
						compositeFile.getLocation().toFile());

			bean.setCompositeFileLocation( relativeLoc );
			providesList.add( bean );
		}

		// Build the associated jbi.xml
		ScaProvides11[] beans = new ScaProvides11[ providesList.size()];
		JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
		genDelegate.setComponentName( "petals-se-sca" );
		genDelegate.setVersion( "1.1" );
		genDelegate.setDefaultIsBc( false );
		StringBuilder sb =genDelegate.createJbiDescriptor( providesList.toArray( beans ));

		// Generate and write the jbi.xml
		IFile jbiXmlFile = compositeFile.getProject().getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( sb.toString().getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( sb.toString().getBytes()), true, true, monitor );
	}
}
