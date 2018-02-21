package com.omfgdev.game;

import android.view.MotionEvent;

import android.graphics.Canvas;
import java.util.ArrayList;

/**
 * Created by omfg on 21.02.18.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager(){
        ACTIVE_SCENE = 0;
        scenes.add(new GamePlayScene());


    }
    public void recieveTouch(MotionEvent event){

        scenes.get(ACTIVE_SCENE).recieveTouch(event);


    }
   public void draw(Canvas canvas){

        scenes.get(ACTIVE_SCENE).update();
   }
   public void update(){
       scenes.get(ACTIVE_SCENE).update();
   }

}
