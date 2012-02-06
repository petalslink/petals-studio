/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.ejb.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.swt.widgets.Shell;

import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class EJBConnectionTest {

	/**
	 * @param newlyCreatedEndpoint
	 * @param shell
	 */
	public static void testConnection(AbstractEndpoint newlyCreatedEndpoint, Shell shell) {

		EJBTestDialog dialog = new EJBTestDialog(shell);
		dialog.setBlockOnOpen(false);
		dialog.open();
		InitialContext context = null;
		try {
			context = getContext(newlyCreatedEndpoint);
			dialog.jndiOk();
			context.lookup("java:comp/env").getClass(); // java:comp/env?
			dialog.lookupOk();
			Object res = context.lookup((String)newlyCreatedEndpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__EJB_JNDI_NAME));
			dialog.ejbOk();

		} catch (Exception ex) {
			dialog.showError(ex);

		} finally {
			try {
				if( context != null )
					context.close();

			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	}


	/**
	 * @param endpoint
	 * @return
	 * @throws NamingException
	 */
    protected static InitialContext getContext(AbstractEndpoint endpoint) throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        Object value = endpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL);
        if (value != null)
        	env.put(Context.INITIAL_CONTEXT_FACTORY, value);

        value = endpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__SECURITY_PRINCIPAL);
        if (value != null)
        	env.put(Context.SECURITY_PRINCIPAL, value);

        value = endpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__SECURITY_CREDENCIALS);
        if (value != null)
        	env.put(Context.SECURITY_CREDENTIALS, value);

        value = endpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL);
        if (value != null)
        	env.put(Context.PROVIDER_URL, value);

        value = endpoint.eGet(EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS);
        if (value != null)
        	env.put(Context.URL_PKG_PREFIXES, value);

        return new InitialContext(env);
    }
}
