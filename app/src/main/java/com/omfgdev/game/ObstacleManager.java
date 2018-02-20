package com.omfgdev.game;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by omfg7 on 18.02.2018.
 */

public class ObstacleManager {

    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private long startTime;

    public ObstacleManager(int playerGap, int obstaleGap, int obstacleHeight, int color) {
        this.playerGap = playerGap;
        this.obstacleGap = obstaleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        populateObsticles();

    }

    public boolean playerCollide(RectPlayer player){
        for (Obstacle ob:obstacles) {
            if (ob.playerCollide(player))
            return true;
        }
        return false;
    }
    private void populateObsticles() {
        int currY = -5 * Constants.SCREEN_HEIGHT / 4;
        while (currY < 0) {

            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH) - playerGap);
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;

        }
    }

    public void update() {
        int elapsedTIme = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT / 10000.0f;
        for (Obstacle ob : obstacles
                ) {
            ob.incrementY(speed * elapsedTIme);

        }
        if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int)(Math.random() * (Constants.SCREEN_WIDTH) - playerGap);
            obstacles.add(0, new Obstacle(obstacleHeight
                    , color, xStart
                    , obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap
                    , playerGap));
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}

