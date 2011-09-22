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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.swt;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;

import com.ebmwebsourcing.petals.services.su.wizards.legacy.validation.AbstractWizardValidator;

/**
 * Beware to not initialize <i>value</i> when creating the widgets.
 * If it is null, it must return null, unless the user modified the value in the widget.
 * 
 * @param <T>
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractWizardWidget<T> {

	/**
	 * The value maintained by this class.
	 * <p>It must be initialized by the constructor.</p>
	 */
	private T value;

	/**
	 * A collection of {@link #AbstractWizardValidator} to validate {@link #value}.
	 */
	protected final Collection<AbstractWizardValidator<T>> validators = new ArrayList<AbstractWizardValidator<T>> ();

	/**
	 * A collection of {@link #AbstractWizardValidator} to validate {@link #value}.
	 */
	protected final Collection<AbstractWizardListener> listeners = new ArrayList<AbstractWizardListener> ();

	/**
	 * An object to keep as data.
	 */
	protected Object data;


	protected String labelText, labelTooltip;

	protected boolean isOptional = false;
	private boolean emptyValueAllowed = false;



	/*
	 * CONSTRUCTOR
	 */


	/**
	 * The super constructor provides an initial value for this class.
	 * <p>
	 * If the given object is null, an exception is raised.
	 * An new empty object should be passed by default.
	 * </p>
	 * 
	 * @param initialValue the initial value of the element maintained by this class.
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param isOptional
	 */
	protected AbstractWizardWidget(
				T initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional ) {

		this.value = initialValue;
		this.labelTooltip = labelTooltip;
		this.isOptional = isOptional;
		this.labelText = createLabel( baseLabel, isOptional, labelSuffix );
	}


	/**
	 * The super constructor provides an initial value for this class.
	 * <p>
	 * If the given object is null, an exception is raised.
	 * An new empty object should be passed by default.
	 * </p>
	 * 
	 * @param initialValue the initial value of the element maintained by this class.
	 * @param parent
	 * @param label
	 * @param isOptional
	 */
	protected AbstractWizardWidget(
				T initialValue, Composite parent,
				String label, String labelTooltip, boolean isOptional ) {

		this.value = initialValue;
		this.labelTooltip = labelTooltip;
		this.isOptional = isOptional;
		this.labelText = ( label != null ? label : "" ) + ( isOptional ? " (optional)" : "" ) + ": ";
	}


	/*
	 * INHERITED
	 */


	/**
	 * Add an instance of {@link AbstractWizardValidator} to validate the value of this class.
	 * @param validator the validator to add
	 */
	public void addValidator( AbstractWizardValidator<T> validator ) {

		if( validator == null )
			throw new NullPointerException();

		Class<?> vClass = validator.getGenericType();
		Class<?> valueClass = this.value.getClass();
		if(  !vClass.isAssignableFrom( valueClass ))
			throw new ClassCastException();

		this.validators.add( validator );
	}


	/**
	 * Remove an instance of {@link AbstractWizardValidator} from the validators of this instance.
	 * @param validator the validator to remove
	 */
	public void removeValidator( AbstractWizardValidator<T> validator ) {
		this.validators.remove( validator );
	}


	/**
	 * Add an instance of {@link Listener} to notify the value of this class has changed.
	 * @param listener the listener to add
	 */
	public void addListener( AbstractWizardListener listener ) {

		if( listener == null )
			throw new NullPointerException();

		this.listeners.add( listener );
	}


	/**
	 * Remove an instance of {@link Listener} from the listeners of this instance.
	 * @param listener the listener to remove
	 */
	public void removeListener( AbstractWizardListener listener ) {
		this.listeners.remove( listener );
	}


	/**
	 * Equivalent to {@link #validate(Object)} where the argument is the 'value' field.
	 * @return
	 */
	public String validate() {
		return validate( this.value );
	}


	/**
	 * Validate the value with the registered validators, in the order in which they were added.
	 * <p>This method also checks if the value is empty and if this empty value is allowed or not.</p>
	 * 
	 * @param value
	 * @return <code>null</code> if the validation succeeds, an error message otherwise
	 */
	public String validate( T value ) {

		if( ! this.emptyValueAllowed && isEmptyValue()) {
			String errorName = this.labelText.toLowerCase().replaceAll( ":(\\s)+", "" );
			return "You have to provide the \"" + errorName + "\".";
		}

		String result = null;
		for( AbstractWizardValidator<T> validator : this.validators ) {
			result = validator.validate( value );
			if( result != null )
				return result;
		}

		return null;
	}


	/**
	 * Notify all the listeners that an event occurred.
	 * @param event
	 */
	private void notifyListeners () {
		for( AbstractWizardListener listener : this.listeners )
			listener.valueHasChanged ();
	}


	/**
	 * Create a clean label name (no namespace, capitalize every word).
	 * 
	 * @param baseLabel the string used as source for the label name.
	 * @param isOptional true if the element has to be marked as optional in the page.
	 * @param labelSuffix
	 * @return a clean label name.
	 */
	public static String createLabel( String baseLabel, boolean isOptional, String labelSuffix ) {

		if( baseLabel == null || baseLabel.trim().length() == 0 )
			return ""; //$NON-NLS-1$

		labelSuffix = labelSuffix == null ? "" : labelSuffix;
		String name = (baseLabel + labelSuffix).trim();

		// Remove name-spaces.
		String[] parts = name.split( ":" ); //$NON-NLS-1$
		if( parts.length > 1 )
			name = parts[ parts.length - 1 ];

		// Add ":" or "*:" at the end.
		String suffix2 = isOptional ? " (Optional):" : ":";  //$NON-NLS-1$ //$NON-NLS-2$
		name = name.trim();
		if( !name.endsWith( suffix2 ))
			name += suffix2;

		// Remove extra spaces and replace "_", "." and "-" by a single space
		name = name.replaceAll( "(\\s)+", " " ); //$NON-NLS-1$ //$NON-NLS-2$
		name = name.replace( "-", " " ); //$NON-NLS-1$ //$NON-NLS-2$
		name = name.replace( "_", " " ); //$NON-NLS-1$ //$NON-NLS-2$
		name = name.replace( ".", " " ); //$NON-NLS-1$ //$NON-NLS-2$

		// Capitalize letters.
		parts = name.split( " " ); //$NON-NLS-1$
		StringBuilder result = new StringBuilder();
		for( String part : parts ) {
			result.append( part.substring(0, 1).toUpperCase());
			result.append( part.substring(1).toLowerCase());
			result.append( " " ); //$NON-NLS-1$
		}

		return result.toString();
	}


	/*
	 * ABSTRACT
	 */


	/**
	 * Create the controls.
	 * <p>
	 * A specific care should be taken to not initialize <i>value</i> when creating the widgets.
	 * If it is null, it must return null, unless the user modified the value in the widget.
	 * </p>
	 * 
	 * @param parent the parent composite
	 */
	protected abstract void createControls( Composite parent );


	/**
	 * Refresh all the widgets (layout and displayed values).
	 * <p>
	 * A specific care should be taken to not initialize <i>value</i> when creating the widgets.
	 * If it is null, it must return null, unless the user modified the value in the widget.
	 * </p>
	 */
	protected abstract void refreshAll ();


	/**
	 * @return true if the value is <i>empty</i>, false otherwise
	 */
	protected abstract boolean isEmptyValue();


	/**
	 * @return the value as a string object
	 */
	protected abstract String getValueAsString();


	/*
	 * GETTERS & SETTERS
	 */


	/**
	 * @return the data
	 */
	public Object getData() {
		return this.data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData( Object data ) {
		this.data = data;
	}


	/**
	 * @return the value
	 */
	public T getValue() {
		return this.value;
	}


	/**
	 * Set the value and update the widget.
	 * @param value the value to set
	 */
	public void setValue( T value ) {
		this.value = value;
		notifyListeners();
	}


	/**
	 * @return the emptyValueAllowed
	 */
	public boolean isEmptyValueAllowed() {
		return this.emptyValueAllowed;
	}


	/**
	 * @param emptyValueAllowed the emptyValueAllowed to set
	 */
	public void setEmptyValueAllowed( boolean emptyValueAllowed ) {
		this.emptyValueAllowed = emptyValueAllowed;
	}


	/**
	 * Enable or disable the widget set (label and other widgets).
	 * @param enabled
	 */
	public abstract void setEnabled( boolean enabled );
}
