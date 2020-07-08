package com.example.randomchatapplication.adapters.profiles;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.databinding.SingleProfileItemBinding;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.ui.profiles.profile.ProfilesFragment;
import com.example.randomchatapplication.ui.profiles.profile_details.ProfileDetailsFragment;

public class ProfilesAdapterViewModel extends BaseAdapterViewModel {

    private Profile profile;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableBoolean fillViewport = new ObservableBoolean(true);
    public ObservableBoolean expanded = new ObservableBoolean(false);
    public Activity activity;
    public SingleProfileItemBinding binding;
    private ProfileDetailsFragment profileDetailsFragment;
    @Override
    public void init(Object[] values) {
        profile = (Profile) values[0];
        activity = (Activity) values[1];
        binding = (SingleProfileItemBinding) values[2];
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
        name.set(profile.getName());
        age.set(String.valueOf(profile.getAge()));
        String getShortCutProfile;
        if (profile.getDescription().length() > 25)
            getShortCutProfile = profile.getDescription().substring(0, 24) + "...";
        else getShortCutProfile = profile.getDescription();
        description.set(getShortCutProfile);
        profileDetailsFragment = ProfileDetailsFragment.newInstance(profile);
    }


    public void initTransition() {

        if (!expanded.get()) {
            expanded.set(true);
            MainActivity mainActivity = (MainActivity) activity;
            MotionLayout mainMotionLayout = mainActivity.binding.mainCoordinator;
            mainMotionLayout.transitionToEnd();
            Window window = mainActivity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ProfilesFragment profilesFragment = (ProfilesFragment) mainActivity.getCurrentFragment();
            MotionLayout motionLayout = ((ProfilesFragmentBinding) profilesFragment.getBinding()).profilesFragmentMotionLayout;
            motionLayout.setTransition(R.id.profiles_start_transition, R.id.profiles_end_transition);
            motionLayout.transitionToEnd();
            MotionLayout singleProfileMotion = binding.singleProfileMotionLayout;
            singleProfileMotion.setTransition(R.id.single_profile_trans_start, R.id.single_profile_trans_end);
            singleProfileMotion.transitionToEnd();
            singleProfileMotion.setTransitionListener(new MotionLayout.TransitionListener() {
                @Override
                public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

                }

                @Override
                public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
                }

                @Override
                public void onTransitionCompleted(MotionLayout motionLayout, int i) {

                    mainActivity.navigator.attach(profileDetailsFragment, ProfileDetailsFragment.TAG);
                }

                @Override
                public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

                }
            });
        }
    }


}
