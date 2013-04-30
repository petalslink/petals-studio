/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.generation.samples;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.BpelProvides10;

/**
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main( String[] args ) {

		// Create the jbi.xml for the service-unit
		String componentName = "petals-se-bpel";
		JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
		genDelegate.setComponentName( componentName );

		BpelProvides10 jbiBean = new BpelProvides10();
		jbiBean.setEndpointName( "myEdpt" );
		jbiBean.setInterfaceName( "myItfName" );
		jbiBean.setInterfaceNamespace( "http://myItf.org" );
		jbiBean.setServiceName( "mySrv" );
		jbiBean.setServiceNamespace( "http://mySrv.org" );

		jbiBean.setProcessName( "process.bpel" );
		//	jbiBean.putFileToImport(
		//			"process.bpel",
		//			new File( "C:/Documents and Settings/vzurczak/Bureau/WSDLs/tuxdroid.bpel" ));

		jbiBean.setWsdl( "process.wsdl" );
		jbiBean.putFileToImport(
					"process.wsdl",
					new File( "C:/Documents and Settings/vzurczak/Bureau/WSDLs/tuxdroid.wsdl" ));

		String jbiXmlContent = genDelegate.createJbiDescriptor( jbiBean ).toString();

		// Create the service-unit name
		String suName = JbiUtils.createSuName(
					jbiBean.getSuType(),
					jbiBean.getServiceName(),
					! jbiBean.isProvides());

		try {
			// Create the service-unit file
			File suFile = new File( System.getProperty( "java.io.tmpdir" ), suName + ".zip" );
			suFile = JbiUtils.createJbiArchive(
						suFile.getAbsolutePath(),
						jbiXmlContent,
						jbiBean.getFilesToImport());
			suFile.deleteOnExit();

			// Create the SA
			String saName = JbiUtils.createSaName( suName );
			String saJbiXmlContent = JbiUtils.generateJbiXmlForSA( componentName, saName, suName );

			File saFile = new File( "C:/Documents and Settings/vzurczak/Bureau", saName + ".zip"  );
			Map<String,File> suNameToSuFile = new HashMap<String,File>( 1 );
			suNameToSuFile.put( suName, suFile );
			saFile = JbiUtils.createJbiArchive( saFile.getAbsolutePath(), saJbiXmlContent, suNameToSuFile );

			assert( saFile != null && saFile.exists());
			System.out.println( "Generation was successful." );

		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

}
