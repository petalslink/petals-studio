/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.soa.sca.sca1_0.model.sca.BaseReference;
import org.eclipse.soa.sca.sca1_0.model.sca.JavaInterface;
import org.eclipse.soa.sca.sca1_0.model.sca.Reference;
import org.eclipse.soa.sca.sca1_0.model.sca.ScaFactory;
import org.eclipse.soa.sca.sca1_0.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.FrascatiFactory;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils.JaxWsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;

/**
 * This wizards simplifies the creation of WSDL references in an SCA application.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsReferenceWizard extends Wizard implements INewWizard {

	private PetalsReferenceWizardPage page;
	private IStructuredSelection selection;


	/**
	 * Constructor.
	 */
	public PetalsReferenceWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "New WSDL Reference" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.page = new PetalsReferenceWizardPage( this.selection );
		addPage( this.page );
	}


	/**
	 * This method is called when 'Finish' button is pressed in the wizard.
	 */
	@Override
	public boolean performFinish() {

		// Create output folder?
		IClasspathEntry outputFolder = null;
		Object o = this.page.getOutputFolder();
		if( o instanceof String ) {
			try {
				// Create the source folder
				IJavaProject jp = this.page.getTargetProject();
				IPath srcFolderPath = new Path((String) o);
				IFolder folder = jp.getProject().getFolder( srcFolderPath );
				if( ! folder.exists())
					folder.create( true, true, null );

				// Add the source entry
				Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
				entries.addAll( Arrays.asList( jp.getRawClasspath()));

				IPath path = jp.getProject().getLocation().append( srcFolderPath );
				outputFolder = JavaCore.newSourceEntry( path );
				if( ! entries.contains( outputFolder )) {
					entries.add( outputFolder );
					IClasspathEntry[] newEntries = entries.toArray( new IClasspathEntry[ entries.size()]);
					jp.setRawClasspath( newEntries, null );
				}

			} catch( Exception e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR );
				String msg = "An error occurred while creating the source folder.\nCheck the log for more details.";
				MessageDialog.openError( getShell(), "Java Errors", msg );
			}

		} else {
			outputFolder = (IClasspathEntry) o;
		}

		// If the output folder was found, go on...
		if( outputFolder != null ) {
			final IFolder srcFolder = ResourcesPlugin.getWorkspace().getRoot().getFolder( outputFolder.getPath());

			// Create the runnable and execute it
			IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run( IProgressMonitor monitor )
				throws InvocationTargetException {
					try {
						monitor.beginTask( "Creating the Petals reference...", IProgressMonitor.UNKNOWN );
						updateComposite( srcFolder.getLocation(), monitor );

					} catch( ScaReferenceException e ) {
						throw new InvocationTargetException( e );

					} finally {
						monitor.done();
					}
				}
			};

			try {
				getContainer().run( true, false, op );
				ResourceUtils.selectResourceInJavaView( true, srcFolder );

			} catch( InterruptedException e ) {
				// nothing

			} catch( InvocationTargetException e ) {
				Throwable realException = e.getTargetException();
				PetalsScaPlugin.log( realException, IStatus.ERROR );
				String msg = "One or several errors occurred during the generation.\nCheck the log for more details.";
				MessageDialog.openError( getShell(), "Generation Errors", msg );
			}
		}

		return true;
	}


	/**
	 * Updates the composite and generates the data-binding classes.
	 * @param sourcePath
	 * @param monitor
	 */
	private void updateComposite( IPath sourcePath, IProgressMonitor monitor )
	throws ScaReferenceException {

		final EditingDomain ed = this.page.getScaModelUtils().getEditingDomain();
		CompoundCommand compoundCmd = new CompoundCommand( "Composite update from WSDL wizard" );
		IProject scaProject = this.page.getTargetProject().getProject();
		EndpointBean edptBean = this.page.getEdptBean();

		// Get the reference first - true reference => no need of the container
		BaseReference reference;
		Object o = this.page.getReference();
		if( o instanceof String ) {

			// Create the reference
			String refName = (String) o;
			reference = ScaFactory.eINSTANCE.createComponentReference();
			reference.setName( refName );
			compoundCmd.append( new AddCommand( ed, this.page.getReferenceOwner().getReference(), reference ));

			// Do we need to promote it?
			if( this.page.isPromoteRef()) {
				Reference compositeReference = ScaFactory.eINSTANCE.createReference();
				compositeReference.setName( refName );
				EList<Reference> references =
					((org.eclipse.soa.sca.sca1_0.model.sca.Composite) this.page.getReferenceOwner().eContainer()).getReference();
				compoundCmd.append( new AddCommand( ed, references, compositeReference ));
				compoundCmd.append( new AddCommand( ed, compositeReference.getPromote(), reference ));
				reference = compositeReference;
			}

		} else {
			reference = (BaseReference) o;
		}

		// Import the WSDL in the JBI directory
		java.net.URI wsdlUri = edptBean.getWsdlUri();
		if( wsdlUri == null ) {
			String msg = "No WSDL could be found for a Petals end-point to be referenced by a SCA application.";
			throw new ScaReferenceException( msg );
		}

		monitor.worked( 1 );
		IFolder jbiFolder = scaProject.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
		if( ! jbiFolder.exists()) {
			try {
				jbiFolder.create( true, true, monitor );

			} catch( CoreException e ) {
				throw new ScaReferenceException( e );
			}
		}

		File jbiFile = jbiFolder.getLocation().toFile();
		Map<String,File> importResultMap =
			new WsdlImportUtils().importWsdlOrXsdAndDependencies( jbiFile, wsdlUri.toString());

		File wsdlFile = importResultMap.get( wsdlUri.toString());
		if( wsdlFile == null
					|| ! wsdlFile.exists()) {
			String msg = "The WSDL import of a Petals end-point failed (SCA creation).";
			throw new ScaReferenceException( msg );
		}

		// Starting with the JAX-WS part
		try {
			// Generate the Java classes
			Set<String> jaxWsInterfaces =
				JaxWsUtils.getJaxAnnotatedJavaTypes( this.page.getTargetProject(), monitor, false, true ).keySet();

			try {
				monitor.worked( 1 );
				JaxWsUtils.INSTANCE.generateWsClient( wsdlFile.toURI(), sourcePath.toFile());

			} catch( IOException e ) {
				throw new ScaReferenceException( e );

			} catch( InterruptedException e ) {
				throw new ScaReferenceException( e );

			} catch( JaxWsException e ) {
				throw new ScaReferenceException( e );
			}

			// Find the new generated JAX-WS interface
			monitor.worked( 1 );
			Set<String> newJaxWsInterfaces =
				JaxWsUtils.getJaxAnnotatedJavaTypes( this.page.getTargetProject(), monitor, false, true ).keySet();

			newJaxWsInterfaces.removeAll( jaxWsInterfaces );
			if( newJaxWsInterfaces.size() != 1 ) {
				String msg = "Unexpected number of Jax-WS interfaces (" + newJaxWsInterfaces.size() + ").";
				throw new ScaReferenceException( msg );
			}

			// Create the Java interface
			monitor.worked( 1 );
			JavaInterface javaInterface = ScaFactory.eINSTANCE.createJavaInterface();
			javaInterface.setInterface( newJaxWsInterfaces.iterator().next());
			compoundCmd.append( new SetCommand(
						ed, reference,
						ScaPackage.Literals.BASE_REFERENCE__INTERFACE, javaInterface ));

		} catch( CoreException e ) {
			throw new ScaReferenceException( e );
		}


		// Create the JBI binding
		monitor.worked( 1 );
		JBIBinding jbiBinding = FrascatiFactory.eINSTANCE.createJBIBinding();
		jbiBinding.setName( reference.getName() + "JbiBinding" );

		String wsdlLocation = IoUtils.getRelativeLocationToFile( jbiFile, wsdlFile );
		jbiBinding.setWsdl( wsdlLocation );

		jbiBinding.setInterfaceName( edptBean.getInterfaceName().getLocalPart());
		jbiBinding.setInterfaceNamespace( edptBean.getInterfaceName().getNamespaceURI());

		if( this.page.isInvokeByEdpt())
			jbiBinding.setEndpointName( edptBean.getEndpointName());

		if( this.page.isInvokeBySrv()) {
			jbiBinding.setServiceName( edptBean.getServiceName().getLocalPart());
			jbiBinding.setServiceNamespace( edptBean.getServiceName().getNamespaceURI());
		}

		compoundCmd.append( new AddCommand( ed, reference.getBinding(), jbiBinding ));


		// Execute the command
		monitor.worked( 1 );
		if( compoundCmd.canExecute())
			ed.getCommandStack().execute( compoundCmd );
		else
			PetalsScaPlugin.log( "Thge compound command could not be executed (new Petals reference).", IStatus.ERROR );


		// Save changes
		monitor.worked( 1 );
		Map<Object,Object> saveOptions = new HashMap<Object,Object>();
		saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));

		for( Resource resource : ed.getResourceSet().getResources()) {
			if( ! resource.getContents().isEmpty()
						&& ! ed.isReadOnly( resource )) {
				try {
					resource.save( saveOptions );
					break;

				} catch( Exception exception ) {
					throw new ScaReferenceException( exception );
				}
			}
		}

		monitor.worked( 1 );
		((BasicCommandStack) ed.getCommandStack()).saveIsDone();
		try {
			this.page.getCompositeFile().refreshLocal( IResource.DEPTH_ZERO, monitor );

		} catch( CoreException e ) {
			// nothing
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		this.selection = selection;

		// Save all the files
		PlatformUI.getWorkbench().saveAllEditors( true );
	}
}
