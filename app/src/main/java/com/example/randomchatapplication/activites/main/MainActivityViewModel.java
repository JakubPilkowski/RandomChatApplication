package com.example.randomchatapplication.activites.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

import com.example.randomchatapplication.R;

import com.example.randomchatapplication.activites.camera.CameraActivity;
import com.example.randomchatapplication.api.BaseCallback;
import com.example.randomchatapplication.api.MockyConnection;
import com.example.randomchatapplication.api.RxJavaCallback;
import com.example.randomchatapplication.api.responses.ProfilesResponse;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ActivityMainBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.ui.profiles.ProfilesFragment;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;


public class MainActivityViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>();
    public ArrayList<Profile> profiles = new ArrayList<>();


    private MainActivity activity;

    public void init() {
        activity = ((MainActivity) getActivity());
        ProgressDialogManager.get().show();
        MockyConnection.get().getProfiles(callback);
    }

    private BaseCallback<ProfilesResponse> callback = new BaseCallback<ProfilesResponse>() {
        @Override
        public void onSuccess(ProfilesResponse profilesResponse) {
            if(profilesResponse.getProfiles()!=null)
            {
                profiles.clear();
                profiles.addAll(profilesResponse.getProfiles());
                ProfilesFragment fragment = (ProfilesFragment) ((MainActivity)getActivity()).getSupportFragmentManager().findFragmentByTag(ProfilesFragment.TAG);
                if (fragment != null)
                    fragment.viewModel.setProfiles(profiles);
            }
            ProgressDialogManager.get().dismiss();
        }

        @Override
        public void onError(String message) {
            Log.d("MainActivityVM", message);
            ProgressDialogManager.get().dismiss();
        }


    };

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void onClick() {
        Intent intent = new Intent(getActivity().getApplicationContext(), CameraActivity.class);
        getActivity().startActivity(intent);
    }

}
