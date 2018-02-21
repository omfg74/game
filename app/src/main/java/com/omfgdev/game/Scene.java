package com.omfgdev.game;

import android.view.MotionEvent;

import android.graphics.Canvas;

/**
 * Created by omfg on 21.02.18.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event) ;

}
