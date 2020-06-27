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
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.randomchatapplication.R;
import com.example.randomchatapplication.base.BaseActivity;
import com.example.randomchatapplication.base.BaseFragment;
import com.example.randomchatapplication.databinding.ActivityCreateProfileBinding;
import com.example.randomchatapplication.helpers.ProgressDialogManager;
import com.example.randomchatapplication.helpers.ScreenHelper;
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



    private int windowNavigationSize;
    private int statusBarHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        windowNavigationSize = ScreenHelper.getNavBarHeight(getApplicationContext());
        statusBarHeight = ScreenHelper.getStatusBarHeight(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
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
//                    ArrayList<Hobby> hobbies = data.getParcelableArrayListExtra("hobbies");
//                    Fragment fragment = viewModel.viewPagerAdapter.get().getItem(3);
//                    SearchViewModel viewModel = (SearchViewModel) ((CreateProfileFragment) fragment).viewModel.getSearchViewModel();
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
