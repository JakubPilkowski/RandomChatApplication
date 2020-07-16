package com.example.randomchatapplication.ui.profiles.profile_details;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.interfaces.ImageClickListener;
import com.example.randomchatapplication.models.Photo;

public class ProfileDetailsImageViewModel {

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
