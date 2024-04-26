package clientserver;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * The model for the message table, gives the table its data
 *
 * @author Cameron Christner
 */
public class MessageModel extends AbstractTableModel {

    private final String[] columnNames = {"Sender", "Messages"};

    ArrayList<Message> messages;

    /**
     * Constructs the message model
     */
    public MessageModel() {

        messages = null;
    }

    /**
     * Sets the list of messages the model will use
     *
     * @param messages the list of messages
     */
    public void setMessages(ArrayList<Message> messages) {

        this.messages = messages;
    }

    /**
     * Sets the number of rows in the table
     *
     * @return the number of messages in the list of messages
     */
    @Override
    public int getRowCount() {

        return messages.size();
    }

    /**
     * Sets the number of column in the table
     *
     * @return 2, for the sender column and message column
     */
    @Override
    public int getColumnCount() {

        return 2;
    }

    /**
     * Sets the data in the table
     * 
     * @param rowIndex the row for data to be set
     * @param columnIndex the column for the data to be set
     * 
     * @return the data to be set
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value;

        if (columnIndex == 0) {

            value = messages.get(rowIndex).getSender();
        } else {

            value = messages.get(rowIndex).getMessage();
        }
        return value;
    }

    /**
     * Sets the headers in the table
     * 
     * @param column the column number that needs a header
     * 
     * @return the header for the column
     */
    @Override
    public String getColumnName(int column) {

        return this.columnNames[column];
    }

}
