package clientserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * This class does all of the client-server communication
 * 
 * @author Cameron Christner
 */
public class Client {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    /**
     * Establishes the connection with the server and the communication streams 
     * 
     * @throws IOException if connection to the server fails
     */
    public Client() throws IOException { 
        //Localhost IP right now, can be changed
        socket = new Socket("10.0.0.94", 65431); 
        in = new DataInputStream(new BufferedInputStream(
                socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Sends a "message" to the server
     * 
     * @param message the item to be sent to the server
     * 
     * @return the Boolean value for whether the send was successful
     */
    public boolean send(String message) {

        boolean success = true;

        try {
            out.writeUTF(message); //Sends message
        } catch (IOException ex) {
            success = false;
        }

        return success;
    }
    
    /**
     * Receives a "message" from the server (Blocking Operation)
     * 
     * @return the item that was received from the server
     */
    public String receive() {

        String message;

        try {
            message = in.readUTF(); //Receives message
        } catch (IOException ex) {
            message = null;
        }

        return message;
    }

    /**
     * Deals with account creation or account login, based on what the user
     * picks (Blocking Operation)
     * 
     * @return true if creation or login is successful
     */
    public String account() {
        
        boolean success;
        String message = receive(); //Server sends prompt messages
        
        if (message.equals("Error: Incorrect option")){
            
            JOptionPane.showMessageDialog(null, message);
            
            return null;
        }

        String username = JOptionPane.showInputDialog(message);

        boolean success1 = send(username); 
        String message2 = receive();

        String password = JOptionPane.showInputDialog(message2);

        boolean success2 = send(password);

        String message3 = receive();
        JOptionPane.showMessageDialog(null, message3);
        
        if (message3.equals("Incorrect username or password") || 
                message3.equals("User already exists")){
            
            success2 = false;
        }

        success = success1 && success2;
        
        if (!success){
            
            username = null;
        }

        return username;
    }

    /**
     * Sends a message to another Client through the Server
     * 
     * @param message the message to be sent
     * 
     * @return the Boolean value for whether the send was successful
     */
    public boolean sendMessage(Message message) {

        boolean success;
        
        //Sends sender of message
        boolean success1 = send(message.getSender()); 

        //Sends actual message
        boolean success2 = send(message.getMessage());
        try {
            Thread.sleep(10); //Because server was receiving two sends
        } catch (InterruptedException ex) {
        } //Just need to avoid the exception
        
        //Sends message recipient
        boolean success3 = send(message.getRecipient());

        success = success1 && success2 && success3;

        return success;
    }

    /**
     * Receives a message from another Client through the Server
     *
     * @param username the username of the client
     * 
     * @return The message from another client 
     */
     public Message receiveMessage(String username) {

        Message mess = null;

        String sender = receive(); //Receives the sender of the message

        if (sender != null) {

            System.out.println(sender);

            String message = receive(); //Receives the actual message

            if (message != null) {

                System.out.println(message);

                mess = new Message(sender, message, username);
            }
        }

        return mess;
    }
}
