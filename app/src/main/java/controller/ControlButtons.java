package controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import com.example.unitec.martianlander.R;

import model.AnimationModel;
import view.AnimationView;

/**
 * Created by Kay on 19/05/2016.
 */
public class ControlButtons implements View.OnClickListener {
    //--- Fields ---
    private Button buttonLeft, buttonUp, buttonRight,btnReset;
    private AnimationModel animModel;
    private AnimationView animView;
    //--- Constructor ---
    public ControlButtons(Activity activity, AnimationModel model, AnimationView animView) {
        animModel = model;
        buttonLeft = (Button) activity.findViewById(R.id.btnLeft);
        buttonUp = (Button) activity.findViewById(R.id.btnUp);
        buttonRight = (Button) activity.findViewById(R.id.btnRight);
        btnReset = (Button) activity.findViewById(R.id.btnRestart);
        buttonRight.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
        buttonUp.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        this.animView = animView;
    }

    //This method is used to set up the click listener of every button.
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRight:
                animModel.rtMoveRight();
                break;
            case R.id.btnLeft:
                animModel.rtMoveLeft();
                break;
            case R.id.btnUp:
                animModel.rtMoveUp();
                break;
            case R.id.btnRestart:
                animModel.reset();
        }
    }
}
