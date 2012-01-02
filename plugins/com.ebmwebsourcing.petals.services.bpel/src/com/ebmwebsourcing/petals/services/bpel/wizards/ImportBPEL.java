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
public class ImportBPEL implements BpelWizardStrategy {

	private String importedBpel;
	private String wsdlUrl;
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.bpel.wizards.BpelWizardStrategy#perform(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void perform(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		
		/*
		abstractEndpoint.eSet(BpelPackage.Literals.bpel, bpel.getName());
			FileImportManager mng = FileImportManager.getFileImportManager();
			
			mng.registerXmlFileElement( bpelElement, url.toString(), null );

			Set<String> uris = new HashSet<String> ();
			for( URI uri : PetalsBpelModules.getWsdlImportUris( url, true, true ).values())
				uris.add( uri.toString());

			for( URI uri : PetalsBpelModules.getWsdlImportUris( url, false, false ).values())
				uris.add( uri.toString());

			for( String uri : uris ) {
				mng.registerXmlFileElement( null, uri, null );
			}
		*/
	}

	/**
	 * @return the importedBpel
	 */
	public String getImportedBpel() {
		return importedBpel;
	}


	/**
	 * @param importedBpel the importedBpel to set
	 */
	public void setImportedBpel(String importedBpel) {
		this.importedBpel = importedBpel;
	}

	/**
	 * @param wsdlUrl
	 */
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
}