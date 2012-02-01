/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.pojo.wizards;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.ebmwebsourcing.petals.services.PetalsImages;
import com.ebmwebsourcing.petals.services.pojo.Messages;
import com.ebmwebsourcing.petals.services.pojo.PetalsPojoPlugin;
import com.ebmwebsourcing.petals.services.pojo.pojo.PojoPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class PojoProvideSpecificPage2x extends AbstractSuWizardPage {

	private final class SelectClassDialog extends SelectionDialog {

		private SelectClassDialog(Shell parentShell) {
			super(parentShell);
		}

		@Override
		public Control createDialogArea(Composite parent) {
			FilteredTree filterdTree = new FilteredTree(parent, SWT.SINGLE | SWT.BORDER, new PatternFilter(), true);
			filterdTree.getViewer().setLabelProvider(new LabelProvider());
			filterdTree.getViewer().setContentProvider(new ArrayTreeContentProvider());
			filterdTree.getViewer().setInput(getAvailableClasses());
			filterdTree.getViewer().setSelection(new StructuredSelection(getInitialElementSelections()));
			filterdTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					if (event.getSelection().isEmpty()) {
						setSelectionResult(new Object[0]);
					} else {
						String res = (String) ((IStructuredSelection)event.getSelection()).getFirstElement();
						setSelectionResult(new Object[] { res });
					}
				}
			});
			return filterdTree;
		}
	}

	/** The text box for the class name. */
	private DataBindingContext dbc;

	private boolean useExistingImplementation = false;

	private List jarList;
	private String[] jars = new String[0];

	private Button createJavaProjectButton;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {
//
//		boolean valid = true;
//		if( this.useExistingImplementation ) {
//			if( this.jarSelection.getFilePaths( false ).length == 0 ) {
//				updateStatus( "You have to provide at least one *.jar file." );
//				valid = false;
//			}
//			else {
//				String error = this.classSelection.validate();
//				updateStatus( error );
//				valid = error == null;
//			}
//		}
//		else {
//			updateStatus( null );
//		}
//
//		return valid;
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		dbc = new DataBindingContext();
		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setDescription( "Select the Java resources of the POJO." );

		GridLayout layout = new GridLayout (3, false);
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		createJavaProjectButton = new Button( container, SWT.RADIO );
		createJavaProjectButton.setText( "Create a Java project and a POJO class." );
		createJavaProjectButton.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 3, 1));
		createJavaProjectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(isPageComplete());
			}
		});
		
		// Add check button
		final Button useExistingImplemButton = new Button( container, SWT.RADIO );
		useExistingImplemButton.setText( "Use an existing POJO implementation." );
		useExistingImplemButton.setSelection( this.useExistingImplementation );
		useExistingImplemButton.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 3, 1));

		// Add controls.
		Label classpathLabel = new Label(container, SWT.NONE);
		classpathLabel.setText(Messages.classpath);
		jarList = new List(container, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL);
		jarList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		Button addLibButton = new Button(container, SWT.PUSH);
		addLibButton.setText(Messages.addLib);
		addLibButton.setImage(PetalsImages.getAdd());
		addLibButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false));
		new Label(container, SWT.NONE); // filler
		final Button removeLibButton = new Button(container, SWT.PUSH);
		removeLibButton.setText(Messages.removeLib);
		removeLibButton.setImage(PetalsImages.getDelete());
		removeLibButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
		ISWTObservableValue reUseProjectObservable = SWTObservables.observeSelection(useExistingImplemButton);
		{	
			Label clazzLabel = new Label(container, SWT.NONE);
			clazzLabel.setText(Messages.className);
			Text clazzText = new Text(container, SWT.BORDER);
			clazzText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			Button selectClassButton = new Button(container, SWT.PUSH);
			selectClassButton.setText(Messages.select);
			selectClassButton.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, false, false));
			dbc.bindValue(SWTObservables.observeText(clazzText, SWT.Modify),
					EMFObservables.observeValue(getNewlyCreatedEndpoint(), PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME));
			
			dbc.bindValue(reUseProjectObservable, SWTObservables.observeEnabled(clazzLabel));
			dbc.bindValue(reUseProjectObservable, SWTObservables.observeEnabled(clazzText));
			dbc.bindValue(reUseProjectObservable, SWTObservables.observeEnabled(selectClassButton));
			
			
			selectClassButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					SelectClassDialog selectionDialog = new SelectClassDialog(getShell());
					selectionDialog.setInitialSelections(new Object[] { getNewlyCreatedEndpoint().eGet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME) });
					if (selectionDialog.open() == Dialog.OK && selectionDialog.getResult().length > 0) {
						getNewlyCreatedEndpoint().eSet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME, selectionDialog.getResult()[0]);
					}
				}
			});
			clazzText.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					setPageComplete(isPageComplete());
				}
			});
		}
		
		// Listeners
		dbc.bindValue(reUseProjectObservable,
				PojoObservables.observeValue(getWizard(), "useExistingImplementation"));
		
		dbc.bindValue(SWTObservables.observeEnabled(classpathLabel), reUseProjectObservable);
		dbc.bindValue(SWTObservables.observeEnabled(jarList),reUseProjectObservable);
		dbc.bindValue(SWTObservables.observeEnabled(addLibButton), reUseProjectObservable);
		Listener updateRemoveButtonListener = new Listener() {
			public void handleEvent(Event e) {
				removeLibButton.setEnabled(
					jarList.isEnabled() &&
					jarList.getSelection().length > 0);
			}
		};
		jarList.addListener(SWT.Deactivate, updateRemoveButtonListener);
		jarList.addListener(SWT.Activate, updateRemoveButtonListener);
		jarList.addListener(SWT.Selection, updateRemoveButtonListener);
		jarList.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeLibButton.setEnabled(
						jarList.isEnabled() &&
						jarList.getSelection().length > 0);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				removeLibButton.setEnabled(
						jarList.isEnabled() &&
						jarList.getSelection().length > 0);
			}
		});
		useExistingImplemButton.addListener(SWT.Selection, updateRemoveButtonListener);
		removeLibButton.addListener(SWT.Selection, updateRemoveButtonListener);
		
		addLibButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.MULTI | SWT.OPEN);
				fd.setFilterPath(System.getProperty("user.home"));
				fd.setFilterExtensions(new String[] { "*.jar" });
				if (fd.open() != null) {
					for (String name : fd.getFileNames()) {
						jarList.add(fd.getFilterPath() + File.separator + name);
					}
				}
				jars = jarList.getItems();
			}
		});
		removeLibButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				jarList.remove(jarList.getSelectionIndices());
				jars = jarList.getItems();
			}
		});
		
		createJavaProjectButton.setSelection(true);
		useExistingImplemButton.setSelection(false);
		removeLibButton.setEnabled(false);
		setControl( container );
	}
	
	public String[] getJarPath() {
		return jars;
	}
	
	public Collection<String> getAvailableClasses() {
		SortedSet<String> classes = new TreeSet<String>();
		for (String jarPath : getJarPath()) {
			try {
				JarFile jar = new JarFile(jarPath);
				Enumeration<JarEntry> jarEntries = jar.entries();
				while (jarEntries.hasMoreElements()) {
					JarEntry entry = jarEntries.nextElement();
					if (entry.getName().endsWith(".class")) {
						classes.add(entry.getName()
								.replace("/", ".")
								.substring(0, entry.getName().length() - ".class".length()));
					}
				}
			} catch (IOException ex) {
				PetalsPojoPlugin.log(ex, IStatus.ERROR);
			}
		}
		return classes;
	}

	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}
	
	@Override
	public boolean isPageComplete() {
		if (createJavaProjectButton.getSelection()) {
			return true;
		}
		Object className = getNewlyCreatedEndpoint().eGet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME);
		return className != null && ! ((String)className).trim().isEmpty(); 
	}
}
