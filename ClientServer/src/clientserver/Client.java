/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 *
 * @author Cameron Christner
 */
public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException { 

        System.out.println("Test1");
        socket = new Socket("10.0.0.94", 65431);
        System.out.println("Test2");
       in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());

    }

    public boolean send(String message) {

        boolean success = true;

        try {
            out.writeUTF(message);
        } catch (IOException ex) {
            success = false;
        }

        return success;
    }

    public String receive() {

        String message;

        try {
            message = in.readUTF();
        } catch (IOException ex) {
            message = null;
        }

        return message;

    }

    /**
     * 
     * 
     * @param username
     * @param password
     * 
     * @return 
     */
    public boolean login(String username, String password) {

        boolean success;
        String message = receive();

        JOptionPane.showMessageDialog(null, message);

        boolean success1 = send(username);
        String message2 = receive();

        JOptionPane.showMessageDialog(null, message2);

        boolean success2 = send(password);

        String message3 = receive();
        JOptionPane.showMessageDialog(null, message3);

        success = success1 && success2;

        return success;
    }

    /**
     * Sends a message to another Client through the Server
     * 
     * @param message the message to be sent
     * 
     * @return 
     */
    public boolean sendMessage(Message message) {

        boolean success;
        boolean success1 = send(message.getSender());

        boolean success2 = send(message.getMessage());
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        boolean success3 = send(message.getRecipient());

        success = success1 && success2 && success3;

        return success;
    }

    /**
     * Receives a message from another Client through the Server
     *
     * @return The message from another client
     */
    public Message receiveMessage() {

        Message mess = null;

        String sender = receive(); //Receives the sender of the message

        if (sender != null) { 

            System.out.println(sender);

            String message = receive(); //Receives the actuall message

            if (message != null) {

                System.out.println(message);

                mess = new Message(sender, message, "CNC");
            }
        }

        return mess;
    }
}
