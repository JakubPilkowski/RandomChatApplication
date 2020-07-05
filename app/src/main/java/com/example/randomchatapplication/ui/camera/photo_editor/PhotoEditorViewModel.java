package com.example.randomchatapplication.ui.camera.photo_editor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import androidx.camera.core.ImageProxy;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.custom_views.EditableImageView;
import com.example.randomchatapplication.databinding.PhotoEditorFragmentBinding;
import com.example.randomchatapplication.helpers.ColorPickerAlert;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ImageHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.interfaces.ColorPickerListener;

public class PhotoEditorViewModel extends BaseViewModel implements ColorPickerListener {
    //Tooltipy
    public ObservableInt backTooltip = new ObservableInt(R.string.back);
    public ObservableInt editorTooltip = new ObservableInt(R.string.edition);
    public ObservableInt cleanTooltip = new ObservableInt(R.string.clean);
    public ObservableInt backBrushTooltip = new ObservableInt(R.string.back_brush);
    public ObservableInt brushTicknessTooltip = new ObservableInt(R.string.brush_tickness);
    public ObservableInt brushColorTooltip = new ObservableInt(R.string.brush_color);
    public ObservableInt brushTickness10Tooltip = new ObservableInt(R.string.brush_tickness_10);
    public ObservableInt brushTickness25Tooltip = new ObservableInt(R.string.brush_tickness_25);
    public ObservableInt brushTickness40Tooltip = new ObservableInt(R.string.brush_tickness_40);
    public ObservableInt brushTickness55Tooltip = new ObservableInt(R.string.brush_tickness_55);
    public ObservableInt acceptEditingTooltip = new ObservableInt(R.string.go_next);


    //Reszta

    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableField<ImageProxy> imageProxy = new ObservableField<>();
    public ObservableInt editingButtonsMarginTop = new ObservableInt();
    public ObservableInt brushTicknessButtonsMarginTop = new ObservableInt();
    public ObservableBoolean isDrawingEnabled = new ObservableBoolean(false);
    public ObservableBoolean ticknessState = new ObservableBoolean(false);
    public ObservableBoolean ticknessButtonsVisibility = new ObservableBoolean(false);
    public ObservableInt brushColor = new ObservableInt(R.color.colorAccent);

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
        ColorPickerAlert.get().show(this);
    }

    @Override
    public void onColorPicked(int color) {
        brushColor.set(color);
        imageView.setSelectedColor(color);
    }

    public void onEndEditingClick(){
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight()
        , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        imageView.draw(canvas);
        saveBitmapToGallery(bitmap);
        getActivity().finish();
    }
    private void saveBitmapToGallery(Bitmap bitmap){

        String filename = "tinderdlaubogichphoto-"+System.currentTimeMillis()+".png";
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, filename , "Image");
    }

}
