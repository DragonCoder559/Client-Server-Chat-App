/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs469.client.server;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author camnc
 */
public class MessageModel extends AbstractTableModel{
    
    private final String[] columnNames = 
    {"Messages"};    
    
    ArrayList<String> messages;
    
    public MessageModel(){
        
        messages = null;
    }
    
    public void setMessages(ArrayList<String> messages){
        
        this.messages = messages;
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        return messages.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int column){
       
        return this.columnNames[column];
    }
    
}
