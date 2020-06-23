package com.example.randomchatapplication.activites.profile_creation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.SelectViewDialogManager;
import com.example.randomchatapplication.interfaces.Providers;
import com.example.randomchatapplication.models.Hobby;
import com.example.randomchatapplication.models.ViewInfo;
import com.example.randomchatapplication.navigation.Navigator;
import com.example.randomchatapplication.ui.create_profile.fields.SearchViewModel;
import com.example.randomchatapplication.ui.create_profile.profile.CreateProfileFragment;

import java.util.ArrayList;


public class CreateProfileActivity extends BaseActivity<ActivityCreateProfileBinding,CreateProfileViewModel> implements Providers {


    public static final int hobbyRequest = 1001;


    public int getNavBarHeight(Context c) {
        int result = 0;
        boolean hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if(!hasMenuKey && !hasBackKey) {
            Resources resources = c.getResources();

            int orientation = resources.getConfiguration().orientation;
            int resourceId;
            if (isTablet(c)){
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
            }  else {
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_width", "dimen", "android");
            }

            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }


    private boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private int windowNavigationSize;
    private int statusBarHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        windowNavigationSize = getNavBarHeight(getApplicationContext());
        statusBarHeight = getStatusBarHeight();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void initActivity(ActivityCreateProfileBinding binding) {
        SelectViewDialogManager.init(this);
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        viewModel.init(windowNavigationSize, statusBarHeight);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == hobbyRequest){
            if(resultCode== RESULT_OK){
                if(data!=null) {
                    ArrayList<Hobby> hobbies = data.getParcelableArrayListExtra("hobbies");
                    Fragment fragment = viewModel.viewPagerAdapter.get().getItem(3);
                    SearchViewModel viewModel = (SearchViewModel) ((CreateProfileFragment) fragment).viewModel.getSearchViewModel();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected Class<CreateProfileViewModel> getViewModel() {
        return CreateProfileViewModel.class;
    }

    @Override
    public int getIdFragmentContainer() {
        return R.id.create_profile_container;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_create_profile;
    }

    @Override
    public Activity getActivity() {
        return this;
    }


    @Override
    public boolean lightStatusBar() {
        return false;
    }

    @Override
    public void onBackPressed() {
//        if(SelectViewDialogManager.get().getDialog().isShowing())
//            SelectViewDialogManager.get().dismiss();
//        else
            super.onBackPressed();
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
        return getCurrentFragment().binding;
    }

    @Override
    public BaseFragment getFragment() {
        return getCurrentFragment();
    }



}
