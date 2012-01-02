/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.wizards;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class CreateBPELFromScratch implements BpelWizardStrategy {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.bpel.wizards.BpelWizardStrategy#perform()
	 */
	@Override
	public void perform(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		/*String processName = resourceFolder.getProject().getName();

		if( processName.startsWith( "su-BPEL-" )) {
			processName = processName.substring( 8 );
			processName = processName.replaceFirst( "-provide.*", "" );
		}

		processName = processName.replaceAll( "\\W", "_" );
		String bpelName = processName + ".bpel";

		IFile file = resourceFolder.getFile( processName + "Definition.wsdl" );
		String result = new ProcessWsdl().generate( processName );
		ComponentCreationWizard.createFile( file, result, monitor );

		file = resourceFolder.getFile( processName + ".bpel" );
		result = new com.ebmwebsourcing.petals.services.bpel.generated.Process().generate( processName );
		ComponentCreationWizard.createFile( file, result, monitor );

		file = resourceFolder.getFile( processName + "Artifacts.wsdl" );
		result = new ProcessArtifacts().generate( processName );
		ComponentCreationWizard.createFile( file, result, monitor );
		
		abstractEndpoint.eSet(BpelPackage.Literals.BPEL, bpelName);*/
	}

}
