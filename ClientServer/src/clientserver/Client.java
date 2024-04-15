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
    
    public Client() throws IOException{
        
        socket = new Socket("127.0.0.1", 65431);
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
        
        
    }
    
    public boolean send(String message){
        
        boolean success = true;
        
        
        try {
            out.writeUTF(message);
        } catch (IOException ex) {
            success = false;
        }
        
        return success;
    }
    
    public String receive(){
        
        String message;
        
        try {
            message = in.readUTF();
        } catch (IOException ex) {
            message = null;
        }
        
        return message;
        
    }
    
    public boolean login(String username, String password){
        
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
    
    public boolean sendMessage(Message message) throws InterruptedException{
        
        boolean success;
        boolean success1 = send(message.getSender());
        
        boolean success2 = send(message.getMessage());
        Thread.sleep(10);
        boolean success3 = send(message.getRecipient());
        
        success = success1 && success2 && success3;
        
        return success;
    }
    
    public Message receiveMessage(){
        
        Message mess = null;
        
        String sender = receive();
        
        if (sender != null){
            
            System.out.println(sender);
            
           String message = receive();
           
           if (message != null){
               
               System.out.println(message);
               
               mess = new Message(sender, message, "test");
           }
        }
        
        
        return mess;
    }
    
    

    //private SocketChannel channel;
    //private ByteBuffer readBuffer;
    //private ByteBuffer writeBuffer;

    /*public Client() throws IOException {

        InetSocketAddress address = new InetSocketAddress(
                "127.0.0.1", 65431);
        this.channel = SocketChannel.open(address);
        this.channel.configureBlocking(false);
        this.readBuffer = ByteBuffer.allocate(256);
        this.writeBuffer = ByteBuffer.allocate(256);
    }
    
    public String receivePrompt(){
        
        String prompt = "";
        

        try {
            
            channel.read(readBuffer);
            readBuffer.flip();

            boolean end = false;

            while (!end && readBuffer.hasRemaining()) {

                char c = readBuffer.getChar();
                if (c == '\r' || c == '\n') {
                    end = true;
                }

                prompt += c;
            }

            readBuffer.clear();
            readBuffer.flip();

        } catch (IOException ex) {
            prompt = null;
        }
        
        return prompt;
    }
    
    public String receivePrompt(){
        
        String prompt = "";
        

        try {
            
            channel.read(readBuffer);
            readBuffer.flip();

            boolean end = false;

            while (!end && readBuffer.hasRemaining()) {

                char c = readBuffer.getChar();
                if (c == '\r' || c == '\n') {
                    end = true;
                }

                prompt += c;
            }

            readBuffer.clear();
            readBuffer.flip();

        } catch (IOException ex) {
            prompt = null;
        }
        
        return prompt;
    }
    
    
    public boolean sendPromptAnswer(String answer){
        
        boolean success = true;

        this.writeBuffer.put(answer.getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success = false;
        }

        writeBuffer.clear();

        this.writeBuffer.flip();
        
        return success;
    }
    
    
    public boolean login(String username, String password) {

        boolean success;
        boolean success1 = true;
        boolean success2 = true;

        this.writeBuffer.put(username.getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success1 = false;
        }

        writeBuffer.clear();

        this.writeBuffer.flip();

        this.writeBuffer.put(password.getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success2 = false;
        }
        
        writeBuffer.clear();
        writeBuffer.flip();

        success = success1 && success2;

        return success;
    }
    
    public boolean sendMessage(Message message) {

        boolean success;
        boolean success1 = true;
        boolean success2 = true;

        this.writeBuffer.put(message.getRecipient().getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success1 = false;
        }

        writeBuffer.clear();

        this.writeBuffer.flip();

        this.writeBuffer.put(message.getMessage().getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success2 = false;
        }
        
        writeBuffer.clear();
        writeBuffer.flip();

        success = success1 && success2;

        return success;
    }

    public Message receiveMessage() {

        Message mess = null;
        String message = "";
        String sender = "";

        try {
            channel.read(readBuffer);
            readBuffer.flip();

            boolean end = false;

            while (!end && readBuffer.hasRemaining()) {

                char c = readBuffer.getChar();
                if (c == '\r' || c == '\n') {
                    end = true;
                }

                sender += c;
            }

            readBuffer.clear();

        } catch (IOException ex) {
            sender = null;
        }

        readBuffer.flip();

        try {
            channel.read(readBuffer);
            readBuffer.flip();

            boolean end = false;

            while (!end && readBuffer.hasRemaining()) {

                char c = readBuffer.getChar();
                if (c == '\r' || c == '\n') {
                    end = true;
                }

                message += c;
            }

            readBuffer.clear();
            readBuffer.flip();

        } catch (IOException ex) {
            message = null;
        }

        if (sender != null && message != null) {

            mess = new Message(sender, message, "test");
        }

        return mess;
    }

    public void closeClient() throws IOException {

        this.channel.close();
    }*/
    
    
    
}
