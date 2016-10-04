/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.jsr181.v15;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.services.jsr181.v11.Jsr181ProvidesWizard11;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;

/**
 * @author Victor Noel - Linagora
 */
public class Jsr181ProvidesWizard15 extends Jsr181ProvidesWizard11 {

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new Jsr181Description15();
	}
	
	@Override
	public List<MavenBean> getAdditionalMavenDependencies() {

		List<MavenBean> result = new ArrayList<MavenBean> ();
		MavenBean bean = new MavenBean();
		bean.setArtifactId( "petals-se-jsr181-library" );
		bean.setGroupId( "org.ow2.petals" );
		bean.setVersion( "1.5.2" );

		result.add( bean );
		return result;
	}
}
