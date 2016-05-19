package com.example.unitec.martianlander;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Kay on 13/05/2016.
 */
public class Fuel {
    public Paint paint,casePaint,textPaint;
    public int x;
    public int usedFuel = 0;

    public Fuel(Context context){
        x=500;
        paint= new Paint();
        paint.setColor(Color.YELLOW);
        casePaint= new Paint();
        casePaint.setColor(Color.WHITE);
        casePaint.setStyle(Paint.Style.STROKE);
        casePaint.setStrokeWidth(5);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
    }

    public void used(){
        usedFuel += 10;
    }

    public boolean checkFuel(){
        if(x-usedFuel<=100){
        return false;
        }else{return true;}
    }

    public void draw(Canvas canvas){
        canvas.drawText("Fuel：", 20, 40, textPaint);
        canvas.drawRect(x - usedFuel, 15, 100, 40, paint);
        canvas.drawRect(x, 15, 100, 40, casePaint);
    }
}
