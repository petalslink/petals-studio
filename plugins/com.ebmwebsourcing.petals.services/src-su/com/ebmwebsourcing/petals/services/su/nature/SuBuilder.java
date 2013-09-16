/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.nature;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.builder.JbiXmlBuilder;
import com.ebmwebsourcing.petals.common.internal.provisional.builder.MarkerBean;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * An incremental builder to validate jbi.xml files of SU projects.
 * <p>
 * It also updates project properties when the jbi.xml file is modified.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuBuilder extends JbiXmlBuilder {

	/**
	 * The ID of this builder.
	 */
	public static final String BUILDER_ID = "com.ebmwebsourcing.petals.tools.eclipse.su.main.suBuilder";	 //$NON-NLS-1$

	/**
	 * An operation name which means that no operation was found.
	 */
	private static final QName INEXISTING_OPERATION = new QName( "http://petals-studio", "there-is-no-operation" );


	/**
	 * Runs a full build.
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	protected void fullBuild( final IProgressMonitor monitor ) throws CoreException {
		clean( monitor );
		getProject().accept( new SuResourceVisitor( monitor ));
	}


	/**
	 * Runs an incremental build.
	 * @param delta
	 * @param monitor
	 * @throws CoreException
	 */
	@Override
	protected void incrementalBuild( IResourceDelta delta, IProgressMonitor monitor)
	throws CoreException {
		delta.accept( new SuDeltaVisitor( monitor ));
	}


	/**
	 * The delta visitor used for the incremental build.
	 */
	private class SuDeltaVisitor implements IResourceDeltaVisitor {
		private final IProgressMonitor monitor;
		private boolean jbiXmlValidated = false;

		/**
		 * @param monitor
		 */
		public SuDeltaVisitor( IProgressMonitor monitor ) {
			this.monitor = monitor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor
		 * #visit(org.eclipse.core.resources.IResourceDelta)
		 */
		@Override
		public boolean visit( IResourceDelta delta ) throws CoreException {

			// Already validated?
			boolean visitChildren = false;
			if( ! this.jbiXmlValidated ) {

				// Check for cancellation.
				checkCancel( this.monitor );

				// Get the resource.
				IResource resource = delta.getResource();
				IFile jbiXmlFile = null;

				if( resource instanceof IContainer ) {
					visitChildren = true;
				}
				else if( resource instanceof IFile ) {
					IFile f = (IFile) resource;
					if( "wsdl".equalsIgnoreCase( f.getFileExtension())
							|| PetalsSPPropertiesManager.PROPERTIES_FILENAME.equals( f.getName()))
						jbiXmlFile = resource.getProject().getFile( PetalsConstants.LOC_JBI_FILE );

					else if( resource.getProjectRelativePath().toString().equals( PetalsConstants.LOC_JBI_FILE ))
						jbiXmlFile = f;
				}

				if( jbiXmlFile != null ) {
					validateAndMarkJbiXmlFile( jbiXmlFile );
					this.jbiXmlValidated = true;
				}
			}

			if( this.monitor != null )
				this.monitor.worked( 1 );

			// Return true to continue visiting children.
			return visitChildren;
		}
	}


	/**
	 * The resource visitor for the full build.
	 */
	private class SuResourceVisitor implements IResourceVisitor {
		private final IProgressMonitor monitor;

		/**
		 * @param monitor
		 */
		public SuResourceVisitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceVisitor
		 * #visit(org.eclipse.core.resources.IResource)
		 */
		@Override
		public boolean visit( IResource resource ) {

			// Check for cancellation.
			checkCancel( this.monitor );

			// Do the job: search a jbi.xml file
			boolean visitChildren = false;
			if( resource instanceof IContainer ) {
				visitChildren = true;
			}
			else if( resource instanceof IFile
					&& "jbi.xml".equals( resource.getName()))
				validateAndMarkJbiXmlFile((IFile) resource);

			if( this.monitor != null )
				this.monitor.worked( 1 );

			// Return true to continue visiting children.
			return visitChildren;
		}
	}


	/**
	 * Validates a jbi.xml file.
	 * @param jbiXmlFile
	 */
	@Override
	protected List<MarkerBean> validate( IFile jbiXmlFile ) {

		// This builder can only work if the project has the SU nature
		// MM validation
		List<MarkerBean> markerBeans = super.validate( jbiXmlFile );
		if( MarkerBean.containsCriticalError( markerBeans ))
			return markerBeans;


		// Check the file position
		IFile expectedFile = null;
		try {
			expectedFile = JbiXmlUtils.getJbiXmlFile( jbiXmlFile.getProject());
		} catch( FileNotFoundException e1 ) {
			// nothing
		}

		Jbi jbi = null;
		try {
			jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		if( expectedFile == null || ! jbiXmlFile.equals( expectedFile )) {
			markerBeans.add( new MarkerBean(
					IMarker.SEVERITY_INFO,
					"The jbi.xml file is not placed at one of the expected locations (" + PetalsConstants.LOC_JBI_FILE + ").",
					jbi,
					jbiXmlFile ));
		}


		// Step 1: check that the BC and provides / consumes are coherent with the component properties
		if( jbi == null || jbi.getServices() == null )
			return markerBeans;

		boolean hasProvides = jbi.getServices().getProvides() != null && jbi.getServices().getProvides().size() > 0;
		boolean hasConsumes = jbi.getServices().getConsumes() != null && jbi.getServices().getConsumes().size() > 0;

		Properties projectProperties = PetalsSPPropertiesManager.getProperties( jbiXmlFile.getProject());
		String suTypeVersion = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_VERSION, "" );
		String componentName = projectProperties.getProperty( PetalsSPPropertiesManager.COMPONENT_NAME, "" );

		ComponentVersionDescription componentDesc = ExtensionManager.INSTANCE.findDescriptionByComponentNameAndVersion( componentName, null );
		if( componentDesc != null ) {

			// General properties of the component
			if( jbi.getServices().isBindingComponent() != componentDesc.isBc()) {
				markerBeans.add( new MarkerBean(
						IMarker.SEVERITY_ERROR,
						"The 'binding-component' attribute does not match the target component type. "
								+ componentName + " is a " + (componentDesc.isBc() ? "binding-component." : "service engine." ),
								jbi.getServices(),
								jbiXmlFile ));
			}

			// Version properties
			String searchedVersion = "petals-se-eip".equalsIgnoreCase( componentName ) ? null : suTypeVersion;
			componentDesc = ExtensionManager.INSTANCE.findDescriptionByComponentNameAndVersion( componentName, searchedVersion );
			if( componentDesc == null
					&& suTypeVersion != null
					&& suTypeVersion.toLowerCase().endsWith( "-snapshot" )) {

				String newV = suTypeVersion.substring( 0, suTypeVersion.length() - 9 );
				componentDesc = ExtensionManager.INSTANCE.findDescriptionByComponentNameAndVersion( componentName, newV );
				if( componentDesc != null )
					suTypeVersion = newV;
			}

			if( componentDesc != null ) {
				if( hasProvides
						&& ! componentDesc.isProvide()
						&& ! componentDesc.isProxy()) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_ERROR,
							"The target component (" + componentName + ") does not allow 'provides' sections.",
							jbi.getServices(),
							jbiXmlFile ));
				}

				if( hasConsumes
						&& ! componentDesc.isConsume()
						&& ! componentDesc.isProxy()) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_ERROR,
							"The target component (" + componentName + ") does not allow 'consumes' sections." ,
							jbi.getServices(),
							jbiXmlFile ));
				}

			} else {
				markerBeans.add( new MarkerBean(
						IMarker.SEVERITY_WARNING,
						"Validation is incomplete: the component " + componentName + " " + suTypeVersion + " is not known by the tooling." ,
						jbi.getServices(),
						jbiXmlFile ));
			}

		} else {
			markerBeans.add( new MarkerBean(
					IMarker.SEVERITY_WARNING,
					"Validation is incomplete: the component " + componentName + " is not known by the tooling." ,
					jbi.getServices(),
					jbiXmlFile ));
		}

		if( MarkerBean.containsCriticalError( markerBeans ))
			return markerBeans;


		// Step 2: check that there is a coherent MEP and an operation attributes...
		// The SOAP component is not concerned by this verification.
		if( hasConsumes
				&& ! "petals-bc-soap".equalsIgnoreCase( componentName )) {

			// Due to what looks like a bug or a limitation in EMF,
			// we have to use DOM...
			// Parsing is done only once for all the consumes...
			File f = EmfUtils.getUnderlyingFile( jbi );
			Map<Consumes,QName> consumesToOperation;
			if( f != null ) {
				consumesToOperation = findConsumesToOperation( f, jbi.getServices().getConsumes());
			} else {
				consumesToOperation = Collections.emptyMap();
				PetalsServicesPlugin.log( "Cannot validate the operations to invoke.", IStatus.INFO );
			}

			for( Map.Entry<Consumes,QName> entry : consumesToOperation.entrySet()) {

				// MEP
				Consumes consumes = entry.getKey();
				String mepValue = JbiXmlUtils.getMepValue( consumes );
				if( mepValue == null ) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"The Message Exchange Pattern( MEP) is not set.",
							EmfUtils.getXpathExpression( consumes ) + "/*[local-name()='mep']",
							jbiXmlFile ));
				}

				// Operation
				QName opValue = entry.getValue();
				if( opValue == null ) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"The operation to invoke is not a valid qualified name (maybe unbounded prefix).",
							EmfUtils.getXpathExpression( consumes ) + "/*[local-name()='operation']",
							jbiXmlFile ));

				} else if( INEXISTING_OPERATION.equals( opValue )) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"The operation to invoke is undefined.",
							consumes,
							jbiXmlFile ));
				}

				// Find a WSDL that describes this...
				else {
					Map<QName,Mep> consumableOps = ConsumeUtils.getValidOperationsForConsume(
							consumes.getInterfaceName(),
							consumes.getServiceName(),
							consumes.getEndpointName());

					if( consumableOps.isEmpty()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_WARNING,
								"No invocable operation was found for this service.",
								EmfUtils.getXpathExpression( consumes ) + "/*[local-name()='operation']",
								jbiXmlFile ));

					} else if( ! consumableOps.containsKey( opValue )) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The invoked operation does not exist for this service.",
								EmfUtils.getXpathExpression( consumes ) + "/*[local-name()='operation']",
								jbiXmlFile ));

					} else {
						Mep mep = Mep.whichMep( mepValue );
						if( mep != Mep.UNKNOWN
								&& consumableOps.get( opValue ) != mep ) {

							markerBeans.add( new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"The invocation MEP does not match the operation's one or there is an ambiguity in the service consumption.",
									EmfUtils.getXpathExpression( consumes ) + "/*[local-name()='mep']",
									jbiXmlFile ));
						}
					}
				}
			}
		}


		// Step 2-bis (for provides): check that there is a WSDL attribute...
		// ... and that its values points to an existing file
		if( hasProvides ) {
			Map<Provides,URI> providesToWsdlUri = new HashMap<Provides,URI> ();
			for( Provides provides : jbi.getServices().getProvides()) {

				String wsdlValue = JbiXmlUtils.getWsdlValue( provides );

				// No given WSDL
				if( StringUtils.isEmpty( wsdlValue )) {
					markerBeans.add( new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"The provides section does not declare a WSDL file.",
							provides,
							jbiXmlFile ));

					// And the itf and srv name spaces must be the same (dixit Roland)
					String srvNs;
					if( provides.getServiceName() != null
							&& provides.getServiceName().getNamespaceURI() != null )
						srvNs = provides.getServiceName().getNamespaceURI();
					else
						srvNs = "";

					String itfNs;
					if( provides.getInterfaceName() != null
							&& provides.getInterfaceName().getNamespaceURI() != null )
						itfNs = provides.getInterfaceName().getNamespaceURI();
					else
						itfNs = "";

					if( ! srvNs.equals( itfNs )) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"Since there is no declared WSDL, the interface and service name spaces must be the same.",
								provides,
								jbiXmlFile ));
					}
				}

				// A WSDL is expected - File or URI
				else {
					File wsdlFile = JbiXmlUtils.getWsdlFile( jbiXmlFile, wsdlValue );
					if( wsdlFile == null ) {
						try {
							URI wsdlUri = UriAndUrlHelper.urlToUri( wsdlValue );
							providesToWsdlUri.put( provides, wsdlUri );

						} catch( Exception e ) {
							markerBeans.add( new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"The WSDL location is not a valid URI.",
									EmfUtils.getXpathExpression( provides ) + "/*[local-name()='wsdl']",
									jbiXmlFile ));
						}

					} else if( ! wsdlFile.exists()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The referenced WSDL file does not exist.",
								EmfUtils.getXpathExpression( provides ) + "/*[local-name()='wsdl']",
								jbiXmlFile ));
					}
					else {
						providesToWsdlUri.put( provides, wsdlFile.toURI());
					}
				}
			}

			if( MarkerBean.containsCriticalError( markerBeans ))
				return markerBeans;


			// Step 3: check that the jbi.xml values and the WSDL properties are coherent
			for( Map.Entry<Provides,URI> entry : providesToWsdlUri.entrySet()) {
				try {
					Provides p = entry.getKey();
					List<JbiBasicBean> beans = null;
					try {
						beans = WsdlUtils.INSTANCE.parse( entry.getValue());
					} catch( InvocationTargetException e ) {
						// nothing
					}

					if( beans == null ) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The WSDL associated with the service " + p.getServiceName().getLocalPart() + " is invalid.",
								EmfUtils.getXpathExpression( p ) + "/*[local-name()='wsdl']",
								jbiXmlFile ));

						continue;
					}

					if( beans.isEmpty()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_WARNING,
								"The WSDL associated with the service " + p.getServiceName().getLocalPart() + " does not seem to contain any data. It may be invalid.",
								EmfUtils.getXpathExpression( p ) + "/*[local-name()='wsdl']",
								jbiXmlFile ));

						continue;
					}

					// Find interface name first
					List<JbiBasicBean> filteredBeans = new ArrayList<JbiBasicBean> ();
					for( JbiBasicBean bean : beans ) {

						if( ! bean.isPortTypeExists()) {
							markerBeans.add( new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"The service's binding references an undefined port type.",
									EmfUtils.getXpathExpression( p ) + "/*[local-name()='wsdl']",
									jbiXmlFile ));
						}

						if( bean.getInterfaceName().equals( p.getInterfaceName()))
							filteredBeans.add( bean );
					}

					if( filteredBeans.isEmpty()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The interface name " + p.getInterfaceName().getLocalPart() + " defined in the jbi.xml does not match the declared WSDL.",
								EmfUtils.getXpathExpression( p ) + "/@interface-name",
								jbiXmlFile ));

						return markerBeans;
					}

					// Find services then
					beans.clear();
					for( JbiBasicBean bean : filteredBeans ) {
						if( bean.getServiceName().equals( p.getServiceName()))
							beans.add( bean );
					}

					if( beans.isEmpty()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The service " + p.getServiceName().getLocalPart() + " defined in the jbi.xml was not found in the declared WSDL.",
								EmfUtils.getXpathExpression( p ) + "/@service-name",
								jbiXmlFile ));

						return markerBeans;
					}

					// Find the end-point name then
					filteredBeans.clear();
					for( JbiBasicBean bean : beans ) {
						if( bean.getEndpointName().equals( p.getEndpointName())) {
							filteredBeans.add( bean );
						}
					}

					if( filteredBeans.isEmpty()) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The end-point name " + p.getEndpointName() + " defined in the jbi.xml does not match the declared WSDL.",
								EmfUtils.getXpathExpression( p ) + "/@endpoint-name",
								jbiXmlFile ));

					} else if( filteredBeans.size() != 1 ) {
						markerBeans.add( new MarkerBean(
								IMarker.SEVERITY_WARNING,
								"More than one service was found with these identifiers. The WSDL is probably incorrect.",
								EmfUtils.getXpathExpression( p ),
								jbiXmlFile ));
					}

				} catch( IllegalArgumentException e ) {
					PetalsServicesPlugin.log( e, IStatus.WARNING );
				}
			}
		}

						// Step 4: check specific parameters (as defined in extensions)
						// TODO
//						Set<ValidationRule> validationRules = ExtensionManager.INSTANCE.;
//						for( TreeIterator<EObject> it = jbi.eAllContents(); it.hasNext(); ) {
//
//							EObject eo = it.next();
//							if( eo.eContainmentFeature() != null ) {
//								String name = ExtendedMetaData.INSTANCE.getName( eo.eContainmentFeature());
//								String namespace = ExtendedMetaData.INSTANCE.getNamespace( eo.eContainmentFeature());
//
//								for( ValidationRule vr : validationRules ) {
//									if( vr.hasSameQName( namespace, name )) {
//
//										// Get the element value
//										String value = null;
//										EStructuralFeature esf = eo.eContainingFeature();
//
//										// Feature Map entry? => validate it
//										if( FeatureMapUtil.isFeatureMap( esf )) {
//											Object o = eo.eGet( esf );
//											if( o instanceof BasicFeatureMap ) {
//
//												// The element exists but has an empty value
//												if(((BasicFeatureMap) o).isEmpty()) {
//													value = "";
//												}
//
//												// The element exists and has a value
//												// Also, skip XML comments!
//												else {
//													int size = ((BasicFeatureMap) o).size();
//													for( int i=0; i<size; i++ ) {
//														Object entry = ((BasicFeatureMap) o).getValue( i );
//														if( entry != null
//																&& ! FeatureMapUtil.isComment(((BasicFeatureMap) o).get( i ))) {
//															value = entry.toString().trim();
//															break;
//														}
//													}
//
//													if( value == null )
//														value = "";
//												}
//											}
//
//											MarkerBean markerBean = validateExtendedRule( value, eo, vr, jbiXmlFile );
//											if( markerBean != null )
//												markerBeans.add( markerBean );
//										}
//
//										// Otherwise, log a trace to indicate we could not get the element's value
//										else {
//											PetalsServicesPlugin.log( "The EObject for {" + namespace + "}" + name + " coult not be resolved." , IStatus.WARNING );
//										}
//									}
//								}
//							}
//						}

						return markerBeans;
	}


	/**
	 * Checks an extended validation rule.
	 * @param value the element value
	 * @param eo an EObject
	 * @param vr a validation rule
	 * @param jbiXmlFile the jbi.xml file
	 * @return a marker bean if an error was found, null otherwise
	 */
	private MarkerBean validateExtendedRule(
			String value,
			EObject eo,
			ValidationRule vr,
			IFile jbiXmlFile ) {

		MarkerBean markerBean = null;

		// Test a null value
		if( value == null ) {
			if( ! vr.isNullAccepted()) {
				String name = ExtendedMetaData.INSTANCE.getName( eo.eContainmentFeature());
				markerBean = new MarkerBean(
						IMarker.SEVERITY_ERROR,
						"'" + name + " cannot be null.",
						EmfUtils.getXpathExpression( eo ),
						jbiXmlFile );
			}
		}

		// Test an empty value
		else if( value.trim().length() == 0 ) {
			if( ! vr.isEmptyValueAccepted()) {
				String name = ExtendedMetaData.INSTANCE.getName( eo.eContainmentFeature());
				markerBean = new MarkerBean(
						IMarker.SEVERITY_ERROR,
						"'" + name + "' cannot have an empty value.",
						EmfUtils.getXpathExpression( eo ),
						jbiXmlFile );
			}
		}

		// Otherwise, test according to the rule type
		else {
			switch( vr.getRuleType()) {
			case FILE:
				File f = new File( value );
				if( f.exists() && f.isAbsolute()) {
					markerBean = new MarkerBean(
							IMarker.SEVERITY_ERROR,
							"Referencing an absolute file is not allowed.",
							EmfUtils.getXpathExpression( eo ),
							jbiXmlFile );

				} else {
					try {
						IFile res = JbiXmlUtils.getResourceFile( jbiXmlFile.getProject(), value );
						if( ! res.exists()) {
							markerBean = new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"The resource " + value + " does not exist.",
									EmfUtils.getXpathExpression( eo ),
									jbiXmlFile );

						} else if( vr.getFileExtension() != null
								&& ! vr.getFileExtension().equalsIgnoreCase( res.getFileExtension())) {

							markerBean = new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"This resource was expected to have the extension ." + vr.getFileExtension(),
									EmfUtils.getXpathExpression( eo ),
									jbiXmlFile );
						}

					} catch( FileNotFoundException e ) {
						markerBean = new MarkerBean(
								IMarker.SEVERITY_ERROR,
								"The resource " + value + " does not exist.",
								EmfUtils.getXpathExpression( eo ),
								jbiXmlFile );
					}
				}

				break;


			case JAVA:
				IJavaProject jp = null;
				try {
					if( jbiXmlFile.getProject().hasNature( JavaCore.NATURE_ID )) {
						jp = JavaCore.create( jbiXmlFile.getProject());
					}

				} catch( CoreException e1 ) {
					PetalsServicesPlugin.log( e1, IStatus.WARNING );
				}

				if( jp == null ) {
					markerBean = new MarkerBean(
							IMarker.SEVERITY_WARNING,
							"The project is not a Java project, this resource cannot be validated.",
							EmfUtils.getXpathExpression( eo ),
							jbiXmlFile );

				} else {
					try {
						IType iType = jp.findType( value );
						if (iType == null) {
							markerBean = new MarkerBean(
									IMarker.SEVERITY_ERROR,
									"The class " + value + " was not found in the project's class path.",
									EmfUtils.getXpathExpression( eo ),
									jbiXmlFile );
						}

					} catch( JavaModelException e ) {
						PetalsServicesPlugin.log( e, IStatus.WARNING );
					}
				}

				break;


			case XPATH:
				XPath xpath = XPathFactory.newInstance().newXPath();
				try {
					xpath.compile( value );

				} catch( Exception e ) {
					String cause = e.getCause().getMessage();
					cause = cause == null ? "" : " " + cause;
					markerBean = new MarkerBean(
							IMarker.SEVERITY_ERROR,
							"Invalid XPath expression." + cause,
							EmfUtils.getXpathExpression( eo ),
							jbiXmlFile );
				}

				break;


			case URI:
				try {
					UriAndUrlHelper.urlToUri( value );

				} catch( Exception e ) {
					markerBean = new MarkerBean(
							IMarker.SEVERITY_ERROR,
							value + " is not a valid URI.",
							EmfUtils.getXpathExpression( eo ),
							jbiXmlFile );
				}

				break;
			}
		}

		return markerBean;
	}


	/**
	 * Finds the invoked operation for each consumes block.
	 * @param jbiXmlFile the jbi.xml file (to parse - again)
	 * @param consumes the consumes block
	 * @return an association map
	 */
	private Map<Consumes,QName> findConsumesToOperation( File jbiXmlFile, Collection<Consumes> consumes ) {

		// At the beginning, we were getting the value with EMF.
		// All the prefixes are stored in the document root, even those that are declared in sub-elements.
		// We used to find the operation in the Feature Map, extract the operation name
		// and its prefix, and to resolve the name space URI in the prefix map.
		//
		// Except that if an element redefines a name space prefix, this won't work!
		// Typical example in Petals: EIP, where the operation prefix can be redefined
		// in every consume.

		// We cannot either serialize the "consumes" mark-up, because EMF
		// looses the second name space declaration (this is true if the prefix
		// is used in an element's value and false if the prefix is used in a
		// structural feature).

		Map<Consumes,QName> result = new HashMap<Consumes,QName> ();
		Document doc = DomUtils.buildDocument( jbiXmlFile );
		if( doc != null ) {
			NodeList nodes = (NodeList) XPathUtils.evaluateXPathExpression( "//*[local-name()='consumes']", doc, XPathConstants.NODESET );
			for( Consumes cons : consumes ) {
				Element opElt = null;

				// Get the right consumes and then the operation mark-up
				for( int i=0; i<nodes.getLength(); i++ ) {
					if( !( nodes.item( i ) instanceof Element ))
						continue;

					Element elt = (Element) nodes.item( i );
					String attr = elt.getAttribute( "interface-name" );
					if( attr != null
							&& cons.getInterfaceName() != null
							&& ! attr.endsWith( cons.getInterfaceName().getLocalPart()))
						continue;

					attr = elt.getAttribute( "service-name" );
					if( attr != null
							&& cons.getServiceName() != null
							&& ! attr.endsWith( cons.getServiceName().getLocalPart()))
						continue;

					attr = elt.getAttribute( "endpoint-name" );
					if( attr != null
							&& cons.getEndpointName() != null
							&& ! attr.endsWith( cons.getEndpointName()))
						continue;

					opElt = (Element) XPathUtils.evaluateXPathExpression( "*[local-name()='operation']", elt, XPathConstants.NODE );
					break;
				}

				QName op = INEXISTING_OPERATION;
				if( opElt != null )
					op = NamespaceUtils.getQNameElement( opElt );

				result.put( cons, op );
			}
		}

		return result;
	}
}
