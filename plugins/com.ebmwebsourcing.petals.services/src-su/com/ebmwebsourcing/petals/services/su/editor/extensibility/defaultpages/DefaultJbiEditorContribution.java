/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.OpenSourceEditorHyperlinkListener;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * This class generates default contributions.
 * <p>
 * It is parameterized by the {@link EClass}es to introspect to generate widgets.
 * The main tab is made of default JBI stuff, advanced tab is made of generated widgets
 * </p>
 *
 * @author Mickael Istria - EBM WebSourcing
 */
public class DefaultJbiEditorContribution extends JbiEditorDetailsContribution {

	private final EClass[] extensionClasses;


	/**
	 * Constructor.
	 * @param extensionEClasses
	 */
	public DefaultJbiEditorContribution( EClass... extensionEClasses ) {
		this.extensionClasses = extensionEClasses;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent( AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, ISharedEdition ise ) {
		Composite composite = createCommonProvideSection( generalDetails, toolkit );
		JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, composite, ise );

		// Add a warning message
		final Image noticeImage = PetalsServicesPlugin.loadImage( "icons/obj16/smartmode_co.gif" );
		FormText ft = new FormText( composite, SWT.BORDER | SWT.WRAP ) {
			@Override
			public void dispose() {
				super.dispose();
				if( noticeImage != null && ! noticeImage.isDisposed())
					noticeImage.dispose();
			}
		};

		ft.marginWidth = 7;
		ft.marginHeight = 7;
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 30;
		layoutData.widthHint = 380;
		layoutData.heightHint = 100;
		ft.setLayoutData( layoutData );

		ft.setImage( "tip", noticeImage );
		StringBuilder sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p><img href=\"tip\" /> <b>Notice</b></p>" );
		sb.append( "<p>This a default edition view. This component or this component version is not supported by the current Petals tooling.</p>" );
		sb.append( "<p vspace=\"false\">It is also possible that this component does not support <b>provides</b> or <b>consumes</b> blocks.</p>" );
		sb.append( "<p>You may prefer to edit the sources directly. In this case, please use the <a>Petals Source Editor</a>.</p>" );
		sb.append( "</form>" );

		ft.addHyperlinkListener( new OpenSourceEditorHyperlinkListener( ise.getEditedFile(), false ));
		ft.setText( sb.toString(), true, false );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent( AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, ISharedEdition ise ) {

		if( this.extensionClasses != null && this.extensionClasses.length > 0 )
			JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, advancedDetails, ise, this.extensionClasses);
		else
			toolkit.createLabel( advancedDetails, "Advanced settings are not available." );
	}
}
