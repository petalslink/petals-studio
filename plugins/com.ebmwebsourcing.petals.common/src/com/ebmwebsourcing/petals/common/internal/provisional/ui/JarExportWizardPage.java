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

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaElementComparator;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.ProblemsLabelDecorator;
import org.eclipse.jdt.ui.StandardJavaElementContentProvider;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.jdt.ContainerFilter;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;

/**
 * A wizard page to export some elements of a Java project into a JAR.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JarExportWizardPage extends WizardPage implements IWizardPage {

	private IJavaProject selectedProject;
	private CheckboxTreeAndListGroup javaSelectionViewer;

	private final JarPackageData jarDescription = new JarPackageData ();
	private boolean exportSources, compressJar, exportWarningsAndErrors = true;



	/**
	 * @param pageName
	 */
	public JarExportWizardPage( String pageName ) {
		super( pageName );

		this.jarDescription.setIncludeDirectoryEntries( true );
		this.jarDescription.setExportClassFiles( true );
		this.jarDescription.setOverwrite( true );
		this.jarDescription.setIncludeDirectoryEntries( true );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout ());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		new Label( container, SWT.NONE ).setText(
		"Select the resources to export.\nThe following projects are the project you selected and the projects it depends on." );


		int labelFlags = JavaElementLabelProvider.SHOW_BASICS
		| JavaElementLabelProvider.SHOW_OVERLAY_ICONS
		| JavaElementLabelProvider.SHOW_SMALL_ICONS;

		ITreeContentProvider treeContentProvider = new StandardJavaElementContentProvider() {
			@Override
			public boolean hasChildren(Object element) {
				return !(element instanceof IPackageFragment) && super.hasChildren(element);
			}

			@Override
			public Object[] getElements( Object parent ) {
				if( parent != null && parent instanceof IWorkspaceRoot ) {
					List<IJavaProject> result =
						JavaUtils.getJavaProjectDependencies( JarExportWizardPage.this.selectedProject );
					return result.toArray();
				}
				return super.getElements( parent );
			}
		};

		final DecoratingLabelProvider provider = new DecoratingLabelProvider(
					new JavaElementLabelProvider( labelFlags ), new ProblemsLabelDecorator( null ));

		this.javaSelectionViewer = new CheckboxTreeAndListGroup(
					container,
					ResourcesPlugin.getWorkspace().getRoot(),
					treeContentProvider,
					provider,
					new StandardJavaElementContentProvider(),
					provider,
					SWT.NONE,
					420,
					150 ) {

			/*
			 * (non-Javadoc)
			 * @see com.ebmwebsourcing.petals.common.internal.provisional.ui.jdt.CheckboxTreeAndListGroup
			 * #setTreeChecked(java.lang.Object, boolean)
			 */
			@Override
			protected void setTreeChecked( final Object element, final boolean state ) {
				if ( element instanceof IResource ) {
					final IResource resource= (IResource) element;
					if( resource.getName().charAt( 0 ) == '.' )
						return;
				}
				super.setTreeChecked( element, state );
			}
		};

		this.javaSelectionViewer.addTreeFilter( new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {

				boolean result = true;
				if( element instanceof IPackageFragment ) {
					IPackageFragment pkg = (IPackageFragment)element;
					try {
						if( pkg.isDefaultPackage())
							result = pkg.hasChildren();
						else
							result = ! pkg.hasSubpackages() || pkg.hasChildren() || pkg.getNonJavaResources().length > 0;

					} catch( JavaModelException e ) {
						result = false;
					}
				}

				return result;
			}
		});

		this.javaSelectionViewer.setTreeComparator( new JavaElementComparator());
		this.javaSelectionViewer.setListComparator( new JavaElementComparator());
		this.javaSelectionViewer.addTreeFilter( new ContainerFilter( ContainerFilter.FILTER_NON_CONTAINERS ));
		this.javaSelectionViewer.addTreeFilter( new ViewerFilter () {
			@Override
			public boolean select( Viewer viewer, Object p, Object element ) {
				if( element instanceof IPackageFragmentRoot ) {
					IPackageFragmentRoot root= (IPackageFragmentRoot) element;
					return !root.isArchive() && !root.isExternal();
				}
				return true;
			}
		});

		this.javaSelectionViewer.addListFilter( new ContainerFilter( ContainerFilter.FILTER_CONTAINERS ));
		this.javaSelectionViewer.expandTreeToLevel( this.selectedProject, 1 );
		this.javaSelectionViewer.addCheckStateListener( new ICheckStateListener () {
			public void checkStateChanged( CheckStateChangedEvent event ) {
				validate();
			}
		});


		// Options
		final Button exportSourcesButton = new Button( container, SWT.CHECK );
		exportSourcesButton.setText( "Export sources" );
		exportSourcesButton.setSelection( this.exportSources );
		exportSourcesButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				JarExportWizardPage.this.exportSources = exportSourcesButton.getSelection();
				validate();
			}
		});

		final Button compressJarButton = new Button( container, SWT.CHECK );
		compressJarButton.setText( "Compress the content of the JAR file" );
		compressJarButton.setSelection( this.compressJar );
		compressJarButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				JarExportWizardPage.this.compressJar = compressJarButton.getSelection();
				validate();
			}
		});

		final Button exportWarningsAndErrorsButton = new Button( container, SWT.CHECK );
		exportWarningsAndErrorsButton.setText( "Export class files with compile warnings or errors" );
		exportWarningsAndErrorsButton.setSelection( this.exportWarningsAndErrors );
		exportWarningsAndErrorsButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				JarExportWizardPage.this.exportWarningsAndErrors = exportWarningsAndErrorsButton.getSelection();
				validate();
			}
		});

		validate();
		setErrorMessage( null );
		setControl( container );
	}


	/**
	 * Validate the page entries.
	 */
	private void validate() {

		this.jarDescription.setExportJavaFiles( this.exportSources );
		this.jarDescription.setCompress( this.compressJar );
		this.jarDescription.setExportErrors( this.exportWarningsAndErrors );
		this.jarDescription.setExportWarnings( this.exportWarningsAndErrors );

		IFile[] files = getSelectedClassFiles();
		if( files.length == 0 ) {
			setPageComplete( false );
			return;
		}

		this.jarDescription.setElements( files );
		setPageComplete( true );
	}


	/**
	 * @return
	 */
	private IFile[] getSelectedClassFiles () {

		Iterator<?> it = this.javaSelectionViewer.getAllCheckedListItems();
		if( !it.hasNext())
			return new IFile[ 0 ];

		ArrayList<IFile> files = new ArrayList<IFile> ();
		while( it.hasNext()) {
			Object o = it.next();
			if( o instanceof ICompilationUnit ) {
				ICompilationUnit cu = (ICompilationUnit) o;
				try {
					IResource r = cu.getCorrespondingResource();
					if( r != null && r instanceof IFile )
						files.add((IFile) r);

				} catch( JavaModelException e ) {
					e.printStackTrace();
				}
			}
			else if( o instanceof IFile )
				files.add((IFile) o);
		}

		IFile[] result = new IFile[ files.size()];
		return files.toArray( result );
	}


	/**
	 * Return the class file resources associated with the given elements.
	 * <p>It is assumed the java projects have already been compiled and the resources are all in a saved state.</p>
	 * 
	 * @param elements
	 * @return
	 */
	public static Collection<IFile> getCorrespondingSources( Collection<IJavaElement> elements ){

		IRegion region = JavaCore.newRegion();
		for( IJavaElement element : elements )
			region.add( element );

		IResource[] resources = JavaCore.getGeneratedResources( region, false );
		Set<IFile> classes = new HashSet<IFile>( resources.length );
		for( IResource resource : resources ) {
			if( resource instanceof IFile )
				classes.add((IFile) resource);
		}

		return classes;
	}


	/**
	 * @return the jarDescription its location must be set by the class that calls this method.
	 */
	public JarPackageData getJarDescription() {
		return this.jarDescription;
	}


	/**
	 * @param selectedProject the selectedProject to set
	 */
	public void setSelectedJavaProject( IJavaProject selectedProject ) {

		this.selectedProject = selectedProject;
		if( this.javaSelectionViewer != null ) {
			this.javaSelectionViewer.refresh();

			if( selectedProject != null ) {
				this.javaSelectionViewer.expandTreeToLevel( selectedProject, 1 );
				this.javaSelectionViewer.setTreeSelection( new StructuredSelection( selectedProject ));
			}
		}
	}
}
