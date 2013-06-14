/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.refactoring;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;

/**
 * An object that holds information for the refactoring.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectRefactoringInfo {

	private IProject project;
	private String newName;
	private final Map<String,Boolean> options = new HashMap<String,Boolean> ();

	/**
	 * @return the project
	 */
	public IProject getProject() {
		return this.project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject( IProject project ) {
		this.project = project;
	}

	/**
	 * @return the newName
	 */
	public String getNewName() {
		return this.newName;
	}

	/**
	 * @param newName the newName to set
	 */
	public void setNewName( String newName ) {
		this.newName = newName;
	}

	/**
	 * @return the options
	 */
	public Map<String,Boolean> getOptions() {
		return this.options;
	}
}
