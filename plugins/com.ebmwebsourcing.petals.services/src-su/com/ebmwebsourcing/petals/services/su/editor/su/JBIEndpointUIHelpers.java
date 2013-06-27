/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.su;

import javax.xml.namespace.QName;

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

import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class JBIEndpointUIHelpers {

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
			final Composite container ) {

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
		if( endpoint.getInterfaceName() != null ) {
			if( endpoint.getInterfaceName().getLocalPart() != null )
				itfNameText.setText( endpoint.getInterfaceName().getLocalPart());
			if( endpoint.getInterfaceName().getNamespaceURI() != null )
				itfNamespaceText.setText( endpoint.getInterfaceName().getNamespaceURI());
		}

		if( endpoint.getServiceName() != null ) {
			if( endpoint.getServiceName().getLocalPart() != null )
				srvNameText.setText( endpoint.getServiceName().getLocalPart());
			if( endpoint.getServiceName().getNamespaceURI() != null )
				srvNamespaceText.setText( endpoint.getServiceName().getNamespaceURI());
		}

		ModifyListener itfListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String local = itfNameText.getText().trim();
				String ns = itfNamespaceText.getText().trim();
				endpoint.setInterfaceName( new QName( ns, local ));
			}
		};

		itfNameText.addModifyListener( itfListener );
		itfNamespaceText.addModifyListener( itfListener );

		ModifyListener srvListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String local = srvNameText.getText().trim();
				String ns = srvNamespaceText.getText().trim();
				endpoint.setServiceName( new QName( ns, local ));
			}
		};

		srvNameText.addModifyListener( srvListener );
		srvNamespaceText.addModifyListener( srvListener );
		edptText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				String text = ((Text) e.widget).getText().trim();
				endpoint.setEndpointName( text.length() > 0 ? text : null );
			}
		});


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
}
