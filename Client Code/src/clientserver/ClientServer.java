package clientserver;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * The main class and method for the Client side of a Client-Server Chat
 * Application
 *
 * @author Cameron Christner
 */
public class ClientServer {

    public static void main(String[] args) {

        Client client; //The client connection
        
        //Contains all of the messages
        ArrayList<Message> messages = new ArrayList<>();

        try {
            client = new Client(); 
            String option = JOptionPane.showInputDialog(client.receive());
            client.send(option.toUpperCase());
            
            String username = client.account(); //Logging in

            if (username != null) {

                Users users = new Users(username); //Sets Username for client

                ClientFrame frame = new ClientFrame(client, messages, users);
                frame.setVisible(true); //Display UI
                
            } else {
                JOptionPane.showMessageDialog(null, 
                        "Could not log in or register"); //Incorrect user  
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Could not connect to Server");
        }
    }
}
