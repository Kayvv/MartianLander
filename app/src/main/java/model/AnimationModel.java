package model;

import android.content.Context;

/**
 * Created by Kay on 19/05/2016.
 */
public class AnimationModel {

    public Rocket mRocket;
    public Mars mars;
    public Fuel mFuel;
    //--- Constructor ---
    public AnimationModel(Context context){
        mRocket = new Rocket(context);
        mars = new Mars(context);
        mFuel = new Fuel(context);
    }
    //This is the method to control the rocket move down
    public void moveDown(){
        mRocket.moveDown();
    }
    //This is the method to control the rocket move right
    public void rtMoveRight(){
        if(mFuel.checkFuel()){
            mRocket.moveLeft();
            mFuel.used();}
    }
    //This is the method to control the rocket move left
    public void rtMoveLeft(){
        if(mFuel.checkFuel()){
            mRocket.moveRight();
            mFuel.used();}
    }
    //This is the method to control the rocket move up
    public void rtMoveUp(){
        if(mFuel.checkFuel()){
            mRocket.moveUp();
            mFuel.used();}
    }

    /***
     * This method is used to check while the rocket hit the mars.
     * @param i this is an int to find out where the rocket is.
     * @return true or false determine the rocket hit the mars or not.
     */
    public boolean hitMars(int i){
        if((mRocket.bmpPosX >= mars.getXLand(i) && mRocket.bmpPosX <= mars.getXLand(i + 1) &&
                (mRocket.bmpPosY+53 >= mars.getRocketY(i,mRocket.bmpPosX)))|
                (mRocket.bmpPosX+52 >= mars.getXLand(i) && mRocket.bmpPosX +52<= mars.getXLand(i + 1) &&
                        (mRocket.bmpPosY+53 >= mars.getRocketY(i,mRocket.bmpPosX+52)))){
            return true;
        }else if(mRocket.bmpPosX >= mars.getXLand(i) && mRocket.bmpPosX <= mars.getXLand(i + 1) &&
                mRocket.bmpPosX+52 >= mars.getXLand(i+1) && mRocket.bmpPosX +52<= mars.getXLand(i + 2)
                &&mRocket.bmpPosY+53 >= mars.getYLand(i + 1)){
            return true;
        }else{return false;}
    }

    /**
     * This method is used to reset the value of rocket and fuel.
     */
    public void reset(){
        mRocket.bmpPosX=mRocket.startX;
        mRocket.bmpPosY=0;
        mFuel.usedFuel = 0;
    }
}
