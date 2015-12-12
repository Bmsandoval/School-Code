
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sandman
 */
public class ChatWindow extends JFrame {
    private String username;
    private Scanner in;
    private PrintWriter out;
    private Socket s;
    /**
     * Creates new form ChatWind
     * @throws java.io.IOException
     */
    public ChatWindow(String un, int pn) throws IOException {
        initComponents();
        username = un;
        try {
            s = new Socket("localhost", pn);
            in = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());
            class PollServer implements Runnable {
                @Override
                public void run(){
                    while(true){
                        if(in.hasNextLine()){
                            String input = in.nextLine();
                            try {
                                PrintToWindow(input);
                            } catch (IOException ex) {
                                Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } 
                    }
                }
            }
            Thread t1 = new Thread(new PollServer());
            t1.start();
            Connect();
        }
        catch (IOException io){
            throw io;
        }
    }
    public void Connect() throws IOException {
        SendToServer("connect " + username);
    }
    public void Disconnect() throws IOException {
        SendToServer("disconnect " + username);
    }
    public void SendToServer(String clientOut) throws IOException {
        out.println(clientOut);
        PrintToWindow(clientOut);
        out.flush();
    }
    public synchronized void PrintToWindow(String clientIn) throws IOException {
        textArea.append(clientIn + "\n");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        button1 = new java.awt.Button();
        textField = new java.awt.TextField();
        textArea = new java.awt.TextArea();
        button = new java.awt.Button();
        button2 = new java.awt.Button();

        button1.setLabel("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setEditable(false);

        button.setLabel("Disconnect");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                disconnectBtnReleased(evt);
            }
        });

        button2.setLabel("Send");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sendBtnReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disconnectBtnReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectBtnReleased
        try {
            Disconnect();// TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_disconnectBtnReleased

    private void sendBtnReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnReleased
        try {
            SendToServer(textField.getText());// TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        textField.setText("");
    }//GEN-LAST:event_sendBtnReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.TextArea textArea;
    private java.awt.TextArea textArea1;
    private java.awt.TextField textField;
    // End of variables declaration//GEN-END:variables
}
