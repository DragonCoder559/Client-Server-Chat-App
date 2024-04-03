/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author camnc
 */
public class MessageModel extends AbstractTableModel{
    
    private final String[] columnNames = 
    {"Sender", "Messages"};    
    
    ArrayList<Message> messages;
    
    public MessageModel(){
        
        messages = null;
    }
    
    public void setMessages(ArrayList<Message> messages){
        
        this.messages = messages;
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Object value;
        
        if (columnIndex == 0){
            
            value = messages.get(rowIndex).getSender();
        } 
        else{
            
             value = messages.get(rowIndex).getMessage();
        }        
        return value;
    }
    
    @Override
    public String getColumnName(int column){
       
        return this.columnNames[column];
    }
    
}
