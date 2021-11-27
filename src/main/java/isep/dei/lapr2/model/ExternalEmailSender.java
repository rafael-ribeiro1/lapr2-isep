
package isep.dei.lapr2.model;

import java.io.Serializable;

/**
 *
 * External interface of Email Sender.
 */
public interface ExternalEmailSender extends Serializable {
    /**
     * Method that sends the email to the destiny.
     * @param emailDestiny Email of Destination.
     * @param header Header of the email.
     * @param content Content of the email.
     */
    public void sendEmail(String emailDestiny, String header , String content);
    
}
