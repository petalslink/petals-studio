/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation
 *******************************************************************************/


package org.eclipse.bpel.ui.wizards;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.Templates.Template;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com) 
 *
 */

public class NewFileWizardPage1 extends WizardPage 
{

	static final String EMPTY = ""; //$NON-NLS-1$
	
	/** last namespace used in creating a project, saved in dialog settings */
	static final String LAST_NAMESPACE_KEY = "last.namespace.used"; //$NON-NLS-1$

	/** Process name field */
    private Text  processNameField;

    /** which namespace to use to create the process */
    Combo processNamespaceField;

    /** which template to use to create a process */
    Combo processTemplateField;
    
    /** Template description, in summary */
    Text templateDescription;

    /** option for creating an abstract process */
    Button processAbstractOptionButton;
    
    private Map<String,Object> mArgs = new HashMap<String,Object> (3);
    
     
    
    private Listener validateListner = new Listener() {       
		public void handleEvent(Event event) {
			setPageComplete(validatePage());			
		}
    };
    
    
    // constants
    private static final int SIZING_TEXT_FIELD_WIDTH = 250;

    /**
     * Creates a new project creation wizard page.
     *
     * @param pageName the name of this page
     */
    public NewFileWizardPage1(String pageName) 
    {
        super(pageName);
        setPageComplete(false);
        
		setTitle(Messages.NewFileWizardPage1_2);
		setDescription(Messages.NewFileWizardPage1_3);
		
        setImageDescriptor( BPELUIPlugin.getDefault().getImageDescriptor( IBPELUIConstants.ICON_WIZARD_BANNER ));
    }
    
    /** 
     * Method declared on IDialogPage.
     * @param parent the parent composite that we must attach ourselves to
     */
    
    public void createControl (Composite parent) 
    {    	
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setFont(parent.getFont());

        initializeDialogUnits(parent);

        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

        createProjectGroup(composite);
        
        setPageComplete( validatePage() );
       
        // no errors on opening up the wizard
        setErrorMessage(null);
        setMessage(null);
        setControl(composite);      
        
        // figure out the what needs to go 
    }
    
    /**
     * Creates the project name specification controls.
     *
     * @param parent the parent composite
     */
    private final void createProjectGroup(Composite parent) 
    {                
        // project specification group
    	IDialogSettings settings = getWizard().getDialogSettings();
    	
        Group projectGroup = new Group(parent, SWT.NONE);
        projectGroup.setText(Messages.NewFileWizardPage1_4);
        projectGroup.setLayout(new GridLayout());
        projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        
        Composite fields = new Composite ( projectGroup, SWT.NONE );
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        fields.setLayout(layout);
        fields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // new project label
        Label projectLabel = new Label(fields, SWT.NONE);
        projectLabel.setText( Messages.NewFileWizardPage1_5); 
        projectLabel.setFont( parent.getFont() );

        // new project name entry field
        processNameField = new Text(fields, SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        processNameField.setLayoutData(data);
        processNameField.setFont(parent.getFont());

        processNameField.addListener(SWT.Modify, validateListner);

        // new project label
        Label namespaceLabel = new Label(fields, SWT.NONE);
        namespaceLabel.setText( Messages.NewFileWizardPage1_6);
        namespaceLabel.setFont(parent.getFont());
        
        // new project name entry field
        processNamespaceField = new Combo( fields, SWT.DROP_DOWN | SWT.SIMPLE );        
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        processNamespaceField.setLayoutData(data);
        processNamespaceField.setFont(parent.getFont());
        String lastNamespace = settings.get( LAST_NAMESPACE_KEY );
        if (lastNamespace != null) {
        	processNamespaceField.setText( lastNamespace );
        }
        	
        // add the namespace values
        processNamespaceField.setItems( BPELUIPlugin.getDefault().getTemplates().getNamespaceNames() );
        processNamespaceField.addListener(SWT.Modify, validateListner);
        

        // new project type
        Label typeLabel = new Label(fields, SWT.NONE);
        typeLabel.setText( Messages.NewFileWizardPage1_7); 
        typeLabel.setFont( parent.getFont() );

        // new project type selector
        processTemplateField = new Combo( fields, SWT.DROP_DOWN | SWT.SIMPLE | SWT.READ_ONLY );
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        processTemplateField.setLayoutData( data );
        
        processTemplateField.addListener(SWT.Modify, new Listener () {

			public void handleEvent(Event event) {
				String val = processTemplateField.getText().trim();
				Template template = BPELUIPlugin.getDefault().getTemplates().getTemplateByName( val );
				if (template != null) {
					String txt = template.getDescription();
					templateDescription.setText ( txt == null ? EMPTY : txt);
				}
				
			}        	
        });
        
        
        
        templateDescription = new Text(projectGroup,SWT.READ_ONLY | SWT.WRAP | SWT.SCROLL_LINE );
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        data.heightHint = 60;
        templateDescription.setLayoutData(data);
        templateDescription.setFont(parent.getFont());
        
        // Scan directories in the "templates" folder
        // and built up a list
        
        String templates[] = BPELUIPlugin.getDefault().getTemplates().getTemplateNames();
        processTemplateField.setItems(templates);
     	
     	// Select the top one.
     	if (templates.length > 0) {
     		processTemplateField.select(0);
     	}
     	
     	//add checkbox for abstract process option
     	processAbstractOptionButton = new Button(projectGroup, SWT.CHECK );
     	processAbstractOptionButton.setText(Messages.NewFileWizardPage1_9); 
     	processAbstractOptionButton.setFont(parent.getFont() );
     	processAbstractOptionButton.addSelectionListener(new SelectionAdapter(){
     		public void widgetSelected(SelectionEvent event) {
    			setPageComplete(validatePage());			
    		}
     	});     	
    }
    

   

    /**
     * Returns the current project name as entered by the user, or its anticipated
     * initial value.
     *
     * @return the project name, its anticipated initial value, or <code>null</code>
     *   if no project name is known
     */
    public String getProjectName() {
        return getProjectNameFieldValue();
    }
    
    /**
     * Returns the value of the project name field
     * with leading and trailing spaces removed.
     * 
     * @return the project name in the field
     */
    private String getProjectNameFieldValue() {
        
        if (processNameField == null) {
            return EMPTY; 
        }
        return processNameField.getText().trim();
    }
    

    
    /**
     * Returns the current project name as entered by the user, or its anticipated
     * initial value.
     *
     * @return the project name, its anticipated initial value, or <code>null</code>
     *   if no project name is known
     */
    public String getProjectNamespace() {
                
        return getProjectNamespaceFieldValue();
    }
    /**
     * Returns the value of the project name field
     * with leading and trailing spaces removed.
     * 
     * @return the project name in the field
     */
    private String getProjectNamespaceFieldValue() {
        if (processNameField == null) {
            return EMPTY; 
        }
        return processNamespaceField.getText().trim();
    }

        
    /**
     * Returns whether this page's controls currently all contain valid 
     * values.
     *
     * @return <code>true</code> if all controls are valid, and
     *   <code>false</code> if at least one is invalid
     */
    protected boolean validatePage() {
    	
    	IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();

    	IDialogSettings settings = getWizard().getDialogSettings();
    	
    	String processName = processNameField.getText();    	 
                  
        if (processName.equals(EMPTY)) { 
            setErrorMessage(null);
            setMessage( Messages.NewFileWizardPage1_8); 
            return false;
        }

        IStatus nameStatus =
            workspace.validateName(processName, IResource.FILE);
        
        if (!nameStatus.isOK()) {
            setErrorMessage(nameStatus.getMessage());
            return false;
        }
        
        // Make sure that there are no spaces in the name
        if( processName.indexOf( " " ) > -1 ) //$NON-NLS-1$
        {
            setErrorMessage(Messages.NewFileWizardPage1_10);
            return false;
        }       
              
        setErrorMessage(null);
        setMessage(null);
      
        String namespace   = processNamespaceField.getText().trim(); 
        if (namespace.length() < 1) {
        	setErrorMessage(Messages.NewFileWizardPage1_11);
        	return false;
        }
        
        String bpelNamespace = (isAbstractOptionButtonChecked())?
        		BPELConstants.NAMESPACE_ABSTRACT_2007: BPELConstants.NAMESPACE;
        
        // settings for next time the dialog is used.
        settings.put( LAST_NAMESPACE_KEY , namespace) ;
                
        // Template arguments
        mArgs.put("processName", processName ); //$NON-NLS-1$
        mArgs.put("namespace",   namespace );   //$NON-NLS-1$
        mArgs.put("bpelNamespace", bpelNamespace );	    //$NON-NLS-1$
        mArgs.put("date",   new Date() );	    //$NON-NLS-1$

        
        return true;
    }

    
    /**
     * @return true if Option for abstract process is checked
     */
    private boolean isAbstractOptionButtonChecked() {
    	return processAbstractOptionButton.getSelection();
    }
    
    /**
     * see @DialogPage.setVisible(boolean)
     * @param visible whether should be visible or not
     * 
     */
    
    @Override
	public void setVisible (boolean visible) {
        super.setVisible(visible);
        if (visible) {
            processNameField.setFocus();
        }
    }

	/**
	 * @return the actual selected template.
	 */
    
	public Template getSelectedTemplate() {
		String txt = processTemplateField.getText().trim();
		return BPELUIPlugin.getDefault().getTemplates().getTemplateByName( txt );
	}

	/**
	 * @return the arguments that need to be supplied to the template mechanism.
	 */
	
	public Map<String,Object> getArgs() {
		
		return mArgs;
	}

	 
  
}
