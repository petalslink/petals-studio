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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.ProblemsLabelDecorator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;

/**
 * A wizard page to select Java libraries from the dependencies of a Java project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class LibrariesExportWizardPage extends WizardPage implements IWizardPage {

	private Image jarImage;
	private CheckboxTreeViewer libViewer;
	private final Map<IJavaProject, Set<JavaProjectLibrary>> dependencies;
	private final Set<File> librariesToImport;



	/**
	 * @param pageName
	 */
	public LibrariesExportWizardPage( String pageName ) {
		super( pageName );

		this.dependencies = new HashMap<IJavaProject, Set<JavaProjectLibrary>> ();
		this.librariesToImport = new HashSet<File> ();

		ImageDescriptor desc = PetalsCommonPlugin.getImageDescriptor( "icons/obj16/jar_l_obj.gif" );
		if( desc != null )
			this.jarImage = desc.createImage();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout ());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		new Label( container, SWT.NONE ).setText( "Select the dependencies to export." );

		this.libViewer = new CheckboxTreeViewer(
					container,
					SWT.CHECK | SWT.BORDER | SWT.HIDE_SELECTION | SWT.SINGLE );

		this.libViewer.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.libViewer.setContentProvider( new JavaLibaryContentProvider ());

		int labelFlags = JavaElementLabelProvider.SHOW_BASICS
		| JavaElementLabelProvider.SHOW_OVERLAY_ICONS
		| JavaElementLabelProvider.SHOW_SMALL_ICONS;

		final DecoratingLabelProvider labelProvider = new DecoratingLabelProvider(
					new JavaElementLabelProvider( labelFlags ),
					new ProblemsLabelDecorator( null )) {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.DecoratingLabelProvider
			 * #getText(java.lang.Object)
			 */
			@Override
			public String getText( Object element ) {
				if( element instanceof JavaProjectLibrary )
					return ((JavaProjectLibrary) element).library.getName();
				return super.getText( element );
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.viewers.DecoratingLabelProvider
			 * #getImage(java.lang.Object)
			 */
			@Override
			public Image getImage( Object element ) {
				if( element instanceof JavaProjectLibrary )
					return LibrariesExportWizardPage.this.jarImage;
				return super.getImage( element );
			}
		};

		this.libViewer.setLabelProvider( labelProvider );
		this.libViewer.setInput( new Object ());
		this.libViewer.addCheckStateListener( new LibraryTreeCheckStateListener ());
		this.libViewer.getTree().addMouseMoveListener( new MouseMoveListener () {
			public void mouseMove( MouseEvent e ) {

				TreeItem item = ((Tree) e.widget).getItem ( new Point( e.x, e.y ));
				if( item != null && item.getData() != null && item.getData() instanceof JavaProjectLibrary ) {
					JavaProjectLibrary jpl = (JavaProjectLibrary) item.getData();
					String tooltip = jpl.library.getAbsolutePath();
					item.getParent().setToolTipText( tooltip );
				}
				else
					LibrariesExportWizardPage.this.libViewer.getTree().setToolTipText( null );
			}
		});

		setControl( container );
	}


	/**
	 * @param selectedProject the selectedProject to set
	 */
	public void setSelectedJavaProject( IJavaProject selectedProject ) {

		this.dependencies.clear();
		if( selectedProject != null ) {
			List<IJavaProject> proj = JavaUtils.getJavaProjectDependencies( selectedProject );
			for( IJavaProject jp : proj ) {

				List<String> locations = JavaUtils.getClasspath( jp, false, false );
				Set<JavaProjectLibrary> libs = new HashSet<JavaProjectLibrary> ();
				for( String loc : locations ) {
					if( !loc.endsWith( ".jar" ) && !loc.endsWith( ".zip" ))
						continue;

					File f = new File( loc );
					JavaProjectLibrary jpl = new JavaProjectLibrary ();
					jpl.javaProject = jp;
					jpl.library = f;
					libs.add( jpl );
				}

				this.dependencies.put( jp, libs );
			}
		}

		if( this.libViewer != null ) {
			this.libViewer.refresh();
			if( selectedProject != null ) {
				this.libViewer.expandToLevel( selectedProject, 1 );
				this.libViewer.setSelection( new StructuredSelection( selectedProject ));
			}
		}
	}


	/**
	 * @return the librariesToImport
	 */
	public Set<File> getLibariesToImport() {
		return this.librariesToImport;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {

		if( this.jarImage != null )
			this.jarImage.dispose();
		super.dispose();
	}


	/**
	 * 
	 */
	private class JavaLibaryContentProvider implements ITreeContentProvider {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider
		 * #getChildren(java.lang.Object)
		 */
		public Object[] getChildren( Object parentElement ) {

			if( parentElement instanceof IJavaProject ) {
				IJavaProject jp = (IJavaProject) parentElement;
				Set<JavaProjectLibrary> libs = LibrariesExportWizardPage.this.dependencies.get( jp );
				return libs.toArray();
			}
			return new Object[ 0 ];
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider
		 * #getParent(java.lang.Object)
		 */
		public Object getParent( Object element ) {

			if( element instanceof JavaProjectLibrary )
				return ((JavaProjectLibrary) element).javaProject;
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider
		 * #hasChildren(java.lang.Object)
		 */
		public boolean hasChildren( Object element ) {
			return getChildren( element ).length > 0;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider
		 * #getElements(java.lang.Object)
		 */
		public Object[] getElements( Object inputElement ) {
			return LibrariesExportWizardPage.this.dependencies.keySet().toArray();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider
		 * #dispose()
		 */
		public void dispose() {
			// nothing
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider
		 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
			// nothing
		}
	}


	/**
	 * 
	 */
	private static class JavaProjectLibrary {
		IJavaProject javaProject;
		File library;
	}


	/**
	 * 
	 */
	private class LibraryTreeCheckStateListener implements ICheckStateListener {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICheckStateListener
		 * #checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
		 */
		public void checkStateChanged( CheckStateChangedEvent event ) {

			// JavaProjectLibrary
			if( event.getElement() instanceof JavaProjectLibrary ) {

				JavaProjectLibrary jpl = (JavaProjectLibrary) event.getElement();
				updateHierarchyState( jpl );

				if( event.getChecked())
					LibrariesExportWizardPage.this.librariesToImport.add( jpl.library );
				else
					LibrariesExportWizardPage.this.librariesToImport.remove( jpl.library );
			}

			// IJavaProject
			else if( event.getElement() instanceof IJavaProject ) {

				IJavaProject jp = (IJavaProject) event.getElement();
				LibrariesExportWizardPage.this.libViewer.setSubtreeChecked( jp, event.getChecked());
				Set<JavaProjectLibrary> jpls = LibrariesExportWizardPage.this.dependencies.get( jp );

				List<File> libs = new ArrayList<File> ();
				for( JavaProjectLibrary jpl : jpls )
					libs.add( jpl.library );
				if( event.getChecked())
					LibrariesExportWizardPage.this.librariesToImport.addAll( libs );
				else
					LibrariesExportWizardPage.this.librariesToImport.removeAll( libs );
			}
		}

		/**
		 * Update the check state in the tree.
		 * 
		 * @param jpl
		 * @param checked
		 */
		private void updateHierarchyState( JavaProjectLibrary jpl ) {

			Set<JavaProjectLibrary> jpls = LibrariesExportWizardPage.this.dependencies.get( jpl.javaProject );
			int childrenCount = jpls.size();
			int checkedChildren = 0;
			for( JavaProjectLibrary jp : jpls ) {
				if( LibrariesExportWizardPage.this.libViewer.getChecked( jp ))
					checkedChildren ++;
			}

			if( checkedChildren == 0 )
				LibrariesExportWizardPage.this.libViewer.setGrayChecked( jpl.javaProject, false );
			else if( checkedChildren == childrenCount ) {
				LibrariesExportWizardPage.this.libViewer.setGrayed( jpl.javaProject, false );
				LibrariesExportWizardPage.this.libViewer.setChecked( jpl.javaProject, true );
			}
			else
				LibrariesExportWizardPage.this.libViewer.setGrayChecked( jpl.javaProject, true );
		}
	}
}
