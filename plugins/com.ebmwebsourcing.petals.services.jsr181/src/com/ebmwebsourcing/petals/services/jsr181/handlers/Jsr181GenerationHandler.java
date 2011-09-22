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
package com.ebmwebsourcing.petals.services.jsr181.handlers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.Jsr181Provides1x;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils.JaxWsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser.JbiBasicBean;
import com.ebmwebsourcing.petals.services.jsr181.PetalsJsr181Plugin;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181GenerationHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( final ExecutionEvent event ) throws ExecutionException {

		final IJavaProject javaProject = getSelectedJavaProject();
		if( javaProject != null ) {

			// Need for a confirmation?
			String msg = "Would you like to generate a new jbi.xml file right after the WSDL generation?\n"
						+ "This jbi.xml will rely on all the generated WSDL definitions.\n\n"
						+ "Please, note this will overwrite the existing one.";

			final boolean generateJbiXml = MessageDialog.openQuestion( new Shell(), "Also generate a new jbi.xml?", msg );

			// Define the generation context
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

				@Override
				protected void execute( IProgressMonitor monitor )
							throws CoreException, InterruptedException {

					try {
						monitor.beginTask( "Generating Petals required files...", IProgressMonitor.UNKNOWN );
						generateJsr181Files( javaProject, generateJbiXml, monitor );

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
				PetalsJsr181Plugin.log( e, IStatus.ERROR, "A problem occurred while generating files for the JSR-181." );
				MessageDialog.openError(
							new Shell(),
							"Generation Error",
							"A problem occurred while generating files for the JSR-181.\nCheck the log for more details." );
			}
		}

		return null;
	}


	/**
	 * Updates the handler state from the current selection.
	 * @return the first selected SU Java project, or null if the selection is not a SU Java project
	 */
	private IJavaProject getSelectedJavaProject() {

		IJavaProject javaProject = null;
		try {
			final ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			Object o;
			if( selection != null
						&& ! selection.isEmpty()
						&& selection instanceof IStructuredSelection
						&& ( o = ((IStructuredSelection) selection).getFirstElement()) instanceof IProject) {

				IProject p = (IProject) o;
				if( p.isAccessible()
							&& p.hasNature( JavaCore.NATURE_ID )
							&& "petals-se-jsr181".equalsIgnoreCase( PetalsServicesProjectUtils.getComponentName( p )))
					javaProject = JavaCore.create( p );
			}

		} catch( CoreException e ) {
			PetalsJsr181Plugin.log( e, IStatus.WARNING );

		} catch( Exception e ) {
			PetalsJsr181Plugin.log( e, IStatus.WARNING );
		}

		return javaProject;
	}


	/**
	 * Generates files for the JSR-181.
	 * <p>
	 * 1. All the WSDL and XML schemas are deleted from the "jbi" directory.<br />
	 * 2. Are listed all the classes contained in the project and annotated @WebService.<br />
	 * 3. For each annotated class, a WSDL is generated in the "jbi" directory.<br />
	 * 4. Eventually, if the option is activated, a jbi.xml is generated from all the WSDL.
	 * </p>
	 * 
	 * @param javaProject
	 * @param generateJbiXml
	 * @param monitor
	 * @throws CoreException
	 */
	public static void generateJsr181Files(
				IJavaProject javaProject,
				boolean generateJbiXml,
				IProgressMonitor monitor )
							throws CoreException {

		// Delete all the WSDL and XSD files from the "jbi" directory
		IFolder jbiFolder = javaProject.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
		javaProject.getProject().refreshLocal( IResource.DEPTH_INFINITE, monitor );
		for( IResource res : jbiFolder.members()) {
			if( res instanceof IFile &&
						( res.getName().endsWith( ".wsdl" ) || res.getName().endsWith( ".xsd" )))
				res.delete( true, monitor );
		}

		// Get the classes that contain a JAX annotated service
		Map<String,String> classNameToServiceName = JaxWsUtils.getJaxAnnotatedJavaTypes( javaProject, monitor, true, false );

		// Create the WSDL
		Map<String,String> classNameToWsdlName = new HashMap<String,String>( classNameToServiceName.size());
		File targetDirectory = jbiFolder.getLocation().toFile();
		int generationErrorsCpt = 0;
		for( Map.Entry<String,String> entry : classNameToServiceName.entrySet()) {
			try {
				if( monitor != null && monitor.isCanceled())
					throw new OperationCanceledException();

				JaxWsUtils.INSTANCE.generateWsdl( entry.getKey(), targetDirectory, javaProject );

				// Only store those whose were generated without error
				classNameToWsdlName.put( entry.getKey(), entry.getValue() + ".wsdl" );

			} catch( IOException e ) {
				PetalsJsr181Plugin.log( e, IStatus.ERROR );

			} catch( InterruptedException e ) {
				PetalsJsr181Plugin.log( e, IStatus.ERROR );

			} catch( JaxWsException e ) {
				generationErrorsCpt ++;
				PetalsJsr181Plugin.log( e, IStatus.ERROR );
			}
		}

		jbiFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		// Generate the jbi.xml?
		if( generateJbiXml )
			generateJbiXml( javaProject.getProject(), classNameToWsdlName, monitor );

		ResourceUtils.selectResourceInPetalsExplorer( true, jbiFolder );

		// Report errors
		if( generationErrorsCpt != 0 ) {
			final String msg = generationErrorsCpt + (generationErrorsCpt > 1 ? " errors" : " error")
						+ " occurred during the generation process.\nCheck the log for more details.";

			Display.getDefault().asyncExec( new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError( new Shell(), "Generation Error", msg );
				}
			});
		}
	}


	/**
	 * Generates the jbi.xml from the WSDL in the project.
	 * 
	 * @param project the SU project
	 * @param classNameToWsdlName map that associated a class name and a WSDL
	 * <p>
	 * Key = the qualified name of the annotated class<br />
	 * Value = the WSDL file name
	 * </p>
	 * 
	 * @param monitor the progress monitor
	 * @throws CoreException if the project resources could not be manipulated
	 */
	public static void generateJbiXml( IProject project, Map<String,String> classNameToWsdlName, IProgressMonitor monitor )
				throws CoreException {

		// Get the generation values
		List<Jsr181Provides1x> provides = new ArrayList<Jsr181Provides1x> ();
		for( Map.Entry<String,String> entry : classNameToWsdlName.entrySet()) {

			IFile wsdlFile = project.getFolder( PetalsConstants.LOC_RES_FOLDER ).getFile( entry.getValue());
			if( wsdlFile.exists()) {

				String uri = wsdlFile.getLocation().toFile().toURI().toString();
				List<JbiBasicBean> beans = WsdlParser.getInstance().parse( uri );
				for( JbiBasicBean bean : beans ) {

					Jsr181Provides1x jsr181Bean = new Jsr181Provides1x();

					jsr181Bean.setEndpointName( bean.getEndpointName());
					jsr181Bean.setServiceName( bean.getServiceName());
					jsr181Bean.setServiceNamespace( bean.getServiceNs());
					jsr181Bean.setInterfaceName( bean.getInterfaceName());
					jsr181Bean.setInterfaceNamespace( bean.getInterfaceNs());

					jsr181Bean.setWsdl( entry.getValue());
					jsr181Bean.setClassName( entry.getKey());
					provides.add( jsr181Bean );
				}
			}
		}

		// Generate and write the jbi.xml
		JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
		genDelegate.setComponentName( "petals-se-jsr181" );
		Jsr181Provides1x[] bps = new Jsr181Provides1x[ provides.size()];
		String jbiXmlContent = genDelegate.createJbiDescriptor( provides.toArray( bps )).toString();

		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );
	}
}
