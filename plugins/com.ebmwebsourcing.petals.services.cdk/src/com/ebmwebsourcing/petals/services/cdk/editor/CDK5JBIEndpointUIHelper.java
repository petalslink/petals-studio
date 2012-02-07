/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.net.URI;
import java.util.Arrays;

import javax.swing.event.HyperlinkEvent;
import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.QNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.StringIsQNameValidator;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.StringToQNameConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.ToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.databinding.StringToMepConverter;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers.CommonUIBean;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class CDK5JBIEndpointUIHelper {

	/**
	 * @param endpoint
	 * @param toolkit
	 * @param generalDetails
	 * @param ise
	 */
	public static void createConsumesUI(final AbstractEndpoint endpoint, final FormToolkit toolkit, final Composite generalDetails, final ISharedEdition ise) {
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, generalDetails, ise);

		// The edition fields
		Label label = toolkit.createLabel( generalDetails, "Operation name:" );
		label.setToolTipText( "The QName of the operation (should match an operation declared in a WSDL)" );

		final Text operationNameText = toolkit.createText( generalDetails, "", SWT.SINGLE | SWT.BORDER );

		label = toolkit.createLabel( generalDetails, "Invocation MEP:" );
		label.setToolTipText( "The Message Exchange Pattern to use for the invocation" );

		CCombo mepCombo = new CCombo( generalDetails, SWT.BORDER | SWT.READ_ONLY );
		mepCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		toolkit.adapt( mepCombo );

		final ComboViewer mepViewer = new ComboViewer( mepCombo );
		mepViewer.setContentProvider( new ArrayContentProvider());
		mepViewer.setLabelProvider( new LabelProvider());
		mepViewer.setInput( Mep.values());


		// Add a set of helpers
		toolkit.createLabel( generalDetails, "" );

		final Section section = toolkit.createSection( generalDetails, ExpandableComposite.TWISTIE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 5;
		section.setLayoutData( layoutData );
		section.clientVerticalSpacing = 10;
		section.setText( "Helpers" );

		Composite subgeneralDetails = toolkit.createComposite( section );
		final GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subgeneralDetails.setLayout( layout );
		subgeneralDetails.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setClient( subgeneralDetails );

		Hyperlink selectLink = toolkit.createHyperlink(
					subgeneralDetails, "Select a Petals service and an operation to invoke", SWT.NONE );
		selectLink.setToolTipText( "Select the service and the operation to invoke from the known Petals services" );
		selectLink.addHyperlinkListener( new PetalsHyperlinkListener() {
			public void linkActivated( HyperlinkEvent e ) {
				final EnhancedConsumeDialog dlg = new EnhancedConsumeDialog( generalDetails.getShell(), toolkit );
				if( dlg.open() == Window.OK ) {
					CompoundCommand compositeCommand = new CompoundCommand();
					EditingDomain editDomain = ise.getEditingDomain();

					QName q = dlg.getItfToInvoke();
					Command command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, q);
					compositeCommand.append(command);

					q = dlg.getSrvToInvoke();
					command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, q);
					compositeCommand.append(command);

					String edpt = dlg.getEdptToInvoke();
					command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edpt);
					compositeCommand.append(command);

					command = new SetCommand(editDomain, endpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, dlg.getOperationToInvoke());
					compositeCommand.append(command);

					command = new SetCommand(editDomain, endpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP, dlg.getInvocationMep().toString());
					compositeCommand.append(command);

					editDomain.getCommandStack().execute(compositeCommand);
				}
			}
		});

		// Operation
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(300, SWTObservables.observeText(operationNameText, SWT.Modify)),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION),
				new UpdateValueStrategy().setConverter(new StringToQNameConverter()).setBeforeSetValidator(new StringIsQNameValidator()),
				new UpdateValueStrategy().setConverter(new QNameToStringConverter()));

		// MEP
		ise.getDataBindingContext().bindValue(
				ViewersObservables.observeSingleSelection(mepViewer),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP),
				new UpdateValueStrategy().setConverter(new StringToMepConverter()),
				new UpdateValueStrategy().setConverter(new ToStringConverter()));
	}


	/**
	 * Creates the UI for "provide" blocks in the JBI editor (with CDK 5 fields).
	 * @param endpoint
	 * @param toolkit
	 * @param generalDetails
	 * @param ise
	 */
	public static void createProvidesUI(
			final AbstractEndpoint endpoint,
			final FormToolkit toolkit,
			final Composite parent,
			final ISharedEdition ise) {


		// First: the WSDL part
		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );

		// The label browser
		Label wsdlLabel = toolkit.createLabel( parent, "WSDL location:" );
		wsdlLabel.setToolTipText( "The relative path of the WSDL in the service unit or an URL" );
		wsdlLabel.setForeground( blueFont );
		GridDataFactory.swtDefaults().align( SWT.LEFT, SWT.TOP ).indent( 0, 6 ).applyTo( wsdlLabel );

		Composite subContainer = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().margins( 0, 0 ).numColumns( 2 ).extendedMargins( 0, 0, 0, 20 ).applyTo( subContainer );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text wsdlText = toolkit.createText( subContainer, "", SWT.SINGLE | SWT.BORDER );
		wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = toolkit.createButton( subContainer, "Browse...", SWT.PUSH );
		browseButton.setToolTipText( "Select a WSDL located in the project resources" );
		browseButton.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				IProject project = ise.getEditedFile().getProject();
				IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

				// FIXME: replace this dialog by one that only shows WSDLs?
				ElementTreeSelectionDialog dlg = SwtFactory.createWorkspaceFileSelectionDialog(
						wsdlText.getShell(), resourceFolder,
						"WSDL Selection", "Select a WSDL file located in the project's resource directory.", "wsdl" );

				File file = JbiXmlUtils.getWsdlFile(
							ise.getEditedFile(),
							wsdlText.getText());

				if( file != null ) {
					IFile wsdlFile = ResourceUtils.getIFile( file );
					if( wsdlFile != null )
						dlg.setInitialElementSelections( Arrays.asList( wsdlFile ));
				}

				if( dlg.open() == Window.OK ) {
					IFile selectedFile = (IFile) dlg.getResult()[ 0 ];
					int rfsc = resourceFolder.getFullPath().segmentCount();
					String wsdlValue = selectedFile.getFullPath().removeFirstSegments( rfsc ).toString();
					wsdlText.setText( wsdlValue );
				}
			}
		});


		// The helpers
		final WsdlParsingJobManager wsdlParsingJob = new WsdlParsingJobManager();
		wsdlText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				String wsdlValue = ((Text) e.widget).getText().trim();
				File f = JbiXmlUtils.getWsdlFile( ise.getEditedFile(), wsdlValue );
				final URI wsdlUri;
				if( f != null )
					wsdlUri = f.toURI();
				else if( wsdlValue != null )
					wsdlUri = UriAndUrlHelper.urlToUri( wsdlValue );
				else
					wsdlUri = null;

				wsdlParsingJob.setWsdlUri( wsdlUri );
			}
		});

		Link link = new Link( subContainer, SWT.NONE );
		link.setText("<A>" + Messages.wsdlTools + "</A>");
		ToolTip tooltip = new WSDLHelperTooltip( link, toolkit, (Provides) endpoint, ise, wsdlParsingJob );
		tooltip.setHideDelay( 0 );
		tooltip.setHideOnMouseDown( false );


		// The data-binding
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText( wsdlText, SWT.Modify )),
				EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_PROVIDES__WSDL ));


		// Then, the common widgets
		final CommonUIBean commonBean = JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, parent, ise );


		// Then, add the auto-generation button
		toolkit.createLabel( parent, "" );

		// Add the button and its label
		final Button generateEdptButton = toolkit.createButton( parent, "", SWT.CHECK );
		generateEdptButton.setText( "Generate the end-point at deployment time" );
		generateEdptButton.setToolTipText( "The end-point name will be generated by Petals on deployment" );
		generateEdptButton.addSelectionListener( new DefaultSelectionListener() {
			private String oldValue;

			@Override
			public void widgetSelected( SelectionEvent e ) {
				boolean selected = generateEdptButton.getSelection();
				commonBean.edptText.setEnabled( ! selected );
				commonBean.edptLabel.setEnabled( ! selected );

				if( selected )
					this.oldValue = commonBean.edptText.getText().trim();
				else if( StringUtils.isEmpty( this.oldValue ))
					this.oldValue = commonBean.srvNameText.getText() + "Endpoint";

				String edptValue = selected ? PetalsConstants.AUTO_GENERATE : this.oldValue;
				Command cmd = SetCommand.create( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edptValue );
				ise.getEditingDomain().getCommandStack().execute( cmd );
			}
		});
	}
}
