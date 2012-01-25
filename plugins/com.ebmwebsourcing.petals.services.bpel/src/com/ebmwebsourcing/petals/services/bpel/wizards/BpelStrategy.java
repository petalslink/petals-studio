/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.parsers.WsdlParser;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;
import org.eclipse.bpel.model.resource.BPELResourceFactoryImpl;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.wizards.NewBpelFileFirstPage.BpelCreationMode;
import org.eclipse.bpel.ui.wizards.NewBpelFileWizardUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Port;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.Service;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.util.JbiResourceFactoryImpl;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class BpelStrategy implements FinishServiceCreationStrategy {

	private IProject project;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy
	 * #finishWizard(com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard,
	 * com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void finishWizard( AbstractServiceUnitWizard wizard, AbstractEndpoint endpoint, IProgressMonitor monitor )
	throws Exception {

		// Create the BPEL stuff
		final BpelSuWizard wiz = (BpelSuWizard) wizard;
		String bpelName = wiz.firstPage.getProcessTemplateProperties().get( "processName" ) + ".bpel";
		final IFile bpelFile = this.project.getFolder( PetalsConstants.LOC_RES_FOLDER ).getFile( bpelName );

		monitor.subTask( "Creating the process..." );
		NewBpelFileWizardUtils utils = new NewBpelFileWizardUtils( wiz.firstPage, wiz.wsdlPage, wiz.portTypePage, bpelFile );
		if( wiz.firstPage.getCreationMode() == BpelCreationMode.GENERATE_BPEL_FROM_WSDL )
			utils.createResourcesFromWsdl( monitor );
		else
			utils.createResourcesFromTemplate( monitor );


		// Analyze the created BPEL file to generate a jbi.xml file
		Assert.isTrue( bpelFile.exists());
		List<JbiXmlBean> beans = getJbiXmlBean( bpelFile );


		// Create the jbi.xml
		final IFile jbiFile = this.project.getFile( PetalsConstants.LOC_JBI_FILE );
		Jbi jbiInstance = JbiFactory.eINSTANCE.createJbi();
		jbiInstance.setVersion( new BigDecimal( "1.0" ));
		jbiInstance.setServices( JbiFactory.eINSTANCE.createServices());
		jbiInstance.getServices().setBindingComponent( false );
		for( JbiXmlBean bean : beans ) {
			Provides provides = JbiFactory.eINSTANCE.createProvides();
			provides.setInterfaceName( bean.interfaceName );
			provides.setServiceName( bean.serviceName );
			provides.setEndpointName( bean.endpointName );

			String wsdlPath = IoUtils.getRelativeLocationToFile( jbiFile.getLocation().toFile(), bean.wsdlFile );
			provides.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, wsdlPath );
			provides.eSet( BpelPackage.Literals.BPEL_PROVIDES__BPEL, bpelFile.getName());
			provides.eSet( BpelPackage.Literals.BPEL_PROVIDES__POOLSIZE, 1 );

			jbiInstance.getServices().getProvides().add( provides );
		}

		// Create the project
		createProject( wizard, monitor );


		// Write the jbi.xml file
		monitor.subTask( "Creating the jbi.xml..." );
		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createPlatformResourceURI( jbiFile.getFullPath().toString(), true);
		Resource resource = new JbiResourceFactoryImpl().createResource( emfUri );
		resource.getContents().add(jbiInstance);
		resource.save( Collections.EMPTY_MAP );
		monitor.worked( 1 );


		// Refresh the workspace
		try {
			bpelFile.getProject().refreshLocal( IResource.DEPTH_INFINITE, null );
		} catch( CoreException e ) {
			BPELUIPlugin.log( e );
		}


		// Open the file?
		wiz.getShell().getDisplay().asyncExec( new Runnable() {
			@Override
			public void run() {
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor( page, bpelFile );
					BasicNewResourceWizard.selectAndReveal( bpelFile, page.getWorkbenchWindow());

				} catch( PartInitException e ) {
					BPELUIPlugin.log( e );
				}
			}
		});
	}


	/**
	 * Creates the project.
	 * <p>
	 * If the project already exists, this method does nothing.
	 * </p>
	 *
	 * @param wizard
	 * @param monitor
	 * @throws CoreException
	 * @throws IOException
	 */
	public void createProject( AbstractServiceUnitWizard wizard, IProgressMonitor monitor ) throws CoreException, IOException {

		if( this.project != null )
			return;

		monitor.beginTask( "", IProgressMonitor.UNKNOWN );
		monitor.subTask( "Creating the project structure..." );

		ProjectPage ppage = (ProjectPage) wizard.getPage( ProjectPage.PAGE_NAME );
		Assert.isNotNull( ppage );
		URI locationURI = ppage.isAtDefaultlocation() ? null : ppage.computeProjectLocation().toURI();
		this.project = PetalsServicesProjectUtils.createSuProject(
				ppage.getProjectName(),
				locationURI,
				wizard.getComponentVersionDescription().getComponentName(),
				wizard.getComponentVersionDescription().getComponentVersion(),
				wizard.getComponentVersionDescription().getComponentAlias(),
				false,
				monitor );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy
	 * #getSUProject(com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IProject getSUProject( AbstractServiceUnitWizard wizard, IProgressMonitor monitor ) {

		if (this.project == null) {
			try {
				createProject( wizard, monitor );

			} catch (Exception ex) {
				PetalsServicesPlugin.log(ex, IStatus.ERROR);
			}
		}

		return this.project;
	}


	/**
	 * Parses the BPEL process definition and builds a set of JBI beans to create jbi.xml files.
	 * @param bpelFile the BPEL file
	 * @return a non-null list of beans to generate jbi.xml files for the BPEL SE
	 */
	private static List<JbiXmlBean> getJbiXmlBean( IFile bpelFile ) {

		List<JbiXmlBean> beans = new ArrayList<JbiXmlBean> ();
		try {
			// Load the BPEL
			ResourceSet resourceSet = WsdlParser.createBasicResourceSetForWsdl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "bpel", new BPELResourceFactoryImpl());
			resourceSet.getPackageRegistry().put( PartnerlinktypePackage.eNS_URI, PartnerlinktypePackage.eINSTANCE );
			resourceSet.getPackageRegistry().put( BPELPackage.eNS_URI, BPELPackage.eINSTANCE );

			org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createPlatformResourceURI( bpelFile.getFullPath().toString(), true );
			Resource res = resourceSet.getResource( emfUri, true );
			Process process = (Process) res.getContents().get( 0 );

			// Find all the interfaces implemented by the process
			// Hint: they have the "myRole" role
			List<PortType> portTypes = new ArrayList<PortType> ();
			if( process.getPartnerLinks() != null ) {	// Empty template => no PL
				for( PartnerLink pl : process.getPartnerLinks().getChildren()) {
					if( pl.getMyRole() != null )
						portTypes.add( (PortType) pl.getMyRole().getPortType());
				}
			}

			if( portTypes.isEmpty())
				PetalsBpelPlugin.log( "No interface was found while analyzing the BPEL process.", IStatus.ERROR );
			else {
				for( Object o : ((Definition) portTypes.get( 0 ).eContainer()).getEServices()) {
					if( !( o instanceof Service ))
						continue;

					for( Object oo : ((Service) o).getEPorts()) {
						Port port = (Port) oo;
						PortType portType = port.getEBinding().getEPortType();
						if( ! portTypes.contains( portType ))
							continue;

						JbiXmlBean bean = new JbiXmlBean();
						bean.interfaceName = portType.getQName();
						bean.serviceName = ((Service) o).getQName();
						bean.endpointName = port.getName();
						bean.wsdlFile = EmfUtils.getUnderlyingFile( port );
						beans.add( bean );
					}
				}
			}

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}

		return beans;
	}


	/**
	 * A simple bean, used only in this class.
	 */
	private static class JbiXmlBean {
		QName interfaceName, serviceName;
		String endpointName;
		File wsdlFile;
	}
}
