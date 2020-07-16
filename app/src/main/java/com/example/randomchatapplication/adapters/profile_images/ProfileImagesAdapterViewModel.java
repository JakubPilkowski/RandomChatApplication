package com.example.randomchatapplication.adapters.profile_images;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.interfaces.ImageClickListener;
import com.example.randomchatapplication.models.Photo;

public class ProfileImagesAdapterViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    private Photo photo;
    private ImageClickListener listener;

    public void onImageClick(){
        listener.onImageClick(photo);
    }

    public void init(Photo photo, ImageClickListener listener) {
        this.photo = photo;
        this.listener = listener;
        imageUrl.set(photo.getPhoto());
    }
}
