/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xml.core.internal.provisional.contenttype.ContentTypeIdForXML;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.LinkWithImageComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.ListWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.QNameText;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;

/**
 * A set of utilities to create SWT and JFace widgets.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SwtFactory {

	/**
	 * Creates a new composite.
	 * @param container the parent
	 * @return a new composite
	 */
	public static Composite createComposite( Composite container ) {
		return new Composite( container, SWT.NONE );
	}


	/**
	 * Creates a label.
	 *
	 * @param container the parent
	 * @param label the label
	 * @param tooltip the tool tip
	 * @return the created label
	 */
	public static Label createLabel( Composite container, String label, String tooltip ) {

		Label l = new Label( container, SWT.NONE );
		l.setText( label );
		l.setToolTipText( tooltip );

		return l;
	}


	/**
	 * Creates a simple text field.
	 *
	 * @param container the parent
	 * @param useWholeSpace true to use the whole space (horizontally)
	 * @return the created text field
	 */
	public static Text createSimpleTextField( Composite container, boolean useWholeSpace ) {

		Text t = new Text( container, SWT.SINGLE | SWT.BORDER );
		if( useWholeSpace )
			t.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		return t;
	}


	/**
	 * Creates a combo with the drop_down option.
	 *
	 * @param container the parent
	 * @param readOnly true to make the combo read only, false if it can be edited manually
	 * @param useWholeSpace true to use the whole space (horizontally)
	 * @return the created combo
	 */
	public static Combo createDropDownCombo( Composite container, boolean readOnly, boolean useWholeSpace ) {

		int style = SWT.DROP_DOWN | SWT.BORDER;
		if( readOnly )
			style |= SWT.READ_ONLY;

		Combo c = new Combo( container, style );
		if( useWholeSpace )
			c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		return c;
	}


	/**
	 * Creates a password field with a button to hide or show the password in clear.
	 * @param container the container
	 * @param useWholeSpace true to use the whole space
	 * @param showPwd true to show the password in clear at the beginning
	 * @return a composite which contains both the text and the button
	 */
	public static TextWithButtonComposite createPasswordField( Composite container, boolean showPwd ) {

		final TextWithButtonComposite twb = new TextWithButtonComposite( container, SWT.SINGLE | SWT.BORDER );
		twb.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		twb.getText().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final char defEchoChar = twb.getText().getEchoChar();
		twb.getButton().addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				char echoChar = twb.getText().getEchoChar();
				if( echoChar == defEchoChar ) {
					twb.getButton().setText( "Show Password" );
					twb.getText().setEchoChar( '\u2022' );
				} else {
					twb.getButton().setText( "Hide Password" );
					twb.getText().setEchoChar( defEchoChar );
				}

				twb.layout();
			}
		});

		if( showPwd )
			twb.getText().setEchoChar( 'a' );

		twb.getButton().notifyListeners( SWT.Selection, new Event());
		return twb;
	}


	/**
	 * Creates a combo viewer with a default label provider and an array content provider.
	 * @param parent the parent
	 * @param useWholeSpace true to use the whole space horizontally
	 * @param values the values to put in the viewer
	 * @return the viewer
	 */
	public static ComboViewer createDefaultComboViewer( Composite parent, boolean useWholeSpace, boolean readOnly, Object[] values ) {

		int style = SWT.SINGLE | SWT.BORDER;
		if( readOnly )
			style |= SWT.READ_ONLY;

		final ComboViewer viewer = new ComboViewer( parent, style );
		if( useWholeSpace )
			viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		viewer.setContentProvider( new ArrayContentProvider());
		viewer.setLabelProvider( new LabelProvider());
		viewer.setInput( values );

		return viewer;
	}


	/**
	 * Creates a styled text with syntax highlighting for XML document.
	 * <p>
	 * Be careful, the layout data must be applied on the parent of the result.
	 * </p>
	 * <code>
	 * StyledText st = SwtFactory.createXmlTextViewer( parent );<br />
	 * st.getParent().setLayoutData( new GridData());
	 * </code>
	 *
	 * @param parent
	 * @return
	 */
	public static StyledText createXmlTextViewer( Composite parent ) {
		return createXmlTextViewer( parent, true );
	}


	/**
	 * Creates a styled text with syntax highlighting for XML document.
	 * <p>
	 * Be careful, the layout data must be applied on the parent of the result.
	 * </p>
	 * <code>
	 * StyledText st = SwtFactory.createXmlTextViewer( parent );<br />
	 * st.getParent().setLayoutData( new GridData());
	 * </code>
	 *
	 * @param parent
	 * @param horizontalScroll
	 * @return
	 */
	@SuppressWarnings( "restriction" )
	public static StyledText createXmlTextViewer( Composite parent, boolean showHorizontalScroll ) {

		Composite editor = new Composite( parent, SWT.NONE );
		editor.setLayout( new FillLayout ());

		SourceViewerConfiguration sourceViewerConfiguration = new StructuredTextViewerConfiguration() {
			StructuredTextViewerConfiguration baseConfiguration = new StructuredTextViewerConfigurationXML();

			@Override
			public String[] getConfiguredContentTypes( ISourceViewer sourceViewer ) {
				return this.baseConfiguration.getConfiguredContentTypes( sourceViewer );
			}

			@Override
			public LineStyleProvider[] getLineStyleProviders( ISourceViewer sourceViewer, String partitionType ) {
				return this.baseConfiguration.getLineStyleProviders( sourceViewer, partitionType );
			}
		};

		SourceViewer viewer = null;
		String contentTypeID = ContentTypeIdForXML.ContentTypeID_XML;
		int style = SWT.BORDER | SWT.V_SCROLL;
		style |= showHorizontalScroll ? SWT.V_SCROLL : SWT.WRAP;

		viewer = new StructuredTextViewer( editor, null, null, false, style );
		((StructuredTextViewer) viewer).getTextWidget().setFont( JFaceResources.getFont( "org.eclipse.wst.sse.ui.textfont" )); //$NON-NLS-1$
		IStructuredModel scratchModel = StructuredModelManager.getModelManager().createUnManagedStructuredModelFor( contentTypeID );
		IDocument document = scratchModel.getStructuredDocument();
		viewer.configure( sourceViewerConfiguration );
		viewer.setDocument( document );

		return viewer.getTextWidget();
	}


	/**
	 * Creates a hyper link.
	 *
	 * @param container the parent
	 * @param text the text to show in the hyper link
	 * @param enabled true if it should be enabled, false otherwise
	 * @param useWholeSpace true to use the whole space (horizontally)
	 * @return the created link
	 * @see #createDecoredLink(Composite, String, Image, boolean)
	 */
	public static Link createLink( Composite container, String text, boolean enabled, boolean useWholeSpace ) {

		Link link = new Link( container, SWT.NONE );
		link.setText( text );
		link.setEnabled( enabled );
		if( useWholeSpace )
			link.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		return link;
	}


	/**
	 * Creates a composite which contains both a label with an image and a hyper link.
	 *
	 * @param parent the parent
	 * @param text the text to show in the hyper link
	 * @param image the image to set
	 * @return the created link
	 * @see #createLink(Composite, String, boolean)
	 */
	public static LinkWithImageComposite createDecoredLink( Composite parent, String text, Image image ) {

		LinkWithImageComposite decoredLink = new LinkWithImageComposite( parent );
		decoredLink.getLabel().setImage( image );
		decoredLink.getLink().setText( text );

		return decoredLink;
	}


	/**
	 * Creates a QName text field.
	 *
	 * @param container the parent
	 * @param useWholeSpace true to use the whole space (horizontally)
	 * @return the created QName text field
	 */
	public static QNameText createQNameTextField( Composite container, boolean useWholeSpace ) {

		QNameText t = new QNameText( container );
		if( useWholeSpace )
			t.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		return t;
	}


	/**
	 * Creates a QName text field.
	 *
	 * @param container the parent
	 * @param useWholeSpace true to use the whole space (horizontally)
	 * @param defaultLocalPart the local part to show when there is no value
	 * @param defaultNamespace the name space to show when there is no value
	 * @return the created QName text field
	 */
	public static QNameText createQNameTextField( Composite container, boolean useWholeSpace, String defaultLocalPart, String defaultNamespace ) {

		QNameText t = new QNameText( container, defaultLocalPart, defaultNamespace );
		if( useWholeSpace )
			t.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		return t;
	}


	/**
	 * Creates a push button.
	 *
	 * @param container the parent
	 * @param label the label
	 * @param tooltip the tool tip
	 * @return the created button
	 */
	public static Button createPushButton( Composite container, String label, String tooltip ) {

		Button b = new Button( container, SWT.PUSH );
		b.setText( label );
		b.setToolTipText( tooltip );

		return b;
	}


	/**
	 * Creates a check box button.
	 *
	 * @param container the parent
	 * @param label the label
	 * @param tooltip the tool tip
	 * @param selected true to select the check box
	 * @return the created button
	 */
	public static Button createCheckBoxButton( Composite container, String label, String tooltip, boolean selected ) {

		Button b = new Button( container, SWT.CHECK );
		b.setText( label );
		b.setToolTipText( tooltip );
		b.setSelection( selected );

		return b;
	}


	/**
	 * Creates a file browser, with a text and a button to open a file dialog.
	 * <p>
	 * This is a convenience method to create a file browser from a file type.<br />
	 * <code>
	 * createFileBrowser( parent, true, "WSDL" );
	 * </code>
	 * is equivalent to
	 * <code>
	 * createFileBrowser( parent, true, new String[]{ WSDL Files (*.wsdl)}, new String[] { "*.wsdl" });
	 * </code>
	 * </p>
	 *
	 * @param parent the parent
	 * @param selectSeveralFiles true to select several files, false for a single one
	 * @param fileAsUri true to write the file path as an URI, false to write the file path
	 * @param fileType the file type (file extension = file type in lower case)
	 * @return the created text
	 * @see #createFileBrowser(Composite, boolean, boolean, String[], String[])
	 */
	public static TextWithButtonComposite createFileBrowser( Composite parent, boolean selectSeveralFiles, boolean fileAsUri, String fileType ) {

		String ext = "*" + fileType.toLowerCase();
		String[] filterNames = new String[] { fileType + " Files (" + ext + ")" };
		return createFileBrowser( parent, selectSeveralFiles, fileAsUri, filterNames, new String[]{ ext });
	}


	/**
	 * Creates a file browser, with a text and a button to open a file dialog.
	 *
	 * @param parent the parent
	 * @param selectSeveralFiles true to select several files, false for a single one
	 * @param fileAsUri true to write the file path as an URI, false to write the file path
	 * @param filterNames the filter names
	 * @param fileExtensions the file extensions
	 * @return the created composite, with a text containing the file selection
	 * @see #createFileBrowser(Composite, boolean, boolean, String)
	 */
	public static TextWithButtonComposite createFileBrowser(
			final Composite parent,
			final boolean selectSeveralFiles,
			final boolean fileAsUri,
			final String[] filterNames,
			final String[] fileExtensions ) {

		final TextWithButtonComposite browser = new TextWithButtonComposite( parent );
		browser.getText().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		browser.getButton().setText( "Browse..." );
		browser.getButton().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dlg = new FileDialog( parent.getShell(), selectSeveralFiles ? SWT.MULTI : SWT.SINGLE );
				dlg.setText( "File Selection" );
				dlg.setFilterNames( filterNames );
				dlg.setFilterExtensions( fileExtensions );

				String path = PreferencesManager.getSavedLocation();
				if( ! StringUtils.isEmpty( path ))
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn != null ) {
					path = dlg.getFilterPath();
					PreferencesManager.setSavedLocation( path );

					File f = new File( fn );
					browser.getText().setText( fileAsUri ? f.toURI().toString() : f.getAbsolutePath());
					browser.getText().setSelection( browser.getText().getText().length());
				}
			}
		});

		return browser;
	}


	/**
	 * Creates a directory browser, with a text and a button to open a directory dialog.
	 * @param parent the parent
	 * @return the created composite, with a text containing the file selection
	 */
	public static TextWithButtonComposite createDirectoryBrowser( final Composite parent ) {

		final TextWithButtonComposite browser = new TextWithButtonComposite( parent );
		browser.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		browser.getText().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		browser.getButton().setText( "Browse..." );
		browser.getButton().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				DirectoryDialog dlg = new DirectoryDialog( parent.getShell(), SWT.NONE );
				dlg.setText( "Select a Directory" );

				String path = PreferencesManager.getSavedLocation();
				if( ! StringUtils.isEmpty( path ))
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn != null ) {
					browser.getText().setText( fn );
					browser.getText().setSelection( browser.getText().getText().length());
				}
			}
		});

		return browser;
	}


	/**
	 * Creates a file list with a browser to open a file dialog.
	 * <p>
	 * This is a convenience method to create a file list browser from a file type.<br />
	 * <code>
	 * createFileListViewer( parent, "WSDL" );
	 * </code>
	 * is equivalent to
	 * <code>
	 * createFileListViewer( parent, new String[]{ WSDL Files (*.wsdl)}, new String[] { "*.wsdl" });
	 * </code>
	 * </p>
	 *
	 * @param parent the parent
	 * @param fileType the file type (file extension = file type in lower case)
	 * @param files a non-null collection of files (can be empty)
	 * @return the tree viewer that displays the selected files
	 */
	public static ListWithButtonComposite createFileListViewer(
			final Composite parent,
			String fileType,
			final Collection<File> files  ) {

		String ext = "*" + fileType.toLowerCase();
		String[] filterNames = new String[] { fileType + " Files (" + ext + ")" };
		return createFileListViewer( parent, filterNames, new String[]{ ext }, files );
	}


	/**
	 * Creates a file list with a browser to open a file dialog.
	 * <p>
	 * When a file is added or removed, a selection listener is invoked.
	 * </p>
	 *
	 * @param parent the parent
	 * @param filterNames the filter names
	 * @param fileExtensions the file extensions
	 * @param files a non-null collection of files (can be empty)
	 * @return the tree viewer that displays the selected files
	 */
	public static ListWithButtonComposite createFileListViewer(
			final Composite parent,
			final String[] filterNames,
			final String[] fileExtensions,
			final Collection<File> files ) {

		final ListWithButtonComposite lbc = new ListWithButtonComposite( parent );
		lbc.setLayoutData( new GridData( GridData.FILL_BOTH ));
		final TableViewer viewer = lbc.getViewer();

		viewer.getTable().setLayoutData( new GridData( GridData.FILL_BOTH ));
		viewer.setContentProvider( new ArrayContentProvider ());
		viewer.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				if( element instanceof File )
					return ((File) element).getAbsolutePath();
				return super.getText( element );
			}
		});


		// ADD button
		lbc.getAddButton().setText( "Add" );
		lbc.getAddButton().setImage( PetalsImages.getAdd());
		lbc.getAddButton().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				FileDialog dlg = new FileDialog( parent.getShell(), SWT.MULTI );
				dlg.setText( "Select one or several files" );
				dlg.setFilterNames( filterNames );
				dlg.setFilterExtensions( fileExtensions );

				String path = PreferencesManager.getSavedLocation();
				if( path.trim().length() > 0 )
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn == null )
					return;

				// Process the files
				path = dlg.getFilterPath();
				PreferencesManager.setSavedLocation( path );

				File parent = new File( path );
				for( String filename : dlg.getFileNames()) {
					File chosenFile = new File( parent, filename );
					files.add( chosenFile );
				}

				viewer.setInput( files );
				viewer.refresh();
				lbc.notifyListeners();
			}
		});


		// REMOVE button
		lbc.getRemoveButton().setText( "Remove" );
		lbc.getRemoveButton().setImage( PetalsImages.getDelete());
		lbc.getRemoveButton().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Iterator<?> it = ((IStructuredSelection) viewer.getSelection()).iterator();
				while( it.hasNext()) {
					File f = (File) it.next();
					files.remove( f );
				}

				viewer.setInput( files );
				viewer.refresh();
				lbc.notifyListeners();
			}
		});

		return lbc;
	}


	/**
	 * Creates a dialog to select a file in the given container.
	 *
	 * @param parent the shell
	 * @param container the parent container (can be the work space root)
	 * @param title the dialog's title
	 * @param desription the dialog's description
	 * @return a selection dialog
	 * @see #createWorkspaceFileSelectionDialog(Shell, IContainer, String, String, String)
	 */
	public static ElementTreeSelectionDialog createWorkspaceFileSelectionDialog(
			Shell parent,
			IContainer container,
			String title,
			String desription ) {

		ElementTreeSelectionDialog dlg = new ElementTreeSelectionDialog(
					parent,
					new WorkbenchLabelProvider(),
					new WorkbenchContentProvider());

		dlg.setInput( container );
		dlg.setTitle( title );
		dlg.setMessage( desription );
		return dlg;
	}


	/**
	 * Creates a dialog to select a file with a given extension in a given container.
	 * <p>
	 * If you want to select any file in the container, the best solution is to
	 * directly use {@link ElementTreeSelectionDialog} with a {@link WorkbenchContentProvider}
	 * and a {@link WorkbenchLabelProvider}. With respect to this solution, this method
	 * adds filtering capabilities.
	 * </p>
	 *
	 * @param parent the shell
	 * @param container the parent container (can be the work space root)
	 * @param title the dialog's title
	 * @param desription the dialog's description
	 * @param fileExtension the file extension (without the dot)
	 * @return a selection dialog
	 * @see #createWorkspaceFileSelectionDialog(Shell, IContainer, String, String)
	 */
	public static ElementTreeSelectionDialog createWorkspaceFileSelectionDialog(
			Shell parent,
			IContainer container,
			String title,
			String desription,
			final String fileExtension ) {

		ElementTreeSelectionDialog dlg = new ElementTreeSelectionDialog(
					parent,
					new WorkbenchLabelProvider(),
					new WorkbenchContentProvider() {

						@Override
						public Object[] getChildren( Object element ) {

							Object[] result = super.getChildren( element );
							if( result == null )
								result = new Object[ 0 ];

							List<Object> filteredResult = new ArrayList<Object>();
							for( Object o : result ) {
								if( o instanceof IFile
											&& fileExtension.equalsIgnoreCase(((IFile) o).getFileExtension()))
									filteredResult.add( o );

								else if( o instanceof IContainer
											&& ! ResourceUtils.getFiles( fileExtension, Arrays.asList((IContainer) o)).isEmpty())
									filteredResult.add( o );
							}

							return filteredResult.toArray();
						}
					});

		dlg.setInput( container );
		dlg.setTitle( title );
		dlg.setMessage( desription );
		return dlg;
	}


	/**
	 * Creates a list dialog with a workbench label provider and an array content provider.
	 * @param shell the parent shell
	 * @param title the title
	 * @param message the message
	 * @return the created list dialog
	 */
	public static ListDialog createListDialog( Shell shell, String title, String message ) {

		ListDialog dlg = new ListDialog( shell );
		dlg.setAddCancelButton( true );
		dlg.setContentProvider( new ArrayContentProvider());
		dlg.setLabelProvider( new WorkbenchLabelProvider());
		dlg.setTitle( title );
		dlg.setMessage( message );

		return dlg;
	}


	/**
	 * Creates grid layout.
	 *
	 * @param columns the number of columns
	 * @param sameWidth true to have make the columns have the same width
	 * @return a new grid layout
	 * @see GridLayout#GridLayout(int, boolean)
	 */
	public static GridLayout createGridLayout( int columns, boolean sameWidth ) {
		return new GridLayout( columns, sameWidth );
	}


	/**
	 * Creates grid layout with predefined margins.
	 * <p>
	 * Note that width and height margins are both set to 0.
	 * </p>
	 *
	 * @param columns the number of columns
	 * @param sameWidth true to have make the columns have the same width
	 * @param mTop the top margin
	 * @param mRight the right margin
	 * @param mBottom the bottom margin
	 * @param mLeft the left margin
	 * @return a new grid layout
	 * @see GridLayout#GridLayout(int, boolean)
	 */
	public static GridLayout createGridLayout( int columns, boolean sameWidth, int mTop, int mRight, int mBottom, int mLeft ) {

		GridLayout layout = new GridLayout( columns, sameWidth );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginBottom = mBottom;
		layout.marginLeft = mLeft;
		layout.marginTop = mTop;
		layout.marginRight = mRight;

		return layout;
	}


	/**
	 * Creates and applies a grid layout to a specific composite.
	 *
	 * @param composite the composite
	 * @param columns the number of columns
	 * @param sameWidth true to have make the columns have the same width
	 * @param mTop the top margin
	 * @param mRight the right margin
	 * @param mBottom the bottom margin
	 * @param mLeft the left margin
	 *
	 * @return the updated composite
	 * @see #createGridLayout(int, boolean, int, int, int, int)
	 */
	public static Composite applyNewGridLayout( Composite composite, int columns, boolean sameWidth ) {
		composite.setLayout( createGridLayout( columns, sameWidth ));
		return composite;
	}


	/**
	 * Creates and applies a grid layout to a specific composite.
	 *
	 * @param composite the composite
	 * @param columns the number of columns
	 * @param sameWidth true to have make the columns have the same width
	 * @return the updated composite
	 * @see GridLayout#GridLayout(int, boolean)
	 */
	public static Composite applyNewGridLayout( Composite composite, int columns, boolean sameWidth, int mTop, int mRight, int mBottom, int mLeft ) {
		composite.setLayout( createGridLayout( columns, sameWidth, mTop, mRight, mBottom, mLeft ));
		return composite;
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 */
	public static void applyHorizontalGridData( Control control ) {
		control.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 * @param hSpan the horizontal span
	 */
	public static void applyHorizontalGridData( Control control, int hSpan ) {

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = hSpan;
		control.setLayoutData( layoutData );
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 * @param hSpan the horizontal span
	 * @param vIndent the vertical indent
	 */
	public static void applyHorizontalGridData( Control control, int hSpan, int vIndent ) {

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = hSpan;
		layoutData.verticalIndent = vIndent;
		control.setLayoutData( layoutData );
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 * @param hSpan the horizontal span
	 * @param vIndent the vertical indent
	 */
	public static void applyGridData( Control control, int hSpan, int vIndent ) {

		GridData layoutData = new GridData();
		layoutData.horizontalSpan = hSpan;
		layoutData.verticalIndent = vIndent;
		control.setLayoutData( layoutData );
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally and vertically.
	 * </p>
	 * @param control the control to update
	 */
	public static void applyGrabbingGridData( Control control ) {
		control.setLayoutData( new GridData( GridData.FILL_BOTH ));
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 * @param hSpan the horizontal span
	 */
	public static void applyGrabbingGridData( Control control, int hSpan ) {

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = hSpan;
		control.setLayoutData( layoutData );
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 * <p>
	 * The control fills the space horizontally.
	 * </p>
	 * @param control the control to update
	 * @param hSpan the horizontal span
	 * @param vIndent the vertical indent
	 */
	public static void applyGrabbingGridData( Control control, int hSpan, int vIndent ) {

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = hSpan;
		layoutData.verticalIndent = vIndent;
		control.setLayoutData( layoutData );
	}


	/**
	 * Creates and applies a grid data to a specific control.
	 *
	 * @param control the control to update
	 * @param hAlign the horizontal alignment
	 * @param vAlign the vertical alignment
	 * @param grabHSpace true to grab extra horizontal space
	 * @param grabVSpace true to grab extra vertical space
	 * @see GridData#GridData(int, int, boolean, boolean)
	 */
	public static void applyNewGridData( Control control, int hAlign, int vAlign, boolean grabHSpace, boolean grabVSpace ) {
		control.setLayoutData( new GridData( hAlign, vAlign, grabHSpace, grabVSpace ));
	}
}
