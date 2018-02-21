package com.omfgdev.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import android.graphics.Canvas;

/**
 * Created by omfg on 21.02.18.
 */

public class GamePlayScene implements Scene {

    private Rect r = new Rect();

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private boolean movingPlayer = false;
    private boolean gameover = false;
    private long gameOverTime;

    public GamePlayScene(){
        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_WIDTH/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
    }

    public void reset(){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,3*Constants.SCREEN_WIDTH/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
        movingPlayer = false;


    }
    @Override
    public void update() {
        if(!gameover) {
            player.update(playerPoint);
            obstacleManager.update();
            if(obstacleManager.playerCollide(player)){
                gameover = true;
                gameOverTime = System.currentTimeMillis();
            }
        }

    }
    private void drawCenterText(android.graphics.Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        if(gameover){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            drawCenterText(canvas,paint,"GAME OVER");

        }
    }

    @Override
    public void terminate() {


      SceneManager.ACTIVE_SCENE = 0;

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LOG","DOWN");
                if (!gameover && player.getRectangle().contains((int) event.getX(), (int) event.getX()))
                    movingPlayer = true;
                if (gameover && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameover = false;

                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LOG","MOVE");
                if(!gameover&& movingPlayer)
                    playerPoint.set((int) event.getX(), (int) event.getY());
                Log.d("LOG"," "+(int) event.getX()+" "+ (int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                Log.d("LOG","UP");
                movingPlayer = false;
                break;
    }
}
}
