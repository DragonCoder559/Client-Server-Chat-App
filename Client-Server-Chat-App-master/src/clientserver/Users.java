package clientserver;

/**
 * Contains the client username and message recipient
 * 
 * @author Cameron Christner
 */
public class Users {
 
    private final String username;
    private String recipient;
    
    /**
     * Constructs the object and sets the username
     * 
     * @param username 
     */
    public Users(String username){
       
        this.username = username;
        recipient = null;
        
    }

    /**
     * Gets the client username
     * 
     * @return the client username
     */
    public String getUsername() {
        
        return username;
    }

    /**
     * Gets the message recipient
     * 
     * @return the message recipient
     */
    public String getRecipient() {
        
        return recipient;
        
    }

    /**
     * Sets the message recipient
     * 
     * @param recipient the message recipient
     */
    public void setRecipient(String recipient) {
        
        this.recipient = recipient;
    }

}
