package clientserver;

import java.util.ArrayList;

/**
 * Sets up a thread that receives messages from the server
 * 
 * @author Cameron Christner 
 */
public class ClientThread implements Runnable {

    private Client client;
    private ClientUI ui;
    private ArrayList<Message> messages;
    private String username;

    /**
     * Gives the thread the resources it needs
     * 
     * @param client the client to receive messages
     * @param ui the UI to refresh the message table when message is received
     * @param messages the list of messages to add new messages to
     * @param username the username of the client
     */
    public ClientThread(Client client, ClientUI ui, ArrayList<Message> messages,
            String username) {
        this.client = client;
        this.ui = ui;
        this.messages = messages;
        this.username = username;  
    }

    /**
     * Runs the thread
     */
    public void run() {

        while (true) {
            
            //Blocks the thread until message is received
            Message message = client.receiveMessage(username);
            
            messages.add(message);

            ui.refresh(messages);
        }
    }
}
