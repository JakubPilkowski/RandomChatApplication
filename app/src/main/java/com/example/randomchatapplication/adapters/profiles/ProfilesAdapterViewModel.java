package com.example.randomchatapplication.adapters.profiles;

import android.app.Activity;
import android.os.Handler;
import android.transition.Fade;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseAdapterViewModel;
import com.example.randomchatapplication.custom_views.ProfileResultView;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.databinding.SingleProfileItemBinding;
import com.example.randomchatapplication.helpers.DetailsTransition;
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
        if(!binding.declineProfileFab.isEnabled())
            binding.declineProfileFab.setEnabled(true);
        if(!binding.acceptProfileFab.isEnabled())
            binding.acceptProfileFab.setEnabled(true);
        imageUrl.set(profile.getPhotos().get(0).getPhoto());
        name.set(profile.getName());
        age.set(String.valueOf(profile.getAge()));
        String getShortCutProfile;
        if (profile.getDescription().length() > 25)
            getShortCutProfile = profile.getDescription().substring(0, 24) + "...";
        else getShortCutProfile = profile.getDescription();
        description.set(getShortCutProfile);
        profileDetailsFragment = ProfileDetailsFragment.newInstance(profile, activity.getApplicationContext());
        binding.profileResultView.setResultListener(new ProfileResultView.ResultListener() {
            @Override
            public void onResultAnimEnd() {
                MainActivity mainActivity = (MainActivity) activity;
                ProfilesFragment profilesFragment = (ProfilesFragment) mainActivity.getCurrentFragment();
                ViewPager2 viewPager2 = ((ProfilesFragmentBinding) profilesFragment.getBinding()).profilesFragmentViewpager;
                RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
                ProfilesAdapter adapter = (ProfilesAdapter) viewPager2.getAdapter();
                recyclerView.smoothScrollBy(viewPager2.getWidth(), 0, new LinearInterpolator(), 500);
                new Handler().postDelayed(() -> {
                    if(adapter.getItems().size()!=1){
                        adapter.removeItem(profile);
                    }
                  },500);
            }

            @Override
            public void onResultAnimStart() {
                binding.declineProfileFab.setEnabled(false);
                binding.acceptProfileFab.setEnabled(false);
            }
        });
    }

    public void onDeclineClick(){
        binding.profileResultView.onDeclineDrawing();
    }


    public void initTransition() {

        if (!expanded.get()) {
            expanded.set(true);
            MainActivity mainActivity = (MainActivity) activity;
            MotionLayout mainMotionLayout = mainActivity.binding.mainCoordinator;
            mainMotionLayout.transitionToEnd();
            Window window = mainActivity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(mainActivity.getResources().getColor(R.color.colorPrimary));
            ProfilesFragment profilesFragment = (ProfilesFragment) mainActivity.getCurrentFragment();
            MotionLayout motionLayout = ((ProfilesFragmentBinding) profilesFragment.getBinding()).profilesFragmentMotionLayout;
            motionLayout.setTransition(R.id.profiles_start_transition, R.id.profiles_end_transition);
            motionLayout.transitionToEnd();
            motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
                @Override
                public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

                }

                @Override
                public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

                }

                @Override
                public void onTransitionCompleted(MotionLayout motionLayout, int i) {

                }

                @Override
                public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

                }
            });

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
                    profileDetailsFragment.setSharedElementEnterTransition(new DetailsTransition());
                    profileDetailsFragment.setExitTransition(new Fade());
                    mainActivity.navigator.profileSharedTransition(profileDetailsFragment, ProfileDetailsFragment.TAG,
                            binding.profileImage, "profile details image");
                }

                @Override
                public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

                }
            });
        }
    }


}
