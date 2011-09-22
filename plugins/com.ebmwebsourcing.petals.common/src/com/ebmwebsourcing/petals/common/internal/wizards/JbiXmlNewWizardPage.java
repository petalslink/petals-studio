/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.Messages;

/**
 * The only required page to create a new 'jbi.xml' file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiXmlNewWizardPage extends WizardPage {

	/**
	 * The selected container for the target file.
	 */
	private IContainer selectedContainer;

	/**
	 * True to overwrite an existing file, false otherwise.
	 */
	private boolean overwriteExistingFile = false;


	/**
	 * Constructor.
	 * @param pageName
	 * @param selection
	 */
	public JbiXmlNewWizardPage( String pageName, IStructuredSelection selection ) {
		super( pageName );
		setTitle( Messages.NewJbiXmlWizardPage_0 );
		setDescription( Messages.NewJbiXmlWizardPage_1 );

		if( selection != null ) {
			Object o = (selection).getFirstElement();
			if( o instanceof IContainer )
				this.selectedContainer = (IContainer) o;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Container viewer
		new Label( container, SWT.NONE ).setText( Messages.NewJbiXmlWizardPage_2 );
		final Text folderPathText = new Text( container, SWT.SINGLE | SWT.BORDER );
		folderPathText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		TreeViewer viewer = new TreeViewer( container, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.HIDE_SELECTION );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 100;
		viewer.getTree().setLayoutData( layoutData );
		viewer.setLabelProvider( new WorkbenchLabelProvider ());
		viewer.setContentProvider( new WorkbenchContentProvider() {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.ui.model.BaseWorkbenchContentProvider
			 * #getChildren(java.lang.Object)
			 */
			@Override
			public Object[] getChildren( Object o ) {

				if( o instanceof IProject && ! ((IProject) o).isOpen())
					return new Object[ 0 ];

				List<IResource> children = new ArrayList<IResource>();
				if( o instanceof IContainer ) {
					try {
						IResource[] members = ((IContainer) o).members();
						for( IResource member : members ) {
							if( member instanceof IContainer
										|| ( member instanceof IFile && member.getName().equals( "jbi.xml" ))) //$NON-NLS-1$
								children.add( member );
						}
					}
					catch( CoreException e ) {
						e.printStackTrace();
					}
				}

				return children.toArray( new IResource[ 0 ]);
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.ui.model.BaseWorkbenchContentProvider
			 * #hasChildren(java.lang.Object)
			 */
			@Override
			public boolean hasChildren( Object element ) {
				return getChildren( element ).length > 0;
			}
		});

		// Set page input
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		viewer.setInput( root );
		if( this.selectedContainer != null ) {
			viewer.setSelection( new StructuredSelection( this.selectedContainer ), true );
			viewer.expandToLevel( this.selectedContainer, 1 );
		}

		viewer.addPostSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( o instanceof IContainer )
					JbiXmlNewWizardPage.this.selectedContainer = (IContainer) o;
				else
					JbiXmlNewWizardPage.this.selectedContainer = ((IFile) o).getParent();

				String path = JbiXmlNewWizardPage.this.selectedContainer.getFullPath().toOSString();
				String fileSeparator = System.getProperty( "file.separator" ); //$NON-NLS-1$
				if( path.startsWith( fileSeparator ))
					path = path.substring( fileSeparator.length());
				folderPathText.setText( path );
				validate();
			}
		});


		// Overwrite existing file ?
		final Button overwriteButton = new Button( container, SWT.CHECK );
		overwriteButton.setText( Messages.NewJbiXmlWizardPage_5 );
		overwriteButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				JbiXmlNewWizardPage.this.overwriteExistingFile = overwriteButton.getSelection();
				validate();
			}
		});


		// Set control
		setControl( container );
	}


	/**
	 * Validates the page entries.
	 */
	private void validate() {

		String errorMsg = null;
		boolean isComplete = true;

		if( this.selectedContainer == null ) {
			isComplete = false;
			errorMsg = Messages.NewJbiXmlWizardPage_6;
		}
		else {
			IFile f = this.selectedContainer.getFile( new Path( "jbi.xml" )); //$NON-NLS-1$
			if( f.exists() && ! this.overwriteExistingFile ) {
				isComplete = false;
				errorMsg = Messages.NewJbiXmlWizardPage_8;
			}
		}

		setErrorMessage( errorMsg );
		setPageComplete( isComplete );
	}


	/**
	 * Gets the content of the created jbi.xml file.
	 * @return the content as an input stream
	 */
	public InputStream getInitialContents() {

		String jbiXmlTemplate =
			"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" //$NON-NLS-1$
			+ "<jbi:jbi\n"  //$NON-NLS-1$
			+ "\tversion=\"1.0\"\n"  //$NON-NLS-1$
			+ "\txmlns=\"http://java.sun.com/xml/ns/jbi\"\n"  //$NON-NLS-1$
			+ "\txmlns:jbi=\"http://java.sun.com/xml/ns/jbi\">\n\n" //$NON-NLS-1$
			+ "</jbi:jbi>"; //$NON-NLS-1$

		InputStream inputStream = new ByteArrayInputStream( jbiXmlTemplate.getBytes());
		return inputStream;
	}


	/**
	 * Creates the new 'jbi.xml' file at the selected location.
	 * @return the created {@link IFile}
	 * @throws CoreException if something went wrong
	 */
	public IFile createNewFile() throws CoreException {

		final IFile file = this.selectedContainer.getFile( new Path( "jbi.xml" )); //$NON-NLS-1$
		IWorkspaceRunnable op = new IWorkspaceRunnable() {
			public void run( IProgressMonitor monitor ) throws CoreException {
				try {
					if( file.exists())
						file.setContents( getInitialContents(), true, true, monitor );
					else
						file.create( getInitialContents(), true, monitor );

				} finally {
					monitor.done();
				}
			}
		};

		ResourcesPlugin.getWorkspace().run( op, new NullProgressMonitor());
		return file;
	}


	/**
	 * @return the selectedContainer
	 */
	public IContainer getSelectedContainer() {
		return this.selectedContainer;
	}
}
