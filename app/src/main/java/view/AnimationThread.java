package view;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


/**
 * Created by Kay on 08/04/2016.
 */
public class AnimationThread extends Thread{
    //--field--
    private AnimationView mAnimView;
    private SurfaceHolder mHolder;
    private boolean mRunning = false;
    Canvas canvas;
    //--- Constructor ---
    public AnimationThread(AnimationView animView){
        super();
        this.mAnimView = animView;
        this.mHolder = animView.getHolder();
    }

    /**
     * By using this method, we can end or pause the game in other classes.
     * @param running to change this can start or end the game.
     */
    public void setRunning(boolean running){
        this.mRunning = running;
    }

    /**
     * The main method of Thread. Will be called when the game started.
     */
    @Override
    public void run(){
        super.run();
        while(mRunning){
            canvas = null;//we will get the canvas from the holder
            //try to update animation status
            try{
                canvas = mHolder.lockCanvas();
                synchronized (mHolder){
                    mAnimView.render(canvas);
                    mAnimView.update();
                }
            }
            finally {
                if(canvas!=null){
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }//end while
    }//end run
}
