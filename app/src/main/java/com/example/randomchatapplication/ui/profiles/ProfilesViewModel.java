package com.example.randomchatapplication.ui.profiles;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.adapters.profiles.ProfilesAdapter;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.helpers.CubeInPageTransformer;
import com.example.randomchatapplication.models.Profile;

import java.util.ArrayList;

public class ProfilesViewModel extends BaseViewModel {

    public ObservableField<ProfilesAdapter> adapter = new ObservableField<>();
    private ProfilesAdapter profilesAdapter;
    public void init() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getFragment().getContext(),LinearLayoutManager.HORIZONTAL, false);
        ViewPager2 viewPager2 = ((ProfilesFragmentBinding)getBinding()).profilesFragmentViewpager;

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        profilesAdapter = new ProfilesAdapter();
        profilesAdapter.setActivity(getActivity());
        viewPager2.setAdapter(profilesAdapter);
        viewPager2.setPageTransformer(new CubeInPageTransformer());
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        profilesAdapter.setItems(profiles);
    }
    // TODO: Implement the ViewModel
}
