package com.ebmwebsourcing.petals.services.su.wizards;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.util.JbiResourceFactoryImpl;

public class CreateJBIStrategy implements FinishServiceCreationStrategy {

	private IProject project;

	@Override
	public void finishWizard(ComponentCreationWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor) throws Exception {
		Jbi jbiInstance;
		jbiInstance = JbiFactory.eINSTANCE.createJbi();
		jbiInstance.setVersion(new BigDecimal("1.0"));
		jbiInstance.setServices(JbiFactory.eINSTANCE.createServices());
		jbiInstance.getServices().setBindingComponent(wizard.getComponentVersionDescription().isBc());
		if (endpoint instanceof Provides) {
			jbiInstance.getServices().getProvides().add((Provides)endpoint);
		} else {
			jbiInstance.getServices().getConsumes().add((Consumes)endpoint);
		}
		
		createProject(wizard, monitor);

		IFile jbiFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		
		monitor.subTask( "Creating the jbi.xml..." );
		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createPlatformResourceURI(jbiFile.getFullPath().toString(), true);
		Resource resource = new JbiResourceFactoryImpl().createResource( emfUri );
		resource.getContents().add(jbiInstance);
		resource.save( Collections.EMPTY_MAP );
		monitor.worked( 1 );
	
		
		// addutuinak
		
			// Open the jbi.xml?
			// Do not open it in the WorkspaceModifyOperation
			// The project viewer must be updated before selecting anything in it
			final IFile jbiXmlFile = getSUProject(wizard, new NullProgressMonitor()).getFile( PetalsConstants.LOC_JBI_FILE );
			if( wizard.getSettings().openJbiEditor) {
				wizard.getShell().getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						try {
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IDE.openEditor( page, jbiXmlFile );
						} catch( PartInitException e ) {
							PetalsServicesPlugin.log( e, IStatus.ERROR );
						}
					}
				});
			}
		}

	public void createProject(ComponentCreationWizard wizard, IProgressMonitor monitor) throws CoreException, IOException {
		if (project != null) {
			return;
		}
		monitor.beginTask( "", IProgressMonitor.UNKNOWN );
		monitor.subTask( "Creating the project structure..." );

		URI locationURI = wizard.projectPage.isAtDefaultlocation() ? null : wizard.projectPage.computeProjectLocation().toURI();
		project = PetalsServicesProjectUtils.createSuProject(
				wizard.projectPage.getProjectName(),
				locationURI,
				wizard.getComponentVersionDescription().getComponentName(),
				wizard.getComponentVersionDescription().getComponentVersion(),
				wizard.getComponentVersionDescription().getComponentAlias(),
				wizard.isJavaProject(),
				monitor );
	}

	@Override
	public IProject getSUProject(ComponentCreationWizard wizard, IProgressMonitor monitor) {
		if (project == null) {
			try {
				createProject(wizard, monitor);
			} catch (Exception ex) {
				PetalsServicesPlugin.log(ex, IStatus.ERROR);
			}
		}
		return project;
	}

}
