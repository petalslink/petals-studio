/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.util;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.bpel.common.extension.model.ExtensionMap;
import org.eclipse.bpel.common.extension.model.ExtensionmodelFactory;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Scope;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;


/**
 * Reads a BPEL file and makes it compatible with the BPEL tooling.
 */
public class BPELReader {

	protected Resource processResource;
	protected Resource extensionsResource;
	protected Process process;
	protected ExtensionMap extensionMap;

	/**
	 * Reads the given BPEL file.
	 */
	public void read(IFile modelFile, ResourceSet resourceSet) {
		// TODO: These two lines are a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=72565
		EcorePackage instance = EcorePackage.eINSTANCE;
		instance.eAdapters();
		URI uri = URI.createPlatformResourceURI(modelFile.getFullPath().toString());
		this.processResource = resourceSet.getResource(uri, true);
		read(this.processResource, modelFile, resourceSet);
	}

	/**
	 * Another public method for those who want to get the process resource
	 * by their own means (such as the editor).
	 */
	public void read(Resource processResource, IFile modelFile, ResourceSet resourceSet) {
		// TODO: These two lines are a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=72565
		EcorePackage instance = EcorePackage.eINSTANCE;
		instance.eAdapters();

		this.processResource = processResource;

		IPath extensionsPath = modelFile.getFullPath().removeFileExtension().addFileExtension(IBPELUIConstants.EXTENSION_MODEL_EXTENSIONS);
		URI extensionsUri = URI.createPlatformResourceURI(extensionsPath.toString());
		IFile extensionsFile = ResourcesPlugin.getWorkspace().getRoot().getFile(extensionsPath);

		try {
			processResource.load(Collections.EMPTY_MAP);
			EList<EObject> contents = processResource.getContents();
			if (!contents.isEmpty())
				this.process = (Process) contents.get(0);
		} catch (Exception e) {
			// TODO: If a file is empty Resource.load(Map) throws a java.lang.NegativeArraySizeException
			// We should investigate EMF to see if we are supposed to handle this case or if this
			// is a bug in EMF.
			BPELUIPlugin.log(e);
		}
		try {
			this.extensionsResource = resourceSet.getResource(extensionsUri, extensionsFile.exists());
			if (this.extensionsResource != null) {
				this.extensionMap = ExtensionmodelFactory.eINSTANCE.findExtensionMap(
							IBPELUIConstants.MODEL_EXTENSIONS_NAMESPACE, this.extensionsResource.getContents());
			}
		} catch (Exception e) {
			BPELUIPlugin.log(e);
		}
		if (this.extensionMap != null) this.extensionMap.initializeAdapter();

		if (this.process == null) {
			this.process = BPELFactory.eINSTANCE.createProcess();
			processResource.getContents().add(this.process);
		}
		if (this.extensionMap == null) {
			this.extensionMap = ExtensionmodelFactory.eINSTANCE.createExtensionMap(IBPELUIConstants.MODEL_EXTENSIONS_NAMESPACE);
			if (this.extensionsResource == null) {
				this.extensionsResource = resourceSet.createResource(extensionsUri);
			}
			this.extensionsResource.getContents().clear();
			this.extensionsResource.getContents().add(this.extensionMap);
		}

		// Make sure the Process has Variables, PartnerLinks, CorrelationSets and MessageExchanges objects.
		// They aren't strictly necessary according to the spec but make we need those in
		// order for the editor tray to work.
		if (this.process.getVariables() == null) {
			this.process.setVariables(BPELFactory.eINSTANCE.createVariables());
		}
		if (this.process.getPartnerLinks() == null) {
			this.process.setPartnerLinks(BPELFactory.eINSTANCE.createPartnerLinks());
		}
		if (this.process.getCorrelationSets() == null) {
			this.process.setCorrelationSets(BPELFactory.eINSTANCE.createCorrelationSets());
		}
		if (this.process.getMessageExchanges() == null) {
			this.process.setMessageExchanges(BPELFactory.eINSTANCE.createMessageExchanges());
		}

		// Make sure scopes have Variables.
		// They aren't strictly necessary according to the spec but make we need those in
		// order for the editor tray to work.
		for (Iterator<EObject> iter = this.process.eAllContents(); iter.hasNext();) {
			EObject object = iter.next();
			if (object instanceof Scope) {
				Scope scope = (Scope)object;
				if (scope.getVariables() == null) {
					scope.setVariables(BPELFactory.eINSTANCE.createVariables());
				}
				if (scope.getPartnerLinks() == null) {
					scope.setPartnerLinks(BPELFactory.eINSTANCE.createPartnerLinks());
				}
				if (scope.getCorrelationSets() == null) {
					scope.setCorrelationSets(BPELFactory.eINSTANCE.createCorrelationSets());
				}
				if (scope.getMessageExchanges() == null) {
					scope.setMessageExchanges(BPELFactory.eINSTANCE.createMessageExchanges());
				}
			}
		}

		// Make sure each model object has the necessary extensions!
		TreeIterator<EObject> it = this.process.eAllContents();
		while (it.hasNext()) {
			EObject modelObject = it.next();
			ModelHelper.createExtensionIfNecessary(this.extensionMap, modelObject);
		}

		if (this.extensionMap.get(this.process) == null) {
			ModelHelper.createExtensionIfNecessary(this.extensionMap, this.process);
		}
	}

	/**
	 * Another public method for those who want to get the process resource
	 * by their own means (such as the editor).
	 */
	public void read(Resource processResource, IDOMModel domModel, ResourceSet resourceSet) {
		// TODO: These two lines are a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=72565
		EcorePackage instance = EcorePackage.eINSTANCE;
		instance.eAdapters();

		this.processResource = processResource;

		//IPath extensionsPath = modelFile.getFullPath().removeFileExtension().addFileExtension(IBPELUIConstants.EXTENSION_MODEL_EXTENSIONS);
		org.eclipse.core.runtime.IPath extensionsPath
		= (new org.eclipse.core.runtime.Path(domModel.getBaseLocation())).removeFileExtension().addFileExtension(IBPELUIConstants.EXTENSION_MODEL_EXTENSIONS);
		URI extensionsUri = URI.createPlatformResourceURI(extensionsPath.toString());
		IFile extensionsFile = ResourcesPlugin.getWorkspace().getRoot().getFile(extensionsPath);

		try {
			processResource.load(Collections.EMPTY_MAP);
			EList<EObject> contents = processResource.getContents();
			if (!contents.isEmpty())
				this.process = (Process) contents.get(0);
		} catch (Exception e) {
			// TODO: If a file is empty Resource.load(Map) throws a java.lang.NegativeArraySizeException
			// We should investigate EMF to see if we are supposed to handle this case or if this
			// is a bug in EMF.
			BPELUIPlugin.log(e);
		}
		try {
			this.extensionsResource = resourceSet.getResource(extensionsUri, extensionsFile.exists());
			if (this.extensionsResource != null) {
				this.extensionMap = ExtensionmodelFactory.eINSTANCE.findExtensionMap(
							IBPELUIConstants.MODEL_EXTENSIONS_NAMESPACE, this.extensionsResource.getContents());
			}
		} catch (Exception e) {
			BPELUIPlugin.log(e);
		}
		if (this.extensionMap != null) this.extensionMap.initializeAdapter();

		if (this.process == null) {
			this.process = BPELFactory.eINSTANCE.createProcess();
			processResource.getContents().add(this.process);
		}
		if (this.extensionMap == null) {
			this.extensionMap = ExtensionmodelFactory.eINSTANCE.createExtensionMap(IBPELUIConstants.MODEL_EXTENSIONS_NAMESPACE);
			if (this.extensionsResource == null) {
				this.extensionsResource = resourceSet.createResource(extensionsUri);
			}
			this.extensionsResource.getContents().clear();
			this.extensionsResource.getContents().add(this.extensionMap);
		}

		// Make sure the Process has Variables, PartnerLinks and CorrelationSets objects.
		// They aren't strictly necessary according to the spec but make we need those in
		// order for the editor tray to work.
		if (this.process.getVariables() == null) {
			this.process.setVariables(BPELFactory.eINSTANCE.createVariables());
		}
		if (this.process.getPartnerLinks() == null) {
			this.process.setPartnerLinks(BPELFactory.eINSTANCE.createPartnerLinks());
		}
		if (this.process.getCorrelationSets() == null) {
			this.process.setCorrelationSets(BPELFactory.eINSTANCE.createCorrelationSets());
		}
		if (this.process.getMessageExchanges() == null) {
			this.process.setMessageExchanges(BPELFactory.eINSTANCE.createMessageExchanges());
		}
		// Make sure scopes have Variables.
		// They aren't strictly necessary according to the spec but make we need those in
		// order for the editor tray to work.
		for (Iterator<EObject> iter = this.process.eAllContents(); iter.hasNext();) {
			EObject object = iter.next();
			if (object instanceof Scope) {
				Scope scope = (Scope)object;
				if (scope.getVariables() == null) {
					scope.setVariables(BPELFactory.eINSTANCE.createVariables());
				}
				if (scope.getPartnerLinks() == null) {
					scope.setPartnerLinks(BPELFactory.eINSTANCE.createPartnerLinks());
				}
				if (scope.getCorrelationSets() == null) {
					scope.setCorrelationSets(BPELFactory.eINSTANCE.createCorrelationSets());
				}
				if (scope.getMessageExchanges() == null) {
					scope.setMessageExchanges(BPELFactory.eINSTANCE.createMessageExchanges());
				}
			}
		}

		// Make sure each model object has the necessary extensions!
		TreeIterator<EObject> it = this.process.eAllContents();
		while (it.hasNext()) {
			EObject modelObject = it.next();
			ModelHelper.createExtensionIfNecessary(this.extensionMap, modelObject);
		}

		if (this.extensionMap.get(this.process) == null) {
			ModelHelper.createExtensionIfNecessary(this.extensionMap, this.process);
		}
	}

	public ExtensionMap getExtensionMap() {
		return this.extensionMap;
	}

	public Resource getExtensionsResource() {
		return this.extensionsResource;
	}

	public Process getProcess() {
		return this.process;
	}

	public Resource getProcessResource() {
		return this.processResource;
	}
}
