import java.awt.EventQueue;
import java.io.IOException;
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
public class ChatProgram {
    /**
     * @param args the command line arguments
     */
    private static String userName = "Anonymous";
    private static int portNum = 4688;
    public static void main(String[] args){
        if(args.length == 1) {
            userName = args[0];
        }
        if(args.length == 2) {
            userName = args[0];
            portNum = Integer.parseInt(args[1]);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    JFrame frame = new ChatWindow(userName, portNum);
                    frame.setTitle("Chat Program");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ChatProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
