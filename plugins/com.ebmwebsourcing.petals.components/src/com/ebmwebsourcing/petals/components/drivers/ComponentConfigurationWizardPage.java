/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.components.drivers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;
import com.ebmwebsourcing.petals.components.utils.ArtifactArchiveUtils;

/**
 * A wizard page to specify one or several SL to add to a component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentConfigurationWizardPage extends WizardPage implements IWizardPage {

	private final static String NAME = "Name";
	private final static String VERSION = "Version";

	private boolean overwrite;
	private String componentUrl, updatedFileLocation;
	private final Map<String,String> slNameToVersion = new HashMap<String,String> ();


	/**
	 * Constructor.
	 */
	protected ComponentConfigurationWizardPage() {
		super( "Component Configuration Page" );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		setTitle( "Configured Component" );
		setMessage( "Update a Petals component to use one or several shared libraries." );

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// Select the component's archive
		Composite subC = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subC.setLayout( layout );
		subC.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Label l = new Label( subC, SWT.NONE );
		l.setText( "Component's URL:" );
		l.setToolTipText( "The URL of the component's ZIP" );

		final Text componentUrlText = new Text( subC, SWT.SINGLE | SWT.BORDER );
		componentUrlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		componentUrlText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				ComponentConfigurationWizardPage.this.componentUrl = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		Button browseButton = new Button( subC, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( getShell(), SWT.SINGLE );
				dlg.setText( "Select a zipped component file" );
				dlg.setFilterNames( new String[] { "ZIP (*.zip)" });
				dlg.setFilterExtensions( new String[] { "*.zip" });

				String path = PreferencesManager.getSavedLocation();
				if( ! StringUtils.isEmpty( path )) {
					try {
						File f = new File( path );
						dlg.setFilterPath( f.getParentFile().getAbsolutePath());
						dlg.setFileName( f.getName());

					} catch( Exception e1 ) {
						// nothing
					}
				}

				String fn = dlg.open();
				if( fn != null ) {
					PreferencesManager.setSavedLocation( fn );
					fn = convertFilePathToUrl( fn );
					componentUrlText.setText( fn );
				}
			}
		});


		// Choose the output location
		l = new Label( subC, SWT.NONE );
		l.setText( "Output Location:" );
		l.setToolTipText( "The location of the component after configuration" );

		final Text outputLocationText = new Text( subC, SWT.SINGLE | SWT.BORDER );
		outputLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		outputLocationText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				ComponentConfigurationWizardPage.this.updatedFileLocation = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		browseButton = new Button( subC, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( getShell(), SWT.SINGLE | SWT.SAVE );
				dlg.setText( "Select a zipped component file" );
				dlg.setFilterNames( new String[] { "ZIP (*.zip)" });
				dlg.setFilterExtensions( new String[] { "*.zip" });

				// File name
				String name = null;
				if( ComponentConfigurationWizardPage.this.componentUrl != null ) {
					int index = Math.max(
							ComponentConfigurationWizardPage.this.componentUrl.lastIndexOf( '/' ),
							ComponentConfigurationWizardPage.this.componentUrl.lastIndexOf( '\\' ));

					if( index > 0 && index < ComponentConfigurationWizardPage.this.componentUrl.length() - 1 ) {
						name = ComponentConfigurationWizardPage.this.componentUrl.substring( index + 1 );
						name = StringUtils.insertSuffixBeforeFileExtension( name, "--patched" );
					}
				}

				// Save location
				String path = PreferencesManager.getSavedLocation();
				if( ! StringUtils.isEmpty( path )) {
					try {
						File f = new File( path );
						dlg.setFilterPath( f.getParentFile().getAbsolutePath());
						dlg.setFileName( name == null ? f.getName() : name );

					} catch( Exception e1 ) {
						// nothing
					}
				} else if( name != null ) {
					dlg.setFileName( name );
				}

				String fn = dlg.open();
				if( fn != null )
					outputLocationText.setText( fn );
			}
		});

		new Label( subC, SWT.NO_FOCUS );
		final Button overwriteButton = new Button( subC, SWT.CHECK );
		overwriteButton.setText( "Overwrite" );
		overwriteButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				ComponentConfigurationWizardPage.this.overwrite = overwriteButton.getSelection();
				validate();
			}
		});


		// Add a table to display the shared libraries to add
		subC = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginTop = 18;
		subC.setLayout( layout );
		subC.setLayoutData( new GridData( GridData.FILL_BOTH ));

		l = new Label( subC, SWT.NONE );
		l.setText( "Select or define the shared libraries this component must use." );
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		l.setLayoutData( layoutData );

		final TableViewer slViewer = new TableViewer( subC, SWT.BORDER | SWT.HIDE_SELECTION | SWT.FULL_SELECTION | SWT.SINGLE );
		slViewer.setContentProvider( new ArrayContentProvider());
		slViewer.setLabelProvider( new ITableLabelProvider() {
			@Override
			public void removeListener( ILabelProviderListener listener ) {
				// nothing
			}

			@Override
			public boolean isLabelProperty( Object element, String property ) {
				return false;
			}

			@Override
			public void dispose() {
				// nothing
			}

			@Override
			public void addListener( ILabelProviderListener listener ) {
				// nothing
			}

			@Override
			public String getColumnText( Object element, int columnIndex ) {
				Object key = ((Map.Entry<?,?>) element).getKey();
				Object value = ((Map.Entry<?,?>) element).getValue();

				String result = "";;
				if( columnIndex == 0 )
					result = (String) key;
				else if( columnIndex == 1 )
					result = (String) value;

				return result;
			}

			@Override
			public Image getColumnImage( Object element, int columnIndex ) {
				return null;
			}
		});

		slViewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		slViewer.getTable().setHeaderVisible( true );
		slViewer.getTable().setLinesVisible( true );

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData( new ColumnWeightData( 85, 300, true ));
		tlayout.addColumnData( new ColumnWeightData( 15, 50, true ));
		slViewer.getTable().setLayout( tlayout );

		TableColumn column = new TableColumn( slViewer.getTable(), SWT.LEFT );
		column.setText( NAME );
		column = new TableColumn( slViewer.getTable(), SWT.CENTER );
		column.setText( VERSION );
		slViewer.setColumnProperties( new String[] { NAME, VERSION });


		// Add some buttons
		Composite buttonsComposite = new Composite( subC, SWT.NONE );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, true ));

		Button addSlFileButton = new Button( buttonsComposite, SWT.PUSH );
		addSlFileButton.setText( "Add a File..." );
		addSlFileButton.setToolTipText( "Select a Shared Library file" );
		addSlFileButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));

		Button addSlUrlButton = new Button( buttonsComposite, SWT.PUSH );
		addSlUrlButton.setText( "Add an URL..." );
		addSlUrlButton.setToolTipText( "Add an URL pointing to a Shared Library" );
		addSlUrlButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));

		Button specifySlButton = new Button( buttonsComposite, SWT.PUSH );
		specifySlButton.setText( "Specify Manually..." );
		specifySlButton.setToolTipText( "Enter a shared library name and a shared library version manually" );
		specifySlButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));

		final Button editButton = new Button( buttonsComposite, SWT.PUSH );
		editButton.setText( "Edit..." );
		editButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));

		final Button deleteButton = new Button( buttonsComposite, SWT.PUSH );
		deleteButton.setText( "Delete" );
		deleteButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, false, false ));


		// Button listeners
		addSlFileButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				openSlFileDialog( slViewer, null );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				openSlFileDialog( slViewer, null );
			}
		});

		addSlUrlButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				openSlUrlDialog( slViewer, null );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				openSlUrlDialog( slViewer, null );
			}
		});

		specifySlButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				openSlDialog( slViewer, null );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				openSlDialog( slViewer, null );
			}
		});

		editButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				if( ! slViewer.getSelection().isEmpty()) {
					Object o = ((IStructuredSelection) slViewer.getSelection()).getFirstElement();
					if( o instanceof Map.Entry<?,?> ) {
						o = ((Map.Entry<?,?>) o).getKey();
						if( o instanceof String )
							openSlDialog( slViewer, (String) o);
					}
				}
			}
		});

		deleteButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				if( ! slViewer.getSelection().isEmpty()) {
					Object o = ((IStructuredSelection) slViewer.getSelection()).getFirstElement();
					ComponentConfigurationWizardPage.this.slNameToVersion.remove( o );
					slViewer.setInput( ComponentConfigurationWizardPage.this.slNameToVersion.entrySet());
					slViewer.refresh();
				}
			}
		});


		// Other listeners
		slViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				deleteButton.setEnabled( true );
				Object o = ((IStructuredSelection) slViewer.getSelection()).getFirstElement();
				if( o instanceof Map.Entry<?,?> ) {
					o = ((Map.Entry<?,?>) o).getKey();
					boolean disabled = o instanceof File;
					editButton.setEnabled( ! disabled );
				}
			}
		});

		setPageComplete( false );
	}


	/**
	 * @param slViewer the shared libraries viewer
	 * @param name
	 */
	private void openSlDialog( final TableViewer slViewer, String name ) {

		DuoInputDialog dlg = new DuoInputDialog( getShell());
		dlg.setName( name );
		dlg.setVersion( this.slNameToVersion.get( name ));

		if( dlg.open() == Window.OK ) {
			ComponentConfigurationWizardPage.this.slNameToVersion.put( dlg.getName(), dlg.getVersion());
			slViewer.setInput( ComponentConfigurationWizardPage.this.slNameToVersion.entrySet());
			slViewer.refresh();
			validate();
		}
	}


	/**
	 * @param slViewer the shared libraries viewer
	 * @param file
	 */
	private void openSlFileDialog( final TableViewer slViewer, File file ) {

		FileDialog dlg = new FileDialog( getShell(), SWT.SINGLE );
		dlg.setText( "Select a shared library file" );
		dlg.setFilterNames( new String[] { "ZIP (*.zip)" });
		dlg.setFilterExtensions( new String[] { "*.zip" });
		if( file != null )
			dlg.setFilterPath( file.getParentFile().getAbsolutePath());

		String path = PreferencesManager.getSavedLocation();
		if( ! StringUtils.isEmpty( path )) {
			try {
				File f = new File( path );
				dlg.setFilterPath( f.getParentFile().getAbsolutePath());
				dlg.setFileName( f.getName());

			} catch( Exception e1 ) {
				// nothing
			}
		}

		String fn = dlg.open();
		if( fn != null ) {
			PreferencesManager.setSavedLocation( fn );

			File f = new File( fn );
			String version = null;
			String name = null;
			try {
				Properties prop = ArtifactArchiveUtils.getSharedLibraryVersion( f );
				version = prop.getProperty( ArtifactArchiveUtils.SL_VERSION );
				name = prop.getProperty( ArtifactArchiveUtils.SL_NAME );

			} catch( InvalidJbiXmlException e ) {
				PetalsComponentsPlugin.log( e, IStatus.ERROR );
			}

			if( ! StringUtils.isEmpty( name )) {
				ComponentConfigurationWizardPage.this.slNameToVersion.put( name, version );
				slViewer.setInput( ComponentConfigurationWizardPage.this.slNameToVersion.entrySet());
				slViewer.refresh();
				validate();
			}
		}
	}


	/**
	 * @param slViewer the shared libraries viewer
	 * @param url
	 */
	private void openSlUrlDialog( final TableViewer slViewer, URL url ) {

		ZipUrlInputDialog dlg = new ZipUrlInputDialog( getShell(), url );
		if( dlg.open() == Window.OK
				&& ! dlg.isCancelled()
				&& ! StringUtils.isEmpty( dlg.getName())) {

			ComponentConfigurationWizardPage.this.slNameToVersion.put( dlg.getName(), dlg.getVersion());
			slViewer.setInput( ComponentConfigurationWizardPage.this.slNameToVersion.entrySet());
			slViewer.refresh();
			validate();
		}
	}


	/**
	 * Validates all the inputs.
	 */
	private void validate() {

		String msg = null;
		if( StringUtils.isEmpty( this.componentUrl ))
			msg = "You must indicate the location of a component archive.";

		else if(( msg = validateUrl( this.componentUrl )) == null ) {
			if( StringUtils.isEmpty( this.updatedFileLocation ))
				msg = "You must indicate the location of the output archive.";
			else if( new File( this.updatedFileLocation ).exists() && ! this.overwrite )
				msg = "The output file already exists.";
			else if( this.componentUrl.equals( convertFilePathToUrl( this.updatedFileLocation )))
				msg = "The output component cannot overwrite the input component.";
			else if( this.slNameToVersion.isEmpty())
				msg = "You must specify at least one shared library to use.";
		}

		setErrorMessage( msg );
		setPageComplete( msg == null );
	}


	/**
	 * Checks that an URL points to an existing resource.
	 * @param url an URL (not null)
	 * @return null if everything is fine, an error message otherwise
	 */
	private String validateUrl( String url ) {

		String msg = null;
		InputStream is = null;
		try {
			is = UriAndUrlHelper.urlToUri( this.componentUrl ).toURL().openStream();

		} catch( MalformedURLException e ) {
			msg = e.getMessage();

		} catch( IOException e ) {
			msg = e.getMessage();

		} finally {
			if( is != null ) {
				try {
					is.close();

				} catch( IOException e ) {
					PetalsComponentsPlugin.log( e, IStatus.WARNING );
				}
			}
		}

		return msg;
	}


	/**
	 * @return the componentUrl
	 */
	public String getComponentUrl() {
		return this.componentUrl;
	}


	/**
	 * @return the updatedFileLocation
	 */
	public String getOutputLocation() {
		return this.updatedFileLocation;
	}


	/**
	 * @return the slNameToVersion
	 */
	public Map<String,String> getSlNameToVersion() {
		return this.slNameToVersion;
	}


	/**
	 * Builds an URL from a string and escapes illegal characters.
	 * @param path the file path or file URL
	 * @return an URL
	 */
	public static String convertFilePathToUrl( String path ) {

		URL url;
		try {
			url = new URL( path );
		} catch( MalformedURLException e1 ) {
			try {
				URI uri = new File( path ).toURI();
				uri.normalize();
				url = uri.toURL();

			} catch( Exception e ) {
				throw new IllegalArgumentException( "Broken URL: " + path );
			}
		}

		return url.toString();
	}
}
