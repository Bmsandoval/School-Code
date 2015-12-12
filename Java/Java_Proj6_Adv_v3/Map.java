import java.io.Serializable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sandman
 */ 
public class Map extends javax.swing.JPanel implements Serializable {
    // items[0] is the y coord, items[1] is the x coord, items[2] is the item name.
    // private final ArrayList<String>[] items = (ArrayList<String>[])new ArrayList[2];
    private ArrayList<String> itemX = new ArrayList<>();
    private ArrayList<String> itemY = new ArrayList<>();
    private ArrayList<String> itemName = new ArrayList<>();
    private ArrayList<String> mapIdentifier = new ArrayList<>();
    private ArrayList<BufferedImage> mapPic = new ArrayList<>();
    private ArrayList<String> inv = new ArrayList<>();
    private ArrayList<String> imgLoc = new ArrayList<>();
    private BufferedImage sprite;
    private BufferedImage offLimits;
    private int cRow;
    private int cCol;
    private int mRow;
    private int mCol;
    private int picH;
    private int picW;
    int personCnt = 0;
    int outCnt = 0;
    private String itemFileName;
    private String[][] map;
    private final char north = 'N';
    private final char south = 'S';
    private final char east = 'E';
    private final char west = 'W';
    /**
     * Creates new form Map
     */
    public Map() {
        initComponents();
        cRow = 0;
        cCol = 0;
    }
    public void writeObject(ObjectOutputStream out) throws IOException {
        // out.defaultWriteObject();
        out.writeInt(cCol);
        out.writeInt(cRow);
        out.writeInt(mRow);
        out.writeInt(mCol);
        out.writeInt(picH);
        out.writeInt(picW);
        out.writeInt(personCnt);
        out.writeInt(outCnt);
        //ImageIO.write(sprite, "png", out);
        //ImageIO.write(offLimits, "png", out);
        //out.writeInt(mapPic.size());
        //for(BufferedImage pics: mapPic)
        //    ImageIO.write(pics, "png", out);
        out.writeObject(itemX);
        out.writeObject(itemY);
        out.writeObject(itemName);
        out.writeObject(mapIdentifier);
        out.writeObject(inv);
        out.writeObject(map);
        out.writeObject(imgLoc);
    }
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // in.defaultReadObject();
        cCol = in.readInt();
        cRow = in.readInt();
        mRow = in.readInt();
        mCol = in.readInt();
        picH = in.readInt();
        picW = in.readInt();
        personCnt = in.readInt();
        outCnt = in.readInt();
        //sprite = ImageIO.read(in);
        //offLimits = ImageIO.read(in);
        //final int imageCount = in.readInt();
        //for(int i = 0; i < imageCount; i++)
        //    mapPic.add(ImageIO.read(in));
        itemX=(ArrayList)in.readObject();
        itemY = (ArrayList)in.readObject();
        itemName = (ArrayList)in.readObject();
        mapIdentifier = (ArrayList)in.readObject();
        inv = (ArrayList)in.readObject();
        map = (String[][])in.readObject();
        imgLoc = (ArrayList)in.readObject();
        
        for(int i = 0; i < imgLoc.size(); i++) {
            if(i == personCnt)
                sprite = ImageIO.read(new File(imgLoc.get(i)));
            else if(i == outCnt)
                offLimits = ImageIO.read(new File(imgLoc.get(i)));
            else
                mapPic.add(ImageIO.read(new File(imgLoc.get(i))));
        }
    }

    public void Init(String input) throws IOException{
        String line;
        Scanner fin;
        String[] temp;
        try {
            fin = new Scanner(new File(input));
            // Take first line and get mapLength and mapHeight. Initialize map array
            line = fin.nextLine();
            temp = line.split(" ");
            mRow = Integer.parseInt(temp[0]);
            mCol = Integer.parseInt(temp[1]);
            map = new String[mRow][mCol];
            for(int j = 0; j < mRow; j++) {
                line = fin.nextLine();
                for(int i = 0; i < mCol; i++){
                    map[j][i] = (line.charAt(i)) + "";
                }
            }
            line = fin.nextLine();
            temp = line.split(" ");
            picH = Integer.parseInt(temp[0]);
            picW = Integer.parseInt(temp[1]);
            itemFileName = fin.nextLine();
            while(fin.hasNextLine()) {
                int cnt = 0;
                line = fin.nextLine();
                temp = line.split(";");
                imgLoc.add(temp[2].toLowerCase());
                switch (temp[1].toLowerCase()) {
                    case "person":
                        personCnt = cnt-1;
                        sprite = ImageIO.read(new File(temp[2]));
                        break;
                    case "out":
                        outCnt = cnt-1;
                        offLimits = ImageIO.read(new File(temp[2]));
                        break;
                    default:
                        mapIdentifier.add(temp[0]);
                        mapPic.add(ImageIO.read(new File(temp[2])));
                        break;
                }
                cnt++;
            }
            fin.close();
        } catch (FileNotFoundException x) {
            System.out.println("Error: " + x);
            System.exit(0);
        }
        try {
            fin = new Scanner(new File(itemFileName));
            while(fin.hasNextLine()) {
                line = fin.nextLine();
                temp = line.split(";");
                initItem(temp[0], temp[1], temp[2]);
            }
        } catch (FileNotFoundException x) {
            System.out.println("Error: " + x);
            System.exit(0);
        }
        repaint();
    }
    /**
     *  
     * @return Contents of the player's inventory
     */
    public String Inventory() {
        String temp = "";
        for (String inv1 : inv) {
            temp += inv1 + "\n";
        }
        return temp;
    }
    /**
     * 
     * @param dir direction of requested move
     * @return name if an item is that location, null if no item. Returns "out" if character attempted to move out of bounds.
     */
    public String Go(char dir) {
        String itemList = "";
        switch(dir){
            case north:
                if(cRow == 0)
                    return "out";
                else
                    cRow--;
                break;
            case south:
                if(cRow == mRow-1)
                    return "out";
                else
                    cRow++;
                break;
            case east:
                if(cCol == mCol-1)
                    return "out";
                else
                    cCol++;
                break;
            case west:
                if(cCol == 0)
                    return "out";
                else
                    cCol--;
                break;
            default:
                break;
        }
        for(int i = 0; i < itemName.size(); i++) {
            if(Integer.parseInt(itemY.get(i))==cRow)
                if(Integer.parseInt(itemX.get(i))==cCol)
                        itemList += "You found: " + itemName.get(i) + "\n";
        }
        return itemList;
    }
    public String GetCoord() {
        return "You are at: " + cRow + "," + cCol;
    }
    public int GetRow() {
        return cRow;
    }
    public int GetCol() {
        return cCol;
    }
    public int GetMaxR() {
        return mRow;
    }
    public int GetMaxC() {
        return mCol;
    }
    public void initItem(String tmpY, String tmpX, String itm) {
        itemY.add(tmpY);
        itemX.add(tmpX);
        itemName.add(itm);
    }
    public String Take(String t) {
        String returnString = "";
        for(int i = 0; i < itemName.size(); i++) {
            if(Integer.parseInt(itemY.get(i))==cRow) {
                if(Integer.parseInt(itemX.get(i))==cCol) {
                    if(itemName.get(i).equalsIgnoreCase(t)) {
                        returnString += itemName.get(i) + "\n";
                        inv.add(itemName.get(i));
                        itemY.remove(i);
                        itemX.remove(i);
                        itemName.remove(i);
                        return "You took: " + returnString;
                    }
                }
            }
        }
        return "Cannot find " + t + "\n";
    }
    public String Drop(String d) {
        Boolean found = false;
        for (int i = 0; i < inv.size(); i++) {
            if(inv.get(i).equalsIgnoreCase(d)) {
                itemY.add(cRow + "");
                itemX.add(cCol + "");
                itemName.add(d);
                inv.remove(i);
                found = true;
            }    
        }
        if(found == false)
            return "You do not have an item by that name";
        return "You dropped " + d + "\n";
    }
    @Override
    public void paintComponent(Graphics g) {
        int adjX = cCol - 2;
        int adjY = cRow - 2;
        // prints entire map by comparing the mapIdentifier array to the current map file.
        // Need to update to only print a certain radius (1)
        for(int yVal = cRow - 2; yVal <= cRow + 2; yVal++) { // y traversion
            for(int xVal = cCol - 2; xVal <= cCol + 2; xVal++) {// x traversion
                if (yVal < 0 || yVal > mRow-1 || xVal < 0 || xVal > mCol-1){
                    g.drawImage(offLimits, (xVal-adjX)*picW, (yVal-adjY)*picH, picW, picH, null);
                } else {
                    for(int f = 0; f < mapIdentifier.size(); f++) {
                        if(mapIdentifier.get(f).equals(map[yVal][xVal])) {
                           g.drawImage(mapPic.get(f), (xVal-adjX)*picW, (yVal-adjY)*picH, picW, picH, null);
                           break;
                        }
                    }
                }
            }
        }
        g.drawImage(sprite, 2*picW, 2*picH, picW, picH, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
