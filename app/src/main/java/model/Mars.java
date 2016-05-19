package model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.Log;

import com.example.unitec.martianlander.R;

import java.util.Random;

/**
 * Created by Kay on 10/04/2016.
 */
public class Mars {

    //--field--
    Paint paint = new Paint();
    Path path;
    //A Bitmap object that is going to be passed to the BitmapShader
    public Bitmap marsImage;
    //The shader that renders the Bitmap
    private BitmapShader marsImageShader;
    private int xPolygon[];
    private int yPolygon[];
    private int savePlace;
    Random random = new Random();

    public Mars(Context context){
        marsImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.mars);
        marsImageShader = new BitmapShader(marsImage, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        xPolygon = new int[14];
        yPolygon = new int[14];
        setLand();
    }
// This method is used to set all the point of the Mars surface
    public void setLand(){
        for(int i = 0;i<14;i++){
            int j = random.nextInt(50)+90*i-50;
            xPolygon[i] =j;
            yPolygon[i]= random.nextInt(230)+400;
        }
        //To set the save place
        savePlace = random.nextInt(9)+2;
        yPolygon[savePlace]= yPolygon[savePlace-1];
    }
//This two methods are used to get the current x and y point of the land.
    public int getXLand(int i){
        return xPolygon[i];
    }
    public int getYLand(int i){
        return yPolygon[i];
    }
//This method is to find out the current y point according to the rocket's x point
    public int getRocketY(int i,int rocketX){
        int rocketY;
        int rates=(getXLand(i+1)-rocketX)*(getYLand(i) - getYLand(i + 1))/(getXLand(i+1)-getXLand(i));
        rocketY = getYLand(i+1)+rates;
        return rocketY;
    }
//This method is used to draw the Mars.
    public void draw(Canvas canvas) {
        path = new Path();
        path.moveTo(0, 780);
        for(int i= 0; i<xPolygon.length; i++){
            path.lineTo(xPolygon[i], yPolygon[i]);
        }
        path.lineTo(1280, 780);
        paint.setShader(marsImageShader);
        canvas.drawPath(path,paint);
    }
}
