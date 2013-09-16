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
 
package com.ebmwebsourcing.petals.services.cdk;

import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class CDK5Description extends ComponentVersionDescription {

	@Override
	public boolean isBc() {
		return false;
	}

	@Override
	public String getComponentName() {
		return "CDK 5.0";
	}

	@Override
	public String getComponentAlias() {
		return "CDK 5.0";
	}

	@Override
	public String getComponentAnnotation() {
		return "CDK 5.0";
	}

	@Override
	public String getProvideDescription() {
		return null;
	}

	@Override
	public String getConsumeDescription() {
		return null;
	}

	@Override
	public List<PetalsKeyWords> getKeyWords() {
		return null;
	}

	@Override
	public String getComponentVersion() {
		return "CDK 5.0";
	}

	@Override
	public boolean isProvide() {
		return false;
	}

	@Override
	public boolean isConsume() {
		return false;
	}

	@Override
	public boolean isProxy() {
		return false;
	}

}
