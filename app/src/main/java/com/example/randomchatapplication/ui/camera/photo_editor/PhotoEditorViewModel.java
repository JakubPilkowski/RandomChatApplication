package com.example.randomchatapplication.ui.camera.photo_editor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.camera.core.ImageProxy;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.custom_views.EditableImageView;
import com.example.randomchatapplication.databinding.PhotoEditorFragmentBinding;
import com.example.randomchatapplication.helpers.ColorPickerAlert;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;

import static com.example.randomchatapplication.ui.earn_points.EarnPointsFragment.TAG;

public class PhotoEditorViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableField<ImageProxy> imageProxy = new ObservableField<>();
    public ObservableInt editingButtonsMarginTop = new ObservableInt();
    public ObservableInt brushTicknessButtonsMarginTop = new ObservableInt();
    public ObservableInt brushColorButtonsMarginTop = new ObservableInt();
    public ObservableBoolean isDrawingEnabled = new ObservableBoolean(false);
    public ObservableBoolean ticknessState = new ObservableBoolean(false);
    public ObservableBoolean ticknessButtonsVisibility = new ObservableBoolean(false);
    public ObservableInt brushColor = new ObservableInt(R.color.gold);

    private Button clearPaintingButton;
    private Button backPaintingButton;
    private Button brushTicknessButton;
    private Button brushColorButton;
    private Button brushTickness10;
    private Button brushTickness15;
    private Button brushTickness20;
    private Button brushTickness25;

    public EditableImageView imageView;
    private boolean state = false;
    private float verticalTransitionDimensionFirst;
    private float verticalTransitionDimensionOthers;
    private float horizontalTransitionDimension;
    public void init(ImageProxy imageProxy){
        this.imageProxy.set(imageProxy);
        int windowNavigationSize = ScreenHelper.getNavBarHeight(getFragment().getContext());
        int statusBarHeight = ScreenHelper.getStatusBarHeight(getFragment().getContext());
        navigationHeight.set(windowNavigationSize);
        this.statusBarHeight.set(statusBarHeight);
        editingButtonsMarginTop.set((int) DimensionsHelper.convertDpToPixel(13, getFragment().getContext()));
        clearPaintingButton = ((PhotoEditorFragmentBinding)getBinding()).clearPaintingButton;
        backPaintingButton = ((PhotoEditorFragmentBinding)getBinding()).backPaintingButton;
        brushTicknessButton = ((PhotoEditorFragmentBinding)getBinding()).brushTicknessButton;
        brushColorButton = ((PhotoEditorFragmentBinding)getBinding()).brushColorButton;
        brushTickness10 = ((PhotoEditorFragmentBinding)getBinding()).brushTickness10;
        brushTickness15 = ((PhotoEditorFragmentBinding)getBinding()).brushTickness15;
        brushTickness20 = ((PhotoEditorFragmentBinding)getBinding()).brushTickness20;
        brushTickness25 = ((PhotoEditorFragmentBinding)getBinding()).brushTickness25;

        verticalTransitionDimensionFirst = DimensionsHelper.convertDpToPixel(60, getFragment().getContext());
        verticalTransitionDimensionOthers = DimensionsHelper.convertDpToPixel(53, getFragment().getContext());
        horizontalTransitionDimension = DimensionsHelper.convertDpToPixel(53,getFragment().getContext());
        imageView = ((PhotoEditorFragmentBinding)getBinding()).imageView;
        brushTicknessButtonsMarginTop.set((int) DimensionsHelper.convertDpToPixel(179, getFragment().getContext()));
    }

    public void onBackPress(){
        getActivity().onBackPressed();
    }

    public void onEditClick(){
        isDrawingEnabled.set(!isDrawingEnabled.get());
        imageView.setDrawingEnabled(isDrawingEnabled.get());
        if (!state) {
            openEditingButtons();
        } else {
            closeEditingButtons();
        }
        state = !state;
    }

    private void closeEditingButtons() {
        if(ticknessState.get()){
            closeBrushButtons();
            ticknessState.set(false);
            ticknessButtonsVisibility.set(false);
        }
        translateAnimation(0, 0, 0, 0, clearPaintingButton);
        translateAnimation(0, 0, 0, 0, backPaintingButton);
        translateAnimation(0, 0, 0, 0, brushTicknessButton);
        translateAnimation(0, 0, 0, 0, brushColorButton);

    }

    private void openEditingButtons() {
        translateAnimation(0, 0, 0, verticalTransitionDimensionFirst, clearPaintingButton);
        translateAnimation(0, 0, 0, verticalTransitionDimensionFirst+verticalTransitionDimensionOthers, backPaintingButton);
        translateAnimation(0, 0, 0, verticalTransitionDimensionFirst+verticalTransitionDimensionOthers*2, brushTicknessButton);
        translateAnimation(0, 0, 0, verticalTransitionDimensionFirst+ verticalTransitionDimensionOthers*3, brushColorButton);
    }

    private void translateAnimation(float fromXDelta, float xToDelta, float fromYDelta, float yToDelta, Button button) {
        button.animate().translationX(xToDelta - fromXDelta).translationY(yToDelta - fromYDelta).setDuration(250).start();
    }

    public void onClearPainting(){
        imageView.clearCanvas();
    }

    public void onBackPaiting(){
        imageView.clearLastPainting();
    }

    public void onBrushTicknessClick(){
        if(!ticknessState.get()){
            openBrushButtons();
        }
        else{
            closeBrushButtons();
        }
        ticknessState.set(!ticknessState.get());
    }

    private void closeBrushButtons() {
        translateAnimation(0, 0, 0, 0, brushTickness10);
        translateAnimation(0, 0, 0, 0, brushTickness15);
        translateAnimation(0, 0, 0, 0, brushTickness20);
        translateAnimation(0, 0, 0, 0, brushTickness25);
        new Handler().postDelayed(() -> ticknessButtonsVisibility.set(false), 250);
    }

    private void openBrushButtons() {
        ticknessButtonsVisibility.set(true);
        translateAnimation(0, -horizontalTransitionDimension, 0, 0, brushTickness10);
        translateAnimation(0, -horizontalTransitionDimension*2,0,0, brushTickness15);
        translateAnimation(0, -horizontalTransitionDimension*3,0,0, brushTickness20);
        translateAnimation(0, -horizontalTransitionDimension*4,0,0, brushTickness25);
    }

    public void onBrushTickness10Click(){
        imageView.setStrokeWidth(10f);
        onBrushTicknessClick();
    }
    public void onBrushTickness15Click(){
        imageView.setStrokeWidth(25f);
        onBrushTicknessClick();
    }
    public void onBrushTickness20Click(){
        imageView.setStrokeWidth(40f);
        onBrushTicknessClick();
    }
    public void onBrushTickness25Click(){
        imageView.setStrokeWidth(55f);
        onBrushTicknessClick();
    }
    public void onBrushColorClick(){
        ColorPickerAlert.get().show();
    }


}
