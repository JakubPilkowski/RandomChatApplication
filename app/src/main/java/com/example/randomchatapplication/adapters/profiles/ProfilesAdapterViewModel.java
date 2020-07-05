package com.example.randomchatapplication.adapters.profiles;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.models.Profile;

public class ProfilesAdapterViewModel extends BaseAdapterViewModel {

    private Profile profile;
    public ObservableField<String> imageUrl = new ObservableField<>();
    @Override
    public void init(Object[] values) {
        profile = (Profile) values[0];
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
    }
}
