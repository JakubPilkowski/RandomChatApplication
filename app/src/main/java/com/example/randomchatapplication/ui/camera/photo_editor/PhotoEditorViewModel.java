package com.example.randomchatapplication.ui.camera.photo_editor;

import android.media.Image;

import androidx.camera.core.ImageProxy;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;

public class PhotoEditorViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableInt statusBarHeight = new ObservableInt();
    public ObservableInt navigationHeight = new ObservableInt();
    public ObservableField<Image> image = new ObservableField<>();

    private ImageProxy imageProxy;

    public void init(ImageProxy imageProxy){
        this.imageProxy = imageProxy;
        image.set(imageProxy.getImage());
        int windowNavigationSize = ScreenHelper.getNavBarHeight(getFragment().getContext());
        int statusBarHeight = ScreenHelper.getStatusBarHeight(getFragment().getContext());
        navigationHeight.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(12, getActivity().getApplicationContext())));
        this.statusBarHeight.set((int) (statusBarHeight + DimensionsHelper.convertDpToPixel(6, getActivity().getApplicationContext())));
    }
}
