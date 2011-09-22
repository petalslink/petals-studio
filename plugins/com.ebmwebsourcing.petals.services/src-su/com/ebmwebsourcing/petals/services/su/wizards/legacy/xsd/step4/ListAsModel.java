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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The class used as model for the table viewer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ListAsModel {

	/** The objects listening to the changes of instances. Any change is signaled to all the listeners. */
	private final Set<ListAsModelViewer> changeListeners = new HashSet<ListAsModelViewer>();
	/** The list of elements managed by this class. */
	private final ArrayList<StringModel> list = new ArrayList<StringModel> ();


	/**
	 * @param stringModel the StringModel to add.
	 */
	public void add( StringModel stringModel ) {
		this.list.add( this.list.size(), stringModel );

		Iterator<ListAsModelViewer> iterator = this.changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().add( stringModel );
	}

	/**
	 * @param stringModel the StringModel to remove.
	 */
	public void remove( StringModel stringModel ) {
		this.list.remove( stringModel );

		Iterator<ListAsModelViewer> iterator = this.changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().remove( stringModel );
	}

	/**
	 * Move an item down means increase its index by one.
	 * @param stringModel the StringModel to move.
	 */
	public void moveDown( StringModel stringModel ) {

		int index = this.list.indexOf( stringModel );
		if( index < this.list.size() - 2 ) {
			this.list.remove( index );
			this.list.add( index + 1, stringModel );
		}
		else if( index == this.list.size() - 2 ) {
			this.list.remove( index );
			this.list.add( stringModel );
		}

		Iterator<ListAsModelViewer> iterator = this.changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().moveDown( stringModel );
	}

	/**
	 * Move an item down means decrease its index by one.
	 * @param stringModel the StringModel to move.
	 */
	public void moveUp( StringModel stringModel ) {

		int index = this.list.indexOf( stringModel );
		if( index > 0 ) {
			this.list.remove( index );
			this.list.add( index - 1, stringModel );
		}

		Iterator<ListAsModelViewer> iterator = this.changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().moveUp( stringModel );
	}

	/**
	 * @param stringModel the StringModel to update.
	 */
	public void update( StringModel stringModel ) {
		Iterator<ListAsModelViewer> iterator = this.changeListeners.iterator();
		while (iterator.hasNext())
			iterator.next().update( stringModel );
	}

	/**
	 * @param viewer the listener to remove.
	 */
	public void removeChangeListener( ListAsModelViewer viewer ) {
		this.changeListeners.remove( viewer );
	}

	/**
	 * @param viewer the listener to add.
	 */
	public void addChangeListener( ListAsModelViewer viewer ) {
		this.changeListeners.add( viewer );
	}

	/**
	 * @return the list of objects managed by instances.
	 */
	public ArrayList<StringModel> getList() {
		return this.list;
	}

	/**
	 * The interface to implement to be able to listen for this class.
	 */
	public static interface ListAsModelViewer {
		/** A StringModel object was added to the model. */
		void add( StringModel stringModel );
		/** A StringModel object was removed from the model. */
		void remove( StringModel stringModel );
		/** A StringModel object already in the model was updated. */
		void update( StringModel stringModel );
		/** A StringModel object was moved up into the model. */
		void moveUp( StringModel stringModel );
		/** A StringModel object was moved down into the model. */
		void moveDown( StringModel stringModel );
	}

	/**
	 * The StringModel class, used as model smallest unit.
	 */
	public static class StringModel {
		/** A text value. */
		private String text;
		/** Whether this string is valid with respect to the use context. */
		private boolean valueValid = true;
		/** The associated error message if the value is invalid. */
		private String errorMsg = ""; //$NON-NLS-1$


		/**
		 * @param text
		 */
		public StringModel(String text) {
			if( text == null )
				this.text = ""; //$NON-NLS-1$
			else
				this.text = text;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return this.text;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * @return the valueValid
		 */
		public boolean isValueValid() {
			return this.valueValid;
		}

		/**
		 * @return the errorMsg
		 */
		public String getErrorMsg() {
			return this.errorMsg;
		}

		/**
		 * @param valueValid the valueValid to set
		 */
		public void setValueValid(boolean valueValid) {
			this.valueValid = valueValid;
		}

		/**
		 * @param errorMsg the errorMsg to set
		 */
		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		@Override
		public String toString() {
			return "StringModel: " + this.text + ((this.valueValid) ? "" : " - error = " + this.errorMsg ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}
}
