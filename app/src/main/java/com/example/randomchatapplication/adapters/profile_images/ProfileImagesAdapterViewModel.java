package com.example.randomchatapplication.adapters.profile_images;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.Photo;

public class ProfileImagesAdapterViewModel extends BaseAdapterViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    private Photo photo;


    public void onImageClick(){

    }

    @Override
    public void init(Object[] values) {
        photo = (Photo) values[0];
        Log.d("init: ", "halo");
        imageUrl.set(photo.getPhoto());
    }
}
