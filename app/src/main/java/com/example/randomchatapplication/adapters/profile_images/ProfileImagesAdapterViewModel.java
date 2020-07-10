package com.example.randomchatapplication.adapters.profile_images;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.models.Photo;

public class ProfileImagesAdapterViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    private Photo photo;

    public void init(Photo photo) {
        this.photo = photo;
        imageUrl.set(photo.getPhoto());
    }

    public void onImageClick(){

    }
}
