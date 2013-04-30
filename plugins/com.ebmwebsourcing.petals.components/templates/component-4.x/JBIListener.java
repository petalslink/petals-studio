
package {ROOT_PACKAGE}.listeners;

import org.ow2.petals.component.framework.api.message.Exchange;
import org.ow2.petals.component.framework.listener.AbstractJBIListener;

/**
 * Listens to messages incoming from inside Petals.
 * <p>
 * This class is in charge of processing messages coming from a Petals
 * consumer. These messages can be requests (in messages) or acknowledgments (ACK).
 * </p>
 * <p>
 * Depending on the invoked operation, the message exchange pattern (MEP) and
 * the component's logic, this class may build and send a response.
 * </p>
 * 
 * @author {AUTHOR}
 */
public class JBIListener extends AbstractJBIListener {

    /*
     * (non-Javadoc)
     * @see org.ow2.petals.component.framework.listener.AbstractJBIListener
     * #onJBIMessage(org.ow2.petals.component.framework.api.message.Exchange)
     */
    @Override
	public boolean onJBIMessage( Exchange exchange ) {

    	// True to let the CDK close the exchange.
    	// False to explicitly return the exchange.
        return true;
    }
}
