package com.example.randomchatapplication.ui.auth.login;

import android.content.Intent;

import androidx.databinding.ObservableField;

import com.example.randomchatapplication.activites.main.MainActivity;
import com.example.randomchatapplication.base.BaseViewModel;
import com.example.randomchatapplication.helpers.WebViewHelper;
import com.example.randomchatapplication.ui.auth.register.RegisterFragment;

public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> htmlEffect = new ObservableField<>(WebViewHelper.MOVING_BORDER_TYPE);

    public void init(){

    }

    public void onLoginClick(){
        getActivity().finish();
        getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
    }
    public void onRegisterClick(){
        getNavigator().attach(RegisterFragment.newInstance(), RegisterFragment.TAG);
    }



}
