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

package com.ebmwebsourcing.petals.common.extensions.internal.wizards;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.extensions.PetalsCommonWsdlExtPlugin;

/**
 * The first page used in the Java2Wsdl wizard.
 * <p>It aims at selecting the Java class to transform into a WSDL.</p>
 * <p>
 * It contains a selection table displaying all the Java projects
 * available in the workspace, and a text field to select
 * a class from the selected project.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JavaToWSDLWizardPage extends WizardPage {

	private String className;
	private IJavaProject javaProject;

	private Text classText;
	private boolean complete = false;
	private boolean firstAppearance = true;


	/**
	 * Constructor.
	 * @param pageName
	 * @param selection
	 */
	public JavaToWSDLWizardPage( String pageName, IStructuredSelection selection ) {
		super( pageName );
		setTitle( "Java Interface" ); //NON-NLS-1
		setDescription( "Select a Java interface." ); //NON-NLS-1

		if( selection != null && ! selection.isEmpty()) {
			Object o = selection.getFirstElement();
			try {
				if( o instanceof IProject
							&& ((IProject) o).isAccessible()
							&& ((IProject) o).hasNature( JavaCore.NATURE_ID ))
					this.javaProject = JavaCore.create((IProject) o);

			} catch( CoreException e ) {
				PetalsCommonWsdlExtPlugin.log( e, IStatus.WARNING );
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Container selection
		Label l = new Label( container, SWT.NONE );
		l.setText( "Select the Java project that contains the classes." );
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		l.setLayoutData( layoutData );

		TreeViewer viewer = new TreeViewer( container, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		viewer.getTree().setLayoutData( layoutData );

		viewer.setLabelProvider( new WorkbenchLabelProvider ());
		viewer.setContentProvider( new WorkbenchContentProvider () {
			@Override
			public Object[] getChildren( Object o ) {

				if( o instanceof IWorkspaceRoot ) {
					IProject[] projects = ((IWorkspaceRoot) o).getProjects();
					ArrayList<IJavaProject> result = new ArrayList<IJavaProject> ();
					for( IProject project : projects ) {

						try {
							if( !project.isOpen() || !project.hasNature( JavaCore.NATURE_ID ))
								continue;
						} catch( CoreException e ) {
							e.printStackTrace();
							continue;
						}

						IJavaProject p = JavaCore.create( project );
						result.add( p );
					}

					return result.toArray();
				}

				return new Object[ 0 ];
			}
		});

		// Set page input
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		viewer.setInput( root );
		if( this.javaProject != null ) {
			viewer.setSelection( new StructuredSelection( this.javaProject ), true );
			viewer.expandToLevel( this.javaProject, 1 );
		}

		viewer.addSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( SelectionChangedEvent event ) {

				IStructuredSelection s = (IStructuredSelection) event.getSelection();
				if( ! s.isEmpty())
					JavaToWSDLWizardPage.this.javaProject = (IJavaProject) s.getFirstElement();
				else
					JavaToWSDLWizardPage.this.javaProject = null;
				validate();
			}
		});

		viewer.addDoubleClickListener( new IDoubleClickListener () {
			public void doubleClick( DoubleClickEvent event ) {
				openClassSelectionDialog();
			}
		});


		// Class field
		l = new Label( container, SWT.NONE );
		l.setText( "Select the Java interface." );
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 15;
		l.setLayoutData( layoutData );

		this.classText = new Text( container, SWT.BORDER | SWT.SINGLE );
		this.classText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.classText.addModifyListener( new ModifyListener () {
			public void modifyText( ModifyEvent e ) {
				JavaToWSDLWizardPage.this.className = JavaToWSDLWizardPage.this.classText.getText();
				validate();
			}
		});

		Button b = new Button( container, SWT.PUSH );
		b.setText( "Browse..." );
		b.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				openClassSelectionDialog();
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {
				openClassSelectionDialog();
			}
		});

		this.classText.setFocus();
		setControl( container );
	}


	/**
	 * Open a dialog to select a class.
	 */
	private void openClassSelectionDialog () {

		if( this.javaProject == null )
			return;

		Shell shell = this.classText.getShell();
		IJavaSearchScope scope = SearchEngine.createJavaSearchScope(
					new IJavaElement[] { this.javaProject },
					IJavaSearchScope.SOURCES
					| IJavaSearchScope.APPLICATION_LIBRARIES
					| IJavaSearchScope.REFERENCED_PROJECTS );

		String filter = this.classText.getText().trim();
		filter = filter.length() == 0 ? "?" : filter;
		try {
			SelectionDialog dlg = JavaUI.createTypeDialog(
						shell,
						new ProgressMonitorDialog( shell ),
						scope,
						IJavaElementSearchConstants.CONSIDER_INTERFACES,
						true,
						filter );

			if( dlg.open() == Window.OK ) {
				IType type = (IType) dlg.getResult()[ 0 ];
				String selection = type.getFullyQualifiedName();
				this.classText.setText( selection );
				this.classText.setSelection( selection.length());
			}

		} catch( JavaModelException e ) {
			PetalsCommonWsdlExtPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Validates the page fields.
	 */
	private void validate() {

		String msg = null;
		if( this.javaProject == null  )
			msg = "You must select a Java project.";
		else if( this.className == null )
			msg = "You must select a Java interface.";

		if( this.firstAppearance )
			this.firstAppearance = false;
		else
			setErrorMessage( msg );

		setPageComplete( msg == null );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		return this.complete;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #setPageComplete(boolean)
	 */
	@Override
	public void setPageComplete( boolean complete ) {
		this.complete = complete;
		super.setPageComplete( complete );
	}


	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}


	/**
	 * @return the javaProject
	 */
	public IJavaProject getJavaProject() {
		return this.javaProject;
	}
}
