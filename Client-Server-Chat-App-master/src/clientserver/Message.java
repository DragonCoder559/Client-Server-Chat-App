package clientserver;

/**
 * This stores a message, its sender, and its recipient
 *
 * @author Cameron Christner
 */
public class Message {

    private final String sender;
    private final String message;
    private final String recipient;

    /**
     * Creates the Message object
     *
     * @param sender the sender of the message
     * @param message the actual message
     * @param recipient the recipient of the message
     */
    public Message(String sender, String message, String recipient) {

        this.sender = sender;
        this.message = message;
        this.recipient = recipient;
    }

    /**
     * Gets the sender of the message
     * 
     * @return the sender of the message
     */
    public String getSender() {
        
        return sender;
    }

    /**
     * Gets the actual message
     * 
     * @return the actual message
     */
    public String getMessage() {
        
        return message;
    }

    /**
     * Gets the recipient of the message
     * 
     * @return the recipient of the message
     */
    public String getRecipient() {
        
        return recipient;
    }

}
