/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
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

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ImageRegistry;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EMFPCStyledLabelProvider extends LabelProvider implements IStyledLabelProvider {

	private final Color consumesKeyWordForegroundColor;
	private final Color providesKeyWordForegroundColor;
	private final Color forbiddenForegroundColor;
	private final Color categoryColor;
	private final Font propertiesFont;
	private final Font categoryFont;
	private final Styler providesKeyWordsStyle, consumesKeyWordsStyle, propertiesStyle;

	private Map<EObject,List<IMarker>> elementToMarkers;
	private final ImageRegistry imageRegistry;
	private final ImageDescriptor providesDesc, consumesDesc;


	/**
	 * Constructor equivalent to <code>new PCStyledLabelProvider( control, Mep.UNKNOWN )</code>.
	 * @param control
	 */
	public EMFPCStyledLabelProvider( Control control ) {
		this( control, Mep.UNKNOWN );
	}


	/**
	 * Constructor.
	 * @param control
	 * @param filteringMep the MEP that a service must support (for at least one operation)
	 * <p>
	 * Use Mep.UNKNOWN to not filter results.
	 * Note that it is only used with {@link EndpointBean} objects as input.
	 * </p>
	 */
	public EMFPCStyledLabelProvider( Control control, Mep filteringMep ) {

		// Stylers
		FontData[] originalData = control.getFont().getFontData();
		this.propertiesFont = new Font( control.getDisplay(), PlatformUtils.getModifiedFontData( originalData, SWT.NORMAL ));
		this.categoryFont = new Font( control.getDisplay(), PlatformUtils.getModifiedFontData( originalData, SWT.BOLD ));
		this.consumesKeyWordForegroundColor = new Color( Display.getCurrent(), 228, 100, 37 );
		this.providesKeyWordForegroundColor = new Color( Display.getCurrent(), 120, 49, 135 );
		this.forbiddenForegroundColor = new Color( Display.getCurrent(), 120, 120, 120 );
		this.categoryColor = new Color(Display.getCurrent(), 153, 50, 204);

		this.consumesKeyWordsStyle = new Styler() {
			@Override
			public void applyStyles( TextStyle textStyle ) {
				textStyle.foreground = EMFPCStyledLabelProvider.this.consumesKeyWordForegroundColor;
			}
		};

		this.providesKeyWordsStyle = new Styler() {
			@Override
			public void applyStyles( TextStyle textStyle ) {
				textStyle.foreground = EMFPCStyledLabelProvider.this.providesKeyWordForegroundColor;
			}
		};

		this.propertiesStyle = new Styler() {
			@Override
			public void applyStyles( TextStyle textStyle ) {
				textStyle.font = EMFPCStyledLabelProvider.this.propertiesFont;
			}
		};

		// Images
		this.imageRegistry = new ImageRegistry();
		this.providesDesc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/provides_3.0.png" );
		this.consumesDesc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/consumes_3.0.png" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StyledCellLabelProvider
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.providesKeyWordForegroundColor != null )
			this.providesKeyWordForegroundColor.dispose();

		if( this.consumesKeyWordForegroundColor != null )
			this.consumesKeyWordForegroundColor.dispose();

		if( this.forbiddenForegroundColor != null )
			this.forbiddenForegroundColor.dispose();

		if( this.propertiesFont != null )
			this.propertiesFont.dispose();

		if (this.categoryColor != null) {
			this.categoryColor.dispose();
		}
		if (this.categoryFont != null) {
			this.categoryFont.dispose();
		}

		this.imageRegistry.dispose();
		super.dispose();
	}


	/**
	 * @param elementToMarkers the elementToMarkers to set
	 */
	public void setElementToMarkers( Map<EObject, List<IMarker>> elementToMarkers ) {
		this.elementToMarkers = elementToMarkers;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {
		return getStyledText( element ).getString();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getImage(java.lang.Object)
	 */
	@Override
	public Image getImage( Object element ) {

		Image image = null;
		if( element instanceof AbstractEndpoint ) {
			AbstractEndpoint endpoint = (AbstractEndpoint)element;
			// Get markers attached to this element
			int level = IStatus.OK;
			if( this.elementToMarkers != null ) {
				List<IMarker> markers = this.elementToMarkers.get( endpoint );
				if( markers != null )
					level = MarkerUtils.getMaximumSeverity( markers );
			}

			ImageDescriptor imageDesc = null;
			if (endpoint instanceof Consumes) {
				imageDesc = this.consumesDesc;
			} else if (endpoint instanceof Provides) {
				imageDesc = this.providesDesc;
			}
			if( level == IStatus.ERROR )
				image = this.imageRegistry.getErrorImage(imageDesc);
			else if( level == IStatus.WARNING )
				image = this.imageRegistry.getWarningImage(imageDesc);
			else
				image = this.imageRegistry.getBaseImage(imageDesc);
		} else if( this.providesDesc != null ) {
			image = this.imageRegistry.getBaseImage( this.providesDesc );
		}
		else {
			image = null;
		}

		return image;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider
	 * #getStyledText(java.lang.Object)
	 */
	@Override
	public StyledString getStyledText( Object element ) {
		StyledString styledString;
		if (element instanceof AbstractEndpoint) {
			styledString = getStyledText((AbstractEndpoint)element);
		} else if (element instanceof Element)
			styledString = getStyledText((Element) element);
		else if( element instanceof JbiBasicBean )
			styledString = getStyledText((JbiBasicBean) element);
		else
			styledString = new StyledString( "" );

		return styledString;
	}


	/**
	 * Gets the styled string from a DOM element.
	 * @param elt
	 * @return
	 */
	protected StyledString getStyledText( Element elt ) {

		StyledString styledString = new StyledString( "" );
		String name = DomUtils.getNodeName( elt );

		String itfName = elt.getAttribute( "interface-name" );
		itfName = NamespaceUtils.removeNamespaceElements( itfName );

		String srvName = elt.getAttribute( "service-name" );
		srvName = NamespaceUtils.removeNamespaceElements( srvName );

		String edptName = elt.getAttribute( "endpoint-name" );
		edptName = NamespaceUtils.removeNamespaceElements( edptName );

		if( "provides".equalsIgnoreCase( name )) {
			styledString.append( srvName.length() > 0 ? srvName : "?", this.propertiesStyle );
			styledString.append( " implements ", this.providesKeyWordsStyle );
			styledString.append( itfName.length() > 0 ? itfName : "?", this.propertiesStyle );
			styledString.append( " @ ", this.providesKeyWordsStyle );
			styledString.append( edptName.length() > 0 ? edptName : "?", this.propertiesStyle );
		}
		else if( "consumes".equalsIgnoreCase( name )) {
			styledString.append( srvName.length() > 0 ? srvName : "*", this.propertiesStyle );
			styledString.append( " implements ", this.consumesKeyWordsStyle );
			styledString.append( itfName.length() > 0 ? itfName : "?", this.propertiesStyle );
			styledString.append( " @ ", this.consumesKeyWordsStyle );
			styledString.append( edptName.length() > 0 ? edptName : "*", this.propertiesStyle );
		}

		return styledString;
	}

	protected StyledString getStyledText( AbstractEndpoint elt ) {

		StyledString styledString = new StyledString( "" );

		String itfName = elt.getInterfaceName().getLocalPart();
		String srvName = null;
		if (elt.getServiceName() != null) {
			srvName = elt.getServiceName().getLocalPart();
		}
		String edptName = elt.getEndpointName();

		Styler styler = null;
		if( elt instanceof Provides) {
			styler = this.providesKeyWordsStyle;
		} else if( elt instanceof Consumes) {
			styler = this.consumesKeyWordsStyle;
		}
		styledString.append(  srvName != null && srvName.length() > 0 ? srvName : "*", this.propertiesStyle );
		styledString.append( " implements ", styler );
		styledString.append( itfName.length() > 0 ? itfName : "?", this.propertiesStyle );
		styledString.append( " @ ", styler );
		styledString.append( edptName != null && edptName.length() > 0 ? edptName : "*", this.propertiesStyle );

		return styledString;
	}


	/**
	 * Gets the styled string from a {@link JbiBasicBean} instance.
	 * @param bean
	 * @return
	 */
	protected StyledString getStyledText( JbiBasicBean bean ) {

		StyledString styledString = new StyledString( "" );
		String srvName = null;
		if( bean.getServiceName() != null )
			srvName = bean.getServiceName().getLocalPart();
		if( srvName == null || srvName.trim().length() == 0 )
			srvName = "?";

		String itfName = null;
		if( bean.getInterfaceName() != null )
			itfName = bean.getInterfaceName().getLocalPart();
		if( itfName == null || itfName.trim().length() == 0 )
			itfName = "?";

		String edptName = bean.getEndpointName();
		if( edptName == null || edptName.trim().length() == 0 )
			edptName = "?";

		styledString.append( srvName.length() > 0 ? srvName : "?", this.propertiesStyle );
		styledString.append( " implements ", this.providesKeyWordsStyle );
		styledString.append( itfName.length() > 0 ? itfName : "?", this.propertiesStyle );
		styledString.append( " @ ", this.providesKeyWordsStyle );
		styledString.append( edptName.length() > 0 ? edptName : "?", this.propertiesStyle );

		return styledString;
	}
}
