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
package org.eclipse.bpel.common.ui.editmodel;

import java.io.IOException;
import java.util.Map;

import org.eclipse.bpel.model.resource.BPELResourceSetImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;

public class EditModelClient {

	/** */
	private ResourceInfo primaryResourceInfo;
	/** */
	private final EditModel editModel;
	/** */
	private IEditModelListener modelListener;
	/** */
	private final IEditorPart editor;


	/**
	 * Creates a new EditModelClient
	 * 
	 * @param editor the editor that is using this model manager
	 * @param file the editor's input
	 * @param modelListener the listener which is used to communicate back to the editor.
	 * @param loadOptions a Map of EMF load/save options. May be empty.
	 */
	public EditModelClient(IEditorPart editor,IFile file,IEditModelListener modelListener, Map<Object,Object> loadOptions) {

		SynchronizationHandler handler = new Synchronizer();
		this.editor = editor;
		this.editModel = getSharedResourceSet(file);
		if( this.editModel == null )
			throw new NullPointerException( "The edit model could not be found." );

		((BPELResourceSetImpl) this.editModel.getResourceSet()).setLoadOptions( loadOptions );
		try {
			if(getCommandStack() == null) {
				EditModelCommandStack commandStack = createCommandStack();
				this.editModel.setCommandStack(commandStack);
			}
			this.primaryResourceInfo = this.editModel.getResourceInfo(file);
			if(loadOptions != null)
				this.primaryResourceInfo.setLoadOptions(loadOptions);
			this.modelListener = modelListener;
			getEditModel().addListener(modelListener);
			new SynchronizationManager(editor,this.editModel,handler);

		} catch (RuntimeException ex) {
			if(this.editModel != null)
				this.editModel.release();
			throw ex;
		}
	}

	/**
	 * @param file
	 * @return
	 */
	protected EditModel getSharedResourceSet(IFile file) {
		return EditModel.getEditModel(file);
	}

	/**
	 * Creates and returns a command stack to be used by this
	 * model manager and the editor.
	 */
	protected EditModelCommandStack createCommandStack() {
		return new EditModelCommandStack();
	}
	/**
	 * Returns the command stack to be used by this
	 * model manager and the editor.
	 */
	public EditModelCommandStack getCommandStack() {
		return this.editModel.getCommandStack();
	}
	/**
	 * Returns the EMFModel editModel used by this model manager.
	 * The EditModel is a model reference count cache.
	 */
	public EditModel getEditModel() {
		return this.editModel;
	}
	/**
	 * Returns the ResourceInfo which is used by the EditModel
	 * and counts the reference for each model cached in the model
	 * editModel.
	 */
	public ResourceInfo getPrimaryResourceInfo() {
		return this.primaryResourceInfo;
	}
	/**
	 * Disposes this model manager, its command stack, and
	 * its model editModel.
	 */
	public void dispose() {
		getEditModel().removeListener(this.modelListener);
		this.editModel.releaseReference(this.primaryResourceInfo);
		this.editModel.release();
	}
	/**
	 * Saves the model
	 */
	public boolean saveAll(IProgressMonitor progressMonitor) {
		return this.editModel.saveAll(progressMonitor);
	}

	/**
	 * Saves the model with a new name
	 */
	public boolean savePrimaryResourceAs(IFile savedFile, IProgressMonitor progressMonitor) {
		return this.editModel.savePrimaryResourceAs(this.primaryResourceInfo,savedFile,progressMonitor);
	}
	void close() {
		this.editor.getSite().getPage().closeEditor(this.editor, false);
	}
	private class Synchronizer implements SynchronizationHandler {
		public void closeEditor() {
			EditModelClient.this.close();
		}
		public boolean saveFileAs(ResourceInfo resourceInfo,IFile file) throws CoreException, IOException {
			if (resourceInfo.equals(EditModelClient.this.primaryResourceInfo))
				return savePrimaryResourceAs(file, null);
			else {
				resourceInfo.saveAs(file);
				return true;
			}
		}
		public void refresh(ResourceInfo resourceInfo) {
			resourceInfo.refresh();
			getCommandStack().flush();
		}
	}
}
