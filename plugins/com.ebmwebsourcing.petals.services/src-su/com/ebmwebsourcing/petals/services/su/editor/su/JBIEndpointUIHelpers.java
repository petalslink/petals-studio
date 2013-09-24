/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.su;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.databinding.LocalQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.NamespaceQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjectUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.CommonUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public final class JBIEndpointUIHelpers {

	/**
	 * Private constructor for utility class.
	 */
	private JBIEndpointUIHelpers() {
		// nothing
	}


	/**
	 * A simple bean.
	 */
	public static class CommonUIBean {
		public Text itfNameText, itfNamespaceText, srvNameText, srvNamespaceText, edptText;
		public Label edptLabel;
	}


	/**
	 * Creates the common widgets for the main tab in the JBI editor.
	 * @param endpoint
	 * @param toolkit
	 * @param generalDetails
	 * @param ise
	 */
	public static CommonUIBean createCommonEndpointUI(
			final AbstractEndpoint endpoint,
			FormToolkit toolkit,
			final Composite container,
			final ISharedEdition ise ) {

		// Controls
		String end = endpoint instanceof Provides ? " *:" : ":";
		Color blueFont = container.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );

		SwtFactory.createLabel( container, "Interface Namespace *:", "The qualified name of the service contract" ).setForeground( blueFont );
		final Text itfNamespaceText = SwtFactory.createSimpleTextField( container, true );

		SwtFactory.createLabel( container, "Interface Name *:", "The qualified name of the service contract" ).setForeground( blueFont );
		final Text itfNameText = SwtFactory.createSimpleTextField( container, true );

		SwtFactory.createLabel( container, "Service Namespace" + end, "The qualified name of the service implementation" ).setForeground( blueFont );
		final Text srvNamespaceText = SwtFactory.createSimpleTextField( container, true );

		SwtFactory.createLabel( container, "Service Name" + end, "The qualified name of the service implementation" ).setForeground( blueFont );
		final Text srvNameText = SwtFactory.createSimpleTextField( container, true );

		Label edptLabel = SwtFactory.createLabel( container, "End-point Name" + end, "The name of the service deployment point" );
		edptLabel.setForeground( blueFont );
		Text edptText = SwtFactory.createSimpleTextField( container, true );


		// Data-binding
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( itfNameText ),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME ),
				null,
				new UpdateValueStrategy().setConverter( new LocalQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( itfNamespaceText ),
				EMFEditObservables.observeValue(ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME),
				null,
				new UpdateValueStrategy().setConverter( new NamespaceQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( srvNameText ),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME ),
				null,
				new UpdateValueStrategy().setConverter( new LocalQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( srvNamespaceText ),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME ),
				null,
				new UpdateValueStrategy().setConverter( new NamespaceQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue( 200, SWTObservables.observeText( edptText, SWT.Modify )),
				EMFEditObservables.observeValue( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME ));


		// The data-binding handles the "model to target (widget)" parts. But not ALL the "widget to model" parts.
		// For QNames, in fact, the data-binding cannot be applied in this sense. We have to use a modify listener for this.
		createModifyListenerForQname(
				ise.getEditingDomain(), endpoint, itfNamespaceText, itfNameText,
				JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, false );

		createModifyListenerForQname(
				ise.getEditingDomain(), endpoint, srvNamespaceText, srvNameText,
				JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, false );

		// PETALSSTUD-268: Wrong handling of empty end-point name
		// Do not set an empty end-point name in the model
		edptText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				String value = ((Text) e.widget).getText().trim();
				if( value.length() > 0 )
					return;

				Command cmd = EObjectUIHelper.createCustomSetCommand(
						ise.getEditingDomain(),
						endpoint,
						JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME,
						null );
				ise.getEditingDomain().getCommandStack().execute( cmd );
			}
		});
		// PETALSSTUD-268


		// Complete the UI effects
		final ModifyListener sameNsModifyListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String serviceNs = srvNamespaceText.getText();
				String interfaceNs = itfNamespaceText.getText();

				Color fgColor;
				if( serviceNs.trim().length() > 0
							&& serviceNs.equals( interfaceNs ))
					fgColor = container.getDisplay().getSystemColor( SWT.COLOR_DARK_GREEN );
				else
					fgColor = container.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );

				srvNamespaceText.setForeground( fgColor );
				itfNamespaceText.setForeground( fgColor );
			}
		};

		FocusListener nsFocusListener = new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				((Text) e.widget).addModifyListener( sameNsModifyListener );
				((Text) e.widget).notifyListeners( SWT.Modify, new Event());
			}

			@Override
			public void focusLost( FocusEvent e ) {
				((Text) e.widget).removeModifyListener( sameNsModifyListener );
				Color fgColor = container.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );
				srvNamespaceText.setForeground( fgColor );
				itfNamespaceText.setForeground( fgColor );
			}
		};

		itfNamespaceText.addFocusListener( nsFocusListener );
		srvNamespaceText.addFocusListener( nsFocusListener );


		// Prepare the result
		CommonUIBean result = new CommonUIBean();
		result.edptText = edptText;
		result.itfNameText = itfNameText;
		result.itfNamespaceText = itfNamespaceText;
		result.srvNameText = srvNameText;
		result.srvNamespaceText = srvNamespaceText;
		result.edptLabel = edptLabel;

		return result;
	}


	/**
	 * Creates a modify listener for QName widgets.
	 * <p>
	 * The data-binding handles the "model to target (widget)" parts. But not ALL the "widget to model" parts.
	 * For QNames, in fact, the data-binding cannot be applied in this sense. We have to use a modify listener for this.
	 * </p>
	 *
	 * @param domain
	 * @param owner
	 * @param namespaceText
	 * @param nameText
	 * @param attribute
	 * @param useCustomSetCommand true to use a custom Set command, false for the usual SetCommand
	 * TODO: replace this method by EMFEditObservables as soon as EMF 2.8.0 or 2.7.2 is out
	 * @See EObjectUIHelper.createCustomSetCommand
	 */
	public static ActivableListener createModifyListenerForQname(
			final EditingDomain domain,
			final EObject owner,
			final Text namespaceText,
			final Text nameText,
			final EAttribute attribute,
			final boolean useCustomSetCommand ) {

		ActivableListener listener = new ActivableListener() {
			@Override
			public void handleEvent( Event e ) {

				// Not enabled, do nothing
				if( ! isEnabled())
					return;

				// Save the caret position
				// Otherwise, the caret goes back to the position 0
				Text focusedText = null;
				if( namespaceText.isFocusControl())
					focusedText = namespaceText;
				else if( nameText.isFocusControl())
					focusedText = nameText;

				int caretPosition = focusedText == null ? -1 : focusedText.getCaretPosition();

				// Update the model?
				String ns = namespaceText.getText().trim();
				String name = nameText.getText().trim();

				Object o = owner.eGet( attribute );
				boolean needsUpdate = false;
				if( o instanceof QName ) {
					String currentNs = ((QName) o).getNamespaceURI();
					String currentName = ((QName) o).getLocalPart();
					needsUpdate = ! CommonUtils.areEqual( ns, currentNs ) || ! CommonUtils.areEqual( name, currentName );
				}

				if( needsUpdate ) {
					QName result;
					if( StringUtils.isEmpty( ns ))
						result = StringUtils.isEmpty( name ) ? null : new QName( name );
					else
						result = new QName( ns, name );

					Command cmd;
					if( useCustomSetCommand )
						cmd = EObjectUIHelper.createCustomSetCommand( domain, owner, attribute, result );
					else
						cmd = SetCommand.create( domain, owner, attribute, result );

					domain.getCommandStack().execute( cmd );
				}

				// Restore the caret position
				if( caretPosition != -1 )
					focusedText.setSelection( caretPosition );
			}
		};

		namespaceText.addListener( SWT.Modify, listener );
		nameText.addListener( SWT.Modify, listener );
		return listener;
	}


	/**
	 * Creates widgets automatically by introspecting EMF classes.
	 * @param endpoint
	 * @param toolkit
	 * @param advancedDetails
	 * @param ise
	 * @param extensionClasses
	 */
	public static void createDefaultWidgetsByEIntrospection(
			AbstractEndpoint endpoint,
			FormToolkit toolkit,
			Composite advancedDetails,
			ISharedEdition ise,
			EClass... extensionClasses) {

		List<EStructuralFeature> toProcessFeaturesList = new ArrayList<EStructuralFeature>();
		for (EClass extensionClass : extensionClasses) {
			for (EStructuralFeature feature : extensionClass.getEAllStructuralFeatures()) {
				if (isInPackage(feature, extensionClass)
						&& feature instanceof EAttribute
						&& !feature.getEType().equals(EcorePackage.Literals.EFEATURE_MAP_ENTRY))
					toProcessFeaturesList.add( feature );
			}
		}

		EStructuralFeature[] toProcessFeatures = toProcessFeaturesList.toArray(new EStructuralFeature[toProcessFeaturesList.size()] );
		EObjectUIHelper.generateWidgets(endpoint, toolkit, advancedDetails, ise.getEditingDomain(), ise.getDataBindingContext(), true, toProcessFeatures);
	}


	/**
	 * @param feature
	 * @param extensionClass
	 * @return
	 */
	private static boolean isInPackage(EStructuralFeature feature, EClass extensionClass) {
		return feature.eContainer() instanceof EClass && ((EClass)feature.eContainer()).getEPackage().equals(extensionClass.getEPackage());
	}
}
