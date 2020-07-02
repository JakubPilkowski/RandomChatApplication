package com.example.randomchatapplication.ui.camera.photo_editor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.camera.core.ImageProxy;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.PhotoEditorFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;

import static com.example.randomchatapplication.ui.earn_points.EarnPointsFragment.TAG;

public class PhotoEditorViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableField<ImageProxy> imageProxy = new ObservableField<>();


    public void init(ImageProxy imageProxy){
        this.imageProxy.set(imageProxy);
        int windowNavigationSize = ScreenHelper.getNavBarHeight(getFragment().getContext());
        int statusBarHeight = ScreenHelper.getStatusBarHeight(getFragment().getContext());
        navigationHeight.set((int) (windowNavigationSize));
        this.statusBarHeight.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(6, getActivity().getApplicationContext())));
    }

    public void onBackPress(){
        getActivity().onBackPressed();
    }

    public void onEditClick(){

    }
}
