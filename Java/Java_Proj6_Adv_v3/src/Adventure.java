
import java.io.IOException;
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
public class Adventure {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new GameChar(args[0]);
        //JFrame frame = new GameChar("testMapFile.txt");
        frame.setTitle("Imageviewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
