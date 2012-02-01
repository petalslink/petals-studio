/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsoucing.petals.repositories.explorer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryContentProvider implements ITreeContentProvider, IRepositoryChangeListener {

	private TreeViewer viewer;


	/**
	 * Constructor.
	 */
	public RepositoryContentProvider() {
		RepositoryManager.getInstance().registerListener( this );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getChildren(java.lang.Object)
	 */
	public Object[] getChildren( Object parentElement ) {


		return new Object[ 0 ];
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getParent(java.lang.Object)
	 */
	public Object getParent( Object element ) {


		return null;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #hasChildren(java.lang.Object)
	 */
	public boolean hasChildren( Object element ) {

		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider
	 * #getElements(java.lang.Object)
	 */
	public Object[] getElements( Object inputElement ) {
		return RepositoryManager.getInstance().getRepositories().toArray();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		RepositoryManager.getInstance().unregisterListener( this );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
		this.viewer = (TreeViewer) viewer;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsoucing.petals.repositories.explorer.IRepositoryChangeListener
	 * #sourceAdded(com.ebmwebsoucing.petals.repositories.explorer.model.Repository)
	 */
	public void repositoryAdded( final Repository repository ) {

		Display.getDefault().asyncExec( new Runnable() {
			public void run() {
				Object input = RepositoryContentProvider.this.viewer.getInput();
				RepositoryContentProvider.this.viewer.add( input, repository );
				RepositoryContentProvider.this.viewer.expandToLevel( repository, 1 );
				RepositoryContentProvider.this.viewer.setSelection( new StructuredSelection( repository ));
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.ISourceChangeListener
	 * #sourceChanged(com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource)
	 */
	public void repositoryChanged( final Repository repository ) {

		Display.getDefault().asyncExec( new Runnable() {
			public void run() {
				RepositoryContentProvider.this.viewer.refresh( repository );
				RepositoryContentProvider.this.viewer.expandToLevel( repository, 1 );
				RepositoryContentProvider.this.viewer.setSelection( new StructuredSelection( repository ));
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.ISourceChangeListener
	 * #sourceRemoved(com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource)
	 */
	public void repositoryRemoved( final Repository repository ) {

		Display.getDefault().asyncExec( new Runnable() {
			public void run() {
				RepositoryContentProvider.this.viewer.remove( repository );
			}
		});
	}
}
