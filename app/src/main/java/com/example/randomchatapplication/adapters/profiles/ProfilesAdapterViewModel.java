package com.example.randomchatapplication.adapters.profiles;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.base.BaseViewHolder;
import com.example.randomchatapplication.models.Profile;

public class ProfilesAdapterViewModel extends BaseAdapterViewModel {

    private Profile profile;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    @Override
    public void init(Object[] values) {
        profile = (Profile) values[0];
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
        name.set(profile.getName());
        age.set(String.valueOf(profile.getAge()));
        String getShortCutProfile ;
        if(profile.getDescription().length() > 25)
            getShortCutProfile = profile.getDescription().substring(0, 24) + "...";
        else getShortCutProfile = profile.getDescription();
        description.set(getShortCutProfile);
    }
}
