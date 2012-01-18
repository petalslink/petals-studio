package com.ebmwebsourcing.petals.services.su.editor.su;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.databinding.LocalQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.NamespaceQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsColors;
import com.ebmwebsourcing.petals.services.PetalsImages;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;

public class JBIEndpointUIHelpers {

	public static class CommonEndpointControls {
		private final Composite interfaceComposite;
		private final Composite serviceComposite;

		public CommonEndpointControls(Composite itf, Composite service) {
			this.interfaceComposite = itf;
			this.serviceComposite = service;
		}

		public Composite getInterfaceComposite() {
			return this.interfaceComposite;
		}

		public Composite getServiceComposite() {
			return this.serviceComposite;
		}
	}

	public static CommonEndpointControls createCommonEndpointUI(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite generalDetails, final ISharedEdition ise) {
		Label label = toolkit.createLabel( generalDetails, Messages.interfaceQName );
		label.setToolTipText( "The Qualified Name '{namespace}element' of the interface (must match an interface declared in the WSDL)" );

		final Composite interfaceComposite = toolkit.createComposite(generalDetails);
		interfaceComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		interfaceComposite.setLayout(new GridLayout(5, false));
		toolkit.createLabel(interfaceComposite, "{");
		final StyledText interfaceNSText = new StyledText(interfaceComposite, SWT.SINGLE);
		interfaceNSText.setEditable(false);
		interfaceNSText.setForeground(PetalsColors.getLightPurple());
		toolkit.createLabel(interfaceComposite, "}");
		StyledText interfaceLocalText = new StyledText(interfaceComposite, SWT.SINGLE);
		interfaceLocalText.setForeground(PetalsColors.getDarkPurple());
		Button editInterfaceButton = toolkit.createButton(interfaceComposite, Messages.edit, SWT.PUSH);
		editInterfaceButton.setImage(PetalsImages.getPencil());
		editInterfaceButton.setLayoutData(new GridData(SWT.END, SWT.DEFAULT, true, false));

		interfaceNSText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				interfaceComposite.layout(true);
			}
		});
		interfaceLocalText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				interfaceComposite.layout(true);
			}
		});

		label = toolkit.createLabel( generalDetails,Messages.serviceQName );
		//label.setToolTipText( "The Qualified Name '{namespace}element' of the service (must match a service declared in the WSDL)" );

		final Composite serviceComposite = toolkit.createComposite(generalDetails);
		serviceComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		serviceComposite.setLayout(new GridLayout(5, false));
		toolkit.createLabel(serviceComposite, "{");
		final StyledText serviceNSText = new StyledText(serviceComposite, SWT.SINGLE);
		serviceNSText.setEditable(false);
		serviceNSText.setForeground(PetalsColors.getLightPurple());
		toolkit.createLabel(serviceComposite, "}");
		StyledText serviceLocalText = new StyledText(serviceComposite, SWT.SINGLE);
		serviceLocalText.setForeground(PetalsColors.getDarkPurple());
		Button editServiceButton = toolkit.createButton(serviceComposite, Messages.edit, SWT.PUSH);
		editServiceButton.setImage(PetalsImages.getPencil());
		editServiceButton.setLayoutData(new GridData(SWT.END, SWT.DEFAULT, true, false));

		serviceNSText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				serviceComposite.layout(true);
			}
		});
		serviceLocalText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				serviceComposite.layout(true);
			}
		});

		Label edptNameLabel = toolkit.createLabel( generalDetails, "End-point name:" );
		edptNameLabel.setToolTipText( "The end-point name, meaning the service location (must match the one declared in the WSDL)" );

		Text edptNameText = toolkit.createText( generalDetails, "", SWT.SINGLE | SWT.BORDER );
		edptNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText(interfaceLocalText),
				EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				null,
				new UpdateValueStrategy().setConverter(new LocalQNameToStringConverter()));
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText(interfaceNSText),
				EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				null,
				new UpdateValueStrategy().setConverter(new NamespaceQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText(serviceLocalText),
				EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME),
				null,
				new UpdateValueStrategy().setConverter(new LocalQNameToStringConverter()));
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText(serviceNSText),
				EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME),
				null,
				new UpdateValueStrategy().setConverter(new NamespaceQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(edptNameText, SWT.Modify)),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME));

		editInterfaceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QNameEditor qnameEditor = new QNameEditor(generalDetails.getShell(), endpoint.getInterfaceName());
				if (qnameEditor.open() == Window.OK ) {
					SetCommand command = new SetCommand(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, qnameEditor.getQName());
					ise.getEditingDomain().getCommandStack().execute(command);
					interfaceComposite.layout(true);
				}
			}
		});
		editServiceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QNameEditor qnameEditor = new QNameEditor(generalDetails.getShell(), endpoint.getServiceName());
				if (qnameEditor.open() == Window.OK ) {
					SetCommand command = new SetCommand(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, qnameEditor.getQName());
					ise.getEditingDomain().getCommandStack().execute(command);
					serviceComposite.layout(true);
				}
			}
		});

		return new CommonEndpointControls(interfaceComposite, serviceComposite);
	}

	public static void createDefaultWidgetsByEIntrospection(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, ISharedEdition ise, EClass[] extensionClasses) {
		List<EStructuralFeature> toProcessFeaturesList = new ArrayList<EStructuralFeature>();
		for (EClass extensionClass : extensionClasses) {
			for (EStructuralFeature feature : extensionClass.getEAllStructuralFeatures()) {
				if (isInPackage(feature, extensionClass) && feature instanceof EAttribute && !feature.getEType().equals(EcorePackage.Literals.EFEATURE_MAP_ENTRY)) {
					toProcessFeaturesList.add(feature);
				}
			}
		}
		EStructuralFeature[] toProcessFeatures = toProcessFeaturesList.toArray(new EStructuralFeature[toProcessFeaturesList.size()] );

		EObjecttUIHelper.generateWidgets(endpoint, toolkit, advancedDetails, ise.getEditingDomain(), ise.getDataBindingContext(), toProcessFeatures);
	}


	private static boolean isInPackage(EStructuralFeature feature, EClass extensionClass) {
		return feature.eContainer() instanceof EClass && ((EClass)feature.eContainer()).getEPackage().equals(extensionClass.getEPackage());
	}
}
