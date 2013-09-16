/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsoucing.petals.repositories.explorer;

import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IRepositoryChangeListener {

	public void repositoryChanged( Repository repository );
	public void repositoryAdded( Repository repository );
	public void repositoryRemoved( Repository repository );
}
