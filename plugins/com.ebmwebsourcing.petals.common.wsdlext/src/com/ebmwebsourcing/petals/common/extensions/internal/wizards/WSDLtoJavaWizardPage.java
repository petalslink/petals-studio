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

package com.ebmwebsourcing.petals.common.extensions.internal.wizards;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.WorkspaceExplorer;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;

/**
 * The page used in the WSDL to Java wizard.
 * <p>
 * It is made up of a text field for the WSDL URL,
 * and selection table for the destination container.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WSDLtoJavaWizardPage extends WizardPage {

	private URI wsdlUri;
	private IContainer outputContainer;
	private boolean complete = false;


	/**
	 * Constructor.
	 * @param pageName
	 * @param selection
	 */
	public WSDLtoJavaWizardPage( String pageName, ISelection selection ) {
		super( pageName );
		setTitle( pageName ); //NON-NLS-1
		setDescription( "Specify the WSDL and Java output locations." ); //NON-NLS-1

		if( ! selection.isEmpty()) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if( o instanceof IContainer )
				this.outputContainer = (IContainer) o;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// WSDL field
		Label l = new Label( container, SWT.NONE );
		l.setText( "WSDL URI:" );
		l.setLayoutData( new GridData());

		final Text text = new Text( container, SWT.BORDER | SWT.SINGLE );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		text.addModifyListener( new ModifyListener () {

			public void modifyText( ModifyEvent e ) {
				String uri = text.getText();
				if( uri.trim().length() == 0 )
					return;

				try {
					WSDLtoJavaWizardPage.this.wsdlUri = UriUtils.urlToUri( uri );

				} catch( Exception e1 ) {
					WSDLtoJavaWizardPage.this.wsdlUri = null;
				}

				validate();
			}
		});


		Composite buttons = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = layout.marginWidth = 0;
		buttons.setLayout( layout );

		Button b = new Button( buttons, SWT.PUSH );
		b.setText( "Browse File System" );
		b.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( text.getShell(), SWT.SINGLE );
				dlg.setFilterNames( new String[] { "WSDL (*.wsdl)" }); //$NON-NLS-1$
				dlg.setFilterExtensions( new String[] { "*.wsdl" }); //$NON-NLS-1$
				String path = dlg.open();

				if( path != null ) {
					text.setText( new File( path ).toURI().toString());
					text.setSelection( path.length());
					text.setFocus();
				}
			}
		});

		b = new Button( buttons, SWT.PUSH );
		b.setText( "Browse Workspace..." );
		b.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				WorkspaceExplorer dlg = new WorkspaceExplorer( getShell(), new String[] { "wsdl" });
				if( dlg.open() == Window.OK ) {
					IResource res = dlg.getSelectedResource();
					text.setText( new File( res.getLocation().toOSString()).toURI().toString());
					text.setSelection( text.getText().length());
					text.setFocus();
				}
			}
		});

		// Container selection
		l = new Label( container, SWT.NONE );
		l.setText( "Select the output location." );
		GridData layoutData = new GridData();
		layoutData.verticalIndent = 15;
		l.setLayoutData( layoutData );

		TreeViewer viewer = new TreeViewer( container, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 200;
		viewer.getTree().setLayoutData( layoutData );
		viewer.setLabelProvider( new WorkbenchLabelProvider ());

		viewer.setContentProvider( new WorkbenchContentProvider () {
			@Override
			public Object[] getChildren( Object o ) {

				if( o instanceof IContainer ) {
					IResource[] members;
					try {
						members = ((IContainer) o).members();
					} catch( Exception e ) {
						return new Object[ 0 ];
					}

					ArrayList<IResource> results = new ArrayList<IResource> ();
					for( IResource member : members ) {
						if( member instanceof IContainer )
							results.add( member);
					}

					return results.toArray();
				}

				return new Object[ 0 ];
			}
		});

		// Set page input
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		viewer.setInput( root );
		if( this.outputContainer != null ) {
			viewer.setSelection( new StructuredSelection( this.outputContainer ), true );
			viewer.expandToLevel( this.outputContainer, 1 );
		}

		viewer.addSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( SelectionChangedEvent event ) {

				IStructuredSelection s = (IStructuredSelection) event.getSelection();
				if( !s.isEmpty())
					WSDLtoJavaWizardPage.this.outputContainer = (IContainer) s.getFirstElement();
				else
					WSDLtoJavaWizardPage.this.outputContainer = null;
				validate();
			}
		});

		text.setFocus();
		setControl( container );
	}


	/**
	 * Validates the page fields.
	 */
	private void validate() {

		String msg = null;
		if( this.wsdlUri == null )
			msg = "The WSDL URI is invalid.";
		else if( this.outputContainer == null )
			msg = "You must select the output location.";

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
	 * @see org.eclipse.jface.wizard.WizardPage#setPageComplete(boolean)
	 */
	@Override
	public void setPageComplete( boolean complete ) {
		this.complete = complete;
		super.setPageComplete( complete );
	}


	/**
	 * @return the outputContainer
	 */
	public IContainer getOutputContainer() {
		return this.outputContainer;
	}


	/**
	 * @return the wsdlUri
	 */
	public URI getWsdlUri() {
		return this.wsdlUri;
	}
}
