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
            JOptionPane.showMessageDialog(null, client.receive());
            client.send("B");
            client.login("CNC", "Abc123");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not connect to Server");
        }

        ClientUI ui = new ClientUI(client, messages);
        ui.setVisible(true);

        Thread t = new Thread(new ClientThread(client, ui, messages));
        t.start();

    }
}
