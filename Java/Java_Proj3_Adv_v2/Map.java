/*
 * Class: Map
 * Purpose: Hold the game map and current location. print surroundings as needed.
 */
import java.util.*;
import java.io.*;
import java.lang.*;


public class Map {
    private int row;
    private int col;
    private int maxR;
    private int maxC;
    private String[][] map;

    /*
     * Method: Map (Parameterized Constructor)
     * Input: Scanner object with the file already opened successfully.
     * Output: string containing maxHeight and maxLength, separated by a space.
     * Purpose: Pull map contents from given file and store in 2d array.
     */
    Map(String input) {
        row = 0;
        col = 0;

        String line;
        Scanner fin = null;
        try
        {
          fin = new Scanner(new File(input));
        }
        catch (FileNotFoundException x)
        {
            System.out.println("Error: " + x);
            System.exit(0);
        }
        // Take first line and get mapLength and mapHeight. Initialize map array
        line = fin.nextLine();
        String[] temp = line.split(" ");
        maxR = Integer.parseInt(temp[0].toString());
        maxC = Integer.parseInt(temp[1].toString());
        map = new String[maxR][maxC];

        for(int j = 0; j < maxR; j++) {
            line = fin.nextLine();
            for(int i = 0; i < maxC; i++){
                map[j][i] = (line.charAt(i)) + "";
            }
        }
    }
    /*
     * Method: Print
     * Input: an int containing current visibility. 0 = show current tile, 1 = show 1 tile radius, etc.
     * Output: None
     * Purpose: Print out current surroundings based on current visibility
     */
    public void Print(int visibility) {
        System.out.println("We are at location " + row + "," + col + " in terrain " + map[row][col]);
        for(int j = row-visibility; j <= row + visibility; j++) {
            for(int i = col-visibility; i <= col + visibility; i++) {
                if(i >= 0 && i <= (maxC -1) && j >= 0 && j <= (maxR -1))
                    System.out.print(map[j][i]);
                else
                    System.out.print("X");
            }
            System.out.println("");
        }
    }

    // ***** Getters and Setters ***** //
    public int GetRow(){
        return row;
    }
    public void SetRow(int r){
        row = r;
    }
    public int GetCol(){
        return col;
    }
    public void SetCol(int c){
        col = c;
    }
    public int GetMaxR(){
        return maxR;
    }
    public int GetMaxC(){
        return maxC;
    }
    public void Move(int r, int c){
        row = r;
        col = c;
    }
}