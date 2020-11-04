package com.example.randomchatapplication.activites.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.activites.authentication.AuthActivity;
import com.example.randomchatapplication.adapters.profiles.ProfilesAdapter;
import com.example.randomchatapplication.adapters.profiles.ProfilesAdapterViewModel;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityMainBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.UserPreferences;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.Profile;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.account.AccountFragment;
import com.example.randomchatapplication.ui.chats.ChatsFragment;
import com.example.randomchatapplication.ui.earn_points.EarnPointsFragment;
import com.example.randomchatapplication.ui.profiles.profile.ProfilesFragment;
import com.example.randomchatapplication.ui.profiles.profile_details.ProfileDetailsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements Providers, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void initActivity(ActivityMainBinding binding) {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navigator.attach(new ProfilesFragment(), ProfilesFragment.TAG);
        binding.setViewModel(viewModel);
        viewModel.setProviders(this);
        viewModel.init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.MainTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean lightStatusBar() {
        return true;
    }

    @Override
    protected void onResume() {
//        if (!UserPreferences.getINSTANCE().hasUser()) {
            finish();
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
//        } else {
//            UserPreferences.getINSTANCE().clear();
//        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        stopAnimationIfExist();
        if (getCurrentFragment() instanceof ProfileDetailsFragment) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            binding.mainCoordinator.transitionToStart();
        }
        if (getCurrentFragment() instanceof ProfilesFragment || getCurrentFragment() instanceof ChatsFragment
                || getCurrentFragment() instanceof EarnPointsFragment || getCurrentFragment() instanceof AccountFragment) {
            updateBottomNavIcon();
        }
        super.onBackPressed();
    }

    private void updateBottomNavIcon() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            String lastFragment = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 2).getName();
            switch (lastFragment) {
                case ProfilesFragment.TAG:
                    binding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
                    break;
                case ChatsFragment.TAG:
                    binding.bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    break;
                case EarnPointsFragment.TAG:
                    binding.bottomNavigationView.getMenu().getItem(3).setChecked(true);
                    break;
                case AccountFragment.TAG:
                    binding.bottomNavigationView.getMenu().getItem(4).setChecked(true);
                    break;
            }
        } else binding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }

    private void stopAnimationIfExist() {
        if (getCurrentFragment() instanceof ProfilesFragment) {
            ProfilesFragment profilesFragment = (ProfilesFragment) getCurrentFragment();
            ProfilesAdapter profilesAdapter = (ProfilesAdapter) profilesFragment.binding.profilesFragmentViewpager.getAdapter();
            int index = profilesFragment.binding.profilesFragmentViewpager.getCurrentItem();
            ProfilesAdapterViewModel viewModel = (ProfilesAdapterViewModel) profilesAdapter.getViewModel(index);
            viewModel.binding.profileResultView.stopAnimations();
        }
    }


    @Override
    protected Class<MainActivityViewModel> getViewModel() {
        return MainActivityViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.main_activity_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public ViewDataBinding getActivityOrFragmentBinding() {
        return getCurrentFragment().getBinding();
    }

    @Override
    public BaseFragment getFragment() {
        return getCurrentFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        stopAnimationIfExist();
        switch (item.getItemId()) {
            case R.id.nav_profiles:
                if (!(getCurrentFragment() instanceof ProfilesFragment))
                    navigator.attach(ProfilesFragment.newInstance(), ProfilesFragment.TAG);
                break;
            case R.id.nav_chats:
                if (!(getCurrentFragment() instanceof ChatsFragment))
                    navigator.attach(ChatsFragment.newInstance(), ChatsFragment.TAG);
                break;
            case R.id.nav_points:
                if (!(getCurrentFragment() instanceof EarnPointsFragment))
                    navigator.attach(EarnPointsFragment.newInstance(), EarnPointsFragment.TAG);
                break;
            case R.id.nav_account:
                if (!(getCurrentFragment() instanceof AccountFragment))
                    navigator.attach(AccountFragment.newInstance(), AccountFragment.TAG);
                break;
            default:
                return true;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        ProgressDialogManager.get().dismiss();
        super.onDestroy();
    }
}
