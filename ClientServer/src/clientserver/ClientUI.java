/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clientserver;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camnc
 */
public class ClientUI extends javax.swing.JFrame {

    private Client client;
    private MessageModel model;
    private ArrayList<Message> messages;

    /**
     * Creates new form ClientUI
     */
    public ClientUI(Client client, ArrayList<Message> messages) {
        initComponents();

        this.client = client;

        model = new MessageModel();
        this.messages = messages;

        model.setMessages(messages);

        messageTable.setModel(model);
        model.fireTableDataChanged();

        messageTable.getColumnModel().getColumn(0).setMaxWidth(100);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messageTable = new javax.swing.JTable();
        messageField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Sender", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(messageTable);
        if (messageTable.getColumnModel().getColumnCount() > 0) {
            messageTable.getColumnModel().getColumn(0).setResizable(false);
            messageTable.getColumnModel().getColumn(0).setPreferredWidth(1);
            messageTable.getColumnModel().getColumn(1).setResizable(false);
        }

        messageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageFieldActionPerformed(evt);
            }
        });
        messageField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                messageFieldKeyTyped(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void refresh(ArrayList<Message> messages) {
        System.out.println("Refresh");
        model.setMessages(messages);
        model.fireTableDataChanged();
    }


    private void messageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageFieldActionPerformed

    }//GEN-LAST:event_messageFieldActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String message = messageField.getText();
        messageField.setText("");

        if (client != null && !client.sendMessage(message)) {

            JOptionPane.showMessageDialog(null,
                    "Could not send message to Server");
        } else {

            Message mess = new Message("Me", message);

            messages.add(mess);
            model.setMessages(messages);
            model.fireTableDataChanged();
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageFieldKeyTyped

    }//GEN-LAST:event_messageFieldKeyTyped

    private void messageFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageFieldKeyPressed
        System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter");
            String message = messageField.getText();
            messageField.setText("");

            if (client != null && !client.sendMessage(message)) {

                JOptionPane.showMessageDialog(null,
                        "Could not send message to Server");
            } else {

                Message mess = new Message("Me", message);

                messages.add(mess);
                model.setMessages(messages);
                model.fireTableDataChanged();
            }
        }
    }//GEN-LAST:event_messageFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageField;
    private javax.swing.JTable messageTable;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}