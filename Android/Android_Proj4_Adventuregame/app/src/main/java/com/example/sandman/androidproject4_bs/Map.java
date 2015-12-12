/*
 * Class: Map
 * Purpose: Hold the game map and current location. print surroundings as needed.
 */
package com.example.sandman.androidproject4_bs;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public class Map {
    private int yCoord;
    private int xCoord;
    private int screenHeight;
    private int screenWidth;
    private float screenDensity;
    private final int maxY = 19;
    private final int maxX = 19;
    private final char[][] map = new char[][]{
            "......m..~~~~~~~....".toCharArray(),
            "...~~mmm..~~~~~.....".toCharArray(),
            "....mmmm...~~~......".toCharArray(),
            "mmm.mmmm....~......~".toCharArray(),
            "mmm.mmm.........~~~~".toCharArray(),
            ".m...m..mmmm...~~~~~".toCharArray(),
            "........mmmmm..~~~~~".toCharArray(),
            "f.f.ff..mm....~~~~~~".toCharArray(),
            ".ff.f..mm......~~~~~".toCharArray(),
            ".fff..mmfff....~~~~~".toCharArray(),
            "fff..mmmfff...~~~~~~".toCharArray(),
            ".......mfff...~~~~~~".toCharArray(),
            "...fmm.fff...~~~~~~~".toCharArray(),
            "..ffmm.ffff.~~~~~~~~".toCharArray(),
            "ffffmm.....~~~~~~~~~".toCharArray(),
            "fmmmmmmff..~~~...~~~".toCharArray(),
            "fmmmmmff~~~~~~.m.~~~".toCharArray(),
            "~~mmmff~f..~~~...~~~".toCharArray(),
            "~~~m~~~ff..~~~~~~~~~".toCharArray(),
            "~~m~mmm.....~~~~~~~~".toCharArray()
    };
    Bitmap b_Forest;
    Bitmap b_Mountain;
    Bitmap b_Out;
    Bitmap b_Person;
    Bitmap b_Plain;
    Bitmap b_Water;
    /*
     * Method: Map (Default Constructor)
     * Input: Scanner object with the file already opened successfully.
     * Output: string containing maxHeight and maxLength, separated by a space.
     * Purpose: Pull map contents from given file and store in 2d array.
     */
    Map(int screenWidth, int screenHeight, float screenDensity, Context context) {
        b_Forest = BitmapFactory.decodeResource(context.getResources(), R.drawable.forest);
        b_Mountain = BitmapFactory.decodeResource(context.getResources(), R.drawable.mountain);
        b_Out = BitmapFactory.decodeResource(context.getResources(), R.drawable.out);
        b_Person = BitmapFactory.decodeResource(context.getResources(), R.drawable.person);
        b_Plain = BitmapFactory.decodeResource(context.getResources(), R.drawable.plain);
        b_Water = BitmapFactory.decodeResource(context.getResources(), R.drawable.water);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDensity = screenDensity;
        yCoord = 0;
        xCoord = 0;
    }
    /*
     * Method: Print
     * Input: an int containing current visibility. 0 = show current tile, 1 = show 1 tile radius, etc.
     * Output: None
     * Purpose: Print out current surroundings based on current visibility
     */
    public void Print(Canvas canvas) {
        int adjX = xCoord-3;
        int adjY = yCoord-5;
        Log.i("Current Location:", yCoord + "," + xCoord);
        for(int yVal = (yCoord-5); yVal < 30; yVal++) {
            for (int xVal = (xCoord-3); xVal < 30; xVal++) {
                if (yVal < 0 || yVal > 19 || xVal < 0 || xVal > 19) {
                    canvas.drawBitmap(b_Out, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                }
                else {
                    switch (map[yVal][xVal]) {
                        case 'm':
                            canvas.drawBitmap(b_Mountain, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                            break;
                        case '.':
                            canvas.drawBitmap(b_Plain, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                            break;
                        case '~':
                            canvas.drawBitmap(b_Water, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                            break;
                        case 'f':
                            canvas.drawBitmap(b_Forest, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                            break;
                        default:
                            canvas.drawBitmap(b_Out, (xVal-adjX) * 60, (yVal-adjY) * 60, null);
                            break;
                    }
                }
            }
        }
        canvas.drawBitmap(b_Person, 180, 300, null);
    }
/*        System.out.println("We are at location " + row + "," + col + " in terrain " + map[row][col]);
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
    */

/*    // First we need to erase everything we draw before.
    //canvas.drawColor(Color.BLACK);
    Bitmap imageBitmap = Bitmap.createBitmap(screenWidth,
            screenHeight, Bitmap.Config.ARGB_8888);

    float scale = screenDensity;
    Paint p = new Paint();
    p.setColor(Color.BLUE);
    p.setTextSize(24*scale);
    canvas.drawText("Hello", screenWidth/2,screenHeight/2,p);
    imageView.setImageBitmap(imageBitmap);
    //Paint paint = new Paint();
    //paint.setColor(Color.WHITE);
    //paint.setTextSize(20);

    //canvas.drawText("Game is running: " + gameTimeSec + " sec", screenWidth/4, screenHeight/2, paint);
*/

    // ***** Getters and Setters ***** //
    public int GetY(){
        return yCoord;
    }
    public int GetX(){
        return xCoord;
    }
    // Return max row value
    public int GetMaxY(){
        return maxY;
    }
    // Return max column value
    public int GetMaxX(){
        return maxX;
    }
    /*
     * Name: Move
     * Input: (Row, Column) location to move to. Must first
     *        check to verify that it is within the map
     * Output: void
     * Purpose: update character location
     */
    public void North(){
        yCoord--;
    }
    public void South(){
        yCoord++;
    }
    public void West(){
        xCoord--;
    }
    public void East() {
        xCoord++;
    }

}
