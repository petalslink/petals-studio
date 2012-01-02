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
import org.ow2.easywsdl.wsdl.api.Description;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class GenerateBPELFromWSDL implements BpelWizardStrategy {

	private String wsdlURL;
	private String bpelName;
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.bpel.wizards.BpelWizardStrategy#perform(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void perform(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		/*this.bpelName = new Path( this.wsdlLocation.getPath()).removeFileExtension().lastSegment();
		if( this.bpelName.endsWith( "?wsdl" ))
			this.bpelName = "baseProcess.bpel";
		else
			this.bpelName += ".bpel";
		model.eSet(BpelPackage.Literals.bpel, bpelName);
		suBean.setWsdlUrl( this.wsdlLocation.toString());

		Description desc = (Description) eclipseSuBean.customObjects.get( "wsdlDescription" );
		QName interfaceName = (QName) eclipseSuBean.customObjects.get( "wsdlName" );
		bpelName = bpelElement != null ? bpelElement.getValue() : null;

		BPELGenerator generator = new BPELGeneratorImpl();
		BPELProject project = generator.generateDefaultBPELProjectFromInterface( interfaceName, desc );
		if( bpelName == null ) {
			bpelName = project.getBpelFileName();
		}


		// Do not write the WSDL of the BPEL: imports are not processed.
		// Instead, we have to import it in the project.
		Map<String,File> urlToFile = new WsdlImportUtils().importWsdlOrXsdAndDependencies(
					resourceFolder.getLocation().toFile(),
					eclipseSuBean.getWsdlUrl());

		// Prepare the file names to update the generated documents
		String wsdlName = urlToFile.get( eclipseSuBean.getWsdlUrl()).getName();
		String artifactsName = resourceFolder.getFile( bpelName ).getLocation()
			.removeFileExtension().lastSegment() + "Artifacts.wsdl";


		// Write the WSDL artifacts
		Document doc = WSDL4BPELFactory.newInstance().newWSDLWriter().getDocument( project.getWsdlArtifacts());
		String content = write( doc );
		content = content.replaceAll( Pattern.quote( project.getWsdlFileName()), wsdlName );

		IFile file = resourceFolder.getFile( artifactsName );
		if( file.exists()) {
			file.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
		} else {
			file.create( new ByteArrayInputStream( content.getBytes()), true, monitor );
		}

		// Write the BPEL
		doc = new BPELWriterImpl().getDocument( project.getBpelProcess());
		content = write( doc );
		content = content.replaceAll( Pattern.quote( project.getWsdlFileName()), wsdlName );
		content = content.replaceAll( Pattern.quote( project.getArtifactFileName()), artifactsName );

		// Hack for bug PETALSSTUD-16 (not yet fixed in EasyBPEL)
		content = content.replaceAll( "myRole=\"mainPartnerRole\"", "myRole=\"" + interfaceName.getLocalPart() + "Role\"" );
		String abstractNsPattern = Pattern.quote( "xmlns:ns3=\"http://docs.oasis-open.org/wsbpel/2.0/process/abstract\"" );
		content = content.replaceAll( abstractNsPattern, "" );
		content = content.replaceAll( "ns3:", "ns2:" );
		// Hack for bug PETALSSTUD-16

		file = resourceFolder.getFile( bpelName );
		if( file.exists()) {
			file.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
		} else {
			file.create( new ByteArrayInputStream( content.getBytes()), true, monitor );
		}*/

	}

	/**
	 * @param wsdlDesc
	 */
	public void setWsdlDescription(Description wsdlDesc) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the wsdlURL
	 */
	public String getWsdlURL() {
		return wsdlURL;
	}

	/**
	 * @param wsdlURL the wsdlURL to set
	 */
	public void setWsdlURL(String wsdlURL) {
		this.wsdlURL = wsdlURL;
	}
}
