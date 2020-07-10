package com.example.randomchatapplication.ui.profiles.profile_details;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.models.Profile;

public class ProfileDetailsViewModel extends BaseViewModel {

    private Profile profile;
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");
    public ObservableField<String> voivodeship = new ObservableField<>();
    public ObservableField<String> plec = new ObservableField<>("");
    public ObservableField<String> gender = new ObservableField<>("");
    public ObservableField<String> target = new ObservableField<>("");
    public ObservableField<String> motto = new ObservableField<>("");
    public ObservableField<Drawable> plecDrawable = new ObservableField<>();


    public ObservableInt closeButtonMarginTop = new ObservableInt();

    public void init(Profile profile) {


        this.profile = profile;
        closeButtonMarginTop.set(ScreenHelper.getStatusBarHeight(getActivity().getApplicationContext()));
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
        name.set(profile.getName());
        String ageSuffix;
        if (profile.getAge() % 10 >= 2 && profile.getAge() % 10 <= 4) {
            ageSuffix = "lata";
        } else {
            ageSuffix = "lat";
        }
        age.set(profile.getAge() + ageSuffix);
        description.set(profile.getDescription());
        city.set(profile.getMiasto());
        voivodeship.set(profile.getWojewództwo());
        plec.set(profile.getPłeć());
        plecDrawable();
        gender.set(profile.getOrientacja());
        target.set(profile.getCel());
        motto.set(profile.getMotto());
    }

    private void plecDrawable() {

        if (plec.get() != null)
            switch (plec.get()) {
            case "Mężczyzna":
                plecDrawable.set(getActivity().getResources().getDrawable(R.drawable.ic_male));
                break;
            case "Kobieta":
                plecDrawable.set(getActivity().getResources().getDrawable(R.drawable.ic_female));
                break;
            default:
                plecDrawable.set(getActivity().getResources().getDrawable(R.drawable.ic_question));
        }
    }


    public void onBackPress() {
        getActivity().onBackPressed();
    }
}
