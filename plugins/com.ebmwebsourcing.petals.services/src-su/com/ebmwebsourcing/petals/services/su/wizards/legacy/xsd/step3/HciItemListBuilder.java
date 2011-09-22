/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.ErrorReporter;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.DependencyAnnotation;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.PetalsXsdAnnotations;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.Attribute;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.BaseType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.Element;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.EnumType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.ExtensionType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.RefElement;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.SequenceType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder.Vertex;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class HciItemListBuilder {

	private final Map<String, List<HciItem>> hciItems = new HashMap<String, List<HciItem>> ();


	public HciItemListBuilder( Vertex rootVertex ) {
		buildHciItems( rootVertex, new ArrayList<Vertex> ());
	}


	/**
	 * Build the list of abstract HCI items for the "currentVertex".
	 * @param currentVertex the currently visited vertex.
	 * @param hciItems the map which associate a vertex with a list of HciItems.
	 * The key is the vertex id and the value is the list of HciItems. This list might be empty but not null.
	 * @param inVisit the list of vertices currently in visitation.
	 * Used in recursive calls to avoid cycles.
	 */
	private void buildHciItems( Vertex currentVertex, List<Vertex> inVisit ) {

		// Enumeration Type
		if( EnumType.class.equals( currentVertex.getClass())) {
			HciItem item = new HciItem();
			item.setType( HciType.ENUM );
			item.setValues(((EnumType) currentVertex ).enumValues );

			List<HciItem> items = new ArrayList<HciItem>();
			items.add( item );
			this.hciItems.put( currentVertex.id, items );
			return;
		}

		// Base Type
		else if( BaseType.class.equals( currentVertex.getClass())) {
			HciItem item = new HciItem();
			if( XsdDependencyGraphBuilder.INTEGER_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.INTEGER );
			else if( XsdDependencyGraphBuilder.DOUBLE_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.DOUBLE );
			else if( XsdDependencyGraphBuilder.BOOLEAN_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.BOOLEAN );
			else if( XsdDependencyGraphBuilder.STRING_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.STRING );
			else if( XsdDependencyGraphBuilder.URI_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.URI );
			else if( XsdDependencyGraphBuilder.QNAME_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.QNAME );
			else if( XsdDependencyGraphBuilder.FILE_BASE_TYPE.equals( currentVertex ))
				item.setType( HciType.FILE );
			else
				item.setType( HciType.UNRESOLVED );

			List<HciItem> items = new ArrayList<HciItem>();
			items.add( item );
			this.hciItems.put( currentVertex.id, items );
			return;
		}

		// Type made up of elements (sequence)
		// + Extension Type
		else if( SequenceType.class.equals( currentVertex.getClass())
					|| ExtensionType.class.equals( currentVertex.getClass())) {

			List<HciItem> items = new ArrayList<HciItem>();
			inVisit.add( currentVertex );

			for( Vertex depVertex : currentVertex.dependencies ) {
				// Avoid cycles in the graph by skipping redundant vertices.
				if( inVisit.contains( depVertex )) {
					String errorMsg =
						NLS.bind(
									Messages.XsdExtractorForHci_5,
									new Object[] { depVertex.id, currentVertex.id });

					ErrorReporter.INSTANCE.registerError( "XSD-parsing", errorMsg, IStatus.WARNING, null );
					continue;
				}

				buildHciItems( depVertex, inVisit );	// recursive call
				List<HciItem> depItems = this.hciItems.get( depVertex.id );
				if( depItems != null )
					items.addAll( depItems );
			}

			inVisit.remove( currentVertex );

			// Duplicate the list items: we do not want to modify references from other vertices.
			items = duplicateHciItemList( items );
			this.hciItems.put( currentVertex.id, items );
			return;
		}

		// Element
		else if( Element.class.equals( currentVertex.getClass())) {
			List<HciItem> items = new ArrayList<HciItem>();
			inVisit.add( currentVertex );

			for( Vertex depVertex : currentVertex.dependencies ) {
				// Avoid cycles in the graph by skipping redundant vertices.
				if( inVisit.contains( depVertex )) {
					String errorMsg =
						NLS.bind(
									Messages.XsdExtractorForHci_5,
									new Object[] { depVertex.id, currentVertex.id });

					ErrorReporter.INSTANCE.registerError( "XSD-parsing", errorMsg, IStatus.WARNING, null );
					continue;
				}

				buildHciItems( depVertex, inVisit );	// recursive call
				List<HciItem> depItems = this.hciItems.get( depVertex.id );
				if( depItems != null )
					items.addAll( depItems );
			}

			inVisit.remove( currentVertex );

			// Duplicate the list items: we do not want to modify references from other vertices.
			items = duplicateHciItemList( items );
			Collection<DependencyAnnotation> annotations = ((Element) currentVertex ).annotations;
			if( PetalsXsdAnnotations.containsNoHciAnnotation( annotations )) {
				for( HciItem item : items )
					item.setVisible( false );
			}

			// If there is only one HCI element, update its properties (simple type, e.g. xs:string...).

			/* PATCH: comment the case where there is only one element.
			 * 
			 * At the beginning, it was supposed than when we meet a situation like
			 * 
			 * <xs:element name="provides" type="jbi:Provides">
			 * <xs:complexType name="Provides">
			 * 		<xs:sequence>
			 * 			<xs:element name="whatever" type="anyType" />
			 * 		</xs:sequence>
			 * </xs:complexType>
			 * 
			 * the XML result would write
			 * 
			 * <jbi:provides>myValueOfType "anyType"</jbi:provides>
			 * 
			 * rather than
			 * 
			 * <jbi:whatever>myValueOfType "anyType"</jbi:whatever>
			 * 
			 * 
			 * Commenting the next lines will make the second choice as effective.
			 */

			List<HciItem> itemsWithoutAttributes = getHciItemsWithoutAttributes( items );
			if( itemsWithoutAttributes.size() == 1 ) {
				itemsWithoutAttributes.get( 0 ).setXsdName(((Element) currentVertex ).name );
				//itemsWithoutAttributes.get( 0 ).setLabelName(((Element) currentVertex ).name );
				//itemsWithoutAttributes.get( 0 ).setOptional(((Element) currentVertex ).optional );
				itemsWithoutAttributes.get( 0 ).setDefaultValue(((Element) currentVertex ).defaultValue );
				itemsWithoutAttributes.get( 0 ).setNillable(((Element) currentVertex ).isNillable );
				itemsWithoutAttributes.get( 0 ).addAnnotations(((Element) currentVertex).annotations );

				String currentLocale = Locale.getDefault().getLanguage();
				if( currentLocale != null
							&& ((Element) currentVertex ).documentations.get( currentLocale ) != null )
					itemsWithoutAttributes.get( 0 ).setTooltip(((Element) currentVertex ).documentations.get( currentLocale ));
			}


			// Find attribute HCI elements (their label and XSD names are made up of two words) and update their name.
			for( HciItem attrItem : items ) {
				String labelName = attrItem.getLabelName();
				if( labelName != null ) {
					attrItem.setLabelName(
								labelName.replaceAll( "%attribute% ", ((Element) currentVertex ).name + " " )); //$NON-NLS-1$ //$NON-NLS-2$
					attrItem.setXsdName(
								labelName.replaceAll( "%attribute% ", ((Element) currentVertex ).name + " " )); //$NON-NLS-1$ //$NON-NLS-2$
				}
				else	// should be called only once
					attrItem.setLabelName(((Element) currentVertex ).name );
			}

			this.hciItems.put( currentVertex.id, items );
			return;
		}

		// Attribute
		else if( Attribute.class.equals( currentVertex.getClass())) {
			List<HciItem> items = new ArrayList<HciItem>();
			inVisit.add( currentVertex );

			for( Vertex depVertex : currentVertex.dependencies ) {
				// Avoid cycles in the graph by skipping redundant vertices.
				if( inVisit.contains( depVertex )) {
					String errorMsg =
						NLS.bind(
									Messages.XsdExtractorForHci_5,
									new Object[] { depVertex.id, currentVertex.id });

					ErrorReporter.INSTANCE.registerError( "XSD-parsing", errorMsg, IStatus.WARNING, null );
					continue;
				}

				buildHciItems( depVertex, inVisit );	// recursive call
				List<HciItem> depItems = this.hciItems.get( depVertex.id );
				if( depItems != null )
					items.addAll( depItems );
			}

			inVisit.remove( currentVertex );

			// Duplicate the list items: we do not want to modify references from other vertices.
			items = duplicateHciItemList( items );
			Collection<DependencyAnnotation> annotations = ((Attribute) currentVertex ).annotations;
			if( PetalsXsdAnnotations.containsNoHciAnnotation( annotations )) {
				for( HciItem item : items )
					item.setVisible( false );
			}

			if( items.size() == 1 ) {
				items.get( 0 ).setXsdName( "%attribute% " + ((Attribute) currentVertex ).name ); //$NON-NLS-1$
				items.get( 0 ).setLabelName( "%attribute% " + ((Attribute) currentVertex ).name ); //$NON-NLS-1$
				items.get( 0 ).setOptional(((Attribute) currentVertex ).optional );
				items.get( 0 ).setDefaultValue(((Attribute) currentVertex ).defaultValue );
				items.get( 0 ).addAnnotations(((Attribute) currentVertex).annotations );

				String currentLocale = Locale.getDefault().getLanguage();
				if( currentLocale != null
							&& ((Attribute) currentVertex ).documentations.get( currentLocale ) != null )
					items.get( 0 ).setTooltip(((Attribute) currentVertex ).documentations.get( currentLocale ));
			}

			this.hciItems.put( currentVertex.id, items );
			return;
		}

		// Referencer Element
		else if( RefElement.class.equals( currentVertex.getClass())) {
			List<HciItem> items = new ArrayList<HciItem>();
			inVisit.add( currentVertex );

			for( Vertex depVertex : currentVertex.dependencies ) {
				// Avoid cycles in the graph by skipping redundant vertices.
				if( inVisit.contains( depVertex )) {
					String errorMsg =
						NLS.bind(
									Messages.XsdExtractorForHci_5,
									new Object[] { depVertex.id, currentVertex.id });

					ErrorReporter.INSTANCE.registerError( "XSD-parsing", errorMsg, IStatus.WARNING, null );
					continue;
				}

				buildHciItems( depVertex, inVisit );	// recursive call
				List<HciItem> depItems = this.hciItems.get( depVertex.id );
				if( depItems != null )
					items.addAll( depItems );
			}

			inVisit.remove( currentVertex );

			// Duplicate the list items: we do not want to modify references from other vertices.
			items = duplicateHciItemList( items );
			Collection<DependencyAnnotation> annotations = ((RefElement) currentVertex ).annotations;
			if( PetalsXsdAnnotations.containsNoHciAnnotation( annotations )) {
				for( HciItem item : items )
					item.setVisible( false );
			}

			if( items.size() == 1 ) {
				items.get( 0 ).setXsdName(((RefElement) currentVertex ).name );
				items.get( 0 ).setLabelName(((RefElement) currentVertex ).name );
				items.get( 0 ).setOptional(((RefElement) currentVertex ).optional );
				items.get( 0 ).setMinOccurs(((RefElement) currentVertex ).minOccurs );
				items.get( 0 ).setMaxOccurs(((RefElement) currentVertex ).maxOccurs );
				items.get( 0 ).addAnnotations(((RefElement) currentVertex).annotations );

				if( ((RefElement) currentVertex ).isNillable )
					items.get( 0 ).setNillable( true );
				if( ((RefElement) currentVertex ).defaultValue != null )
					items.get( 0 ).setDefaultValue(((RefElement) currentVertex ).defaultValue );

				String currentLocale = Locale.getDefault().getLanguage();
				if( currentLocale != null
							&& ((RefElement) currentVertex ).documentations.get( currentLocale ) != null )
					items.get( 0 ).setTooltip(((RefElement) currentVertex ).documentations.get( currentLocale ));
			}

			this.hciItems.put( currentVertex.id, items );
			return;
		}
	}


	/**
	 * Duplicate a list of HciItem and duplicate the items of this list.
	 * Let L1 be the argument list.
	 * L1 contains items i1 and i2.
	 * 
	 * Then, this method will return a list L2,
	 * L2 different from L1,
	 * which contains i3 and i4, where i3.equals( i1 ) = true and i4.equals( i2 ) = true.
	 * 
	 * @param items a list of HciItems, which can be empty but not null.
	 * @return a duplicate list which contains duplicates of the argument list items.
	 */
	private List<HciItem> duplicateHciItemList( List<HciItem> items ) {
		List<HciItem> result = new ArrayList<HciItem> ();
		for( HciItem hciItem : items ) {
			HciItem newHciItem = new HciItem( hciItem );
			result.add( newHciItem );
		}
		return result;
	}


	/**
	 * Extract HCI items which are not HCI items from attributes.
	 * HCI items of attributes are characterized by a label name made up of two words.
	 * @param items a list of HciItems, which can be empty but not null.
	 * @return a list which contains the HCI items of the argument which are not attribute's.
	 */
	private List<HciItem> getHciItemsWithoutAttributes( List<HciItem> items ) {
		List<HciItem> result = new ArrayList<HciItem> ();
		for( HciItem hciItem : items ) {
			if( hciItem.getLabelName() == null
						|| hciItem.getLabelName().split( " " ).length == 1 ) //$NON-NLS-1$
				result.add( hciItem );
		}
		return result;
	}


	/**
	 * Print a list of abstract HCI items.
	 * @param hciItems the list of HCI items to print.
	 */
	public void printAbstractHci( List<HciItem> hciItems ) {
		for( HciItem hciItem : hciItems ) {
			System.out.println( hciItem.toString());
		}

		if( hciItems.isEmpty())
			System.out.println( "HCI list is empty." ); //$NON-NLS-1$
	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<HciItem> get( String id ) {
		return this.hciItems.get( id );
	}
}
