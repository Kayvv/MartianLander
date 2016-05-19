package com.example.unitec.martianlander;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import controller.ControlButtons;
import model.AnimationModel;
import view.AnimationView;

public class MainActivity extends AppCompatActivity{
    public AnimationView mView;
    public AnimationModel mModel;
    public ControlButtons mControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
    }
    // Instantiate MVC components - view from layout file
    public void init(){
        mModel = new AnimationModel(this);
        mView = (AnimationView)findViewById(R.id.animationView);
        mView.setModel(mModel);
        mControls = new ControlButtons(this, mModel, mView);
    }

    @Override
    public void onPause(){
        super.onPause();
        mView.stopAnimation();
    }

    @Override
    public void onResume(){
        super.onResume();
        mView.startAnimation();
    }

}
