/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Cameron Christner
 */
public class Client {

    private SocketChannel channel;
    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;

    public Client() throws IOException {

        //InetSocketAddress address = new InetSocketAddress(
        //        "127.0.0.1", 65432);

        //this.channel = SocketChannel.open(address);
        //this.channel.configureBlocking(false);
        this.readBuffer = ByteBuffer.allocate(256);
        this.writeBuffer = ByteBuffer.allocate(256);
    }

    public boolean sendMessage(String message) {

        boolean success = true;

       /* this.writeBuffer.put(message.getBytes());
        this.writeBuffer.flip();
        try {
            channel.write(this.writeBuffer);
        } catch (IOException ex) {
            success = false;
        }

        writeBuffer.clear();*/

        return success;
    }

    public Message receiveMessage() {

        String message = "";

        /*try {
            channel.read(readBuffer);
            readBuffer.flip();
            
            boolean end = false;
            
            while (!end && readBuffer.hasRemaining()) {

                char c = readBuffer.getChar();
                if (c == '\r' || c == '\n') {
                    end = true;
                }
                
                message+=c;
            }

            readBuffer.clear();
            
        } catch (IOException ex) {
            message = null;
        }*/
        
        Message mess = new Message("", message);

        return mess;
    }
    
    public void closeClient() throws IOException{
        
        this.channel.close();
    }
}
