package com.example.randomchatapplication.ui.profiles.profile_details;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfileDetailsFragmentBinding;
import com.example.randomchatapplication.databinding.ProfileDetailsImageViewBinding;
import com.example.randomchatapplication.databinding.ProfileHobbyViewBinding;
import com.example.randomchatapplication.helpers.ScreenHelper;
import com.example.randomchatapplication.interfaces.ImageClickListener;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.models.Photo;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.ui.profiles.profile_image_gallery.ProfileImageGalleryFragment;

public class ProfileDetailsViewModel extends BaseViewModel implements ImageClickListener {

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
        LinearLayout linearLayout = ((ProfileDetailsFragmentBinding) getBinding()).profileDetailsHobbiesContainer;
        LinearLayout hobbyLayout;
        for (Hobby hobby : profile.getZainteresowania()) {
            hobbyLayout = (LinearLayout) LayoutInflater.from(getFragment().getContext()).inflate(R.layout.profile_hobby_view, linearLayout, false);
            ProfileHobbyViewBinding binding = ProfileHobbyViewBinding.bind(hobbyLayout);
            ProfileDetailsHobbyViewModel viewModel = new ProfileDetailsHobbyViewModel();
            binding.setViewModel(viewModel);
            viewModel.init(hobby);
            linearLayout.addView(hobbyLayout);
        }

        LinearLayout imagesContainer = ((ProfileDetailsFragmentBinding)getBinding()).profileDetailsImagesContainer;
        CardView imageOnContainer;
        for(Photo photo: profile.getPhotos()){
            imageOnContainer = (CardView) LayoutInflater.from(getFragment().getContext()).inflate(R.layout.profile_details_image_view, imagesContainer, false);
            ProfileDetailsImageViewBinding binding = ProfileDetailsImageViewBinding.bind(imageOnContainer);
            ProfileDetailsImageViewModel viewModel = new ProfileDetailsImageViewModel();
            binding.setViewModel(viewModel);
            viewModel.init(photo, this);
            imagesContainer.addView(imageOnContainer);
        }
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

    @Override
    public void onImageClick(Photo photo) {
        getNavigator().attachAdd(ProfileImageGalleryFragment.newInstance(profile.getPhotos(), photo), ProfileImageGalleryFragment.TAG);
    }
}
