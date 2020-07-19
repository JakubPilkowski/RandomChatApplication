package com.example.randomchatapplication.activites.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        if (!UserPreferences.getINSTANCE().hasUser()) {
            finish();
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
        } else {
//            UserPreferences.getINSTANCE().clear();
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        stopAnimationIfExist();
        if(getCurrentFragment() instanceof ProfileDetailsFragment)
        {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            binding.mainCoordinator.transitionToStart();
        }
        super.onBackPressed();
    }

    private void stopAnimationIfExist() {
        if(getCurrentFragment() instanceof ProfilesFragment){
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
                navigator.attach(ProfilesFragment.newInstance(), ProfilesFragment.TAG);
                break;
            case R.id.nav_chats:
                navigator.attach(ChatsFragment.newInstance(), ChatsFragment.TAG);
                break;
            case R.id.nav_points:
                navigator.attach(EarnPointsFragment.newInstance(), EarnPointsFragment.TAG);
                break;
            case R.id.nav_account:
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
