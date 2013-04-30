
package {ROOT_PACKAGE}.listeners;

import org.ow2.petals.component.framework.api.exception.PEtALSCDKException;
import org.ow2.petals.component.framework.listener.AbstractExternalListener;

/**
 * Listens to external events and propagates them in Petals.
 * @author {AUTHOR}
 */
public class ExternalListener extends AbstractExternalListener {

	/*
     * (non-Javadoc)
     * @see org.ow2.petals.component.framework.listener.AbstractExternalListener
     * #start()
     */
    @Override
    public void start() throws PEtALSCDKException {
        // Start to listen external events
    }

    
    /*
     * (non-Javadoc)
     * @see org.ow2.petals.component.framework.listener.AbstractExternalListener
     * #stop()
     */
    @Override
    public void stop() throws PEtALSCDKException {
        // Stop listening
    }
}
