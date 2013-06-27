/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties;

import java.util.Collection;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Vincent Zurczak - Linagora
 */
public class TestAbstractModel {

	@Test
	public void testTypePatterns() {

		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: long" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: double" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: float" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: boolean" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: list" ));

		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: string" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: String" ));
		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: string, nullable" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: string, required" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: string, required, nullable" ));
		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: string, nullable, required" ));

		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: enumeration { item1, item2 }" ));
		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: enumeration { item1 }, nullable" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: enumeration {item1}, required" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: enumeration {item1}, required, nullable" ));

		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: enumeration {}, required" ));
		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: " ));

		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer[1,2]" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer ]1,3]" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer ] 1, 5 [" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer [ 1 , 5 [" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer [ 1 ,[" ));
		Assert.assertEquals( true, AbstractModel.checkTypePattern( "Type: integer [,5[" ));
		Assert.assertEquals( false, AbstractModel.checkTypePattern( "Type: integer [,]" ));
	}


	@Test
	public void testIsRequired() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: string";
		Assert.assertFalse( model.isRequired( "any" ));

		model.typeDecl = "Type: string, required";
		Assert.assertTrue( model.isRequired( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required";
		Assert.assertTrue( model.isRequired( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }";
		Assert.assertFalse( model.isRequired( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [, required";
		Assert.assertTrue( model.isRequired( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [";
		Assert.assertFalse( model.isRequired( "any" ));
	}


	@Test
	public void testIsNullable() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: string";
		Assert.assertFalse( model.isNullable( "any" ));

		model.typeDecl = "Type: string, required, nullable";
		Assert.assertTrue( model.isNullable( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required, nullable";
		Assert.assertTrue( model.isNullable( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required";
		Assert.assertFalse( model.isNullable( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [, required, nullable";
		Assert.assertTrue( model.isNullable( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [";
		Assert.assertFalse( model.isNullable( "any" ));
	}


	@Test
	public void testGetType() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: string";
		Assert.assertEquals( SupportedTypes.STRING, model.getType( "any" ));

		model.typeDecl = "Type: string, required, nullable";
		Assert.assertEquals( SupportedTypes.STRING, model.getType( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required, nullable";
		Assert.assertEquals( SupportedTypes.ENUMERATION, model.getType( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required";
		Assert.assertEquals( SupportedTypes.ENUMERATION, model.getType( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [, required, nullable";
		Assert.assertEquals( SupportedTypes.INTEGER, model.getType( "any" ));

		model.typeDecl = "Type: integer [ 5, 10 [";
		Assert.assertEquals( SupportedTypes.INTEGER, model.getType( "any" ));

		model.typeDecl = "Type: long, required";
		Assert.assertEquals( SupportedTypes.LONG, model.getType( "any" ));

		model.typeDecl = "Type: float, required";
		Assert.assertEquals( SupportedTypes.FLOAT, model.getType( "any" ));

		model.typeDecl = "Type: double, required";
		Assert.assertEquals( SupportedTypes.DOUBLE, model.getType( "any" ));

		model.typeDecl = "Type: boolean, required";
		Assert.assertEquals( SupportedTypes.BOOLEAN, model.getType( "any" ));

		model.typeDecl = "Type: list, required";
		Assert.assertEquals( SupportedTypes.LIST, model.getType( "any" ));
	}


	@Test
	public void testGetEnumeration() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: string, required, nullable";
		Assert.assertNull( model.getEnumeration( "any" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }, required, nullable";
		Collection<String> list = model.getEnumeration( "any" );
		Assert.assertNotNull( list );
		Assert.assertEquals( 2, list.size());
		Assert.assertTrue( list.contains( "12" ));
		Assert.assertTrue( list.contains( "17" ));

		model.typeDecl = "Type: enumeration{ 12 ; 17 }";
		list = model.getEnumeration( "any" );
		Assert.assertNotNull( list );
		Assert.assertEquals( 2, list.size());
		Assert.assertTrue( list.contains( "12" ));
		Assert.assertTrue( list.contains( "17" ));

		model.typeDecl = "Type: enumeration{}";
		list = model.getEnumeration( "any" );
		Assert.assertNotNull( list );
		Assert.assertEquals( 0, list.size());

		model.typeDecl = "Type: enumeration { 12 }";
		list = model.getEnumeration( "any" );
		Assert.assertNotNull( list );
		Assert.assertEquals( 1, list.size());
		Assert.assertTrue( list.contains( "12" ));

		model.typeDecl = "Type: enumeration { InOnly; RobustInOnly }";
		list = model.getEnumeration( "any" );
		Assert.assertNotNull( list );
		Assert.assertEquals( 2, list.size());
		Assert.assertTrue( list.contains( "InOnly" ));
		Assert.assertTrue( list.contains( "RobustInOnly" ));
	}


	@Test
	public void testGetRange() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: string, required, nullable";
		Assert.assertNull( model.getRange( "any" ));

		model.typeDecl = "Type: integer, required, nullable";
		Assert.assertNull( model.getRange( "any" ));

		model.typeDecl = "Type: integer";
		Assert.assertNull( model.getRange( "any" ));

		model.typeDecl = "Type: integer [ 5,10 ]";
		IntegerRange range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( 5, range.getMin());
		Assert.assertEquals( 10, range.getMax());
		Assert.assertEquals( true, range.isMinIncluded());
		Assert.assertEquals( true, range.isMaxIncluded());

		model.typeDecl = "Type: integer [ 5,10 [";
		range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( 5, range.getMin());
		Assert.assertEquals( 10, range.getMax());
		Assert.assertEquals( true, range.isMinIncluded());
		Assert.assertEquals( false, range.isMaxIncluded());

		model.typeDecl = "Type: integer ] 5,10 [";
		range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( 5, range.getMin());
		Assert.assertEquals( 10, range.getMax());
		Assert.assertEquals( false, range.isMinIncluded());
		Assert.assertEquals( false, range.isMaxIncluded());

		model.typeDecl = "Type: integer ] 5,100 ]";
		range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( 5, range.getMin());
		Assert.assertEquals( 100, range.getMax());
		Assert.assertEquals( false, range.isMinIncluded());
		Assert.assertEquals( true, range.isMaxIncluded());

		model.typeDecl = "Type: integer ] 5, ]";
		range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( 5, range.getMin());
		Assert.assertEquals( -1, range.getMax());
		Assert.assertEquals( false, range.isMinIncluded());
		Assert.assertEquals( true, range.isMaxIncluded());

		model.typeDecl = "Type: integer [ , 80 ]";
		range = model.getRange( "any" );
		Assert.assertNotNull( range );
		Assert.assertEquals( -1, range.getMin());
		Assert.assertEquals( 80, range.getMax());
		Assert.assertEquals( true, range.isMinIncluded());
		Assert.assertEquals( true, range.isMaxIncluded());
	}


	@Test
	public void testGetQNameValue() {

		CustomAbstractModel model = new CustomAbstractModel();
		model.typeDecl = "Type: QName";
		model.trimmedValue = "{http://lol}Cool";

		QName qname = model.getQNameValue( "any" );
		Assert.assertNotNull( qname );
		Assert.assertEquals( "http://lol", qname.getNamespaceURI());
		Assert.assertEquals( "Cool", qname.getLocalPart());

		model.trimmedValue = "{  http://lol }  Cool  ";
		qname = model.getQNameValue( "any" );
		Assert.assertNotNull( qname );
		Assert.assertEquals( "http://lol", qname.getNamespaceURI());
		Assert.assertEquals( "Cool", qname.getLocalPart());

		model.trimmedValue = "  Cool  ";
		qname = model.getQNameValue( "any" );
		Assert.assertNotNull( qname );
		Assert.assertEquals( "", qname.getNamespaceURI());
		Assert.assertEquals( "Cool", qname.getLocalPart());

		model.trimmedValue = "";
		qname = model.getQNameValue( "any" );
		Assert.assertNull( qname );

		model.typeDecl = "Type: String";
		qname = model.getQNameValue( "any" );
		Assert.assertNull( qname );
	}


	/**
	 * A class used to perform tests on an abstract model.
	 */
	private static class CustomAbstractModel extends AbstractModel {
		public String typeDecl;
		public String trimmedValue;

		@Override
		public String findTypeDeclaration( String property ) {
			return this.typeDecl;
		}

		@Override
		public String getTrimmedPropertyValue( String property ) {
			return this.trimmedValue;
		}
	}
}
