/****************************************************************************
 *
 * Copyright (c) 2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.ejb;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class Messages extends NLS {

	public static String ejbLibsText;
	public static String serverLibsText;
	public static String testConnection;

	static {
		initializeMessages("messages", Messages.class);
	}
}
