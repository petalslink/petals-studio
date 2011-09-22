/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.monitoring.wizard;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiConstants {

	/**
	 * The preference ID to store process names.
	 */
	public static final String PREF_PROCESS_NAMES = "petals.services.kpi.process.names";

	/**
	 * The string that separates the process names in the preferences.
	 */
	public static final String PREF_PROCESS_SEPARATOR = ";;";

	/**
	 * The constant for the IN flow.
	 */
	public static final String IN_FLOW = "In";

	/**
	 * The constant for the OUT flow.
	 */
	public static final String OUT_FLOW = "Out";

	/**
	 * The constant for the FAULT flow.
	 */
	public static final String FAULT_FLOW = "Fault";

	/**
	 * The constant for the STATUS flow.
	 */
	public static final String STATUS_FLOW = "Status";

	/**
	 * A static array with all the flow names.
	 */
	public static final String[] FLOW_NAMES = new String[] {
		KpiConstants.STATUS_FLOW,
		KpiConstants.IN_FLOW,
		KpiConstants.OUT_FLOW,
		KpiConstants.FAULT_FLOW
	};
}
