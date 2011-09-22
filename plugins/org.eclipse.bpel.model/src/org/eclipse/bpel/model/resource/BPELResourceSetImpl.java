/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.model.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Apr 17, 2007
 *
 */


@SuppressWarnings("nls")

public class BPELResourceSetImpl extends ResourceSetImpl implements IResourceChangeListener {
	 
	public BPELResourceSetImpl() {
		super();
		// FIX ME: This should not have dependency on running eclipse.
		// IWorkspace workspace = ResourcesPlugin.getWorkspace();
		// workspace.addResourceChangeListener(this, IResourceChangeEvent.POST_BUILD);
	}

	/**
	 * Used to force loading using the right resource loaders.
	 */
	static public final String SLIGHTLY_HACKED_KEY = "slightly.hacked.resource.set";
	
	
	
	/**
	 * Load the resource from the resource set, assuming that it is the kind
	 * indicated by the last argument. The "kind" parameter is the extension 
	 * without the . of the resource.
	 * 
	 * This forces the right resource to be loaded even if the URI of the resource
	 * is "wrong".
	 * 
	 * @param uri the URI of the resource.
	 * @param loadOnDemand load on demand
	 * @param kind the resource kind. It has to be of the form "*.wsdl", or "*.xsd", or "*.bpel"
	 * @return the loaded resource. 
	 */
	
	@SuppressWarnings("nls")
	public Resource getResource(URI uri, boolean loadOnDemand, String kind)  {

		Map<URI, Resource> map = getURIResourceMap();
		
		if (map != null) {
			Resource resource = map.get(uri);
			if (resource != null) {
				if (loadOnDemand && !resource.isLoaded()) {
					demandLoadHelper(resource);
				}
				
				return resource;
			}
		}

		URIConverter theURIConverter = getURIConverter();
		URI normalizedURI = theURIConverter.normalize(uri);
		
		for (Resource resource : getResources()) {
			if (theURIConverter.normalize(resource.getURI()).equals(
					normalizedURI)) {
				if (loadOnDemand && !resource.isLoaded()) {
					demandLoadHelper(resource);
				}

				if (map != null) {
					map.put(uri, resource);
				}
				return resource;
			}			
		}
		
		if (loadOnDemand) {
			Resource resource = demandCreateResource(uri,kind);
			if (resource == null) {
				throw new RuntimeException("Cannot create a resource for '"
						+ uri + "'; a registered resource factory is needed");
			}

			demandLoadHelper(resource);

			if (map != null) {
				map.put(uri, resource);
			}
			return resource;
		}

		return null;
	}
	
	
	protected Resource demandCreateResource ( URI uri, String kind ) {
		return createResource ( uri, kind );
	}
	
	
	/**
	 * Create the resource based on the kind.
	 * @param uri
	 * @param kind
	 * @return the created resource
	 */
	
	// TODO: ganymede [ this method apparently is already in the parent resource set ]
	// we can strike it from this resourceset.
	
//	@SuppressWarnings("nls")
//	public Resource createResource ( URI uri, String kind) {
//		
//		if (kind == null) {
//			return super.createResource(uri);
//		}
//		
//		Resource resource = createResource(URI.createURI("*." + kind)); 
//		resource.setURI(uri);		
//		return resource;
//	}


	
	
	
	
	/**
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged (IResourceChangeEvent event) {
		
		// System.out.println("IResourceChangeEvent: " + event + "; " + event.getType()  );				
		IResourceDelta[] deltas = event.getDelta().getAffectedChildren( IResourceDelta.CHANGED | IResourceDelta.REMOVED, IResource.FILE );	
		processDeltas ( deltas );
	}
	
	void processDeltas ( IResourceDelta [] deltas ) {
		
		for(IResourceDelta delta : deltas) {			
			processDeltas( delta.getAffectedChildren(IResourceDelta.CHANGED | IResourceDelta.REMOVED, IResource.FILE) );
			
			IResource resource = delta.getResource();
			if (resource.getType () != IResource.FILE) {
				continue;
			}
			
			if (delta.getKind() == IResourceDelta.REMOVED){
				resourceChanged((IFile)resource);
				continue;
			}
			
//			 * @see IResourceDelta#CONTENT
//			 * @see IResourceDelta#DESCRIPTION
//			 * @see IResourceDelta#ENCODING
//			 * @see IResourceDelta#OPEN
//			 * @see IResourceDelta#MOVED_TO
//			 * @see IResourceDelta#MOVED_FROM
//			 * @see IResourceDelta#TYPE
//			 * @see IResourceDelta#SYNC
//			 * @see IResourceDelta#MARKERS
//			 * @see IResourceDelta#REPLACED
			 
			if ((delta.getFlags() & IResourceDelta.CONTENT) == 0){
				continue;
			}
			
			// TODO: Temporary hack
			// Actually we should remove all resources from the resourceSet,
			// but for some reasons bpel files can't be removed now
			if ("bpel".equals(((IFile) resource).getFileExtension())){
				continue;
			}
			
			resourceChanged((IFile) resource);
		}		
	}
	
	public void setLoadOptions (Map<Object, Object> options) {
		loadOptions = options;
	}
	
	/**
	 * Resource has changed, remove it from the cache or list of loaded resources.
	 * 
	 * @param file
	 */
	public void resourceChanged (IFile file) {
		// System.out.println("ResourceChanged: " + file  );
		URI uri = URI.createPlatformResourceURI( file.getFullPath().toString() ) ;		
		// System.out.println("    ResourceURI: " + uri );		
		URIConverter theURIConverter = getURIConverter();
		URI normalizedURI = theURIConverter.normalize(uri);
				
		if (uriResourceMap != null) {
			uriResourceMap.remove(uri);
			uriResourceMap.remove(normalizedURI);
			// System.out.println("Removed from Map: " + map );
		}
				
		List<Resource> resourceList = getResources();
		if (resources.size() < 1) {
			return ;
		}
		
		for(Resource r : new ArrayList<Resource>(resourceList) )  {
			if (uri.equals(r.getURI()) || normalizedURI.equals(r.getURI() )) {
				resources.remove(r);
				// System.out.println("Removed from List: " + r );				
			}
		}
	}

}
