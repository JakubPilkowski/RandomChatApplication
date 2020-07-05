package com.example.randomchatapplication.ui.camera.camera;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;

import androidx.camera.core.ImageCapture;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.CameraFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;

public class CameraFragmentViewModel extends BaseViewModel {
    //Tooltipy
    public ObservableInt closeTooltip = new ObservableInt(R.string.close);
    public ObservableInt optionsTooltip = new ObservableInt(R.string.options);
    public ObservableInt flashTooltip = new ObservableInt(R.string.flash);
    public ObservableInt swapTooltip = new ObservableInt(R.string.swap);
    public ObservableInt selfTimerTooltip = new ObservableInt(R.string.selfTimer);
    public ObservableInt resetSelfTimerTooltip = new ObservableInt(R.string.selfTimerReset);
    public ObservableInt twoSecSeltTimerTooltip = new ObservableInt(R.string.selfTimerTwoSec);
    public ObservableInt fiveSecSeltTimerTooltip = new ObservableInt(R.string.selfTimerFiveSec);
    public ObservableInt tenSecSeltTimerTooltip = new ObservableInt(R.string.selfTimerTenSec);
    public ObservableInt photoTooltip = new ObservableInt(R.string.photo);


    //Reszta
    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt settingsButtonMarginTop = new ObservableInt();
    public ObservableInt timeButtonsMarginTop = new ObservableInt();
    public ObservableBoolean timeButtonsVisibility = new ObservableBoolean(false);
    public ObservableInt timeColorReset = new ObservableInt(R.color.gold);
    public ObservableInt timeColor2 = new ObservableInt(R.color.colorPrimary);
    public ObservableInt timeColor5 = new ObservableInt(R.color.colorPrimary);
    public ObservableInt timeColor10 = new ObservableInt(R.color.colorPrimary);
    public ObservableBoolean delayTextVisibility = new ObservableBoolean(false);
    public ObservableField<String> delayText = new ObservableField<>();
    private Button timeButton;
    private Button swapButton;
    private Button flashButton;
    private Button time2Button;
    private Button time5Button;
    private Button time10Button;
    private Button timeButtonReset;
    private float translateDimension;
    private float translateCurveDimension;
    private float translateTimeDimension;
    private Drawable flash_on;
    private Drawable flash_off;
    public ObservableField<Drawable> flashDrawable = new ObservableField<>();

    public ObservableBoolean flashState = new ObservableBoolean(false);
    public ObservableBoolean timeState = new ObservableBoolean(false);
    public ObservableBoolean state = new ObservableBoolean(false);

    private int delay = 0;

    public void init(int flashMode) {
        int windowNavigationSize = ScreenHelper.getNavBarHeight(getFragment().getContext());
        int statusBarHeight = ScreenHelper.getStatusBarHeight(getFragment().getContext());
        timeButton = ((CameraFragmentBinding) getBinding()).timeButton;
        swapButton = ((CameraFragmentBinding) getBinding()).swapButton;
        flashButton = ((CameraFragmentBinding) getBinding()).flashButton;
        translateDimension = DimensionsHelper.convertDpToPixel(60, getActivity().getApplicationContext());
        translateTimeDimension = DimensionsHelper.convertDpToPixel(53, getActivity().getApplicationContext());
        translateCurveDimension = DimensionsHelper.convertDpToPixel((float) (30 * Math.sqrt(2)), getActivity().getApplicationContext());
        navigationHeight.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(12, getActivity().getApplicationContext())));
        this.statusBarHeight.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(6, getActivity().getApplicationContext())));
        settingsButtonMarginTop.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(13, getActivity().getApplicationContext())));
        timeButtonsMarginTop.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(73, getActivity().getApplicationContext())));
        timeButtonReset = ((CameraFragmentBinding) getBinding()).timeButtonReset;
        time2Button = ((CameraFragmentBinding) getBinding()).timeButton2;
        time5Button = ((CameraFragmentBinding) getBinding()).timeButton5;
        time10Button = ((CameraFragmentBinding) getBinding()).timeButton10;
        flash_on = getActivity().getResources().getDrawable(R.drawable.camera_flash_on_button_ripple);
        flash_off = getActivity().getResources().getDrawable(R.drawable.camera_flash_off_button);
        flashDrawable.set(flashMode == ImageCapture.FLASH_MODE_ON ? flash_on : flash_off);
    }


    public void onSettingsClick() {
        if (!state.get()) {
            openSettings();
        } else {
            closeSettings();
        }
        state.set(!state.get());
    }

    private void openSettings() {
        translateAnimation(0, -translateDimension, 0, 0, flashButton);
        translateAnimation(0, -translateCurveDimension, 0, translateCurveDimension, swapButton);
        translateAnimation(0, 0, 0, translateDimension, timeButton);
    }

    private void closeSettings() {
        if (timeState.get()) {
            closeClock();
            timeState.set(false);
        }
        if (timeButtonsVisibility.get())
            timeButtonsVisibility.set(false);
        translateAnimation(0, 0, 0, 0, flashButton);
        translateAnimation(0, 0, 0, 0, swapButton);
        translateAnimation(0, 0, 0, 0, timeButton);
    }

    private void translateAnimation(float fromXDelta, float xToDelta, float fromYDelta, float yToDelta, Button button) {
        button.animate().translationX(xToDelta - fromXDelta).translationY(yToDelta - fromYDelta).setDuration(250).start();
    }

    public void onClose() {
        getActivity().onBackPressed();
    }

    public void onFlashClick() {
        flashState.set(!flashState.get());
        if (flashState.get()) flashDrawable.set(flash_on);
        else flashDrawable.set(flash_off);
        ((CameraFragment) getFragment()).toggleFlash();
    }

    public void onSwapClick() {
        ((CameraFragment) getFragment()).swapCamera();
        onSettingsClick();
    }

    public void onTimeClick() {
        if (!timeState.get()) {
            openClock();
        } else {
            closeClock();
        }
        timeState.set(!timeState.get());
    }

    private void openClock() {
        timeButtonsVisibility.set(true);
        translateAnimation(0, 0, 0, translateTimeDimension, timeButtonReset);
        translateAnimation(0, 0, 0, translateTimeDimension * 2, time2Button);
        translateAnimation(0, 0, 0, translateTimeDimension * 3, time5Button);
        translateAnimation(0, 0, 0, translateTimeDimension * 4, time10Button);
    }

    private void closeClock() {
        translateAnimation(0, 0, 0, 0, timeButtonReset);
        translateAnimation(0, 0, 0, 0, time2Button);
        translateAnimation(0, 0, 0, 0, time5Button);
        translateAnimation(0, 0, 0, 0, time10Button);
    }

    public void onResetDelayClick() {
        delay = 0;
        timeColorReset.set(R.color.gold);
        timeColor2.set(R.color.colorPrimary);
        timeColor5.set(R.color.colorPrimary);
        timeColor10.set(R.color.colorPrimary);
        onTimeClick();
    }

    public void on2SecDelayClick() {
        delay = 2000;
        timeColorReset.set(R.color.colorPrimary);
        timeColor2.set(R.color.gold);
        timeColor5.set(R.color.colorPrimary);
        timeColor10.set(R.color.colorPrimary);
        onTimeClick();
    }

    public void on5SecDelayClick() {
        delay = 5000;
        timeColorReset.set(R.color.colorPrimary);
        timeColor2.set(R.color.colorPrimary);
        timeColor5.set(R.color.gold);
        timeColor10.set(R.color.colorPrimary);
        onTimeClick();
    }

    public void on10SecDelayClick() {
        delay = 10000;
        timeColorReset.set(R.color.colorPrimary);
        timeColor2.set(R.color.colorPrimary);
        timeColor5.set(R.color.colorPrimary);
        timeColor10.set(R.color.gold);
        onTimeClick();
    }

    public void onPhotoClick() {
        ((CameraFragment) getFragment()).takePhotoIntializer(delay);
    }

}
