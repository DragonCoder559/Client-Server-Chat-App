/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package cs469.client.server;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author camnc
 */
public class ClientServer {

    public static void main(String[] args) {

        Client client = null;

        try {
            client = new Client();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not connect to Server");
        }
        
        ClientUI ui = new ClientUI(client);

        while (true) {
            ui.setVisible(true);

            //client.receiveMessage();
        }

        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                   
            }
        });*/
    }
}
