package com.example.randomchatapplication.activites.camera;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityCameraBinding;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;

public class CameraViewModel extends BaseViewModel {

    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt settingsButtonMarginTop = new ObservableInt();
    public ObservableInt timeButtonsMarginTop = new ObservableInt();
    public ObservableBoolean timeButtonsVisibility = new ObservableBoolean(false);
    private Button timeButton;
    private Button swapButton;
    private Button flashButton;
    private Button time2Button;
    private Button time5Button;
    private Button time10Button;
    private float translateDimension;
    private float translateCurveDimension;
    private float translateTimeDimension;

    private boolean timeState = false;
    private boolean state = false;

    public void init(int windowNavigationSize, int statusBarHeight){
        timeButton = ((ActivityCameraBinding)getBinding()).timeButton;
        swapButton = ((ActivityCameraBinding)getBinding()).swapButton;
        flashButton = ((ActivityCameraBinding)getBinding()).flashButton;
        translateDimension =  DimensionsHelper.convertDpToPixel(60, getActivity().getApplicationContext());
        translateTimeDimension = DimensionsHelper.convertDpToPixel(53, getActivity().getApplicationContext());
        translateCurveDimension = DimensionsHelper.convertDpToPixel((float) ( 30* Math.sqrt(2)), getActivity().getApplicationContext());
        navigationHeight.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(12, getActivity().getApplicationContext())));
        this.statusBarHeight.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(6, getActivity().getApplicationContext())));
        settingsButtonMarginTop.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(13, getActivity().getApplicationContext())));
        timeButtonsMarginTop.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(73, getActivity().getApplicationContext())));
        time2Button = ((ActivityCameraBinding)getBinding()).timeButton2;
        time5Button = ((ActivityCameraBinding)getBinding()).timeButton5;
        time10Button = ((ActivityCameraBinding)getBinding()).timeButton10;
    }



    public void onSettingsClick(){
        if (!state) {
            openSettings();
        } else {
            closeSettings();
        }
        state = !state;
    }

    private void openSettings(){
        translateAnimation(0, -translateDimension,0,0, flashButton);
        translateAnimation(0, -translateCurveDimension,0,translateCurveDimension, swapButton);
        translateAnimation(0, 0, 0, translateDimension, timeButton);
    }

    private void closeSettings(){
        if(timeState){
            closeClock();
            timeState=false;
        }
        if(timeButtonsVisibility.get())
            timeButtonsVisibility.set(false);
        translateAnimation( 0,0,0,0, flashButton);
        translateAnimation( 0,0,0,0, swapButton);
        translateAnimation(0, 0,  0,0, timeButton);
    }

    private void translateAnimation(float fromXDelta, float xToDelta,float fromYDelta, float yToDelta, Button button) {
        button.animate().translationX(xToDelta-fromXDelta).translationY(yToDelta-fromYDelta).setDuration(250).start();
    }

    public void onClose(){
        getActivity().onBackPressed();
    }

    public void onFlashClick(){

    }

    public void onSwapClick(){

    }

    public void onTimeClick(){
        Log.d("onTimeClick: ", "halo");
        Log.d("onTimeClick: ", String.valueOf(timeState));
        if (!timeState) {
            openClock();
        } else {
            closeClock();
        }
        timeState = !timeState;
    }

    private void openClock(){
        timeButtonsVisibility.set(true);
        translateAnimation(0,0, 0, translateTimeDimension, time2Button);
        translateAnimation(0, 0, 0 , translateTimeDimension*2, time5Button);
        translateAnimation(0, 0, 0, translateTimeDimension*3, time10Button);
    }
    private void closeClock(){
        translateAnimation(0,0, 0,0, time2Button);
        translateAnimation(0, 0,  0,0, time5Button);
        translateAnimation(0, 0, 0,0, time10Button);
    }



}
