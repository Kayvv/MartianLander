package model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;

import com.example.unitec.martianlander.R;

import java.util.Random;

/**
 * Created by Kay on 08/04/2016.
 */
public class Rocket {

    public Bitmap rocketImage,thrusterImage,flameImage;
    public int bmpPosX,bmpPosY,startX;
    public String direction = "down";
    Random random = new Random();
    //--- Constructor ---
    public Rocket(Context context){
        rocketImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.craftmain);
        thrusterImage = BitmapFactory.decodeResource(context.getResources(),R.mipmap.thruster);
        flameImage = BitmapFactory.decodeResource(context.getResources(),R.mipmap.main_flame);
        bmpPosX=random.nextInt(1000);
        startX = bmpPosX;
        bmpPosY=0;
    }

    //This four methods are used to change the rocket's movement.
    public void moveDown(){
        bmpPosY++;
        direction = "down";
    }
    public void moveLeft(){
        bmpPosX-=10;
        direction = "left";
    }
    public void moveRight(){
        bmpPosX+=10;
        direction = "right";
    }
    public void moveUp(){
        bmpPosY-=10;
        direction = "up";
    }

    //This method is used to draw the rocket.
    public void draw(Canvas canvas){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(rocketImage, bmpPosX, bmpPosY, null);
        switch (direction){
            case "left":
                canvas.drawBitmap(thrusterImage, bmpPosX + 51, bmpPosY + 52, null);
                break;
            case "right":
                canvas.drawBitmap(thrusterImage, bmpPosX, bmpPosY + 52, null);
                break;
            case "up":
                canvas.drawBitmap(flameImage,bmpPosX+21,bmpPosY+52,null);
                break;
        }
    }
}
