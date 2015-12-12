package com.example.sandman.android_proj7_adv_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Map {
    private int[] player = new int[]{0,0};
    private int[] treasure = new int[]{16,15};
    private int screenHeight;
    private int screenWidth;
    private float screenDensity;
    private final int maxY = 19;
    private final int maxX = 19;
    public final int north = 0;
    public final int east = 1;
    public final int south = 2;
    public final int west = 3;
    public final int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public final int y = 0;
    public final int x = 1;
    private int enemyCount = 0;
    public final int maxEnemies = 20;
    private List<List<Integer>> monsterLoc = new ArrayList<List<Integer>>(1);
    Random rand = new Random();
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
            "fmmmmmff~~~~~~.*.~~~".toCharArray(),
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
    Bitmap b_Treasure;
    Bitmap b_Enemy;
    /*
     * Method: Map (Default Constructor)
     * Input: Scanner object with the file already opened successfully.
     * Output: string containing maxHeight and maxLength, separated by a space.
     * Purpose: Pull map contents from given file and store in 2d array.
     */
    Map(int screenWidth, int screenHeight, float screenDensity, Context context) {
        b_Forest = BitmapFactory.decodeResource(context.getResources(), R.drawable.forest2);
        b_Mountain = BitmapFactory.decodeResource(context.getResources(), R.drawable.mountain2);
        b_Out = BitmapFactory.decodeResource(context.getResources(), R.drawable.out2);
        b_Person = BitmapFactory.decodeResource(context.getResources(), R.drawable.person2);
        b_Plain = BitmapFactory.decodeResource(context.getResources(), R.drawable.plain2);
        b_Water = BitmapFactory.decodeResource(context.getResources(), R.drawable.water2);
        b_Treasure = BitmapFactory.decodeResource(context.getResources(), R.drawable.treasure2);
        b_Enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenDensity = screenDensity;

    }
    public void NewGame() {
        player[y] = 0;
        player[x] = 0;
        if(enemyCount > 0) {
            monsterLoc.remove(x);
            monsterLoc.remove(y);
        }
        monsterLoc.add(new ArrayList<Integer>());
        monsterLoc.add(new ArrayList<Integer>());
        monsterLoc.get(y).add(treasure[y]);
        monsterLoc.get(x).add(treasure[x]);
        enemyCount = 1;
    }
    public Boolean HasWon() {
        if(player[y] == treasure[y] && player[x] == treasure[x])
            return true;
        return false;
    }
    public Boolean HasLost() {
        for(int i = 0; i < enemyCount; i++) {
            if(monsterLoc.get(y).get(i) == player[y] && monsterLoc.get(x).get(i) == player[x])
                return true;
        }
        return false;
    }

    /**
     * Adds a single enemy to the map in a random location. will keep trying until it finds a space with no monster or player;
     * will not exceed maxEnemies.
     */
    public void AddEnemy(int numberToAdd){
        if(enemyCount == maxEnemies)
            return;
        int tempY;
        int tempX;
        int totalRequested = enemyCount + numberToAdd;
        while(enemyCount < totalRequested) {
            tempY = rand.nextInt(maxY +1);
            tempX = rand.nextInt(maxX +1);
            for(int i = 0; i < enemyCount +1; i++){
                if(monsterLoc.get(y).get(i) == tempY && monsterLoc.get(x).get(i) == tempX)
                    break;
                if(tempY == player[y] && tempX == player[x])
                    break;
                if(i == enemyCount-1){
                    monsterLoc.get(y).add(tempY);
                    monsterLoc.get(x).add(tempX);
                    enemyCount++;
                    break;
                }
            }
        }
    }
    public void MoveEnemies(){
        LinkedList<Integer> directRemain = new LinkedList<Integer>();
        int rn;
        int dir;
        for(int i = 0; i < enemyCount; i++){
            directRemain.add(north);
            directRemain.add(east);
            directRemain.add(south);
            directRemain.add(west);
            for(int j = directRemain.size()-1; j > -1; j--) {
                if(j > 0)
                    rn = rand.nextInt(j);
                else if (j == 0)
                    rn = 0;
                else
                    break;
                dir = directRemain.get(rn);
                directRemain.remove(rn);
                if(MoveEnemy(i, dir))
                    break;
            }
        }
    }
    // return true if move successful, false if can't move there.
    private Boolean MoveEnemy(int enemy, int direct){
        int startY = monsterLoc.get(y).get(enemy);
        int startX = monsterLoc.get(x).get(enemy);
        int newY = startY + directions[direct][y];
        int newX = startX + directions[direct][x];
        for (List<Integer> aMonsterLoc : monsterLoc) {
            if (aMonsterLoc.get(y) == newY && aMonsterLoc.get(x) == newX)
                return false;
            if(newY > maxY-1 || newY < 0 || newX > maxX-1 || newX < 0)
                return false;
        }
        monsterLoc.get(y).set(enemy, newY);
        monsterLoc.get(x).set(enemy, newX);
        return true;
    }
    /*
     * Method: Print
     * Input: an int containing current visibility. 0 = show current tile, 1 = show 1 tile radius, etc.
     * Output: None
     * Purpose: Print out current surroundings based on current visibility
     */
    public void Print(Canvas canvas) {
        int adjX = player[x] - 3;
        int adjY = player[y] - 5;
        for (int yVal = (player[y] - 5); yVal < 30; yVal++) {
            for (int xVal = (player[x] - 3); xVal < 30; xVal++) {
                if (yVal < 0 || yVal > 19 || xVal < 0 || xVal > 19) {
                    canvas.drawBitmap(b_Out, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                } else {
                    switch (map[yVal][xVal]) {
                        case 'm':
                            canvas.drawBitmap(b_Mountain, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                        case '.':
                            canvas.drawBitmap(b_Plain, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                        case '~':
                            canvas.drawBitmap(b_Water, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                        case 'f':
                            canvas.drawBitmap(b_Forest, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                        case '*':
                            canvas.drawBitmap(b_Treasure, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                        default:
                            canvas.drawBitmap(b_Out, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                            break;
                    }
                    for(int i = 0; i < enemyCount; i++){
                        if(monsterLoc.get(y).get(i) == yVal && monsterLoc.get(x).get(i) == xVal)
                            canvas.drawBitmap(b_Enemy, (xVal - adjX) * 60, (yVal - adjY) * 60, null);
                    }
                }
            }
        }
        canvas.drawBitmap(b_Person, 180, 300, null);
    }

    // ***** Getters and Setters ***** //
    public int GetY(){
        return player[y];
    }
    public int GetX(){
        return player[x];
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
        player[y]--;
    }
    public void South(){
        player[y]++;
    }
    public void West(){
        player[x]--;
    }
    public void East() {
        player[x]++;
    }

}
