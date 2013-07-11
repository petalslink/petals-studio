/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.eip.designer.handlers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerSerializer;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.EipExportUtils;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * The default handler for the command "Validate jbi.xml".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExportAsServiceAssemblyHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		// Get the selection's content
		IFile eipChainFile = null;
		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof IFile
						&& ((IFile) o).getName().endsWith( ".peip" ))
				eipChainFile = (IFile) o;
			else
				eipChainFile = null;

		} catch( Exception e1 ) {
			// nothing
		}

		// Load the model
		EipChain eipChain = null;
		if( eipChainFile != null ) {
			try {
				eipChain = EipDesignerSerializer.INSTANCE.read( eipChainFile.getLocation().toFile());

			} catch( IOException e ) {
				PetalsEipPlugin.log( e, IStatus.ERROR );
				MessageDialog.openError( new Shell(), "Export Error", "An error occurred while reading the file. Check the logs for more details." );
			}
		}

		// Go on with the export itself
		if( eipChain != null ) {

			// Prepare the SA name
			String title = "EipChain";
			if( ! StringUtils.isEmpty( eipChain.getTitle()))
				title = eipChain.getTitle().trim();

			String saName = JbiUtils.createSaName( "EIP", title, false );

			// Get the target location
			FileDialog dlg = new FileDialog( new Shell(), SWT.SAVE );
			dlg.setFilterExtensions( new String[] { "*.zip" });
			dlg.setFilterNames( new String[] { "ZIP files" });
			dlg.setFileName( saName );
			dlg.setOverwrite( true );
			String saFilePath = dlg.open();

			// Create the SA
			if( saFilePath != null ) {

				try {
					Map<String,File> suNameToSuFile = new HashMap<String,File> ();
					for( EipNode eip : eipChain.getEipNodes()) {
						String suName = JbiUtils.createSuName( "EIP", eip.getServiceName(), false );
						File suZip = createTemporarySuZip( eip );
						suNameToSuFile.put( suName, suZip );
					}

					File saFile = createSaFile( saFilePath, saName, suNameToSuFile );
					Assert.isNotNull( saFile );

				} catch( Exception e ) {
					PetalsEipPlugin.log( e, IStatus.ERROR );
					MessageDialog.openError( new Shell(), "Export Error", "An error occurred while exporting the EIP chain. Check the logs for more details." );
				}
			}
		}

		return null;
	}


	/**
	 * Creates a ZIP file for the SU content.
	 * @param eip the EIP to process
	 * @return the ZIP file with the SU content
	 * @throws IOException if something went wrong
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws URISyntaxException
	 * @throws
	 */
	private File createTemporarySuZip( EipNode eip )
	throws IOException, URISyntaxException, SAXException, ParserConfigurationException {

		File tempDir = null;
		try  {
			Map<String,File> zipEntryToFile = new HashMap<String,File> ();
			String newWsdlLocation = null;

			// WSDL to copy?
			if( ! StringUtils.isEmpty( eip.getWsdlUri())
						&& eip.isCopyWsdl()) {

				// Create a temporary directory to import the WSDL
				tempDir = new File( new File( System.getProperty( "java.io.tmpdir" )), UUID.randomUUID().toString());
				if( ! tempDir.mkdir())
					throw new IOException( "Could not create a temporary directory." );

				// Copy the WSDL into it (this copy also normalizes the file hierarchy)
				Map<String,File> wsdlUriToFile = new WsdlImportHelper().importWsdlOrXsdAndDependencies( tempDir, eip.getWsdlUri());

				// Prepare the ZIP entries
				for( Map.Entry<String,File> entry : wsdlUriToFile.entrySet()) {
					String path = IoUtils.getRelativeLocationToFile( tempDir, entry.getValue());
					zipEntryToFile.put( path, entry.getValue());

					if( eip.getWsdlUri().equals( entry.getKey()))
						newWsdlLocation = path;
				}
			}

			// Get the content for the jbi.xml
			String jbiXmlContent = EipExportUtils.createJbiXmlContent( eip, newWsdlLocation );

			// Create the ZIP for the SU
			File suZipFile = File.createTempFile( "petals-eip-", ".zip" );
			suZipFile = JbiUtils.createJbiArchive( suZipFile.getAbsolutePath(), jbiXmlContent, zipEntryToFile );

			return suZipFile;

		} finally {
			// Delete temporary files
			if( tempDir != null )
				IoUtils.deleteFilesRecursively( tempDir );
		}
	}


	/**
	 * Creates a ZIP file for the SA.
	 * @param saFilePath the path where the SA must be saved
	 * @param eipChain the exported EIP chain
	 * @param suNameToSuFile a map (key = SU name, value = ZIP file of the SU)
	 * @return the SA ZIP file
	 * @throws IOException if something went wrong
	 */
	private File createSaFile( String saFilePath, String saName, Map<String,File> suNameToSuFile )
	throws IOException {

		try {
			// Prepare the SU names
			String[] suNames = new String[ suNameToSuFile.size()];
			suNames = suNameToSuFile.keySet().toArray( suNames );

			// Create the jbi.Xml for the SA
			String jbiXmlContent = JbiUtils.generateJbiXmlForSA( "petals-se-eip", saName, suNames );

			// Prepare the ZIP entries
			Map<String,File> zipEntryToSuFile = new HashMap<String,File> ();
			for( Map.Entry<String,File> entry : suNameToSuFile.entrySet())
				zipEntryToSuFile.put( entry.getKey() + ".zip", entry.getValue());

			// Build the SA archive
			return JbiUtils.createJbiArchive( saFilePath, jbiXmlContent, zipEntryToSuFile );

		} finally {
			// Delete the SU archives
			for( File f : suNameToSuFile.values()) {
				if( ! f.delete())
					f.deleteOnExit();
			}
		}
	}
}
