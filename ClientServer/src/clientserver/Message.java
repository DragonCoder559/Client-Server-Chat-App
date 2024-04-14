/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver;

/**
 *
 * @author camnc
 */
public class Message {
    
    private String sender;
    private String message;
    private String recipient;

    public Message(String sender, String message, String recipient) {
        this.sender = sender;
        this.message = message;
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }
    
    
    
}
