package com.example.unitec.martianlander;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public AnimationView animationView;
    public Button btnRight,btnLeft,btnUp,btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setButton();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
//land
        }
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
//port
        }
    }

    public void setButton(){
        animationView = (AnimationView)findViewById(R.id.animationView);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnRight.setOnClickListener(this);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(this);
        btnUp = (Button)findViewById(R.id.btnUp);
        btnUp.setOnClickListener(this);
        btnReset = (Button)findViewById(R.id.btnRestart);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRight:
                animationView.rtMoveRight();
                break;
            case R.id.btnLeft:
                animationView.rtMoveLeft();
                break;
            case R.id.btnUp:
                animationView.rtMoveUp();
                break;
            case R.id.btnRestart:
        }
    }
}
