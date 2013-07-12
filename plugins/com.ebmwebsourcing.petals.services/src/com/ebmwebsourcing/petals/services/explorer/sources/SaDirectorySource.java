/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.explorer.sources;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.ServiceUnit;
import com.sun.java.xml.ns.jbi.Target;

/**
 * A source looking for end-points in a directory of zipped service assemblies.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaDirectorySource extends EndpointSource {

	private final boolean unzipAll, showConsumes;
	private final File directoryOrSaFile;
	private final Map<ServiceUnitBean,String> suBeanToSaFile = new HashMap<ServiceUnitBean,String>( 5 );
	private final Map<ServiceUnitBean,File> suBeanToExplodedSu = new HashMap<ServiceUnitBean,File>( 5 );
	private final List<File> filesToDelete = new ArrayList<File> ();


	/**
	 * Constructor.
	 * @param directoryOrSaFile
	 * @param unzipAll true to unzip all the SU, false to unzip just the jbi.xml
	 * @param showConsumes
	 */
	public SaDirectorySource( File directoryOrSaFile, boolean unzipAll, boolean showConsumes ) {
		super( directoryOrSaFile.getName(), directoryOrSaFile.getAbsolutePath());

		this.unzipAll = unzipAll;
		this.showConsumes = showConsumes;
		this.directoryOrSaFile = directoryOrSaFile;
		if( directoryOrSaFile.isDirectory())
			this.description = "The Petals services contained in service assemblies at " + this.directoryOrSaFile.getAbsolutePath() + ".";
		else
			this.description = "The Petals services contained in the service assembly at " + this.directoryOrSaFile.getAbsolutePath() + ".";
	}


	/**
	 * @return the directory
	 */
	public File getDirectoryOrSaFile() {
		return this.directoryOrSaFile;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #dispose()
	 */
	@Override
	public void dispose() {

		// Delete exploded files
		try {
			File[] ftd = new File[ this.suBeanToExplodedSu.size()];
			IoUtils.deleteFilesRecursively( this.suBeanToExplodedSu.values().toArray( ftd ));

			ftd = new File[ this.filesToDelete.size()];
			IoUtils.deleteFilesRecursively( this.filesToDelete.toArray( ftd ));

		} catch( IOException e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #refreshServiceUnits(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected Collection<ServiceUnitBean> refreshServiceUnits( IProgressMonitor monitor ) {

		if( monitor == null )
			monitor = new NullProgressMonitor();

		dispose();
		this.suBeanToSaFile.clear();
		this.suBeanToExplodedSu.clear();

		// Get SAs
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept( File pathname ) {
				return pathname.getName().endsWith( ".zip" );
			}
		};

		File[] saFiles;
		if( this.directoryOrSaFile.isDirectory())
			saFiles = this.directoryOrSaFile.listFiles( filter );
		else
			saFiles = new File[] { this.directoryOrSaFile };

		// Unzip it all
		for( File saFile : saFiles ) {
			ZipFile zipFile = null;
			try {
				zipFile = new ZipFile( saFile );
				ZipEntry jbiXmlEntry = zipFile.getEntry( "META-INF/jbi.xml" );
				if( jbiXmlEntry == null )
					continue;	// Not a SA

				monitor.worked( 1 );

				// Get the content of the jbi.xml for the SA
				File tempSaFile = File.createTempFile( "tempSaJbiXml", ".xml" );

				// Safety measure
				tempSaFile.deleteOnExit();

				this.filesToDelete.add( tempSaFile );
				InputStream is = zipFile.getInputStream( jbiXmlEntry );
				IoUtils.copyStream( is, tempSaFile );
				is.close ();

				Map<String,String> suZipNameToComponentName = new HashMap<String,String> ();
				String saId = null;
				try {
					Jbi jbi = JbiXmlUtils.getJbiXmlModel( tempSaFile );
					if( jbi != null && jbi.getServiceAssembly() != null ) {

						if( jbi.getServiceAssembly().getIdentification() != null )
							saId = jbi.getServiceAssembly().getIdentification().getName();

						EList<ServiceUnit> sus = jbi.getServiceAssembly().getServiceUnit();
						for( ListIterator<ServiceUnit> it = sus.listIterator(); it.hasNext(); ) {
							Target target = it.next().getTarget();
							suZipNameToComponentName.put( target.getArtifactsZip().trim(), target.getComponentName().trim());
						}
					}

				} catch( InvalidJbiXmlException e1 ) {
					PetalsServicesPlugin.log( e1, IStatus.WARNING, "Invalid jbi.xml for the SA " + saFile.getAbsolutePath());
				}

				// Get SU entries
				Enumeration<?> entries = zipFile.entries();
				while( entries.hasMoreElements()) {
					ZipEntry saEntry = (ZipEntry) entries.nextElement();
					if( ! saEntry.getName().endsWith( ".zip" ))
						continue;

					File tmpDir = new File(
							System.getProperty( "java.io.tmpdir" ),
							"Petals-" + UUID.randomUUID().toString());

					if( ! tmpDir.mkdir())
						throw new IOException( "Could not create a temporary directory." );

					this.filesToDelete.add( tmpDir );
					InputStream tempIn = zipFile.getInputStream( saEntry );
					ZipInputStream in = new ZipInputStream( tempIn );

					try {
						ZipEntry suEntry;
						while(( suEntry = in.getNextEntry()) != null ) {

							// Only watch the jbi.xml file?
							if( ! this.unzipAll
									&& ! "META-INF/jbi.xml".equals( suEntry.getName()))
								continue;

							// Extract the jbi.xml file and create a ServiceUnitBean instance
							File tempSuFile = new File( tmpDir, suEntry.getName());

							// Safety measure
							tempSuFile.deleteOnExit();
							if( ! tempSuFile.getParentFile().exists()
									&& ! tempSuFile.getParentFile().mkdirs())
								throw new IOException( "Could not create the parent of a temporary file." );

							if( tempSuFile.getParentFile().exists()
									&& ! tempSuFile.getParentFile().isDirectory())
								throw new IOException( "The parent of a temporary file is not a directory, as expected." );

							if( suEntry.isDirectory()) {
								if( ! tempSuFile.mkdir())
									throw new IOException( "Could not create a temporary directory." );

								// No need to go further
								continue;

							} else if( ! tempSuFile.createNewFile()) {
								throw new IOException( "Could not create a temporary file." );
							}

							IoUtils.copyStream( in, tempSuFile );
							if( ! "jbi.xml".equalsIgnoreCase( tempSuFile.getName()))
								continue;

							String suName = new Path( saEntry.getName()).removeFileExtension().lastSegment();
							try {
								Jbi jbi = JbiXmlUtils.getJbiXmlModel( tempSuFile );
								ServiceUnitBean suBean = new ServiceUnitBean();
								suBean.setSource( this );
								suBean.setServiceAssemblyId( saId == null ? "" : saId );
								suBean.setJbiXmlLocation( tempSuFile.getAbsolutePath());
								suBean.setSaJbiXmlLocation( tempSaFile.getAbsolutePath());

								suBean.setServiceUnitName( suName );
								String componentName = suZipNameToComponentName.get( saEntry.getName());
								if( componentName != null ) {
									componentName = new Path( componentName ).lastSegment();
									suBean.setComponentName( componentName );
								}

								getEndpointBeans( jbi, suBean );
								if( this.showConsumes || suBean.getEndpoints().size() > 0 )
									this.suBeanToSaFile.put( suBean, saFile.getAbsolutePath());

							} catch( InvalidJbiXmlException e ) {
								PetalsServicesPlugin.log(
										e, IStatus.WARNING,
										"Invalid jbi.xml for the SU " + suName + " in " + saFile.getAbsolutePath());
							}

							// Do not loop anymore
							if( ! this.unzipAll )
								break;
						}

					} finally {
						in.close();
						tempIn.close();
					}
				}

			} catch( FileNotFoundException e ) {
				PetalsServicesPlugin.log( e, IStatus.WARNING );

			} catch( IOException e ) {
				PetalsServicesPlugin.log( e, IStatus.WARNING );

			} finally {

				// Close the ZIP file
				if( zipFile != null ) {
					try {
						zipFile.close();

					} catch( IOException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );
					}
				}
			}
		}

		return this.suBeanToSaFile.keySet();
	}


	/**
	 * A class used to unzip a SA and showing a progress bar in the Eclipse UI.
	 */
	private class UnzippingRunnableWithProgress implements IRunnableWithProgress {

		private final ServiceUnitBean suBean;


		/**
		 * Constructor.
		 * @param suBean
		 */
		UnzippingRunnableWithProgress( ServiceUnitBean suBean ) {
			this.suBean = suBean;
		}


		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.operation.IRunnableWithProgress
		 * #run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public void run( IProgressMonitor monitor )
		throws InvocationTargetException, InterruptedException {

			ZipFile zipFile = null;
			try {
				monitor.beginTask( "Unzipping the service assembly...", IProgressMonitor.UNKNOWN );
				Map<ServiceUnitBean, File> localSuBeanToExplodedSu = new HashMap<ServiceUnitBean, File>();
				this.suBean.setWsdlContainerLocationComputed( true );

				// Get the SA and check if it still exists
				String saFileLocation = SaDirectorySource.this.suBeanToSaFile.get( this.suBean );
				File saFile;
				if( saFileLocation == null
						|| ( saFile = new File( saFileLocation )) == null )
					return;

				// Unzip all the SUs
				zipFile = new ZipFile( saFile );
				Enumeration<?> entries = zipFile.entries();
				while( entries.hasMoreElements()) {

					// In case of cancellation, remove all the extracted files of this SA
					if( monitor.isCanceled()) {
						File[] ftd = new File[ localSuBeanToExplodedSu.size()];
						try {
							IoUtils.deleteFilesRecursively( localSuBeanToExplodedSu.values().toArray( ftd ));
							this.suBean.setWsdlContainerLocationComputed( false );

						} catch( IOException e ) {
							PetalsServicesPlugin.log( e, IStatus.WARNING );
						}

						return;
					}

					// Read the next entry
					ZipEntry saEntry = (ZipEntry) entries.nextElement();
					if( ! saEntry.getName().endsWith( ".zip" ))
						continue;

					monitor.worked( 1 );
					monitor.subTask( "Extracting " + saEntry.getName() + "..." );

					// Create a temporary folder to store the SU
					File tempDir = new File(
							System.getProperty( "java.io.tmpdir" ),
							UUID.randomUUID().toString());

					if( ! tempDir.mkdir()) {
						PetalsServicesPlugin.log(
								"The service unit " + saEntry.getName() + " could not be extracted from the service assembly.",
								IStatus.ERROR );
						return;
					}

					localSuBeanToExplodedSu.put( this.suBean, tempDir );

					// Unzip the SU
					InputStream tempIn = zipFile.getInputStream( saEntry );
					ZipInputStream in = new ZipInputStream( tempIn );
					ZipEntry suEntry;
					while(( suEntry = in.getNextEntry()) != null ) {

						File tempFile = new File( tempDir, suEntry.getName());
						if( ! tempFile.exists()) {
							File parent = tempFile.getParentFile();
							if( ! parent.exists() && ! parent.mkdirs())
								continue;

							if( tempFile.createNewFile())
								IoUtils.copyStream( in, tempFile );
						}
					}

					in.close();
					tempIn.close();
					SaDirectorySource.this.suBeanToExplodedSu.putAll( localSuBeanToExplodedSu );
				}

			} catch( ZipException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( IOException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} finally {
				if( zipFile != null ) {
					try {
						zipFile.close();
					} catch( IOException e ) {
						// nothing
					}
				}

				monitor.done();
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #getWsdlContainerLocation(com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean)
	 */
	@Override
	public String getWsdlContainerLocation( ServiceUnitBean suBean ) {

		// Need to unzip?
		if( ! this.suBeanToExplodedSu.containsKey( suBean )) {
			IProgressService ps = PlatformUI.getWorkbench().getProgressService();
			try {
				ps.busyCursorWhile( new UnzippingRunnableWithProgress( suBean ));

			} catch( InvocationTargetException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( InterruptedException e ) {
				// nothing
			}
		}

		// The SU must have been extracted
		File explodedSuFile = this.suBeanToExplodedSu.get( suBean );
		if( explodedSuFile == null || ! explodedSuFile.exists())
			return "";

		return explodedSuFile.getAbsolutePath();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		if( !( obj instanceof SaDirectorySource ))
			return false;

		if( this.directoryOrSaFile == null )
			return ((SaDirectorySource) obj).directoryOrSaFile == null;

		return this.directoryOrSaFile.equals(((SaDirectorySource) obj).directoryOrSaFile);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
