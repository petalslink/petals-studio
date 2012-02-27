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

package com.ebmwebsourcing.petals.services.su.editor.extensibility;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class InitializeModelExtensionCommand extends AbstractCommand {

	private static AtomicInteger ID_COUNTER = new AtomicInteger( 1 );

	private final int id;
	private Set<EStructuralFeature> targetFeatures;
	private final EPackage extensionPackage;
	private final AbstractExtensibleElement element;


	/**
	 * Constructor.
	 * @param extensionPackage
	 * @param element
	 */
	public InitializeModelExtensionCommand( EPackage extensionPackage, AbstractExtensibleElement element ) {
		this.extensionPackage = extensionPackage;
		this.element = element;
		this.id = ID_COUNTER.getAndIncrement();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand
	 * #prepare()
	 */
	@Override
	public boolean prepare() {
		initializeFeatures();
		return needsAdditionalAttributes();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command
	 * #execute()
	 */
	@Override
	public void execute() {

		/* Woooooooow: some explanations are required here!
		 * ================================================
		 *
		 * All the features have been initialized (they were found from the extension-point and EMF registry).
		 * The command is executable if and only if they are elements in the EObject that can be associated with
		 * these structural features.
		 *
		 * That's what the command do: initialize features from the mixed content.
		 * Problem: if we just go through the features in the discovering order, the elements will
		 * be written in this same order. This is wrong because the CDK features must ALWAYS be written
		 * before the component one.
		 *
		 *  So, before invoking this command, the extension packages must be sorted.
		 *  The CDK package must be initialized first.
		 */

		// To follow insertion (and thus write) order, set debug to true.
		// Set it to false before the releases. As its name says it, it is for debug purpose.
		boolean debug = false;
		if( debug )
			System.out.println( "Command " + this.id );

		// Do not insert twice the same feature - change the insertion / write order
		Set<String> alreadySet = new HashSet<String> ();


		// Once sorting is done, we can iterate and initialize the feature values
		// Care must be taken because
		for( EStructuralFeature targetFeature : this.targetFeatures ) {
			Entry entry = getMatchingGroupEntry( targetFeature );
			if( entry == null )
				continue;

			String fName = ExtendedMetaData.INSTANCE.getName( targetFeature);
			String fNs = ExtendedMetaData.INSTANCE.getNamespace( targetFeature);
			String id = fName + " - " + fNs;
			if( alreadySet.contains( id ))
				continue;

			alreadySet.add( id );

			// The value is null
			this.element.getGroup().remove(entry);
			Object value = getActualValue( entry.getValue());
			String display = "Inserting feature: " + id + "  (" + value + ")";

			if( value == null ) {
				this.element.eSet(targetFeature, null);
				if( debug )
					System.out.println( display );
			}

			// The value is not null: set the feature
			else {
				try {
					if (value instanceof String && targetFeature instanceof EAttribute) {
						EDataType expectedType = ((EAttribute) targetFeature).getEAttributeType();
						String instanceClassName = expectedType.getInstanceClassName().toLowerCase();

						if( expectedType.equals( EcorePackage.Literals.EINT )
								|| "int".equals( instanceClassName )
								|| "java.lang.integer".equals( instanceClassName )) {
							this.element.eSet(targetFeature, Integer.valueOf((String)value));
							if( debug )
								System.out.println( display );

						} else if( expectedType.equals( EcorePackage.Literals.ELONG )
									|| "long".equals( instanceClassName )
									|| "java.lang.long".equals( instanceClassName )) {
								this.element.eSet(targetFeature, Long.valueOf((String)value));
								if( debug )
									System.out.println( display );

						} else if( instanceClassName.equals( "javax.xml.namespace.qname" )) {

							// Extract the QName value...
							String[] parts = ((String) value).split( ":" );
							String ns = null, name = null;

							if( parts.length == 1 ) {
								name = parts[ 0 ];

							} else if( parts.length == 2 ) {
								ns = parts[ 0 ];
								name = parts[ 1 ];

							} else {
								PetalsServicesPlugin.log( "Found invalid QName while intializing the model extensions.", IStatus.ERROR );
							}


							// ... and resolve it
							QName newValue = null;
							EMap<String,String> map = JbiXmlUtils.findPrefixMap( this.element );
							if( map == null ) {
								PetalsServicesPlugin.log( "Could not find the prefix map while intializing the model extensions.", IStatus.ERROR );

							} else if( name != null ) {
								if( ns != null )
									ns = map.get( ns );

								newValue = ns != null ? new QName( ns, name ) : new QName( name );
							}

							this.element.eSet( targetFeature, newValue );
							if( debug )
								System.out.println( display );

						} else if( expectedType instanceof EEnum ) {
							EEnum eEnum = (EEnum) expectedType;
							EEnumLiteral literal = eEnum.getEEnumLiteralByLiteral((String)value);
							this.element.eSet(targetFeature, literal.getInstance());
							if( debug )
								System.out.println( display );

						} else if (expectedType.getInstanceClass().equals( boolean.class )) {
							this.element.eSet(targetFeature, Boolean.valueOf((String)value));
							if( debug )
								System.out.println( display );

						} else {
							this.element.eSet(targetFeature, value);
							if( debug )
								System.out.println( display );
						}

					} else {
						this.element.eSet(targetFeature, value);
						if( debug )
							System.out.println( display );
					}

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR, "A model feature could not be initialized. Type = " + ((EAttribute) targetFeature).getEAttributeType().getInstanceClassName());
				}
			}
		}
	}


	/**
	 * Creates the target features.
	 * <p>
	 * This methods adds all the features from the extensions in the target features set.
	 * </p>
	 * <p>
	 * See PETALSSTUD-245<br />
	 * Checks whether these features are part of the right parent (provides or consumes).<br />
	 * This method guarantees that we do not initialize consumes features in a provides.
	 * And vice-versa.
	 * </p>
	 */
	public void initializeFeatures() {

		if( this.targetFeatures != null  )
		 return;

		String forbiddenWord = this.element instanceof Provides ? "consumes" : "provides";
		this.targetFeatures = new LinkedHashSet<EStructuralFeature>();
		if( this.extensionPackage == null )
			return;

		eClassLoop: for( EClassifier classifier : this.extensionPackage.getEClassifiers()) {
			if( !( classifier instanceof EClass ))
				continue;

			// Provides or consumes: do not process them all, only one of them
			EClass eClass = (EClass) classifier;
			for( EClass ec : eClass.getEAllSuperTypes()) {
				if( ec.getName().toLowerCase().contains( forbiddenWord )) {
					continue eClassLoop;
				}
			}

			// Add the features
			for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
				((EStructuralFeatureImpl) feature).setFeatureID( -1 );
				this.targetFeatures.add( feature );
			}
		}
	}


	/**
	 * @return
	 */
	private boolean needsAdditionalAttributes() {
		for (EStructuralFeature targetFeature : this.targetFeatures) {
			Entry actualCurrentEntry = getMatchingGroupEntry(targetFeature);
			if( actualCurrentEntry != null )
				return true;
		}

		return false;
	}


	/**
	 * @param value
	 * @return
	 */
	private Object getActualValue( Object value ) {
		return value instanceof AnyType ? ((AnyType)value).getMixed().get(0).getValue() : value;
	}


	/**
	 * @param referenceFeature
	 * @return
	 */
	private Entry getMatchingGroupEntry( EStructuralFeature referenceFeature ) {

		for (FeatureMap.Entry entry : this.element.getGroup()) {
			String actualName = ExtendedMetaData.INSTANCE.getName( entry.getEStructuralFeature());
			String actualNamespace = ExtendedMetaData.INSTANCE.getNamespace(entry.getEStructuralFeature());

			String referenceName = ExtendedMetaData.INSTANCE.getName( referenceFeature );
			String referenceNamespace = ExtendedMetaData.INSTANCE.getNamespace( referenceFeature );

			boolean sameName = actualName.equals( referenceName )
					|| actualName.equals( referenceFeature.getName())
					|| entry.getEStructuralFeature().getName().equals( referenceName )
					|| entry.getEStructuralFeature().getName().equals( referenceFeature.getName());

			if( actualNamespace.equals( referenceNamespace ) && sameName )
				return entry;
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command
	 * #redo()
	 */
	@Override
	public void redo() {
		execute();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand
	 * #canUndo()
	 */
	@Override
	public boolean canUndo() {
		return false;
	}
}
