package com.example.unitec.martianlander;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by Kay on 08/04/2016.
 */
public class AnimationView extends SurfaceView implements SurfaceHolder.Callback{

    public AnimationThread mAnimThread;
    public Rocket mRocket;
    private Mars mars;
    public Fuel mFuel;


    public AnimationView(Context context) {
        super(context);
        getHolder().addCallback(this);
        mAnimThread = new AnimationThread(this);
        mRocket = new Rocket(context);
        mars = new Mars(context);
        mFuel = new Fuel(context);
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        mAnimThread = new AnimationThread(this);
        mRocket = new Rocket(context);
        mars = new Mars(context);
        mFuel = new Fuel(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mAnimThread.setRunning(true);
        mAnimThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                mAnimThread.join();
                retry = false;//stop trying if successful
            }catch(InterruptedException e){
                //try again shutting down the thread
            }
        }
    }

    /**
     * Implement this method to handle touch screen motion events.
     *
     * @param event The motion event.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
            if(x>mRocket.bmpPosX){
                rtMoveRight();
            }else if (x<mRocket.bmpPosX+50){
                rtMoveLeft();
            }else{
                rtMoveUp();
            }

        return true;  // Signal that we are handling this touch
    }

    //--update and render methods
    public void update(){
        mRocket.moveDown();
            checkEnd();
    }

    public void rtMoveRight(){
        if(mFuel.checkFuel()){
            mRocket.moveRight();
            mFuel.used();}
    }

    public void rtMoveLeft(){
        if(mFuel.checkFuel()){
            mRocket.moveLeft();
            mFuel.used();}
    }

    public void rtMoveUp(){
        if(mFuel.checkFuel()){
            mRocket.moveUp();
            mFuel.used();}
    }

    public void render(Canvas canvas){
        mRocket.draw(canvas);
        mars.draw(canvas);
        mFuel.draw(canvas);
    }

    public void checkEnd() {
        for(int i = 0;i<14;i++) {
            if (hitMars(i)) {
                mAnimThread.setRunning(false);
            }
        }
    }

    public boolean hitMars(int i){
        if((mRocket.bmpPosX >= mars.getXLand(i) && mRocket.bmpPosX <= mars.getXLand(i + 1) &&
                (mRocket.bmpPosY+53 >= mars.getRocketY(i,mRocket.bmpPosX)))|
                (mRocket.bmpPosX+52 >= mars.getXLand(i) && mRocket.bmpPosX +52<= mars.getXLand(i + 1) &&
                        (mRocket.bmpPosY+53 >= mars.getRocketY(i,mRocket.bmpPosX+52)))){
            return true;
        }else{return false;}
    }

}
