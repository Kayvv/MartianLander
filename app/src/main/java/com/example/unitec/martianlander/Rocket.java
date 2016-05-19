package com.example.unitec.martianlander;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;

import java.util.Random;

/**
 * Created by Kay on 08/04/2016.
 */
public class Rocket {

    public Bitmap rocketImage;
    public Bitmap thrusterImage;
    public Bitmap flameImage;
    public int bmpPosX,bmpPosY;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    Random random = new Random();

    public Rocket(Context context){
        rocketImage = BitmapFactory.decodeResource(context.getResources(),R.mipmap.craftmain);
        thrusterImage = BitmapFactory.decodeResource(context.getResources(),R.mipmap.thruster);
        flameImage = BitmapFactory.decodeResource(context.getResources(),R.mipmap.main_flame);
        bmpPosX=random.nextInt(1000);
        bmpPosY=0;
    }

    public void moveDown(){
        bmpPosY++;
    }

    public void moveLeft(){
        bmpPosX-=10;
        left = true;
    }

    public void moveRight(){
        bmpPosX+=10;
        right = true;
    }

    public void moveUp(){
        bmpPosY-=10;
        up = true;
    }

    public void draw(Canvas canvas){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(rocketImage, bmpPosX, bmpPosY, null);
        if(left) {
            canvas.drawBitmap(thrusterImage, bmpPosX + 51, bmpPosY + 52, null);
            left = false;
        }
        if(right){
            canvas.drawBitmap(thrusterImage, bmpPosX, bmpPosY + 52, null);
            right = false;
        }
        if(up){
            canvas.drawBitmap(flameImage,bmpPosX+21,bmpPosY+52,null);
            up = false;
        }
    }
}
