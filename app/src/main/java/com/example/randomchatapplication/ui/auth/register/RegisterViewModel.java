package com.example.randomchatapplication.ui.auth.register;

import android.content.Intent;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.activites.profile_creation.CreateProfileActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.WebViewHelper;

public class RegisterViewModel extends BaseViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<String> htmlEffect = new ObservableField<>(WebViewHelper.MOVING_BORDER_TYPE);

    public void init(){

    }

    public void onNextClick(){
        getActivity().startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
    }
    public void onBackClick(){
        getActivity().onBackPressed();
    }
}
