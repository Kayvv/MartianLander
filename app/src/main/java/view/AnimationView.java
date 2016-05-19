package view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import model.AnimationModel;



/**
 * Created by Kay on 08/04/2016.
 */
public class AnimationView extends SurfaceView implements SurfaceHolder.Callback{

    public AnimationThread mAnimThread;
    public AnimationModel animModel;
    public Boolean end = false;

    //--- Constructor ---

    /**
     * This constructor is for the AnimationView be called in MainActivity.
     * @param context The context here is main activity.
     */
    public AnimationView(Context context) {
        super(context);
        getHolder().addCallback(this);
        mAnimThread = new AnimationThread(this);
    }

    /**
     * This constructor is used for layout, that's why it has two params.
     * @param context The context
     * @param attrs The attribute set
     */
    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        mAnimThread = new AnimationThread(this);
    }

    /**
     * This method will be called when surface created
     * Added the if condition to make sure when user back
     * to desktop, the game can remain.
     * @param holder holder contain the data.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(mAnimThread.getState()== Thread.State.TERMINATED){
            mAnimThread = new AnimationThread(this);
            mAnimThread.setRunning(true);
            mAnimThread.start();
        }else {
            mAnimThread.setRunning(true);
            mAnimThread.start();
        }
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
            if(x>animModel.mRocket.bmpPosX){
                animModel.rtMoveRight();
            }else if (x<animModel.mRocket.bmpPosX+50){
                animModel.rtMoveLeft();
            }else{
                animModel.rtMoveUp();
            }
        return true;  // Signal that we are handling this touch
    }

    //--- Control the animation ---
    public void startAnimation() {
        mAnimThread.setRunning(true);
    }
    public void stopAnimation() {
        mAnimThread.setRunning(false);
    }

    /**
     * This method is used to set up the Model
     * @param model the Model that need to link to the View
     */
    public void setModel(AnimationModel model){
        this.animModel = model;
    }

    //--update and render methods
    public void update(){
        animModel.moveDown();
        checkEnd();
    }
    public void render(Canvas canvas){
        animModel.mRocket.draw(canvas);
        animModel.mars.draw(canvas);
        animModel.mFuel.draw(canvas);
    }
    //To check if the rocket hit the Mars
    public void checkEnd() {
        for(int i = 0;i<14;i++) {
            if (animModel.hitMars(i)) {
                stopAnimation();
                end = true;
            }
        }
    }
}
