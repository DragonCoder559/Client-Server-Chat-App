/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camnc
 */
public class ClientThread implements Runnable {

    private Client client;
    private ClientUI ui;
    private ArrayList<Message> messages;

    public ClientThread(Client client, ClientUI ui, ArrayList<Message> messages) {
        this.client = client;
        this.ui = ui;
        this.messages = messages;
    }

    public void run() {

        int i = 0;
        while (true) {
            Message message = client.receiveMessage();
            
            //Message message = new Message("test", client.receive(), "test");
            i++;
            
            
            messages.add(message);

            ui.refresh(messages);
        }
    }
}
