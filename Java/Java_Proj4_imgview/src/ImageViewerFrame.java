import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sandman
 */
public class ImageViewerFrame extends JFrame {
    private BufferedImage img = null;
    private JFileChooser chooser;
    private Zoom zoom;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    public ImageViewerFrame() {
        class DrawPane extends JComponent {
            @Override
            public void paintComponent(Graphics g) {
                if(img == null)
                    return;
                g.drawImage(img, 0, 0, zoom.GetWidth(), zoom.GetHeight(), null);
            }
        }
        zoom = new Zoom();
        setPreferredSize(new Dimension(zoom.GetWidth(),zoom.GetHeight()));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(new DrawPane());

        // set up the file chooser
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        // set up the menu bar
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu fileOptions = new JMenu("File");
        menuBar.add(fileOptions);
        JMenu zoomOptions = new JMenu("Zoom");
        menuBar.add(zoomOptions);
        
        // "Open" button
        JMenuItem openItem = new JMenuItem("Open");
        fileOptions.add(openItem);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // show file chooser dialog
                int result = chooser.showOpenDialog(null);
                // if file selected, set it as icon of the label
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    try {
                        img = ImageIO.read(new File(name));
                        zoom.SetSize(img.getWidth(), img.getHeight());
                        repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(ImageViewerFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        // "Exit" button
        JMenuItem exitItem = new JMenuItem("Exit");
        fileOptions.add(exitItem);
        exitItem.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        JMenuItem zoomIn = new JMenuItem("In");
        zoomOptions.add(zoomIn);
        zoomIn.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent event) {
                zoom.In();
                repaint();
            }
        });
        
        JMenuItem zoomOut = new JMenuItem("Out");
        zoomOptions.add(zoomOut);
        zoomOut.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent event) {
                zoom.Out();
                repaint();
            }
        });
        
        JMenuItem restore = new JMenuItem("100%");
        zoomOptions.add(restore);
        restore.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent event) {
                zoom.Restore();
                repaint();
            }
        });
    }
    public void resizeGui() {
        pack();
    }
}

