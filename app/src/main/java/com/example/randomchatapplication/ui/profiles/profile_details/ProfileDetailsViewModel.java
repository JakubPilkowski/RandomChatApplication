package com.example.randomchatapplication.ui.profiles.profile_details;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.models.Profile;

public class ProfileDetailsViewModel extends BaseViewModel {

    private Profile profile;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableInt closeButtonMarginTop = new ObservableInt();

    public void init(Profile profile){
        this.profile = profile;
        closeButtonMarginTop.set(ScreenHelper.getStatusBarHeight(getActivity().getApplicationContext()));
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
        name.set(profile.getName());
        String ageSuffix;
        if(profile.getAge() % 10 >= 2 && profile.getAge() % 10 <= 4){
            ageSuffix = "lata";
        }
        else{
            ageSuffix = "lat";
        }
        age.set(profile.getAge()+ ageSuffix);
        description.set(profile.getDescription());
    }
    public void onBackPress(){
        getActivity().onBackPressed();
    }
}
