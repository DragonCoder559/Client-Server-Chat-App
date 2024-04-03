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

    public static void main(String[] args) throws InterruptedException {

        Client client = null;
        
        ArrayList<Message> messages = new ArrayList<>();
        

        try {
            client = new Client();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not connect to Server");
        }
        
        ClientUI ui = new ClientUI(client, messages);
        ui.setVisible(true);
        
                    
        int i = 0;
        while (true) {
            //Message message = client.receiveMessage();

            i++;
            
            Thread.sleep(1000);
            
            Message message = new Message("test", String.valueOf(i));
            
            
            if (message != null){
                
                messages.add(message);
            }
            
            ui.refresh(messages);
        }
    }
}
