package com.example.randomchatapplication.ui.profiles;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.adapters.profiles.ProfilesAdapter;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.databinding.ProfilesFragmentBinding;
import com.example.randomchatapplication.models.Profile;

import java.util.ArrayList;

public class ProfilesViewModel extends BaseViewModel {

    public ObservableField<ProfilesAdapter> adapter = new ObservableField<>();
    private ProfilesAdapter profilesAdapter;
    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getFragment().getContext(),LinearLayoutManager.HORIZONTAL, false);
        RecyclerView profilesRecyclerView = ((ProfilesFragmentBinding)getBinding()).profilesFragmentRecyclerView;
        profilesRecyclerView.setLayoutManager(layoutManager);
        profilesRecyclerView.setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING);

        profilesAdapter = new ProfilesAdapter();
        Log.d("Profile", "ProfilesViewModel");
        adapter.set(profilesAdapter);
        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(profilesRecyclerView);
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        profilesAdapter.setItems(profiles);
    }
    // TODO: Implement the ViewModel
}
