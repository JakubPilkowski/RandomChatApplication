package com.example.randomchatapplication.activites.profile_creation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

    @Override
    protected void initActivity(ActivityCreateProfileBinding binding) {
        viewModel.setProviders(this);
        binding.setViewModel(viewModel);
        ProgressDialogManager.init(this);
        SelectViewDialogManager.init(this);
        viewModel.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == hobbyRequest){
            if(resultCode== RESULT_OK){
                if(data!=null) {
                    ArrayList<Hobby> hobbies = data.getParcelableArrayListExtra("hobbies");
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("CreateProfileFragment4");
                    SearchViewModel viewModel = (SearchViewModel) ((CreateProfileFragment) fragment).viewModel.getSearchViewModel();
                    viewModel.updateItems(hobbies);
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
    public void onBackPressed() {
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
