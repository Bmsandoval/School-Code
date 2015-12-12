package com.example.sandman.android_proj7_adv_v2;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Sandman on 10/22/2014.
 */
public class GameChar extends View {
    Map map;
    public static int screenWidth;
    public static int screenHeight;
    public static float screenDensity;
    private int EnemyStartCount = 0;
    private int Successes;
    Context thisContext;
    public GameChar(Context context, int diff) {
        super(context);
        thisContext = context;
        GameChar.screenWidth  = getResources().getDisplayMetrics().widthPixels;
        GameChar.screenHeight = getResources().getDisplayMetrics().heightPixels;
        GameChar.screenDensity = getResources().getDisplayMetrics().density;
        map = new Map(screenWidth, screenHeight, screenDensity, context);
        EnemyStartCount = diff;
        GameStart();
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    float X = event.getX();
                    float Y = event.getY();
                    if (Y >= (screenHeight / 4) * 3) { // South
                        if (map.GetY() < map.GetMaxY()) {
                            map.South();
                        }
                    } else if (Y <= (screenHeight / 4)) { // North
                        if (map.GetY() > 0) {
                            map.North();
                        }
                    } else if (X >= screenWidth / 2) { // East
                        if (map.GetX() < map.GetMaxX()) {
                            map.East();
                        }
                    } else if (X <= screenWidth / 2) { // West
                        if (map.GetX() > 0) {
                            map.West();
                        }
                    }
                    if (map.HasLost()) {
                        Toast.makeText(thisContext, "OH NOESSSSSS, you lost :( you should try again!",
                                Toast.LENGTH_LONG).show();
                        Successes = 0;
                        GameStart();
                    } else if (map.HasWon()) {
                        Toast.makeText(thisContext, "Grats, you can accomplish something! Can you do so twice?",
                                Toast.LENGTH_LONG).show();
                        Successes++;
                        GameStart();
                    } else
                        map.MoveEnemies();
                    if (map.HasLost()) {
                        Toast.makeText(thisContext, "OH NOESSSSSS, you lost :( you should try again!",
                                Toast.LENGTH_LONG).show();
                        Successes = 0;
                        GameStart();
                    }
                    invalidate();
                }

                return true;
            }
        });
    }
    private void GameStart() {
        map.NewGame();
        map.AddEnemy(EnemyStartCount+(Successes*3)-1);
        invalidate();
    }
    /**
     * Draw the game to the screen.
     *
     * @param canvas Canvas on which we will draw.
     */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        map.Print(canvas);
    }
}

