package com.example.randomchatapplication.ui.profiles.profile_details;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.adapters.profile_hobbies.ProfileHobbiesAdapter;
import com.example.randomchatapplication.adapters.profile_hobbies.ProfileHobbiesAdapterViewModel;
import com.example.randomchatapplication.adapters.profile_images.ProfileImagesAdapter;
import com.example.randomchatapplication.adapters.profiles.ProfilesAdapterViewModel;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.databinding.ProfileHobbyViewBinding;
import com.example.randomchatapplication.helpers.DimensionsHelper;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.models.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    public ObservableField<ProfileHobbiesAdapter> profileHobbiesAdapter = new ObservableField<>();
    private ProfileHobbiesAdapter adapter;
    private RecyclerView hobbiesRecyclerView;
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
        LinearLayout linearLayout = ((ProfileDetailsFragmentBinding)getBinding()).profileDetailsHobbiesContainer;
        LinearLayout hobbyLayout;
        for (Hobby hobby : profile.getZainteresowania()) {
            hobbyLayout = (LinearLayout) LayoutInflater.from(getFragment().getContext()).inflate(R.layout.profile_hobby_view, linearLayout, false);
            ProfileHobbyViewBinding binding = ProfileHobbyViewBinding.bind(hobbyLayout);
            ProfileHobbiesAdapterViewModel viewModel = new ProfileHobbiesAdapterViewModel();
            binding.setViewModel(viewModel);
            viewModel.init(hobby);
            linearLayout.addView(hobbyLayout);
        }
        GridView imagesGridView = ((ProfileDetailsFragmentBinding)getBinding()).imagesGridView;
//        imagesGridView.setAdapter(new ProfileImagesAdapter(imagesGridView.getContext(), profile.getPhotos()));
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
