package com.ebmwebsourcing.petals.services.cdk.editor;

import javax.swing.event.HyperlinkEvent;
import javax.xml.namespace.QName;

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
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.QNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.StringIsQNameValidator;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.StringToQNameConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.ToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.PetalsImages;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.cdk.editor.databinding.StringToMepConverter;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

public class CDK5JBIEndpointUIHelper {

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


	public static void createProvidesUI(final AbstractEndpoint endpoint, final FormToolkit toolkit, final Composite generalDetails, ISharedEdition ise) {
		toolkit.createLabel(generalDetails, Messages.wsdlLocation);
		Composite composite = toolkit.createComposite(generalDetails);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		composite.setLayout(new GridLayout(2, false));
		Text wsdlLocationText = toolkit.createText(composite, "", SWT.BORDER);
		wsdlLocationText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browse = toolkit.createButton(composite, Messages.select, SWT.PUSH);
		browse.setImage(PetalsImages.getSearchWSDL());
		Link link = new Link(composite, SWT.NONE);
		link.setText("<A>" + Messages.wsdlTools + "</A>");
		ToolTip tooltip = new WSDLHelperTooltip(link, toolkit, (Provides)endpoint, ise, link.getShell());
		tooltip.setHideDelay(0);
		tooltip.setHideOnMouseDown(false);

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(wsdlLocationText, SWT.Modify)),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_PROVIDES__WSDL));

	}

}
