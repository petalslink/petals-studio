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

package com.ebmwebsourcing.petals.jbi.editor.form.cdk5;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.common.databinding.StringToMepConverter;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.jbi.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.common.databinding.ToStringConverter;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;
import com.ebmwebsourcing.petals.services.jbi.editor.su.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.JbiPackage;


/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CDK5ConsumesDetails implements JbiEditorDetailsContribution {

	private Text operationText;
	private ComboViewer mepViewer;
	private CompounedSUDetailsPage hostDetailsPage;
	private AbstractServicesFormPage generalFormPage;
	private Consumes selectedEndpoint;


	/**
	 * Constructor.
	 * @param page
	 * @param cdkSupport 
	 */
	public CDK5ConsumesDetails(CompounedSUDetailsPage hostDetailsPage) {
		this.hostDetailsPage = hostDetailsPage;
		this.generalFormPage = hostDetailsPage.getGeneralPage();
		hostDetailsPage.setSectionTitle("Consumes properties");
		hostDetailsPage.setSectionDescription("Define the identifiers of the service to consume.");
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentAfter(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addSUContentAfter(final Composite parent) {
		init();
		
		// Add a section below for the operation to invoke
		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		final FormToolkit toolkit = hostDetailsPage.getManagedForm().getToolkit();
		Section section = toolkit.createSection( parent,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Invocation properties" );
		section.setDescription( "Edit the invoked operation and the message exchange pattern." );

		Composite container = toolkit.createComposite( section );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// The edition fields
		Label label = toolkit.createLabel( container, "Operation namespace:" );
		label.setToolTipText( "The namespace of the operation (should match an operation declared in a WSDL)" );
		label.setForeground( blueFont );

		this.operationText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.operationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = toolkit.createLabel( container, "Operation name:" );
		label.setToolTipText( "The name of the operation (should match an operation declared in a WSDL)" );
		label.setForeground( blueFont );

		this.operationText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );

		label = toolkit.createLabel( container, "Invocation MEP:" );
		label.setToolTipText( "The Message Exchange Pattern to use for the invocation" );
		label.setForeground( blueFont );

		CCombo mepCombo = new CCombo( container, SWT.BORDER | SWT.READ_ONLY );
		mepCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		toolkit.adapt( mepCombo );

		this.mepViewer = new ComboViewer( mepCombo );
		this.mepViewer.setContentProvider( new ArrayContentProvider());
		this.mepViewer.setLabelProvider( new LabelProvider());
		this.mepViewer.setInput( Mep.values());


		// Add a set of helpers
		toolkit.createLabel( container, "" );

		section = toolkit.createSection( container, ExpandableComposite.TWISTIE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 5;
		section.setLayoutData( layoutData );
		section.clientVerticalSpacing = 10;
		section.setText( "Helpers" );

		Composite subContainer = toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setClient( subContainer );

		Hyperlink selectLink = toolkit.createHyperlink(
					subContainer, "Select a Petals service and an operation to invoke", SWT.NONE );
		selectLink.setToolTipText( "Select the service and the operation to invoke from the known Petals services" );
		selectLink.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				final EnhancedConsumeDialog dlg = new EnhancedConsumeDialog( parent.getShell(), toolkit );
				if( dlg.open() == Window.OK ) {
					CompoundCommand compositeCommand = new CompoundCommand();
					EditingDomain editDomain = generalFormPage.getEditDomain();

					QName q = dlg.getItfToInvoke();
					Command command = new SetCommand(editDomain, selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, q);
					compositeCommand.append(command);

					q = dlg.getSrvToInvoke();
					command = new SetCommand(editDomain, selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, q);
					compositeCommand.append(command);

					String edpt = dlg.getEdptToInvoke();
					command = new SetCommand(editDomain, selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edpt);
					compositeCommand.append(command);
					
					command = new SetCommand(editDomain, selectedEndpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, dlg.getOperationToInvoke());
					compositeCommand.append(command);
					
					command = new SetCommand(editDomain, selectedEndpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP, dlg.getInvocationMep().toString());
					compositeCommand.append(command);
					
					editDomain.getCommandStack().execute(compositeCommand);
					refresh();

				}
			}
		});
	}


	private void init() {
		selectedEndpoint = (Consumes)hostDetailsPage.getSelectedEndpoint();
		/*EditingDomain domain = generalFormPage.getEditDomain();
		domain.getCommandStack().execute(ProvidesConsumesCDK5Util.getSetAttributesCommand(domain, selectedEndpoint));*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #refreshCustomWidgets()
	 */
	@Override
	public void refresh() {
		this.selectedEndpoint = (Consumes) hostDetailsPage.getSelectedEndpoint();
		if( selectedEndpoint == null
				|| this.operationText == null 	// page already loaded?
				|| this.operationText.isDisposed())
				return;
		
		InitializeModelExtensionCommand fixExtensionCommand = new InitializeModelExtensionCommand(Cdk5Package.eINSTANCE, selectedEndpoint);
		if (fixExtensionCommand.canExecute()) {
			generalFormPage.getEditDomain().getCommandStack().execute(fixExtensionCommand);
		}

		// TODO use databinding
		
		DataBindingContext dbc = hostDetailsPage.getDataBindingContext();
		
		// Operation
		dbc.bindValue(
				SWTObservables.observeDelayedValue(300, SWTObservables.observeText(operationText, SWT.Modify)),
				EMFEditObservables.observeValue(generalFormPage.getEditDomain(), selectedEndpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION));

		// MEP
		dbc.bindValue(
				ViewersObservables.observeSingleSelection(mepViewer),
				EMFEditObservables.observeValue(generalFormPage.getEditDomain(), selectedEndpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP),
				new UpdateValueStrategy().setConverter(new StringToMepConverter()),
				new UpdateValueStrategy().setConverter(new ToStringConverter()));
	}

	
	@Override
	public void addSUContentBefore(Composite container) {
	}

	@Override
	public void addSUContentAfterEndpoint(Composite container) {
	}




}
