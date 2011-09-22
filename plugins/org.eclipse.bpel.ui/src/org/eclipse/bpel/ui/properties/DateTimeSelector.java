/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.properties;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.util.Date;

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.common.ui.flatui.FlatFormLayout;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.Policy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * Collection of widgets for choosing a specific date and time.
 * 
 * TODO: rewrite this to be independent of serialization format.  Move that code elsewhere.
 */
public class DateTimeSelector extends Composite {

	static final String ID = "id"; //$NON-NLS-1$


	public static String[] MONTH_NAMES = new String[] {
		Messages.DateTimeSelector_Month_1, Messages.DateTimeSelector_Month_2, Messages.DateTimeSelector_Month_3,
		Messages.DateTimeSelector_Month_4, Messages.DateTimeSelector_Month_5, Messages.DateTimeSelector_Month_6,
		Messages.DateTimeSelector_Month_7, Messages.DateTimeSelector_Month_8, Messages.DateTimeSelector_Month_9,
		Messages.DateTimeSelector_Month_10, Messages.DateTimeSelector_Month_11, Messages.DateTimeSelector_Month_12
	};

	static final int YEAR = 0;
	static final int MONTH = YEAR+1;
	static final int DAY = YEAR+2;
	static final int HOUR = YEAR+3;
	static final int MINUTE = YEAR+4;
	static final int SECOND = YEAR+5;

	static final int LAST_IDX = YEAR+6;



	protected Object lastChangeContext ;

	private final Combo[] combo;

	private Label timeSep, timeSep2, dateSep, dateSep2;
	private final int yearMin;
	private final int yearMax;

	protected String dateSepText = "/"; //$NON-NLS-1$
	protected String timeSepText = ":"; //$NON-NLS-1$

	protected static final int[] rawDaysInMonth = {
		31,28,31,30,31,30,31,31,30,31,30,31
	};

	public static int daysInMonth(int monthMinusOne, int year) {

		// february has a leap day every 4 years, except not every 100 years (except every 400..)
		if (monthMinusOne==1 && (year&3)==0 && ((year%100)!=0 || (year%400)==0))  return 29;
		return rawDaysInMonth[monthMinusOne];
	}


	private final TabbedPropertySheetWidgetFactory wf;

	public DateTimeSelector(TabbedPropertySheetWidgetFactory factory, Composite parent, int style, int yearMin, int yearMax) {
		super(parent, style);
		this.combo = new Combo[6];
		this.wf = factory;
		this.yearMin = yearMin;
		this.yearMax = yearMax;

		setLayout(new FillLayout());
		createControls(this);
	}

	private void createControls(Composite parent){
		FlatFormData data;

		Composite composite = this.wf.createComposite(parent);
		FlatFormLayout layout = new FlatFormLayout();
		layout.marginWidth = layout.marginHeight = 0;
		composite.setLayout(layout);

		FlatFormAttachment monthLeft   = new FlatFormAttachment(0,0);
		FlatFormAttachment monthRight  = new FlatFormAttachment(30,0);
		FlatFormAttachment dayLeft     = new FlatFormAttachment(35,0);
		FlatFormAttachment dayRight    = new FlatFormAttachment(65,0);
		FlatFormAttachment yearLeft    = new FlatFormAttachment(70,0);
		FlatFormAttachment yearRight   = new FlatFormAttachment(100,0);

		FlatFormAttachment hourLeft    = new FlatFormAttachment(0,0);
		FlatFormAttachment hourRight   = new FlatFormAttachment(30,0);
		FlatFormAttachment minuteLeft  = new FlatFormAttachment(35,0);
		FlatFormAttachment minuteRight = new FlatFormAttachment(65,0);
		FlatFormAttachment secondLeft  = new FlatFormAttachment(70,0);
		FlatFormAttachment secondRight = new FlatFormAttachment(100,0);

		for(int i=0; i < this.combo.length; i++) {
			this.combo[i] = new Combo(composite,SWT.READ_ONLY);
			this.combo[i].setData(ID, Integer.valueOf( i ));
		}

		this.dateSep  = this.wf.createLabel(composite, this.dateSepText, SWT.CENTER);
		this.dateSep2 = this.wf.createLabel(composite, this.dateSepText, SWT.CENTER);
		this.timeSep  = this.wf.createLabel(composite, this.timeSepText, SWT.CENTER);
		this.timeSep2 = this.wf.createLabel(composite, this.timeSepText, SWT.CENTER);

		data = new FlatFormData();
		data.left = monthLeft;
		data.right = monthRight;
		data.top = new FlatFormAttachment(0, 0);
		this.combo[MONTH].setLayoutData(data);

		data = new FlatFormData();
		data.left = dayLeft;
		data.right = dayRight;
		data.top = new FlatFormAttachment(0, 0);
		this.combo[DAY].setLayoutData(data);

		data = new FlatFormData();
		data.left = yearLeft;
		data.right = yearRight;
		data.top = new FlatFormAttachment(0, 0);
		this.combo[YEAR].setLayoutData(data);

		data = new FlatFormData();
		data.left = hourLeft;
		data.right = hourRight;
		data.top = new FlatFormAttachment(this.combo[YEAR], IDetailsAreaConstants.VSPACE);
		this.combo[HOUR].setLayoutData(data);

		data = new FlatFormData();
		data.left = minuteLeft;
		data.right = minuteRight;
		data.top = new FlatFormAttachment(this.combo[YEAR], IDetailsAreaConstants.VSPACE);
		this.combo[MINUTE].setLayoutData(data);

		data = new FlatFormData();
		data.left = secondLeft;
		data.right = secondRight;
		data.top = new FlatFormAttachment(this.combo[YEAR], IDetailsAreaConstants.VSPACE);
		this.combo[SECOND].setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.combo[MONTH], 0);
		data.right = new FlatFormAttachment(this.combo[DAY], 0);
		data.top = new FlatFormAttachment(this.combo[MONTH], 0, SWT.CENTER);
		this.dateSep.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.combo[DAY], 0);
		data.right = new FlatFormAttachment(this.combo[YEAR], 0);
		data.top = new FlatFormAttachment(this.combo[MONTH], 0, SWT.CENTER);
		this.dateSep2.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.combo[HOUR], 0);
		data.right = new FlatFormAttachment(this.combo[MINUTE], 0);
		data.top = new FlatFormAttachment(this.combo[HOUR], 0, SWT.CENTER);
		this.timeSep.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(this.combo[MINUTE], 0);
		data.right = new FlatFormAttachment(this.combo[SECOND], 0);
		data.top = new FlatFormAttachment(this.combo[HOUR], 0, SWT.CENTER);
		this.timeSep2.setLayoutData(data);

		reorderDateWidgetsForLocale();

		init();
		layout(true);
	}

	private void reorderDateWidgetsForLocale() {
		// swap widgets around if our default layout doesn't match locale-specific order!

		int dayPos;
		int monthPos;
		int yearPos;

		DateFormat dateFlatFormat = DateFormat.getDateInstance();//DateFlatFormat.DEFAULT, Locale.JAPAN);
		Date date = new Date();
		StringBuffer junkBuffer = new StringBuffer();

		FieldPosition pos = new FieldPosition(DateFormat.DATE_FIELD);
		dateFlatFormat.format(date, junkBuffer, pos);
		dayPos = pos.getBeginIndex();
		if (Policy.DEBUG) System.out.println(junkBuffer);
		junkBuffer.setLength(0);

		pos = new FieldPosition(DateFormat.MONTH_FIELD);
		dateFlatFormat.format(date, junkBuffer, pos);
		monthPos = pos.getBeginIndex();
		junkBuffer.setLength(0);

		pos = new FieldPosition(DateFormat.YEAR_FIELD);
		dateFlatFormat.format(date, junkBuffer, pos);
		yearPos = pos.getBeginIndex();

		Combo left = this.combo[MONTH];
		Combo middle = this.combo[DAY];
		Combo right = this.combo[YEAR];

		// The current widget arrangement assumes MDY.
		if (dayPos < monthPos) {
			// it should be DMY or YDM or DYM.
			if (monthPos < yearPos) {
				// D<M<Y
				this.combo[DAY]=left; this.combo[MONTH]=middle; this.combo[YEAR]=right;  // DMY
			} else if (dayPos < yearPos) {
				// D<M  Y<M   D<Y
				this.combo[DAY]=left; this.combo[YEAR]=middle; this.combo[MONTH]=right;  // DYM
			} else {
				// Y<D<M  Y<M
				this.combo[YEAR]=left; this.combo[DAY]=middle; this.combo[MONTH]=right;  // YDM
			}
		} else {
			if (dayPos < yearPos) {
				// M<D<Y
				this.combo[MONTH]=left; this.combo[DAY]=middle; this.combo[YEAR]=right;  // MDY
			} else if (monthPos < yearPos) {
				// M<D   Y<D   M<Y
				this.combo[MONTH]=left; this.combo[YEAR]=middle; this.combo[DAY]=right;  // MYD
			} else {
				// Y<D   Y<M<D
				this.combo[YEAR]=left; this.combo[MONTH]=middle; this.combo[DAY]=right;  // YMD
			}
		}

	}

	private void init(){
		populateCombos();
		setInitialSelections();
		addListeners();
	}

	private void populateComboWithInts(Combo combo, int start, int length) {
		combo.removeAll();
		for (int i = start; i<start+length; i++) {
			combo.add(((i<10)?"0":"")+String.valueOf(i)); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private void updateDaysCombo(){
		int sel = this.combo[DAY].getSelectionIndex();
		int days = daysInMonth(this.combo[MONTH].getSelectionIndex(),
					Integer.parseInt(this.combo[YEAR].getText()));
		populateComboWithInts(this.combo[DAY], 1, days);
		this.combo[DAY].select(Math.min(sel, days-1));
	}

	private void populateCombos() {

		populateComboWithInts(this.combo[YEAR], this.yearMin, Math.max(0, this.yearMax-this.yearMin));
		this.combo[MONTH].setItems(MONTH_NAMES);
		populateComboWithInts(this.combo[DAY], 1, 31);
		populateComboWithInts(this.combo[HOUR],   0, 24);
		populateComboWithInts(this.combo[MINUTE], 0, 60);
		populateComboWithInts(this.combo[SECOND], 0, 60);
	}

	private void setInitialSelections(){
		this.combo[YEAR].select(0);
		this.combo[MONTH].select(0);
		updateDaysCombo();
		this.combo[DAY].select(0);
		this.combo[HOUR].select(12);
		this.combo[MINUTE].select(0);
		this.combo[SECOND].select(0);
	}

	private void addListeners() {

		SelectionListener commonListener = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Combo cw = (Combo) e.widget;
				DateTimeSelector.this.lastChangeContext = cw.getData(ID);

				if (cw == DateTimeSelector.this.combo[YEAR] || cw == DateTimeSelector.this.combo[MONTH])  {
					updateDaysCombo();
				}
				selectionChanged();
			}
			public void widgetDefaultSelected(SelectionEvent e) { widgetSelected(e); }
		};

		for (int i = 0; i<6; i++) {
			this.combo[i].addSelectionListener(commonListener);
		}
	}

	/**
	 * Returns an array of six values for year,month,day,hour,minute,second.
	 */
	public int[] getValues() {
		int[] v = new int[6];
		v[YEAR]   = this.combo[YEAR].getSelectionIndex()+this.yearMin;
		v[MONTH]  = this.combo[MONTH].getSelectionIndex()+1;
		v[DAY]    = this.combo[DAY].getSelectionIndex()+1;
		v[HOUR]   = this.combo[HOUR].getSelectionIndex();
		v[MINUTE] = this.combo[MINUTE].getSelectionIndex();
		v[SECOND] = this.combo[SECOND].getSelectionIndex();

		return v;
	}

	/**
	 * Expects an array of six values for year,month,day,hour,minute,second.
	 */
	public boolean setValues(int[] v) {
		if (!checkValues(v)) return false;
		// these values are within the valid ranges for the combos.
		this.combo[YEAR].select(v[YEAR]-this.yearMin);
		this.combo[MONTH].select(v[MONTH]-1);
		updateDaysCombo();
		this.combo[DAY].select(v[DAY]-1);
		this.combo[HOUR].select(v[HOUR]);
		this.combo[MINUTE].select(v[MINUTE]);
		this.combo[SECOND].select(v[SECOND]);
		return true;
	}

	protected boolean checkValues(int[] v) {
		if (v == null) return false;
		if (v[YEAR] < this.yearMin || v[YEAR] > this.yearMax) return false;
		if (v[MONTH] < 1 || v[MONTH] > 12) return false;
		if (v[DAY] < 1 || v[DAY] > daysInMonth(v[MONTH]-1, v[YEAR])) return false;
		if (v[HOUR] < 0 || v[HOUR] > 23) return false;
		if (v[MINUTE] < 0 || v[MINUTE] > 59) return false;
		if (v[SECOND] < 0 || v[SECOND] > 59) return false;
		return true;
	}

	void selectionChanged() {
		Event e = new Event();
		e.type = SWT.Selection;
		e.widget = this;
		e.widget.notifyListeners(e.type, e);
	}

	public void addSelectionListener(SelectionListener listener){
		TypedListener typedListener = new TypedListener (listener);
		addListener (SWT.Selection,typedListener);
		// addListener (SWT.DefaultSelection,typedListener);
	}

	public Object getUserContext() {
		return this.lastChangeContext;
	}

	public void restoreUserContext(Object userContext) {
		int i = ((Integer)userContext).intValue();
		switch (i) {
		case YEAR:	case MONTH: case DAY:
		case HOUR:	case MINUTE: case SECOND:
			this.combo[i].setFocus();
			return;
		}
	}

	@Override
	public void setEnabled (boolean enabled) {
		super.setEnabled(enabled);
		for( Combo element : this.combo ) {
			element.setEnabled(enabled);
		}
	}
}
