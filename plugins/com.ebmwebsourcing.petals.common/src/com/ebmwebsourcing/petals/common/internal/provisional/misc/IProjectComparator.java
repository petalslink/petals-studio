/******************************************************************************
 * Copyright (c) 2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.misc;

import java.util.Comparator;

import org.eclipse.core.resources.IProject;

/**
 * A comparator for IProjects.
 * @author Vincent Zurczak - Linagora
 */
public class IProjectComparator implements Comparator<IProject> {

	@Override
	public int compare( IProject o1, IProject o2 ) {
		return o1.getName().compareTo( o2.getName());
	}
}
