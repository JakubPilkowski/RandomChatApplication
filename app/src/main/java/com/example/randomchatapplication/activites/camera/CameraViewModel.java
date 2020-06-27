package com.example.randomchatapplication.activites.camera;

import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.DimensionsHelper;

public class CameraViewModel extends BaseViewModel {

    public ObservableInt navigationHeight = new ObservableInt();

    public void init(int windowNavigationSize){
        navigationHeight.set((int) (windowNavigationSize + DimensionsHelper.convertDpToPixel(16, getActivity().getApplicationContext())));
    }


}
