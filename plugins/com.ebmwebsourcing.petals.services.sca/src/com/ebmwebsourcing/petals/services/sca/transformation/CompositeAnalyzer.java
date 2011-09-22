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

package com.ebmwebsourcing.petals.services.sca.transformation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.soa.sca.core.common.utils.ScaResourcesFilter;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils.InvalidScaModelException;
import org.eclipse.soa.sca.sca1_0.common.utils.SuperComposite;
import org.eclipse.soa.sca.sca1_0.model.sca.BaseReference;
import org.eclipse.soa.sca.sca1_0.model.sca.Binding;
import org.eclipse.soa.sca.sca1_0.model.sca.Component;
import org.eclipse.soa.sca.sca1_0.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_0.model.sca.Composite;
import org.eclipse.soa.sca.sca1_0.model.sca.Reference;
import org.eclipse.soa.sca.sca1_0.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_0.model.sca.Service;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;

import com.ebmwebsourcing.petals.common.generation.cdk5.components.ScaProvides11;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.sca.export.ScaBindingConverter;
import com.ebmwebsourcing.petals.services.sca.export.ScaSketchExportBean;
import com.ebmwebsourcing.petals.services.sca.export.ScaSketchExportException;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBeanBuilder.UnsupportedComponentException;

/**
 * A class to analyze and transform composite files to generate SAs.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CompositeAnalyzer {

	/**
	 * The composite file to analyze.
	 */
	private IFile compositeFile;

	/**
	 * 
	 */
	private File updatedSuperCompositeFile;

	/**
	 * The beans to create the jbi.xml for the SCa SU.
	 */
	private final List<ScaProvides11> scaProvidesBeans = new ArrayList<ScaProvides11> ();

	/**
	 * A list of errors that were found during the analysis.
	 */
	private final List<IStatus> statusList = new ArrayList<IStatus> ();



	/**
	 * Constructor.
	 */
	public CompositeAnalyzer() {
		// nothing
	}


	/**
	 * Analyzes the composite and prepares the creation of the SA.
	 * @param compositeFile
	 * @param monitor
	 * @return
	 * @throws InvalidScaModelException
	 * @throws IOException
	 * @throws ScaSketchExportException
	 */
	public SaImportBean analyze( IFile compositeFile, IProgressMonitor monitor )
	throws InvalidScaModelException, IOException, ScaSketchExportException {

		// Update the search locations
		this.compositeFile = compositeFile;

		// Get the composites in the "class path" => binary folders
		IProject[] refProjects;
		try {
			refProjects = this.compositeFile.getProject().getReferencedProjects();
		} catch( CoreException e ) {
			refProjects = new IProject[ 0 ];
		}

		// DFIXME: maybe we should validate the composite from the source directory
		Set<IContainer> binaryFolders = ScaResourcesFilter.getBinaryContainers( this.compositeFile.getProject());
		for( IProject project : refProjects ) {
			Set<IContainer> f = ScaResourcesFilter.getBinaryContainers( project );
			binaryFolders.addAll( f );
		}

		IContainer[] rootContainers = new IContainer[ binaryFolders.size()];
		rootContainers = binaryFolders.toArray( rootContainers );

		// Clear the lists
		this.scaProvidesBeans.clear();
		this.statusList.clear();
		this.updatedSuperCompositeFile = null;

		// Load the composite
		FrascatiModelUtils scaModelUtils = new FrascatiModelUtils();
		Composite c = scaModelUtils.getCompositeFile( this.compositeFile );
		SuperComposite sc = new SuperComposite( c, rootContainers, scaModelUtils );

		// Prepare the introspection
		CompoundCommand compoundCommand = new CompoundCommand( "Replacing bindings by JBI bindings" );
		List<SuImportBean> suImportBeans = new ArrayList<SuImportBean>();

		// Services first
		for( Service service : sc.getService())
			processService( service, suImportBeans, compoundCommand, scaModelUtils );

		// References then
		Set<BaseReference> references = new HashSet<BaseReference> ();
		for( Component component : sc.getComponent()) {
			for( ComponentReference cr : component.getReference()) {
				references.add( cr );
			}
		}

		for( Reference reference : sc.getReference()) {
			references.removeAll( reference.getPromote());
			references.add( reference );
		}

		for( BaseReference ref : references ) {
			if( ! ref.getBinding().isEmpty())
				processReference( ref, suImportBeans, compoundCommand, scaModelUtils );
		}

		// Update and save the SUPER composite in the OS temporary directory
		scaModelUtils.getEditingDomain().getCommandStack().execute( compoundCommand );
		Map<Object,Object> saveOptions = new HashMap<Object,Object>();
		saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));

		IPath path = new Path( System.getProperty( "java.io.tmpdir" )).append( UUID.randomUUID().toString());
		URI uri = URI.createFileURI( path.toString());
		sc.eResource().setURI( uri );
		sc.eResource().save( saveOptions );
		this.updatedSuperCompositeFile = path.toFile();

		return null;
	}


	/**
	 * Processes a SCA reference.
	 * <p>
	 * The following steps are performed:
	 * </p>
	 * <ul>
	 * 	<li>Get the SCA bindings (on the composite or the promoted component references).</li>
	 *  <li>Check the support of each found binding.</li>
	 *  <li>Generate the provides SU that will be referenced by the JBI binding.</li>
	 *  <li>Build a single JBI binding to import the SCA reference in the bus.</li>
	 *  <li>Replace the current bindings by the JBI binding (in the composite).</li>
	 * </ul>
	 * 
	 * @param reference a base reference
	 * @param suImportBeans a list of import beans to fill-in
	 * @param compoundCommand a compound command to update
	 * @param scaModelUtils the SCA model utilities
	 * @throws ScaSketchExportException if this service has only unsupported bindings
	 */
	private void processReference(
				BaseReference reference,
				List<SuImportBean> suImportBeans,
				CompoundCommand compoundCommand,
				FrascatiModelUtils scaModelUtils )
	throws ScaSketchExportException {

		// For each of them, check their support and build a JBI binding to replace them all
		JBIBinding jbiBinding = null;
		for( Binding binding : reference.getBinding()) {
			if( ! ScaBindingConverter.INSTANCE.isSupported( binding )) {
				String msg = "Petals does not support this SCA binding: " + binding.getClass() + ".";
				IStatus status = new Status( IStatus.WARNING, PetalsScaPlugin.PLUGIN_ID, msg );
				this.statusList.add( status );
			}

			try {
				ScaSketchExportBean exportBean = ScaBindingConverter.INSTANCE.createPetalsProvider( binding, reference );

			} catch( UnsupportedComponentException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO: create a unique JBI binding
			// FIXME: how can we sure all the references define a same service?
		}


		// If no JBI binding was built, do not go farther
		if( jbiBinding == null ) {
			String msg = "The SCA reference " + reference.getName() + " cannot be exposed in Petals (no compatible binding).";
			IStatus status = new Status( IStatus.ERROR, PetalsScaPlugin.PLUGIN_ID, msg );
			this.statusList.add( status );
			throw new ScaSketchExportException();
		}


		// Replace the current bindings
		EList<JBIBinding> compositeJbiBindings = new BasicEList<JBIBinding> ();
		compositeJbiBindings.add( jbiBinding );
		SetCommand setCommand = new SetCommand(
					scaModelUtils.getEditingDomain(),
					reference,
					ScaPackage.Literals.BASE_REFERENCE__BINDING,
					compositeJbiBindings );
		compoundCommand.append( setCommand );
	}


	/**
	 * Processes a SCA service.
	 * <p>
	 * The following steps are performed:
	 * </p>
	 * <ul>
	 * 	<li>Get the SCA bindings (on the composite or the promoted component services).</li>
	 *  <li>Check the support of each found binding.</li>
	 *  <li>Build a single JBI binding to expose the SCA service in the bus.</li>
	 *  <li>Create the 'provides' block for the SCA SU.</li>
	 *  <li>Replace the current bindings by the JBI binding (in the composite).</li>
	 *  <li>Generate the consumes SU to replace the SCA bindings.</li>
	 * </ul>
	 * 
	 * @param service a composite service
	 * @param suImportBeans a list of import beans to fill-in
	 * @param compoundCommand a compound command to update
	 * @param scaModelUtils the SCA model utilities
	 * @throws ScaSketchExportException if this service has only unsupported bindings
	 */
	private void processService(
				Service service,
				List<SuImportBean> suImportBeans,
				CompoundCommand compoundCommand,
				FrascatiModelUtils scaModelUtils )
	throws ScaSketchExportException {

		// Get the bindings
		EList<Binding> serviceBindings = new BasicEList<Binding>();
		if( ! service.getBinding().isEmpty())
			serviceBindings.addAll( service.getBinding());
		else
			serviceBindings.addAll( service.getPromote().getBinding());


		// For each of them, check their support and build a JBI binding to replace them all
		JBIBinding jbiBinding = null;
		for( Binding binding : serviceBindings ) {
			if( ! ScaBindingConverter.INSTANCE.isSupported( binding )) {
				String msg = "Petals does not support this SCA binding: " + binding.getClass() + ".";
				IStatus status = new Status( IStatus.WARNING, PetalsScaPlugin.PLUGIN_ID, msg );
				this.statusList.add( status );
			}

			if( jbiBinding == null ) {
				jbiBinding = ScaBindingConverter.INSTANCE.buildJbiBinding(
							binding, (Composite) service.eContainer(), service.getName());
			}
		}


		// If no JBI binding was built, do not go farther
		if( jbiBinding == null ) {
			String msg = "The SCA service " + service.getName() + " cannot be exposed in Petals (no compatible binding).";
			IStatus status = new Status( IStatus.ERROR, PetalsScaPlugin.PLUGIN_ID, msg );
			this.statusList.add( status );
			throw new ScaSketchExportException();
		}


		// Create the SCA provides associated with the JBI binding
		ScaProvides11 bean = new ScaProvides11 ();
		bean.setServiceName( jbiBinding.getServiceName());
		bean.setInterfaceName( jbiBinding.getInterfaceName());
		bean.setEndpointName( jbiBinding.getEndpointName());
		bean.setServiceNamespace( jbiBinding.getServiceNamespace());
		bean.setInterfaceNamespace( jbiBinding.getInterfaceNamespace());

		bean.setWsdl( jbiBinding.getWsdl());	// Might need to be updated later in the export process
		String relativeLoc = IoUtils.getRelativeLocationToFile(
					this.compositeFile.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER ).getLocation().toFile(),
					this.compositeFile.getLocation().toFile());

		bean.setCompositeFileLocation( relativeLoc );
		this.scaProvidesBeans.add( bean );


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


		// Go through the bindings a second time, and build the consumers for this binding
		for( Binding binding : serviceBindings ) {
			try {
				SuImportBean suImportBean = ScaBindingConverter.INSTANCE.createPetalsConsumer( binding, jbiBinding );
				if( suImportBean != null )
					suImportBeans.add( suImportBean );

			} catch( UnsupportedComponentException e ) {
				String msg = "No SU project could be created for the binding " + binding.getClass().getSimpleName() + " (consumes mode).";
				IStatus status = new Status( IStatus.ERROR, PetalsScaPlugin.PLUGIN_ID, msg );
				this.statusList.add( status );
				throw new ScaSketchExportException();
			}
		}
	}
}
