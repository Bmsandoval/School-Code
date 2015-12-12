package com.example.sandman.androidproject4_bs;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

/**
 * Created by Sandman on 10/22/2014.
 */
public class GameChar extends View {
    Map map;
    public static int screenWidth;
    public static int screenHeight;
    public static float screenDensity;

    public GameChar(Context context) {
        super(context);
        GameChar.screenWidth = getResources().getDisplayMetrics().widthPixels;
        GameChar.screenHeight = getResources().getDisplayMetrics().heightPixels;
        GameChar.screenDensity = getResources().getDisplayMetrics().density;
        map = new Map(screenWidth, screenHeight, screenDensity, context);

        // this is the view on which you will listen for touch events
//        final View touchView = findViewById(R.id.touchView);
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    float X = event.getX();
                    float Y = event.getY();
                    if (Y >= (screenHeight/4)*3) { // South
                        if (map.GetY() < map.GetMaxY()) {
                            map.South();
                            invalidate();
                        }
                    } else if (Y <= (screenHeight/4)) { // North
                        if (map.GetY() > 0) {
                            map.North();
                            invalidate();
                        }
                    } else if (X >= screenWidth/2) { // East
                        if (map.GetX() < map.GetMaxX()) {
                            map.East();
                            invalidate();
                        }
                    } else if (X <= screenWidth/2) { // West
                        if (map.GetX() > 0) {
                            map.West();
                            invalidate();
                        }
                    }
                }
                return true;
            }
        });
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

