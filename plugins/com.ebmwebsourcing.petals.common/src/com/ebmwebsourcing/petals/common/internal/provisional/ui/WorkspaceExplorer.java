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

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WorkspaceExplorer extends TitleAreaDialog {

	private TreeViewer wkViewer;
	private final List<String> extensions;
	private IResource selectedResource;


	public WorkspaceExplorer( Shell parentShell, String[] extensions ) {
		super( parentShell );
		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX );
		this.extensions = extensions != null ? Arrays.asList( extensions ) : new ArrayList<String>( 0 );
	}


	/**
	 * @return the selectedResource
	 */
	public IResource getSelectedResource() {
		return this.selectedResource;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		Composite bigContainer = (Composite) super.createDialogArea( parent );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		bigContainer.setLayout( layout );
		bigContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Composite container = new Composite( bigContainer, SWT.NONE );
		layout = new GridLayout ();
		layout.marginWidth = layout.marginHeight = 0;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		addWorkspaceExplorer( container );
		this.wkViewer.setInput( ResourcesPlugin.getWorkspace().getRoot());

		getShell().setText( "Workspace Explorer" );
		setTitle( "Workspace Explorer" );
		setMessage( "Browse the workspace." );

		return bigContainer;
	}


	/**
	 * Add an explorer for the workspace resources.
	 * @param container
	 */
	private void addWorkspaceExplorer( Composite container ) {

		int style = SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.HIDE_SELECTION;
		this.wkViewer = new TreeViewer( container, style );

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 100;
		this.wkViewer.getTree().setLayoutData( layoutData );
		this.wkViewer.setLabelProvider( new WorkbenchLabelProvider ());
		this.wkViewer.setContentProvider( new ITreeContentProvider() {

			/**
			 * @param container
			 * @return the list of sub-containers having resources with one of the filtered extension.
			 * <p>
			 * The result does not include binary folders from Java projects,
			 * and skips resources to hide.
			 * </p>
			 */
			private Object[] getContainerElements( IContainer container ) {
				IResource[] res = ResourceUtils.getDirectValidChildren(
							container,
							WorkspaceExplorer.this.extensions,
							new ArrayList<IResource> ());
				return res;
			}


			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ITreeContentProvider
			 * #getChildren(java.lang.Object)
			 */
			@Override
			public Object[] getChildren( Object o ) {
				if( o instanceof IContainer )
					return getContainerElements((IContainer) o);
				return new Object[ 0 ];
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ITreeContentProvider
			 * #hasChildren(java.lang.Object)
			 */
			@Override
			public boolean hasChildren( Object element ) {
				if( element instanceof IContainer )
					return getContainerElements((IContainer) element).length > 0;
					return false;
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ITreeContentProvider
			 * #getParent(java.lang.Object)
			 */
			@Override
			public Object getParent( Object element ) {

				if( element instanceof IResource ) {
					IContainer parent = ((IResource) element).getParent();
					if( parent instanceof IWorkspaceRoot )
						return null;

					return parent;
				}

				return null;
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.IStructuredContentProvider
			 * #getElements(java.lang.Object)
			 */
			@Override
			public Object[] getElements( Object inputElement ) {

				if( inputElement instanceof IWorkspaceRoot )
					return ((IWorkspaceRoot) inputElement).getProjects();
				else if( inputElement instanceof String ) {
					//					IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject((String) inputElement);
					//					if( p != null ) {
					//						List<IProject> dependencies = JDTUtils.getJavaProjectDependencies( p );
					//						return dependencies.toArray();
					//					}
				}

				return new Object[ 0 ];
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
			 */
			@Override
			public void dispose() {
				// nothing
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.IContentProvider
			 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
			 */
			@Override
			public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
				// nothing
			}
		});


		// Selection listener
		this.wkViewer.addSelectionChangedListener( new ISelectionChangedListener () {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				if( event.getSelection().isEmpty())
					return;

				Button b = getButton( IDialogConstants.OK_ID );
				Object res = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( res instanceof IFile ) {
					WorkspaceExplorer.this.selectedResource = (IFile) res;
					if( b != null )
						b.setEnabled( true );
				}
				else {
					WorkspaceExplorer.this.selectedResource = null;
					if( b != null )
						b.setEnabled( false );
				}
			}
		});


		// Double-clic means selection
		this.wkViewer.addDoubleClickListener( new IDoubleClickListener () {
			@Override
			public void doubleClick( DoubleClickEvent event ) {

				if( event.getSelection().isEmpty())
					return;

				Button b = getButton( IDialogConstants.OK_ID );
				Object res = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( res instanceof IFile ) {
					WorkspaceExplorer.this.selectedResource = (IFile) res;
					if( b != null )
						b.setEnabled( true );
					close();
				}
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize () {
		return new Point( 400, 500 );
	}
}
