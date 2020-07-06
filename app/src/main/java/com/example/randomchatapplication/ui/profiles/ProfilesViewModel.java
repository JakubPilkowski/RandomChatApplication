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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getFragment().getContext(),LinearLayoutManager.HORIZONTAL, false);
        ViewPager2 viewPager2 = ((ProfilesFragmentBinding)getBinding()).profilesFragmentViewpager;
//        viewPager2.setLayoutManager(layoutManager);
//        viewPager2.setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        profilesAdapter = new ProfilesAdapter();
        Log.d("Profile", "ProfilesViewModel");
//        adapter.set(profilesAdapter);
        viewPager2.setAdapter(profilesAdapter);
        viewPager2.setPageTransformer(new CubeInPageTransformer());
//        viewPager2.setUserInputEnabled(false);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
//        LinearSnapHelper snapHelper = new LinearSnapHelper() {
//            @Override
//            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
//                View centerView = findSnapView(layoutManager);
//                if (centerView == null)
//                    return RecyclerView.NO_POSITION;
//
//                int position = layoutManager.getPosition(centerView);
//                int targetPosition = -1;
//                if (layoutManager.canScrollHorizontally()) {
//                    if (velocityX < 0) {
//                        targetPosition = position - 1;
//                    } else {
//                        targetPosition = position + 1;
//                    }
//                }
//
//                if (layoutManager.canScrollVertically()) {
//                    if (velocityY < 0) {
//                        targetPosition = position - 1;
//                    } else {
//                        targetPosition = position + 1;
//                    }
//                }
//
//                final int firstItem = 0;
//                final int lastItem = layoutManager.getItemCount() - 1;
//                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
//                return targetPosition;
//            }
//        };
//        snapHelper.attachToRecyclerView(viewPager2);
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        profilesAdapter.setItems(profiles);
    }
    // TODO: Implement the ViewModel
}
