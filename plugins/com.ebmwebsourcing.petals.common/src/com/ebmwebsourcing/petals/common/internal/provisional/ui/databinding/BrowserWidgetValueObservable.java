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
package com.ebmwebsourcing.petals.common.internal.provisional.ui.databinding;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.BrowserWidget;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class BrowserWidgetValueObservable extends AbstractObservableValue {

	private BrowserWidget widget;
	
	public BrowserWidgetValueObservable(BrowserWidget widget) {
		this.widget = widget;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.observable.value.IObservableValue#getValueType()
	 */
	@Override
	public Object getValueType() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.observable.value.AbstractObservableValue#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		return widget.getValue();
	}
	
	@Override
	protected void doSetValue(Object value) {
		widget.setValue((String)value);
	}

}
