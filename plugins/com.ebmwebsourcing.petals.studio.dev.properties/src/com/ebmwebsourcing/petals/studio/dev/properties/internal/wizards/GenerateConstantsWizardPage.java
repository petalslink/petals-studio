/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties.internal.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.studio.dev.properties.internal.PetalsStudioDevPlugin;
import com.ebmwebsourcing.petals.studio.dev.properties.internal.Utils;

/**
 * The only required page to create a new 'jbi.xml' file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GenerateConstantsWizardPage extends WizardPage {

	private IPackageFragmentRoot target;
	private final IProject originalSelection;
	private String javaPackage, className;


	/**
	 * Constructor.
	 * @param project
	 */
	public GenerateConstantsWizardPage( IProject project ) {
		super( "MainPage" );
		this.originalSelection = project;
		setTitle( "Constants Generation" );
		setDescription( "Generate a Java class with model constants." );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().extendedMargins( 15, 15, 15, 10 ).numColumns( 2 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Container viewer
		Label l = new Label( container, SWT.NONE );
		l.setText( "Select the output directory to generate the Java constants." );
		GridDataFactory.swtDefaults().span( 2, 1 ).applyTo( l );

		TreeViewer viewer = new TreeViewer( container, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.HIDE_SELECTION );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 100;
		layoutData.horizontalSpan = 2;
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

				List<Object> children = new ArrayList<Object> ();
				try {
					if( o instanceof IJavaProject ) {
						for( IPackageFragmentRoot root : ((IJavaProject) o).getPackageFragmentRoots()) {
							if( root.getResource() instanceof IContainer )
								children.add( root );
						}

					} else if( o instanceof IWorkspaceRoot ) {
						for( IProject p : ((IWorkspaceRoot) o).getProjects()) {
							if( ! p.isAccessible()
									|| ! p.hasNature( JavaCore.NATURE_ID ))
								continue;

							IJavaProject jp = JavaCore.create( p );
							if( jp != null )
								children.add( jp );
						}
					}

				} catch( CoreException e ) {
					PetalsStudioDevPlugin.log( e, IStatus.ERROR );
				}

				return children.toArray( new Object[ 0 ]);
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
		viewer.setInput( ResourcesPlugin.getWorkspace().getRoot());
		if( this.originalSelection != null ) {
			try {
				IJavaProject jp = JavaCore.create( this.originalSelection );
				for( IPackageFragmentRoot root : jp.getPackageFragmentRoots()) {
					if( root.getResource() instanceof IContainer ) {
						GenerateConstantsWizardPage.this.target = root;
						break;
					}
				}

			} catch( JavaModelException e ) {
				PetalsStudioDevPlugin.log( e, IStatus.ERROR, "This should not happen (check in the handler)." );
			}
		}

		if( this.target != null ) {
			viewer.setSelection( new StructuredSelection( this.target ), true );
			viewer.expandToLevel( this.target, 1 );
			viewer.getTree().notifyListeners( SWT.Selection, new Event());
		}


		// Java meta
		new Label( container, SWT.NONE ).setText( "Java Package:" );
		final Text packageText = new Text( container, SWT.SINGLE | SWT.BORDER );
		packageText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		packageText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				GenerateConstantsWizardPage.this.javaPackage = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Java Class Name:" );
		final Text classText = new Text( container, SWT.SINGLE | SWT.BORDER );
		classText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		classText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				GenerateConstantsWizardPage.this.className = ((Text) e.widget).getText().trim();
				validate();
			}
		});


		// Add the missing listeners
		viewer.addPostSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( o instanceof IPackageFragmentRoot ) {

					GenerateConstantsWizardPage.this.target = (IPackageFragmentRoot) o;
					String pName = GenerateConstantsWizardPage.this.target.getJavaProject().getProject().getName();
					packageText.setText( pName.replaceAll( "-", "." ) + ".generated" );

					int index = pName.lastIndexOf( '.' ) + 1;
					if( index <= 0 || index > pName.length())
						pName = "Default";
					else
						pName = pName.substring( index );
					classText.setText( pName );

				} else {
					GenerateConstantsWizardPage.this.target = null;
				}
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
		if( this.target == null )
			errorMsg = "You must select a source folder to generate the code.";
		else if( Utils.isEmpty( this.javaPackage ))
			errorMsg = "You must specify a Java package name.";
		else if( Utils.isEmpty( this.className ))
			errorMsg = "You must specify a Java class name.";

		setErrorMessage( errorMsg );
		setPageComplete( errorMsg == null );
	}


	/**
	 * @return the javaPackage
	 */
	public String getJavaPackage() {
		return this.javaPackage;
	}


	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}


	/**
	 * @return the target
	 */
	public IPackageFragmentRoot getTarget() {
		return this.target;
	}
}
