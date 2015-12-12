
import java.awt.Dimension;
import java.awt.Toolkit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sandman
 */
public class Zoom {
    public static final float ZFACTOR = 0.25f;
    private  int HEIGHT_CHANGE;
    private  int WIDTH_CHANGE;
    private  int DEFAULT_HEIGHT = 400;
    private  int DEFAULT_WIDTH = 300;
    private int origHeight;
    private int origWidth;
    private int height;
    private int width;

    public Zoom(){
        HEIGHT_CHANGE = Math.round(DEFAULT_HEIGHT * ZFACTOR);
        WIDTH_CHANGE =  Math.round(DEFAULT_WIDTH * ZFACTOR);
        
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
    }
    public int GetHeight() {
        return height;
    }
    public int GetWidth() {
        return width;
    }
    public void In(){
        height += HEIGHT_CHANGE;
        width += WIDTH_CHANGE;
    }
    public void Out() {
        height -= HEIGHT_CHANGE;
        width -= WIDTH_CHANGE;
    }
    public void Restore() {
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
    }
    public void SetSize(int w, int h) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        origWidth = w;
        origHeight = h;
        
        double widthRatio = screenWidth/(double)w;
        double heightRatio = screenHeight/(double)h;
        if(widthRatio < 1 || heightRatio < 1) {
            if(widthRatio < heightRatio) { // Picture too wide
                DEFAULT_WIDTH = (int) Math.round(widthRatio*w);
                DEFAULT_HEIGHT = (int) Math.round(widthRatio*h);
            } else { // Picture too tall
                DEFAULT_WIDTH = (int) Math.round(heightRatio*w);
                DEFAULT_HEIGHT = (int) Math.round(heightRatio*h);
            }
        } else { // Picture fits fine
                DEFAULT_WIDTH = w;
                DEFAULT_HEIGHT = h;
        }
        HEIGHT_CHANGE = Math.round(DEFAULT_HEIGHT * ZFACTOR);
        WIDTH_CHANGE =  Math.round(DEFAULT_WIDTH * ZFACTOR);
        
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
    }
}
