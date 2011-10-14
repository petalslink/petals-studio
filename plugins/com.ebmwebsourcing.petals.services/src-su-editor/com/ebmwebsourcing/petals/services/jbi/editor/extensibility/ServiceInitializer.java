package com.ebmwebsourcing.petals.services.jbi.editor.extensibility;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

public abstract class ServiceInitializer {

	public abstract void initializeService(Provides provide);
	public abstract void initializeService(Consumes consumes);
	
	public void initializeService(AbstractEndpoint endpoint) {
		if (endpoint instanceof Provides) {
			initializeService((Provides)endpoint);
		} else if (endpoint instanceof Consumes) {
			initializeService((Consumes)endpoint);
		}
	}
}
