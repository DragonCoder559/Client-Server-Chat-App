/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package clientserver;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camnc
 */
public class ClientServer {

    public static String username = "test";
    
    public static void main(String[] args) throws InterruptedException {


        
        Client client = null;
        
        ArrayList<Message> messages = new ArrayList<>();
        

        try {
            client = new Client();
            System.out.println(client.receivePrompt());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not connect to Server");
        }
        
        ClientUI ui = new ClientUI(client, messages);
        ui.setVisible(true);
        
                    
        while (true) {
            Message message = client.receiveMessage();
 
            ui.refresh(messages);
        }
    }
}
