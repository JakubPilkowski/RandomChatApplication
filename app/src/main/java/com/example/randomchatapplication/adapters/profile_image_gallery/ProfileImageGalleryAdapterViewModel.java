package com.example.randomchatapplication.adapters.profile_image_gallery;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.models.Photo;

public class ProfileImageGalleryAdapterViewModel extends BaseAdapterViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    private Photo photo;

    @Override
    public void init(Object[] values) {
        photo = (Photo) values[0];
        imageUrl.set(photo.getPhoto());
    }
}
